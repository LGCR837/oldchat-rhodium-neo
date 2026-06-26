package org.spongycastle.crypto.engines;

/* JADX INFO: loaded from: classes.dex */
public class CamelliaWrapEngine extends RFC3394WrapEngine {
    public CamelliaWrapEngine() {
        super(new CamelliaEngine());
    }
}
