package org.spongycastle.crypto;

/* JADX INFO: loaded from: classes.dex */
public interface KeyEncapsulation {
    CipherParameters decrypt(byte[] bArr, int i2, int i3, int i4);

    CipherParameters encrypt(byte[] bArr, int i2, int i3);

    void init(CipherParameters cipherParameters);
}
