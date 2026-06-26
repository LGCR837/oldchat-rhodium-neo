package org.spongycastle.crypto.engines;

/* JADX INFO: loaded from: classes.dex */
public class ARIAWrapEngine extends RFC3394WrapEngine {
    public ARIAWrapEngine() {
        super(new ARIAEngine());
    }

    public ARIAWrapEngine(boolean z2) {
        super(new ARIAEngine(), z2);
    }
}
