package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1OctetStringParser;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1SequenceParser;
import org.spongycastle.asn1.DERIA5String;

/* JADX INFO: loaded from: classes.dex */
public class TimeStampedDataParser {
    private ASN1OctetStringParser content;
    private DERIA5String dataUri;
    private MetaData metaData;
    private ASN1SequenceParser parser;
    private Evidence temporalEvidence;
    private ASN1Integer version;

    private TimeStampedDataParser(ASN1SequenceParser aSN1SequenceParser) {
        this.parser = aSN1SequenceParser;
        this.version = ASN1Integer.getInstance(aSN1SequenceParser.readObject());
        ASN1Encodable object = aSN1SequenceParser.readObject();
        if (object instanceof DERIA5String) {
            this.dataUri = DERIA5String.getInstance(object);
            object = aSN1SequenceParser.readObject();
        }
        if ((object instanceof MetaData) || (object instanceof ASN1SequenceParser)) {
            this.metaData = MetaData.getInstance(object.toASN1Primitive());
            object = aSN1SequenceParser.readObject();
        }
        if (object instanceof ASN1OctetStringParser) {
            this.content = (ASN1OctetStringParser) object;
        }
    }

    public static TimeStampedDataParser getInstance(Object obj) {
        if (obj instanceof ASN1Sequence) {
            return new TimeStampedDataParser(((ASN1Sequence) obj).parser());
        }
        if (obj instanceof ASN1SequenceParser) {
            return new TimeStampedDataParser((ASN1SequenceParser) obj);
        }
        return null;
    }

    public ASN1OctetStringParser getContent() {
        return this.content;
    }

    public DERIA5String getDataUri() {
        return this.dataUri;
    }

    public MetaData getMetaData() {
        return this.metaData;
    }

    public Evidence getTemporalEvidence() {
        if (this.temporalEvidence == null) {
            this.temporalEvidence = Evidence.getInstance(this.parser.readObject().toASN1Primitive());
        }
        return this.temporalEvidence;
    }
}
