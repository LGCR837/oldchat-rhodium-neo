package org.spongycastle.math.raw;

import java.math.BigInteger;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public abstract class Nat224 {

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    private static final long f7586M = 4294967295L;

    public static int add(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L);
        iArr3[0] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L);
        iArr3[1] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L);
        iArr3[2] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L);
        iArr3[3] = (int) j5;
        long j6 = (j5 >>> 32) + (((long) iArr[4]) & 4294967295L) + (((long) iArr2[4]) & 4294967295L);
        iArr3[4] = (int) j6;
        long j7 = (j6 >>> 32) + (((long) iArr[5]) & 4294967295L) + (((long) iArr2[5]) & 4294967295L);
        iArr3[5] = (int) j7;
        long j8 = (j7 >>> 32) + (((long) iArr[6]) & 4294967295L) + (((long) iArr2[6]) & 4294967295L);
        iArr3[6] = (int) j8;
        return (int) (j8 >>> 32);
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
        long j6 = (j5 >>> 32) + (((long) iArr[4]) & 4294967295L) + (((long) iArr2[4]) & 4294967295L) + (((long) iArr3[4]) & 4294967295L);
        iArr3[4] = (int) j6;
        long j7 = (j6 >>> 32) + (((long) iArr[5]) & 4294967295L) + (((long) iArr2[5]) & 4294967295L) + (((long) iArr3[5]) & 4294967295L);
        iArr3[5] = (int) j7;
        long j8 = (j7 >>> 32) + (((long) iArr[6]) & 4294967295L) + (((long) iArr2[6]) & 4294967295L) + (((long) iArr3[6]) & 4294967295L);
        iArr3[6] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int addTo(int[] iArr, int[] iArr2) {
        long j2 = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L);
        iArr2[0] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L);
        iArr2[1] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L);
        iArr2[2] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L);
        iArr2[3] = (int) j5;
        long j6 = (j5 >>> 32) + (((long) iArr[4]) & 4294967295L) + (((long) iArr2[4]) & 4294967295L);
        iArr2[4] = (int) j6;
        long j7 = (j6 >>> 32) + (((long) iArr[5]) & 4294967295L) + (((long) iArr2[5]) & 4294967295L);
        iArr2[5] = (int) j7;
        long j8 = (j7 >>> 32) + (((long) iArr[6]) & 4294967295L) + (4294967295L & ((long) iArr2[6]));
        iArr2[6] = (int) j8;
        return (int) (j8 >>> 32);
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
        long j5 = (j4 >>> 32) + (((long) iArr[i11]) & 4294967295L) + (((long) iArr2[i12]) & 4294967295L);
        int i13 = (int) j5;
        iArr[i11] = i13;
        iArr2[i12] = i13;
        int i14 = i2 + 4;
        int i15 = i3 + 4;
        long j6 = (j5 >>> 32) + (((long) iArr[i14]) & 4294967295L) + (((long) iArr2[i15]) & 4294967295L);
        int i16 = (int) j6;
        iArr[i14] = i16;
        iArr2[i15] = i16;
        int i17 = i2 + 5;
        int i18 = i3 + 5;
        long j7 = (j6 >>> 32) + (((long) iArr[i17]) & 4294967295L) + (((long) iArr2[i18]) & 4294967295L);
        int i19 = (int) j7;
        iArr[i17] = i19;
        iArr2[i18] = i19;
        int i20 = i2 + 6;
        int i21 = i3 + 6;
        long j8 = (j7 >>> 32) + (((long) iArr[i20]) & 4294967295L) + (4294967295L & ((long) iArr2[i21]));
        int i22 = (int) j8;
        iArr[i20] = i22;
        iArr2[i21] = i22;
        return (int) (j8 >>> 32);
    }

    public static void copy(int[] iArr, int[] iArr2) {
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[1];
        iArr2[2] = iArr[2];
        iArr2[3] = iArr[3];
        iArr2[4] = iArr[4];
        iArr2[5] = iArr[5];
        iArr2[6] = iArr[6];
    }

    public static int[] create() {
        return new int[7];
    }

    public static int[] createExt() {
        return new int[14];
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
        for (int i2 = 6; i2 >= 0; i2--) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 224) {
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

    public static int getBit(int[] iArr, int i2) {
        int i3;
        if (i2 == 0) {
            i3 = iArr[0];
        } else {
            int i4 = i2 >> 5;
            if (i4 < 0 || i4 >= 7) {
                return 0;
            }
            i3 = iArr[i4] >>> (i2 & 31);
        }
        return i3 & 1;
    }

    public static boolean gte(int[] iArr, int[] iArr2) {
        for (int i2 = 6; i2 >= 0; i2--) {
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
        for (int i2 = 1; i2 < 7; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int[] iArr) {
        for (int i2 = 0; i2 < 7; i2++) {
            if (iArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = ((long) iArr2[0]) & 4294967295L;
        long j3 = ((long) iArr2[1]) & 4294967295L;
        long j4 = ((long) iArr2[2]) & 4294967295L;
        long j5 = ((long) iArr2[3]) & 4294967295L;
        long j6 = ((long) iArr2[4]) & 4294967295L;
        long j7 = ((long) iArr2[5]) & 4294967295L;
        long j8 = ((long) iArr2[6]) & 4294967295L;
        long j9 = ((long) iArr[0]) & 4294967295L;
        long j10 = j9 * j2;
        iArr3[0] = (int) j10;
        char c2 = ' ';
        long j11 = (j10 >>> 32) + (j9 * j3);
        iArr3[1] = (int) j11;
        long j12 = (j11 >>> 32) + (j9 * j4);
        iArr3[2] = (int) j12;
        long j13 = (j12 >>> 32) + (j9 * j5);
        iArr3[3] = (int) j13;
        long j14 = (j13 >>> 32) + (j9 * j6);
        iArr3[4] = (int) j14;
        long j15 = (j14 >>> 32) + (j9 * j7);
        iArr3[5] = (int) j15;
        long j16 = (j15 >>> 32) + (j9 * j8);
        iArr3[6] = (int) j16;
        int i2 = (int) (j16 >>> 32);
        iArr3[7] = i2;
        int i3 = 1;
        for (int i4 = 7; i3 < i4; i4 = 7) {
            long j17 = ((long) iArr[i3]) & 4294967295L;
            long j18 = (j17 * j2) + (((long) iArr3[i3]) & 4294967295L);
            long j19 = j2;
            iArr3[i3] = (int) j18;
            int i5 = i3 + 1;
            long j20 = j3;
            long j21 = (j18 >>> c2) + (j17 * j3) + (((long) iArr3[i5]) & 4294967295L);
            iArr3[i5] = (int) j21;
            int i6 = i3 + 2;
            long j22 = (j21 >>> 32) + (j17 * j4) + (((long) iArr3[i6]) & 4294967295L);
            iArr3[i6] = (int) j22;
            int i7 = i3 + 3;
            long j23 = (j22 >>> 32) + (j17 * j5) + (((long) iArr3[i7]) & 4294967295L);
            iArr3[i7] = (int) j23;
            int i8 = i3 + 4;
            long j24 = (j23 >>> 32) + (j17 * j6) + (((long) iArr3[i8]) & 4294967295L);
            iArr3[i8] = (int) j24;
            int i9 = i3 + 5;
            long j25 = (j24 >>> 32) + (j17 * j7) + (((long) iArr3[i9]) & 4294967295L);
            iArr3[i9] = (int) j25;
            c2 = ' ';
            int i10 = i3 + 6;
            long j26 = (j25 >>> 32) + (j17 * j8) + (((long) iArr3[i10]) & 4294967295L);
            iArr3[i10] = (int) j26;
            iArr3[i3 + 7] = (int) (j26 >>> 32);
            j2 = j19;
            i3 = i5;
            j3 = j20;
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
        long j11 = (j9 >>> 32) + (j2 * j10) + j8 + (((long) iArr2[i4 + 3]) & 4294967295L);
        iArr3[i5 + 3] = (int) j11;
        long j12 = ((long) iArr[i3 + 4]) & 4294967295L;
        long j13 = (j11 >>> 32) + (j2 * j12) + j10 + (((long) iArr2[i4 + 4]) & 4294967295L);
        iArr3[i5 + 4] = (int) j13;
        long j14 = ((long) iArr[i3 + 5]) & 4294967295L;
        long j15 = (j13 >>> 32) + (j2 * j14) + j12 + (((long) iArr2[i4 + 5]) & 4294967295L);
        iArr3[i5 + 5] = (int) j15;
        long j16 = ((long) iArr[i3 + 6]) & 4294967295L;
        long j17 = (j15 >>> 32) + (j2 * j16) + j14 + (4294967295L & ((long) iArr2[i4 + 6]));
        iArr3[i5 + 6] = (int) j17;
        return (j17 >>> 32) + j16;
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
        long j10 = j9 >>> 32;
        int i6 = i3 + 3;
        long j11 = j10 + (4294967295L & ((long) iArr[i6]));
        iArr[i6] = (int) j11;
        if ((j11 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(7, iArr, i3, 4);
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
        return Nat.incAt(7, iArr, i4, 3);
    }

    public static int mulAddTo(int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = 4294967295L;
        long j3 = ((long) iArr2[0]) & 4294967295L;
        long j4 = ((long) iArr2[1]) & 4294967295L;
        long j5 = ((long) iArr2[2]) & 4294967295L;
        long j6 = ((long) iArr2[3]) & 4294967295L;
        long j7 = ((long) iArr2[4]) & 4294967295L;
        long j8 = ((long) iArr2[5]) & 4294967295L;
        long j9 = ((long) iArr2[6]) & 4294967295L;
        long j10 = 0;
        int i2 = 0;
        while (i2 < 7) {
            long j11 = j9;
            long j12 = ((long) iArr[i2]) & j2;
            long j13 = j8;
            long j14 = (((long) iArr3[i2]) & j2) + (j12 * j3);
            iArr3[i2] = (int) j14;
            int i3 = i2 + 1;
            long j15 = j4;
            long j16 = (j14 >>> 32) + (j12 * j4) + (((long) iArr3[i3]) & j2);
            iArr3[i3] = (int) j16;
            int i4 = i2 + 2;
            long j17 = (j16 >>> 32) + (j12 * j5) + (((long) iArr3[i4]) & j2);
            iArr3[i4] = (int) j17;
            int i5 = i2 + 3;
            long j18 = (j17 >>> 32) + (j12 * j6) + (((long) iArr3[i5]) & j2);
            iArr3[i5] = (int) j18;
            int i6 = i2 + 4;
            long j19 = (j18 >>> 32) + (j12 * j7) + (((long) iArr3[i6]) & j2);
            iArr3[i6] = (int) j19;
            int i7 = i2 + 5;
            long j20 = (j19 >>> 32) + (j12 * j13) + (((long) iArr3[i7]) & j2);
            iArr3[i7] = (int) j20;
            int i8 = i2 + 6;
            long j21 = (j20 >>> 32) + (j12 * j11) + (((long) iArr3[i8]) & j2);
            iArr3[i8] = (int) j21;
            int i9 = i2 + 7;
            long j22 = (j21 >>> 32) + j10 + (((long) iArr3[i9]) & j2);
            iArr3[i9] = (int) j22;
            j10 = j22 >>> 32;
            i2 = i3;
            j9 = j11;
            j8 = j13;
            j4 = j15;
            j2 = 4294967295L;
        }
        return (int) j10;
    }

    public static int mulByWord(int i2, int[] iArr) {
        long j2 = ((long) i2) & 4294967295L;
        long j3 = (((long) iArr[0]) & 4294967295L) * j2;
        iArr[0] = (int) j3;
        long j4 = (j3 >>> 32) + ((((long) iArr[1]) & 4294967295L) * j2);
        iArr[1] = (int) j4;
        long j5 = (j4 >>> 32) + ((((long) iArr[2]) & 4294967295L) * j2);
        iArr[2] = (int) j5;
        long j6 = (j5 >>> 32) + ((((long) iArr[3]) & 4294967295L) * j2);
        iArr[3] = (int) j6;
        long j7 = (j6 >>> 32) + ((((long) iArr[4]) & 4294967295L) * j2);
        iArr[4] = (int) j7;
        long j8 = (j7 >>> 32) + ((((long) iArr[5]) & 4294967295L) * j2);
        iArr[5] = (int) j8;
        long j9 = (j8 >>> 32) + (j2 * (4294967295L & ((long) iArr[6])));
        iArr[6] = (int) j9;
        return (int) (j9 >>> 32);
    }

    public static int mulByWordAddTo(int i2, int[] iArr, int[] iArr2) {
        long j2 = ((long) i2) & 4294967295L;
        long j3 = ((((long) iArr2[0]) & 4294967295L) * j2) + (((long) iArr[0]) & 4294967295L);
        iArr2[0] = (int) j3;
        long j4 = (j3 >>> 32) + ((((long) iArr2[1]) & 4294967295L) * j2) + (((long) iArr[1]) & 4294967295L);
        iArr2[1] = (int) j4;
        long j5 = (j4 >>> 32) + ((((long) iArr2[2]) & 4294967295L) * j2) + (((long) iArr[2]) & 4294967295L);
        iArr2[2] = (int) j5;
        long j6 = (j5 >>> 32) + ((((long) iArr2[3]) & 4294967295L) * j2) + (((long) iArr[3]) & 4294967295L);
        iArr2[3] = (int) j6;
        long j7 = (j6 >>> 32) + ((((long) iArr2[4]) & 4294967295L) * j2) + (((long) iArr[4]) & 4294967295L);
        iArr2[4] = (int) j7;
        long j8 = (j7 >>> 32) + ((((long) iArr2[5]) & 4294967295L) * j2) + (((long) iArr[5]) & 4294967295L);
        iArr2[5] = (int) j8;
        long j9 = (j8 >>> 32) + (j2 * (((long) iArr2[6]) & 4294967295L)) + (4294967295L & ((long) iArr[6]));
        iArr2[6] = (int) j9;
        return (int) (j9 >>> 32);
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
        } while (i4 < 7);
        return (int) j3;
    }

    public static int mulWordAddTo(int i2, int[] iArr, int i3, int[] iArr2, int i4) {
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
        long j6 = (j5 >>> 32) + ((((long) iArr[i3 + 3]) & 4294967295L) * j2) + (((long) iArr2[i7]) & 4294967295L);
        iArr2[i7] = (int) j6;
        int i8 = i4 + 4;
        long j7 = (j6 >>> 32) + ((((long) iArr[i3 + 4]) & 4294967295L) * j2) + (((long) iArr2[i8]) & 4294967295L);
        iArr2[i8] = (int) j7;
        int i9 = i4 + 5;
        long j8 = (j7 >>> 32) + ((((long) iArr[i3 + 5]) & 4294967295L) * j2) + (((long) iArr2[i9]) & 4294967295L);
        iArr2[i9] = (int) j8;
        int i10 = i4 + 6;
        long j9 = (j8 >>> 32) + (j2 * (((long) iArr[i3 + 6]) & 4294967295L)) + (((long) iArr2[i10]) & 4294967295L);
        iArr2[i10] = (int) j9;
        return (int) (j9 >>> 32);
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
        return Nat.incAt(7, iArr, i3, 3);
    }

    public static void square(int[] iArr, int[] iArr2) {
        long j2 = ((long) iArr[0]) & 4294967295L;
        int i2 = 14;
        int i3 = 6;
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
                long j19 = j16 & 4294967295L;
                long j20 = (((long) iArr2[6]) & 4294967295L) + (j18 >>> 32);
                long j21 = (j15 & 4294967295L) + (j17 * j2);
                int i9 = (int) j21;
                iArr2[3] = (i8 >>> 31) | (i9 << 1);
                int i10 = i9 >>> 31;
                long j22 = j19 + (j21 >>> 32) + (j17 * j7);
                long j23 = (j18 & 4294967295L) + (j22 >>> 32) + (j17 * j11);
                long j24 = j20 + (j23 >>> 32);
                long j25 = ((long) iArr[4]) & 4294967295L;
                long j26 = (((long) iArr2[7]) & 4294967295L) + (j24 >>> 32);
                long j27 = j24 & 4294967295L;
                long j28 = (((long) iArr2[8]) & 4294967295L) + (j26 >>> 32);
                long j29 = (j22 & 4294967295L) + (j25 * j2);
                int i11 = (int) j29;
                iArr2[4] = (i11 << 1) | i10;
                long j30 = (j23 & 4294967295L) + (j29 >>> 32) + (j25 * j7);
                long j31 = j27 + (j30 >>> 32) + (j25 * j11);
                long j32 = (j26 & 4294967295L) + (j31 >>> 32) + (j25 * j17);
                long j33 = j28 + (j32 >>> 32);
                long j34 = j32 & 4294967295L;
                long j35 = ((long) iArr[5]) & 4294967295L;
                long j36 = (((long) iArr2[9]) & 4294967295L) + (j33 >>> 32);
                long j37 = j33 & 4294967295L;
                long j38 = (((long) iArr2[10]) & 4294967295L) + (j36 >>> 32);
                long j39 = (j30 & 4294967295L) + (j35 * j2);
                int i12 = (int) j39;
                iArr2[5] = (i12 << 1) | (i11 >>> 31);
                long j40 = (j31 & 4294967295L) + (j39 >>> 32) + (j35 * j7);
                long j41 = j34 + (j40 >>> 32) + (j35 * j11);
                long j42 = j37 + (j41 >>> 32) + (j35 * j17);
                long j43 = (j36 & 4294967295L) + (j42 >>> 32) + (j35 * j25);
                long j44 = j38 + (j43 >>> 32);
                long j45 = j43 & 4294967295L;
                long j46 = ((long) iArr[6]) & 4294967295L;
                long j47 = (((long) iArr2[11]) & 4294967295L) + (j44 >>> 32);
                long j48 = j44 & 4294967295L;
                long j49 = (((long) iArr2[12]) & 4294967295L) + (j47 >>> 32);
                long j50 = 4294967295L & j47;
                long j51 = (j40 & 4294967295L) + (j2 * j46);
                int i13 = (int) j51;
                iArr2[6] = (i12 >>> 31) | (i13 << 1);
                int i14 = i13 >>> 31;
                long j52 = (j41 & 4294967295L) + (j51 >>> 32) + (j46 * j7);
                long j53 = (j42 & 4294967295L) + (j52 >>> 32) + (j46 * j11);
                long j54 = j45 + (j53 >>> 32) + (j46 * j17);
                long j55 = j48 + (j54 >>> 32) + (j46 * j25);
                long j56 = j50 + (j55 >>> 32) + (j46 * j35);
                long j57 = j49 + (j56 >>> 32);
                int i15 = (int) j52;
                iArr2[7] = i14 | (i15 << 1);
                int i16 = (int) j53;
                iArr2[8] = (i15 >>> 31) | (i16 << 1);
                int i17 = (int) j54;
                iArr2[9] = (i16 >>> 31) | (i17 << 1);
                int i18 = i17 >>> 31;
                int i19 = (int) j55;
                iArr2[10] = i18 | (i19 << 1);
                int i20 = i19 >>> 31;
                int i21 = (int) j56;
                iArr2[11] = i20 | (i21 << 1);
                int i22 = i21 >>> 31;
                int i23 = (int) j57;
                iArr2[12] = i22 | (i23 << 1);
                iArr2[13] = (i23 >>> 31) | ((iArr2[13] + ((int) (j57 >>> 32))) << 1);
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
        long j6 = (j5 >> 32) + ((((long) iArr[4]) & 4294967295L) - (((long) iArr2[4]) & 4294967295L));
        iArr3[4] = (int) j6;
        long j7 = (j6 >> 32) + ((((long) iArr[5]) & 4294967295L) - (((long) iArr2[5]) & 4294967295L));
        iArr3[5] = (int) j7;
        long j8 = (j7 >> 32) + ((((long) iArr[6]) & 4294967295L) - (((long) iArr2[6]) & 4294967295L));
        iArr3[6] = (int) j8;
        return (int) (j8 >> 32);
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
        long j6 = (j5 >> 32) + (((((long) iArr3[4]) & 4294967295L) - (((long) iArr[4]) & 4294967295L)) - (((long) iArr2[4]) & 4294967295L));
        iArr3[4] = (int) j6;
        long j7 = (j6 >> 32) + (((((long) iArr3[5]) & 4294967295L) - (((long) iArr[5]) & 4294967295L)) - (((long) iArr2[5]) & 4294967295L));
        iArr3[5] = (int) j7;
        long j8 = (j7 >> 32) + (((((long) iArr3[6]) & 4294967295L) - (((long) iArr[6]) & 4294967295L)) - (((long) iArr2[6]) & 4294967295L));
        iArr3[6] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int subFrom(int[] iArr, int[] iArr2) {
        long j2 = (((long) iArr2[0]) & 4294967295L) - (((long) iArr[0]) & 4294967295L);
        iArr2[0] = (int) j2;
        long j3 = (j2 >> 32) + ((((long) iArr2[1]) & 4294967295L) - (((long) iArr[1]) & 4294967295L));
        iArr2[1] = (int) j3;
        long j4 = (j3 >> 32) + ((((long) iArr2[2]) & 4294967295L) - (((long) iArr[2]) & 4294967295L));
        iArr2[2] = (int) j4;
        long j5 = (j4 >> 32) + ((((long) iArr2[3]) & 4294967295L) - (((long) iArr[3]) & 4294967295L));
        iArr2[3] = (int) j5;
        long j6 = (j5 >> 32) + ((((long) iArr2[4]) & 4294967295L) - (((long) iArr[4]) & 4294967295L));
        iArr2[4] = (int) j6;
        long j7 = (j6 >> 32) + ((((long) iArr2[5]) & 4294967295L) - (((long) iArr[5]) & 4294967295L));
        iArr2[5] = (int) j7;
        long j8 = (j7 >> 32) + ((((long) iArr2[6]) & 4294967295L) - (4294967295L & ((long) iArr[6])));
        iArr2[6] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static BigInteger toBigInteger(int[] iArr) {
        byte[] bArr = new byte[28];
        for (int i2 = 0; i2 < 7; i2++) {
            int i3 = iArr[i2];
            if (i3 != 0) {
                Pack.intToBigEndian(i3, bArr, (6 - i2) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void zero(int[] iArr) {
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        iArr[5] = 0;
        iArr[6] = 0;
    }

    public static boolean gte(int[] iArr, int i2, int[] iArr2, int i3) {
        for (int i4 = 6; i4 >= 0; i4--) {
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

    public static int add(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j2 = (((long) iArr[i2]) & 4294967295L) + (((long) iArr2[i3]) & 4294967295L);
        iArr3[i4] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[i2 + 1]) & 4294967295L) + (((long) iArr2[i3 + 1]) & 4294967295L);
        iArr3[i4 + 1] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[i2 + 2]) & 4294967295L) + (((long) iArr2[i3 + 2]) & 4294967295L);
        iArr3[i4 + 2] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[i2 + 3]) & 4294967295L) + (((long) iArr2[i3 + 3]) & 4294967295L);
        iArr3[i4 + 3] = (int) j5;
        long j6 = (j5 >>> 32) + (((long) iArr[i2 + 4]) & 4294967295L) + (((long) iArr2[i3 + 4]) & 4294967295L);
        iArr3[i4 + 4] = (int) j6;
        long j7 = (j6 >>> 32) + (((long) iArr[i2 + 5]) & 4294967295L) + (((long) iArr2[i3 + 5]) & 4294967295L);
        iArr3[i4 + 5] = (int) j7;
        long j8 = (j7 >>> 32) + (((long) iArr[i2 + 6]) & 4294967295L) + (((long) iArr2[i3 + 6]) & 4294967295L);
        iArr3[i4 + 6] = (int) j8;
        return (int) (j8 >>> 32);
    }

    public static int addBothTo(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j2 = (((long) iArr[i2]) & 4294967295L) + (((long) iArr2[i3]) & 4294967295L) + (((long) iArr3[i4]) & 4294967295L);
        iArr3[i4] = (int) j2;
        int i5 = i4 + 1;
        long j3 = (j2 >>> 32) + (((long) iArr[i2 + 1]) & 4294967295L) + (((long) iArr2[i3 + 1]) & 4294967295L) + (((long) iArr3[i5]) & 4294967295L);
        iArr3[i5] = (int) j3;
        int i6 = i4 + 2;
        long j4 = (j3 >>> 32) + (((long) iArr[i2 + 2]) & 4294967295L) + (((long) iArr2[i3 + 2]) & 4294967295L) + (((long) iArr3[i6]) & 4294967295L);
        iArr3[i6] = (int) j4;
        int i7 = i4 + 3;
        long j5 = (j4 >>> 32) + (((long) iArr[i2 + 3]) & 4294967295L) + (((long) iArr2[i3 + 3]) & 4294967295L) + (((long) iArr3[i7]) & 4294967295L);
        iArr3[i7] = (int) j5;
        int i8 = i4 + 4;
        long j6 = (j5 >>> 32) + (((long) iArr[i2 + 4]) & 4294967295L) + (((long) iArr2[i3 + 4]) & 4294967295L) + (((long) iArr3[i8]) & 4294967295L);
        iArr3[i8] = (int) j6;
        int i9 = i4 + 5;
        long j7 = (j6 >>> 32) + (((long) iArr[i2 + 5]) & 4294967295L) + (((long) iArr2[i3 + 5]) & 4294967295L) + (((long) iArr3[i9]) & 4294967295L);
        iArr3[i9] = (int) j7;
        int i10 = i4 + 6;
        long j8 = (j7 >>> 32) + (((long) iArr[i2 + 6]) & 4294967295L) + (((long) iArr2[i3 + 6]) & 4294967295L) + (((long) iArr3[i10]) & 4294967295L);
        iArr3[i10] = (int) j8;
        return (int) (j8 >>> 32);
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
        long j5 = (j4 >>> 32) + (((long) iArr[i2 + 3]) & 4294967295L) + (((long) iArr2[i7]) & 4294967295L);
        iArr2[i7] = (int) j5;
        int i8 = i3 + 4;
        long j6 = (j5 >>> 32) + (((long) iArr[i2 + 4]) & 4294967295L) + (((long) iArr2[i8]) & 4294967295L);
        iArr2[i8] = (int) j6;
        int i9 = i3 + 5;
        long j7 = (j6 >>> 32) + (((long) iArr[i2 + 5]) & 4294967295L) + (((long) iArr2[i9]) & 4294967295L);
        iArr2[i9] = (int) j7;
        int i10 = i3 + 6;
        long j8 = (j7 >>> 32) + (((long) iArr[i2 + 6]) & 4294967295L) + (4294967295L & ((long) iArr2[i10]));
        iArr2[i10] = (int) j8;
        return (int) (j8 >>> 32);
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
        long j6 = (j5 >> 32) + ((((long) iArr[i2 + 4]) & 4294967295L) - (((long) iArr2[i3 + 4]) & 4294967295L));
        iArr3[i4 + 4] = (int) j6;
        long j7 = (j6 >> 32) + ((((long) iArr[i2 + 5]) & 4294967295L) - (((long) iArr2[i3 + 5]) & 4294967295L));
        iArr3[i4 + 5] = (int) j7;
        long j8 = (j7 >> 32) + ((((long) iArr[i2 + 6]) & 4294967295L) - (((long) iArr2[i3 + 6]) & 4294967295L));
        iArr3[i4 + 6] = (int) j8;
        return (int) (j8 >> 32);
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
        int i7 = i3 + 4;
        long j6 = (j5 >> 32) + ((((long) iArr2[i7]) & 4294967295L) - (((long) iArr[i2 + 4]) & 4294967295L));
        iArr2[i7] = (int) j6;
        int i8 = i3 + 5;
        long j7 = (j6 >> 32) + ((((long) iArr2[i8]) & 4294967295L) - (((long) iArr[i2 + 5]) & 4294967295L));
        iArr2[i8] = (int) j7;
        int i9 = i3 + 6;
        long j8 = (j7 >> 32) + ((((long) iArr2[i9]) & 4294967295L) - (((long) iArr[i2 + 6]) & 4294967295L));
        iArr2[i9] = (int) j8;
        return (int) (j8 >> 32);
    }

    public static int mulAddTo(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j2 = ((long) iArr2[i3]) & 4294967295L;
        long j3 = ((long) iArr2[i3 + 1]) & 4294967295L;
        long j4 = ((long) iArr2[i3 + 2]) & 4294967295L;
        long j5 = ((long) iArr2[i3 + 3]) & 4294967295L;
        long j6 = ((long) iArr2[i3 + 4]) & 4294967295L;
        long j7 = ((long) iArr2[i3 + 5]) & 4294967295L;
        long j8 = ((long) iArr2[i3 + 6]) & 4294967295L;
        long j9 = 0;
        int i5 = 0;
        int i6 = i4;
        while (i5 < 7) {
            int i7 = i5;
            long j10 = ((long) iArr[i2 + i5]) & 4294967295L;
            long j11 = j2;
            long j12 = (j10 * j2) + (((long) iArr3[i6]) & 4294967295L);
            long j13 = j8;
            iArr3[i6] = (int) j12;
            int i8 = i6 + 1;
            long j14 = (j12 >>> 32) + (j10 * j3) + (((long) iArr3[i8]) & 4294967295L);
            iArr3[i8] = (int) j14;
            int i9 = i6 + 2;
            long j15 = (j14 >>> 32) + (j10 * j4) + (((long) iArr3[i9]) & 4294967295L);
            iArr3[i9] = (int) j15;
            int i10 = i6 + 3;
            long j16 = (j15 >>> 32) + (j10 * j5) + (((long) iArr3[i10]) & 4294967295L);
            iArr3[i10] = (int) j16;
            int i11 = i6 + 4;
            long j17 = (j16 >>> 32) + (j10 * j6) + (((long) iArr3[i11]) & 4294967295L);
            iArr3[i11] = (int) j17;
            int i12 = i6 + 5;
            long j18 = (j17 >>> 32) + (j10 * j7) + (((long) iArr3[i12]) & 4294967295L);
            iArr3[i12] = (int) j18;
            int i13 = i6 + 6;
            long j19 = (j18 >>> 32) + (j10 * j13) + (((long) iArr3[i13]) & 4294967295L);
            iArr3[i13] = (int) j19;
            int i14 = i6 + 7;
            long j20 = (j19 >>> 32) + j9 + (((long) iArr3[i14]) & 4294967295L);
            iArr3[i14] = (int) j20;
            j9 = j20 >>> 32;
            i5 = i7 + 1;
            i6 = i8;
            j8 = j13;
            j2 = j11;
            j4 = j4;
            j3 = j3;
        }
        return (int) j9;
    }

    public static void mul(int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j2 = ((long) iArr2[i3]) & 4294967295L;
        long j3 = ((long) iArr2[i3 + 1]) & 4294967295L;
        long j4 = ((long) iArr2[i3 + 2]) & 4294967295L;
        long j5 = ((long) iArr2[i3 + 3]) & 4294967295L;
        long j6 = ((long) iArr2[i3 + 4]) & 4294967295L;
        long j7 = ((long) iArr2[i3 + 5]) & 4294967295L;
        long j8 = ((long) iArr2[i3 + 6]) & 4294967295L;
        long j9 = ((long) iArr[i2]) & 4294967295L;
        long j10 = j9 * j2;
        iArr3[i4] = (int) j10;
        long j11 = (j10 >>> 32) + (j9 * j3);
        iArr3[i4 + 1] = (int) j11;
        long j12 = (j11 >>> 32) + (j9 * j4);
        iArr3[i4 + 2] = (int) j12;
        long j13 = (j12 >>> 32) + (j9 * j5);
        iArr3[i4 + 3] = (int) j13;
        long j14 = (j13 >>> 32) + (j9 * j6);
        iArr3[i4 + 4] = (int) j14;
        long j15 = (j14 >>> 32) + (j9 * j7);
        iArr3[i4 + 5] = (int) j15;
        long j16 = j8;
        long j17 = (j15 >>> 32) + (j9 * j16);
        iArr3[i4 + 6] = (int) j17;
        iArr3[i4 + 7] = (int) (j17 >>> 32);
        int i5 = i4;
        int i6 = 1;
        while (i6 < 7) {
            int i7 = i5 + 1;
            long j18 = ((long) iArr[i2 + i6]) & 4294967295L;
            long j19 = j16;
            int i8 = i6;
            long j20 = (j18 * j2) + (((long) iArr3[i7]) & 4294967295L);
            iArr3[i7] = (int) j20;
            int i9 = i5 + 2;
            long j21 = (j20 >>> 32) + (j18 * j3) + (((long) iArr3[i9]) & 4294967295L);
            iArr3[i9] = (int) j21;
            int i10 = i5 + 3;
            long j22 = j4;
            long j23 = (j21 >>> 32) + (j18 * j4) + (((long) iArr3[i10]) & 4294967295L);
            iArr3[i10] = (int) j23;
            int i11 = i5 + 4;
            int i12 = i5;
            long j24 = (j23 >>> 32) + (j18 * j5) + (((long) iArr3[i11]) & 4294967295L);
            iArr3[i11] = (int) j24;
            int i13 = i12 + 5;
            long j25 = (j24 >>> 32) + (j18 * j6) + (((long) iArr3[i13]) & 4294967295L);
            iArr3[i13] = (int) j25;
            int i14 = i12 + 6;
            long j26 = (j25 >>> 32) + (j18 * j7) + (((long) iArr3[i14]) & 4294967295L);
            iArr3[i14] = (int) j26;
            int i15 = i12 + 7;
            long j27 = (j26 >>> 32) + (j18 * j19) + (((long) iArr3[i15]) & 4294967295L);
            iArr3[i15] = (int) j27;
            iArr3[i12 + 8] = (int) (j27 >>> 32);
            i6 = i8 + 1;
            i5 = i7;
            j16 = j19;
            j4 = j22;
        }
    }

    public static void square(int[] iArr, int i2, int[] iArr2, int i3) {
        long j2 = ((long) iArr[i2]) & 4294967295L;
        int i4 = 0;
        int i5 = 14;
        int i6 = 6;
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
                long j21 = (j15 & 4294967295L) + (j17 * j2);
                int i17 = (int) j21;
                iArr2[i12] = (i17 << 1) | (i14 >>> 31);
                long j22 = j19 + (j21 >>> 32) + (j17 * j7);
                long j23 = (j18 & 4294967295L) + (j22 >>> 32) + (j17 * j11);
                long j24 = j20 + (j23 >>> 32);
                long j25 = ((long) iArr[i2 + 4]) & 4294967295L;
                int i18 = i3 + 7;
                long j26 = (((long) iArr2[i18]) & 4294967295L) + (j24 >>> 32);
                int i19 = i3 + 8;
                long j27 = (((long) iArr2[i19]) & 4294967295L) + (j26 >>> 32);
                long j28 = (j22 & 4294967295L) + (j25 * j2);
                int i20 = (int) j28;
                iArr2[i13] = (i20 << 1) | (i17 >>> 31);
                int i21 = i20 >>> 31;
                long j29 = (j23 & 4294967295L) + (j28 >>> 32) + (j25 * j7);
                long j30 = (j24 & 4294967295L) + (j29 >>> 32) + (j25 * j11);
                long j31 = (j26 & 4294967295L) + (j30 >>> 32) + (j25 * j17);
                long j32 = j27 + (j31 >>> 32);
                long j33 = ((long) iArr[i2 + 5]) & 4294967295L;
                int i22 = i3 + 9;
                long j34 = (((long) iArr2[i22]) & 4294967295L) + (j32 >>> 32);
                long j35 = j32 & 4294967295L;
                int i23 = i3 + 10;
                long j36 = (((long) iArr2[i23]) & 4294967295L) + (j34 >>> 32);
                long j37 = (j29 & 4294967295L) + (j33 * j2);
                int i24 = (int) j37;
                iArr2[i15] = (i24 << 1) | i21;
                long j38 = (j30 & 4294967295L) + (j37 >>> 32) + (j33 * j7);
                long j39 = (j31 & 4294967295L) + (j38 >>> 32) + (j33 * j11);
                long j40 = j35 + (j39 >>> 32) + (j33 * j17);
                long j41 = (j34 & 4294967295L) + (j40 >>> 32) + (j33 * j25);
                long j42 = j36 + (j41 >>> 32);
                long j43 = j41 & 4294967295L;
                long j44 = ((long) iArr[i2 + 6]) & 4294967295L;
                int i25 = i3 + 11;
                long j45 = (((long) iArr2[i25]) & 4294967295L) + (j42 >>> 32);
                long j46 = j42 & 4294967295L;
                int i26 = i3 + 12;
                long j47 = (((long) iArr2[i26]) & 4294967295L) + (j45 >>> 32);
                long j48 = (j38 & 4294967295L) + (j2 * j44);
                int i27 = (int) j48;
                iArr2[i16] = (i27 << 1) | (i24 >>> 31);
                long j49 = (j39 & 4294967295L) + (j48 >>> 32) + (j44 * j7);
                long j50 = (j40 & 4294967295L) + (j49 >>> 32) + (j44 * j11);
                long j51 = j43 + (j50 >>> 32) + (j44 * j17);
                long j52 = j46 + (j51 >>> 32) + (j44 * j25);
                long j53 = (j45 & 4294967295L) + (j52 >>> 32) + (j44 * j33);
                long j54 = j47 + (j53 >>> 32);
                int i28 = (int) j49;
                iArr2[i18] = (i27 >>> 31) | (i28 << 1);
                int i29 = (int) j50;
                iArr2[i19] = (i28 >>> 31) | (i29 << 1);
                int i30 = i29 >>> 31;
                int i31 = (int) j51;
                iArr2[i22] = i30 | (i31 << 1);
                int i32 = (int) j52;
                iArr2[i23] = (i31 >>> 31) | (i32 << 1);
                int i33 = i32 >>> 31;
                int i34 = (int) j53;
                iArr2[i25] = i33 | (i34 << 1);
                int i35 = i34 >>> 31;
                int i36 = (int) j54;
                iArr2[i26] = i35 | (i36 << 1);
                int i37 = i36 >>> 31;
                int i38 = i3 + 13;
                iArr2[i38] = i37 | ((iArr2[i38] + ((int) (j54 >>> 32))) << 1);
                return;
            }
            i6 = i7;
        }
    }
}
