package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class SRP6GroupParameters {

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    private BigInteger f7425N;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private BigInteger f7426g;

    public SRP6GroupParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f7425N = bigInteger;
        this.f7426g = bigInteger2;
    }

    public BigInteger getG() {
        return this.f7426g;
    }

    public BigInteger getN() {
        return this.f7425N;
    }
}
