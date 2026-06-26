package org.spongycastle.pqc.jcajce.provider;

import org.spongycastle.asn1.bc.BCObjectIdentifiers;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.spongycastle.pqc.asn1.PQCObjectIdentifiers;
import org.spongycastle.pqc.jcajce.provider.xmss.XMSSKeyFactorySpi;
import org.spongycastle.pqc.jcajce.provider.xmss.XMSSMTKeyFactorySpi;

/* JADX INFO: loaded from: classes.dex */
public class XMSS {
    private static final String PREFIX = "org.spongycastle.pqc.jcajce.provider.xmss.";

    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.spongycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.XMSS", "org.spongycastle.pqc.jcajce.provider.xmss.XMSSKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.XMSS", "org.spongycastle.pqc.jcajce.provider.xmss.XMSSKeyPairGeneratorSpi");
            addSignatureAlgorithm(configurableProvider, "SHA256", "XMSS", "org.spongycastle.pqc.jcajce.provider.xmss.XMSSSignatureSpi$withSha256", BCObjectIdentifiers.xmss_with_SHA256);
            addSignatureAlgorithm(configurableProvider, "SHAKE128", "XMSS", "org.spongycastle.pqc.jcajce.provider.xmss.XMSSSignatureSpi$withShake128", BCObjectIdentifiers.xmss_with_SHAKE128);
            addSignatureAlgorithm(configurableProvider, "SHA512", "XMSS", "org.spongycastle.pqc.jcajce.provider.xmss.XMSSSignatureSpi$withSha512", BCObjectIdentifiers.xmss_with_SHA512);
            addSignatureAlgorithm(configurableProvider, "SHAKE256", "XMSS", "org.spongycastle.pqc.jcajce.provider.xmss.XMSSSignatureSpi$withShake256", BCObjectIdentifiers.xmss_with_SHAKE256);
            configurableProvider.addAlgorithm("KeyFactory.XMSSMT", "org.spongycastle.pqc.jcajce.provider.xmss.XMSSMTKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.XMSSMT", "org.spongycastle.pqc.jcajce.provider.xmss.XMSSMTKeyPairGeneratorSpi");
            addSignatureAlgorithm(configurableProvider, "SHA256", "XMSSMT", "org.spongycastle.pqc.jcajce.provider.xmss.XMSSMTSignatureSpi$withSha256", BCObjectIdentifiers.xmss_mt_with_SHA256);
            addSignatureAlgorithm(configurableProvider, "SHAKE128", "XMSSMT", "org.spongycastle.pqc.jcajce.provider.xmss.XMSSMTSignatureSpi$withShake128", BCObjectIdentifiers.xmss_mt_with_SHAKE128);
            addSignatureAlgorithm(configurableProvider, "SHA512", "XMSSMT", "org.spongycastle.pqc.jcajce.provider.xmss.XMSSMTSignatureSpi$withSha512", BCObjectIdentifiers.xmss_mt_with_SHA512);
            addSignatureAlgorithm(configurableProvider, "SHAKE256", "XMSSMT", "org.spongycastle.pqc.jcajce.provider.xmss.XMSSMTSignatureSpi$withShake256", BCObjectIdentifiers.xmss_mt_with_SHAKE256);
            registerOid(configurableProvider, PQCObjectIdentifiers.xmss, "XMSS", new XMSSKeyFactorySpi());
            registerOid(configurableProvider, PQCObjectIdentifiers.xmss_mt, "XMSSMT", new XMSSMTKeyFactorySpi());
        }
    }
}
