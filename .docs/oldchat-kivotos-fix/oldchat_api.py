# OldChat API Powered by LGCR837
import os
import time
import json
import requests
from typing import Dict, List, Optional, Tuple, Any, BinaryIO
from pathlib import Path

class OldChatAPI:
    def __init__(self, base_url: str = "http://43.155.218.45:8080"):
        self.base_url = base_url.rstrip('/')
        self.access_token = None
        self.refresh_token = None
        self.user = None
        self.session = requests.Session()
        self.session.headers.update({"User-Agent": "OldChatCLI/0.2"})

    def _request(self, method: str, path: str, auth: bool = True, **kwargs) -> Dict:
        """发送 HTTP 请求，自动处理 token 刷新"""
        url = f"{self.base_url}{path}"
        if auth and self.access_token:
            self.session.headers.update({"Authorization": f"Bearer {self.access_token}"})

        kwargs.setdefault('timeout', 30)
        response = self.session.request(method, url, **kwargs)

        # token 过期
        if response.status_code == 401 and auth and self.refresh_token:
            if self._refresh():
                self.session.headers.update({"Authorization": f"Bearer {self.access_token}"})
                kwargs.setdefault('timeout', 30)
                response = self.session.request(method, url, **kwargs)
            else:
                raise Exception("登录已过期，请重新登录")

        try:
            data = response.json()
        except:
            data = {"raw": response.text}

        if not response.ok:
            raise Exception(f"HTTP {response.status_code}: {data}")

        return data

    def _refresh(self) -> bool:
        """刷新 access_token"""
        try:
            resp = self._request('POST', '/v1/auth/refresh', auth=False,
                                 json={"refresh_token": self.refresh_token})
            self.access_token = resp['access_token']
            if 'refresh_token' in resp:
                self.refresh_token = resp['refresh_token']
            return True
        except:
            return False

    # ---------- 认证 ----------
    def login(self, identifier: str, password: str, device_name: str = "CLI") -> Dict:
        """登录并保存 token"""
        resp = self._request('POST', '/v1/auth/login', auth=False, json={
            "identifier": identifier,
            "password": password,
            "device_id": "cli-device",
            "device_name": device_name,
            "platform": "cli",
            "app_version": "0.2"
        })
        self.access_token = resp['access_token']
        self.refresh_token = resp['refresh_token']
        self.user = resp['user']
        return self.user

    def logout(self):
        self.access_token = None
        self.refresh_token = None
        self.user = None

    # ---------- 好友与群组 ----------
    def get_friends(self) -> List[Dict]:
        data = self._request('GET', '/v1/friends')
        return data.get('friends', [])

    def get_groups(self) -> List[Dict]:
        data = self._request('GET', '/v1/groups/list')
        return data.get('groups', [])

    def get_group_members(self, group_id: str) -> List[Dict]:
        data = self._request('GET', f'/v1/groups/members', params={"group_id": group_id})
        return data.get('members', [])

    # ---------- 消息拉取 ----------
    def get_direct_messages(self, with_uid: str, limit: int = 50, offset: int = 0) -> List[Dict]:
        data = self._request('GET', '/v1/direct/messages/v2',
                             params={"with_uid": with_uid, "limit": limit, "offset": offset})
        return data.get('messages', [])

    def get_group_messages(self, group_id: str, limit: int = 50, offset: int = 0) -> List[Dict]:
        data = self._request('GET', '/v1/groups/messages/v2',
                             params={"group_id": group_id, "limit": limit, "offset": offset})
        return data.get('messages', [])

    # ---------- 未读消息 ----------
    def get_unread_direct(self, limit: int = 50) -> List[Dict]:
        data = self._request('POST', '/v1/direct/unread', json={"limit": limit})
        return data.get('messages', [])

    def get_unread_groups(self, limit: int = 50) -> List[Dict]:
        data = self._request('POST', '/v1/groups/unread', json={"limit": limit})
        return data.get('messages', [])

    def mark_direct_read(self, with_uid: str):
        self._request('POST', '/v1/direct/read', json={"with_uid": with_uid})

    def mark_group_read(self, group_id: str):
        self._request('POST', '/v1/groups/read', json={"group_id": group_id})

    # ---------- 媒体上传（增强调试与兼容性） ----------
    def upload_media(self, file_path: str) -> Tuple[Optional[str], Optional[str]]:
        """
        上传媒体文件到旧聊服务器
        返回 (media_url, thumb_url)
        """
        if not os.path.exists(file_path):
            print(f"[upload_media] 文件不存在: {file_path}")
            return None, None

        filename = os.path.basename(file_path)
        # 获取文件扩展名
        ext = Path(file_path).suffix.lower()
        mime_map = {
            '.jpg': 'image/jpeg', '.jpeg': 'image/jpeg',
            '.png': 'image/png', '.gif': 'image/gif',
            '.webp': 'image/webp',
            '.mp4': 'video/mp4', '.mov': 'video/quicktime',
            '.mp3': 'audio/mpeg', '.wav': 'audio/wav'
        }
        # 先尝试不指定 content_type，让 requests 自动设置
        ctype_auto = None   # 自动模式
        ctype_manual = mime_map.get(ext, 'application/octet-stream')

        with open(file_path, 'rb') as f:
            file_data = f.read()

        # 尝试多种组合，直到成功
        # 组合：字段名、Content-Type
        attempts = [
            ("file", (filename, file_data)),            # 自动 Content-Type
            ("media", (filename, file_data)),           # 字段名改为 media
            ("file", (filename, file_data, ctype_manual)), # 手动 Content-Type
        ]

        for field_name, files_tuple in attempts:
            try:
                url = f"{self.base_url}/v1/media"
                files = {field_name: files_tuple}
                print(f"[upload_media] 尝试上传: 字段名={field_name}, Content-Type={'自动' if len(files_tuple)==2 else ctype_manual}")
                resp = self.session.post(url,
                    headers={"Authorization": f"Bearer {self.access_token}"},
                    files=files)

                if resp.status_code == 200:
                    r = resp.json()
                    media_url = r.get("url", "")
                    thumb_url = r.get("thumb_url", "")
                    # 补全绝对路径
                    if media_url.startswith('/'):
                        media_url = self.base_url + media_url
                    if thumb_url.startswith('/'):
                        thumb_url = self.base_url + thumb_url
                    print(f"[upload_media] 上传成功，字段名={field_name}")
                    return media_url, thumb_url
                else:
                    print(f"[upload_media] ❌ 上传失败 (HTTP {resp.status_code}): {resp.text[:200]}")
            except Exception as e:
                print(f"[upload_media] 请求异常: {e}")

        print("[upload_media] 所有尝试均失败")
        return None, None

    def upload_media_bytes(self, file_data: bytes, filename: str, ctype: str) -> Tuple[Optional[str], Optional[str]]:
        """上传字节数据（直接调用 upload_media 的尝试逻辑）"""
        # 类似逻辑，但由于无临时文件，我们手动尝试
        attempts = [
            ("file", (filename, file_data)),
            ("media", (filename, file_data)),
            ("file", (filename, file_data, ctype)),
        ]
        for field_name, files_tuple in attempts:
            try:
                url = f"{self.base_url}/v1/media"
                resp = self.session.post(url,
                    headers={"Authorization": f"Bearer {self.access_token}"},
                    files={field_name: files_tuple})
                if resp.status_code == 200:
                    r = resp.json()
                    media_url = r.get("url", "")
                    thumb_url = r.get("thumb_url", "")
                    if media_url.startswith('/'):
                        media_url = self.base_url + media_url
                    if thumb_url.startswith('/'):
                        thumb_url = self.base_url + thumb_url
                    return media_url, thumb_url
                else:
                    print(f"[upload_media_bytes] 上传失败 (HTTP {resp.status_code}): {resp.text[:200]}")
            except Exception as e:
                print(f"[upload_media_bytes] 异常: {e}")
        return None, None

    # ---------- 发送消息（含媒体） ----------
    def send_direct_message(self, to_uid: str, body: str, msg_type: str = "text",
                            burn_after_seconds: int = 0,
                            media_url: str = None, thumb_url: str = None,
                            **kwargs) -> Dict:
        payload = {
            "to_uid": to_uid,
            "body": body,
            "msg_type": msg_type,
            "burn_after_seconds": burn_after_seconds
        }
        if media_url:
            payload["media_url"] = media_url
        if thumb_url:
            payload["thumb_url"] = thumb_url
        payload.update(kwargs)
        return self._request('POST', '/v1/direct/send', json=payload)

    def send_group_message(self, group_id: str, body: str, msg_type: str = "text",
                           burn_after_seconds: int = 0,
                           media_url: str = None, thumb_url: str = None,
                           **kwargs) -> Dict:
        payload = {
            "group_id": group_id,
            "body": body,
            "msg_type": msg_type,
            "burn_after_seconds": burn_after_seconds
        }
        if media_url:
            payload["media_url"] = media_url
        if thumb_url:
            payload["thumb_url"] = thumb_url
        payload.update(kwargs)
        return self._request('POST', '/v1/groups/message/send', json=payload)

    # 便捷方法：发送图片
    def send_image(self, to_id: str, is_group: bool, image_path: str,
                   burn_after_seconds: int = 0) -> bool:
        print(f"[send_image] 开始上传图片: {image_path}")
        media_url, thumb_url = self.upload_media(image_path)
        if not media_url:
            print("[send_image] upload_media 返回 None，发送失败")
            return False
        if is_group:
            self.send_group_message(to_id, "", "image", burn_after_seconds, media_url, thumb_url)
        else:
            self.send_direct_message(to_id, "", "image", burn_after_seconds, media_url, thumb_url)
        return True

    # 便捷方法：发送视频
    def send_video(self, to_id: str, is_group: bool, video_path: str,
                   burn_after_seconds: int = 0) -> bool:
        print(f"[send_video] 开始上传视频: {video_path}")
        media_url, thumb_url = self.upload_media(video_path)
        if not media_url:
            print("[send_video] upload_media 返回 None，发送失败")
            return False
        if is_group:
            self.send_group_message(to_id, "", "video", burn_after_seconds, media_url, thumb_url)
        else:
            self.send_direct_message(to_id, "", "video", burn_after_seconds, media_url, thumb_url)
        return True

    # 便捷方法：发送语音
    def send_voice(self, to_id: str, is_group: bool, voice_path: str,
                   duration_ms: int = 0, burn_after_seconds: int = 0) -> bool:
        print(f"[send_voice] 开始上传语音: {voice_path}")
        # 自动计算音频时长（秒 → 毫秒）
        if not duration_ms:
            try:
                from mutagen import File as MutagenFile
                af = MutagenFile(voice_path)
                if af is not None and af.info and af.info.length:
                    duration_ms = max(1, int(af.info.length * 1000))
                    print(f"[send_voice] 检测到语音时长: {duration_ms}ms")
            except Exception as e:
                print(f"[send_voice] 无法获取音频时长: {e}")
        if not duration_ms:
            duration_ms = 1000  # 默认 1 秒，避免 0 或极小值被服务端拒绝
        media_url, _ = self.upload_media(voice_path)
        if not media_url:
            print("[send_voice] upload_media 返回 None，发送失败")
            return False
        body = json.dumps({"duration_ms": duration_ms})
        if is_group:
            self.send_group_message(to_id, body, "voice", burn_after_seconds, media_url,
                                    duration_ms=duration_ms)
        else:
            self.send_direct_message(to_id, body, "voice", burn_after_seconds, media_url,
                                     duration_ms=duration_ms)
        return True

    # 便捷方法：发送文件
    def send_file(self, to_id: str, is_group: bool, file_path: str,
                  burn_after_seconds: int = 0) -> bool:
        print(f"[send_file] 开始上传文件: {file_path}")
        media_url, _ = self.upload_media(file_path)
        if not media_url:
            print("[send_file] upload_media 返回 None，发送失败")
            return False
        filename = os.path.basename(file_path)
        file_size = os.path.getsize(file_path)
        body = f"文件名: {filename}\n大小: {file_size} 字节\n点击下载"
        if is_group:
            self.send_group_message(to_id, body, "resource", burn_after_seconds, media_url)
        else:
            self.send_direct_message(to_id, body, "resource", burn_after_seconds, media_url)
        return True

    # ---------- 红包 ----------
    def get_redpacket_info(self, packet_id: str) -> Dict:
        return self._request('GET', f'/v1/redpackets/{packet_id}')

    def claim_redpacket(self, packet_id: str) -> Dict:
        return self._request('POST', '/v1/redpackets/claim', json={"packet_id": packet_id})

    # ---------- 消息构建辅助 ----------
    def build_quote_body(self, text: str, quote_msg: Dict) -> str:
        payload = {
            "v": 2,
            "text": text,
            "quote": {
                "id": quote_msg["id"],
                "from_uid": quote_msg["from_uid"],
                "from_name": quote_msg.get("from_name", quote_msg["from_uid"]),
                "type": quote_msg.get("msg_type", "text"),
                "text": quote_msg.get("body", "")
            }
        }
        return json.dumps(payload, ensure_ascii=False)

    def build_mention_body(self, text: str, mentions: List[Dict]) -> str:
        payload = {"v": 2, "text": text, "mentions": mentions}
        return json.dumps(payload, ensure_ascii=False)
    
    def get_user_profile(self, uid: str) -> Dict:
        # Space 个人空间信息获取
        data = self._request('GET', '/v1/users/profile', params={"uid": uid})
        return data
    
    def get_user_moments(self, uid: str, limit: int = 10, offset: int = 0) -> List[Dict]:
        # 获取朋友圈动态
        data = self._request('GET', '/v1/moments/user', params={
            "uid": uid,
            "limit": limit,
            "offset": offset
        })
        moments = data.get('moments', [])
        # 头像绝对路径
        base = self.base_url
        for m in moments:
            avatar = m.get('from_avatar', '')
            if avatar and avatar.startswith('/'):
                m['from_avatar'] = base + avatar
            img = m.get('image_url', '')
            if img and img.startswith('/'):
                m['image_url'] = base + img
        return moments


    def get_emoji_plaza(self, limit: int = 50, offset: int = 0, q: str = None) -> List[Dict]:
        # 表情包广场
        params = {"limit": limit, "offset": offset}
        if q:
            params["q"] = q
        data = self._request('GET', '/v1/emoji/plaza', params=params)
        items = data.get('items', []) or data.get('emojis', [])
        base = self.base_url
        for item in items:
            for field in ['url', 'thumb_url', 'preview_url']:
                val = item.get(field, '')
                if val and val.startswith('/'):
                    item[field] = base + val
        return items

    def get_friend_requests(self) -> List[Dict]:
        data = self._request('GET', '/v1/friends/requests')
        return data.get('requests', [])

    def respond_friend_request(self, request_id: str, accept: bool) -> Dict:
        return self._request('POST', '/v1/friends/respond', json={
            "request_id": request_id,
            "accept": accept
        })