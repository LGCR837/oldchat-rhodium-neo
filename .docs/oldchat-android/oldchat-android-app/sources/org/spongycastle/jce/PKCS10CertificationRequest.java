package org.spongycastle.jce;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PSSParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.CertificationRequest;
import org.spongycastle.asn1.pkcs.CertificationRequestInfo;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.RSASSAPSSparams;
import org.spongycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x509.X509Name;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.util.Strings;

/* JADX INFO: loaded from: classes.dex */
public class PKCS10CertificationRequest extends CertificationRequest {
    private static Hashtable algorithms = new Hashtable();
    private static Hashtable params = new Hashtable();
    private static Hashtable keyAlgorithms = new Hashtable();
    private static Hashtable oids = new Hashtable();
    private static Set noParams = new HashSet();

    static {
        algorithms.put("MD2WITHRSAENCRYPTION", new ASN1ObjectIdentifier("1.2.840.113549.1.1.2"));
        algorithms.put("MD2WITHRSA", new ASN1ObjectIdentifier("1.2.840.113549.1.1.2"));
        algorithms.put("MD5WITHRSAENCRYPTION", new ASN1ObjectIdentifier("1.2.840.113549.1.1.4"));
        algorithms.put("MD5WITHRSA", new ASN1ObjectIdentifier("1.2.840.113549.1.1.4"));
        algorithms.put("RSAWITHMD5", new ASN1ObjectIdentifier("1.2.840.113549.1.1.4"));
        algorithms.put("SHA1WITHRSAENCRYPTION", new ASN1ObjectIdentifier("1.2.840.113549.1.1.5"));
        algorithms.put("SHA1WITHRSA", new ASN1ObjectIdentifier("1.2.840.113549.1.1.5"));
        Hashtable hashtable = algorithms;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.sha224WithRSAEncryption;
        hashtable.put("SHA224WITHRSAENCRYPTION", aSN1ObjectIdentifier);
        algorithms.put("SHA224WITHRSA", aSN1ObjectIdentifier);
        Hashtable hashtable2 = algorithms;
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = PKCSObjectIdentifiers.sha256WithRSAEncryption;
        hashtable2.put("SHA256WITHRSAENCRYPTION", aSN1ObjectIdentifier2);
        algorithms.put("SHA256WITHRSA", aSN1ObjectIdentifier2);
        Hashtable hashtable3 = algorithms;
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = PKCSObjectIdentifiers.sha384WithRSAEncryption;
        hashtable3.put("SHA384WITHRSAENCRYPTION", aSN1ObjectIdentifier3);
        algorithms.put("SHA384WITHRSA", aSN1ObjectIdentifier3);
        Hashtable hashtable4 = algorithms;
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = PKCSObjectIdentifiers.sha512WithRSAEncryption;
        hashtable4.put("SHA512WITHRSAENCRYPTION", aSN1ObjectIdentifier4);
        algorithms.put("SHA512WITHRSA", aSN1ObjectIdentifier4);
        Hashtable hashtable5 = algorithms;
        ASN1ObjectIdentifier aSN1ObjectIdentifier5 = PKCSObjectIdentifiers.id_RSASSA_PSS;
        hashtable5.put("SHA1WITHRSAANDMGF1", aSN1ObjectIdentifier5);
        algorithms.put("SHA224WITHRSAANDMGF1", aSN1ObjectIdentifier5);
        algorithms.put("SHA256WITHRSAANDMGF1", aSN1ObjectIdentifier5);
        algorithms.put("SHA384WITHRSAANDMGF1", aSN1ObjectIdentifier5);
        algorithms.put("SHA512WITHRSAANDMGF1", aSN1ObjectIdentifier5);
        algorithms.put("RSAWITHSHA1", new ASN1ObjectIdentifier("1.2.840.113549.1.1.5"));
        Hashtable hashtable6 = algorithms;
        ASN1ObjectIdentifier aSN1ObjectIdentifier6 = TeleTrusTObjectIdentifiers.rsaSignatureWithripemd128;
        hashtable6.put("RIPEMD128WITHRSAENCRYPTION", aSN1ObjectIdentifier6);
        algorithms.put("RIPEMD128WITHRSA", aSN1ObjectIdentifier6);
        Hashtable hashtable7 = algorithms;
        ASN1ObjectIdentifier aSN1ObjectIdentifier7 = TeleTrusTObjectIdentifiers.rsaSignatureWithripemd160;
        hashtable7.put("RIPEMD160WITHRSAENCRYPTION", aSN1ObjectIdentifier7);
        algorithms.put("RIPEMD160WITHRSA", aSN1ObjectIdentifier7);
        Hashtable hashtable8 = algorithms;
        ASN1ObjectIdentifier aSN1ObjectIdentifier8 = TeleTrusTObjectIdentifiers.rsaSignatureWithripemd256;
        hashtable8.put("RIPEMD256WITHRSAENCRYPTION", aSN1ObjectIdentifier8);
        algorithms.put("RIPEMD256WITHRSA", aSN1ObjectIdentifier8);
        algorithms.put("SHA1WITHDSA", new ASN1ObjectIdentifier("1.2.840.10040.4.3"));
        algorithms.put("DSAWITHSHA1", new ASN1ObjectIdentifier("1.2.840.10040.4.3"));
        Hashtable hashtable9 = algorithms;
        ASN1ObjectIdentifier aSN1ObjectIdentifier9 = NISTObjectIdentifiers.dsa_with_sha224;
        hashtable9.put("SHA224WITHDSA", aSN1ObjectIdentifier9);
        Hashtable hashtable10 = algorithms;
        ASN1ObjectIdentifier aSN1ObjectIdentifier10 = NISTObjectIdentifiers.dsa_with_sha256;
        hashtable10.put("SHA256WITHDSA", aSN1ObjectIdentifier10);
        algorithms.put("SHA384WITHDSA", NISTObjectIdentifiers.dsa_with_sha384);
        algorithms.put("SHA512WITHDSA", NISTObjectIdentifiers.dsa_with_sha512);
        Hashtable hashtable11 = algorithms;
        ASN1ObjectIdentifier aSN1ObjectIdentifier11 = X9ObjectIdentifiers.ecdsa_with_SHA1;
        hashtable11.put("SHA1WITHECDSA", aSN1ObjectIdentifier11);
        Hashtable hashtable12 = algorithms;
        ASN1ObjectIdentifier aSN1ObjectIdentifier12 = X9ObjectIdentifiers.ecdsa_with_SHA224;
        hashtable12.put("SHA224WITHECDSA", aSN1ObjectIdentifier12);
        Hashtable hashtable13 = algorithms;
        ASN1ObjectIdentifier aSN1ObjectIdentifier13 = X9ObjectIdentifiers.ecdsa_with_SHA256;
        hashtable13.put("SHA256WITHECDSA", aSN1ObjectIdentifier13);
        Hashtable hashtable14 = algorithms;
        ASN1ObjectIdentifier aSN1ObjectIdentifier14 = X9ObjectIdentifiers.ecdsa_with_SHA384;
        hashtable14.put("SHA384WITHECDSA", aSN1ObjectIdentifier14);
        Hashtable hashtable15 = algorithms;
        ASN1ObjectIdentifier aSN1ObjectIdentifier15 = X9ObjectIdentifiers.ecdsa_with_SHA512;
        hashtable15.put("SHA512WITHECDSA", aSN1ObjectIdentifier15);
        algorithms.put("ECDSAWITHSHA1", aSN1ObjectIdentifier11);
        Hashtable hashtable16 = algorithms;
        ASN1ObjectIdentifier aSN1ObjectIdentifier16 = CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94;
        hashtable16.put("GOST3411WITHGOST3410", aSN1ObjectIdentifier16);
        algorithms.put("GOST3410WITHGOST3411", aSN1ObjectIdentifier16);
        Hashtable hashtable17 = algorithms;
        ASN1ObjectIdentifier aSN1ObjectIdentifier17 = CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001;
        hashtable17.put("GOST3411WITHECGOST3410", aSN1ObjectIdentifier17);
        algorithms.put("GOST3411WITHECGOST3410-2001", aSN1ObjectIdentifier17);
        algorithms.put("GOST3411WITHGOST3410-2001", aSN1ObjectIdentifier17);
        oids.put(new ASN1ObjectIdentifier("1.2.840.113549.1.1.5"), "SHA1WITHRSA");
        oids.put(aSN1ObjectIdentifier, "SHA224WITHRSA");
        oids.put(aSN1ObjectIdentifier2, "SHA256WITHRSA");
        oids.put(aSN1ObjectIdentifier3, "SHA384WITHRSA");
        oids.put(aSN1ObjectIdentifier4, "SHA512WITHRSA");
        oids.put(aSN1ObjectIdentifier16, "GOST3411WITHGOST3410");
        oids.put(aSN1ObjectIdentifier17, "GOST3411WITHECGOST3410");
        oids.put(new ASN1ObjectIdentifier("1.2.840.113549.1.1.4"), "MD5WITHRSA");
        oids.put(new ASN1ObjectIdentifier("1.2.840.113549.1.1.2"), "MD2WITHRSA");
        oids.put(new ASN1ObjectIdentifier("1.2.840.10040.4.3"), "SHA1WITHDSA");
        oids.put(aSN1ObjectIdentifier11, "SHA1WITHECDSA");
        oids.put(aSN1ObjectIdentifier12, "SHA224WITHECDSA");
        oids.put(aSN1ObjectIdentifier13, "SHA256WITHECDSA");
        oids.put(aSN1ObjectIdentifier14, "SHA384WITHECDSA");
        oids.put(aSN1ObjectIdentifier15, "SHA512WITHECDSA");
        oids.put(OIWObjectIdentifiers.sha1WithRSA, "SHA1WITHRSA");
        oids.put(OIWObjectIdentifiers.dsaWithSHA1, "SHA1WITHDSA");
        oids.put(aSN1ObjectIdentifier9, "SHA224WITHDSA");
        oids.put(aSN1ObjectIdentifier10, "SHA256WITHDSA");
        keyAlgorithms.put(PKCSObjectIdentifiers.rsaEncryption, "RSA");
        keyAlgorithms.put(X9ObjectIdentifiers.id_dsa, "DSA");
        noParams.add(aSN1ObjectIdentifier11);
        noParams.add(aSN1ObjectIdentifier12);
        noParams.add(aSN1ObjectIdentifier13);
        noParams.add(aSN1ObjectIdentifier14);
        noParams.add(aSN1ObjectIdentifier15);
        noParams.add(X9ObjectIdentifiers.id_dsa_with_sha1);
        noParams.add(aSN1ObjectIdentifier9);
        noParams.add(aSN1ObjectIdentifier10);
        noParams.add(aSN1ObjectIdentifier16);
        noParams.add(aSN1ObjectIdentifier17);
        ASN1ObjectIdentifier aSN1ObjectIdentifier18 = OIWObjectIdentifiers.idSHA1;
        DERNull dERNull = DERNull.INSTANCE;
        params.put("SHA1WITHRSAANDMGF1", creatPSSParams(new AlgorithmIdentifier(aSN1ObjectIdentifier18, dERNull), 20));
        params.put("SHA224WITHRSAANDMGF1", creatPSSParams(new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha224, dERNull), 28));
        params.put("SHA256WITHRSAANDMGF1", creatPSSParams(new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256, dERNull), 32));
        params.put("SHA384WITHRSAANDMGF1", creatPSSParams(new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384, dERNull), 48));
        params.put("SHA512WITHRSAANDMGF1", creatPSSParams(new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512, dERNull), 64));
    }

    public PKCS10CertificationRequest(byte[] bArr) {
        super(toDERSequence(bArr));
    }

    private static X509Name convertName(X500Principal x500Principal) {
        try {
            return new X509Principal(x500Principal.getEncoded());
        } catch (IOException unused) {
            throw new IllegalArgumentException("can't convert name");
        }
    }

    private static RSASSAPSSparams creatPSSParams(AlgorithmIdentifier algorithmIdentifier, int i2) {
        return new RSASSAPSSparams(algorithmIdentifier, new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, algorithmIdentifier), new ASN1Integer(i2), new ASN1Integer(1L));
    }

    private static String getDigestAlgName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return PKCSObjectIdentifiers.md5.equals(aSN1ObjectIdentifier) ? "MD5" : OIWObjectIdentifiers.idSHA1.equals(aSN1ObjectIdentifier) ? "SHA1" : NISTObjectIdentifiers.id_sha224.equals(aSN1ObjectIdentifier) ? "SHA224" : NISTObjectIdentifiers.id_sha256.equals(aSN1ObjectIdentifier) ? "SHA256" : NISTObjectIdentifiers.id_sha384.equals(aSN1ObjectIdentifier) ? "SHA384" : NISTObjectIdentifiers.id_sha512.equals(aSN1ObjectIdentifier) ? "SHA512" : TeleTrusTObjectIdentifiers.ripemd128.equals(aSN1ObjectIdentifier) ? "RIPEMD128" : TeleTrusTObjectIdentifiers.ripemd160.equals(aSN1ObjectIdentifier) ? "RIPEMD160" : TeleTrusTObjectIdentifiers.ripemd256.equals(aSN1ObjectIdentifier) ? "RIPEMD256" : CryptoProObjectIdentifiers.gostR3411.equals(aSN1ObjectIdentifier) ? "GOST3411" : aSN1ObjectIdentifier.getId();
    }

    public static String getSignatureName(AlgorithmIdentifier algorithmIdentifier) {
        ASN1Encodable parameters = algorithmIdentifier.getParameters();
        if (parameters == null || DERNull.INSTANCE.equals(parameters) || !algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.id_RSASSA_PSS)) {
            return algorithmIdentifier.getAlgorithm().getId();
        }
        return getDigestAlgName(RSASSAPSSparams.getInstance(parameters).getHashAlgorithm().getAlgorithm()) + "withRSAandMGF1";
    }

    private void setSignatureParameters(Signature signature, ASN1Encodable aSN1Encodable) throws NoSuchAlgorithmException, SignatureException {
        if (aSN1Encodable == null || DERNull.INSTANCE.equals(aSN1Encodable)) {
            return;
        }
        AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance(signature.getAlgorithm(), signature.getProvider());
        try {
            algorithmParameters.init(aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER));
            if (signature.getAlgorithm().endsWith("MGF1")) {
                try {
                    signature.setParameter(algorithmParameters.getParameterSpec(PSSParameterSpec.class));
                } catch (GeneralSecurityException e2) {
                    throw new SignatureException("Exception extracting parameters: " + e2.getMessage());
                }
            }
        } catch (IOException e3) {
            throw new SignatureException("IOException decoding parameters: " + e3.getMessage());
        }
    }

    private static ASN1Sequence toDERSequence(byte[] bArr) {
        try {
            return (ASN1Sequence) new ASN1InputStream(bArr).readObject();
        } catch (Exception unused) {
            throw new IllegalArgumentException("badly encoded request");
        }
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.util.Encodable
    public byte[] getEncoded() {
        try {
            return getEncoded(ASN1Encoding.DER);
        } catch (IOException e2) {
            throw new RuntimeException(e2.toString());
        }
    }

    public PublicKey getPublicKey() {
        return getPublicKey(BouncyCastleProvider.PROVIDER_NAME);
    }

    public boolean verify() {
        return verify(BouncyCastleProvider.PROVIDER_NAME);
    }

    public PKCS10CertificationRequest(ASN1Sequence aSN1Sequence) {
        super(aSN1Sequence);
    }

    public PublicKey getPublicKey(String str) throws NoSuchAlgorithmException, InvalidKeyException {
        SubjectPublicKeyInfo subjectPublicKeyInfo = this.reqInfo.getSubjectPublicKeyInfo();
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(new DERBitString(subjectPublicKeyInfo).getOctets());
            AlgorithmIdentifier algorithm = subjectPublicKeyInfo.getAlgorithm();
            try {
                return str == null ? KeyFactory.getInstance(algorithm.getAlgorithm().getId()).generatePublic(x509EncodedKeySpec) : KeyFactory.getInstance(algorithm.getAlgorithm().getId(), str).generatePublic(x509EncodedKeySpec);
            } catch (NoSuchAlgorithmException e2) {
                if (keyAlgorithms.get(algorithm.getAlgorithm()) == null) {
                    throw e2;
                }
                String str2 = (String) keyAlgorithms.get(algorithm.getAlgorithm());
                return str == null ? KeyFactory.getInstance(str2).generatePublic(x509EncodedKeySpec) : KeyFactory.getInstance(str2, str).generatePublic(x509EncodedKeySpec);
            }
        } catch (IOException unused) {
            throw new InvalidKeyException("error decoding public key");
        } catch (InvalidKeySpecException unused2) {
            throw new InvalidKeyException("error decoding public key");
        }
    }

    public boolean verify(String str) {
        return verify(getPublicKey(str), str);
    }

    public PKCS10CertificationRequest(String str, X509Name x509Name, PublicKey publicKey, ASN1Set aSN1Set, PrivateKey privateKey) {
        this(str, x509Name, publicKey, aSN1Set, privateKey, BouncyCastleProvider.PROVIDER_NAME);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [org.spongycastle.asn1.pkcs.CertificationRequest, org.spongycastle.jce.PKCS10CertificationRequest] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.security.Signature] */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.security.Signature] */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.security.Signature] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v9 */
    public boolean verify(PublicKey publicKey, String str) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, NoSuchProviderException {
        try {
            if (str == 0) {
                str = Signature.getInstance(getSignatureName(this.sigAlgId));
            } else {
                str = Signature.getInstance(getSignatureName(this.sigAlgId), (String) str);
            }
        } catch (NoSuchAlgorithmException e2) {
            if (oids.get(this.sigAlgId.getAlgorithm()) != null) {
                String str2 = (String) oids.get(this.sigAlgId.getAlgorithm());
                if (str == 0) {
                    str = Signature.getInstance(str2);
                } else {
                    str = Signature.getInstance(str2, (String) str);
                }
            } else {
                throw e2;
            }
        }
        setSignatureParameters(str, this.sigAlgId.getParameters());
        str.initVerify(publicKey);
        try {
            str.update(this.reqInfo.getEncoded(ASN1Encoding.DER));
            return str.verify(this.sigBits.getOctets());
        } catch (Exception e3) {
            throw new SignatureException("exception encoding TBS cert request - " + e3);
        }
    }

    public PKCS10CertificationRequest(String str, X500Principal x500Principal, PublicKey publicKey, ASN1Set aSN1Set, PrivateKey privateKey) {
        this(str, convertName(x500Principal), publicKey, aSN1Set, privateKey, BouncyCastleProvider.PROVIDER_NAME);
    }

    public PKCS10CertificationRequest(String str, X500Principal x500Principal, PublicKey publicKey, ASN1Set aSN1Set, PrivateKey privateKey, String str2) {
        this(str, convertName(x500Principal), publicKey, aSN1Set, privateKey, str2);
    }

    public PKCS10CertificationRequest(String str, X509Name x509Name, PublicKey publicKey, ASN1Set aSN1Set, PrivateKey privateKey, String str2) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException {
        Signature signature;
        String upperCase = Strings.toUpperCase(str);
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) algorithms.get(upperCase);
        if (aSN1ObjectIdentifier == null) {
            try {
                aSN1ObjectIdentifier = new ASN1ObjectIdentifier(upperCase);
            } catch (Exception unused) {
                throw new IllegalArgumentException("Unknown signature type requested");
            }
        }
        if (x509Name == null) {
            throw new IllegalArgumentException("subject must not be null");
        }
        if (publicKey != null) {
            if (noParams.contains(aSN1ObjectIdentifier)) {
                this.sigAlgId = new AlgorithmIdentifier(aSN1ObjectIdentifier);
            } else if (params.containsKey(upperCase)) {
                this.sigAlgId = new AlgorithmIdentifier(aSN1ObjectIdentifier, (ASN1Encodable) params.get(upperCase));
            } else {
                this.sigAlgId = new AlgorithmIdentifier(aSN1ObjectIdentifier, DERNull.INSTANCE);
            }
            try {
                this.reqInfo = new CertificationRequestInfo(x509Name, SubjectPublicKeyInfo.getInstance((ASN1Sequence) ASN1Primitive.fromByteArray(publicKey.getEncoded())), aSN1Set);
                if (str2 == null) {
                    signature = Signature.getInstance(str);
                } else {
                    signature = Signature.getInstance(str, str2);
                }
                signature.initSign(privateKey);
                try {
                    signature.update(this.reqInfo.getEncoded(ASN1Encoding.DER));
                    this.sigBits = new DERBitString(signature.sign());
                    return;
                } catch (Exception e2) {
                    throw new IllegalArgumentException("exception encoding TBS cert request - " + e2);
                }
            } catch (IOException unused2) {
                throw new IllegalArgumentException("can't encode public key");
            }
        }
        throw new IllegalArgumentException("public key must not be null");
    }
}
