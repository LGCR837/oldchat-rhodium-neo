package org.spongycastle.pqc.math.ntru.polynomial;

/* JADX INFO: loaded from: classes.dex */
public interface Polynomial {
    BigIntPolynomial mult(BigIntPolynomial bigIntPolynomial);

    IntegerPolynomial mult(IntegerPolynomial integerPolynomial);

    IntegerPolynomial mult(IntegerPolynomial integerPolynomial, int i2);

    IntegerPolynomial toIntegerPolynomial();
}
