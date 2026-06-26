package org.spongycastle.crypto.params;

/* JADX INFO: loaded from: classes.dex */
public class DESedeParameters extends DESParameters {
    public static final int DES_EDE_KEY_LENGTH = 24;

    public DESedeParameters(byte[] bArr) {
        super(bArr);
        if (isWeakKey(bArr, 0, bArr.length)) {
            throw new IllegalArgumentException("attempt to create weak DESede key");
        }
    }

    public static boolean isReal2Key(byte[] bArr, int i2) {
        boolean z2 = false;
        for (int i3 = i2; i3 != i2 + 8; i3++) {
            if (bArr[i3] != bArr[i3 + 8]) {
                z2 = true;
            }
        }
        return z2;
    }

    public static boolean isReal3Key(byte[] bArr, int i2) {
        int i3 = i2;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (true) {
            boolean z5 = true;
            if (i3 == i2 + 8) {
                break;
            }
            byte b2 = bArr[i3];
            byte b3 = bArr[i3 + 8];
            z2 |= b2 != b3;
            byte b4 = bArr[i3 + 16];
            z3 |= b2 != b4;
            if (b3 == b4) {
                z5 = false;
            }
            z4 |= z5;
            i3++;
        }
        return z2 && z3 && z4;
    }

    public static boolean isRealEDEKey(byte[] bArr, int i2) {
        return bArr.length == 16 ? isReal2Key(bArr, i2) : isReal3Key(bArr, i2);
    }

    public static boolean isWeakKey(byte[] bArr, int i2, int i3) {
        while (i2 < i3) {
            if (DESParameters.isWeakKey(bArr, i2)) {
                return true;
            }
            i2 += 8;
        }
        return false;
    }

    public static boolean isWeakKey(byte[] bArr, int i2) {
        return isWeakKey(bArr, i2, bArr.length - i2);
    }
}
