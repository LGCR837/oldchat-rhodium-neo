package org.spongycastle.asn1.x9;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;

/* JADX INFO: loaded from: classes.dex */
public class DHDomainParameters extends ASN1Object {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ASN1Integer f7222g;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private ASN1Integer f7223j;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private ASN1Integer f7224p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private ASN1Integer f7225q;
    private DHValidationParms validationParms;

    public DHDomainParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, DHValidationParms dHValidationParms) {
        if (bigInteger == null) {
            throw new IllegalArgumentException("'p' cannot be null");
        }
        if (bigInteger2 == null) {
            throw new IllegalArgumentException("'g' cannot be null");
        }
        if (bigInteger3 == null) {
            throw new IllegalArgumentException("'q' cannot be null");
        }
        this.f7224p = new ASN1Integer(bigInteger);
        this.f7222g = new ASN1Integer(bigInteger2);
        this.f7225q = new ASN1Integer(bigInteger3);
        this.f7223j = new ASN1Integer(bigInteger4);
        this.validationParms = dHValidationParms;
    }

    public static DHDomainParameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z2) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z2));
    }

    private static ASN1Encodable getNext(Enumeration enumeration) {
        if (enumeration.hasMoreElements()) {
            return (ASN1Encodable) enumeration.nextElement();
        }
        return null;
    }

    public ASN1Integer getG() {
        return this.f7222g;
    }

    public ASN1Integer getJ() {
        return this.f7223j;
    }

    public ASN1Integer getP() {
        return this.f7224p;
    }

    public ASN1Integer getQ() {
        return this.f7225q;
    }

    public DHValidationParms getValidationParms() {
        return this.validationParms;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.f7224p);
        aSN1EncodableVector.add(this.f7222g);
        aSN1EncodableVector.add(this.f7225q);
        ASN1Integer aSN1Integer = this.f7223j;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        }
        DHValidationParms dHValidationParms = this.validationParms;
        if (dHValidationParms != null) {
            aSN1EncodableVector.add(dHValidationParms);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public static DHDomainParameters getInstance(Object obj) {
        if (obj == null || (obj instanceof DHDomainParameters)) {
            return (DHDomainParameters) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new DHDomainParameters((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("Invalid DHDomainParameters: " + obj.getClass().getName());
    }

    public DHDomainParameters(ASN1Integer aSN1Integer, ASN1Integer aSN1Integer2, ASN1Integer aSN1Integer3, ASN1Integer aSN1Integer4, DHValidationParms dHValidationParms) {
        if (aSN1Integer == null) {
            throw new IllegalArgumentException("'p' cannot be null");
        }
        if (aSN1Integer2 == null) {
            throw new IllegalArgumentException("'g' cannot be null");
        }
        if (aSN1Integer3 != null) {
            this.f7224p = aSN1Integer;
            this.f7222g = aSN1Integer2;
            this.f7225q = aSN1Integer3;
            this.f7223j = aSN1Integer4;
            this.validationParms = dHValidationParms;
            return;
        }
        throw new IllegalArgumentException("'q' cannot be null");
    }

    private DHDomainParameters(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() >= 3 && aSN1Sequence.size() <= 5) {
            Enumeration objects = aSN1Sequence.getObjects();
            this.f7224p = ASN1Integer.getInstance(objects.nextElement());
            this.f7222g = ASN1Integer.getInstance(objects.nextElement());
            this.f7225q = ASN1Integer.getInstance(objects.nextElement());
            ASN1Encodable next = getNext(objects);
            if (next != null && (next instanceof ASN1Integer)) {
                this.f7223j = ASN1Integer.getInstance(next);
                next = getNext(objects);
            }
            if (next != null) {
                this.validationParms = DHValidationParms.getInstance(next.toASN1Primitive());
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
    }
}
