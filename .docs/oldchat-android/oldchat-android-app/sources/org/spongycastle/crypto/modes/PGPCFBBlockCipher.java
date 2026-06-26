package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.ParametersWithIV;

/* JADX INFO: loaded from: classes.dex */
public class PGPCFBBlockCipher implements BlockCipher {
    private byte[] FR;
    private byte[] FRE;
    private byte[] IV;
    private int blockSize;
    private BlockCipher cipher;
    private int count;
    private boolean forEncryption;
    private boolean inlineIv;
    private byte[] tmp;

    public PGPCFBBlockCipher(BlockCipher blockCipher, boolean z2) {
        this.cipher = blockCipher;
        this.inlineIv = z2;
        int blockSize = blockCipher.getBlockSize();
        this.blockSize = blockSize;
        this.IV = new byte[blockSize];
        this.FR = new byte[blockSize];
        this.FRE = new byte[blockSize];
        this.tmp = new byte[blockSize];
    }

    private int decryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int i4 = this.blockSize;
        if (i2 + i4 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i4 + i3 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int i5 = 0;
        this.cipher.processBlock(this.FR, 0, this.FRE, 0);
        for (int i6 = 0; i6 < this.blockSize; i6++) {
            bArr2[i3 + i6] = encryptByte(bArr[i2 + i6], i6);
        }
        while (true) {
            int i7 = this.blockSize;
            if (i5 >= i7) {
                return i7;
            }
            this.FR[i5] = bArr[i2 + i5];
            i5++;
        }
    }

    private int decryptBlockWithIV(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int i4;
        int i5 = this.blockSize;
        if (i2 + i5 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + i5 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int i6 = this.count;
        if (i6 == 0) {
            for (int i7 = 0; i7 < this.blockSize; i7++) {
                this.FR[i7] = bArr[i2 + i7];
            }
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            this.count += this.blockSize;
            return 0;
        }
        if (i6 != i5) {
            if (i6 >= i5 + 2) {
                System.arraycopy(bArr, i2, this.tmp, 0, i5);
                bArr2[i3] = encryptByte(this.tmp[0], this.blockSize - 2);
                bArr2[i3 + 1] = encryptByte(this.tmp[1], this.blockSize - 1);
                System.arraycopy(this.tmp, 0, this.FR, this.blockSize - 2, 2);
                this.cipher.processBlock(this.FR, 0, this.FRE, 0);
                int i8 = 0;
                while (true) {
                    i4 = this.blockSize;
                    if (i8 >= i4 - 2) {
                        break;
                    }
                    bArr2[i3 + i8 + 2] = encryptByte(this.tmp[i8 + 2], i8);
                    i8++;
                }
                System.arraycopy(this.tmp, 2, this.FR, 0, i4 - 2);
            }
            return this.blockSize;
        }
        System.arraycopy(bArr, i2, this.tmp, 0, i5);
        byte[] bArr3 = this.FR;
        System.arraycopy(bArr3, 2, bArr3, 0, this.blockSize - 2);
        byte[] bArr4 = this.FR;
        int i9 = this.blockSize;
        byte[] bArr5 = this.tmp;
        bArr4[i9 - 2] = bArr5[0];
        bArr4[i9 - 1] = bArr5[1];
        this.cipher.processBlock(bArr4, 0, this.FRE, 0);
        int i10 = 0;
        while (true) {
            int i11 = this.blockSize;
            if (i10 >= i11 - 2) {
                System.arraycopy(this.tmp, 2, this.FR, 0, i11 - 2);
                this.count += 2;
                return this.blockSize - 2;
            }
            bArr2[i3 + i10] = encryptByte(this.tmp[i10 + 2], i10);
            i10++;
        }
    }

    private int encryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int i4 = this.blockSize;
        if (i2 + i4 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i4 + i3 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int i5 = 0;
        this.cipher.processBlock(this.FR, 0, this.FRE, 0);
        for (int i6 = 0; i6 < this.blockSize; i6++) {
            bArr2[i3 + i6] = encryptByte(bArr[i2 + i6], i6);
        }
        while (true) {
            int i7 = this.blockSize;
            if (i5 >= i7) {
                return i7;
            }
            this.FR[i5] = bArr2[i3 + i5];
            i5++;
        }
    }

    private int encryptBlockWithIV(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int i4;
        int i5;
        int i6 = this.blockSize;
        if (i2 + i6 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        int i7 = this.count;
        if (i7 != 0) {
            if (i7 >= i6 + 2) {
                if (i6 + i3 > bArr2.length) {
                    throw new OutputLengthException("output buffer too short");
                }
                this.cipher.processBlock(this.FR, 0, this.FRE, 0);
                int i8 = 0;
                while (true) {
                    i4 = this.blockSize;
                    if (i8 >= i4) {
                        break;
                    }
                    bArr2[i3 + i8] = encryptByte(bArr[i2 + i8], i8);
                    i8++;
                }
                System.arraycopy(bArr2, i3, this.FR, 0, i4);
            }
            return this.blockSize;
        }
        if ((i6 * 2) + i3 + 2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        this.cipher.processBlock(this.FR, 0, this.FRE, 0);
        int i9 = 0;
        while (true) {
            i5 = this.blockSize;
            if (i9 >= i5) {
                break;
            }
            bArr2[i3 + i9] = encryptByte(this.IV[i9], i9);
            i9++;
        }
        System.arraycopy(bArr2, i3, this.FR, 0, i5);
        this.cipher.processBlock(this.FR, 0, this.FRE, 0);
        int i10 = this.blockSize;
        bArr2[i3 + i10] = encryptByte(this.IV[i10 - 2], 0);
        int i11 = this.blockSize;
        bArr2[i3 + i11 + 1] = encryptByte(this.IV[i11 - 1], 1);
        System.arraycopy(bArr2, i3 + 2, this.FR, 0, this.blockSize);
        this.cipher.processBlock(this.FR, 0, this.FRE, 0);
        int i12 = 0;
        while (true) {
            int i13 = this.blockSize;
            if (i12 >= i13) {
                System.arraycopy(bArr2, i3 + i13 + 2, this.FR, 0, i13);
                int i14 = this.count;
                int i15 = this.blockSize;
                this.count = i14 + (i15 * 2) + 2;
                return (i15 * 2) + 2;
            }
            bArr2[i13 + i3 + 2 + i12] = encryptByte(bArr[i2 + i12], i12);
            i12++;
        }
    }

    private byte encryptByte(byte b2, int i2) {
        return (byte) (b2 ^ this.FRE[i2]);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        if (this.inlineIv) {
            return this.cipher.getAlgorithmName() + "/PGPCFBwithIV";
        }
        return this.cipher.getAlgorithmName() + "/PGPCFB";
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        this.forEncryption = z2;
        if (!(cipherParameters instanceof ParametersWithIV)) {
            reset();
            this.cipher.init(true, cipherParameters);
            return;
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] iv = parametersWithIV.getIV();
        int length = iv.length;
        byte[] bArr = this.IV;
        if (length < bArr.length) {
            System.arraycopy(iv, 0, bArr, bArr.length - iv.length, iv.length);
            int i2 = 0;
            while (true) {
                byte[] bArr2 = this.IV;
                if (i2 >= bArr2.length - iv.length) {
                    break;
                }
                bArr2[i2] = 0;
                i2++;
            }
        } else {
            System.arraycopy(iv, 0, bArr, 0, bArr.length);
        }
        reset();
        this.cipher.init(true, parametersWithIV.getParameters());
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        return this.inlineIv ? this.forEncryption ? encryptBlockWithIV(bArr, i2, bArr2, i3) : decryptBlockWithIV(bArr, i2, bArr2, i3) : this.forEncryption ? encryptBlock(bArr, i2, bArr2, i3) : decryptBlock(bArr, i2, bArr2, i3);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
        this.count = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.FR;
            if (i2 == bArr.length) {
                this.cipher.reset();
                return;
            }
            if (this.inlineIv) {
                bArr[i2] = 0;
            } else {
                bArr[i2] = this.IV[i2];
            }
            i2++;
        }
    }
}
