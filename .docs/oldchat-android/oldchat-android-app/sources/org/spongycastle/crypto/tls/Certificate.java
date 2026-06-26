package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.asn1.ASN1Encoding;

/* JADX INFO: loaded from: classes.dex */
public class Certificate {
    public static final Certificate EMPTY_CHAIN = new Certificate(new org.spongycastle.asn1.x509.Certificate[0]);
    protected org.spongycastle.asn1.x509.Certificate[] certificateList;

    public Certificate(org.spongycastle.asn1.x509.Certificate[] certificateArr) {
        if (certificateArr == null) {
            throw new IllegalArgumentException("'certificateList' cannot be null");
        }
        this.certificateList = certificateArr;
    }

    public static Certificate parse(InputStream inputStream) {
        int uint24 = TlsUtils.readUint24(inputStream);
        if (uint24 == 0) {
            return EMPTY_CHAIN;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(TlsUtils.readFully(uint24, inputStream));
        Vector vector = new Vector();
        while (byteArrayInputStream.available() > 0) {
            vector.addElement(org.spongycastle.asn1.x509.Certificate.getInstance(TlsUtils.readASN1Object(TlsUtils.readOpaque24(byteArrayInputStream))));
        }
        org.spongycastle.asn1.x509.Certificate[] certificateArr = new org.spongycastle.asn1.x509.Certificate[vector.size()];
        for (int i2 = 0; i2 < vector.size(); i2++) {
            certificateArr[i2] = (org.spongycastle.asn1.x509.Certificate) vector.elementAt(i2);
        }
        return new Certificate(certificateArr);
    }

    public org.spongycastle.asn1.x509.Certificate[] cloneCertificateList() {
        org.spongycastle.asn1.x509.Certificate[] certificateArr = this.certificateList;
        int length = certificateArr.length;
        org.spongycastle.asn1.x509.Certificate[] certificateArr2 = new org.spongycastle.asn1.x509.Certificate[length];
        System.arraycopy(certificateArr, 0, certificateArr2, 0, length);
        return certificateArr2;
    }

    public void encode(OutputStream outputStream) {
        Vector vector = new Vector(this.certificateList.length);
        int i2 = 0;
        int length = 0;
        while (true) {
            org.spongycastle.asn1.x509.Certificate[] certificateArr = this.certificateList;
            if (i2 >= certificateArr.length) {
                break;
            }
            byte[] encoded = certificateArr[i2].getEncoded(ASN1Encoding.DER);
            vector.addElement(encoded);
            length += encoded.length + 3;
            i2++;
        }
        TlsUtils.checkUint24(length);
        TlsUtils.writeUint24(length, outputStream);
        for (int i3 = 0; i3 < vector.size(); i3++) {
            TlsUtils.writeOpaque24((byte[]) vector.elementAt(i3), outputStream);
        }
    }

    public org.spongycastle.asn1.x509.Certificate getCertificateAt(int i2) {
        return this.certificateList[i2];
    }

    public org.spongycastle.asn1.x509.Certificate[] getCertificateList() {
        return cloneCertificateList();
    }

    public int getLength() {
        return this.certificateList.length;
    }

    public boolean isEmpty() {
        return this.certificateList.length == 0;
    }
}
