package org.spongycastle.asn1;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class BERSetParser implements ASN1SetParser {
    private ASN1StreamParser _parser;

    public BERSetParser(ASN1StreamParser aSN1StreamParser) {
        this._parser = aSN1StreamParser;
    }

    @Override // org.spongycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() {
        return new BERSet(this._parser.readVector());
    }

    @Override // org.spongycastle.asn1.ASN1SetParser
    public ASN1Encodable readObject() {
        return this._parser.readObject();
    }

    @Override // org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        try {
            return getLoadedObject();
        } catch (IOException e2) {
            throw new ASN1ParsingException(e2.getMessage(), e2);
        }
    }
}
