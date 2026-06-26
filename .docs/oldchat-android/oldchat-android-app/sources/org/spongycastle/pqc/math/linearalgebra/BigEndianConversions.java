package org.spongycastle.pqc.math.linearalgebra;

/* JADX INFO: loaded from: classes.dex */
public final class BigEndianConversions {
    private BigEndianConversions() {
    }

    public static byte[] I2OSP(int i2) {
        return new byte[]{(byte) (i2 >>> 24), (byte) (i2 >>> 16), (byte) (i2 >>> 8), (byte) i2};
    }

    public static int OS2IP(byte[] bArr) {
        if (bArr.length > 4) {
            throw new ArithmeticException("invalid input length");
        }
        if (bArr.length == 0) {
            return 0;
        }
        int length = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            length |= (bArr[i2] & 255) << (((bArr.length - 1) - i2) * 8);
        }
        return length;
    }

    public static long OS2LIP(byte[] bArr, int i2) {
        long j2 = ((((long) bArr[i2]) & 255) << 56) | ((((long) bArr[i2 + 1]) & 255) << 48) | ((((long) bArr[i2 + 2]) & 255) << 40) | ((((long) bArr[i2 + 3]) & 255) << 32) | ((255 & ((long) bArr[i2 + 4])) << 24) | ((long) ((bArr[i2 + 5] & 255) << 16));
        return ((long) (bArr[i2 + 7] & 255)) | j2 | ((long) ((bArr[i2 + 6] & 255) << 8));
    }

    public static byte[] toByteArray(int[] iArr) {
        byte[] bArr = new byte[iArr.length << 2];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            I2OSP(iArr[i2], bArr, i2 << 2);
        }
        return bArr;
    }

    public static int[] toIntArray(byte[] bArr) {
        int length = (bArr.length + 3) / 4;
        int length2 = bArr.length & 3;
        int[] iArr = new int[length];
        int i2 = 0;
        int i3 = 0;
        while (i2 <= length - 2) {
            iArr[i2] = OS2IP(bArr, i3);
            i2++;
            i3 += 4;
        }
        if (length2 != 0) {
            iArr[length - 1] = OS2IP(bArr, i3, length2);
        } else {
            iArr[length - 1] = OS2IP(bArr, i3);
        }
        return iArr;
    }

    public static byte[] I2OSP(int i2, int i3) {
        if (i2 < 0) {
            return null;
        }
        int iCeilLog256 = IntegerFunctions.ceilLog256(i2);
        if (iCeilLog256 > i3) {
            throw new ArithmeticException("Cannot encode given integer into specified number of octets.");
        }
        byte[] bArr = new byte[i3];
        int i4 = i3 - 1;
        for (int i5 = i4; i5 >= i3 - iCeilLog256; i5--) {
            bArr[i5] = (byte) (i2 >>> ((i4 - i5) * 8));
        }
        return bArr;
    }

    public static byte[] toByteArray(int[] iArr, int i2) {
        int length = iArr.length;
        byte[] bArr = new byte[i2];
        int i3 = 0;
        int i4 = 0;
        while (i3 <= length - 2) {
            I2OSP(iArr[i3], bArr, i4);
            i3++;
            i4 += 4;
        }
        I2OSP(iArr[length - 1], bArr, i4, i2 - i4);
        return bArr;
    }

    public static void I2OSP(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) (i2 >>> 24);
        bArr[i3 + 1] = (byte) (i2 >>> 16);
        bArr[i3 + 2] = (byte) (i2 >>> 8);
        bArr[i3 + 3] = (byte) i2;
    }

    public static int OS2IP(byte[] bArr, int i2) {
        int i3 = ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2] & 255) << 24);
        return (bArr[i2 + 3] & 255) | i3 | ((bArr[i2 + 2] & 255) << 8);
    }

    public static byte[] I2OSP(long j2) {
        return new byte[]{(byte) (j2 >>> 56), (byte) (j2 >>> 48), (byte) (j2 >>> 40), (byte) (j2 >>> 32), (byte) (j2 >>> 24), (byte) (j2 >>> 16), (byte) (j2 >>> 8), (byte) j2};
    }

    public static int OS2IP(byte[] bArr, int i2, int i3) {
        if (bArr.length == 0 || bArr.length < (i2 + i3) - 1) {
            return 0;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            i4 |= (bArr[i2 + i5] & 255) << (((i3 - i5) - 1) * 8);
        }
        return i4;
    }

    public static void I2OSP(long j2, byte[] bArr, int i2) {
        bArr[i2] = (byte) (j2 >>> 56);
        bArr[i2 + 1] = (byte) (j2 >>> 48);
        bArr[i2 + 2] = (byte) (j2 >>> 40);
        bArr[i2 + 3] = (byte) (j2 >>> 32);
        bArr[i2 + 4] = (byte) (j2 >>> 24);
        bArr[i2 + 5] = (byte) (j2 >>> 16);
        bArr[i2 + 6] = (byte) (j2 >>> 8);
        bArr[i2 + 7] = (byte) j2;
    }

    public static void I2OSP(int i2, byte[] bArr, int i3, int i4) {
        int i5 = i4 - 1;
        for (int i6 = i5; i6 >= 0; i6--) {
            bArr[i3 + i6] = (byte) (i2 >>> ((i5 - i6) * 8));
        }
    }
}
