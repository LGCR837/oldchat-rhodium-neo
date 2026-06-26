package org.spongycastle.asn1.cmc;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

/* JADX INFO: loaded from: classes.dex */
public class PKIData extends ASN1Object {
    private final TaggedContentInfo[] cmsSequence;
    private final TaggedAttribute[] controlSequence;
    private final OtherMsg[] otherMsgSequence;
    private final TaggedRequest[] reqSequence;

    public PKIData(TaggedAttribute[] taggedAttributeArr, TaggedRequest[] taggedRequestArr, TaggedContentInfo[] taggedContentInfoArr, OtherMsg[] otherMsgArr) {
        this.controlSequence = taggedAttributeArr;
        this.reqSequence = taggedRequestArr;
        this.cmsSequence = taggedContentInfoArr;
        this.otherMsgSequence = otherMsgArr;
    }

    public static PKIData getInstance(Object obj) {
        if (obj instanceof PKIData) {
            return (PKIData) obj;
        }
        if (obj != null) {
            return new PKIData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public TaggedContentInfo[] getCmsSequence() {
        return this.cmsSequence;
    }

    public TaggedAttribute[] getControlSequence() {
        return this.controlSequence;
    }

    public OtherMsg[] getOtherMsgSequence() {
        return this.otherMsgSequence;
    }

    public TaggedRequest[] getReqSequence() {
        return this.reqSequence;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(new ASN1Encodable[]{new DERSequence(this.controlSequence), new DERSequence(this.reqSequence), new DERSequence(this.cmsSequence), new DERSequence(this.otherMsgSequence)});
    }

    private PKIData(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 4) {
            int i2 = 0;
            ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.getObjectAt(0);
            this.controlSequence = new TaggedAttribute[aSN1Sequence2.size()];
            int i3 = 0;
            while (true) {
                TaggedAttribute[] taggedAttributeArr = this.controlSequence;
                if (i3 >= taggedAttributeArr.length) {
                    break;
                }
                taggedAttributeArr[i3] = TaggedAttribute.getInstance(aSN1Sequence2.getObjectAt(i3));
                i3++;
            }
            ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence.getObjectAt(1);
            this.reqSequence = new TaggedRequest[aSN1Sequence3.size()];
            int i4 = 0;
            while (true) {
                TaggedRequest[] taggedRequestArr = this.reqSequence;
                if (i4 >= taggedRequestArr.length) {
                    break;
                }
                taggedRequestArr[i4] = TaggedRequest.getInstance(aSN1Sequence3.getObjectAt(i4));
                i4++;
            }
            ASN1Sequence aSN1Sequence4 = (ASN1Sequence) aSN1Sequence.getObjectAt(2);
            this.cmsSequence = new TaggedContentInfo[aSN1Sequence4.size()];
            int i5 = 0;
            while (true) {
                TaggedContentInfo[] taggedContentInfoArr = this.cmsSequence;
                if (i5 >= taggedContentInfoArr.length) {
                    break;
                }
                taggedContentInfoArr[i5] = TaggedContentInfo.getInstance(aSN1Sequence4.getObjectAt(i5));
                i5++;
            }
            ASN1Sequence aSN1Sequence5 = (ASN1Sequence) aSN1Sequence.getObjectAt(3);
            this.otherMsgSequence = new OtherMsg[aSN1Sequence5.size()];
            while (true) {
                OtherMsg[] otherMsgArr = this.otherMsgSequence;
                if (i2 >= otherMsgArr.length) {
                    return;
                }
                otherMsgArr[i2] = OtherMsg.getInstance(aSN1Sequence5.getObjectAt(i2));
                i2++;
            }
        } else {
            throw new IllegalArgumentException("Sequence not 4 elements.");
        }
    }
}
