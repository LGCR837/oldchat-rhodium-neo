package org.spongycastle.jce.spec;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class ElGamalPrivateKeySpec extends ElGamalKeySpec {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private BigInteger f7483x;

    public ElGamalPrivateKeySpec(BigInteger bigInteger, ElGamalParameterSpec elGamalParameterSpec) {
        super(elGamalParameterSpec);
        this.f7483x = bigInteger;
    }

    public BigInteger getX() {
        return this.f7483x;
    }
}
