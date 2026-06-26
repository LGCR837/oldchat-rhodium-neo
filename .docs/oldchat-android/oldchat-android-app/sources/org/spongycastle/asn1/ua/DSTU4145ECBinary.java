package org.spongycastle.asn1.ua;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.math.ec.ECAlgorithms;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.field.PolynomialExtensionField;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class DSTU4145ECBinary extends ASN1Object {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ASN1Integer f7202a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ASN1OctetString f7203b;
    ASN1OctetString bp;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    DSTU4145BinaryField f7204f;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    ASN1Integer f7205n;
    BigInteger version;

    public DSTU4145ECBinary(ECDomainParameters eCDomainParameters) {
        this.version = BigInteger.valueOf(0L);
        ECCurve curve = eCDomainParameters.getCurve();
        if (!ECAlgorithms.isF2mCurve(curve)) {
            throw new IllegalArgumentException("only binary domain is possible");
        }
        int[] exponentsPresent = ((PolynomialExtensionField) curve.getField()).getMinimalPolynomial().getExponentsPresent();
        if (exponentsPresent.length == 3) {
            this.f7204f = new DSTU4145BinaryField(exponentsPresent[2], exponentsPresent[1]);
        } else {
            if (exponentsPresent.length != 5) {
                throw new IllegalArgumentException("curve must have a trinomial or pentanomial basis");
            }
            this.f7204f = new DSTU4145BinaryField(exponentsPresent[4], exponentsPresent[1], exponentsPresent[2], exponentsPresent[3]);
        }
        this.f7202a = new ASN1Integer(curve.getA().toBigInteger());
        this.f7203b = new DEROctetString(curve.getB().getEncoded());
        this.f7205n = new ASN1Integer(eCDomainParameters.getN());
        this.bp = new DEROctetString(DSTU4145PointEncoder.encodePoint(eCDomainParameters.getG()));
    }

    public static DSTU4145ECBinary getInstance(Object obj) {
        if (obj instanceof DSTU4145ECBinary) {
            return (DSTU4145ECBinary) obj;
        }
        if (obj != null) {
            return new DSTU4145ECBinary(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getA() {
        return this.f7202a.getValue();
    }

    public byte[] getB() {
        return Arrays.clone(this.f7203b.getOctets());
    }

    public DSTU4145BinaryField getField() {
        return this.f7204f;
    }

    public byte[] getG() {
        return Arrays.clone(this.bp.getOctets());
    }

    public BigInteger getN() {
        return this.f7205n.getValue();
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.version.compareTo(BigInteger.valueOf(0L)) != 0) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, new ASN1Integer(this.version)));
        }
        aSN1EncodableVector.add(this.f7204f);
        aSN1EncodableVector.add(this.f7202a);
        aSN1EncodableVector.add(this.f7203b);
        aSN1EncodableVector.add(this.f7205n);
        aSN1EncodableVector.add(this.bp);
        return new DERSequence(aSN1EncodableVector);
    }

    private DSTU4145ECBinary(ASN1Sequence aSN1Sequence) {
        this.version = BigInteger.valueOf(0L);
        int i2 = 0;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.getObjectAt(0);
            if (aSN1TaggedObject.isExplicit() && aSN1TaggedObject.getTagNo() == 0) {
                this.version = ASN1Integer.getInstance(aSN1TaggedObject.getLoadedObject()).getValue();
                i2 = 1;
            } else {
                throw new IllegalArgumentException("object parse error");
            }
        }
        this.f7204f = DSTU4145BinaryField.getInstance(aSN1Sequence.getObjectAt(i2));
        this.f7202a = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(i2 + 1));
        this.f7203b = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(i2 + 2));
        this.f7205n = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(i2 + 3));
        this.bp = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(i2 + 4));
    }
}
