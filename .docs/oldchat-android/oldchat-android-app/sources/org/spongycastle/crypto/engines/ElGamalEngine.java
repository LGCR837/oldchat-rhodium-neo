package org.spongycastle.crypto.engines;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.params.ElGamalKeyParameters;
import org.spongycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.spongycastle.crypto.params.ElGamalPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.util.BigIntegers;

/* JADX INFO: loaded from: classes.dex */
public class ElGamalEngine implements AsymmetricBlockCipher {
    private int bitSize;
    private boolean forEncryption;
    private ElGamalKeyParameters key;
    private SecureRandom random;
    private static final BigInteger ZERO = BigInteger.valueOf(0);
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        return this.forEncryption ? (this.bitSize - 1) / 8 : ((this.bitSize + 7) / 8) * 2;
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        return this.forEncryption ? ((this.bitSize + 7) / 8) * 2 : (this.bitSize - 1) / 8;
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.key = (ElGamalKeyParameters) parametersWithRandom.getParameters();
            this.random = parametersWithRandom.getRandom();
        } else {
            this.key = (ElGamalKeyParameters) cipherParameters;
            this.random = new SecureRandom();
        }
        this.forEncryption = z2;
        this.bitSize = this.key.getParameters().getP().bitLength();
        if (z2) {
            if (!(this.key instanceof ElGamalPublicKeyParameters)) {
                throw new IllegalArgumentException("ElGamalPublicKeyParameters are required for encryption.");
            }
        } else if (!(this.key instanceof ElGamalPrivateKeyParameters)) {
            throw new IllegalArgumentException("ElGamalPrivateKeyParameters are required for decryption.");
        }
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] bArr, int i2, int i3) {
        if (this.key == null) {
            throw new IllegalStateException("ElGamal engine not initialised");
        }
        if (i3 > (this.forEncryption ? (this.bitSize + 6) / 8 : getInputBlockSize())) {
            throw new DataLengthException("input too large for ElGamal cipher.\n");
        }
        BigInteger p2 = this.key.getParameters().getP();
        if (this.key instanceof ElGamalPrivateKeyParameters) {
            int i4 = i3 / 2;
            byte[] bArr2 = new byte[i4];
            byte[] bArr3 = new byte[i4];
            System.arraycopy(bArr, i2, bArr2, 0, i4);
            System.arraycopy(bArr, i2 + i4, bArr3, 0, i4);
            return BigIntegers.asUnsignedByteArray(new BigInteger(1, bArr2).modPow(p2.subtract(ONE).subtract(((ElGamalPrivateKeyParameters) this.key).getX()), p2).multiply(new BigInteger(1, bArr3)).mod(p2));
        }
        if (i2 != 0 || i3 != bArr.length) {
            byte[] bArr4 = new byte[i3];
            System.arraycopy(bArr, i2, bArr4, 0, i3);
            bArr = bArr4;
        }
        BigInteger bigInteger = new BigInteger(1, bArr);
        if (bigInteger.compareTo(p2) >= 0) {
            throw new DataLengthException("input too large for ElGamal cipher.\n");
        }
        ElGamalPublicKeyParameters elGamalPublicKeyParameters = (ElGamalPublicKeyParameters) this.key;
        int iBitLength = p2.bitLength();
        BigInteger bigInteger2 = new BigInteger(iBitLength, this.random);
        while (true) {
            if (!bigInteger2.equals(ZERO) && bigInteger2.compareTo(p2.subtract(TWO)) <= 0) {
                break;
            }
            bigInteger2 = new BigInteger(iBitLength, this.random);
        }
        BigInteger bigIntegerModPow = this.key.getParameters().getG().modPow(bigInteger2, p2);
        BigInteger bigIntegerMod = bigInteger.multiply(elGamalPublicKeyParameters.getY().modPow(bigInteger2, p2)).mod(p2);
        byte[] byteArray = bigIntegerModPow.toByteArray();
        byte[] byteArray2 = bigIntegerMod.toByteArray();
        int outputBlockSize = getOutputBlockSize();
        byte[] bArr5 = new byte[outputBlockSize];
        int i5 = outputBlockSize / 2;
        if (byteArray.length > i5) {
            System.arraycopy(byteArray, 1, bArr5, i5 - (byteArray.length - 1), byteArray.length - 1);
        } else {
            System.arraycopy(byteArray, 0, bArr5, i5 - byteArray.length, byteArray.length);
        }
        if (byteArray2.length > i5) {
            System.arraycopy(byteArray2, 1, bArr5, outputBlockSize - (byteArray2.length - 1), byteArray2.length - 1);
        } else {
            System.arraycopy(byteArray2, 0, bArr5, outputBlockSize - byteArray2.length, byteArray2.length);
        }
        return bArr5;
    }
}
