package org.spongycastle.pqc.jcajce.provider.xmss;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.crypto.digests.SHAKEDigest;
import org.spongycastle.pqc.crypto.xmss.XMSSKeyGenerationParameters;
import org.spongycastle.pqc.crypto.xmss.XMSSKeyPairGenerator;
import org.spongycastle.pqc.crypto.xmss.XMSSParameters;
import org.spongycastle.pqc.crypto.xmss.XMSSPrivateKeyParameters;
import org.spongycastle.pqc.crypto.xmss.XMSSPublicKeyParameters;
import org.spongycastle.pqc.jcajce.spec.XMSSParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class XMSSKeyPairGeneratorSpi extends KeyPairGenerator {
    private XMSSKeyPairGenerator engine;
    private boolean initialised;
    private XMSSKeyGenerationParameters param;
    private SecureRandom random;
    private ASN1ObjectIdentifier treeDigest;

    public XMSSKeyPairGeneratorSpi() {
        super("XMSS");
        this.engine = new XMSSKeyPairGenerator();
        this.random = new SecureRandom();
        this.initialised = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            XMSSKeyGenerationParameters xMSSKeyGenerationParameters = new XMSSKeyGenerationParameters(new XMSSParameters(10, new SHA512Digest()), this.random);
            this.param = xMSSKeyGenerationParameters;
            this.engine.init(xMSSKeyGenerationParameters);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair asymmetricCipherKeyPairGenerateKeyPair = this.engine.generateKeyPair();
        return new KeyPair(new BCXMSSPublicKey(this.treeDigest, (XMSSPublicKeyParameters) asymmetricCipherKeyPairGenerateKeyPair.getPublic()), new BCXMSSPrivateKey(this.treeDigest, (XMSSPrivateKeyParameters) asymmetricCipherKeyPairGenerateKeyPair.getPrivate()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i2, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof XMSSParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a XMSSParameterSpec");
        }
        XMSSParameterSpec xMSSParameterSpec = (XMSSParameterSpec) algorithmParameterSpec;
        if (xMSSParameterSpec.getTreeDigest().equals("SHA256")) {
            this.treeDigest = NISTObjectIdentifiers.id_sha256;
            this.param = new XMSSKeyGenerationParameters(new XMSSParameters(xMSSParameterSpec.getHeight(), new SHA256Digest()), secureRandom);
        } else if (xMSSParameterSpec.getTreeDigest().equals("SHA512")) {
            this.treeDigest = NISTObjectIdentifiers.id_sha512;
            this.param = new XMSSKeyGenerationParameters(new XMSSParameters(xMSSParameterSpec.getHeight(), new SHA512Digest()), secureRandom);
        } else if (xMSSParameterSpec.getTreeDigest().equals("SHAKE128")) {
            this.treeDigest = NISTObjectIdentifiers.id_shake128;
            this.param = new XMSSKeyGenerationParameters(new XMSSParameters(xMSSParameterSpec.getHeight(), new SHAKEDigest(128)), secureRandom);
        } else if (xMSSParameterSpec.getTreeDigest().equals("SHAKE256")) {
            this.treeDigest = NISTObjectIdentifiers.id_shake256;
            this.param = new XMSSKeyGenerationParameters(new XMSSParameters(xMSSParameterSpec.getHeight(), new SHAKEDigest(256)), secureRandom);
        }
        this.engine.init(this.param);
        this.initialised = true;
    }
}
