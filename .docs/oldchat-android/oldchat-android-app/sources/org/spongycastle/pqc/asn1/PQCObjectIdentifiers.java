package org.spongycastle.pqc.asn1;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.bc.BCObjectIdentifiers;
import org.spongycastle.pqc.crypto.gmss.GMSSKeyPairGenerator;
import org.spongycastle.pqc.crypto.mceliece.McElieceFujisakiCipher;
import org.spongycastle.pqc.crypto.mceliece.McElieceKobaraImaiCipher;
import org.spongycastle.pqc.crypto.mceliece.McEliecePointchevalCipher;

/* JADX INFO: loaded from: classes.dex */
public interface PQCObjectIdentifiers {
    public static final ASN1ObjectIdentifier gmss;
    public static final ASN1ObjectIdentifier gmssWithSha1;
    public static final ASN1ObjectIdentifier gmssWithSha224;
    public static final ASN1ObjectIdentifier gmssWithSha256;
    public static final ASN1ObjectIdentifier gmssWithSha384;
    public static final ASN1ObjectIdentifier gmssWithSha512;
    public static final ASN1ObjectIdentifier mcEliece;
    public static final ASN1ObjectIdentifier mcElieceCca2;
    public static final ASN1ObjectIdentifier mcElieceFujisaki;
    public static final ASN1ObjectIdentifier mcElieceKobara_Imai;
    public static final ASN1ObjectIdentifier mcEliecePointcheval;
    public static final ASN1ObjectIdentifier newHope;
    public static final ASN1ObjectIdentifier rainbow;
    public static final ASN1ObjectIdentifier rainbowWithSha1;
    public static final ASN1ObjectIdentifier rainbowWithSha224;
    public static final ASN1ObjectIdentifier rainbowWithSha256;
    public static final ASN1ObjectIdentifier rainbowWithSha384;
    public static final ASN1ObjectIdentifier rainbowWithSha512;
    public static final ASN1ObjectIdentifier sphincs256;
    public static final ASN1ObjectIdentifier sphincs256_with_BLAKE512;
    public static final ASN1ObjectIdentifier sphincs256_with_SHA3_512;
    public static final ASN1ObjectIdentifier sphincs256_with_SHA512;
    public static final ASN1ObjectIdentifier xmss;
    public static final ASN1ObjectIdentifier xmss_mt;
    public static final ASN1ObjectIdentifier xmss_mt_with_SHA256;
    public static final ASN1ObjectIdentifier xmss_mt_with_SHA512;
    public static final ASN1ObjectIdentifier xmss_mt_with_SHAKE128;
    public static final ASN1ObjectIdentifier xmss_mt_with_SHAKE256;
    public static final ASN1ObjectIdentifier xmss_with_SHA256;
    public static final ASN1ObjectIdentifier xmss_with_SHA512;
    public static final ASN1ObjectIdentifier xmss_with_SHAKE128;
    public static final ASN1ObjectIdentifier xmss_with_SHAKE256;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.3.6.1.4.1.8301.3.1.3.5.3.2");
        rainbow = aSN1ObjectIdentifier;
        rainbowWithSha1 = aSN1ObjectIdentifier.branch("1");
        rainbowWithSha224 = aSN1ObjectIdentifier.branch("2");
        rainbowWithSha256 = aSN1ObjectIdentifier.branch("3");
        rainbowWithSha384 = aSN1ObjectIdentifier.branch("4");
        rainbowWithSha512 = aSN1ObjectIdentifier.branch("5");
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = new ASN1ObjectIdentifier(GMSSKeyPairGenerator.OID);
        gmss = aSN1ObjectIdentifier2;
        gmssWithSha1 = aSN1ObjectIdentifier2.branch("1");
        gmssWithSha224 = aSN1ObjectIdentifier2.branch("2");
        gmssWithSha256 = aSN1ObjectIdentifier2.branch("3");
        gmssWithSha384 = aSN1ObjectIdentifier2.branch("4");
        gmssWithSha512 = aSN1ObjectIdentifier2.branch("5");
        mcEliece = new ASN1ObjectIdentifier("1.3.6.1.4.1.8301.3.1.3.4.1");
        mcElieceCca2 = new ASN1ObjectIdentifier("1.3.6.1.4.1.8301.3.1.3.4.2");
        mcElieceFujisaki = new ASN1ObjectIdentifier(McElieceFujisakiCipher.OID);
        mcEliecePointcheval = new ASN1ObjectIdentifier(McEliecePointchevalCipher.OID);
        mcElieceKobara_Imai = new ASN1ObjectIdentifier(McElieceKobaraImaiCipher.OID);
        sphincs256 = BCObjectIdentifiers.sphincs256;
        sphincs256_with_BLAKE512 = BCObjectIdentifiers.sphincs256_with_BLAKE512;
        sphincs256_with_SHA512 = BCObjectIdentifiers.sphincs256_with_SHA512;
        sphincs256_with_SHA3_512 = BCObjectIdentifiers.sphincs256_with_SHA3_512;
        newHope = BCObjectIdentifiers.newHope;
        xmss = BCObjectIdentifiers.xmss;
        xmss_with_SHA256 = BCObjectIdentifiers.xmss_with_SHA256;
        xmss_with_SHA512 = BCObjectIdentifiers.xmss_with_SHA512;
        xmss_with_SHAKE128 = BCObjectIdentifiers.xmss_with_SHAKE128;
        xmss_with_SHAKE256 = BCObjectIdentifiers.xmss_with_SHAKE256;
        xmss_mt = BCObjectIdentifiers.xmss_mt;
        xmss_mt_with_SHA256 = BCObjectIdentifiers.xmss_mt_with_SHA256;
        xmss_mt_with_SHA512 = BCObjectIdentifiers.xmss_mt_with_SHA512;
        xmss_mt_with_SHAKE128 = BCObjectIdentifiers.xmss_mt_with_SHAKE128;
        xmss_mt_with_SHAKE256 = BCObjectIdentifiers.xmss_mt_with_SHAKE256;
    }
}
