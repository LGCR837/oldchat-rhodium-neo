"""
OldChat Compatible Server
基于 Python + Flask + SQLite3 的 OldChat 兼容服务器
"""

import os
import re
import json
import time
import secrets
import hashlib
import sqlite3
import threading
from datetime import datetime, timedelta
from functools import wraps

from flask import Flask, request, jsonify, g, send_from_directory
from werkzeug.security import generate_password_hash, check_password_hash

# ============ ECDH 加密相关 / ECDH Encryption ============
try:
    from cryptography.hazmat.primitives.asymmetric import ec
    from cryptography.hazmat.primitives import serialization, hashes
    from cryptography.hazmat.primitives.ciphers.aead import AESGCM
    from cryptography.hazmat.backends import default_backend
    HAS_CRYPTO = True
except ImportError:
    HAS_CRYPTO = False

# ============ Flask App 配置 / Flask App Config ============
app = Flask(__name__, static_folder='static')
app.config['SECRET_KEY'] = os.environ.get('SECRET_KEY', secrets.token_hex(32))
app.config['DATABASE'] = os.environ.get('DATABASE', 'oldchat.db')
app.config['TOKEN_EXPIRY'] = 7 * 24 * 60 * 60  # 7 days
app.config['MAX_CONTENT_LENGTH'] = 16 * 1024 * 1024  # 16MB max upload

# ============ 数据库初始化 / Database Initialization ============

def get_db():
    """获取数据库连接"""
    if 'db' not in g:
        g.db = sqlite3.connect(app.config['DATABASE'])
        g.db.row_factory = sqlite3.Row
        g.db.execute('PRAGMA foreign_keys = ON')
    return g.db

@app.teardown_appcontext
def close_db(exception):
    """关闭数据库连接"""
    db = g.pop('db', None)
    if db is not None:
        db.close()

def init_db():
    """初始化数据库表结构"""
    db = get_db()
    
    # 用户表
    db.execute('''
        CREATE TABLE IF NOT EXISTS users (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            username TEXT UNIQUE NOT NULL,
            email TEXT UNIQUE NOT NULL DEFAULT '',
            password_hash TEXT NOT NULL,
            nickname TEXT DEFAULT '',
            avatar_url TEXT DEFAULT '',
            signature TEXT DEFAULT '',
            token TEXT,
            token_expire INTEGER,
            public_key TEXT,
            ecdh_private_key TEXT,
            created_at INTEGER NOT NULL,
            last_login INTEGER,
            status TEXT DEFAULT 'online'
        )
    ''')
    
    # 好友表
    db.execute('''
        CREATE TABLE IF NOT EXISTS friends (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            user_id INTEGER NOT NULL,
            friend_id INTEGER NOT NULL,
            status TEXT DEFAULT 'pending',
            created_at INTEGER NOT NULL,
            FOREIGN KEY (user_id) REFERENCES users(id),
            FOREIGN KEY (friend_id) REFERENCES users(id),
            UNIQUE(user_id, friend_id)
        )
    ''')
    
    # 消息表
    db.execute('''
        CREATE TABLE IF NOT EXISTS messages (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            msg_id TEXT UNIQUE NOT NULL,
            sender_id INTEGER NOT NULL,
            receiver_id INTEGER,
            group_id INTEGER,
            content TEXT NOT NULL,
            msg_type TEXT DEFAULT 'text',
            is_encrypted INTEGER DEFAULT 1,
            created_at INTEGER NOT NULL,
            FOREIGN KEY (sender_id) REFERENCES users(id),
            FOREIGN KEY (receiver_id) REFERENCES users(id)
        )
    ''')
    
    # 群组表
    db.execute('''
        CREATE TABLE IF NOT EXISTS groups (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            avatar_url TEXT DEFAULT '',
            owner_id INTEGER NOT NULL,
            created_at INTEGER NOT NULL,
            FOREIGN KEY (owner_id) REFERENCES users(id)
        )
    ''')
    
    # 群成员表
    db.execute('''
        CREATE TABLE IF NOT EXISTS group_members (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            group_id INTEGER NOT NULL,
            user_id INTEGER NOT NULL,
            role TEXT DEFAULT 'member',
            joined_at INTEGER NOT NULL,
            FOREIGN KEY (group_id) REFERENCES groups(id),
            FOREIGN KEY (user_id) REFERENCES users(id),
            UNIQUE(group_id, user_id)
        )
    ''')
    
    # 朋友圈/动态表
    db.execute('''
        CREATE TABLE IF NOT EXISTS moments (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            user_id INTEGER NOT NULL,
            content TEXT NOT NULL,
            images TEXT DEFAULT '[]',
            likes_count INTEGER DEFAULT 0,
            comments_count INTEGER DEFAULT 0,
            created_at INTEGER NOT NULL,
            FOREIGN KEY (user_id) REFERENCES users(id)
        )
    ''')
    
    # 朋友圈评论表
    db.execute('''
        CREATE TABLE IF NOT EXISTS moment_comments (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            moment_id INTEGER NOT NULL,
            user_id INTEGER NOT NULL,
            content TEXT NOT NULL,
            created_at INTEGER NOT NULL,
            FOREIGN KEY (moment_id) REFERENCES moments(id),
            FOREIGN KEY (user_id) REFERENCES users(id)
        )
    ''')
    
    # 朋友圈点赞表
    db.execute('''
        CREATE TABLE IF NOT EXISTS moment_likes (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            moment_id INTEGER NOT NULL,
            user_id INTEGER NOT NULL,
            created_at INTEGER NOT NULL,
            FOREIGN KEY (moment_id) REFERENCES moments(id),
            FOREIGN KEY (user_id) REFERENCES users(id),
            UNIQUE(moment_id, user_id)
        )
    ''')
    
    # 音乐表
    db.execute('''
        CREATE TABLE IF NOT EXISTS music (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            title TEXT NOT NULL,
            artist TEXT DEFAULT '',
            album TEXT DEFAULT '',
            url TEXT NOT NULL,
            cover_url TEXT DEFAULT '',
            duration INTEGER DEFAULT 0,
            uploader_id INTEGER NOT NULL,
            created_at INTEGER NOT NULL,
            play_count INTEGER DEFAULT 0,
            FOREIGN KEY (uploader_id) REFERENCES users(id)
        )
    ''')
    
    # 音乐收藏表
    db.execute('''
        CREATE TABLE IF NOT EXISTS music_favorites (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            user_id INTEGER NOT NULL,
            music_id INTEGER NOT NULL,
            created_at INTEGER NOT NULL,
            FOREIGN KEY (user_id) REFERENCES users(id),
            FOREIGN KEY (music_id) REFERENCES music(id),
            UNIQUE(user_id, music_id)
        )
    ''')
    
    # 签到表
    db.execute('''
        CREATE TABLE IF NOT EXISTS checkins (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            user_id INTEGER NOT NULL,
            date TEXT NOT NULL,
            streak INTEGER DEFAULT 1,
            created_at INTEGER NOT NULL,
            FOREIGN KEY (user_id) REFERENCES users(id),
            UNIQUE(user_id, date)
        )
    ''')
    
    # B站用户绑定表
    db.execute('''
        CREATE TABLE IF NOT EXISTS bilibili_bindings (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            user_id INTEGER NOT NULL UNIQUE,
            bilibili_uid TEXT,
            access_token TEXT,
            refresh_token TEXT,
            cookie TEXT,
            created_at INTEGER NOT NULL,
            updated_at INTEGER,
            FOREIGN KEY (user_id) REFERENCES users(id)
        )
    ''')
    
    # ECDH 密钥会话表
    db.execute('''
        CREATE TABLE IF NOT EXISTS ecdh_sessions (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            user_id INTEGER NOT NULL,
            peer_id INTEGER NOT NULL,
            shared_secret TEXT,
            iv TEXT,
            expires_at INTEGER NOT NULL,
            created_at INTEGER NOT NULL,
            FOREIGN KEY (user_id) REFERENCES users(id),
            FOREIGN KEY (peer_id) REFERENCES users(id)
        )
    ''')
    
    # 创建索引
    db.execute('CREATE INDEX IF NOT EXISTS idx_messages_sender ON messages(sender_id)')
    db.execute('CREATE INDEX IF NOT EXISTS idx_messages_receiver ON messages(receiver_id)')
    db.execute('CREATE INDEX IF NOT EXISTS idx_messages_group ON messages(group_id)')
    db.execute('CREATE INDEX IF NOT EXISTS idx_moments_user ON moments(user_id)')
    db.execute('CREATE INDEX IF NOT EXISTS idx_friends_user ON friends(user_id)')
    
    # 数据库迁移 - 确保旧数据库有新增字段
    try:
        db.execute('ALTER TABLE users ADD COLUMN email TEXT UNIQUE NOT NULL DEFAULT ""')
    except sqlite3.OperationalError:
        pass  # 字段已存在
    
    db.commit()
    print("[OldChat Server] 数据库初始化完成")

# ============ 工具函数 / Utility Functions ============

def generate_token():
    """生成随机Token"""
    return secrets.token_hex(32)

def generate_msg_id():
    """生成唯一消息ID"""
    return f"{int(time.time() * 1000)}{secrets.token_hex(8)}"

def hash_password(password):
    """密码哈希"""
    return generate_password_hash(password)

def verify_password(password, password_hash):
    """验证密码"""
    return check_password_hash(password_hash, password)

def get_timestamp():
    """获取当前时间戳(秒)"""
    return int(time.time())

def get_milliseconds():
    """获取当前时间戳(毫秒)"""
    return int(time.time() * 1000)

def success_response(data=None, message="success"):
    """成功响应"""
    response = {"code": 0, "message": message}
    if data is not None:
        response["data"] = data
    return jsonify(response)

def error_response(code=-1, message="error", data=None):
    """错误响应"""
    response = {"code": code, "message": message}
    if data is not None:
        response["data"] = data
    return jsonify(response)

# ============ ECDH 加密函数 / ECDH Encryption Functions ============

if HAS_CRYPTO:
    def generate_ecdh_keypair():
        """生成ECDH密钥对"""
        private_key = ec.generate_private_key(ec.SECP384R1(), default_backend())
        public_key = private_key.public_key()
        return private_key, public_key
    
    def get_public_key_pem(public_key):
        """获取公钥PEM格式"""
        return public_key.public_bytes(
            encoding=serialization.Encoding.PEM,
            format=serialization.PublicFormat.SubjectPublicKeyInfo
        ).decode('utf-8')
    
    def get_private_key_pem(private_key):
        """获取私钥PEM格式"""
        return private_key.private_bytes(
            encoding=serialization.Encoding.PEM,
            format=serialization.PrivateFormat.PKCS8,
            encryption_algorithm=serialization.NoEncryption()
        ).decode('utf-8')
    
    def load_private_key(pem_data):
        """加载私钥"""
        return serialization.load_pem_private_key(
            pem_data.encode('utf-8'),
            password=None,
            backend=default_backend()
        )
    
    def load_public_key(pem_data):
        """加载公钥"""
        return serialization.load_pem_public_key(
            pem_data.encode('utf-8'),
            backend=default_backend()
        )
    
    def compute_shared_secret(private_key, peer_public_key):
        """计算共享密钥"""
        shared_key = private_key.exchange(ec.ECDH(), peer_public_key)
        return hashlib.sha256(shared_key).digest()
    
    def encrypt_aes_gcm(key, plaintext, nonce=None):
        """AES-GCM加密"""
        if nonce is None:
            nonce = secrets.token_bytes(12)
        aesgcm = AESGCM(key)
        ciphertext = aesgcm.encrypt(nonce, plaintext, None)
        return ciphertext, nonce
    
    def decrypt_aes_gcm(key, ciphertext, nonce):
        """AES-GCM解密"""
        aesgcm = AESGCM(key)
        return aesgcm.decrypt(nonce, ciphertext, None)
else:
    # Fallback: 简单加密（仅用于开发环境）
    def generate_ecdh_keypair():
        return None, None
    
    def get_public_key_pem(public_key):
        return "DEMO_PUBLIC_KEY"
    
    def get_private_key_pem(private_key):
        return "DEMO_PRIVATE_KEY"
    
    def compute_shared_secret(private_key, peer_public_key):
        return b"DEMO_SHARED_SECRET"
    
    def encrypt_aes_gcm(key, plaintext, nonce=None):
        if nonce is None:
            nonce = secrets.token_bytes(12)
        return plaintext + nonce, nonce
    
    def decrypt_aes_gcm(key, ciphertext, nonce):
        return ciphertext[:-12]

# ============ 认证装饰器 / Auth Decorator ============

def require_auth(f):
    """需要Token认证的装饰器"""
    @wraps(f)
    def decorated_function(*args, **kwargs):
        token = request.headers.get('Token') or request.args.get('token')
        
        if not token:
            return error_response(401, "缺少认证Token")
        
        db = get_db()
        user = db.execute(
            'SELECT * FROM users WHERE token = ? AND token_expire > ?',
            (token, get_timestamp())
        ).fetchone()
        
        if not user:
            return error_response(401, "Token无效或已过期")
        
        g.current_user = dict(user)
        return f(*args, **kwargs)
    return decorated_function

def require_encryption(f):
    """需要加密的装饰器"""
    @wraps(f)
    def decorated_function(*args, **kwargs):
        if request.headers.get('X-Encrypted') == 'true':
            # 需要解密请求
            pass
        return f(*args, **kwargs)
    return decorated_function

# ============ 用户相关 API / User APIs ============

@app.route('/api/user/register', methods=['POST'])
def api_user_register():
    """用户注册"""
    data = request.get_json() or {}
    username = data.get('username', '').strip()
    password = data.get('password', '')
    nickname = data.get('nickname', username)
    
    if not username or not password:
        return error_response(400, "用户名和密码不能为空")
    
    if len(username) < 3 or len(username) > 32:
        return error_response(400, "用户名长度需在3-32之间")
    
    if len(password) < 6:
        return error_response(400, "密码长度需至少6位")
    
    db = get_db()
    
    # 检查用户是否已存在
    existing = db.execute('SELECT id FROM users WHERE username = ?', (username,)).fetchone()
    if existing:
        return error_response(400, "用户名已存在")
    
    # 创建用户
    password_hash = hash_password(password)
    token = generate_token()
    token_expire = get_timestamp() + app.config['TOKEN_EXPIRY']
    
    cursor = db.execute('''
        INSERT INTO users (username, password_hash, nickname, token, token_expire, created_at)
        VALUES (?, ?, ?, ?, ?, ?)
    ''', (username, password_hash, nickname, token, token_expire, get_timestamp()))
    db.commit()
    
    user_id = cursor.lastrowid
    
    # 生成ECDH密钥对
    if HAS_CRYPTO:
        private_key, public_key = generate_ecdh_keypair()
        private_pem = get_private_key_pem(private_key)
        public_pem = get_public_key_pem(public_key)
        db.execute('UPDATE users SET ecdh_private_key = ?, public_key = ? WHERE id = ?',
                   (private_pem, public_pem, user_id))
        db.commit()
    else:
        public_pem = "DEMO_PUBLIC_KEY"
    
    return success_response({
        "user_id": user_id,
        "username": username,
        "nickname": nickname,
        "token": token,
        "public_key": public_pem
    }, "注册成功")

@app.route('/api/user/login', methods=['POST'])
def api_user_login():
    """用户登录"""
    data = request.get_json() or {}
    username = data.get('username', '').strip()
    password = data.get('password', '')
    
    if not username or not password:
        return error_response(400, "用户名和密码不能为空")
    
    db = get_db()
    user = db.execute('SELECT * FROM users WHERE username = ?', (username,)).fetchone()
    
    if not user or not verify_password(password, user['password_hash']):
        return error_response(401, "用户名或密码错误")
    
    # 更新Token
    token = generate_token()
    token_expire = get_timestamp() + app.config['TOKEN_EXPIRY']
    db.execute('UPDATE users SET token = ?, token_expire = ?, last_login = ?, status = ? WHERE id = ?',
               (token, token_expire, get_timestamp(), 'online', user['id']))
    db.commit()
    
    return success_response({
        "user_id": user['id'],
        "username": user['username'],
        "nickname": user['nickname'],
        "avatar_url": user['avatar_url'],
        "signature": user['signature'],
        "token": token,
        "public_key": user['public_key']
    }, "登录成功")

@app.route('/api/user/logout', methods=['POST'])
@require_auth
def api_user_logout():
    """用户登出"""
    db = get_db()
    db.execute('UPDATE users SET token = NULL, status = ? WHERE id = ?',
               ('offline', g.current_user['id']))
    db.commit()
    return success_response(message="登出成功")

@app.route('/api/user/profile', methods=['GET'])
@require_auth
def api_user_profile():
    """获取用户资料"""
    user = g.current_user
    return success_response({
        "user_id": user['id'],
        "username": user['username'],
        "nickname": user['nickname'],
        "avatar_url": user['avatar_url'],
        "signature": user['signature'],
        "status": user['status'],
        "created_at": user['created_at'],
        "last_login": user['last_login']
    })

@app.route('/api/user/profile', methods=['POST'])
@require_auth
def api_user_update_profile():
    """更新用户资料"""
    data = request.get_json() or {}
    db = get_db()
    
    nickname = data.get('nickname', g.current_user['nickname'])
    avatar_url = data.get('avatar_url', g.current_user['avatar_url'])
    signature = data.get('signature', g.current_user['signature'])
    
    db.execute('''
        UPDATE users SET nickname = ?, avatar_url = ?, signature = ? WHERE id = ?
    ''', (nickname, avatar_url, signature, g.current_user['id']))
    db.commit()
    
    return success_response(message="资料更新成功")

@app.route('/api/user/search', methods=['GET'])
@require_auth
def api_user_search():
    """搜索用户"""
    keyword = request.args.get('keyword', '').strip()
    if not keyword:
        return error_response(400, "搜索关键词不能为空")
    
    db = get_db()
    users = db.execute('''
        SELECT id, username, nickname, avatar_url, signature, status
        FROM users
        WHERE username LIKE ? OR nickname LIKE ?
        LIMIT 20
    ''', (f'%{keyword}%', f'%{keyword}%')).fetchall()
    
    return success_response({
        "users": [dict(u) for u in users]
    })

@app.route('/api/user/<int:user_id>', methods=['GET'])
@require_auth
def api_user_info(user_id):
    """获取指定用户信息"""
    db = get_db()
    user = db.execute('''
        SELECT id, username, nickname, avatar_url, signature, status, created_at
        FROM users WHERE id = ?
    ''', (user_id,)).fetchone()
    
    if not user:
        return error_response(404, "用户不存在")
    
    return success_response(dict(user))

# ============ 好友相关 API / Friend APIs ============

@app.route('/api/friend/list', methods=['GET'])
@require_auth
def api_friend_list():
    """获取好友列表"""
    db = get_db()
    friends = db.execute('''
        SELECT u.id, u.username, u.nickname, u.avatar_url, u.signature, u.status,
               f.created_at as friend_since
        FROM friends f
        JOIN users u ON f.friend_id = u.id
        WHERE f.user_id = ? AND f.status = 'accepted'
    ''', (g.current_user['id'],)).fetchall()
    
    return success_response({
        "friends": [dict(f) for f in friends]
    })

@app.route('/api/friend/request', methods=['POST'])
@require_auth
def api_friend_request():
    """发送好友请求"""
    data = request.get_json() or {}
    friend_id = data.get('friend_id')
    
    if not friend_id:
        return error_response(400, "friend_id不能为空")
    
    if friend_id == g.current_user['id']:
        return error_response(400, "不能添加自己为好友")
    
    db = get_db()
    
    # 检查目标用户是否存在
    target = db.execute('SELECT id FROM users WHERE id = ?', (friend_id,)).fetchone()
    if not target:
        return error_response(404, "用户不存在")
    
    # 检查是否已是好友或请求已存在
    existing = db.execute('''
        SELECT id FROM friends
        WHERE user_id = ? AND friend_id = ?
    ''', (g.current_user['id'], friend_id)).fetchone()
    
    if existing:
        return error_response(400, "好友请求已存在")
    
    # 双向添加好友请求
    timestamp = get_timestamp()
    db.execute('''
        INSERT INTO friends (user_id, friend_id, status, created_at)
        VALUES (?, ?, 'pending', ?)
    ''', (g.current_user['id'], friend_id, timestamp))
    db.execute('''
        INSERT INTO friends (user_id, friend_id, status, created_at)
        VALUES (?, ?, 'pending', ?)
    ''', (friend_id, g.current_user['id'], timestamp))
    db.commit()
    
    return success_response(message="好友请求已发送")

@app.route('/api/friend/accept', methods=['POST'])
@require_auth
def api_friend_accept():
    """接受好友请求"""
    data = request.get_json() or {}
    friend_id = data.get('friend_id')
    
    if not friend_id:
        return error_response(400, "friend_id不能为空")
    
    db = get_db()
    db.execute('''
        UPDATE friends SET status = 'accepted'
        WHERE user_id = ? AND friend_id = ?
    ''', (g.current_user['id'], friend_id))
    db.execute('''
        UPDATE friends SET status = 'accepted'
        WHERE user_id = ? AND friend_id = ?
    ''', (friend_id, g.current_user['id']))
    db.commit()
    
    return success_response(message="已接受好友请求")

@app.route('/api/friend/reject', methods=['POST'])
@require_auth
def api_friend_reject():
    """拒绝好友请求"""
    data = request.get_json() or {}
    friend_id = data.get('friend_id')
    
    if not friend_id:
        return error_response(400, "friend_id不能为空")
    
    db = get_db()
    db.execute('''
        DELETE FROM friends
        WHERE user_id = ? AND friend_id = ?
    ''', (g.current_user['id'], friend_id))
    db.execute('''
        DELETE FROM friends
        WHERE user_id = ? AND friend_id = ?
    ''', (friend_id, g.current_user['id']))
    db.commit()
    
    return success_response(message="已拒绝好友请求")

@app.route('/api/friend/pending', methods=['GET'])
@require_auth
def api_friend_pending():
    """获取待处理的好友请求"""
    db = get_db()
    requests = db.execute('''
        SELECT u.id, u.username, u.nickname, u.avatar_url, f.created_at
        FROM friends f
        JOIN users u ON f.user_id = u.id
        WHERE f.friend_id = ? AND f.status = 'pending'
    ''', (g.current_user['id'],)).fetchall()
    
    return success_response({
        "requests": [dict(r) for r in requests]
    })

@app.route('/api/friend/delete', methods=['POST'])
@require_auth
def api_friend_delete():
    """删除好友"""
    data = request.get_json() or {}
    friend_id = data.get('friend_id')
    
    if not friend_id:
        return error_response(400, "friend_id不能为空")
    
    db = get_db()
    db.execute('''
        DELETE FROM friends WHERE user_id = ? AND friend_id = ?
    ''', (g.current_user['id'], friend_id))
    db.execute('''
        DELETE FROM friends WHERE user_id = ? AND friend_id = ?
    ''', (friend_id, g.current_user['id']))
    db.commit()
    
    return success_response(message="已删除好友")

# ============ 消息相关 API / Message APIs ============

@app.route('/api/message/send', methods=['POST'])
@require_auth
def api_message_send():
    """发送消息"""
    data = request.get_json() or {}
    receiver_id = data.get('receiver_id')
    group_id = data.get('group_id')
    content = data.get('content', '')
    msg_type = data.get('type', 'text')
    is_encrypted = data.get('encrypted', True)
    
    if not receiver_id and not group_id:
        return error_response(400, "receiver_id或group_id必须提供")
    
    if not content:
        return error_response(400, "消息内容不能为空")
    
    msg_id = generate_msg_id()
    timestamp = get_timestamp()
    
    db = get_db()
    
    if group_id:
        # 群消息
        db.execute('''
            INSERT INTO messages (msg_id, sender_id, group_id, content, msg_type, is_encrypted, created_at)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        ''', (msg_id, g.current_user['id'], group_id, content, msg_type, 1 if is_encrypted else 0, timestamp))
    else:
        # 私聊消息
        db.execute('''
            INSERT INTO messages (msg_id, sender_id, receiver_id, content, msg_type, is_encrypted, created_at)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        ''', (msg_id, g.current_user['id'], receiver_id, content, msg_type, 1 if is_encrypted else 0, timestamp))
    
    db.commit()
    
    return success_response({
        "msg_id": msg_id,
        "created_at": timestamp
    }, "消息发送成功")

@app.route('/api/message/private', methods=['GET'])
@require_auth
def api_message_private():
    """获取私聊消息历史"""
    peer_id = request.args.get('peer_id', type=int)
    before = request.args.get('before', type=int, default=0)
    limit = request.args.get('limit', type=int, default=50)
    
    if not peer_id:
        return error_response(400, "peer_id不能为空")
    
    db = get_db()
    messages = db.execute('''
        SELECT m.*, u.username as sender_name, u.nickname as sender_nickname, u.avatar_url as sender_avatar
        FROM messages m
        JOIN users u ON m.sender_id = u.id
        WHERE ((m.sender_id = ? AND m.receiver_id = ?) OR (m.sender_id = ? AND m.receiver_id = ?))
        AND m.group_id IS NULL AND m.created_at < ?
        ORDER BY m.created_at DESC
        LIMIT ?
    ''', (g.current_user['id'], peer_id, peer_id, g.current_user['id'], before, limit)).fetchall()
    
    return success_response({
        "messages": [dict(m) for m in reversed(messages)]
    })

@app.route('/api/message/group', methods=['GET'])
@require_auth
def api_message_group():
    """获取群消息历史"""
    group_id = request.args.get('group_id', type=int)
    before = request.args.get('before', type=int, default=0)
    limit = request.args.get('limit', type=int, default=50)
    
    if not group_id:
        return error_response(400, "group_id不能为空")
    
    db = get_db()
    messages = db.execute('''
        SELECT m.*, u.username as sender_name, u.nickname as sender_nickname, u.avatar_url as sender_avatar
        FROM messages m
        JOIN users u ON m.sender_id = u.id
        WHERE m.group_id = ? AND m.created_at < ?
        ORDER BY m.created_at DESC
        LIMIT ?
    ''', (group_id, before, limit)).fetchall()
    
    return success_response({
        "messages": [dict(m) for m in reversed(messages)]
    })

@app.route('/api/message/conversations', methods=['GET'])
@require_auth
def api_message_conversations():
    """获取会话列表"""
    db = get_db()
    
    # 获取私聊会话
    private_chats = db.execute('''
        SELECT DISTINCT
            CASE 
                WHEN m.sender_id = ? THEN m.receiver_id 
                ELSE m.sender_id 
            END as peer_id,
            u.username, u.nickname, u.avatar_url, u.status,
            m.content as last_message,
            m.created_at as last_time
        FROM messages m
        JOIN users u ON u.id = CASE 
            WHEN m.sender_id = ? THEN m.receiver_id 
            ELSE m.sender_id 
        END
        WHERE (m.sender_id = ? OR m.receiver_id = ?) AND m.group_id IS NULL
        AND m.created_at = (
            SELECT MAX(created_at) FROM messages 
            WHERE (sender_id = ? AND receiver_id = CASE WHEN m.sender_id = ? THEN m.receiver_id ELSE m.sender_id END)
            OR (receiver_id = ? AND sender_id = CASE WHEN m.sender_id = ? THEN m.receiver_id ELSE m.sender_id END)
        )
        ORDER BY m.created_at DESC
    ''', (g.current_user['id'], g.current_user['id'], g.current_user['id'], g.current_user['id'],
          g.current_user['id'], g.current_user['id'], g.current_user['id'], g.current_user['id'])).fetchall()
    
    return success_response({
        "conversations": [dict(c) for c in private_chats]
    })

# ============ 群组相关 API / Group APIs ============

@app.route('/api/group/create', methods=['POST'])
@require_auth
def api_group_create():
    """创建群组"""
    data = request.get_json() or {}
    name = data.get('name', '').strip()
    
    if not name:
        return error_response(400, "群组名称不能为空")
    
    db = get_db()
    timestamp = get_timestamp()
    
    cursor = db.execute('''
        INSERT INTO groups (name, owner_id, created_at)
        VALUES (?, ?, ?)
    ''', (name, g.current_user['id'], timestamp))
    group_id = cursor.lastrowid
    
    # 创建者自动成为群主
    db.execute('''
        INSERT INTO group_members (group_id, user_id, role, joined_at)
        VALUES (?, ?, 'owner', ?)
    ''', (group_id, g.current_user['id'], timestamp))
    db.commit()
    
    return success_response({
        "group_id": group_id,
        "name": name
    }, "群组创建成功")

@app.route('/api/group/list', methods=['GET'])
@require_auth
def api_group_list():
    """获取我加入的群组列表"""
    db = get_db()
    groups = db.execute('''
        SELECT g.*, gm.role,
               (SELECT COUNT(*) FROM group_members WHERE group_id = g.id) as member_count
        FROM groups g
        JOIN group_members gm ON g.id = gm.group_id
        WHERE gm.user_id = ?
        ORDER BY g.created_at DESC
    ''', (g.current_user['id'],)).fetchall()
    
    return success_response({
        "groups": [dict(g) for g in groups]
    })

@app.route('/api/group/<int:group_id>', methods=['GET'])
@require_auth
def api_group_info(group_id):
    """获取群组信息"""
    db = get_db()
    group = db.execute('''
        SELECT g.*, 
               (SELECT COUNT(*) FROM group_members WHERE group_id = g.id) as member_count
        FROM groups g WHERE g.id = ?
    ''', (group_id,)).fetchone()
    
    if not group:
        return error_response(404, "群组不存在")
    
    members = db.execute('''
        SELECT u.id, u.username, u.nickname, u.avatar_url, gm.role
        FROM group_members gm
        JOIN users u ON gm.user_id = u.id
        WHERE gm.group_id = ?
    ''', (group_id,)).fetchall()
    
    return success_response({
        "group": dict(group),
        "members": [dict(m) for m in members]
    })

@app.route('/api/group/<int:group_id>/join', methods=['POST'])
@require_auth
def api_group_join(group_id):
    """加入群组"""
    db = get_db()
    
    # 检查群组是否存在
    group = db.execute('SELECT id FROM groups WHERE id = ?', (group_id,)).fetchone()
    if not group:
        return error_response(404, "群组不存在")
    
    # 检查是否已是成员
    existing = db.execute('''
        SELECT id FROM group_members WHERE group_id = ? AND user_id = ?
    ''', (group_id, g.current_user['id'])).fetchone()
    
    if existing:
        return error_response(400, "已是群成员")
    
    db.execute('''
        INSERT INTO group_members (group_id, user_id, role, joined_at)
        VALUES (?, ?, 'member', ?)
    ''', (group_id, g.current_user['id'], get_timestamp()))
    db.commit()
    
    return success_response(message="加入群组成功")

@app.route('/api/group/<int:group_id>/leave', methods=['POST'])
@require_auth
def api_group_leave(group_id):
    """退出群组"""
    db = get_db()
    
    # 检查是否是群主
    member = db.execute('''
        SELECT role FROM group_members WHERE group_id = ? AND user_id = ?
    ''', (group_id, g.current_user['id'])).fetchone()
    
    if member and member['role'] == 'owner':
        # 检查是否还有其他成员
        other_members = db.execute('''
            SELECT COUNT(*) as cnt FROM group_members WHERE group_id = ? AND user_id != ?
        ''', (group_id, g.current_user['id'])).fetchone()
        
        if other_members['cnt'] == 0:
            # 删除整个群组
            db.execute('DELETE FROM messages WHERE group_id = ?', (group_id,))
            db.execute('DELETE FROM group_members WHERE group_id = ?', (group_id,))
            db.execute('DELETE FROM groups WHERE id = ?', (group_id,))
        else:
            return error_response(400, "群主无法退出群组，请先转让群主身份")
    else:
        db.execute('''
            DELETE FROM group_members WHERE group_id = ? AND user_id = ?
        ''', (group_id, g.current_user['id']))
    
    db.commit()
    return success_response(message="已退出群组")

# ============ 朋友圈/动态 API / Moment APIs ============

@app.route('/api/moment/publish', methods=['POST'])
@require_auth
def api_moment_publish():
    """发布动态"""
    data = request.get_json() or {}
    content = data.get('content', '').strip()
    images = data.get('images', [])
    
    if not content:
        return error_response(400, "动态内容不能为空")
    
    db = get_db()
    cursor = db.execute('''
        INSERT INTO moments (user_id, content, images, created_at)
        VALUES (?, ?, ?, ?)
    ''', (g.current_user['id'], content, json.dumps(images), get_timestamp()))
    db.commit()
    
    return success_response({
        "moment_id": cursor.lastrowid
    }, "动态发布成功")

@app.route('/api/moment/list', methods=['GET'])
@require_auth
def api_moment_list():
    """获取动态列表"""
    before = request.args.get('before', type=int, default=0)
    limit = request.args.get('limit', type=int, default=20)
    
    db = get_db()
    moments = db.execute('''
        SELECT m.*, u.username, u.nickname, u.avatar_url,
               (SELECT COUNT(*) FROM moment_likes WHERE moment_id = m.id) as likes_count,
               (SELECT COUNT(*) FROM moment_comments WHERE moment_id = m.id) as comments_count
        FROM moments m
        JOIN users u ON m.user_id = u.id
        WHERE m.created_at < ?
        ORDER BY m.created_at DESC
        LIMIT ?
    ''', (before, limit)).fetchall()
    
    result = []
    for m in moments:
        moment_dict = dict(m)
        moment_dict['images'] = json.loads(moment_dict.get('images', '[]'))
        result.append(moment_dict)
    
    return success_response({
        "moments": result
    })

@app.route('/api/moment/<int:moment_id>/like', methods=['POST'])
@require_auth
def api_moment_like(moment_id):
    """点赞动态"""
    db = get_db()
    
    # 检查动态是否存在
    moment = db.execute('SELECT id FROM moments WHERE id = ?', (moment_id,)).fetchone()
    if not moment:
        return error_response(404, "动态不存在")
    
    # 检查是否已点赞
    existing = db.execute('''
        SELECT id FROM moment_likes WHERE moment_id = ? AND user_id = ?
    ''', (moment_id, g.current_user['id'])).fetchone()
    
    if existing:
        # 取消点赞
        db.execute('DELETE FROM moment_likes WHERE id = ?', (existing['id'],))
        db.execute('UPDATE moments SET likes_count = likes_count - 1 WHERE id = ?', (moment_id,))
        action = "已取消点赞"
    else:
        # 添加点赞
        db.execute('''
            INSERT INTO moment_likes (moment_id, user_id, created_at)
            VALUES (?, ?, ?)
        ''', (moment_id, g.current_user['id'], get_timestamp()))
        db.execute('UPDATE moments SET likes_count = likes_count + 1 WHERE id = ?', (moment_id,))
        action = "已点赞"
    
    db.commit()
    return success_response(message=action)

@app.route('/api/moment/<int:moment_id>/comment', methods=['POST'])
@require_auth
def api_moment_comment(moment_id):
    """评论动态"""
    data = request.get_json() or {}
    content = data.get('content', '').strip()
    
    if not content:
        return error_response(400, "评论内容不能为空")
    
    db = get_db()
    
    # 检查动态是否存在
    moment = db.execute('SELECT id FROM moments WHERE id = ?', (moment_id,)).fetchone()
    if not moment:
        return error_response(404, "动态不存在")
    
    cursor = db.execute('''
        INSERT INTO moment_comments (moment_id, user_id, content, created_at)
        VALUES (?, ?, ?, ?)
    ''', (moment_id, g.current_user['id'], content, get_timestamp()))
    db.execute('UPDATE moments SET comments_count = comments_count + 1 WHERE id = ?', (moment_id,))
    db.commit()
    
    return success_response({
        "comment_id": cursor.lastrowid
    }, "评论成功")

@app.route('/api/moment/<int:moment_id>/comments', methods=['GET'])
@require_auth
def api_moment_comments(moment_id):
    """获取动态评论列表"""
    db = get_db()
    comments = db.execute('''
        SELECT c.*, u.username, u.nickname, u.avatar_url
        FROM moment_comments c
        JOIN users u ON c.user_id = u.id
        WHERE c.moment_id = ?
        ORDER BY c.created_at ASC
    ''', (moment_id,)).fetchall()
    
    return success_response({
        "comments": [dict(c) for c in comments]
    })

# ============ 音乐相关 API / Music APIs ============

@app.route('/api/music/upload', methods=['POST'])
@require_auth
def api_music_upload():
    """上传音乐信息"""
    data = request.get_json() or {}
    title = data.get('title', '').strip()
    artist = data.get('artist', '')
    album = data.get('album', '')
    url = data.get('url', '').strip()
    cover_url = data.get('cover_url', '')
    duration = data.get('duration', 0)
    
    if not title or not url:
        return error_response(400, "标题和URL不能为空")
    
    db = get_db()
    cursor = db.execute('''
        INSERT INTO music (title, artist, album, url, cover_url, duration, uploader_id, created_at)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    ''', (title, artist, album, url, cover_url, duration, g.current_user['id'], get_timestamp()))
    db.commit()
    
    return success_response({
        "music_id": cursor.lastrowid
    }, "音乐上传成功")

@app.route('/api/music/list', methods=['GET'])
@require_auth
def api_music_list():
    """获取音乐列表"""
    page = request.args.get('page', type=int, default=1)
    limit = request.args.get('limit', type=int, default=20)
    offset = (page - 1) * limit
    
    db = get_db()
    music_list = db.execute('''
        SELECT m.*, u.username as uploader_name, u.nickname as uploader_nickname
        FROM music m
        JOIN users u ON m.uploader_id = u.id
        ORDER BY m.created_at DESC
        LIMIT ? OFFSET ?
    ''', (limit, offset)).fetchall()
    
    total = db.execute('SELECT COUNT(*) as cnt FROM music').fetchone()['cnt']
    
    return success_response({
        "music_list": [dict(m) for m in music_list],
        "total": total,
        "page": page,
        "limit": limit
    })

@app.route('/api/music/<int:music_id>', methods=['GET'])
@require_auth
def api_music_info(music_id):
    """获取音乐详情"""
    db = get_db()
    music = db.execute('''
        SELECT m.*, u.username as uploader_name, u.nickname as uploader_nickname
        FROM music m
        JOIN users u ON m.uploader_id = u.id
        WHERE m.id = ?
    ''', (music_id,)).fetchone()
    
    if not music:
        return error_response(404, "音乐不存在")
    
    # 增加播放次数
    db.execute('UPDATE music SET play_count = play_count + 1 WHERE id = ?', (music_id,))
    db.commit()
    
    return success_response(dict(music))

@app.route('/api/music/<int:music_id>/favorite', methods=['POST'])
@require_auth
def api_music_favorite(music_id):
    """收藏/取消收藏音乐"""
    db = get_db()
    
    # 检查音乐是否存在
    music = db.execute('SELECT id FROM music WHERE id = ?', (music_id,)).fetchone()
    if not music:
        return error_response(404, "音乐不存在")
    
    existing = db.execute('''
        SELECT id FROM music_favorites WHERE user_id = ? AND music_id = ?
    ''', (g.current_user['id'], music_id)).fetchone()
    
    if existing:
        db.execute('DELETE FROM music_favorites WHERE id = ?', (existing['id'],))
        action = "已取消收藏"
    else:
        db.execute('''
            INSERT INTO music_favorites (user_id, music_id, created_at)
            VALUES (?, ?, ?)
        ''', (g.current_user['id'], music_id, get_timestamp()))
        action = "已收藏"
    
    db.commit()
    return success_response(message=action)

@app.route('/api/music/favorites', methods=['GET'])
@require_auth
def api_music_favorites():
    """获取收藏的音乐列表"""
    db = get_db()
    favorites = db.execute('''
        SELECT m.*, u.username as uploader_name, u.nickname as uploader_nickname
        FROM music_favorites mf
        JOIN music m ON mf.music_id = m.id
        JOIN users u ON m.uploader_id = u.id
        WHERE mf.user_id = ?
        ORDER BY mf.created_at DESC
    ''', (g.current_user['id'],)).fetchall()
    
    return success_response({
        "favorites": [dict(f) for f in favorites]
    })

# ============ 签到 API / Checkin APIs ============

@app.route('/api/checkin', methods=['POST'])
@require_auth
def api_checkin():
    """签到"""
    today = datetime.now().strftime('%Y-%m-%d')
    db = get_db()
    
    # 检查今天是否已签到
    existing = db.execute('''
        SELECT id, streak FROM checkins WHERE user_id = ? AND date = ?
    ''', (g.current_user['id'], today)).fetchone()
    
    if existing:
        return error_response(400, "今天已签到")
    
    # 获取昨天的签到信息
    yesterday = (datetime.now() - timedelta(days=1)).strftime('%Y-%m-%d')
    yesterday_checkin = db.execute('''
        SELECT streak FROM checkins WHERE user_id = ? AND date = ?
    ''', (g.current_user['id'], yesterday)).fetchone()
    
    if yesterday_checkin:
        streak = yesterday_checkin['streak'] + 1
    else:
        streak = 1
    
    cursor = db.execute('''
        INSERT INTO checkins (user_id, date, streak, created_at)
        VALUES (?, ?, ?, ?)
    ''', (g.current_user['id'], today, streak, get_timestamp()))
    db.commit()
    
    return success_response({
        "streak": streak,
        "date": today
    }, "签到成功")

@app.route('/api/checkin/status', methods=['GET'])
@require_auth
def api_checkin_status():
    """获取签到状态"""
    today = datetime.now().strftime('%Y-%m-%d')
    db = get_db()
    
    today_checkin = db.execute('''
        SELECT streak FROM checkins WHERE user_id = ? AND date = ?
    ''', (g.current_user['id'], today)).fetchone()
    
    # 获取最高连续签到记录
    max_streak = db.execute('''
        SELECT MAX(streak) as max_streak FROM checkins WHERE user_id = ?
    ''', (g.current_user['id'],)).fetchone()['max_streak'] or 0
    
    return success_response({
        "checked_in": today_checkin is not None,
        "current_streak": today_checkin['streak'] if today_checkin else 0,
        "max_streak": max_streak
    })

# ============ ECDH 密钥交换 API / ECDH Key Exchange APIs ============

@app.route('/api/ecdh/get_public_key', methods=['GET'])
@require_auth
def api_ecdh_get_public_key():
    """获取当前用户的ECDH公钥"""
    return success_response({
        "public_key": g.current_user['public_key'] or "DEMO_PUBLIC_KEY"
    })

@app.route('/api/ecdh/establish', methods=['POST'])
@require_auth
def api_ecdh_establish():
    """建立ECDH密钥会话"""
    data = request.get_json() or {}
    peer_id = data.get('peer_id')
    peer_public_key_pem = data.get('peer_public_key', '')
    
    if not peer_id:
        return error_response(400, "peer_id不能为空")
    
    db = get_db()
    
    # 获取对方的公钥
    peer = db.execute('SELECT public_key FROM users WHERE id = ?', (peer_id,)).fetchone()
    if not peer:
        return error_response(404, "用户不存在")
    
    if not HAS_CRYPTO:
        return success_response({
            "session_id": f"demo_session_{peer_id}",
            "shared_secret": "DEMO_SHARED_SECRET_HEX"
        }, "密钥会话建立成功")
    
    # 使用本地的私钥和对方的公钥计算共享密钥
    try:
        private_key = load_private_key(g.current_user['ecdh_private_key'])
        peer_public_key = load_public_key(peer_public_key_pem or peer['public_key'])
        shared_secret = compute_shared_secret(private_key, peer_public_key)
        
        # 存储会话
        nonce = secrets.token_bytes(12)
        expires_at = get_timestamp() + 24 * 60 * 60  # 24小时
        
        cursor = db.execute('''
            INSERT INTO ecdh_sessions (user_id, peer_id, shared_secret, iv, expires_at, created_at)
            VALUES (?, ?, ?, ?, ?, ?)
        ''', (g.current_user['id'], peer_id, shared_secret.hex(), nonce.hex(), expires_at, get_timestamp()))
        db.commit()
        
        return success_response({
            "session_id": cursor.lastrowid,
            "shared_secret": shared_secret.hex()
        })
    except Exception as e:
        return error_response(500, f"密钥交换失败: {str(e)}")

# ============ B站集成 API / Bilibili Integration APIs ============

@app.route('/api/bilibili/bind', methods=['POST'])
@require_auth
def api_bilibili_bind():
    """绑定B站账号"""
    data = request.get_json() or {}
    bilibili_uid = data.get('bilibili_uid', '')
    access_token = data.get('access_token', '')
    refresh_token = data.get('refresh_token', '')
    cookie = data.get('cookie', '')
    
    db = get_db()
    
    existing = db.execute('''
        SELECT id FROM bilibili_bindings WHERE user_id = ?
    ''', (g.current_user['id'],)).fetchone()
    
    if existing:
        db.execute('''
            UPDATE bilibili_bindings 
            SET bilibili_uid = ?, access_token = ?, refresh_token = ?, cookie = ?, updated_at = ?
            WHERE user_id = ?
        ''', (bilibili_uid, access_token, refresh_token, cookie, get_timestamp(), g.current_user['id']))
    else:
        db.execute('''
            INSERT INTO bilibili_bindings (user_id, bilibili_uid, access_token, refresh_token, cookie, created_at)
            VALUES (?, ?, ?, ?, ?, ?)
        ''', (g.current_user['id'], bilibili_uid, access_token, refresh_token, cookie, get_timestamp()))
    
    db.commit()
    return success_response(message="B站账号绑定成功")

@app.route('/api/bilibili/status', methods=['GET'])
@require_auth
def api_bilibili_status():
    """获取B站绑定状态"""
    db = get_db()
    binding = db.execute('''
        SELECT * FROM bilibili_bindings WHERE user_id = ?
    ''', (g.current_user['id'],)).fetchone()
    
    if binding:
        return success_response({
            "bound": True,
            "bilibili_uid": binding['bilibili_uid']
        })
    else:
        return success_response({
            "bound": False
        })

@app.route('/api/bilibili/unbind', methods=['POST'])
@require_auth
def api_bilibili_unbind():
    """解除B站账号绑定"""
    db = get_db()
    db.execute('DELETE FROM bilibili_bindings WHERE user_id = ?', (g.current_user['id'],))
    db.commit()
    return success_response(message="已解除B站账号绑定")

# ============ 系统信息 API / System APIs ============

@app.route('/api/system/info', methods=['GET'])
def api_system_info():
    """获取系统信息"""
    return success_response({
        "version": "1.0.0",
        "server_time": get_timestamp(),
        "features": {
            "encryption": HAS_CRYPTO,
            "bilibili": True,
            "music": True,
            "moments": True,
            "groups": True
        }
    })

@app.route('/api/system/ping', methods=['GET'])
def api_system_ping():
    """心跳检测"""
    return success_response({
        "pong": get_timestamp(),
        "uptime": time.time() - start_time
    })

# ============ V1 API 路由 (兼容 OldChat 客户端) / V1 API Routes ============

@app.route('/v1/auth/register', methods=['POST'])
def v1_auth_register():
    """用户注册 - V1版本"""
    data = request.get_json() or {}
    username = data.get('username', '').strip()
    email = data.get('email', '').strip()
    password = data.get('password', '')
    captcha_text = data.get('captcha', '')  # 文字验证码
    captcha_email = data.get('captcha_email', '')  # 邮箱验证码
    nickname = data.get('nickname', username)
    
    # 验证必填参数
    if not username or not password:
        return jsonify({"code": 400, "message": "用户名和密码不能为空"})
    
    if len(username) < 3 or len(username) > 32:
        return jsonify({"code": 400, "message": "用户名长度需在3-32之间"})
    
    if len(password) < 6:
        return jsonify({"code": 400, "message": "密码长度需至少6位"})
    
    # 验证码可以乱填（按要求跳过验证）
    # if not captcha_text or not captcha_email:
    #     return jsonify({"code": 400, "message": "验证码不能为空"})
    
    db = get_db()
    
    # 检查用户是否已存在
    existing = db.execute('SELECT id FROM users WHERE username = ?', (username,)).fetchone()
    if existing:
        return jsonify({"code": 400, "message": "用户名已存在"})
    
    # 检查邮箱是否已被使用（如果提供了邮箱）
    if email:
        existing_email = db.execute('SELECT id FROM users WHERE email = ?', (email,)).fetchone()
        if existing_email:
            return jsonify({"code": 400, "message": "邮箱已被使用"})
    
    # 创建用户
    password_hash = hash_password(password)
    token = generate_token()
    token_expire = get_timestamp() + app.config['TOKEN_EXPIRY']
    
    cursor = db.execute('''
        INSERT INTO users (username, email, password_hash, nickname, token, token_expire, created_at)
        VALUES (?, ?, ?, ?, ?, ?, ?)
    ''', (username, email, password_hash, nickname, token, token_expire, get_timestamp()))
    db.commit()
    
    user_id = cursor.lastrowid
    
    # 生成ECDH密钥对
    if HAS_CRYPTO:
        private_key, public_key = generate_ecdh_keypair()
        private_pem = get_private_key_pem(private_key)
        public_pem = get_public_key_pem(public_key)
        db.execute('UPDATE users SET ecdh_private_key = ?, public_key = ? WHERE id = ?',
                   (private_pem, public_pem, user_id))
        db.commit()
    else:
        public_pem = "DEMO_PUBLIC_KEY"
    
    return jsonify({
        "code": 0,
        "message": "注册成功",
        "data": {
            "user_id": user_id,
            "username": username,
            "nickname": nickname,
            "email": email,
            "token": token,
            "public_key": public_pem
        }
    })

@app.route('/v1/auth/login', methods=['POST'])
def v1_auth_login():
    """用户登录 - V1版本"""
    data = request.get_json() or {}
    username = data.get('username', '').strip()
    password = data.get('password', '')
    
    if not username or not password:
        return jsonify({"code": 400, "message": "用户名和密码不能为空"})
    
    db = get_db()
    user = db.execute('SELECT * FROM users WHERE username = ?', (username,)).fetchone()
    
    if not user or not verify_password(password, user['password_hash']):
        return jsonify({"code": 401, "message": "用户名或密码错误"})
    
    # 更新Token
    token = generate_token()
    token_expire = get_timestamp() + app.config['TOKEN_EXPIRY']
    db.execute('UPDATE users SET token = ?, token_expire = ?, last_login = ?, status = ? WHERE id = ?',
               (token, token_expire, get_timestamp(), 'online', user['id']))
    db.commit()
    
    return jsonify({
        "code": 0,
        "message": "登录成功",
        "data": {
            "user_id": user['id'],
            "username": user['username'],
            "nickname": user['nickname'],
            "email": user['email'],
            "avatar_url": user['avatar_url'],
            "signature": user['signature'],
            "token": token,
            "public_key": user['public_key']
        }
    })

@app.route('/v1/auth/captcha', methods=['GET'])
def v1_auth_captcha():
    """获取验证码 - V1版本"""
    # 返回一个简单的占位验证码
    return jsonify({
        "code": 0,
        "data": {
            "captcha_id": f"captcha_{int(time.time())}",
            "captcha_image": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNk+M9QDwADhgGAWjR9awAAAABJRU5ErkJggg=="
        }
    })

@app.route('/v1/me', methods=['GET'])
def v1_me():
    """获取当前用户信息 - V1版本"""
    token = request.headers.get('Token') or request.args.get('token')
    
    if not token:
        return jsonify({"code": 401, "message": "未登录"})
    
    db = get_db()
    user = db.execute(
        'SELECT * FROM users WHERE token = ? AND token_expire > ?',
        (token, get_timestamp())
    ).fetchone()
    
    if not user:
        return jsonify({"code": 401, "message": "Token无效或已过期"})
    
    return jsonify({
        "code": 0,
        "data": {
            "id": user['id'],
            "username": user['username'],
            "nickname": user['nickname'],
            "email": user['email'],
            "avatar_url": user['avatar_url'],
            "signature": user['signature'],
            "status": user['status'],
            "created_at": user['created_at']
        }
    })

@app.route('/v1/friends', methods=['GET'])
def v1_friends():
    """获取好友列表 - V1版本"""
    token = request.headers.get('Token')
    if not token:
        return jsonify({"code": 401, "message": "未登录"})
    
    db = get_db()
    user = db.execute('SELECT * FROM users WHERE token = ? AND token_expire > ?', (token, get_timestamp())).fetchone()
    if not user:
        return jsonify({"code": 401, "message": "Token无效"})
    
    friends = db.execute('''
        SELECT u.id, u.username, u.nickname, u.avatar_url, u.signature, u.status
        FROM friends f
        JOIN users u ON f.friend_id = u.id
        WHERE f.user_id = ? AND f.status = 'accepted'
    ''', (user['id'],)).fetchall()
    
    return jsonify({
        "code": 0,
        "data": {"friends": [dict(f) for f in friends]}
    })

@app.route('/v1/friends/requests', methods=['GET'])
def v1_friends_requests():
    """获取好友请求列表 - V1版本"""
    token = request.headers.get('Token')
    if not token:
        return jsonify({"code": 401, "message": "未登录"})
    
    db = get_db()
    user = db.execute('SELECT * FROM users WHERE token = ? AND token_expire > ?', (token, get_timestamp())).fetchone()
    if not user:
        return jsonify({"code": 401, "message": "Token无效"})
    
    requests_list = db.execute('''
        SELECT u.id, u.username, u.nickname, u.avatar_url, f.created_at
        FROM friends f
        JOIN users u ON f.user_id = u.id
        WHERE f.friend_id = ? AND f.status = 'pending'
    ''', (user['id'],)).fetchall()
    
    return jsonify({
        "code": 0,
        "data": {"requests": [dict(r) for r in requests_list]}
    })

@app.route('/v1/groups/list', methods=['GET'])
def v1_groups_list():
    """获取群组列表 - V1版本"""
    token = request.headers.get('Token')
    if not token:
        return jsonify({"code": 401, "message": "未登录"})
    
    db = get_db()
    user = db.execute('SELECT * FROM users WHERE token = ? AND token_expire > ?', (token, get_timestamp())).fetchone()
    if not user:
        return jsonify({"code": 401, "message": "Token无效"})
    
    groups = db.execute('''
        SELECT g.*, gm.role
        FROM groups g
        JOIN group_members gm ON g.id = gm.group_id
        WHERE gm.user_id = ?
    ''', (user['id'],)).fetchall()
    
    return jsonify({
        "code": 0,
        "data": {"groups": [dict(g) for g in groups]}
    })

@app.route('/v1/notifications', methods=['GET'])
def v1_notifications():
    """获取通知列表 - V1版本"""
    token = request.headers.get('Token')
    if not token:
        return jsonify({"code": 401, "message": "未登录"})
    
    db = get_db()
    user = db.execute('SELECT * FROM users WHERE token = ? AND token_expire > ?', (token, get_timestamp())).fetchone()
    if not user:
        return jsonify({"code": 401, "message": "Token无效"})
    
    return jsonify({
        "code": 0,
        "data": {"notifications": []}
    })

@app.route('/v1/direct/unread', methods=['POST'])
def v1_direct_unread():
    """获取未读私信数 - V1版本"""
    token = request.headers.get('Token')
    if not token:
        return jsonify({"code": 401, "message": "未登录"})
    
    db = get_db()
    user = db.execute('SELECT * FROM users WHERE token = ? AND token_expire > ?', (token, get_timestamp())).fetchone()
    if not user:
        return jsonify({"code": 401, "message": "Token无效"})
    
    return jsonify({
        "code": 0,
        "data": {"unread": 0}
    })

@app.route('/v1/groups/unread', methods=['POST'])
def v1_groups_unread():
    """获取未读群消息数 - V1版本"""
    token = request.headers.get('Token')
    if not token:
        return jsonify({"code": 401, "message": "未登录"})
    
    db = get_db()
    user = db.execute('SELECT * FROM users WHERE token = ? AND token_expire > ?', (token, get_timestamp())).fetchone()
    if not user:
        return jsonify({"code": 401, "message": "Token无效"})
    
    return jsonify({
        "code": 0,
        "data": {"unread": 0}
    })

@app.route('/update/update.json', methods=['GET'])
def v1_update_check():
    """版本更新检查"""
    return jsonify({
        "code": 0,
        "data": {
            "version": "1.2.34",
            "version_code": 34,
            "update_url": "",
            "changelog": "",
            "force_update": False
        }
    })

# ============ 静态文件服务 / Static File Serving ============

@app.route('/')
def index():
    """首页"""
    return jsonify({
        "name": "OldChat Compatible Server",
        "version": "1.0.0",
        "status": "running"
    })

@app.route('/static/<path:filename>')
def serve_static(filename):
    """提供静态文件"""
    return send_from_directory(app.static_folder, filename)

# ============ 错误处理 / Error Handlers ============

@app.errorhandler(404)
def not_found(e):
    return error_response(404, "接口不存在")

@app.errorhandler(500)
def internal_error(e):
    return error_response(500, "服务器内部错误")

# ============ 启动服务器 / Start Server ============

if __name__ == '__main__':
    import argparse
    
    parser = argparse.ArgumentParser(description='OldChat Compatible Server')
    parser.add_argument('--host', default='0.0.0.0', help='绑定主机地址')
    parser.add_argument('--port', type=int, default=8080, help='绑定端口')
    parser.add_argument('--database', default='oldchat.db', help='数据库文件路径')
    parser.add_argument('--debug', action='store_true', help='调试模式')
    args = parser.parse_args()
    
    # 初始化数据库
    with app.app_context():
        app.config['DATABASE'] = args.database
        init_db()
    
    start_time = time.time()
    print(f"[OldChat Server] 启动中...")
    print(f"[OldChat Server] 数据库: {args.database}")
    print(f"[OldChat Server] 加密支持: {'启用' if HAS_CRYPTO else '禁用 (开发模式)'}")
    print(f"[OldChat Server] 监听: {args.host}:{args.port}")
    
    app.run(host=args.host, port=args.port, debug=args.debug)
