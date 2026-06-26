package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

/* JADX INFO: loaded from: classes.dex */
public class PKIMessages extends ASN1Object {
    private ASN1Sequence content;

    private PKIMessages(ASN1Sequence aSN1Sequence) {
        this.content = aSN1Sequence;
    }

    public static PKIMessages getInstance(Object obj) {
        if (obj instanceof PKIMessages) {
            return (PKIMessages) obj;
        }
        if (obj != null) {
            return new PKIMessages(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.content;
    }

    public PKIMessage[] toPKIMessageArray() {
        int size = this.content.size();
        PKIMessage[] pKIMessageArr = new PKIMessage[size];
        for (int i2 = 0; i2 != size; i2++) {
            pKIMessageArr[i2] = PKIMessage.getInstance(this.content.getObjectAt(i2));
        }
        return pKIMessageArr;
    }

    public PKIMessages(PKIMessage pKIMessage) {
        this.content = new DERSequence(pKIMessage);
    }

    public PKIMessages(PKIMessage[] pKIMessageArr) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (PKIMessage pKIMessage : pKIMessageArr) {
            aSN1EncodableVector.add(pKIMessage);
        }
        this.content = new DERSequence(aSN1EncodableVector);
    }
}
