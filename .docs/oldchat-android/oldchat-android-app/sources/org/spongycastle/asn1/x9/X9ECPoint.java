package org.spongycastle.asn1.x9;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class X9ECPoint extends ASN1Object {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ECCurve f7234c;
    private final ASN1OctetString encoding;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private ECPoint f7235p;

    public X9ECPoint(ECPoint eCPoint) {
        this(eCPoint, false);
    }

    public synchronized ECPoint getPoint() {
        try {
            if (this.f7235p == null) {
                this.f7235p = this.f7234c.decodePoint(this.encoding.getOctets()).normalize();
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.f7235p;
    }

    public byte[] getPointEncoding() {
        return Arrays.clone(this.encoding.getOctets());
    }

    public boolean isPointCompressed() {
        byte[] octets = this.encoding.getOctets();
        if (octets == null || octets.length <= 0) {
            return false;
        }
        byte b2 = octets[0];
        return b2 == 2 || b2 == 3;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.encoding;
    }

    public X9ECPoint(ECPoint eCPoint, boolean z2) {
        this.f7235p = eCPoint.normalize();
        this.encoding = new DEROctetString(eCPoint.getEncoded(z2));
    }

    public X9ECPoint(ECCurve eCCurve, byte[] bArr) {
        this.f7234c = eCCurve;
        this.encoding = new DEROctetString(Arrays.clone(bArr));
    }

    public X9ECPoint(ECCurve eCCurve, ASN1OctetString aSN1OctetString) {
        this(eCCurve, aSN1OctetString.getOctets());
    }
}
