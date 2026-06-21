"""
Encrypted OldChat API Client
实现基于 ECDH (P-256) + AES-256-CBC + HMAC-SHA256 的请求加密/解密
协议逆向自 OldChat Android 客户端 (o0.AbstractC0443i → CryptoUtil)
"""
import json
import base64
import hashlib
import hmac
import time
import os
from typing import Dict, Optional
from cryptography.hazmat.primitives.asymmetric import ec
from cryptography.hazmat.primitives import serialization
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend

from oldchat_api import OldChatAPI


class EncryptedOldChatAPI(OldChatAPI):
    """
    加密版 API 客户端，继承 OldChatAPI，覆盖 _request 方法实现透明加密。
    所有非 /auth/* 的请求自动加密，/auth/* 请求保持明文。
    """

    NON_ENCRYPT_PREFIXES = ("/auth/", "/v1/auth/")
    HANDSHAKE_PATH = "/v1/auth/handshake"
    ENCRYPT_COOLDOWN_SEC = 60   # 加密失败后的明文降级窗口（秒）

    def __init__(self, base_url: str = "http://43.155.218.45:8080"):
        super().__init__(base_url)
        # ECDH 会话状态
        self._session_id: Optional[str] = None
        self._aes_key: Optional[bytes] = None          # 32 字节 AES-256 密钥
        self._hmac_key: Optional[bytes] = None          # 32 字节 HMAC-SHA256 密钥
        self._ec_private_key: Optional[ec.EllipticCurvePrivateKey] = None
        self._ec_public_key: Optional[ec.EllipticCurvePublicKey] = None
        self._handshake_done = False
        self._encrypt_disabled_until: float = 0        # 时间戳，之前不加密

    # ------------------------------------------------------------------
    # 公开方法
    # ------------------------------------------------------------------

    def login(self, identifier: str, password: str, device_name: str = "Web") -> Dict:
        """登录前先做 ECDH 握手，然后明文登录"""
        self._do_handshake()
        return super().login(identifier, password, device_name)

    # ------------------------------------------------------------------
    # 内部：ECDH 握手
    # ------------------------------------------------------------------

    def _do_handshake(self) -> bool:
        """执行 ECDH 密钥交换，协商 AES 会话密钥"""
        # 如果在降级窗口内，跳过握手
        if time.time() < self._encrypt_disabled_until:
            return False

        try:
            # 1. 生成 ECDH P-256 密钥对
            self._ec_private_key = ec.generate_private_key(ec.SECP256R1(), default_backend())
            self._ec_public_key = self._ec_private_key.public_key()

            # 2. 序列化公钥为 Base64（X.509 DER，91 字节 — Android 客户端兼容格式）
            client_pub_bytes = self._ec_public_key.public_bytes(
                encoding=serialization.Encoding.DER,
                format=serialization.PublicFormat.SubjectPublicKeyInfo
            )
            client_pub_b64 = base64.b64encode(client_pub_bytes).decode('ascii')

            # 3. 发送握手请求
            handshake_url = f"{self.base_url}{self.HANDSHAKE_PATH}"
            resp = self.session.post(handshake_url, json={"client_pub": client_pub_b64},
                                     timeout=15)

            if resp.status_code != 200:
                print(f"[加密] 握手失败 HTTP {resp.status_code}，60s 后重试")
                self._handshake_done = False
                self._encrypt_disabled_until = time.time() + self.ENCRYPT_COOLDOWN_SEC
                self._aes_key = None
                self._session_id = None
                return False

            handshake_data = resp.json()
            server_pub_b64 = handshake_data.get("server_pub", "")
            self._session_id = handshake_data.get("session_id", "")

            if not server_pub_b64 or not self._session_id:
                print("[加密] 握手响应缺少 server_pub 或 session_id")
                self._handshake_done = False
                self._encrypt_disabled_until = time.time() + self.ENCRYPT_COOLDOWN_SEC
                return False

            # 4. 解析服务端公钥（服务器返回的也是 X.509 DER 格式）
            server_pub_bytes = base64.b64decode(server_pub_b64)
            server_public_key = serialization.load_der_public_key(
                server_pub_bytes, default_backend()
            )

            # 5. ECDH 共享密钥
            shared_secret = self._ec_private_key.exchange(ec.ECDH(), server_public_key)

            # 6. AES-CBC 密钥 + HMAC 密钥派生
            # Android (AbstractC0443i.j → CryptoUtil):
            #   raw = truncate32(ecdh_shared_secret)
            #   enc_key = SHA-256(raw || "enc")
            #   mac_key = SHA-256(raw || "mac")
            raw_secret = self._truncate_to_32(shared_secret)
            self._aes_key = hashlib.sha256(raw_secret + b"enc").digest()  # 32 字节 AES-256 密钥
            self._hmac_key = hashlib.sha256(raw_secret + b"mac").digest()  # 32 字节 HMAC-SHA256 密钥

            self._handshake_done = True
            print(f"[加密] 握手完成，session_id={self._session_id[:8]}...")
            return True

        except Exception as e:
            print(f"[加密] 握手异常: {e}，60s 后重试")
            self._handshake_done = False
            self._encrypt_disabled_until = time.time() + self.ENCRYPT_COOLDOWN_SEC
            self._aes_key = None
            self._session_id = None
            return False

    # ------------------------------------------------------------------
    # 内部：PKCS7 填充
    # ------------------------------------------------------------------

    @staticmethod
    def _pkcs7_pad(data: bytes, block_size: int = 16) -> bytes:
        """PKCS7 填充"""
        pad_len = block_size - (len(data) % block_size)
        return data + bytes([pad_len] * pad_len)

    @staticmethod
    def _pkcs7_unpad(data: bytes) -> bytes:
        """移除 PKCS7 填充"""
        pad_len = data[-1]
        if pad_len < 1 or pad_len > 16:
            raise ValueError("invalid padding")
        # 验证所有填充字节
        for i in range(len(data) - pad_len, len(data)):
            if data[i] != pad_len:
                raise ValueError("invalid padding")
        return data[:-pad_len]

    @staticmethod
    def _truncate_to_32(data: bytes) -> bytes:
        """截断/补齐到 32 字节（匹配 Android t() 方法）"""
        if len(data) == 32:
            return data
        result = bytearray(32)
        if len(data) > 32:
            # 取最后 32 字节
            result[:] = data[-32:]
        else:
            # 头部补零
            result[32 - len(data):] = data
        return bytes(result)

    # ------------------------------------------------------------------
    # 内部：AES-CBC 加/解密 + HMAC-SHA256
    # ------------------------------------------------------------------

    def _encrypt_body(self, plaintext: bytes) -> Dict:
        """
        AES-256-CBC 加密 + HMAC-SHA256 认证
        返回 {"iv": b64, "data": b64, "mac": b64}
        """
        # 1. 生成 16 字节随机 IV
        iv = os.urandom(16)

        # 2. PKCS7 填充
        padded = self._pkcs7_pad(plaintext)

        # 3. AES-CBC 加密
        cipher = Cipher(algorithms.AES(self._aes_key), modes.CBC(iv), backend=default_backend())
        encryptor = cipher.encryptor()
        ciphertext = encryptor.update(padded) + encryptor.finalize()

        # 4. HMAC-SHA256(mac_key, iv || ciphertext)
        mac = hmac.new(self._hmac_key, iv + ciphertext, 'sha256').digest()

        return {
            "iv": base64.b64encode(iv).decode('ascii'),
            "data": base64.b64encode(ciphertext).decode('ascii'),
            "mac": base64.b64encode(mac).decode('ascii'),
        }

    def _decrypt_body(self, enc_data: Dict) -> bytes:
        """
        验证 HMAC 并解密 AES-CBC 密文
        enc_data: {"iv": b64, "data": b64, "mac": b64}
        返回: 明文字节
        """
        iv = base64.b64decode(enc_data["iv"])
        ciphertext = base64.b64decode(enc_data["data"])
        mac_expected = base64.b64decode(enc_data["mac"])

        # 1. 验证 HMAC
        mac_actual = hmac.new(self._hmac_key, iv + ciphertext, 'sha256').digest()
        if not hmac.compare_digest(mac_actual, mac_expected):
            raise ValueError("HMAC mismatch — payload tampered")

        # 2. AES-CBC 解密
        cipher = Cipher(algorithms.AES(self._aes_key), modes.CBC(iv), backend=default_backend())
        decryptor = cipher.decryptor()
        padded = decryptor.update(ciphertext) + decryptor.finalize()

        # 3. 移除 PKCS7 填充
        return self._pkcs7_unpad(padded)

    # ------------------------------------------------------------------
    # 内部：判断是否需要加密
    # ------------------------------------------------------------------

    def _should_encrypt(self, path: str) -> bool:
        """判断给定路径是否需要加密"""
        # 降级窗口内不加密
        if time.time() < self._encrypt_disabled_until:
            return False
        # /auth/* 不加密
        for prefix in self.NON_ENCRYPT_PREFIXES:
            if path.startswith(prefix):
                return False
        # 需要握手完成 + 有会话密钥
        if self._handshake_done and self._aes_key is not None and bool(self._session_id):
            return True
        # 降级窗口已过但握手未完成 → 尝试重新握手
        if not self._handshake_done and path != self.HANDSHAKE_PATH:
            self._do_handshake()
        return self._handshake_done and self._aes_key is not None and bool(self._session_id)

    # ------------------------------------------------------------------
    # 重写 _request 实现加密
    # ------------------------------------------------------------------

    def _request(self, method: str, path: str, auth: bool = True, **kwargs) -> Dict:
        """
        重写父类 _request：对非认证路径自动加解密请求体。
        保持与父类相同的签名和语义。
        """
        should_enc = self._should_encrypt(path)

        # -- 加密阶段 --
        if should_enc and kwargs.get('json') is not None:
            # 保存原始 json 体
            original_json = kwargs.pop('json')
            try:
                # 序列化 → AES-CBC 加密 + HMAC
                plain_bytes = json.dumps(original_json, ensure_ascii=False).encode('utf-8')
                enc_dict = self._encrypt_body(plain_bytes)  # {"iv", "data", "mac"}

                # 替换 json 体为加密格式
                kwargs['json'] = enc_dict

                # 添加加密头
                self.session.headers.update({
                    "X-Enc": "1",
                    "X-Session": self._session_id,
                })
            except Exception as e:
                print(f"[加密] 加密失败: {e}，降级回明文")
                # 降级：恢复原始 json，60 秒内不再尝试加密
                self._encrypt_disabled_until = time.time() + self.ENCRYPT_COOLDOWN_SEC
                kwargs['json'] = original_json
                self.session.headers.pop("X-Enc", None)
                self.session.headers.pop("X-Session", None)
        else:
            # 不加密 → 清除加密头
            self.session.headers.pop("X-Enc", None)
            self.session.headers.pop("X-Session", None)

        # -- 发送请求（父类逻辑） --
        try:
            result = super()._request(method, path, auth=auth, **kwargs)

            # -- 解密阶段（如果有加密响应） --
            if should_enc and isinstance(result, dict) and "iv" in result and "data" in result and "mac" in result and self._aes_key:
                try:
                    plain_bytes = self._decrypt_body(result)
                    result = json.loads(plain_bytes.decode('utf-8'))
                except Exception as e:
                    print(f"[加密] 响应解密失败: {e}")
                    # 解密失败时尝试返回原始响应

            return result

        except Exception as e:
            # 加密请求失败 → 立即降级重试一次（明文）
            if should_enc:
                print(f"[加密] 请求失败: {e}，降级重试（明文）")
                self._encrypt_disabled_until = time.time() + self.ENCRYPT_COOLDOWN_SEC
                # 恢复原始 json 体（如果被替换过）
                if 'original_json' in dir():
                    kwargs['json'] = original_json
                    self.session.headers.pop("X-Enc", None)
                    self.session.headers.pop("X-Session", None)
                # 明文重试
                try:
                    result = super()._request(method, path, auth=auth, **kwargs)
                    return result
                except Exception as e2:
                    print(f"[加密] 降级重试仍失败: {e2}")
                    raise
            raise

    def _decrypt_response(self, resp_data: Dict) -> Dict:
        """解密响应体中的 data 字段（兼容旧格式）"""
        # 新格式：{"iv": b64, "data": b64, "mac": b64}
        if "iv" in resp_data and "data" in resp_data and "mac" in resp_data and self._aes_key:
            plain_bytes = self._decrypt_body(resp_data)
            return json.loads(plain_bytes.decode('utf-8'))
        return resp_data
