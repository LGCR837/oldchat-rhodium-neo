package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class CramerShoupPublicKeyParameters extends CramerShoupKeyParameters {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BigInteger f7383c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private BigInteger f7384d;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private BigInteger f7385h;

    public CramerShoupPublicKeyParameters(CramerShoupParameters cramerShoupParameters, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        super(false, cramerShoupParameters);
        this.f7383c = bigInteger;
        this.f7384d = bigInteger2;
        this.f7385h = bigInteger3;
    }

    @Override // org.spongycastle.crypto.params.CramerShoupKeyParameters
    public boolean equals(Object obj) {
        if (!(obj instanceof CramerShoupPublicKeyParameters)) {
            return false;
        }
        CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters = (CramerShoupPublicKeyParameters) obj;
        return cramerShoupPublicKeyParameters.getC().equals(this.f7383c) && cramerShoupPublicKeyParameters.getD().equals(this.f7384d) && cramerShoupPublicKeyParameters.getH().equals(this.f7385h) && super.equals(obj);
    }

    public BigInteger getC() {
        return this.f7383c;
    }

    public BigInteger getD() {
        return this.f7384d;
    }

    public BigInteger getH() {
        return this.f7385h;
    }

    @Override // org.spongycastle.crypto.params.CramerShoupKeyParameters
    public int hashCode() {
        return ((this.f7383c.hashCode() ^ this.f7384d.hashCode()) ^ this.f7385h.hashCode()) ^ super.hashCode();
    }
}
