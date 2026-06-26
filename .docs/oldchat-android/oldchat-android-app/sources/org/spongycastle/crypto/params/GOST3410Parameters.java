package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

/* JADX INFO: loaded from: classes.dex */
public class GOST3410Parameters implements CipherParameters {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BigInteger f7411a;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private BigInteger f7412p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private BigInteger f7413q;
    private GOST3410ValidationParameters validation;

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f7412p = bigInteger;
        this.f7413q = bigInteger2;
        this.f7411a = bigInteger3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410Parameters)) {
            return false;
        }
        GOST3410Parameters gOST3410Parameters = (GOST3410Parameters) obj;
        return gOST3410Parameters.getP().equals(this.f7412p) && gOST3410Parameters.getQ().equals(this.f7413q) && gOST3410Parameters.getA().equals(this.f7411a);
    }

    public BigInteger getA() {
        return this.f7411a;
    }

    public BigInteger getP() {
        return this.f7412p;
    }

    public BigInteger getQ() {
        return this.f7413q;
    }

    public GOST3410ValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (this.f7412p.hashCode() ^ this.f7413q.hashCode()) ^ this.f7411a.hashCode();
    }

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, GOST3410ValidationParameters gOST3410ValidationParameters) {
        this.f7411a = bigInteger3;
        this.f7412p = bigInteger;
        this.f7413q = bigInteger2;
        this.validation = gOST3410ValidationParameters;
    }
}
