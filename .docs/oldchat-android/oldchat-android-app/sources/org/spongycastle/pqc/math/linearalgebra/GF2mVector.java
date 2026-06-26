package org.spongycastle.pqc.math.linearalgebra;

/* JADX INFO: loaded from: classes.dex */
public class GF2mVector extends Vector {
    private GF2mField field;
    private int[] vector;

    public GF2mVector(GF2mField gF2mField, byte[] bArr) {
        this.field = new GF2mField(gF2mField);
        int i2 = 8;
        int i3 = 1;
        while (gF2mField.getDegree() > i2) {
            i3++;
            i2 += 8;
        }
        if (bArr.length % i3 != 0) {
            throw new IllegalArgumentException("Byte array is not an encoded vector over the given finite field.");
        }
        int length = bArr.length / i3;
        this.length = length;
        this.vector = new int[length];
        int i4 = 0;
        for (int i5 = 0; i5 < this.vector.length; i5++) {
            int i6 = 0;
            while (i6 < i2) {
                int[] iArr = this.vector;
                iArr[i5] = ((bArr[i4] & 255) << i6) | iArr[i5];
                i6 += 8;
                i4++;
            }
            if (!gF2mField.isElementOfThisField(this.vector[i5])) {
                throw new IllegalArgumentException("Byte array is not an encoded vector over the given finite field.");
            }
        }
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Vector
    public Vector add(Vector vector) {
        throw new RuntimeException("not implemented");
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Vector
    public boolean equals(Object obj) {
        if (!(obj instanceof GF2mVector)) {
            return false;
        }
        GF2mVector gF2mVector = (GF2mVector) obj;
        if (this.field.equals(gF2mVector.field)) {
            return IntUtils.equals(this.vector, gF2mVector.vector);
        }
        return false;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Vector
    public byte[] getEncoded() {
        int i2 = 8;
        int i3 = 1;
        while (this.field.getDegree() > i2) {
            i3++;
            i2 += 8;
        }
        byte[] bArr = new byte[this.vector.length * i3];
        int i4 = 0;
        for (int i5 = 0; i5 < this.vector.length; i5++) {
            int i6 = 0;
            while (i6 < i2) {
                bArr[i4] = (byte) (this.vector[i5] >>> i6);
                i6 += 8;
                i4++;
            }
        }
        return bArr;
    }

    public GF2mField getField() {
        return this.field;
    }

    public int[] getIntArrayForm() {
        return IntUtils.clone(this.vector);
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Vector
    public int hashCode() {
        return (this.field.hashCode() * 31) + this.vector.hashCode();
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Vector
    public boolean isZero() {
        for (int length = this.vector.length - 1; length >= 0; length--) {
            if (this.vector[length] != 0) {
                return false;
            }
        }
        return true;
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Vector
    public Vector multiply(Permutation permutation) {
        int[] vector = permutation.getVector();
        int i2 = this.length;
        if (i2 != vector.length) {
            throw new ArithmeticException("permutation size and vector size mismatch");
        }
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < vector.length; i3++) {
            iArr[i3] = this.vector[vector[i3]];
        }
        return new GF2mVector(this.field, iArr);
    }

    @Override // org.spongycastle.pqc.math.linearalgebra.Vector
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < this.vector.length; i2++) {
            for (int i3 = 0; i3 < this.field.getDegree(); i3++) {
                if (((1 << (i3 & 31)) & this.vector[i2]) != 0) {
                    stringBuffer.append('1');
                } else {
                    stringBuffer.append('0');
                }
            }
            stringBuffer.append(' ');
        }
        return stringBuffer.toString();
    }

    public GF2mVector(GF2mField gF2mField, int[] iArr) {
        this.field = gF2mField;
        this.length = iArr.length;
        for (int length = iArr.length - 1; length >= 0; length--) {
            if (!gF2mField.isElementOfThisField(iArr[length])) {
                throw new ArithmeticException("Element array is not specified over the given finite field.");
            }
        }
        this.vector = IntUtils.clone(iArr);
    }

    public GF2mVector(GF2mVector gF2mVector) {
        this.field = new GF2mField(gF2mVector.field);
        this.length = gF2mVector.length;
        this.vector = IntUtils.clone(gF2mVector.vector);
    }
}
