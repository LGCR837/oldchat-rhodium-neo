package org.spongycastle.crypto.params;

/* JADX INFO: loaded from: classes.dex */
public class GOST3410KeyParameters extends AsymmetricKeyParameter {
    private GOST3410Parameters params;

    public GOST3410KeyParameters(boolean z2, GOST3410Parameters gOST3410Parameters) {
        super(z2);
        this.params = gOST3410Parameters;
    }

    public GOST3410Parameters getParameters() {
        return this.params;
    }
}
