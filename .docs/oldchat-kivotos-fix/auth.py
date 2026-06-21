from flask import Blueprint, request, session, redirect, url_for, render_template
from oldchat_api import OldChatAPI
from encrypted_api import EncryptedOldChatAPI

auth_bp = Blueprint('auth', __name__)

@auth_bp.route('/login', methods=['GET', 'POST'])
def login_page():
    if request.method == 'POST':
        identifier = request.form['identifier']
        password = request.form['password']
        # 读取加密通信复选框
        use_encryption = request.form.get('use_encryption') == 'on'

        try:
            if use_encryption:
                api = EncryptedOldChatAPI()
            else:
                api = OldChatAPI()

            user = api.login(identifier, password)
            session['access_token'] = api.access_token
            session['refresh_token'] = api.refresh_token
            session['user_name'] = user.get('display_name', user.get('username'))
            session['uid'] = user.get('uid')
            session['use_encryption'] = use_encryption   # 存储到 session
            return redirect(url_for('index'))
        except Exception as e:
            # 尝试回退到普通 API（如果加密登录失败）
            if use_encryption:
                try:
                    fallback_api = OldChatAPI()
                    user = fallback_api.login(identifier, password)
                    session['access_token'] = fallback_api.access_token
                    session['refresh_token'] = fallback_api.refresh_token
                    session['user_name'] = user.get('display_name', user.get('username'))
                    session['uid'] = user.get('uid')
                    session['use_encryption'] = False
                    return redirect(url_for('index'))
                except:
                    pass
            return render_template('login.html', error=str(e))
    return render_template('login.html')
