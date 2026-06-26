package org.spongycastle.math.ec;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class DoubleAddMultiplier extends AbstractECMultiplier {
    @Override // org.spongycastle.math.ec.AbstractECMultiplier
    public ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECPoint[] eCPointArr = new ECPoint[2];
        eCPointArr[0] = eCPoint.getCurve().getInfinity();
        eCPointArr[1] = eCPoint;
        int iBitLength = bigInteger.bitLength();
        for (int i2 = 0; i2 < iBitLength; i2++) {
            boolean zTestBit = bigInteger.testBit(i2);
            int i3 = 1 - (zTestBit ? 1 : 0);
            eCPointArr[i3] = eCPointArr[i3].twicePlus(eCPointArr[zTestBit ? 1 : 0]);
        }
        return eCPointArr[0];
    }
}
