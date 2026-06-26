package org.spongycastle.pqc.crypto.ntru;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.crypto.ntru.NTRUSigningPrivateKeyParameters;
import org.spongycastle.pqc.math.ntru.euclid.BigIntEuclidean;
import org.spongycastle.pqc.math.ntru.polynomial.BigDecimalPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Resultant;

/* JADX INFO: loaded from: classes.dex */
public class NTRUSigningKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NTRUSigningKeyGenerationParameters params;

    public class BasisGenerationTask implements Callable<NTRUSigningPrivateKeyParameters.Basis> {
        private BasisGenerationTask() {
        }

        @Override // java.util.concurrent.Callable
        public NTRUSigningPrivateKeyParameters.Basis call() {
            return NTRUSigningKeyPairGenerator.this.generateBoundedBasis();
        }
    }

    public class FGBasis extends NTRUSigningPrivateKeyParameters.Basis {

        /* JADX INFO: renamed from: F, reason: collision with root package name */
        public IntegerPolynomial f7665F;

        /* JADX INFO: renamed from: G, reason: collision with root package name */
        public IntegerPolynomial f7666G;

        public FGBasis(Polynomial polynomial, Polynomial polynomial2, IntegerPolynomial integerPolynomial, IntegerPolynomial integerPolynomial2, IntegerPolynomial integerPolynomial3, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters) {
            super(polynomial, polynomial2, integerPolynomial, nTRUSigningKeyGenerationParameters);
            this.f7665F = integerPolynomial2;
            this.f7666G = integerPolynomial3;
        }

        public boolean isNormOk() {
            NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters = this.params;
            double d2 = nTRUSigningKeyGenerationParameters.keyNormBoundSq;
            int i2 = nTRUSigningKeyGenerationParameters.f7664q;
            return ((double) this.f7665F.centeredNormSq(i2)) < d2 && ((double) this.f7666G.centeredNormSq(i2)) < d2;
        }
    }

    private FGBasis generateBasis() {
        int i2;
        Polynomial polynomialGenerateRandom;
        Polynomial polynomial;
        IntegerPolynomial integerPolynomial;
        IntegerPolynomial integerPolynomialInvertFq;
        int i3;
        Resultant resultant;
        int i4;
        IntegerPolynomial integerPolynomial2;
        int i5;
        IntegerPolynomial integerPolynomial3;
        int i6;
        Polynomial polynomial2;
        Polynomial polynomialGenerateRandom2;
        Polynomial polynomial3;
        IntegerPolynomial integerPolynomial4;
        Resultant resultant2;
        BigIntEuclidean bigIntEuclideanCalculate;
        BigIntPolynomial bigIntPolynomialRound;
        IntegerPolynomial integerPolynomialMult;
        Polynomial polynomial4;
        NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters = this.params;
        int i7 = nTRUSigningKeyGenerationParameters.f7662N;
        int i8 = nTRUSigningKeyGenerationParameters.f7664q;
        int i9 = nTRUSigningKeyGenerationParameters.f7663d;
        int i10 = nTRUSigningKeyGenerationParameters.d1;
        int i11 = nTRUSigningKeyGenerationParameters.d2;
        int i12 = nTRUSigningKeyGenerationParameters.d3;
        int i13 = nTRUSigningKeyGenerationParameters.basisType;
        int i14 = (i7 * 2) + 1;
        boolean z2 = nTRUSigningKeyGenerationParameters.primeCheck;
        while (true) {
            if (this.params.polyType == 0) {
                polynomialGenerateRandom = DenseTernaryPolynomial.generateRandom(i7, i9 + 1, i9, new SecureRandom());
                i2 = i14;
            } else {
                i2 = i14;
                polynomialGenerateRandom = ProductFormPolynomial.generateRandom(i7, i10, i11, i12 + 1, i12, new SecureRandom());
            }
            polynomial = polynomialGenerateRandom;
            integerPolynomial = polynomial.toIntegerPolynomial();
            if ((!z2 || !integerPolynomial.resultant(i2).res.equals(BigInteger.ZERO)) && (integerPolynomialInvertFq = integerPolynomial.invertFq(i8)) != null) {
                break;
            }
            i14 = i2;
        }
        Resultant resultant3 = integerPolynomial.resultant();
        while (true) {
            if (this.params.polyType == 0) {
                polynomialGenerateRandom2 = DenseTernaryPolynomial.generateRandom(i7, i9 + 1, i9, new SecureRandom());
                i3 = i9;
                i4 = i10;
                i5 = i11;
                i6 = i12;
                resultant = resultant3;
                integerPolynomial2 = integerPolynomialInvertFq;
                integerPolynomial3 = integerPolynomial;
                polynomial2 = polynomial;
            } else {
                int i15 = i10;
                i3 = i9;
                resultant = resultant3;
                int i16 = i11;
                i4 = i10;
                integerPolynomial2 = integerPolynomialInvertFq;
                i5 = i11;
                integerPolynomial3 = integerPolynomial;
                int i17 = i12;
                i6 = i12;
                polynomial2 = polynomial;
                polynomialGenerateRandom2 = ProductFormPolynomial.generateRandom(i7, i15, i16, i12 + 1, i17, new SecureRandom());
            }
            polynomial3 = polynomialGenerateRandom2;
            integerPolynomial4 = polynomial3.toIntegerPolynomial();
            if (!z2 || !integerPolynomial4.resultant(i2).res.equals(BigInteger.ZERO)) {
                if (integerPolynomial4.invertFq(i8) != null) {
                    resultant2 = integerPolynomial4.resultant();
                    bigIntEuclideanCalculate = BigIntEuclidean.calculate(resultant.res, resultant2.res);
                    if (bigIntEuclideanCalculate.gcd.equals(BigInteger.ONE)) {
                        break;
                    }
                }
            }
            resultant3 = resultant;
            integerPolynomialInvertFq = integerPolynomial2;
            integerPolynomial = integerPolynomial3;
            polynomial = polynomial2;
            i11 = i5;
            i9 = i3;
            i10 = i4;
            i12 = i6;
        }
        BigIntPolynomial bigIntPolynomial = (BigIntPolynomial) resultant.rho.clone();
        bigIntPolynomial.mult(bigIntEuclideanCalculate.f7691x.multiply(BigInteger.valueOf(i8)));
        BigIntPolynomial bigIntPolynomial2 = (BigIntPolynomial) resultant2.rho.clone();
        bigIntPolynomial2.mult(bigIntEuclideanCalculate.f7692y.multiply(BigInteger.valueOf(-i8)));
        int i18 = 0;
        if (this.params.keyGenAlg == 0) {
            int[] iArr = new int[i7];
            int[] iArr2 = new int[i7];
            iArr[0] = integerPolynomial3.coeffs[0];
            iArr2[0] = integerPolynomial4.coeffs[0];
            for (int i19 = 1; i19 < i7; i19++) {
                int i20 = i7 - i19;
                iArr[i19] = integerPolynomial3.coeffs[i20];
                iArr2[i19] = integerPolynomial4.coeffs[i20];
            }
            IntegerPolynomial integerPolynomial5 = new IntegerPolynomial(iArr);
            IntegerPolynomial integerPolynomial6 = new IntegerPolynomial(iArr2);
            IntegerPolynomial integerPolynomialMult2 = polynomial2.mult(integerPolynomial5);
            integerPolynomialMult2.add(polynomial3.mult(integerPolynomial6));
            Resultant resultant4 = integerPolynomialMult2.resultant();
            BigIntPolynomial bigIntPolynomialMult = integerPolynomial5.mult(bigIntPolynomial2);
            bigIntPolynomialMult.add(integerPolynomial6.mult(bigIntPolynomial));
            bigIntPolynomialRound = bigIntPolynomialMult.mult(resultant4.rho);
            bigIntPolynomialRound.div(resultant4.res);
        } else {
            for (int i21 = 1; i21 < i7; i21 *= 10) {
                i18++;
            }
            BigDecimalPolynomial bigDecimalPolynomialDiv = resultant.rho.div(new BigDecimal(resultant.res), bigIntPolynomial2.getMaxCoeffLength() + 1 + i18);
            BigDecimalPolynomial bigDecimalPolynomialDiv2 = resultant2.rho.div(new BigDecimal(resultant2.res), bigIntPolynomial.getMaxCoeffLength() + 1 + i18);
            BigDecimalPolynomial bigDecimalPolynomialMult = bigDecimalPolynomialDiv.mult(bigIntPolynomial2);
            bigDecimalPolynomialMult.add(bigDecimalPolynomialDiv2.mult(bigIntPolynomial));
            bigDecimalPolynomialMult.halve();
            bigIntPolynomialRound = bigDecimalPolynomialMult.round();
        }
        BigIntPolynomial bigIntPolynomial3 = (BigIntPolynomial) bigIntPolynomial2.clone();
        bigIntPolynomial3.sub(polynomial2.mult(bigIntPolynomialRound));
        BigIntPolynomial bigIntPolynomial4 = (BigIntPolynomial) bigIntPolynomial.clone();
        bigIntPolynomial4.sub(polynomial3.mult(bigIntPolynomialRound));
        IntegerPolynomial integerPolynomial7 = new IntegerPolynomial(bigIntPolynomial3);
        IntegerPolynomial integerPolynomial8 = new IntegerPolynomial(bigIntPolynomial4);
        minimizeFG(integerPolynomial3, integerPolynomial4, integerPolynomial7, integerPolynomial8, i7);
        if (i13 == 0) {
            integerPolynomialMult = polynomial3.mult(integerPolynomial2, i8);
            polynomial4 = integerPolynomial7;
        } else {
            integerPolynomialMult = integerPolynomial7.mult(integerPolynomial2, i8);
            polynomial4 = polynomial3;
        }
        integerPolynomialMult.modPositive(i8);
        return new FGBasis(polynomial2, polynomial4, integerPolynomialMult, integerPolynomial7, integerPolynomial8, this.params);
    }

    private void minimizeFG(IntegerPolynomial integerPolynomial, IntegerPolynomial integerPolynomial2, IntegerPolynomial integerPolynomial3, IntegerPolynomial integerPolynomial4, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = integerPolynomial.coeffs[i4];
            int i6 = integerPolynomial2.coeffs[i4];
            i3 += i2 * 2 * ((i5 * i5) + (i6 * i6));
        }
        int i7 = i3 - 4;
        IntegerPolynomial integerPolynomial5 = (IntegerPolynomial) integerPolynomial.clone();
        IntegerPolynomial integerPolynomial6 = (IntegerPolynomial) integerPolynomial2.clone();
        int i8 = 0;
        int i9 = 0;
        while (i8 < i2 && i9 < i2) {
            int i10 = 0;
            for (int i11 = 0; i11 < i2; i11++) {
                i10 += i2 * 4 * ((integerPolynomial3.coeffs[i11] * integerPolynomial.coeffs[i11]) + (integerPolynomial4.coeffs[i11] * integerPolynomial2.coeffs[i11]));
            }
            int iSumCoeffs = i10 - ((integerPolynomial3.sumCoeffs() + integerPolynomial4.sumCoeffs()) * 4);
            if (iSumCoeffs > i7) {
                integerPolynomial3.sub(integerPolynomial5);
                integerPolynomial4.sub(integerPolynomial6);
            } else if (iSumCoeffs < (-i7)) {
                integerPolynomial3.add(integerPolynomial5);
                integerPolynomial4.add(integerPolynomial6);
            } else {
                i9++;
                integerPolynomial5.rotate1();
                integerPolynomial6.rotate1();
            }
            i8++;
            i9 = 0;
            i9++;
            integerPolynomial5.rotate1();
            integerPolynomial6.rotate1();
        }
    }

    public NTRUSigningPrivateKeyParameters.Basis generateBoundedBasis() {
        FGBasis fGBasisGenerateBasis;
        do {
            fGBasisGenerateBasis = generateBasis();
        } while (!fGBasisGenerateBasis.isNormOk());
        return fGBasisGenerateBasis;
    }

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters;
        ExecutorService executorServiceNewCachedThreadPool = Executors.newCachedThreadPool();
        ArrayList arrayList = new ArrayList();
        int i2 = this.params.f7661B;
        while (true) {
            nTRUSigningPublicKeyParameters = null;
            byte b2 = 0;
            if (i2 < 0) {
                break;
            }
            arrayList.add(executorServiceNewCachedThreadPool.submit(new BasisGenerationTask()));
            i2--;
        }
        executorServiceNewCachedThreadPool.shutdown();
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = this.params.f7661B; i3 >= 0; i3--) {
            Future future = (Future) arrayList.get(i3);
            try {
                arrayList2.add(future.get());
                if (i3 == this.params.f7661B) {
                    nTRUSigningPublicKeyParameters = new NTRUSigningPublicKeyParameters(((NTRUSigningPrivateKeyParameters.Basis) future.get()).f7672h, this.params.getSigningParameters());
                }
            } catch (Exception e2) {
                throw new IllegalStateException(e2);
            }
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) nTRUSigningPublicKeyParameters, (AsymmetricKeyParameter) new NTRUSigningPrivateKeyParameters(arrayList2, nTRUSigningPublicKeyParameters));
    }

    public AsymmetricCipherKeyPair generateKeyPairSingleThread() {
        ArrayList arrayList = new ArrayList();
        NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters = null;
        for (int i2 = this.params.f7661B; i2 >= 0; i2--) {
            NTRUSigningPrivateKeyParameters.Basis basisGenerateBoundedBasis = generateBoundedBasis();
            arrayList.add(basisGenerateBoundedBasis);
            if (i2 == 0) {
                nTRUSigningPublicKeyParameters = new NTRUSigningPublicKeyParameters(basisGenerateBoundedBasis.f7672h, this.params.getSigningParameters());
            }
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) nTRUSigningPublicKeyParameters, (AsymmetricKeyParameter) new NTRUSigningPrivateKeyParameters(arrayList, nTRUSigningPublicKeyParameters));
    }

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.params = (NTRUSigningKeyGenerationParameters) keyGenerationParameters;
    }
}
