package org.spongycastle.crypto.ec;

import org.spongycastle.math.ec.ECPoint;

/* JADX INFO: loaded from: classes.dex */
public class ECPair {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private final ECPoint f7308x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private final ECPoint f7309y;

    public ECPair(ECPoint eCPoint, ECPoint eCPoint2) {
        this.f7308x = eCPoint;
        this.f7309y = eCPoint2;
    }

    public boolean equals(ECPair eCPair) {
        return eCPair.getX().equals(getX()) && eCPair.getY().equals(getY());
    }

    public ECPoint getX() {
        return this.f7308x;
    }

    public ECPoint getY() {
        return this.f7309y;
    }

    public int hashCode() {
        return this.f7308x.hashCode() + (this.f7309y.hashCode() * 37);
    }

    public boolean equals(Object obj) {
        if (obj instanceof ECPair) {
            return equals((ECPair) obj);
        }
        return false;
    }
}
