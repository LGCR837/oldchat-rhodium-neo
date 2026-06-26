package org.spongycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class BERApplicationSpecific extends ASN1ApplicationSpecific {
    public BERApplicationSpecific(boolean z2, int i2, byte[] bArr) {
        super(z2, i2, bArr);
    }

    private static byte[] getEncodedVector(ASN1EncodableVector aSN1EncodableVector) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i2 = 0; i2 != aSN1EncodableVector.size(); i2++) {
            try {
                byteArrayOutputStream.write(((ASN1Object) aSN1EncodableVector.get(i2)).getEncoded(ASN1Encoding.BER));
            } catch (IOException e2) {
                throw new ASN1ParsingException("malformed object: " + e2, e2);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static byte[] getEncoding(boolean z2, ASN1Encodable aSN1Encodable) {
        byte[] encoded = aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.BER);
        if (z2) {
            return encoded;
        }
        int lengthOfHeader = ASN1ApplicationSpecific.getLengthOfHeader(encoded);
        int length = encoded.length - lengthOfHeader;
        byte[] bArr = new byte[length];
        System.arraycopy(encoded, lengthOfHeader, bArr, 0, length);
        return bArr;
    }

    @Override // org.spongycastle.asn1.ASN1ApplicationSpecific, org.spongycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeTag(this.isConstructed ? 96 : 64, this.tag);
        aSN1OutputStream.write(128);
        aSN1OutputStream.write(this.octets);
        aSN1OutputStream.write(0);
        aSN1OutputStream.write(0);
    }

    public BERApplicationSpecific(int i2, ASN1Encodable aSN1Encodable) {
        this(true, i2, aSN1Encodable);
    }

    public BERApplicationSpecific(boolean z2, int i2, ASN1Encodable aSN1Encodable) {
        super(z2 || aSN1Encodable.toASN1Primitive().isConstructed(), i2, getEncoding(z2, aSN1Encodable));
    }

    public BERApplicationSpecific(int i2, ASN1EncodableVector aSN1EncodableVector) {
        super(true, i2, getEncodedVector(aSN1EncodableVector));
    }
}
