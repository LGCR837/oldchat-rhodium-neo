package org.spongycastle.asn1.tsp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

/* JADX INFO: loaded from: classes.dex */
public class Accuracy extends ASN1Object {
    protected static final int MAX_MICROS = 999;
    protected static final int MAX_MILLIS = 999;
    protected static final int MIN_MICROS = 1;
    protected static final int MIN_MILLIS = 1;
    ASN1Integer micros;
    ASN1Integer millis;
    ASN1Integer seconds;

    public Accuracy() {
    }

    public static Accuracy getInstance(Object obj) {
        if (obj instanceof Accuracy) {
            return (Accuracy) obj;
        }
        if (obj != null) {
            return new Accuracy(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Integer getMicros() {
        return this.micros;
    }

    public ASN1Integer getMillis() {
        return this.millis;
    }

    public ASN1Integer getSeconds() {
        return this.seconds;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1Integer aSN1Integer = this.seconds;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        }
        if (this.millis != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.millis));
        }
        if (this.micros != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.micros));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public Accuracy(ASN1Integer aSN1Integer, ASN1Integer aSN1Integer2, ASN1Integer aSN1Integer3) {
        this.seconds = aSN1Integer;
        if (aSN1Integer2 != null && (aSN1Integer2.getValue().intValue() < 1 || aSN1Integer2.getValue().intValue() > 999)) {
            throw new IllegalArgumentException("Invalid millis field : not in (1..999)");
        }
        this.millis = aSN1Integer2;
        if (aSN1Integer3 != null && (aSN1Integer3.getValue().intValue() < 1 || aSN1Integer3.getValue().intValue() > 999)) {
            throw new IllegalArgumentException("Invalid micros field : not in (1..999)");
        }
        this.micros = aSN1Integer3;
    }

    private Accuracy(ASN1Sequence aSN1Sequence) {
        this.seconds = null;
        this.millis = null;
        this.micros = null;
        for (int i2 = 0; i2 < aSN1Sequence.size(); i2++) {
            if (aSN1Sequence.getObjectAt(i2) instanceof ASN1Integer) {
                this.seconds = (ASN1Integer) aSN1Sequence.getObjectAt(i2);
            } else if (aSN1Sequence.getObjectAt(i2) instanceof ASN1TaggedObject) {
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.getObjectAt(i2);
                int tagNo = aSN1TaggedObject.getTagNo();
                if (tagNo == 0) {
                    ASN1Integer aSN1Integer = ASN1Integer.getInstance(aSN1TaggedObject, false);
                    this.millis = aSN1Integer;
                    if (aSN1Integer.getValue().intValue() < 1 || this.millis.getValue().intValue() > 999) {
                        throw new IllegalArgumentException("Invalid millis field : not in (1..999).");
                    }
                } else if (tagNo == 1) {
                    ASN1Integer aSN1Integer2 = ASN1Integer.getInstance(aSN1TaggedObject, false);
                    this.micros = aSN1Integer2;
                    if (aSN1Integer2.getValue().intValue() < 1 || this.micros.getValue().intValue() > 999) {
                        throw new IllegalArgumentException("Invalid micros field : not in (1..999).");
                    }
                } else {
                    throw new IllegalArgumentException("Invalig tag number");
                }
            } else {
                continue;
            }
        }
    }
}
