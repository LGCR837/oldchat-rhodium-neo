package org.spongycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class BERSequenceGenerator extends BERGenerator {
    public BERSequenceGenerator(OutputStream outputStream) throws IOException {
        super(outputStream);
        writeBERHeader(48);
    }

    public void addObject(ASN1Encodable aSN1Encodable) {
        aSN1Encodable.toASN1Primitive().encode(new BEROutputStream(this._out));
    }

    public void close() throws IOException {
        writeBEREnd();
    }

    public BERSequenceGenerator(OutputStream outputStream, int i2, boolean z2) throws IOException {
        super(outputStream, i2, z2);
        writeBERHeader(48);
    }
}
