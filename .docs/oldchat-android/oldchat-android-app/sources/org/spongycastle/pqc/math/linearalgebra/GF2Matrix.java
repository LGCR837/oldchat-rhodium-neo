package org.spongycastle.pqc.math.linearalgebra;

import java.lang.reflect.Array;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class GF2Matrix extends Matrix {
    private int length;
    private int[][] matrix;

    public GF2Matrix(byte[] bArr) {
        if (bArr.length < 9) {
            throw new ArithmeticException("given array is not an encoded matrix over GF(2)");
        }
        this.numRows = LittleEndianConversions.OS2IP(bArr, 0);
        int iOS2IP = LittleEndianConversions.OS2IP(bArr, 4);
        this.numColumns = iOS2IP;
        int i2 = this.numRows;
        int i3 = ((iOS2IP + 7) >>> 3) * i2;
        if (i2 > 0) {
            int i4 = 8;
            if (i3 == bArr.length - 8) {
                int i5 = (iOS2IP + 31) >>> 5;
                this.length = i5;
                this.matrix = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i2, i5);
                int i6 = this.numColumns;
                int i7 = i6 >> 5;
                int i8 = i6 & 31;
                for (int i9 = 0; i9 < this.numRows; i9++) {
                    int i10 = 0;
                    while (i10 < i7) {
                        this.matrix[i9][i10] = LittleEndianConversions.OS2IP(bArr, i4);
                        i10++;
                        i4 += 4;
                    }
                    int i11 = 0;
                    while (i11 < i8) {
                        int[] iArr = this.matrix[i9];
                        iArr[i7] = ((bArr[i4] & 255) << i11) ^ iArr[i7];
                        i11 += 8;
                        i4++;
                    }
                }
                return;
            }
        }
        throw new ArithmeticException("given array is not an encoded matrix over GF(2)");
    }

    private static void addToRow(int[] iArr, int[] iArr2, int i2) {
        for (int length = iArr2.length - 1; length >= i2; length--) {
            iArr2[length] = iArr[length] ^ iArr2[length];
        }
    }

    private void assignRandomLowerTriangularMatrix(int i2, SecureRandom secureRandom) {
        this.numRows = i2;
        this.numColumns = i2;
        int i3 = (i2 + 31) >>> 5;
        this.length = i3;
        this.matrix = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i2, i3);
        for (int i4 = 0; i4 < this.numRows; i4++) {
            int i5 = i4 >>> 5;
            int i6 = i4 & 31;
            int i7 = 31 - i6;
            int i8 = 1 << i6;
            for (int i9 = 0; i9 < i5; i9++) {
                this.matrix[i4][i9] = secureRandom.nextInt();
            }
            this.matrix[i4][i5] = i8 | (secureRandom.nextInt() >>> i7);
            while (true) {
                i5++;
                if (i5 < this.length) {
                    this.matrix[i4][i5] = 0;
                }
            }
        }
    }

    private void assignRandomRegularMatrix(int i2, SecureRandom secureRandom) {
        this.numRows = i2;
        this.numColumns = i2;
        int i3 = (i2 + 31) >>> 5;
        this.length = i3;
        this.matrix = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i2, i3);
        GF2Matrix gF2Matrix = (GF2Matrix) new GF2Matrix(i2, Matrix.MATRIX_TYPE_RANDOM_LT, secureRandom).rightMultiply(new GF2Matrix(i2, Matrix.MATRIX_TYPE_RANDOM_UT, secureRandom));
        int[] vector = new Permutation(i2, secureRandom).getVector();
        for (int i4 = 0; i4 < i2; i4++) {
            System.arraycopy(gF2Matrix.matrix[i4], 0, this.matrix[vector[i4]], 0, this.length);
        }
    }

    private void assignRandomUpperTriangularMatrix(int i2, SecureRandom secureRandom) {
        int i3;
        this.numRows = i2;
        this.numColumns = i2;
        int i4 = (i2 + 31) >>> 5;
        this.length = i4;
        this.matrix = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i2, i4);
        int i5 = i2 & 31;
        int i6 = i5 == 0 ? -1 : (1 << i5) - 1;
        for (int i7 = 0; i7 < this.numRows; i7++) {
            int i8 = i7 >>> 5;
            int i9 = i7 & 31;
            for (int i10 = 0; i10 < i8; i10++) {
                this.matrix[i7][i10] = 0;
            }
            this.matrix[i7][i8] = (secureRandom.nextInt() | 1) << i9;
            while (true) {
                i8++;
                i3 = this.length;
                if (i8 < i3) {
                    this.matrix[i7][i8] = secureRandom.nextInt();
                }
            }
            int[] iArr = this.matrix[i7];
            int i11 = i3 - 1;
            iArr[i11] = iArr[i11] & i6;
        }
    }

    private void assignUnitMatrix(int i2) {
        this.numRows = i2;
        this.numColumns = i2;
        int i3 = (i2 + 31) >>> 5;
        this.length = i3;
        int[] iArr = {i2, i3};
        this.matrix = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, iArr);
        for (int i4 = 0; i4 < this.numRows; i4++) {
            for (int i5 = 0; i5 < this.length; i5++) {
                this.matrix[i4][i5] = 0;
            }
        }
        for (int i6 = 0; i6 < this.numRows; i6++) {
            this.matrix[i6][i6 >>> 5] = 1 << (i6 & 31);
        }
    }

    private void assignZeroMatrix(int i2, int i3) {
        this.numRows = i2;
        this.numColumns = i3;
        int i4 = (i3 + 31) >>> 5;
        this.length = i4;
        this.matrix = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i2, i4);
        for (int i5 = 0; i5 < this.numRows; i5++) {
            for (int i6 = 0; i6 < this.length; i6++) {
                this.matrix[i5][i6] = 0;
            }
        }
    }

    public static GF2Matrix[] createRandomRegularMatrixAndItsInverse(int i2, SecureRandom secureRandom) {
        int i3 = (i2 + 31) >> 5;
        GF2Matrix gF2Matrix = new GF2Matrix(i2, Matrix.MATRIX_TYPE_RANDOM_LT, secureRandom);
        GF2Matrix gF2Matrix2 = new GF2Matrix(i2, Matrix.MATRIX_TYPE_RANDOM_UT, secureRandom);
        GF2Matrix gF2Matrix3 = (GF2Matrix) gF2Matrix.rightMultiply(gF2Matrix2);
        Permutation permutation = new Permutation(i2, secureRandom);
        int[] vector = permutation.getVector();
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i2, i3);
        for (int i4 = 0; i4 < i2; i4++) {
            System.arraycopy(gF2Matrix3.matrix[vector[i4]], 0, iArr[i4], 0, i3);
        }
        GF2Matrix gF2Matrix4 = new GF2Matrix(i2, iArr);
        GF2Matrix gF2Matrix5 = new GF2Matrix(i2, 'I');
        int i5 = 0;
        while (i5 < i2) {
            int i6 = i5 >>> 5;
            int i7 = 1 << (i5 & 31);
            int i8 = i5 + 1;
            for (int i9 = i8; i9 < i2; i9++) {
                if ((gF2Matrix.matrix[i9][i6] & i7) != 0) {
                    for (int i10 = 0; i10 <= i6; i10++) {
                        int[][] iArr2 = gF2Matrix5.matrix;
                        int[] iArr3 = iArr2[i9];
                        iArr3[i10] = iArr3[i10] ^ iArr2[i5][i10];
                    }
                }
            }
            i5 = i8;
        }
        GF2Matrix gF2Matrix6 = new GF2Matrix(i2, 'I');
        for (int i11 = i2 - 1; i11 >= 0; i11--) {
            int i12 = i11 >>> 5;
            int i13 = 1 << (i11 & 31);
            for (int i14 = i11 - 1; i14 >= 0; i14--) {
                if ((gF2Matrix2.matrix[i14][i12] & i13) != 0) {
                    for (int i15 = i12; i15 < i3; i15++) {
                        int[][] iArr4 = gF2Matrix6.matrix;
                        int[] iArr5 = iArr4[i14];
                        iArr5[i15] = iArr4[i11][i15] ^ iArr5[i15];
                    }
                }
            }
        }
        return new GF2Matrix[]{gF2Matrix4, (GF2Matrix) gF2Matrix6.rightMultiply(gF2Matrix5.rightMultiply(permutation))};
    }

    private static void swapRows(int[][] iArr, int i2, int i3) {
        int[] iArr2 = iArr[i2];
        iArr[i2] = iArr[i3];
        iArr[i3] = iArr2;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Matrix
    public Matrix computeInverse() {
        int i2 = this.numRows;
        if (i2 != this.numColumns) {
            throw new ArithmeticException("Matrix is not invertible.");
        }
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i2, this.length);
        for (int i3 = this.numRows - 1; i3 >= 0; i3--) {
            iArr[i3] = IntUtils.clone(this.matrix[i3]);
        }
        int[][] iArr2 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, this.numRows, this.length);
        for (int i4 = this.numRows - 1; i4 >= 0; i4--) {
            iArr2[i4][i4 >> 5] = 1 << (i4 & 31);
        }
        for (int i5 = 0; i5 < this.numRows; i5++) {
            int i6 = i5 >> 5;
            int i7 = 1 << (i5 & 31);
            if ((iArr[i5][i6] & i7) == 0) {
                int i8 = i5 + 1;
                boolean z2 = false;
                while (i8 < this.numRows) {
                    if ((iArr[i8][i6] & i7) != 0) {
                        swapRows(iArr, i5, i8);
                        swapRows(iArr2, i5, i8);
                        i8 = this.numRows;
                        z2 = true;
                    }
                    i8++;
                }
                if (!z2) {
                    throw new ArithmeticException("Matrix is not invertible.");
                }
            }
            for (int i9 = this.numRows - 1; i9 >= 0; i9--) {
                if (i9 != i5) {
                    int[] iArr3 = iArr[i9];
                    if ((iArr3[i6] & i7) != 0) {
                        addToRow(iArr[i5], iArr3, i6);
                        addToRow(iArr2[i5], iArr2[i9], 0);
                    }
                }
            }
        }
        return new GF2Matrix(this.numColumns, iArr2);
    }

    public Matrix computeTranspose() {
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, this.numColumns, (this.numRows + 31) >>> 5);
        int i2 = 0;
        while (true) {
            int i3 = this.numRows;
            if (i2 >= i3) {
                return new GF2Matrix(i3, iArr);
            }
            for (int i4 = 0; i4 < this.numColumns; i4++) {
                int i5 = i2 >>> 5;
                int i6 = i2 & 31;
                if (((this.matrix[i2][i4 >>> 5] >>> (i4 & 31)) & 1) == 1) {
                    int[] iArr2 = iArr[i4];
                    iArr2[i5] = (1 << i6) | iArr2[i5];
                }
            }
            i2++;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GF2Matrix)) {
            return false;
        }
        GF2Matrix gF2Matrix = (GF2Matrix) obj;
        if (this.numRows != gF2Matrix.numRows || this.numColumns != gF2Matrix.numColumns || this.length != gF2Matrix.length) {
            return false;
        }
        for (int i2 = 0; i2 < this.numRows; i2++) {
            if (!IntUtils.equals(this.matrix[i2], gF2Matrix.matrix[i2])) {
                return false;
            }
        }
        return true;
    }

    public GF2Matrix extendLeftCompactForm() {
        int i2 = this.numColumns;
        int i3 = this.numRows;
        GF2Matrix gF2Matrix = new GF2Matrix(i3, i2 + i3);
        int i4 = this.numRows;
        int i5 = (i4 - 1) + this.numColumns;
        int i6 = i4 - 1;
        while (i6 >= 0) {
            System.arraycopy(this.matrix[i6], 0, gF2Matrix.matrix[i6], 0, this.length);
            int[] iArr = gF2Matrix.matrix[i6];
            int i7 = i5 >> 5;
            iArr[i7] = iArr[i7] | (1 << (i5 & 31));
            i6--;
            i5--;
        }
        return gF2Matrix;
    }

    public GF2Matrix extendRightCompactForm() {
        int i2;
        int i3 = this.numRows;
        GF2Matrix gF2Matrix = new GF2Matrix(i3, this.numColumns + i3);
        int i4 = this.numRows;
        int i5 = i4 >> 5;
        int i6 = i4 & 31;
        for (int i7 = i4 - 1; i7 >= 0; i7--) {
            int[] iArr = gF2Matrix.matrix[i7];
            int i8 = i7 >> 5;
            iArr[i8] = iArr[i8] | (1 << (i7 & 31));
            int i9 = 0;
            if (i6 != 0) {
                int i10 = i5;
                while (true) {
                    i2 = this.length;
                    if (i9 >= i2 - 1) {
                        break;
                    }
                    int i11 = this.matrix[i7][i9];
                    int[] iArr2 = gF2Matrix.matrix[i7];
                    int i12 = i10 + 1;
                    iArr2[i10] = iArr2[i10] | (i11 << i6);
                    iArr2[i12] = iArr2[i12] | (i11 >>> (32 - i6));
                    i9++;
                    i10 = i12;
                }
                int i13 = this.matrix[i7][i2 - 1];
                int[] iArr3 = gF2Matrix.matrix[i7];
                int i14 = i10 + 1;
                iArr3[i10] = iArr3[i10] | (i13 << i6);
                if (i14 < gF2Matrix.length) {
                    iArr3[i14] = iArr3[i14] | (i13 >>> (32 - i6));
                }
            } else {
                System.arraycopy(this.matrix[i7], 0, iArr, i5, this.length);
            }
        }
        return gF2Matrix;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Matrix
    public byte[] getEncoded() {
        int i2 = (this.numColumns + 7) >>> 3;
        int i3 = this.numRows;
        int i4 = 8;
        byte[] bArr = new byte[(i2 * i3) + 8];
        LittleEndianConversions.I2OSP(i3, bArr, 0);
        LittleEndianConversions.I2OSP(this.numColumns, bArr, 4);
        int i5 = this.numColumns;
        int i6 = i5 >>> 5;
        int i7 = i5 & 31;
        for (int i8 = 0; i8 < this.numRows; i8++) {
            int i9 = 0;
            while (i9 < i6) {
                LittleEndianConversions.I2OSP(this.matrix[i8][i9], bArr, i4);
                i9++;
                i4 += 4;
            }
            int i10 = 0;
            while (i10 < i7) {
                bArr[i4] = (byte) ((this.matrix[i8][i6] >>> i10) & 255);
                i10 += 8;
                i4++;
            }
        }
        return bArr;
    }

    public double getHammingWeight() {
        int i2 = this.numColumns & 31;
        int i3 = i2 == 0 ? this.length : this.length - 1;
        double d2 = 0.0d;
        double d3 = 0.0d;
        for (int i4 = 0; i4 < this.numRows; i4++) {
            for (int i5 = 0; i5 < i3; i5++) {
                int i6 = this.matrix[i4][i5];
                for (int i7 = 0; i7 < 32; i7++) {
                    double d4 = (i6 >>> i7) & 1;
                    Double.isNaN(d4);
                    d2 += d4;
                    d3 += 1.0d;
                }
            }
            int i8 = this.matrix[i4][this.length - 1];
            for (int i9 = 0; i9 < i2; i9++) {
                double d5 = (i8 >>> i9) & 1;
                Double.isNaN(d5);
                d2 += d5;
                d3 += 1.0d;
            }
        }
        return d2 / d3;
    }

    public int[][] getIntArray() {
        return this.matrix;
    }

    public GF2Matrix getLeftSubMatrix() {
        int i2 = this.numColumns;
        int i3 = this.numRows;
        if (i2 <= i3) {
            throw new ArithmeticException("empty submatrix");
        }
        int i4 = (i3 + 31) >> 5;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i3, i4);
        int i5 = this.numRows;
        int i6 = (1 << (i5 & 31)) - 1;
        if (i6 == 0) {
            i6 = -1;
        }
        for (int i7 = i5 - 1; i7 >= 0; i7--) {
            System.arraycopy(this.matrix[i7], 0, iArr[i7], 0, i4);
            int[] iArr2 = iArr[i7];
            int i8 = i4 - 1;
            iArr2[i8] = iArr2[i8] & i6;
        }
        return new GF2Matrix(this.numRows, iArr);
    }

    public int getLength() {
        return this.length;
    }

    public GF2Matrix getRightSubMatrix() {
        int i2;
        int i3 = this.numColumns;
        int i4 = this.numRows;
        if (i3 <= i4) {
            throw new ArithmeticException("empty submatrix");
        }
        int i5 = i4 >> 5;
        int i6 = i4 & 31;
        GF2Matrix gF2Matrix = new GF2Matrix(i4, i3 - i4);
        for (int i7 = this.numRows - 1; i7 >= 0; i7--) {
            int i8 = 0;
            if (i6 != 0) {
                int i9 = i5;
                while (true) {
                    i2 = gF2Matrix.length;
                    if (i8 >= i2 - 1) {
                        break;
                    }
                    int[] iArr = gF2Matrix.matrix[i7];
                    int[] iArr2 = this.matrix[i7];
                    int i10 = i9 + 1;
                    iArr[i8] = (iArr2[i9] >>> i6) | (iArr2[i10] << (32 - i6));
                    i8++;
                    i9 = i10;
                }
                int[] iArr3 = gF2Matrix.matrix[i7];
                int[] iArr4 = this.matrix[i7];
                int i11 = i9 + 1;
                iArr3[i2 - 1] = iArr4[i9] >>> i6;
                if (i11 < this.length) {
                    int i12 = i2 - 1;
                    iArr3[i12] = iArr3[i12] | (iArr4[i11] << (32 - i6));
                }
            } else {
                System.arraycopy(this.matrix[i7], i5, gF2Matrix.matrix[i7], 0, gF2Matrix.length);
            }
        }
        return gF2Matrix;
    }

    public int[] getRow(int i2) {
        return this.matrix[i2];
    }

    public int hashCode() {
        int iHashCode = (((this.numRows * 31) + this.numColumns) * 31) + this.length;
        for (int i2 = 0; i2 < this.numRows; i2++) {
            iHashCode = (iHashCode * 31) + this.matrix[i2].hashCode();
        }
        return iHashCode;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Matrix
    public boolean isZero() {
        for (int i2 = 0; i2 < this.numRows; i2++) {
            for (int i3 = 0; i3 < this.length; i3++) {
                if (this.matrix[i2][i3] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public Matrix leftMultiply(Permutation permutation) {
        int[] vector = permutation.getVector();
        int length = vector.length;
        int i2 = this.numRows;
        if (length != i2) {
            throw new ArithmeticException("length mismatch");
        }
        int[][] iArr = new int[i2][];
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            iArr[i3] = IntUtils.clone(this.matrix[vector[i3]]);
        }
        return new GF2Matrix(this.numRows, iArr);
    }

    public Vector leftMultiplyLeftCompactForm(Vector vector) {
        if (!(vector instanceof GF2Vector)) {
            throw new ArithmeticException("vector is not defined over GF(2)");
        }
        if (vector.length != this.numRows) {
            throw new ArithmeticException("length mismatch");
        }
        int[] vecArray = ((GF2Vector) vector).getVecArray();
        int i2 = this.numRows;
        int[] iArr = new int[((this.numColumns + i2) + 31) >>> 5];
        int i3 = i2 >>> 5;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = 1;
            do {
                if ((vecArray[i5] & i6) != 0) {
                    for (int i7 = 0; i7 < this.length; i7++) {
                        iArr[i7] = iArr[i7] ^ this.matrix[i4][i7];
                    }
                    int i8 = this.numColumns;
                    int i9 = (i8 + i4) >>> 5;
                    iArr[i9] = (1 << ((i8 + i4) & 31)) | iArr[i9];
                }
                i4++;
                i6 <<= 1;
            } while (i6 != 0);
        }
        int i10 = 1 << (this.numRows & 31);
        for (int i11 = 1; i11 != i10; i11 <<= 1) {
            if ((vecArray[i3] & i11) != 0) {
                for (int i12 = 0; i12 < this.length; i12++) {
                    iArr[i12] = iArr[i12] ^ this.matrix[i4][i12];
                }
                int i13 = this.numColumns;
                int i14 = (i13 + i4) >>> 5;
                iArr[i14] = (1 << ((i13 + i4) & 31)) | iArr[i14];
            }
            i4++;
        }
        return new GF2Vector(iArr, this.numRows + this.numColumns);
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Matrix
    public Matrix rightMultiply(Matrix matrix) {
        if (!(matrix instanceof GF2Matrix)) {
            throw new ArithmeticException("matrix is not defined over GF(2)");
        }
        if (matrix.numRows != this.numColumns) {
            throw new ArithmeticException("length mismatch");
        }
        GF2Matrix gF2Matrix = (GF2Matrix) matrix;
        GF2Matrix gF2Matrix2 = new GF2Matrix(this.numRows, matrix.numColumns);
        int i2 = this.numColumns & 31;
        int i3 = i2 == 0 ? this.length : this.length - 1;
        for (int i4 = 0; i4 < this.numRows; i4++) {
            int i5 = 0;
            for (int i6 = 0; i6 < i3; i6++) {
                int i7 = this.matrix[i4][i6];
                for (int i8 = 0; i8 < 32; i8++) {
                    if (((1 << i8) & i7) != 0) {
                        for (int i9 = 0; i9 < gF2Matrix.length; i9++) {
                            int[] iArr = gF2Matrix2.matrix[i4];
                            iArr[i9] = iArr[i9] ^ gF2Matrix.matrix[i5][i9];
                        }
                    }
                    i5++;
                }
            }
            int i10 = this.matrix[i4][this.length - 1];
            for (int i11 = 0; i11 < i2; i11++) {
                if (((1 << i11) & i10) != 0) {
                    for (int i12 = 0; i12 < gF2Matrix.length; i12++) {
                        int[] iArr2 = gF2Matrix2.matrix[i4];
                        iArr2[i12] = iArr2[i12] ^ gF2Matrix.matrix[i5][i12];
                    }
                }
                i5++;
            }
        }
        return gF2Matrix2;
    }

    public Vector rightMultiplyRightCompactForm(Vector vector) {
        int i2;
        if (!(vector instanceof GF2Vector)) {
            throw new ArithmeticException("vector is not defined over GF(2)");
        }
        if (vector.length != this.numColumns + this.numRows) {
            throw new ArithmeticException("length mismatch");
        }
        int[] vecArray = ((GF2Vector) vector).getVecArray();
        int i3 = this.numRows;
        int[] iArr = new int[(i3 + 31) >>> 5];
        int i4 = i3 >> 5;
        int i5 = i3 & 31;
        int i6 = 0;
        while (true) {
            int i7 = this.numRows;
            if (i6 >= i7) {
                return new GF2Vector(iArr, i7);
            }
            int i8 = i6 >> 5;
            int i9 = i6 & 31;
            int i10 = (vecArray[i8] >>> i9) & 1;
            if (i5 != 0) {
                int i11 = i4;
                int i12 = 0;
                while (true) {
                    i2 = this.length;
                    if (i12 >= i2 - 1) {
                        break;
                    }
                    int i13 = i11 + 1;
                    i10 ^= ((vecArray[i11] >>> i5) | (vecArray[i13] << (32 - i5))) & this.matrix[i6][i12];
                    i12++;
                    i11 = i13;
                }
                int i14 = i11 + 1;
                int i15 = vecArray[i11] >>> i5;
                if (i14 < vecArray.length) {
                    i15 |= vecArray[i14] << (32 - i5);
                }
                i10 ^= this.matrix[i6][i2 - 1] & i15;
            } else {
                int i16 = i4;
                int i17 = 0;
                while (i17 < this.length) {
                    i10 ^= vecArray[i16] & this.matrix[i6][i17];
                    i17++;
                    i16++;
                }
            }
            int i18 = 0;
            for (int i19 = 0; i19 < 32; i19++) {
                i18 ^= i10 & 1;
                i10 >>>= 1;
            }
            if (i18 == 1) {
                iArr[i8] = iArr[i8] | (1 << i9);
            }
            i6++;
        }
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Matrix
    public String toString() {
        int i2 = this.numColumns & 31;
        int i3 = i2 == 0 ? this.length : this.length - 1;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i4 = 0; i4 < this.numRows; i4++) {
            stringBuffer.append(i4 + ": ");
            for (int i5 = 0; i5 < i3; i5++) {
                int i6 = this.matrix[i4][i5];
                for (int i7 = 0; i7 < 32; i7++) {
                    if (((i6 >>> i7) & 1) == 0) {
                        stringBuffer.append('0');
                    } else {
                        stringBuffer.append('1');
                    }
                }
                stringBuffer.append(' ');
            }
            int i8 = this.matrix[i4][this.length - 1];
            for (int i9 = 0; i9 < i2; i9++) {
                if (((i8 >>> i9) & 1) == 0) {
                    stringBuffer.append('0');
                } else {
                    stringBuffer.append('1');
                }
            }
            stringBuffer.append('\n');
        }
        return stringBuffer.toString();
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Matrix
    public Vector leftMultiply(Vector vector) {
        if (vector instanceof GF2Vector) {
            if (vector.length == this.numRows) {
                int[] vecArray = ((GF2Vector) vector).getVecArray();
                int[] iArr = new int[this.length];
                int i2 = this.numRows;
                int i3 = i2 >> 5;
                int i4 = 1 << (i2 & 31);
                int i5 = 0;
                for (int i6 = 0; i6 < i3; i6++) {
                    int i7 = 1;
                    do {
                        if ((vecArray[i6] & i7) != 0) {
                            for (int i8 = 0; i8 < this.length; i8++) {
                                iArr[i8] = iArr[i8] ^ this.matrix[i5][i8];
                            }
                        }
                        i5++;
                        i7 <<= 1;
                    } while (i7 != 0);
                }
                for (int i9 = 1; i9 != i4; i9 <<= 1) {
                    if ((vecArray[i3] & i9) != 0) {
                        for (int i10 = 0; i10 < this.length; i10++) {
                            iArr[i10] = iArr[i10] ^ this.matrix[i5][i10];
                        }
                    }
                    i5++;
                }
                return new GF2Vector(iArr, this.numColumns);
            }
            throw new ArithmeticException("length mismatch");
        }
        throw new ArithmeticException("vector is not defined over GF(2)");
    }

    public GF2Matrix(int i2, int[][] iArr) {
        int[] iArr2 = iArr[0];
        if (iArr2.length == ((i2 + 31) >> 5)) {
            this.numColumns = i2;
            this.numRows = iArr.length;
            this.length = iArr2.length;
            int i3 = i2 & 31;
            int i4 = i3 == 0 ? -1 : (1 << i3) - 1;
            for (int i5 = 0; i5 < this.numRows; i5++) {
                int[] iArr3 = iArr[i5];
                int i6 = this.length - 1;
                iArr3[i6] = iArr3[i6] & i4;
            }
            this.matrix = iArr;
            return;
        }
        throw new ArithmeticException("Int array does not match given number of columns.");
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Matrix
    public Matrix rightMultiply(Permutation permutation) {
        int[] vector = permutation.getVector();
        int length = vector.length;
        int i2 = this.numColumns;
        if (length == i2) {
            GF2Matrix gF2Matrix = new GF2Matrix(this.numRows, i2);
            for (int i3 = this.numColumns - 1; i3 >= 0; i3--) {
                int i4 = i3 >>> 5;
                int i5 = i3 & 31;
                int i6 = vector[i3];
                int i7 = i6 >>> 5;
                int i8 = i6 & 31;
                for (int i9 = this.numRows - 1; i9 >= 0; i9--) {
                    int[] iArr = gF2Matrix.matrix[i9];
                    iArr[i4] = iArr[i4] | (((this.matrix[i9][i7] >>> i8) & 1) << i5);
                }
            }
            return gF2Matrix;
        }
        throw new ArithmeticException("length mismatch");
    }

    public GF2Matrix(int i2, char c2) {
        this(i2, c2, new SecureRandom());
    }

    public GF2Matrix(int i2, char c2, SecureRandom secureRandom) {
        if (i2 <= 0) {
            throw new ArithmeticException("Size of matrix is non-positive.");
        }
        if (c2 == 'I') {
            assignUnitMatrix(i2);
            return;
        }
        if (c2 == 'L') {
            assignRandomLowerTriangularMatrix(i2, secureRandom);
            return;
        }
        if (c2 == 'R') {
            assignRandomRegularMatrix(i2, secureRandom);
        } else if (c2 == 'U') {
            assignRandomUpperTriangularMatrix(i2, secureRandom);
        } else {
            if (c2 == 'Z') {
                assignZeroMatrix(i2, i2);
                return;
            }
            throw new ArithmeticException("Unknown matrix type.");
        }
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Matrix
    public Vector rightMultiply(Vector vector) {
        if (vector instanceof GF2Vector) {
            if (vector.length == this.numColumns) {
                int[] vecArray = ((GF2Vector) vector).getVecArray();
                int[] iArr = new int[(this.numRows + 31) >>> 5];
                int i2 = 0;
                while (true) {
                    int i3 = this.numRows;
                    if (i2 < i3) {
                        int i4 = 0;
                        for (int i5 = 0; i5 < this.length; i5++) {
                            i4 ^= this.matrix[i2][i5] & vecArray[i5];
                        }
                        int i6 = 0;
                        for (int i7 = 0; i7 < 32; i7++) {
                            i6 ^= (i4 >>> i7) & 1;
                        }
                        if (i6 == 1) {
                            int i8 = i2 >>> 5;
                            iArr[i8] = iArr[i8] | (1 << (i2 & 31));
                        }
                        i2++;
                    } else {
                        return new GF2Vector(iArr, i3);
                    }
                }
            } else {
                throw new ArithmeticException("length mismatch");
            }
        } else {
            throw new ArithmeticException("vector is not defined over GF(2)");
        }
    }

    public GF2Matrix(GF2Matrix gF2Matrix) {
        this.numColumns = gF2Matrix.getNumColumns();
        this.numRows = gF2Matrix.getNumRows();
        this.length = gF2Matrix.length;
        this.matrix = new int[gF2Matrix.matrix.length][];
        int i2 = 0;
        while (true) {
            int[][] iArr = this.matrix;
            if (i2 >= iArr.length) {
                return;
            }
            iArr[i2] = IntUtils.clone(gF2Matrix.matrix[i2]);
            i2++;
        }
    }

    private GF2Matrix(int i2, int i3) {
        if (i3 > 0 && i2 > 0) {
            assignZeroMatrix(i2, i3);
            return;
        }
        throw new ArithmeticException("size of matrix is non-positive");
    }
}
