package org.spongycastle.math.ec;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class FixedPointCombMultiplier extends AbstractECMultiplier {
    public int getWidthForCombSize(int i2) {
        return i2 > 257 ? 6 : 5;
    }

    @Override // org.spongycastle.math.ec.AbstractECMultiplier
    public ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECCurve curve = eCPoint.getCurve();
        int combSize = FixedPointUtil.getCombSize(curve);
        if (bigInteger.bitLength() > combSize) {
            throw new IllegalStateException("fixed-point comb doesn't support scalars larger than the curve order");
        }
        FixedPointPreCompInfo fixedPointPreCompInfoPrecompute = FixedPointUtil.precompute(eCPoint, getWidthForCombSize(combSize));
        ECPoint[] preComp = fixedPointPreCompInfoPrecompute.getPreComp();
        int width = fixedPointPreCompInfoPrecompute.getWidth();
        int i2 = ((combSize + width) - 1) / width;
        ECPoint infinity = curve.getInfinity();
        int i3 = (width * i2) - 1;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = 0;
            for (int i6 = i3 - i4; i6 >= 0; i6 -= i2) {
                i5 <<= 1;
                if (bigInteger.testBit(i6)) {
                    i5 |= 1;
                }
            }
            infinity = infinity.twicePlus(preComp[i5]);
        }
        return infinity.add(fixedPointPreCompInfoPrecompute.getOffset());
    }
}
