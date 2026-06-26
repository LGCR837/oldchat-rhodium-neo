package org.spongycastle.crypto.tls;

import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class HeartbeatExtension {
    protected short mode;

    public HeartbeatExtension(short s2) {
        if (!HeartbeatMode.isValid(s2)) {
            throw new IllegalArgumentException("'mode' is not a valid HeartbeatMode value");
        }
        this.mode = s2;
    }

    public static HeartbeatExtension parse(InputStream inputStream) {
        short uint8 = TlsUtils.readUint8(inputStream);
        if (HeartbeatMode.isValid(uint8)) {
            return new HeartbeatExtension(uint8);
        }
        throw new TlsFatalAlert((short) 47);
    }

    public void encode(OutputStream outputStream) {
        TlsUtils.writeUint8(this.mode, outputStream);
    }

    public short getMode() {
        return this.mode;
    }
}
