package com.im.oldchat.ui;

import android.app.Activity;
import android.net.Uri;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: renamed from: com.im.oldchat.ui.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0213d {

    /* JADX INFO: renamed from: com.im.oldchat.ui.d$a */
    public interface a {
        void a(int i2);
    }

    /* JADX INFO: renamed from: com.im.oldchat.ui.d$b */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f4479a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f4480b;

        public b(int i2, String str) {
            this.f4479a = i2;
            this.f4480b = str == null ? "" : str;
        }
    }

    public static String a(String str) {
        String strK = k(str);
        if (strK.endsWith("/files/upload")) {
            return strK;
        }
        if (strK.endsWith("/v1")) {
            return strK + "/files/upload";
        }
        return strK + "/v1/files/upload";
    }

    public static void b(BufferedReader bufferedReader) {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void c(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void d(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    public static String e(String str) {
        try {
            URI uri = new URI(str);
            String scheme = uri.getScheme();
            String rawAuthority = uri.getRawAuthority();
            if (scheme != null && rawAuthority != null && rawAuthority.length() != 0) {
                return scheme.toLowerCase() + "://" + rawAuthority;
            }
        } catch (Exception unused) {
        }
        return "";
    }

    public static String f(String str, String str2) {
        String strTrim = str == null ? "" : str.trim();
        if (strTrim.length() == 0) {
            return "";
        }
        if (strTrim.startsWith("http://") || strTrim.startsWith("https://")) {
            return strTrim;
        }
        String strE = e(str2);
        if (strE.length() == 0) {
            return strTrim;
        }
        if (strTrim.startsWith("/")) {
            return strE + strTrim;
        }
        if (strTrim.startsWith("v1/")) {
            return strE + "/" + strTrim;
        }
        if (strTrim.startsWith("files/")) {
            return strE + "/v1/" + strTrim;
        }
        try {
            return strE + "/v1/files/download?name=" + URLEncoder.encode(strTrim, "UTF-8");
        } catch (Exception unused) {
            return strE + "/v1/files/download?name=" + strTrim;
        }
    }

    public static String g(String str) {
        if (str == null) {
            return "";
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0) {
            return "";
        }
        if (strTrim.indexOf("://") < 0) {
            strTrim = "http://" + strTrim;
        }
        try {
            URI uri = new URI(strTrim);
            String scheme = uri.getScheme();
            String rawAuthority = uri.getRawAuthority();
            if (scheme != null && rawAuthority != null && rawAuthority.length() != 0) {
                String rawPath = uri.getRawPath();
                if (rawPath == null) {
                    rawPath = "";
                }
                while (rawPath.endsWith("/") && rawPath.length() > 0) {
                    rawPath = rawPath.substring(0, rawPath.length() - 1);
                }
                if (rawPath.endsWith("/v1")) {
                    rawPath = rawPath.substring(0, rawPath.length() - 3);
                }
                return scheme.toLowerCase() + "://" + rawAuthority + rawPath;
            }
        } catch (Exception unused) {
        }
        return "";
    }

    public static String h(HttpURLConnection httpURLConnection, int i2) throws Throwable {
        InputStream inputStream;
        InputStream errorStream;
        BufferedReader bufferedReader = null;
        try {
            errorStream = (i2 < 200 || i2 >= 400) ? httpURLConnection.getErrorStream() : httpURLConnection.getInputStream();
            if (errorStream != null) {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(errorStream, "UTF-8"));
                    try {
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            String line = bufferedReader2.readLine();
                            if (line == null) {
                                String string = sb.toString();
                                b(bufferedReader2);
                                c(errorStream);
                                return string;
                            }
                            sb.append(line);
                        }
                    } catch (Exception unused) {
                        bufferedReader = bufferedReader2;
                    } catch (Throwable th) {
                        bufferedReader = bufferedReader2;
                        inputStream = errorStream;
                        th = th;
                        b(bufferedReader);
                        c(inputStream);
                        throw th;
                    }
                } catch (Exception unused2) {
                } catch (Throwable th2) {
                    inputStream = errorStream;
                    th = th2;
                }
            }
        } catch (Exception unused3) {
            errorStream = null;
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
        }
        b(bufferedReader);
        c(errorStream);
        return "";
    }

    public static String i(Activity activity) {
        String string = "";
        if (activity != null) {
            try {
                string = activity.getSharedPreferences("settings", 0).getString("files_server_base_url", "");
            } catch (Exception unused) {
            }
        }
        String strG = g(string);
        if (strG.length() == 0) {
            strG = g("http://151.242.85.89:18098");
        }
        if (strG.endsWith("/v1")) {
            return strG;
        }
        return strG + "/v1";
    }

    public static String j(String str) {
        String strTrim = str == null ? "" : str.trim();
        if (strTrim.length() == 0) {
            strTrim = "file_" + System.currentTimeMillis();
        }
        return strTrim.replace('\"', '_').replace('\n', '_').replace('\r', '_');
    }

    public static String k(String str) {
        String strTrim = str == null ? "" : str.trim();
        while (strTrim.endsWith("/")) {
            strTrim = strTrim.substring(0, strTrim.length() - 1);
        }
        return strTrim;
    }

    public static b l(Activity activity, String str, Uri uri, String str2, String str3, long j2, a aVar) throws Throwable {
        HttpURLConnection httpURLConnection;
        OutputStream outputStream;
        OutputStream outputStream2;
        String str4;
        if (activity == null || uri == null) {
            return new b(-1, "invalid_input");
        }
        try {
            try {
                String str5 = "----OldChatFileBoundary" + System.currentTimeMillis();
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection.setConnectTimeout(20000);
                    httpURLConnection.setReadTimeout(1200000);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + str5);
                    if (j2 > 0) {
                        httpURLConnection.setRequestProperty("X-File-Size", String.valueOf(j2));
                    }
                    httpURLConnection.setChunkedStreamingMode(0);
                    outputStream2 = httpURLConnection.getOutputStream();
                    if (str3 != null) {
                        try {
                            str4 = str3.length() == 0 ? "application/octet-stream" : str3;
                        } catch (Exception e2) {
                            e = e2;
                            b bVar = new b(-1, e.getMessage());
                            c(null);
                            d(outputStream2);
                            if (httpURLConnection != null) {
                                try {
                                    httpURLConnection.disconnect();
                                } catch (Exception unused) {
                                }
                            }
                            return bVar;
                        }
                    }
                    outputStream2.write(("--" + str5 + "\r\nContent-Disposition: form-data; name=\"file\"; filename=\"" + j(str2) + "\"\r\nContent-Type: " + str4 + "\r\n\r\n").getBytes("UTF-8"));
                    InputStream inputStreamOpenInputStream = activity.getContentResolver().openInputStream(uri);
                    if (inputStreamOpenInputStream == null) {
                        b bVar2 = new b(-1, "cannot_open_stream");
                        c(inputStreamOpenInputStream);
                        d(outputStream2);
                        try {
                            httpURLConnection.disconnect();
                        } catch (Exception unused2) {
                        }
                        return bVar2;
                    }
                    byte[] bArr = new byte[PKIFailureInfo.certRevoked];
                    long j3 = 0;
                    long j4 = 0;
                    int i2 = -1;
                    while (true) {
                        int i3 = inputStreamOpenInputStream.read(bArr);
                        if (i3 == -1) {
                            break;
                        }
                        outputStream2.write(bArr, 0, i3);
                        j3 += (long) i3;
                        if (j2 > 0 && aVar != null) {
                            int i4 = (int) ((100 * j3) / j2);
                            long jCurrentTimeMillis = System.currentTimeMillis();
                            if (i4 != i2 || jCurrentTimeMillis - j4 >= 180 || i4 >= 100) {
                                aVar.a(i4);
                                i2 = i4;
                                j4 = jCurrentTimeMillis;
                            }
                        }
                    }
                    outputStream2.write(("\r\n--" + str5 + "--\r\n").getBytes("UTF-8"));
                    outputStream2.flush();
                    int responseCode = httpURLConnection.getResponseCode();
                    b bVar3 = new b(responseCode, h(httpURLConnection, responseCode));
                    c(inputStreamOpenInputStream);
                    d(outputStream2);
                    try {
                        httpURLConnection.disconnect();
                    } catch (Exception unused3) {
                    }
                    return bVar3;
                } catch (Exception e3) {
                    e = e3;
                    outputStream2 = null;
                } catch (Throwable th) {
                    th = th;
                    outputStream = null;
                    c(null);
                    d(outputStream);
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Exception unused4) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
                httpURLConnection = null;
                outputStream2 = null;
            } catch (Throwable th2) {
                th = th2;
                httpURLConnection = null;
                outputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
