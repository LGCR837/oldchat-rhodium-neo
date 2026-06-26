package org.spongycastle.asn1.cmc;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* JADX INFO: loaded from: classes.dex */
public class CertificationRequest extends ASN1Object {
    private static final ASN1Integer ZERO = new ASN1Integer(0);
    private final CertificationRequestInfo certificationRequestInfo;
    private final DERBitString signature;
    private final AlgorithmIdentifier signatureAlgorithm;

    public CertificationRequest(X500Name x500Name, AlgorithmIdentifier algorithmIdentifier, DERBitString dERBitString, ASN1Set aSN1Set, AlgorithmIdentifier algorithmIdentifier2, DERBitString dERBitString2) {
        this.certificationRequestInfo = new CertificationRequestInfo(x500Name, algorithmIdentifier, dERBitString, aSN1Set);
        this.signatureAlgorithm = algorithmIdentifier2;
        this.signature = dERBitString2;
    }

    public static CertificationRequest getInstance(Object obj) {
        if (obj instanceof CertificationRequest) {
            return (CertificationRequest) obj;
        }
        if (obj != null) {
            return new CertificationRequest(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Set getAttributes() {
        return this.certificationRequestInfo.getAttributes();
    }

    public DERBitString getSignature() {
        return this.signature;
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public X500Name getSubject() {
        return this.certificationRequestInfo.getSubject();
    }

    public DERBitString getSubjectPublicKey() {
        return DERBitString.getInstance(this.certificationRequestInfo.getSubjectPublicKeyInfo().getObjectAt(1));
    }

    public AlgorithmIdentifier getSubjectPublicKeyAlgorithm() {
        return AlgorithmIdentifier.getInstance(this.certificationRequestInfo.getSubjectPublicKeyInfo().getObjectAt(0));
    }

    public BigInteger getVersion() {
        return this.certificationRequestInfo.getVersion().getValue();
    }

    public ASN1Primitive parsePublicKey() {
        return ASN1Primitive.fromByteArray(getSubjectPublicKey().getOctets());
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.certificationRequestInfo);
        aSN1EncodableVector.add(this.signatureAlgorithm);
        aSN1EncodableVector.add(this.signature);
        return new DERSequence(aSN1EncodableVector);
    }

    public class CertificationRequestInfo extends ASN1Object {
        private final ASN1Set attributes;
        private final X500Name subject;
        private final ASN1Sequence subjectPublicKeyInfo;
        private final ASN1Integer version;

        /* JADX INFO: Access modifiers changed from: private */
        public ASN1Set getAttributes() {
            return this.attributes;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public X500Name getSubject() {
            return this.subject;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ASN1Sequence getSubjectPublicKeyInfo() {
            return this.subjectPublicKeyInfo;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ASN1Integer getVersion() {
            return this.version;
        }

        @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
        public ASN1Primitive toASN1Primitive() {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            aSN1EncodableVector.add(this.version);
            aSN1EncodableVector.add(this.subject);
            aSN1EncodableVector.add(this.subjectPublicKeyInfo);
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.attributes));
            return new DERSequence(aSN1EncodableVector);
        }

        private CertificationRequestInfo(ASN1Sequence aSN1Sequence) {
            if (aSN1Sequence.size() == 4) {
                this.version = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
                this.subject = X500Name.getInstance(aSN1Sequence.getObjectAt(1));
                ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(2));
                this.subjectPublicKeyInfo = aSN1Sequence2;
                if (aSN1Sequence2.size() == 2) {
                    ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.getObjectAt(3);
                    if (aSN1TaggedObject.getTagNo() == 0) {
                        this.attributes = ASN1Set.getInstance(aSN1TaggedObject, false);
                        return;
                    }
                    throw new IllegalArgumentException("incorrect tag number on attributes for CertificationRequestInfo");
                }
                throw new IllegalArgumentException("incorrect subjectPublicKeyInfo size for CertificationRequestInfo");
            }
            throw new IllegalArgumentException("incorrect sequence size for CertificationRequestInfo");
        }

        private CertificationRequestInfo(X500Name x500Name, AlgorithmIdentifier algorithmIdentifier, DERBitString dERBitString, ASN1Set aSN1Set) {
            this.version = CertificationRequest.ZERO;
            this.subject = x500Name;
            this.subjectPublicKeyInfo = new DERSequence(new ASN1Encodable[]{algorithmIdentifier, dERBitString});
            this.attributes = aSN1Set;
        }
    }

    private CertificationRequest(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 3) {
            this.certificationRequestInfo = new CertificationRequestInfo(ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(0)));
            this.signatureAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
            this.signature = DERBitString.getInstance(aSN1Sequence.getObjectAt(2));
            return;
        }
        throw new IllegalArgumentException("incorrect sequence size");
    }
}
