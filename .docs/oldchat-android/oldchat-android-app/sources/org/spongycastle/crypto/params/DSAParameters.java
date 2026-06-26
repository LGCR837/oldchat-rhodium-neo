package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

/* JADX INFO: loaded from: classes.dex */
public class DSAParameters implements CipherParameters {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private BigInteger f7396g;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private BigInteger f7397p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private BigInteger f7398q;
    private DSAValidationParameters validation;

    public DSAParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f7396g = bigInteger3;
        this.f7397p = bigInteger;
        this.f7398q = bigInteger2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DSAParameters)) {
            return false;
        }
        DSAParameters dSAParameters = (DSAParameters) obj;
        return dSAParameters.getP().equals(this.f7397p) && dSAParameters.getQ().equals(this.f7398q) && dSAParameters.getG().equals(this.f7396g);
    }

    public BigInteger getG() {
        return this.f7396g;
    }

    public BigInteger getP() {
        return this.f7397p;
    }

    public BigInteger getQ() {
        return this.f7398q;
    }

    public DSAValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getQ().hashCode()) ^ getG().hashCode();
    }

    public DSAParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, DSAValidationParameters dSAValidationParameters) {
        this.f7396g = bigInteger3;
        this.f7397p = bigInteger;
        this.f7398q = bigInteger2;
        this.validation = dSAValidationParameters;
    }
}
