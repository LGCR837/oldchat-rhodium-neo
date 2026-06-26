package org.spongycastle.crypto.engines;

/* JADX INFO: loaded from: classes.dex */
public class ARIAWrapPadEngine extends RFC5649WrapEngine {
    public ARIAWrapPadEngine() {
        super(new ARIAEngine());
    }
}
