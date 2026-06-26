package org.spongycastle.jce.interfaces;

import java.security.PrivateKey;
import java.security.PublicKey;

/* JADX INFO: loaded from: classes.dex */
public interface MQVPrivateKey extends PrivateKey {
    PrivateKey getEphemeralPrivateKey();

    PublicKey getEphemeralPublicKey();

    PrivateKey getStaticPrivateKey();
}
