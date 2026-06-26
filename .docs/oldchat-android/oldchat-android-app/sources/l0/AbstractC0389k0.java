package l0;

import android.os.Handler;
import android.os.Looper;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import org.json.JSONObject;

/* JADX INFO: renamed from: l0.k0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0389k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Handler f6138a = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: l0.k0$a */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ long f6139a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f6140b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f6141c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ e f6142d;

        public a(long j2, String str, String str2, e eVar) {
            this.f6139a = j2;
            this.f6140b = str;
            this.f6141c = str2;
            this.f6142d = eVar;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("https://api.bilibili.com/");
                sb.append("x/relation?fid=");
                sb.append(this.f6139a);
                if (!AbstractC0389k0.h(this.f6140b)) {
                    sb.append("&access_key=");
                    sb.append(AbstractC0389k0.i(this.f6140b.trim()));
                }
                f fVarQ = AbstractC0389k0.q("GET", sb.toString(), "", this.f6141c);
                if (!fVarQ.a()) {
                    AbstractC0389k0.l(this.f6142d, false, "获取关注状态失败", false);
                    return;
                }
                JSONObject jSONObject = new JSONObject(fVarQ.f6158b);
                if (jSONObject.optInt("code", -1) != 0) {
                    AbstractC0389k0.l(this.f6142d, false, jSONObject.optString("message", "获取关注状态失败"), false);
                } else {
                    AbstractC0389k0.l(this.f6142d, true, "", AbstractC0389k0.k(AbstractC0389k0.o(jSONObject.optJSONObject("data"))));
                }
            } catch (Exception unused) {
                AbstractC0389k0.l(this.f6142d, false, "获取关注状态失败", false);
            }
        }
    }

    /* JADX INFO: renamed from: l0.k0$b */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ long f6143a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f6144b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f6145c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f6146d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f6147e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ g f6148f;

        public b(long j2, boolean z2, String str, String str2, String str3, g gVar) {
            this.f6143a = j2;
            this.f6144b = z2;
            this.f6145c = str;
            this.f6146d = str2;
            this.f6147e = str3;
            this.f6148f = gVar;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("fid=");
                sb.append(this.f6143a);
                sb.append("&act=");
                sb.append(this.f6144b ? 1 : 2);
                sb.append("&re_src=11");
                if (!AbstractC0389k0.h(this.f6145c)) {
                    sb.append("&access_key=");
                    sb.append(AbstractC0389k0.i(this.f6145c.trim()));
                }
                sb.append("&csrf=");
                sb.append(AbstractC0389k0.i(this.f6146d));
                sb.append("&csrf_token=");
                sb.append(AbstractC0389k0.i(this.f6146d));
                f fVarQ = AbstractC0389k0.q("POST", "https://api.bilibili.com/x/relation/modify", sb.toString(), this.f6147e);
                if (!fVarQ.a()) {
                    AbstractC0389k0.m(this.f6148f, false, "关注操作失败", !this.f6144b);
                    return;
                }
                JSONObject jSONObject = new JSONObject(fVarQ.f6158b);
                if (jSONObject.optInt("code", -1) != 0) {
                    AbstractC0389k0.m(this.f6148f, false, jSONObject.optString("message", "关注操作失败"), !this.f6144b);
                    return;
                }
                g gVar = this.f6148f;
                boolean z2 = this.f6144b;
                AbstractC0389k0.m(gVar, true, z2 ? "关注成功" : "已取消关注", z2);
            } catch (Exception unused) {
                AbstractC0389k0.m(this.f6148f, false, "关注操作失败", true ^ this.f6144b);
            }
        }
    }

    /* JADX INFO: renamed from: l0.k0$c */
    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ e f6149a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f6150b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f6151c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ boolean f6152d;

        public c(e eVar, boolean z2, String str, boolean z3) {
            this.f6149a = eVar;
            this.f6150b = z2;
            this.f6151c = str;
            this.f6152d = z3;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f6149a.a(this.f6150b, this.f6151c, this.f6152d);
        }
    }

    /* JADX INFO: renamed from: l0.k0$d */
    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ g f6153a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f6154b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f6155c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ boolean f6156d;

        public d(g gVar, boolean z2, String str, boolean z3) {
            this.f6153a = gVar;
            this.f6154b = z2;
            this.f6155c = str;
            this.f6156d = z3;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f6153a.a(this.f6154b, this.f6155c, this.f6156d);
        }
    }

    /* JADX INFO: renamed from: l0.k0$e */
    public interface e {
        void a(boolean z2, String str, boolean z3);
    }

    /* JADX INFO: renamed from: l0.k0$f */
    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f6157a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f6158b;

        public f(int i2, String str) {
            this.f6157a = i2;
            this.f6158b = str == null ? "" : str;
        }

        public boolean a() {
            int i2 = this.f6157a;
            return i2 >= 200 && i2 < 300;
        }
    }

    /* JADX INFO: renamed from: l0.k0$g */
    public interface g {
        void a(boolean z2, String str, boolean z3);
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

    public static boolean k(int i2) {
        return (i2 & 2) == 2 || i2 == 6;
    }

    public static void l(e eVar, boolean z2, String str, boolean z3) {
        if (eVar == null) {
            return;
        }
        f6138a.post(new c(eVar, z2, str, z3));
    }

    public static void m(g gVar, boolean z2, String str, boolean z3) {
        if (gVar == null) {
            return;
        }
        f6138a.post(new d(gVar, z2, str, z3));
    }

    public static void n(long j2, String str, String str2, e eVar) {
        if (j2 <= 0) {
            l(eVar, false, "UP 参数错误", false);
        } else {
            r(new a(j2, str, str2, eVar));
        }
    }

    public static int o(JSONObject jSONObject) {
        if (jSONObject == null) {
            return 0;
        }
        if (jSONObject.has("attribute")) {
            return jSONObject.optInt("attribute", 0);
        }
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("be_relation");
        if (jSONObjectOptJSONObject == null || !jSONObjectOptJSONObject.has("attribute")) {
            return 0;
        }
        return jSONObjectOptJSONObject.optInt("attribute", 0);
    }

    public static String p(InputStream inputStream) {
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
    public static l0.AbstractC0389k0.f q(java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l0.AbstractC0389k0.q(java.lang.String, java.lang.String, java.lang.String, java.lang.String):l0.k0$f");
    }

    public static void r(Runnable runnable) {
        new Thread(runnable, "oldview-up-action").start();
    }

    public static void s(long j2, boolean z2, String str, String str2, g gVar) {
        if (j2 <= 0) {
            m(gVar, false, "UP 参数错误", false);
            return;
        }
        String strJ = j(str2);
        if (h(strJ)) {
            m(gVar, false, "请先登录B站账号", false);
        } else {
            r(new b(j2, z2, str, strJ, str2, gVar));
        }
    }
}
