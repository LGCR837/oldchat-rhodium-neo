package org.spongycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat160;

/* JADX INFO: loaded from: classes.dex */
public class SecP160R1Field {

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    private static final long f7527M = 4294967295L;
    private static final int P4 = -1;
    private static final int PExt9 = -1;
    private static final int PInv = -2147483647;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    static final int[] f7528P = {Integer.MAX_VALUE, -1, -1, -1, -1};
    static final int[] PExt = {1, 1073741825, 0, 0, 0, -2, -2, -1, -1, -1};
    private static final int[] PExtInv = {-1, -1073741826, -1, -1, -1, 1, 1};

    public static void add(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat160.add(iArr, iArr2, iArr3) != 0 || (iArr3[4] == -1 && Nat160.gte(iArr3, f7528P))) {
            Nat.addWordTo(5, PInv, iArr3);
        }
    }

    public static void addExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.add(10, iArr, iArr2, iArr3) != 0 || (iArr3[9] == -1 && Nat.gte(10, iArr3, PExt))) {
            int[] iArr4 = PExtInv;
            if (Nat.addTo(iArr4.length, iArr4, iArr3) != 0) {
                Nat.incAt(10, iArr3, iArr4.length);
            }
        }
    }

    public static void addOne(int[] iArr, int[] iArr2) {
        if (Nat.inc(5, iArr, iArr2) != 0 || (iArr2[4] == -1 && Nat160.gte(iArr2, f7528P))) {
            Nat.addWordTo(5, PInv, iArr2);
        }
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        int[] iArrFromBigInteger = Nat160.fromBigInteger(bigInteger);
        if (iArrFromBigInteger[4] == -1) {
            int[] iArr = f7528P;
            if (Nat160.gte(iArrFromBigInteger, iArr)) {
                Nat160.subFrom(iArr, iArrFromBigInteger);
            }
        }
        return iArrFromBigInteger;
    }

    public static void half(int[] iArr, int[] iArr2) {
        if ((iArr[0] & 1) == 0) {
            Nat.shiftDownBit(5, iArr, 0, iArr2);
        } else {
            Nat.shiftDownBit(5, iArr2, Nat160.add(iArr, f7528P, iArr2));
        }
    }

    public static void multiply(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArrCreateExt = Nat160.createExt();
        Nat160.mul(iArr, iArr2, iArrCreateExt);
        reduce(iArrCreateExt, iArr3);
    }

    public static void multiplyAddToExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat160.mulAddTo(iArr, iArr2, iArr3) != 0 || (iArr3[9] == -1 && Nat.gte(10, iArr3, PExt))) {
            int[] iArr4 = PExtInv;
            if (Nat.addTo(iArr4.length, iArr4, iArr3) != 0) {
                Nat.incAt(10, iArr3, iArr4.length);
            }
        }
    }

    public static void negate(int[] iArr, int[] iArr2) {
        if (Nat160.isZero(iArr)) {
            Nat160.zero(iArr2);
        } else {
            Nat160.sub(f7528P, iArr, iArr2);
        }
    }

    public static void reduce(int[] iArr, int[] iArr2) {
        long j2 = ((long) iArr[5]) & 4294967295L;
        long j3 = ((long) iArr[6]) & 4294967295L;
        long j4 = ((long) iArr[7]) & 4294967295L;
        long j5 = ((long) iArr[8]) & 4294967295L;
        long j6 = ((long) iArr[9]) & 4294967295L;
        long j7 = (((long) iArr[0]) & 4294967295L) + j2 + (j2 << 31);
        iArr2[0] = (int) j7;
        long j8 = (j7 >>> 32) + (((long) iArr[1]) & 4294967295L) + j3 + (j3 << 31);
        iArr2[1] = (int) j8;
        long j9 = (j8 >>> 32) + (((long) iArr[2]) & 4294967295L) + j4 + (j4 << 31);
        iArr2[2] = (int) j9;
        long j10 = (j9 >>> 32) + (((long) iArr[3]) & 4294967295L) + j5 + (j5 << 31);
        iArr2[3] = (int) j10;
        long j11 = (j10 >>> 32) + (4294967295L & ((long) iArr[4])) + j6 + (j6 << 31);
        iArr2[4] = (int) j11;
        reduce32((int) (j11 >>> 32), iArr2);
    }

    public static void reduce32(int i2, int[] iArr) {
        if ((i2 == 0 || Nat160.mulWordsAdd(PInv, i2, iArr, 0) == 0) && !(iArr[4] == -1 && Nat160.gte(iArr, f7528P))) {
            return;
        }
        Nat.addWordTo(5, PInv, iArr);
    }

    public static void square(int[] iArr, int[] iArr2) {
        int[] iArrCreateExt = Nat160.createExt();
        Nat160.square(iArr, iArrCreateExt);
        reduce(iArrCreateExt, iArr2);
    }

    public static void squareN(int[] iArr, int i2, int[] iArr2) {
        int[] iArrCreateExt = Nat160.createExt();
        Nat160.square(iArr, iArrCreateExt);
        reduce(iArrCreateExt, iArr2);
        while (true) {
            i2--;
            if (i2 <= 0) {
                return;
            }
            Nat160.square(iArr2, iArrCreateExt);
            reduce(iArrCreateExt, iArr2);
        }
    }

    public static void subtract(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat160.sub(iArr, iArr2, iArr3) != 0) {
            Nat.subWordFrom(5, PInv, iArr3);
        }
    }

    public static void subtractExt(int[] iArr, int[] iArr2, int[] iArr3) {
        if (Nat.sub(10, iArr, iArr2, iArr3) != 0) {
            int[] iArr4 = PExtInv;
            if (Nat.subFrom(iArr4.length, iArr4, iArr3) != 0) {
                Nat.decAt(10, iArr3, iArr4.length);
            }
        }
    }

    public static void twice(int[] iArr, int[] iArr2) {
        if (Nat.shiftUpBit(5, iArr, 0, iArr2) != 0 || (iArr2[4] == -1 && Nat160.gte(iArr2, f7528P))) {
            Nat.addWordTo(5, PInv, iArr2);
        }
    }
}
