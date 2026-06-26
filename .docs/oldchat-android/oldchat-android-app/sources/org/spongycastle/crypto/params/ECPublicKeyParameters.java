package org.spongycastle.crypto.params;

import org.spongycastle.math.ec.ECPoint;

/* JADX INFO: loaded from: classes.dex */
public class ECPublicKeyParameters extends ECKeyParameters {

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    private final ECPoint f7405Q;

    public ECPublicKeyParameters(ECPoint eCPoint, ECDomainParameters eCDomainParameters) {
        super(false, eCDomainParameters);
        this.f7405Q = validate(eCPoint);
    }

    private ECPoint validate(ECPoint eCPoint) {
        if (eCPoint == null) {
            throw new IllegalArgumentException("point has null value");
        }
        if (eCPoint.isInfinity()) {
            throw new IllegalArgumentException("point at infinity");
        }
        ECPoint eCPointNormalize = eCPoint.normalize();
        if (eCPointNormalize.isValid()) {
            return eCPointNormalize;
        }
        throw new IllegalArgumentException("point not on curve");
    }

    public ECPoint getQ() {
        return this.f7405Q;
    }
}
