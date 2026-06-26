package com.im.oldchat.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;
import com.im.oldchat.ui.f0;
import g0.d;
import l0.s0;
import o.c;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class a0 extends s0 {

    public class a implements DialogInterface.OnClickListener {
        public a() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (i2 == 0) {
                a0.this.J0();
            } else if (i2 == 1) {
                a0.this.K0();
            }
        }
    }

    public class b implements f0.e {
        public b() {
        }

        @Override // com.im.oldchat.ui.f0.e
        public void a(j0.w wVar) {
            String str;
            if (wVar == null || (str = wVar.f5646b) == null || str.length() == 0) {
                return;
            }
            a0.this.L0(wVar);
        }
    }

    public class c implements f0.f {
        public c() {
        }

        @Override // com.im.oldchat.ui.f0.f
        public void a(j0.e eVar) {
            String str;
            if (eVar == null || (str = eVar.f5504a) == null || str.length() == 0) {
                return;
            }
            a0.this.M0(eVar);
        }
    }

    public class d implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.w f4380a;

        public d(j0.w wVar) {
            this.f4380a = wVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(a0.this, "已发送", 0).show();
            Intent intent = new Intent(a0.this, (Class<?>) ChatActivity.class);
            intent.putExtra("friend_uid", this.f4380a.f5646b);
            intent.putExtra("friend_name", j0.d.b(this.f4380a));
            intent.putExtra("friend_avatar", this.f4380a.f5651g);
            a0.this.startActivity(intent);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(a0.this, "发送失败: " + i2, 0).show();
        }
    }

    public class e implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j0.e f4382a;

        public e(j0.e eVar) {
            this.f4382a = eVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(a0.this, "已发送", 0).show();
            Intent intent = new Intent(a0.this, (Class<?>) GroupChatActivity.class);
            intent.putExtra("group_id", this.f4382a.f5504a);
            intent.putExtra("group_name", this.f4382a.f5505b);
            intent.putExtra("group_avatar", this.f4382a.f5506c);
            intent.putExtra("group_role", this.f4382a.f5509f);
            a0.this.startActivity(intent);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(a0.this, "发送失败: " + i2, 0).show();
        }
    }

    public void I0() {
        String str;
        if (this.f6311b0 <= 0 && ((str = this.f6312c0) == null || str.length() == 0)) {
            Toast.makeText(this, "视频信息不足", 0).show();
            return;
        }
        String string = getSharedPreferences("auth", 0).getString("access_token", "");
        this.s0 = string;
        if (string == null || string.length() == 0) {
            Toast.makeText(this, "请先登录旧聊", 0).show();
        } else {
            new c.a(this).t("分享视频").g(new CharSequence[]{"分享给好友", "分享到群聊"}, new a()).k("取消", null).v();
        }
    }

    public void J0() {
        f0.k(this, j0.c.b(this), "选择好友", new b());
    }

    public void K0() {
        f0.l(this, j0.f.b(this), "选择群聊", new c());
    }

    public void L0(j0.w wVar) {
        if (!o0.G.d().f()) {
            Toast.makeText(this, "网络不可用", 0).show();
            return;
        }
        String string = getSharedPreferences("auth", 0).getString("access_token", "");
        this.s0 = string;
        if (string == null || string.length() == 0) {
            Toast.makeText(this, "请先登录旧聊", 0).show();
            return;
        }
        try {
            String strA = o0.D.a(j0(), null, null, null);
            String strL0 = l0();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("to_uid", wVar.f5646b);
            jSONObject.put("msg_type", "resource");
            jSONObject.put("media_url", strL0);
            jSONObject.put("body", strA);
            g0.d.S("/direct/send", jSONObject, this.s0, new d(wVar));
        } catch (Exception unused) {
            Toast.makeText(this, "发送失败", 0).show();
        }
    }

    public void M0(j0.e eVar) {
        if (!o0.G.d().f()) {
            Toast.makeText(this, "网络不可用", 0).show();
            return;
        }
        String string = getSharedPreferences("auth", 0).getString("access_token", "");
        this.s0 = string;
        if (string == null || string.length() == 0) {
            Toast.makeText(this, "请先登录旧聊", 0).show();
            return;
        }
        try {
            String strA = o0.D.a(j0(), null, null, null);
            String strL0 = l0();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("group_id", eVar.f5504a);
            jSONObject.put("msg_type", "resource");
            jSONObject.put("media_url", strL0);
            jSONObject.put("body", strA);
            g0.d.S("/groups/message/send", jSONObject, this.s0, new e(eVar));
        } catch (Exception unused) {
            Toast.makeText(this, "发送失败", 0).show();
        }
    }
}
