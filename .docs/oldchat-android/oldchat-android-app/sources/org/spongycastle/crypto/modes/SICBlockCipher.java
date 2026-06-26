package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.SkippingStreamCipher;
import org.spongycastle.crypto.StreamBlockCipher;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class SICBlockCipher extends StreamBlockCipher implements SkippingStreamCipher {
    private byte[] IV;
    private final int blockSize;
    private int byteCount;
    private final BlockCipher cipher;
    private byte[] counter;
    private byte[] counterOut;

    public SICBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.cipher = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.blockSize = blockSize;
        this.IV = new byte[blockSize];
        this.counter = new byte[blockSize];
        this.counterOut = new byte[blockSize];
        this.byteCount = 0;
    }

    private void adjustCounter(long j2) {
        int i2 = 5;
        if (j2 >= 0) {
            long j3 = (((long) this.byteCount) + j2) / ((long) this.blockSize);
            long j4 = j3;
            if (j3 > 255) {
                while (i2 >= 1) {
                    long j5 = 1 << (i2 * 8);
                    while (j4 >= j5) {
                        incrementCounterAt(i2);
                        j4 -= j5;
                    }
                    i2--;
                }
            }
            incrementCounter((int) j4);
            this.byteCount = (int) ((j2 + ((long) this.byteCount)) - (((long) this.blockSize) * j3));
            return;
        }
        long j6 = ((-j2) - ((long) this.byteCount)) / ((long) this.blockSize);
        long j7 = j6;
        if (j6 > 255) {
            while (i2 >= 1) {
                long j8 = 1 << (i2 * 8);
                while (j7 > j8) {
                    decrementCounterAt(i2);
                    j7 -= j8;
                }
                i2--;
            }
        }
        for (long j9 = 0; j9 != j7; j9++) {
            decrementCounterAt(0);
        }
        int i3 = (int) (((long) this.byteCount) + j2 + (((long) this.blockSize) * j6));
        if (i3 >= 0) {
            this.byteCount = 0;
        } else {
            decrementCounterAt(0);
            this.byteCount = this.blockSize + i3;
        }
    }

    private void checkCounter() {
        if (this.IV.length >= this.blockSize) {
            return;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr = this.IV;
            if (i2 == bArr.length) {
                return;
            }
            if (this.counter[i2] != bArr[i2]) {
                throw new IllegalStateException("Counter in CTR/SIC mode out of range.");
            }
            i2++;
        }
    }

    private void decrementCounterAt(int i2) {
        byte b2;
        int length = this.counter.length - i2;
        do {
            length--;
            if (length < 0) {
                return;
            }
            b2 = (byte) (r1[length] - 1);
            this.counter[length] = b2;
        } while (b2 == -1);
    }

    private void incrementCounter(int i2) {
        byte[] bArr = this.counter;
        byte b2 = bArr[bArr.length - 1];
        int length = bArr.length - 1;
        bArr[length] = (byte) (bArr[length] + i2);
        if (b2 == 0 || bArr[bArr.length - 1] >= b2) {
            return;
        }
        incrementCounterAt(1);
    }

    private void incrementCounterAt(int i2) {
        byte b2;
        int length = this.counter.length - i2;
        do {
            length--;
            if (length < 0) {
                return;
            }
            byte[] bArr = this.counter;
            b2 = (byte) (bArr[length] + 1);
            bArr[length] = b2;
        } while (b2 == 0);
    }

    @Override // org.spongycastle.crypto.StreamBlockCipher
    public byte calculateByte(byte b2) {
        int i2 = this.byteCount;
        if (i2 == 0) {
            this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
            byte[] bArr = this.counterOut;
            int i3 = this.byteCount;
            this.byteCount = i3 + 1;
            return (byte) (b2 ^ bArr[i3]);
        }
        byte[] bArr2 = this.counterOut;
        int i4 = i2 + 1;
        this.byteCount = i4;
        byte b3 = (byte) (b2 ^ bArr2[i2]);
        if (i4 == this.counter.length) {
            this.byteCount = 0;
            incrementCounterAt(0);
            checkCounter();
        }
        return b3;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/SIC";
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    @Override // org.spongycastle.crypto.SkippingCipher
    public long getPosition() {
        byte[] bArr = this.counter;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        int i2 = length - 1;
        while (i2 >= 1) {
            byte[] bArr3 = this.IV;
            int i3 = i2 < bArr3.length ? (bArr2[i2] & 255) - (bArr3[i2] & 255) : bArr2[i2] & 255;
            if (i3 < 0) {
                int i4 = i2 - 1;
                bArr2[i4] = (byte) (bArr2[i4] - 1);
                i3 += 256;
            }
            bArr2[i2] = (byte) i3;
            i2--;
        }
        return (Pack.bigEndianToLong(bArr2, length - 8) * ((long) this.blockSize)) + ((long) this.byteCount);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("CTR/SIC mode requires ParametersWithIV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] bArrClone = Arrays.clone(parametersWithIV.getIV());
        this.IV = bArrClone;
        int i2 = this.blockSize;
        if (i2 < bArrClone.length) {
            throw new IllegalArgumentException("CTR/SIC mode requires IV no greater than: " + this.blockSize + " bytes.");
        }
        int i3 = 8 > i2 / 2 ? i2 / 2 : 8;
        if (i2 - bArrClone.length <= i3) {
            if (parametersWithIV.getParameters() != null) {
                this.cipher.init(true, parametersWithIV.getParameters());
            }
            reset();
        } else {
            throw new IllegalArgumentException("CTR/SIC mode requires IV of at least: " + (this.blockSize - i3) + " bytes.");
        }
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        processBytes(bArr, i2, this.blockSize, bArr2, i3);
        return this.blockSize;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
        Arrays.fill(this.counter, (byte) 0);
        byte[] bArr = this.IV;
        System.arraycopy(bArr, 0, this.counter, 0, bArr.length);
        this.cipher.reset();
        this.byteCount = 0;
    }

    @Override // org.spongycastle.crypto.SkippingCipher
    public long seekTo(long j2) {
        reset();
        return skip(j2);
    }

    @Override // org.spongycastle.crypto.SkippingCipher
    public long skip(long j2) {
        adjustCounter(j2);
        checkCounter();
        this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
        return j2;
    }
}
