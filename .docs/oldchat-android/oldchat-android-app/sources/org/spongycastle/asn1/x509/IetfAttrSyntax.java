package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import java.util.Vector;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.DERUTF8String;

/* JADX INFO: loaded from: classes.dex */
public class IetfAttrSyntax extends ASN1Object {
    public static final int VALUE_OCTETS = 1;
    public static final int VALUE_OID = 2;
    public static final int VALUE_UTF8 = 3;
    GeneralNames policyAuthority;
    int valueChoice;
    Vector values = new Vector();

    /* JADX WARN: Removed duplicated region for block: B:11:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private IetfAttrSyntax(org.spongycastle.asn1.ASN1Sequence r6) {
        /*
            r5 = this;
            r5.<init>()
            r0 = 0
            r5.policyAuthority = r0
            java.util.Vector r0 = new java.util.Vector
            r0.<init>()
            r5.values = r0
            r0 = -1
            r5.valueChoice = r0
            r0 = 0
            org.spongycastle.asn1.ASN1Encodable r1 = r6.getObjectAt(r0)
            boolean r1 = r1 instanceof org.spongycastle.asn1.ASN1TaggedObject
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L29
            org.spongycastle.asn1.ASN1Encodable r1 = r6.getObjectAt(r0)
            org.spongycastle.asn1.ASN1TaggedObject r1 = (org.spongycastle.asn1.ASN1TaggedObject) r1
            org.spongycastle.asn1.x509.GeneralNames r0 = org.spongycastle.asn1.x509.GeneralNames.getInstance(r1, r0)
            r5.policyAuthority = r0
        L27:
            r0 = 1
            goto L3a
        L29:
            int r1 = r6.size()
            if (r1 != r2) goto L3a
            org.spongycastle.asn1.ASN1Encodable r0 = r6.getObjectAt(r0)
            org.spongycastle.asn1.x509.GeneralNames r0 = org.spongycastle.asn1.x509.GeneralNames.getInstance(r0)
            r5.policyAuthority = r0
            goto L27
        L3a:
            org.spongycastle.asn1.ASN1Encodable r1 = r6.getObjectAt(r0)
            boolean r1 = r1 instanceof org.spongycastle.asn1.ASN1Sequence
            if (r1 == 0) goto L8a
            org.spongycastle.asn1.ASN1Encodable r6 = r6.getObjectAt(r0)
            org.spongycastle.asn1.ASN1Sequence r6 = (org.spongycastle.asn1.ASN1Sequence) r6
            java.util.Enumeration r6 = r6.getObjects()
        L4c:
            boolean r0 = r6.hasMoreElements()
            if (r0 == 0) goto L89
            java.lang.Object r0 = r6.nextElement()
            org.spongycastle.asn1.ASN1Primitive r0 = (org.spongycastle.asn1.ASN1Primitive) r0
            boolean r1 = r0 instanceof org.spongycastle.asn1.ASN1ObjectIdentifier
            if (r1 == 0) goto L5e
            r1 = 2
            goto L69
        L5e:
            boolean r1 = r0 instanceof org.spongycastle.asn1.DERUTF8String
            if (r1 == 0) goto L64
            r1 = 3
            goto L69
        L64:
            boolean r1 = r0 instanceof org.spongycastle.asn1.DEROctetString
            if (r1 == 0) goto L81
            r1 = 1
        L69:
            int r4 = r5.valueChoice
            if (r4 >= 0) goto L6f
            r5.valueChoice = r1
        L6f:
            int r4 = r5.valueChoice
            if (r1 != r4) goto L79
            java.util.Vector r1 = r5.values
            r1.addElement(r0)
            goto L4c
        L79:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Mix of value types in IetfAttrSyntax"
            r6.<init>(r0)
            throw r6
        L81:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Bad value type encoding IetfAttrSyntax"
            r6.<init>(r0)
            throw r6
        L89:
            return
        L8a:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Non-IetfAttrSyntax encoding"
            r6.<init>(r0)
            goto L93
        L92:
            throw r6
        L93:
            goto L92
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.IetfAttrSyntax.<init>(org.spongycastle.asn1.ASN1Sequence):void");
    }

    public static IetfAttrSyntax getInstance(Object obj) {
        if (obj instanceof IetfAttrSyntax) {
            return (IetfAttrSyntax) obj;
        }
        if (obj != null) {
            return new IetfAttrSyntax(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public GeneralNames getPolicyAuthority() {
        return this.policyAuthority;
    }

    public int getValueType() {
        return this.valueChoice;
    }

    public Object[] getValues() {
        int i2 = 0;
        if (getValueType() == 1) {
            int size = this.values.size();
            ASN1OctetString[] aSN1OctetStringArr = new ASN1OctetString[size];
            while (i2 != size) {
                aSN1OctetStringArr[i2] = (ASN1OctetString) this.values.elementAt(i2);
                i2++;
            }
            return aSN1OctetStringArr;
        }
        if (getValueType() == 2) {
            int size2 = this.values.size();
            ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr = new ASN1ObjectIdentifier[size2];
            while (i2 != size2) {
                aSN1ObjectIdentifierArr[i2] = (ASN1ObjectIdentifier) this.values.elementAt(i2);
                i2++;
            }
            return aSN1ObjectIdentifierArr;
        }
        int size3 = this.values.size();
        DERUTF8String[] dERUTF8StringArr = new DERUTF8String[size3];
        while (i2 != size3) {
            dERUTF8StringArr[i2] = (DERUTF8String) this.values.elementAt(i2);
            i2++;
        }
        return dERUTF8StringArr;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.policyAuthority != null) {
            aSN1EncodableVector.add(new DERTaggedObject(0, this.policyAuthority));
        }
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        Enumeration enumerationElements = this.values.elements();
        while (enumerationElements.hasMoreElements()) {
            aSN1EncodableVector2.add((ASN1Encodable) enumerationElements.nextElement());
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        return new DERSequence(aSN1EncodableVector);
    }
}
