package org.spongycastle.asn1.cryptopro;

import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Hashtable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.math.ec.ECConstants;
import org.spongycastle.math.ec.ECCurve;

/* JADX INFO: loaded from: classes.dex */
public class ECGOST3410NamedCurves {
    static final Hashtable names;
    static final Hashtable objIds;
    static final Hashtable params;

    static {
        Hashtable hashtable = new Hashtable();
        objIds = hashtable;
        Hashtable hashtable2 = new Hashtable();
        params = hashtable2;
        Hashtable hashtable3 = new Hashtable();
        names = hashtable3;
        BigInteger bigInteger = new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639319");
        BigInteger bigInteger2 = new BigInteger("115792089237316195423570985008687907853073762908499243225378155805079068850323");
        BigInteger bigInteger3 = new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639316");
        BigInteger bigInteger4 = new BigInteger("166");
        BigInteger bigInteger5 = ECConstants.ONE;
        ECCurve.Fp fp = new ECCurve.Fp(bigInteger, bigInteger3, bigInteger4, bigInteger2, bigInteger5);
        ECDomainParameters eCDomainParameters = new ECDomainParameters(fp, fp.createPoint(new BigInteger("1"), new BigInteger("64033881142927202683649881450433473985931760268884941288852745803908878638612")), bigInteger2);
        ASN1ObjectIdentifier aSN1ObjectIdentifier = CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_A;
        hashtable2.put(aSN1ObjectIdentifier, eCDomainParameters);
        BigInteger bigInteger6 = new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639319");
        BigInteger bigInteger7 = new BigInteger("115792089237316195423570985008687907853073762908499243225378155805079068850323");
        ECCurve.Fp fp2 = new ECCurve.Fp(bigInteger6, new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639316"), new BigInteger("166"), bigInteger7, bigInteger5);
        ECDomainParameters eCDomainParameters2 = new ECDomainParameters(fp2, fp2.createPoint(new BigInteger("1"), new BigInteger("64033881142927202683649881450433473985931760268884941288852745803908878638612")), bigInteger7);
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_XchA;
        hashtable2.put(aSN1ObjectIdentifier2, eCDomainParameters2);
        BigInteger bigInteger8 = new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564823193");
        BigInteger bigInteger9 = new BigInteger("57896044618658097711785492504343953927102133160255826820068844496087732066703");
        ECCurve.Fp fp3 = new ECCurve.Fp(bigInteger8, new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564823190"), new BigInteger("28091019353058090096996979000309560759124368558014865957655842872397301267595"), bigInteger9, bigInteger5);
        ECDomainParameters eCDomainParameters3 = new ECDomainParameters(fp3, fp3.createPoint(new BigInteger("1"), new BigInteger("28792665814854611296992347458380284135028636778229113005756334730996303888124")), bigInteger9);
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_B;
        hashtable2.put(aSN1ObjectIdentifier3, eCDomainParameters3);
        BigInteger bigInteger10 = new BigInteger("70390085352083305199547718019018437841079516630045180471284346843705633502619");
        BigInteger bigInteger11 = new BigInteger("70390085352083305199547718019018437840920882647164081035322601458352298396601");
        ECCurve.Fp fp4 = new ECCurve.Fp(bigInteger10, new BigInteger("70390085352083305199547718019018437841079516630045180471284346843705633502616"), new BigInteger("32858"), bigInteger11, bigInteger5);
        ECDomainParameters eCDomainParameters4 = new ECDomainParameters(fp4, fp4.createPoint(new BigInteger("0"), new BigInteger("29818893917731240733471273240314769927240550812383695689146495261604565990247")), bigInteger11);
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_XchB;
        hashtable2.put(aSN1ObjectIdentifier4, eCDomainParameters4);
        BigInteger bigInteger12 = new BigInteger("70390085352083305199547718019018437841079516630045180471284346843705633502619");
        BigInteger bigInteger13 = new BigInteger("70390085352083305199547718019018437840920882647164081035322601458352298396601");
        ECCurve.Fp fp5 = new ECCurve.Fp(bigInteger12, new BigInteger("70390085352083305199547718019018437841079516630045180471284346843705633502616"), new BigInteger("32858"), bigInteger13, bigInteger5);
        ECDomainParameters eCDomainParameters5 = new ECDomainParameters(fp5, fp5.createPoint(new BigInteger("0"), new BigInteger("29818893917731240733471273240314769927240550812383695689146495261604565990247")), bigInteger13);
        ASN1ObjectIdentifier aSN1ObjectIdentifier5 = CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_C;
        hashtable2.put(aSN1ObjectIdentifier5, eCDomainParameters5);
        BigInteger bigInteger14 = new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639319");
        BigInteger bigInteger15 = new BigInteger("115792089237316195423570985008687907853073762908499243225378155805079068850323");
        ECCurve.Fp fp6 = new ECCurve.Fp(bigInteger14, new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639316"), new BigInteger("166"), bigInteger15, bigInteger5);
        ECDomainParameters eCDomainParameters6 = new ECDomainParameters(fp6, fp6.createPoint(new BigInteger("1"), new BigInteger("64033881142927202683649881450433473985931760268884941288852745803908878638612")), bigInteger15);
        ASN1ObjectIdentifier aSN1ObjectIdentifier6 = RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256_paramSetA;
        hashtable2.put(aSN1ObjectIdentifier6, eCDomainParameters6);
        BigInteger bigInteger16 = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDC7", 16);
        BigInteger bigInteger17 = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF27E69532F48D89116FF22B8D4E0560609B4B38ABFAD2B85DCACDB1411F10B275", 16);
        ECCurve.Fp fp7 = new ECCurve.Fp(bigInteger16, new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDC4", 16), new BigInteger("E8C2505DEDFC86DDC1BD0B2B6667F1DA34B82574761CB0E879BD081CFD0B6265EE3CB090F30D27614CB4574010DA90DD862EF9D4EBEE4761503190785A71C760", 16), bigInteger17, bigInteger5);
        ECDomainParameters eCDomainParameters7 = new ECDomainParameters(fp7, fp7.createPoint(new BigInteger("00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003"), new BigInteger("7503CFE87A836AE3A61B8816E25450E6CE5E1C93ACF1ABC1778064FDCBEFA921DF1626BE4FD036E93D75E6A50E3A41E98028FE5FC235F5B889A589CB5215F2A4", 16)), bigInteger17);
        ASN1ObjectIdentifier aSN1ObjectIdentifier7 = RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512_paramSetA;
        hashtable2.put(aSN1ObjectIdentifier7, eCDomainParameters7);
        BigInteger bigInteger18 = new BigInteger("8000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006F", 16);
        BigInteger bigInteger19 = new BigInteger("800000000000000000000000000000000000000000000000000000000000000149A1EC142565A545ACFDB77BD9D40CFA8B996712101BEA0EC6346C54374F25BD", 16);
        ECCurve.Fp fp8 = new ECCurve.Fp(bigInteger18, new BigInteger("8000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006C", 16), new BigInteger("687D1B459DC841457E3E06CF6F5E2517B97C7D614AF138BCBF85DC806C4B289F3E965D2DB1416D217F8B276FAD1AB69C50F78BEE1FA3106EFB8CCBC7C5140116", 16), bigInteger19, bigInteger5);
        ECDomainParameters eCDomainParameters8 = new ECDomainParameters(fp8, fp8.createPoint(new BigInteger("00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000002"), new BigInteger("1A8F7EDA389B094C2C071E3647A8940F3C123B697578C213BE6DD9E6C8EC7335DCB228FD1EDF4A39152CBCAAF8C0398828041055F94CEEEC7E21340780FE41BD", 16)), bigInteger19);
        ASN1ObjectIdentifier aSN1ObjectIdentifier8 = RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512_paramSetB;
        hashtable2.put(aSN1ObjectIdentifier8, eCDomainParameters8);
        BigInteger bigInteger20 = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDC7", 16);
        BigInteger bigInteger21 = new BigInteger("3FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFC98CDBA46506AB004C33A9FF5147502CC8EDA9E7A769A12694623CEF47F023ED", 16);
        ECCurve.Fp fp9 = new ECCurve.Fp(bigInteger20, new BigInteger("DC9203E514A721875485A529D2C722FB187BC8980EB866644DE41C68E143064546E861C0E2C9EDD92ADE71F46FCF50FF2AD97F951FDA9F2A2EB6546F39689BD3", 16), new BigInteger("B4C4EE28CEBC6C2C8AC12952CF37F16AC7EFB6A9F69F4B57FFDA2E4F0DE5ADE038CBC2FFF719D2C18DE0284B8BFEF3B52B8CC7A5F5BF0A3C8D2319A5312557E1", 16), bigInteger21, bigInteger5);
        ECDomainParameters eCDomainParameters9 = new ECDomainParameters(fp9, fp9.createPoint(new BigInteger("E2E31EDFC23DE7BDEBE241CE593EF5DE2295B7A9CBAEF021D385F7074CEA043AA27272A7AE602BF2A7B9033DB9ED3610C6FB85487EAE97AAC5BC7928C1950148", 16), new BigInteger("F5CE40D95B5EB899ABBCCFF5911CB8577939804D6527378B8C108C3D2090FF9BE18E2D33E3021ED2EF32D85822423B6304F726AA854BAE07D0396E9A9ADDC40F", 16)), bigInteger21);
        ASN1ObjectIdentifier aSN1ObjectIdentifier9 = RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512_paramSetC;
        hashtable2.put(aSN1ObjectIdentifier9, eCDomainParameters9);
        hashtable.put("GostR3410-2001-CryptoPro-A", aSN1ObjectIdentifier);
        hashtable.put("GostR3410-2001-CryptoPro-B", aSN1ObjectIdentifier3);
        hashtable.put("GostR3410-2001-CryptoPro-C", aSN1ObjectIdentifier5);
        hashtable.put("GostR3410-2001-CryptoPro-XchA", aSN1ObjectIdentifier2);
        hashtable.put("GostR3410-2001-CryptoPro-XchB", aSN1ObjectIdentifier4);
        hashtable.put("Tc26-Gost-3410-12-256-paramSetA", aSN1ObjectIdentifier6);
        hashtable.put("Tc26-Gost-3410-12-512-paramSetA", aSN1ObjectIdentifier7);
        hashtable.put("Tc26-Gost-3410-12-512-paramSetB", aSN1ObjectIdentifier8);
        hashtable.put("Tc26-Gost-3410-12-512-paramSetC", aSN1ObjectIdentifier9);
        hashtable3.put(aSN1ObjectIdentifier, "GostR3410-2001-CryptoPro-A");
        hashtable3.put(aSN1ObjectIdentifier3, "GostR3410-2001-CryptoPro-B");
        hashtable3.put(aSN1ObjectIdentifier5, "GostR3410-2001-CryptoPro-C");
        hashtable3.put(aSN1ObjectIdentifier2, "GostR3410-2001-CryptoPro-XchA");
        hashtable3.put(aSN1ObjectIdentifier4, "GostR3410-2001-CryptoPro-XchB");
        hashtable3.put(aSN1ObjectIdentifier6, "Tc26-Gost-3410-12-256-paramSetA");
        hashtable3.put(aSN1ObjectIdentifier7, "Tc26-Gost-3410-12-512-paramSetA");
        hashtable3.put(aSN1ObjectIdentifier8, "Tc26-Gost-3410-12-512-paramSetB");
        hashtable3.put(aSN1ObjectIdentifier9, "Tc26-Gost-3410-12-512-paramSetC");
    }

    public static ECDomainParameters getByName(String str) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) objIds.get(str);
        if (aSN1ObjectIdentifier != null) {
            return (ECDomainParameters) params.get(aSN1ObjectIdentifier);
        }
        return null;
    }

    public static ECDomainParameters getByOID(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (ECDomainParameters) params.get(aSN1ObjectIdentifier);
    }

    public static String getName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (String) names.get(aSN1ObjectIdentifier);
    }

    public static Enumeration getNames() {
        return names.elements();
    }

    public static ASN1ObjectIdentifier getOID(String str) {
        return (ASN1ObjectIdentifier) objIds.get(str);
    }
}
