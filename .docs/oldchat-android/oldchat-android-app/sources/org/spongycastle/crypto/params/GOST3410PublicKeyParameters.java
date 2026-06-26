package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class GOST3410PublicKeyParameters extends GOST3410KeyParameters {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private BigInteger f7415y;

    public GOST3410PublicKeyParameters(BigInteger bigInteger, GOST3410Parameters gOST3410Parameters) {
        super(false, gOST3410Parameters);
        this.f7415y = bigInteger;
    }

    public BigInteger getY() {
        return this.f7415y;
    }
}
