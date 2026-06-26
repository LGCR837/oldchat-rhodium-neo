package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.StreamBlockCipher;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class CFBBlockCipher extends StreamBlockCipher {
    private byte[] IV;
    private int blockSize;
    private int byteCount;
    private byte[] cfbOutV;
    private byte[] cfbV;
    private BlockCipher cipher;
    private boolean encrypting;
    private byte[] inBuf;

    public CFBBlockCipher(BlockCipher blockCipher, int i2) {
        super(blockCipher);
        this.cipher = blockCipher;
        this.blockSize = i2 / 8;
        this.IV = new byte[blockCipher.getBlockSize()];
        this.cfbV = new byte[blockCipher.getBlockSize()];
        this.cfbOutV = new byte[blockCipher.getBlockSize()];
        this.inBuf = new byte[this.blockSize];
    }

    private byte decryptByte(byte b2) {
        if (this.byteCount == 0) {
            this.cipher.processBlock(this.cfbV, 0, this.cfbOutV, 0);
        }
        byte[] bArr = this.inBuf;
        int i2 = this.byteCount;
        bArr[i2] = b2;
        byte[] bArr2 = this.cfbOutV;
        int i3 = i2 + 1;
        this.byteCount = i3;
        byte b3 = (byte) (b2 ^ bArr2[i2]);
        int i4 = this.blockSize;
        if (i3 == i4) {
            this.byteCount = 0;
            byte[] bArr3 = this.cfbV;
            System.arraycopy(bArr3, i4, bArr3, 0, bArr3.length - i4);
            byte[] bArr4 = this.inBuf;
            byte[] bArr5 = this.cfbV;
            int length = bArr5.length;
            int i5 = this.blockSize;
            System.arraycopy(bArr4, 0, bArr5, length - i5, i5);
        }
        return b3;
    }

    private byte encryptByte(byte b2) {
        if (this.byteCount == 0) {
            this.cipher.processBlock(this.cfbV, 0, this.cfbOutV, 0);
        }
        byte[] bArr = this.cfbOutV;
        int i2 = this.byteCount;
        byte b3 = (byte) (b2 ^ bArr[i2]);
        byte[] bArr2 = this.inBuf;
        int i3 = i2 + 1;
        this.byteCount = i3;
        bArr2[i2] = b3;
        int i4 = this.blockSize;
        if (i3 == i4) {
            this.byteCount = 0;
            byte[] bArr3 = this.cfbV;
            System.arraycopy(bArr3, i4, bArr3, 0, bArr3.length - i4);
            byte[] bArr4 = this.inBuf;
            byte[] bArr5 = this.cfbV;
            int length = bArr5.length;
            int i5 = this.blockSize;
            System.arraycopy(bArr4, 0, bArr5, length - i5, i5);
        }
        return b3;
    }

    @Override // org.spongycastle.crypto.StreamBlockCipher
    public byte calculateByte(byte b2) {
        return this.encrypting ? encryptByte(b2) : decryptByte(b2);
    }

    public int decryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        processBytes(bArr, i2, this.blockSize, bArr2, i3);
        return this.blockSize;
    }

    public int encryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        processBytes(bArr, i2, this.blockSize, bArr2, i3);
        return this.blockSize;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/CFB" + (this.blockSize * 8);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.blockSize;
    }

    public byte[] getCurrentIV() {
        return Arrays.clone(this.cfbV);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        this.encrypting = z2;
        if (!(cipherParameters instanceof ParametersWithIV)) {
            reset();
            if (cipherParameters != null) {
                this.cipher.init(true, cipherParameters);
                return;
            }
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
        if (parametersWithIV.getParameters() != null) {
            this.cipher.init(true, parametersWithIV.getParameters());
        }
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        processBytes(bArr, i2, this.blockSize, bArr2, i3);
        return this.blockSize;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
        byte[] bArr = this.IV;
        System.arraycopy(bArr, 0, this.cfbV, 0, bArr.length);
        Arrays.fill(this.inBuf, (byte) 0);
        this.byteCount = 0;
        this.cipher.reset();
    }
}
