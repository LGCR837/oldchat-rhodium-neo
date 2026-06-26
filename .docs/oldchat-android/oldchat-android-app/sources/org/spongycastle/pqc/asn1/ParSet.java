package org.spongycastle.pqc.asn1;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ParSet extends ASN1Object {
    private static final BigInteger ZERO = BigInteger.valueOf(0);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int[] f7598h;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int[] f7599k;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f7600t;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private int[] f7601w;

    private ParSet(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 4) {
            throw new IllegalArgumentException("sie of seqOfParams = " + aSN1Sequence.size());
        }
        this.f7600t = checkBigIntegerInIntRangeAndPositive(((ASN1Integer) aSN1Sequence.getObjectAt(0)).getValue());
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.getObjectAt(1);
        ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence.getObjectAt(2);
        ASN1Sequence aSN1Sequence4 = (ASN1Sequence) aSN1Sequence.getObjectAt(3);
        if (aSN1Sequence2.size() != this.f7600t || aSN1Sequence3.size() != this.f7600t || aSN1Sequence4.size() != this.f7600t) {
            throw new IllegalArgumentException("invalid size of sequences");
        }
        this.f7598h = new int[aSN1Sequence2.size()];
        this.f7601w = new int[aSN1Sequence3.size()];
        this.f7599k = new int[aSN1Sequence4.size()];
        for (int i2 = 0; i2 < this.f7600t; i2++) {
            this.f7598h[i2] = checkBigIntegerInIntRangeAndPositive(((ASN1Integer) aSN1Sequence2.getObjectAt(i2)).getValue());
            this.f7601w[i2] = checkBigIntegerInIntRangeAndPositive(((ASN1Integer) aSN1Sequence3.getObjectAt(i2)).getValue());
            this.f7599k[i2] = checkBigIntegerInIntRangeAndPositive(((ASN1Integer) aSN1Sequence4.getObjectAt(i2)).getValue());
        }
    }

    private static int checkBigIntegerInIntRangeAndPositive(BigInteger bigInteger) {
        if (bigInteger.compareTo(BigInteger.valueOf(2147483647L)) <= 0 && bigInteger.compareTo(ZERO) > 0) {
            return bigInteger.intValue();
        }
        throw new IllegalArgumentException("BigInteger not in Range: " + bigInteger.toString());
    }

    public static ParSet getInstance(Object obj) {
        if (obj instanceof ParSet) {
            return (ParSet) obj;
        }
        if (obj != null) {
            return new ParSet(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public int[] getH() {
        return Arrays.clone(this.f7598h);
    }

    public int[] getK() {
        return Arrays.clone(this.f7599k);
    }

    public int getT() {
        return this.f7600t;
    }

    public int[] getW() {
        return Arrays.clone(this.f7601w);
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
        int i2 = 0;
        while (true) {
            if (i2 >= this.f7598h.length) {
                ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
                aSN1EncodableVector4.add(new ASN1Integer(this.f7600t));
                aSN1EncodableVector4.add(new DERSequence(aSN1EncodableVector));
                aSN1EncodableVector4.add(new DERSequence(aSN1EncodableVector2));
                aSN1EncodableVector4.add(new DERSequence(aSN1EncodableVector3));
                return new DERSequence(aSN1EncodableVector4);
            }
            aSN1EncodableVector.add(new ASN1Integer(r4[i2]));
            aSN1EncodableVector2.add(new ASN1Integer(this.f7601w[i2]));
            aSN1EncodableVector3.add(new ASN1Integer(this.f7599k[i2]));
            i2++;
        }
    }

    public ParSet(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        this.f7600t = i2;
        this.f7598h = iArr;
        this.f7601w = iArr2;
        this.f7599k = iArr3;
    }
}
