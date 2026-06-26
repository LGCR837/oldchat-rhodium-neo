package org.spongycastle.crypto.params;

/* JADX INFO: loaded from: classes.dex */
public class DSAKeyParameters extends AsymmetricKeyParameter {
    private DSAParameters params;

    public DSAKeyParameters(boolean z2, DSAParameters dSAParameters) {
        super(z2);
        this.params = dSAParameters;
    }

    public DSAParameters getParameters() {
        return this.params;
    }
}
