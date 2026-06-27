function escapeHtml(text) {
    const div = document.createElement('div');
    div.appendChild(document.createTextNode(text));
    return div.innerHTML;
}

function escapeRegExp(string) {
    return string.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
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
    const aboutBtn = document.getElementById('aboutBtn');

    const quotePreview = document.getElementById('quotePreview');
    const quotePreviewText = quotePreview.querySelector('.quote-preview-text');
    const cancelQuoteBtn = document.getElementById('cancelQuoteBtn');

    const emojiPlazaBtn = document.getElementById('emojiPlazaBtn');

    const mergeMessages = document.querySelector('meta[name="theme-merge-messages"]')?.content === 'true';
    let lastRenderedMsg = null;

    let pendingQuote = null;

    let contextMenu = null;
    let contextMsgId = null;

    // 滚动加载历史消息状态
    const convOffsets = {};       // convKey → 当前已加载的消息数量（即下一个 offset）
    const convHasMore = {};       // convKey → boolean
    let isLoadingMore = false;
    let isLoadingMoreReqId = 0;

    function hideContextMenu() {
        if (contextMenu) {
            contextMenu.remove();
            contextMenu = null;
            contextMsgId = null;
        }
    }

    emojiPlazaBtn.addEventListener('click', () => {
        showEmojiPlaza();
    });
    

    logoutBtn.addEventListener('click', () => {
        window.location.href = '/logout';
    });

    aboutBtn.addEventListener('click', () => {
        window.location.href = '/static/about.html';
    });

    async function loadContacts() {
        try {
            const res = await fetch('/api/contacts');
            const data = await res.json();
            if (data.error) { alert(data.error); return; }
            contacts = data;
            renderContacts();
        } catch (e) { console.error(e); }
    }

    function renderContacts() {
        contactList.innerHTML = '';
        contacts.friends.forEach(f => {
            const div = createContactItem(f.uid, f.name, 'direct');
            contactList.appendChild(div);
        });
        contacts.groups.forEach(g => {
            const div = createContactItem(g.id, g.name, 'group');
            contactList.appendChild(div);
        });
    }

    function createContactItem(id, name, type) {
        const div = document.createElement('div');
        div.className = 'contact-item';
        div.innerHTML = `<div class="name">${escapeHtml(name)}</div><div class="uid">${escapeHtml(id)}</div>`;
        div.addEventListener('click', (e) => switchConversation(type, id, name, e));
        return div;
    }

    async function switchConversation(type, id, name, event) {
        document.querySelectorAll('.contact-item').forEach(el => el.classList.remove('active'));
        if (event && event.currentTarget) {
            event.currentTarget.classList.add('active');
        }
    
        const convKey = `${type}:${id}`;
        currentConv = { type, id, name, key: convKey };
    
        // 保存到 localStorage，下次自动恢复
        try {
            localStorage.setItem('lastConversation', convKey);
        } catch (e) {}
    
        chatHeader.innerHTML = `<span>${escapeHtml(name)}</span>`;
        messagesContainer.innerHTML = '';
        lastRenderedMsg = null;

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
            const res = await fetch(`/api/messages/${type}/${id}?limit=${PAGE_SIZE}&offset=0`);
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
            scrollToBottom(true);
            await fetch(`/api/mark_read/${type}/${id}`, { method: 'PUT' });

            // 设置滚动到顶部加载更多
            messagesContainer._scrollHandler = async () => {
                if (!convHasMore[convKey] || isLoadingMore) return;
                // 滚动到顶部附近（留 5px 容差）
                if (messagesContainer.scrollTop > 5) return;

                isLoadingMore = true;
                const loadReqId = ++isLoadingMoreReqId;
                const currentHeight = messagesContainer.scrollHeight;
                try {
                    const res = await fetch(`/api/messages/${type}/${id}?limit=${PAGE_SIZE}&offset=${convOffsets[convKey]}`);
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
                    olderMsgs.forEach(msg => {
                        const el = createMessageElement(msg, convKey, seenMsgIds[convKey]);
                        if (el) frag.appendChild(el);
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

        const fromUid = msg.from_uid || '';
        const isSelf = fromUid.toUpperCase() === myUid.toUpperCase();

        const sender = isSelf ? '你' : (msg.from_name || fromUid);
        const time = new Date(msg.created_at * 1000).toLocaleTimeString('zh-CN', { hour12: false });
        const msgType = msg.msg_type || 'text';
        let content = '';

        if (msgType === 'text') {
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
        } else if (msgType === 'image') {
            const mediaUrl = msg.media_url || '';
            content = `<img src="${mediaUrl}" style="max-width:200px; max-height:200px; cursor:pointer;" class="chat-image" onclick="openImageViewer('${mediaUrl.replace(/'/g, "\\'")}')">`;
        } else if (msgType === 'video') {
            content = `<video controls style="max-width:200px;"><source src="${msg.media_url || ''}"></video>`;
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
            // 从 URL 提取文件名作为兜底
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
            // 如果有展示文本，放在文件卡片上方
            content = displayText
                ? `<div style="margin-bottom:6px;">${displayText}</div>${fileCardHtml}`
                : fileCardHtml;
        } else {
            content = `[${msgType}] ${escapeHtml(msg.body || '')}`;
        }

        const msgDiv = document.createElement('div');
        msgDiv.className = `message ${isSelf ? 'self' : 'other'}`;
        msgDiv.dataset.msgId = msg.id;
        msgDiv.dataset.fromUid = msg.from_uid || '';
        msgDiv.dataset.fromName = msg.from_name || msg.from_uid || '';
        msgDiv.dataset.msgType = msg.msg_type || 'text';

        if (!isSelf) {
            const avatarUrl = msg.from_avatar || 'https://gwebcdn260523.pages.dev/v1/static/default-avatar.png';
            const avatarImg = document.createElement('img');
            avatarImg.src = avatarUrl;
            avatarImg.className = 'msg-avatar';
            avatarImg.onerror = () => { avatarImg.src = 'https://gwebcdn260523.pages.dev/v1/static/default-avatar.png'; };
            avatarImg.addEventListener('click', (e) => {
                e.stopPropagation();
                const uid = msg.from_uid;
                if (uid) {
                    window.open(`/space/${uid}`, '_blank');
                }
            });
            msgDiv.appendChild(avatarImg);
        }

        const bubbleWrapper = document.createElement('div');
        bubbleWrapper.className = 'message-content';
        // 群聊或他人消息显示发送者名称，私聊自己的消息不显示
        const showSender = (convKey && convKey.startsWith('group:')) || (!isSelf);
        bubbleWrapper.innerHTML = `
            ${showSender ? `<div class="message-sender">${escapeHtml(sender)}</div>` : ''}
            <div class="message-bubble">${content}</div>
            <div class="message-time">${time}</div>
        `;
        msgDiv.appendChild(bubbleWrapper);

        const bubble = bubbleWrapper.querySelector('.message-bubble');
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

        // 尝试合并连续的同发送者纯文本消息
        if (mergeMessages && lastRenderedMsg && 
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
            return;
        }
    
        const msgDiv = createMessageElement(msg, convKey, currentSeen);
        if (!msgDiv) return;
    
        messagesContainer.appendChild(msgDiv);
    
        currentSeen.add(msg.id);
        lastRenderedMsg = { convKey, from_uid: fromUid, element: msgDiv };
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
        // 显示"正在播放"图标并记录当前消息
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
        // 无论是否暂停到底，都隐藏"正在播放"图标
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

    // === 语音波形可视化（基于真实音频数据） ===
    var voiceVisCtx = null;
    var voiceVisCache = {};  // url → { data: Float32Array, sampleRate: number }
    var voiceVis = { rafId: null, audio: null, bars: null };
    var currentlyPlayingVoiceMsg = null;  // 当前正在播放的语音消息 DOM 元素

    // 点击"正在播放"按钮 → 跳转到正在播放的消息
    document.getElementById('nowPlayingBtn').addEventListener('click', function() {
        if (currentlyPlayingVoiceMsg && currentlyPlayingVoiceMsg.isConnected) {
            currentlyPlayingVoiceMsg.scrollIntoView({ behavior: 'smooth', block: 'center' });
        }
    });

    function stopVoiceVis() {
        if (voiceVis.rafId) { cancelAnimationFrame(voiceVis.rafId); voiceVis.rafId = null; }
        if (voiceVis.bars) {
            voiceVis.bars.forEach(function(b) { b.style.height = ''; b.style.opacity = ''; });
        }
        if (voiceVis.bgBars) {
            voiceVis.bgBars.forEach(function(b) { b.style.height = ''; b.style.opacity = ''; });
        }
        voiceVis.audio = null;
        voiceVis.bars = null;
        voiceVis.bgBars = null;
        var bgEl = document.getElementById('voiceBgWave');
        if (bgEl) bgEl.classList.remove('visible');
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
        // 设置背景波浪线（Canvas）
        var bgContainer = document.getElementById('voiceBgWave');
        var bgBarsContainer = bgContainer ? bgContainer.querySelector('.voice-bg-wave-bars') : null;
        if (bgBarsContainer) {
            bgBarsContainer.innerHTML = '';
            var canvas = document.createElement('canvas');
            canvas.className = 'voice-bg-wave-canvas';
            function resizeCanvas() {
                canvas.width = window.innerWidth;
                canvas.height = 320;
            }
            resizeCanvas();
            window.addEventListener('resize', resizeCanvas);
            bgBarsContainer.appendChild(canvas);
            voiceVis.bgCanvas = canvas;
            voiceVis.bgCtx = canvas.getContext('2d');
            bgContainer.classList.add('visible');
        }
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

            // 窗口：当前播放位置前后共 ~1.5 秒，增大范围避免长时间拉满
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
                // 语音信号放大，使波形明显
                var val = Math.min(1, avg * 3);
                val = Math.max(0.05, val);
                bars[i].style.height = (val * 22) + 'px';
                bars[i].style.opacity = Math.max(0.2, val * 0.8 + 0.2);
            }

            // 更新背景波形 — 单条连续波浪线
            var bgCanvas = voiceVis.bgCanvas;
            var bgCtx = voiceVis.bgCtx;
            if (bgCanvas && bgCtx) {
                var w = bgCanvas.width;
                var h = bgCanvas.height;
                bgCtx.clearRect(0, 0, w, h);

                var numPoints = 100;
                var halfH = h / 2;
                var maxAmp = h * 0.38;

                bgCtx.beginPath();
                bgCtx.strokeStyle = 'rgba(0, 0, 0, 0.10)';
                bgCtx.lineWidth = 4;
                bgCtx.lineJoin = 'round';
                bgCtx.lineCap = 'round';

                for (var pi = 0; pi < numPoints; pi++) {
                    // 从当前窗口均匀取样本点
                    var t = pi / (numPoints - 1);
                    var sampleIdx = Math.floor(startSample + t * (endSample - startSample));
                    sampleIdx = Math.max(0, Math.min(totalSamples - 1, sampleIdx));
                    // 取该点附近几个样本的平均值，使线条更平滑
                    var sum2 = 0, cnt2 = 0;
                    for (var ss = -2; ss <= 2; ss++) {
                        var si = sampleIdx + ss;
                        if (si >= 0 && si < totalSamples) {
                            sum2 += pcm[si];
                            cnt2++;
                        }
                    }
                    var avgPcm = cnt2 > 0 ? sum2 / cnt2 : 0;
                    var x = t * w;
                    var y = halfH - avgPcm * maxAmp;
                    if (pi === 0) bgCtx.moveTo(x, y);
                    else bgCtx.lineTo(x, y);
                }
                bgCtx.stroke();
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
        try {
            const res = await fetch('/api/send', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });
            const data = await res.json();
            if (data.error) {
                alert('发送失败: ' + data.error);
                return;
            }
            if (pendingQuote) {
                pendingQuote = null;
                quotePreview.style.display = 'none';
            }
            if (data.message) {
                appendMessage(data.message, currentConv.key, seenMsgIds[currentConv.key]);
                scrollToBottom(true);
            }
        } catch (e) { console.error(e); }
    }

    messageInput.addEventListener('keydown', function (e) {
        if (e.key === 'Enter' && !e.shiftKey) {
            e.preventDefault();
            const text = this.value.trim();
            if (text) {
                sendMessage(text);
                this.value = '';
            }
        }
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
        }
    });

    moreBtn.addEventListener('click', (e) => {
        e.stopPropagation();
        moreMenu.style.display = moreMenu.style.display === 'block' ? 'none' : 'block';
    });
    document.addEventListener('click', () => {
        moreMenu.style.display = 'none';
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
        try {
            const res = await fetch('/api/upload_and_send', {
                method: 'POST',
                body: formData
            });
            const data = await res.json();
            if (data.error) {
                alert('发送失败: ' + data.error);
                return;
            }
            if (data.message) {
                appendMessage(data.message, currentConv.key, seenMsgIds[currentConv.key]);
                scrollToBottom(true);
            }
        } catch (error) {
            console.error(error);
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
            contextMenu = menu;
            contextMsgId = msgId;
    
            menu.addEventListener('click', (event) => {
                const action = event.target.dataset.action;
                if (action === 'copy') {
                    const bubble = msgDiv.querySelector('.message-bubble');
                    if (bubble) {
                        const text = bubble.innerText;
                        if (navigator.clipboard && navigator.clipboard.writeText) {
                            navigator.clipboard.writeText(text).catch(() => fallbackCopy(bubble));
                        } else {
                            fallbackCopy(bubble);
                        }
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
            contextMenu = menu;
    
            menu.addEventListener('click', (event) => {
                const action = event.target.dataset.action;
                if (action === 'refresh') {
                    location.reload();
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

    // 轮询未读
    setInterval(async () => {
        if (!currentConv) return;
        const convKey = currentConv.key;
        try {
            const res = await fetch('/api/unread');
            const data = await res.json();
            if (data.error) return;
            // ⚠️ 异步返回后检查会话是否已切换，防止消息错乱
            if (!currentConv || currentConv.key !== convKey) return;
            const allUnread = [...(data.direct || []), ...(data.groups || [])];
            for (const msg of allUnread) {
                const convKeyMsg = msg.group_id ? `group:${msg.group_id}` : `direct:${msg.from_uid}`;
                // 只处理当前会话的消息
                if (convKeyMsg !== convKey) continue;
                appendMessage(msg, convKey, seenMsgIds[convKey]);
                scrollToBottom();
                await fetch(`/api/mark_read/${msg.group_id ? 'group' : 'direct'}/${msg.group_id || msg.from_uid}`, { method: 'PUT' });
            }
        } catch (e) {}
    }, 3000);


    // ===== 侧边栏固定/自动隐藏（绝对定位平移） =====
    let sidebarPinned = true;
    const sidebar = document.querySelector('.sidebar');


    function expandChat() {
        chatArea.style.marginLeft = sidebar.classList.contains('collapsed') ? '0px' : '280px';
    }

    pinSidebarBtn.addEventListener('click', () => {
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
        if (!sidebarPinned && sidebar.classList.contains('collapsed') && e.clientX < 5) {
            sidebar.classList.remove('collapsed');
            expandChat();
            setTimeout(expandChat, 350);
        }
    });

    // 鼠标离开侧边栏时，如果未固定，自动隐藏（带短延迟）
    let leaveTimer;
    sidebar.addEventListener('mouseleave', () => {
        if (!sidebarPinned && !sidebar.classList.contains('collapsed')) {
            clearTimeout(leaveTimer);
            leaveTimer = setTimeout(() => {
                sidebar.classList.add('collapsed');
                expandChat(); // 隐藏时 margin-left 为 0
            }, 200);
        }
    });
    sidebar.addEventListener('mouseenter', () => {
        clearTimeout(leaveTimer);
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
            const res = await fetch('/api/redpacket/claim', {
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
            const res = await fetch('/api/emoticons');
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
                        const uploadRes = await fetch('/api/upload_only', {
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
                const res = await fetch(`/api/emoji/plaza?limit=20&offset=${plazaOffset}`);
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
                }
            }
        }
    });

    // 主题切换菜单
    const themeMenuBtn = document.getElementById('themeMenuBtn');
    const themeMenu = document.getElementById('themeMenu');
    const themeMenuList = document.getElementById('themeMenuList');

    themeMenuBtn.addEventListener('click', (e) => {
        e.stopPropagation();
        if (themeMenu.style.display === 'block') {
            themeMenu.style.display = 'none';
            return;
        }
        fetch('/api/themes')
            .then(r => r.json())
            .then(data => {
                const current = data.current;
                themeMenuList.innerHTML = data.themes.map(t => `
                    <div class="theme-item ${t.id === current ? 'active' : ''}" data-theme-id="${t.id}">
                        <span>${escapeHtml(t.name)}</span>
                        ${t.id === current ? '<span class="theme-check">✓</span>' : ''}
                    </div>
                `).join('');
                themeMenuList.querySelectorAll('.theme-item').forEach(item => {
                    item.addEventListener('click', (ev) => {
                        const themeId = item.dataset.themeId;
                        fetch('/api/set_theme', {
                            method: 'POST',
                            headers: {'Content-Type': 'application/json'},
                            body: JSON.stringify({theme_id: themeId})
                        }).then(r => r.json()).then(res => {
                            if (res.status === 'ok') location.reload();
                        });
                    });
                });
                themeMenu.style.display = 'block';
            });
    });

    document.addEventListener('click', (e) => {
        if (!themeMenu.contains(e.target) && e.target !== themeMenuBtn) {
            themeMenu.style.display = 'none';
        }
    });

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
        window.open(`/space/${targetUid}`, '_blank');
    });

});