# OldChat ECDH 握手协议与加密流程详解

> 本文档基于 Android APK 反编译代码 (`AbstractC0443i.java` → `CryptoUtil`) 与 Python 实现 (`encrypted_api.py`) 编写。
> 深入分析 ECDH 密钥交换、AES-CBC 加密、HMAC 认证的每一步细节。

---

## 一、协议概述

OldChat 使用 **双密钥 ECDH 协议** 保护所有非认证 API 通信：

| 组件 | 算法 | 用途 |
|------|------|------|
| 密钥交换 | ECDH over P-256 (secp256r1) | 协商共享秘密 |
| 加密算法 | AES-256-CBC + PKCS7 | 请求/响应体加密 |
| 完整性认证 | HMAC-SHA256 | 防篡改 & 认证 |
| 密钥派生 | SHA-256 × 2 | 从共享秘密派生出加密密钥 + MAC 密钥 |

### 协议序列总览

```
Client                              Server
  │                                    │
  │  POST /v1/auth/handshake           │
  │  { client_pub: Base64(DER pk) }    │
  │ ────────────────────────────────→  │
  │                                    │
  │  { session_id, server_pub }        │
  │ ←────────────────────────────────  │
  │                                    │
  │  ECDH(sk_client, pk_server)        │
  │  → shared_secret (32字节)          │
  │                                    │
  │  enc_key = SHA-256(ss_trunc || "enc")   │
  │  mac_key = SHA-256(ss_trunc || "mac")   │
  │                                    │
  │  POST /v1/xxx (AES-CBC + HMAC)     │
  │  Headers: X-Enc:1, X-Session       │
  │  Body: { iv, data, mac }           │
  │ ────────────────────────────────→  │
  │                                    │
  │  解密验证后响应同理                │
  │ ←────────────────────────────────  │
```

---

## 二、握手协议详解

### 2.1 密钥对生成（客户端）

```python
from cryptography.hazmat.primitives.asymmetric import ec
from cryptography.hazmat.backends import default_backend

private_key = ec.generate_private_key(ec.SECP256R1(), default_backend())
public_key = private_key.public_key()
```

| 参数 | 值 |
|------|-----|
| 曲线 | NIST P-256 (secp256r1) |
| 私钥长度 | 32 字节 (256-bit) |
| 公钥编码 | X.509 SubjectPublicKeyInfo, DER 格式 |
| 公钥原始长度 | 91 字节 (DER) → 124 字节 (Base64) |
| 随机源 | OS 熵池 (urandom) |

对应的 Android 端实现：

```java
KeyPairGenerator generator = KeyPairGenerator.getInstance("ECDH", "SC");
ECGenParameterSpec ecSpec = new ECGenParameterSpec("P-256");
generator.initialize(ecSpec, new SecureRandom());
KeyPair keyPair = generator.generateKeyPair();
```

> 注意：Android 使用 SpongyCastle 库（`"SC"` Provider），而非原生的 Android 加密库。

### 2.2 握手请求

```http
POST /v1/auth/handshake
Content-Type: application/json

{
    "client_pub": "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE..."
}
```

`client_pub` 是客户端公钥的 DER 编码再做标准 Base64 编码：

```python
client_pub_bytes = public_key.public_bytes(
    encoding=serialization.Encoding.DER,
    format=serialization.PublicFormat.SubjectPublicKeyInfo
)
client_pub_b64 = base64.b64encode(client_pub_bytes).decode('ascii')
```

DER 编码结构（91 字节）：

```
30 59 30 13 06 07 2A 86 48 CE 3D 02 01 06 08 2A
86 48 CE 3D 03 01 07 03 42 00 04 <64-byte-uncompressed-point>
```

- `30 59` — SEQUENCE (89 bytes)
  - `30 13` — 算法标识符 SEQUENCE (19 bytes)
    - OID: 1.2.840.10045.2.1 (EC公钥)
    - OID: 1.2.840.10045.3.1.7 (P-256曲线)
  - `03 42 00` — BIT STRING 标记
  - `04` — 未压缩格式标志
  - 64 字节 — X || Y 坐标 (各 32 字节)

### 2.3 握手响应

```json
{
    "session_id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
    "server_pub": "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE..."
}
```

| 字段 | 类型 | 说明 |
|------|------|------|
| `session_id` | string (URL-safe base64) | 本次会话的唯一标识 |
| `server_pub` | string (Base64) | 服务端公钥（DER 编码） |

> 注意：`session_id` 使用 **URL-safe Base64**（包含 `-` 和 `_` 字符），而非标准 Base64。

### 2.4 ECDH 共享密钥计算

```python
# 解析服务端公钥
server_pub_bytes = base64.b64decode(server_pub_b64)
server_public_key = serialization.load_der_public_key(
    server_pub_bytes, default_backend()
)

# ECDH 密钥协商
shared_secret = private_key.exchange(ec.ECDH(), server_public_key)
```

`shared_secret` 是 ECDH 协议的输出，固定为 **32 字节**（256-bit），即 P-256 曲线的 X 坐标（共享点 `(x, y) = sk_client * pk_server`）。

### 2.5 `truncate_to_32` 处理

Android 实现中的 `t()` 方法（`AbstractC0443i.java`）在密钥派生前对共享秘密做规范化处理：

```python
@staticmethod
def _truncate_to_32(data: bytes) -> bytes:
    """截断/补齐到 32 字节（匹配 Android t() 方法）"""
    if len(data) == 32:
        return data
    result = bytearray(32)
    if len(data) > 32:
        result[:] = data[-32:]   # 取最后 32 字节
    else:
        result[32 - len(data):] = data  # 头部补零
    return bytes(result)
```

> **为什么要做这个处理？** 虽然 ECDH P-256 的标准输出就是 32 字节，但某些加密库（含 SpongyCastle）在特定情况下可能返回不同长度的共享秘密。Android 端的 `t()` 方法是一个防御性兼容层。
>
> 实际上 ECDH 的输出总是 32 字节，所以 `_truncate_to_32` 在当前场景下是恒等操作，但保留这个处理以保持与 Android 客户端完全一致。

### 2.6 密钥派生（最关键的发现）

与文档记载不同，**实际 Android 代码使用了双密钥派生方案**：

```python
# raw = truncate32(shared_secret)
raw_secret = self._truncate_to_32(shared_secret)

# 加密密钥: SHA-256(raw || "enc")
self._aes_key = hashlib.sha256(raw_secret + b"enc").digest()

# MAC 密钥: SHA-256(raw || "mac")
self._hmac_key = hashlib.sha256(raw_secret + b"mac").digest()
```

| 密钥 | 长度 | 派生公式 | 用途 |
|------|------|----------|------|
| `aes_key` | 32 字节 (256-bit) | `SHA-256(trunc32(ss) \|\| "enc")` | AES-256-CBC 加密 |
| `hmac_key` | 32 字节 (256-bit) | `SHA-256(trunc32(ss) \|\| "mac")` | HMAC-SHA256 认证 |

**关键发现**：
- ❌ **不**是 AES-GCM
- ❌ **不**是 `SHA-256(shared_secret + session_id)`（文档错误）
- ✅ 实际使用 **AES-256-CBC + HMAC-SHA256**
- ✅ 密钥派生使用固定字符串 `"enc"` 和 `"mac"` 作为区分符

---

## 三、请求/响应加密协议

### 3.1 加密请求格式

```http
POST /v1/users/me/friends
X-Enc: 1
X-Session: a1b2c3d4-e5f6-7890-abcd-ef1234567890
Content-Type: application/json

{
    "iv": "W5J3sdfk2...==",
    "data": "8XZp4Kj7M...==",
    "mac": "fGh9LmN0Q...=="
}
```

| 头字段 | 值 | 说明 |
|--------|-----|------|
| `X-Enc` | `1` | 标记请求体已加密（服务器据此选择加密/明文路径） |
| `X-Session` | session_id | 握手时协商的会话 ID，用于服务端查找对应的 DH 公钥 |

### 3.2 加密流程（发送方向）

```
明文 JSON (如 {"to_uid":"123","body":"hello"})
    │
    ├─ json.dumps() → UTF-8 bytes
    │
    ├─ PKCS7 填充 (补齐到 16 字节块)
    │   ├─ 输入 N 字节 → 填充 pad_len = 16 - (N % 16)
    │   └─ 每个填充字节值 = pad_len (1~16)
    │
    ├─ AES-256-CBC 加密
    │   ├─ IV: 16 字节随机数 (os.urandom)
    │   └─ 使用 aes_key (32 字节)
    │
    ├─ HMAC-SHA256(mac_key, IV || ciphertext)
    │   └─ 输出 32 字节 MAC 标签
    │
    └─ Base64 编码三个字段
        ├─ "iv":   Base64(IV)
        ├─ "data": Base64(ciphertext)
        └─ "mac":  Base64(HMAC)
```

Python 实现：

```python
def _encrypt_body(self, plaintext: bytes) -> Dict:
    iv = os.urandom(16)
    padded = self._pkcs7_pad(plaintext)
    cipher = Cipher(algorithms.AES(self._aes_key), modes.CBC(iv), backend=default_backend())
    encryptor = cipher.encryptor()
    ciphertext = encryptor.update(padded) + encryptor.finalize()
    mac = hmac.new(self._hmac_key, iv + ciphertext, 'sha256').digest()
    return {
        "iv": base64.b64encode(iv).decode('ascii'),
        "data": base64.b64encode(ciphertext).decode('ascii'),
        "mac": base64.b64encode(mac).decode('ascii'),
    }
```

### 3.3 解密流程（接收方向）

```
服务器返回加密响应 {"iv":"...", "data":"...", "mac":"..."}
    │
    ├─ Base64 解码三个字段
    │
    ├─ HMAC 验证
    │   ├─ 计算 HMAC-SHA256(mac_key, iv || ciphertext)
    │   ├─ 与响应中的 mac 字段比较 (constant-time)
    │   └─ 不匹配 → 抛出异常 (数据被篡改)
    │
    ├─ AES-256-CBC 解密
    │   ├─ 使用 aes_key + iv
    │   └─ 输出 PKCS7 填充后的明文
    │
    ├─ PKCS7 去填充
    │
    └─ json.loads() → 应用层对象
```

### 3.4 加密路由规则

```python
def _should_encrypt(self, path: str) -> bool:
    # 降级窗口内 → 不加密
    if time.time() < self._encrypt_disabled_until:
        return False
    # /auth/* 路径 → 不加密（认证自身不加密）
    for prefix in self.NON_ENCRYPT_PREFIXES:
        if path.startswith(prefix):
            return False
    # 需要握手完成 + 有密钥
    return self._handshake_done and self._aes_key is not None and bool(self._session_id)
```

| 路径前缀 | 是否加密 |
|----------|----------|
| `/v1/auth/` | ❌ 不加密 |
| `/v1/ws` | ❌ 不加密（WebSocket 升级） |
| 其他所有 `/v1/*` | ✅ AES-256-CBC + HMAC |

---

## 四、安全降级与容错机制

### 4.1 降级窗口

当加密请求连续失败时，客户端会退回到明文模式：

| 条件 | 行为 |
|------|------|
| 握手失败 | `encrypt_disabled_until = now + 60s` |
| 加密请求 HTTP 错误 | `encrypt_disabled_until = now + 60s` |
| 加密异常 | `encrypt_disabled_until = now + 60s` |
| 60 秒后 | 重新尝试握手 + 加密 |
| 成功 | 恢复正常加密 |

```python
self.ENCRYPT_COOLDOWN_SEC = 60  # 降级窗口：60 秒
```

### 4.2 Token 刷新安全

当加密请求遇到 401 时：

```
401 Unauthorized
    │
    ├─ 是 /auth/* 路由？→ 不刷新，直接返回错误
    │
    └─ 否 → POST /auth/refresh { refresh_token }
              │
              ├─ 成功 → 更新 token，重试请求
              └─ 失败 → 抛出 "登录已过期"
```

---

## 五、Payload 字段精确规范

### 5.1 字段格式

| 字段 | 原始数据 | 编码方式 | Base64后长度 |
|------|----------|----------|-------------|
| `iv` | 16 字节 | 标准 Base64 (`b64encode`) | 24 字符 |
| `data` | N 字节 (密文，N % 16 == 0) | 标准 Base64 (`b64encode`) | ceil(N/3)*4 |
| `mac` | 32 字节 | 标准 Base64 (`b64encode`) | 44 字符 |

### 5.2 Content-Type 说明

- 加密请求的 Content-Type 为 `application/json`
- metadata 直接放在 JSON 顶层（三个字段），不使用嵌套

---

## 六、安全分析

### 6.1 协议安全性

| 攻击类型 | 防护措施 | 评级 |
|----------|----------|------|
| 重放攻击 | 每个请求随机 IV (16 bytes) | ✅ 强 |
| 篡改攻击 | HMAC-SHA256 认证 (mac_key) | ✅ 强 |
| 窃听攻击 | AES-256-CBC 加密 (aes_key) | ✅ 强 |
| 中间人攻击 | 无证书固定, HTTP 传输 | ❌ 弱 |

### 6.2 设计缺陷

1. **传输层未加密** — 使用 HTTP 明文传输，攻击者可直接读取握手信息并进行 MITM
2. **无证书固定** — 即使使用 HTTPS，也没有 SSL Pinning
3. **降级窗口** — 60 秒明文通信窗口可被攻击者利用
4. **Token 明文存储** — SharedPreferences 无加密

### 6.3 与 Android 端的一致性验证

| 要素 | Android (反编译) | Python 实现 | 匹配 |
|------|-----------------|-------------|------|
| 曲线 | P-256 (secp256r1) | SECP256R1 | ✅ |
| 公钥格式 | DER SubjectPublicKeyInfo | DER | ✅ |
| 加密算法 | AES/CBC/PKCS7Padding | AES-CBC + PKCS7 | ✅ |
| 密钥派生 | SHA-256(ss+\|"enc") / ("mac") | SHA-256(ss+\|"enc") / ("mac") | ✅ |
| IV 长度 | 16 字节 | 16 字节 | ✅ |
| HMAC | SHA-256 over IV\|\|ct | SHA-256 over IV\|\|ct | ✅ |
| Payload 格式 | {"iv","data","mac"} | {"iv","data","mac"} | ✅ |

---

## 七、测试验证流程

### 7.1 独立测试脚本

```python
# test_cbc.py — 验证加密握手 + 请求
pk = ec.generate_private_key(ec.SECP256R1())
cpb = pk.public_key().public_bytes(...)
r = requests.post(f'{SERVER}/v1/auth/handshake',
    json={'client_pub': base64.b64encode(cpb).decode()})
d = r.json()
sid = d['session_id']
spk = serialization.load_der_public_key(base64.b64decode(d['server_pub']))
ss = pk.exchange(ec.ECDH(), spk)

aes_key = hashlib.sha256(ss + b'enc').digest()
mac_key = hashlib.sha256(ss + b'mac').digest()

# 加密
iv = os.urandom(16)
ct = aes_cbc_encrypt(plain + pkcs7_pad, aes_key, iv)
mac = hmac.new(mac_key, iv + ct, 'sha256').digest()

body = {'iv': b64(iv), 'data': b64(ct), 'mac': b64(mac)}
r2 = requests.post(f'{SERVER}/v1/users/me/friends',
    json=body, headers={'X-Enc': '1', 'X-Session': sid})
print(f'HTTP {r2.status_code}')  # 非400 = 加密被接受
```

### 7.2 预期测试结果

| 条件 | 期望结果 |
|------|----------|
| 正确加密 + 有效 session_id | 服务器接受（返回 200/404/401，不会返回 `bad_encryption`） |
| 明文 + X-Enc:1 | HTTP 400 `bad_encryption` |
| 无效 session_id | HTTP 400 `invalid session` |
| 无 X-Enc:1 | HTTP 404（路径未命中） |

---

## 八、常见问题

### Q: session_id 看起来和标准 Base64 不同？

A: session_id 使用 **URL-safe Base64**（字符集 `A-Za-z0-9-_`），解码时需使用 `base64.urlsafe_b64decode()` 而非 `base64.b64decode()`。

### Q: 为什么不是 AES-GCM？

A: 文档和初步分析推测是 AES-256-GCM，但实际反编译代码显示 Android 客户端使用的是 **AES/CBC/PKCS7Padding**，配合独立的 HMAC-SHA256 验证。可能是为了兼容旧版 Android 的加密库限制。

### Q: IV 长度为什么是 16 字节而不是 12 字节？

A: CBC 模式要求 IV 长度等于块大小，AES 块大小固定为 16 字节。GCM 模式通常使用 12 字节 IV。

### Q: 握手失败后会怎样？

A: 客户端进入 60 秒降级窗口，期间所有请求以明文发送。60 秒后自动重试握手。`/auth/*` 路径始终不加密。
