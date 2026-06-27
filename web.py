#!/usr/bin/env python3
"""
OldChat Web 界面 - 独立运行
复用 app.py 中的数据库函数
"""
import os
import sys
import json
import time
import secrets

# 确保 eventlet monkey_patch 在最前面
import eventlet
eventlet.monkey_patch()

from flask import Flask, request, session, jsonify, redirect, url_for, render_template_string, send_from_directory

# 导入 app.py 中的数据库函数
import app as app_module

# ============================================================================
# 配置
# ============================================================================
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
WEB_STATIC_DIR = os.path.join(BASE_DIR, "uploads", "web", "static")
WEB_UPLOADS_DIR = os.path.join(BASE_DIR, "uploads")

app = Flask(__name__, static_folder=WEB_STATIC_DIR, static_url_path="/static")
app.secret_key = secrets.token_hex(32)

# 复用 app.py 的数据库路径
DB_PATH = app_module.DB_PATH

# 默认主题（momopink，去掉多主题选择，但保留样式文件）
DEFAULT_THEME = "momopink"
DEFAULT_THEME_CSS = "style.css"

# ============================================================================
# 工具函数 - 直接复用 app.py 的
# ============================================================================
db_query_one = app_module.db_query_one
db_query_all = app_module.db_query_all
db_execute = app_module.db_execute
verify_password = app_module.verify_password
hash_password = app_module.hash_password
gen_uid = app_module.gen_uid
now_ts = app_module.now_ts

# ============================================================================
# 登录页面（去掉动态壁纸，但保留整体布局样式）
# ============================================================================
LOGIN_TEMPLATE = r"""<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <title>OldChat for Kivotos</title>
    <link rel="icon" href="/static/momotalklogo.png" type="image/png">
    <style>
        @font-face {
            font-family: 'zyyt';
            src: url('/static/zyyt.ttf') format('truetype');
            font-weight: normal;
            font-style: normal;
            font-display: swap;
        }
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { 
            margin: 0; 
            font-family: 'zyyt', 'Segoe UI', Roboto, 'Helvetica Neue', sans-serif; 
            overflow: hidden; 
            background: url('/static/KivotosBg2.jpg') center/cover no-repeat fixed;
            min-height: 100vh;
        }
        .login-wrapper { 
            position: relative; 
            z-index: 10; 
            display: flex; 
            justify-content: flex-end; 
            align-items: center; 
            min-height: 100vh; 
            padding: 20px 80px 20px 20px; 
        }
        @media (max-width: 640px) { 
            .login-wrapper { justify-content: center; padding: 20px; } 
        }
        .login-card { 
            background: rgba(255, 255, 255, 0.95); 
            backdrop-filter: blur(10px);
            padding: 2rem 2rem 2.2rem 2rem; 
            border-radius: 20px; 
            box-shadow: 0 12px 28px rgba(0,0,0,0.12), 0 2px 4px rgba(0,0,0,0.04); 
            width: 100%; 
            max-width: 380px; 
            transition: all 0.2s; 
            text-align: left; 
        }
        .login-card h1 { 
            margin-bottom: 0.35rem; 
            font-weight: 500; 
            font-size: 1.8rem; 
            color: #1f2a3e; 
            letter-spacing: -0.3px; 
        }
        .greeting { 
            display: flex; 
            align-items: center; 
            gap: 10px; 
            font-size: 0.95rem; 
            color: #5a6874; 
            margin-bottom: 1.8rem; 
            border-left: 3px solid #b0c4de; 
            padding-left: 12px; 
            font-weight: 400; 
        }
        .greeting-text { flex: 1; }
        .form-group { margin-bottom: 1.25rem; }
        .login-card input { 
            width: 100%; 
            padding: 12px 16px; 
            border: 1px solid #cfdde6; 
            border-radius: 14px; 
            font-size: 0.95rem; 
            font-family: 'zyyt', inherit; 
            background-color: #fff; 
            transition: 0.2s; 
            outline: none; 
            color: #1e2a3e; 
        }
        .login-card input:focus { 
            border-color: #0078d4; 
            box-shadow: 0 0 0 3px rgba(0, 120, 212, 0.15); 
        }
        .login-card input::placeholder { 
            color: #9aaebf; 
            font-weight: 400; 
            font-size: 0.9rem; 
        }
        .login-card button { 
            width: 100%; 
            padding: 12px 16px; 
            background: #0078d4; 
            color: white; 
            border: none; 
            border-radius: 40px; 
            font-size: 1rem; 
            font-weight: 500; 
            cursor: pointer; 
            transition: background 0.2s, transform 0.05s; 
            margin-top: 6px; 
            letter-spacing: 0.3px; 
        }
        .login-card button:hover { background: #106ebe; }
        .login-card button:active { transform: scale(0.98); }
        .login-card button:disabled { opacity: 0.7; cursor: not-allowed; }
        .error { 
            background: #fff5f5; 
            color: #c23d3d; 
            padding: 10px 14px; 
            border-radius: 14px; 
            font-size: 0.85rem; 
            margin-bottom: 1.25rem; 
            border-left: 3px solid #c23d3d; 
            font-weight: 500; 
        }
        .success {
            background: #f0fff4;
            color: #2d8a4e;
            padding: 10px 14px;
            border-radius: 14px;
            font-size: 0.85rem;
            margin-bottom: 1.25rem;
            border-left: 3px solid #2d8a4e;
            font-weight: 500;
        }
        .checkbox-group { 
            display: flex; 
            align-items: center; 
            gap: 10px; 
            margin: 0.5rem 0 1rem 0; 
            justify-content: flex-start; 
            text-align: left; 
        }
        .checkbox-group input[type="checkbox"] { 
            width: 18px; 
            height: 18px; 
            margin: 0; 
            cursor: pointer; 
            accent-color: #0078d4; 
            flex-shrink: 0; 
        }
        .checkbox-group label { 
            font-size: 0.9rem; 
            color: #2c3e4e; 
            cursor: pointer; 
            user-select: none; 
            font-weight: 450; 
        }
        .extra-note { 
            text-align: center; 
            margin-top: 1.5rem; 
            font-size: 0.7rem; 
            color: #94a3b8; 
            border-top: 1px solid #eaedf0; 
            padding-top: 1rem; 
        }
        .tab-group {
            display: flex;
            gap: 8px;
            margin-bottom: 1.25rem;
        }
        .tab {
            flex: 1;
            padding: 10px;
            text-align: center;
            background: #eef2f7;
            border-radius: 12px;
            cursor: pointer;
            font-size: 0.9rem;
            color: #5a6874;
            transition: all 0.2s;
        }
        .tab.active {
            background: #0078d4;
            color: white;
        }
        .tab-content { display: none; }
        .tab-content.active { display: block; }
        @media (max-width: 480px) {
          .login-card { padding: 1.6rem 1.5rem 1.9rem; }
          .login-card h1 { font-size: 1.6rem; }
          .checkbox-group label { font-size: 0.85rem; }
        }
    </style>
</head>
<body>
    <div class="login-wrapper">
        <div class="login-card">
            <h1>OldChat for Kivotos</h1>
            <div class="greeting">
                <span class="greeting-text">Sensei 欢迎回来，等你很久啦</span>
            </div>

            <div id="message"></div>

            <div class="tab-group">
                <div class="tab active" data-tab="login">登录</div>
                <div class="tab" data-tab="register">注册</div>
            </div>

            <div class="tab-content active" id="login-form">
                <form id="loginForm">
                    <div class="form-group">
                        <input type="text" name="identifier" id="identifier" placeholder="账号 / 邮箱 / UID" required autofocus>
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" id="password" placeholder="密码" required>
                    </div>
                    <div class="checkbox-group" style="display: flex; justify-content: space-between; align-items: center; margin: 0.5rem 0 1rem 0;">
                        <div style="display: flex; align-items: center; gap: 6px;">
                            <input type="checkbox" id="rememberAccountCheckbox">
                            <label for="rememberAccountCheckbox">记住账户</label>
                        </div>
                    </div>
                    <button type="submit">进入什亭之箱</button>
                </form>
            </div>

            <div class="tab-content" id="register-form">
                <form id="registerForm">
                    <div class="form-group">
                        <input type="text" name="username" id="reg-username" placeholder="用户名" required>
                    </div>
                    <div class="form-group">
                        <input type="text" name="display_name" id="reg-display-name" placeholder="显示名称（可选）">
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" id="reg-password" placeholder="密码（至少6位）" required>
                    </div>
                    <button type="submit">注册账号</button>
                </form>
            </div>

            <div class="extra-note">
                OldChat for Kivotos
            </div>
        </div>
    </div>

    <script>
        // Tab 切换
        document.querySelectorAll('.tab').forEach(tab => {
            tab.addEventListener('click', () => {
                document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
                document.querySelectorAll('.tab-content').forEach(c => c.classList.remove('active'));
                tab.classList.add('active');
                document.getElementById(tab.dataset.tab + '-form').classList.add('active');
            });
        });

        const msgEl = document.getElementById('message');
        function showMsg(msg, type) {
            msgEl.innerHTML = '<div class="' + type + '">' + msg + '</div>';
            setTimeout(function() { msgEl.innerHTML = ''; }, 3000);
        }

        // 记住账户
        (function autoFillCredentials() {
            try {
                const savedId = localStorage.getItem('savedIdentifier');
                if (savedId) document.getElementById('identifier').value = savedId;
                const checkbox = document.getElementById('rememberAccountCheckbox');
                if (checkbox && savedId) checkbox.checked = true;
            } catch(e) {}
        })();

        // 登录
        document.getElementById('loginForm').addEventListener('submit', async function(e) {
            e.preventDefault();
            const id = document.getElementById('identifier').value.trim();
            const pwd = document.getElementById('password').value;
            if (!id || !pwd) return;
            
            const btn = this.querySelector('button[type="submit"]');
            btn.disabled = true;
            btn.textContent = '登录中...';
            
            try {
                const r = await fetch('/api/login', {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({identifier: id, password: pwd})
                });
                const d = await r.json();
                if (d.success) {
                    const rememberCheck = document.getElementById('rememberAccountCheckbox');
                    if (rememberCheck && rememberCheck.checked) {
                        try {
                            localStorage.setItem('savedIdentifier', id);
                        } catch(e) {}
                    }
                    showMsg('登录成功！', 'success');
                    setTimeout(function() { window.location.href = '/'; }, 500);
                } else {
                    showMsg(d.error || '登录失败', 'error');
                }
            } catch(err) {
                showMsg('网络错误', 'error');
            }
            
            btn.disabled = false;
            btn.textContent = '进入什亭之箱';
        });

        // 注册
        document.getElementById('registerForm').addEventListener('submit', async function(e) {
            e.preventDefault();
            const u = document.getElementById('reg-username').value.trim();
            const n = document.getElementById('reg-display-name').value.trim();
            const p = document.getElementById('reg-password').value;
            if (!u || !p) return;
            
            const btn = this.querySelector('button[type="submit"]');
            btn.disabled = true;
            btn.textContent = '注册中...';
            
            try {
                const r = await fetch('/api/register', {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({username: u, display_name: n, password: p})
                });
                const d = await r.json();
                if (d.success) {
                    showMsg('注册成功！请登录', 'success');
                    document.querySelector('.tab[data-tab="login"]').click();
                    document.getElementById('identifier').value = u;
                } else {
                    showMsg(d.error || '注册失败', 'error');
                }
            } catch(err) {
                showMsg('网络错误', 'error');
            }
            
            btn.disabled = false;
            btn.textContent = '注册账号';
        });
    </script>
</body>
</html>
"""

# 主页面模板（保留 Kivotos 样式，去掉主题切换按钮，使用默认 momopink 主题）
INDEX_TEMPLATE = r"""<!DOCTYPE html>
<html lang="zh-CN" data-theme="light">
<head>
    <meta charset="UTF-8">
    <title>OldChat for Kivotos</title>
    <link rel="icon" href="/static/momotalk.png" type="image/png">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@7/css/all.min.css">
    <link rel="stylesheet" href="/static/style/momopink/style.css">
    <meta name="uid" content="{{ uid }}">
    <meta name="theme-merge-messages" content="false">
</head>
<body>
    <div class="app-container">
        <div class="voice-bg-wave" id="voiceBgWave"><div class="voice-bg-wave-bars"></div></div>
        <!-- 侧边栏 -->
        <aside class="sidebar">
            <div class="sidebar-header">
                <span class="user-info">{{ user_name }}<br><small>OldChat for Kivotos</small></span>
                <button id="pinSidebarBtn" class="icon-btn" title="取消固定"><i class="fa-solid fa-thumbtack"></i></button>
                <button id="aboutBtn" class="icon-btn" title="关于"><i class="fa-solid fa-circle-info"></i></button>
                <button id="logoutBtn" class="icon-btn" title="退出登录"><i class="fa-solid fa-right-from-bracket"></i></button>
            </div>
            <div class="contact-list" id="contactList">
                <!-- 动态 -->
            </div>
        </aside>

        <main class="chat-area">
            <div class="chat-header" id="chatHeader">
                <span>OldChat for Kivotos</span>
            </div>
            <div class="messages" id="messagesContainer">
            </div>
            <div class="input-area" id="inputArea">
                <div class="quote-preview" id="quotePreview" style="display: none;">
                    <span class="quote-preview-text"></span>
                    <button id="cancelQuoteBtn" class="icon-btn" title="取消引用">✖</button>
                </div>
            
                <textarea id="messageInput" placeholder="输入消息... (Enter发送, Shift+Enter换行)" rows="2"></textarea>
                <div class="input-buttons">
                    <button id="nowPlayingBtn" class="btn" style="display:none;" title="跳转到正在播放的语音"><i class="fa-solid fa-music"></i></button>
                    <button id="sendImageBtn" class="btn">图片</button>
                    <button id="emojiPlazaBtn" class="btn">表情</button>
                    <button id="moreBtn" class="btn">更多</button>
                    <button id="sendBtn" class="btn primary">发送</button>
                </div>
                <div class="more-menu" id="moreMenu" style="display: none;">
                    <button id="sendFileBtn">上传文件</button>
                    <button id="sendUrlImageBtn">发送直链图片 (仅限网页端)</button>
                    <button id="sendUrlVoiceBtn">发送直链音频</button>
                    <button id="sendEmoticonBtn">内置表情</button>
                </div>
                <input type="file" id="fileInput" style="display: none" multiple>
            </div>
        </main>
    </div>
    <div class="url-input-overlay" id="urlInputOverlay" style="display:none;">
        <div class="url-input-dialog">
            <div class="url-input-title">输入图片链接</div>
            <input type="text" id="urlImageInput" placeholder="https://...">
            <div class="url-input-actions">
                <button id="urlInputCancel" class="btn">取消</button>
                <button id="urlInputSend" class="btn primary">发送</button>
            </div>
        </div>
    </div>
    <script src="/static/script.js"></script>
</body>
</html>
"""

# ============================================================================
# 认证辅助
# ============================================================================
def get_current_user():
    """获取当前登录用户"""
    uid = session.get('uid')
    if not uid:
        return None
    row = db_query_one("SELECT * FROM users WHERE uid = ?", (uid,))
    return row

def require_login():
    """需要登录的装饰器风格检查"""
    user = get_current_user()
    if not user:
        return None
    return user

# ============================================================================
# 页面路由
# ============================================================================

@app.route('/')
def index():
    """主页面"""
    user = get_current_user()
    if not user:
        return redirect('/login')
    return render_template_string(
        INDEX_TEMPLATE,
        uid=user['uid'],
        user_name=user['display_name'] or user['username']
    )

@app.route('/login')
def login():
    """登录页面"""
    return LOGIN_TEMPLATE

@app.route('/logout')
def logout():
    """退出登录"""
    session.clear()
    return redirect('/login')

# ============================================================================
# API 路由
# ============================================================================

@app.route('/api/login', methods=['POST'])
def api_login():
    """登录 API"""
    data = request.get_json(silent=True) or {}
    identifier = (data.get('identifier') or '').strip()
    password = data.get('password') or ''
    
    if not identifier or not password:
        return jsonify({'success': False, 'error': '请填写所有字段'}), 400
    
    row = db_query_one(
        "SELECT * FROM users WHERE uid = ? OR username = ?",
        (identifier.upper(), identifier),
    )
    if not row:
        return jsonify({'success': False, 'error': '用户不存在'}), 401
    
    if not verify_password(password, row['password_hash']):
        return jsonify({'success': False, 'error': '密码错误'}), 401
    
    session['uid'] = row['uid']
    session['user_id'] = row['id']
    session['user_name'] = row['display_name'] or row['username']
    
    return jsonify({
        'success': True,
        'uid': row['uid'],
        'name': row['display_name'] or row['username'],
    })

@app.route('/api/register', methods=['POST'])
def api_register():
    """注册 API"""
    data = request.get_json(silent=True) or {}
    username = (data.get('username') or '').strip()
    display_name = (data.get('display_name') or '').strip() or username
    password = data.get('password') or ''
    
    if not username or not password:
        return jsonify({'success': False, 'error': '请填写所有字段'}), 400
    if len(username) < 3 or len(username) > 32:
        return jsonify({'success': False, 'error': '用户名长度需在 3-32 位'}), 400
    if len(password) < 6:
        return jsonify({'success': False, 'error': '密码至少 6 位'}), 400
    
    exist = db_query_one("SELECT id FROM users WHERE username = ?", (username,))
    if exist:
        return jsonify({'success': False, 'error': '用户名已存在'}), 409
    
    uid = gen_uid()
    while db_query_one("SELECT id FROM users WHERE uid = ?", (uid,)):
        uid = gen_uid()
    
    pwd_hash = hash_password(password)
    now = now_ts()
    
    uid_int = db_execute(
        "INSERT INTO users (uid, username, password_hash, display_name, avatar_url, cover_url, bio, online, last_seen, created_at) VALUES (?, ?, ?, ?, '', '', '', 0, ?, ?)",
        (uid, username, pwd_hash, display_name, now, now),
    )
    
    # 自动加入默认群（复用 app.py 的函数）
    default_gid = app_module._ensure_default_group()
    if default_gid:
        try:
            latest_before = db_query_one(
                "SELECT MAX(id) as max_id FROM group_messages WHERE group_id = ?",
                (default_gid,),
            )
            last_read = (latest_before["max_id"] if latest_before else 0) or 0
            db_execute(
                "INSERT OR IGNORE INTO group_members (group_id, user_id, joined_at, last_read_msg_id) VALUES (?, ?, ?, ?)",
                (default_gid, uid_int, now, last_read),
            )
            welcome = (app_module.SETTINGS.get("auto_join_group", {}) or {}).get("welcome_message", "")
            if welcome:
                db_execute(
                    "INSERT INTO group_messages (group_id, from_id, body, msg_type, created_at) VALUES (?, 0, ?, 'system', ?)",
                    (default_gid, welcome, now),
                )
        except Exception as _e:
            pass
    
    session['uid'] = uid
    session['user_id'] = uid_int
    session['user_name'] = display_name
    
    return jsonify({'success': True, 'uid': uid})

@app.route('/api/contacts')
def api_contacts():
    """获取联系人列表（好友 + 群聊）"""
    user = require_login()
    if not user:
        return jsonify({'error': '未登录'}), 401
    
    my_id = user['id']
    
    # 好友列表
    friends = db_query_all(
        """SELECT u.id, u.uid, u.username, u.display_name, u.avatar_url, u.online
           FROM users u WHERE u.id IN (
               SELECT CASE WHEN f.user_id = ? THEN f.friend_id ELSE f.user_id END
               FROM friendships f
               WHERE f.status = 'accepted' AND (f.user_id = ? OR f.friend_id = ?)
           ) ORDER BY u.display_name COLLATE NOCASE""",
        (my_id, my_id, my_id),
    )
    
    friends_list = []
    for f in friends:
        # 获取最后一条消息
        last_msg = db_query_one(
            """SELECT body FROM direct_messages
               WHERE (from_id = ? AND to_id = ?) OR (from_id = ? AND to_id = ?)
               ORDER BY id DESC LIMIT 1""",
            (my_id, f['id'], f['id'], my_id),
        )
        friends_list.append({
            'uid': f['uid'],
            'name': f['display_name'] or f['username'],
            'avatar': f['avatar_url'] or '',
            'online': bool(f['online']),
            'last_message': last_msg['body'] if last_msg else '',
        })
    
    # 群聊列表
    groups = db_query_all(
        """SELECT g.id, g.group_id, g.name, g.avatar_url, gm.joined_at
           FROM groups g
           JOIN group_members gm ON gm.group_id = g.id
           WHERE gm.user_id = ?
           ORDER BY gm.joined_at DESC""",
        (my_id,),
    )
    
    groups_list = []
    for g in groups:
        # 获取群最后一条消息
        last_msg = db_query_one(
            "SELECT body, from_id FROM group_messages WHERE group_id = ? ORDER BY id DESC LIMIT 1",
            (g['id'],),
        )
        last_text = ''
        if last_msg:
            sender = db_query_one("SELECT display_name, username FROM users WHERE id = ?", (last_msg['from_id'],))
            sender_name = (sender['display_name'] or sender['username']) if sender else ''
            last_text = f"{sender_name}: {last_msg['body']}" if last_msg['from_id'] != 0 else last_msg['body']
        
        groups_list.append({
            'id': g['group_id'],
            'name': g['name'],
            'avatar': g['avatar_url'] or '',
            'last_message': last_text,
            'member_count': db_query_one(
                "SELECT COUNT(*) as cnt FROM group_members WHERE group_id = ?",
                (g['id'],),
            )['cnt'],
        })
    
    return jsonify({
        'friends': friends_list,
        'groups': groups_list,
    })

@app.route('/api/messages/<msg_type>/<target_id>')
def api_messages(msg_type, target_id):
    """获取消息列表"""
    user = require_login()
    if not user:
        return jsonify({'error': '未登录'}), 401
    
    my_id = user['id']
    limit = min(int(request.args.get('limit', 100)), 200)
    offset = int(request.args.get('offset', 0))
    
    if msg_type == 'direct':
        # 私聊
        target = db_query_one("SELECT id, uid, display_name FROM users WHERE uid = ?", (target_id.upper(),))
        if not target:
            return jsonify({'messages': []})
        target_id_int = target['id']
        
        # 计算总数用于倒序分页
        total = db_query_one(
            """SELECT COUNT(*) as cnt FROM direct_messages
               WHERE (from_id = ? AND to_id = ?) OR (from_id = ? AND to_id = ?)""",
            (my_id, target_id_int, target_id_int, my_id),
        )['cnt']
        
        # 倒序获取：最新的消息在最后，offset 越大越旧
        mrows = db_query_all(
            """SELECT dm.* FROM direct_messages dm
               WHERE (dm.from_id = ? AND dm.to_id = ?) OR (dm.from_id = ? AND dm.to_id = ?)
               ORDER BY dm.id DESC LIMIT ? OFFSET ?""",
            (my_id, target_id_int, target_id_int, my_id, limit, offset),
        )
        mrows.reverse()  # 翻回来变成正序
        
        msgs = []
        for m in mrows:
            sender = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (m['from_id'],))
            msgs.append({
                'id': m['id'],
                'from_uid': sender['uid'] if sender else '',
                'from_name': (sender['display_name'] or sender['uid']) if sender else '未知',
                'from_avatar': sender['avatar_url'] or '' if sender else '',
                'body': m['body'] or '',
                'msg_type': m.get('msg_type', 'text') or 'text',
                'created_at': m['created_at'],
                'is_me': m['from_id'] == my_id,
            })
        
        return jsonify({'messages': msgs, 'total': total})
    
    elif msg_type == 'group':
        # 群聊
        group = db_query_one("SELECT id, group_id, name FROM groups WHERE group_id = ?", (target_id,))
        if not group:
            return jsonify({'messages': []})
        group_id_int = group['id']
        
        # 检查是否是群成员
        member = db_query_one(
            "SELECT 1 FROM group_members WHERE group_id = ? AND user_id = ?",
            (group_id_int, my_id),
        )
        if not member:
            return jsonify({'messages': []})
        
        total = db_query_one(
            "SELECT COUNT(*) as cnt FROM group_messages WHERE group_id = ?",
            (group_id_int,),
        )['cnt']
        
        mrows = db_query_all(
            """SELECT gm.* FROM group_messages gm
               WHERE gm.group_id = ?
               ORDER BY gm.id DESC LIMIT ? OFFSET ?""",
            (group_id_int, limit, offset),
        )
        mrows.reverse()
        
        msgs = []
        for m in mrows:
            sender = db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (m['from_id'],)) if m['from_id'] != 0 else None
            msgs.append({
                'id': m['id'],
                'from_uid': sender['uid'] if sender else 'system',
                'from_name': (sender['display_name'] or sender['uid']) if sender else '系统',
                'from_avatar': sender['avatar_url'] or '' if sender else '',
                'body': m['body'] or '',
                'msg_type': m.get('msg_type', 'text') or 'text',
                'created_at': m['created_at'],
                'is_me': m['from_id'] == my_id,
                'group_id': target_id,
            })
        
        return jsonify({'messages': msgs, 'total': total})
    
    return jsonify({'messages': []})

@app.route('/api/mark_read/<msg_type>/<target_id>', methods=['PUT'])
def api_mark_read(msg_type, target_id):
    """标记已读"""
    user = require_login()
    if not user:
        return jsonify({'error': '未登录'}), 401
    
    my_id = user['id']
    
    if msg_type == 'direct':
        target = db_query_one("SELECT id FROM users WHERE uid = ?", (target_id.upper(),))
        if target:
            db_execute(
                "UPDATE direct_messages SET is_read = 1 WHERE from_id = ? AND to_id = ? AND is_read = 0",
                (target['id'], my_id),
            )
    
    elif msg_type == 'group':
        group = db_query_one("SELECT id FROM groups WHERE group_id = ?", (target_id,))
        if group:
            latest = db_query_one(
                "SELECT MAX(id) as max_id FROM group_messages WHERE group_id = ?",
                (group['id'],),
            )
            if latest and latest['max_id']:
                db_execute(
                    "UPDATE group_members SET last_read_msg_id = ? WHERE group_id = ? AND user_id = ?",
                    (latest['max_id'], group['id'], my_id),
                )
    
    return jsonify({'success': True})

@app.route('/api/send', methods=['POST'])
def api_send():
    """发送消息"""
    user = require_login()
    if not user:
        return jsonify({'error': '未登录'}), 401
    
    data = request.get_json(silent=True) or {}
    msg_type = data.get('type', 'direct')  # direct 或 group
    target_id = data.get('target_id', '')
    body = data.get('body', '')
    
    if not target_id or not body:
        return jsonify({'error': '参数错误'}), 400
    
    my_id = user['id']
    my_uid = user['uid']
    my_name = user['display_name'] or user['username']
    now = now_ts()
    
    if msg_type == 'direct':
        target = db_query_one("SELECT id, uid, display_name FROM users WHERE uid = ?", (target_id.upper(),))
        if not target:
            return jsonify({'error': '用户不存在'}), 404
        
        mid = db_execute(
            "INSERT INTO direct_messages (from_id, to_id, body, created_at, is_read) VALUES (?, ?, ?, ?, 0)",
            (my_id, target['id'], body, now),
        )
        
        # 推送给对方（复用 app.py 的 push_to_user）
        try:
            app_module.push_to_user(target['id'], {
                'type': 'direct_message',
                'data': {
                    'id': mid,
                    'from_uid': my_uid,
                    'from_name': my_name,
                    'from_avatar': user['avatar_url'] or '',
                    'to_uid': target['uid'],
                    'body': body,
                    'created_at': now,
                },
            })
        except Exception:
            pass
        
        return jsonify({
            'id': mid,
            'from_uid': my_uid,
            'from_name': my_name,
            'body': body,
            'created_at': now,
        })
    
    elif msg_type == 'group':
        group = db_query_one("SELECT id, group_id, name FROM groups WHERE group_id = ?", (target_id,))
        if not group:
            return jsonify({'error': '群不存在'}), 404
        
        # 检查是否是成员
        member = db_query_one(
            "SELECT 1 FROM group_members WHERE group_id = ? AND user_id = ?",
            (group['id'], my_id),
        )
        if not member:
            return jsonify({'error': '不是群成员'}), 403
        
        mid = db_execute(
            "INSERT INTO group_messages (group_id, from_id, body, created_at) VALUES (?, ?, ?, ?)",
            (group['id'], my_id, body, now),
        )
        
        # 推送给所有群成员（简化处理，这里只返回成功）
        try:
            members = db_query_all(
                "SELECT user_id FROM group_members WHERE group_id = ? AND user_id != ?",
                (group['id'], my_id),
            )
            for m in members:
                app_module.push_to_user(m['user_id'], {
                    'type': 'group_message',
                    'data': {
                        'id': mid,
                        'group_id': group['group_id'],
                        'group_name': group['name'],
                        'from_uid': my_uid,
                        'from_name': my_name,
                        'body': body,
                        'created_at': now,
                    },
                })
        except Exception:
            pass
        
        return jsonify({
            'id': mid,
            'group_id': group['group_id'],
            'from_uid': my_uid,
            'from_name': my_name,
            'body': body,
            'created_at': now,
        })
    
    return jsonify({'error': '未知类型'}), 400

@app.route('/api/unread')
def api_unread():
    """获取未读数量（简化）"""
    user = require_login()
    if not user:
        return jsonify({'error': '未登录'}), 401
    return jsonify({'total': 0, 'items': []})

@app.route('/api/themes')
def api_themes():
    """主题列表（只返回默认主题）"""
    return jsonify([
        {'id': 'momopink', 'name': 'Momo Pink', 'preview': ''}
    ])

@app.route('/api/set_theme', methods=['POST'])
def api_set_theme():
    """设置主题（保留接口但不做多主题）"""
    return jsonify({'success': True})

@app.route('/api/upload_and_send', methods=['POST'])
def api_upload_and_send():
    """上传并发送（简化，返回占位）"""
    user = require_login()
    if not user:
        return jsonify({'error': '未登录'}), 401
    return jsonify({'error': '暂未实现'}), 501

@app.route('/api/emoticons')
def api_emoticons():
    """内置表情列表（简化）"""
    return jsonify([])

@app.route('/api/emoji/plaza')
def api_emoji_plaza():
    """表情广场（简化）"""
    return jsonify({'items': [], 'has_more': False})

# ============================================================================
# 静态文件
# ============================================================================

@app.route('/media/<path:filename>')
def media_file(filename):
    """媒体文件（和 app.py 共享 media 目录）"""
    media_dir = os.path.join(BASE_DIR, "media")
    return send_from_directory(media_dir, filename)

# ============================================================================
# 启动
# ============================================================================

if __name__ == '__main__':
    port = 5000
    print()
    print("=" * 60)
    print(" OldChat Web 界面 已启动: http://localhost:%d" % port)
    print(" 数据库: %s" % DB_PATH)
    print(" 静态文件: %s" % WEB_STATIC_DIR)
    print("=" * 60)
    print()
    app.run(host='0.0.0.0', port=port, debug=False, threaded=True)
