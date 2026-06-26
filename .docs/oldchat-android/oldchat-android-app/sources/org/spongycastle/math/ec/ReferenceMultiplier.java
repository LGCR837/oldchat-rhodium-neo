package org.spongycastle.math.ec;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class ReferenceMultiplier extends AbstractECMultiplier {
    @Override // org.spongycastle.math.ec.AbstractECMultiplier
    public ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        return ECAlgorithms.referenceMultiply(eCPoint, bigInteger);
    }
}
