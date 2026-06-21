import os
import json
import tempfile
import time
import requests
from flask import Blueprint, request, session, jsonify, g, Response, current_app
from oldchat_api import OldChatAPI

api_bp = Blueprint('api', __name__)

# 简单的内存缓存（TTL 30 秒），减少频繁的群成员/好友列表请求
_cache = {}
_cache_ttl = 30  # 秒

def _cache_get(key):
    val = _cache.get(key)
    if val and time.time() - val['ts'] < _cache_ttl:
        return val['data']
    return None

def _cache_set(key, data):
    _cache[key] = {'data': data, 'ts': time.time()}

def _cache_clear():
    _cache.clear()

def require_api():
    if not g.api:
        return jsonify({'error': '未登录'}), 401
    return None

def fix_message_urls(msg):
    """将相对路径的媒体URL补全为绝对URL，并转换为代理URL以解决混合内容问题"""
    if not g.api:
        return msg
    base = g.api.base_url.rstrip('/')
    
    # 旧服务器地址，用于兼容历史消息
    old_base = 'http://60.205.94.101:8080'
    
    if msg.get('media_url', '').startswith('/'):
        msg['media_url'] = base + msg['media_url']
    elif msg.get('media_url', '').startswith(old_base):
        # 替换旧服务器地址为新地址
        msg['media_url'] = base + msg['media_url'][len(old_base):]
    
    if msg.get('thumb_url', '').startswith('/'):
        msg['thumb_url'] = base + msg['thumb_url']
    elif msg.get('thumb_url', '').startswith(old_base):
        # 替换旧服务器地址为新地址
        msg['thumb_url'] = base + msg['thumb_url'][len(old_base):]
    
    if msg.get('from_avatar', '').startswith(old_base):
        # 替换旧服务器地址为新地址
        msg['from_avatar'] = base + msg['from_avatar'][len(old_base):]
    
    # 转换为代理URL
    if msg.get('media_url'):
        msg['media_url'] = to_proxy_url(msg['media_url'])
    if msg.get('thumb_url'):
        msg['thumb_url'] = to_proxy_url(msg['thumb_url'])
    if msg.get('from_avatar'):
        msg['from_avatar'] = to_proxy_url(msg['from_avatar'])
    return msg

def to_proxy_url(url):
    """将图片URL转换为代理URL，解决HTTPS混合内容问题"""
    if not url:
        return url
    # 如果已经是代理URL，直接返回
    if '/api/proxy-image' in url:
        return url
    # 编码URL参数，编码所有特殊字符
    from urllib.parse import quote
    return f'/api/proxy-image?url={quote(url, safe="")}'

def from_proxy_url(url):
    """将代理URL转换为原始URL，用于发送消息时传给API服务器"""
    if not url:
        return url
    # 如果不是代理URL，直接返回
    if '/api/proxy-image' not in url:
        return url
    # 提取url参数
    from urllib.parse import urlparse, parse_qs, unquote
    parsed = urlparse(url)
    params = parse_qs(parsed.query)
    if 'url' in params:
        return unquote(params['url'][0])
    return url

def enrich_message_names(msgs, conv_type, conv_id=None):
    """为消息补充 from_name（昵称）和 from_avatar（头像），如果缺失则从服务器获取"""
    if not g.api or not msgs:
        return
    uid_map = {}
    avatar_map = {}
    base = g.api.base_url
    try:
        if conv_type == 'direct':
            # 私聊：使用好友列表映射（名称+头像），缓存 30 秒
            friends = _cache_get('friends_list')
            if friends is None:
                friends = g.api.get_friends()
                _cache_set('friends_list', friends)
            for f in friends:
                uid = f.get('uid', '').upper()
                uid_map[uid] = f.get('display_name') or f.get('username') or f.get('uid')
                avatar = f.get('avatar_url', '')
                if avatar and avatar.startswith('/'):
                    avatar = base + avatar
                if avatar:
                    avatar_map[uid] = avatar
        elif conv_type == 'group' and conv_id:
            # 群聊：获取群成员映射（名称+头像），缓存 30 秒
            cache_key = f'group_members:{conv_id}'
            members = _cache_get(cache_key)
            if members is None:
                members = g.api.get_group_members(conv_id)
                _cache_set(cache_key, members)
            for m in members:
                uid = m.get('uid', '').upper()
                uid_map[uid] = m.get('display_name', m.get('uid', ''))
                avatar = m.get('avatar_url', '')
                if avatar and avatar.startswith('/'):
                    avatar = base + avatar
                if avatar:
                    avatar_map[uid] = avatar
    except Exception as e:
        print(f"[enrich] 获取名称映射失败: {e}")
        return

    for msg in msgs:
        from_uid = msg.get('from_uid', '')
        if not from_uid:
            continue
        uid_key = from_uid.upper()
        if not msg.get('from_name'):
            name = uid_map.get(uid_key, '')
            if name:
                msg['from_name'] = name
        # 补充头像（如有缓存）
        if not msg.get('from_avatar'):
            avatar = avatar_map.get(uid_key, '')
            if avatar:
                msg['from_avatar'] = to_proxy_url(avatar)

def guess_msg_type(filename: str) -> str:
    """根据文件扩展名判断消息类型"""
    ext = os.path.splitext(filename)[1].lower()
    if ext in ('.jpg', '.jpeg', '.png', '.gif', '.bmp', '.webp'):
        return 'image'
    elif ext in ('.mp4', '.mov', '.avi', '.mkv'):
        return 'video'
    elif ext in ('.mp3', '.wav', '.ogg', '.aac'):
        return 'voice'
    else:
        return 'resource'

@api_bp.route('/contacts')
def contacts():
    if not g.api:
        return require_api()
    try:
        friends = []
        for f in g.api.get_friends():
            avatar = f.get('avatar_url', '')
            if avatar and avatar.startswith('/'):
                avatar = g.api.base_url + avatar
            if avatar:
                avatar = to_proxy_url(avatar)
            friends.append({
                'uid': f.get('uid', '').upper(),
                'name': f.get('display_name') or f.get('username') or f.get('uid'),
                'avatar_url': avatar
            })
        groups = []
        for grp in g.api.get_groups():
            groups.append({
                'id': grp.get('group_id', '').upper(),
                'name': grp.get('name', grp.get('group_id'))
            })
        return jsonify({'friends': friends, 'groups': groups})
    except Exception as e:
        return jsonify({'error': str(e)}), 500

@api_bp.route('/messages/<conv_type>/<conv_id>')
def get_messages(conv_type, conv_id):
    if not g.api:
        return require_api()
    limit = request.args.get('limit', 50, type=int)
    offset = request.args.get('offset', 0, type=int)
    # 限制最大单次请求数量，防止滥用
    limit = min(limit, 200)
    try:
        if conv_type == 'direct':
            msgs = g.api.get_direct_messages(conv_id, limit, offset)
        elif conv_type == 'group':
            msgs = g.api.get_group_messages(conv_id, limit, offset)
        else:
            return jsonify({'error': 'Invalid conversation type'}), 400
        msgs.sort(key=lambda x: x.get('created_at', 0))
        for msg in msgs:
            fix_message_urls(msg)
        enrich_message_names(msgs, conv_type, conv_id)
        return jsonify({'messages': msgs})
    except Exception as e:
        return jsonify({'error': str(e)}), 500

@api_bp.route('/unread')
def unread():
    if not g.api:
        return require_api()
    try:
        direct = g.api.get_unread_direct(limit=50)
        groups = g.api.get_unread_groups(limit=50)

        # 补充名称
        if direct:
            enrich_message_names(direct, 'direct')          # 私聊用好友列表
        for gmsg in groups:
            group_id = gmsg.get('group_id', '')
            if group_id:
                enrich_message_names([gmsg], 'group', group_id)  # 群聊逐条补

        for msg in direct:
            fix_message_urls(msg)
        for msg in groups:
            fix_message_urls(msg)
        return jsonify({'direct': direct, 'groups': groups})
    except Exception as e:
        return jsonify({'error': str(e)}), 500

@api_bp.route('/mark_read/<conv_type>/<conv_id>', methods=['PUT'])
def mark_read(conv_type, conv_id):
    if not g.api:
        return require_api()
    try:
        if conv_type == 'direct':
            g.api.mark_direct_read(conv_id)
        elif conv_type == 'group':
            g.api.mark_group_read(conv_id)
        else:
            return jsonify({'error': 'Invalid type'}), 400
        return jsonify({'status': 'ok'})
    except Exception as e:
        return jsonify({'error': str(e)}), 500

@api_bp.route('/send', methods=['POST'])
def send_message():
    if not g.api:
        return require_api()
    data = request.get_json()
    if not data:
        return jsonify({'error': 'No JSON data'}), 400
    conv_type = data.get('type')
    to_id = data.get('to_id')
    body = data.get('body', '')
    msg_type = data.get('msg_type', 'text')
    burn = data.get('burn_after_seconds', 0)
    media_url = data.get('media_url')
    thumb_url = data.get('thumb_url')

    # 如果是代理URL，转换为原始URL再发给API服务器
    if media_url:
        media_url = from_proxy_url(media_url)
    if thumb_url:
        thumb_url = from_proxy_url(thumb_url)

    # 直链语音：远程 API 需要 duration_ms（body 内 + 顶层）
    voice_duration = None
    if msg_type == 'voice' and media_url and not body:
        body = json.dumps({"duration_ms": 3000})
        voice_duration = 3000

    try:
        if conv_type == 'direct':
            kwargs = {}
            if voice_duration:
                kwargs['duration_ms'] = voice_duration
            g.api.send_direct_message(to_id, body, msg_type, burn, media_url, thumb_url,
                                     **kwargs)
            latest_msgs = g.api.get_direct_messages(to_id, limit=1, offset=0)
        elif conv_type == 'group':
            kwargs = {}
            if voice_duration:
                kwargs['duration_ms'] = voice_duration
            g.api.send_group_message(to_id, body, msg_type, burn, media_url, thumb_url,
                                     **kwargs)
            latest_msgs = g.api.get_group_messages(to_id, limit=1, offset=0)
        else:
            return jsonify({'error': 'Invalid type'}), 400

        if latest_msgs:
            latest_msg = latest_msgs[0]
            fix_message_urls(latest_msg)
            enrich_message_names([latest_msg], conv_type, to_id)   # ← 新增
            return jsonify({'status': 'ok', 'message': latest_msg})
        else:
            return jsonify({'status': 'ok', 'message': None})
    except Exception as e:
        return jsonify({'error': str(e)}), 500

# ========== 上传并发送 ==========
@api_bp.route('/upload_and_send', methods=['POST'])
def upload_and_send():
    if not g.api:
        return require_api()
    if 'file' not in request.files:
        return jsonify({'error': 'No file part'}), 400
    file = request.files['file']
    if file.filename == '':
        return jsonify({'error': 'No selected file'}), 400

    conv_type = request.form.get('conv_type')
    to_id = request.form.get('to_id')
    if not conv_type or not to_id:
        return jsonify({'error': 'Missing conv_type or to_id'}), 400

    fd, tmp_path = tempfile.mkstemp(suffix=os.path.splitext(file.filename)[1])
    try:
        file.save(tmp_path)
        # 刷新 token，防止过期
        try:
            g.api._refresh()
        except Exception as e:
            print(f"[upload_and_send] token refresh failed: {e}")

        msg_type = guess_msg_type(file.filename)
        is_group = (conv_type == 'group')

        # 调用 OldChatAPI 的便捷方法
        success = False
        if msg_type == 'image':
            success = g.api.send_image(to_id, is_group, tmp_path)
        elif msg_type == 'video':
            success = g.api.send_video(to_id, is_group, tmp_path)
        elif msg_type == 'voice':
            success = g.api.send_voice(to_id, is_group, tmp_path)
        else:
            success = g.api.send_file(to_id, is_group, tmp_path)

        if not success:
            return jsonify({'error': '文件发送失败（上传或发送出错）'}), 500

        # 拉取最后一条消息
        if conv_type == 'direct':
            latest_msgs = g.api.get_direct_messages(to_id, limit=1, offset=0)
        else:
            latest_msgs = g.api.get_group_messages(to_id, limit=1, offset=0)

        if latest_msgs:
            latest_msg = latest_msgs[0]
            fix_message_urls(latest_msg)
            enrich_message_names([latest_msg], conv_type, to_id)   # ← 新增：补充昵称
            return jsonify({'status': 'ok', 'message': latest_msg})
        else:
            return jsonify({'status': 'ok', 'message': None})

    except Exception as e:
        return jsonify({'error': str(e)}), 500
    finally:
        os.close(fd)
        os.unlink(tmp_path)

@api_bp.route('/redpacket/claim', methods=['POST'])
def claim_redpacket():
    if not g.api:
        return require_api()
    data = request.get_json()
    if not data or 'packet_id' not in data:
        return jsonify({'error': 'Missing packet_id'}), 400
    try:
        result = g.api.claim_redpacket(data['packet_id'])
        return jsonify(result)
    except Exception as e:
        return jsonify({'error': str(e)}), 500

@api_bp.route('/emoticons')
def get_emoticons():
    if not g.api:
        return require_api()
    img_dir = os.path.join(current_app.root_path, 'static', 'images')
    if not os.path.exists(img_dir):
        return jsonify({'images': []})
    files = os.listdir(img_dir)
    allowed_ext = ('.png', '.jpg', '.jpeg', '.gif', '.webp', '.bmp')
    images = [f for f in files if os.path.splitext(f)[1].lower() in allowed_ext]
    return jsonify({'images': sorted(images)})

@api_bp.route('/upload_only', methods=['POST'])
def upload_only():
    """只上传文件到 OldChat 服务器，返回 media_url 和 thumb_url"""
    if not g.api:
        return require_api()
    if 'file' not in request.files:
        return jsonify({'error': 'No file part'}), 400
    file = request.files['file']
    if file.filename == '':
        return jsonify({'error': 'No selected file'}), 400

    fd, tmp_path = tempfile.mkstemp(suffix=os.path.splitext(file.filename)[1])
    try:
        file.save(tmp_path)
        # 刷新 token
        try:
            g.api._refresh()
        except Exception:
            pass

        # 直接调用 upload_media 方法（它已经支持 webp 等多种格式）
        media_url, thumb_url = g.api.upload_media(tmp_path)
        if not media_url:
            return jsonify({'error': '上传失败，服务器拒绝该文件'}), 400

        # upload_media 内部已经补全绝对路径，但为安全再检查一次
        if media_url.startswith('/'):
            media_url = g.api.base_url + media_url
        if thumb_url and thumb_url.startswith('/'):
            thumb_url = g.api.base_url + thumb_url

        # 转换为代理URL，解决HTTPS混合内容问题
        media_url = to_proxy_url(media_url)
        if thumb_url:
            thumb_url = to_proxy_url(thumb_url)

        return jsonify({'media_url': media_url, 'thumb_url': thumb_url})
    except Exception as e:
        return jsonify({'error': str(e)}), 500
    finally:
        os.close(fd)
        os.unlink(tmp_path)

@api_bp.route('/user/<uid>')
def get_user(uid):
    if not g.api:
        return require_api()
    try:
        profile = g.api.get_user_profile(uid)
        # 补全头像和封面图绝对路径
        base = g.api.base_url
        for field in ['avatar_url', 'cover_url']:
            url = profile.get(field, '')
            if url and url.startswith('/'):
                profile[field] = base + url
            if profile.get(field):
                profile[field] = to_proxy_url(profile[field])
        return jsonify(profile)
    except Exception as e:
        return jsonify({'error': str(e)}), 500

@api_bp.route('/me')
def get_my_profile():
    if not g.api:
        return require_api()
    try:
        data = g.api._request('GET', '/v1/me')
        base = g.api.base_url
        for field in ['avatar_url', 'cover_url']:
            url = data.get(field, '')
            if url and url.startswith('/'):
                data[field] = base + url
            if data.get(field):
                data[field] = to_proxy_url(data[field])
        return jsonify(data)
    except Exception as e:
        return jsonify({'error': str(e)}), 500

@api_bp.route('/friends/add', methods=['POST'])
def add_friend():
    if not g.api:
        return require_api()
    data = request.get_json()
    uid = data.get('to_uid')
    if not uid:
        return jsonify({'error': 'Missing to_uid'}), 400
    try:
        resp = g.api._request('POST', '/v1/friends/request', json={"to_uid": uid})
        return jsonify(resp)
    except Exception as e:
        return jsonify({'error': str(e)}), 500

@api_bp.route('/groups/join', methods=['POST'])
def join_group():
    if not g.api:
        return require_api()
    data = request.get_json()
    group_id = data.get('group_id')
    if not group_id:
        return jsonify({'error': 'Missing group_id'}), 400
    try:
        resp = g.api._request('POST', '/v1/groups/join', json={"group_id": group_id})
        return jsonify(resp)
    except Exception as e:
        return jsonify({'error': str(e)}), 500

@api_bp.route('/groups/leave', methods=['POST'])
def leave_group():
    if not g.api:
        return require_api()
    data = request.get_json()
    group_id = data.get('group_id')
    if not group_id:
        return jsonify({'error': 'Missing group_id'}), 400
    try:
        resp = g.api._request('POST', '/v1/groups/leave', json={"group_id": group_id})
        return jsonify(resp)
    except Exception as e:
        return jsonify({'error': str(e)}), 500

@api_bp.route('/moments/post', methods=['POST'])
def post_moment():
    if not g.api:
        return require_api()
    data = request.get_json()
    body = data.get('body', '').strip()
    if not body:
        return jsonify({'error': '内容不能为空'}), 400
    try:
        resp = g.api._request('POST', '/v1/moments', json={"body": body, "image_url": ""})
        return jsonify(resp)
    except Exception as e:
        return jsonify({'error': str(e)}), 500

@api_bp.route('/emoji/plaza')
def get_emoji_plaza():
    if not g.api:
        return require_api()
    try:
        limit = request.args.get('limit', 50, type=int)
        offset = request.args.get('offset', 0, type=int)
        q = request.args.get('q', None)
        items = g.api.get_emoji_plaza(limit, offset, q)
        base = g.api.base_url.rstrip('/')
        for item in items:
            for field in ['url', 'media_url', 'thumb_url', 'preview_url']:
                val = item.get(field, '')
                if val and val.startswith('/'):
                    item[field] = base + val
                if item.get(field):
                    item[field] = to_proxy_url(item[field])
        return jsonify({'items': items})
    except Exception as e:
        return jsonify({'error': str(e)}), 500

@api_bp.route('/friend_requests')
def get_friend_requests():
    if not g.api:
        return require_api()
    try:
        requests_list = g.api.get_friend_requests()
        return jsonify({'requests': requests_list})
    except Exception as e:
        return jsonify({'error': str(e)}), 500

@api_bp.route('/friend_respond', methods=['POST'])
def respond_friend_request():
    if not g.api:
        return require_api()
    data = request.get_json()
    request_id = data.get('request_id')
    accept = data.get('accept')
    if not request_id or accept is None:
        return jsonify({'error': 'Missing request_id or accept'}), 400
    try:
        resp = g.api.respond_friend_request(request_id, accept)
        return jsonify(resp)
    except Exception as e:
        err_str = str(e)
        # 如果请求已不存在（可能对方已经是好友），当成成功处理
        if 'request_not_found' in err_str or 'request not found' in err_str.lower():
            return jsonify({'code': 'already_processed', 'message': '该申请已被处理'})
        return jsonify({'error': err_str}), 500

@api_bp.route('/proxy-image')
def proxy_image():
    """图片代理，解决HTTPS页面加载HTTP图片的混合内容问题"""
    url = request.args.get('url', '')
    if not url:
        return jsonify({'error': 'Missing url parameter'}), 400

    # 安全检查：只允许代理 API 服务器的图片（包括旧服务器地址，兼容历史消息）
    api_base = None
    if g.api:
        api_base = g.api.base_url.rstrip('/')
    else:
        # 未登录时使用默认地址
        api_base = 'http://43.155.218.45:8080'
    
    # 旧服务器地址，用于兼容历史消息
    old_api_base = 'http://60.205.94.101:8080'

    # 如果是相对路径，补全为绝对路径
    if url.startswith('/'):
        url = api_base + url

    # 安全检查：确保 URL 是 API 服务器的（新地址或旧地址）
    if not url.startswith(api_base) and not url.startswith(old_api_base):
        return jsonify({'error': 'Invalid URL'}), 400

    try:
        # 使用 g.api.session 或 requests，确保不使用代理
        session = g.api.session if g.api else requests.Session()
        resp = session.get(url, timeout=30, stream=True)
        resp.raise_for_status()

        # 获取内容类型
        content_type = resp.headers.get('Content-Type', 'image/jpeg')

        # 返回图片数据
        return Response(
            resp.iter_content(chunk_size=8192),
            content_type=content_type,
            headers={
                'Cache-Control': 'public, max-age=86400',
                'Access-Control-Allow-Origin': '*'
            }
        )
    except Exception as e:
        return jsonify({'error': f'Proxy failed: {str(e)}'}), 500