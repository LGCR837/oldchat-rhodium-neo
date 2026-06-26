package org.spongycastle.asn1;

import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public interface ASN1OctetStringParser extends ASN1Encodable, InMemoryRepresentable {
    InputStream getOctetStream();
}
