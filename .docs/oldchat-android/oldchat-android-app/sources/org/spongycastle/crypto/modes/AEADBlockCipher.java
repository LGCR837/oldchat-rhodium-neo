package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;

/* JADX INFO: loaded from: classes.dex */
public interface AEADBlockCipher {
    int doFinal(byte[] bArr, int i2);

    String getAlgorithmName();

    byte[] getMac();

    int getOutputSize(int i2);

    BlockCipher getUnderlyingCipher();

    int getUpdateOutputSize(int i2);

    void init(boolean z2, CipherParameters cipherParameters);

    void processAADByte(byte b2);

    void processAADBytes(byte[] bArr, int i2, int i3);

    int processByte(byte b2, byte[] bArr, int i2);

    int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4);

    void reset();
}
