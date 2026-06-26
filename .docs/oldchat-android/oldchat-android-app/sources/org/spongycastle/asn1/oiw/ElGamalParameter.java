package org.spongycastle.asn1.oiw;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

/* JADX INFO: loaded from: classes.dex */
public class ElGamalParameter extends ASN1Object {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    ASN1Integer f7193g;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    ASN1Integer f7194p;

    public ElGamalParameter(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f7194p = new ASN1Integer(bigInteger);
        this.f7193g = new ASN1Integer(bigInteger2);
    }

    public static ElGamalParameter getInstance(Object obj) {
        if (obj instanceof ElGamalParameter) {
            return (ElGamalParameter) obj;
        }
        if (obj != null) {
            return new ElGamalParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getG() {
        return this.f7193g.getPositiveValue();
    }

    public BigInteger getP() {
        return this.f7194p.getPositiveValue();
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.f7194p);
        aSN1EncodableVector.add(this.f7193g);
        return new DERSequence(aSN1EncodableVector);
    }

    private ElGamalParameter(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.f7194p = (ASN1Integer) objects.nextElement();
        this.f7193g = (ASN1Integer) objects.nextElement();
    }
}
