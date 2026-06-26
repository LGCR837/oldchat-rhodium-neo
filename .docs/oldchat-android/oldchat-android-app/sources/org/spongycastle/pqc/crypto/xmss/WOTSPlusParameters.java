package org.spongycastle.pqc.crypto.xmss;

import org.spongycastle.crypto.Digest;

/* JADX INFO: loaded from: classes.dex */
final class WOTSPlusParameters {
    private final Digest digest;
    private final int digestSize;
    private final int len;
    private final int len1;
    private final int len2;
    private final XMSSOid oid;
    private final int winternitzParameter;

    public WOTSPlusParameters(Digest digest) {
        if (digest == null) {
            throw new NullPointerException("digest == null");
        }
        this.digest = digest;
        int digestSize = XMSSUtil.getDigestSize(digest);
        this.digestSize = digestSize;
        this.winternitzParameter = 16;
        double d2 = digestSize * 8;
        double dLog2 = XMSSUtil.log2(16);
        Double.isNaN(d2);
        Double.isNaN(dLog2);
        int iCeil = (int) Math.ceil(d2 / dLog2);
        this.len1 = iCeil;
        int iFloor = ((int) Math.floor(XMSSUtil.log2((16 - 1) * iCeil) / XMSSUtil.log2(16))) + 1;
        this.len2 = iFloor;
        int i2 = iCeil + iFloor;
        this.len = i2;
        WOTSPlusOid wOTSPlusOidLookup = WOTSPlusOid.lookup(digest.getAlgorithmName(), digestSize, 16, i2);
        this.oid = wOTSPlusOidLookup;
        if (wOTSPlusOidLookup != null) {
            return;
        }
        throw new IllegalArgumentException("cannot find OID for digest algorithm: " + digest.getAlgorithmName());
    }

    public Digest getDigest() {
        return this.digest;
    }

    public int getDigestSize() {
        return this.digestSize;
    }

    public int getLen() {
        return this.len;
    }

    public int getLen1() {
        return this.len1;
    }

    public int getLen2() {
        return this.len2;
    }

    public XMSSOid getOid() {
        return this.oid;
    }

    public int getWinternitzParameter() {
        return this.winternitzParameter;
    }
}
