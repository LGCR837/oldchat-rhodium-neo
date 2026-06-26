package org.spongycastle.pqc.math.linearalgebra;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public class GF2mMatrix extends Matrix {
    protected GF2mField field;
    protected int[][] matrix;

    public GF2mMatrix(GF2mField gF2mField, byte[] bArr) {
        this.field = gF2mField;
        int i2 = 8;
        int i3 = 1;
        while (gF2mField.getDegree() > i2) {
            i3++;
            i2 += 8;
        }
        if (bArr.length < 5) {
            throw new IllegalArgumentException(" Error: given array is not encoded matrix over GF(2^m)");
        }
        int i4 = ((((bArr[3] & 255) << 24) ^ ((bArr[2] & 255) << 16)) ^ ((bArr[1] & 255) << 8)) ^ (bArr[0] & 255);
        this.numRows = i4;
        int i5 = i3 * i4;
        if (i4 > 0) {
            int i6 = 4;
            if ((bArr.length - 4) % i5 == 0) {
                int length = (bArr.length - 4) / i5;
                this.numColumns = length;
                this.matrix = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i4, length);
                for (int i7 = 0; i7 < this.numRows; i7++) {
                    for (int i8 = 0; i8 < this.numColumns; i8++) {
                        int i9 = 0;
                        while (i9 < i2) {
                            int[] iArr = this.matrix[i7];
                            iArr[i8] = iArr[i8] ^ ((bArr[i6] & 255) << i9);
                            i9 += 8;
                            i6++;
                        }
                        if (!this.field.isElementOfThisField(this.matrix[i7][i8])) {
                            throw new IllegalArgumentException(" Error: given array is not encoded matrix over GF(2^m)");
                        }
                    }
                }
                return;
            }
        }
        throw new IllegalArgumentException(" Error: given array is not encoded matrix over GF(2^m)");
    }

    private void addToRow(int[] iArr, int[] iArr2) {
        for (int length = iArr2.length - 1; length >= 0; length--) {
            iArr2[length] = this.field.add(iArr[length], iArr2[length]);
        }
    }

    private int[] multRowWithElement(int[] iArr, int i2) {
        int[] iArr2 = new int[iArr.length];
        for (int length = iArr.length - 1; length >= 0; length--) {
            iArr2[length] = this.field.mult(iArr[length], i2);
        }
        return iArr2;
    }

    private void multRowWithElementThis(int[] iArr, int i2) {
        for (int length = iArr.length - 1; length >= 0; length--) {
            iArr[length] = this.field.mult(iArr[length], i2);
        }
    }

    private static void swapColumns(int[][] iArr, int i2, int i3) {
        int[] iArr2 = iArr[i2];
        iArr[i2] = iArr[i3];
        iArr[i3] = iArr2;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Matrix
    public Matrix computeInverse() {
        int i2;
        int i3 = this.numRows;
        if (i3 != this.numColumns) {
            throw new ArithmeticException("Matrix is not invertible.");
        }
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i3, i3);
        for (int i4 = this.numRows - 1; i4 >= 0; i4--) {
            iArr[i4] = IntUtils.clone(this.matrix[i4]);
        }
        int i5 = this.numRows;
        int[][] iArr2 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i5, i5);
        for (int i6 = this.numRows - 1; i6 >= 0; i6--) {
            iArr2[i6][i6] = 1;
        }
        for (int i7 = 0; i7 < this.numRows; i7++) {
            if (iArr[i7][i7] == 0) {
                int i8 = i7 + 1;
                boolean z2 = false;
                while (i8 < this.numRows) {
                    if (iArr[i8][i7] != 0) {
                        swapColumns(iArr, i7, i8);
                        swapColumns(iArr2, i7, i8);
                        i8 = this.numRows;
                        z2 = true;
                    }
                    i8++;
                }
                if (!z2) {
                    throw new ArithmeticException("Matrix is not invertible.");
                }
            }
            int iInverse = this.field.inverse(iArr[i7][i7]);
            multRowWithElementThis(iArr[i7], iInverse);
            multRowWithElementThis(iArr2[i7], iInverse);
            for (int i9 = 0; i9 < this.numRows; i9++) {
                if (i9 != i7 && (i2 = iArr[i9][i7]) != 0) {
                    int[] iArrMultRowWithElement = multRowWithElement(iArr[i7], i2);
                    int[] iArrMultRowWithElement2 = multRowWithElement(iArr2[i7], i2);
                    addToRow(iArrMultRowWithElement, iArr[i9]);
                    addToRow(iArrMultRowWithElement2, iArr2[i9]);
                }
            }
        }
        return new GF2mMatrix(this.field, iArr2);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof GF2mMatrix)) {
            GF2mMatrix gF2mMatrix = (GF2mMatrix) obj;
            if (this.field.equals(gF2mMatrix.field)) {
                int i2 = gF2mMatrix.numRows;
                int i3 = this.numColumns;
                if (i2 == i3 && gF2mMatrix.numColumns == i3) {
                    for (int i4 = 0; i4 < this.numRows; i4++) {
                        for (int i5 = 0; i5 < this.numColumns; i5++) {
                            if (this.matrix[i4][i5] != gF2mMatrix.matrix[i4][i5]) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Matrix
    public byte[] getEncoded() {
        int i2 = 8;
        int i3 = 1;
        while (this.field.getDegree() > i2) {
            i3++;
            i2 += 8;
        }
        int i4 = this.numRows;
        int i5 = this.numColumns * i4 * i3;
        int i6 = 4;
        byte[] bArr = new byte[i5 + 4];
        bArr[0] = (byte) (i4 & 255);
        bArr[1] = (byte) ((i4 >>> 8) & 255);
        bArr[2] = (byte) ((i4 >>> 16) & 255);
        bArr[3] = (byte) ((i4 >>> 24) & 255);
        for (int i7 = 0; i7 < this.numRows; i7++) {
            for (int i8 = 0; i8 < this.numColumns; i8++) {
                int i9 = 0;
                while (i9 < i2) {
                    bArr[i6] = (byte) (this.matrix[i7][i8] >>> i9);
                    i9 += 8;
                    i6++;
                }
            }
        }
        return bArr;
    }

    public int hashCode() {
        int iHashCode = (((this.field.hashCode() * 31) + this.numRows) * 31) + this.numColumns;
        for (int i2 = 0; i2 < this.numRows; i2++) {
            for (int i3 = 0; i3 < this.numColumns; i3++) {
                iHashCode = (iHashCode * 31) + this.matrix[i2][i3];
            }
        }
        return iHashCode;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Matrix
    public boolean isZero() {
        for (int i2 = 0; i2 < this.numRows; i2++) {
            for (int i3 = 0; i3 < this.numColumns; i3++) {
                if (this.matrix[i2][i3] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Matrix
    public Vector leftMultiply(Vector vector) {
        throw new RuntimeException("Not implemented.");
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Matrix
    public Matrix rightMultiply(Matrix matrix) {
        throw new RuntimeException("Not implemented.");
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Matrix
    public String toString() {
        String str = this.numRows + " x " + this.numColumns + " Matrix over " + this.field.toString() + ": \n";
        for (int i2 = 0; i2 < this.numRows; i2++) {
            for (int i3 = 0; i3 < this.numColumns; i3++) {
                str = str + this.field.elementToStr(this.matrix[i2][i3]) + " : ";
            }
            str = str + "\n";
        }
        return str;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Matrix
    public Matrix rightMultiply(Permutation permutation) {
        throw new RuntimeException("Not implemented.");
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Matrix
    public Vector rightMultiply(Vector vector) {
        throw new RuntimeException("Not implemented.");
    }

    public GF2mMatrix(GF2mMatrix gF2mMatrix) {
        int i2 = gF2mMatrix.numRows;
        this.numRows = i2;
        this.numColumns = gF2mMatrix.numColumns;
        this.field = gF2mMatrix.field;
        this.matrix = new int[i2][];
        for (int i3 = 0; i3 < this.numRows; i3++) {
            this.matrix[i3] = IntUtils.clone(gF2mMatrix.matrix[i3]);
        }
    }

    public GF2mMatrix(GF2mField gF2mField, int[][] iArr) {
        this.field = gF2mField;
        this.matrix = iArr;
        this.numRows = iArr.length;
        this.numColumns = iArr[0].length;
    }
}
