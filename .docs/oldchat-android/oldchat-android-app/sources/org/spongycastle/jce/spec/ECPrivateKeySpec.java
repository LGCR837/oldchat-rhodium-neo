package org.spongycastle.jce.spec;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class ECPrivateKeySpec extends ECKeySpec {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private BigInteger f7479d;

    public ECPrivateKeySpec(BigInteger bigInteger, ECParameterSpec eCParameterSpec) {
        super(eCParameterSpec);
        this.f7479d = bigInteger;
    }

    public BigInteger getD() {
        return this.f7479d;
    }
}
