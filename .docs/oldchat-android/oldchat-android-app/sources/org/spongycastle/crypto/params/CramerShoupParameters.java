package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;

/* JADX INFO: loaded from: classes.dex */
public class CramerShoupParameters implements CipherParameters {

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    private Digest f7380H;
    private BigInteger g1;
    private BigInteger g2;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private BigInteger f7381p;

    public CramerShoupParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, Digest digest) {
        this.f7381p = bigInteger;
        this.g1 = bigInteger2;
        this.g2 = bigInteger3;
        this.f7380H = digest;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DSAParameters)) {
            return false;
        }
        CramerShoupParameters cramerShoupParameters = (CramerShoupParameters) obj;
        return cramerShoupParameters.getP().equals(this.f7381p) && cramerShoupParameters.getG1().equals(this.g1) && cramerShoupParameters.getG2().equals(this.g2);
    }

    public BigInteger getG1() {
        return this.g1;
    }

    public BigInteger getG2() {
        return this.g2;
    }

    public Digest getH() {
        this.f7380H.reset();
        return this.f7380H;
    }

    public BigInteger getP() {
        return this.f7381p;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG1().hashCode()) ^ getG2().hashCode();
    }
}
