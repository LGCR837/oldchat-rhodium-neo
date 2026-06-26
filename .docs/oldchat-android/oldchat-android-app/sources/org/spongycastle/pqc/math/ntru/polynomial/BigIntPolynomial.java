package org.spongycastle.pqc.math.ntru.polynomial;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class BigIntPolynomial {
    private static final double LOG_10_2 = Math.log10(2.0d);
    BigInteger[] coeffs;

    public BigIntPolynomial(int i2) {
        this.coeffs = new BigInteger[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            this.coeffs[i3] = Constants.BIGINT_ZERO;
        }
    }

    public static BigIntPolynomial generateRandomSmall(int i2, int i3, int i4) {
        ArrayList arrayList = new ArrayList();
        for (int i5 = 0; i5 < i3; i5++) {
            arrayList.add(Constants.BIGINT_ONE);
        }
        for (int i6 = 0; i6 < i4; i6++) {
            arrayList.add(BigInteger.valueOf(-1L));
        }
        while (arrayList.size() < i2) {
            arrayList.add(Constants.BIGINT_ZERO);
        }
        Collections.shuffle(arrayList, new SecureRandom());
        BigIntPolynomial bigIntPolynomial = new BigIntPolynomial(i2);
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            bigIntPolynomial.coeffs[i7] = (BigInteger) arrayList.get(i7);
        }
        return bigIntPolynomial;
    }

    private BigInteger maxCoeffAbs() {
        BigInteger bigIntegerAbs = this.coeffs[0].abs();
        int i2 = 1;
        while (true) {
            BigInteger[] bigIntegerArr = this.coeffs;
            if (i2 >= bigIntegerArr.length) {
                return bigIntegerAbs;
            }
            BigInteger bigIntegerAbs2 = bigIntegerArr[i2].abs();
            if (bigIntegerAbs2.compareTo(bigIntegerAbs) > 0) {
                bigIntegerAbs = bigIntegerAbs2;
            }
            i2++;
        }
    }

    private BigIntPolynomial multRecursive(BigIntPolynomial bigIntPolynomial) {
        BigInteger[] bigIntegerArr = this.coeffs;
        BigInteger[] bigIntegerArr2 = bigIntPolynomial.coeffs;
        int length = bigIntegerArr2.length;
        int i2 = 0;
        if (length <= 1) {
            BigInteger[] bigIntegerArrClone = Arrays.clone(bigIntegerArr);
            for (int i3 = 0; i3 < this.coeffs.length; i3++) {
                bigIntegerArrClone[i3] = bigIntegerArrClone[i3].multiply(bigIntPolynomial.coeffs[0]);
            }
            return new BigIntPolynomial(bigIntegerArrClone);
        }
        int i4 = length / 2;
        BigIntPolynomial bigIntPolynomial2 = new BigIntPolynomial(Arrays.copyOf(bigIntegerArr, i4));
        BigIntPolynomial bigIntPolynomial3 = new BigIntPolynomial(Arrays.copyOfRange(bigIntegerArr, i4, length));
        BigIntPolynomial bigIntPolynomial4 = new BigIntPolynomial(Arrays.copyOf(bigIntegerArr2, i4));
        BigIntPolynomial bigIntPolynomial5 = new BigIntPolynomial(Arrays.copyOfRange(bigIntegerArr2, i4, length));
        BigIntPolynomial bigIntPolynomial6 = (BigIntPolynomial) bigIntPolynomial2.clone();
        bigIntPolynomial6.add(bigIntPolynomial3);
        BigIntPolynomial bigIntPolynomial7 = (BigIntPolynomial) bigIntPolynomial4.clone();
        bigIntPolynomial7.add(bigIntPolynomial5);
        BigIntPolynomial bigIntPolynomialMultRecursive = bigIntPolynomial2.multRecursive(bigIntPolynomial4);
        BigIntPolynomial bigIntPolynomialMultRecursive2 = bigIntPolynomial3.multRecursive(bigIntPolynomial5);
        BigIntPolynomial bigIntPolynomialMultRecursive3 = bigIntPolynomial6.multRecursive(bigIntPolynomial7);
        bigIntPolynomialMultRecursive3.sub(bigIntPolynomialMultRecursive);
        bigIntPolynomialMultRecursive3.sub(bigIntPolynomialMultRecursive2);
        BigIntPolynomial bigIntPolynomial8 = new BigIntPolynomial((length * 2) - 1);
        int i5 = 0;
        while (true) {
            BigInteger[] bigIntegerArr3 = bigIntPolynomialMultRecursive.coeffs;
            if (i5 >= bigIntegerArr3.length) {
                break;
            }
            bigIntPolynomial8.coeffs[i5] = bigIntegerArr3[i5];
            i5++;
        }
        int i6 = 0;
        while (true) {
            BigInteger[] bigIntegerArr4 = bigIntPolynomialMultRecursive3.coeffs;
            if (i6 >= bigIntegerArr4.length) {
                break;
            }
            BigInteger[] bigIntegerArr5 = bigIntPolynomial8.coeffs;
            int i7 = i4 + i6;
            bigIntegerArr5[i7] = bigIntegerArr5[i7].add(bigIntegerArr4[i6]);
            i6++;
        }
        while (true) {
            BigInteger[] bigIntegerArr6 = bigIntPolynomialMultRecursive2.coeffs;
            if (i2 >= bigIntegerArr6.length) {
                return bigIntPolynomial8;
            }
            BigInteger[] bigIntegerArr7 = bigIntPolynomial8.coeffs;
            int i8 = (i4 * 2) + i2;
            bigIntegerArr7[i8] = bigIntegerArr7[i8].add(bigIntegerArr6[i2]);
            i2++;
        }
    }

    public void add(BigIntPolynomial bigIntPolynomial, BigInteger bigInteger) {
        add(bigIntPolynomial);
        mod(bigInteger);
    }

    public Object clone() {
        return new BigIntPolynomial((BigInteger[]) this.coeffs.clone());
    }

    public void div(BigInteger bigInteger) {
        BigInteger bigIntegerDivide = bigInteger.add(Constants.BIGINT_ONE).divide(BigInteger.valueOf(2L));
        int i2 = 0;
        while (true) {
            BigInteger[] bigIntegerArr = this.coeffs;
            if (i2 >= bigIntegerArr.length) {
                return;
            }
            bigIntegerArr[i2] = bigIntegerArr[i2].compareTo(Constants.BIGINT_ZERO) > 0 ? this.coeffs[i2].add(bigIntegerDivide) : this.coeffs[i2].add(bigIntegerDivide.negate());
            BigInteger[] bigIntegerArr2 = this.coeffs;
            bigIntegerArr2[i2] = bigIntegerArr2[i2].divide(bigInteger);
            i2++;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && Arrays.areEqual(this.coeffs, ((BigIntPolynomial) obj).coeffs);
    }

    public BigInteger[] getCoeffs() {
        return Arrays.clone(this.coeffs);
    }

    public int getMaxCoeffLength() {
        double dBitLength = maxCoeffAbs().bitLength();
        double d2 = LOG_10_2;
        Double.isNaN(dBitLength);
        return ((int) (dBitLength * d2)) + 1;
    }

    public int hashCode() {
        return 31 + Arrays.hashCode(this.coeffs);
    }

    public void mod(BigInteger bigInteger) {
        int i2 = 0;
        while (true) {
            BigInteger[] bigIntegerArr = this.coeffs;
            if (i2 >= bigIntegerArr.length) {
                return;
            }
            bigIntegerArr[i2] = bigIntegerArr[i2].mod(bigInteger);
            i2++;
        }
    }

    public BigIntPolynomial mult(BigIntPolynomial bigIntPolynomial) {
        BigInteger[] bigIntegerArr;
        int length = this.coeffs.length;
        if (bigIntPolynomial.coeffs.length != length) {
            throw new IllegalArgumentException("Number of coefficients must be the same");
        }
        BigIntPolynomial bigIntPolynomialMultRecursive = multRecursive(bigIntPolynomial);
        if (bigIntPolynomialMultRecursive.coeffs.length > length) {
            int i2 = length;
            while (true) {
                bigIntegerArr = bigIntPolynomialMultRecursive.coeffs;
                if (i2 >= bigIntegerArr.length) {
                    break;
                }
                int i3 = i2 - length;
                bigIntegerArr[i3] = bigIntegerArr[i3].add(bigIntegerArr[i2]);
                i2++;
            }
            bigIntPolynomialMultRecursive.coeffs = Arrays.copyOf(bigIntegerArr, length);
        }
        return bigIntPolynomialMultRecursive;
    }

    public void sub(BigIntPolynomial bigIntPolynomial) {
        BigInteger[] bigIntegerArr = bigIntPolynomial.coeffs;
        int length = bigIntegerArr.length;
        BigInteger[] bigIntegerArr2 = this.coeffs;
        if (length > bigIntegerArr2.length) {
            int length2 = bigIntegerArr2.length;
            this.coeffs = Arrays.copyOf(bigIntegerArr2, bigIntegerArr.length);
            while (true) {
                BigInteger[] bigIntegerArr3 = this.coeffs;
                if (length2 >= bigIntegerArr3.length) {
                    break;
                }
                bigIntegerArr3[length2] = Constants.BIGINT_ZERO;
                length2++;
            }
        }
        int i2 = 0;
        while (true) {
            BigInteger[] bigIntegerArr4 = bigIntPolynomial.coeffs;
            if (i2 >= bigIntegerArr4.length) {
                return;
            }
            BigInteger[] bigIntegerArr5 = this.coeffs;
            bigIntegerArr5[i2] = bigIntegerArr5[i2].subtract(bigIntegerArr4[i2]);
            i2++;
        }
    }

    public BigInteger sumCoeffs() {
        BigInteger bigIntegerAdd = Constants.BIGINT_ZERO;
        int i2 = 0;
        while (true) {
            BigInteger[] bigIntegerArr = this.coeffs;
            if (i2 >= bigIntegerArr.length) {
                return bigIntegerAdd;
            }
            bigIntegerAdd = bigIntegerAdd.add(bigIntegerArr[i2]);
            i2++;
        }
    }

    public void add(BigIntPolynomial bigIntPolynomial) {
        BigInteger[] bigIntegerArr = bigIntPolynomial.coeffs;
        int length = bigIntegerArr.length;
        BigInteger[] bigIntegerArr2 = this.coeffs;
        if (length > bigIntegerArr2.length) {
            int length2 = bigIntegerArr2.length;
            this.coeffs = Arrays.copyOf(bigIntegerArr2, bigIntegerArr.length);
            while (true) {
                BigInteger[] bigIntegerArr3 = this.coeffs;
                if (length2 >= bigIntegerArr3.length) {
                    break;
                }
                bigIntegerArr3[length2] = Constants.BIGINT_ZERO;
                length2++;
            }
        }
        int i2 = 0;
        while (true) {
            BigInteger[] bigIntegerArr4 = bigIntPolynomial.coeffs;
            if (i2 >= bigIntegerArr4.length) {
                return;
            }
            BigInteger[] bigIntegerArr5 = this.coeffs;
            bigIntegerArr5[i2] = bigIntegerArr5[i2].add(bigIntegerArr4[i2]);
            i2++;
        }
    }

    public BigIntPolynomial(BigInteger[] bigIntegerArr) {
        this.coeffs = bigIntegerArr;
    }

    public BigDecimalPolynomial div(BigDecimal bigDecimal, int i2) {
        double dBitLength = maxCoeffAbs().bitLength();
        double d2 = LOG_10_2;
        Double.isNaN(dBitLength);
        BigDecimal bigDecimalDivide = Constants.BIGDEC_ONE.divide(bigDecimal, ((int) (dBitLength * d2)) + 1 + i2 + 1, 6);
        BigDecimalPolynomial bigDecimalPolynomial = new BigDecimalPolynomial(this.coeffs.length);
        for (int i3 = 0; i3 < this.coeffs.length; i3++) {
            bigDecimalPolynomial.coeffs[i3] = new BigDecimal(this.coeffs[i3]).multiply(bigDecimalDivide).setScale(i2, 6);
        }
        return bigDecimalPolynomial;
    }

    public BigIntPolynomial(IntegerPolynomial integerPolynomial) {
        this.coeffs = new BigInteger[integerPolynomial.coeffs.length];
        int i2 = 0;
        while (true) {
            BigInteger[] bigIntegerArr = this.coeffs;
            if (i2 >= bigIntegerArr.length) {
                return;
            }
            bigIntegerArr[i2] = BigInteger.valueOf(integerPolynomial.coeffs[i2]);
            i2++;
        }
    }

    public void mult(BigInteger bigInteger) {
        int i2 = 0;
        while (true) {
            BigInteger[] bigIntegerArr = this.coeffs;
            if (i2 >= bigIntegerArr.length) {
                return;
            }
            bigIntegerArr[i2] = bigIntegerArr[i2].multiply(bigInteger);
            i2++;
        }
    }

    public void mult(int i2) {
        mult(BigInteger.valueOf(i2));
    }
}
