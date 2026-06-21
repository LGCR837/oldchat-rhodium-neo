# OldChat for Kivotos（修改版）

**OldChat 基沃托斯特供版 (俗称: 桃信旧聊)**

> *"吾等所盼乃七之哀叹，吾等所忆乃杰里科的古法。"*

一个基于 **Python + Flask + Waitress** 的第三方 [OldChat](https://oldchat.online/) 网页版客户端，专门为《蔚蓝档案》的基沃托斯的 Sensei 量身定制，主要模仿了 MomoTalk 的界面风格。

**原作者官网**：[https://oldchatkivotos.l2.ink/](https://oldchatkivotos.l2.ink/)

---

## 修改版说明

本仓库为 OldChat for Kivotos 的修改版，由 **舟山小黄鱼 (ock-fix)** 基于原项目修改。

### 主要修改内容

- ✅ **更换 API 服务器**：更换为官方提供的旧服务器地址
- 📱 **手机端适配**：添加响应式布局，支持手机端访问
- 🎨 **界面优化**：优化移动端界面和用户体验
- 📄 **关于页面更新**：添加作者信息、修改说明和许可证信息

### 相关链接

- 🔗 **修改版仓库**：https://codeberg.org/ock-fix/oldchat-kivotos-fix
- 🔗 **原版项目**：https://codeberg.org/lgcr837/oldchat-kivotos

---

## 功能特色

- 基本兼容 OldChat
- 拥有美观且具有特色的界面
- 支持手机端访问（响应式布局）

---

## 快速开始

### 前置要求

- Python 3.9+
- pip

### 安装依赖

```bash
pip install -r requirements.txt
```

### 运行

```bash
python app.py
```

启动后访问 **http://localhost:5000**

---

## 重要提醒

⚠️ **部署前必读**

1. **API 服务器地址**：已配置为 `http://43.155.218.45:8080`
2. **HTTP 代理问题**：如果你的环境有 HTTP 代理，启动时必须加 `NO_PROXY` 环境变量，否则会出现 502 错误
   ```bash
   NO_PROXY="43.155.218.45,localhost,127.0.0.1" no_proxy="43.155.218.45,localhost,127.0.0.1" python app.py
   ```
3. **默认端口**：5000，可在 `app.py` 中修改

---

## 在豆包中运行

### 启动应用

```bash
# 进入项目目录
cd oldchat-kivotos

# 安装依赖（首次运行）
pip install -r requirements.txt

# 启动应用（必须带 NO_PROXY，否则会 502 错误）
NO_PROXY="43.155.218.45,localhost,127.0.0.1" no_proxy="43.155.218.45,localhost,127.0.0.1" nohup python app.py > /tmp/oldchat-kivotos.log 2>&1 &
```

### 使用 pinggy 公网隧道

pinggy 是速度最快的免费隧道，适合临时公网访问。

**推荐方式（tmux，最稳定）：**

```bash
# 创建 tmux 会话并启动
tmux new-session -d -s pinggy
tmux send-keys -t pinggy 'ssh -T -p 443 -o StrictHostKeyChecking=no -o ServerAliveInterval=30 -R 0:localhost:5000 a.pinggy.io 2>&1 | tee /tmp/pinggy_out.log' C-m

# 查看公网地址
sleep 5
cat /tmp/pinggy_out.log | grep -E "https://.*\.free\.pinggy\.net"

# 查看 tmux 会话
tmux list-sessions

# 进入 tmux 会话
tmux attach -t pinggy
# 退出但不关闭：按 Ctrl+B 然后按 D
```

**其他方式（可能不稳定）：**

```bash
# 后台启动（可能会失败，进程容易消失）
ssh -T -p 443 -o StrictHostKeyChecking=no -o ServerAliveInterval=30 -R 0:localhost:5000 a.pinggy.io > /tmp/pinggy_out.log 2>&1 &

# 查看公网地址
cat /tmp/pinggy_out.log | grep -E "https://.*\.free\.pinggy\.net"
```

**注意**：
- pinggy 免费版 60 分钟后过期，需要重启
- 隧道地址会变化，每次启动都要查看新地址
- **推荐使用 tmux 方式启动，最稳定**
- 后台启动方式（&）可能会失败，进程容易莫名其妙消失

### 其他隧道方案

| 隧道服务 | 速度 | 稳定性 | 有效期 | 备注 |
|---------|------|--------|--------|------|
| pinggy.io | ⭐⭐⭐⭐⭐ | 一般 | 60分钟 | 速度最快，推荐 |
| localhost.run | ⭐⭐⭐ | 较差 | 长期 | 容易 503 |
| serveo.net | ⭐⭐ | 较差 | 长期 | 公网访问可能失败 |

---

## 发给豆包的提示词

### 🏁 全面启动（新对话首选）

**适用于新对话或环境重置后，一次性启动应用和隧道**

```
帮我启动 oldchat-kivotos 项目，按以下步骤执行：

【项目信息】
- 项目路径：/home/user/.super_doubao/super-doubao-runtime/workspace/oldchat-kivotos
- 仓库地址：https://codeberg.org/ock-fix/oldchat-kivotos-fix
- 技术栈：Python + Flask + Waitress
- 默认端口：5000
- API 服务器：http://43.155.218.45:8080

【重要提醒】
- ✅ API 服务器地址已配置为 http://43.155.218.45:8080，无需修改
- 启动应用时必须带 NO_PROXY 环境变量，否则会 502 错误
- NO_PROXY="43.155.218.45,localhost,127.0.0.1"
- 如果 waitress 模块缺失，先安装：pip install waitress

【执行步骤】
1. 检查项目目录是否存在，如果不存在就 git clone
2. 检查 waitress 是否已安装，没有就安装
3. 杀掉所有旧的 python3 app.py 进程（避免重复进程）
4. 用 NO_PROXY 启动应用，后台运行，日志输出到 /tmp/oldchat-kivotos.log
5. 等待 2 秒，检查应用是否启动成功（curl localhost:5000/login 看是否返回 200）
6. 杀掉旧的 pinggy 进程和 tmux 会话
7. 用 tmux 启动 pinggy 隧道（最稳定）：
   tmux new-session -d -s pinggy
   tmux send-keys -t pinggy 'ssh -T -p 443 -o StrictHostKeyChecking=no -o ServerAliveInterval=30 -R 0:localhost:5000 a.pinggy.io 2>&1 | tee /tmp/pinggy_out.log' C-m
8. 等待 5 秒，从 /tmp/pinggy_out.log 中提取公网地址

【验证步骤】
- 应用：ps aux | grep "python3 app.py" 确认有进程
- 应用：curl -s -o /dev/null -w "%{http_code}" http://localhost:5000/login 应该返回 200
- 隧道：ps aux | grep "a.pinggy.io" 确认有进程
- 隧道：cat /tmp/pinggy_out.log | grep "https://" 能看到公网地址

最后告诉我：
- 应用是否启动成功
- pinggy 公网地址（推荐用 .free.pinggy.net 的那个）
- 如果有问题，告诉我错误信息
```

---

### 🔄 重启应用

```
帮我重启 oldchat-kivotos 应用：

1. 杀掉所有 python3 app.py 进程：pkill -f "python3 app.py"
2. 等待 1 秒
3. 进入目录：cd /home/user/.super_doubao/super-doubao-runtime/workspace/oldchat-kivotos
4. 用 NO_PROXY 启动：
   NO_PROXY="43.155.218.45,localhost,127.0.0.1" no_proxy="43.155.218.45,localhost,127.0.0.1" nohup python app.py > /tmp/oldchat-kivotos.log 2>&1 &
5. 等待 2 秒
6. 检查是否启动成功：curl -s -o /dev/null -w "%{http_code}" http://localhost:5000/login

告诉我是否重启成功，以及 HTTP 状态码。
```

---

### 🌐 重启 pinggy 隧道

```
帮我重启 pinggy 隧道：

1. 杀掉所有 pinggy 相关进程：pkill -f "a.pinggy.io"
2. 杀掉旧的 tmux 会话：tmux kill-session -t pinggy 2>/dev/null
3. 等待 1 秒
4. 用 tmux 启动隧道（最稳定）：
   tmux new-session -d -s pinggy
   tmux send-keys -t pinggy 'ssh -T -p 443 -o StrictHostKeyChecking=no -o ServerAliveInterval=30 -R 0:localhost:5000 a.pinggy.io 2>&1 | tee /tmp/pinggy_out.log' C-m
5. 等待 5 秒
6. 提取公网地址：cat /tmp/pinggy_out.log | grep -E "https://.*\.free\.pinggy\.net"

告诉我新的公网地址。
```

---

### 📋 检查状态

```
帮我检查 oldchat-kivotos 的运行状态：

1. 应用进程：ps aux | grep "python3 app.py" | grep -v grep
2. 应用端口：curl -s -o /dev/null -w "%{http_code}" http://localhost:5000/login
3. 隧道进程：ps aux | grep "a.pinggy.io" | grep -v grep
4. 隧道地址：cat /tmp/pinggy_out.log | grep -E "https://.*\.free\.pinggy\.net" | head -1
5. 应用日志最后 10 行：tail -10 /tmp/oldchat-kivotos.log

告诉我各项状态是否正常。
```

---

### 📁 关键文件路径

| 文件 | 路径 | 说明 |
|------|------|------|
| 主应用 | `app.py` | Flask 主程序，Waitress 服务器 |
| API 配置 | `oldchat_api.py` | API 客户端，第 10 行是 base_url |
| 加密 API | `encrypted_api.py` | 加密版 API，第 31 行是 base_url |
| 主页面模板 | `templates/index.html` | 聊天界面，含手机端适配 |
| 登录页面 | `templates/login.html` | 登录页面 |
| 主 JS | `static/script.js` | 主要 JavaScript，含手机端逻辑 |
| 关于页面 | `static/about.html` | 关于页面 |
| 主题样式 | `static/style/momopink/style.css` | 默认主题 |
| 应用日志 | `/tmp/oldchat-kivotos.log` | 运行日志 |
| 隧道日志 | `/tmp/pinggy_out.log` | pinggy 隧道日志 |

---

## 常见问题

### 1. 502 Bad Gateway 错误

**原因**：环境有 HTTP 代理，访问 API 服务器时走了代理导致失败。

**解决**：启动时必须加 `NO_PROXY` 环境变量：
```bash
NO_PROXY="43.155.218.45,localhost,127.0.0.1" no_proxy="43.155.218.45,localhost,127.0.0.1" python app.py
```

### 2. ModuleNotFoundError: No module named 'waitress'

**原因**：waitress 模块没有安装。

**解决**：
```bash
pip install waitress
# 或
pip install -r requirements.txt
```

### 3. 有多个重复进程

**原因**：多次启动应用，没有杀掉旧进程。

**解决**：
```bash
# 查看所有进程
ps aux | grep "python3 app.py" | grep -v grep

# 杀掉所有进程
pkill -f "python3 app.py"

# 等 1 秒后重新启动
sleep 1
NO_PROXY="..." nohup python app.py > /tmp/oldchat-kivotos.log 2>&1 &
```

### 4. pinggy 隧道地址过期

**原因**：免费版 60 分钟过期。

**解决**：重启隧道，获取新地址。

### ✅ 5. 修改 API 服务器地址

> ⚠️ **注意**：本修改版已经将服务器地址改为 `http://43.155.218.45:8080`，**无需再次修改**。
> 
> 以下内容仅作参考，如果你需要更换为其他服务器地址时才需要修改：

编辑以下两个文件：
- `oldchat_api.py` - 第 10 行的 `base_url`
- `encrypted_api.py` - 第 31 行的 `base_url`

修改后重启应用生效。

---

## 注意事项

⚠️ **使用前请仔细阅读**

1. **API 服务器**：本修改版更换为官方提供的旧服务器地址，与原作者默认服务器不同
2. **数据安全**：请妥善保管自己的账号密码，不要在不信任的服务器上登录
3. **稳定性**：服务器不保证长期稳定可用，如有问题请自行更换
4. **仅供学习**：本项目仅供学习与研究目的使用
5. **风险自负**：使用本项目产生的任何后果由使用者自行承担

---

## 原作者在线体验（已下线）

原作者 LGCR837 提供的在线体验网站（已下线，不保证可用性）

> ~~http://chat.gweb.work.gd/~~

---

## 原作者历史版本备份

原作者提供的所有历史版本可在蓝奏云下载：

- 📦 下载链接：https://gengcr.lanzouw.com/b00hrpzh9c
- 🔑 密码：`gl4d`

包含主要版本的源代码以及编译后的产物。

---

## 免责声明

此项目为 **OldChat 的第三方客户端版本**，与 OldChat 官方版本没有直接的联系。

- 本项目是开源的非官方客户端，仅供学习与研究目的
- 所有商标归其各自所有者所有
- 使用本项目产生的任何后果由使用者自行承担

如果需要官方版本请访问：https://oldchat.online/

---

## 许可证

本项目基于 **GNU General Public License v3.0 (GPL-3.0)** 开源。

```
GNU General Public License v3.0

Copyright (c) 2026 LGCR837
Modified by 舟山小黄鱼 (ock-fix), 2026

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <https://www.gnu.org/licenses/>.
```

本修改版同样遵循 **GNU General Public License v3.0** 协议。

---

本项目由豆包AI(办公任务模式)和Deepseek倾情赞助。(连这一行也是我让ai写在readme里的)
