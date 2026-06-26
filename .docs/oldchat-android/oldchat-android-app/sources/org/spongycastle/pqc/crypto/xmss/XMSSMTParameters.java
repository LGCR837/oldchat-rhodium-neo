package org.spongycastle.pqc.crypto.xmss;

import org.spongycastle.crypto.Digest;

/* JADX INFO: loaded from: classes.dex */
public final class XMSSMTParameters {
    private final int height;
    private final int layers;
    private final XMSSOid oid;
    private final XMSSParameters xmssParams;

    public XMSSMTParameters(int i2, int i3, Digest digest) {
        this.height = i2;
        this.layers = i3;
        this.xmssParams = new XMSSParameters(xmssTreeHeight(i2, i3), digest);
        this.oid = DefaultXMSSMTOid.lookup(getDigest().getAlgorithmName(), getDigestSize(), getWinternitzParameter(), getLen(), getHeight(), i3);
    }

    private static int xmssTreeHeight(int i2, int i3) {
        if (i2 < 2) {
            throw new IllegalArgumentException("totalHeight must be > 1");
        }
        if (i2 % i3 != 0) {
            throw new IllegalArgumentException("layers must divide totalHeight without remainder");
        }
        int i4 = i2 / i3;
        if (i4 != 1) {
            return i4;
        }
        throw new IllegalArgumentException("height / layers must be greater than 1");
    }

    public Digest getDigest() {
        return this.xmssParams.getDigest();
    }

    public int getDigestSize() {
        return this.xmssParams.getDigestSize();
    }

    public int getHeight() {
        return this.height;
    }

    public int getLayers() {
        return this.layers;
    }

    public int getLen() {
        return this.xmssParams.getWOTSPlus().getParams().getLen();
    }

    public WOTSPlus getWOTSPlus() {
        return this.xmssParams.getWOTSPlus();
    }

    public int getWinternitzParameter() {
        return this.xmssParams.getWinternitzParameter();
    }

    public XMSSParameters getXMSSParameters() {
        return this.xmssParams;
    }
}
