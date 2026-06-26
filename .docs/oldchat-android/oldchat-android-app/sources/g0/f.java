package g0;

import g0.d;
import g0.e;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract class f extends e {
    public static e.c A(String str, d.k kVar, String str2, String str3, String str4, String str5, String str6, d.j jVar) throws Throwable {
        String str7 = "----OldChatBoundary" + System.currentTimeMillis();
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(e.f5335a + str).openConnection();
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(120000);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + str7);
        if (str4 != null && str4.length() > 0) {
            httpURLConnection.setRequestProperty("Authorization", "Bearer " + str4);
        }
        httpURLConnection.setChunkedStreamingMode(0);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        if (str5 != null && str5.length() > 0 && str6 != null) {
            K(outputStream, str7, str5, str6);
        }
        L(outputStream, str7, "file", kVar, str2, str3, jVar);
        outputStream.write(("--" + str7 + "--\r\n").getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
        int responseCode = httpURLConnection.getResponseCode();
        return new e.c(responseCode, e.p(httpURLConnection, responseCode));
    }

    public static e.c B(String str, d.k kVar, String str2, String str3, byte[] bArr, String str4, String str5, String str6, d.j jVar) throws Throwable {
        String str7 = "----OldChatBoundary" + System.currentTimeMillis();
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(e.f5335a + str).openConnection();
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(120000);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + str7);
        if (str6 != null && str6.length() > 0) {
            httpURLConnection.setRequestProperty("Authorization", "Bearer " + str6);
        }
        httpURLConnection.setChunkedStreamingMode(0);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        L(outputStream, str7, "file", kVar, str2, str3, jVar);
        if (bArr != null && bArr.length > 0 && str4 != null && str5 != null) {
            J(outputStream, str7, "thumb", bArr, str4, str5);
        }
        outputStream.write(("--" + str7 + "--\r\n").getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
        int responseCode = httpURLConnection.getResponseCode();
        return new e.c(responseCode, e.p(httpURLConnection, responseCode));
    }

    public static e.c C(String str, byte[] bArr, String str2, String str3, byte[] bArr2, String str4, String str5, String str6, d.j jVar) {
        HttpURLConnection httpURLConnection;
        String str7;
        String str8 = "----OldChatBoundary" + System.currentTimeMillis();
        HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(e.f5335a + str).openConnection();
        httpURLConnection2.setConnectTimeout(8000);
        httpURLConnection2.setReadTimeout(15000);
        httpURLConnection2.setRequestMethod("POST");
        httpURLConnection2.setDoOutput(true);
        httpURLConnection2.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + str8);
        if (str6 != null && str6.length() > 0) {
            httpURLConnection2.setRequestProperty("Authorization", "Bearer " + str6);
        }
        long length = bArr.length;
        if (bArr2 != null) {
            length += (long) bArr2.length;
        }
        long j2 = length + 500;
        OutputStream outputStream = httpURLConnection2.getOutputStream();
        long jM = M(outputStream, str8, "file", bArr, str2, str3, 0L, j2, jVar);
        if (bArr2 == null || str4 == null || str5 == null) {
            httpURLConnection = httpURLConnection2;
            str7 = str8;
        } else {
            httpURLConnection = httpURLConnection2;
            str7 = str8;
            jM += M(outputStream, str8, "thumb", bArr2, str4, str5, jM, j2, jVar);
        }
        byte[] bytes = ("--" + str7 + "--\r\n").getBytes("UTF-8");
        outputStream.write(bytes);
        long length2 = jM + ((long) bytes.length);
        if (jVar != null) {
            jVar.a(length2, j2);
        }
        outputStream.flush();
        outputStream.close();
        int responseCode = httpURLConnection.getResponseCode();
        return new e.c(responseCode, e.p(httpURLConnection, responseCode));
    }

    public static e.c D(String str, d.k kVar, String str2, String str3, String str4, String str5, String str6, d.j jVar) throws Throwable {
        String strE;
        e.c cVar = new e.c(-1, "network_error");
        for (int i2 = 0; i2 <= 1; i2++) {
            try {
                e.c cVarA = A(str, kVar, str2, str3, str4, str5, str6, jVar);
                if (cVarA.f5348a == 401 && e.s(str, str4) && (strE = a.e()) != null) {
                    return e.b(A(str, kVar, str2, str3, strE, str5, str6, jVar), str, strE);
                }
                cVar = e.b(cVarA, str, str4);
            } catch (Exception e2) {
                cVar = new e.c(-1, F(e2));
            }
            if (!H(cVar.f5348a) || i2 >= 1) {
                break;
            }
            I(i2);
        }
        return cVar;
    }

    public static e.c E(String str, d.k kVar, String str2, String str3, byte[] bArr, String str4, String str5, String str6, d.j jVar) throws Throwable {
        String strE;
        e.c cVar = new e.c(-1, "network_error");
        for (int i2 = 0; i2 <= 1; i2++) {
            try {
                e.c cVarB = B(str, kVar, str2, str3, bArr, str4, str5, str6, jVar);
                if (cVarB.f5348a == 401 && e.s(str, str6) && (strE = a.e()) != null) {
                    return e.b(B(str, kVar, str2, str3, bArr, str4, str5, strE, jVar), str, strE);
                }
                cVar = e.b(cVarB, str, str6);
            } catch (Exception e2) {
                cVar = new e.c(-1, F(e2));
            }
            if (!H(cVar.f5348a) || i2 >= 1) {
                break;
            }
            I(i2);
        }
        return cVar;
    }

    public static String F(Throwable th) {
        if (th == null) {
            return "network_error";
        }
        String message = th.getMessage();
        return (message == null || message.length() == 0) ? th.getClass().getSimpleName() : message;
    }

    public static String G(String str) {
        String strTrim = str == null ? "" : str.trim();
        if (strTrim.length() == 0) {
            return "file";
        }
        String strReplace = strTrim.replace('\r', '_').replace('\n', '_').replace('\"', '_').replace('\\', '_');
        if (strReplace.length() > 120) {
            strReplace = strReplace.substring(strReplace.length() - 120);
        }
        return strReplace.length() == 0 ? "file" : strReplace;
    }

    public static boolean H(int i2) {
        return i2 <= 0 || i2 == 408 || i2 == 503 || i2 == 504 || i2 == 500 || i2 == 502 || i2 == 429;
    }

    public static void I(int i2) {
        long j2 = (1 << i2) * 600;
        if (j2 > 1200) {
            j2 = 1200;
        }
        try {
            Thread.sleep(j2);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    public static void J(OutputStream outputStream, String str, String str2, byte[] bArr, String str3, String str4) throws IOException {
        outputStream.write(("--" + str + "\r\nContent-Disposition: form-data; name=\"" + str2 + "\"; filename=\"" + G(str3) + "\"\r\nContent-Type: " + str4 + "\r\n\r\n").getBytes("UTF-8"));
        outputStream.write(bArr);
        outputStream.write("\r\n".getBytes("UTF-8"));
    }

    public static void K(OutputStream outputStream, String str, String str2, String str3) throws IOException {
        if (str2 == null) {
            str2 = "";
        }
        if (str3 == null) {
            str3 = "";
        }
        outputStream.write(("--" + str + "\r\nContent-Disposition: form-data; name=\"" + str2 + "\"\r\n\r\n" + str3 + "\r\n").getBytes("UTF-8"));
    }

    public static void L(OutputStream outputStream, String str, String str2, d.k kVar, String str3, String str4, d.j jVar) throws Throwable {
        InputStream inputStreamA;
        if (str4 == null || str4.length() == 0) {
            str4 = "application/octet-stream";
        }
        outputStream.write(("--" + str + "\r\nContent-Disposition: form-data; name=\"" + str2 + "\"; filename=\"" + G(str3) + "\"\r\nContent-Type: " + str4 + "\r\n\r\n").getBytes("UTF-8"));
        try {
            inputStreamA = kVar.a();
            try {
                if (inputStreamA == null) {
                    throw new IOException("cannot_open_stream");
                }
                long length = kVar.length();
                byte[] bArr = new byte[PKIFailureInfo.certRevoked];
                long j2 = 0;
                while (true) {
                    int i2 = inputStreamA.read(bArr);
                    if (i2 != -1) {
                        outputStream.write(bArr, 0, i2);
                        j2 += (long) i2;
                        if (jVar != null && length > 0) {
                            jVar.a(j2, length);
                        }
                    } else {
                        try {
                            break;
                        } catch (Exception unused) {
                        }
                    }
                }
                inputStreamA.close();
                outputStream.write("\r\n".getBytes("UTF-8"));
            } catch (Throwable th) {
                th = th;
                if (inputStreamA != null) {
                    try {
                        inputStreamA.close();
                    } catch (Exception unused2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStreamA = null;
        }
    }

    public static long M(OutputStream outputStream, String str, String str2, byte[] bArr, String str3, String str4, long j2, long j3, d.j jVar) throws IOException {
        byte[] bytes = ("--" + str + "\r\nContent-Disposition: form-data; name=\"" + str2 + "\"; filename=\"" + G(str3) + "\"\r\nContent-Type: " + str4 + "\r\n\r\n").getBytes("UTF-8");
        outputStream.write(bytes);
        long length = (long) bytes.length;
        int i2 = 0;
        while (i2 < bArr.length) {
            int iMin = Math.min(PKIFailureInfo.certRevoked, bArr.length - i2);
            outputStream.write(bArr, i2, iMin);
            i2 += iMin;
            length += (long) iMin;
            if (jVar != null) {
                jVar.a(j2 + length, j3);
            }
        }
        byte[] bytes2 = "\r\n".getBytes("UTF-8");
        outputStream.write(bytes2);
        return length + ((long) bytes2.length);
    }

    public static e.c z(String str, byte[] bArr, String str2, String str3, byte[] bArr2, String str4, String str5, String str6) {
        String str7 = "----OldChatBoundary" + System.currentTimeMillis();
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(e.f5335a + str).openConnection();
        httpURLConnection.setConnectTimeout(8000);
        httpURLConnection.setReadTimeout(15000);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + str7);
        if (str6 != null && str6.length() > 0) {
            httpURLConnection.setRequestProperty("Authorization", "Bearer " + str6);
        }
        OutputStream outputStream = httpURLConnection.getOutputStream();
        J(outputStream, str7, "file", bArr, str2, str3);
        if (bArr2 != null && str4 != null && str5 != null) {
            J(outputStream, str7, "thumb", bArr2, str4, str5);
        }
        outputStream.write(("--" + str7 + "--\r\n").getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
        int responseCode = httpURLConnection.getResponseCode();
        return new e.c(responseCode, e.p(httpURLConnection, responseCode));
    }
}
