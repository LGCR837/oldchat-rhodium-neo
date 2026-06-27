function escapeHtml(text) {
    const div = document.createElement('div');
    div.appendChild(document.createTextNode(text));
    return div.innerHTML;
}

function escapeRegExp(string) {
    return string.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
}

function formatTimeSeparator(ts) {
    const now = new Date();
    const d = new Date(ts * 1000);
    const pad = n => (n < 10 ? '0' : '') + n;
    const hhmm = pad(d.getHours()) + ':' + pad(d.getMinutes());
    const isSameDay = d.getFullYear() === now.getFullYear() &&
        d.getMonth() === now.getMonth() && d.getDate() === now.getDate();
    if (isSameDay) return hhmm;
    const isSameYear = d.getFullYear() === now.getFullYear();
    if (isSameYear) return pad(d.getMonth() + 1) + '-' + pad(d.getDate()) + ' ' + hhmm;
    return d.getFullYear() + '-' + pad(d.getMonth() + 1) + '-' + pad(d.getDate()) + ' ' + hhmm;
}

function createTimeSeparator(ts) {
    const div = document.createElement('div');
    div.className = 'time-separator';
    div.textContent = formatTimeSeparator(ts);
    return div;
}


function openImageViewer(src) {
	let overlay = document.getElementById('imageOverlay');
	let img = document.getElementById('imageOverlayImg');
	let dragging = false;
	let hasDragged = false;
	let startX = 0,
		startY = 0;
	let imgStartX = 0,
		imgStartY = 0;
	if (!overlay) {
		overlay = document.createElement('div');
		overlay.id = 'imageOverlay';
		overlay.style.cssText = `position:fixed;top:0;left:0;right:0;bottom:0;background:rgba(0,0,0,0.85);display:none;justify-content:center;align-items:center;z-index:99999;opacity:0;transition:opacity 0.25s ease;cursor:grab;user-select:none;`;
		img = document.createElement('img');
		img.id = 'imageOverlayImg';
		img.style.cssText = `max-width:90%;max-height:90%;object-fit:contain;border-radius:4px;transform:scale(1)translate(0px,0px);transition:transform 0.2s ease;pointer-events:none;user-select:none;`;
		img.draggable = false;
		img._scale = 1;
		img._translateX = 0;
		img._translateY = 0;
		overlay.appendChild(img);
		document.body.appendChild(overlay);

		function closeViewer() {
			overlay.style.opacity = '0';
			img.style.transform = 'scale(1) translate(0px, 0px)';
			img._scale = 1;
			img._translateX = 0;
			img._translateY = 0;
			overlay.addEventListener('transitionend', function handler() {
				overlay.style.display = 'none';
				overlay.removeEventListener('transitionend', handler)
			})
		}
		overlay.addEventListener('click', function(e) {
			if (hasDragged) {
				hasDragged = false;
				return
			}
			closeViewer()
		});
		overlay.addEventListener('mousedown', function(e) {
			e.preventDefault();
			dragging = true;
			hasDragged = false;
			startX = e.clientX;
			startY = e.clientY;
			imgStartX = img._translateX || 0;
			imgStartY = img._translateY || 0;
			overlay.style.cursor = 'grabbing';
			img.style.transition = 'none'
		});
		window.addEventListener('mousemove', function(e) {
			if (!dragging) return;
			const dx = e.clientX - startX;
			const dy = e.clientY - startY;
			const scale = img._scale || 1;
			const newX = imgStartX + dx / scale;
			const newY = imgStartY + dy / scale;
			img._translateX = newX;
			img._translateY = newY;
			img.style.transform = `scale(${scale})translate(${newX}px,${newY}px)`;
			if (Math.abs(dx) > 3 || Math.abs(dy) > 3) {
				hasDragged = true
			}
		});
		window.addEventListener('mouseup', function() {
			if (dragging) {
				dragging = false;
				overlay.style.cursor = 'grab';
				img.style.transition = 'transform 0.2s ease'
			}
		});
		overlay.addEventListener('wheel', function(e) {
			e.preventDefault();
			let scale = img._scale || 1;
			const delta = e.deltaY > 0 ? -0.2 : 0.2;
			scale = Math.min(5, Math.max(0.5, scale + delta));
			img._scale = scale;
			const tx = img._translateX || 0;
			const ty = img._translateY || 0;
			img.style.transform = `scale(${scale})translate(${tx}px,${ty}px)`
		}, {
			passive: false
		})
	} else {
		img = document.getElementById('imageOverlayImg')
	}
	img.src = src;
	img._scale = 1;
	img._translateX = 0;
	img._translateY = 0;
	img.style.transform = 'scale(1) translate(0px, 0px)';
	overlay.style.display = 'flex';
	requestAnimationFrame(() => {
		overlay.style.opacity = '1'
	})
}

document.addEventListener('DOMContentLoaded', () => {
    const myUid = document.querySelector('meta[name="uid"]')?.content || '';
    const myName = document.querySelector('meta[name="name"]')?.content || '';
    const myAvatar = document.querySelector('meta[name="avatar"]')?.content || '';

    let currentConv = null;
    const seenMsgIds = {};
    let switchRequestId = 0;
    let contacts = { friends: [], groups: [] };

    const contactList = document.getElementById('contactList');
    const chatHeader = document.getElementById('chatHeader');
    const messagesContainer = document.getElementById('messagesContainer');
    const messageInput = document.getElementById('messageInput');
    const sendBtn = document.getElementById('sendBtn');
    const moreBtn = document.getElementById('moreBtn');
    const moreMenu = document.getElementById('moreMenu');
    const fileInput = document.getElementById('fileInput');
    const logoutBtn = document.getElementById('logoutBtn');
    const pinSidebarBtn = document.getElementById('pinSidebarBtn');
    const mobileMenuBtn = document.getElementById('mobileMenuBtn');
    const aboutBtn = document.getElementById('aboutBtn');

    const quotePreview = document.getElementById('quotePreview');
    const quotePreviewText = quotePreview.querySelector('.quote-preview-text');
    const cancelQuoteBtn = document.getElementById('cancelQuoteBtn');

    const emojiPlazaBtn = document.getElementById('emojiPlazaBtn');

    const mergeMessages = document.querySelector('meta[name="theme-merge-messages"]')?.content === 'true';
    let lastRenderedMsg = null;

    let pendingQuote = null;
    let lastRenderedTs = 0;

    const defaultAvatar = 'https://gwebcdn260523.pages.dev/v1/static/default-avatar.png';

    function openSpacePanel(uid) {
        const overlay = document.createElement('div');
        overlay.style.cssText = 'position:fixed;top:0;left:0;right:0;bottom:0;z-index:9999;background:var(--bg);display:flex;flex-direction:column;font-family:inherit;opacity:0;transition:opacity 0.2s;';
        const btnBase = 'padding:6px 20px;border-radius:20px;border:none;font-size:14px;font-family:inherit;cursor:pointer;font-weight:500;';
        overlay.innerHTML = `
            <div style="background:#fa94a6;color:#fff;padding:13px 12px;display:flex;align-items:center;font-size:15px;font-weight:500;flex-shrink:0;position:relative;">
                <button onclick="this.closest('div[style*=fixed]').remove()" style="position:absolute;left:12px;background:none;border:none;color:#fff;font-size:18px;cursor:pointer;padding:4px 8px;border-radius:8px;"><i class="fa-solid fa-chevron-left"></i></button>
                <span style="width:100%;text-align:center;">用户空间</span>
            </div>
            <div id="sp-scroll" style="flex:1;overflow-y:auto;scrollbar-color:rgba(0,0,0,0.2) transparent;"></div>
        `;
        document.body.appendChild(overlay);
        requestAnimationFrame(() => overlay.style.opacity = '1');

        const scroll = overlay.querySelector('#sp-scroll');
        scroll.innerHTML = '<div style="text-align:center;padding:40px;color:#999;">加载中...</div>';

        function closePanel() { overlay.remove(); }

        async function load() {
            try {
                const [profRes, momentsRes] = await Promise.all([
                    fetch('/web/api/space/' + uid),
                    fetch('/web/api/space/moments/' + uid)
                ]);
                const prof = await profRes.json();
                const momentsData = await momentsRes.json();
                if (prof.error) { scroll.innerHTML = '<div style="text-align:center;padding:40px;color:#999;">' + prof.error + '</div>'; return; }
                const u = prof.user;
                const relation = prof.relation || 'none';
                const avatar = u.avatar_url || defaultAvatar;

                let btnHtml = '';
                if (relation !== 'self') {
                    if (relation === 'friend') {
                        btnHtml = '<button style="' + btnBase + 'background:#fff;color:#fa94a6;border:1.5px solid #fa94a6;" onclick="spMsg()">私信</button>';
                    } else if (relation === 'pending_sent') {
                        btnHtml = '<button style="' + btnBase + 'background:#e0e0e0;color:#888;">已发送申请</button>';
                    } else if (relation === 'pending_received') {
                        btnHtml = '<button style="' + btnBase + 'background:#fa94a6;color:#fff;" onclick="spRespond(\'accept\')">接受好友</button>' +
                                 '<button style="' + btnBase + 'background:#e0e0e0;color:#666;" onclick="spRespond(\'reject\')">拒绝</button>';
                    } else {
                        btnHtml = '<button style="' + btnBase + 'background:#fa94a6;color:#fff;" onclick="spAddFriend()">加好友</button>';
                    }
                }

                function fmtTs(ts) {
                    if (!ts) return '';
                    const d = new Date(ts * 1000);
                    const pad = n => (n < 10 ? '0' : '') + n;
                    return d.getFullYear() + '-' + pad(d.getMonth()+1) + '-' + pad(d.getDate()) + ' ' + pad(d.getHours()) + ':' + pad(d.getMinutes());
                }

                let momentsHtml = '<div style="text-align:center;padding:40px;color:#999;">暂无动态</div>';
                const mom = momentsData.moments || [];
                if (mom.length > 0) {
                    momentsHtml = '<div style="padding:0 16px 20px;display:grid;grid-template-columns:repeat(auto-fill,minmax(280px,1fr));gap:10px;max-width:960px;margin:0 auto;">';
                    mom.forEach(m => {
                        let media = m.media_url ? '<div style="margin-top:8px;"><img src="' + m.media_url + '" style="width:100%;max-height:200px;object-fit:cover;border-radius:8px;cursor:pointer;" onerror="this.style.display=\'none\'"></div>' : '';
                        momentsHtml += '<div style="background:#fff;border-radius:12px;padding:14px 16px;border:1px solid var(--border);">' +
                            '<div style="font-size:11px;color:var(--secondary-text);margin-bottom:6px;">' + fmtTs(m.created_at) + '</div>' +
                            '<div style="font-size:14px;color:var(--text);line-height:1.6;white-space:pre-wrap;word-break:break-word;">' + (m.body || '') + '</div>' +
                            media +
                            (m.likes > 0 ? '<div style="font-size:12px;color:var(--secondary-text);margin-top:8px;">❤ ' + m.likes + '</div>' : '') +
                            '</div>';
                    });
                    momentsHtml += '</div>';
                }
                scroll.innerHTML =
                    '<div style="background:#fff;padding:28px 20px 20px;display:flex;flex-direction:column;align-items:center;border-bottom:1px solid var(--border);">' +
                        '<img src="' + avatar + '" style="width:80px;height:80px;border-radius:50%;object-fit:cover;margin-bottom:12px;background:var(--border);" onerror="this.src=\'' + defaultAvatar + '\'">' +
                        '<div style="font-size:20px;font-weight:600;color:var(--text);margin-bottom:4px;">' + (u.display_name || u.username) + '</div>' +
                        '<div style="font-size:12px;color:var(--secondary-text);margin-bottom:4px;">' + u.uid + '</div>' +
                        (u.bio ? '<div style="font-size:13px;color:var(--secondary-text);margin-bottom:12px;text-align:center;max-width:300px;">' + u.bio + '</div>' : '') +
                        '<div style="font-size:12px;color:var(--secondary-text);margin-bottom:10px;">注册于 ' + fmtTs(u.created_at) + '</div>' +
                        (btnHtml ? '<div style="display:flex;gap:10px;">' + btnHtml + '</div>' : '') +
                    '</div>' +
                    '<div style="font-size:14px;font-weight:600;color:var(--text);padding:14px 16px 8px;">TA 的动态</div>' +
                    momentsHtml;

                window.spMsg = function() {
                    closePanel();
                    if (currentConv && currentConv.key === 'direct:' + u.uid) return;
                    let found = contacts.friends.find(f => f.uid === u.uid);
                    if (found) {
                        switchConversation('direct', u.uid, found.name);
                    } else {
                        switchConversation('direct', u.uid, u.display_name || u.username);
                    }
                };
                window.spAddFriend = async function() {
                    try {
                        const r = await fetch('/web/api/space/add_friend', { method: 'POST', headers: {'Content-Type':'application/json'}, body: JSON.stringify({to_uid: uid}) });
                        const d = await r.json();
                        if (d.error) { alert(d.error); return; }
                        load();
                    } catch(e) { alert('请求失败'); }
                };
                window.spRespond = async function(action) {
                    try {
                        const r = await fetch('/web/api/space/respond_friend', { method: 'POST', headers: {'Content-Type':'application/json'}, body: JSON.stringify({uid: uid, action: action}) });
                        const d = await r.json();
                        if (d.error) { alert(d.error); return; }
                        load();
                    } catch(e) { alert('请求失败'); }
                };
            } catch(e) {
                scroll.innerHTML = '<div style="text-align:center;padding:40px;color:#999;">加载失败</div>';
            }
        }
        load();
    }

    let contextMenu = null;
    let contextMsgId = null;

    // 滚动加载历史消息状态
    const convOffsets = {};       // convKey → 当前已加载的消息数量（即下一个 offset）
    const convHasMore = {};       // convKey → boolean
    let isLoadingMore = false;
    let isLoadingMoreReqId = 0;

    function hideContextMenu() {
        if (contextMenu) {
            const el = contextMenu;
            el.classList.remove('show');
            el.addEventListener('transitionend', () => el.remove(), { once: true });
            setTimeout(() => el.remove(), 200);
            contextMenu = null;
            contextMsgId = null;
        }
    }

    emojiPlazaBtn.addEventListener('click', () => {
        showEmojiPlaza();
    });
    

    logoutBtn.addEventListener('click', () => {
        window.location.href = '/web/logout';
    });

    aboutBtn.addEventListener('click', () => {
        window.location.href = '/static/about.html';
    });

    async function loadContacts() {
        try {
            const res = await fetch('/web/api/contacts');
            const data = await res.json();
            if (data.error) { alert(data.error); return; }
            contacts = data;
            renderContacts();
        } catch (e) { console.error(e); }
    }

    // WebSocket 连接
    let ws = null;
    let wsReconnectTimer = null;

    async function initWebSocket() {
        try {
            const res = await fetch('/web/api/ws_token');
            const data = await res.json();
            if (!data.token) return;
            const protocol = window.location.protocol === 'https:' ? 'wss:' : 'ws:';
            const wsUrl = `${protocol}//${window.location.host}/v1/ws?token=${encodeURIComponent(data.token)}`;
            ws = new WebSocket(wsUrl);
            ws.onopen = () => {
                console.log('[WS] connected');
            };
            ws.onmessage = (event) => {
                try {
                    const msg = JSON.parse(event.data);
                    handleWsMessage(msg);
                } catch (e) {
                    console.error('[WS] parse error:', e);
                }
            };
            ws.onclose = () => {
                console.log('[WS] closed');
                ws = null;
                if (wsReconnectTimer) clearTimeout(wsReconnectTimer);
                wsReconnectTimer = setTimeout(initWebSocket, 3000);
            };
            ws.onerror = (e) => {
                console.error('[WS] error:', e);
            };
        } catch (e) {
            console.error('[WS] init failed:', e);
            if (wsReconnectTimer) clearTimeout(wsReconnectTimer);
            wsReconnectTimer = setTimeout(initWebSocket, 5000);
        }
    }

    const notifySound = new Audio('/uploads/web/notice.mp3');

    function handleWsMessage(msg) {
        if (!msg || !msg.type) return;
        if (msg.type === 'direct_message') {
            const d = msg.data || {};
            const fromUid = d.from_uid || '';
            if (fromUid.toUpperCase() === myUid.toUpperCase()) return;
            notifySound.play().catch(() => {});
            const convKey = `direct:${fromUid}`;
            const msgObj = {
                id: d.id,
                from_uid: fromUid,
                from_name: d.from_name || fromUid,
                from_avatar: d.from_avatar || '',
                body: d.body || '',
                msg_type: d.msg_type || 'text',
                media_url: d.media_url || null,
                thumb_url: d.thumb_url || null,
                created_at: d.created_at,
            };
            appendMessage(msgObj, convKey, seenMsgIds[convKey]);
            if (currentConv && currentConv.key === convKey) {
                scrollToBottom(true);
                fetch(`/web/api/mark_read/direct/${fromUid}`, { method: 'PUT' });
            }
        } else if (msg.type === 'group_message') {
            const d = msg.data || {};
            const groupId = d.group_id || '';
            const fromUid = d.from_uid || '';
            if (fromUid.toUpperCase() === myUid.toUpperCase()) return;
            notifySound.play().catch(() => {});
            const convKey = `group:${groupId}`;
            const msgObj = {
                id: d.id,
                from_uid: fromUid,
                from_name: d.from_name || fromUid,
                from_avatar: d.from_avatar || '',
                body: d.body || '',
                msg_type: d.msg_type || 'text',
                media_url: d.media_url || null,
                thumb_url: d.thumb_url || null,
                created_at: d.created_at,
                group_id: groupId,
            };
            appendMessage(msgObj, convKey, seenMsgIds[convKey]);
            if (currentConv && currentConv.key === convKey) {
                scrollToBottom(true);
                fetch(`/web/api/mark_read/group/${groupId}`, { method: 'PUT' });
            }
        }
    }

    function renderContacts() {
        contactList.innerHTML = '';
        if (contacts.groups.length > 0) {
            const sep = document.createElement('div');
            sep.style.cssText = 'padding:8px 15px;font-size:11px;color:#999;font-weight:500;';
            sep.textContent = '群聊';
            contactList.appendChild(sep);
            contacts.groups.forEach(g => {
                const div = createContactItem(g.id, g.name, 'group');
                contactList.appendChild(div);
            });
        }
        if (contacts.friends.length > 0) {
            const sep = document.createElement('div');
            sep.style.cssText = 'padding:8px 15px;font-size:11px;color:#999;font-weight:500;';
            sep.textContent = '私聊';
            contactList.appendChild(sep);
            contacts.friends.forEach(f => {
                const div = createContactItem(f.uid, f.name, 'direct');
                contactList.appendChild(div);
            });
        }
    }

    function createContactItem(id, name, type) {
        const div = document.createElement('div');
        div.className = 'contact-item';
        div.dataset.convKey = type + ':' + id;
        div.innerHTML = `<div class="name">${escapeHtml(name)}</div><div class="uid">${escapeHtml(id)}</div>`;
        div.addEventListener('click', (e) => switchConversation(type, id, name, e));
        return div;
    }

    async function switchConversation(type, id, name, event) {
        document.querySelectorAll('.contact-item').forEach(el => el.classList.remove('active'));
        if (event && event.currentTarget) {
            event.currentTarget.classList.add('active');
        }

        if (isMobile()) {
            sidebar.classList.add('collapsed');
            expandChat();
        }
    
        const convKey = `${type}:${id}`;
        currentConv = { type, id, name, key: convKey };
    
        // 保存到 localStorage，下次自动恢复
        try {
            localStorage.setItem('lastConversation', convKey);
        } catch (e) {}
    
        chatHeader.querySelector('.chat-title').textContent = name;
        messagesContainer.innerHTML = '';
        lastRenderedMsg = null;
        lastRenderedTs = 0;

        // 重置滚动加载状态
        convOffsets[convKey] = 0;
        convHasMore[convKey] = true;

        // 清除该会话的已见消息记录，确保历史消息重新显示
        if (seenMsgIds[convKey]) {
            delete seenMsgIds[convKey];
        }

        // 移除旧的滚动监听
        messagesContainer._scrollHandler && messagesContainer.removeEventListener('scroll', messagesContainer._scrollHandler);
    
        pendingQuote = null;
        quotePreview.style.display = 'none';
    
        const PAGE_SIZE = 100;
        const reqId = ++switchRequestId;
    
        try {
            const res = await fetch(`/web/api/messages/${type}/${id}?limit=${PAGE_SIZE}&offset=0`);
            const data = await res.json();
    
            if (reqId !== switchRequestId) return;
    
            if (data.error) {
                console.error(data.error);
                messagesContainer.innerHTML = '<div class="system-msg">加载消息失败，请刷新重试</div>';
                return;
            }
    
            const msgs = data.messages || [];
    
            // 更新偏移量
            convOffsets[convKey] = msgs.length;
            convHasMore[convKey] = msgs.length >= PAGE_SIZE;
    
            // 重新创建该会话的已见集合
            if (!seenMsgIds[convKey]) {
                seenMsgIds[convKey] = new Set();
            }
            const currentSeen = seenMsgIds[convKey];
    
            msgs.forEach(msg => {
                appendMessage(msg, convKey, currentSeen);
            });
            messagesContainer.classList.remove('fade-in');
            void messagesContainer.offsetWidth;
            messagesContainer.classList.add('fade-in');
            scrollToBottom(true);
            await fetch(`/web/api/mark_read/${type}/${id}`, { method: 'PUT' });

            // 设置滚动到顶部加载更多
            messagesContainer._scrollHandler = async () => {
                if (!convHasMore[convKey] || isLoadingMore) return;
                // 滚动到顶部附近（留 5px 容差）
                if (messagesContainer.scrollTop > 5) return;

                isLoadingMore = true;
                const loadReqId = ++isLoadingMoreReqId;
                const currentHeight = messagesContainer.scrollHeight;
                try {
                    const res = await fetch(`/web/api/messages/${type}/${id}?limit=${PAGE_SIZE}&offset=${convOffsets[convKey]}`);
                    const data = await res.json();
                    if (loadReqId !== isLoadingMoreReqId) return;
                    if (data.error) return;

                    const olderMsgs = data.messages || [];
                    if (olderMsgs.length === 0) {
                        convHasMore[convKey] = false;
                        return;
                    }
                    convOffsets[convKey] += olderMsgs.length;
                    convHasMore[convKey] = olderMsgs.length >= PAGE_SIZE;

                    // 记录展开时的滚动位置
                    const scrollBottom = messagesContainer.scrollHeight - messagesContainer.scrollTop;

                    // 创建一个 fragment 批量插入
                    const frag = document.createDocumentFragment();
                    let prevTs = 0;
                    olderMsgs.forEach((msg) => {
                        const msgTs = msg.created_at || 0;
                        if (prevTs && msgTs && (msgTs - prevTs) > 300) {
                            frag.appendChild(createTimeSeparator(msgTs));
                        }
                        const el = createMessageElement(msg, convKey, seenMsgIds[convKey]);
                        if (el) frag.appendChild(el);
                        prevTs = msgTs;
                    });
                    messagesContainer.insertBefore(frag, messagesContainer.firstChild);

                    // 调整滚动位置，保持可视区域不变
                    messagesContainer.scrollTop = messagesContainer.scrollHeight - scrollBottom;
                } catch (e) {
                    console.error(e);
                } finally {
                    isLoadingMore = false;
                }
            };
            messagesContainer.addEventListener('scroll', messagesContainer._scrollHandler);
        } catch (e) {
            console.error(e);
            if (reqId === switchRequestId) {
                messagesContainer.innerHTML = '<div class="system-msg">网络错误，无法加载消息</div>';
            }
        }
    }

    /**
     * 根据消息数据创建 DOM 元素（不插入、不去重、不合并）
     */
    function createMessageElement(msg, convKey, currentSeen) {
        if (!msg || !msg.id) return null;

        const fromUid = msg.from_uid || msg.sender_uid || '';
        const msgType = msg.msg_type || 'text';

        if (msgType === 'system') {
            const div = document.createElement('div');
            div.className = 'time-separator';
            div.textContent = msg.body || '';
            div.dataset.msgId = msg.id;
            div.dataset.fromUid = fromUid;
            div.dataset.msgType = 'system';
            return div;
        }

        const isSelfByUid = fromUid && myUid && fromUid.toUpperCase() === myUid.toUpperCase();
        const isSelfByFlag = msg.is_me === true || msg.isSelf === true;
        const isSelf = isSelfByUid || isSelfByFlag;

        const sender = isSelf ? '' : (msg.from_name || msg.sender_name || msg.display_name || fromUid || '未知用户');
        const time = new Date(msg.created_at * 1000).toLocaleTimeString('zh-CN', { hour12: false });
        let content = '';

        if (msgType === 'image') {
            const mediaUrl = msg.media_url || '';
            const imgEl = document.createElement('img');
            imgEl.src = mediaUrl;
            imgEl.style.cssText = 'max-width:200px;max-height:200px;border-radius:8px;cursor:pointer;';
            imgEl.className = 'chat-image';
            imgEl.onclick = () => openImageViewer(mediaUrl);
            imgEl.onerror = function() { this.style.display='none'; };

            const msgDiv = document.createElement('div');
            msgDiv.className = `message ${isSelf ? 'self' : 'other'} bare-image`;
            msgDiv.dataset.msgId = msg.id;
            msgDiv.dataset.fromUid = fromUid;
            msgDiv.dataset.fromName = sender || '';
            msgDiv.dataset.msgType = msgType;

            if (!isSelf) {
                const avatarUrl = msg.from_avatar || msg.sender_avatar || msg.avatar_url || 'https://gwebcdn260523.pages.dev/v1/static/default-avatar.png';
                const avatarImg = document.createElement('img');
                avatarImg.src = avatarUrl;
                avatarImg.className = 'msg-avatar';
                avatarImg.onerror = () => { avatarImg.src = 'https://gwebcdn260523.pages.dev/v1/static/default-avatar.png'; };
                avatarImg.addEventListener('click', (e) => {
                    e.stopPropagation();
                    if (fromUid) openSpacePanel(fromUid);
                });
                msgDiv.appendChild(avatarImg);
            }

            if (!isSelf && sender) {
                const senderDiv = document.createElement('div');
                senderDiv.className = 'message-sender';
                senderDiv.textContent = sender;
                msgDiv.appendChild(senderDiv);
            }

            msgDiv.appendChild(imgEl);

            const timeDiv = document.createElement('div');
            timeDiv.className = 'message-time';
            timeDiv.textContent = time;
            msgDiv.appendChild(timeDiv);

            msgDiv.dataset.rawBody = JSON.stringify(msg) || '';
            return msgDiv;
        }

        if (msgType === 'video') {
            content = `<video controls style="max-width:200px;"><source src="${msg.media_url || ''}"></video>`;
        } else if (msgType === 'audio') {
            content = `<audio controls style="max-width:200px;" src="${msg.media_url || ''}"></audio>`;
        } else if (msgType === 'resource') {
            // 支持嵌套 v2 JSON body（如音乐分享等）
            let fileName = '';
            let displayText = '';
            if (msg.body && msg.body.trim().startsWith('{')) {
                try {
                    const obj = JSON.parse(msg.body);
                    if (obj.v === 2) {
                        displayText = obj.text || '';
                        if (obj.quote) {
                            const quote = obj.quote;
                            displayText = `<div class="quote-block" data-quoted-id="${escapeHtml(quote.id || '')}">
                                <div class="quote-sender">${escapeHtml(quote.from_name || quote.from_uid || '')}</div>
                                <div>${escapeHtml(quote.text || '')}</div>
                            </div>` + (displayText ? `<div style="white-space: pre-wrap; word-break: break-word;">${displayText}</div>` : '');
                        }
                        if (obj.mentions && Array.isArray(obj.mentions)) {
                            obj.mentions.forEach(m => {
                                const name = m.name || m.uid;
                                const regex = new RegExp(`@${escapeRegExp(name)}`, 'g');
                                displayText = displayText.replace(regex,
                                    `<span class="mention-highlight" data-uid="${escapeHtml(m.uid || '')}">@${escapeHtml(name)}</span>`);
                            });
                        }
                        displayText = displayText.replace(/\n/g, '<br>');
                    } else {
                        fileName = msg.body;
                    }
                } catch (e) {
                    fileName = msg.body;
                }
            } else {
                fileName = msg.body || '';
            }
            if (!fileName && msg.media_url) {
                const urlParts = msg.media_url.split('/');
                fileName = decodeURIComponent(urlParts.pop()) || '文件';
            }
            const fileCardHtml = `<div class="file-card">
                <div class="file-info">
                    <div class="file-name">${escapeHtml(fileName)}</div>
                </div>
                <a href="${msg.media_url}" target="_blank" class="file-download-btn">⬇</a>
            </div>`;
            content = displayText
                ? `<div style="margin-bottom:6px;">${displayText}</div>${fileCardHtml}`
                : fileCardHtml;
        } else if (msgType === 'voice') {
            if (msg.media_url) {
                content = `
                    <div class="voice-message" data-url="${msg.media_url}">
                        <div class="voice-top-row">
                            <button class="voice-play-btn">▶</button>
                            <div class="voice-wave" data-url="${msg.media_url}">
                                <div class="voice-wave-bg" style="width:0%"></div>
                                <div class="voice-wave-bars">${'<span></span>'.repeat(20)}</div>
                            </div>
                            <span class="voice-duration">0:00</span>
                        </div>
                        <audio preload="metadata" src="${msg.media_url}"></audio>
                    </div>`;
            } else {
                const dur = (msg.duration_ms || 0) / 1000;
                const mins = Math.floor(dur / 60);
                const secs = Math.floor(dur % 60);
                const durStr = dur ? mins + ':' + (secs < 10 ? '0' : '') + secs : '0:00';
                content = `[语音 ${durStr}]`;
            }
        } else if (msgType === 'file' || (msg.media_url && msgType !== 'text')) {
            const fileUrl = msg.media_url || '';
            const fileName = msg.body || fileUrl.split('/').pop();
            content = `<a href="${fileUrl}" target="_blank" class="file-download-btn" style="color:var(--link-other);">📎 ${escapeHtml(fileName)}</a>`;
        } else if (msgType === 'text') {
            let body = msg.body || '';
            let quoteHtml = '';

            if (body.trim().startsWith('{')) {
                try {
                    const obj = JSON.parse(body);
                    if (obj.v === 2) {
                        let textBody = obj.text || '';
                        if (obj.quote) {
                            const quote = obj.quote;
                            quoteHtml = `
                                <div class="quote-block" data-quoted-id="${escapeHtml(quote.id || '')}">
                                    <div class="quote-sender">${escapeHtml(quote.from_name || quote.from_uid || '')}</div>
                                    <div>${escapeHtml(quote.text || '')}</div>
                                </div>`;
                        }
                        if (obj.mentions && Array.isArray(obj.mentions)) {
                            obj.mentions.forEach(m => {
                                const name = m.name || m.uid;
                                const regex = new RegExp(`@${escapeRegExp(name)}`, 'g');
                                textBody = textBody.replace(regex,
                                    `<span class="mention-highlight" data-uid="${escapeHtml(m.uid || '')}">@${escapeHtml(name)}</span>`);
                            });
                        }
                        textBody = textBody.replace(/\n/g, '<br>');
                        content = quoteHtml + (textBody ? `<div style="white-space: pre-wrap; word-break: break-word;">${textBody}</div>` : '');
                    } else {
                        body = escapeHtml(body);
                        body = body.replace(/\n/g, '<br>');
                        content = `<div style="white-space: pre-wrap; word-break: break-word;">${body}</div>`;
                    }
                } catch (e) {
                    body = escapeHtml(body);
                    body = body.replace(/\n/g, '<br>');
                    content = `<div style="white-space: pre-wrap; word-break: break-word;">${body}</div>`;
                }
            } else {
                body = escapeHtml(body);
                body = body.replace(/\n/g, '<br>');
                content = `<div style="white-space: pre-wrap; word-break: break-word;">${body}</div>`;
            }
        } else if (msgType === 'red_packet') {
            let packetData = null;
            try {
                if (msg.body && msg.body.trim().startsWith('{')) {
                    packetData = JSON.parse(msg.body);
                }
            } catch (e) {}
            if (packetData && packetData.packet_id) {
                const packetId = packetData.packet_id;
                const totalAmount = packetData.total_amount || '?';
                const totalCount = packetData.total_count || '?';
                content = `
                    <div class="red-packet-card" data-packet-id="${escapeHtml(packetId)}" data-claimed="false">
                        <div class="rp-icon">🧧</div>
                        <div class="rp-info">
                            <div class="rp-title">红包</div>
                            <div class="rp-desc">总额 ${totalAmount} · ${totalCount}个</div>
                        </div>
                        <div class="rp-status">点击领取</div>
                    </div>`;
            } else {
                content = `[红包] ${escapeHtml(msg.body || '')}`;
            }
        } else {
            content = `[${msgType}] ${escapeHtml(msg.body || '')}`;
        }

        const msgDiv = document.createElement('div');
        msgDiv.className = `message ${isSelf ? 'self' : 'other'}`;
        msgDiv.dataset.msgId = msg.id;
        msgDiv.dataset.fromUid = fromUid;
        msgDiv.dataset.fromName = sender || '';
        msgDiv.dataset.msgType = msgType;

        if (!isSelf) {
            const avatarUrl = msg.from_avatar || msg.sender_avatar || msg.avatar_url || 'https://gwebcdn260523.pages.dev/v1/static/default-avatar.png';
            const avatarImg = document.createElement('img');
            avatarImg.src = avatarUrl;
            avatarImg.className = 'msg-avatar';
            avatarImg.onerror = () => { avatarImg.src = 'https://gwebcdn260523.pages.dev/v1/static/default-avatar.png'; };
            avatarImg.addEventListener('click', (e) => {
                e.stopPropagation();
                const uid = msg.from_uid;
                if (uid) {
                    openSpacePanel(uid);
                }
            });
            msgDiv.appendChild(avatarImg);
        }

        if (!isSelf && sender) {
            const senderDiv = document.createElement('div');
            senderDiv.className = 'message-sender';
            senderDiv.textContent = sender;
            msgDiv.appendChild(senderDiv);
        }

        const bubble = document.createElement('div');
        bubble.className = 'message-bubble';
        bubble.innerHTML = content;
        msgDiv.appendChild(bubble);

        const timeDiv = document.createElement('div');
        timeDiv.className = 'message-time';
        timeDiv.textContent = time;
        msgDiv.appendChild(timeDiv);

        if (bubble) {
            msgDiv.dataset.plainText = bubble.innerText;
        }
        msgDiv.dataset.rawBody = JSON.stringify(msg) || '';
        return msgDiv;
    }

    function appendMessage(msg, convKey, currentSeen) {
        if (!msg || !msg.id) return;
        if (!convKey) convKey = currentConv?.key;
        if (!convKey) return;
    
        if (!currentSeen) {
            if (!seenMsgIds[convKey]) seenMsgIds[convKey] = new Set();
            currentSeen = seenMsgIds[convKey];
        }
    
        if (currentSeen.has(msg.id)) {
            return;
        }

        const fromUid = msg.from_uid || '';
        const isPlainText = (msg.msg_type || 'text') === 'text' && !(msg.body || '').trim().startsWith('{');
        const msgTs = msg.created_at || 0;

        // 检查是否为连续消息（同发送者、5分钟内、同会话）
        const isConsecutive = lastRenderedMsg &&
            lastRenderedMsg.convKey === convKey &&
            lastRenderedMsg.from_uid === fromUid &&
            msgTs && lastRenderedTs && (msgTs - lastRenderedTs) <= 300;

        // 检查时间间隔，超过5分钟插入时间分隔符
        if (msgTs && lastRenderedTs && (msgTs - lastRenderedTs) > 300) {
            const sep = createTimeSeparator(msgTs);
            messagesContainer.appendChild(sep);
        }

        // 尝试合并连续的同发送者纯文本消息（保留旧逻辑但禁用）
        if (false && mergeMessages && lastRenderedMsg && 
            lastRenderedMsg.convKey === convKey && 
            lastRenderedMsg.from_uid === fromUid &&
            isPlainText &&
            !msg.burn_after_seconds) {
            const time = new Date(msg.created_at * 1000).toLocaleTimeString('zh-CN', { hour12: false });
            const container = lastRenderedMsg.element.querySelector('.message-content');
            const oldTime = container.querySelector('.message-time');
            if (oldTime) oldTime.remove();
            const content = msg.body ? escapeHtml(msg.body).replace(/\n/g, '<br>') : '';
            const bubble = document.createElement('div');
            bubble.className = 'message-bubble merged';
            bubble.innerHTML = content;
            container.appendChild(bubble);
            const newTime = document.createElement('div');
            newTime.className = 'message-time';
            newTime.textContent = time;
            container.appendChild(newTime);
            
            currentSeen.add(msg.id);
            lastRenderedTs = msgTs;
            return;
        }
    
        const msgDiv = createMessageElement(msg, convKey, currentSeen);
        if (!msgDiv) return;

        // 添加连续消息标记
        if (isConsecutive) {
            msgDiv.classList.add('consecutive');
            // 标记上一条消息为连续组的首条
            if (lastRenderedMsg && lastRenderedMsg.element) {
                lastRenderedMsg.element.classList.add('consecutive-first');
            }
        } else {
            // 非连续消息，移除首条标记
            if (lastRenderedMsg && lastRenderedMsg.element) {
                lastRenderedMsg.element.classList.remove('consecutive-first');
            }
        }
    
        messagesContainer.appendChild(msgDiv);
    
        currentSeen.add(msg.id);
        lastRenderedMsg = { convKey, from_uid: fromUid, element: msgDiv };
        lastRenderedTs = msgTs;
    }

    function scrollToBottom(force = false) {
        if (force) {
            // 强制滚动到底部（切换会话/发送消息后使用）
            messagesContainer.scrollTop = messagesContainer.scrollHeight;
            return;
        }
        // 只在用户已近底部时才自动滚动，避免强制拉到最下方
        const threshold = messagesContainer.clientHeight / 2;
        const atBottom = messagesContainer.scrollHeight - messagesContainer.scrollTop - messagesContainer.clientHeight < threshold;
        if (atBottom) {
            messagesContainer.scrollTop = messagesContainer.scrollHeight;
        }
    }

    // 点击引用块跳转到被引用的消息
    messagesContainer.addEventListener('click', function(e) {
        const quoteBlock = e.target.closest('.quote-block');
        if (!quoteBlock) return;
        const quotedId = quoteBlock.dataset.quotedId;
        if (!quotedId) return;

        const targetMsg = document.querySelector(`.message[data-msg-id="${CSS.escape(quotedId)}"]`);
        if (!targetMsg) return;

        targetMsg.scrollIntoView({ behavior: 'smooth', block: 'center' });
    });

    // 语音消息播放/暂停
    messagesContainer.addEventListener('click', function(e) {
        const playBtn = e.target.closest('.voice-play-btn');
        if (!playBtn) return;
        const voiceMsg = playBtn.closest('.voice-message');
        if (!voiceMsg) return;
        const audio = voiceMsg.querySelector('audio');
        if (!audio) return;

        if (audio.paused) {
            // 暂停其他播放中的语音
            document.querySelectorAll('.voice-message audio').forEach(function(a) {
                if (a !== audio && !a.paused) a.pause();
            });
            audio.play().catch(function() {});
        } else {
            audio.pause();
        }
    });

    // 音频元数据加载完成 → 显示总时长
    messagesContainer.addEventListener('loadedmetadata', function(e) {
        if (e.target.tagName !== 'AUDIO') return;
        const voiceMsg = e.target.closest('.voice-message');
        if (!voiceMsg) return;
        const audio = e.target;
        const dur = audio.duration;
        if (!isFinite(dur)) return;
        const durEl = voiceMsg.querySelector('.voice-duration');
        if (durEl && !voiceMsg.classList.contains('is-playing')) {
            durEl.textContent = formatTime(dur);
        }
    }, true);

    // 播放进度更新 → 波形背景高亮(0-100%) + 秒数
    messagesContainer.addEventListener('timeupdate', function(e) {
        if (e.target.tagName !== 'AUDIO') return;
        const voiceMsg = e.target.closest('.voice-message');
        if (!voiceMsg) return;
        const audio = e.target;
        const dur = audio.duration;
        if (!isFinite(dur) || dur <= 0) return;
        const wave = voiceMsg.querySelector('.voice-wave');
        if (wave && !wave.classList.contains('dragging')) {
            const pct = (audio.currentTime / dur) * 100;
            wave.querySelector('.voice-wave-bg').style.width = pct + '%';
        }
        const durEl = voiceMsg.querySelector('.voice-duration');
        if (durEl) durEl.textContent = formatTime(audio.currentTime) + '/' + formatTime(dur);
    }, true);

    // 在波形上拖拽/点击跳转
    var voiceDrag = { active: false, wave: null };

    messagesContainer.addEventListener('mousedown', function(e) {
        const wave = e.target.closest('.voice-wave');
        if (!wave) return;
        const voiceMsg = wave.closest('.voice-message');
        if (!voiceMsg) return;
        const audio = voiceMsg.querySelector('audio');
        if (!audio) return;
        voiceDrag.active = true;
        voiceDrag.wave = wave;
        wave.classList.add('dragging');
        seekWave(wave, audio, e.clientX);
        e.preventDefault();
    });

    document.addEventListener('mousemove', function(e) {
        if (!voiceDrag.active || !voiceDrag.wave) return;
        const voiceMsg = voiceDrag.wave.closest('.voice-message');
        if (!voiceMsg) return;
        const audio = voiceMsg.querySelector('audio');
        if (!audio) return;
        seekWave(voiceDrag.wave, audio, e.clientX);
    });

    document.addEventListener('mouseup', function() {
        if (voiceDrag.active && voiceDrag.wave) {
            voiceDrag.wave.classList.remove('dragging');
            voiceDrag.active = false;
            voiceDrag.wave = null;
        }
    });

    function seekWave(wave, audio, clientX) {
        const rect = wave.getBoundingClientRect();
        var ratio = (clientX - rect.left) / rect.width;
        if (ratio < 0) ratio = 0;
        if (ratio > 1) ratio = 1;
        const dur = audio.duration;
        if (!isFinite(dur) || dur <= 0) return;
        audio.currentTime = ratio * dur;
        wave.querySelector('.voice-wave-bg').style.width = (ratio * 100) + '%';
        const durEl = wave.closest('.voice-message').querySelector('.voice-duration');
        if (durEl) durEl.textContent = formatTime(ratio * dur) + '/' + formatTime(dur);
    }

    // 播放开始 → 更新图标/启动波形可视化
    messagesContainer.addEventListener('play', function(e) {
        if (e.target.tagName !== 'AUDIO') return;
        const voiceMsg = e.target.closest('.voice-message');
        if (!voiceMsg) return;
        voiceMsg.classList.add('is-playing');
        voiceMsg.querySelector('.voice-play-btn').textContent = '⏸';
        const wave = voiceMsg.querySelector('.voice-wave');
        if (wave) startVoiceVis(e.target, wave);
        currentlyPlayingVoiceMsg = voiceMsg;
        document.getElementById('nowPlayingBtn').style.display = '';
    }, true);

    // 暂停 → 恢复图标/波形
    messagesContainer.addEventListener('pause', function(e) {
        if (e.target.tagName !== 'AUDIO') return;
        const voiceMsg = e.target.closest('.voice-message');
        if (!voiceMsg) return;
        voiceMsg.querySelector('.voice-play-btn').textContent = '▶';
        stopVoiceVis();
        currentlyPlayingVoiceMsg = null;
        document.getElementById('nowPlayingBtn').style.display = 'none';
        // 播放结束后恢复显示总时长
        const audio = e.target;
        if (audio.currentTime >= audio.duration - 0.1 || audio.currentTime === 0) {
            voiceMsg.classList.remove('is-playing');
            const durEl = voiceMsg.querySelector('.voice-duration');
            if (durEl && isFinite(audio.duration)) {
                durEl.textContent = formatTime(audio.duration);
            }
        }
    }, true);

    // 播放结束 → 重置
    messagesContainer.addEventListener('ended', function(e) {
        if (e.target.tagName !== 'AUDIO') return;
        const voiceMsg = e.target.closest('.voice-message');
        if (!voiceMsg) return;
        voiceMsg.classList.remove('is-playing');
        voiceMsg.querySelector('.voice-play-btn').textContent = '▶';
        const wave = voiceMsg.querySelector('.voice-wave');
        if (wave) { wave.querySelector('.voice-wave-bg').style.width = '0%'; }
        stopVoiceVis();
        const audio = e.target;
        const durEl = voiceMsg.querySelector('.voice-duration');
        if (durEl && isFinite(audio.duration)) durEl.textContent = formatTime(audio.duration);
        // 播放结束 → 隐藏正在播放图标
        currentlyPlayingVoiceMsg = null;
        document.getElementById('nowPlayingBtn').style.display = 'none';
    }, true);

    var currentlyPlayingVoiceMsg = null;

    document.getElementById('nowPlayingBtn').addEventListener('click', function() {
        if (currentlyPlayingVoiceMsg && currentlyPlayingVoiceMsg.isConnected) {
            currentlyPlayingVoiceMsg.scrollIntoView({ behavior: 'smooth', block: 'center' });
        }
    });

    // === 语音波形动画（消息内） ===
    var voiceVisCtx = null;
    var voiceVisCache = {};
    var voiceVis = { rafId: null, audio: null, bars: null };

    function stopVoiceVis() {
        if (voiceVis.rafId) { cancelAnimationFrame(voiceVis.rafId); voiceVis.rafId = null; }
        if (voiceVis.bars) {
            voiceVis.bars.forEach(function(b) { b.style.height = ''; b.style.opacity = ''; });
        }
        voiceVis.audio = null;
        voiceVis.bars = null;
    }

    async function startVoiceVis(audio, wave) {
        stopVoiceVis();

        if (!voiceVisCtx) {
            voiceVisCtx = new (window.AudioContext || window.webkitAudioContext)();
            if (voiceVisCtx.state === 'suspended') voiceVisCtx.resume();
        }

        var url = audio.currentSrc || audio.src;
        if (!url || url.startsWith('blob:')) return;

        var cached = voiceVisCache[url];
        if (!cached) {
            try {
                var resp = await fetch(url);
                var buf = await resp.arrayBuffer();
                var decoded = await voiceVisCtx.decodeAudioData(buf);
                cached = { data: decoded.getChannelData(0), sampleRate: decoded.sampleRate };
                voiceVisCache[url] = cached;
            } catch (e) {
                console.warn('语音波形解码失败:', e);
                return;
            }
        }

        var pcm = cached.data;
        var bars = wave.querySelectorAll('.voice-wave-bars span');
        if (!bars.length) return;

        voiceVis.audio = audio;
        voiceVis.bars = bars;
        var totalSamples = pcm.length;
        var barCount = bars.length;

        function render() {
            if (audio.paused || audio.ended) { stopVoiceVis(); return; }

            var ct = audio.currentTime;
            var dur = audio.duration;
            if (!isFinite(dur) || dur <= 0) {
                voiceVis.rafId = requestAnimationFrame(render);
                return;
            }

            var windowLen = Math.min(totalSamples, Math.floor(totalSamples * 1.5 / dur));
            var midSample = (ct / dur) * totalSamples;
            var startSample = Math.max(0, Math.floor(midSample - windowLen * 0.4));
            var endSample = Math.min(totalSamples, startSample + windowLen);
            if (endSample - startSample < barCount) {
                endSample = Math.min(totalSamples, startSample + barCount);
            }

            var samplesPerBar = Math.max(1, Math.floor((endSample - startSample) / barCount));

            for (var i = 0; i < barCount; i++) {
                var sStart = startSample + i * samplesPerBar;
                var sEnd = Math.min(endSample, sStart + samplesPerBar);
                var sum = 0;
                for (var s = sStart; s < sEnd; s++) {
                    sum += Math.abs(pcm[s]);
                }
                var avg = sum / (sEnd - sStart);
                var val = Math.min(1, avg * 3);
                val = Math.max(0.05, val);
                bars[i].style.height = (val * 22) + 'px';
                bars[i].style.opacity = Math.max(0.2, val * 0.8 + 0.2);
            }

            voiceVis.rafId = requestAnimationFrame(render);
        }

        render();
    }

    // 格式化秒数为 mm:ss
    function formatTime(seconds) {
        if (!isFinite(seconds) || seconds < 0) return '0:00';
        var s = Math.floor(seconds);
        var m = Math.floor(s / 60);
        var sec = s % 60;
        return m + ':' + (sec < 10 ? '0' : '') + sec;
    }


    async function sendMessage(body, msgType = 'text', mediaUrl = null, thumbUrl = null) {
        if (!currentConv) return;
    
        // 如果文本包含换行且不是引用消息，自动转成 v2 格式保留换行
        if (msgType === 'text' && body.includes('\n') && !pendingQuote) {
            body = JSON.stringify({ v: 2, text: body });
        }
    
        if (pendingQuote && msgType === 'text' && body.trim()) {
            const quotePayload = {
                v: 2,
                text: body,
                quote: pendingQuote
            };
            body = JSON.stringify(quotePayload);
            msgType = 'text';
        }
    
        const payload = {
            type: currentConv.type,
            target_id: currentConv.id,
            body: body,
            msg_type: msgType,
            burn_after_seconds: 0,
            media_url: mediaUrl || null,
            thumb_url: thumbUrl || null
        };

        // 立即显示发送中消息（半透明）
        const tempId = 'temp_' + Date.now();
        const tempMsg = {
            id: tempId,
            from_uid: myUid,
            from_name: myName,
            from_avatar: myAvatar || '',
            body: msgType === 'text' ? body : '',
            msg_type: msgType,
            media_url: mediaUrl,
            thumb_url: thumbUrl,
            created_at: Math.floor(Date.now() / 1000),
        };
        if (currentConv.type === 'group') {
            tempMsg.group_id = currentConv.id;
        }
        appendMessage(tempMsg, currentConv.key, seenMsgIds[currentConv.key]);
        scrollToBottom(true);

        // 找到临时消息元素，设置半透明
        const tempEl = messagesContainer.querySelector(`[data-msg-id="${tempId}"]`);
        if (tempEl) {
            tempEl.style.opacity = '0.5';
        }

        try {
            const res = await fetch('/web/api/send', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });
            const data = await res.json();
            if (data.error) {
                alert('发送失败: ' + data.error);
                // 移除临时消息
                if (tempEl) tempEl.remove();
                seenMsgIds[currentConv.key]?.delete(tempId);
                return;
            }
            if (pendingQuote) {
                pendingQuote = null;
                quotePreview.style.display = 'none';
            }
            // 后端直接返回消息对象，或包在 message 字段里
            const msg = data.message || data;
            if (msg && msg.id) {
                // 替换临时消息
                if (tempEl) {
                    const newEl = createMessageElement(msg, currentConv.key, seenMsgIds[currentConv.key]);
                    if (newEl) {
                        tempEl.replaceWith(newEl);
                    }
                    seenMsgIds[currentConv.key]?.delete(tempId);
                    seenMsgIds[currentConv.key]?.add(msg.id);
                } else {
                    appendMessage(msg, currentConv.key, seenMsgIds[currentConv.key]);
                    scrollToBottom(true);
                }
            }
        } catch (e) {
            console.error(e);
            // 发送失败，移除临时消息
            if (tempEl) tempEl.remove();
            seenMsgIds[currentConv.key]?.delete(tempId);
        }
    }

    messageInput.addEventListener('keydown', function (e) {
        if (e.key === 'Enter' && !e.shiftKey) {
            e.preventDefault();
            const text = this.value.trim();
            if (text) {
                sendMessage(text);
                this.value = '';
                this.style.height = 'auto';
            }
        }
    });

    messageInput.addEventListener('input', function () {
        this.style.height = 'auto';
        const maxH = Math.floor(window.innerHeight * 0.35);
        this.style.height = Math.min(this.scrollHeight, maxH) + 'px';
    });

    // 图片粘贴の上传判定
    messageInput.addEventListener('paste', (e) => {
        const items = (e.clipboardData || window.clipboardData).items;
        if (!items) return;

        for (let i = 0; i < items.length; i++) {
            if (items[i].type.startsWith('image/')) {
                e.preventDefault();
                const file = items[i].getAsFile();
                if (!file) continue;
                if (!currentConv) {
                    alert('请先选择一个会话');
                    return;
                }
                if (confirm(`是否发送图片 "${file.name || '粘贴的图片'}"？`)) {
                    uploadAndSend(file);
                }
                break;
            }
        }
    });

    sendBtn.addEventListener('click', () => {
        const text = messageInput.value.trim();
        if (text) {
            sendMessage(text);
            messageInput.value = '';
            messageInput.style.height = 'auto';
        }
    });

    moreBtn.addEventListener('click', (e) => {
        e.stopPropagation();
        moreMenu.classList.toggle('show');
    });
    document.addEventListener('click', () => {
        moreMenu.classList.remove('show');
    });

    document.getElementById('sendImageBtn').addEventListener('click', () => {
        fileInput.accept = 'image/*';
        fileInput.click();
    });
    document.getElementById('sendFileBtn').addEventListener('click', () => {
        fileInput.accept = '*';
        fileInput.click();
    });

    const sendEmoticonBtn = document.getElementById('sendEmoticonBtn');
    sendEmoticonBtn.addEventListener('click', () => {
        showEmoticonPicker();
    });

    fileInput.addEventListener('change', async (e) => {
        const files = e.target.files;
        if (!files.length) return;
        const file = files[0];
        if (!confirm(`是否发送文件 "${file.name}"？`)) return;
        await uploadAndSend(file);
        fileInput.value = '';
    });

    const chatArea = document.querySelector('.chat-area');
    chatArea.addEventListener('dragover', (e) => {
        e.preventDefault();
    });
    chatArea.addEventListener('drop', async (e) => {
        e.preventDefault();
        const files = e.dataTransfer.files;
        if (!files.length || !currentConv) return;
        const file = files[0];
        if (!confirm(`是否发送文件 "${file.name}" 到当前会话？`)) return;
        await uploadAndSend(file);
    });

    async function uploadAndSend(file) {
        if (!currentConv) return;
        const formData = new FormData();
        formData.append('file', file);
        formData.append('conv_type', currentConv.type);
        formData.append('to_id', currentConv.id);

        // 立即显示发送中消息（半透明）
        const tempId = 'temp_' + Date.now();
        const tempMsg = {
            id: tempId,
            from_uid: myUid,
            from_name: myName,
            from_avatar: myAvatar || '',
            body: '',
            msg_type: 'resource',
            media_url: URL.createObjectURL(file),
            thumb_url: null,
            created_at: Math.floor(Date.now() / 1000),
        };
        if (currentConv.type === 'group') {
            tempMsg.group_id = currentConv.id;
        }
        appendMessage(tempMsg, currentConv.key, seenMsgIds[currentConv.key]);
        scrollToBottom(true);
        const tempEl = messagesContainer.querySelector(`[data-msg-id="${tempId}"]`);
        if (tempEl) {
            tempEl.style.opacity = '0.5';
        }

        try {
            const res = await fetch('/web/api/upload_and_send', {
                method: 'POST',
                body: formData
            });
            const data = await res.json();
            if (data.error || !data.message || !data.message.id) {
                if (tempEl) tempEl.remove();
                seenMsgIds[currentConv.key]?.delete(tempId);
                alert('发送失败: ' + (data.error || '未知错误'));
                return;
            }
            const msg = data.message;
            // 替换临时消息
            if (tempEl) {
                const newEl = createMessageElement(msg, currentConv.key, seenMsgIds[currentConv.key]);
                if (newEl) {
                    tempEl.replaceWith(newEl);
                }
            }
            scrollToBottom(true);
        } catch (error) {
            console.error(error);
            if (tempEl) tempEl.remove();
            seenMsgIds[currentConv.key]?.delete(tempId);
            alert('网络错误，发送失败');
        }
    }

    document.addEventListener('contextmenu', (e) => {
        hideContextMenu();
        
        // 排除输入框区域（textarea 和其父级）
        if (e.target.closest('.input-area') || e.target.closest('textarea') || e.target.closest('#messageInput')) {
            return;  // 让系统默认菜单弹出
        }
        
        // 优先显示消息菜单
        const msgDiv = e.target.closest('.message');
        if (msgDiv) {
            const sel = window.getSelection();
            const selectedText = sel && sel.toString() ? sel.toString() : '';
            e.preventDefault();
            const msgId = msgDiv.dataset.msgId;
            if (!msgId) return;

            const fromUid = msgDiv.dataset.fromUid;
            const isOwn = fromUid && fromUid.toUpperCase() === myUid.toUpperCase();

            const menu = document.createElement('div');
            menu.className = 'custom-context-menu';
            menu.style.left = e.clientX + 'px';
            menu.style.top = e.clientY + 'px';
            let menuHtml = `
                <div class="context-menu-item" data-action="copy">复制</div>
                <div class="context-menu-item" data-action="copy-raw">复制原始消息</div>
                <div class="context-menu-divider"></div>
                <div class="context-menu-item" data-action="quote">引用</div>
            `;
            menu.innerHTML = menuHtml;
            document.body.appendChild(menu);
            requestAnimationFrame(() => menu.classList.add('show'));
            contextMenu = menu;
            contextMsgId = msgId;
    
            menu.addEventListener('click', (event) => {
                const action = event.target.dataset.action;
                if (action === 'copy') {
                    const textToCopy = selectedText || msgDiv.querySelector('.message-bubble')?.innerText || '';
                    if (navigator.clipboard && navigator.clipboard.writeText) {
                        navigator.clipboard.writeText(textToCopy).catch(() => fallbackCopyText(textToCopy));
                    } else {
                        fallbackCopyText(textToCopy);
                    }
                } else if (action === 'copy-raw') {
                    const rawBody = msgDiv.dataset.rawBody || '';
                    if (navigator.clipboard && navigator.clipboard.writeText) {
                        navigator.clipboard.writeText(rawBody).catch(() => fallbackCopyText(rawBody));
                    } else {
                        fallbackCopyText(rawBody);
                    }
                } else if (action === 'quote') {
                    const fromUid = msgDiv.dataset.fromUid;
                    const fromName = msgDiv.dataset.fromName;
                    const msgType = msgDiv.dataset.msgType;
                    const plainText = msgDiv.dataset.plainText || '';
    
                    pendingQuote = {
                        id: msgId,
                        from_uid: fromUid,
                        from_name: fromName,
                        type: msgType,
                        text: plainText.substring(0, 200)
                    };
    
                    quotePreviewText.textContent = `引用: ${fromName} - ${plainText.substring(0, 50)}`;
                    quotePreview.style.display = 'flex';
                    messageInput.focus();
                }
                hideContextMenu();
            });
    
            const closeHandler = (ev) => {
                if (!menu.contains(ev.target)) {
                    hideContextMenu();
                    document.removeEventListener('click', closeHandler);
                }
            };
            setTimeout(() => document.addEventListener('click', closeHandler), 0);
            return;
        }
    
        // 其次判断是否在右侧聊天区域（.chat-area 或 .messages）的空白处
        // 注意：此时已经排除了 input-area
        const chatArea = e.target.closest('.chat-area');
        if (chatArea) {
            e.preventDefault();
            const menu = document.createElement('div');
            menu.className = 'custom-context-menu';
            menu.style.left = e.clientX + 'px';
            menu.style.top = e.clientY + 'px';
            menu.innerHTML = `
                <div class="context-menu-item" data-action="refresh">刷新</div>
            `;
            document.body.appendChild(menu);
            requestAnimationFrame(() => menu.classList.add('show'));
            contextMenu = menu;
    
            menu.addEventListener('click', (event) => {
                const action = event.target.dataset.action;
                if (action === 'refresh') {
                    if (currentConv) {
                        const convKey = currentConv.key;
                        switchConversation(currentConv.type, currentConv.id, currentConv.name);
                        setTimeout(() => {
                            const items = contactList.querySelectorAll('.contact-item');
                            items.forEach(item => {
                                if (item.dataset.convKey === convKey) {
                                    item.classList.add('active');
                                }
                            });
                        }, 0);
                    }
                }
                hideContextMenu();
            });
    
            const closeHandler = (ev) => {
                if (!menu.contains(ev.target)) {
                    hideContextMenu();
                    document.removeEventListener('click', closeHandler);
                }
            };
            setTimeout(() => document.addEventListener('click', closeHandler), 0);
            return;
        }
    });



    function fallbackCopy(element) {
        const range = document.createRange();
        range.selectNodeContents(element);
        const selection = window.getSelection();
        selection.removeAllRanges();
        selection.addRange(range);
        try { document.execCommand('copy'); } catch (e) { alert('复制失败'); }
        selection.removeAllRanges();
    }

    function fallbackCopyText(text) {
        const textarea = document.createElement('textarea');
        textarea.value = text;
        textarea.style.position = 'fixed';
        textarea.style.opacity = '0';
        document.body.appendChild(textarea);
        textarea.select();
        try { document.execCommand('copy'); } catch (e) { alert('复制失败'); }
        document.body.removeChild(textarea);
    }

    cancelQuoteBtn.addEventListener('click', () => {
        pendingQuote = null;
        quotePreview.style.display = 'none';
        messageInput.focus();
    });




    // ===== 侧边栏固定/自动隐藏（绝对定位平移） =====
    let sidebarPinned = true;
    const sidebar = document.querySelector('.sidebar');

    const isMobile = () => window.innerWidth <= 768;

    function expandChat() {
        if (isMobile()) {
            chatArea.style.marginLeft = '0px';
        } else {
            chatArea.style.marginLeft = sidebar.classList.contains('collapsed') ? '0px' : '280px';
        }
    }

    if (isMobile()) {
        sidebar.classList.remove('collapsed');
        sidebarPinned = false;
        expandChat();
    }

    pinSidebarBtn.addEventListener('click', () => {
        if (isMobile()) return;
        sidebarPinned = !sidebarPinned;
        if (sidebarPinned) {
            sidebar.classList.remove('collapsed');
            pinSidebarBtn.innerHTML = '<i class="fa-solid fa-thumbtack"></i>';
            pinSidebarBtn.title = '取消固定';
            expandChat();
            setTimeout(expandChat, 350);
        } else {
            sidebar.classList.add('collapsed');
            pinSidebarBtn.innerHTML = '<i class="fa-solid fa-angles-right"></i>';
            pinSidebarBtn.title = '固定侧边栏';
            expandChat();
        }
    });

    // 鼠标移到屏幕最左侧边缘时，如果未固定且隐藏，则展开
    document.addEventListener('mousemove', (e) => {
        if (isMobile()) return;
        if (!sidebarPinned && sidebar.classList.contains('collapsed') && e.clientX < 5) {
            sidebar.classList.remove('collapsed');
            expandChat();
            setTimeout(expandChat, 350);
        }
    });

    // 鼠标离开侧边栏时，如果未固定，自动隐藏（带短延迟）
    let leaveTimer;
    sidebar.addEventListener('mouseleave', () => {
        if (isMobile()) return;
        if (!sidebarPinned && !sidebar.classList.contains('collapsed')) {
            clearTimeout(leaveTimer);
            leaveTimer = setTimeout(() => {
                sidebar.classList.add('collapsed');
                expandChat();
            }, 200);
        }
    });
    sidebar.addEventListener('mouseenter', () => {
        clearTimeout(leaveTimer);
    });

    window.addEventListener('resize', () => {
        expandChat();
    });

    mobileMenuBtn.addEventListener('click', () => {
        if (!isMobile()) return;
        sidebar.classList.remove('collapsed');
    });

    // 直链图片/音频发送
    let urlInputMode = 'image'; // 'image' | 'voice'
    const sendUrlImageBtn = document.getElementById('sendUrlImageBtn');
    const sendUrlVoiceBtn = document.getElementById('sendUrlVoiceBtn');
    const urlInputOverlay = document.getElementById('urlInputOverlay');
    const urlImageInput = document.getElementById('urlImageInput');
    const urlInputCancel = document.getElementById('urlInputCancel');
    const urlInputSend = document.getElementById('urlInputSend');
    const urlInputTitle = document.querySelector('.url-input-title');

    function showUrlInput(mode) {
        urlInputMode = mode;
        if (mode === 'image') {
            urlInputTitle.textContent = '输入图片链接';
            urlImageInput.placeholder = 'https://...';
        } else {
            urlInputTitle.textContent = '输入音频链接';
            urlImageInput.placeholder = 'https://...';
        }
        urlInputOverlay.style.display = 'flex';
        urlImageInput.value = '';
        urlImageInput.focus();
    }

    sendUrlImageBtn.addEventListener('click', () => showUrlInput('image'));
    sendUrlVoiceBtn.addEventListener('click', () => showUrlInput('voice'));

    urlInputCancel.addEventListener('click', () => {
        urlInputOverlay.style.display = 'none';
    });
    urlInputOverlay.addEventListener('click', (e) => {
        if (e.target === urlInputOverlay) urlInputOverlay.style.display = 'none';
    });

    urlInputSend.addEventListener('click', () => {
        const url = urlImageInput.value.trim();
        if (!url) { alert('请输入链接'); return; }
        const msgType = urlInputMode === 'image' ? 'image' : 'voice';
        sendMessage('', msgType, url);
        urlInputOverlay.style.display = 'none';
    });
    
    // 红包领取处理（事件委托）
    document.addEventListener('click', async (e) => {
        const card = e.target.closest('.red-packet-card');
        if (!card) return;
        const claimed = card.dataset.claimed === 'true';
        if (claimed) return; // 已领取不再请求

        const packetId = card.dataset.packetId;
        if (!packetId) return;

        // 立即禁用，避免重复点击
        card.dataset.claimed = 'true';
        card.style.pointerEvents = 'none';
        card.querySelector('.rp-status').textContent = '领取中...';

        try {
            const res = await fetch('/web/api/redpacket/claim', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ packet_id: packetId })
            });
            const data = await res.json();
            if (data.error) {
                card.querySelector('.rp-status').textContent = data.error;
                card.style.opacity = '0.7';
            } else {
                // 领取成功，显示金额（如果接口返回 amount 字段）
                const amount = data.amount !== undefined ? data.amount : '';
                card.querySelector('.rp-status').textContent = amount ? `已领取 ${amount}` : '已领取';
                card.style.opacity = '0.7';
                card.style.cursor = 'default';
            }
        } catch (err) {
            card.querySelector('.rp-status').textContent = '网络错误';
            card.style.opacity = '0.7';
        }
    });

    async function showEmoticonPicker() {
        const existing = document.getElementById('emoticonPicker');
        if (existing) existing.remove();
    
        try {
            const res = await fetch('/web/api/emoticons');
            const data = await res.json();
            const images = data.images || [];
            if (images.length === 0) {
                alert('没有可用的表情包');
                return;
            }
    
            const picker = document.createElement('div');
            picker.id = 'emoticonPicker';
            picker.className = 'emoticon-picker';
            picker.innerHTML = `<div class="emoticon-grid"></div>`;
            const grid = picker.querySelector('.emoticon-grid');
    
            images.forEach(img => {
                const imgUrl = `https://gwebcdn260523.pages.dev/v1/static/images/${img}`;
                const item = document.createElement('div');
                item.className = 'emoticon-item';
                item.innerHTML = `<img src="${imgUrl}" loading="lazy">`;
                item.addEventListener('click', async () => {
                    picker.remove();
                    try {
                        const response = await fetch(imgUrl);
                        const blob = await response.blob();
                        const formData = new FormData();
                        formData.append('file', blob, img);
                        const uploadRes = await fetch('/web/api/upload_only', {
                            method: 'POST',
                            body: formData
                        });
                        const uploadData = await uploadRes.json();
                        if (uploadData.error) {
                            alert('上传表情失败: ' + uploadData.error);
                            return;
                        }
                        sendMessage('', 'image', uploadData.media_url, uploadData.thumb_url);
                    } catch (err) {
                        console.error(err);
                        alert('发送表情失败');
                    }
                });
                grid.appendChild(item);
            });
    
            document.body.appendChild(picker);
    
            const closeHandler = (e) => {
                if (!picker.contains(e.target)) {
                    picker.remove();
                    document.removeEventListener('click', closeHandler);
                }
            };
            setTimeout(() => document.addEventListener('click', closeHandler), 0);
        } catch (e) {
            console.error(e);
            alert('加载表情失败');
        }
    }

    let plazaOffset = 0;
    let plazaLoading = false;
    let plazaHasMore = true;

    async function showEmojiPlaza() {
        const existing = document.getElementById('emojiPlazaPanel');
        if (existing) { existing.remove(); return; }

        plazaOffset = 0;
        plazaHasMore = true;

        const panel = document.createElement('div');
        panel.id = 'emojiPlazaPanel';
        panel.className = 'emoticon-picker';
        panel.style.maxHeight = '400px';
        panel.innerHTML = `
            <div class="emoticon-grid" id="plazaGrid"></div>
            <div style="text-align:center; margin-top:8px;" id="plazaLoadMore">
                <button class="btn" id="plazaLoadMoreBtn">加载更多</button>
            </div>
        `;
        document.body.appendChild(panel);

        const grid = document.getElementById('plazaGrid');
        const loadMoreBtn = document.getElementById('plazaLoadMoreBtn');

        const loadPage = async () => {
            if (plazaLoading || !plazaHasMore) return;
            plazaLoading = true;
            loadMoreBtn.textContent = '加载中…';
            try {
                const res = await fetch(`/web/api/emoji/plaza?limit=20&offset=${plazaOffset}`);
                const data = await res.json();
                const items = data.items || [];
                if (items.length === 0) {
                    plazaHasMore = false;
                    loadMoreBtn.textContent = '没有更多了';
                    loadMoreBtn.disabled = true;
                    return;
                }
                items.forEach(item => {
                    const imgUrl = item.url || item.media_url || '';
                    if (!imgUrl) return;
                    const div = document.createElement('div');
                    div.className = 'emoticon-item';
                    div.innerHTML = `<img src="${imgUrl}" loading="lazy">`;
                    div.addEventListener('click', () => {
                        sendMessage('', 'image', imgUrl);
                        panel.remove();
                    });
                    grid.appendChild(div);
                });
                plazaOffset += items.length;
            } catch(e) { console.error(e); }
            finally {
                plazaLoading = false;
                if (plazaHasMore) loadMoreBtn.textContent = '加载更多';
            }
        };

        loadPage();
        loadMoreBtn.addEventListener('click', loadPage);

        const closeHandler = (e) => {
            if (!panel.contains(e.target) && e.target !== emojiPlazaBtn) {
                panel.remove();
                document.removeEventListener('click', closeHandler);
            }
        };
        setTimeout(() => document.addEventListener('click', closeHandler), 0);
    }

    expandChat();
    loadContacts().then(() => {
        // 读取上次打开的会话
        const lastConv = localStorage.getItem('lastConversation');
        if (lastConv) {
            const [type, id] = lastConv.split(':');
            if (type && id) {
                let found = null;
                if (type === 'direct') {
                    found = contacts.friends.find(f => f.uid === id);
                } else if (type === 'group') {
                    found = contacts.groups.find(g => g.id === id);
                }
                if (found) {
                    const name = found.name || (type === 'direct' ? found.uid : found.id);
                    switchConversation(type, id, name);
                    const items = contactList.querySelectorAll('.contact-item');
                    items.forEach(item => {
                        const convKey2 = type + ':' + id;
                        if (item.dataset.convKey === convKey2) {
                            item.classList.add('active');
                        }
                    });
                }
            }
        }
    });
    // 连接 WebSocket
    initWebSocket();

    // ===== @ 提及点击跳转 =====
    // 注入跳转目标闪烁动画
    const styleEl = document.createElement('style');
    styleEl.textContent = `
        @keyframes mention-jump-flash {
            0%   { background-color: rgba(255, 255, 0, 0.5); }
            100% { background-color: transparent; }
        }
        .mention-jump-target {
            animation: mention-jump-flash 1.5s ease-out;
        }
    `;
    document.head.appendChild(styleEl);

    // 点击消息中的 @xxx，跳转到该用户的上一条消息（如果存在），否则打开其空间页
    messagesContainer.addEventListener('click', (e) => {
        const mention = e.target.closest('.mention-highlight');
        if (!mention) return;
        e.preventDefault();
        e.stopPropagation();

        const targetUid = mention.dataset.uid;
        if (!targetUid) return;

        // 从当前消息往前找到最近一条该用户发送的消息
        const currentMsg = mention.closest('.message');
        if (!currentMsg) return;

        let prev = currentMsg.previousElementSibling;
        while (prev) {
            if (prev.classList.contains('message') && prev.dataset.fromUid === targetUid) {
                // 跳转并闪烁高亮
                prev.scrollIntoView({ block: 'center', behavior: 'smooth' });
                prev.classList.add('mention-jump-target');
                setTimeout(() => prev.classList.remove('mention-jump-target'), 2000);
                return;
            }
            prev = prev.previousElementSibling;
        }

        // 没找到该用户的历史消息，跳转 space 页
        openSpacePanel(targetUid);
    });

});