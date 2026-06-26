package org.spongycastle.jcajce.provider.asymmetric.x509;

import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.util.encoders.Base64;

/* JADX INFO: loaded from: classes.dex */
class PEMUtil {
    private final String _footer1;
    private final String _footer2;
    private final String _header1;
    private final String _header2;
    private final String _header3 = "-----BEGIN PKCS7-----";
    private final String _footer3 = "-----END PKCS7-----";

    public PEMUtil(String str) {
        this._header1 = "-----BEGIN " + str + "-----";
        this._header2 = "-----BEGIN X509 " + str + "-----";
        this._footer1 = "-----END " + str + "-----";
        this._footer2 = "-----END X509 " + str + "-----";
    }

    private String readLine(InputStream inputStream) throws IOException {
        int i2;
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            i2 = inputStream.read();
            if (i2 != 13 && i2 != 10 && i2 >= 0) {
                stringBuffer.append((char) i2);
            } else if (i2 < 0 || stringBuffer.length() != 0) {
                break;
            }
        }
        if (i2 < 0) {
            return null;
        }
        if (i2 == 13) {
            inputStream.mark(1);
            int i3 = inputStream.read();
            if (i3 == 10) {
                inputStream.mark(1);
            }
            if (i3 > 0) {
                inputStream.reset();
            }
        }
        return stringBuffer.toString();
    }

    public ASN1Sequence readPEMObject(InputStream inputStream) throws IOException {
        String line;
        StringBuffer stringBuffer = new StringBuffer();
        do {
            line = readLine(inputStream);
            if (line == null || line.startsWith(this._header1) || line.startsWith(this._header2)) {
                break;
            }
        } while (!line.startsWith(this._header3));
        while (true) {
            String line2 = readLine(inputStream);
            if (line2 == null || line2.startsWith(this._footer1) || line2.startsWith(this._footer2) || line2.startsWith(this._footer3)) {
                break;
            }
            stringBuffer.append(line2);
        }
        if (stringBuffer.length() == 0) {
            return null;
        }
        try {
            return ASN1Sequence.getInstance(Base64.decode(stringBuffer.toString()));
        } catch (Exception unused) {
            throw new IOException("malformed PEM data encountered");
        }
    }
}
