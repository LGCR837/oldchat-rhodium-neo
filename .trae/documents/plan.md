# OldChat 兼容服务器实现计划

## 一、需求分析与目标

### 1.1 目标
用 **Python + Flask + SQLite3** 构建一个 **兼容 OldChat 协议** 的服务端，让 OldChat 官方客户端（Android）与 OCK 网页客户端能够正常注册、登录、发送/接收消息。**所有代码塞进一个文件 `app.py`**，不拆分成多个模块。

### 1.2 参考资源（已阅读与分析）
- `/workspace/.docs/ockdocs/00-项目总览与架构.md` — 客户端架构与加密系统
- `/workspace/.docs/ockdocs/02-网络层与API通信机制.md` — API 端点汇总、WebSocket 协议
- `/workspace/.docs/oldchat-kivotos-fix/oldchat_api.py` — 明文版 API 客户端
- `/workspace/.docs/oldchat-kivotos-fix/encrypted_api.py` — ECDH + AES-CBC + HMAC 加密版客户端
- `/workspace/.docs/oldchat-kivotos-fix/api.py` — 反向代理实现（客户端→服务端请求模型）

## 二、协议与端点总览

### 2.1 HTTP API（明文路径）
| 方法 | 路径 | 作用 |
|------|------|------|
| POST | `/v1/auth/handshake` | ECDH 密钥交换（客户端公钥→服务器公钥+session_id） |
| POST | `/v1/auth/login` | 账号密码登录（返回 access_token / refresh_token / user） |
| POST | `/v1/auth/register` | 注册新账号 |
| POST | `/v1/auth/refresh` | 刷新 access_token |
| GET | `/v1/users/profile?uid=` | 拉取指定用户资料 |
| GET | `/v1/me` | 当前用户资料 |
| POST | `/v1/me/profile` | 更新资料 |
| GET | `/v1/friends` | 好友列表 |
| POST | `/v1/friends/request` | 发送好友请求 |
| GET | `/v1/friends/requests` | 拉取好友请求列表 |
| POST | `/v1/friends/respond` | 接受/拒绝好友请求 |
| GET | `/v1/groups/list` | 群组列表 |
| POST | `/v1/groups/create` | 创建群组 |
| POST | `/v1/groups/join` | 加入群组 |
| GET | `/v1/groups/members?group_id=` | 群成员 |
| GET | `/v1/direct/messages/v2?with_uid=&limit=&offset=` | 私聊历史消息 |
| POST | `/v1/direct/send` | 发送私聊 |
| POST | `/v1/direct/read` | 标记已读 |
| POST | `/v1/direct/unread` | 未读摘要 |
| GET | `/v1/groups/messages/v2?group_id=` | 群聊历史消息 |
| POST | `/v1/groups/message/send` | 发送群聊 |
| POST | `/v1/groups/read` | 标记群聊已读 |
| POST | `/v1/groups/unread` | 群未读摘要 |
| POST | `/v1/media` | 媒体上传（multipart/form-data） |

### 2.2 请求体加密（非 `/v1/auth/*` 路径）
**加密包格式：**
```json
{ "iv": "base64", "data": "base64", "mac": "base64" }
```
**算法链：**
1. `ECDH(SECP256R1)` 交换 shared_secret（32 字节）
2. `enc_key = SHA-256(raw_secret || "enc")`（32 字节）
3. `mac_key = SHA-256(raw_secret || "mac")`（32 字节）
4. `AES-256-CBC(PKCS7 pad(plaintext))` → ciphertext
5. `HMAC-SHA256(mac_key, iv || ciphertext)` → mac

**请求头：**
- `X-Enc: 1`（标记加密请求）
- `X-Session: <session_id>`（会话绑定）
- `Authorization: Bearer <access_token>`（认证）

**降级策略：** 若请求没有 `X-Enc` 头，也接受明文（方便调试与兼容 OCK 非加密模式）。

### 2.3 WebSocket 实时推送（`ws://<host>/ws`）
- 连接时通过 query 参数或首条消息携带 token 鉴权
- 推送消息类型：
  - `message`（新消息到达，私聊/群聊统一）
  - `presence`（好友在线状态变化）
  - `read`（对方已读回执）

### 2.4 认证 Token 格式
- `access_token`：随机 40 位十六进制字符串（短有效期，实现中设为 30 天，跳过严格过期检查以降低复杂度）
- `refresh_token`：随机 64 位十六进制字符串（极长有效期）
- `session_id`：随机 32 位十六进制字符串（ECDH 会话绑定）

## 三、数据库设计（SQLite3）

数据库文件：`./oldchat.db`（与 `app.py` 同目录，首次启动自动建表）

### 表结构
```
users (用户表)
  id INTEGER PRIMARY KEY AUTOINCREMENT
  uid TEXT UNIQUE              -- 大写 UPPER(hex)，例如 "7A3F9B2C..."
  username TEXT UNIQUE         -- 账号/用户名
  password_hash TEXT           -- pbkdf2_sha256
  display_name TEXT            -- 昵称
  avatar_url TEXT              -- /media/<filename> 相对路径
  cover_url TEXT               -- /media/<filename> 相对路径
  bio TEXT                     -- 个性签名
  online INTEGER DEFAULT 0     -- 在线状态 0/1
  last_seen INTEGER            -- 时间戳
  created_at INTEGER

sessions (ECDH 会话表)
  session_id TEXT PRIMARY KEY
  user_id INTEGER              -- 关联 user，可能为 null（握手时还没登录）
  aes_key_b64 TEXT             -- 32 字节 base64
  hmac_key_b64 TEXT            -- 32 字节 base64
  created_at INTEGER
  expires_at INTEGER           -- 默认 24 小时

tokens (Token 表)
  access_token TEXT PRIMARY KEY
  refresh_token TEXT UNIQUE
  user_id INTEGER
  created_at INTEGER
  expires_at INTEGER            -- access_token 过期，refresh 可刷新

friendships (好友关系表)
  id INTEGER PRIMARY KEY AUTOINCREMENT
  user_id INTEGER
  friend_id INTEGER
  status TEXT                   -- 'pending' / 'accepted'
  initiated_by INTEGER          -- 发起方
  created_at INTEGER
  UNIQUE(user_id, friend_id)

groups (群组表)
  id INTEGER PRIMARY KEY AUTOINCREMENT
  group_id TEXT UNIQUE          -- 对外公开的 ID
  name TEXT
  avatar_url TEXT
  owner_id INTEGER
  created_at INTEGER

group_members (群成员表)
  id INTEGER PRIMARY KEY AUTOINCREMENT
  group_id TEXT
  user_id INTEGER
  joined_at INTEGER
  UNIQUE(group_id, user_id)

direct_messages (私聊消息表)
  id INTEGER PRIMARY KEY AUTOINCREMENT
  from_id INTEGER
  to_id INTEGER
  body TEXT
  msg_type TEXT                 -- text/image/video/voice/resource
  media_url TEXT
  thumb_url TEXT
  burn_after_seconds INTEGER DEFAULT 0
  created_at INTEGER
  is_read INTEGER DEFAULT 0

group_messages (群聊消息表)
  id INTEGER PRIMARY KEY AUTOINCREMENT
  group_id TEXT
  from_id INTEGER
  body TEXT
  msg_type TEXT
  media_url TEXT
  thumb_url TEXT
  burn_after_seconds INTEGER DEFAULT 0
  created_at INTEGER
```

## 四、文件实现计划（单文件 `app.py`）

### 4.1 文件结构（所有逻辑都在一个文件内）
```
app.py  (约 700-900 行，单文件)
├── 0. imports (flask, sqlite3, hashlib, cryptography, etc.)
├── 1. 配置与常量 (DB_PATH, MEDIA_DIR, WS_PORT)
├── 2. 工具函数 (加密/解密、密码哈希、JSON 响应包装)
├── 3. DB 层 (init_db()、get_db()、cur/conn 管理)
├── 4. 加密中间件 (针对 POST body 的自动解密中间件)
├── 5. 路由定义
│   ├── /v1/auth/*            (handshake/login/register/refresh)
│   ├── /v1/me, /v1/me/profile
│   ├── /v1/users/profile
│   ├── /v1/friends/*
│   ├── /v1/groups/*
│   ├── /v1/direct/*
│   ├── /v1/groups/message/*
│   └── /v1/media             (上传)
├── 6. WebSocket 端 (/ws)    (用 Python 标准库或 flask-sockets)
└── 7. __main__ 启动块 (本地媒体静态服务 + Flask run)
```

### 4.2 关键模块说明

**(1) 加密/解密核心**
- 复用 `encrypted_api.py` 中逆向出来的算法：`ECDH(SECP256R1) → SHA-256(raw||"enc"/"mac") → AES-256-CBC + HMAC-SHA256`
- 服务端也使用 `cryptography` 库
- 每个 `session_id` 绑定一对（aes_key, hmac_key），存 SQLite（base64 编码）

**(2) 请求处理流程**
```
Request → before_request 钩子:
  1. 若有 X-Session 头 → 从 DB 查 session，得到 aes_key / hmac_key
  2. 若有 X-Enc: 1 头 且 body 存在 → 解密请求体
  3. 若有 Authorization: Bearer <token> → 绑定 user_id
     → 401 时检查 /v1/auth/* 白名单
```

**(3) 响应处理**
```
after_request 钩子:
  若当前请求是加密会话（X-Enc: 1）→ 将 JSON 响应加密成 {iv,data,mac}
```

**(4) WebSocket 推送**
- 使用 `flask_socketio` 或 `flask_sockets`（视环境兼容性选择）
- 连接建立后，服务端维护 `{user_id -> websocket_list}`
- 发送消息时，若目标用户在线，立即通过 WS 推送 `{"type":"message", ...}`
- 在线状态变化通过 `presence` 消息广播给好友

**(5) 媒体上传**
- `/v1/media` 接收 multipart/form-data 的 `file` 字段
- 文件保存在 `./media/<uuid>.<ext>`
- 返回 `{"url": "/media/...", "thumb_url": "/media/..._thumb"}`

### 4.3 跳过/简化的功能（MVP 优先）
- ✅ 跳过阅后即焚的实际销毁逻辑（字段保留）
- ✅ 跳过朋友圈 / 音乐广场 / 签到 / 红包 / 表情包广场（客户端会看到空列表或 404，不影响聊天）
- ✅ 跳过加密请求的严格强制（同时接受明文请求，方便调试）
- ✅ Token 过期使用长过期 + 软刷新，不强制踢线
- ✅ 忽略设备封禁 / 速率限制 / 邮箱验证码

### 4.4 依赖
```text
Flask>=3.0.0
cryptography>=41.0.0
flask-socketio>=5.3.0   # 或 flask-sockets
python-socketio>=5.11.0
eventlet>=0.36.0        # （WebSocket 后端，可选）
```
> 若环境无 flask-socketio，降级为 `flask run` + 长轮询（/poll 端点），但官方客户端期望 WS。我们优先使用 `flask-socketio + eventlet`；若安装失败，可提供 WebSocket 降级方案。

## 五、代码实现步骤

### Step 1：基础骨架
- `app.py` 顶层导入、配置、DB 初始化（首次运行建表 + 自动建 `./media/` 目录）
- 提供 `init_db()` / `get_db()` / `query(sql, args)` / `execute(sql, args)` 工具

### Step 2：加解密中间件
- `do_ecdh_handshake(client_pub_b64)` → 生成服务器密钥对、协商会话、写入 `sessions` 表、返回 `(session_id, server_pub_b64)`
- `encrypt_for_session(session_id, plaintext_dict)` → 返回 `{iv, data, mac}`
- `decrypt_for_session(session_id, enc_dict)` → 返回原始 dict
- `before_request` 中根据 `X-Enc / X-Session` 头自动解密 body
- `after_request` 中根据会话自动加密响应

### Step 3：认证模块
- `/v1/auth/handshake`：返回 `session_id, server_pub`
- `/v1/auth/register`：注册用户，写 `users`，返回 `access_token/refresh_token/user`
- `/v1/auth/login`：密码校验，写 `tokens`，返回 token 与用户
- `/v1/auth/refresh`：根据 refresh_token 发放新 access_token

### Step 4：用户 / 好友 / 群组
- `/v1/me`、`/v1/me/profile`、`/v1/users/profile`
- `/v1/friends`、`/v1/friends/request`、`/v1/friends/requests`、`/v1/friends/respond`
- `/v1/groups/list`、`/v1/groups/create`、`/v1/groups/join`、`/v1/groups/members`

### Step 5：消息与实时推送
- `/v1/direct/send` → 写 `direct_messages` → 若对方在线，WS push
- `/v1/direct/messages/v2` → 分页拉取历史
- `/v1/direct/read`、`/v1/direct/unread`
- 群聊对应端点同上
- `/ws` WebSocket 接入：握手时校验 token、记录 user_id→socket 映射、处理心跳

### Step 6：媒体上传
- `/v1/media` (POST multipart)：保存文件 → 返回 `url/thumb_url`
- Flask 路由 `GET /media/<filename>` 直接 `send_from_directory`

### Step 7：入口与启动
- `app.py` 末尾 `if __name__ == "__main__": app.run(host="0.0.0.0", port=8080)`
- 首次运行自动建库、建表、建目录

## 六、预期响应格式（统一 JSON）

```json
// 成功
{ "access_token": "...", "refresh_token": "...", "user": {...} }
{ "friends": [ {...}, ... ] }
{ "messages": [ { "id":..., "from_uid":..., "body":..., "created_at":... }, ... ] }

// 失败（HTTP 4xx/5xx）
{ "error": "描述信息", "code": "错误码" }
```

消息对象结构（客户端期望）：
```json
{
  "id": 123,
  "from_uid": "7A3F9B...",
  "to_uid": "3B2C1A..." 或 "group_id": "GRP-7788",
  "body": "你好",
  "msg_type": "text",
  "media_url": null,
  "thumb_url": null,
  "burn_after_seconds": 0,
  "created_at": 1734567890,
  "is_read": 0,
  "from_name": "Alice",
  "from_avatar": "/media/a1.png"
}
```

## 七、风险与兼容性处理

1. **旧客户端加密格式**：严格遵循 `AES-CBC + HMAC` 双密钥派生（`"enc"/"mac"` 后缀），避免与 `AES-GCM` 混淆（原文档中曾提到 GCM，但逆向代码明确使用 CBC+MAC）。
2. **WebSocket 握手差异**：不同客户端（Android vs OCK Web）连接方式不同，服务端接受两种认证：
   - query: `ws://host/ws?token=<access_token>`
   - 首条消息: `{"type":"auth","token":"<access_token>"}`
3. **UID 大小写**：统一使用大写十六进制，比对时做 `UPPER()`。
4. **依赖缺失**：`cryptography`、`flask-socketio`、`eventlet` 若缺失则在启动时打印友好错误，指示 `pip install`。
5. **跨域（CORS）**：开发模式下放宽（`Access-Control-Allow-Origin: *`）。

## 八、交付与验证

- 单文件：`/workspace/app.py`
- 依赖：写入 `requirements.txt` 或 app.py 内引导文本
- 验证流程（启动后应可通过）：
  1. `POST /v1/auth/register` → 获得 token
  2. `POST /v1/auth/login` → 获得 token
  3. `POST /v1/auth/handshake` → 握手成功
  4. 对 `/v1/friends`、`/v1/groups/list`、`/v1/direct/messages/v2` 发送加密请求
  5. 两个用户互发消息，通过 WS 接收推送
