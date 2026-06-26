package org.spongycastle.math.ec;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class NafR2LMultiplier extends AbstractECMultiplier {
    @Override // org.spongycastle.math.ec.AbstractECMultiplier
    public ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        int[] iArrGenerateCompactNaf = WNafUtil.generateCompactNaf(bigInteger);
        ECPoint infinity = eCPoint.getCurve().getInfinity();
        int i2 = 0;
        int i3 = 0;
        while (i2 < iArrGenerateCompactNaf.length) {
            int i4 = iArrGenerateCompactNaf[i2];
            int i5 = i4 >> 16;
            eCPoint = eCPoint.timesPow2(i3 + (i4 & 65535));
            infinity = infinity.add(i5 < 0 ? eCPoint.negate() : eCPoint);
            i2++;
            i3 = 1;
        }
        return infinity;
    }
}
