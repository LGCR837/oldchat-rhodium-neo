package org.spongycastle.crypto.tls;

/* JADX INFO: loaded from: classes.dex */
public class TlsFatalAlert extends TlsException {
    protected short alertDescription;

    public TlsFatalAlert(short s2) {
        this(s2, null);
    }

    public short getAlertDescription() {
        return this.alertDescription;
    }

    public TlsFatalAlert(short s2, Throwable th) {
        super(AlertDescription.getText(s2), th);
        this.alertDescription = s2;
    }
}
