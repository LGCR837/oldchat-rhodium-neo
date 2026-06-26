package org.spongycastle.asn1.cmc;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

/* JADX INFO: loaded from: classes.dex */
public class ControlsProcessed extends ASN1Object {
    private final ASN1Sequence bodyPartReferences;

    public ControlsProcessed(BodyPartReference bodyPartReference) {
        this.bodyPartReferences = new DERSequence(bodyPartReference);
    }

    public static ControlsProcessed getInstance(Object obj) {
        if (obj instanceof ControlsProcessed) {
            return (ControlsProcessed) obj;
        }
        if (obj != null) {
            return new ControlsProcessed(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BodyPartReference[] getBodyList() {
        BodyPartReference[] bodyPartReferenceArr = new BodyPartReference[this.bodyPartReferences.size()];
        for (int i2 = 0; i2 != this.bodyPartReferences.size(); i2++) {
            bodyPartReferenceArr[i2] = BodyPartReference.getInstance(this.bodyPartReferences.getObjectAt(i2));
        }
        return bodyPartReferenceArr;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.bodyPartReferences);
    }

    public ControlsProcessed(BodyPartReference[] bodyPartReferenceArr) {
        this.bodyPartReferences = new DERSequence(bodyPartReferenceArr);
    }

    private ControlsProcessed(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 1) {
            this.bodyPartReferences = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(0));
            return;
        }
        throw new IllegalArgumentException("incorrect sequence size");
    }
}
