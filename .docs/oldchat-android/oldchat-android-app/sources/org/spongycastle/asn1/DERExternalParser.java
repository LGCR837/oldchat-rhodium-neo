package org.spongycastle.asn1;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class DERExternalParser implements ASN1Encodable, InMemoryRepresentable {
    private ASN1StreamParser _parser;

    public DERExternalParser(ASN1StreamParser aSN1StreamParser) {
        this._parser = aSN1StreamParser;
    }

    @Override // org.spongycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() throws ASN1Exception {
        try {
            return new DERExternal(this._parser.readVector());
        } catch (IllegalArgumentException e2) {
            throw new ASN1Exception(e2.getMessage(), e2);
        }
    }

    public ASN1Encodable readObject() {
        return this._parser.readObject();
    }

    @Override // org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        try {
            return getLoadedObject();
        } catch (IOException e2) {
            throw new ASN1ParsingException("unable to get DER object", e2);
        } catch (IllegalArgumentException e3) {
            throw new ASN1ParsingException("unable to get DER object", e3);
        }
    }
}
