package org.spongycastle.crypto.modes.gcm;

import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public abstract class GCMUtil {
    private static final int E1 = -520093696;
    private static final long E1L = -2233785415175766016L;
    private static final int[] LOOKUP = generateLookup();

    public static byte[] asBytes(int[] iArr) {
        byte[] bArr = new byte[16];
        Pack.intToBigEndian(iArr, bArr, 0);
        return bArr;
    }

    public static int[] asInts(byte[] bArr) {
        int[] iArr = new int[4];
        Pack.bigEndianToInt(bArr, 0, iArr);
        return iArr;
    }

    public static long[] asLongs(byte[] bArr) {
        long[] jArr = new long[2];
        Pack.bigEndianToLong(bArr, 0, jArr);
        return jArr;
    }

    private static int[] generateLookup() {
        int[] iArr = new int[256];
        for (int i2 = 0; i2 < 256; i2++) {
            int i3 = 0;
            for (int i4 = 7; i4 >= 0; i4--) {
                if (((1 << i4) & i2) != 0) {
                    i3 ^= E1 >>> (7 - i4);
                }
            }
            iArr[i2] = i3;
        }
        return iArr;
    }

    public static void multiply(byte[] bArr, byte[] bArr2) {
        int[] iArrAsInts = asInts(bArr);
        multiply(iArrAsInts, asInts(bArr2));
        asBytes(iArrAsInts, bArr);
    }

    public static void multiplyP(int[] iArr) {
        int iShiftRight = shiftRight(iArr) >> 8;
        iArr[0] = (iShiftRight & E1) ^ iArr[0];
    }

    public static void multiplyP8(int[] iArr) {
        int iShiftRightN = shiftRightN(iArr, 8);
        iArr[0] = LOOKUP[iShiftRightN >>> 24] ^ iArr[0];
    }

    public static byte[] oneAsBytes() {
        byte[] bArr = new byte[16];
        bArr[0] = -128;
        return bArr;
    }

    public static int[] oneAsInts() {
        int[] iArr = new int[4];
        iArr[0] = Integer.MIN_VALUE;
        return iArr;
    }

    public static long[] oneAsLongs() {
        return new long[]{Long.MIN_VALUE, 0};
    }

    public static int shiftRight(int[] iArr) {
        int i2 = iArr[0];
        iArr[0] = i2 >>> 1;
        int i3 = iArr[1];
        iArr[1] = (i2 << 31) | (i3 >>> 1);
        int i4 = i3 << 31;
        int i5 = iArr[2];
        iArr[2] = i4 | (i5 >>> 1);
        int i6 = i5 << 31;
        int i7 = iArr[3];
        iArr[3] = i6 | (i7 >>> 1);
        return i7 << 31;
    }

    public static int shiftRightN(int[] iArr, int i2) {
        int i3 = iArr[0];
        int i4 = 32 - i2;
        iArr[0] = i3 >>> i2;
        int i5 = iArr[1];
        iArr[1] = (i3 << i4) | (i5 >>> i2);
        int i6 = i5 << i4;
        int i7 = iArr[2];
        iArr[2] = i6 | (i7 >>> i2);
        int i8 = i7 << i4;
        int i9 = iArr[3];
        iArr[3] = (i9 >>> i2) | i8;
        return i9 << i4;
    }

    public static void xor(byte[] bArr, byte[] bArr2) {
        int i2 = 0;
        do {
            bArr[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
            int i3 = i2 + 1;
            bArr[i3] = (byte) (bArr[i3] ^ bArr2[i3]);
            int i4 = i2 + 2;
            bArr[i4] = (byte) (bArr[i4] ^ bArr2[i4]);
            int i5 = i2 + 3;
            bArr[i5] = (byte) (bArr[i5] ^ bArr2[i5]);
            i2 += 4;
        } while (i2 < 16);
    }

    public static void asBytes(int[] iArr, byte[] bArr) {
        Pack.intToBigEndian(iArr, bArr, 0);
    }

    public static void asInts(byte[] bArr, int[] iArr) {
        Pack.bigEndianToInt(bArr, 0, iArr);
    }

    public static void asLongs(byte[] bArr, long[] jArr) {
        Pack.bigEndianToLong(bArr, 0, jArr);
    }

    public static void multiplyP(int[] iArr, int[] iArr2) {
        int iShiftRight = shiftRight(iArr, iArr2) >> 8;
        iArr2[0] = (iShiftRight & E1) ^ iArr2[0];
    }

    public static void multiplyP8(int[] iArr, int[] iArr2) {
        int iShiftRightN = shiftRightN(iArr, 8, iArr2);
        iArr2[0] = LOOKUP[iShiftRightN >>> 24] ^ iArr2[0];
    }

    public static byte[] asBytes(long[] jArr) {
        byte[] bArr = new byte[16];
        Pack.longToBigEndian(jArr, bArr, 0);
        return bArr;
    }

    public static void multiply(int[] iArr, int[] iArr2) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = iArr[3];
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < 4; i10++) {
            int i11 = iArr2[i10];
            for (int i12 = 0; i12 < 32; i12++) {
                int i13 = i11 >> 31;
                i11 <<= 1;
                i6 ^= i2 & i13;
                i7 ^= i3 & i13;
                i8 ^= i4 & i13;
                i9 ^= i13 & i5;
                int i14 = (i5 << 31) >> 8;
                i5 = (i5 >>> 1) | (i4 << 31);
                i4 = (i4 >>> 1) | (i3 << 31);
                i3 = (i3 >>> 1) | (i2 << 31);
                i2 = (i2 >>> 1) ^ (i14 & E1);
            }
        }
        iArr[0] = i6;
        iArr[1] = i7;
        iArr[2] = i8;
        iArr[3] = i9;
    }

    public static void xor(byte[] bArr, byte[] bArr2, int i2, int i3) {
        while (true) {
            i3--;
            if (i3 < 0) {
                return;
            } else {
                bArr[i3] = (byte) (bArr[i3] ^ bArr2[i2 + i3]);
            }
        }
    }

    public static void asBytes(long[] jArr, byte[] bArr) {
        Pack.longToBigEndian(jArr, bArr, 0);
    }

    public static void xor(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int i2 = 0;
        do {
            bArr3[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
            int i3 = i2 + 1;
            bArr3[i3] = (byte) (bArr[i3] ^ bArr2[i3]);
            int i4 = i2 + 2;
            bArr3[i4] = (byte) (bArr[i4] ^ bArr2[i4]);
            int i5 = i2 + 3;
            bArr3[i5] = (byte) (bArr[i5] ^ bArr2[i5]);
            i2 += 4;
        } while (i2 < 16);
    }

    public static int shiftRight(int[] iArr, int[] iArr2) {
        int i2 = iArr[0];
        iArr2[0] = i2 >>> 1;
        int i3 = iArr[1];
        iArr2[1] = (i2 << 31) | (i3 >>> 1);
        int i4 = i3 << 31;
        int i5 = iArr[2];
        iArr2[2] = i4 | (i5 >>> 1);
        int i6 = iArr[3];
        iArr2[3] = (i5 << 31) | (i6 >>> 1);
        return i6 << 31;
    }

    public static int shiftRightN(int[] iArr, int i2, int[] iArr2) {
        int i3 = iArr[0];
        int i4 = 32 - i2;
        iArr2[0] = i3 >>> i2;
        int i5 = iArr[1];
        iArr2[1] = (i3 << i4) | (i5 >>> i2);
        int i6 = i5 << i4;
        int i7 = iArr[2];
        iArr2[2] = i6 | (i7 >>> i2);
        int i8 = iArr[3];
        iArr2[3] = (i8 >>> i2) | (i7 << i4);
        return i8 << i4;
    }

    public static void xor(int[] iArr, int[] iArr2) {
        iArr[0] = iArr[0] ^ iArr2[0];
        iArr[1] = iArr[1] ^ iArr2[1];
        iArr[2] = iArr[2] ^ iArr2[2];
        iArr[3] = iArr2[3] ^ iArr[3];
    }

    public static void multiply(long[] jArr, long[] jArr2) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = 0;
        long j5 = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            long j6 = jArr2[i2];
            for (int i3 = 0; i3 < 64; i3++) {
                long j7 = j6 >> 63;
                j6 <<= 1;
                j4 ^= j2 & j7;
                j5 ^= j7 & j3;
                long j8 = (j3 << 63) >> 8;
                j3 = (j3 >>> 1) | (j2 << 63);
                j2 = (j2 >>> 1) ^ (j8 & E1L);
            }
        }
        jArr[0] = j4;
        jArr[1] = j5;
    }

    public static void xor(int[] iArr, int[] iArr2, int[] iArr3) {
        iArr3[0] = iArr[0] ^ iArr2[0];
        iArr3[1] = iArr[1] ^ iArr2[1];
        iArr3[2] = iArr[2] ^ iArr2[2];
        iArr3[3] = iArr[3] ^ iArr2[3];
    }

    public static long shiftRight(long[] jArr) {
        long j2 = jArr[0];
        jArr[0] = j2 >>> 1;
        long j3 = jArr[1];
        jArr[1] = (j2 << 63) | (j3 >>> 1);
        return j3 << 63;
    }

    public static void xor(long[] jArr, long[] jArr2) {
        jArr[0] = jArr[0] ^ jArr2[0];
        jArr[1] = jArr[1] ^ jArr2[1];
    }

    public static void xor(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr2[1] ^ jArr[1];
    }

    public static long shiftRight(long[] jArr, long[] jArr2) {
        long j2 = jArr[0];
        jArr2[0] = j2 >>> 1;
        long j3 = jArr[1];
        jArr2[1] = (j2 << 63) | (j3 >>> 1);
        return j3 << 63;
    }
}
