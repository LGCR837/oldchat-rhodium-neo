package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class OpenPGPCFBBlockCipher implements BlockCipher {
    private byte[] FR;
    private byte[] FRE;
    private byte[] IV;
    private int blockSize;
    private BlockCipher cipher;
    private int count;
    private boolean forEncryption;

    public OpenPGPCFBBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.blockSize = blockSize;
        this.IV = new byte[blockSize];
        this.FR = new byte[blockSize];
        this.FRE = new byte[blockSize];
    }

    private int decryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int i4;
        int i5;
        int i6 = this.blockSize;
        if (i2 + i6 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + i6 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int i7 = this.count;
        int i8 = 2;
        int i9 = 0;
        if (i7 > i6) {
            byte b2 = bArr[i2];
            this.FR[i6 - 2] = b2;
            bArr2[i3] = encryptByte(b2, i6 - 2);
            byte b3 = bArr[i2 + 1];
            byte[] bArr3 = this.FR;
            int i10 = this.blockSize;
            bArr3[i10 - 1] = b3;
            bArr2[i3 + 1] = encryptByte(b3, i10 - 1);
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            while (i8 < this.blockSize) {
                byte b4 = bArr[i2 + i8];
                int i11 = i8 - 2;
                this.FR[i11] = b4;
                bArr2[i3 + i8] = encryptByte(b4, i11);
                i8++;
            }
        } else if (i7 == 0) {
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            while (true) {
                i5 = this.blockSize;
                if (i9 >= i5) {
                    break;
                }
                int i12 = i2 + i9;
                this.FR[i9] = bArr[i12];
                bArr2[i9] = encryptByte(bArr[i12], i9);
                i9++;
            }
            this.count += i5;
        } else if (i7 == i6) {
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            byte b5 = bArr[i2];
            byte b6 = bArr[i2 + 1];
            bArr2[i3] = encryptByte(b5, 0);
            bArr2[i3 + 1] = encryptByte(b6, 1);
            byte[] bArr4 = this.FR;
            System.arraycopy(bArr4, 2, bArr4, 0, this.blockSize - 2);
            byte[] bArr5 = this.FR;
            int i13 = this.blockSize;
            bArr5[i13 - 2] = b5;
            bArr5[i13 - 1] = b6;
            this.cipher.processBlock(bArr5, 0, this.FRE, 0);
            while (true) {
                i4 = this.blockSize;
                if (i8 >= i4) {
                    break;
                }
                byte b7 = bArr[i2 + i8];
                int i14 = i8 - 2;
                this.FR[i14] = b7;
                bArr2[i3 + i8] = encryptByte(b7, i14);
                i8++;
            }
            this.count += i4;
        }
        return this.blockSize;
    }

    private int encryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int i4;
        int i5;
        int i6 = this.blockSize;
        if (i2 + i6 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + i6 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int i7 = this.count;
        int i8 = 2;
        int i9 = 0;
        if (i7 > i6) {
            byte[] bArr3 = this.FR;
            int i10 = i6 - 2;
            byte bEncryptByte = encryptByte(bArr[i2], i6 - 2);
            bArr2[i3] = bEncryptByte;
            bArr3[i10] = bEncryptByte;
            byte[] bArr4 = this.FR;
            int i11 = this.blockSize;
            int i12 = i11 - 1;
            byte bEncryptByte2 = encryptByte(bArr[i2 + 1], i11 - 1);
            bArr2[i3 + 1] = bEncryptByte2;
            bArr4[i12] = bEncryptByte2;
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            while (i8 < this.blockSize) {
                byte[] bArr5 = this.FR;
                int i13 = i8 - 2;
                byte bEncryptByte3 = encryptByte(bArr[i2 + i8], i13);
                bArr2[i3 + i8] = bEncryptByte3;
                bArr5[i13] = bEncryptByte3;
                i8++;
            }
        } else if (i7 == 0) {
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            while (true) {
                i5 = this.blockSize;
                if (i9 >= i5) {
                    break;
                }
                byte[] bArr6 = this.FR;
                byte bEncryptByte4 = encryptByte(bArr[i2 + i9], i9);
                bArr2[i3 + i9] = bEncryptByte4;
                bArr6[i9] = bEncryptByte4;
                i9++;
            }
            this.count += i5;
        } else if (i7 == i6) {
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            bArr2[i3] = encryptByte(bArr[i2], 0);
            bArr2[i3 + 1] = encryptByte(bArr[i2 + 1], 1);
            byte[] bArr7 = this.FR;
            System.arraycopy(bArr7, 2, bArr7, 0, this.blockSize - 2);
            System.arraycopy(bArr2, i3, this.FR, this.blockSize - 2, 2);
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            while (true) {
                i4 = this.blockSize;
                if (i8 >= i4) {
                    break;
                }
                byte[] bArr8 = this.FR;
                int i14 = i8 - 2;
                byte bEncryptByte5 = encryptByte(bArr[i2 + i8], i14);
                bArr2[i3 + i8] = bEncryptByte5;
                bArr8[i14] = bEncryptByte5;
                i8++;
            }
            this.count += i4;
        }
        return this.blockSize;
    }

    private byte encryptByte(byte b2, int i2) {
        return (byte) (b2 ^ this.FRE[i2]);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/OpenPGPCFB";
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
        reset();
        this.cipher.init(true, cipherParameters);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        return this.forEncryption ? encryptBlock(bArr, i2, bArr2, i3) : decryptBlock(bArr, i2, bArr2, i3);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
        this.count = 0;
        byte[] bArr = this.IV;
        byte[] bArr2 = this.FR;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.cipher.reset();
    }
}
