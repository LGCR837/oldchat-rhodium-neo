package org.spongycastle.asn1.bc;

import org.spongycastle.asn1.ASN1ObjectIdentifier;

/* JADX INFO: loaded from: classes.dex */
public interface BCObjectIdentifiers {
    public static final ASN1ObjectIdentifier bc;
    public static final ASN1ObjectIdentifier bc_exch;
    public static final ASN1ObjectIdentifier bc_pbe;
    public static final ASN1ObjectIdentifier bc_pbe_sha1;
    public static final ASN1ObjectIdentifier bc_pbe_sha1_pkcs12;
    public static final ASN1ObjectIdentifier bc_pbe_sha1_pkcs12_aes128_cbc;
    public static final ASN1ObjectIdentifier bc_pbe_sha1_pkcs12_aes192_cbc;
    public static final ASN1ObjectIdentifier bc_pbe_sha1_pkcs12_aes256_cbc;
    public static final ASN1ObjectIdentifier bc_pbe_sha1_pkcs5;
    public static final ASN1ObjectIdentifier bc_pbe_sha224;
    public static final ASN1ObjectIdentifier bc_pbe_sha256;
    public static final ASN1ObjectIdentifier bc_pbe_sha256_pkcs12;
    public static final ASN1ObjectIdentifier bc_pbe_sha256_pkcs12_aes128_cbc;
    public static final ASN1ObjectIdentifier bc_pbe_sha256_pkcs12_aes192_cbc;
    public static final ASN1ObjectIdentifier bc_pbe_sha256_pkcs12_aes256_cbc;
    public static final ASN1ObjectIdentifier bc_pbe_sha256_pkcs5;
    public static final ASN1ObjectIdentifier bc_pbe_sha384;
    public static final ASN1ObjectIdentifier bc_pbe_sha512;
    public static final ASN1ObjectIdentifier bc_sig;
    public static final ASN1ObjectIdentifier newHope;
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
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.3.6.1.4.1.22554");
        bc = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch = aSN1ObjectIdentifier.branch("1");
        bc_pbe = aSN1ObjectIdentifierBranch;
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch2 = aSN1ObjectIdentifierBranch.branch("1");
        bc_pbe_sha1 = aSN1ObjectIdentifierBranch2;
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch3 = aSN1ObjectIdentifierBranch.branch("2.1");
        bc_pbe_sha256 = aSN1ObjectIdentifierBranch3;
        bc_pbe_sha384 = aSN1ObjectIdentifierBranch.branch("2.2");
        bc_pbe_sha512 = aSN1ObjectIdentifierBranch.branch("2.3");
        bc_pbe_sha224 = aSN1ObjectIdentifierBranch.branch("2.4");
        bc_pbe_sha1_pkcs5 = aSN1ObjectIdentifierBranch2.branch("1");
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch4 = aSN1ObjectIdentifierBranch2.branch("2");
        bc_pbe_sha1_pkcs12 = aSN1ObjectIdentifierBranch4;
        bc_pbe_sha256_pkcs5 = aSN1ObjectIdentifierBranch3.branch("1");
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch5 = aSN1ObjectIdentifierBranch3.branch("2");
        bc_pbe_sha256_pkcs12 = aSN1ObjectIdentifierBranch5;
        bc_pbe_sha1_pkcs12_aes128_cbc = aSN1ObjectIdentifierBranch4.branch("1.2");
        bc_pbe_sha1_pkcs12_aes192_cbc = aSN1ObjectIdentifierBranch4.branch("1.22");
        bc_pbe_sha1_pkcs12_aes256_cbc = aSN1ObjectIdentifierBranch4.branch("1.42");
        bc_pbe_sha256_pkcs12_aes128_cbc = aSN1ObjectIdentifierBranch5.branch("1.2");
        bc_pbe_sha256_pkcs12_aes192_cbc = aSN1ObjectIdentifierBranch5.branch("1.22");
        bc_pbe_sha256_pkcs12_aes256_cbc = aSN1ObjectIdentifierBranch5.branch("1.42");
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch6 = aSN1ObjectIdentifier.branch("2");
        bc_sig = aSN1ObjectIdentifierBranch6;
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch7 = aSN1ObjectIdentifierBranch6.branch("1");
        sphincs256 = aSN1ObjectIdentifierBranch7;
        sphincs256_with_BLAKE512 = aSN1ObjectIdentifierBranch7.branch("1");
        sphincs256_with_SHA512 = aSN1ObjectIdentifierBranch7.branch("2");
        sphincs256_with_SHA3_512 = aSN1ObjectIdentifierBranch7.branch("3");
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch8 = aSN1ObjectIdentifierBranch6.branch("2");
        xmss = aSN1ObjectIdentifierBranch8;
        xmss_with_SHA256 = aSN1ObjectIdentifierBranch8.branch("1");
        xmss_with_SHA512 = aSN1ObjectIdentifierBranch8.branch("2");
        xmss_with_SHAKE128 = aSN1ObjectIdentifierBranch8.branch("3");
        xmss_with_SHAKE256 = aSN1ObjectIdentifierBranch8.branch("4");
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch9 = aSN1ObjectIdentifierBranch6.branch("3");
        xmss_mt = aSN1ObjectIdentifierBranch9;
        xmss_mt_with_SHA256 = aSN1ObjectIdentifierBranch9.branch("1");
        xmss_mt_with_SHA512 = aSN1ObjectIdentifierBranch9.branch("2");
        xmss_mt_with_SHAKE128 = aSN1ObjectIdentifierBranch9.branch("3");
        xmss_mt_with_SHAKE256 = aSN1ObjectIdentifierBranch9.branch("4");
        ASN1ObjectIdentifier aSN1ObjectIdentifierBranch10 = aSN1ObjectIdentifier.branch("3");
        bc_exch = aSN1ObjectIdentifierBranch10;
        newHope = aSN1ObjectIdentifierBranch10.branch("1");
    }
}
