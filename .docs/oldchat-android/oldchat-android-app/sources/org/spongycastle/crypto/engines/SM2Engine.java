package org.spongycastle.crypto.engines;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.digests.SM3Digest;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.math.ec.ECConstants;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

/* JADX INFO: loaded from: classes.dex */
public class SM2Engine {
    private int curveLength;
    private final Digest digest;
    private ECKeyParameters ecKey;
    private ECDomainParameters ecParams;
    private boolean forEncryption;
    private SecureRandom random;

    public SM2Engine() {
        this(new SM3Digest());
    }

    private void addFieldElement(Digest digest, ECFieldElement eCFieldElement) {
        byte[] bArrAsUnsignedByteArray = BigIntegers.asUnsignedByteArray(this.curveLength, eCFieldElement.toBigInteger());
        digest.update(bArrAsUnsignedByteArray, 0, bArrAsUnsignedByteArray.length);
    }

    private void clearBlock(byte[] bArr) {
        for (int i2 = 0; i2 != bArr.length; i2++) {
            bArr[i2] = 0;
        }
    }

    private byte[] decrypt(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        int i4 = (this.curveLength * 2) + 1;
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, i2, bArr2, 0, i4);
        ECPoint eCPointDecodePoint = this.ecParams.getCurve().decodePoint(bArr2);
        if (eCPointDecodePoint.multiply(this.ecParams.getH()).isInfinity()) {
            throw new InvalidCipherTextException("[h]C1 at infinity");
        }
        ECPoint eCPointNormalize = eCPointDecodePoint.multiply(((ECPrivateKeyParameters) this.ecKey).getD()).normalize();
        int digestSize = (i3 - i4) - this.digest.getDigestSize();
        byte[] bArr3 = new byte[digestSize];
        System.arraycopy(bArr, i2 + i4, bArr3, 0, digestSize);
        kdf(this.digest, eCPointNormalize, bArr3);
        int digestSize2 = this.digest.getDigestSize();
        byte[] bArr4 = new byte[digestSize2];
        addFieldElement(this.digest, eCPointNormalize.getAffineXCoord());
        this.digest.update(bArr3, 0, digestSize);
        addFieldElement(this.digest, eCPointNormalize.getAffineYCoord());
        this.digest.doFinal(bArr4, 0);
        int i5 = 0;
        for (int i6 = 0; i6 != digestSize2; i6++) {
            i5 |= bArr4[i6] ^ bArr[(i4 + digestSize) + i6];
        }
        clearBlock(bArr2);
        clearBlock(bArr4);
        if (i5 == 0) {
            return bArr3;
        }
        clearBlock(bArr3);
        throw new InvalidCipherTextException("invalid cipher text");
    }

    private byte[] encrypt(byte[] bArr, int i2, int i3) {
        byte[] encoded;
        ECPoint eCPointNormalize;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        do {
            BigInteger bigIntegerNextK = nextK();
            encoded = this.ecParams.getG().multiply(bigIntegerNextK).normalize().getEncoded(false);
            eCPointNormalize = ((ECPublicKeyParameters) this.ecKey).getQ().multiply(bigIntegerNextK).normalize();
            kdf(this.digest, eCPointNormalize, bArr2);
        } while (notEncrypted(bArr2, bArr, i2));
        byte[] bArr3 = new byte[this.digest.getDigestSize()];
        addFieldElement(this.digest, eCPointNormalize.getAffineXCoord());
        this.digest.update(bArr, i2, i3);
        addFieldElement(this.digest, eCPointNormalize.getAffineYCoord());
        this.digest.doFinal(bArr3, 0);
        return Arrays.concatenate(encoded, bArr2, bArr3);
    }

    private void kdf(Digest digest, ECPoint eCPoint, byte[] bArr) {
        int digestSize = digest.getDigestSize();
        int digestSize2 = digest.getDigestSize();
        byte[] bArr2 = new byte[digestSize2];
        int i2 = 1;
        int i3 = 1;
        int i4 = 0;
        while (i2 <= ((bArr.length + digestSize) - 1) / digestSize) {
            addFieldElement(digest, eCPoint.getAffineXCoord());
            addFieldElement(digest, eCPoint.getAffineYCoord());
            digest.update((byte) (i3 >> 24));
            digest.update((byte) (i3 >> 16));
            digest.update((byte) (i3 >> 8));
            digest.update((byte) i3);
            digest.doFinal(bArr2, 0);
            int i5 = i4 + digestSize2;
            if (i5 < bArr.length) {
                xor(bArr, bArr2, i4, digestSize2);
            } else {
                xor(bArr, bArr2, i4, bArr.length - i4);
            }
            i3++;
            i2++;
            i4 = i5;
        }
    }

    private BigInteger nextK() {
        int iBitLength = this.ecParams.getN().bitLength();
        while (true) {
            BigInteger bigInteger = new BigInteger(iBitLength, this.random);
            if (!bigInteger.equals(ECConstants.ZERO) && bigInteger.compareTo(this.ecParams.getN()) < 0) {
                return bigInteger;
            }
        }
    }

    private boolean notEncrypted(byte[] bArr, byte[] bArr2, int i2) {
        for (int i3 = 0; i3 != bArr.length; i3++) {
            if (bArr[i3] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private void xor(byte[] bArr, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 != i3; i4++) {
            int i5 = i2 + i4;
            bArr[i5] = (byte) (bArr[i5] ^ bArr2[i4]);
        }
    }

    public void init(boolean z2, CipherParameters cipherParameters) {
        this.forEncryption = z2;
        if (z2) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            ECKeyParameters eCKeyParameters = (ECKeyParameters) parametersWithRandom.getParameters();
            this.ecKey = eCKeyParameters;
            this.ecParams = eCKeyParameters.getParameters();
            if (((ECPublicKeyParameters) this.ecKey).getQ().multiply(this.ecParams.getH()).isInfinity()) {
                throw new IllegalArgumentException("invalid key: [h]Q at infinity");
            }
            this.random = parametersWithRandom.getRandom();
        } else {
            ECKeyParameters eCKeyParameters2 = (ECKeyParameters) cipherParameters;
            this.ecKey = eCKeyParameters2;
            this.ecParams = eCKeyParameters2.getParameters();
        }
        this.curveLength = (this.ecParams.getCurve().getFieldSize() + 7) / 8;
    }

    public byte[] processBlock(byte[] bArr, int i2, int i3) {
        return this.forEncryption ? encrypt(bArr, i2, i3) : decrypt(bArr, i2, i3);
    }

    public SM2Engine(Digest digest) {
        this.digest = digest;
    }
}
