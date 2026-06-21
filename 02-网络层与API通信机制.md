# OldChat Android 客户端 — 网络层与 API 通信机制

> 本章深入分析客户端的网络通信架构，包括 HTTP 客户端封装、加密通信协议、WebSocket 实时推送和认证流程。

---

## 一、网络层架构总览

```
┌──────────────────────────────────────────────────┐
│                  UI 层                           │
│  Activity / Fragment 发起请求                     │
├──────────────────────────────────────────────────┤
│              请求封装层 (g0.d)                    │
│  d.S() / d.R() / d.U() 静态方法                  │
│  - 自动注入加密头                                │
│  - Token 刷新重试                                │
│  - 响应解析回调                                  │
├──────────────────────────────────────────────────┤
│             HTTP 工具层 (o0.H / o0.J)             │
│  - OkHttpClient 管理                             │
│  - 连接池 / 超时 / 重定向                        │
├──────────────────────────────────────────────────┤
│              加密层 (CryptoUtil)                  │
│  - ECDH 密钥交换                                 │
│  - AES-GCM 加密/解密                             │
│  - Base64 编码                                   │
├──────────────────────────────────────────────────┤
│            OkHttp 3.x Client                     │
│  - 连接池 / 拦截器 / 证书                        │
└──────────────────────────────────────────────────┘
```

## 二、HTTP 客户端封装

### 2.1 核心请求方法

HTTP 工具类 `g0.d` 提供静态方法封装：

| 方法 | 用途 | 签名 |
|------|------|------|
| `d.S()` | POST JSON 请求 | `(String path, JSONObject body, String token, d.i callback)` |
| `d.R()` | GET 请求 | `(String path, String token, d.i callback)` |
| `d.U()` | Multipart 上传 | `(String path, d.k streamProvider, String sectionId, String localPath, String token, d.j progress, d.i callback)` |
| `d.T()` | DELETE 请求 | `(String path, String token, d.i callback)` |

所有请求均为异步，通过回调接口 `d.i` 返回结果：

```java
public interface d.i {
    void b(String response);      // 成功回调
    void c(int errorCode, String errorMsg);  // 失败回调
}
```

### 2.2 加密与非加密路由

加密判断逻辑 (`shouldEncrypt`)：

```java
// 只有 /auth/* 开头的 API 不加密
// 非 /auth/* + ECDH 支持 → 加密
return path != null 
    && !path.startsWith("/auth/") 
    && CryptoUtil.isEcdhSupported();
```

| 加密状态 | 路由范围 |
|----------|----------|
| ❌ 不加密 | `POST /auth/handshake` |
| ❌ 不加密 | `POST /auth/login` |
| ❌ 不加密 | `POST /auth/register` |
| ❌ 不加密 | `POST /auth/refresh` |
| ❌ 不加密 | `GET /auth/captcha` |
| ❌ 不加密 | `POST /auth/email/send` |
| ✅ 加密 | 所有其他 API |

### 2.3 加密失败的降级策略

- 距上次加密失败 60 秒内 → 跳过加密（纯文本发送）
- 60 秒后 → 重试加密
- 避免因加密问题导致用户完全无法使用

### 2.4 加密请求头

加密请求添加以下自定义头：

| 头 | 值 | 说明 |
|---|-----|------|
| `X-Enc` | `1` | 启用加密标识 |
| `X-Session` | `{sessionId}` | ECDH 协商的会话 ID |
| `Authorization` | `Bearer {token}` | 认证令牌 |
| `X-Burn-Secure` | `1` | 阅后即焚安全模式 |

请求体被加密为 JSON：`{"data":"base64_encrypted_content"}`

## 三、ECDH 加密握手流程

### 3.1 密钥交换

```
客户端                             服务端
  │                                   │
  │  1. 生成 ECDH 密钥对              │
  │     (P-256 曲线)                  │
  │                                   │
  │  2. POST /auth/handshake          │
  │     { "client_pub": "..." } ────→ │
  │                                   │
  │  3.                     生成 ECDH 密钥对
  │                                   │
  │  4. ←─── { "session_id": "...",   │
  │              "server_pub": "..." } │
  │                                   │
  │  5. 计算共享密钥                  │
  │     ECDH(客户端私钥, 服务端公钥)   │
  │                                   │
  │  6. 派生 AES-256 密钥             │
  │     共享密钥 → SHA-256 → AES key  │
  │                                   │
  │  7. 存储 session_id + AES key     │
```

### 3.2 请求加密流程

```java
// 1. 序列化请求体为 JSON
String plainText = jsonObject.toString();

// 2. AES-GCM 加密
byte[] iv = secureRandom.generateSeed(12);  // 96-bit IV
byte[] cipherText = aesGcmEncrypt(plainText, aesKey, iv);

// 3. Base64 编码
String encryptedBody = base64Encode(iv + cipherText);

// 4. 封装加密请求
JSONObject encryptedRequest = new JSONObject();
encryptedRequest.put("data", encryptedBody);

// 5. 发送 + 设置 X-Enc / X-Session 头
```

## 四、认证与 Token 管理

### 4.1 认证流程

```
登录请求
  │
  ├─ POST /auth/login
  │    { identifier, password, device_id, imei, device_name, platform, app_version }
  │
  ├─ 成功响应
  │    { access_token, refresh_token, user: { id, uid } }
  │
  └─ 存储到 SharedPreferences (auth.xml)
       • access_token
       • refresh_token
       • user_id
       • my_uid
```

### 4.2 Token 刷新机制

```
请求返回 401
    │
    ├─ 是否 /auth/* 路由？ → 不刷新，直接失败
    │
    ├─ POST /auth/refresh
    │    { refresh_token }
    │
    ├─ 成功 → 更新 access_token + refresh_token → 重试原请求
    │
    └─ 失败 → 连续失败计数
                │
                ├─ < 5 次 → 静默处理
                └─ ≥ 5 次 → Toast 提示 + 跳转 LoginActivity
```

### 4.3 登录设备信息

登录时收集的设备信息：

```java
JSONObject body = new JSONObject();
body.put("identifier", username);
body.put("password", password);
body.put("device_id", DeviceUtil.getDeviceId(context));    // 持久化 UUID
body.put("imei", DeviceUtil.getIMEI(context));              // 或 Android ID
body.put("device_name", Build.MODEL);                       // 设备型号
body.put("platform", "android");
body.put("app_version", DeviceUtil.getAppVersion(context)); // 版本名
```

## 五、WebSocket 实时通信

### 5.1 连接管理 (WSManager / WSClient)

| 参数 | 值 |
|------|-----|
| 地址 | `ws://60.205.94.101:8080/ws` |
| 连接超时 | 3000ms |
| Socket 超时 | 75000ms |
| Ping 间隔 | 由服务器控制（Opcode 0x9） |

### 5.2 重连策略（指数退避）

```
失败 → 300ms → 失败 → 600ms → 失败 → 1200ms
     → 2400ms → 4800ms → 9600ms → 19200ms → 30000ms(max)
```

- 初始延迟：300ms
- 最大延迟：30000ms
- 快速重试次数：3 次（短间隔）

### 5.3 WebSocket 消息类型

| type | 方向 | 说明 |
|------|------|------|
| `message` | 服务端→客户端 | 新消息（私聊或群聊） |
| `recall` | 服务端→客户端 | 消息撤回通知 |
| `typing` | 服务端→客户端 | 对方正在输入状态 |
| `presence` | 服务端→客户端 | 用户在线状态变更 |
| `read` | 服务端→客户端 | 已读回执 |
| `pong` | 双向 | 心跳响应 |
| `ping` | 服务端 | 心跳探测 (Opcode 0x9) |

### 5.4 Listener 接口

```java
public interface Listener {
    void onDirectMessage(WSModels.DirectMessage msg);
    void onDirectRecall(String messageId, String threadId, String fromUid);
    void onGroupMessage(WSModels.GroupMessage msg);
    void onGroupRecall(String messageId, String groupId, String fromUid);
    void onTyping(WSModels.TypingEvent event);
    void onPresence(WSModels.PresenceEvent event);
    void onConnectionChanged(boolean connected);
    void onDirectRead(String threadId, String byUid, long readAt);
}
```

## 六、重试机制

| 请求类型 | 重试次数 | 延迟策略 |
|----------|----------|----------|
| GET 请求 | 最多 3 次（首次 + 2 次重试） | 350ms × 2^attempt，最大 1200ms |
| Multipart 上传 | 最多 2 次（首次 + 1 次重试） | 600ms × 2^attempt，最大 1200ms |

触发重试的条件：
- 网络错误（-1）
- HTTP 408, 429, 500, 502, 503, 504

## 七、API 端点汇总

| 模块 | 端点 | 方法 | 加密 |
|------|------|------|:----:|
| **认证** | `/auth/handshake` | POST | ❌ |
| | `/auth/login` | POST | ❌ |
| | `/auth/register` | POST | ❌ |
| | `/auth/refresh` | POST | ❌ |
| | `/auth/captcha` | GET | ❌ |
| | `/auth/email/send` | POST | ❌ |
| **用户** | `/me` | GET | ✅ |
| | `/me/profile` | POST | ✅ |
| | `/me/uid` | POST | ✅ |
| | `/me/avatar` | POST | ✅ |
| | `/me/cover` | POST | ✅ |
| | `/me/presence` | POST | ✅ |
| | `/users/profile` | GET | ✅ |
| **好友** | `/friends` | GET | ✅ |
| | `/friends/requests` | GET | ✅ |
| | `/friends/request` | POST | ✅ |
| | `/friends/respond` | POST | ✅ |
| **私聊** | `/direct/send` | POST | ✅ |
| | `/direct/read` | POST | ✅ |
| | `/direct/messages/v2` | GET | ✅ |
| | `/direct/messages/search` | GET | ✅ |
| | `/direct/burn/open` | POST | ✅ |
| | `/direct/unread` | POST | ✅ |
| | `/direct/messages/{id}` | DELETE | ✅ |
| | `/chats/typing` | POST | ✅ |
| **群组** | `/groups/list` | GET | ✅ |
| | `/groups/create` | POST | ✅ |
| | `/groups/join` | POST | ✅ |
| | `/groups/leave` | POST | ✅ |
| | `/groups/dissolve` | POST | ✅ |
| | `/groups/name` | POST | ✅ |
| | `/groups/avatar` | POST | ✅ |
| | `/groups/settings` | POST | ✅ |
| | `/groups/announcement` | POST | ✅ |
| | `/groups/members` | GET | ✅ |
| | `/groups/kick` | POST | ✅ |
| | `/groups/admin` | POST | ✅ |
| | `/groups/requests` | GET | ✅ |
| | `/groups/approve` | POST | ✅ |
| | `/groups/message/send` | POST | ✅ |
| | `/groups/read` | POST | ✅ |
| | `/groups/unread` | POST | ✅ |
| | `/groups/typing` | POST | ✅ |
| | `/groups/burn/open` | POST | ✅ |
| **朋友圈** | `/moments/v2` | GET | ✅ |
| | `/moments` | POST | ✅ |
| | `/moments/comments` | GET | ✅ |
| | `/moments/comment` | POST | ✅ |
| | `/moments/like` | POST | ✅ |
| | `/moments/unlike` | POST | ✅ |
| | `/moments/user` | GET | ✅ |
| **签到** | `/me/checkin/wall` | GET | ✅ |
| | `/me/checkin` | POST | ✅ |
| | `/me/checkin/wall` | POST | ✅ |
| | `/me/checkin/wall/comments` | GET | ✅ |
| **音乐** | `/music/plaza` | GET | ✅ |
| | `/music/plaza` | POST | ✅ |
| | `/music/search` | GET | ✅ |
| | `/music/like` | POST | ✅ |
| | `/music/unlike` | POST | ✅ |
| | `/music/comments` | GET | ✅ |
| | `/music/comment` | POST | ✅ |
| **其他** | `/resources/upload` | POST | ✅ |
| | `/notifications` | GET | ✅ |
| | `/emoji/plaza` | GET | ✅ |
| | `/emoji/download` | POST | ✅ |
| | `/red/packet/send` | POST | ✅ |
| | `/red/packet/open` | POST | ✅ |
| | `/red/packet/detail` | GET | ✅ |

## 八、错误码体系

### HTTP 状态码

| 状态码 | 含义 |
|--------|------|
| 200 | 成功 |
| 201 | 资源创建成功 |
| 400 | 请求格式错误 |
| 401 | Token 过期/未授权 |
| 403 | 权限不足/设备封禁 |
| 404 | 资源不存在 |
| 408 | 请求超时 |
| 409 | 资源冲突 |
| 429 | 请求过频 |
| 500 | 服务器内部错误 |

### 业务错误码

| 错误标识 | HTTP 状态 | 说明 |
|----------|-----------|------|
| `rate_limited` | 429 | 请求过频 |
| `device_banned` | 403 | 设备被封禁 |
| `invalid_email_code` | 400 | 邮箱验证码错误 |
| `invalid_email_domain` | 400 | 不支持的邮箱域名 |
| `invalid_captcha` | - | 图形验证码错误 |
| `email_cooldown` | 429 | 邮箱发送冷却中 |
| `invalid_session` | - | Session 无效 |
| `missing session keys` | - | 缺少会话密钥 |
| `auth_silent` | - | 静默认证失败 |
| `auth_warning` | - | 认证警告 |

### 错误响应格式

```json
{
    "error": "错误消息描述",
    "code": "错误码标识（可选）"
}
```
