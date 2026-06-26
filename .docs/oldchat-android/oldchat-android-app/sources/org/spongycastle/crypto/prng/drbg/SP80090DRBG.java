package org.spongycastle.crypto.prng.drbg;

/* JADX INFO: loaded from: classes.dex */
public interface SP80090DRBG {
    int generate(byte[] bArr, byte[] bArr2, boolean z2);

    int getBlockSize();

    void reseed(byte[] bArr);
}
