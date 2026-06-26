package org.spongycastle.pqc.crypto.xmss;

import org.spongycastle.crypto.Digest;

/* JADX INFO: loaded from: classes.dex */
public final class XMSSParameters {
    private final int height;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final int f7678k;
    private final XMSSOid oid;
    private final WOTSPlus wotsPlus;

    public XMSSParameters(int i2, Digest digest) {
        if (i2 < 2) {
            throw new IllegalArgumentException("height must be >= 2");
        }
        if (digest == null) {
            throw new NullPointerException("digest == null");
        }
        WOTSPlus wOTSPlus = new WOTSPlus(new WOTSPlusParameters(digest));
        this.wotsPlus = wOTSPlus;
        this.height = i2;
        this.f7678k = determineMinK();
        this.oid = DefaultXMSSOid.lookup(getDigest().getAlgorithmName(), getDigestSize(), getWinternitzParameter(), wOTSPlus.getParams().getLen(), i2);
    }

    private int determineMinK() {
        int i2 = 2;
        while (true) {
            int i3 = this.height;
            if (i2 > i3) {
                throw new IllegalStateException("should never happen...");
            }
            if ((i3 - i2) % 2 == 0) {
                return i2;
            }
            i2++;
        }
    }

    public Digest getDigest() {
        return this.wotsPlus.getParams().getDigest();
    }

    public int getDigestSize() {
        return this.wotsPlus.getParams().getDigestSize();
    }

    public int getHeight() {
        return this.height;
    }

    public int getK() {
        return this.f7678k;
    }

    public WOTSPlus getWOTSPlus() {
        return this.wotsPlus;
    }

    public int getWinternitzParameter() {
        return this.wotsPlus.getParams().getWinternitzParameter();
    }
}
