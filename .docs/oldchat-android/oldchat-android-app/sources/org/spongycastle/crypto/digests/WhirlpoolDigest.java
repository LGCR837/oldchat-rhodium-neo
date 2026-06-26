package org.spongycastle.crypto.digests;

import org.spongycastle.asn1.eac.CertificateBody;
import org.spongycastle.asn1.x509.DisplayText;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.crypto.tls.ExtensionType;
import org.spongycastle.math.Primes;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Memoable;

/* JADX INFO: loaded from: classes.dex */
public final class WhirlpoolDigest implements ExtendedDigest, Memoable {
    private static final int BITCOUNT_ARRAY_SIZE = 32;
    private static final int BYTE_LENGTH = 64;
    private static final int DIGEST_LENGTH_BYTES = 64;
    private static final short[] EIGHT;
    private static final int REDUCTION_POLYNOMIAL = 285;
    private static final int ROUNDS = 10;
    private long[] _K;
    private long[] _L;
    private short[] _bitCount;
    private long[] _block;
    private byte[] _buffer;
    private int _bufferPos;
    private long[] _hash;
    private final long[] _rc;
    private long[] _state;
    private static final int[] SBOX = {24, 35, 198, 232, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA256, 1, 79, 54, CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256, 210, 245, 121, 111, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA, 82, 96, 188, CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA, CipherSuite.TLS_DHE_PSK_WITH_RC4_128_SHA, CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384, 12, 123, 53, 29, 224, 215, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256, 46, 75, 254, 87, 21, 119, 55, 229, CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, 240, 74, 218, 88, 201, 41, 10, CipherSuite.TLS_PSK_WITH_NULL_SHA384, CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA256, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, 93, 16, 244, 203, 62, 5, 103, 228, 39, 65, CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA, CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384, 125, CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA, 216, 251, 238, 124, 102, 221, 23, 71, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, 202, 45, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, 7, CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384, 90, 131, 51, 99, 2, CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256, 113, DisplayText.DISPLAY_TEXT_MAXIMUM_SIZE, 25, 73, 217, 242, 227, 91, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA, 38, 50, CipherSuite.TLS_PSK_WITH_NULL_SHA256, 233, 15, 213, 128, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256, 205, 52, 72, 255, 122, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, 95, 32, 104, 26, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256, 84, CipherSuite.TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA, 34, 100, 241, 115, 18, 64, 8, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256, 236, 219, CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA, 61, CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA, 0, 207, 43, 118, 130, 214, 27, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384, CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_DHE_DSS_WITH_AES_256_CBC_SHA256, 80, 69, 243, 48, 239, 63, 85, CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, 234, ExtensionType.negotiated_ff_dhe_groups, CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256, 47, 192, 222, 28, 253, 77, CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA, 117, 6, CipherSuite.TLS_PSK_WITH_RC4_128_SHA, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256, 230, 14, 31, 98, 212, CipherSuite.TLS_PSK_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA, 249, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 37, 89, CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, 114, 57, 76, 94, 120, 56, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA, 209, CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384, 226, 97, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384, 33, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, 30, 67, 199, 252, 4, 81, CipherSuite.TLS_DHE_DSS_WITH_SEED_CBC_SHA, CipherSuite.TLS_DH_anon_WITH_AES_256_CBC_SHA256, 13, 250, 223, 126, 36, 59, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384, 206, 17, CipherSuite.TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA, 78, CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384, 235, 60, 129, CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA, 247, CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA384, 19, 44, Primes.SMALL_FACTOR_LIMIT, 231, 110, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256, 3, 86, 68, CertificateBody.profileType, CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384, 42, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, 83, 220, 11, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256, 49, 116, 246, 70, CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA, 20, 225, 22, 58, CipherSuite.TLS_DH_RSA_WITH_AES_256_CBC_SHA256, 9, 112, CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA256, 208, 237, 204, 66, CipherSuite.TLS_DH_RSA_WITH_SEED_CBC_SHA, CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256, 40, 92, 248, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA};
    private static final long[] C0 = new long[256];
    private static final long[] C1 = new long[256];
    private static final long[] C2 = new long[256];
    private static final long[] C3 = new long[256];
    private static final long[] C4 = new long[256];
    private static final long[] C5 = new long[256];
    private static final long[] C6 = new long[256];
    private static final long[] C7 = new long[256];

    static {
        short[] sArr = new short[32];
        EIGHT = sArr;
        sArr[31] = 8;
    }

    public WhirlpoolDigest() {
        this._rc = new long[11];
        this._buffer = new byte[64];
        this._bufferPos = 0;
        this._bitCount = new short[32];
        this._hash = new long[8];
        this._K = new long[8];
        this._L = new long[8];
        this._block = new long[8];
        this._state = new long[8];
        for (int i2 = 0; i2 < 256; i2++) {
            int i3 = SBOX[i2];
            int iMaskWithReductionPolynomial = maskWithReductionPolynomial(i3 << 1);
            int iMaskWithReductionPolynomial2 = maskWithReductionPolynomial(iMaskWithReductionPolynomial << 1);
            int i4 = iMaskWithReductionPolynomial2 ^ i3;
            int iMaskWithReductionPolynomial3 = maskWithReductionPolynomial(iMaskWithReductionPolynomial2 << 1);
            int i5 = iMaskWithReductionPolynomial3 ^ i3;
            C0[i2] = packIntoLong(i3, i3, iMaskWithReductionPolynomial2, i3, iMaskWithReductionPolynomial3, i4, iMaskWithReductionPolynomial, i5);
            C1[i2] = packIntoLong(i5, i3, i3, iMaskWithReductionPolynomial2, i3, iMaskWithReductionPolynomial3, i4, iMaskWithReductionPolynomial);
            C2[i2] = packIntoLong(iMaskWithReductionPolynomial, i5, i3, i3, iMaskWithReductionPolynomial2, i3, iMaskWithReductionPolynomial3, i4);
            C3[i2] = packIntoLong(i4, iMaskWithReductionPolynomial, i5, i3, i3, iMaskWithReductionPolynomial2, i3, iMaskWithReductionPolynomial3);
            C4[i2] = packIntoLong(iMaskWithReductionPolynomial3, i4, iMaskWithReductionPolynomial, i5, i3, i3, iMaskWithReductionPolynomial2, i3);
            C5[i2] = packIntoLong(i3, iMaskWithReductionPolynomial3, i4, iMaskWithReductionPolynomial, i5, i3, i3, iMaskWithReductionPolynomial2);
            C6[i2] = packIntoLong(iMaskWithReductionPolynomial2, i3, iMaskWithReductionPolynomial3, i4, iMaskWithReductionPolynomial, i5, i3, i3);
            C7[i2] = packIntoLong(i3, iMaskWithReductionPolynomial2, i3, iMaskWithReductionPolynomial3, i4, iMaskWithReductionPolynomial, i5, i3);
        }
        this._rc[0] = 0;
        for (int i6 = 1; i6 <= 10; i6++) {
            int i7 = (i6 - 1) * 8;
            this._rc[i6] = (((((((C0[i7] & (-72057594037927936L)) ^ (C1[i7 + 1] & 71776119061217280L)) ^ (C2[i7 + 2] & 280375465082880L)) ^ (C3[i7 + 3] & 1095216660480L)) ^ (C4[i7 + 4] & 4278190080L)) ^ (C5[i7 + 5] & 16711680)) ^ (C6[i7 + 6] & 65280)) ^ (C7[i7 + 7] & 255);
        }
    }

    private long bytesToLongFromBuffer(byte[] bArr, int i2) {
        return (((long) bArr[i2 + 7]) & 255) | ((((long) bArr[i2]) & 255) << 56) | ((((long) bArr[i2 + 1]) & 255) << 48) | ((((long) bArr[i2 + 2]) & 255) << 40) | ((((long) bArr[i2 + 3]) & 255) << 32) | ((((long) bArr[i2 + 4]) & 255) << 24) | ((((long) bArr[i2 + 5]) & 255) << 16) | ((((long) bArr[i2 + 6]) & 255) << 8);
    }

    private void convertLongToByteArray(long j2, byte[] bArr, int i2) {
        for (int i3 = 0; i3 < 8; i3++) {
            bArr[i2 + i3] = (byte) ((j2 >> (56 - (i3 * 8))) & 255);
        }
    }

    private byte[] copyBitLength() {
        byte[] bArr = new byte[32];
        for (int i2 = 0; i2 < 32; i2++) {
            bArr[i2] = (byte) (this._bitCount[i2] & 255);
        }
        return bArr;
    }

    private void finish() {
        byte[] bArrCopyBitLength = copyBitLength();
        byte[] bArr = this._buffer;
        int i2 = this._bufferPos;
        int i3 = i2 + 1;
        this._bufferPos = i3;
        bArr[i2] = (byte) (bArr[i2] | 128);
        if (i3 == bArr.length) {
            processFilledBuffer(bArr, 0);
        }
        if (this._bufferPos > 32) {
            while (this._bufferPos != 0) {
                update((byte) 0);
            }
        }
        while (this._bufferPos <= 32) {
            update((byte) 0);
        }
        System.arraycopy(bArrCopyBitLength, 0, this._buffer, 32, bArrCopyBitLength.length);
        processFilledBuffer(this._buffer, 0);
    }

    private void increment() {
        int i2 = 0;
        for (int length = this._bitCount.length - 1; length >= 0; length--) {
            short[] sArr = this._bitCount;
            int i3 = (sArr[length] & 255) + EIGHT[length] + i2;
            i2 = i3 >>> 8;
            sArr[length] = (short) (i3 & 255);
        }
    }

    private int maskWithReductionPolynomial(int i2) {
        return ((long) i2) >= 256 ? i2 ^ REDUCTION_POLYNOMIAL : i2;
    }

    private long packIntoLong(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        return (((((((((long) i3) << 48) ^ (((long) i2) << 56)) ^ (((long) i4) << 40)) ^ (((long) i5) << 32)) ^ (((long) i6) << 24)) ^ (((long) i7) << 16)) ^ (((long) i8) << 8)) ^ ((long) i9);
    }

    private void processFilledBuffer(byte[] bArr, int i2) {
        for (int i3 = 0; i3 < this._state.length; i3++) {
            this._block[i3] = bytesToLongFromBuffer(this._buffer, i3 * 8);
        }
        processBlock();
        this._bufferPos = 0;
        Arrays.fill(this._buffer, (byte) 0);
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new WhirlpoolDigest(this);
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i2) {
        finish();
        for (int i3 = 0; i3 < 8; i3++) {
            convertLongToByteArray(this._hash[i3], bArr, (i3 * 8) + i2);
        }
        reset();
        return getDigestSize();
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Whirlpool";
    }

    @Override // org.spongycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 64;
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return 64;
    }

    public void processBlock() {
        for (int i2 = 0; i2 < 8; i2++) {
            long[] jArr = this._state;
            long j2 = this._block[i2];
            long[] jArr2 = this._K;
            long j3 = this._hash[i2];
            jArr2[i2] = j3;
            jArr[i2] = j2 ^ j3;
        }
        int i3 = 1;
        while (i3 <= 10) {
            int i4 = 0;
            while (i4 < 8) {
                long[] jArr3 = this._L;
                jArr3[i4] = 0;
                long[] jArr4 = C0;
                long[] jArr5 = this._K;
                long j4 = jArr4[((int) (jArr5[i4 & 7] >>> 56)) & 255];
                jArr3[i4] = j4;
                long j5 = C1[((int) (jArr5[(i4 - 1) & 7] >>> 48)) & 255] ^ j4;
                jArr3[i4] = j5;
                long j6 = j5 ^ C2[((int) (jArr5[(i4 - 2) & 7] >>> 40)) & 255];
                jArr3[i4] = j6;
                long j7 = j6 ^ C3[((int) (jArr5[(i4 - 3) & 7] >>> 32)) & 255];
                jArr3[i4] = j7;
                long j8 = j7 ^ C4[((int) (jArr5[(i4 - 4) & 7] >>> 24)) & 255];
                jArr3[i4] = j8;
                long j9 = j8 ^ C5[((int) (jArr5[(i4 - 5) & 7] >>> 16)) & 255];
                jArr3[i4] = j9;
                long j10 = j9 ^ C6[((int) (jArr5[(i4 - 6) & 7] >>> 8)) & 255];
                jArr3[i4] = j10;
                jArr3[i4] = j10 ^ C7[((int) jArr5[(i4 - 7) & 7]) & 255];
                i4++;
                i3 = i3;
            }
            int i5 = i3;
            long[] jArr6 = this._L;
            long[] jArr7 = this._K;
            System.arraycopy(jArr6, 0, jArr7, 0, jArr7.length);
            long[] jArr8 = this._K;
            jArr8[0] = jArr8[0] ^ this._rc[i5];
            for (int i6 = 0; i6 < 8; i6++) {
                long[] jArr9 = this._L;
                long j11 = this._K[i6];
                jArr9[i6] = j11;
                long[] jArr10 = C0;
                long[] jArr11 = this._state;
                long j12 = jArr10[((int) (jArr11[i6 & 7] >>> 56)) & 255] ^ j11;
                jArr9[i6] = j12;
                long j13 = j12 ^ C1[((int) (jArr11[(i6 - 1) & 7] >>> 48)) & 255];
                jArr9[i6] = j13;
                long j14 = j13 ^ C2[((int) (jArr11[(i6 - 2) & 7] >>> 40)) & 255];
                jArr9[i6] = j14;
                long j15 = j14 ^ C3[((int) (jArr11[(i6 - 3) & 7] >>> 32)) & 255];
                jArr9[i6] = j15;
                long j16 = j15 ^ C4[((int) (jArr11[(i6 - 4) & 7] >>> 24)) & 255];
                jArr9[i6] = j16;
                long j17 = j16 ^ C5[((int) (jArr11[(i6 - 5) & 7] >>> 16)) & 255];
                jArr9[i6] = j17;
                long j18 = j17 ^ C6[((int) (jArr11[(i6 - 6) & 7] >>> 8)) & 255];
                jArr9[i6] = j18;
                jArr9[i6] = j18 ^ C7[((int) jArr11[(i6 - 7) & 7]) & 255];
            }
            long[] jArr12 = this._L;
            long[] jArr13 = this._state;
            System.arraycopy(jArr12, 0, jArr13, 0, jArr13.length);
            i3 = i5 + 1;
        }
        for (int i7 = 0; i7 < 8; i7++) {
            long[] jArr14 = this._hash;
            jArr14[i7] = jArr14[i7] ^ (this._state[i7] ^ this._block[i7]);
        }
    }

    @Override // org.spongycastle.crypto.Digest
    public void reset() {
        this._bufferPos = 0;
        Arrays.fill(this._bitCount, (short) 0);
        Arrays.fill(this._buffer, (byte) 0);
        Arrays.fill(this._hash, 0L);
        Arrays.fill(this._K, 0L);
        Arrays.fill(this._L, 0L);
        Arrays.fill(this._block, 0L);
        Arrays.fill(this._state, 0L);
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte b2) {
        byte[] bArr = this._buffer;
        int i2 = this._bufferPos;
        bArr[i2] = b2;
        int i3 = i2 + 1;
        this._bufferPos = i3;
        if (i3 == bArr.length) {
            processFilledBuffer(bArr, 0);
        }
        increment();
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte[] bArr, int i2, int i3) {
        while (i3 > 0) {
            update(bArr[i2]);
            i2++;
            i3--;
        }
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        WhirlpoolDigest whirlpoolDigest = (WhirlpoolDigest) memoable;
        long[] jArr = whirlpoolDigest._rc;
        long[] jArr2 = this._rc;
        System.arraycopy(jArr, 0, jArr2, 0, jArr2.length);
        byte[] bArr = whirlpoolDigest._buffer;
        byte[] bArr2 = this._buffer;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this._bufferPos = whirlpoolDigest._bufferPos;
        short[] sArr = whirlpoolDigest._bitCount;
        short[] sArr2 = this._bitCount;
        System.arraycopy(sArr, 0, sArr2, 0, sArr2.length);
        long[] jArr3 = whirlpoolDigest._hash;
        long[] jArr4 = this._hash;
        System.arraycopy(jArr3, 0, jArr4, 0, jArr4.length);
        long[] jArr5 = whirlpoolDigest._K;
        long[] jArr6 = this._K;
        System.arraycopy(jArr5, 0, jArr6, 0, jArr6.length);
        long[] jArr7 = whirlpoolDigest._L;
        long[] jArr8 = this._L;
        System.arraycopy(jArr7, 0, jArr8, 0, jArr8.length);
        long[] jArr9 = whirlpoolDigest._block;
        long[] jArr10 = this._block;
        System.arraycopy(jArr9, 0, jArr10, 0, jArr10.length);
        long[] jArr11 = whirlpoolDigest._state;
        long[] jArr12 = this._state;
        System.arraycopy(jArr11, 0, jArr12, 0, jArr12.length);
    }

    public WhirlpoolDigest(WhirlpoolDigest whirlpoolDigest) {
        this._rc = new long[11];
        this._buffer = new byte[64];
        this._bufferPos = 0;
        this._bitCount = new short[32];
        this._hash = new long[8];
        this._K = new long[8];
        this._L = new long[8];
        this._block = new long[8];
        this._state = new long[8];
        reset(whirlpoolDigest);
    }
}
