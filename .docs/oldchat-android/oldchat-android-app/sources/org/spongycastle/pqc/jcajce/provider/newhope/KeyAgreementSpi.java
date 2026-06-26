package org.spongycastle.pqc.jcajce.provider.newhope;

import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi;
import org.spongycastle.pqc.crypto.ExchangePair;
import org.spongycastle.pqc.crypto.newhope.NHAgreement;
import org.spongycastle.pqc.crypto.newhope.NHExchangePairGenerator;
import org.spongycastle.pqc.crypto.newhope.NHPublicKeyParameters;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class KeyAgreementSpi extends BaseAgreementSpi {
    private NHAgreement agreement;
    private NHExchangePairGenerator exchangePairGenerator;
    private BCNHPublicKey otherPartyKey;
    private byte[] shared;

    public KeyAgreementSpi() {
        super("NH", null);
    }

    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi
    public byte[] calcSecret() {
        return engineGenerateSecret();
    }

    @Override // javax.crypto.KeyAgreementSpi
    public Key engineDoPhase(Key key, boolean z2) {
        if (!z2) {
            throw new IllegalStateException("NewHope can only be between two parties.");
        }
        BCNHPublicKey bCNHPublicKey = (BCNHPublicKey) key;
        this.otherPartyKey = bCNHPublicKey;
        NHExchangePairGenerator nHExchangePairGenerator = this.exchangePairGenerator;
        if (nHExchangePairGenerator == null) {
            this.shared = this.agreement.calculateAgreement(bCNHPublicKey.getKeyParams());
            return null;
        }
        ExchangePair exchangePairGenerateExchange = nHExchangePairGenerator.generateExchange((AsymmetricKeyParameter) bCNHPublicKey.getKeyParams());
        this.shared = exchangePairGenerateExchange.getSharedValue();
        return new BCNHPublicKey((NHPublicKeyParameters) exchangePairGenerateExchange.getPublicKey());
    }

    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi, javax.crypto.KeyAgreementSpi
    public byte[] engineGenerateSecret() {
        byte[] bArrClone = Arrays.clone(this.shared);
        Arrays.fill(this.shared, (byte) 0);
        return bArrClone;
    }

    @Override // javax.crypto.KeyAgreementSpi
    public void engineInit(Key key, SecureRandom secureRandom) {
        if (key == null) {
            this.exchangePairGenerator = new NHExchangePairGenerator(secureRandom);
            return;
        }
        NHAgreement nHAgreement = new NHAgreement();
        this.agreement = nHAgreement;
        nHAgreement.init(((BCNHPrivateKey) key).getKeyParams());
    }

    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi, javax.crypto.KeyAgreementSpi
    public int engineGenerateSecret(byte[] bArr, int i2) {
        byte[] bArr2 = this.shared;
        System.arraycopy(bArr2, 0, bArr, i2, bArr2.length);
        Arrays.fill(this.shared, (byte) 0);
        return this.shared.length;
    }

    @Override // javax.crypto.KeyAgreementSpi
    public void engineInit(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException("NewHope does not require parameters");
    }
}
