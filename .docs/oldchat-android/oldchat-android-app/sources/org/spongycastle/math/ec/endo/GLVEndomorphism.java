package org.spongycastle.math.ec.endo;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public interface GLVEndomorphism extends ECEndomorphism {
    BigInteger[] decomposeScalar(BigInteger bigInteger);
}
