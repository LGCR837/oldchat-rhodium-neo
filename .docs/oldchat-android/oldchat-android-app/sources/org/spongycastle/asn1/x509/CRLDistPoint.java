package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.util.Strings;

/* JADX INFO: loaded from: classes.dex */
public class CRLDistPoint extends ASN1Object {
    ASN1Sequence seq;

    private CRLDistPoint(ASN1Sequence aSN1Sequence) {
        this.seq = aSN1Sequence;
    }

    public static CRLDistPoint getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z2) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z2));
    }

    public DistributionPoint[] getDistributionPoints() {
        DistributionPoint[] distributionPointArr = new DistributionPoint[this.seq.size()];
        for (int i2 = 0; i2 != this.seq.size(); i2++) {
            distributionPointArr[i2] = DistributionPoint.getInstance(this.seq.getObjectAt(i2));
        }
        return distributionPointArr;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.seq;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String strLineSeparator = Strings.lineSeparator();
        stringBuffer.append("CRLDistPoint:");
        stringBuffer.append(strLineSeparator);
        DistributionPoint[] distributionPoints = getDistributionPoints();
        for (int i2 = 0; i2 != distributionPoints.length; i2++) {
            stringBuffer.append("    ");
            stringBuffer.append(distributionPoints[i2]);
            stringBuffer.append(strLineSeparator);
        }
        return stringBuffer.toString();
    }

    public static CRLDistPoint getInstance(Object obj) {
        if (obj instanceof CRLDistPoint) {
            return (CRLDistPoint) obj;
        }
        if (obj != null) {
            return new CRLDistPoint(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public CRLDistPoint(DistributionPoint[] distributionPointArr) {
        this.seq = null;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i2 = 0; i2 != distributionPointArr.length; i2++) {
            aSN1EncodableVector.add(distributionPointArr[i2]);
        }
        this.seq = new DERSequence(aSN1EncodableVector);
    }
}
