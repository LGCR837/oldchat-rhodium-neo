package org.spongycastle.jce.spec;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class GOST3410PublicKeyParameterSetSpec {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BigInteger f7489a;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private BigInteger f7490p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private BigInteger f7491q;

    public GOST3410PublicKeyParameterSetSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f7490p = bigInteger;
        this.f7491q = bigInteger2;
        this.f7489a = bigInteger3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410PublicKeyParameterSetSpec)) {
            return false;
        }
        GOST3410PublicKeyParameterSetSpec gOST3410PublicKeyParameterSetSpec = (GOST3410PublicKeyParameterSetSpec) obj;
        return this.f7489a.equals(gOST3410PublicKeyParameterSetSpec.f7489a) && this.f7490p.equals(gOST3410PublicKeyParameterSetSpec.f7490p) && this.f7491q.equals(gOST3410PublicKeyParameterSetSpec.f7491q);
    }

    public BigInteger getA() {
        return this.f7489a;
    }

    public BigInteger getP() {
        return this.f7490p;
    }

    public BigInteger getQ() {
        return this.f7491q;
    }

    public int hashCode() {
        return (this.f7489a.hashCode() ^ this.f7490p.hashCode()) ^ this.f7491q.hashCode();
    }
}
