package org.spongycastle.crypto;

/* JADX INFO: loaded from: classes.dex */
public interface AsymmetricBlockCipher {
    int getInputBlockSize();

    int getOutputBlockSize();

    void init(boolean z2, CipherParameters cipherParameters);

    byte[] processBlock(byte[] bArr, int i2, int i3);
}
