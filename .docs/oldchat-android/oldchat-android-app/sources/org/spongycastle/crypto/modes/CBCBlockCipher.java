package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class CBCBlockCipher implements BlockCipher {
    private byte[] IV;
    private int blockSize;
    private byte[] cbcNextV;
    private byte[] cbcV;
    private BlockCipher cipher;
    private boolean encrypting;

    public CBCBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.blockSize = blockSize;
        this.IV = new byte[blockSize];
        this.cbcV = new byte[blockSize];
        this.cbcNextV = new byte[blockSize];
    }

    private int decryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int i4 = this.blockSize;
        if (i2 + i4 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        System.arraycopy(bArr, i2, this.cbcNextV, 0, i4);
        int iProcessBlock = this.cipher.processBlock(bArr, i2, bArr2, i3);
        for (int i5 = 0; i5 < this.blockSize; i5++) {
            int i6 = i3 + i5;
            bArr2[i6] = (byte) (bArr2[i6] ^ this.cbcV[i5]);
        }
        byte[] bArr3 = this.cbcV;
        this.cbcV = this.cbcNextV;
        this.cbcNextV = bArr3;
        return iProcessBlock;
    }

    private int encryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (this.blockSize + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        for (int i4 = 0; i4 < this.blockSize; i4++) {
            byte[] bArr3 = this.cbcV;
            bArr3[i4] = (byte) (bArr3[i4] ^ bArr[i2 + i4]);
        }
        int iProcessBlock = this.cipher.processBlock(this.cbcV, 0, bArr2, i3);
        byte[] bArr4 = this.cbcV;
        System.arraycopy(bArr2, i3, bArr4, 0, bArr4.length);
        return iProcessBlock;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/CBC";
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
        boolean z3 = this.encrypting;
        this.encrypting = z2;
        if (!(cipherParameters instanceof ParametersWithIV)) {
            reset();
            if (cipherParameters != null) {
                this.cipher.init(z2, cipherParameters);
                return;
            } else {
                if (z3 != z2) {
                    throw new IllegalArgumentException("cannot change encrypting state without providing key.");
                }
                return;
            }
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] iv = parametersWithIV.getIV();
        if (iv.length != this.blockSize) {
            throw new IllegalArgumentException("initialisation vector must be the same length as block size");
        }
        System.arraycopy(iv, 0, this.IV, 0, iv.length);
        reset();
        if (parametersWithIV.getParameters() != null) {
            this.cipher.init(z2, parametersWithIV.getParameters());
        } else if (z3 != z2) {
            throw new IllegalArgumentException("cannot change encrypting state without providing key.");
        }
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        return this.encrypting ? encryptBlock(bArr, i2, bArr2, i3) : decryptBlock(bArr, i2, bArr2, i3);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
        byte[] bArr = this.IV;
        System.arraycopy(bArr, 0, this.cbcV, 0, bArr.length);
        Arrays.fill(this.cbcNextV, (byte) 0);
        this.cipher.reset();
    }
}
