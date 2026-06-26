package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

/* JADX INFO: loaded from: classes.dex */
public class DHParameters implements CipherParameters {
    private static final int DEFAULT_MINIMUM_LENGTH = 160;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private BigInteger f7386g;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private BigInteger f7387j;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f7388l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f7389m;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private BigInteger f7390p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private BigInteger f7391q;
    private DHValidationParameters validation;

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, null, 0);
    }

    private static int getDefaultMParam(int i2) {
        if (i2 != 0 && i2 < 160) {
            return i2;
        }
        return 160;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHParameters)) {
            return false;
        }
        DHParameters dHParameters = (DHParameters) obj;
        if (getQ() != null) {
            if (!getQ().equals(dHParameters.getQ())) {
                return false;
            }
        } else if (dHParameters.getQ() != null) {
            return false;
        }
        return dHParameters.getP().equals(this.f7390p) && dHParameters.getG().equals(this.f7386g);
    }

    public BigInteger getG() {
        return this.f7386g;
    }

    public BigInteger getJ() {
        return this.f7387j;
    }

    public int getL() {
        return this.f7388l;
    }

    public int getM() {
        return this.f7389m;
    }

    public BigInteger getP() {
        return this.f7390p;
    }

    public BigInteger getQ() {
        return this.f7391q;
    }

    public DHValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG().hashCode()) ^ (getQ() != null ? getQ().hashCode() : 0);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this(bigInteger, bigInteger2, bigInteger3, 0);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i2) {
        this(bigInteger, bigInteger2, bigInteger3, getDefaultMParam(i2), i2, null, null);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i2, int i3) {
        this(bigInteger, bigInteger2, bigInteger3, i2, i3, null, null);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, DHValidationParameters dHValidationParameters) {
        this(bigInteger, bigInteger2, bigInteger3, 160, 0, bigInteger4, dHValidationParameters);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i2, int i3, BigInteger bigInteger4, DHValidationParameters dHValidationParameters) {
        if (i3 != 0) {
            if (i3 > bigInteger.bitLength()) {
                throw new IllegalArgumentException("when l value specified, it must satisfy 2^(l-1) <= p");
            }
            if (i3 < i2) {
                throw new IllegalArgumentException("when l value specified, it may not be less than m value");
            }
        }
        this.f7386g = bigInteger2;
        this.f7390p = bigInteger;
        this.f7391q = bigInteger3;
        this.f7389m = i2;
        this.f7388l = i3;
        this.f7387j = bigInteger4;
        this.validation = dHValidationParameters;
    }
}
