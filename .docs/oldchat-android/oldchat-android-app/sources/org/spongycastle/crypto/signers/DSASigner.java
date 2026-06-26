package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.params.DSAKeyParameters;
import org.spongycastle.crypto.params.DSAParameters;
import org.spongycastle.crypto.params.DSAPrivateKeyParameters;
import org.spongycastle.crypto.params.DSAPublicKeyParameters;

/* JADX INFO: loaded from: classes.dex */
public class DSASigner implements DSA {
    private final DSAKCalculator kCalculator;
    private DSAKeyParameters key;
    private SecureRandom random;

    public DSASigner() {
        this.kCalculator = new RandomDSAKCalculator();
    }

    private BigInteger calculateE(BigInteger bigInteger, byte[] bArr) {
        if (bigInteger.bitLength() >= bArr.length * 8) {
            return new BigInteger(1, bArr);
        }
        int iBitLength = bigInteger.bitLength() / 8;
        byte[] bArr2 = new byte[iBitLength];
        System.arraycopy(bArr, 0, bArr2, 0, iBitLength);
        return new BigInteger(1, bArr2);
    }

    private BigInteger getRandomizer(BigInteger bigInteger, SecureRandom secureRandom) {
        if (secureRandom == null) {
            secureRandom = new SecureRandom();
        }
        return new BigInteger(7, secureRandom).add(BigInteger.valueOf(128L)).multiply(bigInteger);
    }

    @Override // org.spongycastle.crypto.DSA
    public BigInteger[] generateSignature(byte[] bArr) {
        DSAParameters parameters = this.key.getParameters();
        BigInteger q2 = parameters.getQ();
        BigInteger bigIntegerCalculateE = calculateE(q2, bArr);
        BigInteger x2 = ((DSAPrivateKeyParameters) this.key).getX();
        if (this.kCalculator.isDeterministic()) {
            this.kCalculator.init(q2, x2, bArr);
        } else {
            this.kCalculator.init(q2, this.random);
        }
        BigInteger bigIntegerNextK = this.kCalculator.nextK();
        BigInteger bigIntegerMod = parameters.getG().modPow(bigIntegerNextK.add(getRandomizer(q2, this.random)), parameters.getP()).mod(q2);
        return new BigInteger[]{bigIntegerMod, bigIntegerNextK.modInverse(q2).multiply(bigIntegerCalculateE.add(x2.multiply(bigIntegerMod))).mod(q2)};
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
            org.spongycastle.crypto.params.DSAPrivateKeyParameters r0 = (org.spongycastle.crypto.params.DSAPrivateKeyParameters) r0
            r1.key = r0
            java.security.SecureRandom r3 = r3.getRandom()
            goto L1f
        L15:
            org.spongycastle.crypto.params.DSAPrivateKeyParameters r3 = (org.spongycastle.crypto.params.DSAPrivateKeyParameters) r3
            r1.key = r3
            goto L1e
        L1a:
            org.spongycastle.crypto.params.DSAPublicKeyParameters r3 = (org.spongycastle.crypto.params.DSAPublicKeyParameters) r3
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
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.signers.DSASigner.init(boolean, org.spongycastle.crypto.CipherParameters):void");
    }

    public SecureRandom initSecureRandom(boolean z2, SecureRandom secureRandom) {
        if (z2) {
            return secureRandom != null ? secureRandom : new SecureRandom();
        }
        return null;
    }

    @Override // org.spongycastle.crypto.DSA
    public boolean verifySignature(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        DSAParameters parameters = this.key.getParameters();
        BigInteger q2 = parameters.getQ();
        BigInteger bigIntegerCalculateE = calculateE(q2, bArr);
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0L);
        if (bigIntegerValueOf.compareTo(bigInteger) >= 0 || q2.compareTo(bigInteger) <= 0 || bigIntegerValueOf.compareTo(bigInteger2) >= 0 || q2.compareTo(bigInteger2) <= 0) {
            return false;
        }
        BigInteger bigIntegerModInverse = bigInteger2.modInverse(q2);
        BigInteger bigIntegerMod = bigIntegerCalculateE.multiply(bigIntegerModInverse).mod(q2);
        BigInteger bigIntegerMod2 = bigInteger.multiply(bigIntegerModInverse).mod(q2);
        BigInteger p2 = parameters.getP();
        return parameters.getG().modPow(bigIntegerMod, p2).multiply(((DSAPublicKeyParameters) this.key).getY().modPow(bigIntegerMod2, p2)).mod(p2).mod(q2).equals(bigInteger);
    }

    public DSASigner(DSAKCalculator dSAKCalculator) {
        this.kCalculator = dSAKCalculator;
    }
}
