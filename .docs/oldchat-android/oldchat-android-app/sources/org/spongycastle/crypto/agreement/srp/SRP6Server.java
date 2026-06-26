package org.spongycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.SRP6GroupParameters;

/* JADX INFO: loaded from: classes.dex */
public class SRP6Server {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    protected BigInteger f7257A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    protected BigInteger f7258B;
    protected BigInteger Key;
    protected BigInteger M1;
    protected BigInteger M2;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    protected BigInteger f7259N;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    protected BigInteger f7260S;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected BigInteger f7261b;
    protected Digest digest;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected BigInteger f7262g;
    protected SecureRandom random;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    protected BigInteger f7263u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    protected BigInteger f7264v;

    private BigInteger calculateS() {
        return this.f7264v.modPow(this.f7263u, this.f7259N).multiply(this.f7257A).mod(this.f7259N).modPow(this.f7261b, this.f7259N);
    }

    public BigInteger calculateSecret(BigInteger bigInteger) throws CryptoException {
        BigInteger bigIntegerValidatePublicValue = SRP6Util.validatePublicValue(this.f7259N, bigInteger);
        this.f7257A = bigIntegerValidatePublicValue;
        this.f7263u = SRP6Util.calculateU(this.digest, this.f7259N, bigIntegerValidatePublicValue, this.f7258B);
        BigInteger bigIntegerCalculateS = calculateS();
        this.f7260S = bigIntegerCalculateS;
        return bigIntegerCalculateS;
    }

    public BigInteger calculateServerEvidenceMessage() throws CryptoException {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = this.f7257A;
        if (bigInteger3 == null || (bigInteger = this.M1) == null || (bigInteger2 = this.f7260S) == null) {
            throw new CryptoException("Impossible to compute M2: some data are missing from the previous operations (A,M1,S)");
        }
        BigInteger bigIntegerCalculateM2 = SRP6Util.calculateM2(this.digest, this.f7259N, bigInteger3, bigInteger, bigInteger2);
        this.M2 = bigIntegerCalculateM2;
        return bigIntegerCalculateM2;
    }

    public BigInteger calculateSessionKey() throws CryptoException {
        BigInteger bigInteger = this.f7260S;
        if (bigInteger == null || this.M1 == null || this.M2 == null) {
            throw new CryptoException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        BigInteger bigIntegerCalculateKey = SRP6Util.calculateKey(this.digest, this.f7259N, bigInteger);
        this.Key = bigIntegerCalculateKey;
        return bigIntegerCalculateKey;
    }

    public BigInteger generateServerCredentials() {
        BigInteger bigIntegerCalculateK = SRP6Util.calculateK(this.digest, this.f7259N, this.f7262g);
        this.f7261b = selectPrivateValue();
        BigInteger bigIntegerMod = bigIntegerCalculateK.multiply(this.f7264v).mod(this.f7259N).add(this.f7262g.modPow(this.f7261b, this.f7259N)).mod(this.f7259N);
        this.f7258B = bigIntegerMod;
        return bigIntegerMod;
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, Digest digest, SecureRandom secureRandom) {
        this.f7259N = bigInteger;
        this.f7262g = bigInteger2;
        this.f7264v = bigInteger3;
        this.random = secureRandom;
        this.digest = digest;
    }

    public BigInteger selectPrivateValue() {
        return SRP6Util.generatePrivateValue(this.digest, this.f7259N, this.f7262g, this.random);
    }

    public boolean verifyClientEvidenceMessage(BigInteger bigInteger) throws CryptoException {
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4 = this.f7257A;
        if (bigInteger4 == null || (bigInteger2 = this.f7258B) == null || (bigInteger3 = this.f7260S) == null) {
            throw new CryptoException("Impossible to compute and verify M1: some data are missing from the previous operations (A,B,S)");
        }
        if (!SRP6Util.calculateM1(this.digest, this.f7259N, bigInteger4, bigInteger2, bigInteger3).equals(bigInteger)) {
            return false;
        }
        this.M1 = bigInteger;
        return true;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, BigInteger bigInteger, Digest digest, SecureRandom secureRandom) {
        init(sRP6GroupParameters.getN(), sRP6GroupParameters.getG(), bigInteger, digest, secureRandom);
    }
}
