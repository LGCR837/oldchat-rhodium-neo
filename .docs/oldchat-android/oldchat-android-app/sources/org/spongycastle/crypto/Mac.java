package org.spongycastle.crypto;

/* JADX INFO: loaded from: classes.dex */
public interface Mac {
    int doFinal(byte[] bArr, int i2);

    String getAlgorithmName();

    int getMacSize();

    void init(CipherParameters cipherParameters);

    void reset();

    void update(byte b2);

    void update(byte[] bArr, int i2, int i3);
}
