package org.spongycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;

/* JADX INFO: loaded from: classes.dex */
public class GOST3410PublicKeySpec implements KeySpec {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BigInteger f7492a;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private BigInteger f7493p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private BigInteger f7494q;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private BigInteger f7495y;

    public GOST3410PublicKeySpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.f7495y = bigInteger;
        this.f7493p = bigInteger2;
        this.f7494q = bigInteger3;
        this.f7492a = bigInteger4;
    }

    public BigInteger getA() {
        return this.f7492a;
    }

    public BigInteger getP() {
        return this.f7493p;
    }

    public BigInteger getQ() {
        return this.f7494q;
    }

    public BigInteger getY() {
        return this.f7495y;
    }
}
