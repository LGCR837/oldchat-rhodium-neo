package org.spongycastle.asn1.x9;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1TaggedObject;

/* JADX INFO: loaded from: classes.dex */
public class DHPublicKey extends ASN1Object {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private ASN1Integer f7226y;

    private DHPublicKey(ASN1Integer aSN1Integer) {
        if (aSN1Integer == null) {
            throw new IllegalArgumentException("'y' cannot be null");
        }
        this.f7226y = aSN1Integer;
    }

    public static DHPublicKey getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z2) {
        return getInstance(ASN1Integer.getInstance(aSN1TaggedObject, z2));
    }

    public BigInteger getY() {
        return this.f7226y.getPositiveValue();
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.f7226y;
    }

    public static DHPublicKey getInstance(Object obj) {
        if (obj == null || (obj instanceof DHPublicKey)) {
            return (DHPublicKey) obj;
        }
        if (obj instanceof ASN1Integer) {
            return new DHPublicKey((ASN1Integer) obj);
        }
        throw new IllegalArgumentException("Invalid DHPublicKey: " + obj.getClass().getName());
    }

    public DHPublicKey(BigInteger bigInteger) {
        if (bigInteger != null) {
            this.f7226y = new ASN1Integer(bigInteger);
            return;
        }
        throw new IllegalArgumentException("'y' cannot be null");
    }
}
