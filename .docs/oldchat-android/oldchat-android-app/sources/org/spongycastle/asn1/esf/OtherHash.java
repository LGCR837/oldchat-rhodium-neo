package org.spongycastle.asn1.esf;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

/* JADX INFO: loaded from: classes.dex */
public class OtherHash extends ASN1Object implements ASN1Choice {
    private OtherHashAlgAndValue otherHash;
    private ASN1OctetString sha1Hash;

    private OtherHash(ASN1OctetString aSN1OctetString) {
        this.sha1Hash = aSN1OctetString;
    }

    public static OtherHash getInstance(Object obj) {
        return obj instanceof OtherHash ? (OtherHash) obj : obj instanceof ASN1OctetString ? new OtherHash((ASN1OctetString) obj) : new OtherHash(OtherHashAlgAndValue.getInstance(obj));
    }

    public AlgorithmIdentifier getHashAlgorithm() {
        OtherHashAlgAndValue otherHashAlgAndValue = this.otherHash;
        return otherHashAlgAndValue == null ? new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1) : otherHashAlgAndValue.getHashAlgorithm();
    }

    public byte[] getHashValue() {
        OtherHashAlgAndValue otherHashAlgAndValue = this.otherHash;
        return otherHashAlgAndValue == null ? this.sha1Hash.getOctets() : otherHashAlgAndValue.getHashValue().getOctets();
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        OtherHashAlgAndValue otherHashAlgAndValue = this.otherHash;
        return otherHashAlgAndValue == null ? this.sha1Hash : otherHashAlgAndValue.toASN1Primitive();
    }

    public OtherHash(OtherHashAlgAndValue otherHashAlgAndValue) {
        this.otherHash = otherHashAlgAndValue;
    }

    public OtherHash(byte[] bArr) {
        this.sha1Hash = new DEROctetString(bArr);
    }
}
