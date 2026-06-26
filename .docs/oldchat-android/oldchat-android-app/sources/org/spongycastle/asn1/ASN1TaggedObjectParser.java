package org.spongycastle.asn1;

/* JADX INFO: loaded from: classes.dex */
public interface ASN1TaggedObjectParser extends ASN1Encodable, InMemoryRepresentable {
    ASN1Encodable getObjectParser(int i2, boolean z2);

    int getTagNo();
}
