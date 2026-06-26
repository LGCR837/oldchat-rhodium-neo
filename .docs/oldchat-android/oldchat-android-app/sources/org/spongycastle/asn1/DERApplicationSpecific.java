package org.spongycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.spongycastle.util.encoders.Hex;

/* JADX INFO: loaded from: classes.dex */
public class DERApplicationSpecific extends ASN1ApplicationSpecific {
    public DERApplicationSpecific(boolean z2, int i2, byte[] bArr) {
        super(z2, i2, bArr);
    }

    private static byte[] getEncodedVector(ASN1EncodableVector aSN1EncodableVector) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i2 = 0; i2 != aSN1EncodableVector.size(); i2++) {
            try {
                byteArrayOutputStream.write(((ASN1Object) aSN1EncodableVector.get(i2)).getEncoded(ASN1Encoding.DER));
            } catch (IOException e2) {
                throw new ASN1ParsingException("malformed object: " + e2, e2);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static byte[] getEncoding(boolean z2, ASN1Encodable aSN1Encodable) {
        byte[] encoded = aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER);
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
        aSN1OutputStream.writeEncoded(this.isConstructed ? 96 : 64, this.tag, this.octets);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        if (isConstructed()) {
            stringBuffer.append("CONSTRUCTED ");
        }
        stringBuffer.append("APPLICATION ");
        stringBuffer.append(Integer.toString(getApplicationTag()));
        stringBuffer.append("]");
        if (this.octets != null) {
            stringBuffer.append(" #");
            stringBuffer.append(Hex.toHexString(this.octets));
        } else {
            stringBuffer.append(" #null");
        }
        stringBuffer.append(" ");
        return stringBuffer.toString();
    }

    public DERApplicationSpecific(int i2, byte[] bArr) {
        this(false, i2, bArr);
    }

    public DERApplicationSpecific(int i2, ASN1Encodable aSN1Encodable) {
        this(true, i2, aSN1Encodable);
    }

    public DERApplicationSpecific(boolean z2, int i2, ASN1Encodable aSN1Encodable) {
        super(z2 || aSN1Encodable.toASN1Primitive().isConstructed(), i2, getEncoding(z2, aSN1Encodable));
    }

    public DERApplicationSpecific(int i2, ASN1EncodableVector aSN1EncodableVector) {
        super(true, i2, getEncodedVector(aSN1EncodableVector));
    }
}
