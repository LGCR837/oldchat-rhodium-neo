package org.spongycastle.pqc.math.ntru.util;

import java.io.InputStream;
import java.math.BigInteger;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ArrayEncoder {
    private static final int[] COEFF1_TABLE = {0, 0, 0, 1, 1, 1, -1, -1};
    private static final int[] COEFF2_TABLE = {0, 1, -1, 0, 1, -1, 0, 1};
    private static final int[] BIT1_TABLE = {1, 1, 1, 0, 0, 0, 1, 0, 1};
    private static final int[] BIT2_TABLE = {1, 1, 1, 1, 0, 0, 0, 1, 0};
    private static final int[] BIT3_TABLE = {1, 0, 1, 0, 0, 1, 1, 1, 0};

    public static int[] decodeMod3Sves(byte[] bArr, int i2) {
        int[] iArr = new int[i2];
        int i3 = 0;
        int i4 = 0;
        while (i3 < bArr.length * 8) {
            int bit = getBit(bArr, i3);
            int i5 = i3 + 2;
            int bit2 = getBit(bArr, i3 + 1);
            i3 += 3;
            int bit3 = (bit * 4) + (bit2 * 2) + getBit(bArr, i5);
            int i6 = i4 + 1;
            iArr[i4] = COEFF1_TABLE[bit3];
            i4 += 2;
            iArr[i6] = COEFF2_TABLE[bit3];
            if (i4 > i2 - 2) {
                break;
            }
        }
        return iArr;
    }

    public static int[] decodeMod3Tight(byte[] bArr, int i2) {
        BigInteger bigInteger = new BigInteger(1, bArr);
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int iIntValue = bigInteger.mod(BigInteger.valueOf(3L)).intValue();
            int i4 = iIntValue - 1;
            iArr[i3] = i4;
            if (i4 > 1) {
                iArr[i3] = iIntValue - 4;
            }
            bigInteger = bigInteger.divide(BigInteger.valueOf(3L));
        }
        return iArr;
    }

    public static int[] decodeModQ(byte[] bArr, int i2, int i3) {
        int[] iArr = new int[i2];
        int iNumberOfLeadingZeros = 31 - Integer.numberOfLeadingZeros(i3);
        int i4 = i2 * iNumberOfLeadingZeros;
        int i5 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            if (i6 > 0 && i6 % iNumberOfLeadingZeros == 0) {
                i5++;
            }
            iArr[i5] = iArr[i5] + (getBit(bArr, i6) << (i6 % iNumberOfLeadingZeros));
        }
        return iArr;
    }

    public static byte[] encodeMod3Sves(int[] iArr) {
        byte[] bArr = new byte[((((iArr.length * 3) + 1) / 2) + 7) / 8];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < (iArr.length / 2) * 2) {
            int i5 = i2 + 1;
            int i6 = iArr[i2] + 1;
            i2 += 2;
            int i7 = iArr[i5] + 1;
            if (i6 == 0 && i7 == 0) {
                throw new IllegalStateException("Illegal encoding!");
            }
            int i8 = (i6 * 3) + i7;
            int[] iArr2 = {BIT1_TABLE[i8], BIT2_TABLE[i8], BIT3_TABLE[i8]};
            for (int i9 = 0; i9 < 3; i9++) {
                bArr[i4] = (byte) (bArr[i4] | (iArr2[i9] << i3));
                if (i3 == 7) {
                    i4++;
                    i3 = 0;
                } else {
                    i3++;
                }
            }
        }
        return bArr;
    }

    public static byte[] encodeMod3Tight(int[] iArr) {
        BigInteger bigIntegerAdd = BigInteger.ZERO;
        for (int length = iArr.length - 1; length >= 0; length--) {
            bigIntegerAdd = bigIntegerAdd.multiply(BigInteger.valueOf(3L)).add(BigInteger.valueOf(iArr[length] + 1));
        }
        int iBitLength = (BigInteger.valueOf(3L).pow(iArr.length).bitLength() + 7) / 8;
        byte[] byteArray = bigIntegerAdd.toByteArray();
        if (byteArray.length >= iBitLength) {
            return byteArray.length > iBitLength ? Arrays.copyOfRange(byteArray, 1, byteArray.length) : byteArray;
        }
        byte[] bArr = new byte[iBitLength];
        System.arraycopy(byteArray, 0, bArr, iBitLength - byteArray.length, byteArray.length);
        return bArr;
    }

    public static byte[] encodeModQ(int[] iArr, int i2) {
        int iNumberOfLeadingZeros = 31 - Integer.numberOfLeadingZeros(i2);
        byte[] bArr = new byte[((iArr.length * iNumberOfLeadingZeros) + 7) / 8];
        int i3 = 0;
        int i4 = 0;
        for (int i5 : iArr) {
            for (int i6 = 0; i6 < iNumberOfLeadingZeros; i6++) {
                bArr[i4] = (byte) ((((i5 >> i6) & 1) << i3) | bArr[i4]);
                if (i3 == 7) {
                    i4++;
                    i3 = 0;
                } else {
                    i3++;
                }
            }
        }
        return bArr;
    }

    private static int getBit(byte[] bArr, int i2) {
        return ((bArr[i2 / 8] & 255) >> (i2 % 8)) & 1;
    }

    public static int[] decodeMod3Tight(InputStream inputStream, int i2) {
        double d2 = i2;
        double dLog = Math.log(3.0d);
        Double.isNaN(d2);
        return decodeMod3Tight(Util.readFullLength(inputStream, (int) Math.ceil(((d2 * dLog) / Math.log(2.0d)) / 8.0d)), i2);
    }

    public static int[] decodeModQ(InputStream inputStream, int i2, int i3) {
        return decodeModQ(Util.readFullLength(inputStream, (((31 - Integer.numberOfLeadingZeros(i3)) * i2) + 7) / 8), i2, i3);
    }
}
