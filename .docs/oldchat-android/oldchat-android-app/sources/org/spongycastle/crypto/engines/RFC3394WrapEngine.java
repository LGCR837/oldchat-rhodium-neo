package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Wrapper;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class RFC3394WrapEngine implements Wrapper {
    private BlockCipher engine;
    private boolean forWrapping;
    private byte[] iv;
    private KeyParameter param;
    private boolean wrapCipherMode;

    public RFC3394WrapEngine(BlockCipher blockCipher) {
        this(blockCipher, false);
    }

    @Override // org.spongycastle.crypto.Wrapper
    public String getAlgorithmName() {
        return this.engine.getAlgorithmName();
    }

    @Override // org.spongycastle.crypto.Wrapper
    public void init(boolean z2, CipherParameters cipherParameters) {
        this.forWrapping = z2;
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom) cipherParameters).getParameters();
        }
        if (cipherParameters instanceof KeyParameter) {
            this.param = (KeyParameter) cipherParameters;
            return;
        }
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.iv = parametersWithIV.getIV();
            this.param = (KeyParameter) parametersWithIV.getParameters();
            if (this.iv.length != 8) {
                throw new IllegalArgumentException("IV not equal to 8");
            }
        }
    }

    @Override // org.spongycastle.crypto.Wrapper
    public byte[] unwrap(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        if (this.forWrapping) {
            throw new IllegalStateException("not set for unwrapping");
        }
        int i4 = i3 / 8;
        if (i4 * 8 != i3) {
            throw new InvalidCipherTextException("unwrap data must be a multiple of 8 bytes");
        }
        byte[] bArr2 = this.iv;
        byte[] bArr3 = new byte[i3 - bArr2.length];
        byte[] bArr4 = new byte[bArr2.length];
        byte[] bArr5 = new byte[bArr2.length + 8];
        System.arraycopy(bArr, i2, bArr4, 0, bArr2.length);
        byte[] bArr6 = this.iv;
        System.arraycopy(bArr, i2 + bArr6.length, bArr3, 0, i3 - bArr6.length);
        this.engine.init(!this.wrapCipherMode, this.param);
        int i5 = i4 - 1;
        for (int i6 = 5; i6 >= 0; i6--) {
            for (int i7 = i5; i7 >= 1; i7--) {
                System.arraycopy(bArr4, 0, bArr5, 0, this.iv.length);
                int i8 = (i7 - 1) * 8;
                System.arraycopy(bArr3, i8, bArr5, this.iv.length, 8);
                int i9 = (i5 * i6) + i7;
                int i10 = 1;
                while (i9 != 0) {
                    int length = this.iv.length - i10;
                    bArr5[length] = (byte) (((byte) i9) ^ bArr5[length]);
                    i9 >>>= 8;
                    i10++;
                }
                this.engine.processBlock(bArr5, 0, bArr5, 0);
                System.arraycopy(bArr5, 0, bArr4, 0, 8);
                System.arraycopy(bArr5, 8, bArr3, i8, 8);
            }
        }
        if (Arrays.constantTimeAreEqual(bArr4, this.iv)) {
            return bArr3;
        }
        throw new InvalidCipherTextException("checksum failed");
    }

    @Override // org.spongycastle.crypto.Wrapper
    public byte[] wrap(byte[] bArr, int i2, int i3) {
        if (!this.forWrapping) {
            throw new IllegalStateException("not set for wrapping");
        }
        int i4 = i3 / 8;
        if (i4 * 8 != i3) {
            throw new DataLengthException("wrap data must be a multiple of 8 bytes");
        }
        byte[] bArr2 = this.iv;
        byte[] bArr3 = new byte[bArr2.length + i3];
        byte[] bArr4 = new byte[bArr2.length + 8];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(bArr, i2, bArr3, this.iv.length, i3);
        this.engine.init(this.wrapCipherMode, this.param);
        for (int i5 = 0; i5 != 6; i5++) {
            for (int i6 = 1; i6 <= i4; i6++) {
                System.arraycopy(bArr3, 0, bArr4, 0, this.iv.length);
                int i7 = i6 * 8;
                System.arraycopy(bArr3, i7, bArr4, this.iv.length, 8);
                this.engine.processBlock(bArr4, 0, bArr4, 0);
                int i8 = (i4 * i5) + i6;
                int i9 = 1;
                while (i8 != 0) {
                    int length = this.iv.length - i9;
                    bArr4[length] = (byte) (((byte) i8) ^ bArr4[length]);
                    i8 >>>= 8;
                    i9++;
                }
                System.arraycopy(bArr4, 0, bArr3, 0, 8);
                System.arraycopy(bArr4, 8, bArr3, i7, 8);
            }
        }
        return bArr3;
    }

    public RFC3394WrapEngine(BlockCipher blockCipher, boolean z2) {
        this.iv = new byte[]{-90, -90, -90, -90, -90, -90, -90, -90};
        this.engine = blockCipher;
        this.wrapCipherMode = !z2;
    }
}
