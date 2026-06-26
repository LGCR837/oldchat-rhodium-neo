package org.spongycastle.math.ec.custom.gm;

import java.math.BigInteger;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat256;

/* JADX INFO: loaded from: classes.dex */
public class SM2P256V1Field {

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    private static final long f7516M = 4294967295L;
    private static final int P7s1 = Integer.MAX_VALUE;
    private static final int PExt15s1 = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    static final int[] f7517P = {-1, -1, 0, -1, -1, -1, -1, -2};
    static final int[] PExt = {1, 0, -2, 1, 1, -2, 0, 2, -2, -3, 3, -2, -1, -1, 0, -2};

    public static void add(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat256.add(iArr, iArr2, iArr3) != 0 || ((iArr3[7] >>> 1) >= Integer.MAX_VALUE && Nat256.gte(iArr3, f7517P))) {
            addPInvTo(iArr3);
        }
    }

    public static void addExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.add(16, iArr, iArr2, iArr3) != 0 || ((iArr3[15] >>> 1) >= Integer.MAX_VALUE && Nat.gte(16, iArr3, PExt))) {
            Nat.subFrom(16, PExt, iArr3);
        }
    }

    public static void addOne(int[] iArr, int[] iArr2) {
        if (Nat.inc(8, iArr, iArr2) != 0 || ((iArr2[7] >>> 1) >= Integer.MAX_VALUE && Nat256.gte(iArr2, f7517P))) {
            addPInvTo(iArr2);
        }
    }

    private static void addPInvTo(int[] iArr) {
        long j2 = (((long) iArr[0]) & 4294967295L) + 1;
        iArr[0] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            long j4 = j3 + (((long) iArr[1]) & 4294967295L);
            iArr[1] = (int) j4;
            j3 = j4 >> 32;
        }
        long j5 = j3 + ((((long) iArr[2]) & 4294967295L) - 1);
        iArr[2] = (int) j5;
        long j6 = (j5 >> 32) + (((long) iArr[3]) & 4294967295L) + 1;
        iArr[3] = (int) j6;
        long j7 = j6 >> 32;
        if (j7 != 0) {
            long j8 = j7 + (((long) iArr[4]) & 4294967295L);
            iArr[4] = (int) j8;
            long j9 = (j8 >> 32) + (((long) iArr[5]) & 4294967295L);
            iArr[5] = (int) j9;
            long j10 = (j9 >> 32) + (((long) iArr[6]) & 4294967295L);
            iArr[6] = (int) j10;
            j7 = j10 >> 32;
        }
        iArr[7] = (int) (j7 + (4294967295L & ((long) iArr[7])) + 1);
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] iArrFromBigInteger = Nat256.fromBigInteger(bigInteger);
        if ((iArrFromBigInteger[7] >>> 1) >= Integer.MAX_VALUE) {
            int[] iArr = f7517P;
            if (Nat256.gte(iArrFromBigInteger, iArr)) {
                Nat256.subFrom(iArr, iArrFromBigInteger);
            }
        }
        return iArrFromBigInteger;
    }

    public static void half(int[] iArr, int[] iArr2) {
        if ((iArr[0] & 1) == 0) {
            Nat.shiftDownBit(8, iArr, 0, iArr2);
        } else {
            Nat.shiftDownBit(8, iArr2, Nat256.add(iArr, f7517P, iArr2));
        }
    }

    public static void multiply(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArrCreateExt = Nat256.createExt();
        Nat256.mul(iArr, iArr2, iArrCreateExt);
        reduce(iArrCreateExt, iArr3);
    }

    public static void multiplyAddToExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat256.mulAddTo(iArr, iArr2, iArr3) != 0 || ((iArr3[15] >>> 1) >= Integer.MAX_VALUE && Nat.gte(16, iArr3, PExt))) {
            Nat.subFrom(16, PExt, iArr3);
        }
    }

    public static void negate(int[] iArr, int[] iArr2) {
        if (Nat256.isZero(iArr)) {
            Nat256.zero(iArr2);
        } else {
            Nat256.sub(f7517P, iArr, iArr2);
        }
    }

    public static void reduce(int[] iArr, int[] iArr2) {
        long j2 = ((long) iArr[8]) & 4294967295L;
        long j3 = ((long) iArr[9]) & 4294967295L;
        long j4 = ((long) iArr[10]) & 4294967295L;
        long j5 = ((long) iArr[11]) & 4294967295L;
        long j6 = ((long) iArr[12]) & 4294967295L;
        long j7 = ((long) iArr[13]) & 4294967295L;
        long j8 = ((long) iArr[14]) & 4294967295L;
        long j9 = ((long) iArr[15]) & 4294967295L;
        long j10 = j4 + j5;
        long j11 = j7 + j8;
        long j12 = j11 + (j9 << 1);
        long j13 = j2 + j3 + j11;
        long j14 = j10 + j6 + j9 + j13;
        long j15 = (((long) iArr[0]) & 4294967295L) + j14 + j7 + j8 + j9;
        iArr2[0] = (int) j15;
        long j16 = (j15 >> 32) + (((((long) iArr[1]) & 4294967295L) + j14) - j2) + j8 + j9;
        iArr2[1] = (int) j16;
        long j17 = (j16 >> 32) + ((((long) iArr[2]) & 4294967295L) - j13);
        iArr2[2] = (int) j17;
        long j18 = (j17 >> 32) + ((((((long) iArr[3]) & 4294967295L) + j14) - j3) - j4) + j7;
        iArr2[3] = (int) j18;
        long j19 = (j18 >> 32) + ((((((long) iArr[4]) & 4294967295L) + j14) - j10) - j2) + j8;
        iArr2[4] = (int) j19;
        long j20 = (j19 >> 32) + (((long) iArr[5]) & 4294967295L) + j12 + j4;
        iArr2[5] = (int) j20;
        long j21 = (j20 >> 32) + (((long) iArr[6]) & 4294967295L) + j5 + j8 + j9;
        iArr2[6] = (int) j21;
        long j22 = (j21 >> 32) + (4294967295L & ((long) iArr[7])) + j14 + j12 + j6;
        iArr2[7] = (int) j22;
        reduce32((int) (j22 >> 32), iArr2);
    }

    public static void reduce32(int i2, int[] iArr) {
        long j2;
        if (i2 != 0) {
            long j3 = ((long) i2) & 4294967295L;
            long j4 = (((long) iArr[0]) & 4294967295L) + j3;
            iArr[0] = (int) j4;
            long j5 = j4 >> 32;
            if (j5 != 0) {
                long j6 = j5 + (((long) iArr[1]) & 4294967295L);
                iArr[1] = (int) j6;
                j5 = j6 >> 32;
            }
            long j7 = j5 + ((((long) iArr[2]) & 4294967295L) - j3);
            iArr[2] = (int) j7;
            long j8 = (j7 >> 32) + (((long) iArr[3]) & 4294967295L) + j3;
            iArr[3] = (int) j8;
            long j9 = j8 >> 32;
            if (j9 != 0) {
                long j10 = j9 + (((long) iArr[4]) & 4294967295L);
                iArr[4] = (int) j10;
                long j11 = (j10 >> 32) + (((long) iArr[5]) & 4294967295L);
                iArr[5] = (int) j11;
                long j12 = (j11 >> 32) + (((long) iArr[6]) & 4294967295L);
                iArr[6] = (int) j12;
                j9 = j12 >> 32;
            }
            long j13 = j9 + (4294967295L & ((long) iArr[7])) + j3;
            iArr[7] = (int) j13;
            j2 = j13 >> 32;
        } else {
            j2 = 0;
        }
        if (j2 != 0 || ((iArr[7] >>> 1) >= Integer.MAX_VALUE && Nat256.gte(iArr, f7517P))) {
            addPInvTo(iArr);
        }
    }

    public static void square(int[] iArr, int[] iArr2) {
        int[] iArrCreateExt = Nat256.createExt();
        Nat256.square(iArr, iArrCreateExt);
        reduce(iArrCreateExt, iArr2);
    }

    public static void squareN(int[] iArr, int i2, int[] iArr2) {
        int[] iArrCreateExt = Nat256.createExt();
        Nat256.square(iArr, iArrCreateExt);
        reduce(iArrCreateExt, iArr2);
        while (true) {
            i2--;
            if (i2 <= 0) {
                return;
            }
            Nat256.square(iArr2, iArrCreateExt);
            reduce(iArrCreateExt, iArr2);
        }
    }

    private static void subPInvFrom(int[] iArr) {
        long j2 = (((long) iArr[0]) & 4294967295L) - 1;
        iArr[0] = (int) j2;
        long j3 = j2 >> 32;
        if (j3 != 0) {
            long j4 = j3 + (((long) iArr[1]) & 4294967295L);
            iArr[1] = (int) j4;
            j3 = j4 >> 32;
        }
        long j5 = j3 + (((long) iArr[2]) & 4294967295L) + 1;
        iArr[2] = (int) j5;
        long j6 = (j5 >> 32) + ((((long) iArr[3]) & 4294967295L) - 1);
        iArr[3] = (int) j6;
        long j7 = j6 >> 32;
        if (j7 != 0) {
            long j8 = j7 + (((long) iArr[4]) & 4294967295L);
            iArr[4] = (int) j8;
            long j9 = (j8 >> 32) + (((long) iArr[5]) & 4294967295L);
            iArr[5] = (int) j9;
            long j10 = (j9 >> 32) + (((long) iArr[6]) & 4294967295L);
            iArr[6] = (int) j10;
            j7 = j10 >> 32;
        }
        iArr[7] = (int) (j7 + ((4294967295L & ((long) iArr[7])) - 1));
    }

    public static void subtract(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat256.sub(iArr, iArr2, iArr3) != 0) {
            subPInvFrom(iArr3);
        }
    }

    public static void subtractExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.sub(16, iArr, iArr2, iArr3) != 0) {
            Nat.addTo(16, PExt, iArr3);
        }
    }

    public static void twice(int[] iArr, int[] iArr2) {
        if (Nat.shiftUpBit(8, iArr, 0, iArr2) != 0 || ((iArr2[7] >>> 1) >= Integer.MAX_VALUE && Nat256.gte(iArr2, f7517P))) {
            addPInvTo(iArr2);
        }
    }
}
