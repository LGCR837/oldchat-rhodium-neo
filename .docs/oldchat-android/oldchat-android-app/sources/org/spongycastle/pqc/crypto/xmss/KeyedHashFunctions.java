package org.spongycastle.pqc.crypto.xmss;

import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Xof;

/* JADX INFO: loaded from: classes.dex */
final class KeyedHashFunctions {
    private final Digest digest;
    private final int digestSize;

    public KeyedHashFunctions(Digest digest, int i2) {
        if (digest == null) {
            throw new NullPointerException("digest == null");
        }
        this.digest = digest;
        this.digestSize = i2;
    }

    private byte[] coreDigest(int i2, byte[] bArr, byte[] bArr2) {
        byte[] bytesBigEndian = XMSSUtil.toBytesBigEndian(i2, this.digestSize);
        this.digest.update(bytesBigEndian, 0, bytesBigEndian.length);
        this.digest.update(bArr, 0, bArr.length);
        this.digest.update(bArr2, 0, bArr2.length);
        int i3 = this.digestSize;
        byte[] bArr3 = new byte[i3];
        Digest digest = this.digest;
        if (digest instanceof Xof) {
            ((Xof) digest).doFinal(bArr3, 0, i3);
        } else {
            digest.doFinal(bArr3, 0);
        }
        return bArr3;
    }

    public byte[] F(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int i2 = this.digestSize;
        if (length != i2) {
            throw new IllegalArgumentException("wrong key length");
        }
        if (bArr2.length == i2) {
            return coreDigest(0, bArr, bArr2);
        }
        throw new IllegalArgumentException("wrong in length");
    }

    public byte[] H(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int i2 = this.digestSize;
        if (length != i2) {
            throw new IllegalArgumentException("wrong key length");
        }
        if (bArr2.length == i2 * 2) {
            return coreDigest(1, bArr, bArr2);
        }
        throw new IllegalArgumentException("wrong in length");
    }

    public byte[] HMsg(byte[] bArr, byte[] bArr2) {
        if (bArr.length == this.digestSize * 3) {
            return coreDigest(2, bArr, bArr2);
        }
        throw new IllegalArgumentException("wrong key length");
    }

    public byte[] PRF(byte[] bArr, byte[] bArr2) {
        if (bArr.length != this.digestSize) {
            throw new IllegalArgumentException("wrong key length");
        }
        if (bArr2.length == 32) {
            return coreDigest(3, bArr, bArr2);
        }
        throw new IllegalArgumentException("wrong address length");
    }
}
