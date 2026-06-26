package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SM3Digest;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithID;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.math.ec.ECConstants;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.ec.ECMultiplier;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.ec.FixedPointCombMultiplier;
import org.spongycastle.util.BigIntegers;

/* JADX INFO: loaded from: classes.dex */
public class SM2Signer implements DSA, ECConstants {
    private int curveLength;
    private ECKeyParameters ecKey;
    private ECDomainParameters ecParams;
    private final DSAKCalculator kCalculator = new RandomDSAKCalculator();
    private ECPoint pubPoint;
    private SecureRandom random;
    private byte[] userID;

    private void addFieldElement(Digest digest, ECFieldElement eCFieldElement) {
        byte[] bArrAsUnsignedByteArray = BigIntegers.asUnsignedByteArray(this.curveLength, eCFieldElement.toBigInteger());
        digest.update(bArrAsUnsignedByteArray, 0, bArrAsUnsignedByteArray.length);
    }

    private void addUserID(Digest digest, byte[] bArr) {
        int length = bArr.length * 8;
        digest.update((byte) ((length >> 8) & 255));
        digest.update((byte) (length & 255));
        digest.update(bArr, 0, bArr.length);
    }

    private byte[] getZ(Digest digest) {
        addUserID(digest, this.userID);
        addFieldElement(digest, this.ecParams.getCurve().getA());
        addFieldElement(digest, this.ecParams.getCurve().getB());
        addFieldElement(digest, this.ecParams.getG().getAffineXCoord());
        addFieldElement(digest, this.ecParams.getG().getAffineYCoord());
        addFieldElement(digest, this.pubPoint.getAffineXCoord());
        addFieldElement(digest, this.pubPoint.getAffineYCoord());
        byte[] bArr = new byte[digest.getDigestSize()];
        digest.doFinal(bArr, 0);
        return bArr;
    }

    public BigInteger calculateE(byte[] bArr) {
        return new BigInteger(1, bArr);
    }

    public ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    @Override // org.spongycastle.crypto.DSA
    public BigInteger[] generateSignature(byte[] bArr) {
        SM3Digest sM3Digest = new SM3Digest();
        byte[] z2 = getZ(sM3Digest);
        sM3Digest.update(z2, 0, z2.length);
        sM3Digest.update(bArr, 0, bArr.length);
        byte[] bArr2 = new byte[sM3Digest.getDigestSize()];
        sM3Digest.doFinal(bArr2, 0);
        BigInteger n2 = this.ecParams.getN();
        BigInteger bigIntegerCalculateE = calculateE(bArr2);
        BigInteger d2 = ((ECPrivateKeyParameters) this.ecKey).getD();
        ECMultiplier eCMultiplierCreateBasePointMultiplier = createBasePointMultiplier();
        while (true) {
            BigInteger bigIntegerNextK = this.kCalculator.nextK();
            BigInteger bigIntegerMod = bigIntegerCalculateE.add(eCMultiplierCreateBasePointMultiplier.multiply(this.ecParams.getG(), bigIntegerNextK).normalize().getAffineXCoord().toBigInteger()).mod(n2);
            BigInteger bigInteger = ECConstants.ZERO;
            if (!bigIntegerMod.equals(bigInteger) && !bigIntegerMod.add(bigIntegerNextK).equals(n2)) {
                BigInteger bigIntegerMod2 = d2.add(ECConstants.ONE).modInverse(n2).multiply(bigIntegerNextK.subtract(bigIntegerMod.multiply(d2)).mod(n2)).mod(n2);
                if (!bigIntegerMod2.equals(bigInteger)) {
                    return new BigInteger[]{bigIntegerMod, bigIntegerMod2};
                }
            }
        }
    }

    @Override // org.spongycastle.crypto.DSA
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithID) {
            ParametersWithID parametersWithID = (ParametersWithID) cipherParameters;
            CipherParameters parameters = parametersWithID.getParameters();
            this.userID = parametersWithID.getID();
            cipherParameters = parameters;
        } else {
            this.userID = new byte[0];
        }
        if (z2) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                ECKeyParameters eCKeyParameters = (ECKeyParameters) parametersWithRandom.getParameters();
                this.ecKey = eCKeyParameters;
                ECDomainParameters parameters2 = eCKeyParameters.getParameters();
                this.ecParams = parameters2;
                this.kCalculator.init(parameters2.getN(), parametersWithRandom.getRandom());
            } else {
                ECKeyParameters eCKeyParameters2 = (ECKeyParameters) cipherParameters;
                this.ecKey = eCKeyParameters2;
                ECDomainParameters parameters3 = eCKeyParameters2.getParameters();
                this.ecParams = parameters3;
                this.kCalculator.init(parameters3.getN(), new SecureRandom());
            }
            this.pubPoint = this.ecParams.getG().multiply(((ECPrivateKeyParameters) this.ecKey).getD()).normalize();
        } else {
            ECKeyParameters eCKeyParameters3 = (ECKeyParameters) cipherParameters;
            this.ecKey = eCKeyParameters3;
            this.ecParams = eCKeyParameters3.getParameters();
            this.pubPoint = ((ECPublicKeyParameters) this.ecKey).getQ();
        }
        this.curveLength = (this.ecParams.getCurve().getFieldSize() + 7) / 8;
    }

    @Override // org.spongycastle.crypto.DSA
    public boolean verifySignature(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger n2 = this.ecParams.getN();
        BigInteger bigInteger3 = ECConstants.ONE;
        if (bigInteger.compareTo(bigInteger3) < 0 || bigInteger.compareTo(n2) >= 0 || bigInteger2.compareTo(bigInteger3) < 0 || bigInteger2.compareTo(n2) >= 0) {
            return false;
        }
        ECPoint q2 = ((ECPublicKeyParameters) this.ecKey).getQ();
        SM3Digest sM3Digest = new SM3Digest();
        byte[] z2 = getZ(sM3Digest);
        sM3Digest.update(z2, 0, z2.length);
        sM3Digest.update(bArr, 0, bArr.length);
        byte[] bArr2 = new byte[sM3Digest.getDigestSize()];
        sM3Digest.doFinal(bArr2, 0);
        BigInteger bigIntegerCalculateE = calculateE(bArr2);
        BigInteger bigIntegerMod = bigInteger.add(bigInteger2).mod(n2);
        if (bigIntegerMod.equals(ECConstants.ZERO)) {
            return false;
        }
        return bigInteger.equals(bigIntegerCalculateE.add(this.ecParams.getG().multiply(bigInteger2).add(q2.multiply(bigIntegerMod)).normalize().getAffineXCoord().toBigInteger()).mod(n2));
    }
}
