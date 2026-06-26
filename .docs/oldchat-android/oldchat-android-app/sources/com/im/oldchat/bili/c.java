package com.im.oldchat.bili;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final z.e f2209a = new z.e();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile String f2210b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile String f2211c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile long f2212d;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f2213a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f2214b;

        public a(int i2, String str) {
            this.f2213a = i2;
            this.f2214b = str;
        }

        public String a() {
            String str = this.f2214b;
            return (str == null || str.length() <= 0) ? "网络错误" : this.f2214b;
        }

        public boolean b() {
            int i2 = this.f2213a;
            return i2 >= 200 && i2 < 300;
        }
    }

    public static void a(HttpURLConnection httpURLConnection, String str) {
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 4.0.4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Mobile Safari/537.36");
        httpURLConnection.setRequestProperty("Referer", "https://www.bilibili.com/");
        httpURLConnection.setRequestProperty("Origin", "https://www.bilibili.com");
        httpURLConnection.setRequestProperty("Accept", "application/json, text/plain, */*");
        httpURLConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
        String strB = b(str);
        if (strB == null || strB.length() <= 0) {
            return;
        }
        httpURLConnection.setRequestProperty("Cookie", strB);
    }

    public static String b(String str) {
        String strTrim = str != null ? str.trim() : "";
        if (strTrim.length() != 0 && strTrim.indexOf("buvid3") >= 0) {
            return strTrim;
        }
        String str2 = "buvid3=FE" + UUID.randomUUID().toString().toUpperCase(Locale.US) + "infoc";
        if (strTrim.length() == 0) {
            return str2;
        }
        return strTrim + "; " + str2;
    }

    public static String c(Map map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z2 = true;
        for (Map.Entry entry : map.entrySet()) {
            if (!z2) {
                sb.append('&');
            }
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (str2 == null) {
                str2 = "";
            }
            sb.append(l(str));
            sb.append('=');
            sb.append(l(str2));
            z2 = false;
        }
        return sb.toString();
    }

    public static String d(String str) {
        if (str == null) {
            return "";
        }
        if (str.length() <= 300) {
            return str;
        }
        return str.substring(0, 300) + "...";
    }

    public static void e(AsyncTask asyncTask, Object... objArr) {
        if (Build.VERSION.SDK_INT >= 11) {
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, objArr);
        } else {
            asyncTask.execute(objArr);
        }
    }

    public static String f(String str) {
        if (str == null) {
            return "";
        }
        String[] strArrSplit = str.split(";");
        for (int i2 = 0; i2 < strArrSplit.length; i2++) {
            String str2 = strArrSplit[i2];
            String strTrim = str2 != null ? str2.trim() : "";
            if (strTrim.startsWith("bili_jct=")) {
                return strTrim.substring(9);
            }
        }
        return "";
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.im.oldchat.bili.c.a g(java.lang.String r3, java.util.Map r4, java.lang.String r5) throws java.lang.Throwable {
        /*
            java.lang.String r4 = c(r4)
            if (r4 == 0) goto L28
            int r0 = r4.length()
            if (r0 <= 0) goto L28
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r3)
            java.lang.String r1 = "?"
            boolean r3 = r3.contains(r1)
            if (r3 == 0) goto L1e
            java.lang.String r1 = "&"
        L1e:
            r0.append(r1)
            r0.append(r4)
            java.lang.String r3 = r0.toString()
        L28:
            r4 = 0
            java.net.URL r0 = new java.net.URL     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L66
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L66
            java.net.URLConnection r3 = r0.openConnection()     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L66
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L66
            r4 = 15000(0x3a98, float:2.102E-41)
            r3.setConnectTimeout(r4)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            r3.setReadTimeout(r4)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            r4 = 0
            r3.setUseCaches(r4)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            java.lang.String r4 = "GET"
            r3.setRequestMethod(r4)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            r4 = 1
            r3.setDoInput(r4)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            a(r3, r5)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            int r4 = r3.getResponseCode()     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            java.lang.String r5 = k(r3, r4)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            com.im.oldchat.bili.c$a r0 = new com.im.oldchat.bili.c$a     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            r0.<init>(r4, r5)     // Catch: java.lang.Throwable -> L5d java.lang.Exception -> L5f
            r3.disconnect()
            return r0
        L5d:
            r4 = move-exception
            goto L7a
        L5f:
            r4 = move-exception
            goto L6a
        L61:
            r3 = move-exception
            r2 = r4
            r4 = r3
            r3 = r2
            goto L7a
        L66:
            r3 = move-exception
            r2 = r4
            r4 = r3
            r3 = r2
        L6a:
            com.im.oldchat.bili.c$a r5 = new com.im.oldchat.bili.c$a     // Catch: java.lang.Throwable -> L5d
            java.lang.String r4 = r4.getMessage()     // Catch: java.lang.Throwable -> L5d
            r0 = -1
            r5.<init>(r0, r4)     // Catch: java.lang.Throwable -> L5d
            if (r3 == 0) goto L79
            r3.disconnect()
        L79:
            return r5
        L7a:
            if (r3 == 0) goto L7f
            r3.disconnect()
        L7f:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.bili.c.g(java.lang.String, java.util.Map, java.lang.String):com.im.oldchat.bili.c$a");
    }

    public static void h(String str, Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append("【bilibili 测试报错！！！！！】 ");
        if (str == null) {
            str = "";
        }
        sb.append(str);
        String string = sb.toString();
        if (th != null) {
            Log.e("BiliApi", string, th);
        } else {
            Log.e("BiliApi", string);
        }
    }

    public static String i(String str) {
        if (str == null) {
            return null;
        }
        if (!str.startsWith("//")) {
            return str.startsWith("http://") ? str.replace("http://", "https://") : str;
        }
        return "https:" + str;
    }

    public static a j(String str, Map map, String str2) throws Throwable {
        String strC = c(map);
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection2.setConnectTimeout(15000);
                    httpURLConnection2.setReadTimeout(15000);
                    httpURLConnection2.setUseCaches(false);
                    httpURLConnection2.setRequestMethod("POST");
                    httpURLConnection2.setDoInput(true);
                    httpURLConnection2.setDoOutput(true);
                    httpURLConnection2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                    a(httpURLConnection2, str2);
                    if (strC != null && strC.length() > 0) {
                        OutputStream outputStream = httpURLConnection2.getOutputStream();
                        outputStream.write(strC.getBytes("UTF-8"));
                        outputStream.flush();
                        outputStream.close();
                    }
                    int responseCode = httpURLConnection2.getResponseCode();
                    a aVar = new a(responseCode, k(httpURLConnection2, responseCode));
                    httpURLConnection2.disconnect();
                    return aVar;
                } catch (Exception e2) {
                    e = e2;
                    httpURLConnection = httpURLConnection2;
                    a aVar2 = new a(-1, e.getMessage());
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return aVar2;
                } catch (Throwable th) {
                    th = th;
                    httpURLConnection = httpURLConnection2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String k(HttpURLConnection httpURLConnection, int i2) {
        InputStream errorStream = null;
        try {
            errorStream = (i2 < 200 || i2 >= 400) ? httpURLConnection.getErrorStream() : httpURLConnection.getInputStream();
            if (errorStream == null) {
                if (errorStream != null) {
                    try {
                        errorStream.close();
                    } catch (Exception unused) {
                    }
                }
                return "";
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
            bufferedReader.close();
            String string = sb.toString();
            try {
                errorStream.close();
            } catch (Exception unused2) {
            }
            return string;
        } catch (Exception unused3) {
            if (errorStream != null) {
                try {
                    errorStream.close();
                } catch (Exception unused4) {
                }
            }
            return "";
        } catch (Throwable th) {
            if (errorStream != null) {
                try {
                    errorStream.close();
                } catch (Exception unused5) {
                }
            }
            throw th;
        }
    }

    public static String l(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception unused) {
            return str;
        }
    }
}
