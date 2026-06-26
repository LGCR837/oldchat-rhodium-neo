package org.spongycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public abstract class GF2nField {
    protected GF2Polynomial fieldPolynomial;
    protected java.util.Vector fields;
    protected int mDegree;
    protected java.util.Vector matrices;
    protected final SecureRandom random;

    public GF2nField(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    public abstract void computeCOBMatrix(GF2nField gF2nField);

    public abstract void computeFieldPolynomial();

    public final GF2nElement convert(GF2nElement gF2nElement, GF2nField gF2nField) {
        if (gF2nField == this) {
            return (GF2nElement) gF2nElement.clone();
        }
        if (this.fieldPolynomial.equals(gF2nField.fieldPolynomial)) {
            return (GF2nElement) gF2nElement.clone();
        }
        if (this.mDegree != gF2nField.mDegree) {
            throw new RuntimeException("GF2nField.convert: B1 has a different degree and thus cannot be coverted to!");
        }
        int iIndexOf = this.fields.indexOf(gF2nField);
        if (iIndexOf == -1) {
            computeCOBMatrix(gF2nField);
            iIndexOf = this.fields.indexOf(gF2nField);
        }
        GF2Polynomial[] gF2PolynomialArr = (GF2Polynomial[]) this.matrices.elementAt(iIndexOf);
        GF2nElement gF2nElement2 = (GF2nElement) gF2nElement.clone();
        if (gF2nElement2 instanceof GF2nONBElement) {
            ((GF2nONBElement) gF2nElement2).reverseOrder();
        }
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.mDegree, gF2nElement2.toFlexiBigInt());
        gF2Polynomial.expandN(this.mDegree);
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.mDegree);
        for (int i2 = 0; i2 < this.mDegree; i2++) {
            if (gF2Polynomial.vectorMult(gF2PolynomialArr[i2])) {
                gF2Polynomial2.setBit((this.mDegree - 1) - i2);
            }
        }
        if (gF2nField instanceof GF2nPolynomialField) {
            return new GF2nPolynomialElement((GF2nPolynomialField) gF2nField, gF2Polynomial2);
        }
        if (!(gF2nField instanceof GF2nONBField)) {
            throw new RuntimeException("GF2nField.convert: B1 must be an instance of GF2nPolynomialField or GF2nONBField!");
        }
        GF2nONBElement gF2nONBElement = new GF2nONBElement((GF2nONBField) gF2nField, gF2Polynomial2.toFlexiBigInt());
        gF2nONBElement.reverseOrder();
        return gF2nONBElement;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2nField)) {
            return false;
        }
        GF2nField gF2nField = (GF2nField) obj;
        if (gF2nField.mDegree != this.mDegree || !this.fieldPolynomial.equals(gF2nField.fieldPolynomial)) {
            return false;
        }
        if (!(this instanceof GF2nPolynomialField) || (gF2nField instanceof GF2nPolynomialField)) {
            return !(this instanceof GF2nONBField) || (gF2nField instanceof GF2nONBField);
        }
        return false;
    }

    public final int getDegree() {
        return this.mDegree;
    }

    public final GF2Polynomial getFieldPolynomial() {
        if (this.fieldPolynomial == null) {
            computeFieldPolynomial();
        }
        return new GF2Polynomial(this.fieldPolynomial);
    }

    public abstract GF2nElement getRandomRoot(GF2Polynomial gF2Polynomial);

    public int hashCode() {
        return this.mDegree + this.fieldPolynomial.hashCode();
    }

    public final GF2Polynomial[] invertMatrix(GF2Polynomial[] gF2PolynomialArr) {
        GF2Polynomial[] gF2PolynomialArr2 = new GF2Polynomial[gF2PolynomialArr.length];
        GF2Polynomial[] gF2PolynomialArr3 = new GF2Polynomial[gF2PolynomialArr.length];
        int i2 = 0;
        for (int i3 = 0; i3 < this.mDegree; i3++) {
            try {
                gF2PolynomialArr2[i3] = new GF2Polynomial(gF2PolynomialArr[i3]);
                GF2Polynomial gF2Polynomial = new GF2Polynomial(this.mDegree);
                gF2PolynomialArr3[i3] = gF2Polynomial;
                gF2Polynomial.setBit((this.mDegree - 1) - i3);
            } catch (RuntimeException e2) {
                e2.printStackTrace();
            }
        }
        while (true) {
            int i4 = this.mDegree;
            if (i2 >= i4 - 1) {
                for (int i5 = i4 - 1; i5 > 0; i5--) {
                    for (int i6 = i5 - 1; i6 >= 0; i6--) {
                        if (gF2PolynomialArr2[i6].testBit((this.mDegree - 1) - i5)) {
                            gF2PolynomialArr2[i6].addToThis(gF2PolynomialArr2[i5]);
                            gF2PolynomialArr3[i6].addToThis(gF2PolynomialArr3[i5]);
                        }
                    }
                }
                return gF2PolynomialArr3;
            }
            int i7 = i2;
            while (true) {
                int i8 = this.mDegree;
                if (i7 >= i8 || gF2PolynomialArr2[i7].testBit((i8 - 1) - i2)) {
                    break;
                }
                i7++;
            }
            if (i7 >= this.mDegree) {
                throw new RuntimeException("GF2nField.invertMatrix: Matrix cannot be inverted!");
            }
            if (i2 != i7) {
                GF2Polynomial gF2Polynomial2 = gF2PolynomialArr2[i2];
                gF2PolynomialArr2[i2] = gF2PolynomialArr2[i7];
                gF2PolynomialArr2[i7] = gF2Polynomial2;
                GF2Polynomial gF2Polynomial3 = gF2PolynomialArr3[i2];
                gF2PolynomialArr3[i2] = gF2PolynomialArr3[i7];
                gF2PolynomialArr3[i7] = gF2Polynomial3;
            }
            int i9 = i2 + 1;
            int i10 = i9;
            while (true) {
                int i11 = this.mDegree;
                if (i10 < i11) {
                    if (gF2PolynomialArr2[i10].testBit((i11 - 1) - i2)) {
                        gF2PolynomialArr2[i10].addToThis(gF2PolynomialArr2[i2]);
                        gF2PolynomialArr3[i10].addToThis(gF2PolynomialArr3[i2]);
                    }
                    i10++;
                }
            }
            i2 = i9;
        }
    }
}
