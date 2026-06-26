package org.spongycastle.asn1;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class ASN1Exception extends IOException {
    private Throwable cause;

    public ASN1Exception(String str) {
        super(str);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public ASN1Exception(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
