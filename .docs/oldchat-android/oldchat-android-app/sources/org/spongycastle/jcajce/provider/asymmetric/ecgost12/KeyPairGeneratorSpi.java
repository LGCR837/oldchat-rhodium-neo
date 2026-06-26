package org.spongycastle.jcajce.provider.asymmetric.ecgost12;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import org.spongycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.generators.ECKeyPairGenerator;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyGenerationParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.spongycastle.jcajce.provider.config.ProviderConfiguration;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ECNamedCurveGenParameterSpec;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.jce.spec.ECParameterSpec;
import org.spongycastle.math.ec.ECCurve;

/* JADX INFO: loaded from: classes.dex */
public class KeyPairGeneratorSpi extends KeyPairGenerator {
    String algorithm;
    Object ecParams;
    ECKeyPairGenerator engine;
    boolean initialised;
    ECKeyGenerationParameters param;
    SecureRandom random;
    int strength;

    public KeyPairGeneratorSpi() {
        super("ECGOST3410-2012");
        this.ecParams = null;
        this.engine = new ECKeyPairGenerator();
        this.algorithm = "ECGOST3410-2012";
        this.strength = 239;
        this.random = null;
        this.initialised = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            throw new IllegalStateException("EC Key Pair Generator not initialised");
        }
        AsymmetricCipherKeyPair asymmetricCipherKeyPairGenerateKeyPair = this.engine.generateKeyPair();
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) asymmetricCipherKeyPairGenerateKeyPair.getPublic();
        ECPrivateKeyParameters eCPrivateKeyParameters = (ECPrivateKeyParameters) asymmetricCipherKeyPairGenerateKeyPair.getPrivate();
        Object obj = this.ecParams;
        if (obj instanceof ECParameterSpec) {
            ECParameterSpec eCParameterSpec = (ECParameterSpec) obj;
            BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey = new BCECGOST3410_2012PublicKey(this.algorithm, eCPublicKeyParameters, eCParameterSpec);
            return new KeyPair(bCECGOST3410_2012PublicKey, new BCECGOST3410_2012PrivateKey(this.algorithm, eCPrivateKeyParameters, bCECGOST3410_2012PublicKey, eCParameterSpec));
        }
        if (obj == null) {
            return new KeyPair(new BCECGOST3410_2012PublicKey(this.algorithm, eCPublicKeyParameters), new BCECGOST3410_2012PrivateKey(this.algorithm, eCPrivateKeyParameters));
        }
        java.security.spec.ECParameterSpec eCParameterSpec2 = (java.security.spec.ECParameterSpec) obj;
        BCECGOST3410_2012PublicKey bCECGOST3410_2012PublicKey2 = new BCECGOST3410_2012PublicKey(this.algorithm, eCPublicKeyParameters, eCParameterSpec2);
        return new KeyPair(bCECGOST3410_2012PublicKey2, new BCECGOST3410_2012PrivateKey(this.algorithm, eCPrivateKeyParameters, bCECGOST3410_2012PublicKey2, eCParameterSpec2));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i2, SecureRandom secureRandom) {
        this.strength = i2;
        this.random = secureRandom;
        Object obj = this.ecParams;
        if (obj == null) {
            throw new InvalidParameterException("unknown key size.");
        }
        try {
            initialize((ECGenParameterSpec) obj, secureRandom);
        } catch (InvalidAlgorithmParameterException unused) {
            throw new InvalidParameterException("key size not configurable.");
        }
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        String name;
        if (algorithmParameterSpec instanceof ECParameterSpec) {
            ECParameterSpec eCParameterSpec = (ECParameterSpec) algorithmParameterSpec;
            this.ecParams = algorithmParameterSpec;
            ECKeyGenerationParameters eCKeyGenerationParameters = new ECKeyGenerationParameters(new ECDomainParameters(eCParameterSpec.getCurve(), eCParameterSpec.getG(), eCParameterSpec.getN(), eCParameterSpec.getH()), secureRandom);
            this.param = eCKeyGenerationParameters;
            this.engine.init(eCKeyGenerationParameters);
            this.initialised = true;
            return;
        }
        if (algorithmParameterSpec instanceof java.security.spec.ECParameterSpec) {
            java.security.spec.ECParameterSpec eCParameterSpec2 = (java.security.spec.ECParameterSpec) algorithmParameterSpec;
            this.ecParams = algorithmParameterSpec;
            ECCurve eCCurveConvertCurve = EC5Util.convertCurve(eCParameterSpec2.getCurve());
            ECKeyGenerationParameters eCKeyGenerationParameters2 = new ECKeyGenerationParameters(new ECDomainParameters(eCCurveConvertCurve, EC5Util.convertPoint(eCCurveConvertCurve, eCParameterSpec2.getGenerator(), false), eCParameterSpec2.getOrder(), BigInteger.valueOf(eCParameterSpec2.getCofactor())), secureRandom);
            this.param = eCKeyGenerationParameters2;
            this.engine.init(eCKeyGenerationParameters2);
            this.initialised = true;
            return;
        }
        boolean z2 = algorithmParameterSpec instanceof ECGenParameterSpec;
        if (!z2 && !(algorithmParameterSpec instanceof ECNamedCurveGenParameterSpec)) {
            if (algorithmParameterSpec == null) {
                ProviderConfiguration providerConfiguration = BouncyCastleProvider.CONFIGURATION;
                if (providerConfiguration.getEcImplicitlyCa() != null) {
                    ECParameterSpec ecImplicitlyCa = providerConfiguration.getEcImplicitlyCa();
                    this.ecParams = algorithmParameterSpec;
                    ECKeyGenerationParameters eCKeyGenerationParameters3 = new ECKeyGenerationParameters(new ECDomainParameters(ecImplicitlyCa.getCurve(), ecImplicitlyCa.getG(), ecImplicitlyCa.getN(), ecImplicitlyCa.getH()), secureRandom);
                    this.param = eCKeyGenerationParameters3;
                    this.engine.init(eCKeyGenerationParameters3);
                    this.initialised = true;
                    return;
                }
            }
            if (algorithmParameterSpec == null && BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa() == null) {
                throw new InvalidAlgorithmParameterException("null parameter passed but no implicitCA set");
            }
            throw new InvalidAlgorithmParameterException("parameter object not a ECParameterSpec: " + algorithmParameterSpec.getClass().getName());
        }
        if (z2) {
            name = ((ECGenParameterSpec) algorithmParameterSpec).getName();
        } else {
            name = ((ECNamedCurveGenParameterSpec) algorithmParameterSpec).getName();
        }
        String str = name;
        ECDomainParameters byName = ECGOST3410NamedCurves.getByName(str);
        if (byName != null) {
            ECNamedCurveSpec eCNamedCurveSpec = new ECNamedCurveSpec(str, byName.getCurve(), byName.getG(), byName.getN(), byName.getH(), byName.getSeed());
            this.ecParams = eCNamedCurveSpec;
            ECNamedCurveSpec eCNamedCurveSpec2 = eCNamedCurveSpec;
            ECCurve eCCurveConvertCurve2 = EC5Util.convertCurve(eCNamedCurveSpec2.getCurve());
            ECKeyGenerationParameters eCKeyGenerationParameters4 = new ECKeyGenerationParameters(new ECDomainParameters(eCCurveConvertCurve2, EC5Util.convertPoint(eCCurveConvertCurve2, eCNamedCurveSpec2.getGenerator(), false), eCNamedCurveSpec2.getOrder(), BigInteger.valueOf(eCNamedCurveSpec2.getCofactor())), secureRandom);
            this.param = eCKeyGenerationParameters4;
            this.engine.init(eCKeyGenerationParameters4);
            this.initialised = true;
            return;
        }
        throw new InvalidAlgorithmParameterException("unknown curve name: " + str);
    }
}
