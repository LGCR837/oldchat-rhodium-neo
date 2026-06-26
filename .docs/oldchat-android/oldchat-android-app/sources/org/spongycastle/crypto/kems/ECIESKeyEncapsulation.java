package org.spongycastle.crypto.kems;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.KeyEncapsulation;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.KDFParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECMultiplier;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.ec.FixedPointCombMultiplier;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

/* JADX INFO: loaded from: classes.dex */
public class ECIESKeyEncapsulation implements KeyEncapsulation {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private boolean CofactorMode;
    private boolean OldCofactorMode;
    private boolean SingleHashMode;
    private DerivationFunction kdf;
    private ECKeyParameters key;
    private SecureRandom rnd;

    public ECIESKeyEncapsulation(DerivationFunction derivationFunction, SecureRandom secureRandom) {
        this.kdf = derivationFunction;
        this.rnd = secureRandom;
        this.CofactorMode = false;
        this.OldCofactorMode = false;
        this.SingleHashMode = false;
    }

    public ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    @Override // org.spongycastle.crypto.KeyEncapsulation
    public CipherParameters decrypt(byte[] bArr, int i2, int i3, int i4) {
        ECKeyParameters eCKeyParameters = this.key;
        if (!(eCKeyParameters instanceof ECPrivateKeyParameters)) {
            throw new IllegalArgumentException("Private key required for encryption");
        }
        ECPrivateKeyParameters eCPrivateKeyParameters = (ECPrivateKeyParameters) eCKeyParameters;
        ECDomainParameters parameters = eCPrivateKeyParameters.getParameters();
        ECCurve curve = parameters.getCurve();
        BigInteger n2 = parameters.getN();
        BigInteger h2 = parameters.getH();
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        ECPoint eCPointDecodePoint = curve.decodePoint(bArr2);
        if (this.CofactorMode || this.OldCofactorMode) {
            eCPointDecodePoint = eCPointDecodePoint.multiply(h2);
        }
        BigInteger d2 = eCPrivateKeyParameters.getD();
        if (this.CofactorMode) {
            d2 = d2.multiply(h2.modInverse(n2)).mod(n2);
        }
        return deriveKey(i4, bArr2, eCPointDecodePoint.multiply(d2).normalize().getAffineXCoord().getEncoded());
    }

    public KeyParameter deriveKey(int i2, byte[] bArr, byte[] bArr2) {
        if (!this.SingleHashMode) {
            byte[] bArrConcatenate = Arrays.concatenate(bArr, bArr2);
            Arrays.fill(bArr2, (byte) 0);
            bArr2 = bArrConcatenate;
        }
        try {
            this.kdf.init(new KDFParameters(bArr2, null));
            byte[] bArr3 = new byte[i2];
            this.kdf.generateBytes(bArr3, 0, i2);
            return new KeyParameter(bArr3);
        } finally {
            Arrays.fill(bArr2, (byte) 0);
        }
    }

    @Override // org.spongycastle.crypto.KeyEncapsulation
    public CipherParameters encrypt(byte[] bArr, int i2, int i3) {
        ECKeyParameters eCKeyParameters = this.key;
        if (!(eCKeyParameters instanceof ECPublicKeyParameters)) {
            throw new IllegalArgumentException("Public key required for encryption");
        }
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) eCKeyParameters;
        ECDomainParameters parameters = eCPublicKeyParameters.getParameters();
        ECCurve curve = parameters.getCurve();
        BigInteger n2 = parameters.getN();
        BigInteger h2 = parameters.getH();
        BigInteger bigIntegerCreateRandomInRange = BigIntegers.createRandomInRange(ONE, n2, this.rnd);
        ECPoint[] eCPointArr = {createBasePointMultiplier().multiply(parameters.getG(), bigIntegerCreateRandomInRange), eCPublicKeyParameters.getQ().multiply(this.CofactorMode ? bigIntegerCreateRandomInRange.multiply(h2).mod(n2) : bigIntegerCreateRandomInRange)};
        curve.normalizeAll(eCPointArr);
        ECPoint eCPoint = eCPointArr[0];
        ECPoint eCPoint2 = eCPointArr[1];
        byte[] encoded = eCPoint.getEncoded(false);
        System.arraycopy(encoded, 0, bArr, i2, encoded.length);
        return deriveKey(i3, encoded, eCPoint2.getAffineXCoord().getEncoded());
    }

    @Override // org.spongycastle.crypto.KeyEncapsulation
    public void init(CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ECKeyParameters)) {
            throw new IllegalArgumentException("EC key required");
        }
        this.key = (ECKeyParameters) cipherParameters;
    }

    public ECIESKeyEncapsulation(DerivationFunction derivationFunction, SecureRandom secureRandom, boolean z2, boolean z3, boolean z4) {
        this.kdf = derivationFunction;
        this.rnd = secureRandom;
        this.CofactorMode = z2;
        this.OldCofactorMode = z3;
        this.SingleHashMode = z4;
    }

    public CipherParameters decrypt(byte[] bArr, int i2) {
        return decrypt(bArr, 0, bArr.length, i2);
    }

    public CipherParameters encrypt(byte[] bArr, int i2) {
        return encrypt(bArr, 0, i2);
    }
}
