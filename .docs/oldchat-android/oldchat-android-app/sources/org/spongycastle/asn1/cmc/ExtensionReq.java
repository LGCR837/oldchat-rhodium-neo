package org.spongycastle.asn1.cmc;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.Extension;

/* JADX INFO: loaded from: classes.dex */
public class ExtensionReq extends ASN1Object {
    private final Extension[] extensions;

    public ExtensionReq(Extension extension) {
        this.extensions = new Extension[]{extension};
    }

    public static ExtensionReq getInstance(Object obj) {
        if (obj instanceof ExtensionReq) {
            return (ExtensionReq) obj;
        }
        if (obj != null) {
            return new ExtensionReq(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public Extension[] getExtensions() {
        return Utils.clone(this.extensions);
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.extensions);
    }

    public ExtensionReq(Extension[] extensionArr) {
        this.extensions = Utils.clone(extensionArr);
    }

    public static ExtensionReq getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z2) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z2));
    }

    private ExtensionReq(ASN1Sequence aSN1Sequence) {
        this.extensions = new Extension[aSN1Sequence.size()];
        for (int i2 = 0; i2 != aSN1Sequence.size(); i2++) {
            this.extensions[i2] = Extension.getInstance(aSN1Sequence.getObjectAt(i2));
        }
    }
}
