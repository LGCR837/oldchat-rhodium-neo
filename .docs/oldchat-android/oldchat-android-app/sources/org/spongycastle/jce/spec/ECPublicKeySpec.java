package org.spongycastle.jce.spec;

import org.spongycastle.math.ec.ECPoint;

/* JADX INFO: loaded from: classes.dex */
public class ECPublicKeySpec extends ECKeySpec {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private ECPoint f7480q;

    public ECPublicKeySpec(ECPoint eCPoint, ECParameterSpec eCParameterSpec) {
        super(eCParameterSpec);
        if (eCPoint.getCurve() != null) {
            this.f7480q = eCPoint.normalize();
        } else {
            this.f7480q = eCPoint;
        }
    }

    public ECPoint getQ() {
        return this.f7480q;
    }
}
