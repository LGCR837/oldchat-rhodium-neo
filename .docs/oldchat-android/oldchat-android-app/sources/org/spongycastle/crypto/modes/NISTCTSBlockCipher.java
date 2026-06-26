package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class NISTCTSBlockCipher extends BufferedBlockCipher {
    public static final int CS1 = 1;
    public static final int CS2 = 2;
    public static final int CS3 = 3;
    private final int blockSize;
    private final int type;

    public NISTCTSBlockCipher(int i2, BlockCipher blockCipher) {
        this.type = i2;
        this.cipher = new CBCBlockCipher(blockCipher);
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
                throw new DataLengthException("need at least one block of input for NISTCTS");
            }
            if (i3 > blockSize) {
                byte[] bArr3 = new byte[blockSize];
                int i5 = this.type;
                if (i5 == 2 || i5 == 3) {
                    this.cipher.processBlock(this.buf, 0, bArr2, 0);
                    System.arraycopy(this.buf, blockSize, bArr3, 0, i4);
                    this.cipher.processBlock(bArr3, 0, bArr3, 0);
                    if (this.type == 2 && i4 == blockSize) {
                        System.arraycopy(bArr2, 0, bArr, i2, blockSize);
                        System.arraycopy(bArr3, 0, bArr, i2 + blockSize, i4);
                    } else {
                        System.arraycopy(bArr3, 0, bArr, i2, blockSize);
                        System.arraycopy(bArr2, 0, bArr, i2 + blockSize, i4);
                    }
                } else {
                    System.arraycopy(this.buf, 0, bArr2, 0, blockSize);
                    this.cipher.processBlock(bArr2, 0, bArr2, 0);
                    System.arraycopy(bArr2, 0, bArr, i2, i4);
                    System.arraycopy(this.buf, this.bufOff - i4, bArr3, 0, i4);
                    this.cipher.processBlock(bArr3, 0, bArr3, 0);
                    System.arraycopy(bArr3, 0, bArr, i2 + i4, blockSize);
                }
            } else {
                this.cipher.processBlock(this.buf, 0, bArr2, 0);
                System.arraycopy(bArr2, 0, bArr, i2, blockSize);
            }
        } else {
            if (i3 < blockSize) {
                throw new DataLengthException("need at least one block of input for CTS");
            }
            byte[] bArr4 = new byte[blockSize];
            if (i3 > blockSize) {
                int i6 = this.type;
                if (i6 == 3 || (i6 == 2 && (this.buf.length - i3) % blockSize != 0)) {
                    BlockCipher blockCipher = this.cipher;
                    if (blockCipher instanceof CBCBlockCipher) {
                        ((CBCBlockCipher) blockCipher).getUnderlyingCipher().processBlock(this.buf, 0, bArr2, 0);
                    } else {
                        blockCipher.processBlock(this.buf, 0, bArr2, 0);
                    }
                    for (int i7 = blockSize; i7 != this.bufOff; i7++) {
                        int i8 = i7 - blockSize;
                        bArr4[i8] = (byte) (bArr2[i8] ^ this.buf[i7]);
                    }
                    System.arraycopy(this.buf, blockSize, bArr2, 0, i4);
                    this.cipher.processBlock(bArr2, 0, bArr, i2);
                    System.arraycopy(bArr4, 0, bArr, i2 + blockSize, i4);
                } else {
                    ((CBCBlockCipher) this.cipher).getUnderlyingCipher().processBlock(this.buf, this.bufOff - blockSize, bArr4, 0);
                    System.arraycopy(this.buf, 0, bArr2, 0, blockSize);
                    if (i4 != blockSize) {
                        System.arraycopy(bArr4, i4, bArr2, i4, blockSize - i4);
                    }
                    this.cipher.processBlock(bArr2, 0, bArr2, 0);
                    System.arraycopy(bArr2, 0, bArr, i2, blockSize);
                    for (int i9 = 0; i9 != i4; i9++) {
                        bArr4[i9] = (byte) (bArr4[i9] ^ this.buf[i9]);
                    }
                    System.arraycopy(bArr4, 0, bArr, i2 + blockSize, i4);
                }
            } else {
                this.cipher.processBlock(this.buf, 0, bArr2, 0);
                System.arraycopy(bArr2, 0, bArr, i2, blockSize);
            }
        }
        int i10 = this.bufOff;
        reset();
        return i10;
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
