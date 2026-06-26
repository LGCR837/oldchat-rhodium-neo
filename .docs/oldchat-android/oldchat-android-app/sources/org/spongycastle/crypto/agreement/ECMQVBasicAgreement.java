package org.spongycastle.crypto.agreement;

import java.math.BigInteger;
import org.spongycastle.crypto.BasicAgreement;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.MQVPrivateParameters;
import org.spongycastle.crypto.params.MQVPublicParameters;
import org.spongycastle.math.ec.ECAlgorithms;
import org.spongycastle.math.ec.ECConstants;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.util.Properties;

/* JADX INFO: loaded from: classes.dex */
public class ECMQVBasicAgreement implements BasicAgreement {
    MQVPrivateParameters privParams;

    private ECPoint calculateMqvAgreement(ECDomainParameters eCDomainParameters, ECPrivateKeyParameters eCPrivateKeyParameters, ECPrivateKeyParameters eCPrivateKeyParameters2, ECPublicKeyParameters eCPublicKeyParameters, ECPublicKeyParameters eCPublicKeyParameters2, ECPublicKeyParameters eCPublicKeyParameters3) {
        BigInteger n2 = eCDomainParameters.getN();
        int iBitLength = (n2.bitLength() + 1) / 2;
        BigInteger bigIntegerShiftLeft = ECConstants.ONE.shiftLeft(iBitLength);
        ECCurve curve = eCDomainParameters.getCurve();
        ECPoint[] eCPointArr = {ECAlgorithms.importPoint(curve, eCPublicKeyParameters.getQ()), ECAlgorithms.importPoint(curve, eCPublicKeyParameters2.getQ()), ECAlgorithms.importPoint(curve, eCPublicKeyParameters3.getQ())};
        curve.normalizeAll(eCPointArr);
        ECPoint eCPoint = eCPointArr[0];
        ECPoint eCPoint2 = eCPointArr[1];
        ECPoint eCPoint3 = eCPointArr[2];
        BigInteger bigIntegerMod = eCPrivateKeyParameters.getD().multiply(eCPoint.getAffineXCoord().toBigInteger().mod(bigIntegerShiftLeft).setBit(iBitLength)).add(eCPrivateKeyParameters2.getD()).mod(n2);
        BigInteger bit = eCPoint3.getAffineXCoord().toBigInteger().mod(bigIntegerShiftLeft).setBit(iBitLength);
        BigInteger bigIntegerMod2 = eCDomainParameters.getH().multiply(bigIntegerMod).mod(n2);
        return ECAlgorithms.sumOfTwoMultiplies(eCPoint2, bit.multiply(bigIntegerMod2).mod(n2), eCPoint3, bigIntegerMod2);
    }

    @Override // org.spongycastle.crypto.BasicAgreement
    public BigInteger calculateAgreement(CipherParameters cipherParameters) {
        if (Properties.isOverrideSet("org.spongycastle.ec.disable_mqv")) {
            throw new IllegalStateException("ECMQV explicitly disabled");
        }
        MQVPublicParameters mQVPublicParameters = (MQVPublicParameters) cipherParameters;
        ECPrivateKeyParameters staticPrivateKey = this.privParams.getStaticPrivateKey();
        ECDomainParameters parameters = staticPrivateKey.getParameters();
        if (!parameters.equals(mQVPublicParameters.getStaticPublicKey().getParameters())) {
            throw new IllegalStateException("ECMQV public key components have wrong domain parameters");
        }
        ECPoint eCPointNormalize = calculateMqvAgreement(parameters, staticPrivateKey, this.privParams.getEphemeralPrivateKey(), this.privParams.getEphemeralPublicKey(), mQVPublicParameters.getStaticPublicKey(), mQVPublicParameters.getEphemeralPublicKey()).normalize();
        if (eCPointNormalize.isInfinity()) {
            throw new IllegalStateException("Infinity is not a valid agreement value for MQV");
        }
        return eCPointNormalize.getAffineXCoord().toBigInteger();
    }

    @Override // org.spongycastle.crypto.BasicAgreement
    public int getFieldSize() {
        return (this.privParams.getStaticPrivateKey().getParameters().getCurve().getFieldSize() + 7) / 8;
    }

    @Override // org.spongycastle.crypto.BasicAgreement
    public void init(CipherParameters cipherParameters) {
        this.privParams = (MQVPrivateParameters) cipherParameters;
    }
}
