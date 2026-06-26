package org.spongycastle.crypto.params;

/* JADX INFO: loaded from: classes.dex */
public class CramerShoupKeyParameters extends AsymmetricKeyParameter {
    private CramerShoupParameters params;

    public CramerShoupKeyParameters(boolean z2, CramerShoupParameters cramerShoupParameters) {
        super(z2);
        this.params = cramerShoupParameters;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CramerShoupKeyParameters)) {
            return false;
        }
        CramerShoupKeyParameters cramerShoupKeyParameters = (CramerShoupKeyParameters) obj;
        CramerShoupParameters cramerShoupParameters = this.params;
        return cramerShoupParameters == null ? cramerShoupKeyParameters.getParameters() == null : cramerShoupParameters.equals(cramerShoupKeyParameters.getParameters());
    }

    public CramerShoupParameters getParameters() {
        return this.params;
    }

    public int hashCode() {
        int i2 = !isPrivate() ? 1 : 0;
        CramerShoupParameters cramerShoupParameters = this.params;
        return cramerShoupParameters != null ? i2 ^ cramerShoupParameters.hashCode() : i2;
    }
}
