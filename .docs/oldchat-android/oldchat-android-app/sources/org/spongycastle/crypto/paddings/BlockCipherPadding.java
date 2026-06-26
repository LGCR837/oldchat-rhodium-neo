package org.spongycastle.crypto.paddings;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public interface BlockCipherPadding {
    int addPadding(byte[] bArr, int i2);

    String getPaddingName();

    void init(SecureRandom secureRandom);

    int padCount(byte[] bArr);
}
