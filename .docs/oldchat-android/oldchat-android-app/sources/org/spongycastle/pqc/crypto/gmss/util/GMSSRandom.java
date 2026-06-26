package org.spongycastle.pqc.crypto.gmss.util;

import org.spongycastle.crypto.Digest;

/* JADX INFO: loaded from: classes.dex */
public class GMSSRandom {
    private Digest messDigestTree;

    public GMSSRandom(Digest digest) {
        this.messDigestTree = digest;
    }

    private void addByteArrays(byte[] bArr, byte[] bArr2) {
        byte b2 = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = (bArr[i2] & 255) + (bArr2[i2] & 255) + b2;
            bArr[i2] = (byte) i3;
            b2 = (byte) (i3 >> 8);
        }
    }

    private void addOne(byte[] bArr) {
        byte b2 = 1;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = (bArr[i2] & 255) + b2;
            bArr[i2] = (byte) i3;
            b2 = (byte) (i3 >> 8);
        }
    }

    public byte[] nextSeed(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        this.messDigestTree.update(bArr, 0, bArr.length);
        byte[] bArr3 = new byte[this.messDigestTree.getDigestSize()];
        this.messDigestTree.doFinal(bArr3, 0);
        addByteArrays(bArr, bArr3);
        addOne(bArr);
        return bArr3;
    }
}
