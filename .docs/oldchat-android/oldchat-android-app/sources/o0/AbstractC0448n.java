package o0;

import android.content.Context;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import o0.AbstractC0446l;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: renamed from: o0.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0448n {
    public static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void b(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    public static long c(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[PKIFailureInfo.certRevoked];
        long j2 = 0;
        while (true) {
            int i2 = inputStream.read(bArr);
            if (i2 == -1) {
                outputStream.flush();
                return j2;
            }
            outputStream.write(bArr, 0, i2);
            j2 += (long) i2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:96:0x015a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static o0.AbstractC0446l.d d(java.lang.String r9, java.io.File r10, java.lang.String r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 370
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: o0.AbstractC0448n.d(java.lang.String, java.io.File, java.lang.String):o0.l$d");
    }

    public static AbstractC0446l.d e(String str, File file, String str2) throws Throwable {
        String[] strArrI = A.i(str);
        if (strArrI == null || strArrI.length == 0) {
            return d(str, file, str2);
        }
        String str3 = "保存失败";
        for (String str4 : strArrI) {
            if (str4 != null && str4.length() != 0) {
                AbstractC0446l.d dVarD = d(str4, file, str2);
                if (dVarD.f7123a) {
                    return dVarD;
                }
                String str5 = dVarD.f7124b;
                if (str5 != null && str5.length() > 0) {
                    str3 = dVarD.f7124b;
                }
            }
        }
        return h(str3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0136 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x014d A[ADDED_TO_REGION] */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.content.ContentResolver] */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r10v8, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r10v9, types: [java.net.HttpURLConnection, java.net.URLConnection] */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v16, types: [android.net.Uri] */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v18 */
    /* JADX WARN: Type inference failed for: r12v19 */
    /* JADX WARN: Type inference failed for: r12v20 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r12v7, types: [android.net.Uri] */
    /* JADX WARN: Type inference failed for: r12v8, types: [android.net.Uri] */
    /* JADX WARN: Type inference failed for: r13v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v11, types: [android.content.ContentResolver] */
    /* JADX WARN: Type inference failed for: r13v12, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r13v6, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r13v9 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r9v10, types: [android.content.ContentResolver] */
    /* JADX WARN: Type inference failed for: r9v11, types: [android.content.ContentResolver] */
    /* JADX WARN: Type inference failed for: r9v2, types: [android.content.ContentResolver] */
    /* JADX WARN: Type inference failed for: r9v4, types: [android.content.ContentResolver] */
    /* JADX WARN: Type inference failed for: r9v9, types: [android.content.ContentResolver] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static o0.AbstractC0446l.d f(android.content.Context r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.String r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 345
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: o0.AbstractC0448n.f(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String):o0.l$d");
    }

    public static AbstractC0446l.d g(Context context, String str, String str2, String str3, String str4) throws Throwable {
        String[] strArrI = A.i(str);
        if (strArrI == null || strArrI.length == 0) {
            return f(context, str, str2, str3, str4);
        }
        String str5 = "保存失败";
        for (String str6 : strArrI) {
            if (str6 != null && str6.length() != 0) {
                AbstractC0446l.d dVarF = f(context, str6, str2, str3, str4);
                if (dVarF.f7123a) {
                    return dVarF;
                }
                String str7 = dVarF.f7124b;
                if (str7 != null && str7.length() > 0) {
                    str5 = dVarF.f7124b;
                }
            }
        }
        return h(str5);
    }

    public static AbstractC0446l.d h(String str) {
        if (str == null || str.length() == 0) {
            str = "保存失败";
        }
        return new AbstractC0446l.d(false, str);
    }

    public static String i(Exception exc) {
        if (exc == null) {
            return "保存失败";
        }
        if (exc instanceof SocketTimeoutException) {
            return "下载超时，请重试";
        }
        if (exc instanceof UnknownHostException) {
            return "网络不可用，请检查连接";
        }
        if (exc instanceof FileNotFoundException) {
            return "资源不存在或无权限访问";
        }
        if (exc instanceof SSLException) {
            return "安全连接失败，请稍后重试";
        }
        String message = exc.getMessage();
        if (message != null) {
            String strTrim = message.trim();
            if (strTrim.length() > 0) {
                return strTrim;
            }
        }
        return "保存失败";
    }

    public static String j(Context context) {
        if (context == null) {
            return "";
        }
        try {
            String string = context.getSharedPreferences("auth", 0).getString("access_token", "");
            return string == null ? "" : string.trim();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String k(String str) {
        if (str == null) {
            return "application/octet-stream";
        }
        String lowerCase = str.toLowerCase();
        return (".jpg".equals(lowerCase) || ".jpeg".equals(lowerCase)) ? "image/jpeg" : ".png".equals(lowerCase) ? "image/png" : ".gif".equals(lowerCase) ? "image/gif" : (".mp4".equals(lowerCase) || ".m4v".equals(lowerCase)) ? "video/mp4" : (".3gp".equals(lowerCase) || ".3gpp".equals(lowerCase)) ? "video/3gpp" : ".zip".equals(lowerCase) ? "application/zip" : ".mp3".equals(lowerCase) ? "audio/mpeg" : "application/octet-stream";
    }

    public static HttpURLConnection l(String str, String str2) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setInstanceFollowRedirects(true);
        if (str2 != null && str2.length() > 0) {
            httpURLConnection.setRequestProperty("Authorization", "Bearer " + str2);
        }
        httpURLConnection.connect();
        return httpURLConnection;
    }

    public static AbstractC0446l.d m() {
        return new AbstractC0446l.d(true, "");
    }
}
