package org.spongycastle.asn1;

import java.util.Enumeration;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class ASN1EncodableVector {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private final Vector f7175v = new Vector();

    public void add(ASN1Encodable aSN1Encodable) {
        this.f7175v.addElement(aSN1Encodable);
    }

    public void addAll(ASN1EncodableVector aSN1EncodableVector) {
        Enumeration enumerationElements = aSN1EncodableVector.f7175v.elements();
        while (enumerationElements.hasMoreElements()) {
            this.f7175v.addElement(enumerationElements.nextElement());
        }
    }

    public ASN1Encodable get(int i2) {
        return (ASN1Encodable) this.f7175v.elementAt(i2);
    }

    public int size() {
        return this.f7175v.size();
    }
}
