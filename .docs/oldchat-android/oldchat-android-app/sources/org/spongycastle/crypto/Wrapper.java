package org.spongycastle.crypto;

/* JADX INFO: loaded from: classes.dex */
public interface Wrapper {
    String getAlgorithmName();

    void init(boolean z2, CipherParameters cipherParameters);

    byte[] unwrap(byte[] bArr, int i2, int i3);

    byte[] wrap(byte[] bArr, int i2, int i3);
}
