package org.spongycastle.asn1.cmc;

import java.io.IOException;
import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;

/* JADX INFO: loaded from: classes.dex */
public class OtherStatusInfo extends ASN1Object implements ASN1Choice {
    private final ExtendedFailInfo extendedFailInfo;
    private final CMCFailInfo failInfo;
    private final PendInfo pendInfo;

    public OtherStatusInfo(CMCFailInfo cMCFailInfo) {
        this(cMCFailInfo, null, null);
    }

    public static OtherStatusInfo getInstance(Object obj) {
        if (obj instanceof OtherStatusInfo) {
            return (OtherStatusInfo) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1Integer) {
                return new OtherStatusInfo(CMCFailInfo.getInstance(aSN1Primitive));
            }
            if (aSN1Primitive instanceof ASN1Sequence) {
                return ((ASN1Sequence) aSN1Primitive).getObjectAt(0) instanceof ASN1ObjectIdentifier ? new OtherStatusInfo(ExtendedFailInfo.getInstance(aSN1Primitive)) : new OtherStatusInfo(PendInfo.getInstance(aSN1Primitive));
            }
        } else if (obj instanceof byte[]) {
            try {
                return getInstance(ASN1Primitive.fromByteArray((byte[]) obj));
            } catch (IOException e2) {
                throw new IllegalArgumentException("parsing error: " + e2.getMessage());
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance(): " + obj.getClass().getName());
    }

    public boolean isExtendedFailInfo() {
        return this.extendedFailInfo != null;
    }

    public boolean isFailInfo() {
        return this.failInfo != null;
    }

    public boolean isPendingInfo() {
        return this.pendInfo != null;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        PendInfo pendInfo = this.pendInfo;
        if (pendInfo != null) {
            return pendInfo.toASN1Primitive();
        }
        CMCFailInfo cMCFailInfo = this.failInfo;
        return cMCFailInfo != null ? cMCFailInfo.toASN1Primitive() : this.extendedFailInfo.toASN1Primitive();
    }

    public OtherStatusInfo(PendInfo pendInfo) {
        this(null, pendInfo, null);
    }

    public OtherStatusInfo(ExtendedFailInfo extendedFailInfo) {
        this(null, null, extendedFailInfo);
    }

    private OtherStatusInfo(CMCFailInfo cMCFailInfo, PendInfo pendInfo, ExtendedFailInfo extendedFailInfo) {
        this.failInfo = cMCFailInfo;
        this.pendInfo = pendInfo;
        this.extendedFailInfo = extendedFailInfo;
    }
}
