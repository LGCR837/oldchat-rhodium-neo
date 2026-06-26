package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.math.ec.WNafUtil;
import org.spongycastle.util.BigIntegers;

/* JADX INFO: loaded from: classes.dex */
class DHKeyGeneratorHelper {
    static final DHKeyGeneratorHelper INSTANCE = new DHKeyGeneratorHelper();
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    private DHKeyGeneratorHelper() {
    }

    public BigInteger calculatePrivate(DHParameters dHParameters, SecureRandom secureRandom) {
        BigInteger bigIntegerCreateRandomInRange;
        BigInteger bit;
        int l2 = dHParameters.getL();
        if (l2 != 0) {
            int i2 = l2 >>> 2;
            do {
                bit = new BigInteger(l2, secureRandom).setBit(l2 - 1);
            } while (WNafUtil.getNafWeight(bit) < i2);
            return bit;
        }
        BigInteger bigInteger = TWO;
        int m2 = dHParameters.getM();
        BigInteger bigIntegerShiftLeft = m2 != 0 ? ONE.shiftLeft(m2 - 1) : bigInteger;
        BigInteger q2 = dHParameters.getQ();
        if (q2 == null) {
            q2 = dHParameters.getP();
        }
        BigInteger bigIntegerSubtract = q2.subtract(bigInteger);
        int iBitLength = bigIntegerSubtract.bitLength() >>> 2;
        do {
            bigIntegerCreateRandomInRange = BigIntegers.createRandomInRange(bigIntegerShiftLeft, bigIntegerSubtract, secureRandom);
        } while (WNafUtil.getNafWeight(bigIntegerCreateRandomInRange) < iBitLength);
        return bigIntegerCreateRandomInRange;
    }

    public BigInteger calculatePublic(DHParameters dHParameters, BigInteger bigInteger) {
        return dHParameters.getG().modPow(bigInteger, dHParameters.getP());
    }
}
