package org.spongycastle.pqc.crypto.xmss;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* JADX INFO: loaded from: classes.dex */
final class WOTSPlusOid implements XMSSOid {
    private static final Map<String, WOTSPlusOid> oidLookupTable;
    private final int oid;
    private final String stringRepresentation;

    static {
        HashMap map = new HashMap();
        map.put(createKey(McElieceCCA2KeyGenParameterSpec.SHA256, 32, 16, 67), new WOTSPlusOid(16777217, "WOTSP_SHA2-256_W16"));
        map.put(createKey(McElieceCCA2KeyGenParameterSpec.SHA512, 64, 16, 131), new WOTSPlusOid(33554434, "WOTSP_SHA2-512_W16"));
        map.put(createKey("SHAKE128", 32, 16, 67), new WOTSPlusOid(50331651, "WOTSP_SHAKE128_W16"));
        map.put(createKey("SHAKE256", 64, 16, 131), new WOTSPlusOid(67108868, "WOTSP_SHAKE256_W16"));
        oidLookupTable = Collections.unmodifiableMap(map);
    }

    private WOTSPlusOid(int i2, String str) {
        this.oid = i2;
        this.stringRepresentation = str;
    }

    private static String createKey(String str, int i2, int i3, int i4) {
        if (str == null) {
            throw new NullPointerException("algorithmName == null");
        }
        return str + "-" + i2 + "-" + i3 + "-" + i4;
    }

    public static WOTSPlusOid lookup(String str, int i2, int i3, int i4) {
        if (str != null) {
            return oidLookupTable.get(createKey(str, i2, i3, i4));
        }
        throw new NullPointerException("algorithmName == null");
    }

    @Override // org.spongycastle.pqc.crypto.xmss.XMSSOid
    public int getOid() {
        return this.oid;
    }

    @Override // org.spongycastle.pqc.crypto.xmss.XMSSOid
    public String toString() {
        return this.stringRepresentation;
    }
}
