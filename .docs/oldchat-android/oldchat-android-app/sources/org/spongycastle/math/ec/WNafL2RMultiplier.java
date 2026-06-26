package org.spongycastle.math.ec;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class WNafL2RMultiplier extends AbstractECMultiplier {
    public int getWindowSize(int i2) {
        return WNafUtil.getWindowSize(i2);
    }

    @Override // org.spongycastle.math.ec.AbstractECMultiplier
    public ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECPoint eCPointAdd;
        int iMax = Math.max(2, Math.min(16, getWindowSize(bigInteger.bitLength())));
        WNafPreCompInfo wNafPreCompInfoPrecompute = WNafUtil.precompute(eCPoint, iMax, true);
        ECPoint[] preComp = wNafPreCompInfoPrecompute.getPreComp();
        ECPoint[] preCompNeg = wNafPreCompInfoPrecompute.getPreCompNeg();
        int[] iArrGenerateCompactWindowNaf = WNafUtil.generateCompactWindowNaf(iMax, bigInteger);
        ECPoint infinity = eCPoint.getCurve().getInfinity();
        int length = iArrGenerateCompactWindowNaf.length;
        if (length > 1) {
            length--;
            int i2 = iArrGenerateCompactWindowNaf[length];
            int i3 = i2 >> 16;
            int i4 = i2 & 65535;
            int iAbs = Math.abs(i3);
            ECPoint[] eCPointArr = i3 < 0 ? preCompNeg : preComp;
            if ((iAbs << 2) < (1 << iMax)) {
                byte b2 = LongArray.bitLengths[iAbs];
                int i5 = iMax - b2;
                eCPointAdd = eCPointArr[((1 << (iMax - 1)) - 1) >>> 1].add(eCPointArr[(((iAbs ^ (1 << (b2 - 1))) << i5) + 1) >>> 1]);
                i4 -= i5;
            } else {
                eCPointAdd = eCPointArr[iAbs >>> 1];
            }
            infinity = eCPointAdd.timesPow2(i4);
        }
        while (length > 0) {
            length--;
            int i6 = iArrGenerateCompactWindowNaf[length];
            int i7 = i6 >> 16;
            infinity = infinity.twicePlus((i7 < 0 ? preCompNeg : preComp)[Math.abs(i7) >>> 1]).timesPow2(i6 & 65535);
        }
        return infinity;
    }
}
