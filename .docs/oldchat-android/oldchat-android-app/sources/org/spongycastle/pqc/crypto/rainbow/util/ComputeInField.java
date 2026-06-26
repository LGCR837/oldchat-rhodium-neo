package org.spongycastle.pqc.crypto.rainbow.util;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public class ComputeInField {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    private short[][] f7675A;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    short[] f7676x;

    private void computeZerosAbove() {
        for (int length = this.f7675A.length - 1; length > 0; length--) {
            for (int i2 = length - 1; i2 >= 0; i2--) {
                short[][] sArr = this.f7675A;
                short s2 = sArr[i2][length];
                short sInvElem = GF2Field.invElem(sArr[length][length]);
                if (sInvElem == 0) {
                    throw new RuntimeException("The matrix is not invertible");
                }
                int i3 = length;
                while (true) {
                    short[][] sArr2 = this.f7675A;
                    if (i3 < sArr2.length * 2) {
                        short sMultElem = GF2Field.multElem(s2, GF2Field.multElem(sArr2[length][i3], sInvElem));
                        short[] sArr3 = this.f7675A[i2];
                        sArr3[i3] = GF2Field.addElem(sArr3[i3], sMultElem);
                        i3++;
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0056, code lost:
    
        r0 = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void computeZerosUnder(boolean r10) {
        /*
            r9 = this;
            if (r10 == 0) goto L8
            short[][] r10 = r9.f7675A
            int r10 = r10.length
            int r10 = r10 * 2
            goto Ld
        L8:
            short[][] r10 = r9.f7675A
            int r10 = r10.length
            int r10 = r10 + 1
        Ld:
            r0 = 0
        Le:
            short[][] r1 = r9.f7675A
            int r1 = r1.length
            int r1 = r1 + (-1)
            if (r0 >= r1) goto L58
            int r1 = r0 + 1
            r2 = r1
        L18:
            short[][] r3 = r9.f7675A
            int r4 = r3.length
            if (r2 >= r4) goto L56
            r4 = r3[r2]
            short r4 = r4[r0]
            r3 = r3[r0]
            short r3 = r3[r0]
            short r3 = org.spongycastle.pqc.crypto.rainbow.util.GF2Field.invElem(r3)
            if (r3 == 0) goto L4e
            r5 = r0
        L2c:
            if (r5 >= r10) goto L4b
            short[][] r6 = r9.f7675A
            r6 = r6[r0]
            short r6 = r6[r5]
            short r6 = org.spongycastle.pqc.crypto.rainbow.util.GF2Field.multElem(r6, r3)
            short r6 = org.spongycastle.pqc.crypto.rainbow.util.GF2Field.multElem(r4, r6)
            short[][] r7 = r9.f7675A
            r7 = r7[r2]
            short r8 = r7[r5]
            short r6 = org.spongycastle.pqc.crypto.rainbow.util.GF2Field.addElem(r8, r6)
            r7[r5] = r6
            int r5 = r5 + 1
            goto L2c
        L4b:
            int r2 = r2 + 1
            goto L18
        L4e:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "Matrix not invertible! We have to choose another one!"
            r10.<init>(r0)
            throw r10
        L56:
            r0 = r1
            goto Le
        L58:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.pqc.crypto.rainbow.util.ComputeInField.computeZerosUnder(boolean):void");
    }

    private void substitute() {
        short sInvElem = GF2Field.invElem(this.f7675A[r0.length - 1][r0.length - 1]);
        if (sInvElem == 0) {
            throw new IllegalStateException("The equation system is not solvable");
        }
        short[] sArr = this.f7676x;
        short[][] sArr2 = this.f7675A;
        sArr[sArr2.length - 1] = GF2Field.multElem(sArr2[sArr2.length - 1][sArr2.length], sInvElem);
        for (int length = this.f7675A.length - 2; length >= 0; length--) {
            short[][] sArr3 = this.f7675A;
            short sAddElem = sArr3[length][sArr3.length];
            for (int length2 = sArr3.length - 1; length2 > length; length2--) {
                sAddElem = GF2Field.addElem(sAddElem, GF2Field.multElem(this.f7675A[length][length2], this.f7676x[length2]));
            }
            short sInvElem2 = GF2Field.invElem(this.f7675A[length][length]);
            if (sInvElem2 == 0) {
                throw new IllegalStateException("Not solvable equation system");
            }
            this.f7676x[length] = GF2Field.multElem(sAddElem, sInvElem2);
        }
    }

    public short[][] addSquareMatrix(short[][] sArr, short[][] sArr2) {
        if (sArr.length != sArr2.length || sArr[0].length != sArr2[0].length) {
            throw new RuntimeException("Addition is not possible!");
        }
        short[][] sArr3 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, sArr.length, sArr.length);
        for (int i2 = 0; i2 < sArr.length; i2++) {
            for (int i3 = 0; i3 < sArr2.length; i3++) {
                sArr3[i2][i3] = GF2Field.addElem(sArr[i2][i3], sArr2[i2][i3]);
            }
        }
        return sArr3;
    }

    public short[] addVect(short[] sArr, short[] sArr2) {
        if (sArr.length != sArr2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        int length = sArr.length;
        short[] sArr3 = new short[length];
        for (int i2 = 0; i2 < length; i2++) {
            sArr3[i2] = GF2Field.addElem(sArr[i2], sArr2[i2]);
        }
        return sArr3;
    }

    public short[][] inverse(short[][] sArr) {
        try {
            int i2 = 0;
            this.f7675A = (short[][]) Array.newInstance((Class<?>) Short.TYPE, sArr.length, sArr.length * 2);
            if (sArr.length != sArr[0].length) {
                throw new RuntimeException("The matrix is not invertible. Please choose another one!");
            }
            for (int i3 = 0; i3 < sArr.length; i3++) {
                for (int i4 = 0; i4 < sArr.length; i4++) {
                    this.f7675A[i3][i4] = sArr[i3][i4];
                }
                for (int length = sArr.length; length < sArr.length * 2; length++) {
                    this.f7675A[i3][length] = 0;
                }
                short[][] sArr2 = this.f7675A;
                sArr2[i3][sArr2.length + i3] = 1;
            }
            computeZerosUnder(true);
            int i5 = 0;
            while (true) {
                short[][] sArr3 = this.f7675A;
                if (i5 >= sArr3.length) {
                    break;
                }
                short sInvElem = GF2Field.invElem(sArr3[i5][i5]);
                int i6 = i5;
                while (true) {
                    short[][] sArr4 = this.f7675A;
                    if (i6 < sArr4.length * 2) {
                        short[] sArr5 = sArr4[i5];
                        sArr5[i6] = GF2Field.multElem(sArr5[i6], sInvElem);
                        i6++;
                    }
                }
                i5++;
            }
            computeZerosAbove();
            short[][] sArr6 = this.f7675A;
            short[][] sArr7 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, sArr6.length, sArr6.length);
            while (true) {
                short[][] sArr8 = this.f7675A;
                if (i2 >= sArr8.length) {
                    return sArr7;
                }
                int length2 = sArr8.length;
                while (true) {
                    short[][] sArr9 = this.f7675A;
                    if (length2 < sArr9.length * 2) {
                        sArr7[i2][length2 - sArr9.length] = sArr9[i2][length2];
                        length2++;
                    }
                }
                i2++;
            }
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public short[][] multMatrix(short s2, short[][] sArr) {
        short[][] sArr2 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, sArr.length, sArr[0].length);
        for (int i2 = 0; i2 < sArr.length; i2++) {
            for (int i3 = 0; i3 < sArr[0].length; i3++) {
                sArr2[i2][i3] = GF2Field.multElem(s2, sArr[i2][i3]);
            }
        }
        return sArr2;
    }

    public short[] multVect(short s2, short[] sArr) {
        int length = sArr.length;
        short[] sArr2 = new short[length];
        for (int i2 = 0; i2 < length; i2++) {
            sArr2[i2] = GF2Field.multElem(s2, sArr[i2]);
        }
        return sArr2;
    }

    public short[][] multVects(short[] sArr, short[] sArr2) {
        if (sArr.length != sArr2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        short[][] sArr3 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, sArr.length, sArr2.length);
        for (int i2 = 0; i2 < sArr.length; i2++) {
            for (int i3 = 0; i3 < sArr2.length; i3++) {
                sArr3[i2][i3] = GF2Field.multElem(sArr[i2], sArr2[i3]);
            }
        }
        return sArr3;
    }

    public short[][] multiplyMatrix(short[][] sArr, short[][] sArr2) {
        if (sArr[0].length != sArr2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        this.f7675A = (short[][]) Array.newInstance((Class<?>) Short.TYPE, sArr.length, sArr2[0].length);
        for (int i2 = 0; i2 < sArr.length; i2++) {
            for (int i3 = 0; i3 < sArr2.length; i3++) {
                for (int i4 = 0; i4 < sArr2[0].length; i4++) {
                    short sMultElem = GF2Field.multElem(sArr[i2][i3], sArr2[i3][i4]);
                    short[] sArr3 = this.f7675A[i2];
                    sArr3[i4] = GF2Field.addElem(sArr3[i4], sMultElem);
                }
            }
        }
        return this.f7675A;
    }

    public short[] solveEquation(short[][] sArr, short[] sArr2) {
        if (sArr.length != sArr2.length) {
            return null;
        }
        try {
            this.f7675A = (short[][]) Array.newInstance((Class<?>) Short.TYPE, sArr.length, sArr.length + 1);
            this.f7676x = new short[sArr.length];
            for (int i2 = 0; i2 < sArr.length; i2++) {
                for (int i3 = 0; i3 < sArr[0].length; i3++) {
                    this.f7675A[i2][i3] = sArr[i2][i3];
                }
            }
            for (int i4 = 0; i4 < sArr2.length; i4++) {
                short[] sArr3 = this.f7675A[i4];
                sArr3[sArr2.length] = GF2Field.addElem(sArr2[i4], sArr3[sArr2.length]);
            }
            computeZerosUnder(false);
            substitute();
            return this.f7676x;
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public short[] multiplyMatrix(short[][] sArr, short[] sArr2) {
        if (sArr[0].length == sArr2.length) {
            short[] sArr3 = new short[sArr.length];
            for (int i2 = 0; i2 < sArr.length; i2++) {
                for (int i3 = 0; i3 < sArr2.length; i3++) {
                    sArr3[i2] = GF2Field.addElem(sArr3[i2], GF2Field.multElem(sArr[i2][i3], sArr2[i3]));
                }
            }
            return sArr3;
        }
        throw new RuntimeException("Multiplication is not possible!");
    }
}
