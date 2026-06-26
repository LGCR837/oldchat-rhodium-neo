package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class PaddedBlockCipher extends BufferedBlockCipher {
    public PaddedBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        this.buf = new byte[blockCipher.getBlockSize()];
        this.bufOff = 0;
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public int doFinal(byte[] bArr, int i2) throws InvalidCipherTextException {
        int iProcessBlock;
        int iProcessBlock2;
        int blockSize = this.cipher.getBlockSize();
        if (this.forEncryption) {
            if (this.bufOff != blockSize) {
                iProcessBlock2 = 0;
            } else {
                if ((blockSize * 2) + i2 > bArr.length) {
                    throw new OutputLengthException("output buffer too short");
                }
                iProcessBlock2 = this.cipher.processBlock(this.buf, 0, bArr, i2);
                this.bufOff = 0;
            }
            byte b2 = (byte) (blockSize - this.bufOff);
            while (true) {
                int i3 = this.bufOff;
                if (i3 >= blockSize) {
                    break;
                }
                this.buf[i3] = b2;
                this.bufOff = i3 + 1;
            }
            iProcessBlock = iProcessBlock2 + this.cipher.processBlock(this.buf, 0, bArr, i2 + iProcessBlock2);
        } else {
            if (this.bufOff != blockSize) {
                throw new DataLengthException("last block incomplete in decryption");
            }
            BlockCipher blockCipher = this.cipher;
            byte[] bArr2 = this.buf;
            int iProcessBlock3 = blockCipher.processBlock(bArr2, 0, bArr2, 0);
            this.bufOff = 0;
            byte[] bArr3 = this.buf;
            int i4 = bArr3[blockSize - 1] & 255;
            if (i4 < 0 || i4 > blockSize) {
                throw new InvalidCipherTextException("pad block corrupted");
            }
            iProcessBlock = iProcessBlock3 - i4;
            System.arraycopy(bArr3, 0, bArr, i2, iProcessBlock);
        }
        reset();
        return iProcessBlock;
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public int getOutputSize(int i2) {
        int length;
        int i3 = i2 + this.bufOff;
        byte[] bArr = this.buf;
        int length2 = i3 % bArr.length;
        if (length2 != 0) {
            i3 -= length2;
            length = bArr.length;
        } else {
            if (!this.forEncryption) {
                return i3;
            }
            length = bArr.length;
        }
        return i3 + length;
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
            this.bufOff = 0;
            i4 = iProcessBlock;
        }
        byte[] bArr3 = this.buf;
        int i5 = this.bufOff;
        this.bufOff = i5 + 1;
        bArr3[i5] = b2;
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
        int iProcessBlock = 0;
        if (i3 > i6) {
            System.arraycopy(bArr, i2, bArr3, i5, i6);
            int iProcessBlock2 = this.cipher.processBlock(this.buf, 0, bArr2, i4);
            this.bufOff = 0;
            i3 -= i6;
            i2 += i6;
            iProcessBlock = iProcessBlock2;
            while (i3 > this.buf.length) {
                iProcessBlock += this.cipher.processBlock(bArr, i2, bArr2, i4 + iProcessBlock);
                i3 -= blockSize;
                i2 += blockSize;
            }
        }
        System.arraycopy(bArr, i2, this.buf, this.bufOff, i3);
        this.bufOff += i3;
        return iProcessBlock;
    }
}
