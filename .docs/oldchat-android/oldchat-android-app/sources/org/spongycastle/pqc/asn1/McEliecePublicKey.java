package org.spongycastle.pqc.asn1;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

/* JADX INFO: loaded from: classes.dex */
public class McEliecePublicKey extends ASN1Object {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final GF2Matrix f7595g;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final int f7596n;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private final int f7597t;

    public McEliecePublicKey(int i2, int i3, GF2Matrix gF2Matrix) {
        this.f7596n = i2;
        this.f7597t = i3;
        this.f7595g = new GF2Matrix(gF2Matrix);
    }

    public static McEliecePublicKey getInstance(Object obj) {
        if (obj instanceof McEliecePublicKey) {
            return (McEliecePublicKey) obj;
        }
        if (obj != null) {
            return new McEliecePublicKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public GF2Matrix getG() {
        return new GF2Matrix(this.f7595g);
    }

    public int getN() {
        return this.f7596n;
    }

    public int getT() {
        return this.f7597t;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.f7596n));
        aSN1EncodableVector.add(new ASN1Integer(this.f7597t));
        aSN1EncodableVector.add(new DEROctetString(this.f7595g.getEncoded()));
        return new DERSequence(aSN1EncodableVector);
    }

    private McEliecePublicKey(ASN1Sequence aSN1Sequence) {
        this.f7596n = ((ASN1Integer) aSN1Sequence.getObjectAt(0)).getValue().intValue();
        this.f7597t = ((ASN1Integer) aSN1Sequence.getObjectAt(1)).getValue().intValue();
        this.f7595g = new GF2Matrix(((ASN1OctetString) aSN1Sequence.getObjectAt(2)).getOctets());
    }
}
