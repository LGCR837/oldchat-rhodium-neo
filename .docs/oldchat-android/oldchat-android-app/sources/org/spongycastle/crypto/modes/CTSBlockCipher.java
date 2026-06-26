package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.StreamBlockCipher;

/* JADX INFO: loaded from: classes.dex */
public class CTSBlockCipher extends BufferedBlockCipher {
    private int blockSize;

    public CTSBlockCipher(BlockCipher blockCipher) {
        if (blockCipher instanceof StreamBlockCipher) {
            throw new IllegalArgumentException("CTSBlockCipher can only accept ECB, or CBC ciphers");
        }
        this.cipher = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.blockSize = blockSize;
        this.buf = new byte[blockSize * 2];
        this.bufOff = 0;
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public int doFinal(byte[] bArr, int i2) {
        if (this.bufOff + i2 > bArr.length) {
            throw new OutputLengthException("output buffer to small in doFinal");
        }
        int blockSize = this.cipher.getBlockSize();
        int i3 = this.bufOff;
        int i4 = i3 - blockSize;
        byte[] bArr2 = new byte[blockSize];
        if (this.forEncryption) {
            if (i3 < blockSize) {
                throw new DataLengthException("need at least one block of input for CTS");
            }
            this.cipher.processBlock(this.buf, 0, bArr2, 0);
            int i5 = this.bufOff;
            if (i5 > blockSize) {
                while (true) {
                    byte[] bArr3 = this.buf;
                    if (i5 == bArr3.length) {
                        break;
                    }
                    bArr3[i5] = bArr2[i5 - blockSize];
                    i5++;
                }
                for (int i6 = blockSize; i6 != this.bufOff; i6++) {
                    byte[] bArr4 = this.buf;
                    bArr4[i6] = (byte) (bArr4[i6] ^ bArr2[i6 - blockSize]);
                }
                BlockCipher blockCipher = this.cipher;
                if (blockCipher instanceof CBCBlockCipher) {
                    ((CBCBlockCipher) blockCipher).getUnderlyingCipher().processBlock(this.buf, blockSize, bArr, i2);
                } else {
                    blockCipher.processBlock(this.buf, blockSize, bArr, i2);
                }
                System.arraycopy(bArr2, 0, bArr, i2 + blockSize, i4);
            } else {
                System.arraycopy(bArr2, 0, bArr, i2, blockSize);
            }
        } else {
            if (i3 < blockSize) {
                throw new DataLengthException("need at least one block of input for CTS");
            }
            byte[] bArr5 = new byte[blockSize];
            if (i3 > blockSize) {
                BlockCipher blockCipher2 = this.cipher;
                if (blockCipher2 instanceof CBCBlockCipher) {
                    ((CBCBlockCipher) blockCipher2).getUnderlyingCipher().processBlock(this.buf, 0, bArr2, 0);
                } else {
                    blockCipher2.processBlock(this.buf, 0, bArr2, 0);
                }
                for (int i7 = blockSize; i7 != this.bufOff; i7++) {
                    int i8 = i7 - blockSize;
                    bArr5[i8] = (byte) (bArr2[i8] ^ this.buf[i7]);
                }
                System.arraycopy(this.buf, blockSize, bArr2, 0, i4);
                this.cipher.processBlock(bArr2, 0, bArr, i2);
                System.arraycopy(bArr5, 0, bArr, i2 + blockSize, i4);
            } else {
                this.cipher.processBlock(this.buf, 0, bArr2, 0);
                System.arraycopy(bArr2, 0, bArr, i2, blockSize);
            }
        }
        int i9 = this.bufOff;
        reset();
        return i9;
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public int getOutputSize(int i2) {
        return i2 + this.bufOff;
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public int getUpdateOutputSize(int i2) {
        int i3 = i2 + this.bufOff;
        byte[] bArr = this.buf;
        int length = i3 % bArr.length;
        return length == 0 ? i3 - bArr.length : i3 - length;
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public int processByte(byte b2, byte[] bArr, int i2) {
        int i3 = this.bufOff;
        byte[] bArr2 = this.buf;
        int i4 = 0;
        if (i3 == bArr2.length) {
            int iProcessBlock = this.cipher.processBlock(bArr2, 0, bArr, i2);
            byte[] bArr3 = this.buf;
            int i5 = this.blockSize;
            System.arraycopy(bArr3, i5, bArr3, 0, i5);
            this.bufOff = this.blockSize;
            i4 = iProcessBlock;
        }
        byte[] bArr4 = this.buf;
        int i6 = this.bufOff;
        this.bufOff = i6 + 1;
        bArr4[i6] = b2;
        return i4;
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (i3 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int blockSize = getBlockSize();
        int updateOutputSize = getUpdateOutputSize(i3);
        if (updateOutputSize > 0 && updateOutputSize + i4 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        byte[] bArr3 = this.buf;
        int length = bArr3.length;
        int i5 = this.bufOff;
        int i6 = length - i5;
        int i7 = 0;
        if (i3 > i6) {
            System.arraycopy(bArr, i2, bArr3, i5, i6);
            int iProcessBlock = this.cipher.processBlock(this.buf, 0, bArr2, i4);
            byte[] bArr4 = this.buf;
            System.arraycopy(bArr4, blockSize, bArr4, 0, blockSize);
            this.bufOff = blockSize;
            i3 -= i6;
            i2 += i6;
            while (i3 > blockSize) {
                System.arraycopy(bArr, i2, this.buf, this.bufOff, blockSize);
                iProcessBlock += this.cipher.processBlock(this.buf, 0, bArr2, i4 + iProcessBlock);
                byte[] bArr5 = this.buf;
                System.arraycopy(bArr5, blockSize, bArr5, 0, blockSize);
                i3 -= blockSize;
                i2 += blockSize;
            }
            i7 = iProcessBlock;
        }
        System.arraycopy(bArr, i2, this.buf, this.bufOff, i3);
        this.bufOff += i3;
        return i7;
    }
}
