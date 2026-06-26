package org.spongycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Iterable;

/* JADX INFO: loaded from: classes.dex */
public abstract class ASN1Set extends ASN1Primitive implements Iterable<ASN1Encodable> {
    private boolean isSorted;
    private Vector set;

    public ASN1Set() {
        this.set = new Vector();
        this.isSorted = false;
    }

    private byte[] getDEREncoded(ASN1Encodable aSN1Encodable) {
        try {
            return aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER);
        } catch (IOException unused) {
            throw new IllegalArgumentException("cannot encode object added to SET");
        }
    }

    public static ASN1Set getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1Set)) {
            return (ASN1Set) obj;
        }
        if (obj instanceof ASN1SetParser) {
            return getInstance(((ASN1SetParser) obj).toASN1Primitive());
        }
        if (obj instanceof byte[]) {
            try {
                return getInstance(ASN1Primitive.fromByteArray((byte[]) obj));
            } catch (IOException e2) {
                throw new IllegalArgumentException("failed to construct set from byte[]: " + e2.getMessage());
            }
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1Set) {
                return (ASN1Set) aSN1Primitive;
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
    }

    private ASN1Encodable getNext(Enumeration enumeration) {
        ASN1Encodable aSN1Encodable = (ASN1Encodable) enumeration.nextElement();
        return aSN1Encodable == null ? DERNull.INSTANCE : aSN1Encodable;
    }

    private boolean lessThanOrEqual(byte[] bArr, byte[] bArr2) {
        int iMin = Math.min(bArr.length, bArr2.length);
        for (int i2 = 0; i2 != iMin; i2++) {
            byte b2 = bArr[i2];
            byte b3 = bArr2[i2];
            if (b2 != b3) {
                return (b2 & 255) < (b3 & 255);
            }
        }
        return iMin == bArr.length;
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1Set)) {
            return false;
        }
        ASN1Set aSN1Set = (ASN1Set) aSN1Primitive;
        if (size() != aSN1Set.size()) {
            return false;
        }
        Enumeration objects = getObjects();
        Enumeration objects2 = aSN1Set.getObjects();
        while (objects.hasMoreElements()) {
            ASN1Encodable next = getNext(objects);
            ASN1Encodable next2 = getNext(objects2);
            ASN1Primitive aSN1Primitive2 = next.toASN1Primitive();
            ASN1Primitive aSN1Primitive3 = next2.toASN1Primitive();
            if (aSN1Primitive2 != aSN1Primitive3 && !aSN1Primitive2.equals(aSN1Primitive3)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    public abstract void encode(ASN1OutputStream aSN1OutputStream);

    public ASN1Encodable getObjectAt(int i2) {
        return (ASN1Encodable) this.set.elementAt(i2);
    }

    public Enumeration getObjects() {
        return this.set.elements();
    }

    @Override // org.spongycastle.asn1.ASN1Primitive, org.spongycastle.asn1.ASN1Object
    public int hashCode() {
        Enumeration objects = getObjects();
        int size = size();
        while (objects.hasMoreElements()) {
            size = (size * 17) ^ getNext(objects).hashCode();
        }
        return size;
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return true;
    }

    @Override // org.spongycastle.util.Iterable, java.lang.Iterable
    public Iterator<ASN1Encodable> iterator() {
        return new Arrays.Iterator(toArray());
    }

    public ASN1SetParser parser() {
        return new ASN1SetParser() { // from class: org.spongycastle.asn1.ASN1Set.1
            private int index;
            private final int max;

            {
                this.max = ASN1Set.this.size();
            }

            @Override // org.spongycastle.asn1.InMemoryRepresentable
            public ASN1Primitive getLoadedObject() {
                return this;
            }

            @Override // org.spongycastle.asn1.ASN1SetParser
            public ASN1Encodable readObject() {
                int i2 = this.index;
                if (i2 == this.max) {
                    return null;
                }
                ASN1Set aSN1Set = ASN1Set.this;
                this.index = i2 + 1;
                ASN1Encodable objectAt = aSN1Set.getObjectAt(i2);
                return objectAt instanceof ASN1Sequence ? ((ASN1Sequence) objectAt).parser() : objectAt instanceof ASN1Set ? ((ASN1Set) objectAt).parser() : objectAt;
            }

            @Override // org.spongycastle.asn1.ASN1Encodable
            public ASN1Primitive toASN1Primitive() {
                return this;
            }
        };
    }

    public int size() {
        return this.set.size();
    }

    public void sort() {
        if (this.isSorted) {
            return;
        }
        this.isSorted = true;
        if (this.set.size() > 1) {
            int size = this.set.size() - 1;
            boolean z2 = true;
            while (z2) {
                int i2 = 0;
                byte[] dEREncoded = getDEREncoded((ASN1Encodable) this.set.elementAt(0));
                z2 = false;
                int i3 = 0;
                while (i3 != size) {
                    int i4 = i3 + 1;
                    byte[] dEREncoded2 = getDEREncoded((ASN1Encodable) this.set.elementAt(i4));
                    if (lessThanOrEqual(dEREncoded, dEREncoded2)) {
                        dEREncoded = dEREncoded2;
                    } else {
                        Object objElementAt = this.set.elementAt(i3);
                        Vector vector = this.set;
                        vector.setElementAt(vector.elementAt(i4), i3);
                        this.set.setElementAt(objElementAt, i4);
                        i2 = i3;
                        z2 = true;
                    }
                    i3 = i4;
                }
                size = i2;
            }
        }
    }

    public ASN1Encodable[] toArray() {
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[size()];
        for (int i2 = 0; i2 != size(); i2++) {
            aSN1EncodableArr[i2] = getObjectAt(i2);
        }
        return aSN1EncodableArr;
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    public ASN1Primitive toDERObject() {
        if (this.isSorted) {
            DERSet dERSet = new DERSet();
            dERSet.set = this.set;
            return dERSet;
        }
        Vector vector = new Vector();
        for (int i2 = 0; i2 != this.set.size(); i2++) {
            vector.addElement(this.set.elementAt(i2));
        }
        DERSet dERSet2 = new DERSet();
        dERSet2.set = vector;
        dERSet2.sort();
        return dERSet2;
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    public ASN1Primitive toDLObject() {
        DLSet dLSet = new DLSet();
        dLSet.set = this.set;
        return dLSet;
    }

    public String toString() {
        return this.set.toString();
    }

    public ASN1Set(ASN1Encodable aSN1Encodable) {
        Vector vector = new Vector();
        this.set = vector;
        this.isSorted = false;
        vector.addElement(aSN1Encodable);
    }

    public ASN1Set(ASN1EncodableVector aSN1EncodableVector, boolean z2) {
        this.set = new Vector();
        this.isSorted = false;
        for (int i2 = 0; i2 != aSN1EncodableVector.size(); i2++) {
            this.set.addElement(aSN1EncodableVector.get(i2));
        }
        if (z2) {
            sort();
        }
    }

    public static ASN1Set getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z2) {
        if (z2) {
            if (aSN1TaggedObject.isExplicit()) {
                return (ASN1Set) aSN1TaggedObject.getObject();
            }
            throw new IllegalArgumentException("object implicit - explicit expected.");
        }
        if (aSN1TaggedObject.isExplicit()) {
            if (aSN1TaggedObject instanceof BERTaggedObject) {
                return new BERSet(aSN1TaggedObject.getObject());
            }
            return new DLSet(aSN1TaggedObject.getObject());
        }
        if (aSN1TaggedObject.getObject() instanceof ASN1Set) {
            return (ASN1Set) aSN1TaggedObject.getObject();
        }
        if (aSN1TaggedObject.getObject() instanceof ASN1Sequence) {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) aSN1TaggedObject.getObject();
            if (aSN1TaggedObject instanceof BERTaggedObject) {
                return new BERSet(aSN1Sequence.toArray());
            }
            return new DLSet(aSN1Sequence.toArray());
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + aSN1TaggedObject.getClass().getName());
    }

    public ASN1Set(ASN1Encodable[] aSN1EncodableArr, boolean z2) {
        this.set = new Vector();
        this.isSorted = false;
        for (int i2 = 0; i2 != aSN1EncodableArr.length; i2++) {
            this.set.addElement(aSN1EncodableArr[i2]);
        }
        if (z2) {
            sort();
        }
    }
}
