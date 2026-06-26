package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class DSAPrivateKeyParameters extends DSAKeyParameters {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private BigInteger f7399x;

    public DSAPrivateKeyParameters(BigInteger bigInteger, DSAParameters dSAParameters) {
        super(true, dSAParameters);
        this.f7399x = bigInteger;
    }

    public BigInteger getX() {
        return this.f7399x;
    }
}
