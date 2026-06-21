import os
import secrets
from datetime import datetime
from flask import Flask, session, g, redirect, url_for, render_template, jsonify, request
from auth import auth_bp
from api import api_bp
from oldchat_api import OldChatAPI
from encrypted_api import EncryptedOldChatAPI
from waitress import serve

def create_app():
    app = Flask(__name__)
    app.secret_key = secrets.token_hex(32)

    # 自定义模板过滤器：将时间戳转为可读时间
    @app.template_filter('datetimeformat')
    def datetimeformat(timestamp):
        if not timestamp:
            return ''
        return datetime.fromtimestamp(timestamp).strftime('%Y-%m-%d %H:%M')

    app.register_blueprint(auth_bp)
    app.register_blueprint(api_bp, url_prefix='/api')

    def load_themes():
        # 加载主题配置，返回 (themes_dict, default_theme_id)
        import json
        themes = {}
        default_theme = 'default'
        theme_base = os.path.join(app.root_path, 'static', 'style')
        mainconfig_path = os.path.join(theme_base, 'mainconfig.json')
        if os.path.exists(mainconfig_path):
            with open(mainconfig_path, 'r', encoding='utf-8') as f:
                mainconfig = json.load(f)
                default_theme = mainconfig.get('default_theme', 'default')
        # 扫描子目录
        if os.path.exists(theme_base):
            for name in os.listdir(theme_base):
                theme_dir = os.path.join(theme_base, name)
                if os.path.isdir(theme_dir):
                    config_path = os.path.join(theme_dir, 'config.json')
                    if os.path.exists(config_path):
                        with open(config_path, 'r', encoding='utf-8') as f:
                            config = json.load(f)
                            themes[name] = config
        return themes, default_theme

    @app.before_request
    def before_request():
        g.api = None
        if 'access_token' in session and 'refresh_token' in session:
            if session.get('use_encryption'):
                api = EncryptedOldChatAPI()
            else:
                api = OldChatAPI()
            api.access_token = session['access_token']
            api.refresh_token = session['refresh_token']
            g.api = api
            # 自动设置默认主题
            if 'theme' not in session:
                theme_from_cookie = request.cookies.get('theme')
                if theme_from_cookie:
                    themes, _ = load_themes()
                    if theme_from_cookie in themes:
                        session['theme'] = theme_from_cookie
                    else:
                        # cookie 中的主题无效，使用默认主题
                        _, default_theme = load_themes()
                        session['theme'] = default_theme
                else:
                    _, default_theme = load_themes()
                    session['theme'] = default_theme
    
    @app.context_processor
    def inject_theme():
        themes, _ = load_themes()
        current_theme = session.get('theme', 'default')
        theme_config = themes.get(current_theme, {
            'css_file': 'style.css',
            'name': current_theme,
            'merge_messages': False,
            'extra_html': ''
        })
        return {
            'themes': themes,
            'current_theme': current_theme,
            'theme_css_file': theme_config.get('css_file', 'style.css'),
            'current_theme_config': theme_config
        }

    @app.after_request
    def after_request(response):
        if g.api and (g.api.access_token != session.get('access_token') or
                      g.api.refresh_token != session.get('refresh_token')):
            session['access_token'] = g.api.access_token
            session['refresh_token'] = g.api.refresh_token
        return response

    @app.route('/logout')
    def logout():
        session.clear()
        return redirect(url_for('auth.login_page'))

    @app.route('/')
    def index():
        if 'access_token' not in session:
            return redirect(url_for('auth.login_page'))
        return render_template('index.html')

    @app.route('/api/themes')
    def api_themes():
        themes, default_theme = load_themes()
        # 返回列表，方便前端生成菜单
        theme_list = []
        for tid, tconf in themes.items():
            theme_list.append({
                'id': tid,
                'name': tconf.get('name', tid),
                'author': tconf.get('author', ''),
                'version': tconf.get('version', '')
            })
        return jsonify({'themes': theme_list, 'default_theme': default_theme, 'current': session.get('theme', default_theme)})

    @app.route('/api/set_theme', methods=['POST'])
    def set_theme():
        data = request.get_json()
        theme_id = data.get('theme_id')
        if not theme_id:
            return jsonify({'error': 'Missing theme_id'}), 400
        themes, _ = load_themes()
        if theme_id not in themes:
            return jsonify({'error': 'Invalid theme'}), 404
        
        session['theme'] = theme_id
        
        resp = jsonify({'status': 'ok', 'theme': theme_id})
        resp.set_cookie('theme', theme_id, max_age=365*24*60*60, path='/')
        return resp


    @app.route('/space/<uid>')
    def user_space(uid):
        if 'access_token' not in session:
            return redirect(url_for('auth.login_page'))

        api = OldChatAPI()
        api.access_token = session['access_token']
        api.refresh_token = session['refresh_token']
        try:
            profile = api.get_user_profile(uid)
            base = api.base_url
            for field in ['avatar_url', 'cover_url']:
                url = profile.get(field, '')
                if url and url.startswith('/'):
                    profile[field] = base + url

            # 判断是否是自己的空间
            is_owner = False
            try:
                me_data = api._request('GET', '/v1/me')
                my_uid = me_data.get('uid', '').upper()
                is_owner = (my_uid == uid.upper())
            except:
                pass

            moments = []
            try:
                moments = api.get_user_moments(uid, limit=10)
            except Exception as e:
                print(f"获取动态失败: {e}")

            return render_template('space.html', user=profile, moments=moments, is_owner=is_owner)
        except Exception as e:
            return f"<h2>获取用户信息失败</h2><p>{e}</p>", 500
    
    @app.route('/me')
    def my_profile():
        if 'access_token' not in session:
            return redirect(url_for('auth.login_page'))
        return render_template('me.html')
    
    return app

if __name__ == '__main__':
    import logging
    app = create_app()
    logger = logging.getLogger('waitress')
    logger.setLevel(logging.INFO)
    serve(app, host='0.0.0.0', port=5000, threads=150)