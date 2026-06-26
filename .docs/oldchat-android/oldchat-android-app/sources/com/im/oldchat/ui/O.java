package com.im.oldchat.ui;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.r;
import g0.d;
import java.io.File;
import java.util.List;
import l0.AbstractC0380g;
import l0.AbstractC0403s;
import o0.C;
import org.json.JSONObject;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: loaded from: classes.dex */
public class O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Activity f3569a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final l0.D f3570b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final TextView f3571c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final EditText f3572d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f3573e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f3574f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f3575g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final e f3576h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final d f3577i;

    public class a implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3578a;

        public a(String str) {
            this.f3578a = str;
        }

        @Override // g0.d.i
        public void b(String str) {
            if (O.this.f3572d != null) {
                O.this.f3572d.setText("");
            }
            if (O.this.f3577i != null) {
                O.this.f3577i.a();
            }
            j0.j.k(O.this.f3569a, O.this.f3574f, O.this.f3575g, null, O.this.m(TextBundle.TEXT_ENTRY, this.f3578a), System.currentTimeMillis());
            O.this.t();
            j0.i iVarL = O.this.f3570b == null ? null : O.this.f3570b.l(str);
            if (iVarL != null && O.this.f3570b != null) {
                O.this.f3570b.b(iVarL, O.this.f3570b.f());
            } else if (O.this.f3570b != null) {
                O.this.f3570b.x(O.this.f3573e, false, 0L, false);
            }
            if (O.this.f3576h != null) {
                O.this.f3576h.a(false);
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (O.this.f3576h != null) {
                O.this.f3576h.a(false);
            }
            if (i2 == 401) {
                g0.e.w();
                return;
            }
            if (g0.e.v(i2, str)) {
                return;
            }
            String strN = O.this.n(i2, str);
            if (strN != null) {
                Toast.makeText(O.this.f3569a, strN, 0).show();
                return;
            }
            Toast.makeText(O.this.f3569a, "发送失败: " + i2, 0).show();
        }
    }

    public class b implements r.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ C.d f3580a;

        public b(C.d dVar) {
            this.f3580a = dVar;
        }

        @Override // com.im.oldchat.ui.r.a
        public void a(String str) {
            if (O.this.f3576h != null) {
                O.this.f3576h.a(false);
            }
            Toast.makeText(O.this.f3569a, str, 0).show();
        }

        @Override // com.im.oldchat.ui.r.a
        public void b(String str) {
            try {
                O.this.r("image", str, "", 0, o0.D.a("", this.f3580a, null, "emoji"));
            } catch (Exception unused) {
                Toast.makeText(O.this.f3569a, "发送表情失败", 0).show();
            }
            if (O.this.f3576h != null) {
                O.this.f3576h.a(false);
            }
        }
    }

    public class c implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3582a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f3583b;

        public c(String str, String str2) {
            this.f3582a = str;
            this.f3583b = str2;
        }

        @Override // g0.d.i
        public void b(String str) {
            j0.j.k(O.this.f3569a, O.this.f3574f, O.this.f3575g, null, O.this.m(this.f3582a, this.f3583b), System.currentTimeMillis());
            O.this.t();
            j0.i iVarL = O.this.f3570b == null ? null : O.this.f3570b.l(str);
            if (iVarL != null && O.this.f3570b != null) {
                O.this.f3570b.b(iVarL, true);
            } else if (O.this.f3570b != null) {
                O.this.f3570b.x(O.this.f3573e, false, 0L, false);
            }
            if (O.this.f3577i != null) {
                O.this.f3577i.a();
            }
            if (O.this.f3576h != null) {
                O.this.f3576h.a(false);
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (O.this.f3576h != null) {
                O.this.f3576h.a(false);
            }
            if (i2 == 401) {
                g0.e.w();
                return;
            }
            if (g0.e.v(i2, str)) {
                return;
            }
            if (str != null && str.contains("duration_too_long")) {
                Toast.makeText(O.this.f3569a, "语音不能超过60秒", 0).show();
                return;
            }
            String strN = O.this.n(i2, str);
            if (strN != null) {
                Toast.makeText(O.this.f3569a, strN, 0).show();
                return;
            }
            Toast.makeText(O.this.f3569a, "发送失败: " + i2, 0).show();
        }
    }

    public interface d {
        void a();
    }

    public interface e {
        void a(boolean z2);
    }

    public O(Activity activity, l0.D d2, TextView textView, EditText editText, String str, String str2, String str3, e eVar, d dVar) {
        this.f3569a = activity;
        this.f3570b = d2;
        this.f3571c = textView;
        this.f3572d = editText;
        this.f3573e = str;
        this.f3574f = str2;
        this.f3575g = str3;
        this.f3576h = eVar;
        this.f3577i = dVar;
    }

    public final String m(String str, String str2) {
        return AbstractC0380g.e(str, str2);
    }

    public final String n(int i2, String str) {
        if (i2 != 403 || str == null) {
            return null;
        }
        String lowerCase = str.toLowerCase();
        return lowerCase.contains("not_member") ? "你已不在该群，无法发送消息" : lowerCase.contains("group_muted") ? "当前群已全员禁言" : lowerCase.contains("user_banned") ? "账号已被封禁，无法发送消息" : lowerCase.contains("video_disabled") ? "服务器禁用了视频/3GP上传，请联系管理员" : "发送失败: 403";
    }

    public void o(String str, boolean z2, C.d dVar) {
        if (str == null || str.length() == 0) {
            return;
        }
        File file = new File(str);
        if (!file.exists()) {
            Toast.makeText(this.f3569a, "表情文件不存在", 0).show();
            return;
        }
        e eVar = this.f3576h;
        if (eVar != null) {
            eVar.a(true);
        }
        r.d(this.f3569a, file, z2, this.f3573e, new b(dVar));
    }

    public void p(String str, String str2, long j2, C.d dVar) {
        r("resource", str, "", 0, o0.D.a(AbstractC0403s.a(str2, j2, ""), dVar, null, null));
    }

    public void q(String str, String str2, String str3, int i2, C.d dVar) {
        r(str, str2, str3, i2, o0.D.a("", dVar, null, null));
    }

    public final void r(String str, String str2, String str3, int i2, String str4) {
        String str5 = this.f3574f;
        if (str5 == null || str5.length() == 0 || str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return;
        }
        try {
            if ("voice".equals(str)) {
                if (i2 <= 0) {
                    Toast.makeText(this.f3569a, "语音时长无效", 0).show();
                    return;
                } else if (i2 > 60000) {
                    i2 = 60000;
                }
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("group_id", this.f3574f);
            jSONObject.put("msg_type", str);
            jSONObject.put("media_url", str2);
            if (str3 != null && str3.length() > 0) {
                jSONObject.put("thumb_url", str3);
            }
            if (i2 > 0) {
                jSONObject.put("duration_ms", i2);
            }
            if (str4 != null && str4.length() > 0) {
                jSONObject.put("body", str4);
            }
            e eVar = this.f3576h;
            if (eVar != null) {
                eVar.a(true);
            }
            g0.d.S("/groups/message/send", jSONObject, this.f3573e, new c(str, str4));
        } catch (Exception unused) {
            Toast.makeText(this.f3569a, "发送失败", 0).show();
            e eVar2 = this.f3576h;
            if (eVar2 != null) {
                eVar2.a(false);
            }
        }
    }

    public void s(String str, C.d dVar, List list) {
        String str2;
        String strTrim = str == null ? "" : str.trim();
        if ((strTrim.length() == 0 && dVar == null) || (str2 = this.f3574f) == null || str2.length() == 0) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("group_id", this.f3574f);
            String strA = o0.D.a(strTrim, dVar, list, null);
            jSONObject.put("body", strA);
            jSONObject.put("msg_type", TextBundle.TEXT_ENTRY);
            e eVar = this.f3576h;
            if (eVar != null) {
                eVar.a(true);
            }
            g0.d.S("/groups/message/send", jSONObject, this.f3573e, new a(strA));
        } catch (Exception unused) {
            Toast.makeText(this.f3569a, "发送失败", 0).show();
            e eVar2 = this.f3576h;
            if (eVar2 != null) {
                eVar2.a(false);
            }
        }
    }

    public final void t() {
        TextView textView = this.f3571c;
        if (textView != null) {
            textView.setEnabled(true);
            this.f3571c.setText(f0.j.l1);
        }
    }
}
