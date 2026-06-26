package org.spongycastle.pqc.crypto.rainbow.util;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public class RainbowUtil {
    public static short[] convertArray(byte[] bArr) {
        short[] sArr = new short[bArr.length];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            sArr[i2] = (short) (bArr[i2] & 255);
        }
        return sArr;
    }

    public static int[] convertArraytoInt(byte[] bArr) {
        int[] iArr = new int[bArr.length];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        return iArr;
    }

    public static byte[] convertIntArray(int[] iArr) {
        byte[] bArr = new byte[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            bArr[i2] = (byte) iArr[i2];
        }
        return bArr;
    }

    public static boolean equals(short[] sArr, short[] sArr2) {
        if (sArr.length != sArr2.length) {
            return false;
        }
        boolean z2 = true;
        for (int length = sArr.length - 1; length >= 0; length--) {
            z2 &= sArr[length] == sArr2[length];
        }
        return z2;
    }

    public static short[][] convertArray(byte[][] bArr) {
        short[][] sArr = (short[][]) Array.newInstance((Class<?>) Short.TYPE, bArr.length, bArr[0].length);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            for (int i3 = 0; i3 < bArr[0].length; i3++) {
                sArr[i2][i3] = (short) (bArr[i2][i3] & 255);
            }
        }
        return sArr;
    }

    public static boolean equals(short[][] sArr, short[][] sArr2) {
        if (sArr.length != sArr2.length) {
            return false;
        }
        boolean zEquals = true;
        for (int length = sArr.length - 1; length >= 0; length--) {
            zEquals &= equals(sArr[length], sArr2[length]);
        }
        return zEquals;
    }

    public static boolean equals(short[][][] sArr, short[][][] sArr2) {
        if (sArr.length != sArr2.length) {
            return false;
        }
        boolean zEquals = true;
        for (int length = sArr.length - 1; length >= 0; length--) {
            zEquals &= equals(sArr[length], sArr2[length]);
        }
        return zEquals;
    }

    public static short[][][] convertArray(byte[][][] bArr) {
        int length = bArr.length;
        byte[][] bArr2 = bArr[0];
        short[][][] sArr = (short[][][]) Array.newInstance((Class<?>) Short.TYPE, length, bArr2.length, bArr2[0].length);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            for (int i3 = 0; i3 < bArr[0].length; i3++) {
                for (int i4 = 0; i4 < bArr[0][0].length; i4++) {
                    sArr[i2][i3][i4] = (short) (bArr[i2][i3][i4] & 255);
                }
            }
        }
        return sArr;
    }

    public static byte[] convertArray(short[] sArr) {
        byte[] bArr = new byte[sArr.length];
        for (int i2 = 0; i2 < sArr.length; i2++) {
            bArr[i2] = (byte) sArr[i2];
        }
        return bArr;
    }

    public static byte[][] convertArray(short[][] sArr) {
        byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, sArr.length, sArr[0].length);
        for (int i2 = 0; i2 < sArr.length; i2++) {
            for (int i3 = 0; i3 < sArr[0].length; i3++) {
                bArr[i2][i3] = (byte) sArr[i2][i3];
            }
        }
        return bArr;
    }

    public static byte[][][] convertArray(short[][][] sArr) {
        int length = sArr.length;
        short[][] sArr2 = sArr[0];
        byte[][][] bArr = (byte[][][]) Array.newInstance((Class<?>) Byte.TYPE, length, sArr2.length, sArr2[0].length);
        for (int i2 = 0; i2 < sArr.length; i2++) {
            for (int i3 = 0; i3 < sArr[0].length; i3++) {
                for (int i4 = 0; i4 < sArr[0][0].length; i4++) {
                    bArr[i2][i3][i4] = (byte) sArr[i2][i3][i4];
                }
            }
        }
        return bArr;
    }
}
