package org.spongycastle.math.raw;

import java.math.BigInteger;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public abstract class Nat576 {
    public static void copy64(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0];
        jArr2[1] = jArr[1];
        jArr2[2] = jArr[2];
        jArr2[3] = jArr[3];
        jArr2[4] = jArr[4];
        jArr2[5] = jArr[5];
        jArr2[6] = jArr[6];
        jArr2[7] = jArr[7];
        jArr2[8] = jArr[8];
    }

    public static long[] create64() {
        return new long[9];
    }

    public static long[] createExt64() {
        return new long[18];
    }

    public static boolean eq64(long[] jArr, long[] jArr2) {
        for (int i2 = 8; i2 >= 0; i2--) {
            if (jArr[i2] != jArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static long[] fromBigInteger64(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 576) {
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

    public static boolean isOne64(long[] jArr) {
        if (jArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < 9; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero64(long[] jArr) {
        for (int i2 = 0; i2 < 9; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static BigInteger toBigInteger64(long[] jArr) {
        byte[] bArr = new byte[72];
        for (int i2 = 0; i2 < 9; i2++) {
            long j2 = jArr[i2];
            if (j2 != 0) {
                Pack.longToBigEndian(j2, bArr, (8 - i2) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }
}
