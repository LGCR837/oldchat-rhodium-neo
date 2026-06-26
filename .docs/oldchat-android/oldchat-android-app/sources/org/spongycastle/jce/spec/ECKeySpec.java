package org.spongycastle.jce.spec;

import java.security.spec.KeySpec;

/* JADX INFO: loaded from: classes.dex */
public class ECKeySpec implements KeySpec {
    private ECParameterSpec spec;

    public ECKeySpec(ECParameterSpec eCParameterSpec) {
        this.spec = eCParameterSpec;
    }

    public ECParameterSpec getParams() {
        return this.spec;
    }
}
