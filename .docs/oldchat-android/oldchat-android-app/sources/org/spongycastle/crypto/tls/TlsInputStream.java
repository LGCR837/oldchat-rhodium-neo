package org.spongycastle.crypto.tls;

import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
class TlsInputStream extends InputStream {
    private byte[] buf = new byte[1];
    private TlsProtocol handler;

    public TlsInputStream(TlsProtocol tlsProtocol) {
        this.handler = tlsProtocol;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.handler.applicationDataAvailable();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws TlsFatalAlert {
        this.handler.close();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        return this.handler.readApplicationData(bArr, i2, i3);
    }

    @Override // java.io.InputStream
    public int read() {
        if (read(this.buf) < 0) {
            return -1;
        }
        return this.buf[0] & 255;
    }
}
