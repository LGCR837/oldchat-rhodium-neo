package org.spongycastle.crypto.engines;

import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Wrapper;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.util.DigestFactory;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class RC2WrapEngine implements Wrapper {
    private static final byte[] IV2 = {74, -35, -94, 44, 121, -24, 33, 5};
    private CBCBlockCipher engine;
    private boolean forWrapping;
    private byte[] iv;
    private CipherParameters param;
    private ParametersWithIV paramPlusIV;
    private SecureRandom sr;
    Digest sha1 = DigestFactory.createSHA1();
    byte[] digest = new byte[20];

    private byte[] calculateCMSKeyChecksum(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        this.sha1.update(bArr, 0, bArr.length);
        this.sha1.doFinal(this.digest, 0);
        System.arraycopy(this.digest, 0, bArr2, 0, 8);
        return bArr2;
    }

    private boolean checkCMSKeyChecksum(byte[] bArr, byte[] bArr2) {
        return Arrays.constantTimeAreEqual(calculateCMSKeyChecksum(bArr), bArr2);
    }

    @Override // org.spongycastle.crypto.Wrapper
    public String getAlgorithmName() {
        return "RC2";
    }

    @Override // org.spongycastle.crypto.Wrapper
    public void init(boolean z2, CipherParameters cipherParameters) {
        this.forWrapping = z2;
        this.engine = new CBCBlockCipher(new RC2Engine());
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.sr = parametersWithRandom.getRandom();
            cipherParameters = parametersWithRandom.getParameters();
        } else {
            this.sr = new SecureRandom();
        }
        if (!(cipherParameters instanceof ParametersWithIV)) {
            this.param = cipherParameters;
            if (this.forWrapping) {
                byte[] bArr = new byte[8];
                this.iv = bArr;
                this.sr.nextBytes(bArr);
                this.paramPlusIV = new ParametersWithIV(this.param, this.iv);
                return;
            }
            return;
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        this.paramPlusIV = parametersWithIV;
        this.iv = parametersWithIV.getIV();
        this.param = this.paramPlusIV.getParameters();
        if (!this.forWrapping) {
            throw new IllegalArgumentException("You should not supply an IV for unwrapping");
        }
        byte[] bArr2 = this.iv;
        if (bArr2 == null || bArr2.length != 8) {
            throw new IllegalArgumentException("IV is not 8 octets");
        }
    }

    @Override // org.spongycastle.crypto.Wrapper
    public byte[] unwrap(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        if (this.forWrapping) {
            throw new IllegalStateException("Not set for unwrapping");
        }
        if (bArr == null) {
            throw new InvalidCipherTextException("Null pointer as ciphertext");
        }
        if (i3 % this.engine.getBlockSize() != 0) {
            throw new InvalidCipherTextException("Ciphertext not multiple of " + this.engine.getBlockSize());
        }
        this.engine.init(false, new ParametersWithIV(this.param, IV2));
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        for (int i4 = 0; i4 < i3 / this.engine.getBlockSize(); i4++) {
            int blockSize = this.engine.getBlockSize() * i4;
            this.engine.processBlock(bArr2, blockSize, bArr2, blockSize);
        }
        byte[] bArr3 = new byte[i3];
        int i5 = 0;
        while (i5 < i3) {
            int i6 = i5 + 1;
            bArr3[i5] = bArr2[i3 - i6];
            i5 = i6;
        }
        byte[] bArr4 = new byte[8];
        this.iv = bArr4;
        int i7 = i3 - 8;
        byte[] bArr5 = new byte[i7];
        System.arraycopy(bArr3, 0, bArr4, 0, 8);
        System.arraycopy(bArr3, 8, bArr5, 0, i7);
        ParametersWithIV parametersWithIV = new ParametersWithIV(this.param, this.iv);
        this.paramPlusIV = parametersWithIV;
        this.engine.init(false, parametersWithIV);
        byte[] bArr6 = new byte[i7];
        System.arraycopy(bArr5, 0, bArr6, 0, i7);
        for (int i8 = 0; i8 < i7 / this.engine.getBlockSize(); i8++) {
            int blockSize2 = this.engine.getBlockSize() * i8;
            this.engine.processBlock(bArr6, blockSize2, bArr6, blockSize2);
        }
        int i9 = i3 - 16;
        byte[] bArr7 = new byte[i9];
        byte[] bArr8 = new byte[8];
        System.arraycopy(bArr6, 0, bArr7, 0, i9);
        System.arraycopy(bArr6, i9, bArr8, 0, 8);
        if (!checkCMSKeyChecksum(bArr7, bArr8)) {
            throw new InvalidCipherTextException("Checksum inside ciphertext is corrupted");
        }
        int i10 = bArr7[0];
        if (i9 - ((i10 & 255) + 1) <= 7) {
            byte[] bArr9 = new byte[i10];
            System.arraycopy(bArr7, 1, bArr9, 0, i10);
            return bArr9;
        }
        throw new InvalidCipherTextException("too many pad bytes (" + (i9 - ((bArr7[0] & 255) + 1)) + ")");
    }

    @Override // org.spongycastle.crypto.Wrapper
    public byte[] wrap(byte[] bArr, int i2, int i3) {
        if (!this.forWrapping) {
            throw new IllegalStateException("Not initialized for wrapping");
        }
        int i4 = i3 + 1;
        int i5 = i4 % 8;
        int i6 = i5 != 0 ? (8 - i5) + i4 : i4;
        byte[] bArr2 = new byte[i6];
        bArr2[0] = (byte) i3;
        System.arraycopy(bArr, i2, bArr2, 1, i3);
        int i7 = (i6 - i3) - 1;
        byte[] bArr3 = new byte[i7];
        if (i7 > 0) {
            this.sr.nextBytes(bArr3);
            System.arraycopy(bArr3, 0, bArr2, i4, i7);
        }
        byte[] bArrCalculateCMSKeyChecksum = calculateCMSKeyChecksum(bArr2);
        int length = bArrCalculateCMSKeyChecksum.length + i6;
        byte[] bArr4 = new byte[length];
        System.arraycopy(bArr2, 0, bArr4, 0, i6);
        System.arraycopy(bArrCalculateCMSKeyChecksum, 0, bArr4, i6, bArrCalculateCMSKeyChecksum.length);
        byte[] bArr5 = new byte[length];
        System.arraycopy(bArr4, 0, bArr5, 0, length);
        int blockSize = length / this.engine.getBlockSize();
        if (length % this.engine.getBlockSize() != 0) {
            throw new IllegalStateException("Not multiple of block length");
        }
        this.engine.init(true, this.paramPlusIV);
        for (int i8 = 0; i8 < blockSize; i8++) {
            int blockSize2 = this.engine.getBlockSize() * i8;
            this.engine.processBlock(bArr5, blockSize2, bArr5, blockSize2);
        }
        byte[] bArr6 = this.iv;
        int length2 = bArr6.length + length;
        byte[] bArr7 = new byte[length2];
        System.arraycopy(bArr6, 0, bArr7, 0, bArr6.length);
        System.arraycopy(bArr5, 0, bArr7, this.iv.length, length);
        byte[] bArr8 = new byte[length2];
        int i9 = 0;
        while (i9 < length2) {
            int i10 = i9 + 1;
            bArr8[i9] = bArr7[length2 - i10];
            i9 = i10;
        }
        this.engine.init(true, new ParametersWithIV(this.param, IV2));
        for (int i11 = 0; i11 < blockSize + 1; i11++) {
            int blockSize3 = this.engine.getBlockSize() * i11;
            this.engine.processBlock(bArr8, blockSize3, bArr8, blockSize3);
        }
        return bArr8;
    }
}
