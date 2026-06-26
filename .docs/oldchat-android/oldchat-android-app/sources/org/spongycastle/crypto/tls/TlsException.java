package org.spongycastle.crypto.tls;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class TlsException extends IOException {
    protected Throwable cause;

    public TlsException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
