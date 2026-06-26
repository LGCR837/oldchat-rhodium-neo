package l0;

import android.os.Handler;
import android.os.Looper;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: l0.p0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0399p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Handler f6238a = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: l0.p0$a */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6239a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f6240b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ f f6241c;

        public a(String str, String str2, f fVar) {
            this.f6239a = str;
            this.f6240b = str2;
            this.f6241c = fVar;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            try {
                g gVarR = AbstractC0399p0.r("GET", "https://api.bilibili.com/x/web-interface/archive/has/like?bvid=" + AbstractC0399p0.i(this.f6239a.trim()), "", this.f6240b);
                if (!gVarR.a()) {
                    AbstractC0399p0.l(this.f6241c, false, "获取点赞状态失败", false);
                    return;
                }
                JSONObject jSONObject = new JSONObject(gVarR.f6261b);
                if (jSONObject.optInt("code", -1) != 0) {
                    AbstractC0399p0.l(this.f6241c, false, jSONObject.optString("message", "获取点赞状态失败"), false);
                } else {
                    AbstractC0399p0.l(this.f6241c, true, "", jSONObject.optInt("data", 0) == 1);
                }
            } catch (Exception unused) {
                AbstractC0399p0.l(this.f6241c, false, "获取点赞状态失败", false);
            }
        }
    }

    /* JADX INFO: renamed from: l0.p0$b */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6242a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f6243b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f6244c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f6245d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f6246e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ h f6247f;

        public b(String str, boolean z2, String str2, String str3, String str4, h hVar) {
            this.f6242a = str;
            this.f6243b = z2;
            this.f6244c = str2;
            this.f6245d = str3;
            this.f6246e = str4;
            this.f6247f = hVar;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("bvid=");
                sb.append(AbstractC0399p0.i(this.f6242a.trim()));
                sb.append("&like=");
                sb.append(this.f6243b ? "1" : "2");
                if (!AbstractC0399p0.h(this.f6244c)) {
                    sb.append("&access_key=");
                    sb.append(AbstractC0399p0.i(this.f6244c));
                }
                sb.append("&csrf=");
                sb.append(AbstractC0399p0.i(this.f6245d));
                sb.append("&csrf_token=");
                sb.append(AbstractC0399p0.i(this.f6245d));
                g gVarR = AbstractC0399p0.r("POST", "https://api.bilibili.com/x/web-interface/archive/like", sb.toString(), this.f6246e);
                if (!gVarR.a()) {
                    AbstractC0399p0.m(this.f6247f, false, "点赞请求失败");
                    return;
                }
                JSONObject jSONObject = new JSONObject(gVarR.f6261b);
                if (jSONObject.optInt("code", -1) == 0) {
                    AbstractC0399p0.m(this.f6247f, true, this.f6243b ? "点赞成功" : "已取消点赞");
                } else {
                    AbstractC0399p0.m(this.f6247f, false, jSONObject.optString("message", "点赞失败"));
                }
            } catch (Exception unused) {
                AbstractC0399p0.m(this.f6247f, false, "点赞失败");
            }
        }
    }

    /* JADX INFO: renamed from: l0.p0$c */
    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6248a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ h f6249b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ long f6250c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f6251d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f6252e;

        public c(String str, h hVar, long j2, String str2, String str3) {
            this.f6248a = str;
            this.f6249b = hVar;
            this.f6250c = j2;
            this.f6251d = str2;
            this.f6252e = str3;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            try {
                long jP = AbstractC0399p0.p(this.f6248a);
                long jN = jP > 0 ? AbstractC0399p0.n(jP, this.f6248a) : 0L;
                if (jN <= 0) {
                    AbstractC0399p0.m(this.f6249b, false, "请先创建收藏夹");
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("rid=");
                sb.append(this.f6250c);
                sb.append("&type=2");
                sb.append("&add_media_ids=");
                sb.append(jN);
                sb.append("&del_media_ids=");
                if (!AbstractC0399p0.h(this.f6251d)) {
                    sb.append("&access_key=");
                    sb.append(AbstractC0399p0.i(this.f6251d));
                }
                sb.append("&csrf=");
                sb.append(AbstractC0399p0.i(this.f6252e));
                sb.append("&csrf_token=");
                sb.append(AbstractC0399p0.i(this.f6252e));
                g gVarR = AbstractC0399p0.r("POST", "https://api.bilibili.com/x/v3/fav/resource/deal", sb.toString(), this.f6248a);
                if (!gVarR.a()) {
                    AbstractC0399p0.m(this.f6249b, false, "收藏请求失败");
                    return;
                }
                JSONObject jSONObject = new JSONObject(gVarR.f6261b);
                if (jSONObject.optInt("code", -1) == 0) {
                    AbstractC0399p0.m(this.f6249b, true, "收藏成功");
                } else {
                    AbstractC0399p0.m(this.f6249b, false, jSONObject.optString("message", "收藏失败"));
                }
            } catch (Exception unused) {
                AbstractC0399p0.m(this.f6249b, false, "收藏失败");
            }
        }
    }

    /* JADX INFO: renamed from: l0.p0$d */
    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ f f6253a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f6254b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f6255c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ boolean f6256d;

        public d(f fVar, boolean z2, String str, boolean z3) {
            this.f6253a = fVar;
            this.f6254b = z2;
            this.f6255c = str;
            this.f6256d = z3;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f6253a.a(this.f6254b, this.f6255c, this.f6256d);
        }
    }

    /* JADX INFO: renamed from: l0.p0$e */
    public class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h f6257a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f6258b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f6259c;

        public e(h hVar, boolean z2, String str) {
            this.f6257a = hVar;
            this.f6258b = z2;
            this.f6259c = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f6257a.a(this.f6258b, this.f6259c);
        }
    }

    /* JADX INFO: renamed from: l0.p0$f */
    public interface f {
        void a(boolean z2, String str, boolean z3);
    }

    /* JADX INFO: renamed from: l0.p0$g */
    public static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f6260a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f6261b;

        public g(int i2, String str) {
            this.f6260a = i2;
            this.f6261b = str == null ? "" : str;
        }

        public boolean a() {
            int i2 = this.f6260a;
            return i2 >= 200 && i2 < 300;
        }
    }

    /* JADX INFO: renamed from: l0.p0$h */
    public interface h {
        void a(boolean z2, String str);
    }

    public static boolean h(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String i(String str) {
        if (str == null) {
            str = "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception unused) {
            return "";
        }
    }

    public static String j(String str) {
        if (str == null) {
            return "";
        }
        String[] strArrSplit = str.split(";");
        for (int i2 = 0; i2 < strArrSplit.length; i2++) {
            String str2 = strArrSplit[i2];
            String strTrim = str2 == null ? "" : str2.trim();
            if (strTrim.startsWith("bili_jct=")) {
                return strTrim.substring(9).trim();
            }
        }
        return "";
    }

    public static void k(long j2, String str, String str2, h hVar) {
        String strJ = j(str2);
        if (j2 <= 0) {
            m(hVar, false, "视频参数错误");
        } else if (h(strJ)) {
            m(hVar, false, "请先登录B站账号");
        } else {
            s(new c(str2, hVar, j2, str, strJ));
        }
    }

    public static void l(f fVar, boolean z2, String str, boolean z3) {
        if (fVar == null) {
            return;
        }
        f6238a.post(new d(fVar, z2, str, z3));
    }

    public static void m(h hVar, boolean z2, String str) {
        if (hVar == null) {
            return;
        }
        f6238a.post(new e(hVar, z2, str));
    }

    public static long n(long j2, String str) throws Throwable {
        try {
            g gVarR = r("GET", "https://api.bilibili.com/x/v3/fav/folder/created/list-all?up_mid=" + j2 + "&type=2", "", str);
            if (!gVarR.a()) {
                return 0L;
            }
            JSONObject jSONObject = new JSONObject(gVarR.f6261b);
            JSONObject jSONObjectOptJSONObject = null;
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optInt("code", -1) == 0 ? jSONObject.optJSONObject("data") : null;
            JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject2 == null ? null : jSONObjectOptJSONObject2.optJSONArray("list");
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(0);
            }
            if (jSONObjectOptJSONObject == null) {
                return 0L;
            }
            long jOptLong = jSONObjectOptJSONObject.optLong("id", 0L);
            return jOptLong > 0 ? jOptLong : jSONObjectOptJSONObject.optLong("fid", 0L);
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static void o(String str, String str2, f fVar) {
        if (h(str)) {
            l(fVar, false, "视频参数错误", false);
        } else {
            s(new a(str, str2, fVar));
        }
    }

    public static long p(String str) throws Throwable {
        try {
            g gVarR = r("GET", "https://api.bilibili.com/x/web-interface/nav", "", str);
            if (!gVarR.a()) {
                return 0L;
            }
            JSONObject jSONObject = new JSONObject(gVarR.f6261b);
            JSONObject jSONObjectOptJSONObject = jSONObject.optInt("code", -1) == 0 ? jSONObject.optJSONObject("data") : null;
            if (jSONObjectOptJSONObject == null) {
                return 0L;
            }
            return jSONObjectOptJSONObject.optLong("mid", 0L);
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static String q(InputStream inputStream) {
        if (inputStream == null) {
            return "";
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    return sb.toString();
                }
                sb.append(line);
            }
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00c8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static l0.AbstractC0399p0.g r(java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l0.AbstractC0399p0.r(java.lang.String, java.lang.String, java.lang.String, java.lang.String):l0.p0$g");
    }

    public static void s(Runnable runnable) {
        new Thread(runnable, "oldview-action").start();
    }

    public static void t(String str, boolean z2, String str2, String str3, h hVar) {
        String strJ = j(str3);
        if (h(str)) {
            m(hVar, false, "视频参数错误");
        } else if (h(strJ)) {
            m(hVar, false, "请先登录B站账号");
        } else {
            s(new b(str, z2, str2, strJ, str3, hVar));
        }
    }
}
