# -*- coding: utf-8 -*-
"""
OldChat Web 界面 (OldChat for Kivotos 风格)
与 app.py 共用同一个 Flask app 实例
"""
import os

# 静态文件目录（会在 init_web 时根据 BASE_DIR 设置）
WEB_DIR = None


def init_web(app):
    """将所有 web 路由注册到指定的 Flask app 上"""
    global WEB_DIR
    # web.py 和 app.py 在同一目录，所以用当前工作目录
    BASE_DIR = os.getcwd()
    WEB_DIR = os.path.join(BASE_DIR, "uploads", "web")
    os.makedirs(WEB_DIR, exist_ok=True)

    # =========================================================================
    #  HTML 模板
    # =========================================================================

    # 登录页 HTML（匹配聊天界面粉色风格，居中卡片，记住账号密码）
    _WEB_LOGIN_HTML = r"""<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <title>OldChat for Kivotos</title>
    <link rel="icon" href="https://gwebcdn260523.pages.dev/v1/static/momotalklogo.png" type="image/png">
    <style>
        @font-face {
            font-family: 'zyyt';
            src: url('https://gwebcdn260523.pages.dev/v1/static/zyyt.ttf') format('truetype');
            font-weight: normal;
            font-style: normal;
            font-display: swap;
        }
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body {
            font-family: 'zyyt', '微软雅黑', 'Microsoft YaHei', sans-serif;
            min-height: 100vh;
            background: #f4f7f8;
            display: flex;
            flex-direction: column;
        }
        .login-header {
            background: #fa94a6;
            color: #fff;
            padding: 13px 12px;
            font-size: 15px;
            font-weight: 500;
            text-align: center;
            flex-shrink: 0;
        }
        .login-body {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        .login-card {
            background: #fff;
            border-radius: 16px;
            box-shadow: 0 2px 12px rgba(0,0,0,0.06);
            width: 100%;
            max-width: 380px;
            overflow: hidden;
        }
        .login-card-top {
            padding: 32px 24px 0;
            text-align: center;
        }
        .login-card-top h1 {
            font-size: 1.5rem;
            font-weight: 600;
            color: #fa94a6;
        }
        .login-card-body {
            padding: 24px;
        }
        .tab-group {
            display: flex;
            gap: 0;
            margin-bottom: 20px;
        }
        .tab {
            flex: 1;
            padding: 10px;
            text-align: center;
            cursor: pointer;
            font-size: 0.9rem;
            font-weight: 500;
            color: #999;
            transition: all 0.2s;
            border-bottom: 2px solid transparent;
        }
        .tab.active {
            color: #fa94a6;
            border-bottom: 2px solid #fa94a6;
        }
        .tab-content { display: none; }
        .tab-content.active { display: block; }
        .form-group { margin-bottom: 14px; }
        .form-group input {
            width: 100%;
            padding: 11px 14px;
            border: 1px solid #e1e1e1;
            border-radius: 10px;
            font-size: 0.9rem;
            font-family: inherit;
            background: #f9f9f9;
            outline: none;
            color: #333;
            transition: 0.2s;
        }
        .form-group input:focus {
            border-color: #fa94a6;
            box-shadow: 0 0 0 3px rgba(250,148,166,0.15);
            background: #fff;
        }
        .form-group input::placeholder { color: #aaa; }
        .checkbox-group {
            display: flex;
            align-items: center;
            gap: 8px;
            margin: 0 0 16px 0;
        }
        .checkbox-group input[type="checkbox"] {
            width: 16px;
            height: 16px;
            cursor: pointer;
            accent-color: #fff;
            background: #fa94a6;
            border: 1.5px solid #fa94a6;
            border-radius: 3px;
        }
        .checkbox-group label {
            font-size: 0.85rem;
            color: #666;
            cursor: pointer;
            user-select: none;
        }
        .login-card button[type="submit"] {
            width: 100%;
            padding: 11px;
            background: #fa94a6;
            color: #fff;
            border: none;
            border-radius: 10px;
            font-size: 0.95rem;
            font-weight: 500;
            cursor: pointer;
            transition: background 0.2s;
            font-family: inherit;
        }
        .login-card button[type="submit"]:hover { background: #e88294; }
        .login-card button[type="submit"]:active { transform: scale(0.98); }
        .login-card button[type="submit"]:disabled { opacity: 0.7; cursor: not-allowed; }
        .msg-error {
            background: #fff0f0;
            color: #c23d3d;
            padding: 8px 12px;
            border-radius: 8px;
            font-size: 0.82rem;
            margin-bottom: 14px;
            text-align: center;
        }
        .msg-success {
            background: #f0fff4;
            color: #2d8a4e;
            padding: 8px 12px;
            border-radius: 8px;
            font-size: 0.82rem;
            margin-bottom: 14px;
            text-align: center;
        }
        .extra-note {
            text-align: center;
            margin-top: 20px;
            font-size: 0.7rem;
            color: #bbb;
        }
    </style>
</head>
<body>
    <div class="login-header">OldChat for Kivotos</div>
    <div class="login-body">
        <div class="login-card">
            <div class="login-card-top">
                <h1>欢迎回来</h1>
            </div>
            <div class="login-card-body">
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
                        <div class="checkbox-group">
                            <input type="checkbox" id="rememberAccountCheckbox">
                            <label for="rememberAccountCheckbox">记住账号</label>
                        </div>
                        <button type="submit">登录</button>
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
                        <button type="submit">注册</button>
                    </form>
                </div>
                <div class="extra-note">OldChat for Kivotos</div>
            </div>
        </div>
    </div>
    <script>
        document.querySelectorAll('.tab').forEach(function(tab) {
            tab.addEventListener('click', function() {
                document.querySelectorAll('.tab').forEach(function(t) { t.classList.remove('active'); });
                document.querySelectorAll('.tab-content').forEach(function(c) { c.classList.remove('active'); });
                tab.classList.add('active');
                document.getElementById(tab.dataset.tab + '-form').classList.add('active');
            });
        });
        var msgEl = document.getElementById('message');
        function showMsg(msg, type) {
            msgEl.innerHTML = '<div class="msg-' + type + '">' + msg + '</div>';
            setTimeout(function() { msgEl.innerHTML = ''; }, 3000);
        }
        (function autoFill() {
            try {
                var savedId = localStorage.getItem('savedIdentifier');
                var savedPwd = localStorage.getItem('savedPassword');
                if (savedId) document.getElementById('identifier').value = savedId;
                if (savedPwd) document.getElementById('password').value = savedPwd;
                var cb = document.getElementById('rememberAccountCheckbox');
                if (cb && savedId) cb.checked = true;
            } catch(e) {}
        })();
        document.getElementById('loginForm').addEventListener('submit', async function(e) {
            e.preventDefault();
            var id = document.getElementById('identifier').value.trim();
            var pwd = document.getElementById('password').value;
            if (!id || !pwd) return;
            var btn = this.querySelector('button[type="submit"]');
            btn.disabled = true; btn.textContent = '登录中...';
            try {
                var r = await fetch('/web/api/login', {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({identifier: id, password: pwd})
                });
                var d = await r.json();
                if (d.success) {
                    var cb = document.getElementById('rememberAccountCheckbox');
                    if (cb && cb.checked) {
                        try {
                            localStorage.setItem('savedIdentifier', id);
                            localStorage.setItem('savedPassword', pwd);
                        } catch(e) {}
                    } else {
                        try {
                            localStorage.removeItem('savedIdentifier');
                            localStorage.removeItem('savedPassword');
                        } catch(e) {}
                    }
                    window.location.href = '/web/';
                } else {
                    showMsg(d.error || '登录失败', 'error');
                }
            } catch(err) {
                showMsg('网络错误', 'error');
            }
            btn.disabled = false; btn.textContent = '登录';
        });
        document.getElementById('registerForm').addEventListener('submit', async function(e) {
            e.preventDefault();
            var u = document.getElementById('reg-username').value.trim();
            var n = document.getElementById('reg-display-name').value.trim();
            var p = document.getElementById('reg-password').value;
            if (!u || !p) return;
            var btn = this.querySelector('button[type="submit"]');
            btn.disabled = true; btn.textContent = '注册中...';
            try {
                var r = await fetch('/web/api/register', {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({username: u, display_name: n, password: p})
                });
                var d = await r.json();
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
            btn.disabled = false; btn.textContent = '注册';
        });
    </script>
</body>
</html>
"""

    # 主页面 HTML（保留 Kivotos 整体布局，使用 momopink 主题，去掉主题切换）
    _WEB_INDEX_HTML = r"""<!DOCTYPE html>
<html lang="zh-CN" data-theme="light">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>OldChat for Kivotos</title>
    <link rel="icon" href="https://gwebcdn260523.pages.dev/v1/static/momotalklogo.png" type="image/png">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@7/css/all.min.css">
    <link rel="stylesheet" href="/web/static/style.css">
    <meta name="uid" content="{{ uid }}">
    <meta name="name" content="{{ user_name }}">
    <meta name="avatar" content="{{ user_avatar }}">
    <meta name="theme-merge-messages" content="false">
</head>
<body>
    <div class="app-container">
        <div class="voice-bg-wave" id="voiceBgWave"><div class="voice-bg-wave-bars"></div></div>
        <aside class="sidebar">
            <div class="sidebar-header">
                <span class="user-info">{{ user_name }}<br><small>OldChat for Kivotos</small></span>
                <button id="pinSidebarBtn" class="icon-btn" title="取消固定"><i class="fa-solid fa-thumbtack"></i></button>
                <button id="aboutBtn" class="icon-btn" title="关于"><i class="fa-solid fa-circle-info"></i></button>
                <button id="logoutBtn" class="icon-btn" title="退出登录"><i class="fa-solid fa-right-from-bracket"></i></button>
            </div>
            <div class="contact-list" id="contactList"></div>
        </aside>
        <main class="chat-area">
            <div class="chat-header" id="chatHeader">
                <button class="mobile-menu-btn" id="mobileMenuBtn"><i class="fa-solid fa-chevron-left"></i></button>
                <span class="chat-title">OldChat for Kivotos</span>
            </div>
            <div class="messages" id="messagesContainer"></div>
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
    <script src="/web/static/script.js"></script>
    <script>
        // 覆盖原 script.js 中的 API 基础路径
        // 原 script.js 使用 /api/...，我们改成 /web/api/...
        // 用一个简单的方式：拦截 fetch
        (function() {
            var origFetch = window.fetch;
            window.fetch = function(url, options) {
                if (typeof url === 'string' && url.startsWith('/api/')) {
                    url = '/web' + url;
                }
                return origFetch(url, options);
            };
        })();
    </script>
</body>
</html>
"""

    # =========================================================================
    #  路由函数定义
    # =========================================================================

    def web_index():
        from flask import session, redirect
        uid = session.get("web_uid")
        if not uid:
            return redirect("/web/login")
        import app as app_module
        user = app_module.db_query_one("SELECT * FROM users WHERE uid = ?", (uid,))
        if not user:
            session.clear()
            return redirect("/web/login")
        return _WEB_INDEX_HTML.replace(
            "{{ uid }}", user["uid"]
        ).replace(
            "{{ user_name }}", user["display_name"] or user["username"]
        ).replace(
            "{{ user_avatar }}", user["avatar_url"] or ""
        )

    def web_login():
        return _WEB_LOGIN_HTML

    def web_logout():
        from flask import session, redirect
        session.clear()
        return redirect("/web/login")

    def web_static(filename):
        from flask import send_from_directory
        return send_from_directory(WEB_DIR, filename)

    def web_api_login():
        from flask import request, jsonify, session
        import app as app_module
        body = request.get_json(silent=True) or {}
        identifier = (body.get("identifier") or "").strip()
        password = body.get("password") or ""
        if not identifier or not password:
            return jsonify({"success": False, "error": "请填写所有字段"}), 400
        row = app_module.db_query_one(
            "SELECT * FROM users WHERE uid = ? OR username = ?",
            (identifier.upper(), identifier),
        )
        if not row:
            return jsonify({"success": False, "error": "用户不存在"}), 401
        if not app_module.verify_password(password, row["password_hash"]):
            return jsonify({"success": False, "error": "密码错误"}), 401
        session["web_uid"] = row["uid"]
        session["web_user_id"] = row["id"]
        session["web_user_name"] = row["display_name"] or row["username"]
        return jsonify({
            "success": True,
            "uid": row["uid"],
            "name": row["display_name"] or row["username"],
        })

    def web_api_register():
        from flask import request, jsonify, session
        import app as app_module
        body = request.get_json(silent=True) or {}
        username = (body.get("username") or "").strip()
        display_name = (body.get("display_name") or "").strip() or username
        password = body.get("password") or ""
        if not username or not password:
            return jsonify({"success": False, "error": "请填写所有字段"}), 400
        if len(username) < 3 or len(username) > 32:
            return jsonify({"success": False, "error": "用户名长度需在 3-32 位"}), 400
        if len(password) < 6:
            return jsonify({"success": False, "error": "密码至少 6 位"}), 400
        exist = app_module.db_query_one("SELECT id FROM users WHERE username = ?", (username,))
        if exist:
            return jsonify({"success": False, "error": "用户名已存在"}), 409
        uid = app_module.gen_uid()
        while app_module.db_query_one("SELECT id FROM users WHERE uid = ?", (uid,)):
            uid = app_module.gen_uid()
        pwd_hash = app_module.hash_password(password)
        now = app_module.now_ts()
        uid_int = app_module.db_execute(
            "INSERT INTO users (uid, username, password_hash, display_name, avatar_url, cover_url, bio, online, last_seen, created_at) VALUES (?, ?, ?, ?, '', '', '', 0, ?, ?)",
            (uid, username, pwd_hash, display_name, now, now),
        )
        default_gid = app_module._ensure_default_group()
        if default_gid:
            try:
                latest_before = app_module.db_query_one(
                    "SELECT MAX(id) as max_id FROM group_messages WHERE group_id = ?",
                    (default_gid,),
                )
                last_read = (latest_before["max_id"] if latest_before else 0) or 0
                app_module.db_execute(
                    "INSERT OR IGNORE INTO group_members (group_id, user_id, joined_at, last_read_msg_id) VALUES (?, ?, ?, ?)",
                    (default_gid, uid_int, now, last_read),
                )
                welcome = (app_module.SETTINGS.get("auto_join_group", {}) or {}).get("welcome_message", "")
                if welcome:
                    body = display_name + " " + welcome
                    app_module.db_execute(
                        "INSERT INTO group_messages (group_id, from_id, body, msg_type, created_at) VALUES (?, 0, ?, 'system', ?)",
                        (default_gid, body, now),
                    )
            except Exception as _e:
                app_module.log.warning("[web register] auto-join failed: %s", _e)
        session["web_uid"] = uid
        session["web_user_id"] = uid_int
        session["web_user_name"] = display_name
        return jsonify({"success": True, "uid": uid})

    def _web_current_user():
        from flask import session
        import app as app_module
        uid = session.get("web_uid")
        if not uid:
            return None
        return app_module.db_query_one("SELECT * FROM users WHERE uid = ?", (uid,))

    def web_api_ws_token():
        from flask import jsonify, session
        import app as app_module
        user = _web_current_user()
        if not user:
            return jsonify({"error": "未登录"}), 401
        import secrets
        access_token = secrets.token_hex(32)
        expires_at = app_module.now_ts() + 7 * 24 * 3600
        # 先删旧 token，再插入新的，避免 UNIQUE 约束冲突
        app_module.db_execute("DELETE FROM tokens WHERE user_id = ?", (user["id"],))
        app_module.db_execute(
            "INSERT INTO tokens (access_token, user_id, created_at, expires_at) VALUES (?, ?, ?, ?)",
            (access_token, user["id"], app_module.now_ts(), expires_at),
        )
        return jsonify({"token": access_token})

    def web_api_contacts():
        from flask import jsonify
        import app as app_module
        user = _web_current_user()
        if not user:
            return jsonify({"error": "未登录"}), 401
        my_id = user["id"]
        # 好友
        friends = app_module.db_query_all(
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
            last_msg = app_module.db_query_one(
                """SELECT body FROM direct_messages
                   WHERE (from_id = ? AND to_id = ?) OR (from_id = ? AND to_id = ?)
                   ORDER BY id DESC LIMIT 1""",
                (my_id, f["id"], f["id"], my_id),
            )
            friends_list.append({
                "uid": f["uid"],
                "name": f["display_name"] or f["username"],
                "avatar": f["avatar_url"] or "",
                "online": bool(f["online"]),
                "last_message": last_msg["body"] if last_msg else "",
            })
        # 群聊
        groups = app_module.db_query_all(
            """SELECT g.id, g.group_id, g.name, g.avatar_url, gm.joined_at
               FROM groups g
               JOIN group_members gm ON gm.group_id = g.group_id
               WHERE gm.user_id = ?
               ORDER BY gm.joined_at DESC""",
            (my_id,),
        )
        groups_list = []
        for g in groups:
            last_msg = app_module.db_query_one(
                "SELECT body, from_id FROM group_messages WHERE group_id = ? ORDER BY id DESC LIMIT 1",
                (g["group_id"],),
            )
            last_text = ""
            if last_msg:
                if last_msg["from_id"] == 0:
                    last_text = last_msg["body"]
                else:
                    sender = app_module.db_query_one("SELECT display_name, username FROM users WHERE id = ?", (last_msg["from_id"],))
                    sn = (sender["display_name"] or sender["username"]) if sender else ""
                    last_text = sn + ": " + last_msg["body"]
            groups_list.append({
                "id": g["group_id"],
                "name": g["name"],
                "avatar": g["avatar_url"] or "",
                "last_message": last_text,
                "member_count": app_module.db_query_one(
                    "SELECT COUNT(*) as cnt FROM group_members WHERE group_id = ?", (g["group_id"],),
                )["cnt"],
            })
        return jsonify({"friends": friends_list, "groups": groups_list})

    def web_api_messages(msg_type, target_id):
        from flask import request, jsonify
        import app as app_module
        user = _web_current_user()
        if not user:
            return jsonify({"error": "未登录"}), 401
        my_id = user["id"]
        limit = min(int(request.args.get("limit", 100)), 200)
        offset = int(request.args.get("offset", 0))
        if msg_type == "direct":
            target = app_module.db_query_one("SELECT id, uid, display_name, avatar_url FROM users WHERE uid = ?", (target_id.upper(),))
            if not target:
                return jsonify({"messages": []})
            tid = target["id"]
            mrows = app_module.db_query_all(
                """SELECT dm.* FROM direct_messages dm
                   WHERE (dm.from_id = ? AND dm.to_id = ?) OR (dm.from_id = ? AND dm.to_id = ?)
                   ORDER BY dm.id DESC LIMIT ? OFFSET ?""",
                (my_id, tid, tid, my_id, limit, offset),
            )
            mrows.reverse()
            msgs = []
            for m in mrows:
                sender = app_module.db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (m["from_id"],))
                msgs.append({
                    "id": m["id"],
                    "from_uid": sender["uid"] if sender else "",
                    "from_name": (sender["display_name"] or sender["uid"]) if sender else "未知",
                    "from_avatar": sender["avatar_url"] or "" if sender else "",
                    "body": m["body"] or "",
                    "msg_type": m["msg_type"] or "text",
                    "media_url": m["media_url"] or "",
                    "thumb_url": m["thumb_url"] or "",
                    "created_at": m["created_at"],
                    "is_me": m["from_id"] == my_id,
                })
            return jsonify({"messages": msgs})
        elif msg_type == "group":
            group = app_module.db_query_one("SELECT id, group_id, name FROM groups WHERE group_id = ?", (target_id,))
            if not group:
                return jsonify({"messages": []})
            gid_str = group["group_id"]
            member = app_module.db_query_one(
                "SELECT 1 FROM group_members WHERE group_id = ? AND user_id = ?", (gid_str, my_id),
            )
            if not member:
                return jsonify({"messages": []})
            mrows = app_module.db_query_all(
                """SELECT gm.* FROM group_messages gm
                   WHERE gm.group_id = ?
                   ORDER BY gm.id DESC LIMIT ? OFFSET ?""",
                (gid_str, limit, offset),
            )
            mrows.reverse()
            msgs = []
            for m in mrows:
                if m["from_id"] == 0:
                    msgs.append({
                        "id": m["id"],
                        "from_uid": "system",
                        "from_name": "系统",
                        "from_avatar": "",
                        "body": m["body"] or "",
                        "msg_type": m["msg_type"] or "text",
                        "media_url": m["media_url"] or "",
                        "thumb_url": m["thumb_url"] or "",
                        "created_at": m["created_at"],
                        "is_me": False,
                        "group_id": target_id,
                    })
                else:
                    sender = app_module.db_query_one("SELECT uid, display_name, avatar_url FROM users WHERE id = ?", (m["from_id"],))
                    msgs.append({
                        "id": m["id"],
                        "from_uid": sender["uid"] if sender else "",
                        "from_name": (sender["display_name"] or sender["uid"]) if sender else "未知",
                        "from_avatar": sender["avatar_url"] or "" if sender else "",
                        "body": m["body"] or "",
                        "msg_type": m["msg_type"] or "text",
                        "media_url": m["media_url"] or "",
                        "thumb_url": m["thumb_url"] or "",
                        "created_at": m["created_at"],
                        "is_me": m["from_id"] == my_id,
                        "group_id": target_id,
                    })
            return jsonify({"messages": msgs})
        return jsonify({"messages": []})

    def web_api_mark_read(msg_type, target_id):
        from flask import request, jsonify
        import app as app_module
        user = _web_current_user()
        if not user:
            return jsonify({"error": "未登录"}), 401
        my_id = user["id"]
        if msg_type == "direct":
            target = app_module.db_query_one("SELECT id FROM users WHERE uid = ?", (target_id.upper(),))
            if target:
                app_module.db_execute(
                    "UPDATE direct_messages SET is_read = 1 WHERE from_id = ? AND to_id = ? AND is_read = 0",
                    (target["id"], my_id),
                )
        elif msg_type == "group":
            group = app_module.db_query_one("SELECT id, group_id FROM groups WHERE group_id = ?", (target_id,))
            if group:
                latest = app_module.db_query_one(
                    "SELECT MAX(id) as max_id FROM group_messages WHERE group_id = ?", (group["group_id"],),
                )
                if latest and latest["max_id"]:
                    app_module.db_execute(
                        "UPDATE group_members SET last_read_msg_id = ? WHERE group_id = ? AND user_id = ?",
                        (latest["max_id"], group["group_id"], my_id),
                    )
        return jsonify({"success": True})

    def web_api_send():
        from flask import request, jsonify
        import app as app_module
        user = _web_current_user()
        if not user:
            return jsonify({"error": "未登录"}), 401
        data = request.get_json(silent=True) or {}
        conv_type = data.get("type", "direct")
        target_id = data.get("target_id", "")
        body = data.get("body", "")
        msg_type = data.get("msg_type", "text")
        media_url = data.get("media_url") or None
        thumb_url = data.get("thumb_url") or None
        burn_after_seconds = int(data.get("burn_after_seconds") or 0)
        if not target_id:
            return jsonify({"error": "参数错误"}), 400
        if not body and not media_url and msg_type == "text":
            return jsonify({"error": "参数错误"}), 400
        my_id = user["id"]
        my_uid = user["uid"]
        my_name = user["display_name"] or user["username"]
        my_avatar = user["avatar_url"] or ""
        now = app_module.now_ts()
        if conv_type == "direct":
            target = app_module.db_query_one("SELECT id, uid, display_name FROM users WHERE uid = ?", (target_id.upper(),))
            if not target:
                return jsonify({"error": "用户不存在"}), 404
            mid = app_module.db_execute(
                "INSERT INTO direct_messages (from_id, to_id, body, msg_type, media_url, thumb_url, burn_after_seconds, created_at, is_read) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0)",
                (my_id, target["id"], body, msg_type, media_url, thumb_url, burn_after_seconds, now),
            )
            msg_dict = {
                "id": str(mid),
                "thread_id": target["uid"],
                "from_uid": my_uid,
                "to_uid": target["uid"],
                "body": body,
                "msg_type": msg_type,
                "media_url": media_url or "",
                "thumb_url": thumb_url or "",
                "duration_ms": burn_after_seconds,
                "burn_after_seconds": burn_after_seconds,
                "created_at": now,
                "is_read": 0,
                "status": 1,
                "from_name": my_name,
                "from_avatar": my_avatar,
            }
            try:
                sent = app_module.push_to_user(target["id"], {"type": "direct_message", "data": msg_dict})
                app_module.log.info("[web_send] push to user_id=%s, sent=%s", target["id"], sent)
            except Exception as e:
                app_module.log.warning("[web_send] push error: %s", e)
            return jsonify(msg_dict)
        elif conv_type == "group":
            group = app_module.db_query_one("SELECT id, group_id, name FROM groups WHERE group_id = ?", (target_id,))
            if not group:
                return jsonify({"error": "群不存在"}), 404
            gid_str = group["group_id"]
            member = app_module.db_query_one(
                "SELECT 1 FROM group_members WHERE group_id = ? AND user_id = ?", (gid_str, my_id),
            )
            if not member:
                return jsonify({"error": "不是群成员"}), 403
            mid = app_module.db_execute(
                "INSERT INTO group_messages (group_id, from_id, body, msg_type, media_url, thumb_url, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)",
                (gid_str, my_id, body, msg_type, media_url, thumb_url, now),
            )
            msg_dict = {
                "id": str(mid),
                "group_id": group["group_id"],
                "thread_id": group["group_id"],
                "from_uid": my_uid,
                "body": body,
                "msg_type": msg_type,
                "media_url": media_url or "",
                "thumb_url": thumb_url or "",
                "duration_ms": 0,
                "burn_after_seconds": 0,
                "created_at": now,
                "status": 1,
                "from_name": my_name,
                "from_avatar": my_avatar,
            }
            try:
                members = app_module.db_query_all(
                    "SELECT user_id FROM group_members WHERE group_id = ? AND user_id != ?", (gid_str, my_id),
                )
                for m in members:
                    app_module.push_to_user(m["user_id"], {"type": "group_message", "data": msg_dict})
            except Exception:
                pass
            return jsonify(msg_dict)
        return jsonify({"error": "未知类型"}), 400

    def web_api_unread():
        from flask import jsonify
        user = _web_current_user()
        if not user:
            return jsonify({"error": "未登录"}), 401
        return jsonify({"total": 0, "items": []})

    def web_api_themes():
        from flask import jsonify
        return jsonify([{"id": "momopink", "name": "Momo Pink", "preview": ""}])

    def web_api_set_theme():
        from flask import jsonify
        return jsonify({"success": True})

    def web_api_upload_and_send():
        from flask import request, jsonify
        import app as app_module
        import secrets
        import os
        user = _web_current_user()
        if not user:
            return jsonify({"error": "未登录"}), 401
        if "file" not in request.files:
            return jsonify({"error": "no file"}), 400
        f = request.files["file"]
        if not f or f.filename == "":
            return jsonify({"error": "empty file"}), 400
        ext = os.path.splitext(f.filename)[1].lower()
        filename = secrets.token_hex(16) + ext
        filepath = os.path.join(app_module.MEDIA_DIR, filename)
        f.save(filepath)
        media_url = "/media/" + filename
        thumb_url = media_url
        conv_type = request.form.get("conv_type", "direct")
        target_id = request.form.get("to_id", "")
        if not target_id:
            return jsonify({"error": "no target_id"}), 400
        # body 存入原始文件名，客户端显示用
        original_filename = f.filename or filename
        body = request.form.get("body", "") or original_filename
        # 根据扩展名自动判断 msg_type
        img_exts = {'.jpg', '.jpeg', '.png', '.gif', '.webp', '.bmp', '.svg'}
        vid_exts = {'.mp4', '.webm', '.avi', '.mov', '.mkv'}
        aud_exts = {'.mp3', '.wav', '.ogg', '.aac', '.m4a', '.flac'}
        if ext in img_exts:
            msg_type = "image"
        elif ext in vid_exts:
            msg_type = "video"
        elif ext in aud_exts:
            msg_type = "audio"
        else:
            msg_type = "resource"
        my_id = user["id"]
        my_uid = user["uid"]
        my_name = user["display_name"] or my_uid
        my_avatar = user["avatar_url"] or ""
        now = app_module.now_ts()
        if conv_type == "direct":
            target = app_module.db_query_one("SELECT id, uid, display_name FROM users WHERE uid = ?", (target_id.upper(),))
            if not target:
                return jsonify({"error": "用户不存在"}), 404
            mid = app_module.db_execute(
                "INSERT INTO direct_messages (from_id, to_id, body, msg_type, media_url, thumb_url, created_at, is_read) VALUES (?, ?, ?, ?, ?, ?, ?, 0)",
                (my_id, target["id"], body, msg_type, media_url, thumb_url, now),
            )
            msg_dict = {
                "id": str(mid),
                "thread_id": target["uid"],
                "from_uid": my_uid,
                "to_uid": target["uid"],
                "body": body,
                "msg_type": msg_type,
                "media_url": media_url or "",
                "thumb_url": thumb_url or "",
                "duration_ms": 0,
                "burn_after_seconds": 0,
                "created_at": now,
                "is_read": 0,
                "status": 1,
                "from_name": my_name,
                "from_avatar": my_avatar,
            }
            try:
                app_module.push_to_user(target["id"], {"type": "direct_message", "data": msg_dict})
            except Exception:
                pass
            return jsonify({"message": msg_dict})
        elif conv_type == "group":
            group = app_module.db_query_one("SELECT id, group_id, name FROM groups WHERE group_id = ?", (target_id,))
            if not group:
                return jsonify({"error": "群不存在"}), 404
            gid_str = group["group_id"]
            member = app_module.db_query_one("SELECT 1 FROM group_members WHERE group_id = ? AND user_id = ?", (gid_str, my_id))
            if not member:
                return jsonify({"error": "不是群成员"}), 403
            mid = app_module.db_execute(
                "INSERT INTO group_messages (group_id, from_id, body, msg_type, media_url, thumb_url, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)",
                (gid_str, my_id, body, msg_type, media_url, thumb_url, now),
            )
            msg_dict = {
                "id": str(mid),
                "group_id": group["group_id"],
                "thread_id": group["group_id"],
                "from_uid": my_uid,
                "body": body,
                "msg_type": msg_type,
                "media_url": media_url or "",
                "thumb_url": thumb_url or "",
                "duration_ms": 0,
                "burn_after_seconds": 0,
                "created_at": now,
                "status": 1,
                "from_name": my_name,
                "from_avatar": my_avatar,
            }
            try:
                members = app_module.db_query_all("SELECT user_id FROM group_members WHERE group_id = ? AND user_id != ?", (gid_str, my_id))
                for m in members:
                    app_module.push_to_user(m["user_id"], {"type": "group_message", "data": msg_dict})
            except Exception:
                pass
            return jsonify({"message": msg_dict})
        return jsonify({"error": "未知类型"}), 400

    def web_api_emoticons():
        from flask import jsonify
        return jsonify([])

    def web_api_emoji_plaza():
        from flask import jsonify
        return jsonify({"items": [], "has_more": False})

    # =========================================================================
    #  用户空间页
    # =========================================================================

    _WEB_SPACE_HTML = r"""<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>用户主页 - OldChat for Kivotos</title>
    <link rel="icon" href="https://gwebcdn260523.pages.dev/v1/static/momotalklogo.png" type="image/png">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@7/css/all.min.css">
    <link rel="stylesheet" href="/web/static/style.css">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { background: var(--bg); font-family: 'zyyt','微软雅黑','Microsoft YaHei',sans-serif; height: 100vh; overflow: hidden; display: flex; flex-direction: column; }
        .space-header {
            background: #fa94a6;
            color: #fff;
            padding: 13px 12px;
            display: flex;
            align-items: center;
            font-size: 15px;
            font-weight: 500;
            flex-shrink: 0;
            position: relative;
        }
        .space-header .back-btn {
            position: absolute;
            left: 12px;
            background: none;
            border: none;
            color: #fff;
            font-size: 18px;
            cursor: pointer;
            padding: 4px 8px;
            border-radius: 8px;
        }
        .space-header .header-title { width: 100%; text-align: center; }

        .space-scroll {
            flex: 1;
            overflow-y: auto;
            scrollbar-color: rgba(0,0,0,0.2) transparent;
        }
        .space-scroll::-webkit-scrollbar { width: 6px; }
        .space-scroll::-webkit-scrollbar-track { background: transparent; }
        .space-scroll::-webkit-scrollbar-thumb { background: rgba(0,0,0,0.2); border-radius: 3px; }

        .space-profile {
            background: #fff;
            padding: 28px 20px 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
            border-bottom: 1px solid var(--border);
        }
        .space-avatar {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            object-fit: cover;
            margin-bottom: 12px;
            background: var(--border);
        }
        .space-name {
            font-size: 20px;
            font-weight: 600;
            color: var(--text);
            margin-bottom: 4px;
        }
        .space-uid {
            font-size: 12px;
            color: var(--secondary-text);
            margin-bottom: 4px;
        }
        .space-bio {
            font-size: 13px;
            color: var(--secondary-text);
            margin-bottom: 12px;
            text-align: center;
            max-width: 300px;
        }
        .space-actions {
            display: flex;
            gap: 10px;
        }
        .space-actions button {
            padding: 8px 22px;
            border-radius: 20px;
            border: none;
            font-size: 14px;
            font-family: inherit;
            cursor: pointer;
            transition: all 0.2s;
            font-weight: 500;
        }
        .space-actions .btn-friend {
            background: #fa94a6;
            color: #fff;
        }
        .space-actions .btn-friend:hover { background: #e88294; }
        .space-actions .btn-friend.friendship {
            background: #e0e0e0;
            color: #888;
        }
        .space-actions .btn-msg {
            background: #fff;
            color: #fa94a6;
            border: 1.5px solid #fa94a6;
        }
        .space-actions .btn-msg:hover { background: #fff0f3; }
        .space-actions .btn-back {
            background: #e0e0e0;
            color: #666;
        }
        .space-actions .btn-back:hover { background: #d0d0d0; }

        .space-section-title {
            font-size: 14px;
            font-weight: 600;
            color: var(--text);
            padding: 14px 16px 8px;
        }
        .space-moments {
            padding: 0 16px 20px;
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
            gap: 10px;
            max-width: 960px;
            margin: 0 auto;
        }
        .moment-card {
            background: #fff;
            border-radius: 12px;
            padding: 14px 16px;
            border: 1px solid var(--border);
        }
        .moment-card .moment-time {
            font-size: 11px;
            color: var(--secondary-text);
            margin-bottom: 6px;
        }
        .moment-card .moment-body {
            font-size: 14px;
            color: var(--text);
            line-height: 1.6;
            white-space: pre-wrap;
            word-break: break-word;
        }
        .moment-card .moment-media {
            margin-top: 8px;
        }
        .moment-card .moment-media img {
            width: 100%;
            max-height: 200px;
            object-fit: cover;
            border-radius: 8px;
            cursor: pointer;
        }
        .moment-card .moment-likes {
            font-size: 12px;
            color: var(--secondary-text);
            margin-top: 8px;
        }
        .space-empty {
            text-align: center;
            padding: 40px 20px;
            color: var(--secondary-text);
            font-size: 14px;
            grid-column: 1 / -1;
        }
        .space-join-time {
            font-size: 12px;
            color: var(--secondary-text);
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="space-header">
        <button class="back-btn" onclick="history.back()"><i class="fa-solid fa-chevron-left"></i></button>
        <span class="header-title">用户空间</span>
    </div>
    <div class="space-scroll">
        <div id="profileArea"></div>
        <div class="space-section-title">TA 的动态</div>
        <div class="space-moments" id="momentsArea"></div>
    </div>

    <script>
        (function() {
            const parts = location.pathname.split('/');
            const uid = parts[parts.length - 1];
            if (!uid) { document.getElementById('profileArea').innerHTML = '<div class="space-empty">用户不存在</div>'; return; }

            const defaultAvatar = 'https://gwebcdn260523.pages.dev/v1/static/default-avatar.png';

            function formatTs(ts) {
                if (!ts) return '';
                const d = new Date(ts * 1000);
                const pad = n => (n < 10 ? '0' : '') + n;
                return d.getFullYear() + '-' + pad(d.getMonth()+1) + '-' + pad(d.getDate()) + ' ' + pad(d.getHours()) + ':' + pad(d.getMinutes());
            }

            async function loadProfile() {
                try {
                    const res = await fetch('/web/api/space/' + uid);
                    const data = await res.json();
                    if (data.error) {
                        document.getElementById('profileArea').innerHTML = '<div class="space-empty">' + data.error + '</div>';
                        return;
                    }
                    const u = data.user;
                    const relation = data.relation || 'none';
                    const avatar = u.avatar_url || defaultAvatar;
                    let btnHtml = '';
                    if (relation === 'self') {
                        btnHtml = '';
                    } else if (relation === 'friend') {
                        btnHtml = '<button class="btn-msg" onclick="location.href=\'/web/\'">私信</button>';
                    } else if (relation === 'pending_sent') {
                        btnHtml = '<button class="btn-friend friendship">已发送申请</button>';
                    } else if (relation === 'pending_received') {
                        btnHtml = '<button class="btn-friend" onclick="respondFriend(\'accept\')">接受好友</button><button class="btn-back" onclick="respondFriend(\'reject\')">拒绝</button>';
                    } else {
                        btnHtml = '<button class="btn-friend" onclick="addFriend()">加好友</button><button class="btn-msg" onclick="location.href=\'/web/\'">私信</button>';
                    }
                    document.getElementById('profileArea').innerHTML =
                        '<div class="space-profile">' +
                            '<img class="space-avatar" src="' + avatar + '" onerror="this.src=\'' + defaultAvatar + '\'">' +
                            '<div class="space-name">' + (u.display_name || u.username) + '</div>' +
                            '<div class="space-uid">' + u.uid + '</div>' +
                            (u.bio ? '<div class="space-bio">' + u.bio + '</div>' : '') +
                            '<div class="space-join-time">注册于 ' + formatTs(u.created_at) + '</div>' +
                            '<div class="space-actions">' + btnHtml + '</div>' +
                        '</div>';
                } catch(e) {
                    document.getElementById('profileArea').innerHTML = '<div class="space-empty">加载失败</div>';
                }
            }

            async function loadMoments() {
                try {
                    const res = await fetch('/web/api/space/moments/' + uid);
                    const data = await res.json();
                    const moments = data.moments || [];
                    if (moments.length === 0) {
                        document.getElementById('momentsArea').innerHTML = '<div class="space-empty">暂无动态</div>';
                        return;
                    }
                    let html = '';
                    moments.forEach(m => {
                        let mediaHtml = '';
                        if (m.media_url) {
                            mediaHtml = '<div class="moment-media"><img src="' + m.media_url + '" onerror="this.style.display=\'none\'"></div>';
                        }
                        html += '<div class="moment-card">' +
                            '<div class="moment-time">' + formatTs(m.created_at) + '</div>' +
                            '<div class="moment-body">' + (m.body || '') + '</div>' +
                            mediaHtml +
                            (m.likes > 0 ? '<div class="moment-likes">❤ ' + m.likes + '</div>' : '') +
                            '</div>';
                    });
                    document.getElementById('momentsArea').innerHTML = html;
                } catch(e) {
                    document.getElementById('momentsArea').innerHTML = '<div class="space-empty">加载动态失败</div>';
                }
            }

            window.addFriend = async function() {
                try {
                    const res = await fetch('/web/api/space/add_friend', {
                        method: 'POST',
                        headers: {'Content-Type': 'application/json'},
                        body: JSON.stringify({ to_uid: uid })
                    });
                    const data = await res.json();
                    if (data.error) { alert(data.error); return; }
                    loadProfile();
                } catch(e) { alert('请求失败'); }
            };

            window.respondFriend = async function(action) {
                try {
                    const res = await fetch('/web/api/space/respond_friend', {
                        method: 'POST',
                        headers: {'Content-Type': 'application/json'},
                        body: JSON.stringify({ uid: uid, action: action })
                    });
                    const data = await res.json();
                    if (data.error) { alert(data.error); return; }
                    loadProfile();
                } catch(e) { alert('请求失败'); }
            };

            loadProfile();
            loadMoments();
        })();
    </script>
</body>
</html>
"""

    def web_space(uid):
        return _WEB_SPACE_HTML

    def web_api_space(uid):
        from flask import jsonify
        import app as app_module
        user = _web_current_user()
        if not user:
            return jsonify({"error": "未登录"}), 401
        target = app_module.db_query_one(
            "SELECT id, uid, username, display_name, avatar_url, cover_url, bio, online, last_seen, created_at FROM users WHERE uid = ?",
            (uid.upper(),),
        )
        if not target:
            return jsonify({"error": "用户不存在"}), 404
        my_id = user["id"]
        target_id = target["id"]
        if target_id == my_id:
            relation = "self"
        else:
            row = app_module.db_query_one(
                "SELECT status, initiated_by FROM friendships WHERE (user_id = ? AND friend_id = ?) OR (user_id = ? AND friend_id = ?)",
                (my_id, target_id, target_id, my_id),
            )
            if not row:
                relation = "none"
            elif row["status"] == "accepted":
                relation = "friend"
            elif row["initiated_by"] == my_id:
                relation = "pending_sent"
            else:
                relation = "pending_received"
        return jsonify({
            "user": {
                "uid": target["uid"],
                "username": target["username"],
                "display_name": target["display_name"],
                "avatar_url": target["avatar_url"],
                "cover_url": target["cover_url"],
                "bio": target["bio"],
                "online": bool(target["online"]),
                "last_seen": target["last_seen"],
                "created_at": target["created_at"],
            },
            "relation": relation,
        })

    def web_api_space_moments(uid):
        from flask import jsonify
        import app as app_module
        user = _web_current_user()
        if not user:
            return jsonify({"error": "未登录"}), 401
        target = app_module.db_query_one("SELECT id FROM users WHERE uid = ?", (uid.upper(),))
        if not target:
            return jsonify({"moments": []})
        rows = app_module.db_query_all(
            "SELECT moment_id, body, media_url, thumb_url, created_at, likes FROM moments WHERE user_id = ? ORDER BY created_at DESC LIMIT 20",
            (target["id"],),
        )
        return jsonify({"moments": [
            {"id": m["moment_id"], "body": m["body"], "media_url": m["media_url"], "created_at": m["created_at"], "likes": m["likes"]}
            for m in rows
        ]})

    def web_api_space_add_friend():
        from flask import request, jsonify
        import app as app_module
        user = _web_current_user()
        if not user:
            return jsonify({"error": "未登录"}), 401
        data = request.get_json(silent=True) or {}
        to_uid = (data.get("to_uid") or "").strip().upper()
        if not to_uid:
            return jsonify({"error": "参数错误"}), 400
        target = app_module.db_query_one("SELECT id, uid FROM users WHERE uid = ?", (to_uid,))
        if not target:
            return jsonify({"error": "用户不存在"}), 404
        if target["id"] == user["id"]:
            return jsonify({"error": "不能添加自己"}), 400
        existing = app_module.db_query_one(
            "SELECT id, status FROM friendships WHERE (user_id = ? AND friend_id = ?) OR (user_id = ? AND friend_id = ?)",
            (user["id"], target["id"], target["id"], user["id"]),
        )
        if existing:
            if existing["status"] == "accepted":
                return jsonify({"message": "已经是好友"})
            return jsonify({"message": "已发送过申请"})
        now = app_module.now_ts()
        app_module.db_execute(
            "INSERT INTO friendships (user_id, friend_id, status, initiated_by, created_at) VALUES (?, ?, 'pending', ?, ?)",
            (user["id"], target["id"], user["id"], now),
        )
        try:
            app_module.push_to_user(target["id"], {
                "type": "friend_request",
                "data": {"from_uid": user["uid"], "from_name": user["display_name"] or user["username"]},
            })
        except Exception:
            pass
        return jsonify({"message": "已发送好友申请"})

    def web_api_space_respond_friend():
        from flask import request, jsonify
        import app as app_module
        user = _web_current_user()
        if not user:
            return jsonify({"error": "未登录"}), 401
        data = request.get_json(silent=True) or {}
        target_uid = (data.get("uid") or "").strip().upper()
        action = data.get("action") or "accept"
        if not target_uid:
            return jsonify({"error": "参数错误"}), 400
        target = app_module.db_query_one("SELECT id FROM users WHERE uid = ?", (target_uid,))
        if not target:
            return jsonify({"error": "用户不存在"}), 404
        row = app_module.db_query_one(
            "SELECT id FROM friendships WHERE user_id = ? AND friend_id = ? AND status = 'pending'",
            (target["id"], user["id"]),
        )
        if not row:
            return jsonify({"error": "无待处理的好友申请"}), 404
        if action == "accept":
            app_module.db_execute("UPDATE friendships SET status = 'accepted' WHERE id = ?", (row["id"],))
        else:
            app_module.db_execute("DELETE FROM friendships WHERE id = ?", (row["id"],))
        return jsonify({"message": "已处理"})

    # =========================================================================
    #  注册路由
    # =========================================================================

    app.add_url_rule("/web/", "web_index", web_index)
    app.add_url_rule("/web/login", "web_login", web_login)
    app.add_url_rule("/web/logout", "web_logout", web_logout)
    app.add_url_rule("/web/static/<path:filename>", "web_static", web_static)
    app.add_url_rule("/web/api/login", "web_api_login", web_api_login, methods=["POST"])
    app.add_url_rule("/web/api/register", "web_api_register", web_api_register, methods=["POST"])
    app.add_url_rule("/web/api/ws_token", "web_api_ws_token", web_api_ws_token)
    app.add_url_rule("/web/api/contacts", "web_api_contacts", web_api_contacts)
    app.add_url_rule("/web/api/messages/<msg_type>/<target_id>", "web_api_messages", web_api_messages)
    app.add_url_rule("/web/api/mark_read/<msg_type>/<target_id>", "web_api_mark_read", web_api_mark_read, methods=["PUT"])
    app.add_url_rule("/web/api/send", "web_api_send", web_api_send, methods=["POST"])
    app.add_url_rule("/web/api/unread", "web_api_unread", web_api_unread)
    app.add_url_rule("/web/api/themes", "web_api_themes", web_api_themes)
    app.add_url_rule("/web/api/set_theme", "web_api_set_theme", web_api_set_theme, methods=["POST"])
    app.add_url_rule("/web/api/upload_and_send", "web_api_upload_and_send", web_api_upload_and_send, methods=["POST"])
    app.add_url_rule("/web/api/emoticons", "web_api_emoticons", web_api_emoticons)
    app.add_url_rule("/web/api/emoji/plaza", "web_api_emoji_plaza", web_api_emoji_plaza)
    app.add_url_rule("/web/space/<uid>", "web_space", web_space)
    app.add_url_rule("/web/api/space/<uid>", "web_api_space", web_api_space)
    app.add_url_rule("/web/api/space/moments/<uid>", "web_api_space_moments", web_api_space_moments)
    app.add_url_rule("/web/api/space/add_friend", "web_api_space_add_friend", web_api_space_add_friend, methods=["POST"])
    app.add_url_rule("/web/api/space/respond_friend", "web_api_space_respond_friend", web_api_space_respond_friend, methods=["POST"])
