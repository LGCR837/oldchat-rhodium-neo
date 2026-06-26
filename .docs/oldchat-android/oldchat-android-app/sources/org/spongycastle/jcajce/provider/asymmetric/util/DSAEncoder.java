package org.spongycastle.jcajce.provider.asymmetric.util;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public interface DSAEncoder {
    BigInteger[] decode(byte[] bArr);

    byte[] encode(BigInteger bigInteger, BigInteger bigInteger2);
}
