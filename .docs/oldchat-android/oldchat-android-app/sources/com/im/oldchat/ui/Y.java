package com.im.oldchat.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;
import com.im.oldchat.ui.f0;
import g0.d;
import java.util.Locale;
import o.c;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class Y {

    public class a implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f4316a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f4317b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ f f4318c;

        public a(Activity activity, String str, f fVar) {
            this.f4316a = activity;
            this.f4317b = str;
            this.f4318c = fVar;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (i2 == 0) {
                Y.i(this.f4316a, this.f4317b, this.f4318c);
            } else {
                Y.j(this.f4316a, this.f4317b, this.f4318c);
            }
        }
    }

    public class b implements f0.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f4319a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f4320b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ f f4321c;

        public b(Activity activity, String str, f fVar) {
            this.f4319a = activity;
            this.f4320b = str;
            this.f4321c = fVar;
        }

        @Override // com.im.oldchat.ui.f0.e
        public void a(j0.w wVar) {
            String str;
            if (wVar == null || (str = wVar.f5646b) == null || str.length() == 0) {
                return;
            }
            Y.k(this.f4319a, this.f4320b, this.f4321c, wVar);
        }
    }

    public class c implements f0.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f4322a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f4323b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ f f4324c;

        public c(Activity activity, String str, f fVar) {
            this.f4322a = activity;
            this.f4323b = str;
            this.f4324c = fVar;
        }

        @Override // com.im.oldchat.ui.f0.f
        public void a(j0.e eVar) {
            String str;
            if (eVar == null || (str = eVar.f5504a) == null || str.length() == 0) {
                return;
            }
            Y.l(this.f4322a, this.f4323b, this.f4324c, eVar);
        }
    }

    public class d implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f4325a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ j0.w f4326b;

        public d(Activity activity, j0.w wVar) {
            this.f4325a = activity;
            this.f4326b = wVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(this.f4325a, "已发送", 0).show();
            Intent intent = new Intent(this.f4325a, (Class<?>) ChatActivity.class);
            intent.putExtra("friend_uid", this.f4326b.f5646b);
            intent.putExtra("friend_name", j0.d.b(this.f4326b));
            intent.putExtra("friend_avatar", this.f4326b.f5651g);
            this.f4325a.startActivity(intent);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(this.f4325a, "发送失败: " + i2, 0).show();
        }
    }

    public class e implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f4327a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ j0.e f4328b;

        public e(Activity activity, j0.e eVar) {
            this.f4327a = activity;
            this.f4328b = eVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            Toast.makeText(this.f4327a, "已发送", 0).show();
            Intent intent = new Intent(this.f4327a, (Class<?>) GroupChatActivity.class);
            intent.putExtra("group_id", this.f4328b.f5504a);
            intent.putExtra("group_name", this.f4328b.f5505b);
            intent.putExtra("group_avatar", this.f4328b.f5506c);
            intent.putExtra("group_role", this.f4328b.f5509f);
            this.f4327a.startActivity(intent);
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(this.f4327a, "发送失败: " + i2, 0).show();
        }
    }

    public static final class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f4329a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f4330b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f4331c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f4332d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f4333e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f4334f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f4335g;
    }

    public static JSONObject e(f fVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("msg_type", "resource");
        jSONObject.put("media_url", n(fVar.f4331c));
        String strH = h(fVar.f4332d);
        if (strH.length() > 0) {
            jSONObject.put("thumb_url", strH);
        }
        jSONObject.put("body", o0.D.a(f(fVar), null, null, "music"));
        return jSONObject;
    }

    public static String f(f fVar) {
        String strN = n(fVar == null ? null : fVar.f4330b);
        if (strN.length() == 0) {
            strN = "音乐分享";
        }
        String strN2 = n(fVar == null ? null : fVar.f4334f);
        if (strN2.length() == 0) {
            strN2 = n(fVar == null ? null : fVar.f4333e);
        }
        if (strN2.length() == 0) {
            strN2 = "未知歌手";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("歌曲: ");
        sb.append(strN);
        sb.append("\n歌手: ");
        sb.append(strN2);
        String strG = g(fVar == null ? 0 : fVar.f4335g);
        if (strG.length() > 0) {
            sb.append("\n时长: ");
            sb.append(strG);
        }
        String strH = h(fVar != null ? fVar.f4332d : null);
        if (strH.length() > 0) {
            sb.append("\n封面: ");
            sb.append(strH);
        }
        sb.append("\n点击播放");
        return sb.toString();
    }

    public static String g(int i2) {
        if (i2 <= 0) {
            return "";
        }
        int i3 = i2 / 1000;
        int i4 = i3 / 60;
        int i5 = i3 % 60;
        if (i4 > 99) {
            i4 = 99;
        }
        return String.format(Locale.getDefault(), "%02d:%02d", Integer.valueOf(i4), Integer.valueOf(i5));
    }

    public static String h(String str) {
        String strN = n(str);
        if (strN.length() == 0) {
            return "";
        }
        if (strN.length() > 1 && ((strN.startsWith("\"") && strN.endsWith("\"")) || (strN.startsWith("'") && strN.endsWith("'")))) {
            strN = strN.substring(1, strN.length() - 1).trim();
        }
        String strReplace = strN.replace("\\u0026", "&").replace("\\/", "/");
        if (strReplace.startsWith("v1/") || strReplace.startsWith("music/") || strReplace.startsWith("uploads/")) {
            strReplace = "/" + strReplace;
        }
        if (strReplace.startsWith("/v1/uploads/media/")) {
            String strTrim = strReplace.substring(18).trim();
            if (strTrim.length() <= 0 || strTrim.indexOf(47) >= 0 || strTrim.indexOf(92) >= 0) {
                return strReplace;
            }
            return "/v1/music/cover/" + strTrim;
        }
        if (!strReplace.startsWith("/uploads/media/")) {
            if (!strReplace.startsWith("/music/cover/")) {
                return strReplace;
            }
            return "/v1" + strReplace;
        }
        String strTrim2 = strReplace.substring(15).trim();
        if (strTrim2.length() <= 0 || strTrim2.indexOf(47) >= 0 || strTrim2.indexOf(92) >= 0) {
            return strReplace;
        }
        return "/v1/music/cover/" + strTrim2;
    }

    public static void i(Activity activity, String str, f fVar) {
        f0.k(activity, j0.c.b(activity), "选择好友", new b(activity, str, fVar));
    }

    public static void j(Activity activity, String str, f fVar) {
        f0.l(activity, j0.f.b(activity), "选择群聊", new c(activity, str, fVar));
    }

    public static void k(Activity activity, String str, f fVar, j0.w wVar) {
        if (!o0.G.d().f()) {
            Toast.makeText(activity, "网络不可用", 0).show();
            return;
        }
        try {
            JSONObject jSONObjectE = e(fVar);
            jSONObjectE.put("to_uid", wVar.f5646b);
            g0.d.S("/direct/send", jSONObjectE, str, new d(activity, wVar));
        } catch (Exception unused) {
            Toast.makeText(activity, "发送失败", 0).show();
        }
    }

    public static void l(Activity activity, String str, f fVar, j0.e eVar) {
        if (!o0.G.d().f()) {
            Toast.makeText(activity, "网络不可用", 0).show();
            return;
        }
        try {
            JSONObject jSONObjectE = e(fVar);
            jSONObjectE.put("group_id", eVar.f5504a);
            g0.d.S("/groups/message/send", jSONObjectE, str, new e(activity, eVar));
        } catch (Exception unused) {
            Toast.makeText(activity, "发送失败", 0).show();
        }
    }

    public static void m(Activity activity, String str, f fVar) {
        if (activity == null || fVar == null) {
            return;
        }
        if (str == null || str.length() == 0) {
            Toast.makeText(activity, "未登录", 0).show();
        } else if (n(fVar.f4331c).length() == 0) {
            Toast.makeText(activity, "歌曲链接不可用", 0).show();
        } else {
            new c.a(activity).t("分享音乐").g(new CharSequence[]{"分享给好友", "分享到群聊"}, new a(activity, str, fVar)).k("取消", null).v();
        }
    }

    public static String n(String str) {
        return str == null ? "" : str.trim();
    }
}
