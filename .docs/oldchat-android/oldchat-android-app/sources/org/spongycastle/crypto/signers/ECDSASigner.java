package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.math.ec.ECAlgorithms;
import org.spongycastle.math.ec.ECConstants;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.ec.ECMultiplier;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.ec.FixedPointCombMultiplier;

/* JADX INFO: loaded from: classes.dex */
public class ECDSASigner implements ECConstants, DSA {
    private final DSAKCalculator kCalculator;
    private ECKeyParameters key;
    private SecureRandom random;

    public ECDSASigner() {
        this.kCalculator = new RandomDSAKCalculator();
    }

    public BigInteger calculateE(BigInteger bigInteger, byte[] bArr) {
        int iBitLength = bigInteger.bitLength();
        int length = bArr.length * 8;
        BigInteger bigInteger2 = new BigInteger(1, bArr);
        return iBitLength < length ? bigInteger2.shiftRight(length - iBitLength) : bigInteger2;
    }

    public ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    @Override // org.spongycastle.crypto.DSA
    public BigInteger[] generateSignature(byte[] bArr) {
        ECDomainParameters parameters = this.key.getParameters();
        BigInteger n2 = parameters.getN();
        BigInteger bigIntegerCalculateE = calculateE(n2, bArr);
        BigInteger d2 = ((ECPrivateKeyParameters) this.key).getD();
        if (this.kCalculator.isDeterministic()) {
            this.kCalculator.init(n2, d2, bArr);
        } else {
            this.kCalculator.init(n2, this.random);
        }
        ECMultiplier eCMultiplierCreateBasePointMultiplier = createBasePointMultiplier();
        while (true) {
            BigInteger bigIntegerNextK = this.kCalculator.nextK();
            BigInteger bigIntegerMod = eCMultiplierCreateBasePointMultiplier.multiply(parameters.getG(), bigIntegerNextK).normalize().getAffineXCoord().toBigInteger().mod(n2);
            BigInteger bigInteger = ECConstants.ZERO;
            if (!bigIntegerMod.equals(bigInteger)) {
                BigInteger bigIntegerMod2 = bigIntegerNextK.modInverse(n2).multiply(bigIntegerCalculateE.add(d2.multiply(bigIntegerMod))).mod(n2);
                if (!bigIntegerMod2.equals(bigInteger)) {
                    return new BigInteger[]{bigIntegerMod, bigIntegerMod2};
                }
            }
        }
    }

    public ECFieldElement getDenominator(int i2, ECPoint eCPoint) {
        if (i2 != 1) {
            if (i2 == 2 || i2 == 3 || i2 == 4) {
                return eCPoint.getZCoord(0).square();
            }
            if (i2 != 6 && i2 != 7) {
                return null;
            }
        }
        return eCPoint.getZCoord(0);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002b  */
    @Override // org.spongycastle.crypto.DSA
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void init(boolean r2, org.spongycastle.crypto.CipherParameters r3) {
        /*
            r1 = this;
            if (r2 == 0) goto L1a
            boolean r0 = r3 instanceof org.spongycastle.crypto.params.ParametersWithRandom
            if (r0 == 0) goto L15
            org.spongycastle.crypto.params.ParametersWithRandom r3 = (org.spongycastle.crypto.params.ParametersWithRandom) r3
            org.spongycastle.crypto.CipherParameters r0 = r3.getParameters()
            org.spongycastle.crypto.params.ECPrivateKeyParameters r0 = (org.spongycastle.crypto.params.ECPrivateKeyParameters) r0
            r1.key = r0
            java.security.SecureRandom r3 = r3.getRandom()
            goto L1f
        L15:
            org.spongycastle.crypto.params.ECPrivateKeyParameters r3 = (org.spongycastle.crypto.params.ECPrivateKeyParameters) r3
            r1.key = r3
            goto L1e
        L1a:
            org.spongycastle.crypto.params.ECPublicKeyParameters r3 = (org.spongycastle.crypto.params.ECPublicKeyParameters) r3
            r1.key = r3
        L1e:
            r3 = 0
        L1f:
            if (r2 == 0) goto L2b
            org.spongycastle.crypto.signers.DSAKCalculator r2 = r1.kCalculator
            boolean r2 = r2.isDeterministic()
            if (r2 != 0) goto L2b
            r2 = 1
            goto L2c
        L2b:
            r2 = 0
        L2c:
            java.security.SecureRandom r2 = r1.initSecureRandom(r2, r3)
            r1.random = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.signers.ECDSASigner.init(boolean, org.spongycastle.crypto.CipherParameters):void");
    }

    public SecureRandom initSecureRandom(boolean z2, SecureRandom secureRandom) {
        if (z2) {
            return secureRandom != null ? secureRandom : new SecureRandom();
        }
        return null;
    }

    @Override // org.spongycastle.crypto.DSA
    public boolean verifySignature(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger cofactor;
        ECFieldElement denominator;
        ECDomainParameters parameters = this.key.getParameters();
        BigInteger n2 = parameters.getN();
        BigInteger bigIntegerCalculateE = calculateE(n2, bArr);
        BigInteger bigInteger3 = ECConstants.ONE;
        if (bigInteger.compareTo(bigInteger3) < 0 || bigInteger.compareTo(n2) >= 0 || bigInteger2.compareTo(bigInteger3) < 0 || bigInteger2.compareTo(n2) >= 0) {
            return false;
        }
        BigInteger bigIntegerModInverse = bigInteger2.modInverse(n2);
        ECPoint eCPointSumOfTwoMultiplies = ECAlgorithms.sumOfTwoMultiplies(parameters.getG(), bigIntegerCalculateE.multiply(bigIntegerModInverse).mod(n2), ((ECPublicKeyParameters) this.key).getQ(), bigInteger.multiply(bigIntegerModInverse).mod(n2));
        if (eCPointSumOfTwoMultiplies.isInfinity()) {
            return false;
        }
        ECCurve curve = eCPointSumOfTwoMultiplies.getCurve();
        if (curve == null || (cofactor = curve.getCofactor()) == null || cofactor.compareTo(ECConstants.EIGHT) > 0 || (denominator = getDenominator(curve.getCoordinateSystem(), eCPointSumOfTwoMultiplies)) == null || denominator.isZero()) {
            return eCPointSumOfTwoMultiplies.normalize().getAffineXCoord().toBigInteger().mod(n2).equals(bigInteger);
        }
        ECFieldElement xCoord = eCPointSumOfTwoMultiplies.getXCoord();
        while (curve.isValidFieldElement(bigInteger)) {
            if (curve.fromBigInteger(bigInteger).multiply(denominator).equals(xCoord)) {
                return true;
            }
            bigInteger = bigInteger.add(n2);
        }
        return false;
    }

    public ECDSASigner(DSAKCalculator dSAKCalculator) {
        this.kCalculator = dSAKCalculator;
    }
}
