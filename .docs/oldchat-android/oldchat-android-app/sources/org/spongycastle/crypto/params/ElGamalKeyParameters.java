package org.spongycastle.crypto.params;

/* JADX INFO: loaded from: classes.dex */
public class ElGamalKeyParameters extends AsymmetricKeyParameter {
    private ElGamalParameters params;

    public ElGamalKeyParameters(boolean z2, ElGamalParameters elGamalParameters) {
        super(z2);
        this.params = elGamalParameters;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ElGamalKeyParameters)) {
            return false;
        }
        ElGamalKeyParameters elGamalKeyParameters = (ElGamalKeyParameters) obj;
        ElGamalParameters elGamalParameters = this.params;
        return elGamalParameters == null ? elGamalKeyParameters.getParameters() == null : elGamalParameters.equals(elGamalKeyParameters.getParameters());
    }

    public ElGamalParameters getParameters() {
        return this.params;
    }

    public int hashCode() {
        ElGamalParameters elGamalParameters = this.params;
        if (elGamalParameters != null) {
            return elGamalParameters.hashCode();
        }
        return 0;
    }
}
