package org.spongycastle.crypto.paddings;

import java.security.SecureRandom;
import org.spongycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class ISO10126d2Padding implements BlockCipherPadding {
    SecureRandom random;

    @Override // org.spongycastle.crypto.paddings.BlockCipherPadding
    public int addPadding(byte[] bArr, int i2) {
        byte length = (byte) (bArr.length - i2);
        while (i2 < bArr.length - 1) {
            bArr[i2] = (byte) this.random.nextInt();
            i2++;
        }
        bArr[i2] = length;
        return length;
    }

    @Override // org.spongycastle.crypto.paddings.BlockCipherPadding
    public String getPaddingName() {
        return "ISO10126-2";
    }

    @Override // org.spongycastle.crypto.paddings.BlockCipherPadding
    public void init(SecureRandom secureRandom) {
        if (secureRandom != null) {
            this.random = secureRandom;
        } else {
            this.random = new SecureRandom();
        }
    }

    @Override // org.spongycastle.crypto.paddings.BlockCipherPadding
    public int padCount(byte[] bArr) throws InvalidCipherTextException {
        int i2 = bArr[bArr.length - 1] & 255;
        if (i2 <= bArr.length) {
            return i2;
        }
        throw new InvalidCipherTextException("pad block corrupted");
    }
}
