package org.spongycastle.pqc.math.ntru.polynomial;

import java.security.SecureRandom;
import org.spongycastle.pqc.math.ntru.util.Util;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class DenseTernaryPolynomial extends IntegerPolynomial implements TernaryPolynomial {
    public DenseTernaryPolynomial(int i2) {
        super(i2);
        checkTernarity();
    }

    private void checkTernarity() {
        int i2;
        int i3 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i3 == iArr.length) {
                return;
            }
            i2 = iArr[i3];
            if (i2 < -1 || i2 > 1) {
                break;
            } else {
                i3++;
            }
        }
        throw new IllegalStateException("Illegal value: " + i2 + ", must be one of {-1, 0, 1}");
    }

    public static DenseTernaryPolynomial generateRandom(int i2, int i3, int i4, SecureRandom secureRandom) {
        return new DenseTernaryPolynomial(Util.generateRandomTernary(i2, i3, i4, secureRandom));
    }

    @Override // org.spongycastle.pqc.math.ntru.polynomial.TernaryPolynomial
    public int[] getNegOnes() {
        int length = this.coeffs.length;
        int[] iArr = new int[length];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (this.coeffs[i3] == -1) {
                iArr[i2] = i3;
                i2++;
            }
        }
        return Arrays.copyOf(iArr, i2);
    }

    @Override // org.spongycastle.pqc.math.ntru.polynomial.TernaryPolynomial
    public int[] getOnes() {
        int length = this.coeffs.length;
        int[] iArr = new int[length];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (this.coeffs[i3] == 1) {
                iArr[i2] = i3;
                i2++;
            }
        }
        return Arrays.copyOf(iArr, i2);
    }

    @Override // org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial, org.spongycastle.pqc.math.ntru.polynomial.Polynomial
    public IntegerPolynomial mult(IntegerPolynomial integerPolynomial, int i2) {
        if (i2 != 2048) {
            return super.mult(integerPolynomial, i2);
        }
        IntegerPolynomial integerPolynomial2 = (IntegerPolynomial) integerPolynomial.clone();
        integerPolynomial2.modPositive(2048);
        return new LongPolynomial5(integerPolynomial2).mult(this).toIntegerPolynomial();
    }

    @Override // org.spongycastle.pqc.math.ntru.polynomial.TernaryPolynomial
    public int size() {
        return this.coeffs.length;
    }

    public DenseTernaryPolynomial(IntegerPolynomial integerPolynomial) {
        this(integerPolynomial.coeffs);
    }

    public static DenseTernaryPolynomial generateRandom(int i2, SecureRandom secureRandom) {
        DenseTernaryPolynomial denseTernaryPolynomial = new DenseTernaryPolynomial(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            denseTernaryPolynomial.coeffs[i3] = secureRandom.nextInt(3) - 1;
        }
        return denseTernaryPolynomial;
    }

    public DenseTernaryPolynomial(int[] iArr) {
        super(iArr);
        checkTernarity();
    }
}
