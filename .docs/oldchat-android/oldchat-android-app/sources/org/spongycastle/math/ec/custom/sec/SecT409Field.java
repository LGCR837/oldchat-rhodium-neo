package org.spongycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.asn1.cmc.BodyPartID;
import org.spongycastle.math.raw.Interleave;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat448;

/* JADX INFO: loaded from: classes.dex */
public class SecT409Field {
    private static final long M25 = 33554431;
    private static final long M59 = 576460752303423487L;

    public static void add(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr3[0] = jArr[0] ^ jArr2[0];
        jArr3[1] = jArr[1] ^ jArr2[1];
        jArr3[2] = jArr[2] ^ jArr2[2];
        jArr3[3] = jArr[3] ^ jArr2[3];
        jArr3[4] = jArr[4] ^ jArr2[4];
        jArr3[5] = jArr[5] ^ jArr2[5];
        jArr3[6] = jArr2[6] ^ jArr[6];
    }

    public static void addExt(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i2 = 0; i2 < 13; i2++) {
            jArr3[i2] = jArr[i2] ^ jArr2[i2];
        }
    }

    public static void addOne(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
        jArr2[4] = jArr[4];
        jArr2[5] = jArr[5];
        jArr2[6] = jArr[6];
    }

    public static long[] fromBigInteger(BigInteger bigInteger) {
        long[] jArrFromBigInteger64 = Nat448.fromBigInteger64(bigInteger);
        reduce39(jArrFromBigInteger64, 0);
        return jArrFromBigInteger64;
    }

    public static void implCompactExt(long[] jArr) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        long j6 = jArr[4];
        long j7 = jArr[5];
        long j8 = jArr[6];
        long j9 = jArr[7];
        long j10 = jArr[8];
        long j11 = jArr[9];
        long j12 = jArr[10];
        long j13 = jArr[11];
        long j14 = jArr[12];
        long j15 = jArr[13];
        jArr[0] = j2 ^ (j3 << 59);
        jArr[1] = (j3 >>> 5) ^ (j4 << 54);
        jArr[2] = (j4 >>> 10) ^ (j5 << 49);
        jArr[3] = (j5 >>> 15) ^ (j6 << 44);
        jArr[4] = (j6 >>> 20) ^ (j7 << 39);
        jArr[5] = (j7 >>> 25) ^ (j8 << 34);
        jArr[6] = (j8 >>> 30) ^ (j9 << 29);
        jArr[7] = (j9 >>> 35) ^ (j10 << 24);
        jArr[8] = (j10 >>> 40) ^ (j11 << 19);
        jArr[9] = (j11 >>> 45) ^ (j12 << 14);
        jArr[10] = (j12 >>> 50) ^ (j13 << 9);
        jArr[11] = ((j13 >>> 55) ^ (j14 << 4)) ^ (j15 << 63);
        jArr[12] = (j14 >>> 60) ^ (j15 >>> 1);
        jArr[13] = 0;
    }

    public static void implExpand(long[] jArr, long[] jArr2) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        long j6 = jArr[4];
        long j7 = jArr[5];
        long j8 = jArr[6];
        jArr2[0] = j2 & M59;
        jArr2[1] = ((j2 >>> 59) ^ (j3 << 5)) & M59;
        jArr2[2] = ((j3 >>> 54) ^ (j4 << 10)) & M59;
        jArr2[3] = ((j4 >>> 49) ^ (j5 << 15)) & M59;
        jArr2[4] = ((j5 >>> 44) ^ (j6 << 20)) & M59;
        jArr2[5] = ((j6 >>> 39) ^ (j7 << 25)) & M59;
        jArr2[6] = (j7 >>> 34) ^ (j8 << 30);
    }

    public static void implMultiply(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[7];
        long[] jArr5 = new long[7];
        implExpand(jArr, jArr4);
        implExpand(jArr2, jArr5);
        for (int i2 = 0; i2 < 7; i2++) {
            implMulwAcc(jArr4, jArr5[i2], jArr3, i2);
        }
        implCompactExt(jArr3);
    }

    public static void implMulwAcc(long[] jArr, long j2, long[] jArr2, int i2) {
        long j3 = j2 << 1;
        long j4 = j3 ^ j2;
        long j5 = j2 << 2;
        long j6 = j4 << 1;
        long[] jArr3 = {0, j2, j3, j4, j5, j5 ^ j2, j6, j6 ^ j2};
        for (int i3 = 0; i3 < 7; i3++) {
            long j7 = jArr[i3];
            int i4 = (int) j7;
            long j8 = jArr3[i4 & 7] ^ (jArr3[(i4 >>> 3) & 7] << 3);
            long j9 = 0;
            int i5 = 54;
            do {
                int i6 = (int) (j7 >>> i5);
                long j10 = (jArr3[(i6 >>> 3) & 7] << 3) ^ jArr3[i6 & 7];
                j8 ^= j10 << i5;
                j9 ^= j10 >>> (-i5);
                i5 -= 6;
            } while (i5 > 0);
            int i7 = i2 + i3;
            jArr2[i7] = jArr2[i7] ^ (M59 & j8);
            int i8 = i7 + 1;
            jArr2[i8] = jArr2[i8] ^ ((j8 >>> 59) ^ (j9 << 5));
        }
    }

    public static void implSquare(long[] jArr, long[] jArr2) {
        for (int i2 = 0; i2 < 6; i2++) {
            Interleave.expand64To128(jArr[i2], jArr2, i2 << 1);
        }
        jArr2[12] = Interleave.expand32to64((int) jArr[6]);
    }

    public static void invert(long[] jArr, long[] jArr2) {
        if (Nat448.isZero64(jArr)) {
            throw new IllegalStateException();
        }
        long[] jArrCreate64 = Nat448.create64();
        long[] jArrCreate642 = Nat448.create64();
        long[] jArrCreate643 = Nat448.create64();
        square(jArr, jArrCreate64);
        squareN(jArrCreate64, 1, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate64);
        squareN(jArrCreate642, 1, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate64);
        squareN(jArrCreate64, 3, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate64);
        squareN(jArrCreate64, 6, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate64);
        squareN(jArrCreate64, 12, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate643);
        squareN(jArrCreate643, 24, jArrCreate64);
        squareN(jArrCreate64, 24, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate64);
        squareN(jArrCreate64, 48, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate64);
        squareN(jArrCreate64, 96, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate64);
        squareN(jArrCreate64, 192, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate64);
        multiply(jArrCreate64, jArrCreate643, jArr2);
    }

    public static void multiply(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrCreateExt64 = Nat448.createExt64();
        implMultiply(jArr, jArr2, jArrCreateExt64);
        reduce(jArrCreateExt64, jArr3);
    }

    public static void multiplyAddToExt(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrCreateExt64 = Nat448.createExt64();
        implMultiply(jArr, jArr2, jArrCreateExt64);
        addExt(jArr3, jArrCreateExt64, jArr3);
    }

    public static void reduce(long[] jArr, long[] jArr2) {
        long j2 = jArr[0];
        long j3 = jArr[1];
        long j4 = jArr[2];
        long j5 = jArr[3];
        long j6 = jArr[4];
        long j7 = jArr[5];
        long j8 = jArr[6];
        long j9 = jArr[7];
        long j10 = jArr[12];
        long j11 = j7 ^ (j10 << 39);
        long j12 = j8 ^ ((j10 >>> 25) ^ (j10 << 62));
        long j13 = j9 ^ (j10 >>> 2);
        long j14 = jArr[11];
        long j15 = j6 ^ (j14 << 39);
        long j16 = j11 ^ ((j14 >>> 25) ^ (j14 << 62));
        long j17 = j12 ^ (j14 >>> 2);
        long j18 = jArr[10];
        long j19 = j5 ^ (j18 << 39);
        long j20 = j15 ^ ((j18 >>> 25) ^ (j18 << 62));
        long j21 = j16 ^ (j18 >>> 2);
        long j22 = jArr[9];
        long j23 = j4 ^ (j22 << 39);
        long j24 = j19 ^ ((j22 >>> 25) ^ (j22 << 62));
        long j25 = j20 ^ (j22 >>> 2);
        long j26 = jArr[8];
        long j27 = j2 ^ (j13 << 39);
        long j28 = (j3 ^ (j26 << 39)) ^ ((j13 >>> 25) ^ (j13 << 62));
        long j29 = (j23 ^ ((j26 >>> 25) ^ (j26 << 62))) ^ (j13 >>> 2);
        long j30 = j17 >>> 25;
        jArr2[0] = j27 ^ j30;
        jArr2[1] = (j30 << 23) ^ j28;
        jArr2[2] = j29;
        jArr2[3] = j24 ^ (j26 >>> 2);
        jArr2[4] = j25;
        jArr2[5] = j21;
        jArr2[6] = j17 & M25;
    }

    public static void reduce39(long[] jArr, int i2) {
        int i3 = i2 + 6;
        long j2 = jArr[i3];
        long j3 = j2 >>> 25;
        jArr[i2] = jArr[i2] ^ j3;
        int i4 = i2 + 1;
        jArr[i4] = (j3 << 23) ^ jArr[i4];
        jArr[i3] = j2 & M25;
    }

    public static void sqrt(long[] jArr, long[] jArr2) {
        long jUnshuffle = Interleave.unshuffle(jArr[0]);
        long jUnshuffle2 = Interleave.unshuffle(jArr[1]);
        long j2 = (jUnshuffle & BodyPartID.bodyIdMax) | (jUnshuffle2 << 32);
        long j3 = (jUnshuffle >>> 32) | (jUnshuffle2 & (-4294967296L));
        long jUnshuffle3 = Interleave.unshuffle(jArr[2]);
        long jUnshuffle4 = Interleave.unshuffle(jArr[3]);
        long j4 = (jUnshuffle3 & BodyPartID.bodyIdMax) | (jUnshuffle4 << 32);
        long j5 = (jUnshuffle3 >>> 32) | (jUnshuffle4 & (-4294967296L));
        long jUnshuffle5 = Interleave.unshuffle(jArr[4]);
        long jUnshuffle6 = Interleave.unshuffle(jArr[5]);
        long j6 = (jUnshuffle5 & BodyPartID.bodyIdMax) | (jUnshuffle6 << 32);
        long j7 = (jUnshuffle5 >>> 32) | (jUnshuffle6 & (-4294967296L));
        long jUnshuffle7 = Interleave.unshuffle(jArr[6]);
        long j8 = jUnshuffle7 & BodyPartID.bodyIdMax;
        long j9 = jUnshuffle7 >>> 32;
        jArr2[0] = j2 ^ (j3 << 44);
        jArr2[1] = (j4 ^ (j5 << 44)) ^ (j3 >>> 20);
        jArr2[2] = (j6 ^ (j7 << 44)) ^ (j5 >>> 20);
        jArr2[3] = (((j9 << 44) ^ j8) ^ (j7 >>> 20)) ^ (j3 << 13);
        jArr2[4] = (j3 >>> 51) ^ ((jUnshuffle7 >>> 52) ^ (j5 << 13));
        jArr2[5] = (j7 << 13) ^ (j5 >>> 51);
        jArr2[6] = (j9 << 13) ^ (j7 >>> 51);
    }

    public static void square(long[] jArr, long[] jArr2) {
        long[] jArrCreate64 = Nat.create64(13);
        implSquare(jArr, jArrCreate64);
        reduce(jArrCreate64, jArr2);
    }

    public static void squareAddToExt(long[] jArr, long[] jArr2) {
        long[] jArrCreate64 = Nat.create64(13);
        implSquare(jArr, jArrCreate64);
        addExt(jArr2, jArrCreate64, jArr2);
    }

    public static void squareN(long[] jArr, int i2, long[] jArr2) {
        long[] jArrCreate64 = Nat.create64(13);
        implSquare(jArr, jArrCreate64);
        reduce(jArrCreate64, jArr2);
        while (true) {
            i2--;
            if (i2 <= 0) {
                return;
            }
            implSquare(jArr2, jArrCreate64);
            reduce(jArrCreate64, jArr2);
        }
    }

    public static int trace(long[] jArr) {
        return ((int) jArr[0]) & 1;
    }
}
