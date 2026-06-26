package org.spongycastle.asn1;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class BERApplicationSpecificParser implements ASN1ApplicationSpecificParser {
    private final ASN1StreamParser parser;
    private final int tag;

    public BERApplicationSpecificParser(int i2, ASN1StreamParser aSN1StreamParser) {
        this.tag = i2;
        this.parser = aSN1StreamParser;
    }

    @Override // org.spongycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() {
        return new BERApplicationSpecific(this.tag, this.parser.readVector());
    }

    @Override // org.spongycastle.asn1.ASN1ApplicationSpecificParser
    public ASN1Encodable readObject() {
        return this.parser.readObject();
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
