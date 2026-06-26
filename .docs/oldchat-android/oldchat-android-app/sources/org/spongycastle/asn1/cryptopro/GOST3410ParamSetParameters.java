package org.spongycastle.asn1.cryptopro;

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
public class GOST3410ParamSetParameters extends ASN1Object {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ASN1Integer f7182a;
    int keySize;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    ASN1Integer f7183p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    ASN1Integer f7184q;

    public GOST3410ParamSetParameters(int i2, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.keySize = i2;
        this.f7183p = new ASN1Integer(bigInteger);
        this.f7184q = new ASN1Integer(bigInteger2);
        this.f7182a = new ASN1Integer(bigInteger3);
    }

    public static GOST3410ParamSetParameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z2) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z2));
    }

    public BigInteger getA() {
        return this.f7182a.getPositiveValue();
    }

    public int getKeySize() {
        return this.keySize;
    }

    public int getLKeySize() {
        return this.keySize;
    }

    public BigInteger getP() {
        return this.f7183p.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f7184q.getPositiveValue();
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.keySize));
        aSN1EncodableVector.add(this.f7183p);
        aSN1EncodableVector.add(this.f7184q);
        aSN1EncodableVector.add(this.f7182a);
        return new DERSequence(aSN1EncodableVector);
    }

    public static GOST3410ParamSetParameters getInstance(Object obj) {
        if (obj == null || (obj instanceof GOST3410ParamSetParameters)) {
            return (GOST3410ParamSetParameters) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new GOST3410ParamSetParameters((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("Invalid GOST3410Parameter: " + obj.getClass().getName());
    }

    public GOST3410ParamSetParameters(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.keySize = ((ASN1Integer) objects.nextElement()).getValue().intValue();
        this.f7183p = (ASN1Integer) objects.nextElement();
        this.f7184q = (ASN1Integer) objects.nextElement();
        this.f7182a = (ASN1Integer) objects.nextElement();
    }
}
