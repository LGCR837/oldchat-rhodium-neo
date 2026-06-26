package org.spongycastle.pqc.crypto.mceliece;

import java.math.BigInteger;
import org.spongycastle.pqc.math.linearalgebra.BigIntUtils;
import org.spongycastle.pqc.math.linearalgebra.GF2Vector;
import org.spongycastle.pqc.math.linearalgebra.IntegerFunctions;

/* JADX INFO: loaded from: classes.dex */
final class Conversions {
    private static final BigInteger ZERO = BigInteger.valueOf(0);
    private static final BigInteger ONE = BigInteger.valueOf(1);

    private Conversions() {
    }

    public static byte[] decode(int i2, int i3, GF2Vector gF2Vector) {
        if (gF2Vector.getLength() != i2 || gF2Vector.getHammingWeight() != i3) {
            throw new IllegalArgumentException("vector has wrong length or hamming weight");
        }
        int[] vecArray = gF2Vector.getVecArray();
        BigInteger bigIntegerBinomial = IntegerFunctions.binomial(i2, i3);
        BigInteger bigIntegerAdd = ZERO;
        int i4 = i2;
        for (int i5 = 0; i5 < i2; i5++) {
            bigIntegerBinomial = bigIntegerBinomial.multiply(BigInteger.valueOf(i4 - i3)).divide(BigInteger.valueOf(i4));
            i4--;
            if ((vecArray[i5 >> 5] & (1 << (i5 & 31))) != 0) {
                bigIntegerAdd = bigIntegerAdd.add(bigIntegerBinomial);
                int i6 = i3 - 1;
                bigIntegerBinomial = i4 == i6 ? ONE : bigIntegerBinomial.multiply(BigInteger.valueOf(i3)).divide(BigInteger.valueOf(i4 - i6));
                i3 = i6;
            }
        }
        return BigIntUtils.toMinimalByteArray(bigIntegerAdd);
    }

    public static GF2Vector encode(int i2, int i3, byte[] bArr) {
        if (i2 < i3) {
            throw new IllegalArgumentException("n < t");
        }
        BigInteger bigIntegerBinomial = IntegerFunctions.binomial(i2, i3);
        BigInteger bigInteger = new BigInteger(1, bArr);
        if (bigInteger.compareTo(bigIntegerBinomial) >= 0) {
            throw new IllegalArgumentException("Encoded number too large.");
        }
        GF2Vector gF2Vector = new GF2Vector(i2);
        int i4 = i2;
        for (int i5 = 0; i5 < i2; i5++) {
            bigIntegerBinomial = bigIntegerBinomial.multiply(BigInteger.valueOf(i4 - i3)).divide(BigInteger.valueOf(i4));
            i4--;
            if (bigIntegerBinomial.compareTo(bigInteger) <= 0) {
                gF2Vector.setBit(i5);
                bigInteger = bigInteger.subtract(bigIntegerBinomial);
                int i6 = i3 - 1;
                bigIntegerBinomial = i4 == i6 ? ONE : bigIntegerBinomial.multiply(BigInteger.valueOf(i3)).divide(BigInteger.valueOf(i4 - i6));
                i3 = i6;
            }
        }
        return gF2Vector;
    }

    public static byte[] signConversion(int i2, int i3, byte[] bArr) {
        if (i2 < i3) {
            throw new IllegalArgumentException("n < t");
        }
        BigInteger bigIntegerBinomial = IntegerFunctions.binomial(i2, i3);
        int iBitLength = bigIntegerBinomial.bitLength() - 1;
        int i4 = iBitLength >> 3;
        int i5 = iBitLength & 7;
        int i6 = 8;
        if (i5 == 0) {
            i4--;
            i5 = 8;
        }
        int i7 = i2 >> 3;
        int i8 = i2 & 7;
        if (i8 == 0) {
            i7--;
        } else {
            i6 = i8;
        }
        int i9 = i7 + 1;
        byte[] bArr2 = new byte[i9];
        if (bArr.length < i9) {
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            for (int length = bArr.length; length < i9; length++) {
                bArr2[length] = 0;
            }
        } else {
            System.arraycopy(bArr, 0, bArr2, 0, i7);
            bArr2[i7] = (byte) (bArr[i7] & ((1 << i6) - 1));
        }
        BigInteger bigIntegerAdd = ZERO;
        int i10 = i2;
        for (int i11 = 0; i11 < i2; i11++) {
            bigIntegerBinomial = bigIntegerBinomial.multiply(new BigInteger(Integer.toString(i10 - i3))).divide(new BigInteger(Integer.toString(i10)));
            i10--;
            if (((byte) (bArr2[i11 >>> 3] & (1 << (i11 & 7)))) != 0) {
                bigIntegerAdd = bigIntegerAdd.add(bigIntegerBinomial);
                int i12 = i3 - 1;
                bigIntegerBinomial = i10 == i12 ? ONE : bigIntegerBinomial.multiply(new BigInteger(Integer.toString(i3))).divide(new BigInteger(Integer.toString(i10 - i12)));
                i3 = i12;
            }
        }
        int i13 = i4 + 1;
        byte[] bArr3 = new byte[i13];
        byte[] byteArray = bigIntegerAdd.toByteArray();
        if (byteArray.length < i13) {
            System.arraycopy(byteArray, 0, bArr3, 0, byteArray.length);
            for (int length2 = byteArray.length; length2 < i13; length2++) {
                bArr3[length2] = 0;
            }
        } else {
            System.arraycopy(byteArray, 0, bArr3, 0, i4);
            bArr3[i4] = (byte) (((1 << i5) - 1) & byteArray[i4]);
        }
        return bArr3;
    }
}
