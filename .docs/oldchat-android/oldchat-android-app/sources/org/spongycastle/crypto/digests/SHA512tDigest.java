package org.spongycastle.crypto.digests;

import org.spongycastle.asn1.cmc.BodyPartID;
import org.spongycastle.util.Memoable;
import org.spongycastle.util.MemoableResetException;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class SHA512tDigest extends LongDigest {
    private long H1t;
    private long H2t;
    private long H3t;
    private long H4t;
    private long H5t;
    private long H6t;
    private long H7t;
    private long H8t;
    private int digestLength;

    public SHA512tDigest(int i2) {
        if (i2 >= 512) {
            throw new IllegalArgumentException("bitLength cannot be >= 512");
        }
        if (i2 % 8 != 0) {
            throw new IllegalArgumentException("bitLength needs to be a multiple of 8");
        }
        if (i2 == 384) {
            throw new IllegalArgumentException("bitLength cannot be 384 use SHA384 instead");
        }
        int i3 = i2 / 8;
        this.digestLength = i3;
        tIvGenerate(i3 * 8);
        reset();
    }

    private static void intToBigEndian(int i2, byte[] bArr, int i3, int i4) {
        int iMin = Math.min(4, i4);
        while (true) {
            iMin--;
            if (iMin < 0) {
                return;
            } else {
                bArr[i3 + iMin] = (byte) (i2 >>> ((3 - iMin) * 8));
            }
        }
    }

    private static void longToBigEndian(long j2, byte[] bArr, int i2, int i3) {
        if (i3 > 0) {
            intToBigEndian((int) (j2 >>> 32), bArr, i2, i3);
            if (i3 > 4) {
                intToBigEndian((int) (j2 & BodyPartID.bodyIdMax), bArr, i2 + 4, i3 - 4);
            }
        }
    }

    private static int readDigestLength(byte[] bArr) {
        return Pack.bigEndianToInt(bArr, bArr.length - 4);
    }

    private void tIvGenerate(int i2) {
        this.H1 = -3482333909917012819L;
        this.H2 = 2216346199247487646L;
        this.H3 = -7364697282686394994L;
        this.H4 = 65953792586715988L;
        this.H5 = -816286391624063116L;
        this.H6 = 4512832404995164602L;
        this.H7 = -5033199132376557362L;
        this.H8 = -124578254951840548L;
        update((byte) 83);
        update((byte) 72);
        update((byte) 65);
        update((byte) 45);
        update((byte) 53);
        update((byte) 49);
        update((byte) 50);
        update((byte) 47);
        if (i2 > 100) {
            update((byte) ((i2 / 100) + 48));
            int i3 = i2 % 100;
            update((byte) ((i3 / 10) + 48));
            update((byte) ((i3 % 10) + 48));
        } else if (i2 > 10) {
            update((byte) ((i2 / 10) + 48));
            update((byte) ((i2 % 10) + 48));
        } else {
            update((byte) (i2 + 48));
        }
        finish();
        this.H1t = this.H1;
        this.H2t = this.H2;
        this.H3t = this.H3;
        this.H4t = this.H4;
        this.H5t = this.H5;
        this.H6t = this.H6;
        this.H7t = this.H7;
        this.H8t = this.H8;
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new SHA512tDigest(this);
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i2) {
        finish();
        longToBigEndian(this.H1, bArr, i2, this.digestLength);
        longToBigEndian(this.H2, bArr, i2 + 8, this.digestLength - 8);
        longToBigEndian(this.H3, bArr, i2 + 16, this.digestLength - 16);
        longToBigEndian(this.H4, bArr, i2 + 24, this.digestLength - 24);
        longToBigEndian(this.H5, bArr, i2 + 32, this.digestLength - 32);
        longToBigEndian(this.H6, bArr, i2 + 40, this.digestLength - 40);
        longToBigEndian(this.H7, bArr, i2 + 48, this.digestLength - 48);
        longToBigEndian(this.H8, bArr, i2 + 56, this.digestLength - 56);
        reset();
        return this.digestLength;
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHA-512/" + Integer.toString(this.digestLength * 8);
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return this.digestLength;
    }

    @Override // org.spongycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        int encodedStateSize = getEncodedStateSize();
        byte[] bArr = new byte[encodedStateSize + 4];
        populateState(bArr);
        Pack.intToBigEndian(this.digestLength * 8, bArr, encodedStateSize);
        return bArr;
    }

    @Override // org.spongycastle.crypto.digests.LongDigest, org.spongycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.H1 = this.H1t;
        this.H2 = this.H2t;
        this.H3 = this.H3t;
        this.H4 = this.H4t;
        this.H5 = this.H5t;
        this.H6 = this.H6t;
        this.H7 = this.H7t;
        this.H8 = this.H8t;
    }

    public SHA512tDigest(SHA512tDigest sHA512tDigest) {
        super(sHA512tDigest);
        this.digestLength = sHA512tDigest.digestLength;
        reset(sHA512tDigest);
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        SHA512tDigest sHA512tDigest = (SHA512tDigest) memoable;
        if (this.digestLength == sHA512tDigest.digestLength) {
            super.copyIn(sHA512tDigest);
            this.H1t = sHA512tDigest.H1t;
            this.H2t = sHA512tDigest.H2t;
            this.H3t = sHA512tDigest.H3t;
            this.H4t = sHA512tDigest.H4t;
            this.H5t = sHA512tDigest.H5t;
            this.H6t = sHA512tDigest.H6t;
            this.H7t = sHA512tDigest.H7t;
            this.H8t = sHA512tDigest.H8t;
            return;
        }
        throw new MemoableResetException("digestLength inappropriate in other");
    }

    public SHA512tDigest(byte[] bArr) {
        this(readDigestLength(bArr));
        restoreState(bArr);
    }
}
