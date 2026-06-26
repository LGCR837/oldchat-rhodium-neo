package org.spongycastle.asn1.eac;

import java.util.Hashtable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERApplicationSpecific;
import org.spongycastle.util.Integers;

/* JADX INFO: loaded from: classes.dex */
public class CertificateHolderAuthorization extends ASN1Object {
    public static final int CVCA = 192;
    public static final int DV_DOMESTIC = 128;
    public static final int DV_FOREIGN = 64;
    public static final int IS = 0;
    public static final int RADG3 = 1;
    public static final int RADG4 = 2;
    DERApplicationSpecific accessRights;
    ASN1ObjectIdentifier oid;
    public static final ASN1ObjectIdentifier id_role_EAC = EACObjectIdentifiers.bsi_de.branch("3.1.2.1");
    static Hashtable RightsDecodeMap = new Hashtable();
    static BidirectionalMap AuthorizationRole = new BidirectionalMap();
    static Hashtable ReverseMap = new Hashtable();

    static {
        RightsDecodeMap.put(Integers.valueOf(2), "RADG4");
        RightsDecodeMap.put(Integers.valueOf(1), "RADG3");
        AuthorizationRole.put(Integers.valueOf(192), "CVCA");
        AuthorizationRole.put(Integers.valueOf(128), "DV_DOMESTIC");
        AuthorizationRole.put(Integers.valueOf(64), "DV_FOREIGN");
        AuthorizationRole.put(Integers.valueOf(0), "IS");
    }

    public CertificateHolderAuthorization(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i2) {
        setOid(aSN1ObjectIdentifier);
        setAccessRights((byte) i2);
    }

    public static int getFlag(String str) {
        Integer num = (Integer) AuthorizationRole.getReverse(str);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException("Unknown value " + str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String getRoleDescription(int i2) {
        return (String) AuthorizationRole.get(Integers.valueOf(i2));
    }

    private void setAccessRights(byte b2) {
        this.accessRights = new DERApplicationSpecific(19, new byte[]{b2});
    }

    private void setOid(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.oid = aSN1ObjectIdentifier;
    }

    private void setPrivateData(ASN1InputStream aSN1InputStream) {
        ASN1Primitive object = aSN1InputStream.readObject();
        if (!(object instanceof ASN1ObjectIdentifier)) {
            throw new IllegalArgumentException("no Oid in CerticateHolderAuthorization");
        }
        this.oid = (ASN1ObjectIdentifier) object;
        ASN1Primitive object2 = aSN1InputStream.readObject();
        if (!(object2 instanceof DERApplicationSpecific)) {
            throw new IllegalArgumentException("No access rights in CerticateHolderAuthorization");
        }
        this.accessRights = (DERApplicationSpecific) object2;
    }

    public int getAccessRights() {
        return this.accessRights.getContents()[0] & 255;
    }

    public ASN1ObjectIdentifier getOid() {
        return this.oid;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.oid);
        aSN1EncodableVector.add(this.accessRights);
        return new DERApplicationSpecific(76, aSN1EncodableVector);
    }

    public CertificateHolderAuthorization(DERApplicationSpecific dERApplicationSpecific) {
        if (dERApplicationSpecific.getApplicationTag() == 76) {
            setPrivateData(new ASN1InputStream(dERApplicationSpecific.getContents()));
        }
    }
}
