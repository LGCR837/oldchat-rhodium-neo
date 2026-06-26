package org.spongycastle.crypto.digests;

import android.R;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class Blake2bDigest implements ExtendedDigest {
    private static final int BLOCK_LENGTH_BYTES = 128;
    private static final long[] blake2b_IV = {7640891576956012808L, -4942790177534073029L, 4354685564936845355L, -6534734903238641935L, 5840696475078001361L, -7276294671716946913L, 2270897969802886507L, 6620516959819538809L};
    private static final byte[][] blake2b_sigma = {new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, new byte[]{14, 10, 4, 8, 9, 15, 13, 6, 1, 12, 0, 2, 11, 7, 5, 3}, new byte[]{11, 8, 12, 0, 5, 2, 15, 13, 10, 14, 3, 6, 7, 1, 9, 4}, new byte[]{7, 9, 3, 1, 13, 12, 11, 14, 2, 6, 5, 10, 4, 0, 15, 8}, new byte[]{9, 0, 5, 7, 2, 4, 10, 15, 14, 1, 11, 12, 6, 8, 3, 13}, new byte[]{2, 12, 6, 10, 0, 11, 8, 3, 4, 13, 7, 5, 15, 14, 1, 9}, new byte[]{12, 5, 1, 15, 14, 13, 4, 10, 0, 7, 6, 3, 9, 2, 8, 11}, new byte[]{13, 11, 7, 14, 12, 1, 3, 9, 5, 0, 15, 4, 8, 6, 2, 10}, new byte[]{6, 15, 14, 9, 11, 3, 0, 8, 12, 2, 13, 7, 1, 4, 10, 5}, new byte[]{10, 2, 8, 4, 7, 6, 1, 5, 15, 11, 9, 14, 3, 12, 13, 0}, new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, new byte[]{14, 10, 4, 8, 9, 15, 13, 6, 1, 12, 0, 2, 11, 7, 5, 3}};
    private static int rOUNDS = 12;
    private byte[] buffer;
    private int bufferPos;
    private long[] chainValue;
    private int digestLength;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    private long f7267f0;
    private long[] internalState;
    private byte[] key;
    private int keyLength;
    private byte[] personalization;
    private byte[] salt;
    private long t0;
    private long t1;

    public Blake2bDigest() {
        this(512);
    }

    private void G(long j2, long j3, int i2, int i3, int i4, int i5) {
        long[] jArr = this.internalState;
        long j4 = jArr[i2] + jArr[i3] + j2;
        jArr[i2] = j4;
        jArr[i5] = rotr64(jArr[i5] ^ j4, 32);
        long[] jArr2 = this.internalState;
        long j5 = jArr2[i4] + jArr2[i5];
        jArr2[i4] = j5;
        jArr2[i3] = rotr64(j5 ^ jArr2[i3], 24);
        long[] jArr3 = this.internalState;
        long j6 = jArr3[i2] + jArr3[i3] + j3;
        jArr3[i2] = j6;
        jArr3[i5] = rotr64(jArr3[i5] ^ j6, 16);
        long[] jArr4 = this.internalState;
        long j7 = jArr4[i4] + jArr4[i5];
        jArr4[i4] = j7;
        jArr4[i3] = rotr64(j7 ^ jArr4[i3], 63);
    }

    private final long bytes2long(byte[] bArr, int i2) {
        return ((((long) bArr[i2 + 7]) & 255) << 56) | (((long) bArr[i2]) & 255) | ((((long) bArr[i2 + 1]) & 255) << 8) | ((((long) bArr[i2 + 2]) & 255) << 16) | ((((long) bArr[i2 + 3]) & 255) << 24) | ((((long) bArr[i2 + 4]) & 255) << 32) | ((((long) bArr[i2 + 5]) & 255) << 40) | ((((long) bArr[i2 + 6]) & 255) << 48);
    }

    private void compress(byte[] bArr, int i2) {
        initializeInternalState();
        long[] jArr = new long[16];
        int i3 = 0;
        for (int i4 = 0; i4 < 16; i4++) {
            jArr[i4] = bytes2long(bArr, (i4 * 8) + i2);
        }
        for (int i5 = 0; i5 < rOUNDS; i5++) {
            byte[][] bArr2 = blake2b_sigma;
            byte[] bArr3 = bArr2[i5];
            G(jArr[bArr3[0]], jArr[bArr3[1]], 0, 4, 8, 12);
            byte[] bArr4 = bArr2[i5];
            G(jArr[bArr4[2]], jArr[bArr4[3]], 1, 5, 9, 13);
            byte[] bArr5 = bArr2[i5];
            G(jArr[bArr5[4]], jArr[bArr5[5]], 2, 6, 10, 14);
            byte[] bArr6 = bArr2[i5];
            G(jArr[bArr6[6]], jArr[bArr6[7]], 3, 7, 11, 15);
            byte[] bArr7 = bArr2[i5];
            G(jArr[bArr7[8]], jArr[bArr7[9]], 0, 5, 10, 15);
            byte[] bArr8 = bArr2[i5];
            G(jArr[bArr8[10]], jArr[bArr8[11]], 1, 6, 11, 12);
            byte[] bArr9 = bArr2[i5];
            G(jArr[bArr9[12]], jArr[bArr9[13]], 2, 7, 8, 13);
            byte[] bArr10 = bArr2[i5];
            G(jArr[bArr10[14]], jArr[bArr10[15]], 3, 4, 9, 14);
        }
        while (true) {
            long[] jArr2 = this.chainValue;
            if (i3 >= jArr2.length) {
                return;
            }
            long j2 = jArr2[i3];
            long[] jArr3 = this.internalState;
            jArr2[i3] = (j2 ^ jArr3[i3]) ^ jArr3[i3 + 8];
            i3++;
        }
    }

    private void init() {
        if (this.chainValue == null) {
            long[] jArr = {jArr[0] ^ ((long) ((this.digestLength | (this.keyLength << 8)) | R.attr.theme)), jArr[1], jArr[2], jArr[3], j, jArr[5], 0, 0};
            this.chainValue = jArr;
            long[] jArr2 = blake2b_IV;
            long j2 = jArr2[4];
            byte[] bArr = this.salt;
            if (bArr != null) {
                jArr[4] = j2 ^ bytes2long(bArr, 0);
                long[] jArr3 = this.chainValue;
                jArr3[5] = jArr3[5] ^ bytes2long(this.salt, 8);
            }
            long[] jArr4 = this.chainValue;
            long j3 = jArr2[6];
            jArr4[6] = j3;
            jArr4[7] = jArr2[7];
            byte[] bArr2 = this.personalization;
            if (bArr2 != null) {
                jArr4[6] = bytes2long(bArr2, 0) ^ j3;
                long[] jArr5 = this.chainValue;
                jArr5[7] = jArr5[7] ^ bytes2long(this.personalization, 8);
            }
        }
    }

    private void initializeInternalState() {
        long[] jArr = this.chainValue;
        System.arraycopy(jArr, 0, this.internalState, 0, jArr.length);
        long[] jArr2 = blake2b_IV;
        System.arraycopy(jArr2, 0, this.internalState, this.chainValue.length, 4);
        long[] jArr3 = this.internalState;
        jArr3[12] = this.t0 ^ jArr2[4];
        jArr3[13] = this.t1 ^ jArr2[5];
        jArr3[14] = this.f7267f0 ^ jArr2[6];
        jArr3[15] = jArr2[7];
    }

    private final byte[] long2bytes(long j2) {
        return new byte[]{(byte) j2, (byte) (j2 >> 8), (byte) (j2 >> 16), (byte) (j2 >> 24), (byte) (j2 >> 32), (byte) (j2 >> 40), (byte) (j2 >> 48), (byte) (j2 >> 56)};
    }

    private long rotr64(long j2, int i2) {
        return (j2 << (64 - i2)) | (j2 >>> i2);
    }

    public void clearKey() {
        byte[] bArr = this.key;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
            Arrays.fill(this.buffer, (byte) 0);
        }
    }

    public void clearSalt() {
        byte[] bArr = this.salt;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i2) {
        long[] jArr;
        int i3;
        this.f7267f0 = -1L;
        long j2 = this.t0;
        int i4 = this.bufferPos;
        long j3 = j2 + ((long) i4);
        this.t0 = j3;
        if (j3 < 0 && i4 > (-j3)) {
            this.t1++;
        }
        compress(this.buffer, 0);
        Arrays.fill(this.buffer, (byte) 0);
        Arrays.fill(this.internalState, 0L);
        int i5 = 0;
        while (true) {
            jArr = this.chainValue;
            if (i5 >= jArr.length || (i3 = i5 * 8) >= this.digestLength) {
                break;
            }
            byte[] bArrLong2bytes = long2bytes(jArr[i5]);
            int i6 = this.digestLength;
            if (i3 < i6 - 8) {
                System.arraycopy(bArrLong2bytes, 0, bArr, i3 + i2, 8);
            } else {
                System.arraycopy(bArrLong2bytes, 0, bArr, i2 + i3, i6 - i3);
            }
            i5++;
        }
        Arrays.fill(jArr, 0L);
        reset();
        return this.digestLength;
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Blake2b";
    }

    @Override // org.spongycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 128;
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return this.digestLength;
    }

    @Override // org.spongycastle.crypto.Digest
    public void reset() {
        this.bufferPos = 0;
        this.f7267f0 = 0L;
        this.t0 = 0L;
        this.t1 = 0L;
        this.chainValue = null;
        Arrays.fill(this.buffer, (byte) 0);
        byte[] bArr = this.key;
        if (bArr != null) {
            System.arraycopy(bArr, 0, this.buffer, 0, bArr.length);
            this.bufferPos = 128;
        }
        init();
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte b2) {
        int i2 = this.bufferPos;
        if (128 - i2 != 0) {
            this.buffer[i2] = b2;
            this.bufferPos = i2 + 1;
            return;
        }
        long j2 = this.t0 + 128;
        this.t0 = j2;
        if (j2 == 0) {
            this.t1++;
        }
        compress(this.buffer, 0);
        Arrays.fill(this.buffer, (byte) 0);
        this.buffer[0] = b2;
        this.bufferPos = 1;
    }

    public Blake2bDigest(Blake2bDigest blake2bDigest) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.t0 = 0L;
        this.t1 = 0L;
        this.f7267f0 = 0L;
        this.bufferPos = blake2bDigest.bufferPos;
        this.buffer = Arrays.clone(blake2bDigest.buffer);
        this.keyLength = blake2bDigest.keyLength;
        this.key = Arrays.clone(blake2bDigest.key);
        this.digestLength = blake2bDigest.digestLength;
        this.chainValue = Arrays.clone(blake2bDigest.chainValue);
        this.personalization = Arrays.clone(blake2bDigest.personalization);
        this.salt = Arrays.clone(blake2bDigest.salt);
        this.t0 = blake2bDigest.t0;
        this.t1 = blake2bDigest.t1;
        this.f7267f0 = blake2bDigest.f7267f0;
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte[] bArr, int i2, int i3) {
        int i4;
        if (bArr == null || i3 == 0) {
            return;
        }
        int i5 = this.bufferPos;
        if (i5 != 0) {
            i4 = 128 - i5;
            if (i4 < i3) {
                System.arraycopy(bArr, i2, this.buffer, i5, i4);
                long j2 = this.t0 + 128;
                this.t0 = j2;
                if (j2 == 0) {
                    this.t1++;
                }
                compress(this.buffer, 0);
                this.bufferPos = 0;
                Arrays.fill(this.buffer, (byte) 0);
            } else {
                System.arraycopy(bArr, i2, this.buffer, i5, i3);
                this.bufferPos += i3;
                return;
            }
        } else {
            i4 = 0;
        }
        int i6 = i3 + i2;
        int i7 = i6 - 128;
        int i8 = i2 + i4;
        while (i8 < i7) {
            long j3 = this.t0 + 128;
            this.t0 = j3;
            if (j3 == 0) {
                this.t1++;
            }
            compress(bArr, i8);
            i8 += 128;
        }
        int i9 = i6 - i8;
        System.arraycopy(bArr, i8, this.buffer, 0, i9);
        this.bufferPos += i9;
    }

    public Blake2bDigest(int i2) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.t0 = 0L;
        this.t1 = 0L;
        this.f7267f0 = 0L;
        if (i2 != 160 && i2 != 256 && i2 != 384 && i2 != 512) {
            throw new IllegalArgumentException("Blake2b digest restricted to one of [160, 256, 384, 512]");
        }
        this.buffer = new byte[128];
        this.keyLength = 0;
        this.digestLength = i2 / 8;
        init();
    }

    public Blake2bDigest(byte[] bArr) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.t0 = 0L;
        this.t1 = 0L;
        this.f7267f0 = 0L;
        this.buffer = new byte[128];
        if (bArr != null) {
            byte[] bArr2 = new byte[bArr.length];
            this.key = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            if (bArr.length <= 64) {
                this.keyLength = bArr.length;
                System.arraycopy(bArr, 0, this.buffer, 0, bArr.length);
                this.bufferPos = 128;
            } else {
                throw new IllegalArgumentException("Keys > 64 are not supported");
            }
        }
        this.digestLength = 64;
        init();
    }

    public Blake2bDigest(byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.t0 = 0L;
        this.t1 = 0L;
        this.f7267f0 = 0L;
        this.buffer = new byte[128];
        if (i2 >= 1 && i2 <= 64) {
            this.digestLength = i2;
            if (bArr2 != null) {
                if (bArr2.length == 16) {
                    byte[] bArr4 = new byte[16];
                    this.salt = bArr4;
                    System.arraycopy(bArr2, 0, bArr4, 0, bArr2.length);
                } else {
                    throw new IllegalArgumentException("salt length must be exactly 16 bytes");
                }
            }
            if (bArr3 != null) {
                if (bArr3.length == 16) {
                    byte[] bArr5 = new byte[16];
                    this.personalization = bArr5;
                    System.arraycopy(bArr3, 0, bArr5, 0, bArr3.length);
                } else {
                    throw new IllegalArgumentException("personalization length must be exactly 16 bytes");
                }
            }
            if (bArr != null) {
                byte[] bArr6 = new byte[bArr.length];
                this.key = bArr6;
                System.arraycopy(bArr, 0, bArr6, 0, bArr.length);
                if (bArr.length <= 64) {
                    this.keyLength = bArr.length;
                    System.arraycopy(bArr, 0, this.buffer, 0, bArr.length);
                    this.bufferPos = 128;
                } else {
                    throw new IllegalArgumentException("Keys > 64 are not supported");
                }
            }
            init();
            return;
        }
        throw new IllegalArgumentException("Invalid digest length (required: 1 - 64)");
    }
}
