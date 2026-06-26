package org.spongycastle.asn1;

import java.io.IOException;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public class DERTaggedObject extends ASN1TaggedObject {
    private static final byte[] ZERO_BYTES = new byte[0];

    public DERTaggedObject(boolean z2, int i2, ASN1Encodable aSN1Encodable) {
        super(z2, i2, aSN1Encodable);
    }

    @Override // org.spongycastle.asn1.ASN1TaggedObject, org.spongycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        boolean z2 = this.empty;
        int i2 = CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256;
        if (z2) {
            aSN1OutputStream.writeEncoded(CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256, this.tagNo, ZERO_BYTES);
            return;
        }
        ASN1Primitive dERObject = this.obj.toASN1Primitive().toDERObject();
        if (this.explicit) {
            aSN1OutputStream.writeTag(CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256, this.tagNo);
            aSN1OutputStream.writeLength(dERObject.encodedLength());
            aSN1OutputStream.writeObject(dERObject);
        } else {
            if (!dERObject.isConstructed()) {
                i2 = 128;
            }
            aSN1OutputStream.writeTag(i2, this.tagNo);
            aSN1OutputStream.writeImplicitObject(dERObject);
        }
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    public int encodedLength() {
        int iCalculateTagLength;
        if (this.empty) {
            return StreamUtil.calculateTagLength(this.tagNo) + 1;
        }
        int iEncodedLength = this.obj.toASN1Primitive().toDERObject().encodedLength();
        if (this.explicit) {
            iCalculateTagLength = StreamUtil.calculateTagLength(this.tagNo) + StreamUtil.calculateBodyLength(iEncodedLength);
        } else {
            iEncodedLength--;
            iCalculateTagLength = StreamUtil.calculateTagLength(this.tagNo);
        }
        return iCalculateTagLength + iEncodedLength;
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        if (this.empty || this.explicit) {
            return true;
        }
        return this.obj.toASN1Primitive().toDERObject().isConstructed();
    }

    public DERTaggedObject(int i2, ASN1Encodable aSN1Encodable) {
        super(true, i2, aSN1Encodable);
    }
}
