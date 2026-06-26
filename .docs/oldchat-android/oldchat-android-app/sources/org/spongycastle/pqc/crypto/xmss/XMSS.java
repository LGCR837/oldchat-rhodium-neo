package org.spongycastle.pqc.crypto.xmss;

import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.pqc.crypto.xmss.XMSSPrivateKeyParameters;
import org.spongycastle.pqc.crypto.xmss.XMSSPublicKeyParameters;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class XMSS {
    private final XMSSParameters params;
    private XMSSPrivateKeyParameters privateKey;
    private SecureRandom prng;
    private XMSSPublicKeyParameters publicKey;
    private WOTSPlus wotsPlus;

    public XMSS(XMSSParameters xMSSParameters, SecureRandom secureRandom) {
        if (xMSSParameters == null) {
            throw new NullPointerException("params == null");
        }
        this.params = xMSSParameters;
        this.wotsPlus = xMSSParameters.getWOTSPlus();
        this.prng = secureRandom;
    }

    public byte[] exportPrivateKey() {
        return this.privateKey.toByteArray();
    }

    public byte[] exportPublicKey() {
        return this.publicKey.toByteArray();
    }

    public void generateKeys() {
        XMSSKeyPairGenerator xMSSKeyPairGenerator = new XMSSKeyPairGenerator();
        xMSSKeyPairGenerator.init(new XMSSKeyGenerationParameters(getParams(), this.prng));
        AsymmetricCipherKeyPair asymmetricCipherKeyPairGenerateKeyPair = xMSSKeyPairGenerator.generateKeyPair();
        this.privateKey = (XMSSPrivateKeyParameters) asymmetricCipherKeyPairGenerateKeyPair.getPrivate();
        this.publicKey = (XMSSPublicKeyParameters) asymmetricCipherKeyPairGenerateKeyPair.getPublic();
        this.wotsPlus.importKeys(new byte[this.params.getDigestSize()], this.privateKey.getPublicSeed());
    }

    public int getIndex() {
        return this.privateKey.getIndex();
    }

    public XMSSParameters getParams() {
        return this.params;
    }

    public XMSSPrivateKeyParameters getPrivateKey() {
        return this.privateKey;
    }

    public byte[] getPublicSeed() {
        return this.privateKey.getPublicSeed();
    }

    public byte[] getRoot() {
        return this.privateKey.getRoot();
    }

    public WOTSPlus getWOTSPlus() {
        return this.wotsPlus;
    }

    public void importState(XMSSPrivateKeyParameters xMSSPrivateKeyParameters, XMSSPublicKeyParameters xMSSPublicKeyParameters) {
        if (!Arrays.areEqual(xMSSPrivateKeyParameters.getRoot(), xMSSPublicKeyParameters.getRoot())) {
            throw new IllegalStateException("root of private key and public key do not match");
        }
        if (!Arrays.areEqual(xMSSPrivateKeyParameters.getPublicSeed(), xMSSPublicKeyParameters.getPublicSeed())) {
            throw new IllegalStateException("public seed of private key and public key do not match");
        }
        this.privateKey = xMSSPrivateKeyParameters;
        this.publicKey = xMSSPublicKeyParameters;
        this.wotsPlus.importKeys(new byte[this.params.getDigestSize()], this.privateKey.getPublicSeed());
    }

    public void setIndex(int i2) {
        this.privateKey = new XMSSPrivateKeyParameters.Builder(this.params).withSecretKeySeed(this.privateKey.getSecretKeySeed()).withSecretKeyPRF(this.privateKey.getSecretKeyPRF()).withPublicSeed(this.privateKey.getPublicSeed()).withRoot(this.privateKey.getRoot()).withBDSState(this.privateKey.getBDSState()).build();
    }

    public void setPublicSeed(byte[] bArr) {
        this.privateKey = new XMSSPrivateKeyParameters.Builder(this.params).withSecretKeySeed(this.privateKey.getSecretKeySeed()).withSecretKeyPRF(this.privateKey.getSecretKeyPRF()).withPublicSeed(bArr).withRoot(getRoot()).withBDSState(this.privateKey.getBDSState()).build();
        this.publicKey = new XMSSPublicKeyParameters.Builder(this.params).withRoot(getRoot()).withPublicSeed(bArr).build();
        this.wotsPlus.importKeys(new byte[this.params.getDigestSize()], bArr);
    }

    public void setRoot(byte[] bArr) {
        this.privateKey = new XMSSPrivateKeyParameters.Builder(this.params).withSecretKeySeed(this.privateKey.getSecretKeySeed()).withSecretKeyPRF(this.privateKey.getSecretKeyPRF()).withPublicSeed(getPublicSeed()).withRoot(bArr).withBDSState(this.privateKey.getBDSState()).build();
        this.publicKey = new XMSSPublicKeyParameters.Builder(this.params).withRoot(bArr).withPublicSeed(getPublicSeed()).build();
    }

    public byte[] sign(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("message == null");
        }
        XMSSSigner xMSSSigner = new XMSSSigner();
        xMSSSigner.init(true, this.privateKey);
        byte[] bArrGenerateSignature = xMSSSigner.generateSignature(bArr);
        XMSSPrivateKeyParameters xMSSPrivateKeyParameters = (XMSSPrivateKeyParameters) xMSSSigner.getUpdatedPrivateKey();
        this.privateKey = xMSSPrivateKeyParameters;
        importState(xMSSPrivateKeyParameters, this.publicKey);
        return bArrGenerateSignature;
    }

    public boolean verifySignature(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null) {
            throw new NullPointerException("message == null");
        }
        if (bArr2 == null) {
            throw new NullPointerException("signature == null");
        }
        if (bArr3 == null) {
            throw new NullPointerException("publicKey == null");
        }
        XMSSSigner xMSSSigner = new XMSSSigner();
        xMSSSigner.init(false, new XMSSPublicKeyParameters.Builder(getParams()).withPublicKey(bArr3).build());
        return xMSSSigner.verifySignature(bArr, bArr2);
    }

    public WOTSPlusSignature wotsSign(byte[] bArr, OTSHashAddress oTSHashAddress) {
        if (bArr.length != this.params.getDigestSize()) {
            throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
        }
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        WOTSPlus wOTSPlus = this.wotsPlus;
        wOTSPlus.importKeys(wOTSPlus.getWOTSPlusSecretKey(this.privateKey.getSecretKeySeed(), oTSHashAddress), getPublicSeed());
        return this.wotsPlus.sign(bArr, oTSHashAddress);
    }

    public void importState(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            throw new NullPointerException("privateKey == null");
        }
        if (bArr2 != null) {
            XMSSPrivateKeyParameters xMSSPrivateKeyParametersBuild = new XMSSPrivateKeyParameters.Builder(this.params).withPrivateKey(bArr, getParams()).build();
            XMSSPublicKeyParameters xMSSPublicKeyParametersBuild = new XMSSPublicKeyParameters.Builder(this.params).withPublicKey(bArr2).build();
            if (Arrays.areEqual(xMSSPrivateKeyParametersBuild.getRoot(), xMSSPublicKeyParametersBuild.getRoot())) {
                if (Arrays.areEqual(xMSSPrivateKeyParametersBuild.getPublicSeed(), xMSSPublicKeyParametersBuild.getPublicSeed())) {
                    this.privateKey = xMSSPrivateKeyParametersBuild;
                    this.publicKey = xMSSPublicKeyParametersBuild;
                    this.wotsPlus.importKeys(new byte[this.params.getDigestSize()], this.privateKey.getPublicSeed());
                    return;
                }
                throw new IllegalStateException("public seed of private key and public key do not match");
            }
            throw new IllegalStateException("root of private key and public key do not match");
        }
        throw new NullPointerException("publicKey == null");
    }
}
