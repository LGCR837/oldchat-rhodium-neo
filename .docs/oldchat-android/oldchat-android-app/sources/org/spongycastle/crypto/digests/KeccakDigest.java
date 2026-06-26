package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class KeccakDigest implements ExtendedDigest {
    protected int bitsInQueue;
    protected byte[] dataQueue;
    protected int fixedOutputLength;
    protected int rate;
    protected boolean squeezing;
    protected long[] state;
    private static long[] KeccakRoundConstants = keccakInitializeRoundConstants();
    private static int[] KeccakRhoOffsets = keccakInitializeRhoOffsets();

    public KeccakDigest() {
        this(288);
    }

    private void KeccakAbsorb(byte[] bArr, int i2) {
        int i3 = this.rate >> 6;
        for (int i4 = 0; i4 < i3; i4++) {
            long[] jArr = this.state;
            jArr[i4] = jArr[i4] ^ Pack.littleEndianToLong(bArr, i2);
            i2 += 8;
        }
        KeccakPermutation();
    }

    private void KeccakExtract() {
        Pack.longToLittleEndian(this.state, 0, this.rate >> 6, this.dataQueue, 0);
    }

    private void KeccakPermutation() {
        for (int i2 = 0; i2 < 24; i2++) {
            theta(this.state);
            rho(this.state);
            pi(this.state);
            chi(this.state);
            iota(this.state, i2);
        }
    }

    private static boolean LFSR86540(byte[] bArr) {
        byte b2 = bArr[0];
        boolean z2 = (b2 & 1) != 0;
        if ((b2 & 128) != 0) {
            bArr[0] = (byte) ((b2 << 1) ^ 113);
        } else {
            bArr[0] = (byte) (b2 << 1);
        }
        return z2;
    }

    private static void chi(long[] jArr) {
        for (int i2 = 0; i2 < 25; i2 += 5) {
            long j2 = jArr[i2];
            int i3 = i2 + 1;
            long j3 = jArr[i3];
            int i4 = i2 + 2;
            long j4 = jArr[i4];
            long j5 = ((j3 ^ (-1)) & j4) ^ j2;
            int i5 = i2 + 3;
            long j6 = jArr[i5];
            long j7 = ((j4 ^ (-1)) & j6) ^ j3;
            int i6 = i2 + 4;
            long j8 = jArr[i6];
            long j9 = j4 ^ ((j6 ^ (-1)) & j8);
            jArr[i2] = j5;
            jArr[i3] = j7;
            jArr[i4] = j9;
            jArr[i5] = j6 ^ ((j8 ^ (-1)) & j2);
            jArr[i6] = j8 ^ ((j2 ^ (-1)) & j3);
        }
    }

    private void init(int i2) {
        if (i2 != 128 && i2 != 224 && i2 != 256 && i2 != 288 && i2 != 384 && i2 != 512) {
            throw new IllegalArgumentException("bitLength must be one of 128, 224, 256, 288, 384, or 512.");
        }
        initSponge(1600 - (i2 << 1));
    }

    private void initSponge(int i2) {
        if (i2 <= 0 || i2 >= 1600 || i2 % 64 != 0) {
            throw new IllegalStateException("invalid rate value");
        }
        this.rate = i2;
        int i3 = 0;
        while (true) {
            long[] jArr = this.state;
            if (i3 >= jArr.length) {
                Arrays.fill(this.dataQueue, (byte) 0);
                this.bitsInQueue = 0;
                this.squeezing = false;
                this.fixedOutputLength = (1600 - i2) / 2;
                return;
            }
            jArr[i3] = 0;
            i3++;
        }
    }

    private static void iota(long[] jArr, int i2) {
        jArr[0] = jArr[0] ^ KeccakRoundConstants[i2];
    }

    private static int[] keccakInitializeRhoOffsets() {
        int[] iArr = new int[25];
        int i2 = 0;
        iArr[0] = 0;
        int i3 = 0;
        int i4 = 1;
        while (i2 < 24) {
            int i5 = i2 + 1;
            iArr[(i4 % 5) + ((i3 % 5) * 5)] = (((i2 + 2) * i5) / 2) % 64;
            int i6 = i3 % 5;
            i3 = ((i4 * 2) + (i3 * 3)) % 5;
            i4 = i6;
            i2 = i5;
        }
        return iArr;
    }

    private static long[] keccakInitializeRoundConstants() {
        long[] jArr = new long[24];
        byte[] bArr = {1};
        for (int i2 = 0; i2 < 24; i2++) {
            jArr[i2] = 0;
            for (int i3 = 0; i3 < 7; i3++) {
                int i4 = (1 << i3) - 1;
                if (LFSR86540(bArr)) {
                    jArr[i2] = jArr[i2] ^ (1 << i4);
                }
            }
        }
        return jArr;
    }

    private static long leftRotate(long j2, int i2) {
        return (j2 >>> (-i2)) | (j2 << i2);
    }

    private void padAndSwitchToSqueezingPhase() {
        byte[] bArr = this.dataQueue;
        int i2 = this.bitsInQueue;
        int i3 = i2 >> 3;
        bArr[i3] = (byte) (bArr[i3] | ((byte) (1 << (i2 & 7))));
        int i4 = i2 + 1;
        this.bitsInQueue = i4;
        if (i4 == this.rate) {
            KeccakAbsorb(bArr, 0);
            this.bitsInQueue = 0;
        }
        int i5 = this.bitsInQueue;
        int i6 = i5 >> 6;
        int i7 = i5 & 63;
        int i8 = 0;
        for (int i9 = 0; i9 < i6; i9++) {
            long[] jArr = this.state;
            jArr[i9] = jArr[i9] ^ Pack.littleEndianToLong(this.dataQueue, i8);
            i8 += 8;
        }
        if (i7 > 0) {
            long j2 = (1 << i7) - 1;
            long[] jArr2 = this.state;
            jArr2[i6] = jArr2[i6] ^ (j2 & Pack.littleEndianToLong(this.dataQueue, i8));
        }
        long[] jArr3 = this.state;
        int i10 = (this.rate - 1) >> 6;
        jArr3[i10] = jArr3[i10] ^ Long.MIN_VALUE;
        KeccakPermutation();
        KeccakExtract();
        this.bitsInQueue = this.rate;
        this.squeezing = true;
    }

    private static void pi(long[] jArr) {
        long j2 = jArr[1];
        jArr[1] = jArr[6];
        jArr[6] = jArr[9];
        jArr[9] = jArr[22];
        jArr[22] = jArr[14];
        jArr[14] = jArr[20];
        jArr[20] = jArr[2];
        jArr[2] = jArr[12];
        jArr[12] = jArr[13];
        jArr[13] = jArr[19];
        jArr[19] = jArr[23];
        jArr[23] = jArr[15];
        jArr[15] = jArr[4];
        jArr[4] = jArr[24];
        jArr[24] = jArr[21];
        jArr[21] = jArr[8];
        jArr[8] = jArr[16];
        jArr[16] = jArr[5];
        jArr[5] = jArr[3];
        jArr[3] = jArr[18];
        jArr[18] = jArr[17];
        jArr[17] = jArr[11];
        jArr[11] = jArr[7];
        jArr[7] = jArr[10];
        jArr[10] = j2;
    }

    private static void rho(long[] jArr) {
        for (int i2 = 1; i2 < 25; i2++) {
            jArr[i2] = leftRotate(jArr[i2], KeccakRhoOffsets[i2]);
        }
    }

    private static void theta(long[] jArr) {
        long j2 = (((jArr[0] ^ jArr[5]) ^ jArr[10]) ^ jArr[15]) ^ jArr[20];
        long j3 = (((jArr[1] ^ jArr[6]) ^ jArr[11]) ^ jArr[16]) ^ jArr[21];
        long j4 = (((jArr[2] ^ jArr[7]) ^ jArr[12]) ^ jArr[17]) ^ jArr[22];
        long j5 = (((jArr[3] ^ jArr[8]) ^ jArr[13]) ^ jArr[18]) ^ jArr[23];
        long j6 = (((jArr[4] ^ jArr[9]) ^ jArr[14]) ^ jArr[19]) ^ jArr[24];
        long jLeftRotate = leftRotate(j3, 1) ^ j6;
        jArr[0] = jArr[0] ^ jLeftRotate;
        jArr[5] = jArr[5] ^ jLeftRotate;
        jArr[10] = jArr[10] ^ jLeftRotate;
        jArr[15] = jArr[15] ^ jLeftRotate;
        jArr[20] = jArr[20] ^ jLeftRotate;
        long jLeftRotate2 = leftRotate(j4, 1) ^ j2;
        jArr[1] = jArr[1] ^ jLeftRotate2;
        jArr[6] = jArr[6] ^ jLeftRotate2;
        jArr[11] = jArr[11] ^ jLeftRotate2;
        jArr[16] = jArr[16] ^ jLeftRotate2;
        jArr[21] = jLeftRotate2 ^ jArr[21];
        long jLeftRotate3 = leftRotate(j5, 1) ^ j3;
        jArr[2] = jArr[2] ^ jLeftRotate3;
        jArr[7] = jArr[7] ^ jLeftRotate3;
        jArr[12] = jArr[12] ^ jLeftRotate3;
        jArr[17] = jArr[17] ^ jLeftRotate3;
        jArr[22] = jLeftRotate3 ^ jArr[22];
        long jLeftRotate4 = leftRotate(j6, 1) ^ j4;
        jArr[3] = jArr[3] ^ jLeftRotate4;
        jArr[8] = jArr[8] ^ jLeftRotate4;
        jArr[13] = jArr[13] ^ jLeftRotate4;
        jArr[18] = jArr[18] ^ jLeftRotate4;
        jArr[23] = jLeftRotate4 ^ jArr[23];
        long jLeftRotate5 = leftRotate(j2, 1) ^ j5;
        jArr[4] = jArr[4] ^ jLeftRotate5;
        jArr[9] = jArr[9] ^ jLeftRotate5;
        jArr[14] = jArr[14] ^ jLeftRotate5;
        jArr[19] = jArr[19] ^ jLeftRotate5;
        jArr[24] = jLeftRotate5 ^ jArr[24];
    }

    public void absorb(byte[] bArr, int i2, int i3) {
        int i4;
        int i5 = this.bitsInQueue;
        if (i5 % 8 != 0) {
            throw new IllegalStateException("attempt to absorb with odd length queue");
        }
        if (this.squeezing) {
            throw new IllegalStateException("attempt to absorb while squeezing");
        }
        int i6 = i5 >> 3;
        int i7 = this.rate >> 3;
        int i8 = 0;
        while (i8 < i3) {
            if (i6 != 0 || i8 > (i4 = i3 - i7)) {
                int iMin = Math.min(i7 - i6, i3 - i8);
                System.arraycopy(bArr, i2 + i8, this.dataQueue, i6, iMin);
                i6 += iMin;
                i8 += iMin;
                if (i6 == i7) {
                    KeccakAbsorb(this.dataQueue, 0);
                    i6 = 0;
                }
            } else {
                do {
                    KeccakAbsorb(bArr, i2 + i8);
                    i8 += i7;
                } while (i8 <= i4);
            }
        }
        this.bitsInQueue = i6 << 3;
    }

    public void absorbBits(int i2, int i3) {
        if (i3 < 1 || i3 > 7) {
            throw new IllegalArgumentException("'bits' must be in the range 1 to 7");
        }
        int i4 = this.bitsInQueue;
        if (i4 % 8 != 0) {
            throw new IllegalStateException("attempt to absorb with odd length queue");
        }
        if (this.squeezing) {
            throw new IllegalStateException("attempt to absorb while squeezing");
        }
        this.dataQueue[i4 >> 3] = (byte) (i2 & ((1 << i3) - 1));
        this.bitsInQueue = i4 + i3;
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i2) {
        squeeze(bArr, i2, this.fixedOutputLength);
        reset();
        return getDigestSize();
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Keccak-" + this.fixedOutputLength;
    }

    @Override // org.spongycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return this.rate / 8;
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return this.fixedOutputLength / 8;
    }

    @Override // org.spongycastle.crypto.Digest
    public void reset() {
        init(this.fixedOutputLength);
    }

    public void squeeze(byte[] bArr, int i2, long j2) {
        if (!this.squeezing) {
            padAndSwitchToSqueezingPhase();
        }
        long j3 = 0;
        if (j2 % 8 != 0) {
            throw new IllegalStateException("outputLength not a multiple of 8");
        }
        while (j3 < j2) {
            if (this.bitsInQueue == 0) {
                KeccakPermutation();
                KeccakExtract();
                this.bitsInQueue = this.rate;
            }
            int iMin = (int) Math.min(this.bitsInQueue, j2 - j3);
            System.arraycopy(this.dataQueue, (this.rate - this.bitsInQueue) / 8, bArr, ((int) (j3 / 8)) + i2, iMin / 8);
            this.bitsInQueue -= iMin;
            j3 += (long) iMin;
        }
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte b2) {
        absorb(new byte[]{b2}, 0, 1);
    }

    public KeccakDigest(int i2) {
        this.state = new long[25];
        this.dataQueue = new byte[192];
        init(i2);
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte[] bArr, int i2, int i3) {
        absorb(bArr, i2, i3);
    }

    public int doFinal(byte[] bArr, int i2, byte b2, int i3) {
        if (i3 > 0) {
            absorbBits(b2, i3);
        }
        squeeze(bArr, i2, this.fixedOutputLength);
        reset();
        return getDigestSize();
    }

    public KeccakDigest(KeccakDigest keccakDigest) {
        long[] jArr = new long[25];
        this.state = jArr;
        this.dataQueue = new byte[192];
        long[] jArr2 = keccakDigest.state;
        System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
        byte[] bArr = keccakDigest.dataQueue;
        System.arraycopy(bArr, 0, this.dataQueue, 0, bArr.length);
        this.rate = keccakDigest.rate;
        this.bitsInQueue = keccakDigest.bitsInQueue;
        this.fixedOutputLength = keccakDigest.fixedOutputLength;
        this.squeezing = keccakDigest.squeezing;
    }
}
