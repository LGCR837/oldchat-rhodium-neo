package g0;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.im.oldchat.OldChatApplication;
import com.im.oldchat.ui.LoginActivity;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import o0.AbstractC0443i;
import o0.G;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile String f5335a = "http://60.205.94.101:8080/v1";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile long f5336b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f5337c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f5338d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile long f5339e = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static volatile boolean f5341g = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Object f5340f = new Object();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Object f5342h = new Object();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final HashMap f5343i = new HashMap();

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f5344a;

        public a(Context context) {
            this.f5344a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.f5344a, "登录已过期，请重新登录", 1).show();
            e.m(this.f5344a);
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f5345a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f5346b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public c f5347c;

        public b() {
        }

        public /* synthetic */ b(a aVar) {
            this();
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5348a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f5349b;

        public c(int i2, String str) {
            this.f5348a = i2;
            this.f5349b = str;
        }
    }

    public static c b(c cVar, String str, String str2) {
        if (cVar == null) {
            return cVar;
        }
        if (!s(str, str2)) {
            int i2 = cVar.f5348a;
            if (i2 >= 200 && i2 < 300) {
                r();
            }
            return cVar;
        }
        int i3 = cVar.f5348a;
        if (i3 != 401) {
            if (i3 < 200 || i3 >= 300) {
                G.d().h(cVar.f5348a);
                r();
            } else {
                G.d().i();
                r();
            }
            return cVar;
        }
        if (f5341g) {
            cVar.f5349b = "auth_silent";
        } else {
            int i4 = f5337c + 1;
            f5337c = i4;
            if (i4 < 5 || f5338d) {
                cVar.f5349b = "auth_silent";
            } else {
                f5338d = true;
                cVar.f5349b = "auth_warning";
            }
        }
        return cVar;
    }

    public static boolean c(String str) {
        synchronized (f5342h) {
            try {
                HashMap map = f5343i;
                b bVar = (b) map.get(str);
                a aVar = null;
                if (bVar == null) {
                    bVar = new b(aVar);
                    map.put(str, bVar);
                }
                if (bVar.f5345a) {
                    return false;
                }
                bVar.f5345a = true;
                bVar.f5347c = null;
                bVar.f5346b = 0L;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static String d(String str, String str2) {
        if (str == null) {
            str = "";
        }
        return str + "#" + (str2 == null ? 0 : str2.hashCode());
    }

    public static c e(c cVar) {
        if (cVar == null) {
            return null;
        }
        return new c(cVar.f5348a, cVar.f5349b);
    }

    public static boolean f() {
        return g0.a.b();
    }

    public static c g(String str, String str2, JSONObject jSONObject, String str3) throws Throwable {
        c cVar = new c(-1, "network_error");
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                cVar = h(str, str2, jSONObject, str3);
            } catch (Exception e2) {
                cVar = new c(-1, e2.getMessage());
            }
            if (!u(cVar.f5348a) || i2 >= 2) {
                break;
            }
            x(i2);
        }
        return cVar;
    }

    public static c h(String str, String str2, JSONObject jSONObject, String str3) throws Throwable {
        String strI;
        OutputStream outputStream;
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(f5335a + str2).openConnection();
        httpURLConnection.setConnectTimeout(8000);
        httpURLConnection.setReadTimeout(15000);
        httpURLConnection.setUseCaches("GET".equals(str));
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setRequestMethod(str);
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
        boolean zT = t(str2);
        if (zT) {
            if (g0.a.b()) {
                httpURLConnection.setRequestProperty("X-Enc", "1");
                String strO = AbstractC0443i.o();
                if (strO != null && strO.length() > 0) {
                    httpURLConnection.setRequestProperty("X-Session", strO);
                }
            } else {
                f5336b = System.currentTimeMillis() + 60000;
                AbstractC0443i.g();
                zT = false;
            }
        }
        if (str3 != null && str3.length() > 0) {
            httpURLConnection.setRequestProperty("Authorization", "Bearer " + str3);
        }
        if (jSONObject != null) {
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setDoOutput(true);
            try {
                outputStream = httpURLConnection.getOutputStream();
                try {
                    String string = jSONObject.toString();
                    if (zT) {
                        string = AbstractC0443i.j(string);
                    }
                    outputStream.write(string.getBytes("UTF-8"));
                    outputStream.flush();
                    try {
                        outputStream.close();
                    } catch (Exception unused) {
                    }
                } catch (Throwable th) {
                    th = th;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Exception unused2) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                outputStream = null;
            }
        }
        try {
            int responseCode = httpURLConnection.getResponseCode();
            String strP = p(httpURLConnection, responseCode);
            if (zT && (strI = AbstractC0443i.i(strP)) != null) {
                strP = strI;
            }
            c cVar = new c(responseCode, strP);
            try {
                httpURLConnection.disconnect();
            } catch (Exception unused3) {
            }
            return cVar;
        } finally {
            try {
                httpURLConnection.disconnect();
            } catch (Exception unused4) {
            }
        }
    }

    public static c i(String str, String str2, JSONObject jSONObject, String str3) throws Throwable {
        if (!"GET".equals(str)) {
            try {
                return h(str, str2, jSONObject, str3);
            } catch (Exception e2) {
                return new c(-1, e2.getMessage());
            }
        }
        if (!k(str2)) {
            return g(str, str2, jSONObject, str3);
        }
        String strD = d(str2, str3);
        c cVarO = o(strD);
        if (cVarO != null) {
            return cVarO;
        }
        boolean zC = c(strD);
        if (!zC) {
            c cVarY = y(strD);
            if (cVarY != null) {
                return cVarY;
            }
            zC = c(strD);
            if (!zC) {
                c cVarY2 = y(strD);
                if (cVarY2 != null) {
                    return cVarY2;
                }
                zC = true;
            }
        }
        c cVarG = g(str, str2, jSONObject, str3);
        if (zC) {
            j(strD, cVarG);
        }
        return cVarG;
    }

    public static void j(String str, c cVar) {
        Object obj = f5342h;
        synchronized (obj) {
            try {
                HashMap map = f5343i;
                b bVar = (b) map.get(str);
                if (bVar == null) {
                    bVar = new b(null);
                    map.put(str, bVar);
                }
                bVar.f5345a = false;
                bVar.f5346b = System.currentTimeMillis();
                bVar.f5347c = e(cVar);
                n();
                obj.notifyAll();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static boolean k(String str) {
        if (str != null && str.length() != 0 && !str.startsWith("/auth/") && !str.startsWith("/ws")) {
            String lowerCase = str.toLowerCase();
            if (lowerCase.indexOf("/messages") < 0 && lowerCase.indexOf("/typing") < 0 && lowerCase.indexOf("/redpackets") < 0 && lowerCase.indexOf("timestamp=") < 0 && lowerCase.indexOf("nonce=") < 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean l(String str) {
        if (str == null) {
            return false;
        }
        return str.contains("\"code\":\"invalid_session\"") || str.contains("invalid_session");
    }

    public static void m(Context context) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - f5339e < 15000) {
            return;
        }
        f5339e = jCurrentTimeMillis;
        Intent intent = new Intent(context, (Class<?>) LoginActivity.class);
        intent.addFlags(268468224);
        context.startActivity(intent);
    }

    public static void n() {
        c cVar;
        long jCurrentTimeMillis = System.currentTimeMillis();
        Iterator it = f5343i.entrySet().iterator();
        while (it.hasNext()) {
            b bVar = (b) ((Map.Entry) it.next()).getValue();
            if (bVar != null && !bVar.f5345a && (cVar = bVar.f5347c) != null) {
                int i2 = cVar.f5348a;
                if (jCurrentTimeMillis - bVar.f5346b > ((i2 < 200 || i2 >= 300) ? 300L : 1500L)) {
                    it.remove();
                }
            }
        }
        HashMap map = f5343i;
        if (map.size() <= 180) {
            return;
        }
        Iterator it2 = map.entrySet().iterator();
        while (f5343i.size() > 180 && it2.hasNext()) {
            b bVar2 = (b) ((Map.Entry) it2.next()).getValue();
            if (bVar2 != null && !bVar2.f5345a) {
                it2.remove();
            }
        }
    }

    public static c o(String str) {
        c cVar;
        long jCurrentTimeMillis = System.currentTimeMillis();
        synchronized (f5342h) {
            try {
                HashMap map = f5343i;
                b bVar = (b) map.get(str);
                if (bVar != null && !bVar.f5345a && (cVar = bVar.f5347c) != null) {
                    int i2 = cVar.f5348a;
                    if (i2 >= 200 && i2 < 300) {
                        if (jCurrentTimeMillis - bVar.f5346b <= 1500) {
                            return e(cVar);
                        }
                        map.remove(str);
                        return null;
                    }
                    if (jCurrentTimeMillis - bVar.f5346b > 300) {
                        map.remove(str);
                    }
                    return null;
                }
                return null;
            } finally {
            }
        }
    }

    public static String p(HttpURLConnection httpURLConnection, int i2) throws Throwable {
        InputStream gZIPInputStream;
        String contentEncoding;
        BufferedReader bufferedReader;
        if (httpURLConnection == null) {
            return "";
        }
        BufferedReader bufferedReader2 = null;
        try {
            if ((i2 >= 200 && i2 < 300) || (gZIPInputStream = httpURLConnection.getErrorStream()) == null) {
                gZIPInputStream = httpURLConnection.getInputStream();
            }
        } catch (Exception unused) {
            gZIPInputStream = null;
        }
        if (gZIPInputStream == null) {
            return "";
        }
        try {
            contentEncoding = httpURLConnection.getContentEncoding();
        } catch (Exception unused2) {
            contentEncoding = null;
        }
        if (contentEncoding != null && contentEncoding.toLowerCase().indexOf("gzip") >= 0) {
            try {
                gZIPInputStream = new GZIPInputStream(gZIPInputStream);
            } catch (Exception unused3) {
            }
        }
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(gZIPInputStream, "UTF-8"));
        } catch (Exception unused4) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
            String string = sb.toString();
            try {
                bufferedReader.close();
            } catch (Exception unused5) {
            }
            return string;
        } catch (Exception unused6) {
            bufferedReader2 = bufferedReader;
            try {
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                } else {
                    gZIPInputStream.close();
                }
            } catch (Exception unused7) {
            }
            return "";
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            try {
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                } else {
                    gZIPInputStream.close();
                }
            } catch (Exception unused8) {
            }
            throw th;
        }
    }

    public static c q(String str, String str2, JSONObject jSONObject, String str3) throws Throwable {
        try {
            c cVarI = i(str, str2, jSONObject, str3);
            if (cVarI.f5348a == 400 && l(cVarI.f5349b)) {
                AbstractC0443i.g();
                cVarI = i(str, str2, jSONObject, str3);
            }
            if (cVarI.f5348a == 401 && s(str2, str3)) {
                synchronized (f5340f) {
                    try {
                        if (f5341g) {
                            Thread.sleep(500L);
                            String strD = g0.a.d();
                            if (strD != null && !strD.isEmpty()) {
                                c cVarI2 = i(str, str2, jSONObject, strD);
                                int i2 = cVarI2.f5348a;
                                if (i2 >= 200 && i2 < 300) {
                                    r();
                                }
                                return b(cVarI2, str2, strD);
                            }
                        } else {
                            f5341g = true;
                            String strE = g0.a.e();
                            if (strE != null) {
                                r();
                                c cVarB = b(i(str, str2, jSONObject, strE), str2, strE);
                                f5341g = false;
                                return cVarB;
                            }
                            f5341g = false;
                        }
                        w();
                    } catch (Throwable th) {
                        f5341g = false;
                        throw th;
                    } finally {
                    }
                }
            }
            return b(cVarI, str2, str3);
        } catch (Exception e2) {
            G.d().h(-1);
            return new c(-1, e2.getMessage());
        }
    }

    public static void r() {
        f5337c = 0;
        f5338d = false;
    }

    public static boolean s(String str, String str2) {
        return (str2 == null || str2.length() == 0 || str.startsWith("/auth/login") || str.startsWith("/auth/register") || str.startsWith("/auth/refresh")) ? false : true;
    }

    public static boolean t(String str) {
        return System.currentTimeMillis() >= f5336b && str != null && !str.startsWith("/auth/handshake") && AbstractC0443i.s();
    }

    public static boolean u(int i2) {
        return i2 <= 0 || i2 == 408 || i2 == 503 || i2 == 504 || i2 == 429 || i2 == 500 || i2 == 502;
    }

    public static boolean v(int i2, String str) {
        if (i2 != 401 || str == null) {
            return false;
        }
        return "auth_silent".equals(str) || "auth_warning".equals(str);
    }

    public static void w() {
        Context contextA = OldChatApplication.a();
        if (contextA == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new a(contextA));
    }

    public static void x(int i2) {
        long j2 = (1 << i2) * 350;
        if (j2 > 1200) {
            j2 = 1200;
        }
        try {
            Thread.sleep(j2);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    public static c y(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis() + 4000;
        synchronized (f5342h) {
            while (true) {
                try {
                    b bVar = (b) f5343i.get(str);
                    if (bVar == null) {
                        return null;
                    }
                    if (!bVar.f5345a) {
                        if (bVar.f5347c == null) {
                            return null;
                        }
                        long jCurrentTimeMillis2 = System.currentTimeMillis() - bVar.f5346b;
                        c cVar = bVar.f5347c;
                        int i2 = cVar.f5348a;
                        if (i2 < 200 || i2 >= 300) {
                            if (jCurrentTimeMillis2 <= 300) {
                                return e(cVar);
                            }
                        } else if (jCurrentTimeMillis2 <= 1500) {
                            return e(cVar);
                        }
                        return null;
                    }
                    long jCurrentTimeMillis3 = jCurrentTimeMillis - System.currentTimeMillis();
                    if (jCurrentTimeMillis3 <= 0) {
                        return null;
                    }
                    try {
                        f5342h.wait(jCurrentTimeMillis3);
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        return null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
