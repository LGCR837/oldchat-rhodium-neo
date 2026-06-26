package org.spongycastle.pqc.asn1;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

/* JADX INFO: loaded from: classes.dex */
public class McElieceCCA2PublicKey extends ASN1Object {
    private final AlgorithmIdentifier digest;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final GF2Matrix f7590g;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final int f7591n;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private final int f7592t;

    public McElieceCCA2PublicKey(int i2, int i3, GF2Matrix gF2Matrix, AlgorithmIdentifier algorithmIdentifier) {
        this.f7591n = i2;
        this.f7592t = i3;
        this.f7590g = new GF2Matrix(gF2Matrix.getEncoded());
        this.digest = algorithmIdentifier;
    }

    public static McElieceCCA2PublicKey getInstance(Object obj) {
        if (obj instanceof McElieceCCA2PublicKey) {
            return (McElieceCCA2PublicKey) obj;
        }
        if (obj != null) {
            return new McElieceCCA2PublicKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public AlgorithmIdentifier getDigest() {
        return this.digest;
    }

    public GF2Matrix getG() {
        return this.f7590g;
    }

    public int getN() {
        return this.f7591n;
    }

    public int getT() {
        return this.f7592t;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.f7591n));
        aSN1EncodableVector.add(new ASN1Integer(this.f7592t));
        aSN1EncodableVector.add(new DEROctetString(this.f7590g.getEncoded()));
        aSN1EncodableVector.add(this.digest);
        return new DERSequence(aSN1EncodableVector);
    }

    private McElieceCCA2PublicKey(ASN1Sequence aSN1Sequence) {
        this.f7591n = ((ASN1Integer) aSN1Sequence.getObjectAt(0)).getValue().intValue();
        this.f7592t = ((ASN1Integer) aSN1Sequence.getObjectAt(1)).getValue().intValue();
        this.f7590g = new GF2Matrix(((ASN1OctetString) aSN1Sequence.getObjectAt(2)).getOctets());
        this.digest = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(3));
    }
}
