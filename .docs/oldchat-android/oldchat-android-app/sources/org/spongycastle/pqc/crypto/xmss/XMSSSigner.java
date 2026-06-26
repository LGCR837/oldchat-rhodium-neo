package org.spongycastle.pqc.crypto.xmss;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.crypto.StateAwareMessageSigner;
import org.spongycastle.pqc.crypto.xmss.OTSHashAddress;
import org.spongycastle.pqc.crypto.xmss.XMSSSignature;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class XMSSSigner implements StateAwareMessageSigner {
    private boolean hasGenerated;
    private boolean initSign;
    private KeyedHashFunctions khf;
    private XMSSPrivateKeyParameters nextKeyGenerator;
    private XMSSParameters params;
    private XMSSPrivateKeyParameters privateKey;
    private XMSSPublicKeyParameters publicKey;

    private WOTSPlusSignature wotsSign(byte[] bArr, OTSHashAddress oTSHashAddress) {
        if (bArr.length != this.params.getDigestSize()) {
            throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
        }
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        this.params.getWOTSPlus().importKeys(this.params.getWOTSPlus().getWOTSPlusSecretKey(this.privateKey.getSecretKeySeed(), oTSHashAddress), this.privateKey.getPublicSeed());
        return this.params.getWOTSPlus().sign(bArr, oTSHashAddress);
    }

    @Override // org.spongycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("message == null");
        }
        if (!this.initSign) {
            throw new IllegalStateException("signer not initialized for signature generation");
        }
        XMSSPrivateKeyParameters xMSSPrivateKeyParameters = this.privateKey;
        if (xMSSPrivateKeyParameters == null) {
            throw new IllegalStateException("signing key no longer usable");
        }
        if (xMSSPrivateKeyParameters.getBDSState().getAuthenticationPath().isEmpty()) {
            throw new IllegalStateException("not initialized");
        }
        int index = this.privateKey.getIndex();
        long j2 = index;
        if (!XMSSUtil.isIndexValid(this.params.getHeight(), j2)) {
            throw new IllegalStateException("index out of bounds");
        }
        byte[] bArrPRF = this.khf.PRF(this.privateKey.getSecretKeyPRF(), XMSSUtil.toBytesBigEndian(j2, 32));
        XMSSSignature xMSSSignature = (XMSSSignature) new XMSSSignature.Builder(this.params).withIndex(index).withRandom(bArrPRF).withWOTSPlusSignature(wotsSign(this.khf.HMsg(Arrays.concatenate(bArrPRF, this.privateKey.getRoot(), XMSSUtil.toBytesBigEndian(j2, this.params.getDigestSize())), bArr), (OTSHashAddress) new OTSHashAddress.Builder().withOTSAddress(index).build())).withAuthPath(this.privateKey.getBDSState().getAuthenticationPath()).build();
        this.hasGenerated = true;
        XMSSPrivateKeyParameters xMSSPrivateKeyParameters2 = this.nextKeyGenerator;
        if (xMSSPrivateKeyParameters2 != null) {
            XMSSPrivateKeyParameters nextKey = xMSSPrivateKeyParameters2.getNextKey();
            this.privateKey = nextKey;
            this.nextKeyGenerator = nextKey;
        } else {
            this.privateKey = null;
        }
        return xMSSSignature.toByteArray();
    }

    @Override // org.spongycastle.pqc.crypto.StateAwareMessageSigner
    public AsymmetricKeyParameter getUpdatedPrivateKey() {
        if (!this.hasGenerated) {
            XMSSPrivateKeyParameters nextKey = this.nextKeyGenerator.getNextKey();
            this.nextKeyGenerator = null;
            return nextKey;
        }
        XMSSPrivateKeyParameters xMSSPrivateKeyParameters = this.privateKey;
        this.privateKey = null;
        this.nextKeyGenerator = null;
        return xMSSPrivateKeyParameters;
    }

    @Override // org.spongycastle.pqc.crypto.MessageSigner
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (!z2) {
            this.initSign = false;
            XMSSPublicKeyParameters xMSSPublicKeyParameters = (XMSSPublicKeyParameters) cipherParameters;
            this.publicKey = xMSSPublicKeyParameters;
            XMSSParameters parameters = xMSSPublicKeyParameters.getParameters();
            this.params = parameters;
            this.khf = parameters.getWOTSPlus().getKhf();
            return;
        }
        this.initSign = true;
        this.hasGenerated = false;
        XMSSPrivateKeyParameters xMSSPrivateKeyParameters = (XMSSPrivateKeyParameters) cipherParameters;
        this.privateKey = xMSSPrivateKeyParameters;
        this.nextKeyGenerator = xMSSPrivateKeyParameters;
        XMSSParameters parameters2 = xMSSPrivateKeyParameters.getParameters();
        this.params = parameters2;
        this.khf = parameters2.getWOTSPlus().getKhf();
    }

    @Override // org.spongycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        XMSSSignature xMSSSignatureBuild = new XMSSSignature.Builder(this.params).withSignature(bArr2).build();
        int index = xMSSSignatureBuild.getIndex();
        this.params.getWOTSPlus().importKeys(new byte[this.params.getDigestSize()], this.publicKey.getPublicSeed());
        long j2 = index;
        byte[] bArrHMsg = this.khf.HMsg(Arrays.concatenate(xMSSSignatureBuild.getRandom(), this.publicKey.getRoot(), XMSSUtil.toBytesBigEndian(j2, this.params.getDigestSize())), bArr);
        int height = this.params.getHeight();
        return Arrays.constantTimeAreEqual(XMSSVerifierUtil.getRootNodeFromSignature(this.params.getWOTSPlus(), height, bArrHMsg, xMSSSignatureBuild, (OTSHashAddress) new OTSHashAddress.Builder().withOTSAddress(index).build(), XMSSUtil.getLeafIndex(j2, height)).getValue(), this.publicKey.getRoot());
    }
}
