package org.spongycastle.math.ec;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class MixedNafR2LMultiplier extends AbstractECMultiplier {
    protected int additionCoord;
    protected int doublingCoord;

    public MixedNafR2LMultiplier() {
        this(2, 4);
    }

    public ECCurve configureCurve(ECCurve eCCurve, int i2) {
        if (eCCurve.getCoordinateSystem() == i2) {
            return eCCurve;
        }
        if (eCCurve.supportsCoordinateSystem(i2)) {
            return eCCurve.configure().setCoordinateSystem(i2).create();
        }
        throw new IllegalArgumentException("Coordinate system " + i2 + " not supported by this curve");
    }

    @Override // org.spongycastle.math.ec.AbstractECMultiplier
    public ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECCurve curve = eCPoint.getCurve();
        ECCurve eCCurveConfigureCurve = configureCurve(curve, this.additionCoord);
        ECCurve eCCurveConfigureCurve2 = configureCurve(curve, this.doublingCoord);
        int[] iArrGenerateCompactNaf = WNafUtil.generateCompactNaf(bigInteger);
        ECPoint infinity = eCCurveConfigureCurve.getInfinity();
        ECPoint eCPointImportPoint = eCCurveConfigureCurve2.importPoint(eCPoint);
        int i2 = 0;
        ECPoint eCPointAdd = infinity;
        int i3 = 0;
        while (i2 < iArrGenerateCompactNaf.length) {
            int i4 = iArrGenerateCompactNaf[i2];
            int i5 = i4 >> 16;
            eCPointImportPoint = eCPointImportPoint.timesPow2(i3 + (i4 & 65535));
            ECPoint eCPointImportPoint2 = eCCurveConfigureCurve.importPoint(eCPointImportPoint);
            if (i5 < 0) {
                eCPointImportPoint2 = eCPointImportPoint2.negate();
            }
            eCPointAdd = eCPointAdd.add(eCPointImportPoint2);
            i2++;
            i3 = 1;
        }
        return curve.importPoint(eCPointAdd);
    }

    public MixedNafR2LMultiplier(int i2, int i3) {
        this.additionCoord = i2;
        this.doublingCoord = i3;
    }
}
