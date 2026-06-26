package org.spongycastle.math.ec.tools;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.asn1.x9.ECNamedCurveTable;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.math.ec.ECAlgorithms;
import org.spongycastle.math.ec.ECConstants;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.util.BigIntegers;

/* JADX INFO: loaded from: classes.dex */
public class DiscoverEndomorphisms {
    private static final int radix = 16;

    private static boolean areRelativelyPrime(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger.gcd(bigInteger2).equals(ECConstants.ONE);
    }

    private static BigInteger[] calculateRange(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        return order(bigInteger.subtract(bigInteger2).divide(bigInteger3), bigInteger.add(bigInteger2).divide(bigInteger3));
    }

    private static BigInteger[] chooseShortest(BigInteger[] bigIntegerArr, BigInteger[] bigIntegerArr2) {
        return isShorter(bigIntegerArr, bigIntegerArr2) ? bigIntegerArr : bigIntegerArr2;
    }

    public static void discoverEndomorphisms(X9ECParameters x9ECParameters) {
        if (x9ECParameters == null) {
            throw new NullPointerException("x9");
        }
        ECCurve curve = x9ECParameters.getCurve();
        if (ECAlgorithms.isFpCurve(curve)) {
            BigInteger characteristic = curve.getField().getCharacteristic();
            if (curve.getA().isZero() && characteristic.mod(ECConstants.THREE).equals(ECConstants.ONE)) {
                System.out.println("Curve has a 'GLV Type B' endomorphism with these parameters:");
                printGLVTypeBParameters(x9ECParameters);
            }
        }
    }

    private static BigInteger[] extEuclidBezout(BigInteger[] bigIntegerArr) {
        boolean z2 = bigIntegerArr[0].compareTo(bigIntegerArr[1]) < 0;
        if (z2) {
            swap(bigIntegerArr);
        }
        BigInteger bigInteger = bigIntegerArr[0];
        BigInteger bigInteger2 = bigIntegerArr[1];
        BigInteger bigInteger3 = ECConstants.ONE;
        BigInteger bigInteger4 = ECConstants.ZERO;
        BigInteger bigInteger5 = bigInteger3;
        BigInteger bigInteger6 = bigInteger4;
        BigInteger bigInteger7 = bigInteger2;
        BigInteger bigInteger8 = bigInteger;
        while (bigInteger7.compareTo(ECConstants.ONE) > 0) {
            BigInteger[] bigIntegerArrDivideAndRemainder = bigInteger8.divideAndRemainder(bigInteger7);
            BigInteger bigInteger9 = bigIntegerArrDivideAndRemainder[0];
            BigInteger bigInteger10 = bigIntegerArrDivideAndRemainder[1];
            BigInteger bigIntegerSubtract = bigInteger3.subtract(bigInteger9.multiply(bigInteger4));
            BigInteger bigIntegerSubtract2 = bigInteger6.subtract(bigInteger9.multiply(bigInteger5));
            BigInteger bigInteger11 = bigInteger7;
            bigInteger7 = bigInteger10;
            bigInteger8 = bigInteger11;
            BigInteger bigInteger12 = bigInteger4;
            bigInteger4 = bigIntegerSubtract;
            bigInteger3 = bigInteger12;
            bigInteger6 = bigInteger5;
            bigInteger5 = bigIntegerSubtract2;
        }
        if (bigInteger7.signum() <= 0) {
            return null;
        }
        BigInteger[] bigIntegerArr2 = {bigInteger4, bigInteger5};
        if (z2) {
            swap(bigIntegerArr2);
        }
        return bigIntegerArr2;
    }

    private static BigInteger[] extEuclidGLV(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigInteger3 = ECConstants.ZERO;
        BigInteger bigInteger4 = ECConstants.ONE;
        BigInteger bigInteger5 = bigInteger;
        while (true) {
            BigInteger[] bigIntegerArrDivideAndRemainder = bigInteger5.divideAndRemainder(bigInteger2);
            BigInteger bigInteger6 = bigIntegerArrDivideAndRemainder[0];
            BigInteger bigInteger7 = bigIntegerArrDivideAndRemainder[1];
            BigInteger bigIntegerSubtract = bigInteger3.subtract(bigInteger6.multiply(bigInteger4));
            if (isLessThanSqrt(bigInteger2, bigInteger)) {
                return new BigInteger[]{bigInteger5, bigInteger3, bigInteger2, bigInteger4, bigInteger7, bigIntegerSubtract};
            }
            bigInteger5 = bigInteger2;
            bigInteger3 = bigInteger4;
            bigInteger2 = bigInteger7;
            bigInteger4 = bigIntegerSubtract;
        }
    }

    private static ECFieldElement[] findBetaValues(ECCurve eCCurve) {
        BigInteger bigIntegerModPow;
        BigInteger characteristic = eCCurve.getField().getCharacteristic();
        BigInteger bigIntegerDivide = characteristic.divide(ECConstants.THREE);
        SecureRandom secureRandom = new SecureRandom();
        do {
            BigInteger bigInteger = ECConstants.TWO;
            bigIntegerModPow = BigIntegers.createRandomInRange(bigInteger, characteristic.subtract(bigInteger), secureRandom).modPow(bigIntegerDivide, characteristic);
        } while (bigIntegerModPow.equals(ECConstants.ONE));
        ECFieldElement eCFieldElementFromBigInteger = eCCurve.fromBigInteger(bigIntegerModPow);
        return new ECFieldElement[]{eCFieldElementFromBigInteger, eCFieldElementFromBigInteger.square()};
    }

    private static BigInteger[] intersect(BigInteger[] bigIntegerArr, BigInteger[] bigIntegerArr2) {
        BigInteger bigIntegerMax = bigIntegerArr[0].max(bigIntegerArr2[0]);
        BigInteger bigIntegerMin = bigIntegerArr[1].min(bigIntegerArr2[1]);
        if (bigIntegerMax.compareTo(bigIntegerMin) > 0) {
            return null;
        }
        return new BigInteger[]{bigIntegerMax, bigIntegerMin};
    }

    private static boolean isLessThanSqrt(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigIntegerAbs = bigInteger.abs();
        BigInteger bigIntegerAbs2 = bigInteger2.abs();
        int iBitLength = bigIntegerAbs2.bitLength();
        int iBitLength2 = bigIntegerAbs.bitLength() * 2;
        return iBitLength2 + (-1) <= iBitLength && (iBitLength2 < iBitLength || bigIntegerAbs.multiply(bigIntegerAbs).compareTo(bigIntegerAbs2) < 0);
    }

    private static boolean isShorter(BigInteger[] bigIntegerArr, BigInteger[] bigIntegerArr2) {
        BigInteger bigIntegerAbs = bigIntegerArr[0].abs();
        BigInteger bigIntegerAbs2 = bigIntegerArr[1].abs();
        BigInteger bigIntegerAbs3 = bigIntegerArr2[0].abs();
        BigInteger bigIntegerAbs4 = bigIntegerArr2[1].abs();
        boolean z2 = bigIntegerAbs.compareTo(bigIntegerAbs3) < 0;
        return z2 == (bigIntegerAbs2.compareTo(bigIntegerAbs4) < 0) ? z2 : bigIntegerAbs.multiply(bigIntegerAbs).add(bigIntegerAbs2.multiply(bigIntegerAbs2)).compareTo(bigIntegerAbs3.multiply(bigIntegerAbs3).add(bigIntegerAbs4.multiply(bigIntegerAbs4))) < 0;
    }

    private static boolean isVectorBoundedBySqrt(BigInteger[] bigIntegerArr, BigInteger bigInteger) {
        return isLessThanSqrt(bigIntegerArr[0].abs().max(bigIntegerArr[1].abs()), bigInteger);
    }

    private static BigInteger isqrt(BigInteger bigInteger) {
        BigInteger bigIntegerShiftRight = bigInteger.shiftRight(bigInteger.bitLength() / 2);
        while (true) {
            BigInteger bigIntegerShiftRight2 = bigIntegerShiftRight.add(bigInteger.divide(bigIntegerShiftRight)).shiftRight(1);
            if (bigIntegerShiftRight2.equals(bigIntegerShiftRight)) {
                return bigIntegerShiftRight2;
            }
            bigIntegerShiftRight = bigIntegerShiftRight2;
        }
    }

    public static void main(String[] strArr) {
        if (strArr.length < 1) {
            System.err.println("Expected a list of curve names as arguments");
            return;
        }
        for (String str : strArr) {
            discoverEndomorphisms(str);
        }
    }

    private static BigInteger[] order(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger.compareTo(bigInteger2) <= 0 ? new BigInteger[]{bigInteger, bigInteger2} : new BigInteger[]{bigInteger2, bigInteger};
    }

    private static void printGLVTypeBParameters(X9ECParameters x9ECParameters) {
        BigInteger n2 = x9ECParameters.getN();
        BigInteger bigInteger = ECConstants.ONE;
        BigInteger[] bigIntegerArrSolveQuadraticEquation = solveQuadraticEquation(n2, bigInteger, bigInteger);
        ECFieldElement[] eCFieldElementArrFindBetaValues = findBetaValues(x9ECParameters.getCurve());
        printGLVTypeBParameters(x9ECParameters, bigIntegerArrSolveQuadraticEquation[0], eCFieldElementArrFindBetaValues);
        System.out.println("OR");
        printGLVTypeBParameters(x9ECParameters, bigIntegerArrSolveQuadraticEquation[1], eCFieldElementArrFindBetaValues);
    }

    private static void printProperty(String str, Object obj) {
        StringBuffer stringBuffer = new StringBuffer("  ");
        stringBuffer.append(str);
        while (stringBuffer.length() < 20) {
            stringBuffer.append(' ');
        }
        stringBuffer.append("= ");
        stringBuffer.append(obj.toString());
        System.out.println(stringBuffer.toString());
    }

    private static BigInteger roundQuotient(BigInteger bigInteger, BigInteger bigInteger2) {
        boolean z2 = bigInteger.signum() != bigInteger2.signum();
        BigInteger bigIntegerAbs = bigInteger.abs();
        BigInteger bigIntegerAbs2 = bigInteger2.abs();
        BigInteger bigIntegerDivide = bigIntegerAbs.add(bigIntegerAbs2.shiftRight(1)).divide(bigIntegerAbs2);
        return z2 ? bigIntegerDivide.negate() : bigIntegerDivide;
    }

    private static BigInteger[] solveQuadraticEquation(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        BigInteger bigInteger4 = new ECFieldElement.Fp(bigInteger, bigInteger2.multiply(bigInteger2).subtract(bigInteger3.shiftLeft(2)).mod(bigInteger)).sqrt().toBigInteger();
        BigInteger bigIntegerSubtract = bigInteger.subtract(bigInteger4);
        if (bigInteger4.testBit(0)) {
            bigIntegerSubtract = bigIntegerSubtract.add(bigInteger);
        } else {
            bigInteger4 = bigInteger4.add(bigInteger);
        }
        return new BigInteger[]{bigInteger4.shiftRight(1), bigIntegerSubtract.shiftRight(1)};
    }

    private static void swap(BigInteger[] bigIntegerArr) {
        BigInteger bigInteger = bigIntegerArr[0];
        bigIntegerArr[0] = bigIntegerArr[1];
        bigIntegerArr[1] = bigInteger;
    }

    private static void printGLVTypeBParameters(X9ECParameters x9ECParameters, BigInteger bigInteger, ECFieldElement[] eCFieldElementArr) {
        ECPoint eCPointNormalize = x9ECParameters.getG().normalize();
        ECPoint eCPointNormalize2 = eCPointNormalize.multiply(bigInteger).normalize();
        if (eCPointNormalize.getYCoord().equals(eCPointNormalize2.getYCoord())) {
            ECFieldElement eCFieldElement = eCFieldElementArr[0];
            if (!eCPointNormalize.getXCoord().multiply(eCFieldElement).equals(eCPointNormalize2.getXCoord())) {
                eCFieldElement = eCFieldElementArr[1];
                if (!eCPointNormalize.getXCoord().multiply(eCFieldElement).equals(eCPointNormalize2.getXCoord())) {
                    throw new IllegalStateException("Derivation of GLV Type B parameters failed unexpectedly");
                }
            }
            BigInteger n2 = x9ECParameters.getN();
            BigInteger[] bigIntegerArrExtEuclidGLV = extEuclidGLV(n2, bigInteger);
            BigInteger[] bigIntegerArr = {bigIntegerArrExtEuclidGLV[2], bigIntegerArrExtEuclidGLV[3].negate()};
            BigInteger[] bigIntegerArrChooseShortest = chooseShortest(new BigInteger[]{bigIntegerArrExtEuclidGLV[0], bigIntegerArrExtEuclidGLV[1].negate()}, new BigInteger[]{bigIntegerArrExtEuclidGLV[4], bigIntegerArrExtEuclidGLV[5].negate()});
            if (!isVectorBoundedBySqrt(bigIntegerArrChooseShortest, n2) && areRelativelyPrime(bigIntegerArr[0], bigIntegerArr[1])) {
                BigInteger bigInteger2 = bigIntegerArr[0];
                BigInteger bigInteger3 = bigIntegerArr[1];
                BigInteger bigIntegerDivide = bigInteger2.add(bigInteger3.multiply(bigInteger)).divide(n2);
                BigInteger[] bigIntegerArrExtEuclidBezout = extEuclidBezout(new BigInteger[]{bigIntegerDivide.abs(), bigInteger3.abs()});
                if (bigIntegerArrExtEuclidBezout != null) {
                    BigInteger bigIntegerNegate = bigIntegerArrExtEuclidBezout[0];
                    BigInteger bigIntegerNegate2 = bigIntegerArrExtEuclidBezout[1];
                    if (bigIntegerDivide.signum() < 0) {
                        bigIntegerNegate = bigIntegerNegate.negate();
                    }
                    if (bigInteger3.signum() > 0) {
                        bigIntegerNegate2 = bigIntegerNegate2.negate();
                    }
                    BigInteger bigIntegerSubtract = bigIntegerDivide.multiply(bigIntegerNegate).subtract(bigInteger3.multiply(bigIntegerNegate2));
                    BigInteger bigInteger4 = ECConstants.ONE;
                    if (bigIntegerSubtract.equals(bigInteger4)) {
                        BigInteger bigIntegerSubtract2 = bigIntegerNegate2.multiply(n2).subtract(bigIntegerNegate.multiply(bigInteger));
                        BigInteger bigIntegerNegate3 = bigIntegerNegate.negate();
                        BigInteger bigIntegerNegate4 = bigIntegerSubtract2.negate();
                        BigInteger bigIntegerAdd = isqrt(n2.subtract(bigInteger4)).add(bigInteger4);
                        BigInteger[] bigIntegerArrIntersect = intersect(calculateRange(bigIntegerNegate3, bigIntegerAdd, bigInteger3), calculateRange(bigIntegerNegate4, bigIntegerAdd, bigInteger2));
                        if (bigIntegerArrIntersect != null) {
                            for (BigInteger bigIntegerAdd2 = bigIntegerArrIntersect[0]; bigIntegerAdd2.compareTo(bigIntegerArrIntersect[1]) <= 0; bigIntegerAdd2 = bigIntegerAdd2.add(ECConstants.ONE)) {
                                BigInteger[] bigIntegerArr2 = {bigIntegerSubtract2.add(bigIntegerAdd2.multiply(bigInteger2)), bigIntegerNegate.add(bigIntegerAdd2.multiply(bigInteger3))};
                                if (isShorter(bigIntegerArr2, bigIntegerArrChooseShortest)) {
                                    bigIntegerArrChooseShortest = bigIntegerArr2;
                                }
                            }
                        }
                    } else {
                        throw new IllegalStateException();
                    }
                }
            }
            BigInteger bigIntegerSubtract3 = bigIntegerArr[0].multiply(bigIntegerArrChooseShortest[1]).subtract(bigIntegerArr[1].multiply(bigIntegerArrChooseShortest[0]));
            int iBitLength = (n2.bitLength() + 16) - (n2.bitLength() & 7);
            BigInteger bigIntegerRoundQuotient = roundQuotient(bigIntegerArrChooseShortest[1].shiftLeft(iBitLength), bigIntegerSubtract3);
            BigInteger bigIntegerNegate5 = roundQuotient(bigIntegerArr[1].shiftLeft(iBitLength), bigIntegerSubtract3).negate();
            printProperty("Beta", eCFieldElement.toBigInteger().toString(16));
            printProperty("Lambda", bigInteger.toString(16));
            printProperty("v1", "{ " + bigIntegerArr[0].toString(16) + ", " + bigIntegerArr[1].toString(16) + " }");
            printProperty("v2", "{ " + bigIntegerArrChooseShortest[0].toString(16) + ", " + bigIntegerArrChooseShortest[1].toString(16) + " }");
            printProperty("d", bigIntegerSubtract3.toString(16));
            printProperty("(OPT) g1", bigIntegerRoundQuotient.toString(16));
            printProperty("(OPT) g2", bigIntegerNegate5.toString(16));
            printProperty("(OPT) bits", Integer.toString(iBitLength));
            return;
        }
        throw new IllegalStateException("Derivation of GLV Type B parameters failed unexpectedly");
    }

    private static void discoverEndomorphisms(String str) {
        X9ECParameters byName = ECNamedCurveTable.getByName(str);
        if (byName == null) {
            System.err.println("Unknown curve: " + str);
            return;
        }
        ECCurve curve = byName.getCurve();
        if (ECAlgorithms.isFpCurve(curve)) {
            BigInteger characteristic = curve.getField().getCharacteristic();
            if (curve.getA().isZero() && characteristic.mod(ECConstants.THREE).equals(ECConstants.ONE)) {
                System.out.println("Curve '" + str + "' has a 'GLV Type B' endomorphism with these parameters:");
                printGLVTypeBParameters(byName);
            }
        }
    }
}
