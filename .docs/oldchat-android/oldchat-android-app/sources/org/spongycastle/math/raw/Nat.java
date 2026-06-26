package org.spongycastle.math.raw;

import java.math.BigInteger;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public abstract class Nat {

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    private static final long f7582M = 4294967295L;

    public static int add(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            long j3 = j2 + (((long) iArr[i3]) & 4294967295L) + (4294967295L & ((long) iArr2[i3]));
            iArr3[i3] = (int) j3;
            j2 = j3 >>> 32;
        }
        return (int) j2;
    }

    public static int add33At(int i2, int i3, int[] iArr, int i4) {
        long j2 = (((long) iArr[i4]) & 4294967295L) + (((long) i3) & 4294967295L);
        iArr[i4] = (int) j2;
        int i5 = i4 + 1;
        long j3 = (j2 >>> 32) + (4294967295L & ((long) iArr[i5])) + 1;
        iArr[i5] = (int) j3;
        if ((j3 >>> 32) == 0) {
            return 0;
        }
        return incAt(i2, iArr, i4 + 2);
    }

    public static int add33To(int i2, int i3, int[] iArr) {
        long j2 = (((long) iArr[0]) & 4294967295L) + (((long) i3) & 4294967295L);
        iArr[0] = (int) j2;
        long j3 = (j2 >>> 32) + (4294967295L & ((long) iArr[1])) + 1;
        iArr[1] = (int) j3;
        if ((j3 >>> 32) == 0) {
            return 0;
        }
        return incAt(i2, iArr, 2);
    }

    public static int addBothTo(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            long j3 = j2 + (((long) iArr[i3]) & 4294967295L) + (((long) iArr2[i3]) & 4294967295L) + (4294967295L & ((long) iArr3[i3]));
            iArr3[i3] = (int) j3;
            j2 = j3 >>> 32;
        }
        return (int) j2;
    }

    public static int addDWordAt(int i2, long j2, int[] iArr, int i3) {
        long j3 = (((long) iArr[i3]) & 4294967295L) + (j2 & 4294967295L);
        iArr[i3] = (int) j3;
        int i4 = i3 + 1;
        long j4 = (j3 >>> 32) + (4294967295L & ((long) iArr[i4])) + (j2 >>> 32);
        iArr[i4] = (int) j4;
        if ((j4 >>> 32) == 0) {
            return 0;
        }
        return incAt(i2, iArr, i3 + 2);
    }

    public static int addDWordTo(int i2, long j2, int[] iArr) {
        long j3 = (((long) iArr[0]) & 4294967295L) + (j2 & 4294967295L);
        iArr[0] = (int) j3;
        long j4 = (j3 >>> 32) + (4294967295L & ((long) iArr[1])) + (j2 >>> 32);
        iArr[1] = (int) j4;
        if ((j4 >>> 32) == 0) {
            return 0;
        }
        return incAt(i2, iArr, 2);
    }

    public static int addTo(int i2, int[] iArr, int[] iArr2) {
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            long j3 = j2 + (((long) iArr[i3]) & 4294967295L) + (4294967295L & ((long) iArr2[i3]));
            iArr2[i3] = (int) j3;
            j2 = j3 >>> 32;
        }
        return (int) j2;
    }

    public static int addWordAt(int i2, int i3, int[] iArr, int i4) {
        long j2 = (((long) i3) & 4294967295L) + (4294967295L & ((long) iArr[i4]));
        iArr[i4] = (int) j2;
        if ((j2 >>> 32) == 0) {
            return 0;
        }
        return incAt(i2, iArr, i4 + 1);
    }

    public static int addWordTo(int i2, int i3, int[] iArr) {
        long j2 = (((long) i3) & 4294967295L) + (4294967295L & ((long) iArr[0]));
        iArr[0] = (int) j2;
        if ((j2 >>> 32) == 0) {
            return 0;
        }
        return incAt(i2, iArr, 1);
    }

    public static int[] copy(int i2, int[] iArr) {
        int[] iArr2 = new int[i2];
        System.arraycopy(iArr, 0, iArr2, 0, i2);
        return iArr2;
    }

    public static int[] create(int i2) {
        return new int[i2];
    }

    public static long[] create64(int i2) {
        return new long[i2];
    }

    public static int dec(int i2, int[] iArr) {
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = iArr[i3] - 1;
            iArr[i3] = i4;
            if (i4 != -1) {
                return 0;
            }
        }
        return -1;
    }

    public static int decAt(int i2, int[] iArr, int i3) {
        while (i3 < i2) {
            int i4 = iArr[i3] - 1;
            iArr[i3] = i4;
            if (i4 != -1) {
                return 0;
            }
            i3++;
        }
        return -1;
    }

    public static boolean eq(int i2, int[] iArr, int[] iArr2) {
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            if (iArr[i3] != iArr2[i3]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(int i2, BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > i2) {
            throw new IllegalArgumentException();
        }
        int[] iArrCreate = create((i2 + 31) >> 5);
        int i3 = 0;
        while (bigInteger.signum() != 0) {
            iArrCreate[i3] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
            i3++;
        }
        return iArrCreate;
    }

    public static int getBit(int[] iArr, int i2) {
        int i3;
        if (i2 == 0) {
            i3 = iArr[0];
        } else {
            int i4 = i2 >> 5;
            if (i4 < 0 || i4 >= iArr.length) {
                return 0;
            }
            i3 = iArr[i4] >>> (i2 & 31);
        }
        return i3 & 1;
    }

    public static boolean gte(int i2, int[] iArr, int[] iArr2) {
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            int i4 = iArr[i3] ^ PKIFailureInfo.systemUnavail;
            int i5 = Integer.MIN_VALUE ^ iArr2[i3];
            if (i4 < i5) {
                return false;
            }
            if (i4 > i5) {
                return true;
            }
        }
        return true;
    }

    public static int inc(int i2, int[] iArr) {
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = iArr[i3] + 1;
            iArr[i3] = i4;
            if (i4 != 0) {
                return 0;
            }
        }
        return 1;
    }

    public static int incAt(int i2, int[] iArr, int i3) {
        while (i3 < i2) {
            int i4 = iArr[i3] + 1;
            iArr[i3] = i4;
            if (i4 != 0) {
                return 0;
            }
            i3++;
        }
        return 1;
    }

    public static boolean isOne(int i2, int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i3 = 1; i3 < i2; i3++) {
            if (iArr[i3] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int i2, int[] iArr) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (iArr[i3] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        iArr3[i2] = mulWord(i2, iArr[0], iArr2, iArr3);
        for (int i3 = 1; i3 < i2; i3++) {
            iArr3[i3 + i2] = mulWordAddTo(i2, iArr[i3], iArr2, 0, iArr3, i3);
        }
    }

    public static int mul31BothAdd(int i2, int i3, int[] iArr, int i4, int[] iArr2, int[] iArr3, int i5) {
        long j2 = ((long) i3) & 4294967295L;
        long j3 = ((long) i4) & 4294967295L;
        long j4 = 0;
        int i6 = 0;
        do {
            int i7 = i5 + i6;
            long j5 = j4 + ((((long) iArr[i6]) & 4294967295L) * j2) + ((((long) iArr2[i6]) & 4294967295L) * j3) + (((long) iArr3[i7]) & 4294967295L);
            iArr3[i7] = (int) j5;
            j4 = j5 >>> 32;
            i6++;
        } while (i6 < i2);
        return (int) j4;
    }

    public static int mulAddTo(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 + i2;
            long jMulWordAddTo = (((long) mulWordAddTo(i2, iArr[i3], iArr2, 0, iArr3, i3)) & 4294967295L) + j2 + (4294967295L & ((long) iArr3[i4]));
            iArr3[i4] = (int) jMulWordAddTo;
            j2 = jMulWordAddTo >>> 32;
        }
        return (int) j2;
    }

    public static int mulWord(int i2, int i3, int[] iArr, int[] iArr2) {
        long j2 = ((long) i3) & 4294967295L;
        long j3 = 0;
        int i4 = 0;
        do {
            long j4 = j3 + ((((long) iArr[i4]) & 4294967295L) * j2);
            iArr2[i4] = (int) j4;
            j3 = j4 >>> 32;
            i4++;
        } while (i4 < i2);
        return (int) j3;
    }

    public static int mulWordAddTo(int i2, int i3, int[] iArr, int i4, int[] iArr2, int i5) {
        long j2 = ((long) i3) & 4294967295L;
        long j3 = 0;
        int i6 = 0;
        do {
            int i7 = i5 + i6;
            long j4 = j3 + ((((long) iArr[i4 + i6]) & 4294967295L) * j2) + (((long) iArr2[i7]) & 4294967295L);
            iArr2[i7] = (int) j4;
            j3 = j4 >>> 32;
            i6++;
        } while (i6 < i2);
        return (int) j3;
    }

    public static int mulWordDwordAddAt(int i2, int i3, long j2, int[] iArr, int i4) {
        long j3 = ((long) i3) & 4294967295L;
        long j4 = ((j2 & 4294967295L) * j3) + (((long) iArr[i4]) & 4294967295L);
        iArr[i4] = (int) j4;
        long j5 = j3 * (j2 >>> 32);
        int i5 = i4 + 1;
        long j6 = (j4 >>> 32) + j5 + (((long) iArr[i5]) & 4294967295L);
        iArr[i5] = (int) j6;
        int i6 = i4 + 2;
        long j7 = (j6 >>> 32) + (4294967295L & ((long) iArr[i6]));
        iArr[i6] = (int) j7;
        if ((j7 >>> 32) == 0) {
            return 0;
        }
        return incAt(i2, iArr, i4 + 3);
    }

    public static int shiftDownBit(int i2, int[] iArr, int i3) {
        while (true) {
            i2--;
            if (i2 < 0) {
                return i3 << 31;
            }
            int i4 = iArr[i2];
            iArr[i2] = (i3 << 31) | (i4 >>> 1);
            i3 = i4;
        }
    }

    public static int shiftDownBits(int i2, int[] iArr, int i3, int i4) {
        while (true) {
            i2--;
            if (i2 < 0) {
                return i4 << (-i3);
            }
            int i5 = iArr[i2];
            iArr[i2] = (i4 << (-i3)) | (i5 >>> i3);
            i4 = i5;
        }
    }

    public static int shiftDownWord(int i2, int[] iArr, int i3) {
        while (true) {
            i2--;
            if (i2 < 0) {
                return i3;
            }
            int i4 = iArr[i2];
            iArr[i2] = i3;
            i3 = i4;
        }
    }

    public static int shiftUpBit(int i2, int[] iArr, int i3) {
        int i4 = 0;
        while (i4 < i2) {
            int i5 = iArr[i4];
            iArr[i4] = (i3 >>> 31) | (i5 << 1);
            i4++;
            i3 = i5;
        }
        return i3 >>> 31;
    }

    public static long shiftUpBit64(int i2, long[] jArr, int i3, long j2, long[] jArr2, int i4) {
        int i5 = 0;
        while (i5 < i2) {
            long j3 = jArr[i3 + i5];
            jArr2[i4 + i5] = (j2 >>> 63) | (j3 << 1);
            i5++;
            j2 = j3;
        }
        return j2 >>> 63;
    }

    public static int shiftUpBits(int i2, int[] iArr, int i3, int i4) {
        int i5 = 0;
        while (i5 < i2) {
            int i6 = iArr[i5];
            iArr[i5] = (i4 >>> (-i3)) | (i6 << i3);
            i5++;
            i4 = i6;
        }
        return i4 >>> (-i3);
    }

    public static long shiftUpBits64(int i2, long[] jArr, int i3, int i4, long j2) {
        int i5 = 0;
        while (i5 < i2) {
            int i6 = i3 + i5;
            long j3 = jArr[i6];
            jArr[i6] = (j2 >>> (-i4)) | (j3 << i4);
            i5++;
            j2 = j3;
        }
        return j2 >>> (-i4);
    }

    public static void square(int i2, int[] iArr, int[] iArr2) {
        int i3;
        int i4 = i2 << 1;
        int i5 = 0;
        int i6 = i2;
        int i7 = i4;
        while (true) {
            i6--;
            long j2 = ((long) iArr[i6]) & 4294967295L;
            long j3 = j2 * j2;
            iArr2[i7 - 1] = (i5 << 31) | ((int) (j3 >>> 33));
            i7 -= 2;
            iArr2[i7] = (int) (j3 >>> 1);
            int i8 = (int) j3;
            if (i6 <= 0) {
                break;
            } else {
                i5 = i8;
            }
        }
        for (i3 = 1; i3 < i2; i3++) {
            addWordAt(i4, squareWordAdd(iArr, i3, iArr2), iArr2, i3 << 1);
        }
        shiftUpBit(i4, iArr2, iArr[0] << 31);
    }

    public static int squareWordAdd(int[] iArr, int i2, int[] iArr2) {
        long j2 = ((long) iArr[i2]) & 4294967295L;
        long j3 = 0;
        int i3 = 0;
        do {
            int i4 = i2 + i3;
            long j4 = j3 + ((((long) iArr[i3]) & 4294967295L) * j2) + (((long) iArr2[i4]) & 4294967295L);
            iArr2[i4] = (int) j4;
            j3 = j4 >>> 32;
            i3++;
        } while (i3 < i2);
        return (int) j3;
    }

    public static int sub(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            long j3 = j2 + ((((long) iArr[i3]) & 4294967295L) - (4294967295L & ((long) iArr2[i3])));
            iArr3[i3] = (int) j3;
            j2 = j3 >> 32;
        }
        return (int) j2;
    }

    public static int sub33At(int i2, int i3, int[] iArr, int i4) {
        long j2 = (((long) iArr[i4]) & 4294967295L) - (((long) i3) & 4294967295L);
        iArr[i4] = (int) j2;
        int i5 = i4 + 1;
        long j3 = (j2 >> 32) + ((4294967295L & ((long) iArr[i5])) - 1);
        iArr[i5] = (int) j3;
        if ((j3 >> 32) == 0) {
            return 0;
        }
        return decAt(i2, iArr, i4 + 2);
    }

    public static int sub33From(int i2, int i3, int[] iArr) {
        long j2 = (((long) iArr[0]) & 4294967295L) - (((long) i3) & 4294967295L);
        iArr[0] = (int) j2;
        long j3 = (j2 >> 32) + ((4294967295L & ((long) iArr[1])) - 1);
        iArr[1] = (int) j3;
        if ((j3 >> 32) == 0) {
            return 0;
        }
        return decAt(i2, iArr, 2);
    }

    public static int subBothFrom(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            long j3 = j2 + (((((long) iArr3[i3]) & 4294967295L) - (((long) iArr[i3]) & 4294967295L)) - (4294967295L & ((long) iArr2[i3])));
            iArr3[i3] = (int) j3;
            j2 = j3 >> 32;
        }
        return (int) j2;
    }

    public static int subDWordAt(int i2, long j2, int[] iArr, int i3) {
        long j3 = (((long) iArr[i3]) & 4294967295L) - (j2 & 4294967295L);
        iArr[i3] = (int) j3;
        int i4 = i3 + 1;
        long j4 = (j3 >> 32) + ((4294967295L & ((long) iArr[i4])) - (j2 >>> 32));
        iArr[i4] = (int) j4;
        if ((j4 >> 32) == 0) {
            return 0;
        }
        return decAt(i2, iArr, i3 + 2);
    }

    public static int subDWordFrom(int i2, long j2, int[] iArr) {
        long j3 = (((long) iArr[0]) & 4294967295L) - (j2 & 4294967295L);
        iArr[0] = (int) j3;
        long j4 = (j3 >> 32) + ((4294967295L & ((long) iArr[1])) - (j2 >>> 32));
        iArr[1] = (int) j4;
        if ((j4 >> 32) == 0) {
            return 0;
        }
        return decAt(i2, iArr, 2);
    }

    public static int subFrom(int i2, int[] iArr, int[] iArr2) {
        long j2 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            long j3 = j2 + ((((long) iArr2[i3]) & 4294967295L) - (4294967295L & ((long) iArr[i3])));
            iArr2[i3] = (int) j3;
            j2 = j3 >> 32;
        }
        return (int) j2;
    }

    public static int subWordAt(int i2, int i3, int[] iArr, int i4) {
        long j2 = (((long) iArr[i4]) & 4294967295L) - (4294967295L & ((long) i3));
        iArr[i4] = (int) j2;
        if ((j2 >> 32) == 0) {
            return 0;
        }
        return decAt(i2, iArr, i4 + 1);
    }

    public static int subWordFrom(int i2, int i3, int[] iArr) {
        long j2 = (((long) iArr[0]) & 4294967295L) - (4294967295L & ((long) i3));
        iArr[0] = (int) j2;
        if ((j2 >> 32) == 0) {
            return 0;
        }
        return decAt(i2, iArr, 1);
    }

    public static BigInteger toBigInteger(int i2, int[] iArr) {
        byte[] bArr = new byte[i2 << 2];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = iArr[i3];
            if (i4 != 0) {
                Pack.intToBigEndian(i4, bArr, ((i2 - 1) - i3) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void zero(int i2, int[] iArr) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i3] = 0;
        }
    }

    public static void zero64(int i2, long[] jArr) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr[i3] = 0;
        }
    }

    public static int dec(int i2, int[] iArr, int[] iArr2) {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = iArr[i3] - 1;
            iArr2[i3] = i4;
            i3++;
            if (i4 != -1) {
                while (i3 < i2) {
                    iArr2[i3] = iArr[i3];
                    i3++;
                }
                return 0;
            }
        }
        return -1;
    }

    public static int decAt(int i2, int[] iArr, int i3, int i4) {
        while (i4 < i2) {
            int i5 = i3 + i4;
            int i6 = iArr[i5] - 1;
            iArr[i5] = i6;
            if (i6 != -1) {
                return 0;
            }
            i4++;
        }
        return -1;
    }

    public static int inc(int i2, int[] iArr, int[] iArr2) {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = iArr[i3] + 1;
            iArr2[i3] = i4;
            i3++;
            if (i4 != 0) {
                while (i3 < i2) {
                    iArr2[i3] = iArr[i3];
                    i3++;
                }
                return 0;
            }
        }
        return 1;
    }

    public static int incAt(int i2, int[] iArr, int i3, int i4) {
        while (i4 < i2) {
            int i5 = i3 + i4;
            int i6 = iArr[i5] + 1;
            iArr[i5] = i6;
            if (i6 != 0) {
                return 0;
            }
            i4++;
        }
        return 1;
    }

    public static int addBothTo(int i2, int[] iArr, int i3, int[] iArr2, int i4, int[] iArr3, int i5) {
        long j2 = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = i5 + i6;
            long j3 = j2 + (((long) iArr[i3 + i6]) & 4294967295L) + (((long) iArr2[i4 + i6]) & 4294967295L) + (4294967295L & ((long) iArr3[i7]));
            iArr3[i7] = (int) j3;
            j2 = j3 >>> 32;
        }
        return (int) j2;
    }

    public static int addTo(int i2, int[] iArr, int i3, int[] iArr2, int i4) {
        long j2 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i4 + i5;
            long j3 = j2 + (((long) iArr[i3 + i5]) & 4294967295L) + (4294967295L & ((long) iArr2[i6]));
            iArr2[i6] = (int) j3;
            j2 = j3 >>> 32;
        }
        return (int) j2;
    }

    public static void copy(int i2, int[] iArr, int[] iArr2) {
        System.arraycopy(iArr, 0, iArr2, 0, i2);
    }

    public static void mul(int i2, int[] iArr, int i3, int[] iArr2, int i4, int[] iArr3, int i5) {
        iArr3[i5 + i2] = mulWord(i2, iArr[i3], iArr2, i4, iArr3, i5);
        for (int i6 = 1; i6 < i2; i6++) {
            int i7 = i5 + i6;
            iArr3[i7 + i2] = mulWordAddTo(i2, iArr[i3 + i6], iArr2, i4, iArr3, i7);
        }
    }

    public static int mulWord(int i2, int i3, int[] iArr, int i4, int[] iArr2, int i5) {
        long j2 = ((long) i3) & 4294967295L;
        long j3 = 0;
        int i6 = 0;
        do {
            long j4 = j3 + ((((long) iArr[i4 + i6]) & 4294967295L) * j2);
            iArr2[i5 + i6] = (int) j4;
            j3 = j4 >>> 32;
            i6++;
        } while (i6 < i2);
        return (int) j3;
    }

    public static int shiftDownBit(int i2, int[] iArr, int i3, int i4) {
        while (true) {
            i2--;
            if (i2 < 0) {
                return i4 << 31;
            }
            int i5 = i3 + i2;
            int i6 = iArr[i5];
            iArr[i5] = (i4 << 31) | (i6 >>> 1);
            i4 = i6;
        }
    }

    public static int shiftDownBits(int i2, int[] iArr, int i3, int i4, int i5) {
        while (true) {
            i2--;
            if (i2 < 0) {
                return i5 << (-i4);
            }
            int i6 = i3 + i2;
            int i7 = iArr[i6];
            iArr[i6] = (i5 << (-i4)) | (i7 >>> i4);
            i5 = i7;
        }
    }

    public static int shiftUpBit(int i2, int[] iArr, int i3, int i4) {
        int i5 = 0;
        while (i5 < i2) {
            int i6 = i3 + i5;
            int i7 = iArr[i6];
            iArr[i6] = (i4 >>> 31) | (i7 << 1);
            i5++;
            i4 = i7;
        }
        return i4 >>> 31;
    }

    public static int shiftUpBits(int i2, int[] iArr, int i3, int i4, int i5) {
        int i6 = 0;
        while (i6 < i2) {
            int i7 = i3 + i6;
            int i8 = iArr[i7];
            iArr[i7] = (i5 >>> (-i4)) | (i8 << i4);
            i6++;
            i5 = i8;
        }
        return i5 >>> (-i4);
    }

    public static long shiftUpBits64(int i2, long[] jArr, int i3, int i4, long j2, long[] jArr2, int i5) {
        int i6 = 0;
        while (i6 < i2) {
            long j3 = jArr[i3 + i6];
            jArr2[i5 + i6] = (j2 >>> (-i4)) | (j3 << i4);
            i6++;
            j2 = j3;
        }
        return j2 >>> (-i4);
    }

    public static int sub(int i2, int[] iArr, int i3, int[] iArr2, int i4, int[] iArr3, int i5) {
        long j2 = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            long j3 = j2 + ((((long) iArr[i3 + i6]) & 4294967295L) - (4294967295L & ((long) iArr2[i4 + i6])));
            iArr3[i5 + i6] = (int) j3;
            j2 = j3 >> 32;
        }
        return (int) j2;
    }

    public static int subBothFrom(int i2, int[] iArr, int i3, int[] iArr2, int i4, int[] iArr3, int i5) {
        long j2 = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = i5 + i6;
            long j3 = j2 + (((((long) iArr3[i7]) & 4294967295L) - (((long) iArr[i3 + i6]) & 4294967295L)) - (4294967295L & ((long) iArr2[i4 + i6])));
            iArr3[i7] = (int) j3;
            j2 = j3 >> 32;
        }
        return (int) j2;
    }

    public static int subFrom(int i2, int[] iArr, int i3, int[] iArr2, int i4) {
        long j2 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i4 + i5;
            long j3 = j2 + ((((long) iArr2[i6]) & 4294967295L) - (4294967295L & ((long) iArr[i3 + i5])));
            iArr2[i6] = (int) j3;
            j2 = j3 >> 32;
        }
        return (int) j2;
    }

    public static int addWordAt(int i2, int i3, int[] iArr, int i4, int i5) {
        long j2 = ((long) i3) & 4294967295L;
        int i6 = i4 + i5;
        long j3 = j2 + (4294967295L & ((long) iArr[i6]));
        iArr[i6] = (int) j3;
        if ((j3 >>> 32) == 0) {
            return 0;
        }
        return incAt(i2, iArr, i4, i5 + 1);
    }

    public static int addWordTo(int i2, int i3, int[] iArr, int i4) {
        long j2 = (((long) i3) & 4294967295L) + (4294967295L & ((long) iArr[i4]));
        iArr[i4] = (int) j2;
        if ((j2 >>> 32) == 0) {
            return 0;
        }
        return incAt(i2, iArr, i4, 1);
    }

    public static int mulAddTo(int i2, int[] iArr, int i3, int[] iArr2, int i4, int[] iArr3, int i5) {
        int i6 = i5;
        long j2 = 0;
        for (int i7 = 0; i7 < i2; i7++) {
            int i8 = i6 + i2;
            long jMulWordAddTo = (((long) mulWordAddTo(i2, iArr[i3 + i7], iArr2, i4, iArr3, i6)) & 4294967295L) + j2 + (4294967295L & ((long) iArr3[i8]));
            iArr3[i8] = (int) jMulWordAddTo;
            j2 = jMulWordAddTo >>> 32;
            i6++;
        }
        return (int) j2;
    }

    public static int squareWordAdd(int[] iArr, int i2, int i3, int[] iArr2, int i4) {
        long j2 = ((long) iArr[i2 + i3]) & 4294967295L;
        long j3 = 0;
        int i5 = 0;
        int i6 = i4;
        do {
            int i7 = i3 + i6;
            long j4 = j3 + ((((long) iArr[i2 + i5]) & 4294967295L) * j2) + (((long) iArr2[i7]) & 4294967295L);
            iArr2[i7] = (int) j4;
            j3 = j4 >>> 32;
            i6++;
            i5++;
        } while (i5 < i3);
        return (int) j3;
    }

    public static int subWordAt(int i2, int i3, int[] iArr, int i4, int i5) {
        int i6 = i4 + i5;
        long j2 = (((long) iArr[i6]) & 4294967295L) - (4294967295L & ((long) i3));
        iArr[i6] = (int) j2;
        if ((j2 >> 32) == 0) {
            return 0;
        }
        return decAt(i2, iArr, i4, i5 + 1);
    }

    public static int subWordFrom(int i2, int i3, int[] iArr, int i4) {
        long j2 = (((long) iArr[i4]) & 4294967295L) - (4294967295L & ((long) i3));
        iArr[i4] = (int) j2;
        if ((j2 >> 32) == 0) {
            return 0;
        }
        return decAt(i2, iArr, i4, 1);
    }

    public static int shiftDownBit(int i2, int[] iArr, int i3, int[] iArr2) {
        while (true) {
            i2--;
            if (i2 < 0) {
                return i3 << 31;
            }
            int i4 = iArr[i2];
            iArr2[i2] = (i3 << 31) | (i4 >>> 1);
            i3 = i4;
        }
    }

    public static int shiftDownBits(int i2, int[] iArr, int i3, int i4, int[] iArr2) {
        while (true) {
            i2--;
            if (i2 < 0) {
                return i4 << (-i3);
            }
            int i5 = iArr[i2];
            iArr2[i2] = (i4 << (-i3)) | (i5 >>> i3);
            i4 = i5;
        }
    }

    public static int shiftUpBit(int i2, int[] iArr, int i3, int[] iArr2) {
        int i4 = 0;
        while (i4 < i2) {
            int i5 = iArr[i4];
            iArr2[i4] = (i3 >>> 31) | (i5 << 1);
            i4++;
            i3 = i5;
        }
        return i3 >>> 31;
    }

    public static int shiftUpBits(int i2, int[] iArr, int i3, int i4, int[] iArr2) {
        int i5 = 0;
        while (i5 < i2) {
            int i6 = iArr[i5];
            iArr2[i5] = (i4 >>> (-i3)) | (i6 << i3);
            i5++;
            i4 = i6;
        }
        return i4 >>> (-i3);
    }

    public static int add33At(int i2, int i3, int[] iArr, int i4, int i5) {
        int i6 = i4 + i5;
        long j2 = (((long) iArr[i6]) & 4294967295L) + (((long) i3) & 4294967295L);
        iArr[i6] = (int) j2;
        int i7 = i6 + 1;
        long j3 = (j2 >>> 32) + (4294967295L & ((long) iArr[i7])) + 1;
        iArr[i7] = (int) j3;
        if ((j3 >>> 32) == 0) {
            return 0;
        }
        return incAt(i2, iArr, i4, i5 + 2);
    }

    public static int add33To(int i2, int i3, int[] iArr, int i4) {
        long j2 = (((long) iArr[i4]) & 4294967295L) + (((long) i3) & 4294967295L);
        iArr[i4] = (int) j2;
        int i5 = i4 + 1;
        long j3 = (j2 >>> 32) + (4294967295L & ((long) iArr[i5])) + 1;
        iArr[i5] = (int) j3;
        if ((j3 >>> 32) == 0) {
            return 0;
        }
        return incAt(i2, iArr, i4, 2);
    }

    public static int addDWordAt(int i2, long j2, int[] iArr, int i3, int i4) {
        int i5 = i3 + i4;
        long j3 = (((long) iArr[i5]) & 4294967295L) + (j2 & 4294967295L);
        iArr[i5] = (int) j3;
        int i6 = i5 + 1;
        long j4 = (j3 >>> 32) + (4294967295L & ((long) iArr[i6])) + (j2 >>> 32);
        iArr[i6] = (int) j4;
        if ((j4 >>> 32) == 0) {
            return 0;
        }
        return incAt(i2, iArr, i3, i4 + 2);
    }

    public static int addDWordTo(int i2, long j2, int[] iArr, int i3) {
        long j3 = (((long) iArr[i3]) & 4294967295L) + (j2 & 4294967295L);
        iArr[i3] = (int) j3;
        int i4 = i3 + 1;
        long j4 = (j3 >>> 32) + (4294967295L & ((long) iArr[i4])) + (j2 >>> 32);
        iArr[i4] = (int) j4;
        if ((j4 >>> 32) == 0) {
            return 0;
        }
        return incAt(i2, iArr, i3, 2);
    }

    public static int sub33At(int i2, int i3, int[] iArr, int i4, int i5) {
        int i6 = i4 + i5;
        long j2 = (((long) iArr[i6]) & 4294967295L) - (((long) i3) & 4294967295L);
        iArr[i6] = (int) j2;
        int i7 = i6 + 1;
        long j3 = (j2 >> 32) + ((4294967295L & ((long) iArr[i7])) - 1);
        iArr[i7] = (int) j3;
        if ((j3 >> 32) == 0) {
            return 0;
        }
        return decAt(i2, iArr, i4, i5 + 2);
    }

    public static int sub33From(int i2, int i3, int[] iArr, int i4) {
        long j2 = (((long) iArr[i4]) & 4294967295L) - (((long) i3) & 4294967295L);
        iArr[i4] = (int) j2;
        int i5 = i4 + 1;
        long j3 = (j2 >> 32) + ((4294967295L & ((long) iArr[i5])) - 1);
        iArr[i5] = (int) j3;
        if ((j3 >> 32) == 0) {
            return 0;
        }
        return decAt(i2, iArr, i4, 2);
    }

    public static int subDWordAt(int i2, long j2, int[] iArr, int i3, int i4) {
        int i5 = i3 + i4;
        long j3 = (((long) iArr[i5]) & 4294967295L) - (j2 & 4294967295L);
        iArr[i5] = (int) j3;
        int i6 = i5 + 1;
        long j4 = (j3 >> 32) + ((4294967295L & ((long) iArr[i6])) - (j2 >>> 32));
        iArr[i6] = (int) j4;
        if ((j4 >> 32) == 0) {
            return 0;
        }
        return decAt(i2, iArr, i3, i4 + 2);
    }

    public static int subDWordFrom(int i2, long j2, int[] iArr, int i3) {
        long j3 = (((long) iArr[i3]) & 4294967295L) - (j2 & 4294967295L);
        iArr[i3] = (int) j3;
        int i4 = i3 + 1;
        long j4 = (j3 >> 32) + ((4294967295L & ((long) iArr[i4])) - (j2 >>> 32));
        iArr[i4] = (int) j4;
        if ((j4 >> 32) == 0) {
            return 0;
        }
        return decAt(i2, iArr, i3, 2);
    }

    public static int shiftDownBit(int i2, int[] iArr, int i3, int i4, int[] iArr2, int i5) {
        while (true) {
            i2--;
            if (i2 < 0) {
                return i4 << 31;
            }
            int i6 = iArr[i3 + i2];
            iArr2[i5 + i2] = (i4 << 31) | (i6 >>> 1);
            i4 = i6;
        }
    }

    public static int shiftDownBits(int i2, int[] iArr, int i3, int i4, int i5, int[] iArr2, int i6) {
        while (true) {
            i2--;
            if (i2 < 0) {
                return i5 << (-i4);
            }
            int i7 = iArr[i3 + i2];
            iArr2[i6 + i2] = (i5 << (-i4)) | (i7 >>> i4);
            i5 = i7;
        }
    }

    public static int shiftUpBit(int i2, int[] iArr, int i3, int i4, int[] iArr2, int i5) {
        int i6 = 0;
        while (i6 < i2) {
            int i7 = iArr[i3 + i6];
            iArr2[i5 + i6] = (i4 >>> 31) | (i7 << 1);
            i6++;
            i4 = i7;
        }
        return i4 >>> 31;
    }

    public static int shiftUpBits(int i2, int[] iArr, int i3, int i4, int i5, int[] iArr2, int i6) {
        int i7 = 0;
        while (i7 < i2) {
            int i8 = iArr[i3 + i7];
            iArr2[i6 + i7] = (i5 >>> (-i4)) | (i8 << i4);
            i7++;
            i5 = i8;
        }
        return i5 >>> (-i4);
    }

    public static void square(int i2, int[] iArr, int i3, int[] iArr2, int i4) {
        int i5;
        int i6 = i2 << 1;
        int i7 = 0;
        int i8 = i2;
        int i9 = i6;
        do {
            i8--;
            long j2 = ((long) iArr[i3 + i8]) & 4294967295L;
            long j3 = j2 * j2;
            iArr2[(i9 - 1) + i4] = (i7 << 31) | ((int) (j3 >>> 33));
            i9 -= 2;
            iArr2[i4 + i9] = (int) (j3 >>> 1);
            i7 = (int) j3;
        } while (i8 > 0);
        for (i5 = 1; i5 < i2; i5++) {
            addWordAt(i6, squareWordAdd(iArr, i3, i5, iArr2, i4), iArr2, i4, i5 << 1);
        }
        shiftUpBit(i6, iArr2, i4, iArr[i3] << 31);
    }
}
