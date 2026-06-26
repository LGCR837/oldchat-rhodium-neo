package org.spongycastle.crypto.tls;

import java.security.SecureRandom;
import org.spongycastle.crypto.prng.RandomGenerator;

/* JADX INFO: loaded from: classes.dex */
public interface TlsContext {
    byte[] exportKeyingMaterial(String str, byte[] bArr, int i2);

    ProtocolVersion getClientVersion();

    RandomGenerator getNonceRandomGenerator();

    TlsSession getResumableSession();

    SecureRandom getSecureRandom();

    SecurityParameters getSecurityParameters();

    ProtocolVersion getServerVersion();

    Object getUserObject();

    boolean isServer();

    void setUserObject(Object obj);
}
