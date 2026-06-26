package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

/* JADX INFO: loaded from: classes.dex */
public class ElGamalParameters implements CipherParameters {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private BigInteger f7406g;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f7407l;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private BigInteger f7408p;

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, 0);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ElGamalParameters)) {
            return false;
        }
        ElGamalParameters elGamalParameters = (ElGamalParameters) obj;
        return elGamalParameters.getP().equals(this.f7408p) && elGamalParameters.getG().equals(this.f7406g) && elGamalParameters.getL() == this.f7407l;
    }

    public BigInteger getG() {
        return this.f7406g;
    }

    public int getL() {
        return this.f7407l;
    }

    public BigInteger getP() {
        return this.f7408p;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG().hashCode()) + this.f7407l;
    }

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2, int i2) {
        this.f7406g = bigInteger2;
        this.f7408p = bigInteger;
        this.f7407l = i2;
    }
}
