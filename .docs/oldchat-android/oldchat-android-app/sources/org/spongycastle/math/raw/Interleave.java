package org.spongycastle.math.raw;

import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public class Interleave {
    private static final long M32 = 1431655765;
    private static final long M64 = 6148914691236517205L;

    public static int expand16to32(int i2) {
        int i3 = i2 & 65535;
        int i4 = (i3 | (i3 << 8)) & 16711935;
        int i5 = (i4 | (i4 << 4)) & 252645135;
        int i6 = (i5 | (i5 << 2)) & 858993459;
        return (i6 | (i6 << 1)) & 1431655765;
    }

    public static long expand32to64(int i2) {
        int i3 = ((i2 >>> 8) ^ i2) & CipherSuite.DRAFT_TLS_DHE_RSA_WITH_AES_128_OCB;
        int i4 = i2 ^ (i3 ^ (i3 << 8));
        int i5 = ((i4 >>> 4) ^ i4) & 15728880;
        int i6 = i4 ^ (i5 ^ (i5 << 4));
        int i7 = ((i6 >>> 2) ^ i6) & 202116108;
        int i8 = i6 ^ (i7 ^ (i7 << 2));
        int i9 = ((i8 >>> 1) ^ i8) & 572662306;
        int i10 = i8 ^ (i9 ^ (i9 << 1));
        return ((((long) (i10 >>> 1)) & M32) << 32) | (M32 & ((long) i10));
    }

    public static void expand64To128(long j2, long[] jArr, int i2) {
        long j3 = ((j2 >>> 16) ^ j2) & 4294901760L;
        long j4 = j2 ^ (j3 ^ (j3 << 16));
        long j5 = ((j4 >>> 8) ^ j4) & 280375465148160L;
        long j6 = j4 ^ (j5 ^ (j5 << 8));
        long j7 = ((j6 >>> 4) ^ j6) & 67555025218437360L;
        long j8 = j6 ^ (j7 ^ (j7 << 4));
        long j9 = ((j8 >>> 2) ^ j8) & 868082074056920076L;
        long j10 = j8 ^ (j9 ^ (j9 << 2));
        long j11 = ((j10 >>> 1) ^ j10) & 2459565876494606882L;
        long j12 = j10 ^ (j11 ^ (j11 << 1));
        jArr[i2] = j12 & M64;
        jArr[i2 + 1] = (j12 >>> 1) & M64;
    }

    public static int expand8to16(int i2) {
        int i3 = i2 & 255;
        int i4 = (i3 | (i3 << 4)) & 3855;
        int i5 = (i4 | (i4 << 2)) & 13107;
        return (i5 | (i5 << 1)) & 21845;
    }

    public static long unshuffle(long j2) {
        long j3 = ((j2 >>> 1) ^ j2) & 2459565876494606882L;
        long j4 = j2 ^ (j3 ^ (j3 << 1));
        long j5 = ((j4 >>> 2) ^ j4) & 868082074056920076L;
        long j6 = j4 ^ (j5 ^ (j5 << 2));
        long j7 = ((j6 >>> 4) ^ j6) & 67555025218437360L;
        long j8 = j6 ^ (j7 ^ (j7 << 4));
        long j9 = ((j8 >>> 8) ^ j8) & 280375465148160L;
        long j10 = j8 ^ (j9 ^ (j9 << 8));
        long j11 = ((j10 >>> 16) ^ j10) & 4294901760L;
        return j10 ^ (j11 ^ (j11 << 16));
    }
}
