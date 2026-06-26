package org.spongycastle.asn1.bc;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class EncryptedSecretKeyData extends ASN1Object {
    private final ASN1OctetString encryptedKeyData;
    private final AlgorithmIdentifier keyEncryptionAlgorithm;

    public EncryptedSecretKeyData(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) {
        this.keyEncryptionAlgorithm = algorithmIdentifier;
        this.encryptedKeyData = new DEROctetString(Arrays.clone(bArr));
    }

    public static EncryptedSecretKeyData getInstance(Object obj) {
        if (obj instanceof EncryptedSecretKeyData) {
            return (EncryptedSecretKeyData) obj;
        }
        if (obj != null) {
            return new EncryptedSecretKeyData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public byte[] getEncryptedKeyData() {
        return Arrays.clone(this.encryptedKeyData.getOctets());
    }

    public AlgorithmIdentifier getKeyEncryptionAlgorithm() {
        return this.keyEncryptionAlgorithm;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.keyEncryptionAlgorithm);
        aSN1EncodableVector.add(this.encryptedKeyData);
        return new DERSequence(aSN1EncodableVector);
    }

    private EncryptedSecretKeyData(ASN1Sequence aSN1Sequence) {
        this.keyEncryptionAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.encryptedKeyData = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1));
    }
}
