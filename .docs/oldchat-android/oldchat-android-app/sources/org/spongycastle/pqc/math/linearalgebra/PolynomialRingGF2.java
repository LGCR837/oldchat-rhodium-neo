package org.spongycastle.pqc.math.linearalgebra;

import org.spongycastle.asn1.cmc.BodyPartID;

/* JADX INFO: loaded from: classes.dex */
public final class PolynomialRingGF2 {
    private PolynomialRingGF2() {
    }

    public static int add(int i2, int i3) {
        return i2 ^ i3;
    }

    public static int degree(int i2) {
        int i3 = -1;
        while (i2 != 0) {
            i3++;
            i2 >>>= 1;
        }
        return i3;
    }

    public static int gcd(int i2, int i3) {
        while (true) {
            int i4 = i3;
            int i5 = i2;
            i2 = i4;
            if (i2 == 0) {
                return i5;
            }
            i3 = remainder(i5, i2);
        }
    }

    public static int getIrreduciblePolynomial(int i2) {
        if (i2 < 0) {
            System.err.println("The Degree is negative");
            return 0;
        }
        if (i2 > 31) {
            System.err.println("The Degree is more then 31");
            return 0;
        }
        if (i2 == 0) {
            return 1;
        }
        int i3 = 1 << (i2 + 1);
        for (int i4 = (1 << i2) + 1; i4 < i3; i4 += 2) {
            if (isIrreducible(i4)) {
                return i4;
            }
        }
        return 0;
    }

    public static boolean isIrreducible(int i2) {
        if (i2 == 0) {
            return false;
        }
        int iDegree = degree(i2) >>> 1;
        int iModMultiply = 2;
        for (int i3 = 0; i3 < iDegree; i3++) {
            iModMultiply = modMultiply(iModMultiply, iModMultiply, i2);
            if (gcd(iModMultiply ^ 2, i2) != 1) {
                return false;
            }
        }
        return true;
    }

    public static int modMultiply(int i2, int i3, int i4) {
        int iRemainder = remainder(i2, i4);
        int iRemainder2 = remainder(i3, i4);
        int i5 = 0;
        if (iRemainder2 != 0) {
            int iDegree = 1 << degree(i4);
            while (iRemainder != 0) {
                if (((byte) (iRemainder & 1)) == 1) {
                    i5 ^= iRemainder2;
                }
                iRemainder >>>= 1;
                iRemainder2 <<= 1;
                if (iRemainder2 >= iDegree) {
                    iRemainder2 ^= i4;
                }
            }
        }
        return i5;
    }

    public static long multiply(int i2, int i3) {
        long j2 = 0;
        if (i3 != 0) {
            long j3 = ((long) i3) & BodyPartID.bodyIdMax;
            while (i2 != 0) {
                if (((byte) (i2 & 1)) == 1) {
                    j2 ^= j3;
                }
                i2 >>>= 1;
                j3 <<= 1;
            }
        }
        return j2;
    }

    public static int remainder(int i2, int i3) {
        if (i3 == 0) {
            System.err.println("Error: to be divided by 0");
            return 0;
        }
        while (degree(i2) >= degree(i3)) {
            i2 ^= i3 << (degree(i2) - degree(i3));
        }
        return i2;
    }

    public static int rest(long j2, int i2) {
        if (i2 == 0) {
            System.err.println("Error: to be divided by 0");
            return 0;
        }
        long j3 = ((long) i2) & BodyPartID.bodyIdMax;
        while ((j2 >>> 32) != 0) {
            j2 ^= j3 << (degree(j2) - degree(j3));
        }
        int iDegree = (int) j2;
        while (degree(iDegree) >= degree(i2)) {
            iDegree ^= i2 << (degree(iDegree) - degree(i2));
        }
        return iDegree;
    }

    public static int degree(long j2) {
        int i2 = 0;
        while (j2 != 0) {
            i2++;
            j2 >>>= 1;
        }
        return i2 - 1;
    }
}
