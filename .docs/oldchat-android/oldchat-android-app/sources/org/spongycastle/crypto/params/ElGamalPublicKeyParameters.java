package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class ElGamalPublicKeyParameters extends ElGamalKeyParameters {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private BigInteger f7410y;

    public ElGamalPublicKeyParameters(BigInteger bigInteger, ElGamalParameters elGamalParameters) {
        super(false, elGamalParameters);
        this.f7410y = bigInteger;
    }

    @Override // org.spongycastle.crypto.params.ElGamalKeyParameters
    public boolean equals(Object obj) {
        return (obj instanceof ElGamalPublicKeyParameters) && ((ElGamalPublicKeyParameters) obj).getY().equals(this.f7410y) && super.equals(obj);
    }

    public BigInteger getY() {
        return this.f7410y;
    }

    @Override // org.spongycastle.crypto.params.ElGamalKeyParameters
    public int hashCode() {
        return this.f7410y.hashCode() ^ super.hashCode();
    }
}
