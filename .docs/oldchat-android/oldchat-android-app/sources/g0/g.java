package g0;

import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.SSLSocketFactory;
import org.spongycastle.asn1.eac.CertificateBody;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final URI f5350a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f5351b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Socket f5352c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public InputStream f5353d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public OutputStream f5354e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Thread f5355f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public volatile boolean f5356g;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.l();
        }
    }

    public interface b {
        void a(Exception exc);

        void b();

        void c(int i2, String str);

        void d(String str);
    }

    public g(URI uri, b bVar) {
        this.f5350a = uri;
        this.f5351b = bVar;
    }

    public void b() {
        this.f5356g = false;
        try {
            m(8, new byte[0]);
        } catch (Exception unused) {
        }
        c();
    }

    public final void c() {
        try {
            InputStream inputStream = this.f5353d;
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception unused) {
        }
        try {
            OutputStream outputStream = this.f5354e;
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (Exception unused2) {
        }
        try {
            Socket socket = this.f5352c;
            if (socket != null) {
                socket.close();
            }
        } catch (Exception unused3) {
        }
        this.f5353d = null;
        this.f5354e = null;
        this.f5352c = null;
    }

    public final String d(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA1);
        messageDigest.update((str + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes("UTF-8"));
        return Base64.encodeToString(messageDigest.digest(), 2);
    }

    public synchronized void e() {
        try {
            Thread thread = this.f5355f;
            if (thread != null) {
                if (thread.isAlive()) {
                    return;
                } else {
                    this.f5355f = null;
                }
            }
            Thread thread2 = new Thread(new a(), "ws-reader");
            this.f5355f = thread2;
            thread2.start();
        } catch (Throwable th) {
            throw th;
        }
    }

    public final String f(String str, String str2) {
        for (String str3 : str.split("\r\n")) {
            int iIndexOf = str3.indexOf(58);
            if (iIndexOf > 0 && str3.substring(0, iIndexOf).trim().equalsIgnoreCase(str2)) {
                return str3.substring(iIndexOf + 1).trim();
            }
        }
        return null;
    }

    public final void g() throws Exception {
        String rawPath = this.f5350a.getRawPath();
        if (rawPath == null || rawPath.isEmpty()) {
            rawPath = "/";
        }
        if (this.f5350a.getRawQuery() != null && !this.f5350a.getRawQuery().isEmpty()) {
            rawPath = rawPath + "?" + this.f5350a.getRawQuery();
        }
        String host = this.f5350a.getHost();
        int port = this.f5350a.getPort();
        if (port == -1) {
            port = "wss".equalsIgnoreCase(this.f5350a.getScheme()) ? 443 : 80;
        }
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        String strEncodeToString = Base64.encodeToString(bArr, 2);
        this.f5354e.write(("GET " + rawPath + " HTTP/1.1\r\nHost: " + host + ":" + port + "\r\nUpgrade: websocket\r\nConnection: Upgrade\r\nSec-WebSocket-Key: " + strEncodeToString + "\r\nSec-WebSocket-Version: 13\r\n\r\n").getBytes("UTF-8"));
        this.f5354e.flush();
        String str = new String(k(this.f5353d), "UTF-8");
        if (!str.startsWith("HTTP/1.1 101")) {
            throw new Exception("handshake failed: " + str.split("\r\n")[0]);
        }
        String strF = f(str, "Sec-WebSocket-Accept");
        String strD = d(strEncodeToString);
        if (strF == null || !strF.trim().equals(strD)) {
            throw new Exception("bad accept");
        }
    }

    public final void h() {
        String scheme = this.f5350a.getScheme();
        String host = this.f5350a.getHost();
        int port = this.f5350a.getPort();
        if (port == -1) {
            port = "wss".equalsIgnoreCase(scheme) ? 443 : 80;
        }
        if ("wss".equalsIgnoreCase(scheme)) {
            this.f5352c = SSLSocketFactory.getDefault().createSocket(host, port);
        } else {
            this.f5352c = new Socket(host, port);
        }
        this.f5353d = this.f5352c.getInputStream();
        this.f5354e = this.f5352c.getOutputStream();
    }

    public final void i() throws Exception {
        int i2;
        int i3;
        byte[] bArr;
        while (this.f5356g && (i2 = this.f5353d.read()) != -1 && (i3 = this.f5353d.read()) != -1) {
            int i4 = i2 & 15;
            boolean z2 = (i3 & 128) != 0;
            long j2 = i3 & CertificateBody.profileType;
            if (j2 == 126) {
                j2 = ((this.f5353d.read() & 255) << 8) | (this.f5353d.read() & 255);
            } else if (j2 == 127) {
                j2 = 0;
                for (int i5 = 0; i5 < 8; i5++) {
                    j2 = (j2 << 8) | ((long) (this.f5353d.read() & 255));
                }
            }
            if (z2) {
                bArr = new byte[4];
                j(bArr, 4);
            } else {
                bArr = null;
            }
            int i6 = (int) j2;
            byte[] bArr2 = new byte[i6];
            j(bArr2, i6);
            if (z2 && bArr != null) {
                for (int i7 = 0; i7 < i6; i7++) {
                    bArr2[i7] = (byte) (bArr2[i7] ^ bArr[i7 % 4]);
                }
            }
            if (i4 == 1) {
                String str = new String(bArr2, "UTF-8");
                b bVar = this.f5351b;
                if (bVar != null) {
                    bVar.d(str);
                }
            } else if (i4 == 8) {
                m(8, new byte[0]);
                return;
            } else if (i4 == 9) {
                m(10, bArr2);
            }
        }
    }

    public final void j(byte[] bArr, int i2) throws Exception {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = this.f5353d.read(bArr, i3, i2 - i3);
            if (i4 == -1) {
                throw new Exception("eof");
            }
            i3 += i4;
        }
    }

    public final byte[] k(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4];
        int i2 = 0;
        while (true) {
            int i3 = inputStream.read();
            if (i3 == -1) {
                throw new Exception("eof");
            }
            byteArrayOutputStream.write(i3);
            bArr[i2 % 4] = (byte) i3;
            int i4 = i2 + 1;
            if (i4 >= 4 && bArr[(i2 - 3) % 4] == 13 && bArr[(i2 - 2) % 4] == 10 && bArr[(i2 - 1) % 4] == 13 && bArr[i2 % 4] == 10) {
                return byteArrayOutputStream.toByteArray();
            }
            i2 = i4;
        }
    }

    public final void l() {
        b bVar;
        try {
            try {
                h();
                g();
                this.f5356g = true;
                b bVar2 = this.f5351b;
                if (bVar2 != null) {
                    bVar2.b();
                }
                i();
                this.f5356g = false;
                c();
                synchronized (this) {
                    this.f5355f = null;
                }
                bVar = this.f5351b;
                if (bVar == null) {
                    return;
                }
            } catch (Exception e2) {
                b bVar3 = this.f5351b;
                if (bVar3 != null) {
                    bVar3.a(e2);
                }
                this.f5356g = false;
                c();
                synchronized (this) {
                    this.f5355f = null;
                    bVar = this.f5351b;
                    if (bVar == null) {
                        return;
                    }
                }
            }
            bVar.c(0, "");
        } catch (Throwable th) {
            this.f5356g = false;
            c();
            synchronized (this) {
                this.f5355f = null;
                b bVar4 = this.f5351b;
                if (bVar4 != null) {
                    bVar4.c(0, "");
                }
                throw th;
            }
        }
    }

    public final synchronized void m(int i2, byte[] bArr) {
        try {
            if (this.f5354e == null) {
                return;
            }
            int length = bArr.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write((i2 & 15) | 128);
            if (length <= 125) {
                byteArrayOutputStream.write(length | 128);
            } else if (length <= 65535) {
                byteArrayOutputStream.write(254);
                byteArrayOutputStream.write((length >> 8) & 255);
                byteArrayOutputStream.write(length & 255);
            } else {
                byteArrayOutputStream.write(255);
                long j2 = length;
                for (int i3 = 7; i3 >= 0; i3--) {
                    byteArrayOutputStream.write((int) ((j2 >> (i3 * 8)) & 255));
                }
            }
            byte[] bArr2 = new byte[4];
            new SecureRandom().nextBytes(bArr2);
            byteArrayOutputStream.write(bArr2);
            byte[] bArr3 = new byte[bArr.length];
            for (int i4 = 0; i4 < bArr.length; i4++) {
                bArr3[i4] = (byte) (bArr[i4] ^ bArr2[i4 % 4]);
            }
            byteArrayOutputStream.write(bArr3);
            this.f5354e.write(byteArrayOutputStream.toByteArray());
            this.f5354e.flush();
        } catch (Throwable th) {
            throw th;
        }
    }
}
