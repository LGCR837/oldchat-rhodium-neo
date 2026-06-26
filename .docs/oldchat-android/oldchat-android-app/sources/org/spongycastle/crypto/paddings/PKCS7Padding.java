package org.spongycastle.crypto.paddings;

import java.security.SecureRandom;
import org.spongycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class PKCS7Padding implements BlockCipherPadding {
    @Override // org.spongycastle.crypto.paddings.BlockCipherPadding
    public int addPadding(byte[] bArr, int i2) {
        byte length = (byte) (bArr.length - i2);
        while (i2 < bArr.length) {
            bArr[i2] = length;
            i2++;
        }
        return length;
    }

    @Override // org.spongycastle.crypto.paddings.BlockCipherPadding
    public String getPaddingName() {
        return "PKCS7";
    }

    @Override // org.spongycastle.crypto.paddings.BlockCipherPadding
    public void init(SecureRandom secureRandom) {
    }

    @Override // org.spongycastle.crypto.paddings.BlockCipherPadding
    public int padCount(byte[] bArr) throws InvalidCipherTextException {
        int i2 = bArr[bArr.length - 1] & 255;
        byte b2 = (byte) i2;
        boolean z2 = (i2 > bArr.length) | (i2 == 0);
        for (int i3 = 0; i3 < bArr.length; i3++) {
            z2 |= (bArr.length - i3 <= i2) & (bArr[i3] != b2);
        }
        if (z2) {
            throw new InvalidCipherTextException("pad block corrupted");
        }
        return i2;
    }
}
