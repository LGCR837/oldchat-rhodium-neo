package com.im.oldchat.ui;

import android.content.Context;
import android.widget.Toast;
import g0.d;
import org.json.JSONObject;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: renamed from: com.im.oldchat.ui.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0228t {

    /* JADX INFO: renamed from: com.im.oldchat.ui.t$a */
    public class a implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f4748a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f4749b;

        public a(Context context, String str) {
            this.f4748a = context;
            this.f4749b = str;
        }

        @Override // g0.d.i
        public void b(String str) {
            Context context = this.f4748a;
            String str2 = this.f4749b;
            Toast.makeText(context, (str2 == null || str2.length() == 0) ? "已收藏" : this.f4749b, 0).show();
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            if (g0.e.v(i2, str)) {
                return;
            }
            Toast.makeText(this.f4748a, "收藏失败", 0).show();
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.t$b */
    public class b implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c f4750a;

        public b(c cVar) {
            this.f4750a = cVar;
        }

        @Override // g0.d.i
        public void b(String str) {
            c cVar = this.f4750a;
            if (cVar != null) {
                cVar.a(true);
            }
        }

        @Override // g0.d.i
        public void c(int i2, String str) {
            c cVar = this.f4750a;
            if (cVar != null) {
                cVar.a(false);
            }
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.t$c */
    public interface c {
        void a(boolean z2);
    }

    public static void a(Context context, String str, String str2, String str3, String str4, String str5) {
        String str6;
        String string;
        String strI = i(str);
        if (strI.length() == 0) {
            return;
        }
        String str7 = "chat_image".equals(strI) ? "聊天图片" : "chat_voice".equals(strI) ? "聊天语音" : "chat_video".equals(strI) ? "聊天视频" : "聊天";
        if (str3 == null || str3.length() == 0) {
            str6 = "";
        } else {
            str6 = "来自 " + str3;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            if (str5 == null) {
                str5 = "";
            }
            jSONObject.put("scene", str5);
            if (str == null) {
                str = "";
            }
            jSONObject.put("msg_type", str);
            string = jSONObject.toString();
        } catch (Exception unused) {
            string = "";
        }
        c(context, strI, str2, str7, str6, str4, string, "已加入收藏");
    }

    public static void b(Context context, String str, String str2, String str3, String str4, String str5) {
        String string;
        try {
            JSONObject jSONObject = new JSONObject();
            if (str5 == null) {
                str5 = "";
            }
            jSONObject.put("package_url", str5);
            string = jSONObject.toString();
        } catch (Exception unused) {
            string = "";
        }
        if (str2 == null || str2.length() == 0) {
            str2 = "表情包";
        }
        c(context, "emoji_pack", str, str2, str3 == null ? "" : str3, str4, string, "已收藏表情包");
    }

    public static void c(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        if (context == null) {
            return;
        }
        String strF = f(context);
        if (strF == null || strF.length() == 0) {
            Toast.makeText(context, "请先登录", 0).show();
            return;
        }
        String strG = g(str2, str5);
        if (strG.length() == 0) {
            Toast.makeText(context, "收藏失败：目标无效", 0).show();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            if (str == null) {
                str = "";
            }
            jSONObject.put("type", str);
            jSONObject.put("target_id", strG);
            if (str3 == null) {
                str3 = "";
            }
            jSONObject.put(MessageBundle.TITLE_ENTRY, str3);
            if (str4 == null) {
                str4 = "";
            }
            jSONObject.put("subtitle", str4);
            if (str5 == null) {
                str5 = "";
            }
            jSONObject.put("media_url", str5);
            if (str6 == null) {
                str6 = "";
            }
            jSONObject.put("extra", str6);
            g0.d.S("/favorites/add", jSONObject, strF, new a(context, str7));
        } catch (Exception unused) {
            Toast.makeText(context, "收藏失败", 0).show();
        }
    }

    public static void d(Context context, String str, String str2, String str3, String str4, String str5) {
        String string;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("song_url", str4 == null ? "" : str4);
            if (str5 == null) {
                str5 = "";
            }
            jSONObject.put("cover_url", str5);
            string = jSONObject.toString();
        } catch (Exception unused) {
            string = "";
        }
        if (str2 == null || str2.length() == 0) {
            str2 = "歌曲";
        }
        c(context, "music_song", str, str2, str3 == null ? "" : str3, str4, string, "已收藏歌曲");
    }

    public static void e(Context context, j0.t tVar) {
        if (tVar == null) {
            return;
        }
        String str = tVar.f5620c;
        String str2 = (str == null || str.length() == 0) ? "资源文件" : tVar.f5620c;
        String str3 = tVar.f5624g;
        if (str3 == null || str3.length() == 0) {
            str3 = tVar.f5623f;
        }
        String str4 = tVar.f5618a;
        if (str3 == null) {
            str3 = "";
        }
        c(context, "resource_file", str4, str2, str3, tVar.f5621d, "", "已收藏资源");
    }

    public static String f(Context context) {
        return context == null ? "" : context.getSharedPreferences("auth", 0).getString("access_token", "");
    }

    public static String g(String str, String str2) {
        if (str != null && str.trim().length() > 0) {
            return str.trim();
        }
        if (str2 == null || str2.trim().length() == 0) {
            return "";
        }
        return "url_" + Integer.toHexString(str2.trim().hashCode());
    }

    public static void h(Context context, String str, String str2, String str3, c cVar) {
        if (context == null) {
            if (cVar != null) {
                cVar.a(false);
                return;
            }
            return;
        }
        String strF = f(context);
        if (strF == null || strF.length() == 0) {
            if (cVar != null) {
                cVar.a(false);
            }
            Toast.makeText(context, "请先登录", 0).show();
            return;
        }
        String strG = g(str2, str3);
        if (strG.length() == 0) {
            if (cVar != null) {
                cVar.a(false);
                return;
            }
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            if (str == null) {
                str = "";
            }
            jSONObject.put("type", str);
            jSONObject.put("target_id", strG);
            g0.d.S("/favorites/remove", jSONObject, strF, new b(cVar));
        } catch (Exception unused) {
            if (cVar != null) {
                cVar.a(false);
            }
        }
    }

    public static String i(String str) {
        if (str == null) {
            return "";
        }
        String lowerCase = str.toLowerCase();
        return "image".equals(lowerCase) ? "chat_image" : "voice".equals(lowerCase) ? "chat_voice" : "video".equals(lowerCase) ? "chat_video" : "";
    }
}
