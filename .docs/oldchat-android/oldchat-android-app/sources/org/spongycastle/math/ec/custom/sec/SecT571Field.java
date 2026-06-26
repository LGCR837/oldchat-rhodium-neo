package org.spongycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.asn1.cmc.BodyPartID;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.math.raw.Interleave;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat576;

/* JADX INFO: loaded from: classes.dex */
public class SecT571Field {
    private static final long M59 = 576460752303423487L;
    private static final long RM = -1190112520884487202L;
    private static final long[] ROOT_Z = {3161836309350906777L, -7642453882179322845L, -3821226941089661423L, 7312758566309945096L, -556661012383879292L, 8945041530681231562L, -4750851271514160027L, 6847946401097695794L, 541669439031730457L};

    public static void add(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i2 = 0; i2 < 9; i2++) {
            jArr3[i2] = jArr[i2] ^ jArr2[i2];
        }
    }

    public static void addBothTo(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i2 = 0; i2 < 9; i2++) {
            jArr3[i2] = jArr3[i2] ^ (jArr[i2] ^ jArr2[i2]);
        }
    }

    public static void addExt(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i2 = 0; i2 < 18; i2++) {
            jArr3[i2] = jArr[i2] ^ jArr2[i2];
        }
    }

    public static void addOne(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        for (int i2 = 1; i2 < 9; i2++) {
            jArr2[i2] = jArr[i2];
        }
    }

    public static long[] fromBigInteger(BigInteger bigInteger) {
        long[] jArrFromBigInteger64 = Nat576.fromBigInteger64(bigInteger);
        reduce5(jArrFromBigInteger64, 0);
        return jArrFromBigInteger64;
    }

    public static void implMultiply(long[] jArr, long[] jArr2, long[] jArr3) {
        implMultiplyPrecomp(jArr, precompMultiplicand(jArr2), jArr3);
    }

    public static void implMultiplyPrecomp(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i2 = 56; i2 >= 0; i2 -= 8) {
            for (int i3 = 1; i3 < 9; i3 += 2) {
                int i4 = (int) (jArr[i3] >>> i2);
                addBothTo(jArr2, (i4 & 15) * 9, jArr2, (((i4 >>> 4) & 15) + 16) * 9, jArr3, i3 - 1);
            }
            Nat.shiftUpBits64(16, jArr3, 0, 8, 0L);
        }
        for (int i5 = 56; i5 >= 0; i5 -= 8) {
            for (int i6 = 0; i6 < 9; i6 += 2) {
                int i7 = (int) (jArr[i6] >>> i5);
                addBothTo(jArr2, (i7 & 15) * 9, jArr2, (((i7 >>> 4) & 15) + 16) * 9, jArr3, i6);
            }
            if (i5 > 0) {
                Nat.shiftUpBits64(18, jArr3, 0, 8, 0L);
            }
        }
    }

    public static void implMulwAcc(long[] jArr, long j2, long[] jArr2, int i2) {
        long[] jArr3 = new long[32];
        jArr3[1] = j2;
        for (int i3 = 2; i3 < 32; i3 += 2) {
            long j3 = jArr3[i3 >>> 1] << 1;
            jArr3[i3] = j3;
            jArr3[i3 + 1] = j3 ^ j2;
        }
        long j4 = 0;
        int i4 = 0;
        while (i4 < 9) {
            long j5 = jArr[i4];
            long j6 = j4 ^ jArr3[((int) j5) & 31];
            int i5 = 60;
            long j7 = 0;
            do {
                long j8 = jArr3[((int) (j5 >>> i5)) & 31];
                j6 ^= j8 << i5;
                j7 ^= j8 >>> (-i5);
                i5 -= 5;
            } while (i5 > 0);
            for (int i6 = 0; i6 < 4; i6++) {
                j5 = (j5 & RM) >>> 1;
                j7 ^= ((j2 << i6) >> 63) & j5;
            }
            int i7 = i2 + i4;
            jArr2[i7] = j6 ^ jArr2[i7];
            i4++;
            j4 = j7;
        }
        int i8 = i2 + 9;
        jArr2[i8] = jArr2[i8] ^ j4;
    }

    public static void implSquare(long[] jArr, long[] jArr2) {
        for (int i2 = 0; i2 < 9; i2++) {
            Interleave.expand64To128(jArr[i2], jArr2, i2 << 1);
        }
    }

    public static void invert(long[] jArr, long[] jArr2) {
        if (Nat576.isZero64(jArr)) {
            throw new IllegalStateException();
        }
        long[] jArrCreate64 = Nat576.create64();
        long[] jArrCreate642 = Nat576.create64();
        long[] jArrCreate643 = Nat576.create64();
        square(jArr, jArrCreate643);
        square(jArrCreate643, jArrCreate64);
        square(jArrCreate64, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate64);
        squareN(jArrCreate64, 2, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate64);
        multiply(jArrCreate64, jArrCreate643, jArrCreate64);
        squareN(jArrCreate64, 5, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate64);
        squareN(jArrCreate642, 5, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate64);
        squareN(jArrCreate64, 15, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate643);
        squareN(jArrCreate643, 30, jArrCreate64);
        squareN(jArrCreate64, 30, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate64);
        squareN(jArrCreate64, 60, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate64);
        squareN(jArrCreate642, 60, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate64);
        squareN(jArrCreate64, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate64);
        squareN(jArrCreate642, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256, jArrCreate642);
        multiply(jArrCreate64, jArrCreate642, jArrCreate64);
        multiply(jArrCreate64, jArrCreate643, jArr2);
    }

    public static void multiply(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrCreateExt64 = Nat576.createExt64();
        implMultiply(jArr, jArr2, jArrCreateExt64);
        reduce(jArrCreateExt64, jArr3);
    }

    public static void multiplyAddToExt(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrCreateExt64 = Nat576.createExt64();
        implMultiply(jArr, jArr2, jArrCreateExt64);
        addExt(jArr3, jArrCreateExt64, jArr3);
    }

    public static void multiplyPrecomp(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrCreateExt64 = Nat576.createExt64();
        implMultiplyPrecomp(jArr, jArr2, jArrCreateExt64);
        reduce(jArrCreateExt64, jArr3);
    }

    public static void multiplyPrecompAddToExt(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArrCreateExt64 = Nat576.createExt64();
        implMultiplyPrecomp(jArr, jArr2, jArrCreateExt64);
        addExt(jArr3, jArrCreateExt64, jArr3);
    }

    public static long[] precompMultiplicand(long[] jArr) {
        long[] jArr2 = new long[288];
        System.arraycopy(jArr, 0, jArr2, 9, 9);
        int i2 = 7;
        int i3 = 0;
        while (i2 > 0) {
            int i4 = i3 + 18;
            Nat.shiftUpBit64(9, jArr2, i4 >>> 1, 0L, jArr2, i4);
            reduce5(jArr2, i4);
            add(jArr2, 9, jArr2, i4, jArr2, i3 + 27);
            i2--;
            i3 = i4;
        }
        Nat.shiftUpBits64(CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, jArr2, 0, 4, 0L, jArr2, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA);
        return jArr2;
    }

    public static void reduce(long[] jArr, long[] jArr2) {
        long j2 = jArr[9];
        long j3 = jArr[17];
        long j4 = (((j2 ^ (j3 >>> 59)) ^ (j3 >>> 57)) ^ (j3 >>> 54)) ^ (j3 >>> 49);
        long j5 = (j3 << 15) ^ (((jArr[8] ^ (j3 << 5)) ^ (j3 << 7)) ^ (j3 << 10));
        for (int i2 = 16; i2 >= 10; i2--) {
            long j6 = jArr[i2];
            jArr2[i2 - 8] = (((j5 ^ (j6 >>> 59)) ^ (j6 >>> 57)) ^ (j6 >>> 54)) ^ (j6 >>> 49);
            j5 = (((jArr[i2 - 9] ^ (j6 << 5)) ^ (j6 << 7)) ^ (j6 << 10)) ^ (j6 << 15);
        }
        jArr2[1] = (((j5 ^ (j4 >>> 59)) ^ (j4 >>> 57)) ^ (j4 >>> 54)) ^ (j4 >>> 49);
        long j7 = (j4 << 15) ^ (((jArr[0] ^ (j4 << 5)) ^ (j4 << 7)) ^ (j4 << 10));
        long j8 = jArr2[8];
        long j9 = j8 >>> 59;
        jArr2[0] = (((j7 ^ j9) ^ (j9 << 2)) ^ (j9 << 5)) ^ (j9 << 10);
        jArr2[8] = M59 & j8;
    }

    public static void reduce5(long[] jArr, int i2) {
        int i3 = i2 + 8;
        long j2 = jArr[i3];
        long j3 = j2 >>> 59;
        jArr[i2] = ((j3 << 10) ^ (((j3 << 2) ^ j3) ^ (j3 << 5))) ^ jArr[i2];
        jArr[i3] = j2 & M59;
    }

    public static void sqrt(long[] jArr, long[] jArr2) {
        long[] jArrCreate64 = Nat576.create64();
        long[] jArrCreate642 = Nat576.create64();
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            int i4 = i2 + 1;
            long jUnshuffle = Interleave.unshuffle(jArr[i2]);
            i2 += 2;
            long jUnshuffle2 = Interleave.unshuffle(jArr[i4]);
            jArrCreate64[i3] = (BodyPartID.bodyIdMax & jUnshuffle) | (jUnshuffle2 << 32);
            jArrCreate642[i3] = (jUnshuffle >>> 32) | ((-4294967296L) & jUnshuffle2);
        }
        long jUnshuffle3 = Interleave.unshuffle(jArr[i2]);
        jArrCreate64[4] = BodyPartID.bodyIdMax & jUnshuffle3;
        jArrCreate642[4] = jUnshuffle3 >>> 32;
        multiply(jArrCreate642, ROOT_Z, jArr2);
        add(jArr2, jArrCreate64, jArr2);
    }

    public static void square(long[] jArr, long[] jArr2) {
        long[] jArrCreateExt64 = Nat576.createExt64();
        implSquare(jArr, jArrCreateExt64);
        reduce(jArrCreateExt64, jArr2);
    }

    public static void squareAddToExt(long[] jArr, long[] jArr2) {
        long[] jArrCreateExt64 = Nat576.createExt64();
        implSquare(jArr, jArrCreateExt64);
        addExt(jArr2, jArrCreateExt64, jArr2);
    }

    public static void squareN(long[] jArr, int i2, long[] jArr2) {
        long[] jArrCreateExt64 = Nat576.createExt64();
        implSquare(jArr, jArrCreateExt64);
        reduce(jArrCreateExt64, jArr2);
        while (true) {
            i2--;
            if (i2 <= 0) {
                return;
            }
            implSquare(jArr2, jArrCreateExt64);
            reduce(jArrCreateExt64, jArr2);
        }
    }

    public static int trace(long[] jArr) {
        long j2 = jArr[0];
        long j3 = jArr[8];
        return ((int) ((j2 ^ (j3 >>> 49)) ^ (j3 >>> 57))) & 1;
    }

    private static void add(long[] jArr, int i2, long[] jArr2, int i3, long[] jArr3, int i4) {
        for (int i5 = 0; i5 < 9; i5++) {
            jArr3[i4 + i5] = jArr[i2 + i5] ^ jArr2[i3 + i5];
        }
    }

    private static void addBothTo(long[] jArr, int i2, long[] jArr2, int i3, long[] jArr3, int i4) {
        for (int i5 = 0; i5 < 9; i5++) {
            int i6 = i4 + i5;
            jArr3[i6] = jArr3[i6] ^ (jArr[i2 + i5] ^ jArr2[i3 + i5]);
        }
    }
}
