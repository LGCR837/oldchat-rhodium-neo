package org.spongycastle.crypto;

/* JADX INFO: loaded from: classes.dex */
public interface StreamCipher {
    String getAlgorithmName();

    void init(boolean z2, CipherParameters cipherParameters);

    int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4);

    void reset();

    byte returnByte(byte b2);
}
