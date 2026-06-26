package org.spongycastle.asn1.x500;

import java.util.Vector;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.x500.style.BCStyle;

/* JADX INFO: loaded from: classes.dex */
public class X500NameBuilder {
    private Vector rdns;
    private X500NameStyle template;

    public X500NameBuilder() {
        this(BCStyle.INSTANCE);
    }

    public X500NameBuilder addMultiValuedRDN(ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr, String[] strArr) {
        int length = strArr.length;
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[length];
        for (int i2 = 0; i2 != length; i2++) {
            aSN1EncodableArr[i2] = this.template.stringToValue(aSN1ObjectIdentifierArr[i2], strArr[i2]);
        }
        return addMultiValuedRDN(aSN1ObjectIdentifierArr, aSN1EncodableArr);
    }

    public X500NameBuilder addRDN(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        addRDN(aSN1ObjectIdentifier, this.template.stringToValue(aSN1ObjectIdentifier, str));
        return this;
    }

    public X500Name build() {
        int size = this.rdns.size();
        RDN[] rdnArr = new RDN[size];
        for (int i2 = 0; i2 != size; i2++) {
            rdnArr[i2] = (RDN) this.rdns.elementAt(i2);
        }
        return new X500Name(this.template, rdnArr);
    }

    public X500NameBuilder(X500NameStyle x500NameStyle) {
        this.rdns = new Vector();
        this.template = x500NameStyle;
    }

    public X500NameBuilder addRDN(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.rdns.addElement(new RDN(aSN1ObjectIdentifier, aSN1Encodable));
        return this;
    }

    public X500NameBuilder addRDN(AttributeTypeAndValue attributeTypeAndValue) {
        this.rdns.addElement(new RDN(attributeTypeAndValue));
        return this;
    }

    public X500NameBuilder addMultiValuedRDN(ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr, ASN1Encodable[] aSN1EncodableArr) {
        AttributeTypeAndValue[] attributeTypeAndValueArr = new AttributeTypeAndValue[aSN1ObjectIdentifierArr.length];
        for (int i2 = 0; i2 != aSN1ObjectIdentifierArr.length; i2++) {
            attributeTypeAndValueArr[i2] = new AttributeTypeAndValue(aSN1ObjectIdentifierArr[i2], aSN1EncodableArr[i2]);
        }
        return addMultiValuedRDN(attributeTypeAndValueArr);
    }

    public X500NameBuilder addMultiValuedRDN(AttributeTypeAndValue[] attributeTypeAndValueArr) {
        this.rdns.addElement(new RDN(attributeTypeAndValueArr));
        return this;
    }
}
