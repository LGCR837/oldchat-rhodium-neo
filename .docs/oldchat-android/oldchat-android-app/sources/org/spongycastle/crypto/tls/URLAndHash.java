package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.util.Strings;

/* JADX INFO: loaded from: classes.dex */
public class URLAndHash {
    protected byte[] sha1Hash;
    protected String url;

    public URLAndHash(String str, byte[] bArr) {
        if (str == null || str.length() < 1 || str.length() >= 65536) {
            throw new IllegalArgumentException("'url' must have length from 1 to (2^16 - 1)");
        }
        if (bArr != null && bArr.length != 20) {
            throw new IllegalArgumentException("'sha1Hash' must have length == 20, if present");
        }
        this.url = str;
        this.sha1Hash = bArr;
    }

    public static URLAndHash parse(TlsContext tlsContext, InputStream inputStream) throws IOException {
        byte[] fully;
        byte[] opaque16 = TlsUtils.readOpaque16(inputStream);
        if (opaque16.length < 1) {
            throw new TlsFatalAlert((short) 47);
        }
        String strFromByteArray = Strings.fromByteArray(opaque16);
        short uint8 = TlsUtils.readUint8(inputStream);
        if (uint8 != 0) {
            if (uint8 != 1) {
                throw new TlsFatalAlert((short) 47);
            }
            fully = TlsUtils.readFully(20, inputStream);
        } else {
            if (TlsUtils.isTLSv12(tlsContext)) {
                throw new TlsFatalAlert((short) 47);
            }
            fully = null;
        }
        return new URLAndHash(strFromByteArray, fully);
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsUtils.writeOpaque16(Strings.toByteArray(this.url), outputStream);
        if (this.sha1Hash == null) {
            TlsUtils.writeUint8(0, outputStream);
        } else {
            TlsUtils.writeUint8(1, outputStream);
            outputStream.write(this.sha1Hash);
        }
    }

    public byte[] getSHA1Hash() {
        return this.sha1Hash;
    }

    public String getURL() {
        return this.url;
    }
}
