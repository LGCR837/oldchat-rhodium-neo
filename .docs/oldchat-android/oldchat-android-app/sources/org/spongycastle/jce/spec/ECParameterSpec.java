package org.spongycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;

/* JADX INFO: loaded from: classes.dex */
public class ECParameterSpec implements AlgorithmParameterSpec {

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    private ECPoint f7476G;
    private ECCurve curve;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private BigInteger f7477h;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private BigInteger f7478n;
    private byte[] seed;

    public ECParameterSpec(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger) {
        this.curve = eCCurve;
        this.f7476G = eCPoint.normalize();
        this.f7478n = bigInteger;
        this.f7477h = BigInteger.valueOf(1L);
        this.seed = null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ECParameterSpec)) {
            return false;
        }
        ECParameterSpec eCParameterSpec = (ECParameterSpec) obj;
        return getCurve().equals(eCParameterSpec.getCurve()) && getG().equals(eCParameterSpec.getG());
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public ECPoint getG() {
        return this.f7476G;
    }

    public BigInteger getH() {
        return this.f7477h;
    }

    public BigInteger getN() {
        return this.f7478n;
    }

    public byte[] getSeed() {
        return this.seed;
    }

    public int hashCode() {
        return getCurve().hashCode() ^ getG().hashCode();
    }

    public ECParameterSpec(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        this.curve = eCCurve;
        this.f7476G = eCPoint.normalize();
        this.f7478n = bigInteger;
        this.f7477h = bigInteger2;
        this.seed = null;
    }

    public ECParameterSpec(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.curve = eCCurve;
        this.f7476G = eCPoint.normalize();
        this.f7478n = bigInteger;
        this.f7477h = bigInteger2;
        this.seed = bArr;
    }
}
