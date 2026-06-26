package org.spongycastle.pqc.math.linearalgebra;

/* JADX INFO: loaded from: classes.dex */
public class PolynomialRingGF2m {
    private GF2mField field;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private PolynomialGF2mSmallM f7690p;
    protected PolynomialGF2mSmallM[] sqMatrix;
    protected PolynomialGF2mSmallM[] sqRootMatrix;

    public PolynomialRingGF2m(GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM) {
        this.field = gF2mField;
        this.f7690p = polynomialGF2mSmallM;
        computeSquaringMatrix();
        computeSquareRootMatrix();
    }

    private void computeSquareRootMatrix() {
        int coefficient;
        int degree = this.f7690p.getDegree();
        PolynomialGF2mSmallM[] polynomialGF2mSmallMArr = new PolynomialGF2mSmallM[degree];
        int i2 = degree - 1;
        for (int i3 = i2; i3 >= 0; i3--) {
            polynomialGF2mSmallMArr[i3] = new PolynomialGF2mSmallM(this.sqMatrix[i3]);
        }
        this.sqRootMatrix = new PolynomialGF2mSmallM[degree];
        while (i2 >= 0) {
            this.sqRootMatrix[i2] = new PolynomialGF2mSmallM(this.field, i2);
            i2--;
        }
        for (int i4 = 0; i4 < degree; i4++) {
            if (polynomialGF2mSmallMArr[i4].getCoefficient(i4) == 0) {
                int i5 = i4 + 1;
                boolean z2 = false;
                while (i5 < degree) {
                    if (polynomialGF2mSmallMArr[i5].getCoefficient(i4) != 0) {
                        swapColumns(polynomialGF2mSmallMArr, i4, i5);
                        swapColumns(this.sqRootMatrix, i4, i5);
                        i5 = degree;
                        z2 = true;
                    }
                    i5++;
                }
                if (!z2) {
                    throw new ArithmeticException("Squaring matrix is not invertible.");
                }
            }
            int iInverse = this.field.inverse(polynomialGF2mSmallMArr[i4].getCoefficient(i4));
            polynomialGF2mSmallMArr[i4].multThisWithElement(iInverse);
            this.sqRootMatrix[i4].multThisWithElement(iInverse);
            for (int i6 = 0; i6 < degree; i6++) {
                if (i6 != i4 && (coefficient = polynomialGF2mSmallMArr[i6].getCoefficient(i4)) != 0) {
                    PolynomialGF2mSmallM polynomialGF2mSmallMMultWithElement = polynomialGF2mSmallMArr[i4].multWithElement(coefficient);
                    PolynomialGF2mSmallM polynomialGF2mSmallMMultWithElement2 = this.sqRootMatrix[i4].multWithElement(coefficient);
                    polynomialGF2mSmallMArr[i6].addToThis(polynomialGF2mSmallMMultWithElement);
                    this.sqRootMatrix[i6].addToThis(polynomialGF2mSmallMMultWithElement2);
                }
            }
        }
    }

    private void computeSquaringMatrix() {
        int i2;
        int degree = this.f7690p.getDegree();
        this.sqMatrix = new PolynomialGF2mSmallM[degree];
        int i3 = 0;
        while (true) {
            i2 = degree >> 1;
            if (i3 >= i2) {
                break;
            }
            int i4 = i3 << 1;
            int[] iArr = new int[i4 + 1];
            iArr[i4] = 1;
            this.sqMatrix[i3] = new PolynomialGF2mSmallM(this.field, iArr);
            i3++;
        }
        while (i2 < degree) {
            int i5 = i2 << 1;
            int[] iArr2 = new int[i5 + 1];
            iArr2[i5] = 1;
            this.sqMatrix[i2] = new PolynomialGF2mSmallM(this.field, iArr2).mod(this.f7690p);
            i2++;
        }
    }

    private static void swapColumns(PolynomialGF2mSmallM[] polynomialGF2mSmallMArr, int i2, int i3) {
        PolynomialGF2mSmallM polynomialGF2mSmallM = polynomialGF2mSmallMArr[i2];
        polynomialGF2mSmallMArr[i2] = polynomialGF2mSmallMArr[i3];
        polynomialGF2mSmallMArr[i3] = polynomialGF2mSmallM;
    }

    public PolynomialGF2mSmallM[] getSquareRootMatrix() {
        return this.sqRootMatrix;
    }

    public PolynomialGF2mSmallM[] getSquaringMatrix() {
        return this.sqMatrix;
    }
}
