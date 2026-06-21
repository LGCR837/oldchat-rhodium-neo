# ====================================================================
# OldChat 兼容服务器 — 单文件实现
# Python 3 + Flask + SQLite3 + cryptography + eventlet (WebSocket)
# ====================================================================

# --- 1. Imports ---
import os
import sys
import json
import time
import base64
import secrets
import hashlib
import hmac as hmac_mod
import sqlite3
import logging
import threading
from pathlib import Path

from flask import (Flask, g, request, jsonify, send_from_directory,
                   make_response, abort)

from cryptography.hazmat.primitives.asymmetric import ec
from cryptography.hazmat.primitives import serialization
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend

# --- eventlet (for WebSocket) ---
try:
    import eventlet
    eventlet.monkey_patch(socket=True, select=True, time=True, thread=True)
    HAS_EVENTLET = True
    from eventlet import wsgi as eventlet_wsgi
    from eventlet.websocket import WebSocketWSGI
except ImportError:
    HAS_EVENTLET = False

# --- 2. Config & Setup ---
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
DB_PATH = os.path.join(BASE_DIR, "oldchat.db")
MEDIA_DIR = os.path.join(BASE_DIR, "media")
os.makedirs(MEDIA_DIR, exist_ok=True)

app = Flask(__name__)
app.config["MAX_CONTENT_LENGTH"] = 50 * 1024 * 1024  # 50MB
app.secret_key = secrets.token_hex(32)

if __name__ != "__main__":
    gunicorn_logger = logging.getLogger("gunicorn.error")
    if gunicorn_logger.handlers:
        app.logger.handlers = gunicorn_logger.handlers
        app.logger.setLevel(gunicorn_logger.level)
else:
    logging.basicConfig(level=logging.INFO,
                        format="%(asctime)s [%(levelname)s] %(message)s")

# --- 3. Crypto Helpers ---
def pkcs7_pad(data: bytes, block_size: int = 16) -> bytes:
    pad_len = block_size - (len(data) % block_size)
    return data + bytes([pad_len] * pad_len)

def pkcs7_unpad(data: bytes) -> bytes:
    if not data:
        raise ValueError("empty data")
    pad_len = data[-1]
    if pad_len < 1 or pad_len > 16:
        raise ValueError("invalid padding")
    for i in range(len(data) - pad_len, len(data)):
        if data[i] != pad_len:
            raise ValueError("invalid padding")
    return data[:-pad_len]

def truncate_to_32(data: bytes) -> bytes:
    if len(data) == 32:
        return data
    result = bytearray(32)
    if len(data) > 32:
        result[:] = data[-32:]
    else:
        result[32 - len(data):] = data
    return bytes(result)

def encrypt_body(aes_key: bytes, mac_key: bytes, plaintext: bytes) -> dict:
    iv = os.urandom(16)
    padded = pkcs7_pad(plaintext)
    cipher = Cipher(algorithms.AES(aes_key), modes.CBC(iv), backend=default_backend())
    encryptor = cipher.encryptor()
    ciphertext = encryptor.update(padded) + encryptor.finalize()
    mac_val = hmac_mod.new(mac_key, iv + ciphertext, "sha256").digest()
    return {
        "iv": base64.b64encode(iv).decode("ascii"),
        "data": base64.b64encode(ciphertext).decode("ascii"),
        "mac": base64.b64encode(mac_val).decode("ascii"),
    }

def decrypt_body(aes_key: bytes, mac_key: bytes, enc_dict: dict) -> bytes:
    iv = base64.b64decode(enc_dict["iv"])
    ciphertext = base64.b64decode(enc_dict["data"])
    mac_expected = base64.b64decode(enc_dict["mac"])
    mac_actual = hmac_mod.new(mac_key, iv + ciphertext, "sha256").digest()
    if not hmac_mod.compare_digest(mac_actual, mac_expected):
        raise ValueError("HMAC mismatch")
    cipher = Cipher(algorithms.AES(aes_key), modes.CBC(iv), backend=default_backend())
    decryptor = cipher.decryptor()
    padded = decryptor.update(ciphertext) + decryptor.finalize()
    return pkcs7_unpad(padded)

def do_ecdh_handshake(client_pub_b64: str):
    """执行 ECDH 密钥交换，返回 (session_id, server_pub_b64, aes_key, hmac_key)"""
    server_private = ec.generate_private_key(ec.SECP256R1(), default_backend())
    server_public = server_private.public_key()
    client_pub_bytes = base64.b64decode(client_pub_b64)
    client_public = serialization.load_der_public_key(client_pub_bytes, default_backend())
    shared_secret = server_private.exchange(ec.ECDH(), client_public)
    raw_secret = truncate_to_32(shared_secret)
    aes_key = hashlib.sha256(raw_secret + b"enc").digest()
    mac_key = hashlib.sha256(raw_secret + b"mac").digest()
    session_id = secrets.token_hex(16).upper()
    server_pub_bytes = server_public.public_bytes(
        encoding=serialization.Encoding.DER,
        format=serialization.PublicFormat.SubjectPublicKeyInfo,
    )
    server_pub_b64 = base64.b64encode(server_pub_bytes).decode("ascii")
    return session_id, server_pub_b64, aes_key, mac_key

# --- 4. DB Helpers ---
_db_lock = threading.Lock()

def get_db():
    db = getattr(g, "_db", None)
    if db is None:
        db = sqlite3.connect(DB_PATH, check_same_thread=False)
        db.row_factory = sqlite3.Row
        db.execute("PRAGMA journal_mode=WAL")
        db.execute("PRAGMA foreign_keys=ON")
        g._db = db
    return db

def db_close(exception=None):
    db = getattr(g, "_db", None)
    if db is not None:
        db.close()

app.teardown_appcontext(db_close)

def db_query_one(sql: str, params=()):
    with _db_lock:
        conn = sqlite3.connect(DB_PATH, check_same_thread=False)
        conn.row_factory = sqlite3.Row
        try:
            cur = conn.execute(sql, params)
            row = cur.fetchone()
            return row
        finally:
            conn.close()

def db_query_all(sql: str, params=()):
    with _db_lock:
        conn = sqlite3.connect(DB_PATH, check_same_thread=False)
        conn.row_factory = sqlite3.Row
        try:
            cur = conn.execute(sql, params)
            return cur.fetchall()
        finally:
            conn.close()

def db_execute(sql: str, params=()) -> int:
    with _db_lock:
        conn = sqlite3.connect(DB_PATH, check_same_thread=False)
        try:
            cur = conn.execute(sql, params)
            conn.commit()
            last_id = cur.lastrowid
            return last_id if last_id else 0
        finally:
            conn.close()

def db_executemany(sql: str, params_list) -> None:
    with _db_lock:
        conn = sqlite3.connect(DB_PATH, check_same_thread=False)
        try:
            conn.executemany(sql, params_list)
            conn.commit()
        finally:
            conn.close()

def init_db():
    conn = sqlite3.connect(DB_PATH, check_same_thread=False)
    try:
        conn.executescript("""
        CREATE TABLE IF NOT EXISTS users (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            uid TEXT UNIQUE NOT NULL,
            username TEXT UNIQUE NOT NULL,
            password_hash TEXT NOT NULL,
            display_name TEXT,
            avatar_url TEXT,
            cover_url TEXT,
            bio TEXT DEFAULT '',
            online INTEGER DEFAULT 0,
            last_seen INTEGER,
            created_at INTEGER
        );

        CREATE TABLE IF NOT EXISTS sessions (
            session_id TEXT PRIMARY KEY,
            user_id INTEGER,
            aes_key_b64 TEXT NOT NULL,
            hmac_key_b64 TEXT NOT NULL,
            created_at INTEGER,
            expires_at INTEGER
        );

        CREATE TABLE IF NOT EXISTS tokens (
            access_token TEXT PRIMARY KEY,
            refresh_token TEXT UNIQUE,
            user_id INTEGER NOT NULL,
            created_at INTEGER,
            expires_at INTEGER
        );

        CREATE TABLE IF NOT EXISTS friendships (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            user_id INTEGER NOT NULL,
            friend_id INTEGER NOT NULL,
            status TEXT DEFAULT 'pending',
            initiated_by INTEGER,
            created_at INTEGER,
            UNIQUE(user_id, friend_id)
        );

        CREATE TABLE IF NOT EXISTS groups (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            group_id TEXT UNIQUE NOT NULL,
            name TEXT NOT NULL,
            avatar_url TEXT,
            owner_id INTEGER,
            created_at INTEGER
        );

        CREATE TABLE IF NOT EXISTS group_members (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            group_id TEXT NOT NULL,
            user_id INTEGER NOT NULL,
            joined_at INTEGER,
            UNIQUE(group_id, user_id)
        );

        CREATE TABLE IF NOT EXISTS direct_messages (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            from_id INTEGER NOT NULL,
            to_id INTEGER NOT NULL,
            body TEXT,
            msg_type TEXT DEFAULT 'text',
            media_url TEXT,
            thumb_url TEXT,
            burn_after_seconds INTEGER DEFAULT 0,
            created_at INTEGER,
            is_read INTEGER DEFAULT 0
        );

        CREATE TABLE IF NOT EXISTS group_messages (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            group_id TEXT NOT NULL,
            from_id INTEGER NOT NULL,
            body TEXT,
            msg_type TEXT DEFAULT 'text',
            media_url TEXT,
            thumb_url TEXT,
            burn_after_seconds INTEGER DEFAULT 0,
            created_at INTEGER
        );

        CREATE TABLE IF NOT EXISTS group_message_reads (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            group_id TEXT NOT NULL,
            user_id INTEGER NOT NULL,
            message_id INTEGER NOT NULL,
            created_at INTEGER,
            UNIQUE(group_id, user_id, message_id)
        );

        CREATE INDEX IF NOT EXISTS idx_dm_pair ON direct_messages(from_id, to_id);
        CREATE INDEX IF NOT EXISTS idx_dm_pair2 ON direct_messages(to_id, from_id);
        CREATE INDEX IF NOT EXISTS idx_gm_group ON group_messages(group_id);
        """)
        conn.commit()
    finally:
        conn.close()

# --- WebSocket Connection Registry ---
_ws_lock = threading.Lock()
_ws_conns = {}  # {user_id: set(ws_objects)}

def register_ws(user_id, ws):
    with _ws_lock:
        _ws_conns.setdefault(user_id, set()).add(ws)

def unregister_ws(user_id, ws):
    with _ws_lock:
        if user_id in _ws_conns:
            _ws_conns[user_id].discard(ws)
            if not _ws_conns[user_id]:
                del _ws_conns[user_id]

def get_online_user_ids():
    with _ws_lock:
        return set(_ws_conns.keys())

def push_message(user_id, payload: dict):
    """向特定用户的所有 WebSocket 连接推送消息"""
    with _ws_lock:
        conns = list(_ws_conns.get(user_id, set()))
    if not conns:
        return
    msg_text = json.dumps(payload, ensure_ascii=False)
    for ws in conns:
        try:
            ws.send(msg_text)
        except Exception:
            pass

def broadcast_presence(user_id, online: bool):
    """向该用户的所有好友广播在线状态变更"""
    row = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (user_id,))
    if not row:
        return
    my_uid = row["uid"]
    friends = db_query_all(
        """SELECT u.id FROM users u
           JOIN friendships f ON (f.user_id = u.id AND f.friend_id = ? AND f.status = 'accepted')
                              OR (f.friend_id = u.id AND f.user_id = ? AND f.status = 'accepted')
           WHERE u.id != ?""",
        (user_id, user_id, user_id),
    )
    payload = {
        "type": "presence",
        "uid": my_uid,
        "online": 1 if online else 0,
        "display_name": row["display_name"] or my_uid,
        "avatar_url": row["avatar_url"] or "",
    }
    for f in friends:
        push_message(f["id"], payload)

# --- 5. Middleware ---
@app.before_request
def before_request_hook():
    # CORS OPTIONS
    if request.method == "OPTIONS":
        resp = make_response("", 204)
        resp.headers["Access-Control-Allow-Origin"] = "*"
        resp.headers["Access-Control-Allow-Methods"] = "GET, POST, PUT, DELETE, OPTIONS"
        resp.headers["Access-Control-Allow-Headers"] = "Content-Type, Authorization, X-Enc, X-Session"
        return resp

    # 初始化 g 字段
    g.current_session_id = None
    g.current_aes_key = None
    g.current_hmac_key = None
    g.decrypted_json = None
    g.current_user_id = None
    g.current_user = None

    # 判断是否 multipart 上传
    ctype = request.headers.get("Content-Type", "")
    is_multipart = "multipart/form-data" in ctype.lower()

    # --- ECDH Session & 解密 ---
    session_id = request.headers.get("X-Session")
    enc_flag = request.headers.get("X-Enc")

    if session_id and enc_flag == "1" and not is_multipart:
        row = db_query_one(
            "SELECT aes_key_b64, hmac_key_b64, user_id FROM sessions WHERE session_id = ? AND expires_at > ?",
            (session_id, int(time.time())),
        )
        if row:
            g.current_session_id = session_id
            g.current_aes_key = base64.b64decode(row["aes_key_b64"])
            g.current_hmac_key = base64.b64decode(row["hmac_key_b64"])
            if row["user_id"]:
                g.current_user_id_from_session = row["user_id"]

            # 有 body 的 POST/PUT 请求，尝试解密
            if request.method in ("POST", "PUT", "DELETE"):
                raw_body = request.get_data(cache=True)
                if raw_body and len(raw_body) > 0:
                    try:
                        enc_json = json.loads(raw_body.decode("utf-8"))
                        if "iv" in enc_json and "data" in enc_json and "mac" in enc_json:
                            plain_bytes = decrypt_body(g.current_aes_key, g.current_hmac_key, enc_json)
                            g.decrypted_json = json.loads(plain_bytes.decode("utf-8"))
                    except Exception:
                        pass  # 失败就当作明文或空 body 处理

    # --- Authorization Bearer Token ---
    auth_header = request.headers.get("Authorization", "")
    if auth_header.lower().startswith("bearer "):
        token = auth_header.split(" ", 1)[1].strip()
        row = db_query_one(
            "SELECT user_id FROM tokens WHERE access_token = ? AND expires_at > ?",
            (token, int(time.time())),
        )
        if row:
            g.current_user_id = row["user_id"]
            user_row = db_query_one("SELECT * FROM users WHERE id = ?", (g.current_user_id,))
            if user_row:
                g.current_user = dict(user_row)

    # 如果没有 Authorization 头但有 session 关联的 user_id，也填充
    if not g.current_user_id and hasattr(g, "current_user_id_from_session") and g.current_user_id_from_session:
        g.current_user_id = g.current_user_id_from_session
        user_row = db_query_one("SELECT * FROM users WHERE id = ?", (g.current_user_id,))
        if user_row:
            g.current_user = dict(user_row)

@app.after_request
def after_request_hook(response):
    # CORS 头
    response.headers["Access-Control-Allow-Origin"] = "*"
    response.headers["Access-Control-Allow-Methods"] = "GET, POST, PUT, DELETE, OPTIONS"
    response.headers["Access-Control-Allow-Headers"] = "Content-Type, Authorization, X-Enc, X-Session"

    # 如果是加密会话且响应是 JSON → 加密
    if (g.current_session_id and g.current_aes_key and g.current_hmac_key):
        if response.is_json:
            try:
                data = response.get_json()
                plain_bytes = json.dumps(data, ensure_ascii=False).encode("utf-8")
                enc = encrypt_body(g.current_aes_key, g.current_hmac_key, plain_bytes)
                response.data = json.dumps(enc, ensure_ascii=False).encode("utf-8")
                response.headers["Content-Type"] = "application/json"
            except Exception as e:
                app.logger.debug(f"response encrypt failed: {e}")

    return response

# --- 6. Utilities ---
def req_json() -> dict:
    if getattr(g, "decrypted_json", None) is not None:
        return g.decrypted_json
    data = request.get_json(silent=True)
    return data if data else {}

def require_auth():
    if not getattr(g, "current_user_id", None):
        return jsonify({"error": "Unauthorized", "code": "unauthorized"}), 401
    return None

def err(msg: str, code: str = "error", status: int = 400):
    return jsonify({"error": msg, "code": code}), status

def now_ts() -> int:
    return int(time.time())

def gen_uid() -> str:
    return secrets.token_hex(16).upper()

def gen_group_id() -> str:
    return "GRP-" + secrets.token_hex(8).upper()

def hash_password(password: str) -> str:
    salt = os.urandom(16)
    pw_hash = hashlib.pbkdf2_hmac("sha256", password.encode("utf-8"), salt, 100000)
    return f"pbkdf2_sha256$100000${base64.b64encode(salt).decode()}${base64.b64encode(pw_hash).decode()}"

def verify_password(password: str, stored: str) -> bool:
    try:
        parts = stored.split("$")
        if len(parts) != 4:
            return False
        algo, iterations, salt_b64, hash_b64 = parts
        salt = base64.b64decode(salt_b64)
        expected = base64.b64decode(hash_b64)
        actual = hashlib.pbkdf2_hmac("sha256", password.encode("utf-8"), salt, int(iterations))
        return hmac_mod.compare_digest(actual, expected)
    except Exception:
        return False

def user_to_dict(row) -> dict:
    if row is None:
        return {}
    uid = row["uid"]
    online = 0
    if row["id"] in get_online_user_ids():
        online = 1
    return {
        "id": row["id"],
        "uid": uid,
        "username": row["username"],
        "display_name": row["display_name"] or uid,
        "avatar_url": row["avatar_url"] or "",
        "cover_url": row["cover_url"] or "",
        "bio": row["bio"] or "",
        "online": online,
        "last_seen": row["last_seen"] or row["created_at"],
        "created_at": row["created_at"],
    }

def direct_msg_to_dict(row, from_user=None) -> dict:
    if row is None:
        return {}
    msg = {
        "id": row["id"],
        "from_uid": "",
        "to_uid": "",
        "body": row["body"] or "",
        "msg_type": row["msg_type"] or "text",
        "media_url": row["media_url"] or "",
        "thumb_url": row["thumb_url"] or "",
        "burn_after_seconds": row["burn_after_seconds"] or 0,
        "created_at": row["created_at"],
        "is_read": row["is_read"] or 0,
        "from_name": "",
        "from_avatar": "",
    }
    # from_uid, to_uid, from_name, from_avatar
    f = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (row["from_id"],))
    t = db_query_one("SELECT uid FROM users WHERE id = ?", (row["to_id"],))
    if f:
        msg["from_uid"] = f["uid"]
        msg["from_name"] = f["display_name"] or f["uid"]
        msg["from_avatar"] = f["avatar_url"] or ""
    if t:
        msg["to_uid"] = t["uid"]
    return msg

def group_msg_to_dict(row) -> dict:
    if row is None:
        return {}
    msg = {
        "id": row["id"],
        "group_id": row["group_id"],
        "from_uid": "",
        "body": row["body"] or "",
        "msg_type": row["msg_type"] or "text",
        "media_url": row["media_url"] or "",
        "thumb_url": row["thumb_url"] or "",
        "burn_after_seconds": row["burn_after_seconds"] or 0,
        "created_at": row["created_at"],
        "from_name": "",
        "from_avatar": "",
    }
    f = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (row["from_id"],))
    if f:
        msg["from_uid"] = f["uid"]
        msg["from_name"] = f["display_name"] or f["uid"]
        msg["from_avatar"] = f["avatar_url"] or ""
    return msg

def issue_tokens(user_id: int) -> tuple:
    access_token = secrets.token_hex(20).upper()
    refresh_token = secrets.token_hex(32).upper()
    created = now_ts()
    expires = created + 30 * 24 * 3600  # 30 天
    db_execute(
        "INSERT INTO tokens (access_token, refresh_token, user_id, created_at, expires_at) VALUES (?, ?, ?, ?, ?)",
        (access_token, refresh_token, user_id, created, expires),
    )
    return access_token, refresh_token

def update_last_seen(user_id: int):
    db_execute("UPDATE users SET last_seen = ? WHERE id = ?", (now_ts(), user_id))

# --- 7. Auth Routes ---
@app.route("/v1/auth/handshake", methods=["POST"])
def route_auth_handshake():
    body = req_json()
    client_pub = body.get("client_pub") or body.get("clientPub")
    if not client_pub:
        return err("missing client_pub", "missing_pubkey")
    try:
        session_id, server_pub, aes_key, mac_key = do_ecdh_handshake(client_pub)
    except Exception as e:
        return err(f"handshake failed: {e}", "handshake_error")

    created = now_ts()
    expires = created + 24 * 3600  # 24 小时
    db_execute(
        "INSERT INTO sessions (session_id, user_id, aes_key_b64, hmac_key_b64, created_at, expires_at) VALUES (?, ?, ?, ?, ?, ?)",
        (session_id, None, base64.b64encode(aes_key).decode(), base64.b64encode(mac_key).decode(), created, expires),
    )
    return jsonify({
        "session_id": session_id,
        "server_pub": server_pub,
        "expires_at": expires,
    })

@app.route("/v1/auth/register", methods=["POST"])
def route_auth_register():
    body = req_json()
    username = (body.get("username") or body.get("identifier") or "").strip()
    password = (body.get("password") or "").strip()
    display_name = (body.get("display_name") or "").strip() or username
    if len(username) < 3:
        return err("username too short", "username_short")
    if len(password) < 4:
        return err("password too short", "password_short")

    # 检查用户名是否已存在
    existing = db_query_one("SELECT id FROM users WHERE username = ?", (username,))
    if existing:
        return err("username already exists", "username_exists", 409)

    uid = gen_uid()
    pw_hash = hash_password(password)
    created = now_ts()
    user_id = db_execute(
        """INSERT INTO users (uid, username, password_hash, display_name, avatar_url, cover_url, bio, online, last_seen, created_at)
           VALUES (?, ?, ?, ?, '', '', '', 0, ?, ?)""",
        (uid, username, pw_hash, display_name, created, created),
    )
    # 重新读取用户（db_execute 返回 lastrowid 在不同情况下可能为 0）
    row = db_query_one("SELECT * FROM users WHERE uid = ?", (uid,))
    if not row:
        return err("failed to create user", "db_error", 500)

    # 关联 session（如果在握手后立刻注册）
    if g.current_session_id:
        db_execute("UPDATE sessions SET user_id = ? WHERE session_id = ?",
                   (row["id"], g.current_session_id))

    access_token, refresh_token = issue_tokens(row["id"])
    user_dict = user_to_dict(row)
    return jsonify({
        "access_token": access_token,
        "refresh_token": refresh_token,
        "user": user_dict,
    })

@app.route("/v1/auth/login", methods=["POST"])
def route_auth_login():
    body = req_json()
    identifier = (body.get("identifier") or body.get("username") or "").strip()
    password = (body.get("password") or "").strip()
    if not identifier or not password:
        return err("missing credentials", "missing_credentials")

    row = db_query_one("SELECT * FROM users WHERE username = ? OR uid = ?", (identifier, identifier))
    if not row:
        return err("invalid credentials", "invalid_credentials", 401)
    if not verify_password(password, row["password_hash"]):
        return err("invalid credentials", "invalid_credentials", 401)

    # 关联 session（如果已握手）
    if g.current_session_id:
        db_execute("UPDATE sessions SET user_id = ? WHERE session_id = ?",
                   (row["id"], g.current_session_id))

    access_token, refresh_token = issue_tokens(row["id"])
    update_last_seen(row["id"])
    user_dict = user_to_dict(row)
    return jsonify({
        "access_token": access_token,
        "refresh_token": refresh_token,
        "user": user_dict,
    })

@app.route("/v1/auth/refresh", methods=["POST"])
def route_auth_refresh():
    body = req_json()
    refresh_token = (body.get("refresh_token") or "").strip()
    if not refresh_token:
        return err("missing refresh_token", "missing_refresh", 401)
    row = db_query_one(
        "SELECT user_id FROM tokens WHERE refresh_token = ? AND expires_at > ?",
        (refresh_token, int(time.time()) - 365 * 24 * 3600),  # refresh token 1年有效
    )
    if not row:
        return err("invalid refresh token", "invalid_refresh", 401)
    # 失效旧 token
    db_execute("DELETE FROM tokens WHERE refresh_token = ?", (refresh_token,))
    access_token, new_refresh = issue_tokens(row["user_id"])
    user_row = db_query_one("SELECT * FROM users WHERE id = ?", (row["user_id"],))
    return jsonify({
        "access_token": access_token,
        "refresh_token": new_refresh,
        "user": user_to_dict(user_row) if user_row else {},
    })

@app.route("/v1/auth/captcha", methods=["GET"])
def route_auth_captcha():
    # 简化：不需要验证码
    return jsonify({"captcha_id": "none", "image_url": ""})

@app.route("/v1/auth/email/send", methods=["POST"])
def route_auth_email_send():
    # 简化：不需要邮件验证
    return jsonify({"sent": False, "message": "email verification disabled"})

# --- 8. User Routes ---
@app.route("/v1/me", methods=["GET"])
def route_me():
    if auth_err := require_auth():
        return auth_err
    update_last_seen(g.current_user_id)
    row = db_query_one("SELECT * FROM users WHERE id = ?", (g.current_user_id,))
    if not row:
        return err("user not found", "not_found", 404)
    return jsonify(user_to_dict(row))

@app.route("/v1/me/profile", methods=["POST"])
def route_me_profile():
    if auth_err := require_auth():
        return auth_err
    body = req_json()
    display_name = body.get("display_name")
    bio = body.get("bio")
    avatar_url = body.get("avatar_url")
    cover_url = body.get("cover_url")

    sets, params = [], []
    if display_name is not None:
        sets.append("display_name = ?")
        params.append(display_name)
    if bio is not None:
        sets.append("bio = ?")
        params.append(bio)
    if avatar_url is not None:
        sets.append("avatar_url = ?")
        params.append(avatar_url)
    if cover_url is not None:
        sets.append("cover_url = ?")
        params.append(cover_url)
    if not sets:
        row = db_query_one("SELECT * FROM users WHERE id = ?", (g.current_user_id,))
        return jsonify(user_to_dict(row))
    params.append(g.current_user_id)
    db_execute(f"UPDATE users SET {', '.join(sets)} WHERE id = ?", tuple(params))
    row = db_query_one("SELECT * FROM users WHERE id = ?", (g.current_user_id,))
    return jsonify(user_to_dict(row))

@app.route("/v1/me/avatar", methods=["POST"])
def route_me_avatar():
    if auth_err := require_auth():
        return auth_err
    body = req_json()
    avatar_url = body.get("avatar_url") or ""
    db_execute("UPDATE users SET avatar_url = ? WHERE id = ?", (avatar_url, g.current_user_id))
    return jsonify({"avatar_url": avatar_url})

@app.route("/v1/me/presence", methods=["POST"])
def route_me_presence():
    if auth_err := require_auth():
        return auth_err
    body = req_json()
    online = int(body.get("online", 1))
    db_execute("UPDATE users SET online = ?, last_seen = ? WHERE id = ?", (online, now_ts(), g.current_user_id))
    broadcast_presence(g.current_user_id, bool(online))
    return jsonify({"online": online})

@app.route("/v1/users/profile", methods=["GET"])
def route_user_profile():
    if auth_err := require_auth():
        return auth_err
    uid = request.args.get("uid", "").strip()
    if not uid:
        return err("missing uid", "missing_uid")
    row = db_query_one("SELECT * FROM users WHERE uid = ?", (uid.upper(),))
    if not row:
        return err("user not found", "not_found", 404)
    return jsonify(user_to_dict(row))

# --- 9. Friends Routes ---
@app.route("/v1/friends", methods=["GET"])
def route_friends():
    if auth_err := require_auth():
        return auth_err
    uid = g.current_user_id
    # 获取所有已接受的好友
    rows = db_query_all(
        """SELECT u.* FROM users u
           WHERE u.id IN (
               SELECT CASE WHEN f.user_id = ? THEN f.friend_id ELSE f.user_id END
               FROM friendships f
               WHERE f.status = 'accepted' AND (f.user_id = ? OR f.friend_id = ?)
           )""",
        (uid, uid, uid),
    )
    friends = [user_to_dict(r) for r in rows]
    return jsonify({"friends": friends})

@app.route("/v1/friends/request", methods=["POST"])
def route_friend_request():
    if auth_err := require_auth():
        return auth_err
    body = req_json()
    to_uid = (body.get("to_uid") or "").strip().upper()
    if not to_uid:
        return err("missing to_uid", "missing_uid")
    target = db_query_one("SELECT id FROM users WHERE uid = ?", (to_uid,))
    if not target:
        return err("user not found", "not_found", 404)
    target_id = target["id"]
    if target_id == g.current_user_id:
        return err("cannot add self", "invalid_user")

    # 检查是否已有请求或已为好友
    existing = db_query_one(
        "SELECT id, status FROM friendships WHERE (user_id = ? AND friend_id = ?) OR (user_id = ? AND friend_id = ?)",
        (g.current_user_id, target_id, target_id, g.current_user_id),
    )
    if existing:
        if existing["status"] == "accepted":
            return jsonify({"message": "already friends", "request_id": existing["id"]})
        else:
            return jsonify({"message": "request pending", "request_id": existing["id"]})

    created = now_ts()
    rid = db_execute(
        "INSERT INTO friendships (user_id, friend_id, status, initiated_by, created_at) VALUES (?, ?, 'pending', ?, ?)",
        (g.current_user_id, target_id, g.current_user_id, created),
    )
    return jsonify({"request_id": rid, "message": "request sent"})

@app.route("/v1/friends/requests", methods=["GET"])
def route_friend_requests():
    if auth_err := require_auth():
        return auth_err
    # 查询发给我的好友请求
    rows = db_query_all(
        """SELECT f.id, f.user_id AS from_user_id, f.created_at
           FROM friendships f
           WHERE f.friend_id = ? AND f.status = 'pending'
           ORDER BY f.created_at DESC""",
        (g.current_user_id,),
    )
    result = []
    for r in rows:
        from_user = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (r["from_user_id"],))
        if from_user:
            result.append({
                "id": r["id"],
                "from_uid": from_user["uid"],
                "from_name": from_user["display_name"] or from_user["uid"],
                "from_avatar": from_user["avatar_url"] or "",
                "created_at": r["created_at"],
                "status": "pending",
            })
    return jsonify({"requests": result})

@app.route("/v1/friends/respond", methods=["POST"])
def route_friend_respond():
    if auth_err := require_auth():
        return auth_err
    body = req_json()
    request_id = body.get("request_id")
    accept = body.get("accept")
    if accept is None:
        accept = body.get("accepted")
    if accept is None:
        accept = True
    if not request_id:
        return err("missing request_id", "missing_id")

    # 找到请求，且我是被请求方
    row = db_query_one(
        "SELECT * FROM friendships WHERE id = ? AND friend_id = ? AND status = 'pending'",
        (request_id, g.current_user_id),
    )
    if not row:
        return err("request not found", "not_found", 404)
    if accept:
        db_execute("UPDATE friendships SET status = 'accepted' WHERE id = ?", (request_id,))
    else:
        db_execute("DELETE FROM friendships WHERE id = ?", (request_id,))
    return jsonify({"status": "accepted" if accept else "rejected"})

# --- 10. Groups Routes ---
@app.route("/v1/groups/list", methods=["GET"])
def route_groups_list():
    if auth_err := require_auth():
        return auth_err
    rows = db_query_all(
        """SELECT g.* FROM groups g
           JOIN group_members gm ON gm.group_id = g.group_id
           WHERE gm.user_id = ?
           ORDER BY g.created_at DESC""",
        (g.current_user_id,),
    )
    groups = []
    for r in rows:
        member_count = db_query_one(
            "SELECT COUNT(*) AS c FROM group_members WHERE group_id = ?", (r["group_id"],)
        )
        groups.append({
            "group_id": r["group_id"],
            "name": r["name"],
            "avatar_url": r["avatar_url"] or "",
            "owner_id": r["owner_id"],
            "created_at": r["created_at"],
            "member_count": member_count["c"] if member_count else 0,
        })
    return jsonify({"groups": groups})

@app.route("/v1/groups/create", methods=["POST"])
def route_groups_create():
    if auth_err := require_auth():
        return auth_err
    body = req_json()
    name = (body.get("name") or body.get("group_name") or "").strip()
    avatar_url = body.get("avatar_url") or ""
    if not name:
        return err("group name required", "missing_name")
    group_id = gen_group_id()
    created = now_ts()
    db_execute(
        "INSERT INTO groups (group_id, name, avatar_url, owner_id, created_at) VALUES (?, ?, ?, ?, ?)",
        (group_id, name, avatar_url, g.current_user_id, created),
    )
    db_execute(
        "INSERT INTO group_members (group_id, user_id, joined_at) VALUES (?, ?, ?)",
        (group_id, g.current_user_id, created),
    )
    return jsonify({
        "group_id": group_id,
        "name": name,
        "avatar_url": avatar_url,
        "owner_id": g.current_user_id,
        "created_at": created,
    })

@app.route("/v1/groups/join", methods=["POST"])
def route_groups_join():
    if auth_err := require_auth():
        return auth_err
    body = req_json()
    group_id = (body.get("group_id") or "").strip()
    if not group_id:
        return err("missing group_id", "missing_group")
    g_row = db_query_one("SELECT * FROM groups WHERE group_id = ?", (group_id,))
    if not g_row:
        return err("group not found", "not_found", 404)
    existing = db_query_one(
        "SELECT id FROM group_members WHERE group_id = ? AND user_id = ?",
        (group_id, g.current_user_id),
    )
    if existing:
        return jsonify({"message": "already member", "group_id": group_id})
    db_execute(
        "INSERT INTO group_members (group_id, user_id, joined_at) VALUES (?, ?, ?)",
        (group_id, g.current_user_id, now_ts()),
    )
    return jsonify({"group_id": group_id, "joined": True})

@app.route("/v1/groups/leave", methods=["POST"])
def route_groups_leave():
    if auth_err := require_auth():
        return auth_err
    body = req_json()
    group_id = (body.get("group_id") or "").strip()
    db_execute("DELETE FROM group_members WHERE group_id = ? AND user_id = ?",
               (group_id, g.current_user_id))
    return jsonify({"left": True, "group_id": group_id})

@app.route("/v1/groups/members", methods=["GET"])
def route_groups_members():
    if auth_err := require_auth():
        return auth_err
    group_id = request.args.get("group_id", "").strip()
    if not group_id:
        return err("missing group_id", "missing_group")
    rows = db_query_all(
        """SELECT u.*, gm.joined_at FROM users u
           JOIN group_members gm ON gm.user_id = u.id
           WHERE gm.group_id = ?
           ORDER BY gm.joined_at ASC""",
        (group_id,),
    )
    members = []
    for r in rows:
        ud = user_to_dict(r)
        ud["joined_at"] = r["joined_at"]
        members.append(ud)
    return jsonify({"members": members})

@app.route("/v1/groups/settings", methods=["POST"])
def route_groups_settings():
    if auth_err := require_auth():
        return auth_err
    body = req_json()
    group_id = (body.get("group_id") or "").strip()
    name = body.get("name")
    avatar_url = body.get("avatar_url")
    row = db_query_one("SELECT * FROM groups WHERE group_id = ? AND owner_id = ?",
                       (group_id, g.current_user_id))
    if not row:
        return err("not owner or group not found", "forbidden", 403)
    sets, params = [], []
    if name:
        sets.append("name = ?")
        params.append(name)
    if avatar_url:
        sets.append("avatar_url = ?")
        params.append(avatar_url)
    if sets:
        params.append(group_id)
        db_execute(f"UPDATE groups SET {', '.join(sets)} WHERE group_id = ?", tuple(params))
    row = db_query_one("SELECT * FROM groups WHERE group_id = ?", (group_id,))
    return jsonify({
        "group_id": row["group_id"],
        "name": row["name"],
        "avatar_url": row["avatar_url"] or "",
        "owner_id": row["owner_id"],
        "created_at": row["created_at"],
    })

# --- 11. Direct Messages ---
@app.route("/v1/direct/messages/v2", methods=["GET"])
def route_direct_messages():
    if auth_err := require_auth():
        return auth_err
    with_uid = (request.args.get("with_uid") or "").strip().upper()
    limit = min(int(request.args.get("limit", 50)), 200)
    offset = int(request.args.get("offset", 0))
    if not with_uid:
        return err("missing with_uid", "missing_uid")
    target = db_query_one("SELECT id FROM users WHERE uid = ?", (with_uid,))
    if not target:
        return err("user not found", "not_found", 404)
    target_id = target["id"]
    me = g.current_user_id

    # 子查询分页，确保按时间升序返回
    rows = db_query_all(
        """SELECT * FROM (
            SELECT * FROM direct_messages
            WHERE (from_id = ? AND to_id = ?) OR (from_id = ? AND to_id = ?)
            ORDER BY created_at DESC, id DESC
            LIMIT ? OFFSET ?
        ) ORDER BY created_at ASC, id ASC""",
        (me, target_id, target_id, me, limit, offset),
    )
    messages = [direct_msg_to_dict(r) for r in rows]
    return jsonify({"messages": messages})

@app.route("/v1/direct/send", methods=["POST"])
def route_direct_send():
    if auth_err := require_auth():
        return auth_err
    body = req_json()
    to_uid = (body.get("to_uid") or "").strip().upper()
    msg_body = body.get("body", "") or ""
    msg_type = body.get("msg_type", "text") or "text"
    burn = int(body.get("burn_after_seconds", 0) or 0)
    media_url = body.get("media_url") or ""
    thumb_url = body.get("thumb_url") or ""

    if not to_uid:
        return err("missing to_uid", "missing_uid")
    target = db_query_one("SELECT id FROM users WHERE uid = ?", (to_uid,))
    if not target:
        return err("user not found", "not_found", 404)
    target_id = target["id"]

    created = now_ts()
    msg_id = db_execute(
        """INSERT INTO direct_messages
           (from_id, to_id, body, msg_type, media_url, thumb_url, burn_after_seconds, created_at, is_read)
           VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0)""",
        (g.current_user_id, target_id, msg_body, msg_type, media_url, thumb_url, burn, created),
    )
    # 获取插入的消息
    row = db_query_one("SELECT * FROM direct_messages WHERE id = ?", (msg_id,))
    msg_dict = direct_msg_to_dict(row)

    # WebSocket 推送
    online_ids = get_online_user_ids()
    if target_id in online_ids:
        push_message(target_id, {
            "type": "message",
            "message": msg_dict,
            "conversation_type": "direct",
        })

    return jsonify({"status": "ok", "message": msg_dict})

@app.route("/v1/direct/read", methods=["POST"])
def route_direct_read():
    if auth_err := require_auth():
        return auth_err
    body = req_json()
    with_uid = (body.get("with_uid") or "").strip().upper()
    if not with_uid:
        return err("missing with_uid", "missing_uid")
    target = db_query_one("SELECT id FROM users WHERE uid = ?", (with_uid,))
    if not target:
        return err("user not found", "not_found", 404)
    target_id = target["id"]
    me = g.current_user_id
    db_execute(
        "UPDATE direct_messages SET is_read = 1 WHERE from_id = ? AND to_id = ? AND is_read = 0",
        (target_id, me),
    )
    # 广播已读回执
    push_message(target_id, {
        "type": "read",
        "thread_uid": db_query_one("SELECT uid FROM users WHERE id = ?", (me,))["uid"],
        "by_uid": db_query_one("SELECT uid FROM users WHERE id = ?", (me,))["uid"],
        "read_at": now_ts(),
    })
    return jsonify({"status": "ok"})

@app.route("/v1/direct/unread", methods=["POST", "GET"])
def route_direct_unread():
    if auth_err := require_auth():
        return auth_err
    limit = min(int(request.args.get("limit", 50) or req_json().get("limit", 50)), 200)
    me = g.current_user_id
    # 获取每个会话最后一条未读消息
    rows = db_query_all(
        """SELECT dm.* FROM direct_messages dm
           WHERE dm.to_id = ? AND dm.is_read = 0
           ORDER BY dm.created_at DESC
           LIMIT ?""",
        (me, limit),
    )
    messages = [direct_msg_to_dict(r) for r in rows]
    return jsonify({"messages": messages})

# --- 12. Group Messages ---
@app.route("/v1/groups/messages/v2", methods=["GET"])
def route_group_messages():
    if auth_err := require_auth():
        return auth_err
    group_id = (request.args.get("group_id") or "").strip()
    limit = min(int(request.args.get("limit", 50)), 200)
    offset = int(request.args.get("offset", 0))
    if not group_id:
        return err("missing group_id", "missing_group")

    # 检查是否群成员
    member = db_query_one(
        "SELECT id FROM group_members WHERE group_id = ? AND user_id = ?",
        (group_id, g.current_user_id),
    )
    if not member:
        return err("not a member", "forbidden", 403)

    rows = db_query_all(
        """SELECT * FROM (
            SELECT * FROM group_messages
            WHERE group_id = ?
            ORDER BY created_at DESC, id DESC
            LIMIT ? OFFSET ?
        ) ORDER BY created_at ASC, id ASC""",
        (group_id, limit, offset),
    )
    messages = [group_msg_to_dict(r) for r in rows]
    return jsonify({"messages": messages})

@app.route("/v1/groups/message/send", methods=["POST"])
def route_group_send():
    if auth_err := require_auth():
        return auth_err
    body = req_json()
    group_id = (body.get("group_id") or "").strip()
    msg_body = body.get("body", "") or ""
    msg_type = body.get("msg_type", "text") or "text"
    burn = int(body.get("burn_after_seconds", 0) or 0)
    media_url = body.get("media_url") or ""
    thumb_url = body.get("thumb_url") or ""
    if not group_id:
        return err("missing group_id", "missing_group")

    member = db_query_one(
        "SELECT id FROM group_members WHERE group_id = ? AND user_id = ?",
        (group_id, g.current_user_id),
    )
    if not member:
        return err("not a member", "forbidden", 403)

    created = now_ts()
    msg_id = db_execute(
        """INSERT INTO group_messages
           (group_id, from_id, body, msg_type, media_url, thumb_url, burn_after_seconds, created_at)
           VALUES (?, ?, ?, ?, ?, ?, ?, ?)""",
        (group_id, g.current_user_id, msg_body, msg_type, media_url, thumb_url, burn, created),
    )
    row = db_query_one("SELECT * FROM group_messages WHERE id = ?", (msg_id,))
    msg_dict = group_msg_to_dict(row)

    # 推送给所有在线群成员（排除发送者）
    members = db_query_all(
        "SELECT user_id FROM group_members WHERE group_id = ? AND user_id != ?",
        (group_id, g.current_user_id),
    )
    online_ids = get_online_user_ids()
    for m in members:
        if m["user_id"] in online_ids:
            push_message(m["user_id"], {
                "type": "message",
                "message": msg_dict,
                "conversation_type": "group",
            })

    return jsonify({"status": "ok", "message": msg_dict})

@app.route("/v1/groups/read", methods=["POST"])
def route_group_read():
    if auth_err := require_auth():
        return auth_err
    body = req_json()
    group_id = (body.get("group_id") or "").strip()
    if not group_id:
        return err("missing group_id", "missing_group")
    member = db_query_one(
        "SELECT id FROM group_members WHERE group_id = ? AND user_id = ?",
        (group_id, g.current_user_id),
    )
    if not member:
        return err("not a member", "forbidden", 403)
    return jsonify({"status": "ok"})

@app.route("/v1/groups/unread", methods=["POST", "GET"])
def route_group_unread():
    if auth_err := require_auth():
        return auth_err
    limit = min(int(request.args.get("limit", 50) or req_json().get("limit", 50)), 200)
    me = g.current_user_id
    # 获取我所在群组的最新消息（简化版）
    rows = db_query_all(
        """SELECT gm.* FROM group_messages gm
           JOIN group_members gmm ON gmm.group_id = gm.group_id
           WHERE gmm.user_id = ? AND gm.from_id != ?
           ORDER BY gm.created_at DESC
           LIMIT ?""",
        (me, me, limit),
    )
    messages = [group_msg_to_dict(r) for r in rows]
    return jsonify({"messages": messages})

# --- 13. Media Upload & Static ---
@app.route("/v1/media", methods=["POST"])
def route_upload_media():
    if auth_err := require_auth():
        return auth_err
    if "file" not in request.files:
        return err("no file", "no_file")
    f = request.files["file"]
    if not f or f.filename == "":
        return err("empty file", "empty_file")
    ext = os.path.splitext(f.filename)[1].lower()
    filename = secrets.token_hex(16) + ext
    filepath = os.path.join(MEDIA_DIR, filename)
    f.save(filepath)
    url = f"/media/{filename}"
    return jsonify({"url": url, "media_url": url, "thumb_url": url, "filename": filename})

@app.route("/media/<path:filename>", methods=["GET"])
def route_serve_media(filename):
    return send_from_directory(MEDIA_DIR, filename)

@app.route("/", methods=["GET"])
def route_root():
    return jsonify({
        "service": "oldchat-compat",
        "status": "ok",
        "endpoints": [
            "/v1/auth/handshake", "/v1/auth/login", "/v1/auth/register", "/v1/auth/refresh",
            "/v1/me", "/v1/users/profile",
            "/v1/friends", "/v1/friends/request", "/v1/friends/requests", "/v1/friends/respond",
            "/v1/groups/list", "/v1/groups/create", "/v1/groups/join", "/v1/groups/members",
            "/v1/direct/messages/v2", "/v1/direct/send", "/v1/direct/read", "/v1/direct/unread",
            "/v1/groups/messages/v2", "/v1/groups/message/send", "/v1/groups/read", "/v1/groups/unread",
            "/v1/media", "/media/<filename>",
        ],
        "websocket": "/ws",
    })

# --- 14. WebSocket Handler ---
if HAS_EVENTLET:
    @WebSocketWSGI
    def ws_handler(ws):
        user_id = None
        auth_done = False
        # 先尝试从 query 参数获取 token
        env = getattr(ws, "environ", {}) or {}
        query_string = env.get("QUERY_STRING", "")
        query_token = None
        for part in query_string.split("&"):
            if part.startswith("token="):
                query_token = part[6:]
                break

        if query_token:
            row = db_query_one(
                "SELECT user_id FROM tokens WHERE access_token = ? AND expires_at > ?",
                (query_token, int(time.time())),
            )
            if row:
                user_id = row["user_id"]
                auth_done = True
                register_ws(user_id, ws)
                try:
                    user_row = db_query_one("SELECT uid, display_name FROM users WHERE id = ?", (user_id,))
                    ws.send(json.dumps({
                        "type": "connected",
                        "user_id": user_row["uid"] if user_row else user_id,
                        "online": 1,
                    }, ensure_ascii=False))
                except Exception:
                    pass
                broadcast_presence(user_id, True)

        # 持续处理消息
        try:
            while True:
                msg = ws.wait()
                if msg is None:
                    break
                try:
                    data = json.loads(msg)
                except Exception:
                    continue
                mtype = data.get("type")
                if mtype == "auth" and not auth_done:
                    token = data.get("token", "")
                    row = db_query_one(
                        "SELECT user_id FROM tokens WHERE access_token = ? AND expires_at > ?",
                        (token, int(time.time())),
                    )
                    if row:
                        user_id = row["user_id"]
                        auth_done = True
                        register_ws(user_id, ws)
                        try:
                            user_row = db_query_one("SELECT uid FROM users WHERE id = ?", (user_id,))
                            ws.send(json.dumps({
                                "type": "connected",
                                "user_id": user_row["uid"] if user_row else user_id,
                            }, ensure_ascii=False))
                        except Exception:
                            pass
                        broadcast_presence(user_id, True)
                elif mtype == "ping":
                    try:
                        ws.send(json.dumps({"type": "pong", "ts": int(time.time())}))
                    except Exception:
                        break
                elif mtype == "keepalive":
                    pass
        except Exception:
            pass
        finally:
            if user_id:
                unregister_ws(user_id, ws)
                broadcast_presence(user_id, False)

    def dispatch(environ, start_response):
        path = environ.get("PATH_INFO", "")
        if path == "/ws":
            return ws_handler(environ, start_response)
        return app(environ, start_response)
else:
    def dispatch(environ, start_response):
        path = environ.get("PATH_INFO", "")
        if path == "/ws":
            start_response("501 Not Implemented", [("Content-Type", "application/json")])
            return [b'{"error":"websocket not available","hint":"install eventlet"}']
    dispatch = None  # type: ignore

# --- 15. Main ---
def _bootstrap():
    init_db()
    logging.info(f"[init] DB: {DB_PATH}")
    logging.info(f"[init] Media: {MEDIA_DIR}")
    logging.info(f"[init] WebSocket: {'eventlet' if HAS_EVENTLET else 'UNAVAILABLE'}")

if __name__ == "__main__":
    _bootstrap()
    host = os.environ.get("HOST", "0.0.0.0")
    port = int(os.environ.get("PORT", 8080))
    print(f"\nOldChat 兼容服务器启动: http://{host}:{port}")
    print(f"WebSocket: ws://{host}:{port}/ws")
    if HAS_EVENTLET:
        eventlet_wsgi.server(eventlet.listen((host, port)), dispatch)
    else:
        app.run(host=host, port=port, debug=False, threaded=True)
else:
    _bootstrap()
