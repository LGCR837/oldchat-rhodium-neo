package org.spongycastle.asn1.x509;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;

/* JADX INFO: loaded from: classes.dex */
public class DSAParameter extends ASN1Object {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    ASN1Integer f7214g;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    ASN1Integer f7215p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    ASN1Integer f7216q;

    public DSAParameter(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f7215p = new ASN1Integer(bigInteger);
        this.f7216q = new ASN1Integer(bigInteger2);
        this.f7214g = new ASN1Integer(bigInteger3);
    }

    public static DSAParameter getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z2) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z2));
    }

    public BigInteger getG() {
        return this.f7214g.getPositiveValue();
    }

    public BigInteger getP() {
        return this.f7215p.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f7216q.getPositiveValue();
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.f7215p);
        aSN1EncodableVector.add(this.f7216q);
        aSN1EncodableVector.add(this.f7214g);
        return new DERSequence(aSN1EncodableVector);
    }

    public static DSAParameter getInstance(Object obj) {
        if (obj instanceof DSAParameter) {
            return (DSAParameter) obj;
        }
        if (obj != null) {
            return new DSAParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private DSAParameter(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 3) {
            Enumeration objects = aSN1Sequence.getObjects();
            this.f7215p = ASN1Integer.getInstance(objects.nextElement());
            this.f7216q = ASN1Integer.getInstance(objects.nextElement());
            this.f7214g = ASN1Integer.getInstance(objects.nextElement());
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
    }
}
