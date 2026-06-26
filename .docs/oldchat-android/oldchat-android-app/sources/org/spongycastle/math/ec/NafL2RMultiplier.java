package org.spongycastle.math.ec;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class NafL2RMultiplier extends AbstractECMultiplier {
    @Override // org.spongycastle.math.ec.AbstractECMultiplier
    public ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        int[] iArrGenerateCompactNaf = WNafUtil.generateCompactNaf(bigInteger);
        ECPoint eCPointNormalize = eCPoint.normalize();
        ECPoint eCPointNegate = eCPointNormalize.negate();
        ECPoint infinity = eCPoint.getCurve().getInfinity();
        int length = iArrGenerateCompactNaf.length;
        while (true) {
            length--;
            if (length < 0) {
                return infinity;
            }
            int i2 = iArrGenerateCompactNaf[length];
            infinity = infinity.twicePlus((i2 >> 16) < 0 ? eCPointNegate : eCPointNormalize).timesPow2(i2 & 65535);
        }
    }
}
