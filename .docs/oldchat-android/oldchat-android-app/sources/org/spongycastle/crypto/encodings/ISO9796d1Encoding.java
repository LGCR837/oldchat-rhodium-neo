package org.spongycastle.crypto.encodings;

import java.math.BigInteger;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.RSAKeyParameters;

/* JADX INFO: loaded from: classes.dex */
public class ISO9796d1Encoding implements AsymmetricBlockCipher {
    private int bitSize;
    private AsymmetricBlockCipher engine;
    private boolean forEncryption;
    private BigInteger modulus;
    private int padBits = 0;
    private static final BigInteger SIXTEEN = BigInteger.valueOf(16);
    private static final BigInteger SIX = BigInteger.valueOf(6);
    private static byte[] shadows = {14, 3, 5, 8, 9, 4, 2, 15, 0, 13, 11, 6, 7, 10, 12, 1};
    private static byte[] inverse = {8, 15, 6, 1, 5, 2, 11, 12, 3, 4, 13, 10, 14, 9, 0, 7};

    public ISO9796d1Encoding(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.engine = asymmetricBlockCipher;
    }

    private static byte[] convertOutputDecryptOnly(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] != 0) {
            return byteArray;
        }
        int length = byteArray.length - 1;
        byte[] bArr = new byte[length];
        System.arraycopy(byteArray, 1, bArr, 0, length);
        return bArr;
    }

    private byte[] decodeBlock(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        byte[] bArrProcessBlock = this.engine.processBlock(bArr, i2, i3);
        int i4 = (this.bitSize + 13) / 16;
        BigInteger bigInteger = new BigInteger(1, bArrProcessBlock);
        BigInteger bigInteger2 = SIXTEEN;
        BigInteger bigIntegerMod = bigInteger.mod(bigInteger2);
        BigInteger bigInteger3 = SIX;
        if (!bigIntegerMod.equals(bigInteger3)) {
            if (!this.modulus.subtract(bigInteger).mod(bigInteger2).equals(bigInteger3)) {
                throw new InvalidCipherTextException("resulting integer iS or (modulus - iS) is not congruent to 6 mod 16");
            }
            bigInteger = this.modulus.subtract(bigInteger);
        }
        byte[] bArrConvertOutputDecryptOnly = convertOutputDecryptOnly(bigInteger);
        if ((bArrConvertOutputDecryptOnly[bArrConvertOutputDecryptOnly.length - 1] & 15) != 6) {
            throw new InvalidCipherTextException("invalid forcing byte in block");
        }
        bArrConvertOutputDecryptOnly[bArrConvertOutputDecryptOnly.length - 1] = (byte) (((bArrConvertOutputDecryptOnly[bArrConvertOutputDecryptOnly.length - 1] & 255) >>> 4) | (inverse[(bArrConvertOutputDecryptOnly[bArrConvertOutputDecryptOnly.length - 2] & 255) >> 4] << 4));
        byte[] bArr2 = shadows;
        byte b2 = bArrConvertOutputDecryptOnly[1];
        byte b3 = (byte) (bArr2[b2 & 15] | (bArr2[(b2 & 255) >>> 4] << 4));
        bArrConvertOutputDecryptOnly[0] = b3;
        int i5 = 0;
        boolean z2 = false;
        int i6 = 1;
        for (int length = bArrConvertOutputDecryptOnly.length - 1; length >= bArrConvertOutputDecryptOnly.length - (i4 * 2); length -= 2) {
            byte[] bArr3 = shadows;
            byte b4 = bArrConvertOutputDecryptOnly[length];
            int i7 = bArr3[b4 & 15] | (bArr3[(b4 & 255) >>> 4] << 4);
            int i8 = length - 1;
            byte b5 = bArrConvertOutputDecryptOnly[i8];
            if (((b5 ^ i7) & 255) != 0) {
                if (z2) {
                    throw new InvalidCipherTextException("invalid tsums in block");
                }
                i6 = (b5 ^ i7) & 255;
                i5 = i8;
                z2 = true;
            }
        }
        bArrConvertOutputDecryptOnly[i5] = 0;
        int length2 = (bArrConvertOutputDecryptOnly.length - i5) / 2;
        byte[] bArr4 = new byte[length2];
        for (int i9 = 0; i9 < length2; i9++) {
            bArr4[i9] = bArrConvertOutputDecryptOnly[(i9 * 2) + i5 + 1];
        }
        this.padBits = i6 - 1;
        return bArr4;
    }

    private byte[] encodeBlock(byte[] bArr, int i2, int i3) {
        int i4 = this.bitSize;
        int i5 = (i4 + 7) / 8;
        byte[] bArr2 = new byte[i5];
        int i6 = 1;
        int i7 = this.padBits + 1;
        int i8 = (i4 + 13) / 16;
        int i9 = 0;
        while (i9 < i8) {
            if (i9 > i8 - i3) {
                int i10 = i8 - i9;
                System.arraycopy(bArr, (i2 + i3) - i10, bArr2, i5 - i8, i10);
            } else {
                System.arraycopy(bArr, i2, bArr2, i5 - (i9 + i3), i3);
            }
            i9 += i3;
        }
        for (int i11 = i5 - (i8 * 2); i11 != i5; i11 += 2) {
            byte b2 = bArr2[(i5 - i8) + (i11 / 2)];
            byte[] bArr3 = shadows;
            bArr2[i11] = (byte) (bArr3[b2 & 15] | (bArr3[(b2 & 255) >>> 4] << 4));
            bArr2[i11 + 1] = b2;
        }
        int i12 = i5 - (i3 * 2);
        bArr2[i12] = (byte) (bArr2[i12] ^ i7);
        int i13 = i5 - 1;
        bArr2[i13] = (byte) ((bArr2[i13] << 4) | 6);
        int i14 = 8 - ((this.bitSize - 1) % 8);
        if (i14 != 8) {
            byte b3 = (byte) (bArr2[0] & (255 >>> i14));
            bArr2[0] = b3;
            bArr2[0] = (byte) ((128 >>> i14) | b3);
            i6 = 0;
        } else {
            bArr2[0] = 0;
            bArr2[1] = (byte) (bArr2[1] | 128);
        }
        return this.engine.processBlock(bArr2, i6, i5 - i6);
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        int inputBlockSize = this.engine.getInputBlockSize();
        return this.forEncryption ? (inputBlockSize + 1) / 2 : inputBlockSize;
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        int outputBlockSize = this.engine.getOutputBlockSize();
        return this.forEncryption ? outputBlockSize : (outputBlockSize + 1) / 2;
    }

    public int getPadBits() {
        return this.padBits;
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.engine;
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        RSAKeyParameters rSAKeyParameters = cipherParameters instanceof ParametersWithRandom ? (RSAKeyParameters) ((ParametersWithRandom) cipherParameters).getParameters() : (RSAKeyParameters) cipherParameters;
        this.engine.init(z2, cipherParameters);
        BigInteger modulus = rSAKeyParameters.getModulus();
        this.modulus = modulus;
        this.bitSize = modulus.bitLength();
        this.forEncryption = z2;
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] bArr, int i2, int i3) {
        return this.forEncryption ? encodeBlock(bArr, i2, i3) : decodeBlock(bArr, i2, i3);
    }

    public void setPadBits(int i2) {
        if (i2 > 7) {
            throw new IllegalArgumentException("padBits > 7");
        }
        this.padBits = i2;
    }
}
