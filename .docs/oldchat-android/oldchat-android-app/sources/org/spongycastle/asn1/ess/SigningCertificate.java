package org.spongycastle.asn1.ess;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.PolicyInformation;

/* JADX INFO: loaded from: classes.dex */
public class SigningCertificate extends ASN1Object {
    ASN1Sequence certs;
    ASN1Sequence policies;

    private SigningCertificate(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 1 || aSN1Sequence.size() > 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        this.certs = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() > 1) {
            this.policies = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
        }
    }

    public static SigningCertificate getInstance(Object obj) {
        if (obj instanceof SigningCertificate) {
            return (SigningCertificate) obj;
        }
        if (obj != null) {
            return new SigningCertificate(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ESSCertID[] getCerts() {
        ESSCertID[] eSSCertIDArr = new ESSCertID[this.certs.size()];
        for (int i2 = 0; i2 != this.certs.size(); i2++) {
            eSSCertIDArr[i2] = ESSCertID.getInstance(this.certs.getObjectAt(i2));
        }
        return eSSCertIDArr;
    }

    public PolicyInformation[] getPolicies() {
        ASN1Sequence aSN1Sequence = this.policies;
        if (aSN1Sequence == null) {
            return null;
        }
        PolicyInformation[] policyInformationArr = new PolicyInformation[aSN1Sequence.size()];
        for (int i2 = 0; i2 != this.policies.size(); i2++) {
            policyInformationArr[i2] = PolicyInformation.getInstance(this.policies.getObjectAt(i2));
        }
        return policyInformationArr;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.certs);
        ASN1Sequence aSN1Sequence = this.policies;
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(aSN1Sequence);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public SigningCertificate(ESSCertID eSSCertID) {
        this.certs = new DERSequence(eSSCertID);
    }
}
