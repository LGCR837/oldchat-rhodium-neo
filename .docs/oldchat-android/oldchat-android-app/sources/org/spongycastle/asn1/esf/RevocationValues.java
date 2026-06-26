package org.spongycastle.asn1.esf;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.ocsp.BasicOCSPResponse;
import org.spongycastle.asn1.x509.CertificateList;

/* JADX INFO: loaded from: classes.dex */
public class RevocationValues extends ASN1Object {
    private ASN1Sequence crlVals;
    private ASN1Sequence ocspVals;
    private OtherRevVals otherRevVals;

    private RevocationValues(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() > 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objects.nextElement();
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 0) {
                ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1TaggedObject.getObject();
                Enumeration objects2 = aSN1Sequence2.getObjects();
                while (objects2.hasMoreElements()) {
                    CertificateList.getInstance(objects2.nextElement());
                }
                this.crlVals = aSN1Sequence2;
            } else if (tagNo == 1) {
                ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1TaggedObject.getObject();
                Enumeration objects3 = aSN1Sequence3.getObjects();
                while (objects3.hasMoreElements()) {
                    BasicOCSPResponse.getInstance(objects3.nextElement());
                }
                this.ocspVals = aSN1Sequence3;
            } else {
                if (tagNo != 2) {
                    throw new IllegalArgumentException("invalid tag: " + aSN1TaggedObject.getTagNo());
                }
                this.otherRevVals = OtherRevVals.getInstance(aSN1TaggedObject.getObject());
            }
        }
    }

    public static RevocationValues getInstance(Object obj) {
        if (obj instanceof RevocationValues) {
            return (RevocationValues) obj;
        }
        if (obj != null) {
            return new RevocationValues(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public CertificateList[] getCrlVals() {
        ASN1Sequence aSN1Sequence = this.crlVals;
        if (aSN1Sequence == null) {
            return new CertificateList[0];
        }
        int size = aSN1Sequence.size();
        CertificateList[] certificateListArr = new CertificateList[size];
        for (int i2 = 0; i2 < size; i2++) {
            certificateListArr[i2] = CertificateList.getInstance(this.crlVals.getObjectAt(i2));
        }
        return certificateListArr;
    }

    public BasicOCSPResponse[] getOcspVals() {
        ASN1Sequence aSN1Sequence = this.ocspVals;
        if (aSN1Sequence == null) {
            return new BasicOCSPResponse[0];
        }
        int size = aSN1Sequence.size();
        BasicOCSPResponse[] basicOCSPResponseArr = new BasicOCSPResponse[size];
        for (int i2 = 0; i2 < size; i2++) {
            basicOCSPResponseArr[i2] = BasicOCSPResponse.getInstance(this.ocspVals.getObjectAt(i2));
        }
        return basicOCSPResponseArr;
    }

    public OtherRevVals getOtherRevVals() {
        return this.otherRevVals;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.crlVals != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.crlVals));
        }
        if (this.ocspVals != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, this.ocspVals));
        }
        if (this.otherRevVals != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 2, this.otherRevVals.toASN1Primitive()));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public RevocationValues(CertificateList[] certificateListArr, BasicOCSPResponse[] basicOCSPResponseArr, OtherRevVals otherRevVals) {
        if (certificateListArr != null) {
            this.crlVals = new DERSequence(certificateListArr);
        }
        if (basicOCSPResponseArr != null) {
            this.ocspVals = new DERSequence(basicOCSPResponseArr);
        }
        this.otherRevVals = otherRevVals;
    }
}
