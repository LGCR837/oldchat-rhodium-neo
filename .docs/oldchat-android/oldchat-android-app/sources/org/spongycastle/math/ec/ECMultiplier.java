package org.spongycastle.math.ec;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public interface ECMultiplier {
    ECPoint multiply(ECPoint eCPoint, BigInteger bigInteger);
}
