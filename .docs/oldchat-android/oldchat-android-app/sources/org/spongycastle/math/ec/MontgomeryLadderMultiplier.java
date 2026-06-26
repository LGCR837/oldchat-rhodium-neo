package org.spongycastle.math.ec;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class MontgomeryLadderMultiplier extends AbstractECMultiplier {
    @Override // org.spongycastle.math.ec.AbstractECMultiplier
    public ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECPoint[] eCPointArr = new ECPoint[2];
        eCPointArr[0] = eCPoint.getCurve().getInfinity();
        eCPointArr[1] = eCPoint;
        int iBitLength = bigInteger.bitLength();
        while (true) {
            iBitLength--;
            if (iBitLength < 0) {
                return eCPointArr[0];
            }
            boolean zTestBit = bigInteger.testBit(iBitLength);
            int i2 = 1 - (zTestBit ? 1 : 0);
            eCPointArr[i2] = eCPointArr[i2].add(eCPointArr[zTestBit ? 1 : 0]);
            eCPointArr[zTestBit ? 1 : 0] = eCPointArr[zTestBit ? 1 : 0].twice();
        }
    }
}
