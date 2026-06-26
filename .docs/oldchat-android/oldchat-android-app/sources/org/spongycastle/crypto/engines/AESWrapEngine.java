package org.spongycastle.crypto.engines;

/* JADX INFO: loaded from: classes.dex */
public class AESWrapEngine extends RFC3394WrapEngine {
    public AESWrapEngine() {
        super(new AESEngine());
    }

    public AESWrapEngine(boolean z2) {
        super(new AESEngine(), z2);
    }
}
