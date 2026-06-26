package org.spongycastle.jcajce.provider.asymmetric.dh;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.agreement.kdf.DHKEKGenerator;
import org.spongycastle.crypto.util.DigestFactory;
import org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi;
import org.spongycastle.jcajce.spec.UserKeyingMaterialSpec;

/* JADX INFO: loaded from: classes.dex */
public class KeyAgreementSpi extends BaseAgreementSpi {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private BigInteger f7446g;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private BigInteger f7447p;
    private BigInteger result;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private BigInteger f7448x;

    public static class DHwithRFC2631KDF extends KeyAgreementSpi {
        public DHwithRFC2631KDF() {
            super("DHwithRFC2631KDF", new DHKEKGenerator(DigestFactory.createSHA1()));
        }
    }

    public KeyAgreementSpi() {
        super("Diffie-Hellman", null);
    }

    public byte[] bigIntToBytes(BigInteger bigInteger) {
        int iBitLength = (this.f7447p.bitLength() + 7) / 8;
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length == iBitLength) {
            return byteArray;
        }
        if (byteArray[0] != 0 || byteArray.length != iBitLength + 1) {
            byte[] bArr = new byte[iBitLength];
            System.arraycopy(byteArray, 0, bArr, iBitLength - byteArray.length, byteArray.length);
            return bArr;
        }
        int length = byteArray.length - 1;
        byte[] bArr2 = new byte[length];
        System.arraycopy(byteArray, 1, bArr2, 0, length);
        return bArr2;
    }

    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi
    public byte[] calcSecret() {
        return bigIntToBytes(this.result);
    }

    @Override // javax.crypto.KeyAgreementSpi
    public Key engineDoPhase(Key key, boolean z2) throws InvalidKeyException {
        if (this.f7448x == null) {
            throw new IllegalStateException("Diffie-Hellman not initialised.");
        }
        if (!(key instanceof DHPublicKey)) {
            throw new InvalidKeyException("DHKeyAgreement doPhase requires DHPublicKey");
        }
        DHPublicKey dHPublicKey = (DHPublicKey) key;
        if (!dHPublicKey.getParams().getG().equals(this.f7446g) || !dHPublicKey.getParams().getP().equals(this.f7447p)) {
            throw new InvalidKeyException("DHPublicKey not for this KeyAgreement!");
        }
        BigInteger y2 = dHPublicKey.getY();
        if (y2 != null && y2.compareTo(TWO) >= 0) {
            BigInteger bigInteger = this.f7447p;
            BigInteger bigInteger2 = ONE;
            if (y2.compareTo(bigInteger.subtract(bigInteger2)) < 0) {
                BigInteger bigIntegerModPow = y2.modPow(this.f7448x, this.f7447p);
                this.result = bigIntegerModPow;
                if (bigIntegerModPow.compareTo(bigInteger2) == 0) {
                    throw new InvalidKeyException("Shared key can't be 1");
                }
                if (z2) {
                    return null;
                }
                return new BCDHPublicKey(this.result, dHPublicKey.getParams());
            }
        }
        throw new InvalidKeyException("Invalid DH PublicKey");
    }

    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi, javax.crypto.KeyAgreementSpi
    public byte[] engineGenerateSecret() {
        if (this.f7448x != null) {
            return super.engineGenerateSecret();
        }
        throw new IllegalStateException("Diffie-Hellman not initialised.");
    }

    @Override // javax.crypto.KeyAgreementSpi
    public void engineInit(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (!(key instanceof DHPrivateKey)) {
            throw new InvalidKeyException("DHKeyAgreement requires DHPrivateKey for initialisation");
        }
        DHPrivateKey dHPrivateKey = (DHPrivateKey) key;
        if (algorithmParameterSpec == null) {
            this.f7447p = dHPrivateKey.getParams().getP();
            this.f7446g = dHPrivateKey.getParams().getG();
        } else if (algorithmParameterSpec instanceof DHParameterSpec) {
            DHParameterSpec dHParameterSpec = (DHParameterSpec) algorithmParameterSpec;
            this.f7447p = dHParameterSpec.getP();
            this.f7446g = dHParameterSpec.getG();
        } else {
            if (!(algorithmParameterSpec instanceof UserKeyingMaterialSpec)) {
                throw new InvalidAlgorithmParameterException("DHKeyAgreement only accepts DHParameterSpec");
            }
            this.f7447p = dHPrivateKey.getParams().getP();
            this.f7446g = dHPrivateKey.getParams().getG();
            this.ukmParameters = ((UserKeyingMaterialSpec) algorithmParameterSpec).getUserKeyingMaterial();
        }
        BigInteger x2 = dHPrivateKey.getX();
        this.result = x2;
        this.f7448x = x2;
    }

    public KeyAgreementSpi(String str, DerivationFunction derivationFunction) {
        super(str, derivationFunction);
    }

    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi, javax.crypto.KeyAgreementSpi
    public int engineGenerateSecret(byte[] bArr, int i2) {
        if (this.f7448x != null) {
            return super.engineGenerateSecret(bArr, i2);
        }
        throw new IllegalStateException("Diffie-Hellman not initialised.");
    }

    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi, javax.crypto.KeyAgreementSpi
    public SecretKey engineGenerateSecret(String str) {
        if (this.f7448x != null) {
            byte[] bArrBigIntToBytes = bigIntToBytes(this.result);
            if (str.equals("TlsPremasterSecret")) {
                return new SecretKeySpec(BaseAgreementSpi.trimZeroes(bArrBigIntToBytes), str);
            }
            return super.engineGenerateSecret(str);
        }
        throw new IllegalStateException("Diffie-Hellman not initialised.");
    }

    @Override // javax.crypto.KeyAgreementSpi
    public void engineInit(Key key, SecureRandom secureRandom) throws InvalidKeyException {
        if (key instanceof DHPrivateKey) {
            DHPrivateKey dHPrivateKey = (DHPrivateKey) key;
            this.f7447p = dHPrivateKey.getParams().getP();
            this.f7446g = dHPrivateKey.getParams().getG();
            BigInteger x2 = dHPrivateKey.getX();
            this.result = x2;
            this.f7448x = x2;
            return;
        }
        throw new InvalidKeyException("DHKeyAgreement requires DHPrivateKey");
    }
}
