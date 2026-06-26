package org.spongycastle.asn1.crmf;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

/* JADX INFO: loaded from: classes.dex */
public class CertReqMessages extends ASN1Object {
    private ASN1Sequence content;

    private CertReqMessages(ASN1Sequence aSN1Sequence) {
        this.content = aSN1Sequence;
    }

    public static CertReqMessages getInstance(Object obj) {
        if (obj instanceof CertReqMessages) {
            return (CertReqMessages) obj;
        }
        if (obj != null) {
            return new CertReqMessages(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.content;
    }

    public CertReqMsg[] toCertReqMsgArray() {
        int size = this.content.size();
        CertReqMsg[] certReqMsgArr = new CertReqMsg[size];
        for (int i2 = 0; i2 != size; i2++) {
            certReqMsgArr[i2] = CertReqMsg.getInstance(this.content.getObjectAt(i2));
        }
        return certReqMsgArr;
    }

    public CertReqMessages(CertReqMsg certReqMsg) {
        this.content = new DERSequence(certReqMsg);
    }

    public CertReqMessages(CertReqMsg[] certReqMsgArr) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (CertReqMsg certReqMsg : certReqMsgArr) {
            aSN1EncodableVector.add(certReqMsg);
        }
        this.content = new DERSequence(aSN1EncodableVector);
    }
}
