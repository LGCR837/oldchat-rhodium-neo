package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class KXTSBlockCipher extends BufferedBlockCipher {
    private static final long RED_POLY_128 = 135;
    private static final long RED_POLY_256 = 1061;
    private static final long RED_POLY_512 = 293;
    private final int blockSize;
    private int counter;
    private final long reductionPolynomial;
    private final long[] tw_current;
    private final long[] tw_init;

    public KXTSBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.blockSize = blockSize;
        this.reductionPolynomial = getReductionPolynomial(blockSize);
        this.tw_init = new long[blockSize >>> 3];
        this.tw_current = new long[blockSize >>> 3];
        this.counter = -1;
    }

    private static void GF_double(long j2, long[] jArr) {
        long j3 = 0;
        int i2 = 0;
        while (i2 < jArr.length) {
            long j4 = jArr[i2];
            jArr[i2] = j3 ^ (j4 << 1);
            i2++;
            j3 = j4 >>> 63;
        }
        jArr[0] = (j2 & (-j3)) ^ jArr[0];
    }

    public static long getReductionPolynomial(int i2) {
        if (i2 == 16) {
            return RED_POLY_128;
        }
        if (i2 == 32) {
            return RED_POLY_256;
        }
        if (i2 == 64) {
            return RED_POLY_512;
        }
        throw new IllegalArgumentException("Only 128, 256, and 512 -bit block sizes supported");
    }

    private void processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int i4 = this.counter;
        if (i4 == -1) {
            throw new IllegalStateException("Attempt to process too many blocks");
        }
        this.counter = i4 + 1;
        GF_double(this.reductionPolynomial, this.tw_current);
        byte[] bArr3 = new byte[this.blockSize];
        Pack.longToLittleEndian(this.tw_current, bArr3, 0);
        int i5 = this.blockSize;
        byte[] bArr4 = new byte[i5];
        System.arraycopy(bArr3, 0, bArr4, 0, i5);
        for (int i6 = 0; i6 < this.blockSize; i6++) {
            bArr4[i6] = (byte) (bArr4[i6] ^ bArr[i2 + i6]);
        }
        this.cipher.processBlock(bArr4, 0, bArr4, 0);
        for (int i7 = 0; i7 < this.blockSize; i7++) {
            bArr2[i3 + i7] = (byte) (bArr4[i7] ^ bArr3[i7]);
        }
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public int doFinal(byte[] bArr, int i2) {
        reset();
        return 0;
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public int getOutputSize(int i2) {
        return i2;
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public int getUpdateOutputSize(int i2) {
        return i2;
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Invalid parameters passed");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        CipherParameters parameters = parametersWithIV.getParameters();
        byte[] iv = parametersWithIV.getIV();
        int length = iv.length;
        int i2 = this.blockSize;
        if (length != i2) {
            throw new IllegalArgumentException("Currently only support IVs of exactly one block");
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(iv, 0, bArr, 0, i2);
        this.cipher.init(true, parameters);
        this.cipher.processBlock(bArr, 0, bArr, 0);
        this.cipher.init(z2, parameters);
        Pack.littleEndianToLong(bArr, 0, this.tw_init);
        long[] jArr = this.tw_init;
        System.arraycopy(jArr, 0, this.tw_current, 0, jArr.length);
        this.counter = 0;
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public int processByte(byte b2, byte[] bArr, int i2) {
        throw new IllegalStateException("unsupported operation");
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (bArr.length - i2 < i3) {
            throw new DataLengthException("Input buffer too short");
        }
        if (bArr2.length - i2 < i3) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (i3 % this.blockSize != 0) {
            throw new IllegalArgumentException("Partial blocks not supported");
        }
        int i5 = 0;
        while (i5 < i3) {
            processBlock(bArr, i2 + i5, bArr2, i4 + i5);
            i5 += this.blockSize;
        }
        return i3;
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public void reset() {
        this.cipher.reset();
        long[] jArr = this.tw_init;
        System.arraycopy(jArr, 0, this.tw_current, 0, jArr.length);
        this.counter = 0;
    }
}
