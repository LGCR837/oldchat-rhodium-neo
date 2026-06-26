package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class RSAPrivateCrtKeyParameters extends RSAKeyParameters {
    private BigInteger dP;
    private BigInteger dQ;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private BigInteger f7422e;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private BigInteger f7423p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private BigInteger f7424q;
    private BigInteger qInv;

    public RSAPrivateCrtKeyParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8) {
        super(true, bigInteger, bigInteger3);
        this.f7422e = bigInteger2;
        this.f7423p = bigInteger4;
        this.f7424q = bigInteger5;
        this.dP = bigInteger6;
        this.dQ = bigInteger7;
        this.qInv = bigInteger8;
    }

    public BigInteger getDP() {
        return this.dP;
    }

    public BigInteger getDQ() {
        return this.dQ;
    }

    public BigInteger getP() {
        return this.f7423p;
    }

    public BigInteger getPublicExponent() {
        return this.f7422e;
    }

    public BigInteger getQ() {
        return this.f7424q;
    }

    public BigInteger getQInv() {
        return this.qInv;
    }
}
