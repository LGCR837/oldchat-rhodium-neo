package org.spongycastle.crypto.paddings;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.ParametersWithRandom;

/* JADX INFO: loaded from: classes.dex */
public class PaddedBufferedBlockCipher extends BufferedBlockCipher {
    BlockCipherPadding padding;

    public PaddedBufferedBlockCipher(BlockCipher blockCipher, BlockCipherPadding blockCipherPadding) {
        this.cipher = blockCipher;
        this.padding = blockCipherPadding;
        this.buf = new byte[blockCipher.getBlockSize()];
        this.bufOff = 0;
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public int doFinal(byte[] bArr, int i2) {
        int iProcessBlock;
        int blockSize = this.cipher.getBlockSize();
        if (this.forEncryption) {
            if (this.bufOff != blockSize) {
                iProcessBlock = 0;
            } else {
                if ((blockSize * 2) + i2 > bArr.length) {
                    reset();
                    throw new OutputLengthException("output buffer too short");
                }
                iProcessBlock = this.cipher.processBlock(this.buf, 0, bArr, i2);
                this.bufOff = 0;
            }
            this.padding.addPadding(this.buf, this.bufOff);
            return iProcessBlock + this.cipher.processBlock(this.buf, 0, bArr, i2 + iProcessBlock);
        }
        if (this.bufOff != blockSize) {
            reset();
            throw new DataLengthException("last block incomplete in decryption");
        }
        BlockCipher blockCipher = this.cipher;
        byte[] bArr2 = this.buf;
        int iProcessBlock2 = blockCipher.processBlock(bArr2, 0, bArr2, 0);
        this.bufOff = 0;
        try {
            int iPadCount = iProcessBlock2 - this.padding.padCount(this.buf);
            System.arraycopy(this.buf, 0, bArr, i2, iPadCount);
            return iPadCount;
        } finally {
            reset();
        }
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
        return length == 0 ? Math.max(0, i3 - bArr.length) : i3 - length;
    }

    @Override // org.spongycastle.crypto.BufferedBlockCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        this.forEncryption = z2;
        reset();
        if (!(cipherParameters instanceof ParametersWithRandom)) {
            this.padding.init(null);
            this.cipher.init(z2, cipherParameters);
        } else {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.padding.init(parametersWithRandom.getRandom());
            this.cipher.init(z2, parametersWithRandom.getParameters());
        }
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

    public PaddedBufferedBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, new PKCS7Padding());
    }
}
