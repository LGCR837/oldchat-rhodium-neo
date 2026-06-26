package com.im.oldchat.ui;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.ui.r;
import g0.d;
import java.io.File;
import l0.AbstractC0403s;
import l0.C0386j;
import o0.C;
import org.json.JSONObject;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: renamed from: com.im.oldchat.ui.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0226q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Activity f4716a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0386j f4717b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final TextView f4718c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final EditText f4719d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f4720e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f4721f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final e f4722g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final d f4723h;

    /* JADX INFO: renamed from: com.im.oldchat.ui.q$a */
    public class a implements d.i {
        public a() {
        }

        @Override // g0.d.i
        public void b(String str) {
            if (C0226q.this.f4719d != null) {
                C0226q.this.f4719d.setText("");
            }
            if (C0226q.this.f4723h != null) {
                C0226q.this.f4723h.a();
            }
            C0226q.this.p();
            j0.k kVarL = C0226q.this.f4717b == null ? null : C0226q.this.f4717b.l(str);
            if (kVarL != null && C0226q.this.f4717b != null) {
                C0226q.this.f4717b.a(kVarL, true, C0226q.this.f4720e);
            } else if (C0226q.this.f4717b != null) {
                C0226q.this.f4717b.z(C0226q.this.f4720e, false, 0L, false);
            }
            if (C0226q.this.f4722g != null) {
                C0226q.this.f4722g.a(false);
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (C0226q.this.f4722g != null) {
                C0226q.this.f4722g.a(false);
            }
            if (i2 == 401) {
                g0.e.w();
                return;
            }
            if (g0.e.v(i2, str)) {
                return;
            }
            String strJ = C0226q.this.j(i2, str);
            if (strJ != null) {
                Toast.makeText(C0226q.this.f4716a, strJ, 0).show();
                return;
            }
            Toast.makeText(C0226q.this.f4716a, "发送失败: " + i2, 0).show();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.q$b */
    public class b implements r.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ C.d f4725a;

        public b(C.d dVar) {
            this.f4725a = dVar;
        }

        @Override // com.im.oldchat.ui.r.a
        public void a(String str) {
            if (C0226q.this.f4722g != null) {
                C0226q.this.f4722g.a(false);
            }
            Toast.makeText(C0226q.this.f4716a, str, 0).show();
        }

        @Override // com.im.oldchat.ui.r.a
        public void b(String str) {
            try {
                C0226q.this.n("image", str, "", 0, o0.D.a("", this.f4725a, null, "emoji"));
            } catch (Exception unused) {
                Toast.makeText(C0226q.this.f4716a, "发送表情失败", 0).show();
            }
            if (C0226q.this.f4722g != null) {
                C0226q.this.f4722g.a(false);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.q$c */
    public class c implements d.i {
        public c() {
        }

        @Override // g0.d.i
        public void b(String str) {
            C0226q.this.p();
            j0.k kVarL = C0226q.this.f4717b == null ? null : C0226q.this.f4717b.l(str);
            if (kVarL != null && C0226q.this.f4717b != null) {
                C0226q.this.f4717b.a(kVarL, C0226q.this.f4717b.g(), C0226q.this.f4720e);
            } else if (C0226q.this.f4717b != null) {
                C0226q.this.f4717b.z(C0226q.this.f4720e, false, 0L, false);
            }
            if (C0226q.this.f4723h != null) {
                C0226q.this.f4723h.a();
            }
            if (C0226q.this.f4722g != null) {
                C0226q.this.f4722g.a(false);
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (C0226q.this.f4722g != null) {
                C0226q.this.f4722g.a(false);
            }
            if (i2 == 401) {
                g0.e.w();
                return;
            }
            if (g0.e.v(i2, str)) {
                return;
            }
            if (str != null && str.contains("duration_too_long")) {
                Toast.makeText(C0226q.this.f4716a, "语音不能超过60秒", 0).show();
                return;
            }
            String strJ = C0226q.this.j(i2, str);
            if (strJ != null) {
                Toast.makeText(C0226q.this.f4716a, strJ, 0).show();
                return;
            }
            Toast.makeText(C0226q.this.f4716a, "发送失败: " + i2, 0).show();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.q$d */
    public interface d {
        void a();
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.q$e */
    public interface e {
        void a(boolean z2);
    }

    public C0226q(Activity activity, C0386j c0386j, TextView textView, EditText editText, String str, String str2, e eVar, d dVar) {
        this.f4716a = activity;
        this.f4717b = c0386j;
        this.f4718c = textView;
        this.f4719d = editText;
        this.f4720e = str;
        this.f4721f = str2;
        this.f4722g = eVar;
        this.f4723h = dVar;
    }

    public final String j(int i2, String str) {
        if (i2 != 403 || str == null) {
            return null;
        }
        String lowerCase = str.toLowerCase();
        return lowerCase.contains("not_friends") ? "你们还不是好友，不能发送消息" : lowerCase.contains("user_banned") ? "账号已被封禁，无法发送消息" : lowerCase.contains("video_disabled") ? "服务器禁用了视频/3GP上传，请联系管理员" : "发送失败: 403";
    }

    public void k(String str, boolean z2, C.d dVar) {
        if (str == null || str.length() == 0) {
            return;
        }
        File file = new File(str);
        if (!file.exists()) {
            Toast.makeText(this.f4716a, "表情文件不存在", 0).show();
            return;
        }
        e eVar = this.f4722g;
        if (eVar != null) {
            eVar.a(true);
        }
        r.d(this.f4716a, file, z2, this.f4720e, new b(dVar));
    }

    public void l(String str, String str2, long j2, C.d dVar) {
        n("resource", str, "", 0, o0.D.a(AbstractC0403s.a(str2, j2, ""), dVar, null, null));
    }

    public void m(String str, String str2, String str3, int i2, C.d dVar) {
        n(str, str2, str3, i2, o0.D.a("", dVar, null, null));
    }

    public final void n(String str, String str2, String str3, int i2, String str4) {
        try {
            if ("voice".equals(str)) {
                if (i2 <= 0) {
                    Toast.makeText(this.f4716a, "语音时长无效", 0).show();
                    return;
                } else if (i2 > 60000) {
                    i2 = 60000;
                }
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("to_uid", this.f4721f);
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
            e eVar = this.f4722g;
            if (eVar != null) {
                eVar.a(true);
            }
            g0.d.S("/direct/send", jSONObject, this.f4720e, new c());
        } catch (Exception unused) {
            Toast.makeText(this.f4716a, "发送失败", 0).show();
            e eVar2 = this.f4722g;
            if (eVar2 != null) {
                eVar2.a(false);
            }
        }
    }

    public void o(String str, C.d dVar) {
        String strTrim = str == null ? "" : str.trim();
        if (strTrim.length() == 0 && dVar == null) {
            return;
        }
        String strA = o0.D.a(strTrim, dVar, null, null);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("to_uid", this.f4721f);
            jSONObject.put("body", strA);
            jSONObject.put("msg_type", TextBundle.TEXT_ENTRY);
            e eVar = this.f4722g;
            if (eVar != null) {
                eVar.a(true);
            }
            g0.d.S("/direct/send", jSONObject, this.f4720e, new a());
        } catch (Exception unused) {
            e eVar2 = this.f4722g;
            if (eVar2 != null) {
                eVar2.a(false);
            }
        }
    }

    public final void p() {
        TextView textView = this.f4718c;
        if (textView != null) {
            textView.setEnabled(true);
            this.f4718c.setText(f0.j.l1);
        }
    }
}
