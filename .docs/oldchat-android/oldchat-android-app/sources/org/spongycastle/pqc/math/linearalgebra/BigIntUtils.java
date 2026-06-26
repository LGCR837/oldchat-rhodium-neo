package org.spongycastle.pqc.math.linearalgebra;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class BigIntUtils {
    private BigIntUtils() {
    }

    public static boolean equals(BigInteger[] bigIntegerArr, BigInteger[] bigIntegerArr2) {
        if (bigIntegerArr.length != bigIntegerArr2.length) {
            return false;
        }
        int iCompareTo = 0;
        for (int i2 = 0; i2 < bigIntegerArr.length; i2++) {
            iCompareTo |= bigIntegerArr[i2].compareTo(bigIntegerArr2[i2]);
        }
        return iCompareTo == 0;
    }

    public static void fill(BigInteger[] bigIntegerArr, BigInteger bigInteger) {
        for (int length = bigIntegerArr.length - 1; length >= 0; length--) {
            bigIntegerArr[length] = bigInteger;
        }
    }

    public static BigInteger[] subArray(BigInteger[] bigIntegerArr, int i2, int i3) {
        int i4 = i3 - i2;
        BigInteger[] bigIntegerArr2 = new BigInteger[i4];
        System.arraycopy(bigIntegerArr, i2, bigIntegerArr2, 0, i4);
        return bigIntegerArr2;
    }

    public static int[] toIntArray(BigInteger[] bigIntegerArr) {
        int[] iArr = new int[bigIntegerArr.length];
        for (int i2 = 0; i2 < bigIntegerArr.length; i2++) {
            iArr[i2] = bigIntegerArr[i2].intValue();
        }
        return iArr;
    }

    public static int[] toIntArrayModQ(int i2, BigInteger[] bigIntegerArr) {
        BigInteger bigIntegerValueOf = BigInteger.valueOf(i2);
        int[] iArr = new int[bigIntegerArr.length];
        for (int i3 = 0; i3 < bigIntegerArr.length; i3++) {
            iArr[i3] = bigIntegerArr[i3].mod(bigIntegerValueOf).intValue();
        }
        return iArr;
    }

    public static byte[] toMinimalByteArray(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length == 1 || (bigInteger.bitLength() & 7) != 0) {
            return byteArray;
        }
        int iBitLength = bigInteger.bitLength() >> 3;
        byte[] bArr = new byte[iBitLength];
        System.arraycopy(byteArray, 1, bArr, 0, iBitLength);
        return bArr;
    }
}
