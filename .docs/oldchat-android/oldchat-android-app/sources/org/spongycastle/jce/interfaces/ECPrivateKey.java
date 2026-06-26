package org.spongycastle.jce.interfaces;

import java.math.BigInteger;
import java.security.PrivateKey;

/* JADX INFO: loaded from: classes.dex */
public interface ECPrivateKey extends ECKey, PrivateKey {
    BigInteger getD();
}
