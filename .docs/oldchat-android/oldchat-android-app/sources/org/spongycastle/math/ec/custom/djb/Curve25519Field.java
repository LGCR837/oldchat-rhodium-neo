package org.spongycastle.math.ec.custom.djb;

import java.math.BigInteger;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat256;

/* JADX INFO: loaded from: classes.dex */
public class Curve25519Field {

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    private static final long f7511M = 4294967295L;
    private static final int PInv = 19;
    private static final int P7 = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    static final int[] f7512P = {-19, -1, -1, -1, -1, -1, -1, P7};
    private static final int[] PExt = {361, 0, 0, 0, 0, 0, 0, 0, -19, -1, -1, -1, -1, -1, -1, 1073741823};

    public static void add(int[] iArr, int[] iArr2, int[] iArr3) {
        Nat256.add(iArr, iArr2, iArr3);
        if (Nat256.gte(iArr3, f7512P)) {
            subPFrom(iArr3);
        }
    }

    public static void addExt(int[] iArr, int[] iArr2, int[] iArr3) {
        Nat.add(16, iArr, iArr2, iArr3);
        if (Nat.gte(16, iArr3, PExt)) {
            subPExtFrom(iArr3);
        }
    }

    public static void addOne(int[] iArr, int[] iArr2) {
        Nat.inc(8, iArr, iArr2);
        if (Nat256.gte(iArr2, f7512P)) {
            subPFrom(iArr2);
        }
    }

    private static int addPExtTo(int[] iArr) {
        long j2 = ((long) iArr[0]) & 4294967295L;
        int[] iArr2 = PExt;
        long j3 = j2 + (((long) iArr2[0]) & 4294967295L);
        iArr[0] = (int) j3;
        long jIncAt = j3 >> 32;
        if (jIncAt != 0) {
            jIncAt = Nat.incAt(8, iArr, 1);
        }
        long j4 = jIncAt + ((((long) iArr[8]) & 4294967295L) - 19);
        iArr[8] = (int) j4;
        long jDecAt = j4 >> 32;
        if (jDecAt != 0) {
            jDecAt = Nat.decAt(15, iArr, 9);
        }
        long j5 = jDecAt + (((long) iArr[15]) & 4294967295L) + (4294967295L & ((long) (iArr2[15] + 1)));
        iArr[15] = (int) j5;
        return (int) (j5 >> 32);
    }

    private static int addPTo(int[] iArr) {
        long j2 = (((long) iArr[0]) & 4294967295L) - 19;
        iArr[0] = (int) j2;
        long jDecAt = j2 >> 32;
        if (jDecAt != 0) {
            jDecAt = Nat.decAt(7, iArr, 1);
        }
        long j3 = jDecAt + (4294967295L & ((long) iArr[7])) + 2147483648L;
        iArr[7] = (int) j3;
        return (int) (j3 >> 32);
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] iArrFromBigInteger = Nat256.fromBigInteger(bigInteger);
        while (true) {
            int[] iArr = f7512P;
            if (!Nat256.gte(iArrFromBigInteger, iArr)) {
                return iArrFromBigInteger;
            }
            Nat256.subFrom(iArr, iArrFromBigInteger);
        }
    }

    public static void half(int[] iArr, int[] iArr2) {
        if ((iArr[0] & 1) == 0) {
            Nat.shiftDownBit(8, iArr, 0, iArr2);
        } else {
            Nat256.add(iArr, f7512P, iArr2);
            Nat.shiftDownBit(8, iArr2, 0);
        }
    }

    public static void multiply(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArrCreateExt = Nat256.createExt();
        Nat256.mul(iArr, iArr2, iArrCreateExt);
        reduce(iArrCreateExt, iArr3);
    }

    public static void multiplyAddToExt(int[] iArr, int[] iArr2, int[] iArr3) {
        Nat256.mulAddTo(iArr, iArr2, iArr3);
        if (Nat.gte(16, iArr3, PExt)) {
            subPExtFrom(iArr3);
        }
    }

    public static void negate(int[] iArr, int[] iArr2) {
        if (Nat256.isZero(iArr)) {
            Nat256.zero(iArr2);
        } else {
            Nat256.sub(f7512P, iArr, iArr2);
        }
    }

    public static void reduce(int[] iArr, int[] iArr2) {
        int i2 = iArr[7];
        Nat.shiftUpBit(8, iArr, 8, i2, iArr2, 0);
        int iMulByWordAddTo = Nat256.mulByWordAddTo(19, iArr, iArr2) << 1;
        int i3 = iArr2[7];
        iArr2[7] = (i3 & P7) + Nat.addWordTo(7, (iMulByWordAddTo + ((i3 >>> 31) - (i2 >>> 31))) * 19, iArr2);
        if (Nat256.gte(iArr2, f7512P)) {
            subPFrom(iArr2);
        }
    }

    public static void reduce27(int i2, int[] iArr) {
        int i3 = iArr[7];
        iArr[7] = (i3 & P7) + Nat.addWordTo(7, ((i2 << 1) | (i3 >>> 31)) * 19, iArr);
        if (Nat256.gte(iArr, f7512P)) {
            subPFrom(iArr);
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

    private static int subPExtFrom(int[] iArr) {
        long j2 = ((long) iArr[0]) & 4294967295L;
        int[] iArr2 = PExt;
        long j3 = j2 - (((long) iArr2[0]) & 4294967295L);
        iArr[0] = (int) j3;
        long jDecAt = j3 >> 32;
        if (jDecAt != 0) {
            jDecAt = Nat.decAt(8, iArr, 1);
        }
        long j4 = jDecAt + (((long) iArr[8]) & 4294967295L) + 19;
        iArr[8] = (int) j4;
        long jIncAt = j4 >> 32;
        if (jIncAt != 0) {
            jIncAt = Nat.incAt(15, iArr, 9);
        }
        long j5 = jIncAt + ((((long) iArr[15]) & 4294967295L) - (4294967295L & ((long) (iArr2[15] + 1))));
        iArr[15] = (int) j5;
        return (int) (j5 >> 32);
    }

    private static int subPFrom(int[] iArr) {
        long j2 = (((long) iArr[0]) & 4294967295L) + 19;
        iArr[0] = (int) j2;
        long jIncAt = j2 >> 32;
        if (jIncAt != 0) {
            jIncAt = Nat.incAt(7, iArr, 1);
        }
        long j3 = jIncAt + ((4294967295L & ((long) iArr[7])) - 2147483648L);
        iArr[7] = (int) j3;
        return (int) (j3 >> 32);
    }

    public static void subtract(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat256.sub(iArr, iArr2, iArr3) != 0) {
            addPTo(iArr3);
        }
    }

    public static void subtractExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.sub(16, iArr, iArr2, iArr3) != 0) {
            addPExtTo(iArr3);
        }
    }

    public static void twice(int[] iArr, int[] iArr2) {
        Nat.shiftUpBit(8, iArr, 0, iArr2);
        if (Nat256.gte(iArr2, f7512P)) {
            subPFrom(iArr2);
        }
    }
}
