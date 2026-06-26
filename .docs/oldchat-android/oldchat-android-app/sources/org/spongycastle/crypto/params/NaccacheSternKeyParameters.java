package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class NaccacheSternKeyParameters extends AsymmetricKeyParameter {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private BigInteger f7420g;
    int lowerSigmaBound;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private BigInteger f7421n;

    public NaccacheSternKeyParameters(boolean z2, BigInteger bigInteger, BigInteger bigInteger2, int i2) {
        super(z2);
        this.f7420g = bigInteger;
        this.f7421n = bigInteger2;
        this.lowerSigmaBound = i2;
    }

    public BigInteger getG() {
        return this.f7420g;
    }

    public int getLowerSigmaBound() {
        return this.lowerSigmaBound;
    }

    public BigInteger getModulus() {
        return this.f7421n;
    }
}
