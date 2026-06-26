package org.spongycastle.asn1.bc;

import java.io.IOException;
import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;

/* JADX INFO: loaded from: classes.dex */
public class ObjectStoreIntegrityCheck extends ASN1Object implements ASN1Choice {
    public static final int PBKD_MAC_CHECK = 0;
    private final ASN1Object integrityCheck;
    private final int type;

    public ObjectStoreIntegrityCheck(PbkdMacIntegrityCheck pbkdMacIntegrityCheck) {
        this((ASN1Encodable) pbkdMacIntegrityCheck);
    }

    public static ObjectStoreIntegrityCheck getInstance(Object obj) {
        if (obj instanceof ObjectStoreIntegrityCheck) {
            return (ObjectStoreIntegrityCheck) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return new ObjectStoreIntegrityCheck(ASN1Primitive.fromByteArray((byte[]) obj));
            } catch (IOException unused) {
                throw new IllegalArgumentException("Unable to parse integrity check details.");
            }
        }
        if (obj != null) {
            return new ObjectStoreIntegrityCheck((ASN1Encodable) obj);
        }
        return null;
    }

    public ASN1Object getIntegrityCheck() {
        return this.integrityCheck;
    }

    public int getType() {
        return this.type;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.integrityCheck.toASN1Primitive();
    }

    private ObjectStoreIntegrityCheck(ASN1Encodable aSN1Encodable) {
        if (!(aSN1Encodable instanceof ASN1Sequence) && !(aSN1Encodable instanceof PbkdMacIntegrityCheck)) {
            throw new IllegalArgumentException("Unknown check object in integrity check.");
        }
        this.type = 0;
        this.integrityCheck = PbkdMacIntegrityCheck.getInstance(aSN1Encodable);
    }
}
