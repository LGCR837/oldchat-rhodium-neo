package org.spongycastle.pqc.math.linearalgebra;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class GF2nONBField extends GF2nField {
    private static final int MAXLONG = 64;
    private int mBit;
    private int mLength;
    int[][] mMult;
    private int mType;

    public GF2nONBField(int i2, SecureRandom secureRandom) {
        super(secureRandom);
        if (i2 < 3) {
            throw new IllegalArgumentException("k must be at least 3");
        }
        this.mDegree = i2;
        int i3 = i2 / 64;
        this.mLength = i3;
        int i4 = i2 & 63;
        this.mBit = i4;
        if (i4 == 0) {
            this.mBit = 64;
        } else {
            this.mLength = i3 + 1;
        }
        computeType();
        if (this.mType >= 3) {
            throw new RuntimeException("\nThe type of this field is " + this.mType);
        }
        this.mMult = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, this.mDegree, 2);
        for (int i5 = 0; i5 < this.mDegree; i5++) {
            int[] iArr = this.mMult[i5];
            iArr[0] = -1;
            iArr[1] = -1;
        }
        computeMultMatrix();
        computeFieldPolynomial();
        this.fields = new java.util.Vector();
        this.matrices = new java.util.Vector();
    }

    private void computeMultMatrix() {
        int i2;
        int i3 = this.mType;
        if ((i3 & 7) == 0) {
            throw new RuntimeException("bisher nur fuer Gausssche Normalbasen implementiert");
        }
        int i4 = this.mDegree * i3;
        int i5 = i4 + 1;
        int[] iArr = new int[i5];
        int iElementOfOrder = i3 == 1 ? 1 : i3 == 2 ? i4 : elementOfOrder(i3, i5);
        int i6 = 0;
        int i7 = 1;
        while (true) {
            i2 = this.mType;
            if (i6 >= i2) {
                break;
            }
            int i8 = i7;
            for (int i9 = 0; i9 < this.mDegree; i9++) {
                iArr[i8] = i9;
                i8 = (i8 << 1) % i5;
                if (i8 < 0) {
                    i8 += i5;
                }
            }
            i7 = (i7 * iElementOfOrder) % i5;
            if (i7 < 0) {
                i7 += i5;
            }
            i6++;
        }
        if (i2 != 1) {
            if (i2 != 2) {
                throw new RuntimeException("only type 1 or type 2 implemented");
            }
            int i10 = 1;
            while (i10 < i4) {
                int i11 = i10 + 1;
                int[] iArr2 = this.mMult[iArr[i11]];
                if (iArr2[0] == -1) {
                    iArr2[0] = iArr[i5 - i10];
                } else {
                    iArr2[1] = iArr[i5 - i10];
                }
                i10 = i11;
            }
            return;
        }
        int i12 = 1;
        while (i12 < i4) {
            int i13 = i12 + 1;
            int[] iArr3 = this.mMult[iArr[i13]];
            if (iArr3[0] == -1) {
                iArr3[0] = iArr[i5 - i12];
            } else {
                iArr3[1] = iArr[i5 - i12];
            }
            i12 = i13;
        }
        int i14 = this.mDegree >> 1;
        for (int i15 = 1; i15 <= i14; i15++) {
            int[][] iArr4 = this.mMult;
            int i16 = i15 - 1;
            int[] iArr5 = iArr4[i16];
            if (iArr5[0] == -1) {
                iArr5[0] = (i14 + i15) - 1;
            } else {
                iArr5[1] = (i14 + i15) - 1;
            }
            int[] iArr6 = iArr4[(i14 + i15) - 1];
            if (iArr6[0] == -1) {
                iArr6[0] = i16;
            } else {
                iArr6[1] = i16;
            }
        }
    }

    private void computeType() {
        if ((this.mDegree & 7) == 0) {
            throw new RuntimeException("The extension degree is divisible by 8!");
        }
        this.mType = 1;
        int iGcd = 0;
        while (iGcd != 1) {
            int i2 = (this.mType * this.mDegree) + 1;
            if (IntegerFunctions.isPrime(i2)) {
                int iOrder = IntegerFunctions.order(2, i2);
                int i3 = this.mType;
                int i4 = this.mDegree;
                iGcd = IntegerFunctions.gcd((i3 * i4) / iOrder, i4);
            }
            this.mType++;
        }
        int i5 = this.mType - 1;
        this.mType = i5;
        if (i5 == 1) {
            int i6 = (this.mDegree << 1) + 1;
            if (IntegerFunctions.isPrime(i6)) {
                int iOrder2 = IntegerFunctions.order(2, i6);
                int i7 = this.mDegree;
                if (IntegerFunctions.gcd((i7 << 1) / iOrder2, i7) == 1) {
                    this.mType++;
                }
            }
        }
    }

    private int elementOfOrder(int i2, int i3) {
        Random random = new Random();
        int iNextInt = 0;
        while (iNextInt == 0) {
            int i4 = i3 - 1;
            iNextInt = random.nextInt() % i4;
            if (iNextInt < 0) {
                iNextInt += i4;
            }
        }
        int iOrder = IntegerFunctions.order(iNextInt, i3);
        while (true) {
            if (iOrder % i2 == 0 && iOrder != 0) {
                break;
            }
            while (iNextInt == 0) {
                int i5 = i3 - 1;
                iNextInt = random.nextInt() % i5;
                if (iNextInt < 0) {
                    iNextInt += i5;
                }
            }
            iOrder = IntegerFunctions.order(iNextInt, i3);
        }
        int i6 = iNextInt;
        for (int i7 = 2; i7 <= i2 / iOrder; i7++) {
            i6 *= iNextInt;
        }
        return i6;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GF2nField
    public void computeCOBMatrix(GF2nField gF2nField) {
        GF2nElement randomRoot;
        int i2 = this.mDegree;
        if (i2 != gF2nField.mDegree) {
            throw new IllegalArgumentException("GF2nField.computeCOBMatrix: B1 has a different degree and thus cannot be coverted to!");
        }
        GF2Polynomial[] gF2PolynomialArr = new GF2Polynomial[i2];
        for (int i3 = 0; i3 < this.mDegree; i3++) {
            gF2PolynomialArr[i3] = new GF2Polynomial(this.mDegree);
        }
        do {
            randomRoot = gF2nField.getRandomRoot(this.fieldPolynomial);
        } while (randomRoot.isZero());
        GF2nElement[] gF2nElementArr = new GF2nPolynomialElement[this.mDegree];
        gF2nElementArr[0] = (GF2nElement) randomRoot.clone();
        for (int i4 = 1; i4 < this.mDegree; i4++) {
            gF2nElementArr[i4] = gF2nElementArr[i4 - 1].square();
        }
        for (int i5 = 0; i5 < this.mDegree; i5++) {
            for (int i6 = 0; i6 < this.mDegree; i6++) {
                if (gF2nElementArr[i5].testBit(i6)) {
                    int i7 = this.mDegree;
                    gF2PolynomialArr[(i7 - i6) - 1].setBit((i7 - i5) - 1);
                }
            }
        }
        this.fields.addElement(gF2nField);
        this.matrices.addElement(gF2PolynomialArr);
        gF2nField.fields.addElement(this);
        gF2nField.matrices.addElement(invertMatrix(gF2PolynomialArr));
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GF2nField
    public void computeFieldPolynomial() {
        int i2 = this.mType;
        if (i2 == 1) {
            this.fieldPolynomial = new GF2Polynomial(this.mDegree + 1, "ALL");
            return;
        }
        if (i2 == 2) {
            GF2Polynomial gF2Polynomial = new GF2Polynomial(this.mDegree + 1, "ONE");
            GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.mDegree + 1, "X");
            gF2Polynomial2.addToThis(gF2Polynomial);
            GF2Polynomial gF2Polynomial3 = gF2Polynomial;
            GF2Polynomial gF2Polynomial4 = gF2Polynomial2;
            int i3 = 1;
            while (i3 < this.mDegree) {
                GF2Polynomial gF2PolynomialShiftLeft = gF2Polynomial4.shiftLeft();
                gF2PolynomialShiftLeft.addToThis(gF2Polynomial3);
                i3++;
                gF2Polynomial3 = gF2Polynomial4;
                gF2Polynomial4 = gF2PolynomialShiftLeft;
            }
            this.fieldPolynomial = gF2Polynomial4;
        }
    }

    public int getONBBit() {
        return this.mBit;
    }

    public int getONBLength() {
        return this.mLength;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.GF2nField
    public GF2nElement getRandomRoot(GF2Polynomial gF2Polynomial) {
        GF2nPolynomial gF2nPolynomialGcd;
        int degree;
        int degree2;
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(gF2Polynomial, this);
        int degree3 = gF2nPolynomial.getDegree();
        while (degree3 > 1) {
            while (true) {
                GF2nONBElement gF2nONBElement = new GF2nONBElement(this, this.random);
                GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial(2, GF2nONBElement.ZERO(this));
                gF2nPolynomial2.set(1, gF2nONBElement);
                GF2nPolynomial gF2nPolynomial3 = new GF2nPolynomial(gF2nPolynomial2);
                for (int i2 = 1; i2 <= this.mDegree - 1; i2++) {
                    gF2nPolynomial3 = gF2nPolynomial3.multiplyAndReduce(gF2nPolynomial3, gF2nPolynomial).add(gF2nPolynomial2);
                }
                gF2nPolynomialGcd = gF2nPolynomial3.gcd(gF2nPolynomial);
                degree = gF2nPolynomialGcd.getDegree();
                degree2 = gF2nPolynomial.getDegree();
                if (degree != 0 && degree != degree2) {
                    break;
                }
            }
            gF2nPolynomial = (degree << 1) > degree2 ? gF2nPolynomial.quotient(gF2nPolynomialGcd) : new GF2nPolynomial(gF2nPolynomialGcd);
            degree3 = gF2nPolynomial.getDegree();
        }
        return gF2nPolynomial.at(0);
    }

    public int[][] invMatrix(int[][] iArr) {
        int i2 = this.mDegree;
        int[] iArr2 = {i2, i2};
        Class cls = Integer.TYPE;
        int i3 = this.mDegree;
        int[][] iArr3 = (int[][]) Array.newInstance((Class<?>) cls, i3, i3);
        for (int i4 = 0; i4 < this.mDegree; i4++) {
            iArr3[i4][i4] = 1;
        }
        for (int i5 = 0; i5 < this.mDegree; i5++) {
            int i6 = i5;
            while (true) {
                int i7 = this.mDegree;
                if (i6 < i7) {
                    iArr[(i7 - 1) - i5][i6] = iArr[i5][i5];
                    i6++;
                }
            }
        }
        return null;
    }
}
