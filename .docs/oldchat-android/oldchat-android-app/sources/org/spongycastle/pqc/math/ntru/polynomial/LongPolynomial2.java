package org.spongycastle.pqc.math.ntru.polynomial;

import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class LongPolynomial2 {
    private long[] coeffs;
    private int numCoeffs;

    public LongPolynomial2(IntegerPolynomial integerPolynomial) {
        long j2;
        int length = integerPolynomial.coeffs.length;
        this.numCoeffs = length;
        this.coeffs = new long[(length + 1) / 2];
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.numCoeffs) {
            int i4 = i2 + 1;
            int i5 = integerPolynomial.coeffs[i2];
            while (i5 < 0) {
                i5 += 2048;
            }
            if (i4 < this.numCoeffs) {
                i2 += 2;
                j2 = integerPolynomial.coeffs[i4];
            } else {
                i2 = i4;
                j2 = 0;
            }
            while (j2 < 0) {
                j2 += 2048;
            }
            this.coeffs[i3] = ((long) i5) + (j2 << 24);
            i3++;
        }
    }

    private void add(LongPolynomial2 longPolynomial2) {
        long[] jArr = longPolynomial2.coeffs;
        int length = jArr.length;
        long[] jArr2 = this.coeffs;
        if (length > jArr2.length) {
            this.coeffs = Arrays.copyOf(jArr2, jArr.length);
        }
        int i2 = 0;
        while (true) {
            long[] jArr3 = longPolynomial2.coeffs;
            if (i2 >= jArr3.length) {
                return;
            }
            long[] jArr4 = this.coeffs;
            jArr4[i2] = (jArr4[i2] + jArr3[i2]) & 34342963199L;
            i2++;
        }
    }

    private LongPolynomial2 multRecursive(LongPolynomial2 longPolynomial2) {
        long[] jArr = this.coeffs;
        long[] jArr2 = longPolynomial2.coeffs;
        int length = jArr2.length;
        int i2 = 0;
        if (length <= 32) {
            int i3 = length * 2;
            LongPolynomial2 longPolynomial22 = new LongPolynomial2(new long[i3]);
            for (int i4 = 0; i4 < i3; i4++) {
                for (int iMax = Math.max(0, (i4 - length) + 1); iMax <= Math.min(i4, length - 1); iMax++) {
                    long j2 = jArr[i4 - iMax] * jArr2[iMax];
                    long[] jArr3 = longPolynomial22.coeffs;
                    jArr3[i4] = (jArr3[i4] + (j2 & ((j2 & 2047) + 34342961152L))) & 34342963199L;
                    int i5 = i4 + 1;
                    jArr3[i5] = (jArr3[i5] + ((j2 >>> 48) & 2047)) & 34342963199L;
                }
            }
            return longPolynomial22;
        }
        int i6 = length / 2;
        LongPolynomial2 longPolynomial23 = new LongPolynomial2(Arrays.copyOf(jArr, i6));
        LongPolynomial2 longPolynomial24 = new LongPolynomial2(Arrays.copyOfRange(jArr, i6, length));
        LongPolynomial2 longPolynomial25 = new LongPolynomial2(Arrays.copyOf(jArr2, i6));
        LongPolynomial2 longPolynomial26 = new LongPolynomial2(Arrays.copyOfRange(jArr2, i6, length));
        LongPolynomial2 longPolynomial27 = (LongPolynomial2) longPolynomial23.clone();
        longPolynomial27.add(longPolynomial24);
        LongPolynomial2 longPolynomial28 = (LongPolynomial2) longPolynomial25.clone();
        longPolynomial28.add(longPolynomial26);
        LongPolynomial2 longPolynomial2MultRecursive = longPolynomial23.multRecursive(longPolynomial25);
        LongPolynomial2 longPolynomial2MultRecursive2 = longPolynomial24.multRecursive(longPolynomial26);
        LongPolynomial2 longPolynomial2MultRecursive3 = longPolynomial27.multRecursive(longPolynomial28);
        longPolynomial2MultRecursive3.sub(longPolynomial2MultRecursive);
        longPolynomial2MultRecursive3.sub(longPolynomial2MultRecursive2);
        LongPolynomial2 longPolynomial29 = new LongPolynomial2(length * 2);
        int i7 = 0;
        while (true) {
            long[] jArr4 = longPolynomial2MultRecursive.coeffs;
            if (i7 >= jArr4.length) {
                break;
            }
            longPolynomial29.coeffs[i7] = jArr4[i7] & 34342963199L;
            i7++;
        }
        int i8 = 0;
        while (true) {
            long[] jArr5 = longPolynomial2MultRecursive3.coeffs;
            if (i8 >= jArr5.length) {
                break;
            }
            long[] jArr6 = longPolynomial29.coeffs;
            int i9 = i6 + i8;
            jArr6[i9] = (jArr6[i9] + jArr5[i8]) & 34342963199L;
            i8++;
        }
        while (true) {
            long[] jArr7 = longPolynomial2MultRecursive2.coeffs;
            if (i2 >= jArr7.length) {
                return longPolynomial29;
            }
            long[] jArr8 = longPolynomial29.coeffs;
            int i10 = (i6 * 2) + i2;
            jArr8[i10] = (jArr8[i10] + jArr7[i2]) & 34342963199L;
            i2++;
        }
    }

    private void sub(LongPolynomial2 longPolynomial2) {
        long[] jArr = longPolynomial2.coeffs;
        int length = jArr.length;
        long[] jArr2 = this.coeffs;
        if (length > jArr2.length) {
            this.coeffs = Arrays.copyOf(jArr2, jArr.length);
        }
        int i2 = 0;
        while (true) {
            long[] jArr3 = longPolynomial2.coeffs;
            if (i2 >= jArr3.length) {
                return;
            }
            long[] jArr4 = this.coeffs;
            jArr4[i2] = 34342963199L & ((jArr4[i2] + 140737496743936L) - jArr3[i2]);
            i2++;
        }
    }

    public Object clone() {
        LongPolynomial2 longPolynomial2 = new LongPolynomial2((long[]) this.coeffs.clone());
        longPolynomial2.numCoeffs = this.numCoeffs;
        return longPolynomial2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof LongPolynomial2) {
            return Arrays.areEqual(this.coeffs, ((LongPolynomial2) obj).coeffs);
        }
        return false;
    }

    public LongPolynomial2 mult(LongPolynomial2 longPolynomial2) {
        long[] jArr;
        long[] jArr2;
        int length = this.coeffs.length;
        if (longPolynomial2.coeffs.length != length || this.numCoeffs != longPolynomial2.numCoeffs) {
            throw new IllegalArgumentException("Number of coefficients must be the same");
        }
        LongPolynomial2 longPolynomial2MultRecursive = multRecursive(longPolynomial2);
        if (longPolynomial2MultRecursive.coeffs.length > length) {
            if (this.numCoeffs % 2 == 0) {
                int i2 = length;
                while (true) {
                    jArr2 = longPolynomial2MultRecursive.coeffs;
                    if (i2 >= jArr2.length) {
                        break;
                    }
                    int i3 = i2 - length;
                    jArr2[i3] = (jArr2[i3] + jArr2[i2]) & 34342963199L;
                    i2++;
                }
                longPolynomial2MultRecursive.coeffs = Arrays.copyOf(jArr2, length);
            } else {
                int i4 = length;
                while (true) {
                    jArr = longPolynomial2MultRecursive.coeffs;
                    if (i4 >= jArr.length) {
                        break;
                    }
                    int i5 = i4 - length;
                    long j2 = jArr[i5] + (jArr[i4 - 1] >> 24);
                    jArr[i5] = j2;
                    long j3 = j2 + ((2047 & jArr[i4]) << 24);
                    jArr[i5] = j3;
                    jArr[i5] = j3 & 34342963199L;
                    i4++;
                }
                long[] jArrCopyOf = Arrays.copyOf(jArr, length);
                longPolynomial2MultRecursive.coeffs = jArrCopyOf;
                int length2 = jArrCopyOf.length - 1;
                jArrCopyOf[length2] = jArrCopyOf[length2] & 2047;
            }
        }
        LongPolynomial2 longPolynomial22 = new LongPolynomial2(longPolynomial2MultRecursive.coeffs);
        longPolynomial22.numCoeffs = this.numCoeffs;
        return longPolynomial22;
    }

    public void mult2And(int i2) {
        long j2 = i2;
        long j3 = (j2 << 24) + j2;
        int i3 = 0;
        while (true) {
            long[] jArr = this.coeffs;
            if (i3 >= jArr.length) {
                return;
            }
            jArr[i3] = (jArr[i3] << 1) & j3;
            i3++;
        }
    }

    public void subAnd(LongPolynomial2 longPolynomial2, int i2) {
        long j2 = i2;
        long j3 = (j2 << 24) + j2;
        int i3 = 0;
        while (true) {
            long[] jArr = longPolynomial2.coeffs;
            if (i3 >= jArr.length) {
                return;
            }
            long[] jArr2 = this.coeffs;
            jArr2[i3] = ((jArr2[i3] + 140737496743936L) - jArr[i3]) & j3;
            i3++;
        }
    }

    public IntegerPolynomial toIntegerPolynomial() {
        int[] iArr = new int[this.numCoeffs];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            long[] jArr = this.coeffs;
            if (i2 >= jArr.length) {
                return new IntegerPolynomial(iArr);
            }
            int i4 = i3 + 1;
            long j2 = jArr[i2];
            iArr[i3] = (int) (j2 & 2047);
            if (i4 < this.numCoeffs) {
                i3 += 2;
                iArr[i4] = (int) ((j2 >> 24) & 2047);
            } else {
                i3 = i4;
            }
            i2++;
        }
    }

    private LongPolynomial2(long[] jArr) {
        this.coeffs = jArr;
    }

    private LongPolynomial2(int i2) {
        this.coeffs = new long[i2];
    }
}
