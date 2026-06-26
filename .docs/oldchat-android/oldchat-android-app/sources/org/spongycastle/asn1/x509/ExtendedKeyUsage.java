package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;

/* JADX INFO: loaded from: classes.dex */
public class ExtendedKeyUsage extends ASN1Object {
    ASN1Sequence seq;
    Hashtable usageTable = new Hashtable();

    public ExtendedKeyUsage(KeyPurposeId keyPurposeId) {
        this.seq = new DERSequence(keyPurposeId);
        this.usageTable.put(keyPurposeId, keyPurposeId);
    }

    public static ExtendedKeyUsage fromExtensions(Extensions extensions) {
        return getInstance(extensions.getExtensionParsedValue(Extension.extendedKeyUsage));
    }

    public static ExtendedKeyUsage getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z2) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z2));
    }

    public KeyPurposeId[] getUsages() {
        KeyPurposeId[] keyPurposeIdArr = new KeyPurposeId[this.seq.size()];
        Enumeration objects = this.seq.getObjects();
        int i2 = 0;
        while (objects.hasMoreElements()) {
            keyPurposeIdArr[i2] = KeyPurposeId.getInstance(objects.nextElement());
            i2++;
        }
        return keyPurposeIdArr;
    }

    public boolean hasKeyPurposeId(KeyPurposeId keyPurposeId) {
        return this.usageTable.get(keyPurposeId) != null;
    }

    public int size() {
        return this.usageTable.size();
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.seq;
    }

    public static ExtendedKeyUsage getInstance(Object obj) {
        if (obj instanceof ExtendedKeyUsage) {
            return (ExtendedKeyUsage) obj;
        }
        if (obj != null) {
            return new ExtendedKeyUsage(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private ExtendedKeyUsage(ASN1Sequence aSN1Sequence) {
        this.seq = aSN1Sequence;
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1Encodable aSN1Encodable = (ASN1Encodable) objects.nextElement();
            if (aSN1Encodable.toASN1Primitive() instanceof ASN1ObjectIdentifier) {
                this.usageTable.put(aSN1Encodable, aSN1Encodable);
            } else {
                throw new IllegalArgumentException("Only ASN1ObjectIdentifiers allowed in ExtendedKeyUsage.");
            }
        }
    }

    public ExtendedKeyUsage(KeyPurposeId[] keyPurposeIdArr) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i2 = 0; i2 != keyPurposeIdArr.length; i2++) {
            aSN1EncodableVector.add(keyPurposeIdArr[i2]);
            Hashtable hashtable = this.usageTable;
            KeyPurposeId keyPurposeId = keyPurposeIdArr[i2];
            hashtable.put(keyPurposeId, keyPurposeId);
        }
        this.seq = new DERSequence(aSN1EncodableVector);
    }

    public ExtendedKeyUsage(Vector vector) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Enumeration enumerationElements = vector.elements();
        while (enumerationElements.hasMoreElements()) {
            KeyPurposeId keyPurposeId = KeyPurposeId.getInstance(enumerationElements.nextElement());
            aSN1EncodableVector.add(keyPurposeId);
            this.usageTable.put(keyPurposeId, keyPurposeId);
        }
        this.seq = new DERSequence(aSN1EncodableVector);
    }
}
