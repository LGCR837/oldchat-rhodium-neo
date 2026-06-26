# ====================================================================
# OldChat 兼容服务器 — 单文件实现 (Python 3 + Flask + SQLite3 + eventlet)
# 参考: OldChat Android 源码 (g0/*, o0/*, com/im/oldchat/ui/*)
# ====================================================================

# --- eventlet.monkey_patch() 必须放在最前面 ---
try:
    import eventlet
    eventlet.monkey_patch(socket=True, select=True, time=True, thread=True)
    HAS_EVENTLET = True
except Exception:
    HAS_EVENTLET = False

import os
import sys
import json
import time
import secrets
import hashlib
import hmac
import base64
import sqlite3
import logging
import threading
from pathlib import Path

from flask import (Flask, g, request, jsonify, send_from_directory,
                   make_response, abort)

if HAS_EVENTLET:
    try:
        from eventlet import wsgi as _ew
        from eventlet.websocket import WebSocketWSGI
        from eventlet.websocket import WebSocket
    except Exception:
        HAS_EVENTLET = False

from cryptography.hazmat.primitives.asymmetric import ec
from cryptography.hazmat.primitives import serialization
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend

# --- Config ---
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
DB_PATH = os.path.join(BASE_DIR, "oldchat.db")
MEDIA_DIR = os.path.join(BASE_DIR, "media")
UPLOAD_DIR = os.path.join(BASE_DIR, "uploads")
os.makedirs(MEDIA_DIR, exist_ok=True)
os.makedirs(UPLOAD_DIR, exist_ok=True)

APP_VERSION = "1.0.0"
APP_NAME = "OldChat"

app = Flask(__name__, static_folder=None)
app.config["MAX_CONTENT_LENGTH"] = 100 * 1024 * 1024
app.secret_key = secrets.token_hex(32)

IS_WINDOWS = sys.platform.startswith("win")

logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s [%(levelname)s] %(name)s: %(message)s",
)
log = logging.getLogger("oldchat")

# ---------- Settings ----------
# SETTINGS_PATH 保留环境变量 fallback（方便命令行指定配置文件）
SETTINGS_PATH = os.environ.get(
    "SETTINGS_PATH",
    os.path.join(os.path.dirname(os.path.abspath(__file__)), "settings.json")
)

def _load_settings(path):
    try:
        with open(path, "r", encoding="utf-8") as f:
            data = json.load(f)
        log.info("[settings] 已加载: %s", path)
        return data
    except FileNotFoundError:
        log.warning("[settings] %s 未找到，使用默认配置", path)
    except Exception as e:
        log.warning("[settings] 加载失败: %s，使用默认配置", e)
    return {
        "server": {"name": "OldChat", "version": "1.0.0", "url": "", "host": "0.0.0.0", "port": 8080},
        "debug": false,
        "announcement": {"title": "服务器公告", "body": "这是 OldChat 兼容服务器。", "enabled": true},
        "auto_join_group": {"enabled": True, "group_id": "", "group_name": "官方群聊", "auto_create": True, "welcome_message": "欢迎加入官方群聊！"},
        "features": {},
        "limits": {"max_upload_mb": 20, "max_message_length": 2000},
    }

SETTINGS = _load_settings(SETTINGS_PATH)

# 从 settings 读取配置，环境变量仅作为 fallback 覆盖
DEBUG_REQ = bool(SETTINGS.get("debug", True))
if os.environ.get("DEBUG_REQ"):
    DEBUG_REQ = os.environ["DEBUG_REQ"] == "1"

def _ensure_default_group():
    cfg = SETTINGS.get("auto_join_group", {}) or {}
    if not cfg.get("enabled"):
        return None
    gid = (cfg.get("group_id") or "").strip()
    name = cfg.get("group_name") or "官方群聊"
    if gid:
        existing = db_query_one("SELECT group_id FROM groups WHERE group_id = ?", (gid,))
        if existing:
            return gid
        db_execute(
            "INSERT INTO groups (group_id, name, avatar_url, owner_id, created_at) VALUES (?, ?, '', 0, ?)",
            (gid, name, now_ts()),
        )
        return gid
    if cfg.get("auto_create"):
        existing = db_query_one("SELECT group_id FROM groups WHERE name = ?", (name,))
        if existing:
            return existing["group_id"]
        gid = gen_group_id()
        db_execute(
            "INSERT INTO groups (group_id, name, avatar_url, owner_id, created_at) VALUES (?, ?, '', 0, ?)",
            (gid, name, now_ts()),
        )
        return gid
    return None

# Windows 下 eventlet 有一些兼容性问题（WSAEventSelect / greenlet 初始化等）
# 如果在 Windows 上 monkey_patch 没能把 socket 真正绿化，就安全降级为 threading + 明文模式
if IS_WINDOWS and HAS_EVENTLET:
    try:
        import socket as _socket_mod
        _mod = getattr(_socket_mod.socket, "__module__", "") or ""
        if "eventlet" not in _mod:
            log.warning("[Windows] eventlet monkey_patch 未完全生效（socket=%s），降级为 threading 模式（WebSocket 将不可用）", _mod)
            HAS_EVENTLET = False
    except Exception as _e:
        log.warning("[Windows] eventlet 自检失败(%s)，降级为 threading 模式", _e)
        HAS_EVENTLET = False

# --- Crypto Helpers -------------------------------------------------------

def encrypt_body(aes_key: bytes, mac_key: bytes, plaintext: bytes) -> dict:
    iv = os.urandom(16)
    pad_len = 16 - (len(plaintext) % 16)
    padded = plaintext + bytes([pad_len]) * pad_len
    cipher = Cipher(algorithms.AES(aes_key), modes.CBC(iv), backend=default_backend())
    ct = cipher.encryptor().update(padded) + cipher.encryptor().finalize()
    # HMAC-SHA256(mac_key, iv || ciphertext) — 与 Android 客户端完全一致
    mac_v = hmac.new(mac_key, iv + ct, hashlib.sha256).digest()
    return {
        "iv": base64.b64encode(iv).decode("ascii"),
        "data": base64.b64encode(ct).decode("ascii"),
        "mac": base64.b64encode(mac_v).decode("ascii"),
    }

def decrypt_body(aes_key: bytes, mac_key: bytes, enc_dict: dict) -> bytes:
    iv = base64.b64decode(enc_dict["iv"])
    ct = base64.b64decode(enc_dict["data"])
    mac_expected = base64.b64decode(enc_dict["mac"])
    # HMAC-SHA256(mac_key, iv || ciphertext)
    mac_actual = hmac.new(mac_key, iv + ct, hashlib.sha256).digest()
    if not hmac.compare_digest(mac_actual, mac_expected):
        raise ValueError("MAC mismatch")
    cipher = Cipher(algorithms.AES(aes_key), modes.CBC(iv), backend=default_backend())
    plain = cipher.decryptor().update(ct) + cipher.decryptor().finalize()
    pad_len = plain[-1]
    return plain[:-pad_len]

def do_ecdh_handshake(client_pub_b64: str):
    server_private = ec.generate_private_key(ec.SECP256R1(), default_backend())
    server_pub = server_private.public_key().public_bytes(
        encoding=serialization.Encoding.DER,
        format=serialization.PublicFormat.SubjectPublicKeyInfo,
    )
    client_pub = serialization.load_der_public_key(
        base64.b64decode(client_pub_b64), default_backend()
    )
    shared = server_private.exchange(ec.ECDH(), client_pub)
    if len(shared) < 32:
        raw = b"\x00" * (32 - len(shared)) + shared
    else:
        raw = shared[-32:]
    aes_key = hashlib.sha256(raw + b"enc").digest()
    mac_key = hashlib.sha256(raw + b"mac").digest()
    session_id = secrets.token_hex(16).upper()
    return session_id, base64.b64encode(server_pub).decode("ascii"), aes_key, mac_key

# --- DB -------------------------------------------------------------------

_db_lock = threading.Lock()

def db_query_one(sql: str, params=()):
    with _db_lock:
        conn = sqlite3.connect(DB_PATH, check_same_thread=False)
        conn.row_factory = sqlite3.Row
        try:
            cur = conn.execute(sql, params)
            return cur.fetchone()
        finally:
            conn.close()

def db_query_all(sql: str, params=()):
    with _db_lock:
        conn = sqlite3.connect(DB_PATH, check_same_thread=False)
        conn.row_factory = sqlite3.Row
        try:
            return conn.execute(sql, params).fetchall()
        finally:
            conn.close()

def db_execute(sql: str, params=()) -> int:
    with _db_lock:
        conn = sqlite3.connect(DB_PATH, check_same_thread=False)
        try:
            cur = conn.execute(sql, params)
            conn.commit()
            lid = cur.lastrowid
            return lid if lid else 0
        finally:
            conn.close()

def db_executemany(sql: str, params_list):
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
                signature TEXT DEFAULT '',
                online INTEGER DEFAULT 0,
                last_seen INTEGER,
                created_at INTEGER
            );
            CREATE INDEX IF NOT EXISTS idx_users_uid ON users(uid);

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
                last_read_msg_id INTEGER DEFAULT 0,
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
            CREATE INDEX IF NOT EXISTS idx_dm_pair ON direct_messages(from_id, to_id);
            CREATE INDEX IF NOT EXISTS idx_dm_pair2 ON direct_messages(to_id, from_id);

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
            CREATE INDEX IF NOT EXISTS idx_gm_group ON group_messages(group_id);

            CREATE TABLE IF NOT EXISTS notifications (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id INTEGER NOT NULL,
                type TEXT DEFAULT 'system',
                title TEXT,
                body TEXT,
                extra_json TEXT,
                created_at INTEGER,
                is_read INTEGER DEFAULT 0
            );
            CREATE INDEX IF NOT EXISTS idx_notif_user ON notifications(user_id);

            CREATE TABLE IF NOT EXISTS moments (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                moment_id TEXT UNIQUE NOT NULL,
                user_id INTEGER NOT NULL,
                body TEXT,
                media_url TEXT,
                thumb_url TEXT,
                created_at INTEGER,
                likes INTEGER DEFAULT 0
            );
            CREATE INDEX IF NOT EXISTS idx_moments_user ON moments(user_id);

            CREATE TABLE IF NOT EXISTS moment_comments (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                moment_id TEXT NOT NULL,
                user_id INTEGER NOT NULL,
                body TEXT,
                created_at INTEGER
            );
            CREATE INDEX IF NOT EXISTS idx_mc_moment ON moment_comments(moment_id);

            CREATE TABLE IF NOT EXISTS moment_likes (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                moment_id TEXT NOT NULL,
                user_id INTEGER NOT NULL,
                created_at INTEGER,
                UNIQUE(moment_id, user_id)
            );

            CREATE TABLE IF NOT EXISTS typing_states (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                chat_id TEXT NOT NULL,
                user_id INTEGER NOT NULL,
                is_group INTEGER DEFAULT 0,
                updated_at INTEGER,
                UNIQUE(chat_id, user_id)
            );
        """)
        # 迁移：旧数据库升级
        _migrations = [
            ("users", "signature", "TEXT DEFAULT ''"),
            ("group_members", "last_read_msg_id", "INTEGER DEFAULT 0"),
        ]
        for table, col, definition in _migrations:
            try:
                conn.execute(f"ALTER TABLE {table} ADD COLUMN {col} {definition}")
            except Exception:
                pass
        conn.commit()
    finally:
        conn.close()

# --- WebSocket Connection Registry ----------------------------------------

_ws_lock = threading.Lock()
_ws_conns = {}  # user_id -> set(ws_objects)
_ws_active_sessions = {}  # user_id -> set(session_ids) (for dedupe)

def register_ws(user_id, ws):
    with _ws_lock:
        _ws_conns.setdefault(user_id, set()).add(ws)

def unregister_ws(user_id, ws):
    with _ws_lock:
        if user_id in _ws_conns:
            _ws_conns[user_id].discard(ws)
            if not _ws_conns[user_id]:
                del _ws_conns[user_id]

def ws_online_user_ids():
    with _ws_lock:
        return set(_ws_conns.keys())

def push_to_user(user_id, payload: dict):
    with _ws_lock:
        conns = list(_ws_conns.get(user_id, set()))
    if not conns:
        return 0
    text = json.dumps(payload, ensure_ascii=False)
    sent = 0
    for ws in conns:
        try:
            ws.send(text)
            sent += 1
        except Exception:
            pass
    return sent

# --- Helpers --------------------------------------------------------------

def now_ts() -> int:
    return int(time.time())

def gen_uid() -> str:
    return secrets.token_hex(5).upper()

def gen_group_id() -> str:
    import string
    chars = string.ascii_uppercase + string.digits
    return "GRP-" + "".join(secrets.choice(chars) for _ in range(6))

def gen_moment_id() -> str:
    return "MMT-" + secrets.token_hex(12).upper()

def hash_password(password: str) -> str:
    salt = os.urandom(16)
    pw_hash = hashlib.pbkdf2_hmac("sha256", password.encode("utf-8"), salt, 100000)
    return "pbkdf2_sha256$100000$" + base64.b64encode(salt).decode() + "$" + base64.b64encode(pw_hash).decode()

def verify_password(password: str, stored: str) -> bool:
    try:
        parts = stored.split("$")
        if len(parts) != 4:
            return False
        _, iters, salt_b64, hash_b64 = parts
        salt = base64.b64decode(salt_b64)
        expected = base64.b64decode(hash_b64)
        actual = hashlib.pbkdf2_hmac("sha256", password.encode("utf-8"), salt, int(iters))
        return actual == expected
    except Exception:
        return False

def issue_tokens(user_id: int) -> tuple:
    access_token = secrets.token_hex(20).upper()
    refresh_token = secrets.token_hex(32).upper()
    created = now_ts()
    expires = created + 30 * 24 * 3600
    db_execute(
        "INSERT INTO tokens (access_token, refresh_token, user_id, created_at, expires_at) VALUES (?, ?, ?, ?, ?)",
        (access_token, refresh_token, user_id, created, expires),
    )
    return access_token, refresh_token

def user_to_dict(row) -> dict:
    if row is None:
        return {}
    uid = row["uid"]
    return {
        "id": str(row["id"]),
        "uid": uid,
        "username": row["username"],
        "display_name": row["display_name"] or uid,
        "avatar_url": row["avatar_url"] or "",
        "cover_url": row["cover_url"] or "",
        "bio": row["bio"] or "",
        "signature": row["signature"] if "signature" in row.keys() else "",
        "online": 1 if row["id"] in ws_online_user_ids() else 0,
        "last_seen": row["last_seen"] or row["created_at"],
        "created_at": row["created_at"],
    }

def uid_to_user_id(uid: str):
    if not uid:
        return None
    row = db_query_one("SELECT id FROM users WHERE uid = ?", (uid.upper(),))
    return row["id"] if row else None

def require_auth():
    """Return error response if not authenticated, else None."""
    if not getattr(g, "current_user_id", None):
        return jsonify({"code": 401, "msg": "Unauthorized"}), 401
    return None

# --- Middleware -----------------------------------------------------------

@app.before_request
def before_request_mw():
    if request.method == "OPTIONS":
        resp = make_response("", 204)
        resp.headers["Access-Control-Allow-Origin"] = "*"
        resp.headers["Access-Control-Allow-Methods"] = "GET, POST, PUT, DELETE, OPTIONS"
        resp.headers["Access-Control-Allow-Headers"] = "Content-Type, Authorization, X-Enc, X-Session"
        return resp

    g.current_session_id = None
    g.current_aes_key = None
    g.current_hmac_key = None
    g.decrypted_json = None
    g.current_user_id = None

    # --- 调试模式：打印请求摘要 ---
    if DEBUG_REQ:
        ctype = request.headers.get("Content-Type", "")
        log.info("REQ %s %s (IP=%s, ctype=%s)", request.method, request.path,
                 request.remote_addr, ctype)

    # --- 认证：Authorization header (plain bearer) ---
    auth_header = request.headers.get("Authorization", "")
    if auth_header.lower().startswith("bearer "):
        token = auth_header.split(" ", 1)[1].strip()
        row = db_query_one(
            "SELECT user_id FROM tokens WHERE access_token = ? AND expires_at > ?",
            (token, now_ts()),
        )
        if row:
            g.current_user_id = row["user_id"]
            if DEBUG_REQ:
                log.info("  -> bearer ok, user=%s", row["user_id"])

    # --- 会话：X-Session + X-Enc 头 ---
    session_id = request.headers.get("X-Session") or ""
    enc_flag = request.headers.get("X-Enc") or ""
    if session_id and enc_flag == "1":
        row = db_query_one(
            "SELECT aes_key_b64, hmac_key_b64, user_id FROM sessions WHERE session_id = ? AND expires_at > ?",
            (session_id, now_ts()),
        )
        if row:
            g.current_session_id = session_id
            try:
                g.current_aes_key = base64.b64decode(row["aes_key_b64"])
                g.current_hmac_key = base64.b64decode(row["hmac_key_b64"])
            except Exception:
                g.current_aes_key = None
                g.current_hmac_key = None
            if row["user_id"]:
                g.current_session_user_id = row["user_id"]
                if not g.current_user_id:
                    g.current_user_id = row["user_id"]
            if DEBUG_REQ:
                log.info("  -> session ok (X-Session=%s)", session_id[:12])

            # --- 解密请求体（POST/PUT/DELETE 且有 aes_key）---
            if request.method in ("POST", "PUT", "DELETE") and g.current_aes_key:
                raw_body = request.get_data(cache=True)
                if raw_body and len(raw_body) > 0:
                    try:
                        enc_json = json.loads(raw_body.decode("utf-8"))
                        if isinstance(enc_json, dict) and "iv" in enc_json and "data" in enc_json and "mac" in enc_json:
                            plain_bytes = decrypt_body(g.current_aes_key, g.current_hmac_key, enc_json)
                            g.decrypted_json = json.loads(plain_bytes.decode("utf-8"))
                            if DEBUG_REQ:
                                log.info("  -> decrypted body: %s",
                                         json.dumps(g.decrypted_json, ensure_ascii=False)[:200])
                    except Exception as _e:
                        if DEBUG_REQ:
                            log.info("  -> decrypt skipped: %s", _e)
                        # 解密失败时：保留原 JSON（plaintext request），不报错，不中断
                        pass

    # --- fallback: 若 session 绑定了 user_id 但没有 bearer ---
    if not g.current_user_id and getattr(g, "current_session_user_id", None):
        g.current_user_id = g.current_session_user_id

@app.after_request
def after_request_mw(response):
    response.headers["Access-Control-Allow-Origin"] = "*"
    response.headers["Access-Control-Allow-Methods"] = "GET, POST, PUT, DELETE, OPTIONS"
    response.headers["Access-Control-Allow-Headers"] = "Content-Type, Authorization, X-Enc, X-Session"

    if g.current_session_id and g.current_aes_key and response.is_json:
        try:
            data = response.get_json()
            if isinstance(data, (dict, list)):
                plain = json.dumps(data, ensure_ascii=False).encode("utf-8")
                enc = encrypt_body(g.current_aes_key, g.current_hmac_key, plain)
                response.data = json.dumps(enc, ensure_ascii=False).encode("utf-8")
                response.headers["Content-Type"] = "application/json"
        except Exception as e:
            log.warning("encrypt response failed: %s", e)

    return response

# --- body helper ---------------------------------------------------------

def req_json() -> dict:
    if getattr(g, "decrypted_json", None):
        return g.decrypted_json
    data = request.get_json(silent=True)
    return data if data else {}

# =========================================================================
#  Auth Routes
# =========================================================================

@app.route("/v1/auth/handshake", methods=["POST"])
def auth_handshake():
    # handshake 永远用明文 JSON（客户端在握手前还没拿到 session/key）
    body = request.get_json(silent=True) or {}
    client_pub = body.get("client_pub") or body.get("clientPub")
    if not client_pub:
        if DEBUG_REQ:
            log.info("handshake: no client_pub, body keys=%s", list(body.keys())[:10])
        return jsonify({"code": 400, "msg": "missing client_pub"}), 400
    try:
        session_id, server_pub, aes_key, mac_key = do_ecdh_handshake(client_pub)
    except Exception as e:
        if DEBUG_REQ:
            log.warning("handshake failed: %s", e)
        return jsonify({"code": 400, "msg": "handshake failed"}), 400
    created = now_ts()
    expires = created + 24 * 3600
    db_execute(
        "INSERT INTO sessions (session_id, user_id, aes_key_b64, hmac_key_b64, created_at, expires_at) VALUES (?, ?, ?, ?, ?, ?)",
        (session_id, None, base64.b64encode(aes_key).decode(),
         base64.b64encode(mac_key).decode(), created, expires),
    )
    if DEBUG_REQ:
        log.info("handshake ok: session=%s", session_id[:12])
    return jsonify({"session_id": session_id, "server_pub": server_pub, "expires_at": expires})

@app.route("/v1/auth/register", methods=["POST"])
def auth_register():
    # 当请求带 X-Enc=1 且已解密时，用解密后的 body；否则用原生 JSON
    body = g.decrypted_json if getattr(g, "decrypted_json", None) else (request.get_json(silent=True) or {})
    username = (body.get("username") or body.get("uid") or "").strip()
    password = (body.get("password") or "").strip()
    display_name = (body.get("display_name") or "").strip() or username
    if DEBUG_REQ:
        log.info("register: username=%s body_keys=%s", username, list(body.keys())[:10])
    if len(username) < 2:
        return jsonify({"code": 400, "msg": "username too short"}), 400
    if len(password) < 4:
        return jsonify({"code": 400, "msg": "password too short"}), 400
    existing = db_query_one("SELECT id FROM users WHERE username = ? OR uid = ?", (username, username.upper()))
    if existing:
        return jsonify({"code": 409, "msg": "username already exists"}), 409
    uid = gen_uid()
    pw_hash = hash_password(password)
    created = now_ts()
    db_execute(
        "INSERT INTO users (uid, username, password_hash, display_name, avatar_url, cover_url, bio, online, last_seen, created_at) VALUES (?, ?, ?, ?, '', '', '', 0, ?, ?)",
        (uid, username, pw_hash, display_name, created, created),
    )
    row = db_query_one("SELECT * FROM users WHERE uid = ?", (uid,))
    if not row:
        return jsonify({"code": 500, "msg": "db error"}), 500
    if g.current_session_id:
        db_execute("UPDATE sessions SET user_id = ? WHERE session_id = ?",
                   (row["id"], g.current_session_id))
    # Auto-join default group (for new users only)
    default_gid = _ensure_default_group()
    if default_gid:
        try:
            # 先获取入群前的最新消息 ID，作为初始已读位置
            latest_before = db_query_one(
                "SELECT MAX(id) as max_id FROM group_messages WHERE group_id = ?",
                (default_gid,),
            )
            last_read = (latest_before["max_id"] if latest_before else 0) or 0
            db_execute(
                "INSERT OR IGNORE INTO group_members (group_id, user_id, joined_at, last_read_msg_id) VALUES (?, ?, ?, ?)",
                (default_gid, row["id"], now_ts(), last_read),
            )
            # 可选：在群里发一条系统欢迎消息（对新用户来说是未读的）
            welcome = (SETTINGS.get("auto_join_group", {}) or {}).get("welcome_message", "")
            if welcome:
                db_execute(
                    "INSERT INTO group_messages (group_id, from_id, body, msg_type, created_at) VALUES (?, 0, ?, 'system', ?)",
                    (default_gid, welcome, now_ts()),
                )
        except Exception as _e:
            log.warning("[register] auto-join default group failed: %s", _e)
    access_token, refresh_token = issue_tokens(row["id"])
    return jsonify({
        "access_token": access_token,
        "refresh_token": refresh_token,
        "user": user_to_dict(row),
    })

@app.route("/v1/auth/login", methods=["POST"])
def auth_login():
    # 如果请求被加密解密过，优先用它；否则用原生 JSON
    body = g.decrypted_json if getattr(g, "decrypted_json", None) else (request.get_json(silent=True) or {})
    identifier = (body.get("identifier") or body.get("username") or body.get("uid") or "").strip()
    password = (body.get("password") or "").strip()
    if DEBUG_REQ:
        log.info("login: identifier=%s body_keys=%s session=%s",
                 identifier, list(body.keys())[:10],
                 getattr(g, "current_session_id", "")[:12] if getattr(g, "current_session_id", None) else "")
    if not identifier or not password:
        return jsonify({"code": 400, "msg": "missing credentials"}), 400
    row = db_query_one("SELECT * FROM users WHERE username = ? OR uid = ?", (identifier, identifier.upper()))
    if not row or not verify_password(password, row["password_hash"]):
        return jsonify({"code": 401, "msg": "invalid credentials"}), 401
    if g.current_session_id:
        db_execute("UPDATE sessions SET user_id = ? WHERE session_id = ?",
                   (row["id"], g.current_session_id))
    access_token, refresh_token = issue_tokens(row["id"])
    db_execute("UPDATE users SET last_seen = ? WHERE id = ?", (now_ts(), row["id"]))
    return jsonify({
        "access_token": access_token,
        "refresh_token": refresh_token,
        "user": user_to_dict(row),
    })

@app.route("/v1/auth/refresh", methods=["POST"])
def auth_refresh():
    body = req_json()
    refresh_token = (body.get("refresh_token") or "").strip()
    if not refresh_token:
        return jsonify({"code": 401, "msg": "missing refresh_token"}), 401
    row = db_query_one("SELECT user_id FROM tokens WHERE refresh_token = ?", (refresh_token,))
    if not row:
        return jsonify({"code": 401, "msg": "invalid refresh token"}), 401
    db_execute("DELETE FROM tokens WHERE refresh_token = ?", (refresh_token,))
    access_token, new_refresh = issue_tokens(row["user_id"])
    user_row = db_query_one("SELECT * FROM users WHERE id = ?", (row["user_id"],))
    return jsonify({
        "access_token": access_token,
        "refresh_token": new_refresh,
        "user": user_to_dict(user_row) if user_row else {},
    })

@app.route("/v1/auth/captcha", methods=["GET"])
def auth_captcha():
    return jsonify({"captcha_id": "none", "image_url": ""})

@app.route("/v1/auth/email/send", methods=["POST"])
def auth_email_send():
    return jsonify({"sent": False, "message": "email verification disabled"})

# =========================================================================
#  User Routes
# =========================================================================

@app.route("/v1/me", methods=["GET"])
def user_me():
    _auth = require_auth()
    if _auth: return _auth
    row = db_query_one("SELECT * FROM users WHERE id = ?", (g.current_user_id,))
    if not row:
        return jsonify({"code": 404, "msg": "user not found"}), 404
    return jsonify(user_to_dict(row))

@app.route("/v1/me/profile", methods=["POST"])
def user_update_profile():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    if DEBUG_REQ:
        log.info("  -> user_update_profile: body_keys=%s", list(body.keys()))
    sets, params = [], []
    if body.get("display_name") is not None:
        sets.append("display_name = ?"); params.append(body["display_name"])
    if body.get("bio") is not None:
        sets.append("bio = ?"); params.append(body["bio"])
    if body.get("signature") is not None:
        sets.append("signature = ?"); params.append(body["signature"])
    if body.get("avatar_url") is not None:
        sets.append("avatar_url = ?"); params.append(body["avatar_url"])
    if body.get("cover_url") is not None:
        sets.append("cover_url = ?"); params.append(body["cover_url"])
    if sets:
        params.append(g.current_user_id)
        db_execute("UPDATE users SET %s WHERE id = ?" % ", ".join(sets), tuple(params))
        if DEBUG_REQ:
            log.info("  -> user_update_profile: updated fields=%s", sets)
    row = db_query_one("SELECT * FROM users WHERE id = ?", (g.current_user_id,))
    if DEBUG_REQ and row:
        log.info("  -> user_update_profile result: signature='%s', cover_url='%s'",
                 row["signature"] if "signature" in row.keys() else "N/A",
                 row["cover_url"] or "")
    return jsonify(user_to_dict(row))

@app.route("/v1/me/uid", methods=["POST"])
def user_update_uid():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    new_uid = (body.get("uid") or "").strip().upper()
    if DEBUG_REQ:
        log.info("  -> user_update_uid: new_uid=%s, body_keys=%s", new_uid, list(body.keys()))
    if not new_uid:
        return jsonify({"code": 400, "msg": "missing uid"}), 400
    if len(new_uid) < 3 or len(new_uid) > 32:
        return jsonify({"code": 400, "msg": "uid length must be 3-32"}), 400
    existing = db_query_one("SELECT id FROM users WHERE uid = ?", (new_uid,))
    if existing and existing["id"] != g.current_user_id:
        return jsonify({"code": 409, "msg": "uid already exists"}), 409
    db_execute("UPDATE users SET uid = ? WHERE id = ?", (new_uid, g.current_user_id))
    row = db_query_one("SELECT * FROM users WHERE id = ?", (g.current_user_id,))
    if DEBUG_REQ:
        log.info("  -> user_update_uid success: new_uid=%s", row["uid"] if row else "")
    return jsonify(user_to_dict(row))

def _get_upload_file(candidates):
    """从 request.files 中获取上传文件，支持多种字段名，兜底取第一个非空文件"""
    for name in candidates:
        f = request.files.get(name)
        if f and f.filename:
            return f
    for key in request.files:
        f = request.files[key]
        if f and f.filename:
            return f
    return None

@app.route("/v1/me/avatar", methods=["POST"])
def user_update_avatar():
    _auth = require_auth()
    if _auth: return _auth
    f = _get_upload_file(["file", "avatar", "image", "photo", "picture", "upload"])
    if f and f.filename:
        ext = os.path.splitext(f.filename)[1].lower()
        if ext not in (".jpg", ".jpeg", ".png", ".gif", ".webp", ".bmp"):
            ext = ".png"
        filename = "avatar_" + secrets.token_hex(12) + ext
        filepath = os.path.join(MEDIA_DIR, filename)
        f.save(filepath)
        avatar_url = "/media/" + filename
    else:
        body = req_json()
        avatar_url = body.get("avatar_url") or body.get("url") or ""
    if avatar_url:
        db_execute("UPDATE users SET avatar_url = ? WHERE id = ?", (avatar_url, g.current_user_id))
    if DEBUG_REQ:
        log.info("  -> avatar upload result: url=%s, has_file=%s, files_keys=%s",
                 avatar_url, f is not None, list(request.files.keys()))
    return jsonify({"avatar_url": avatar_url})

@app.route("/v1/me/cover", methods=["POST"])
def user_update_cover():
    _auth = require_auth()
    if _auth: return _auth
    f = _get_upload_file(["file", "cover", "image", "photo", "background", "upload"])
    if f and f.filename:
        ext = os.path.splitext(f.filename)[1].lower()
        if ext not in (".jpg", ".jpeg", ".png", ".gif", ".webp", ".bmp"):
            ext = ".png"
        filename = "cover_" + secrets.token_hex(12) + ext
        filepath = os.path.join(MEDIA_DIR, filename)
        f.save(filepath)
        cover_url = "/media/" + filename
    else:
        body = req_json()
        cover_url = body.get("cover_url") or body.get("url") or ""
    if cover_url:
        db_execute("UPDATE users SET cover_url = ? WHERE id = ?", (cover_url, g.current_user_id))
    if DEBUG_REQ:
        log.info("  -> cover upload result: url=%s, has_file=%s, files_keys=%s",
                 cover_url, f is not None, list(request.files.keys()))
    return jsonify({"cover_url": cover_url})

@app.route("/v1/me/presence", methods=["POST"])
def user_presence():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    online = int(body.get("online", 1))
    db_execute("UPDATE users SET online = ?, last_seen = ? WHERE id = ?", (online, now_ts(), g.current_user_id))
    return jsonify({"online": online})

@app.route("/v1/users/profile", methods=["GET"])
def user_profile():
    _auth = require_auth()
    if _auth: return _auth
    uid = (request.args.get("uid") or "").strip().upper()
    if not uid:
        return jsonify({"code": 400, "msg": "missing uid"}), 400
    row = db_query_one("SELECT * FROM users WHERE uid = ?", (uid,))
    if not row:
        return jsonify({"code": 404, "msg": "user not found"}), 404
    return jsonify(user_to_dict(row))

# =========================================================================
#  Friends / Friendships
# =========================================================================

@app.route("/v1/friends", methods=["GET"])
def friends_list():
    _auth = require_auth()
    if _auth: return _auth
    me = g.current_user_id
    rows = db_query_all(
        """SELECT u.* FROM users u WHERE u.id IN (
            SELECT CASE WHEN f.user_id = ? THEN f.friend_id ELSE f.user_id END
            FROM friendships f WHERE f.status = 'accepted' AND (f.user_id = ? OR f.friend_id = ?)
        )""",
        (me, me, me),
    )
    return jsonify({"friends": [user_to_dict(r) for r in rows]})

@app.route("/v1/friends/request", methods=["POST"])
@app.route("/v1/friends/add", methods=["POST"])
@app.route("/v1/users/add", methods=["POST"])
def friends_request():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    # 兼容多种参数命名
    to_uid = (
        body.get("to_uid") or body.get("uid") or body.get("friend_uid") or
        body.get("target_uid") or body.get("user_id") or body.get("username") or ""
    )
    to_uid = str(to_uid).strip().upper()
    if DEBUG_REQ:
        log.info("friends_request: to_uid=%s body_keys=%s", to_uid, list(body.keys())[:10])
    if not to_uid:
        return jsonify({"code": 400, "msg": "missing to_uid"}), 400
    target = db_query_one("SELECT id, uid, display_name FROM users WHERE uid = ?", (to_uid,))
    if not target:
        # fallback: search by username
        target = db_query_one(
            "SELECT id, uid, display_name FROM users WHERE username = ?", (str(body.get("username") or body.get("to_uid") or ""),)
        )
    if not target:
        return jsonify({"code": 404, "msg": "user not found"}), 404
    target_id = target["id"]
    if target_id == g.current_user_id:
        return jsonify({"code": 400, "msg": "cannot add self"}), 400
    existing = db_query_one(
        "SELECT id, status FROM friendships WHERE (user_id = ? AND friend_id = ?) OR (user_id = ? AND friend_id = ?)",
        (g.current_user_id, target_id, target_id, g.current_user_id),
    )
    if existing:
        if existing["status"] == "accepted":
            return jsonify({"request_id": str(existing["id"]), "message": "already friends"})
        return jsonify({"request_id": str(existing["id"]), "message": "request pending or already friends"})
    rid = db_execute(
        "INSERT INTO friendships (user_id, friend_id, status, initiated_by, created_at) VALUES (?, ?, 'pending', ?, ?)",
        (g.current_user_id, target_id, g.current_user_id, now_ts()),
    )
    # Add notification for target
    me_row = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (g.current_user_id,))
    notif_data = {
        "from_uid": me_row["uid"] if me_row else "",
        "from_name": (me_row["display_name"] or me_row["uid"]) if me_row else "",
        "from_avatar": me_row["avatar_url"] or "" if me_row else "",
        "request_id": str(rid),
        "type": "friend_request",
    }
    try:
        db_execute(
            "INSERT INTO notifications (user_id, type, title, body, extra_json, created_at, is_read) VALUES (?, 'friend_request', ?, ?, ?, ?, 0)",
            (target_id, "好友请求", "%s 请求加你为好友" % (me_row["display_name"] or me_row["uid"]),
             json.dumps(notif_data), now_ts()),
        )
    except Exception:
        pass
    # Push via WebSocket to target user (real-time)
    push_to_user(target_id, {
        "type": "friend_request",
        "data": {
            "id": str(rid),
            "from_uid": me_row["uid"] if me_row else "",
            "from_name": (me_row["display_name"] or me_row["uid"]) if me_row else "",
            "from_avatar": me_row["avatar_url"] or "" if me_row else "",
            "status": "pending",
            "created_at": now_ts(),
        },
    })
    if DEBUG_REQ:
        log.info("  -> friends_request sent: rid=%s, target_id=%s, pushed=%s",
                 rid, target_id, push_to_user(target_id, {"type": "ping"}) > 0)
    return jsonify({"request_id": str(rid), "message": "request sent", "to_uid": target["uid"]})

@app.route("/v1/friends/requests", methods=["GET"])
def friends_requests():
    _auth = require_auth()
    if _auth: return _auth
    rows = db_query_all(
        """SELECT f.id, f.user_id AS from_user_id, f.status, f.created_at
           FROM friendships f WHERE f.friend_id = ? ORDER BY f.created_at DESC LIMIT 100""",
        (g.current_user_id,),
    )
    results = []
    for r in rows:
        from_user = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (r["from_user_id"],))
        results.append({
            "id": str(r["id"]),
            "from_uid": from_user["uid"] if from_user else "",
            "from_name": (from_user["display_name"] or from_user["uid"]) if from_user else "",
            "from_avatar": from_user["avatar_url"] or "" if from_user else "",
            "status": r["status"],
            "created_at": r["created_at"],
        })
    return jsonify({"requests": results})

@app.route("/v1/friends/respond", methods=["POST"])
def friends_respond():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    request_id = body.get("request_id")
    accept = body.get("accept") if body.get("accept") is not None else True
    if not request_id:
        return jsonify({"code": 400, "msg": "missing request_id"}), 400
    try:
        rid_int = int(request_id)
    except Exception:
        rid_int = 0
    row = db_query_one(
        "SELECT * FROM friendships WHERE id = ? AND friend_id = ? AND status = 'pending'",
        (rid_int, g.current_user_id),
    )
    if not row:
        return jsonify({"code": 404, "msg": "request not found"}), 404
    initiator_id = row["user_id"] if row["friend_id"] == g.current_user_id else row["friend_id"]
    if accept:
        db_execute("UPDATE friendships SET status = 'accepted' WHERE id = ?", (rid_int,))
        # Notify the initiator
        me_row = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (g.current_user_id,))
        try:
            db_execute(
                "INSERT INTO notifications (user_id, type, title, body, extra_json, created_at, is_read) VALUES (?, 'friend_accepted', ?, ?, ?, ?, 0)",
                (initiator_id, "好友请求已通过",
                 "%s 接受了你的好友请求" % (me_row["display_name"] or me_row["uid"] if me_row else ""),
                 json.dumps({"from_uid": me_row["uid"] if me_row else ""}), now_ts()),
            )
        except Exception:
            pass
        push_to_user(initiator_id, {
            "type": "friend_accepted",
            "data": {
                "uid": me_row["uid"] if me_row else "",
                "name": (me_row["display_name"] or me_row["uid"]) if me_row else "",
                "avatar": me_row["avatar_url"] or "" if me_row else "",
            },
        })
    else:
        db_execute("DELETE FROM friendships WHERE id = ?", (rid_int,))
    return jsonify({"status": "accepted" if accept else "rejected"})

# =========================================================================
#  Groups
# =========================================================================

@app.route("/v1/groups/list", methods=["GET"])
def groups_list():
    _auth = require_auth()
    if _auth: return _auth
    rows = db_query_all(
        """SELECT g.* FROM groups g JOIN group_members gm ON gm.group_id = g.group_id
           WHERE gm.user_id = ? ORDER BY g.created_at DESC""",
        (g.current_user_id,),
    )
    groups = []
    for r in rows:
        mc = db_query_one("SELECT COUNT(*) AS c FROM group_members WHERE group_id = ?", (r["group_id"],))
        groups.append({
            "group_id": r["group_id"],
            "name": r["name"],
            "avatar_url": r["avatar_url"] or "",
            "owner_id": str(r["owner_id"]) if r["owner_id"] else "",
            "created_at": r["created_at"],
            "member_count": mc["c"] if mc else 0,
        })
    return jsonify({"groups": groups})

@app.route("/v1/groups/create", methods=["POST"])
def groups_create():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    name = (body.get("name") or body.get("group_name") or "").strip()
    avatar_url = body.get("avatar_url") or ""
    if not name:
        return jsonify({"code": 400, "msg": "group name required"}), 400
    group_id = gen_group_id()
    created = now_ts()
    db_execute(
        "INSERT INTO groups (group_id, name, avatar_url, owner_id, created_at) VALUES (?, ?, ?, ?, ?)",
        (group_id, name, avatar_url, g.current_user_id, created),
    )
    db_execute(
        "INSERT INTO group_members (group_id, user_id, joined_at, last_read_msg_id) VALUES (?, ?, ?, 0)",
        (group_id, g.current_user_id, created),
    )
    return jsonify({"group_id": group_id, "name": name, "avatar_url": avatar_url, "owner_id": str(g.current_user_id), "created_at": created})

@app.route("/v1/groups/join", methods=["POST"])
def groups_join():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    group_id = (body.get("group_id") or "").strip()
    if not group_id:
        return jsonify({"code": 400, "msg": "missing group_id"}), 400
    gr = db_query_one("SELECT * FROM groups WHERE group_id = ?", (group_id,))
    if not gr:
        return jsonify({"code": 404, "msg": "group not found"}), 404
    existing = db_query_one("SELECT id FROM group_members WHERE group_id = ? AND user_id = ?", (group_id, g.current_user_id))
    if existing:
        return jsonify({"group_id": group_id, "joined": True, "message": "already member"})
    # 入群前获取最新消息 ID 作为初始已读位置
    latest = db_query_one("SELECT MAX(id) as max_id FROM group_messages WHERE group_id = ?", (group_id,))
    last_read = (latest["max_id"] if latest else 0) or 0
    db_execute(
        "INSERT INTO group_members (group_id, user_id, joined_at, last_read_msg_id) VALUES (?, ?, ?, ?)",
        (group_id, g.current_user_id, now_ts(), last_read),
    )
    return jsonify({"group_id": group_id, "joined": True})

@app.route("/v1/groups/leave", methods=["POST"])
def groups_leave():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    group_id = (body.get("group_id") or "").strip()
    db_execute("DELETE FROM group_members WHERE group_id = ? AND user_id = ?", (group_id, g.current_user_id))
    return jsonify({"group_id": group_id, "left": True})

@app.route("/v1/groups/members", methods=["GET"])
def groups_members():
    _auth = require_auth()
    if _auth: return _auth
    group_id = (request.args.get("group_id") or "").strip()
    if not group_id:
        return jsonify({"code": 400, "msg": "missing group_id"}), 400
    rows = db_query_all(
        """SELECT u.* FROM users u JOIN group_members gm ON gm.user_id = u.id
           WHERE gm.group_id = ? ORDER BY gm.joined_at ASC""",
        (group_id,),
    )
    return jsonify({"members": [user_to_dict(r) for r in rows]})

@app.route("/v1/groups/settings", methods=["POST"])
def groups_settings():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    group_id = (body.get("group_id") or "").strip()
    name = body.get("name")
    avatar_url = body.get("avatar_url")
    gr = db_query_one("SELECT * FROM groups WHERE group_id = ?", (group_id,))
    if not gr:
        return jsonify({"code": 404, "msg": "group not found"}), 404
    sets, params = [], []
    if name:
        sets.append("name = ?"); params.append(name)
    if avatar_url:
        sets.append("avatar_url = ?"); params.append(avatar_url)
    if sets:
        params.append(group_id)
        db_execute("UPDATE groups SET %s WHERE group_id = ?" % ", ".join(sets), tuple(params))
    gr = db_query_one("SELECT * FROM groups WHERE group_id = ?", (group_id,))
    return jsonify({
        "group_id": gr["group_id"],
        "name": gr["name"],
        "avatar_url": gr["avatar_url"] or "",
        "owner_id": str(gr["owner_id"]) if gr["owner_id"] else "",
        "created_at": gr["created_at"],
    })

# =========================================================================
#  Direct Messages
# =========================================================================

@app.route("/v1/direct/messages/v2", methods=["GET"])
def direct_messages():
    _auth = require_auth()
    if _auth: return _auth
    with_uid = (request.args.get("with_uid") or "").strip().upper()
    limit = min(int(request.args.get("limit", 50)), 200)
    offset = int(request.args.get("offset", 0))
    if not with_uid:
        return jsonify({"code": 400, "msg": "missing with_uid"}), 400
    target = db_query_one("SELECT id, uid FROM users WHERE uid = ?", (with_uid,))
    if not target:
        return jsonify({"code": 404, "msg": "user not found"}), 404
    me = g.current_user_id
    target_id = target["id"]
    rows = db_query_all(
        """SELECT * FROM (
            SELECT * FROM direct_messages
            WHERE (from_id = ? AND to_id = ?) OR (from_id = ? AND to_id = ?)
            ORDER BY created_at DESC, id DESC LIMIT ? OFFSET ?
        ) ORDER BY created_at ASC, id ASC""",
        (me, target_id, target_id, me, limit, offset),
    )
    messages = []
    for r in rows:
        from_user = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (r["from_id"],))
        to_user = db_query_one("SELECT uid FROM users WHERE id = ?", (r["to_id"],))
        # thread_id 是对方的 UID（当前对话的另一方）
        other_uid = ""
        if r["from_id"] == g.current_user_id:
            other_uid = to_user["uid"] if to_user else ""
        else:
            other_uid = from_user["uid"] if from_user else ""
        messages.append({
            "id": str(r["id"]),
            "thread_id": other_uid,
            "from_uid": from_user["uid"] if from_user else "",
            "to_uid": to_user["uid"] if to_user else "",
            "body": r["body"] or "",
            "msg_type": r["msg_type"] or "text",
            "media_url": r["media_url"] or "",
            "thumb_url": r["thumb_url"] or "",
            "duration_ms": r["burn_after_seconds"] or 0,
            "burn_after_seconds": r["burn_after_seconds"] or 0,
            "created_at": r["created_at"],
            "is_read": 1 if r["is_read"] else 0,
            "status": 3 if r["is_read"] else 1,
            "from_name": (from_user["display_name"] or from_user["uid"]) if from_user else "",
            "from_avatar": from_user["avatar_url"] or "" if from_user else "",
        })
    return jsonify({"messages": messages})

@app.route("/v1/direct/send", methods=["POST"])
def direct_send():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    to_uid = (body.get("to_uid") or "").strip().upper()
    msg_body = body.get("body", "") or ""
    msg_type = body.get("msg_type", "text") or "text"
    burn = int(body.get("burn_after_seconds", 0) or 0)
    media_url = body.get("media_url") or ""
    thumb_url = body.get("thumb_url") or ""
    if not to_uid:
        return jsonify({"code": 400, "msg": "missing to_uid"}), 400
    target = db_query_one("SELECT id, uid FROM users WHERE uid = ?", (to_uid,))
    if not target:
        return jsonify({"code": 404, "msg": "user not found"}), 404
    target_id = target["id"]
    created = now_ts()
    msg_id = db_execute(
        "INSERT INTO direct_messages (from_id, to_id, body, msg_type, media_url, thumb_url, burn_after_seconds, created_at, is_read) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0)",
        (g.current_user_id, target_id, msg_body, msg_type, media_url, thumb_url, burn, created),
    )
    from_user = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (g.current_user_id,))
    msg_dict = {
        "id": str(msg_id),
        "thread_id": to_uid,
        "from_uid": from_user["uid"] if from_user else "",
        "to_uid": to_uid,
        "body": msg_body,
        "msg_type": msg_type,
        "media_url": media_url,
        "thumb_url": thumb_url,
        "duration_ms": burn,
        "burn_after_seconds": burn,
        "created_at": created,
        "is_read": 0,
        "status": 1,
        "from_name": (from_user["display_name"] or from_user["uid"]) if from_user else "",
        "from_avatar": from_user["avatar_url"] or "" if from_user else "",
    }
    # Push via WebSocket to target user
    sent = push_to_user(target_id, {"type": "direct_message", "data": msg_dict})
    if sent == 0:
        # Not online: add a notification
        db_execute(
            "INSERT INTO notifications (user_id, type, title, body, extra_json, created_at, is_read) VALUES (?, 'message', ?, ?, ?, ?, 0)",
            (target_id, "新消息", msg_body, json.dumps({"from_uid": from_user["uid"] if from_user else ""}), created),
        )
    # 注意：返回格式必须是消息对象本身（不能外层包 message），客户端 l(String str) 直接解析响应 JSON
    return jsonify(msg_dict)

@app.route("/v1/direct/read", methods=["POST"])
def direct_read():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    with_uid = (body.get("with_uid") or "").strip().upper()
    if not with_uid:
        return jsonify({"code": 400, "msg": "missing with_uid"}), 400
    target = db_query_one("SELECT id, uid FROM users WHERE uid = ?", (with_uid,))
    if not target:
        return jsonify({"code": 404, "msg": "user not found"}), 404
    db_execute(
        "UPDATE direct_messages SET is_read = 1 WHERE from_id = ? AND to_id = ? AND is_read = 0",
        (target["id"], g.current_user_id),
    )
    push_to_user(target["id"], {
        "type": "direct_read",
        "data": {
            "thread_id": with_uid,
            "reader_uid": db_query_one("SELECT uid FROM users WHERE id = ?", (g.current_user_id,))["uid"],
            "read_at": now_ts(),
        },
    })
    return jsonify({"status": "ok"})

@app.route("/v1/direct/unread", methods=["GET", "POST"])
def direct_unread():
    _auth = require_auth()
    if _auth: return _auth
    limit = min(int(request.args.get("limit", 50) or req_json().get("limit", 50)), 200)
    me = g.current_user_id
    rows = db_query_all(
        """SELECT * FROM direct_messages WHERE to_id = ? AND is_read = 0
           ORDER BY created_at DESC LIMIT ?""",
        (me, limit),
    )
    messages = []
    for r in rows:
        from_user = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (r["from_id"],))
        messages.append({
            "id": str(r["id"]),
            "thread_id": from_user["uid"] if from_user else "",
            "from_uid": from_user["uid"] if from_user else "",
            "body": r["body"] or "",
            "msg_type": r["msg_type"] or "text",
            "media_url": r["media_url"] or "",
            "thumb_url": r["thumb_url"] or "",
            "duration_ms": r["burn_after_seconds"] or 0,
            "created_at": r["created_at"],
            "is_read": 0,
            "from_name": (from_user["display_name"] or from_user["uid"]) if from_user else "",
            "from_avatar": from_user["avatar_url"] or "" if from_user else "",
        })
    return jsonify({"messages": messages})

# =========================================================================
#  Group Messages
# =========================================================================

@app.route("/v1/groups/messages/v2", methods=["GET"])
def group_messages():
    _auth = require_auth()
    if _auth: return _auth
    group_id = (request.args.get("group_id") or "").strip()
    limit = min(int(request.args.get("limit", 50)), 200)
    offset = int(request.args.get("offset", 0))
    if not group_id:
        return jsonify({"code": 400, "msg": "missing group_id"}), 400
    member = db_query_one("SELECT id FROM group_members WHERE group_id = ? AND user_id = ?",
                          (group_id, g.current_user_id))
    if not member:
        return jsonify({"code": 403, "msg": "not a member"}), 403
    rows = db_query_all(
        """SELECT * FROM (SELECT * FROM group_messages WHERE group_id = ? ORDER BY created_at DESC, id DESC LIMIT ? OFFSET ?)
           ORDER BY created_at ASC, id ASC""",
        (group_id, limit, offset),
    )
    messages = []
    for r in rows:
        from_user = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (r["from_id"],)) if r["from_id"] else None
        is_system = r["from_id"] == 0
        messages.append({
            "id": str(r["id"]),
            "group_id": r["group_id"],
            "thread_id": r["group_id"],
            "from_uid": from_user["uid"] if from_user else ("system" if is_system else ""),
            "body": r["body"] or "",
            "msg_type": r["msg_type"] or "text",
            "media_url": r["media_url"] or "",
            "thumb_url": r["thumb_url"] or "",
            "duration_ms": r["burn_after_seconds"] or 0,
            "created_at": r["created_at"],
            "status": 1,
            "from_name": (from_user["display_name"] or from_user["uid"]) if from_user else ("系统消息" if is_system else ""),
            "from_avatar": from_user["avatar_url"] or "" if from_user else "",
        })
    return jsonify({"messages": messages})

@app.route("/v1/groups/message/send", methods=["POST"])
def group_send():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    group_id = (body.get("group_id") or "").strip()
    msg_body = body.get("body", "") or ""
    msg_type = body.get("msg_type", "text") or "text"
    burn = int(body.get("burn_after_seconds", 0) or 0)
    media_url = body.get("media_url") or ""
    thumb_url = body.get("thumb_url") or ""
    if not group_id:
        return jsonify({"code": 400, "msg": "missing group_id"}), 400
    member = db_query_one("SELECT id FROM group_members WHERE group_id = ? AND user_id = ?",
                          (group_id, g.current_user_id))
    if not member:
        return jsonify({"code": 403, "msg": "not a member"}), 403
    created = now_ts()
    msg_id = db_execute(
        "INSERT INTO group_messages (group_id, from_id, body, msg_type, media_url, thumb_url, burn_after_seconds, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
        (group_id, g.current_user_id, msg_body, msg_type, media_url, thumb_url, burn, created),
    )
    from_user = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (g.current_user_id,))
    msg_dict = {
        "id": str(msg_id),
        "group_id": group_id,
        "thread_id": group_id,
        "from_uid": from_user["uid"] if from_user else "",
        "body": msg_body,
        "msg_type": msg_type,
        "media_url": media_url,
        "thumb_url": thumb_url,
        "duration_ms": burn,
        "burn_after_seconds": burn,
        "created_at": created,
        "status": 1,
        "from_name": (from_user["display_name"] or from_user["uid"]) if from_user else "",
        "from_avatar": from_user["avatar_url"] or "" if from_user else "",
    }
    # Push to all other group members online
    members = db_query_all(
        "SELECT user_id FROM group_members WHERE group_id = ? AND user_id != ?",
        (group_id, g.current_user_id),
    )
    for m in members:
        push_to_user(m["user_id"], {"type": "group_message", "data": msg_dict})
    # 注意：返回格式必须是消息对象本身（不能外层包 message），客户端 l(String str) 直接解析响应 JSON
    return jsonify(msg_dict)

@app.route("/v1/groups/read", methods=["POST"])
def group_read():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    group_id = (body.get("group_id") or "").strip()
    if not group_id:
        return jsonify({"code": 400, "msg": "missing group_id"}), 400
    # 获取该群最新的消息 ID
    latest = db_query_one(
        "SELECT MAX(id) as max_id FROM group_messages WHERE group_id = ?",
        (group_id,),
    )
    max_id = (latest["max_id"] if latest else 0) or 0
    db_execute(
        "UPDATE group_members SET last_read_msg_id = ? WHERE group_id = ? AND user_id = ?",
        (max_id, group_id, g.current_user_id),
    )
    return jsonify({"status": "ok"})

@app.route("/v1/groups/unread", methods=["GET", "POST"])
def group_unread():
    _auth = require_auth()
    if _auth: return _auth
    limit = min(int(request.args.get("limit", 50) or req_json().get("limit", 50)), 200)
    me = g.current_user_id
    # 找出所有群中，消息 ID > 用户已读到的最后一条消息 ID 的消息（排除自己发的）
    rows = db_query_all(
        """SELECT gm.* FROM group_messages gm
           JOIN group_members gmm ON gmm.group_id = gm.group_id
           WHERE gmm.user_id = ? AND gm.from_id != ?
             AND gm.id > COALESCE(gmm.last_read_msg_id, 0)
           ORDER BY gm.created_at DESC, gm.id DESC LIMIT ?""",
        (me, me, limit),
    )
    messages = []
    for r in rows:
        from_user = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (r["from_id"],)) if r["from_id"] else None
        is_system = r["from_id"] == 0
        messages.append({
            "id": str(r["id"]),
            "group_id": r["group_id"],
            "thread_id": r["group_id"],
            "from_uid": from_user["uid"] if from_user else ("system" if is_system else ""),
            "body": r["body"] or "",
            "msg_type": r["msg_type"] or "text",
            "media_url": r["media_url"] or "",
            "thumb_url": r["thumb_url"] or "",
            "duration_ms": r["burn_after_seconds"] or 0,
            "created_at": r["created_at"],
            "status": 1,
            "from_name": (from_user["display_name"] or from_user["uid"]) if from_user else ("系统消息" if is_system else ""),
            "from_avatar": from_user["avatar_url"] or "" if from_user else "",
        })
    return jsonify({"messages": messages})

# =========================================================================
#  Typing Indicator
# =========================================================================

@app.route("/v1/groups/typing", methods=["POST"])
def groups_typing():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    group_id = (body.get("group_id") or "").strip()
    is_typing = bool(body.get("is_typing", False))
    if not group_id:
        return jsonify({"code": 400, "msg": "missing group_id"}), 400
    member = db_query_one("SELECT id FROM group_members WHERE group_id = ? AND user_id = ?",
                          (group_id, g.current_user_id))
    if not member:
        return jsonify({"code": 403, "msg": "not a member"}), 403
    # Save state
    db_execute(
        "INSERT OR REPLACE INTO typing_states (chat_id, user_id, is_group, updated_at) VALUES (?, ?, 1, ?)",
        (group_id, g.current_user_id, now_ts()),
    )
    # Push typing state to other group members
    me_uid = db_query_one("SELECT uid FROM users WHERE id = ?", (g.current_user_id,))["uid"]
    members = db_query_all(
        "SELECT user_id FROM group_members WHERE group_id = ? AND user_id != ?",
        (group_id, g.current_user_id),
    )
    for m in members:
        push_to_user(m["user_id"], {
            "type": "typing",
            "data": {
                "chat_id": group_id,
                "uid": me_uid,
                "is_group": True,
                "is_typing": is_typing,
            },
        })
    return jsonify({"status": "ok"})

@app.route("/v1/groups/<group_id>/typing", methods=["GET"])
def groups_typing_get(group_id):
    _auth = require_auth()
    if _auth: return _auth
    rows = db_query_all(
        "SELECT user_id FROM typing_states WHERE chat_id = ? AND is_group = 1 AND updated_at > ?",
        (group_id, now_ts() - 10),
    )
    users = []
    for r in rows:
        u = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (r["user_id"],))
        if u:
            users.append({
                "uid": u["uid"],
                "display_name": u["display_name"] or u["uid"],
                "avatar_url": u["avatar_url"] or "",
                "is_typing": True,
            })
    return jsonify({"users": users})

@app.route("/v1/chats/<uid>/typing", methods=["POST"])
def chats_typing_post(uid):
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    is_typing = bool(body.get("is_typing", False))
    target = db_query_one("SELECT id, uid FROM users WHERE uid = ?", (uid.upper(),))
    if not target:
        return jsonify({"code": 404, "msg": "user not found"}), 404
    chat_id = "%s-%s" % tuple(sorted([str(g.current_user_id), str(target["id"])]))
    db_execute(
        "INSERT OR REPLACE INTO typing_states (chat_id, user_id, is_group, updated_at) VALUES (?, ?, 0, ?)",
        (chat_id, g.current_user_id, now_ts()),
    )
    me_uid = db_query_one("SELECT uid FROM users WHERE id = ?", (g.current_user_id,))["uid"]
    push_to_user(target["id"], {
        "type": "typing",
        "data": {
            "chat_id": uid,
            "uid": me_uid,
            "is_group": False,
            "is_typing": is_typing,
        },
    })
    return jsonify({"status": "ok"})

@app.route("/v1/chats/<uid>/typing", methods=["GET"])
def chats_typing_get(uid):
    _auth = require_auth()
    if _auth: return _auth
    target = db_query_one("SELECT id, uid FROM users WHERE uid = ?", (uid.upper(),))
    if not target:
        return jsonify({"users": []})
    chat_id = "%s-%s" % tuple(sorted([str(g.current_user_id), str(target["id"])]))
    rows = db_query_all(
        "SELECT user_id FROM typing_states WHERE chat_id = ? AND is_group = 0 AND updated_at > ?",
        (chat_id, now_ts() - 10),
    )
    users = []
    for r in rows:
        if r["user_id"] == g.current_user_id:
            continue
        u = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (r["user_id"],))
        if u:
            users.append({
                "uid": u["uid"],
                "display_name": u["display_name"] or u["uid"],
                "avatar_url": u["avatar_url"] or "",
                "is_typing": True,
            })
    return jsonify({"users": users})

# =========================================================================
#  Notifications
# =========================================================================

@app.route("/v1/notifications", methods=["GET"])
def notifications_get():
    _auth = require_auth()
    if _auth: return _auth
    limit = max(1, min(int(request.args.get("limit", 20)), 200))
    # 先返回系统公告（从 settings.json），再返回用户个人通知
    notifs = []
    ann = SETTINGS.get("announcement", {}) or {}
    if ann.get("enabled"):
        server_info = SETTINGS.get("server", {}) or {}
        notifs.append({
            "id": "sys-announcement",
            "type": "announcement",
            "title": ann.get("title") or server_info.get("name") or "服务器公告",
            "body": ann.get("body") or "",
            "server_name": server_info.get("name"),
            "server_url": server_info.get("url"),
            "version": server_info.get("version"),
            "created_at": int(os.path.getmtime(SETTINGS_PATH)) if os.path.exists(SETTINGS_PATH) else now_ts(),
            "is_read": 1,
        })
    # 用户个人通知
    rows = db_query_all(
        "SELECT * FROM notifications WHERE user_id = ? ORDER BY created_at DESC LIMIT ?",
        (g.current_user_id, limit),
    )
    for r in rows:
        n = {
            "id": str(r["id"]),
            "type": r["type"] or "system",
            "title": r["title"] or "",
            "body": r["body"] or "",
            "created_at": r["created_at"],
            "is_read": 1 if r["is_read"] else 0,
        }
        if r["extra_json"]:
            try:
                extra = json.loads(r["extra_json"])
                n.update(extra)
            except Exception:
                pass
        notifs.append(n)
    return jsonify({"notifications": notifs, "total": len(notifs)})

@app.route("/v1/notifications/read", methods=["POST"])
def notifications_read():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    nid = body.get("id")
    if nid:
        try:
            nid_int = int(nid)
            db_execute("UPDATE notifications SET is_read = 1 WHERE id = ? AND user_id = ?", (nid_int, g.current_user_id))
        except Exception:
            pass
    else:
        db_execute("UPDATE notifications SET is_read = 1 WHERE user_id = ?", (g.current_user_id,))
    return jsonify({"status": "ok"})

@app.route("/v1/notifications", methods=["POST"])
def notifications_post():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    title = body.get("title") or ""
    text = body.get("body") or body.get("text") or ""
    ntype = body.get("type") or "system"
    db_execute(
        "INSERT INTO notifications (user_id, type, title, body, extra_json, created_at, is_read) VALUES (?, ?, ?, ?, ?, ?, 0)",
        (g.current_user_id, ntype, title, text, json.dumps({}), now_ts()),
    )
    return jsonify({"status": "ok"})

# =========================================================================
#  Moments (朋友圈 / 动态)
# =========================================================================

@app.route("/v1/moments/user", methods=["GET"])
def moments_user():
    _auth = require_auth()
    if _auth: return _auth
    uid = (request.args.get("uid") or "").strip().upper()
    limit = min(int(request.args.get("limit", 20)), 200)
    user_id = None
    if uid:
        u = db_query_one("SELECT id FROM users WHERE uid = ?", (uid,))
        if u:
            user_id = u["id"]
    if not user_id:
        user_id = g.current_user_id
    rows = db_query_all(
        "SELECT * FROM moments WHERE user_id = ? ORDER BY created_at DESC LIMIT ?",
        (user_id, limit),
    )
    moments = []
    for r in rows:
        author = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (r["user_id"],))
        likes_count = db_query_one("SELECT COUNT(*) AS c FROM moment_likes WHERE moment_id = ?", (r["moment_id"],))
        comments_count = db_query_one("SELECT COUNT(*) AS c FROM moment_comments WHERE moment_id = ?", (r["moment_id"],))
        moments.append({
            "moment_id": r["moment_id"],
            "uid": author["uid"] if author else "",
            "author_name": (author["display_name"] or author["uid"]) if author else "",
            "author_avatar": author["avatar_url"] or "" if author else "",
            "body": r["body"] or "",
            "media_url": r["media_url"] or "",
            "image_url": r["media_url"] or "",
            "thumb_url": r["thumb_url"] or "",
            "created_at": r["created_at"],
            "likes": likes_count["c"] if likes_count else 0,
            "comments": comments_count["c"] if comments_count else 0,
        })
    return jsonify({"moments": moments})

@app.route("/v1/moments", methods=["POST"])
def moments_create():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    text = body.get("body") or body.get("text") or ""
    media_url = body.get("media_url") or body.get("image_url") or ""
    thumb_url = body.get("thumb_url") or media_url or ""
    if not text and not media_url:
        return jsonify({"code": 400, "msg": "empty moment"}), 400
    moment_id = gen_moment_id()
    created = now_ts()
    db_execute(
        "INSERT INTO moments (moment_id, user_id, body, media_url, thumb_url, created_at, likes) VALUES (?, ?, ?, ?, ?, ?, 0)",
        (moment_id, g.current_user_id, text, media_url, thumb_url, created),
    )
    author = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (g.current_user_id,))
    return {
        "moment_id": moment_id,
        "uid": author["uid"] if author else "",
        "author_name": (author["display_name"] or author["uid"]) if author else "",
        "author_avatar": author["avatar_url"] or "" if author else "",
        "body": text,
        "media_url": media_url,
        "image_url": media_url,
        "thumb_url": thumb_url,
        "created_at": created,
        "likes": 0,
        "comments": 0,
    }

@app.route("/v1/moments/timeline", methods=["GET"])
def moments_timeline():
    _auth = require_auth()
    if _auth: return _auth
    limit = min(int(request.args.get("limit", 50)), 200)
    me = g.current_user_id
    # My moments + friends' moments
    rows = db_query_all(
        """SELECT m.* FROM moments m WHERE m.user_id = ? OR m.user_id IN (
            SELECT CASE WHEN f.user_id = ? THEN f.friend_id ELSE f.user_id END
            FROM friendships f WHERE f.status = 'accepted' AND (f.user_id = ? OR f.friend_id = ?)
        ) ORDER BY m.created_at DESC LIMIT ?""",
        (me, me, me, me, limit),
    )
    moments = []
    for r in rows:
        author = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (r["user_id"],))
        lc = db_query_one("SELECT COUNT(*) AS c FROM moment_likes WHERE moment_id = ?", (r["moment_id"],))
        cc = db_query_one("SELECT COUNT(*) AS c FROM moment_comments WHERE moment_id = ?", (r["moment_id"],))
        moments.append({
            "moment_id": r["moment_id"],
            "uid": author["uid"] if author else "",
            "author_name": (author["display_name"] or author["uid"]) if author else "",
            "author_avatar": author["avatar_url"] or "" if author else "",
            "body": r["body"] or "",
            "media_url": r["media_url"] or "",
            "image_url": r["media_url"] or "",
            "thumb_url": r["thumb_url"] or "",
            "created_at": r["created_at"],
            "likes": lc["c"] if lc else 0,
            "comments": cc["c"] if cc else 0,
        })
    return jsonify({"moments": moments})

@app.route("/v1/moments/like", methods=["POST"])
def moments_like():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    moment_id = (body.get("moment_id") or body.get("id") or "").strip()
    if DEBUG_REQ:
        log.info("  -> moments_like: moment_id='%s', body_keys=%s", moment_id, list(body.keys()))
    if not moment_id:
        return jsonify({"code": 400, "msg": "missing moment_id"}), 400
    m = db_query_one("SELECT moment_id FROM moments WHERE moment_id = ?", (moment_id,))
    if not m:
        return jsonify({"code": 404, "msg": "moment not found"}), 404
    try:
        db_execute("INSERT INTO moment_likes (moment_id, user_id, created_at) VALUES (?, ?, ?)",
                   (moment_id, g.current_user_id, now_ts()))
    except Exception:
        pass
    db_execute("UPDATE moments SET likes = (SELECT COUNT(*) FROM moment_likes WHERE moment_id = ?) WHERE moment_id = ?",
               (moment_id, moment_id))
    return jsonify({"status": "ok"})

@app.route("/v1/moments/unlike", methods=["POST"])
def moments_unlike():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    moment_id = (body.get("moment_id") or "").strip()
    if not moment_id:
        return jsonify({"code": 400, "msg": "missing moment_id"}), 400
    db_execute("DELETE FROM moment_likes WHERE moment_id = ? AND user_id = ?", (moment_id, g.current_user_id))
    db_execute("UPDATE moments SET likes = (SELECT COUNT(*) FROM moment_likes WHERE moment_id = ?) WHERE moment_id = ?",
               (moment_id, moment_id))
    return jsonify({"status": "ok"})

@app.route("/v1/moments/comments", methods=["GET"])
def moments_comments():
    _auth = require_auth()
    if _auth: return _auth
    moment_id = request.args.get("moment_id", "").strip()
    limit = min(int(request.args.get("limit", 50)), 200)
    if not moment_id:
        return jsonify({"code": 400, "msg": "missing moment_id"}), 400
    rows = db_query_all(
        "SELECT * FROM moment_comments WHERE moment_id = ? ORDER BY created_at ASC LIMIT ?",
        (moment_id, limit),
    )
    comments = []
    for r in rows:
        u = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (r["user_id"],))
        comments.append({
            "id": str(r["id"]),
            "moment_id": r["moment_id"],
            "uid": u["uid"] if u else "",
            "name": (u["display_name"] or u["uid"]) if u else "",
            "avatar": u["avatar_url"] or "" if u else "",
            "body": r["body"] or "",
            "created_at": r["created_at"],
        })
    return jsonify({"comments": comments})

@app.route("/v1/moments/comment", methods=["POST"])
def moments_comment():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    moment_id = (body.get("moment_id") or "").strip()
    text = body.get("body") or body.get("text") or ""
    if not moment_id or not text:
        return jsonify({"code": 400, "msg": "missing moment_id or body"}), 400
    cid = db_execute("INSERT INTO moment_comments (moment_id, user_id, body, created_at) VALUES (?, ?, ?, ?)",
                     (moment_id, g.current_user_id, text, now_ts()))
    return jsonify({"status": "ok", "comment_id": str(cid)})

@app.route("/v1/moments/comment/delete", methods=["POST"])
def moments_comment_delete():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    cid = body.get("comment_id") or body.get("id")
    try:
        cid_int = int(cid)
    except Exception:
        return jsonify({"code": 400, "msg": "invalid comment_id"}), 400
    db_execute("DELETE FROM moment_comments WHERE id = ? AND user_id = ?", (cid_int, g.current_user_id))
    return jsonify({"status": "ok"})

@app.route("/v1/moments/delete", methods=["POST"])
def moments_delete():
    _auth = require_auth()
    if _auth: return _auth
    body = req_json()
    moment_id = (body.get("moment_id") or "").strip()
    if not moment_id:
        return jsonify({"code": 400, "msg": "missing moment_id"}), 400
    db_execute("DELETE FROM moments WHERE moment_id = ? AND user_id = ?", (moment_id, g.current_user_id))
    db_execute("DELETE FROM moment_likes WHERE moment_id = ?", (moment_id,))
    db_execute("DELETE FROM moment_comments WHERE moment_id = ?", (moment_id,))
    return jsonify({"status": "ok"})

# =========================================================================
#  Media Upload & Static
# =========================================================================

@app.route("/v1/media", methods=["POST"])
def upload_media():
    _auth = require_auth()
    if _auth: return _auth
    if "file" not in request.files:
        return jsonify({"code": 400, "msg": "no file"}), 400
    f = request.files["file"]
    if not f or f.filename == "":
        return jsonify({"code": 400, "msg": "empty file"}), 400
    ext = os.path.splitext(f.filename)[1].lower()
    filename = secrets.token_hex(16) + ext
    filepath = os.path.join(MEDIA_DIR, filename)
    f.save(filepath)
    url = "/media/" + filename
    return jsonify({"url": url, "media_url": url, "thumb_url": url, "filename": filename})

@app.route("/v1/files/upload", methods=["POST"])
def upload_file():
    return upload_media()

@app.route("/v1/files/download", methods=["GET"])
def download_file():
    filename = request.args.get("name", "")
    if not filename or ".." in filename or "/" in filename:
        return jsonify({"code": 400, "msg": "invalid filename"}), 400
    # Search in media and uploads directories
    for d in [MEDIA_DIR, UPLOAD_DIR]:
        fp = os.path.join(d, filename)
        if os.path.exists(fp):
            return send_from_directory(d, filename, as_attachment=True)
    return jsonify({"code": 404, "msg": "not found"}), 404

@app.route("/media/<path:filename>", methods=["GET"])
def serve_media(filename):
    return send_from_directory(MEDIA_DIR, filename)

# =========================================================================
#  Update check
# =========================================================================

@app.route("/update/update.json", methods=["GET"])
def update_check():
    return jsonify({
        "app": APP_NAME,
        "version": APP_VERSION,
        "version_code": 1,
        "min_version": "1.0.0",
        "min_version_code": 1,
        "title": "旧聊 OldChat",
        "body": "这是一个兼容旧聊协议的服务器实现",
        "download_url": "",
        "force_update": False,
    })

# =========================================================================
#  Root + Echo
# =========================================================================

@app.route("/", methods=["GET"])
def root():
    return jsonify({
        "service": APP_NAME,
        "version": APP_VERSION,
        "status": "ok",
        "endpoints": {
            "auth": ["/v1/auth/handshake", "/v1/auth/login", "/v1/auth/register", "/v1/auth/refresh"],
            "user": ["/v1/me", "/v1/me/profile", "/v1/users/profile"],
            "friends": ["/v1/friends", "/v1/friends/request", "/v1/friends/requests", "/v1/friends/respond"],
            "groups": ["/v1/groups/list", "/v1/groups/create", "/v1/groups/join", "/v1/groups/leave",
                       "/v1/groups/members", "/v1/groups/typing"],
            "direct": ["/v1/direct/messages/v2", "/v1/direct/send", "/v1/direct/read", "/v1/direct/unread"],
            "group_messages": ["/v1/groups/messages/v2", "/v1/groups/message/send", "/v1/groups/read", "/v1/groups/unread"],
            "notifications": ["/v1/notifications"],
            "moments": ["/v1/moments", "/v1/moments/user", "/v1/moments/timeline",
                        "/v1/moments/like", "/v1/moments/unlike", "/v1/moments/comments"],
            "media": ["/v1/media", "/v1/files/upload", "/v1/files/download", "/media/<filename>"],
            "ws": "/v1/ws?token=<access_token>&sid=<session_id>",
        },
    })

# =========================================================================
#  WebSocket handler (/v1/ws)
# =========================================================================

if HAS_EVENTLET:
    @WebSocketWSGI
    def ws_handler(ws):
        user_id = None
        token_from_q = None
        sid_from_q = None
        env = getattr(ws, "environ", {})
        query = env.get("QUERY_STRING", "") if isinstance(env, dict) else ""
        for part in query.split("&"):
            if part.startswith("token=") and not token_from_q:
                token_from_q = part[6:]
            elif part.startswith("sid=") and not sid_from_q:
                sid_from_q = part[4:]

        # First try query-based auth
        if token_from_q:
            row = db_query_one("SELECT user_id FROM tokens WHERE access_token = ? AND expires_at > ?",
                               (token_from_q, now_ts()))
            if row:
                user_id = row["user_id"]
        # Fallback: wait for first message as auth
        if not user_id:
            try:
                first = ws.wait()
                if isinstance(first, str) and first.strip():
                    try:
                        data = json.loads(first)
                        token = data.get("token") or data.get("access_token")
                        if token:
                            row = db_query_one(
                                "SELECT user_id FROM tokens WHERE access_token = ? AND expires_at > ?",
                                (token, now_ts()),
                            )
                            if row:
                                user_id = row["user_id"]
                    except Exception:
                        pass
            except Exception:
                pass

        if not user_id:
            try:
                ws.send(json.dumps({"type": "error", "message": "authentication required"}))
            except Exception:
                pass
            return

        # Register connection
        register_ws(user_id, ws)
        try:
            me = db_query_one("SELECT uid, display_name FROM users WHERE id = ?", (user_id,))
            ws.send(json.dumps({
                "type": "connected",
                "uid": me["uid"] if me else "",
                "display_name": me["display_name"] if me else "",
                "online": 1,
            }, ensure_ascii=False))
        except Exception:
            pass

        # Heartbeat & presence broadcast
        try:
            # Notify friends this user is online
            friends = db_query_all(
                """SELECT u.id FROM users u JOIN friendships f ON
                   (f.user_id = u.id AND f.friend_id = ? AND f.status = 'accepted') OR
                   (f.friend_id = u.id AND f.user_id = ? AND f.status = 'accepted')
                   WHERE u.id != ?""",
                (user_id, user_id, user_id),
            )
            me2 = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (user_id,))
            for f in friends:
                push_to_user(f["id"], {
                    "type": "presence",
                    "data": {
                        "uid": me2["uid"] if me2 else "",
                        "display_name": (me2["display_name"] or me2["uid"]) if me2 else "",
                        "avatar_url": me2["avatar_url"] or "" if me2 else "",
                        "online": 1,
                    },
                })

            # Read loop
            while True:
                msg = ws.wait()
                if msg is None:
                    break
                if not isinstance(msg, str):
                    continue
                try:
                    data = json.loads(msg)
                except Exception:
                    continue
                mtype = data.get("type")
                if mtype == "ping":
                    try:
                        ws.send(json.dumps({"type": "pong", "ts": now_ts()}))
                    except Exception:
                        break
                elif mtype == "keepalive":
                    pass
                elif mtype == "echo":
                    try:
                        ws.send(msg)
                    except Exception:
                        break
        except Exception:
            pass
        finally:
            unregister_ws(user_id, ws)
            # Notify friends offline
            me3 = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (user_id,))
            for f in friends:
                push_to_user(f["id"], {
                    "type": "presence",
                    "data": {
                        "uid": me3["uid"] if me3 else "",
                        "display_name": (me3["display_name"] or me3["uid"]) if me3 else "",
                        "avatar_url": me3["avatar_url"] or "" if me3 else "",
                        "online": 0,
                    },
                })

    # WSGI dispatch
    def dispatch(environ, start_response):
        path = environ.get("PATH_INFO", "")
        if path == "/v1/ws":
            return ws_handler(environ, start_response)
        return app(environ, start_response)
else:
    def dispatch(environ, start_response):
        path = environ.get("PATH_INFO", "")
        if path == "/v1/ws":
            start_response("501 Not Implemented", [("Content-Type", "application/json")])
            return [b'{"code":501,"msg":"websocket unavailable: install eventlet"}']
        return app(environ, start_response)

# =========================================================================
#  Main entry
# =========================================================================

_bootstrap_done = False

def bootstrap():
    global _bootstrap_done
    if _bootstrap_done:
        return
    init_db()
    _bootstrap_done = True

if __name__ == "__main__":
    init_db()
    log.info("DB: %s", DB_PATH)
    log.info("Media dir: %s", MEDIA_DIR)
    log.info("WebSocket: enabled (eventlet=%s)", HAS_EVENTLET)
    srv = SETTINGS.get("server", {}) or {}
    host = os.environ.get("HOST", srv.get("host", "0.0.0.0"))
    port = int(os.environ.get("PORT", srv.get("port", 8080)))
    print()
    print("=" * 60)
    print(" OldChat 兼容服务器 已启动: http://%s:%d" % (host, port))
    print(" WebSocket 路径: ws://%s:%d/v1/ws?token=<access_token>&sid=<session_id>" % (host, port))
    if IS_WINDOWS:
        print(" [Windows] eventlet 模式=%s（如发现请求不响应，可在 settings.json 打开 debug 看日志）" % HAS_EVENTLET)
    if DEBUG_REQ:
        print(" [调试] debug=true 已启用，每个请求会打印方法、路径、请求头等")
    print(" 配置文件: %s" % SETTINGS_PATH)
    print("=" * 60)
    print()
    if HAS_EVENTLET:
        try:
            eventlet.wsgi.server(eventlet.listen((host, port)), dispatch, log_output=False)
        except Exception as _e:
            log.error("eventlet 启动失败，退回 threading 模式: %s", _e)
            app.run(host=host, port=port, debug=False, threaded=True)
    else:
        app.run(host=host, port=port, debug=False, threaded=True)
else:
    init_db()