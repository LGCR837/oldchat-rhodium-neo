package org.spongycastle.math.raw;

import java.math.BigInteger;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public abstract class Nat128 {

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    private static final long f7583M = 4294967295L;

    public static int add(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L);
        iArr3[0] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L);
        iArr3[1] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L);
        iArr3[2] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L);
        iArr3[3] = (int) j5;
        return (int) (j5 >>> 32);
    }

    public static int addBothTo(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L) + (((long) iArr3[0]) & 4294967295L);
        iArr3[0] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L) + (((long) iArr3[1]) & 4294967295L);
        iArr3[1] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L) + (((long) iArr3[2]) & 4294967295L);
        iArr3[2] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L) + (((long) iArr3[3]) & 4294967295L);
        iArr3[3] = (int) j5;
        return (int) (j5 >>> 32);
    }

    public static int addTo(int[] iArr, int[] iArr2) {
        long j2 = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L);
        iArr2[0] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L);
        iArr2[1] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L);
        iArr2[2] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[3]) & 4294967295L) + (4294967295L & ((long) iArr2[3]));
        iArr2[3] = (int) j5;
        return (int) (j5 >>> 32);
    }

    public static int addToEachOther(int[] iArr, int i2, int[] iArr2, int i3) {
        long j2 = (((long) iArr[i2]) & 4294967295L) + (((long) iArr2[i3]) & 4294967295L);
        int i4 = (int) j2;
        iArr[i2] = i4;
        iArr2[i3] = i4;
        int i5 = i2 + 1;
        int i6 = i3 + 1;
        long j3 = (j2 >>> 32) + (((long) iArr[i5]) & 4294967295L) + (((long) iArr2[i6]) & 4294967295L);
        int i7 = (int) j3;
        iArr[i5] = i7;
        iArr2[i6] = i7;
        int i8 = i2 + 2;
        int i9 = i3 + 2;
        long j4 = (j3 >>> 32) + (((long) iArr[i8]) & 4294967295L) + (((long) iArr2[i9]) & 4294967295L);
        int i10 = (int) j4;
        iArr[i8] = i10;
        iArr2[i9] = i10;
        int i11 = i2 + 3;
        int i12 = i3 + 3;
        long j5 = (j4 >>> 32) + (((long) iArr[i11]) & 4294967295L) + (4294967295L & ((long) iArr2[i12]));
        int i13 = (int) j5;
        iArr[i11] = i13;
        iArr2[i12] = i13;
        return (int) (j5 >>> 32);
    }

    public static void copy(int[] iArr, int[] iArr2) {
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[1];
        iArr2[2] = iArr[2];
        iArr2[3] = iArr[3];
    }

    public static void copy64(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
    }

    public static int[] create() {
        return new int[4];
    }

    public static long[] create64() {
        return new long[2];
    }

    public static int[] createExt() {
        return new int[8];
    }

    public static long[] createExt64() {
        return new long[4];
    }

    public static boolean diff(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        boolean zGte = gte(iArr, i2, iArr2, i3);
        if (zGte) {
            sub(iArr, i2, iArr2, i3, iArr3, i4);
        } else {
            sub(iArr2, i3, iArr, i2, iArr3, i4);
        }
        return zGte;
    }

    public static boolean eq(int[] iArr, int[] iArr2) {
        for (int i2 = 3; i2 >= 0; i2--) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static boolean eq64(long[] jArr, long[] jArr2) {
        for (int i2 = 1; i2 >= 0; i2--) {
            if (jArr[i2] != jArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 128) {
            throw new IllegalArgumentException();
        }
        int[] iArrCreate = create();
        int i2 = 0;
        while (bigInteger.signum() != 0) {
            iArrCreate[i2] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
            i2++;
        }
        return iArrCreate;
    }

    public static long[] fromBigInteger64(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 128) {
            throw new IllegalArgumentException();
        }
        long[] jArrCreate64 = create64();
        int i2 = 0;
        while (bigInteger.signum() != 0) {
            jArrCreate64[i2] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
            i2++;
        }
        return jArrCreate64;
    }

    public static int getBit(int[] iArr, int i2) {
        int i3;
        if (i2 == 0) {
            i3 = iArr[0];
        } else {
            int i4 = i2 >> 5;
            if (i4 < 0 || i4 >= 4) {
                return 0;
            }
            i3 = iArr[i4] >>> (i2 & 31);
        }
        return i3 & 1;
    }

    public static boolean gte(int[] iArr, int[] iArr2) {
        for (int i2 = 3; i2 >= 0; i2--) {
            int i3 = iArr[i2] ^ PKIFailureInfo.systemUnavail;
            int i4 = Integer.MIN_VALUE ^ iArr2[i2];
            if (i3 < i4) {
                return false;
            }
            if (i3 > i4) {
                return true;
            }
        }
        return true;
    }

    public static boolean isOne(int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < 4; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOne64(long[] jArr) {
        return jArr[0] == 1 && jArr[1] == 0;
    }

    public static boolean isZero(int[] iArr) {
        for (int i2 = 0; i2 < 4; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero64(long[] jArr) {
        for (int i2 = 0; i2 < 2; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = ((long) iArr2[0]) & 4294967295L;
        int i2 = 1;
        long j3 = ((long) iArr2[1]) & 4294967295L;
        long j4 = ((long) iArr2[2]) & 4294967295L;
        long j5 = ((long) iArr2[3]) & 4294967295L;
        long j6 = ((long) iArr[0]) & 4294967295L;
        long j7 = j6 * j2;
        iArr3[0] = (int) j7;
        char c2 = ' ';
        long j8 = (j7 >>> 32) + (j6 * j3);
        iArr3[1] = (int) j8;
        long j9 = (j8 >>> 32) + (j6 * j4);
        iArr3[2] = (int) j9;
        long j10 = (j9 >>> 32) + (j6 * j5);
        iArr3[3] = (int) j10;
        int i3 = (int) (j10 >>> 32);
        iArr3[4] = i3;
        for (int i4 = 4; i2 < i4; i4 = 4) {
            long j11 = ((long) iArr[i2]) & 4294967295L;
            long j12 = (j11 * j2) + (((long) iArr3[i2]) & 4294967295L);
            iArr3[i2] = (int) j12;
            int i5 = i2 + 1;
            long j13 = j2;
            long j14 = (j12 >>> c2) + (j11 * j3) + (((long) iArr3[i5]) & 4294967295L);
            iArr3[i5] = (int) j14;
            int i6 = i2 + 2;
            long j15 = (j14 >>> 32) + (j11 * j4) + (((long) iArr3[i6]) & 4294967295L);
            iArr3[i6] = (int) j15;
            c2 = ' ';
            int i7 = i2 + 3;
            long j16 = (j15 >>> 32) + (j11 * j5) + (((long) iArr3[i7]) & 4294967295L);
            iArr3[i7] = (int) j16;
            iArr3[i2 + 4] = (int) (j16 >>> 32);
            i2 = i5;
            j2 = j13;
            j3 = j3;
        }
    }

    public static long mul33Add(int i2, int[] iArr, int i3, int[] iArr2, int i4, int[] iArr3, int i5) {
        long j2 = ((long) i2) & 4294967295L;
        long j3 = ((long) iArr[i3]) & 4294967295L;
        long j4 = (j2 * j3) + (((long) iArr2[i4]) & 4294967295L);
        iArr3[i5] = (int) j4;
        long j5 = ((long) iArr[i3 + 1]) & 4294967295L;
        long j6 = (j4 >>> 32) + (j2 * j5) + j3 + (((long) iArr2[i4 + 1]) & 4294967295L);
        iArr3[i5 + 1] = (int) j6;
        long j7 = j6 >>> 32;
        long j8 = ((long) iArr[i3 + 2]) & 4294967295L;
        long j9 = j7 + (j2 * j8) + j5 + (((long) iArr2[i4 + 2]) & 4294967295L);
        iArr3[i5 + 2] = (int) j9;
        long j10 = ((long) iArr[i3 + 3]) & 4294967295L;
        long j11 = (j9 >>> 32) + (j2 * j10) + j8 + (4294967295L & ((long) iArr2[i4 + 3]));
        iArr3[i5 + 3] = (int) j11;
        return (j11 >>> 32) + j10;
    }

    public static int mul33DWordAdd(int i2, long j2, int[] iArr, int i3) {
        long j3 = ((long) i2) & 4294967295L;
        long j4 = j2 & 4294967295L;
        long j5 = (j3 * j4) + (((long) iArr[i3]) & 4294967295L);
        iArr[i3] = (int) j5;
        long j6 = j2 >>> 32;
        long j7 = (j3 * j6) + j4;
        int i4 = i3 + 1;
        long j8 = (j5 >>> 32) + j7 + (((long) iArr[i4]) & 4294967295L);
        iArr[i4] = (int) j8;
        int i5 = i3 + 2;
        long j9 = (j8 >>> 32) + j6 + (((long) iArr[i5]) & 4294967295L);
        iArr[i5] = (int) j9;
        int i6 = i3 + 3;
        long j10 = (j9 >>> 32) + (((long) iArr[i6]) & 4294967295L);
        iArr[i6] = (int) j10;
        return (int) (j10 >>> 32);
    }

    public static int mul33WordAdd(int i2, int i3, int[] iArr, int i4) {
        long j2 = ((long) i2) & 4294967295L;
        long j3 = ((long) i3) & 4294967295L;
        long j4 = (j2 * j3) + (((long) iArr[i4]) & 4294967295L);
        iArr[i4] = (int) j4;
        int i5 = i4 + 1;
        long j5 = (j4 >>> 32) + j3 + (((long) iArr[i5]) & 4294967295L);
        iArr[i5] = (int) j5;
        long j6 = j5 >>> 32;
        int i6 = i4 + 2;
        long j7 = j6 + (4294967295L & ((long) iArr[i6]));
        iArr[i6] = (int) j7;
        if ((j7 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(4, iArr, i4, 3);
    }

    public static int mulAddTo(int[] iArr, int[] iArr2, int[] iArr3) {
        int i2 = 0;
        long j2 = ((long) iArr2[0]) & 4294967295L;
        long j3 = ((long) iArr2[1]) & 4294967295L;
        long j4 = ((long) iArr2[2]) & 4294967295L;
        long j5 = ((long) iArr2[3]) & 4294967295L;
        long j6 = 0;
        while (i2 < 4) {
            long j7 = ((long) iArr[i2]) & 4294967295L;
            long j8 = j2;
            long j9 = (((long) iArr3[i2]) & 4294967295L) + (j7 * j2);
            iArr3[i2] = (int) j9;
            int i3 = i2 + 1;
            long j10 = (j9 >>> 32) + (j7 * j3) + (((long) iArr3[i3]) & 4294967295L);
            iArr3[i3] = (int) j10;
            int i4 = i2 + 2;
            long j11 = (j10 >>> 32) + (j7 * j4) + (((long) iArr3[i4]) & 4294967295L);
            iArr3[i4] = (int) j11;
            int i5 = i2 + 3;
            long j12 = (j11 >>> 32) + (j7 * j5) + (((long) iArr3[i5]) & 4294967295L);
            iArr3[i5] = (int) j12;
            int i6 = i2 + 4;
            long j13 = (j12 >>> 32) + j6 + (((long) iArr3[i6]) & 4294967295L);
            iArr3[i6] = (int) j13;
            j6 = j13 >>> 32;
            i2 = i3;
            j2 = j8;
            j3 = j3;
        }
        return (int) j6;
    }

    public static int mulWord(int i2, int[] iArr, int[] iArr2, int i3) {
        long j2 = ((long) i2) & 4294967295L;
        long j3 = 0;
        int i4 = 0;
        do {
            long j4 = j3 + ((((long) iArr[i4]) & 4294967295L) * j2);
            iArr2[i3 + i4] = (int) j4;
            j3 = j4 >>> 32;
            i4++;
        } while (i4 < 4);
        return (int) j3;
    }

    public static int mulWordAddExt(int i2, int[] iArr, int i3, int[] iArr2, int i4) {
        long j2 = ((long) i2) & 4294967295L;
        long j3 = ((((long) iArr[i3]) & 4294967295L) * j2) + (((long) iArr2[i4]) & 4294967295L);
        iArr2[i4] = (int) j3;
        int i5 = i4 + 1;
        long j4 = (j3 >>> 32) + ((((long) iArr[i3 + 1]) & 4294967295L) * j2) + (((long) iArr2[i5]) & 4294967295L);
        iArr2[i5] = (int) j4;
        int i6 = i4 + 2;
        long j5 = (j4 >>> 32) + ((((long) iArr[i3 + 2]) & 4294967295L) * j2) + (((long) iArr2[i6]) & 4294967295L);
        iArr2[i6] = (int) j5;
        int i7 = i4 + 3;
        long j6 = (j5 >>> 32) + (j2 * (((long) iArr[i3 + 3]) & 4294967295L)) + (((long) iArr2[i7]) & 4294967295L);
        iArr2[i7] = (int) j6;
        return (int) (j6 >>> 32);
    }

    public static int mulWordDwordAdd(int i2, long j2, int[] iArr, int i3) {
        long j3 = ((long) i2) & 4294967295L;
        long j4 = ((j2 & 4294967295L) * j3) + (((long) iArr[i3]) & 4294967295L);
        iArr[i3] = (int) j4;
        long j5 = j3 * (j2 >>> 32);
        int i4 = i3 + 1;
        long j6 = (j4 >>> 32) + j5 + (((long) iArr[i4]) & 4294967295L);
        iArr[i4] = (int) j6;
        int i5 = i3 + 2;
        long j7 = (j6 >>> 32) + (4294967295L & ((long) iArr[i5]));
        iArr[i5] = (int) j7;
        if ((j7 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(4, iArr, i3, 3);
    }

    public static int mulWordsAdd(int i2, int i3, int[] iArr, int i4) {
        long j2 = ((((long) i3) & 4294967295L) * (((long) i2) & 4294967295L)) + (((long) iArr[i4]) & 4294967295L);
        iArr[i4] = (int) j2;
        int i5 = i4 + 1;
        long j3 = (j2 >>> 32) + (4294967295L & ((long) iArr[i5]));
        iArr[i5] = (int) j3;
        if ((j3 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(4, iArr, i4, 2);
    }

    public static void square(int[] iArr, int[] iArr2) {
        long j2 = ((long) iArr[0]) & 4294967295L;
        int i2 = 8;
        int i3 = 3;
        int i4 = 0;
        while (true) {
            int i5 = i3 - 1;
            long j3 = ((long) iArr[i3]) & 4294967295L;
            long j4 = j3 * j3;
            iArr2[i2 - 1] = (i4 << 31) | ((int) (j4 >>> 33));
            i2 -= 2;
            iArr2[i2] = (int) (j4 >>> 1);
            i4 = (int) j4;
            if (i5 <= 0) {
                long j5 = j2 * j2;
                long j6 = (j5 >>> 33) | (((long) (i4 << 31)) & 4294967295L);
                iArr2[0] = (int) j5;
                long j7 = ((long) iArr[1]) & 4294967295L;
                long j8 = ((long) iArr2[2]) & 4294967295L;
                long j9 = j6 + (j7 * j2);
                int i6 = (int) j9;
                iArr2[1] = (i6 << 1) | (((int) (j5 >>> 32)) & 1);
                int i7 = i6 >>> 31;
                long j10 = j8 + (j9 >>> 32);
                long j11 = ((long) iArr[2]) & 4294967295L;
                long j12 = ((long) iArr2[3]) & 4294967295L;
                long j13 = ((long) iArr2[4]) & 4294967295L;
                long j14 = j10 + (j11 * j2);
                int i8 = (int) j14;
                iArr2[2] = (i8 << 1) | i7;
                long j15 = j12 + (j14 >>> 32) + (j11 * j7);
                long j16 = j13 + (j15 >>> 32);
                long j17 = ((long) iArr[3]) & 4294967295L;
                long j18 = (((long) iArr2[5]) & 4294967295L) + (j16 >>> 32);
                long j19 = (((long) iArr2[6]) & 4294967295L) + (j18 >>> 32);
                long j20 = (j15 & 4294967295L) + (j2 * j17);
                int i9 = (int) j20;
                iArr2[3] = (i9 << 1) | (i8 >>> 31);
                long j21 = (j16 & 4294967295L) + (j20 >>> 32) + (j7 * j17);
                long j22 = (j18 & 4294967295L) + (j21 >>> 32) + (j17 * j11);
                long j23 = j19 + (j22 >>> 32);
                int i10 = (int) j21;
                iArr2[4] = (i9 >>> 31) | (i10 << 1);
                int i11 = i10 >>> 31;
                int i12 = (int) (4294967295L & j22);
                iArr2[5] = i11 | (i12 << 1);
                int i13 = i12 >>> 31;
                int i14 = (int) j23;
                iArr2[6] = i13 | (i14 << 1);
                iArr2[7] = ((iArr2[7] + ((int) (j23 >>> 32))) << 1) | (i14 >>> 31);
                return;
            }
            i3 = i5;
        }
    }

    public static int sub(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = (((long) iArr[0]) & 4294967295L) - (((long) iArr2[0]) & 4294967295L);
        iArr3[0] = (int) j2;
        long j3 = (j2 >> 32) + ((((long) iArr[1]) & 4294967295L) - (((long) iArr2[1]) & 4294967295L));
        iArr3[1] = (int) j3;
        long j4 = (j3 >> 32) + ((((long) iArr[2]) & 4294967295L) - (((long) iArr2[2]) & 4294967295L));
        iArr3[2] = (int) j4;
        long j5 = (j4 >> 32) + ((((long) iArr[3]) & 4294967295L) - (((long) iArr2[3]) & 4294967295L));
        iArr3[3] = (int) j5;
        return (int) (j5 >> 32);
    }

    public static int subBothFrom(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = ((((long) iArr3[0]) & 4294967295L) - (((long) iArr[0]) & 4294967295L)) - (((long) iArr2[0]) & 4294967295L);
        iArr3[0] = (int) j2;
        long j3 = (j2 >> 32) + (((((long) iArr3[1]) & 4294967295L) - (((long) iArr[1]) & 4294967295L)) - (((long) iArr2[1]) & 4294967295L));
        iArr3[1] = (int) j3;
        long j4 = (j3 >> 32) + (((((long) iArr3[2]) & 4294967295L) - (((long) iArr[2]) & 4294967295L)) - (((long) iArr2[2]) & 4294967295L));
        iArr3[2] = (int) j4;
        long j5 = (j4 >> 32) + (((((long) iArr3[3]) & 4294967295L) - (((long) iArr[3]) & 4294967295L)) - (((long) iArr2[3]) & 4294967295L));
        iArr3[3] = (int) j5;
        return (int) (j5 >> 32);
    }

    public static int subFrom(int[] iArr, int[] iArr2) {
        long j2 = (((long) iArr2[0]) & 4294967295L) - (((long) iArr[0]) & 4294967295L);
        iArr2[0] = (int) j2;
        long j3 = (j2 >> 32) + ((((long) iArr2[1]) & 4294967295L) - (((long) iArr[1]) & 4294967295L));
        iArr2[1] = (int) j3;
        long j4 = (j3 >> 32) + ((((long) iArr2[2]) & 4294967295L) - (((long) iArr[2]) & 4294967295L));
        iArr2[2] = (int) j4;
        long j5 = (j4 >> 32) + ((((long) iArr2[3]) & 4294967295L) - (4294967295L & ((long) iArr[3])));
        iArr2[3] = (int) j5;
        return (int) (j5 >> 32);
    }

    public static BigInteger toBigInteger(int[] iArr) {
        byte[] bArr = new byte[16];
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0) {
                Pack.intToBigEndian(i3, bArr, (3 - i2) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static BigInteger toBigInteger64(long[] jArr) {
        byte[] bArr = new byte[16];
        for (int i2 = 0; i2 < 2; i2++) {
            long j2 = jArr[i2];
            if (j2 != 0) {
                Pack.longToBigEndian(j2, bArr, (1 - i2) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void zero(int[] iArr) {
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
    }

    public static boolean gte(int[] iArr, int i2, int[] iArr2, int i3) {
        for (int i4 = 3; i4 >= 0; i4--) {
            int i5 = iArr[i2 + i4] ^ PKIFailureInfo.systemUnavail;
            int i6 = Integer.MIN_VALUE ^ iArr2[i3 + i4];
            if (i5 < i6) {
                return false;
            }
            if (i5 > i6) {
                return true;
            }
        }
        return true;
    }

    public static int addTo(int[] iArr, int i2, int[] iArr2, int i3, int i4) {
        long j2 = (((long) i4) & 4294967295L) + (((long) iArr[i2]) & 4294967295L) + (((long) iArr2[i3]) & 4294967295L);
        iArr2[i3] = (int) j2;
        int i5 = i3 + 1;
        long j3 = (j2 >>> 32) + (((long) iArr[i2 + 1]) & 4294967295L) + (((long) iArr2[i5]) & 4294967295L);
        iArr2[i5] = (int) j3;
        int i6 = i3 + 2;
        long j4 = (j3 >>> 32) + (((long) iArr[i2 + 2]) & 4294967295L) + (((long) iArr2[i6]) & 4294967295L);
        iArr2[i6] = (int) j4;
        int i7 = i3 + 3;
        long j5 = (j4 >>> 32) + (((long) iArr[i2 + 3]) & 4294967295L) + (4294967295L & ((long) iArr2[i7]));
        iArr2[i7] = (int) j5;
        return (int) (j5 >>> 32);
    }

    public static int sub(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j2 = (((long) iArr[i2]) & 4294967295L) - (((long) iArr2[i3]) & 4294967295L);
        iArr3[i4] = (int) j2;
        long j3 = (j2 >> 32) + ((((long) iArr[i2 + 1]) & 4294967295L) - (((long) iArr2[i3 + 1]) & 4294967295L));
        iArr3[i4 + 1] = (int) j3;
        long j4 = (j3 >> 32) + ((((long) iArr[i2 + 2]) & 4294967295L) - (((long) iArr2[i3 + 2]) & 4294967295L));
        iArr3[i4 + 2] = (int) j4;
        long j5 = (j4 >> 32) + ((((long) iArr[i2 + 3]) & 4294967295L) - (((long) iArr2[i3 + 3]) & 4294967295L));
        iArr3[i4 + 3] = (int) j5;
        return (int) (j5 >> 32);
    }

    public static int subFrom(int[] iArr, int i2, int[] iArr2, int i3) {
        long j2 = (((long) iArr2[i3]) & 4294967295L) - (((long) iArr[i2]) & 4294967295L);
        iArr2[i3] = (int) j2;
        int i4 = i3 + 1;
        long j3 = (j2 >> 32) + ((((long) iArr2[i4]) & 4294967295L) - (((long) iArr[i2 + 1]) & 4294967295L));
        iArr2[i4] = (int) j3;
        int i5 = i3 + 2;
        long j4 = (j3 >> 32) + ((((long) iArr2[i5]) & 4294967295L) - (((long) iArr[i2 + 2]) & 4294967295L));
        iArr2[i5] = (int) j4;
        int i6 = i3 + 3;
        long j5 = (j4 >> 32) + ((((long) iArr2[i6]) & 4294967295L) - (((long) iArr[i2 + 3]) & 4294967295L));
        iArr2[i6] = (int) j5;
        return (int) (j5 >> 32);
    }

    public static int mulAddTo(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j2 = 4294967295L;
        long j3 = ((long) iArr2[i3]) & 4294967295L;
        long j4 = ((long) iArr2[i3 + 1]) & 4294967295L;
        long j5 = ((long) iArr2[i3 + 2]) & 4294967295L;
        long j6 = ((long) iArr2[i3 + 3]) & 4294967295L;
        long j7 = 0;
        int i5 = 0;
        int i6 = i4;
        while (i5 < 4) {
            long j8 = ((long) iArr[i2 + i5]) & j2;
            long j9 = j3;
            long j10 = (j8 * j3) + (((long) iArr3[i6]) & j2);
            iArr3[i6] = (int) j10;
            int i7 = i6 + 1;
            long j11 = (j10 >>> 32) + (j8 * j4) + (((long) iArr3[i7]) & 4294967295L);
            iArr3[i7] = (int) j11;
            int i8 = i6 + 2;
            long j12 = (j11 >>> 32) + (j8 * j5) + (((long) iArr3[i8]) & 4294967295L);
            iArr3[i8] = (int) j12;
            int i9 = i6 + 3;
            long j13 = (j12 >>> 32) + (j8 * j6) + (((long) iArr3[i9]) & 4294967295L);
            iArr3[i9] = (int) j13;
            int i10 = i6 + 4;
            long j14 = (j13 >>> 32) + j7 + (((long) iArr3[i10]) & 4294967295L);
            iArr3[i10] = (int) j14;
            j7 = j14 >>> 32;
            i5++;
            i6 = i7;
            j2 = 4294967295L;
            j3 = j9;
            j4 = j4;
        }
        return (int) j7;
    }

    public static void mul(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j2 = ((long) iArr2[i3]) & 4294967295L;
        long j3 = ((long) iArr2[i3 + 1]) & 4294967295L;
        long j4 = ((long) iArr2[i3 + 2]) & 4294967295L;
        long j5 = ((long) iArr2[i3 + 3]) & 4294967295L;
        long j6 = ((long) iArr[i2]) & 4294967295L;
        long j7 = j6 * j2;
        iArr3[i4] = (int) j7;
        char c2 = ' ';
        long j8 = (j7 >>> 32) + (j6 * j3);
        iArr3[i4 + 1] = (int) j8;
        long j9 = (j8 >>> 32) + (j6 * j4);
        iArr3[i4 + 2] = (int) j9;
        long j10 = (j9 >>> 32) + (j6 * j5);
        iArr3[i4 + 3] = (int) j10;
        iArr3[i4 + 4] = (int) (j10 >>> 32);
        int i5 = i4;
        int i6 = 1;
        while (i6 < 4) {
            int i7 = i5 + 1;
            long j11 = ((long) iArr[i2 + i6]) & 4294967295L;
            long j12 = j2;
            long j13 = (j11 * j2) + (((long) iArr3[i7]) & 4294967295L);
            iArr3[i7] = (int) j13;
            int i8 = i5 + 2;
            long j14 = (j13 >>> c2) + (j11 * j3) + (((long) iArr3[i8]) & 4294967295L);
            iArr3[i8] = (int) j14;
            int i9 = i5 + 3;
            long j15 = (j14 >>> 32) + (j11 * j4) + (((long) iArr3[i9]) & 4294967295L);
            iArr3[i9] = (int) j15;
            int i10 = i5 + 4;
            long j16 = (j15 >>> 32) + (j11 * j5) + (((long) iArr3[i10]) & 4294967295L);
            iArr3[i10] = (int) j16;
            iArr3[i5 + 5] = (int) (j16 >>> 32);
            i6++;
            i5 = i7;
            j4 = j4;
            j2 = j12;
            j3 = j3;
            c2 = ' ';
        }
    }

    public static void square(int[] iArr, int i2, int[] iArr2, int i3) {
        long j2 = ((long) iArr[i2]) & 4294967295L;
        int i4 = 0;
        int i5 = 8;
        int i6 = 3;
        while (true) {
            int i7 = i6 - 1;
            long j3 = ((long) iArr[i2 + i6]) & 4294967295L;
            long j4 = j3 * j3;
            iArr2[i3 + (i5 - 1)] = (i4 << 31) | ((int) (j4 >>> 33));
            i5 -= 2;
            iArr2[i3 + i5] = (int) (j4 >>> 1);
            i4 = (int) j4;
            if (i7 <= 0) {
                long j5 = j2 * j2;
                long j6 = (j5 >>> 33) | (((long) (i4 << 31)) & 4294967295L);
                iArr2[i3] = (int) j5;
                int i8 = ((int) (j5 >>> 32)) & 1;
                long j7 = ((long) iArr[i2 + 1]) & 4294967295L;
                int i9 = i3 + 2;
                long j8 = ((long) iArr2[i9]) & 4294967295L;
                long j9 = j6 + (j7 * j2);
                int i10 = (int) j9;
                iArr2[i3 + 1] = (i10 << 1) | i8;
                int i11 = i10 >>> 31;
                long j10 = j8 + (j9 >>> 32);
                long j11 = ((long) iArr[i2 + 2]) & 4294967295L;
                int i12 = i3 + 3;
                long j12 = ((long) iArr2[i12]) & 4294967295L;
                int i13 = i3 + 4;
                long j13 = ((long) iArr2[i13]) & 4294967295L;
                long j14 = j10 + (j11 * j2);
                int i14 = (int) j14;
                iArr2[i9] = (i14 << 1) | i11;
                long j15 = j12 + (j14 >>> 32) + (j11 * j7);
                long j16 = j13 + (j15 >>> 32);
                long j17 = ((long) iArr[i2 + 3]) & 4294967295L;
                int i15 = i3 + 5;
                long j18 = (((long) iArr2[i15]) & 4294967295L) + (j16 >>> 32);
                long j19 = j16 & 4294967295L;
                int i16 = i3 + 6;
                long j20 = (((long) iArr2[i16]) & 4294967295L) + (j18 >>> 32);
                long j21 = (j15 & 4294967295L) + (j2 * j17);
                int i17 = (int) j21;
                iArr2[i12] = (i17 << 1) | (i14 >>> 31);
                long j22 = (j21 >>> 32) + (j17 * j7) + j19;
                long j23 = (4294967295L & j18) + (j22 >>> 32) + (j17 * j11);
                long j24 = j20 + (j23 >>> 32);
                int i18 = (int) j22;
                iArr2[i13] = (i17 >>> 31) | (i18 << 1);
                int i19 = i18 >>> 31;
                int i20 = (int) j23;
                iArr2[i15] = i19 | (i20 << 1);
                int i21 = i20 >>> 31;
                int i22 = (int) j24;
                iArr2[i16] = i21 | (i22 << 1);
                int i23 = i22 >>> 31;
                int i24 = i3 + 7;
                iArr2[i24] = i23 | ((iArr2[i24] + ((int) (j24 >>> 32))) << 1);
                return;
            }
            i6 = i7;
        }
    }
}
