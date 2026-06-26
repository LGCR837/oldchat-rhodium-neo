package org.spongycastle.crypto.tls;

/* JADX INFO: loaded from: classes.dex */
public interface TlsPSKIdentityManager {
    byte[] getHint();

    byte[] getPSK(byte[] bArr);
}
