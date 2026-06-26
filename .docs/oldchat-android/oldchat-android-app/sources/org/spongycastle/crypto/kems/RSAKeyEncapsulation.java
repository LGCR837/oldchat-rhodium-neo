package org.spongycastle.crypto.kems;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.KeyEncapsulation;
import org.spongycastle.crypto.params.KDFParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.util.BigIntegers;

/* JADX INFO: loaded from: classes.dex */
public class RSAKeyEncapsulation implements KeyEncapsulation {
    private DerivationFunction kdf;
    private RSAKeyParameters key;
    private SecureRandom rnd;
    private static final BigInteger ZERO = BigInteger.valueOf(0);
    private static final BigInteger ONE = BigInteger.valueOf(1);

    public RSAKeyEncapsulation(DerivationFunction derivationFunction, SecureRandom secureRandom) {
        this.kdf = derivationFunction;
        this.rnd = secureRandom;
    }

    @Override // org.spongycastle.crypto.KeyEncapsulation
    public CipherParameters decrypt(byte[] bArr, int i2, int i3, int i4) {
        if (!this.key.isPrivate()) {
            throw new IllegalArgumentException("Private key required for decryption");
        }
        BigInteger modulus = this.key.getModulus();
        BigInteger exponent = this.key.getExponent();
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        return generateKey(modulus, new BigInteger(1, bArr2).modPow(exponent, modulus), i4);
    }

    @Override // org.spongycastle.crypto.KeyEncapsulation
    public CipherParameters encrypt(byte[] bArr, int i2, int i3) {
        if (this.key.isPrivate()) {
            throw new IllegalArgumentException("Public key required for encryption");
        }
        BigInteger modulus = this.key.getModulus();
        BigInteger exponent = this.key.getExponent();
        BigInteger bigIntegerCreateRandomInRange = BigIntegers.createRandomInRange(ZERO, modulus.subtract(ONE), this.rnd);
        byte[] bArrAsUnsignedByteArray = BigIntegers.asUnsignedByteArray((modulus.bitLength() + 7) / 8, bigIntegerCreateRandomInRange.modPow(exponent, modulus));
        System.arraycopy(bArrAsUnsignedByteArray, 0, bArr, i2, bArrAsUnsignedByteArray.length);
        return generateKey(modulus, bigIntegerCreateRandomInRange, i3);
    }

    public KeyParameter generateKey(BigInteger bigInteger, BigInteger bigInteger2, int i2) {
        this.kdf.init(new KDFParameters(BigIntegers.asUnsignedByteArray((bigInteger.bitLength() + 7) / 8, bigInteger2), null));
        byte[] bArr = new byte[i2];
        this.kdf.generateBytes(bArr, 0, i2);
        return new KeyParameter(bArr);
    }

    @Override // org.spongycastle.crypto.KeyEncapsulation
    public void init(CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof RSAKeyParameters)) {
            throw new IllegalArgumentException("RSA key required");
        }
        this.key = (RSAKeyParameters) cipherParameters;
    }

    public CipherParameters decrypt(byte[] bArr, int i2) {
        return decrypt(bArr, 0, bArr.length, i2);
    }

    public CipherParameters encrypt(byte[] bArr, int i2) {
        return encrypt(bArr, 0, i2);
    }
}
