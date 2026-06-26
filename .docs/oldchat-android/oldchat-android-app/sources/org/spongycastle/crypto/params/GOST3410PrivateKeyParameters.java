package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class GOST3410PrivateKeyParameters extends GOST3410KeyParameters {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private BigInteger f7414x;

    public GOST3410PrivateKeyParameters(BigInteger bigInteger, GOST3410Parameters gOST3410Parameters) {
        super(true, gOST3410Parameters);
        this.f7414x = bigInteger;
    }

    public BigInteger getX() {
        return this.f7414x;
    }
}
