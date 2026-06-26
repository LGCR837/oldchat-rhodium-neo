package org.spongycastle.math.ec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;

/* JADX INFO: loaded from: classes.dex */
class Tnaf {
    private static final BigInteger MINUS_ONE;
    private static final BigInteger MINUS_THREE;
    private static final BigInteger MINUS_TWO;
    public static final byte POW_2_WIDTH = 16;
    public static final byte WIDTH = 4;
    public static final ZTauElement[] alpha0;
    public static final byte[][] alpha0Tnaf;
    public static final ZTauElement[] alpha1;
    public static final byte[][] alpha1Tnaf;

    static {
        BigInteger bigInteger = ECConstants.ONE;
        BigInteger bigIntegerNegate = bigInteger.negate();
        MINUS_ONE = bigIntegerNegate;
        MINUS_TWO = ECConstants.TWO.negate();
        BigInteger bigIntegerNegate2 = ECConstants.THREE.negate();
        MINUS_THREE = bigIntegerNegate2;
        BigInteger bigInteger2 = ECConstants.ZERO;
        alpha0 = new ZTauElement[]{null, new ZTauElement(bigInteger, bigInteger2), null, new ZTauElement(bigIntegerNegate2, bigIntegerNegate), null, new ZTauElement(bigIntegerNegate, bigIntegerNegate), null, new ZTauElement(bigInteger, bigIntegerNegate), null};
        alpha0Tnaf = new byte[][]{null, new byte[]{1}, null, new byte[]{-1, 0, 1}, null, new byte[]{1, 0, 1}, null, new byte[]{-1, 0, 0, 1}};
        alpha1 = new ZTauElement[]{null, new ZTauElement(bigInteger, bigInteger2), null, new ZTauElement(bigIntegerNegate2, bigInteger), null, new ZTauElement(bigIntegerNegate, bigInteger), null, new ZTauElement(bigInteger, bigInteger), null};
        alpha1Tnaf = new byte[][]{null, new byte[]{1}, null, new byte[]{-1, 0, 1}, null, new byte[]{1, 0, 1}, null, new byte[]{-1, 0, 0, -1}};
    }

    public static SimpleBigDecimal approximateDivisionByN(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, byte b2, int i2, int i3) {
        BigInteger bigIntegerMultiply = bigInteger2.multiply(bigInteger.shiftRight(((i2 - r0) - 2) + b2));
        BigInteger bigIntegerAdd = bigIntegerMultiply.add(bigInteger3.multiply(bigIntegerMultiply.shiftRight(i2)));
        int i4 = (((i2 + 5) / 2) + i3) - i3;
        BigInteger bigIntegerShiftRight = bigIntegerAdd.shiftRight(i4);
        if (bigIntegerAdd.testBit(i4 - 1)) {
            bigIntegerShiftRight = bigIntegerShiftRight.add(ECConstants.ONE);
        }
        return new SimpleBigDecimal(bigIntegerShiftRight, i3);
    }

    public static BigInteger[] getLucas(byte b2, int i2, boolean z2) {
        BigInteger bigInteger;
        BigInteger bigIntegerSubtract;
        if (b2 != 1 && b2 != -1) {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
        if (z2) {
            bigInteger = ECConstants.TWO;
            bigIntegerSubtract = BigInteger.valueOf(b2);
        } else {
            bigInteger = ECConstants.ZERO;
            bigIntegerSubtract = ECConstants.ONE;
        }
        int i3 = 1;
        while (i3 < i2) {
            i3++;
            BigInteger bigInteger2 = bigIntegerSubtract;
            bigIntegerSubtract = (b2 == 1 ? bigIntegerSubtract : bigIntegerSubtract.negate()).subtract(bigInteger.shiftLeft(1));
            bigInteger = bigInteger2;
        }
        return new BigInteger[]{bigInteger, bigIntegerSubtract};
    }

    public static byte getMu(int i2) {
        return (byte) (i2 == 0 ? -1 : 1);
    }

    public static ECPoint.AbstractF2m[] getPreComp(ECPoint.AbstractF2m abstractF2m, byte b2) {
        byte[][] bArr = b2 == 0 ? alpha0Tnaf : alpha1Tnaf;
        ECPoint.AbstractF2m[] abstractF2mArr = new ECPoint.AbstractF2m[(bArr.length + 1) >>> 1];
        abstractF2mArr[0] = abstractF2m;
        int length = bArr.length;
        for (int i2 = 3; i2 < length; i2 += 2) {
            abstractF2mArr[i2 >>> 1] = multiplyFromTnaf(abstractF2m, bArr[i2]);
        }
        abstractF2m.getCurve().normalizeAll(abstractF2mArr);
        return abstractF2mArr;
    }

    public static int getShiftsForCofactor(BigInteger bigInteger) {
        if (bigInteger != null) {
            if (bigInteger.equals(ECConstants.TWO)) {
                return 1;
            }
            if (bigInteger.equals(ECConstants.FOUR)) {
                return 2;
            }
        }
        throw new IllegalArgumentException("h (Cofactor) must be 2 or 4");
    }

    public static BigInteger[] getSi(ECCurve.AbstractF2m abstractF2m) {
        if (!abstractF2m.isKoblitz()) {
            throw new IllegalArgumentException("si is defined for Koblitz curves only");
        }
        int fieldSize = abstractF2m.getFieldSize();
        int iIntValue = abstractF2m.getA().toBigInteger().intValue();
        byte mu = getMu(iIntValue);
        int shiftsForCofactor = getShiftsForCofactor(abstractF2m.getCofactor());
        BigInteger[] lucas = getLucas(mu, (fieldSize + 3) - iIntValue, false);
        if (mu == 1) {
            lucas[0] = lucas[0].negate();
            lucas[1] = lucas[1].negate();
        }
        BigInteger bigInteger = ECConstants.ONE;
        return new BigInteger[]{bigInteger.add(lucas[1]).shiftRight(shiftsForCofactor), bigInteger.add(lucas[0]).shiftRight(shiftsForCofactor).negate()};
    }

    public static BigInteger getTw(byte b2, int i2) {
        if (i2 == 4) {
            return b2 == 1 ? BigInteger.valueOf(6L) : BigInteger.valueOf(10L);
        }
        BigInteger[] lucas = getLucas(b2, i2, false);
        BigInteger bit = ECConstants.ZERO.setBit(i2);
        return ECConstants.TWO.multiply(lucas[0]).multiply(lucas[1].modInverse(bit)).mod(bit);
    }

    public static ECPoint.AbstractF2m multiplyFromTnaf(ECPoint.AbstractF2m abstractF2m, byte[] bArr) {
        ECPoint.AbstractF2m abstractF2m2 = (ECPoint.AbstractF2m) abstractF2m.getCurve().getInfinity();
        ECPoint.AbstractF2m abstractF2m3 = (ECPoint.AbstractF2m) abstractF2m.negate();
        int i2 = 0;
        for (int length = bArr.length - 1; length >= 0; length--) {
            i2++;
            byte b2 = bArr[length];
            if (b2 != 0) {
                abstractF2m2 = (ECPoint.AbstractF2m) abstractF2m2.tauPow(i2).add(b2 > 0 ? abstractF2m : abstractF2m3);
                i2 = 0;
            }
        }
        return i2 > 0 ? abstractF2m2.tauPow(i2) : abstractF2m2;
    }

    public static ECPoint.AbstractF2m multiplyRTnaf(ECPoint.AbstractF2m abstractF2m, BigInteger bigInteger) {
        ECCurve.AbstractF2m abstractF2m2 = (ECCurve.AbstractF2m) abstractF2m.getCurve();
        int fieldSize = abstractF2m2.getFieldSize();
        int iIntValue = abstractF2m2.getA().toBigInteger().intValue();
        return multiplyTnaf(abstractF2m, partModReduction(bigInteger, fieldSize, (byte) iIntValue, abstractF2m2.getSi(), getMu(iIntValue), (byte) 10));
    }

    public static ECPoint.AbstractF2m multiplyTnaf(ECPoint.AbstractF2m abstractF2m, ZTauElement zTauElement) {
        return multiplyFromTnaf(abstractF2m, tauAdicNaf(getMu(((ECCurve.AbstractF2m) abstractF2m.getCurve()).getA()), zTauElement));
    }

    public static BigInteger norm(byte b2, ZTauElement zTauElement) {
        BigInteger bigInteger = zTauElement.f7508u;
        BigInteger bigIntegerMultiply = bigInteger.multiply(bigInteger);
        BigInteger bigIntegerMultiply2 = zTauElement.f7508u.multiply(zTauElement.f7509v);
        BigInteger bigInteger2 = zTauElement.f7509v;
        BigInteger bigIntegerShiftLeft = bigInteger2.multiply(bigInteger2).shiftLeft(1);
        if (b2 == 1) {
            return bigIntegerMultiply.add(bigIntegerMultiply2).add(bigIntegerShiftLeft);
        }
        if (b2 == -1) {
            return bigIntegerMultiply.subtract(bigIntegerMultiply2).add(bigIntegerShiftLeft);
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    public static ZTauElement partModReduction(BigInteger bigInteger, int i2, byte b2, BigInteger[] bigIntegerArr, byte b3, byte b4) {
        BigInteger bigIntegerAdd = b3 == 1 ? bigIntegerArr[0].add(bigIntegerArr[1]) : bigIntegerArr[0].subtract(bigIntegerArr[1]);
        BigInteger bigInteger2 = getLucas(b3, i2, true)[1];
        ZTauElement zTauElementRound = round(approximateDivisionByN(bigInteger, bigIntegerArr[0], bigInteger2, b2, i2, b4), approximateDivisionByN(bigInteger, bigIntegerArr[1], bigInteger2, b2, i2, b4), b3);
        return new ZTauElement(bigInteger.subtract(bigIntegerAdd.multiply(zTauElementRound.f7508u)).subtract(BigInteger.valueOf(2L).multiply(bigIntegerArr[1]).multiply(zTauElementRound.f7509v)), bigIntegerArr[1].multiply(zTauElementRound.f7508u).subtract(bigIntegerArr[0].multiply(zTauElementRound.f7509v)));
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0066, code lost:
    
        if (r5.compareTo(org.spongycastle.math.ec.Tnaf.MINUS_ONE) < 0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x007f, code lost:
    
        if (r5.compareTo(r9) >= 0) goto L31;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.spongycastle.math.ec.ZTauElement round(org.spongycastle.math.ec.SimpleBigDecimal r8, org.spongycastle.math.ec.SimpleBigDecimal r9, byte r10) {
        /*
            int r0 = r8.getScale()
            int r1 = r9.getScale()
            if (r1 != r0) goto La6
            r0 = -1
            r1 = 1
            if (r10 == r1) goto L19
            if (r10 != r0) goto L11
            goto L19
        L11:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "mu must be 1 or -1"
            r8.<init>(r9)
            throw r8
        L19:
            java.math.BigInteger r2 = r8.round()
            java.math.BigInteger r3 = r9.round()
            org.spongycastle.math.ec.SimpleBigDecimal r8 = r8.subtract(r2)
            org.spongycastle.math.ec.SimpleBigDecimal r9 = r9.subtract(r3)
            org.spongycastle.math.ec.SimpleBigDecimal r4 = r8.add(r8)
            if (r10 != r1) goto L34
            org.spongycastle.math.ec.SimpleBigDecimal r4 = r4.add(r9)
            goto L38
        L34:
            org.spongycastle.math.ec.SimpleBigDecimal r4 = r4.subtract(r9)
        L38:
            org.spongycastle.math.ec.SimpleBigDecimal r5 = r9.add(r9)
            org.spongycastle.math.ec.SimpleBigDecimal r5 = r5.add(r9)
            org.spongycastle.math.ec.SimpleBigDecimal r9 = r5.add(r9)
            if (r10 != r1) goto L4f
            org.spongycastle.math.ec.SimpleBigDecimal r5 = r8.subtract(r5)
            org.spongycastle.math.ec.SimpleBigDecimal r8 = r8.add(r9)
            goto L57
        L4f:
            org.spongycastle.math.ec.SimpleBigDecimal r5 = r8.add(r5)
            org.spongycastle.math.ec.SimpleBigDecimal r8 = r8.subtract(r9)
        L57:
            java.math.BigInteger r9 = org.spongycastle.math.ec.ECConstants.ONE
            int r6 = r4.compareTo(r9)
            r7 = 0
            if (r6 < 0) goto L69
            java.math.BigInteger r6 = org.spongycastle.math.ec.Tnaf.MINUS_ONE
            int r6 = r5.compareTo(r6)
            if (r6 >= 0) goto L73
            goto L71
        L69:
            java.math.BigInteger r1 = org.spongycastle.math.ec.ECConstants.TWO
            int r1 = r8.compareTo(r1)
            if (r1 < 0) goto L72
        L71:
            r7 = r10
        L72:
            r1 = 0
        L73:
            java.math.BigInteger r6 = org.spongycastle.math.ec.Tnaf.MINUS_ONE
            int r4 = r4.compareTo(r6)
            if (r4 >= 0) goto L85
            int r8 = r5.compareTo(r9)
            if (r8 < 0) goto L8e
        L81:
            int r8 = -r10
            byte r7 = (byte) r8
        L83:
            r0 = r1
            goto L8e
        L85:
            java.math.BigInteger r9 = org.spongycastle.math.ec.Tnaf.MINUS_TWO
            int r8 = r8.compareTo(r9)
            if (r8 >= 0) goto L83
            goto L81
        L8e:
            long r8 = (long) r0
            java.math.BigInteger r8 = java.math.BigInteger.valueOf(r8)
            java.math.BigInteger r8 = r2.add(r8)
            long r9 = (long) r7
            java.math.BigInteger r9 = java.math.BigInteger.valueOf(r9)
            java.math.BigInteger r9 = r3.add(r9)
            org.spongycastle.math.ec.ZTauElement r10 = new org.spongycastle.math.ec.ZTauElement
            r10.<init>(r8, r9)
            return r10
        La6:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "lambda0 and lambda1 do not have same scale"
            r8.<init>(r9)
            goto Laf
        Lae:
            throw r8
        Laf:
            goto Lae
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.math.ec.Tnaf.round(org.spongycastle.math.ec.SimpleBigDecimal, org.spongycastle.math.ec.SimpleBigDecimal, byte):org.spongycastle.math.ec.ZTauElement");
    }

    public static ECPoint.AbstractF2m tau(ECPoint.AbstractF2m abstractF2m) {
        return abstractF2m.tau();
    }

    public static byte[] tauAdicNaf(byte b2, ZTauElement zTauElement) {
        if (b2 != 1 && b2 != -1) {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
        int iBitLength = norm(b2, zTauElement).bitLength();
        byte[] bArr = new byte[iBitLength > 30 ? iBitLength + 4 : 34];
        BigInteger bigIntegerClearBit = zTauElement.f7508u;
        BigInteger bigInteger = zTauElement.f7509v;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            BigInteger bigInteger2 = ECConstants.ZERO;
            if (bigIntegerClearBit.equals(bigInteger2) && bigInteger.equals(bigInteger2)) {
                int i4 = i2 + 1;
                byte[] bArr2 = new byte[i4];
                System.arraycopy(bArr, 0, bArr2, 0, i4);
                return bArr2;
            }
            if (bigIntegerClearBit.testBit(0)) {
                byte bIntValue = (byte) ECConstants.TWO.subtract(bigIntegerClearBit.subtract(bigInteger.shiftLeft(1)).mod(ECConstants.FOUR)).intValue();
                bArr[i3] = bIntValue;
                bigIntegerClearBit = bIntValue == 1 ? bigIntegerClearBit.clearBit(0) : bigIntegerClearBit.add(ECConstants.ONE);
                i2 = i3;
            } else {
                bArr[i3] = 0;
            }
            BigInteger bigIntegerShiftRight = bigIntegerClearBit.shiftRight(1);
            BigInteger bigIntegerAdd = b2 == 1 ? bigInteger.add(bigIntegerShiftRight) : bigInteger.subtract(bigIntegerShiftRight);
            BigInteger bigIntegerNegate = bigIntegerClearBit.shiftRight(1).negate();
            i3++;
            bigIntegerClearBit = bigIntegerAdd;
            bigInteger = bigIntegerNegate;
        }
    }

    public static byte[] tauAdicWNaf(byte b2, ZTauElement zTauElement, byte b3, BigInteger bigInteger, BigInteger bigInteger2, ZTauElement[] zTauElementArr) {
        boolean z2;
        if (b2 != 1 && b2 != -1) {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
        int iBitLength = norm(b2, zTauElement).bitLength();
        byte[] bArr = new byte[iBitLength > 30 ? iBitLength + 4 + b3 : b3 + 34];
        BigInteger bigIntegerShiftRight = bigInteger.shiftRight(1);
        BigInteger bigIntegerAdd = zTauElement.f7508u;
        BigInteger bigIntegerAdd2 = zTauElement.f7509v;
        int i2 = 0;
        while (true) {
            BigInteger bigInteger3 = ECConstants.ZERO;
            if (bigIntegerAdd.equals(bigInteger3) && bigIntegerAdd2.equals(bigInteger3)) {
                return bArr;
            }
            if (bigIntegerAdd.testBit(0)) {
                BigInteger bigIntegerMod = bigIntegerAdd.add(bigIntegerAdd2.multiply(bigInteger2)).mod(bigInteger);
                byte bIntValue = (byte) (bigIntegerMod.compareTo(bigIntegerShiftRight) >= 0 ? bigIntegerMod.subtract(bigInteger).intValue() : bigIntegerMod.intValue());
                bArr[i2] = bIntValue;
                if (bIntValue < 0) {
                    bIntValue = (byte) (-bIntValue);
                    z2 = false;
                } else {
                    z2 = true;
                }
                if (z2) {
                    bigIntegerAdd = bigIntegerAdd.subtract(zTauElementArr[bIntValue].f7508u);
                    bigIntegerAdd2 = bigIntegerAdd2.subtract(zTauElementArr[bIntValue].f7509v);
                } else {
                    bigIntegerAdd = bigIntegerAdd.add(zTauElementArr[bIntValue].f7508u);
                    bigIntegerAdd2 = bigIntegerAdd2.add(zTauElementArr[bIntValue].f7509v);
                }
            } else {
                bArr[i2] = 0;
            }
            BigInteger bigIntegerAdd3 = b2 == 1 ? bigIntegerAdd2.add(bigIntegerAdd.shiftRight(1)) : bigIntegerAdd2.subtract(bigIntegerAdd.shiftRight(1));
            BigInteger bigIntegerNegate = bigIntegerAdd.shiftRight(1).negate();
            i2++;
            bigIntegerAdd = bigIntegerAdd3;
            bigIntegerAdd2 = bigIntegerNegate;
        }
    }

    public static byte getMu(ECCurve.AbstractF2m abstractF2m) {
        if (abstractF2m.isKoblitz()) {
            return abstractF2m.getA().isZero() ? (byte) -1 : (byte) 1;
        }
        throw new IllegalArgumentException("No Koblitz curve (ABC), TNAF multiplication not possible");
    }

    public static byte getMu(ECFieldElement eCFieldElement) {
        return (byte) (eCFieldElement.isZero() ? -1 : 1);
    }

    public static SimpleBigDecimal norm(byte b2, SimpleBigDecimal simpleBigDecimal, SimpleBigDecimal simpleBigDecimal2) {
        SimpleBigDecimal simpleBigDecimalMultiply = simpleBigDecimal.multiply(simpleBigDecimal);
        SimpleBigDecimal simpleBigDecimalMultiply2 = simpleBigDecimal.multiply(simpleBigDecimal2);
        SimpleBigDecimal simpleBigDecimalShiftLeft = simpleBigDecimal2.multiply(simpleBigDecimal2).shiftLeft(1);
        if (b2 == 1) {
            return simpleBigDecimalMultiply.add(simpleBigDecimalMultiply2).add(simpleBigDecimalShiftLeft);
        }
        if (b2 == -1) {
            return simpleBigDecimalMultiply.subtract(simpleBigDecimalMultiply2).add(simpleBigDecimalShiftLeft);
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    public static BigInteger[] getSi(int i2, int i3, BigInteger bigInteger) {
        byte mu = getMu(i3);
        int shiftsForCofactor = getShiftsForCofactor(bigInteger);
        BigInteger[] lucas = getLucas(mu, (i2 + 3) - i3, false);
        if (mu == 1) {
            lucas[0] = lucas[0].negate();
            lucas[1] = lucas[1].negate();
        }
        BigInteger bigInteger2 = ECConstants.ONE;
        return new BigInteger[]{bigInteger2.add(lucas[1]).shiftRight(shiftsForCofactor), bigInteger2.add(lucas[0]).shiftRight(shiftsForCofactor).negate()};
    }
}
