package org.spongycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.SRP6GroupParameters;

/* JADX INFO: loaded from: classes.dex */
public class SRP6Client {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    protected BigInteger f7249A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    protected BigInteger f7250B;
    protected BigInteger Key;
    protected BigInteger M1;
    protected BigInteger M2;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    protected BigInteger f7251N;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    protected BigInteger f7252S;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected BigInteger f7253a;
    protected Digest digest;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected BigInteger f7254g;
    protected SecureRandom random;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    protected BigInteger f7255u;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    protected BigInteger f7256x;

    private BigInteger calculateS() {
        BigInteger bigIntegerCalculateK = SRP6Util.calculateK(this.digest, this.f7251N, this.f7254g);
        return this.f7250B.subtract(this.f7254g.modPow(this.f7256x, this.f7251N).multiply(bigIntegerCalculateK).mod(this.f7251N)).mod(this.f7251N).modPow(this.f7255u.multiply(this.f7256x).add(this.f7253a), this.f7251N);
    }

    public BigInteger calculateClientEvidenceMessage() throws CryptoException {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = this.f7249A;
        if (bigInteger3 == null || (bigInteger = this.f7250B) == null || (bigInteger2 = this.f7252S) == null) {
            throw new CryptoException("Impossible to compute M1: some data are missing from the previous operations (A,B,S)");
        }
        BigInteger bigIntegerCalculateM1 = SRP6Util.calculateM1(this.digest, this.f7251N, bigInteger3, bigInteger, bigInteger2);
        this.M1 = bigIntegerCalculateM1;
        return bigIntegerCalculateM1;
    }

    public BigInteger calculateSecret(BigInteger bigInteger) throws CryptoException {
        BigInteger bigIntegerValidatePublicValue = SRP6Util.validatePublicValue(this.f7251N, bigInteger);
        this.f7250B = bigIntegerValidatePublicValue;
        this.f7255u = SRP6Util.calculateU(this.digest, this.f7251N, this.f7249A, bigIntegerValidatePublicValue);
        BigInteger bigIntegerCalculateS = calculateS();
        this.f7252S = bigIntegerCalculateS;
        return bigIntegerCalculateS;
    }

    public BigInteger calculateSessionKey() throws CryptoException {
        BigInteger bigInteger = this.f7252S;
        if (bigInteger == null || this.M1 == null || this.M2 == null) {
            throw new CryptoException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        BigInteger bigIntegerCalculateKey = SRP6Util.calculateKey(this.digest, this.f7251N, bigInteger);
        this.Key = bigIntegerCalculateKey;
        return bigIntegerCalculateKey;
    }

    public BigInteger generateClientCredentials(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.f7256x = SRP6Util.calculateX(this.digest, this.f7251N, bArr, bArr2, bArr3);
        BigInteger bigIntegerSelectPrivateValue = selectPrivateValue();
        this.f7253a = bigIntegerSelectPrivateValue;
        BigInteger bigIntegerModPow = this.f7254g.modPow(bigIntegerSelectPrivateValue, this.f7251N);
        this.f7249A = bigIntegerModPow;
        return bigIntegerModPow;
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, Digest digest, SecureRandom secureRandom) {
        this.f7251N = bigInteger;
        this.f7254g = bigInteger2;
        this.digest = digest;
        this.random = secureRandom;
    }

    public BigInteger selectPrivateValue() {
        return SRP6Util.generatePrivateValue(this.digest, this.f7251N, this.f7254g, this.random);
    }

    public boolean verifyServerEvidenceMessage(BigInteger bigInteger) throws CryptoException {
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4 = this.f7249A;
        if (bigInteger4 == null || (bigInteger2 = this.M1) == null || (bigInteger3 = this.f7252S) == null) {
            throw new CryptoException("Impossible to compute and verify M2: some data are missing from the previous operations (A,M1,S)");
        }
        if (!SRP6Util.calculateM2(this.digest, this.f7251N, bigInteger4, bigInteger2, bigInteger3).equals(bigInteger)) {
            return false;
        }
        this.M2 = bigInteger;
        return true;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, Digest digest, SecureRandom secureRandom) {
        init(sRP6GroupParameters.getN(), sRP6GroupParameters.getG(), digest, secureRandom);
    }
}
