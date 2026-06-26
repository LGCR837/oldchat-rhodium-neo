package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

/* JADX INFO: loaded from: classes.dex */
public class NameConstraints extends ASN1Object {
    private GeneralSubtree[] excluded;
    private GeneralSubtree[] permitted;

    private NameConstraints(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(objects.nextElement());
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 0) {
                this.permitted = createArray(ASN1Sequence.getInstance(aSN1TaggedObject, false));
            } else {
                if (tagNo != 1) {
                    throw new IllegalArgumentException("Unknown tag encountered: " + aSN1TaggedObject.getTagNo());
                }
                this.excluded = createArray(ASN1Sequence.getInstance(aSN1TaggedObject, false));
            }
        }
    }

    private static GeneralSubtree[] cloneSubtree(GeneralSubtree[] generalSubtreeArr) {
        if (generalSubtreeArr == null) {
            return null;
        }
        int length = generalSubtreeArr.length;
        GeneralSubtree[] generalSubtreeArr2 = new GeneralSubtree[length];
        System.arraycopy(generalSubtreeArr, 0, generalSubtreeArr2, 0, length);
        return generalSubtreeArr2;
    }

    private GeneralSubtree[] createArray(ASN1Sequence aSN1Sequence) {
        int size = aSN1Sequence.size();
        GeneralSubtree[] generalSubtreeArr = new GeneralSubtree[size];
        for (int i2 = 0; i2 != size; i2++) {
            generalSubtreeArr[i2] = GeneralSubtree.getInstance(aSN1Sequence.getObjectAt(i2));
        }
        return generalSubtreeArr;
    }

    public static NameConstraints getInstance(Object obj) {
        if (obj instanceof NameConstraints) {
            return (NameConstraints) obj;
        }
        if (obj != null) {
            return new NameConstraints(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public GeneralSubtree[] getExcludedSubtrees() {
        return cloneSubtree(this.excluded);
    }

    public GeneralSubtree[] getPermittedSubtrees() {
        return cloneSubtree(this.permitted);
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.permitted != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, new DERSequence(this.permitted)));
        }
        if (this.excluded != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, new DERSequence(this.excluded)));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public NameConstraints(GeneralSubtree[] generalSubtreeArr, GeneralSubtree[] generalSubtreeArr2) {
        this.permitted = cloneSubtree(generalSubtreeArr);
        this.excluded = cloneSubtree(generalSubtreeArr2);
    }
}
