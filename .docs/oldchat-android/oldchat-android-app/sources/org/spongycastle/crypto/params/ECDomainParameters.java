package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECConstants;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ECDomainParameters implements ECConstants {

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    private ECPoint f7401G;
    private ECCurve curve;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private BigInteger f7402h;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private BigInteger f7403n;
    private byte[] seed;

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger) {
        this(eCCurve, eCPoint, bigInteger, ECConstants.ONE, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ECDomainParameters)) {
            return false;
        }
        ECDomainParameters eCDomainParameters = (ECDomainParameters) obj;
        return this.curve.equals(eCDomainParameters.curve) && this.f7401G.equals(eCDomainParameters.f7401G) && this.f7403n.equals(eCDomainParameters.f7403n) && this.f7402h.equals(eCDomainParameters.f7402h);
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public ECPoint getG() {
        return this.f7401G;
    }

    public BigInteger getH() {
        return this.f7402h;
    }

    public BigInteger getN() {
        return this.f7403n;
    }

    public byte[] getSeed() {
        return Arrays.clone(this.seed);
    }

    public int hashCode() {
        return (((((this.curve.hashCode() * 37) ^ this.f7401G.hashCode()) * 37) ^ this.f7403n.hashCode()) * 37) ^ this.f7402h.hashCode();
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        this(eCCurve, eCPoint, bigInteger, bigInteger2, null);
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.curve = eCCurve;
        this.f7401G = eCPoint.normalize();
        this.f7403n = bigInteger;
        this.f7402h = bigInteger2;
        this.seed = bArr;
    }
}
