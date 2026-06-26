package org.spongycastle.pqc.math.ntru.polynomial;

import java.math.BigDecimal;

/* JADX INFO: loaded from: classes.dex */
public class BigDecimalPolynomial {
    BigDecimal[] coeffs;
    private static final BigDecimal ZERO = new BigDecimal("0");
    private static final BigDecimal ONE_HALF = new BigDecimal("0.5");

    public BigDecimalPolynomial(int i2) {
        this.coeffs = new BigDecimal[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            this.coeffs[i3] = ZERO;
        }
    }

    private BigDecimal[] copyOf(BigDecimal[] bigDecimalArr, int i2) {
        BigDecimal[] bigDecimalArr2 = new BigDecimal[i2];
        if (bigDecimalArr.length < i2) {
            i2 = bigDecimalArr.length;
        }
        System.arraycopy(bigDecimalArr, 0, bigDecimalArr2, 0, i2);
        return bigDecimalArr2;
    }

    private BigDecimal[] copyOfRange(BigDecimal[] bigDecimalArr, int i2, int i3) {
        int length = i3 - i2;
        BigDecimal[] bigDecimalArr2 = new BigDecimal[length];
        if (bigDecimalArr.length - i2 < length) {
            length = bigDecimalArr.length - i2;
        }
        System.arraycopy(bigDecimalArr, i2, bigDecimalArr2, 0, length);
        return bigDecimalArr2;
    }

    private BigDecimalPolynomial multRecursive(BigDecimalPolynomial bigDecimalPolynomial) {
        BigDecimal[] bigDecimalArr = this.coeffs;
        BigDecimal[] bigDecimalArr2 = bigDecimalPolynomial.coeffs;
        int length = bigDecimalArr2.length;
        int i2 = 0;
        if (length <= 1) {
            BigDecimal[] bigDecimalArr3 = (BigDecimal[]) bigDecimalArr.clone();
            for (int i3 = 0; i3 < this.coeffs.length; i3++) {
                bigDecimalArr3[i3] = bigDecimalArr3[i3].multiply(bigDecimalPolynomial.coeffs[0]);
            }
            return new BigDecimalPolynomial(bigDecimalArr3);
        }
        int i4 = length / 2;
        BigDecimalPolynomial bigDecimalPolynomial2 = new BigDecimalPolynomial(copyOf(bigDecimalArr, i4));
        BigDecimalPolynomial bigDecimalPolynomial3 = new BigDecimalPolynomial(copyOfRange(bigDecimalArr, i4, length));
        BigDecimalPolynomial bigDecimalPolynomial4 = new BigDecimalPolynomial(copyOf(bigDecimalArr2, i4));
        BigDecimalPolynomial bigDecimalPolynomial5 = new BigDecimalPolynomial(copyOfRange(bigDecimalArr2, i4, length));
        BigDecimalPolynomial bigDecimalPolynomial6 = (BigDecimalPolynomial) bigDecimalPolynomial2.clone();
        bigDecimalPolynomial6.add(bigDecimalPolynomial3);
        BigDecimalPolynomial bigDecimalPolynomial7 = (BigDecimalPolynomial) bigDecimalPolynomial4.clone();
        bigDecimalPolynomial7.add(bigDecimalPolynomial5);
        BigDecimalPolynomial bigDecimalPolynomialMultRecursive = bigDecimalPolynomial2.multRecursive(bigDecimalPolynomial4);
        BigDecimalPolynomial bigDecimalPolynomialMultRecursive2 = bigDecimalPolynomial3.multRecursive(bigDecimalPolynomial5);
        BigDecimalPolynomial bigDecimalPolynomialMultRecursive3 = bigDecimalPolynomial6.multRecursive(bigDecimalPolynomial7);
        bigDecimalPolynomialMultRecursive3.sub(bigDecimalPolynomialMultRecursive);
        bigDecimalPolynomialMultRecursive3.sub(bigDecimalPolynomialMultRecursive2);
        BigDecimalPolynomial bigDecimalPolynomial8 = new BigDecimalPolynomial((length * 2) - 1);
        int i5 = 0;
        while (true) {
            BigDecimal[] bigDecimalArr4 = bigDecimalPolynomialMultRecursive.coeffs;
            if (i5 >= bigDecimalArr4.length) {
                break;
            }
            bigDecimalPolynomial8.coeffs[i5] = bigDecimalArr4[i5];
            i5++;
        }
        int i6 = 0;
        while (true) {
            BigDecimal[] bigDecimalArr5 = bigDecimalPolynomialMultRecursive3.coeffs;
            if (i6 >= bigDecimalArr5.length) {
                break;
            }
            BigDecimal[] bigDecimalArr6 = bigDecimalPolynomial8.coeffs;
            int i7 = i4 + i6;
            bigDecimalArr6[i7] = bigDecimalArr6[i7].add(bigDecimalArr5[i6]);
            i6++;
        }
        while (true) {
            BigDecimal[] bigDecimalArr7 = bigDecimalPolynomialMultRecursive2.coeffs;
            if (i2 >= bigDecimalArr7.length) {
                return bigDecimalPolynomial8;
            }
            BigDecimal[] bigDecimalArr8 = bigDecimalPolynomial8.coeffs;
            int i8 = (i4 * 2) + i2;
            bigDecimalArr8[i8] = bigDecimalArr8[i8].add(bigDecimalArr7[i2]);
            i2++;
        }
    }

    public void add(BigDecimalPolynomial bigDecimalPolynomial) {
        BigDecimal[] bigDecimalArr = bigDecimalPolynomial.coeffs;
        int length = bigDecimalArr.length;
        BigDecimal[] bigDecimalArr2 = this.coeffs;
        if (length > bigDecimalArr2.length) {
            int length2 = bigDecimalArr2.length;
            this.coeffs = copyOf(bigDecimalArr2, bigDecimalArr.length);
            while (true) {
                BigDecimal[] bigDecimalArr3 = this.coeffs;
                if (length2 >= bigDecimalArr3.length) {
                    break;
                }
                bigDecimalArr3[length2] = ZERO;
                length2++;
            }
        }
        int i2 = 0;
        while (true) {
            BigDecimal[] bigDecimalArr4 = bigDecimalPolynomial.coeffs;
            if (i2 >= bigDecimalArr4.length) {
                return;
            }
            BigDecimal[] bigDecimalArr5 = this.coeffs;
            bigDecimalArr5[i2] = bigDecimalArr5[i2].add(bigDecimalArr4[i2]);
            i2++;
        }
    }

    public Object clone() {
        return new BigDecimalPolynomial((BigDecimal[]) this.coeffs.clone());
    }

    public BigDecimal[] getCoeffs() {
        BigDecimal[] bigDecimalArr = this.coeffs;
        BigDecimal[] bigDecimalArr2 = new BigDecimal[bigDecimalArr.length];
        System.arraycopy(bigDecimalArr, 0, bigDecimalArr2, 0, bigDecimalArr.length);
        return bigDecimalArr2;
    }

    public void halve() {
        int i2 = 0;
        while (true) {
            BigDecimal[] bigDecimalArr = this.coeffs;
            if (i2 >= bigDecimalArr.length) {
                return;
            }
            bigDecimalArr[i2] = bigDecimalArr[i2].multiply(ONE_HALF);
            i2++;
        }
    }

    public BigDecimalPolynomial mult(BigIntPolynomial bigIntPolynomial) {
        return mult(new BigDecimalPolynomial(bigIntPolynomial));
    }

    public BigIntPolynomial round() {
        int length = this.coeffs.length;
        BigIntPolynomial bigIntPolynomial = new BigIntPolynomial(length);
        for (int i2 = 0; i2 < length; i2++) {
            bigIntPolynomial.coeffs[i2] = this.coeffs[i2].setScale(0, 6).toBigInteger();
        }
        return bigIntPolynomial;
    }

    public void sub(BigDecimalPolynomial bigDecimalPolynomial) {
        BigDecimal[] bigDecimalArr = bigDecimalPolynomial.coeffs;
        int length = bigDecimalArr.length;
        BigDecimal[] bigDecimalArr2 = this.coeffs;
        if (length > bigDecimalArr2.length) {
            int length2 = bigDecimalArr2.length;
            this.coeffs = copyOf(bigDecimalArr2, bigDecimalArr.length);
            while (true) {
                BigDecimal[] bigDecimalArr3 = this.coeffs;
                if (length2 >= bigDecimalArr3.length) {
                    break;
                }
                bigDecimalArr3[length2] = ZERO;
                length2++;
            }
        }
        int i2 = 0;
        while (true) {
            BigDecimal[] bigDecimalArr4 = bigDecimalPolynomial.coeffs;
            if (i2 >= bigDecimalArr4.length) {
                return;
            }
            BigDecimal[] bigDecimalArr5 = this.coeffs;
            bigDecimalArr5[i2] = bigDecimalArr5[i2].subtract(bigDecimalArr4[i2]);
            i2++;
        }
    }

    public BigDecimalPolynomial mult(BigDecimalPolynomial bigDecimalPolynomial) {
        BigDecimal[] bigDecimalArr;
        int length = this.coeffs.length;
        if (bigDecimalPolynomial.coeffs.length != length) {
            throw new IllegalArgumentException("Number of coefficients must be the same");
        }
        BigDecimalPolynomial bigDecimalPolynomialMultRecursive = multRecursive(bigDecimalPolynomial);
        if (bigDecimalPolynomialMultRecursive.coeffs.length > length) {
            int i2 = length;
            while (true) {
                bigDecimalArr = bigDecimalPolynomialMultRecursive.coeffs;
                if (i2 >= bigDecimalArr.length) {
                    break;
                }
                int i3 = i2 - length;
                bigDecimalArr[i3] = bigDecimalArr[i3].add(bigDecimalArr[i2]);
                i2++;
            }
            bigDecimalPolynomialMultRecursive.coeffs = copyOf(bigDecimalArr, length);
        }
        return bigDecimalPolynomialMultRecursive;
    }

    public BigDecimalPolynomial(BigDecimal[] bigDecimalArr) {
        this.coeffs = bigDecimalArr;
    }

    public BigDecimalPolynomial(BigIntPolynomial bigIntPolynomial) {
        int length = bigIntPolynomial.coeffs.length;
        this.coeffs = new BigDecimal[length];
        for (int i2 = 0; i2 < length; i2++) {
            this.coeffs[i2] = new BigDecimal(bigIntPolynomial.coeffs[i2]);
        }
    }
}
