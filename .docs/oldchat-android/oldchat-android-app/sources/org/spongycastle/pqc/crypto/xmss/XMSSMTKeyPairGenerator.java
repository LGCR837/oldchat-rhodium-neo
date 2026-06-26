package org.spongycastle.pqc.crypto.xmss;

import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.crypto.xmss.OTSHashAddress;
import org.spongycastle.pqc.crypto.xmss.XMSSMTPrivateKeyParameters;
import org.spongycastle.pqc.crypto.xmss.XMSSMTPublicKeyParameters;

/* JADX INFO: loaded from: classes.dex */
public final class XMSSMTKeyPairGenerator {
    private XMSSMTParameters params;
    private SecureRandom prng;
    private XMSSParameters xmssParams;

    private XMSSMTPrivateKeyParameters generatePrivateKey(BDSStateMap bDSStateMap) {
        int digestSize = this.params.getDigestSize();
        byte[] bArr = new byte[digestSize];
        this.prng.nextBytes(bArr);
        byte[] bArr2 = new byte[digestSize];
        this.prng.nextBytes(bArr2);
        byte[] bArr3 = new byte[digestSize];
        this.prng.nextBytes(bArr3);
        return new XMSSMTPrivateKeyParameters.Builder(this.params).withSecretKeySeed(bArr).withSecretKeyPRF(bArr2).withPublicSeed(bArr3).withBDSState(bDSStateMap).build();
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParametersGeneratePrivateKey = generatePrivateKey(new XMSSMTPrivateKeyParameters.Builder(this.params).build().getBDSState());
        this.xmssParams.getWOTSPlus().importKeys(new byte[this.params.getDigestSize()], xMSSMTPrivateKeyParametersGeneratePrivateKey.getPublicSeed());
        int layers = this.params.getLayers() - 1;
        BDS bds = new BDS(this.xmssParams, xMSSMTPrivateKeyParametersGeneratePrivateKey.getPublicSeed(), xMSSMTPrivateKeyParametersGeneratePrivateKey.getSecretKeySeed(), (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(layers).build());
        XMSSNode root = bds.getRoot();
        xMSSMTPrivateKeyParametersGeneratePrivateKey.getBDSState().put(layers, bds);
        XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParametersBuild = new XMSSMTPrivateKeyParameters.Builder(this.params).withSecretKeySeed(xMSSMTPrivateKeyParametersGeneratePrivateKey.getSecretKeySeed()).withSecretKeyPRF(xMSSMTPrivateKeyParametersGeneratePrivateKey.getSecretKeyPRF()).withPublicSeed(xMSSMTPrivateKeyParametersGeneratePrivateKey.getPublicSeed()).withRoot(root.getValue()).withBDSState(xMSSMTPrivateKeyParametersGeneratePrivateKey.getBDSState()).build();
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new XMSSMTPublicKeyParameters.Builder(this.params).withRoot(root.getValue()).withPublicSeed(xMSSMTPrivateKeyParametersBuild.getPublicSeed()).build(), (AsymmetricKeyParameter) xMSSMTPrivateKeyParametersBuild);
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        XMSSMTKeyGenerationParameters xMSSMTKeyGenerationParameters = (XMSSMTKeyGenerationParameters) keyGenerationParameters;
        this.prng = xMSSMTKeyGenerationParameters.getRandom();
        XMSSMTParameters parameters = xMSSMTKeyGenerationParameters.getParameters();
        this.params = parameters;
        this.xmssParams = parameters.getXMSSParameters();
    }
}
