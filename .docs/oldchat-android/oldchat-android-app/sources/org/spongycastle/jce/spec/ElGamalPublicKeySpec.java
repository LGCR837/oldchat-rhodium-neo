package org.spongycastle.jce.spec;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class ElGamalPublicKeySpec extends ElGamalKeySpec {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private BigInteger f7484y;

    public ElGamalPublicKeySpec(BigInteger bigInteger, ElGamalParameterSpec elGamalParameterSpec) {
        super(elGamalParameterSpec);
        this.f7484y = bigInteger;
    }

    public BigInteger getY() {
        return this.f7484y;
    }
}
