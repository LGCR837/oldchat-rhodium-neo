package org.spongycastle.crypto;

/* JADX INFO: loaded from: classes.dex */
public interface BlockCipher {
    String getAlgorithmName();

    int getBlockSize();

    void init(boolean z2, CipherParameters cipherParameters);

    int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3);

    void reset();
}
