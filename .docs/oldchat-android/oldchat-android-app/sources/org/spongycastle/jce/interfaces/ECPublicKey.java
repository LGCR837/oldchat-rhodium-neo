package org.spongycastle.jce.interfaces;

import java.security.PublicKey;
import org.spongycastle.math.ec.ECPoint;

/* JADX INFO: loaded from: classes.dex */
public interface ECPublicKey extends ECKey, PublicKey {
    ECPoint getQ();
}
