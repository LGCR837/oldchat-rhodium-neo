package org.spongycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public class BERTaggedObject extends ASN1TaggedObject {
    public BERTaggedObject(int i2, ASN1Encodable aSN1Encodable) {
        super(true, i2, aSN1Encodable);
    }

    @Override // org.spongycastle.asn1.ASN1TaggedObject, org.spongycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        Enumeration objects;
        aSN1OutputStream.writeTag(CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256, this.tagNo);
        aSN1OutputStream.write(128);
        if (!this.empty) {
            if (this.explicit) {
                aSN1OutputStream.writeObject(this.obj);
            } else {
                ASN1Encodable aSN1Encodable = this.obj;
                if (aSN1Encodable instanceof ASN1OctetString) {
                    objects = aSN1Encodable instanceof BEROctetString ? ((BEROctetString) aSN1Encodable).getObjects() : new BEROctetString(((ASN1OctetString) aSN1Encodable).getOctets()).getObjects();
                } else if (aSN1Encodable instanceof ASN1Sequence) {
                    objects = ((ASN1Sequence) aSN1Encodable).getObjects();
                } else {
                    if (!(aSN1Encodable instanceof ASN1Set)) {
                        throw new ASN1Exception("not implemented: " + this.obj.getClass().getName());
                    }
                    objects = ((ASN1Set) aSN1Encodable).getObjects();
                }
                while (objects.hasMoreElements()) {
                    aSN1OutputStream.writeObject((ASN1Encodable) objects.nextElement());
                }
            }
        }
        aSN1OutputStream.write(0);
        aSN1OutputStream.write(0);
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    public int encodedLength() {
        int iCalculateTagLength;
        if (this.empty) {
            return StreamUtil.calculateTagLength(this.tagNo) + 1;
        }
        int iEncodedLength = this.obj.toASN1Primitive().encodedLength();
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

    public BERTaggedObject(boolean z2, int i2, ASN1Encodable aSN1Encodable) {
        super(z2, i2, aSN1Encodable);
    }

    public BERTaggedObject(int i2) {
        super(false, i2, new BERSequence());
    }
}
