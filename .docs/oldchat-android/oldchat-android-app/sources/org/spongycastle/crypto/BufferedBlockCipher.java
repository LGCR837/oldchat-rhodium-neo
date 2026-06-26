package org.spongycastle.crypto;

/* JADX INFO: loaded from: classes.dex */
public class BufferedBlockCipher {
    protected byte[] buf;
    protected int bufOff;
    protected BlockCipher cipher;
    protected boolean forEncryption;
    protected boolean partialBlockOkay;
    protected boolean pgpCFB;

    public BufferedBlockCipher() {
    }

    public int doFinal(byte[] bArr, int i2) {
        try {
            int i3 = this.bufOff;
            if (i2 + i3 > bArr.length) {
                throw new OutputLengthException("output buffer too short for doFinal()");
            }
            int i4 = 0;
            if (i3 != 0) {
                if (!this.partialBlockOkay) {
                    throw new DataLengthException("data not block size aligned");
                }
                BlockCipher blockCipher = this.cipher;
                byte[] bArr2 = this.buf;
                blockCipher.processBlock(bArr2, 0, bArr2, 0);
                int i5 = this.bufOff;
                this.bufOff = 0;
                System.arraycopy(this.buf, 0, bArr, i2, i5);
                i4 = i5;
            }
            reset();
            return i4;
        } catch (Throwable th) {
            reset();
            throw th;
        }
    }

    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    public int getOutputSize(int i2) {
        return i2 + this.bufOff;
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public int getUpdateOutputSize(int i2) {
        int i3 = i2 + this.bufOff;
        int length = (this.pgpCFB && this.forEncryption) ? (i3 % this.buf.length) - (this.cipher.getBlockSize() + 2) : i3 % this.buf.length;
        return i3 - length;
    }

    public void init(boolean z2, CipherParameters cipherParameters) {
        this.forEncryption = z2;
        reset();
        this.cipher.init(z2, cipherParameters);
    }

    public int processByte(byte b2, byte[] bArr, int i2) {
        byte[] bArr2 = this.buf;
        int i3 = this.bufOff;
        int i4 = i3 + 1;
        this.bufOff = i4;
        bArr2[i3] = b2;
        if (i4 != bArr2.length) {
            return 0;
        }
        int iProcessBlock = this.cipher.processBlock(bArr2, 0, bArr, i2);
        this.bufOff = 0;
        return iProcessBlock;
    }

    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        int iProcessBlock;
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
        if (i3 > i6) {
            System.arraycopy(bArr, i2, bArr3, i5, i6);
            iProcessBlock = this.cipher.processBlock(this.buf, 0, bArr2, i4);
            this.bufOff = 0;
            i3 -= i6;
            i2 += i6;
            while (i3 > this.buf.length) {
                iProcessBlock += this.cipher.processBlock(bArr, i2, bArr2, i4 + iProcessBlock);
                i3 -= blockSize;
                i2 += blockSize;
            }
        } else {
            iProcessBlock = 0;
        }
        System.arraycopy(bArr, i2, this.buf, this.bufOff, i3);
        int i7 = this.bufOff + i3;
        this.bufOff = i7;
        byte[] bArr4 = this.buf;
        if (i7 != bArr4.length) {
            return iProcessBlock;
        }
        int iProcessBlock2 = iProcessBlock + this.cipher.processBlock(bArr4, 0, bArr2, i4 + iProcessBlock);
        this.bufOff = 0;
        return iProcessBlock2;
    }

    public void reset() {
        int i2 = 0;
        while (true) {
            byte[] bArr = this.buf;
            if (i2 >= bArr.length) {
                this.bufOff = 0;
                this.cipher.reset();
                return;
            } else {
                bArr[i2] = 0;
                i2++;
            }
        }
    }

    public BufferedBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        this.buf = new byte[blockCipher.getBlockSize()];
        boolean z2 = false;
        this.bufOff = 0;
        String algorithmName = blockCipher.getAlgorithmName();
        int iIndexOf = algorithmName.indexOf(47) + 1;
        boolean z3 = iIndexOf > 0 && algorithmName.startsWith("PGP", iIndexOf);
        this.pgpCFB = z3;
        if (z3 || (blockCipher instanceof StreamCipher)) {
            this.partialBlockOkay = true;
            return;
        }
        if (iIndexOf > 0 && algorithmName.startsWith("OpenPGP", iIndexOf)) {
            z2 = true;
        }
        this.partialBlockOkay = z2;
    }
}
