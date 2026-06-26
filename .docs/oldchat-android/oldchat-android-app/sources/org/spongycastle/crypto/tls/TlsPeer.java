package org.spongycastle.crypto.tls;

/* JADX INFO: loaded from: classes.dex */
public interface TlsPeer {
    TlsCipher getCipher();

    TlsCompression getCompression();

    void notifyAlertRaised(short s2, short s3, String str, Throwable th);

    void notifyAlertReceived(short s2, short s3);

    void notifyHandshakeComplete();

    void notifySecureRenegotiation(boolean z2);

    boolean shouldUseGMTUnixTime();
}
