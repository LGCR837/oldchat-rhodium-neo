package org.spongycastle.crypto;

/* JADX INFO: loaded from: classes.dex */
public interface DerivationFunction {
    int generateBytes(byte[] bArr, int i2, int i3);

    void init(DerivationParameters derivationParameters);
}
