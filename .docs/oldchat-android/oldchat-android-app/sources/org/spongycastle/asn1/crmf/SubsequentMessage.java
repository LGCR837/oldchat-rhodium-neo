package org.spongycastle.asn1.crmf;

import org.spongycastle.asn1.ASN1Integer;

/* JADX INFO: loaded from: classes.dex */
public class SubsequentMessage extends ASN1Integer {
    public static final SubsequentMessage encrCert = new SubsequentMessage(0);
    public static final SubsequentMessage challengeResp = new SubsequentMessage(1);

    private SubsequentMessage(int i2) {
        super(i2);
    }

    public static SubsequentMessage valueOf(int i2) {
        if (i2 == 0) {
            return encrCert;
        }
        if (i2 == 1) {
            return challengeResp;
        }
        throw new IllegalArgumentException("unknown value: " + i2);
    }
}
