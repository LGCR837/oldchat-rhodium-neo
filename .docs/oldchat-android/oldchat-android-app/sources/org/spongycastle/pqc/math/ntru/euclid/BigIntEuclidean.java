package org.spongycastle.pqc.math.ntru.euclid;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class BigIntEuclidean {
    public BigInteger gcd;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public BigInteger f7691x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public BigInteger f7692y;

    private BigIntEuclidean() {
    }

    public static BigIntEuclidean calculate(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigInteger3 = BigInteger.ZERO;
        BigInteger bigIntegerSubtract = BigInteger.ONE;
        BigInteger bigInteger4 = bigIntegerSubtract;
        BigInteger bigInteger5 = bigInteger3;
        BigInteger bigInteger6 = bigInteger;
        BigInteger bigInteger7 = bigInteger2;
        while (!bigInteger7.equals(BigInteger.ZERO)) {
            BigInteger[] bigIntegerArrDivideAndRemainder = bigInteger6.divideAndRemainder(bigInteger7);
            BigInteger bigInteger8 = bigIntegerArrDivideAndRemainder[0];
            BigInteger bigInteger9 = bigIntegerArrDivideAndRemainder[1];
            BigInteger bigIntegerSubtract2 = bigInteger4.subtract(bigInteger8.multiply(bigInteger5));
            bigInteger6 = bigInteger7;
            bigInteger7 = bigInteger9;
            BigInteger bigInteger10 = bigIntegerSubtract;
            bigIntegerSubtract = bigInteger3.subtract(bigInteger8.multiply(bigIntegerSubtract));
            bigInteger3 = bigInteger10;
            bigInteger4 = bigInteger5;
            bigInteger5 = bigIntegerSubtract2;
        }
        BigIntEuclidean bigIntEuclidean = new BigIntEuclidean();
        bigIntEuclidean.f7691x = bigInteger4;
        bigIntEuclidean.f7692y = bigInteger3;
        bigIntEuclidean.gcd = bigInteger6;
        return bigIntEuclidean;
    }
}
