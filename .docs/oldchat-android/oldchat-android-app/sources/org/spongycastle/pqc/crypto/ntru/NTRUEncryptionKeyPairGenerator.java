package org.spongycastle.pqc.crypto.ntru;

import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.util.Util;

/* JADX INFO: loaded from: classes.dex */
public class NTRUEncryptionKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NTRUEncryptionKeyGenerationParameters params;

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        Polynomial polynomialGenerateRandomTernary;
        IntegerPolynomial integerPolynomial;
        IntegerPolynomial integerPolynomialInvertFq;
        IntegerPolynomial integerPolynomial2;
        DenseTernaryPolynomial denseTernaryPolynomialGenerateRandom;
        NTRUEncryptionKeyGenerationParameters nTRUEncryptionKeyGenerationParameters = this.params;
        int i2 = nTRUEncryptionKeyGenerationParameters.f7652N;
        int i3 = nTRUEncryptionKeyGenerationParameters.f7654q;
        int i4 = nTRUEncryptionKeyGenerationParameters.df;
        int i5 = nTRUEncryptionKeyGenerationParameters.df1;
        int i6 = nTRUEncryptionKeyGenerationParameters.df2;
        int i7 = nTRUEncryptionKeyGenerationParameters.df3;
        int i8 = nTRUEncryptionKeyGenerationParameters.dg;
        boolean z2 = nTRUEncryptionKeyGenerationParameters.fastFp;
        boolean z3 = nTRUEncryptionKeyGenerationParameters.sparse;
        IntegerPolynomial integerPolynomialInvertF3 = null;
        while (true) {
            if (z2) {
                NTRUEncryptionKeyGenerationParameters nTRUEncryptionKeyGenerationParameters2 = this.params;
                polynomialGenerateRandomTernary = nTRUEncryptionKeyGenerationParameters2.polyType == 0 ? Util.generateRandomTernary(i2, i4, i4, z3, nTRUEncryptionKeyGenerationParameters2.getRandom()) : ProductFormPolynomial.generateRandom(i2, i5, i6, i7, i7, nTRUEncryptionKeyGenerationParameters2.getRandom());
                integerPolynomial = polynomialGenerateRandomTernary.toIntegerPolynomial();
                integerPolynomial.mult(3);
                int[] iArr = integerPolynomial.coeffs;
                iArr[0] = iArr[0] + 1;
            } else {
                NTRUEncryptionKeyGenerationParameters nTRUEncryptionKeyGenerationParameters3 = this.params;
                polynomialGenerateRandomTernary = nTRUEncryptionKeyGenerationParameters3.polyType == 0 ? Util.generateRandomTernary(i2, i4, i4 - 1, z3, nTRUEncryptionKeyGenerationParameters3.getRandom()) : ProductFormPolynomial.generateRandom(i2, i5, i6, i7, i7 - 1, nTRUEncryptionKeyGenerationParameters3.getRandom());
                integerPolynomial = polynomialGenerateRandomTernary.toIntegerPolynomial();
                integerPolynomialInvertF3 = integerPolynomial.invertF3();
                if (integerPolynomialInvertF3 == null) {
                    continue;
                }
            }
            integerPolynomialInvertFq = integerPolynomial.invertFq(i3);
            if (integerPolynomialInvertFq != null) {
                break;
            }
        }
        if (z2) {
            integerPolynomial2 = new IntegerPolynomial(i2);
            integerPolynomial2.coeffs[0] = 1;
        } else {
            integerPolynomial2 = integerPolynomialInvertF3;
        }
        do {
            denseTernaryPolynomialGenerateRandom = DenseTernaryPolynomial.generateRandom(i2, i8, i8 - 1, this.params.getRandom());
        } while (denseTernaryPolynomialGenerateRandom.invertFq(i3) == null);
        IntegerPolynomial integerPolynomialMult = denseTernaryPolynomialGenerateRandom.mult(integerPolynomialInvertFq, i3);
        integerPolynomialMult.mult3(i3);
        integerPolynomialMult.ensurePositive(i3);
        denseTernaryPolynomialGenerateRandom.clear();
        integerPolynomialInvertFq.clear();
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new NTRUEncryptionPublicKeyParameters(integerPolynomialMult, this.params.getEncryptionParameters()), (AsymmetricKeyParameter) new NTRUEncryptionPrivateKeyParameters(integerPolynomialMult, polynomialGenerateRandomTernary, integerPolynomial2, this.params.getEncryptionParameters()));
    }

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.params = (NTRUEncryptionKeyGenerationParameters) keyGenerationParameters;
    }
}
