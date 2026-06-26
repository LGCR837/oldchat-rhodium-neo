package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class CertificateURL {
    protected short type;
    protected Vector urlAndHashList;

    public class ListBuffer16 extends ByteArrayOutputStream {
        public ListBuffer16() throws IOException {
            TlsUtils.writeUint16(0, this);
        }

        public void encodeTo(OutputStream outputStream) throws IOException {
            int i2 = ((ByteArrayOutputStream) this).count - 2;
            TlsUtils.checkUint16(i2);
            TlsUtils.writeUint16(i2, ((ByteArrayOutputStream) this).buf, 0);
            outputStream.write(((ByteArrayOutputStream) this).buf, 0, ((ByteArrayOutputStream) this).count);
            ((ByteArrayOutputStream) this).buf = null;
        }
    }

    public CertificateURL(short s2, Vector vector) {
        if (!CertChainType.isValid(s2)) {
            throw new IllegalArgumentException("'type' is not a valid CertChainType value");
        }
        if (vector == null || vector.isEmpty()) {
            throw new IllegalArgumentException("'urlAndHashList' must have length > 0");
        }
        this.type = s2;
        this.urlAndHashList = vector;
    }

    public static CertificateURL parse(TlsContext tlsContext, InputStream inputStream) throws IOException {
        short uint8 = TlsUtils.readUint8(inputStream);
        if (!CertChainType.isValid(uint8)) {
            throw new TlsFatalAlert((short) 50);
        }
        int uint16 = TlsUtils.readUint16(inputStream);
        if (uint16 < 1) {
            throw new TlsFatalAlert((short) 50);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(TlsUtils.readFully(uint16, inputStream));
        Vector vector = new Vector();
        while (byteArrayInputStream.available() > 0) {
            vector.addElement(URLAndHash.parse(tlsContext, byteArrayInputStream));
        }
        return new CertificateURL(uint8, vector);
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsUtils.writeUint8(this.type, outputStream);
        ListBuffer16 listBuffer16 = new ListBuffer16();
        for (int i2 = 0; i2 < this.urlAndHashList.size(); i2++) {
            ((URLAndHash) this.urlAndHashList.elementAt(i2)).encode(listBuffer16);
        }
        listBuffer16.encodeTo(outputStream);
    }

    public short getType() {
        return this.type;
    }

    public Vector getURLAndHashList() {
        return this.urlAndHashList;
    }
}
