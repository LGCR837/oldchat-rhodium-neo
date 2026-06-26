package org.spongycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class ElGamalParameterSpec implements AlgorithmParameterSpec {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private BigInteger f7481g;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private BigInteger f7482p;

    public ElGamalParameterSpec(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f7482p = bigInteger;
        this.f7481g = bigInteger2;
    }

    public BigInteger getG() {
        return this.f7481g;
    }

    public BigInteger getP() {
        return this.f7482p;
    }
}
