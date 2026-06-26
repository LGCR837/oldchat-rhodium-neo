package org.spongycastle.jcajce.provider.asymmetric.util;

import java.util.HashSet;
import java.util.Set;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.util.Strings;

/* JADX INFO: loaded from: classes.dex */
public class DESUtil {
    private static final Set<String> des;

    static {
        HashSet hashSet = new HashSet();
        des = hashSet;
        hashSet.add("DES");
        hashSet.add("DESEDE");
        hashSet.add(OIWObjectIdentifiers.desCBC.getId());
        ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.des_EDE3_CBC;
        hashSet.add(aSN1ObjectIdentifier.getId());
        hashSet.add(aSN1ObjectIdentifier.getId());
        hashSet.add(PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId());
    }

    public static boolean isDES(String str) {
        return des.contains(Strings.toUpperCase(str));
    }

    public static void setOddParity(byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b2 = bArr[i2];
            bArr[i2] = (byte) (((((b2 >> 7) ^ ((((((b2 >> 1) ^ (b2 >> 2)) ^ (b2 >> 3)) ^ (b2 >> 4)) ^ (b2 >> 5)) ^ (b2 >> 6))) ^ 1) & 1) | (b2 & 254));
        }
    }
}
