package org.spongycastle.crypto.agreement.jpake;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class JPAKEPrimeOrderGroup {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final BigInteger f7242g;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final BigInteger f7243p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private final BigInteger f7244q;

    public JPAKEPrimeOrderGroup(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this(bigInteger, bigInteger2, bigInteger3, false);
    }

    public BigInteger getG() {
        return this.f7242g;
    }

    public BigInteger getP() {
        return this.f7243p;
    }

    public BigInteger getQ() {
        return this.f7244q;
    }

    public JPAKEPrimeOrderGroup(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, boolean z2) {
        JPAKEUtil.validateNotNull(bigInteger, "p");
        JPAKEUtil.validateNotNull(bigInteger2, "q");
        JPAKEUtil.validateNotNull(bigInteger3, "g");
        if (!z2) {
            BigInteger bigInteger4 = JPAKEUtil.ONE;
            if (!bigInteger.subtract(bigInteger4).mod(bigInteger2).equals(JPAKEUtil.ZERO)) {
                throw new IllegalArgumentException("p-1 must be evenly divisible by q");
            }
            if (bigInteger3.compareTo(BigInteger.valueOf(2L)) == -1 || bigInteger3.compareTo(bigInteger.subtract(bigInteger4)) == 1) {
                throw new IllegalArgumentException("g must be in [2, p-1]");
            }
            if (!bigInteger3.modPow(bigInteger2, bigInteger).equals(bigInteger4)) {
                throw new IllegalArgumentException("g^q mod p must equal 1");
            }
            if (!bigInteger.isProbablePrime(20)) {
                throw new IllegalArgumentException("p must be prime");
            }
            if (!bigInteger2.isProbablePrime(20)) {
                throw new IllegalArgumentException("q must be prime");
            }
        }
        this.f7243p = bigInteger;
        this.f7244q = bigInteger2;
        this.f7242g = bigInteger3;
    }
}
