package org.spongycastle.crypto.params;

/* JADX INFO: loaded from: classes.dex */
public class DHKeyParameters extends AsymmetricKeyParameter {
    private DHParameters params;

    public DHKeyParameters(boolean z2, DHParameters dHParameters) {
        super(z2);
        this.params = dHParameters;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHKeyParameters)) {
            return false;
        }
        DHKeyParameters dHKeyParameters = (DHKeyParameters) obj;
        DHParameters dHParameters = this.params;
        return dHParameters == null ? dHKeyParameters.getParameters() == null : dHParameters.equals(dHKeyParameters.getParameters());
    }

    public DHParameters getParameters() {
        return this.params;
    }

    public int hashCode() {
        int i2 = !isPrivate() ? 1 : 0;
        DHParameters dHParameters = this.params;
        return dHParameters != null ? i2 ^ dHParameters.hashCode() : i2;
    }
}
