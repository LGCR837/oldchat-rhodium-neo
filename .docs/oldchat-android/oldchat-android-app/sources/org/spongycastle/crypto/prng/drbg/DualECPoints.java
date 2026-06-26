package org.spongycastle.crypto.prng.drbg;

import org.spongycastle.math.ec.ECPoint;

/* JADX INFO: loaded from: classes.dex */
public class DualECPoints {
    private final int cofactor;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final ECPoint f7433p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private final ECPoint f7434q;
    private final int securityStrength;

    public DualECPoints(int i2, ECPoint eCPoint, ECPoint eCPoint2, int i3) {
        if (!eCPoint.getCurve().equals(eCPoint2.getCurve())) {
            throw new IllegalArgumentException("points need to be on the same curve");
        }
        this.securityStrength = i2;
        this.f7433p = eCPoint;
        this.f7434q = eCPoint2;
        this.cofactor = i3;
    }

    private static int log2(int i2) {
        int i3 = 0;
        while (true) {
            i2 >>= 1;
            if (i2 == 0) {
                return i3;
            }
            i3++;
        }
    }

    public int getCofactor() {
        return this.cofactor;
    }

    public int getMaxOutlen() {
        return ((this.f7433p.getCurve().getFieldSize() - (log2(this.cofactor) + 13)) / 8) * 8;
    }

    public ECPoint getP() {
        return this.f7433p;
    }

    public ECPoint getQ() {
        return this.f7434q;
    }

    public int getSecurityStrength() {
        return this.securityStrength;
    }

    public int getSeedLen() {
        return this.f7433p.getCurve().getFieldSize();
    }
}
