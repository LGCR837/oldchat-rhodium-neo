package org.spongycastle.pqc.math.linearalgebra;

/* JADX INFO: loaded from: classes.dex */
public class GF2nPolynomial {
    private GF2nElement[] coeff;
    private int size;

    public GF2nPolynomial(int i2, GF2nElement gF2nElement) {
        this.size = i2;
        this.coeff = new GF2nElement[i2];
        for (int i3 = 0; i3 < this.size; i3++) {
            this.coeff[i3] = (GF2nElement) gF2nElement.clone();
        }
    }

    public final GF2nPolynomial add(GF2nPolynomial gF2nPolynomial) {
        GF2nPolynomial gF2nPolynomial2;
        int i2 = 0;
        if (size() >= gF2nPolynomial.size()) {
            gF2nPolynomial2 = new GF2nPolynomial(size());
            while (i2 < gF2nPolynomial.size()) {
                gF2nPolynomial2.coeff[i2] = (GF2nElement) this.coeff[i2].add(gF2nPolynomial.coeff[i2]);
                i2++;
            }
            while (i2 < size()) {
                gF2nPolynomial2.coeff[i2] = this.coeff[i2];
                i2++;
            }
        } else {
            gF2nPolynomial2 = new GF2nPolynomial(gF2nPolynomial.size());
            while (i2 < size()) {
                gF2nPolynomial2.coeff[i2] = (GF2nElement) this.coeff[i2].add(gF2nPolynomial.coeff[i2]);
                i2++;
            }
            while (i2 < gF2nPolynomial.size()) {
                gF2nPolynomial2.coeff[i2] = gF2nPolynomial.coeff[i2];
                i2++;
            }
        }
        return gF2nPolynomial2;
    }

    public final void assignZeroToElements() {
        for (int i2 = 0; i2 < this.size; i2++) {
            this.coeff[i2].assignZero();
        }
    }

    public final GF2nElement at(int i2) {
        return this.coeff[i2];
    }

    public final GF2nPolynomial[] divide(GF2nPolynomial gF2nPolynomial) {
        GF2nPolynomial[] gF2nPolynomialArr = new GF2nPolynomial[2];
        GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial(this);
        gF2nPolynomial2.shrink();
        int degree = gF2nPolynomial.getDegree();
        GF2nElement gF2nElement = (GF2nElement) gF2nPolynomial.coeff[degree].invert();
        if (gF2nPolynomial2.getDegree() < degree) {
            GF2nPolynomial gF2nPolynomial3 = new GF2nPolynomial(this);
            gF2nPolynomialArr[0] = gF2nPolynomial3;
            gF2nPolynomial3.assignZeroToElements();
            gF2nPolynomialArr[0].shrink();
            GF2nPolynomial gF2nPolynomial4 = new GF2nPolynomial(this);
            gF2nPolynomialArr[1] = gF2nPolynomial4;
            gF2nPolynomial4.shrink();
            return gF2nPolynomialArr;
        }
        GF2nPolynomial gF2nPolynomial5 = new GF2nPolynomial(this);
        gF2nPolynomialArr[0] = gF2nPolynomial5;
        gF2nPolynomial5.assignZeroToElements();
        int degree2 = gF2nPolynomial2.getDegree();
        while (true) {
            int i2 = degree2 - degree;
            if (i2 < 0) {
                gF2nPolynomialArr[1] = gF2nPolynomial2;
                gF2nPolynomialArr[0].shrink();
                return gF2nPolynomialArr;
            }
            GF2nElement gF2nElement2 = (GF2nElement) gF2nPolynomial2.coeff[gF2nPolynomial2.getDegree()].multiply(gF2nElement);
            GF2nPolynomial gF2nPolynomialScalarMultiply = gF2nPolynomial.scalarMultiply(gF2nElement2);
            gF2nPolynomialScalarMultiply.shiftThisLeft(i2);
            gF2nPolynomial2 = gF2nPolynomial2.add(gF2nPolynomialScalarMultiply);
            gF2nPolynomial2.shrink();
            gF2nPolynomialArr[0].coeff[i2] = (GF2nElement) gF2nElement2.clone();
            degree2 = gF2nPolynomial2.getDegree();
        }
    }

    public final void enlarge(int i2) {
        int i3 = this.size;
        if (i2 <= i3) {
            return;
        }
        GF2nElement[] gF2nElementArr = new GF2nElement[i2];
        System.arraycopy(this.coeff, 0, gF2nElementArr, 0, i3);
        GF2nField field = this.coeff[0].getField();
        GF2nElement gF2nElement = this.coeff[0];
        if (gF2nElement instanceof GF2nPolynomialElement) {
            for (int i4 = this.size; i4 < i2; i4++) {
                gF2nElementArr[i4] = GF2nPolynomialElement.ZERO((GF2nPolynomialField) field);
            }
        } else if (gF2nElement instanceof GF2nONBElement) {
            for (int i5 = this.size; i5 < i2; i5++) {
                gF2nElementArr[i5] = GF2nONBElement.ZERO((GF2nONBField) field);
            }
        }
        this.size = i2;
        this.coeff = gF2nElementArr;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2nPolynomial)) {
            return false;
        }
        GF2nPolynomial gF2nPolynomial = (GF2nPolynomial) obj;
        if (getDegree() != gF2nPolynomial.getDegree()) {
            return false;
        }
        for (int i2 = 0; i2 < this.size; i2++) {
            if (!this.coeff[i2].equals(gF2nPolynomial.coeff[i2])) {
                return false;
            }
        }
        return true;
    }

    public final GF2nPolynomial gcd(GF2nPolynomial gF2nPolynomial) {
        GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial(this);
        GF2nPolynomial gF2nPolynomial3 = new GF2nPolynomial(gF2nPolynomial);
        gF2nPolynomial2.shrink();
        gF2nPolynomial3.shrink();
        GF2nPolynomial gF2nPolynomial4 = gF2nPolynomial2;
        GF2nPolynomial gF2nPolynomialRemainder = gF2nPolynomial3;
        while (!gF2nPolynomialRemainder.isZero()) {
            GF2nPolynomial gF2nPolynomial5 = gF2nPolynomialRemainder;
            gF2nPolynomialRemainder = gF2nPolynomial4.remainder(gF2nPolynomialRemainder);
            gF2nPolynomial4 = gF2nPolynomial5;
        }
        return gF2nPolynomial4.scalarMultiply((GF2nElement) gF2nPolynomial4.coeff[gF2nPolynomial4.getDegree()].invert());
    }

    public final int getDegree() {
        for (int i2 = this.size - 1; i2 >= 0; i2--) {
            if (!this.coeff[i2].isZero()) {
                return i2;
            }
        }
        return -1;
    }

    public int hashCode() {
        return getDegree() + this.coeff.hashCode();
    }

    public final boolean isZero() {
        for (int i2 = 0; i2 < this.size; i2++) {
            GF2nElement gF2nElement = this.coeff[i2];
            if (gF2nElement != null && !gF2nElement.isZero()) {
                return false;
            }
        }
        return true;
    }

    public final GF2nPolynomial multiply(GF2nPolynomial gF2nPolynomial) {
        if (size() != gF2nPolynomial.size()) {
            throw new IllegalArgumentException("PolynomialGF2n.multiply: this and b must have the same size!");
        }
        GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial((r0 << 1) - 1);
        for (int i2 = 0; i2 < size(); i2++) {
            for (int i3 = 0; i3 < gF2nPolynomial.size(); i3++) {
                GF2nElement[] gF2nElementArr = gF2nPolynomial2.coeff;
                int i4 = i2 + i3;
                GF2nElement gF2nElement = gF2nElementArr[i4];
                if (gF2nElement == null) {
                    gF2nElementArr[i4] = (GF2nElement) this.coeff[i2].multiply(gF2nPolynomial.coeff[i3]);
                } else {
                    gF2nElementArr[i4] = (GF2nElement) gF2nElement.add(this.coeff[i2].multiply(gF2nPolynomial.coeff[i3]));
                }
            }
        }
        return gF2nPolynomial2;
    }

    public final GF2nPolynomial multiplyAndReduce(GF2nPolynomial gF2nPolynomial, GF2nPolynomial gF2nPolynomial2) {
        return multiply(gF2nPolynomial).reduce(gF2nPolynomial2);
    }

    public final GF2nPolynomial quotient(GF2nPolynomial gF2nPolynomial) {
        return divide(gF2nPolynomial)[0];
    }

    public final GF2nPolynomial reduce(GF2nPolynomial gF2nPolynomial) {
        return remainder(gF2nPolynomial);
    }

    public final GF2nPolynomial remainder(GF2nPolynomial gF2nPolynomial) {
        return divide(gF2nPolynomial)[1];
    }

    public final GF2nPolynomial scalarMultiply(GF2nElement gF2nElement) {
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(size());
        for (int i2 = 0; i2 < size(); i2++) {
            gF2nPolynomial.coeff[i2] = (GF2nElement) this.coeff[i2].multiply(gF2nElement);
        }
        return gF2nPolynomial;
    }

    public final void set(int i2, GF2nElement gF2nElement) {
        if (!(gF2nElement instanceof GF2nPolynomialElement) && !(gF2nElement instanceof GF2nONBElement)) {
            throw new IllegalArgumentException("PolynomialGF2n.set f must be an instance of either GF2nPolynomialElement or GF2nONBElement!");
        }
        this.coeff[i2] = (GF2nElement) gF2nElement.clone();
    }

    public final GF2nPolynomial shiftLeft(int i2) {
        if (i2 <= 0) {
            return new GF2nPolynomial(this);
        }
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(this.size + i2, this.coeff[0]);
        gF2nPolynomial.assignZeroToElements();
        for (int i3 = 0; i3 < this.size; i3++) {
            gF2nPolynomial.coeff[i3 + i2] = this.coeff[i3];
        }
        return gF2nPolynomial;
    }

    public final void shiftThisLeft(int i2) {
        if (i2 > 0) {
            int i3 = this.size;
            GF2nField field = this.coeff[0].getField();
            enlarge(this.size + i2);
            for (int i4 = i3 - 1; i4 >= 0; i4--) {
                GF2nElement[] gF2nElementArr = this.coeff;
                gF2nElementArr[i4 + i2] = gF2nElementArr[i4];
            }
            GF2nElement gF2nElement = this.coeff[0];
            if (gF2nElement instanceof GF2nPolynomialElement) {
                for (int i5 = i2 - 1; i5 >= 0; i5--) {
                    this.coeff[i5] = GF2nPolynomialElement.ZERO((GF2nPolynomialField) field);
                }
                return;
            }
            if (gF2nElement instanceof GF2nONBElement) {
                for (int i6 = i2 - 1; i6 >= 0; i6--) {
                    this.coeff[i6] = GF2nONBElement.ZERO((GF2nONBField) field);
                }
            }
        }
    }

    public final void shrink() {
        int i2 = this.size;
        do {
            i2--;
            if (!this.coeff[i2].isZero()) {
                break;
            }
        } while (i2 > 0);
        int i3 = i2 + 1;
        if (i3 < this.size) {
            GF2nElement[] gF2nElementArr = new GF2nElement[i3];
            System.arraycopy(this.coeff, 0, gF2nElementArr, 0, i3);
            this.coeff = gF2nElementArr;
            this.size = i3;
        }
    }

    public final int size() {
        return this.size;
    }

    private GF2nPolynomial(int i2) {
        this.size = i2;
        this.coeff = new GF2nElement[i2];
    }

    public GF2nPolynomial(GF2nPolynomial gF2nPolynomial) {
        int i2 = gF2nPolynomial.size;
        this.coeff = new GF2nElement[i2];
        this.size = i2;
        for (int i3 = 0; i3 < this.size; i3++) {
            this.coeff[i3] = (GF2nElement) gF2nPolynomial.coeff[i3].clone();
        }
    }

    public GF2nPolynomial(GF2Polynomial gF2Polynomial, GF2nField gF2nField) {
        int degree = gF2nField.getDegree() + 1;
        this.size = degree;
        this.coeff = new GF2nElement[degree];
        int i2 = 0;
        if (gF2nField instanceof GF2nONBField) {
            while (i2 < this.size) {
                if (gF2Polynomial.testBit(i2)) {
                    this.coeff[i2] = GF2nONBElement.ONE((GF2nONBField) gF2nField);
                } else {
                    this.coeff[i2] = GF2nONBElement.ZERO((GF2nONBField) gF2nField);
                }
                i2++;
            }
            return;
        }
        if (gF2nField instanceof GF2nPolynomialField) {
            while (i2 < this.size) {
                if (gF2Polynomial.testBit(i2)) {
                    this.coeff[i2] = GF2nPolynomialElement.ONE((GF2nPolynomialField) gF2nField);
                } else {
                    this.coeff[i2] = GF2nPolynomialElement.ZERO((GF2nPolynomialField) gF2nField);
                }
                i2++;
            }
            return;
        }
        throw new IllegalArgumentException("PolynomialGF2n(Bitstring, GF2nField): B1 must be an instance of GF2nONBField or GF2nPolynomialField!");
    }
}
