package org.spongycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;

/* JADX INFO: loaded from: classes.dex */
public class GOST3410PrivateKeySpec implements KeySpec {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BigInteger f7485a;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private BigInteger f7486p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private BigInteger f7487q;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private BigInteger f7488x;

    public GOST3410PrivateKeySpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.f7488x = bigInteger;
        this.f7486p = bigInteger2;
        this.f7487q = bigInteger3;
        this.f7485a = bigInteger4;
    }

    public BigInteger getA() {
        return this.f7485a;
    }

    public BigInteger getP() {
        return this.f7486p;
    }

    public BigInteger getQ() {
        return this.f7487q;
    }

    public BigInteger getX() {
        return this.f7488x;
    }
}
