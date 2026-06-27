#!/usr/bin/env python3
"""
OldChat Web 界面 - 简化版
前后端通信使用 WebSocket，简单加密或不加密
"""
import os
import sys
import json
import time
import secrets
import hashlib
import base64

# 确保 eventlet monkey_patch 在最前面
import eventlet
eventlet.monkey_patch()

from flask import Flask, request, session, jsonify, redirect, url_for, send_from_directory
from flask_socketio import SocketIO, emit, join_room, leave_room
import sqlite3

# ============================================================================
# 配置
# ============================================================================
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
UPLOADS_DIR = os.path.join(BASE_DIR, "uploads")
STATIC_DIR = os.path.join(BASE_DIR, "static_web")

os.makedirs(UPLOADS_DIR, exist_ok=True)
os.makedirs(STATIC_DIR, exist_ok=True)

app = Flask(__name__, static_folder=STATIC_DIR, static_url_path="/static")
app.secret_key = secrets.token_hex(32)
app.config["UPLOAD_FOLDER"] = UPLOADS_DIR
socketio = SocketIO(app, cors_allowed_origins="*", async_mode="eventlet")

# 共享数据库路径（如果存在 app.py）
DB_PATH = os.path.join(BASE_DIR, "oldchat.db")

# ============================================================================
# 工具函数
# ============================================================================
def get_db():
    if os.path.exists(DB_PATH):
        return sqlite3.connect(DB_PATH, check_same_thread=False)
    return None

def dict_from_row(row, cols):
    if row is None:
        return None
    return dict(zip(cols, row))

def now_ts():
    return int(time.time())

def ws_online_user_ids():
    """获取当前在线的用户ID列表"""
    online = []
    for sid, data in socketio.server.manager.rooms.items():
        if sid and sid.startswith("/"):
            continue
        for room in data:
            if room and room.startswith("user_"):
                uid = room.replace("user_", "")
                if uid not in online:
                    online.append(uid)
    return online

# ============================================================================
# HTML 模板（简化版）
# ============================================================================
LOGIN_HTML = """<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OldChat Web</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body {
            font-family: 'Segoe UI', Roboto, 'Helvetica Neue', sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .login-card {
            background: white;
            padding: 2.5rem;
            border-radius: 20px;
            box-shadow: 0 20px 60px rgba(0,0,0,0.3);
            width: 100%;
            max-width: 400px;
            margin: 20px;
        }
        .login-card h1 {
            text-align: center;
            color: #333;
            margin-bottom: 0.5rem;
            font-size: 1.8rem;
        }
        .subtitle {
            text-align: center;
            color: #888;
            margin-bottom: 2rem;
            font-size: 0.9rem;
        }
        .form-group {
            margin-bottom: 1.2rem;
        }
        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            color: #555;
            font-size: 0.9rem;
            font-weight: 500;
        }
        .form-group input {
            width: 100%;
            padding: 14px 16px;
            border: 2px solid #e0e0e0;
            border-radius: 12px;
            font-size: 1rem;
            transition: border-color 0.2s;
            outline: none;
        }
        .form-group input:focus {
            border-color: #667eea;
        }
        .btn-login {
            width: 100%;
            padding: 14px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            border-radius: 12px;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            transition: transform 0.1s, box-shadow 0.2s;
        }
        .btn-login:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(102, 126, 234, 0.4);
        }
        .btn-login:active {
            transform: translateY(0);
        }
        .btn-login:disabled {
            opacity: 0.7;
            cursor: not-allowed;
        }
        .error {
            background: #fee;
            color: #c33;
            padding: 12px 16px;
            border-radius: 12px;
            margin-bottom: 1rem;
            font-size: 0.9rem;
            border-left: 4px solid #c33;
        }
        .success {
            background: #efe;
            color: #3c3;
            padding: 12px 16px;
            border-radius: 12px;
            margin-bottom: 1rem;
            font-size: 0.9rem;
            border-left: 4px solid #3c3;
        }
        .tabs {
            display: flex;
            gap: 10px;
            margin-bottom: 1.5rem;
        }
        .tab {
            flex: 1;
            padding: 12px;
            text-align: center;
            background: #f5f5f5;
            border-radius: 10px;
            cursor: pointer;
            transition: all 0.2s;
            font-weight: 500;
            color: #666;
        }
        .tab.active {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        .tab-content {
            display: none;
        }
        .tab-content.active {
            display: block;
        }
        .toggle-password {
            position: absolute;
            right: 12px;
            top: 50%;
            transform: translateY(-50%);
            cursor: pointer;
            color: #999;
        }
        .input-wrapper {
            position: relative;
        }
    </style>
</head>
<body>
    <div class="login-card">
        <h1>OldChat Web</h1>
        <p class="subtitle">登录或注册账号</p>
        
        <div id="message"></div>
        
        <div class="tabs">
            <div class="tab active" data-tab="login">登录</div>
            <div class="tab" data-tab="register">注册</div>
        </div>
        
        <!-- 登录表单 -->
        <div class="tab-content active" id="login-form">
            <div class="form-group">
                <label>账号 / UID</label>
                <input type="text" id="login-identifier" placeholder="输入账号或UID" autocomplete="username">
            </div>
            <div class="form-group">
                <label>密码</label>
                <div class="input-wrapper">
                    <input type="password" id="login-password" placeholder="输入密码" autocomplete="current-password">
                </div>
            </div>
            <button class="btn-login" id="btn-login">登录</button>
        </div>
        
        <!-- 注册表单 -->
        <div class="tab-content" id="register-form">
            <div class="form-group">
                <label>用户名</label>
                <input type="text" id="reg-username" placeholder="设置用户名" autocomplete="username">
            </div>
            <div class="form-group">
                <label>显示名称</label>
                <input type="text" id="reg-display-name" placeholder="设置显示名称" autocomplete="name">
            </div>
            <div class="form-group">
                <label>密码</label>
                <input type="password" id="reg-password" placeholder="设置密码" autocomplete="new-password">
            </div>
            <button class="btn-login" id="btn-register">注册</button>
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

        const messageEl = document.getElementById('message');
        function showMessage(msg, type = 'error') {
            messageEl.innerHTML = `<div class="${type}">${msg}</div>`;
            setTimeout(() => messageEl.innerHTML = '', 3000);
        }

        // 登录
        document.getElementById('btn-login').addEventListener('click', async () => {
            const identifier = document.getElementById('login-identifier').value.trim();
            const password = document.getElementById('login-password').value;
            
            if (!identifier || !password) {
                showMessage('请填写所有字段');
                return;
            }
            
            const btn = document.getElementById('btn-login');
            btn.disabled = true;
            btn.textContent = '登录中...';
            
            try {
                const resp = await fetch('/web/api/login', {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({identifier, password})
                });
                const data = await resp.json();
                
                if (data.success) {
                    showMessage('登录成功！正在跳转...', 'success');
                    localStorage.setItem('web_token', data.token);
                    localStorage.setItem('web_uid', data.uid);
                    localStorage.setItem('web_name', data.name);
                    setTimeout(() => window.location.href = '/web/', 500);
                } else {
                    showMessage(data.error || '登录失败');
                }
            } catch (e) {
                showMessage('网络错误');
            }
            
            btn.disabled = false;
            btn.textContent = '登录';
        });

        // 注册
        document.getElementById('btn-register').addEventListener('click', async () => {
            const username = document.getElementById('reg-username').value.trim();
            const display_name = document.getElementById('reg-display-name').value.trim();
            const password = document.getElementById('reg-password').value;
            
            if (!username || !password) {
                showMessage('请填写所有字段');
                return;
            }
            
            const btn = document.getElementById('btn-register');
            btn.disabled = true;
            btn.textContent = '注册中...';
            
            try {
                const resp = await fetch('/web/api/register', {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({username, display_name, password})
                });
                const data = await resp.json();
                
                if (data.success) {
                    showMessage('注册成功！请登录', 'success');
                    document.querySelector('.tab[data-tab="login"]').click();
                } else {
                    showMessage(data.error || '注册失败');
                }
            } catch (e) {
                showMessage('网络错误');
            }
            
            btn.disabled = false;
            btn.textContent = '注册';
        });

        // 自动填充记住的账号
        const savedId = localStorage.getItem('web_uid');
        if (savedId) {
            document.getElementById('login-identifier').value = savedId;
        }
    </script>
</body>
</html>
"""

INDEX_HTML = """<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OldChat Web</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@7/css/all.min.css">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        :root {
            --primary: #667eea;
            --bg: #f0f2f5;
            --sidebar-bg: #ffffff;
            --chat-bg: #f8f9fa;
            --msg-own: #667eea;
            --msg-other: #ffffff;
            --text: #333;
            --text-muted: #888;
            --border: #e0e0e0;
        }
        html, body {
            height: 100%;
            font-family: 'Segoe UI', Roboto, 'Helvetica Neue', sans-serif;
            background: var(--bg);
        }
        .app-container {
            display: flex;
            height: 100vh;
        }
        /* 侧边栏 */
        .sidebar {
            width: 300px;
            background: var(--sidebar-bg);
            border-right: 1px solid var(--border);
            display: flex;
            flex-direction: column;
        }
        .sidebar-header {
            padding: 16px;
            border-bottom: 1px solid var(--border);
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .user-info {
            flex: 1;
        }
        .user-info strong {
            font-size: 1rem;
            color: var(--text);
        }
        .user-info small {
            color: var(--text-muted);
            font-size: 0.8rem;
        }
        .icon-btn {
            width: 36px;
            height: 36px;
            border: none;
            background: transparent;
            border-radius: 50%;
            cursor: pointer;
            color: var(--text-muted);
            transition: all 0.2s;
        }
        .icon-btn:hover {
            background: var(--bg);
            color: var(--primary);
        }
        .search-box {
            padding: 12px 16px;
            border-bottom: 1px solid var(--border);
        }
        .search-box input {
            width: 100%;
            padding: 10px 14px;
            border: 1px solid var(--border);
            border-radius: 20px;
            outline: none;
            font-size: 0.9rem;
        }
        .search-box input:focus {
            border-color: var(--primary);
        }
        .contact-list {
            flex: 1;
            overflow-y: auto;
        }
        .contact-item {
            display: flex;
            align-items: center;
            padding: 12px 16px;
            cursor: pointer;
            transition: background 0.2s;
            border-bottom: 1px solid #f0f0f0;
        }
        .contact-item:hover {
            background: var(--bg);
        }
        .contact-item.active {
            background: #e8ecff;
        }
        .contact-item.unread::before {
            content: '';
            width: 10px;
            height: 10px;
            background: #f44336;
            border-radius: 50%;
            margin-right: 8px;
            flex-shrink: 0;
        }
        .contact-avatar {
            width: 44px;
            height: 44px;
            border-radius: 50%;
            background: linear-gradient(135deg, #667eea, #764ba2);
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-weight: 600;
            font-size: 1.1rem;
            margin-right: 12px;
            flex-shrink: 0;
            overflow: hidden;
        }
        .contact-avatar img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
        .contact-info {
            flex: 1;
            min-width: 0;
        }
        .contact-name {
            font-weight: 500;
            color: var(--text);
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .contact-preview {
            font-size: 0.85rem;
            color: var(--text-muted);
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .contact-time {
            font-size: 0.75rem;
            color: var(--text-muted);
            flex-shrink: 0;
        }
        /* 主聊天区 */
        .chat-area {
            flex: 1;
            display: flex;
            flex-direction: column;
            background: var(--chat-bg);
        }
        .chat-header {
            padding: 16px 20px;
            background: white;
            border-bottom: 1px solid var(--border);
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .chat-header-title {
            font-weight: 600;
            font-size: 1.1rem;
        }
        .chat-messages {
            flex: 1;
            overflow-y: auto;
            padding: 20px;
            display: flex;
            flex-direction: column;
            gap: 12px;
        }
        .message {
            max-width: 70%;
            padding: 12px 16px;
            border-radius: 18px;
            position: relative;
            word-break: break-word;
        }
        .message.other {
            background: var(--msg-other);
            align-self: flex-start;
            border-bottom-left-radius: 4px;
            box-shadow: 0 1px 2px rgba(0,0,0,0.1);
        }
        .message.own {
            background: var(--msg-own);
            color: white;
            align-self: flex-end;
            border-bottom-right-radius: 4px;
        }
        .message-sender {
            font-size: 0.75rem;
            color: var(--primary);
            margin-bottom: 4px;
            font-weight: 500;
        }
        .message-time {
            font-size: 0.65rem;
            color: var(--text-muted);
            margin-top: 4px;
            text-align: right;
        }
        .message.own .message-time {
            color: rgba(255,255,255,0.7);
        }
        .chat-input-area {
            padding: 16px 20px;
            background: white;
            border-top: 1px solid var(--border);
        }
        .input-wrapper {
            display: flex;
            gap: 12px;
            align-items: flex-end;
        }
        .chat-input {
            flex: 1;
            padding: 12px 16px;
            border: 1px solid var(--border);
            border-radius: 24px;
            outline: none;
            font-size: 0.95rem;
            resize: none;
            max-height: 120px;
            font-family: inherit;
        }
        .chat-input:focus {
            border-color: var(--primary);
        }
        .btn-send {
            width: 48px;
            height: 48px;
            border: none;
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: white;
            border-radius: 50%;
            cursor: pointer;
            font-size: 1.2rem;
            transition: transform 0.1s;
        }
        .btn-send:hover {
            transform: scale(1.05);
        }
        .btn-send:active {
            transform: scale(0.95);
        }
        .empty-chat {
            flex: 1;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            color: var(--text-muted);
        }
        .empty-chat i {
            font-size: 4rem;
            margin-bottom: 16px;
            opacity: 0.3;
        }
        .empty-chat p {
            font-size: 1.1rem;
        }
        /* 登录提示 */
        .login-prompt {
            position: fixed;
            inset: 0;
            background: rgba(0,0,0,0.5);
            display: flex;
            align-items: center;
            justify-content: center;
            z-index: 1000;
        }
        .login-prompt-card {
            background: white;
            padding: 2rem;
            border-radius: 16px;
            text-align: center;
            max-width: 300px;
        }
        .login-prompt-card h3 {
            margin-bottom: 1rem;
        }
        .login-prompt-card button {
            padding: 10px 24px;
            background: var(--primary);
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 1rem;
        }
        /* 在线状态 */
        .online-dot {
            width: 8px;
            height: 8px;
            background: #4caf50;
            border-radius: 50%;
            display: inline-block;
            margin-left: 6px;
        }
        .offline-dot {
            width: 8px;
            height: 8px;
            background: #ccc;
            border-radius: 50%;
            display: inline-block;
            margin-left: 6px;
        }
    </style>
</head>
<body>
    <div id="login-prompt" style="display: none;">
        <div class="login-prompt-card">
            <h3>请先登录</h3>
            <p style="margin-bottom: 1rem; color: #666;">您需要登录才能使用聊天功能</p>
            <button onclick="window.location.href='/web/login'">去登录</button>
        </div>
    </div>

    <div class="app-container">
        <aside class="sidebar">
            <div class="sidebar-header">
                <div class="user-info">
                    <strong id="my-name">加载中...</strong>
                    <small>OldChat Web</small>
                </div>
                <button class="icon-btn" id="btn-logout" title="退出">
                    <i class="fas fa-sign-out-alt"></i>
                </button>
            </div>
            <div class="search-box">
                <input type="text" placeholder="搜索好友或群聊..." id="search-input">
            </div>
            <div class="contact-list" id="contact-list">
                <div style="padding: 20px; text-align: center; color: #888;">
                    加载中...
                </div>
            </div>
        </aside>

        <main class="chat-area">
            <div class="chat-header">
                <span class="chat-header-title" id="chat-title">OldChat Web</span>
                <div style="display: flex; gap: 8px;">
                    <button class="icon-btn" title="刷新" id="btn-refresh">
                        <i class="fas fa-sync-alt"></i>
                    </button>
                </div>
            </div>
            
            <div id="empty-state" class="empty-chat">
                <i class="fas fa-comments"></i>
                <p>选择一个聊天开始</p>
            </div>
            
            <div class="chat-messages" id="chat-messages" style="display: none;"></div>
            
            <div class="chat-input-area">
                <div class="input-wrapper">
                    <textarea class="chat-input" id="message-input" placeholder="输入消息..." rows="1"></textarea>
                    <button class="btn-send" id="btn-send">
                        <i class="fas fa-paper-plane"></i>
                    </button>
                </div>
            </div>
        </main>
    </div>

    <script>
        // 全局状态
        const state = {
            token: localStorage.getItem('web_token'),
            uid: localStorage.getItem('web_uid'),
            name: localStorage.getItem('web_name'),
            ws: null,
            currentChat: null,
            contacts: [],
            messages: {}
        };

        // 检查登录
        function checkLogin() {
            if (!state.token) {
                document.getElementById('login-prompt').style.display = 'flex';
                return false;
            }
            document.getElementById('my-name').textContent = state.name || state.uid;
            return true;
        }

        // 连接 WebSocket
        function connectWS() {
            const protocol = location.protocol === 'https:' ? 'wss:' : 'ws:';
            const wsUrl = `${protocol}//${location.host}/web/ws`;
            
            state.ws = new WebSocket(wsUrl);
            
            state.ws.onopen = () => {
                console.log('WebSocket connected');
                state.ws.send(JSON.stringify({
                    type: 'auth',
                    token: state.token
                }));
            };
            
            state.ws.onmessage = (e) => {
                try {
                    const data = JSON.parse(e.data);
                    handleWSMessage(data);
                } catch (err) {
                    console.error('WS message error:', err);
                }
            };
            
            state.ws.onclose = () => {
                console.log('WebSocket closed, reconnecting...');
                setTimeout(connectWS, 3000);
            };
            
            state.ws.onerror = (err) => {
                console.error('WebSocket error:', err);
            };
        }

        // 处理 WebSocket 消息
        function handleWSMessage(data) {
            switch (data.type) {
                case 'auth_ok':
                    loadContacts();
                    break;
                case 'contacts':
                    renderContacts(data.contacts);
                    break;
                case 'messages':
                    renderMessages(data);
                    break;
                case 'new_message':
                    addMessage(data);
                    break;
                case 'online_status':
                    updateOnlineStatus(data);
                    break;
                case 'error':
                    alert(data.message || '发生错误');
                    break;
            }
        }

        // 加载联系人列表
        function loadContacts() {
            if (state.ws && state.ws.readyState === WebSocket.OPEN) {
                state.ws.send(JSON.stringify({type: 'get_contacts'}));
            }
        }

        // 渲染联系人列表
        function renderContacts(contacts) {
            state.contacts = contacts;
            const list = document.getElementById('contact-list');
            
            if (!contacts || contacts.length === 0) {
                list.innerHTML = '<div style="padding: 20px; text-align: center; color: #888;">暂无好友，开始聊天吧！</div>';
                return;
            }
            
            list.innerHTML = contacts.map(c => `
                <div class="contact-item" data-id="${c.uid}" data-type="${c.type}">
                    <div class="contact-avatar">
                        ${c.avatar ? `<img src="${c.avatar}" alt="">` : c.name.charAt(0).toUpperCase()}
                    </div>
                    <div class="contact-info">
                        <div class="contact-name">
                            ${c.name}
                            ${c.online ? '<span class="online-dot"></span>' : '<span class="offline-dot"></span>'}
                        </div>
                        <div class="contact-preview">${c.last_message || ''}</div>
                    </div>
                    ${c.unread ? `<div class="contact-time" style="background: #f44336; color: white; padding: 2px 6px; border-radius: 10px; font-size: 0.7rem;">${c.unread}</div>` : ''}
                </div>
            `).join('');
            
            // 绑定点击事件
            list.querySelectorAll('.contact-item').forEach(item => {
                item.addEventListener('click', () => selectChat(item));
            });
        }

        // 选择聊天
        function selectChat(el) {
            document.querySelectorAll('.contact-item').forEach(c => c.classList.remove('active'));
            el.classList.add('active');
            
            const id = el.dataset.id;
            const type = el.dataset.type;
            const contact = state.contacts.find(c => c.uid === id);
            
            state.currentChat = {uid: id, type, name: contact?.name};
            
            document.getElementById('chat-title').textContent = contact?.name || id;
            document.getElementById('empty-state').style.display = 'none';
            document.getElementById('chat-messages').style.display = 'flex';
            
            // 加载消息
            if (state.ws && state.ws.readyState === WebSocket.OPEN) {
                state.ws.send(JSON.stringify({
                    type: 'get_messages',
                    uid: id,
                    type: type
                }));
            }
        }

        // 渲染消息
        function renderMessages(data) {
            const container = document.getElementById('chat-messages');
            state.messages[data.uid] = data.messages || [];
            
            container.innerHTML = (data.messages || []).map(m => `
                <div class="message ${m.from_me ? 'own' : 'other'}">
                    ${!m.from_me ? `<div class="message-sender">${m.sender}</div>` : ''}
                    <div class="message-content">${escapeHtml(m.body)}</div>
                    <div class="message-time">${formatTime(m.time)}</div>
                </div>
            `).join('');
            
            container.scrollTop = container.scrollHeight;
        }

        // 添加新消息
        function addMessage(data) {
            if (!state.messages[data.uid]) {
                state.messages[data.uid] = [];
            }
            state.messages[data.uid].push(data);
            
            if (state.currentChat?.uid === data.uid) {
                const container = document.getElementById('chat-messages');
                const isOwn = data.from_me;
                const html = `
                    <div class="message ${isOwn ? 'own' : 'other'}">
                        ${!isOwn ? `<div class="message-sender">${data.sender}</div>` : ''}
                        <div class="message-content">${escapeHtml(data.body)}</div>
                        <div class="message-time">${formatTime(data.time)}</div>
                    </div>
                `;
                container.insertAdjacentHTML('beforeend', html);
                container.scrollTop = container.scrollHeight;
            }
            
            // 更新联系人列表的最后消息
            loadContacts();
        }

        // 更新在线状态
        function updateOnlineStatus(data) {
            const contact = state.contacts.find(c => c.uid === data.uid);
            if (contact) {
                contact.online = data.online;
                renderContacts(state.contacts);
                if (state.currentChat?.uid === data.uid) {
                    const title = document.getElementById('chat-title');
                    title.innerHTML = state.currentChat.name + (data.online ? '<span class="online-dot"></span>' : '<span class="offline-dot"></span>');
                }
            }
        }

        // 发送消息
        function sendMessage() {
            const input = document.getElementById('message-input');
            const text = input.value.trim();
            
            if (!text || !state.currentChat || !state.ws) return;
            
            state.ws.send(JSON.stringify({
                type: 'send_message',
                uid: state.currentChat.uid,
                body: text,
                chat_type: state.currentChat.type
            }));
            
            input.value = '';
            input.style.height = 'auto';
        }

        // 工具函数
        function escapeHtml(text) {
            const div = document.createElement('div');
            div.textContent = text;
            return div.innerHTML;
        }

        function formatTime(ts) {
            const d = new Date(ts * 1000);
            return d.toLocaleTimeString('zh-CN', {hour: '2-digit', minute: '2-digit'});
        }

        // 事件绑定
        document.getElementById('btn-send').addEventListener('click', sendMessage);
        document.getElementById('message-input').addEventListener('keydown', (e) => {
            if (e.key === 'Enter' && !e.shiftKey) {
                e.preventDefault();
                sendMessage();
            }
        });
        document.getElementById('message-input').addEventListener('input', function() {
            this.style.height = 'auto';
            this.style.height = Math.min(this.scrollHeight, 120) + 'px';
        });
        document.getElementById('btn-logout').addEventListener('click', () => {
            localStorage.clear();
            window.location.href = '/web/login';
        });
        document.getElementById('btn-refresh').addEventListener('click', loadContacts);

        // 初始化
        if (checkLogin()) {
            connectWS();
        }
    </script>
</body>
</html>
"""

# ============================================================================
# Web 路由
# ============================================================================

@app.route('/web/')
def web_index():
    """主页面"""
    if not session.get('web_token'):
        return redirect('/web/login')
    return INDEX_HTML

@app.route('/web/login')
def web_login():
    """登录页面"""
    return LOGIN_HTML

@app.route('/web/api/login', methods=['POST'])
def web_api_login():
    """登录 API"""
    db = get_db()
    if not db:
        return jsonify({'success': False, 'error': '数据库未连接'})
    
    try:
        data = request.get_json()
        identifier = (data.get('identifier') or '').strip()
        password = data.get('password') or ''
        
        if not identifier or not password:
            return jsonify({'success': False, 'error': '请填写所有字段'})
        
        # 查找用户（支持 uid、username、email）
        cursor = db.execute(
            "SELECT id, uid, username, display_name, password FROM users WHERE uid = ? OR username = ? OR email = ?",
            (identifier.upper(), identifier, identifier)
        )
        user = cursor.fetchone()
        
        if not user:
            return jsonify({'success': False, 'error': '用户不存在'})
        
        # 验证密码
        pwd_hash = hashlib.sha256(password.encode()).hexdigest()
        if pwd_hash != user[4]:
            return jsonify({'success': False, 'error': '密码错误'})
        
        # 生成 token
        token = secrets.token_hex(32)
        now = now_ts()
        
        # 存储 session
        session['web_token'] = token
        session['web_uid'] = user[2]  # uid
        session['web_user_id'] = user[0]  # 数据库 id
        
        return jsonify({
            'success': True,
            'token': token,
            'uid': user[2],
            'name': user[3] or user[2]
        })
        
    except Exception as e:
        return jsonify({'success': False, 'error': str(e)})
    finally:
        db.close()

@app.route('/web/api/register', methods=['POST'])
def web_api_register():
    """注册 API"""
    db = get_db()
    if not db:
        return jsonify({'success': False, 'error': '数据库未连接'})
    
    try:
        data = request.get_json()
        username = (data.get('username') or '').strip()
        display_name = (data.get('display_name') or '').strip() or username
        password = data.get('password') or ''
        
        if not username or not password:
            return jsonify({'success': False, 'error': '请填写所有字段'})
        
        if len(username) < 3 or len(username) > 32:
            return jsonify({'success': False, 'error': '用户名长度需在 3-32 位'})
        
        if len(password) < 6:
            return jsonify({'success': False, 'error': '密码长度至少 6 位'})
        
        # 检查用户名是否已存在
        cursor = db.execute("SELECT id FROM users WHERE username = ?", (username,))
        if cursor.fetchone():
            return jsonify({'success': False, 'error': '用户名已存在'})
        
        # 生成 UID
        import string
        uid = "WEB" + ''.join(secrets.choice(string.ascii_uppercase + string.digits) for _ in range(6))
        
        # 检查 UID 唯一性
        while db.execute("SELECT id FROM users WHERE uid = ?", (uid,)).fetchone():
            uid = "WEB" + ''.join(secrets.choice(string.ascii_uppercase + string.digits) for _ in range(6))
        
        # 创建用户
        pwd_hash = hashlib.sha256(password.encode()).hexdigest()
        now = now_ts()
        
        db.execute(
            "INSERT INTO users (uid, username, display_name, password, online, created_at, last_seen) VALUES (?, ?, ?, ?, 1, ?, ?)",
            (uid, username, display_name, pwd_hash, now, now)
        )
        db.commit()
        
        return jsonify({'success': True})
        
    except Exception as e:
        return jsonify({'success': False, 'error': str(e)})
    finally:
        db.close()

# ============================================================================
# WebSocket 事件
# ============================================================================

web_sessions = {}  # token -> {sid, uid, user_id}

@socketio.on('connect')
def on_connect():
    print(f'Web client connected: {request.sid}')

@socketio.on('disconnect')
def on_disconnect():
    # 移除 session
    for token, data in list(web_sessions.items()):
        if data['sid'] == request.sid:
            del web_sessions[token]
            break

@socketio.on('message')
def on_message(data):
    """处理 WebSocket 消息"""
    msg_type = data.get('type')
    
    if msg_type == 'auth':
        # 认证
        token = data.get('token')
        db = get_db()
        
        if not db:
            emit('message', {'type': 'error', 'message': '数据库未连接'})
            return
        
        try:
            # 简单验证：检查用户是否存在
            # 实际项目中应该用 token 验证，这里简化处理
            cursor = db.execute(
                "SELECT id, uid, username, display_name FROM users WHERE uid = ?",
                (session.get('web_uid', ''),)
            )
            user = cursor.fetchone()
            
            if user:
                web_sessions[token] = {
                    'sid': request.sid,
                    'uid': user[1],
                    'user_id': user[0],
                    'username': user[2],
                    'display_name': user[3]
                }
                emit('message', {
                    'type': 'auth_ok',
                    'uid': user[1],
                    'name': user[3] or user[2]
                })
            else:
                emit('message', {'type': 'error', 'message': '认证失败'})
        finally:
            db.close()
    
    elif msg_type == 'get_contacts':
        # 获取联系人列表
        db = get_db()
        if not db:
            return
        
        try:
            my_id = session.get('web_user_id')
            if not my_id:
                # 从 web_sessions 查找
                for token, data in web_sessions.items():
                    if data['sid'] == request.sid:
                        my_id = data['user_id']
                        break
            
            if not my_id:
                emit('message', {'type': 'contacts', 'contacts': []})
                return
            
            # 获取好友列表
            contacts = []
            cursor = db.execute("""
                SELECT u.uid, u.username, u.display_name, u.avatar_url, u.online
                FROM users u
                WHERE u.id IN (
                    SELECT CASE WHEN f.user_id = ? THEN f.friend_id ELSE f.user_id END
                    FROM friendships f
                    WHERE f.status = 'accepted' AND (f.user_id = ? OR f.friend_id = ?)
                )
            """, (my_id, my_id, my_id))
            
            for row in cursor.fetchall():
                contacts.append({
                    'uid': row[0],
                    'name': row[2] or row[1],
                    'avatar': row[3] or '',
                    'online': bool(row[4]),
                    'type': 'direct',
                    'last_message': '',
                    'unread': 0
                })
            
            emit('message', {'type': 'contacts', 'contacts': contacts})
        finally:
            db.close()
    
    elif msg_type == 'get_messages':
        # 获取消息
        db = get_db()
        if not db:
            return
        
        try:
            my_id = session.get('web_user_id')
            target_uid = data.get('uid')
            
            if not my_id or not target_uid:
                emit('message', {'type': 'messages', 'uid': target_uid, 'messages': []})
                return
            
            # 查找目标用户 ID
            cursor = db.execute("SELECT id FROM users WHERE uid = ?", (target_uid,))
            row = cursor.fetchone()
            if not row:
                emit('message', {'type': 'messages', 'uid': target_uid, 'messages': []})
                return
            
            target_id = row[0]
            
            # 获取私聊消息
            cursor = db.execute("""
                SELECT dm.id, dm.from_id, dm.to_id, dm.body, dm.created_at,
                       CASE WHEN dm.from_id = ? THEN 1 ELSE 0 END as is_me
                FROM direct_messages dm
                WHERE (dm.from_id = ? AND dm.to_id = ?) OR (dm.from_id = ? AND dm.to_id = ?)
                ORDER BY dm.created_at ASC
                LIMIT 100
            """, (my_id, my_id, target_id, target_id, my_id))
            
            messages = []
            for row in cursor.fetchall():
                from_id = row[1]
                # 获取发送者信息
                sender_row = db.execute("SELECT uid, display_name FROM users WHERE id = ?", (from_id,)).fetchone()
                sender_name = sender_row[1] or sender_row[0] if sender_row else '未知'
                sender_uid = sender_row[0] if sender_row else ''
                
                messages.append({
                    'id': row[0],
                    'from_me': row[5] == 1,
                    'sender': sender_name,
                    'sender_uid': sender_uid,
                    'body': row[3],
                    'time': row[4]
                })
            
            emit('message', {'type': 'messages', 'uid': target_uid, 'messages': messages})
        finally:
            db.close()
    
    elif msg_type == 'send_message':
        # 发送消息
        db = get_db()
        if not db:
            emit('message', {'type': 'error', 'message': '数据库未连接'})
            return
        
        try:
            my_id = session.get('web_user_id')
            target_uid = data.get('uid')
            body = data.get('body', '').strip()
            chat_type = data.get('chat_type', 'direct')
            
            if not my_id or not target_uid or not body:
                emit('message', {'type': 'error', 'message': '参数错误'})
                return
            
            # 查找目标用户
            cursor = db.execute("SELECT id FROM users WHERE uid = ?", (target_uid,))
            row = cursor.fetchone()
            if not row:
                emit('message', {'type': 'error', 'message': '用户不存在'})
                return
            
            target_id = row[0]
            now = now_ts()
            
            # 插入消息
            cursor = db.execute(
                "INSERT INTO direct_messages (from_id, to_id, body, created_at, is_read) VALUES (?, ?, ?, ?, 0)",
                (my_id, target_id, body, now)
            )
            db.commit()
            
            # 获取发送者信息
            sender_row = db.execute("SELECT uid, display_name FROM users WHERE id = ?", (my_id,)).fetchone()
            
            # 广播给相关用户（简化：只发回给发送者）
            emit('message', {
                'type': 'new_message',
                'uid': target_uid,
                'id': cursor.lastrowid,
                'from_me': True,
                'sender': sender_row[1] or sender_row[0] if sender_row else '我',
                'sender_uid': sender_row[0] if sender_row else '',
                'body': body,
                'time': now
            })
        finally:
            db.close()

# ============================================================================
# 静态文件
# ============================================================================

@app.route('/uploads/<path:filename>')
def serve_upload(filename):
    """提供上传文件"""
    return send_from_directory(UPLOADS_DIR, filename)

# ============================================================================
# 启动
# ============================================================================

if __name__ == '__main__':
    port = 5000
    print(f"OldChat Web 启动中...")
    print(f"访问 http://localhost:{port}/web/")
    socketio.run(app, host='0.0.0.0', port=port, debug=True)
