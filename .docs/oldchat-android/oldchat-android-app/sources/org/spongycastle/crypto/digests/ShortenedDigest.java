package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.ExtendedDigest;

/* JADX INFO: loaded from: classes.dex */
public class ShortenedDigest implements ExtendedDigest {
    private ExtendedDigest baseDigest;
    private int length;

    public ShortenedDigest(ExtendedDigest extendedDigest, int i2) {
        if (extendedDigest == null) {
            throw new IllegalArgumentException("baseDigest must not be null");
        }
        if (i2 > extendedDigest.getDigestSize()) {
            throw new IllegalArgumentException("baseDigest output not large enough to support length");
        }
        this.baseDigest = extendedDigest;
        this.length = i2;
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i2) {
        byte[] bArr2 = new byte[this.baseDigest.getDigestSize()];
        this.baseDigest.doFinal(bArr2, 0);
        System.arraycopy(bArr2, 0, bArr, i2, this.length);
        return this.length;
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return this.baseDigest.getAlgorithmName() + "(" + (this.length * 8) + ")";
    }

    @Override // org.spongycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return this.baseDigest.getByteLength();
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return this.length;
    }

    @Override // org.spongycastle.crypto.Digest
    public void reset() {
        this.baseDigest.reset();
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte b2) {
        this.baseDigest.update(b2);
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte[] bArr, int i2, int i3) {
        this.baseDigest.update(bArr, i2, i3);
    }
}
