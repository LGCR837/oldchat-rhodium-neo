package org.spongycastle.pqc.crypto.xmss;

import java.security.SecureRandom;
import org.spongycastle.crypto.KeyGenerationParameters;

/* JADX INFO: loaded from: classes.dex */
public final class XMSSMTKeyGenerationParameters extends KeyGenerationParameters {
    private final XMSSMTParameters xmssmtParameters;

    public XMSSMTKeyGenerationParameters(XMSSMTParameters xMSSMTParameters, SecureRandom secureRandom) {
        super(secureRandom, -1);
        this.xmssmtParameters = xMSSMTParameters;
    }

    public XMSSMTParameters getParameters() {
        return this.xmssmtParameters;
    }
}
