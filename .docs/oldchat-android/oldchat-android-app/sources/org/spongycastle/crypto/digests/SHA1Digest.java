package org.spongycastle.crypto.digests;

import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class SHA1Digest extends GeneralDigest implements EncodableDigest {
    private static final int DIGEST_LENGTH = 20;
    private static final int Y1 = 1518500249;
    private static final int Y2 = 1859775393;
    private static final int Y3 = -1894007588;
    private static final int Y4 = -899497514;
    private int H1;
    private int H2;
    private int H3;
    private int H4;
    private int H5;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    private int[] f7295X;
    private int xOff;

    public SHA1Digest() {
        this.f7295X = new int[80];
        reset();
    }

    private void copyIn(SHA1Digest sHA1Digest) {
        this.H1 = sHA1Digest.H1;
        this.H2 = sHA1Digest.H2;
        this.H3 = sHA1Digest.H3;
        this.H4 = sHA1Digest.H4;
        this.H5 = sHA1Digest.H5;
        int[] iArr = sHA1Digest.f7295X;
        System.arraycopy(iArr, 0, this.f7295X, 0, iArr.length);
        this.xOff = sHA1Digest.xOff;
    }

    private int f(int i2, int i3, int i4) {
        return ((i2 ^ (-1)) & i4) | (i3 & i2);
    }

    private int g(int i2, int i3, int i4) {
        return (i2 & (i3 | i4)) | (i3 & i4);
    }

    private int h(int i2, int i3, int i4) {
        return (i2 ^ i3) ^ i4;
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new SHA1Digest(this);
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i2) {
        finish();
        Pack.intToBigEndian(this.H1, bArr, i2);
        Pack.intToBigEndian(this.H2, bArr, i2 + 4);
        Pack.intToBigEndian(this.H3, bArr, i2 + 8);
        Pack.intToBigEndian(this.H4, bArr, i2 + 12);
        Pack.intToBigEndian(this.H5, bArr, i2 + 16);
        reset();
        return 20;
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return McElieceCCA2KeyGenParameterSpec.SHA1;
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return 20;
    }

    @Override // org.spongycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] bArr = new byte[(this.xOff * 4) + 40];
        super.populateState(bArr);
        Pack.intToBigEndian(this.H1, bArr, 16);
        Pack.intToBigEndian(this.H2, bArr, 20);
        Pack.intToBigEndian(this.H3, bArr, 24);
        Pack.intToBigEndian(this.H4, bArr, 28);
        Pack.intToBigEndian(this.H5, bArr, 32);
        Pack.intToBigEndian(this.xOff, bArr, 36);
        for (int i2 = 0; i2 != this.xOff; i2++) {
            Pack.intToBigEndian(this.f7295X[i2], bArr, (i2 * 4) + 40);
        }
        return bArr;
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    public void processBlock() {
        for (int i2 = 16; i2 < 80; i2++) {
            int[] iArr = this.f7295X;
            int i3 = ((iArr[i2 - 3] ^ iArr[i2 - 8]) ^ iArr[i2 - 14]) ^ iArr[i2 - 16];
            iArr[i2] = (i3 >>> 31) | (i3 << 1);
        }
        int iH = this.H1;
        int iH2 = this.H2;
        int i4 = this.H3;
        int i5 = this.H4;
        int i6 = this.H5;
        int i7 = 0;
        for (int i8 = 0; i8 < 4; i8++) {
            int iF = i6 + ((iH << 5) | (iH >>> 27)) + f(iH2, i4, i5) + this.f7295X[i7] + Y1;
            int i9 = (iH2 >>> 2) | (iH2 << 30);
            int iF2 = i5 + ((iF << 5) | (iF >>> 27)) + f(iH, i9, i4) + this.f7295X[i7 + 1] + Y1;
            int i10 = (iH >>> 2) | (iH << 30);
            int iF3 = i4 + ((iF2 << 5) | (iF2 >>> 27)) + f(iF, i10, i9) + this.f7295X[i7 + 2] + Y1;
            i6 = (iF >>> 2) | (iF << 30);
            int i11 = i7 + 4;
            iH2 = i9 + ((iF3 << 5) | (iF3 >>> 27)) + f(iF2, i6, i10) + this.f7295X[i7 + 3] + Y1;
            i5 = (iF2 >>> 2) | (iF2 << 30);
            i7 += 5;
            iH = i10 + ((iH2 << 5) | (iH2 >>> 27)) + f(iF3, i5, i6) + this.f7295X[i11] + Y1;
            i4 = (iF3 >>> 2) | (iF3 << 30);
        }
        for (int i12 = 0; i12 < 4; i12++) {
            int iH3 = i6 + ((iH << 5) | (iH >>> 27)) + h(iH2, i4, i5) + this.f7295X[i7] + Y2;
            int i13 = (iH2 >>> 2) | (iH2 << 30);
            int iH4 = i5 + ((iH3 << 5) | (iH3 >>> 27)) + h(iH, i13, i4) + this.f7295X[i7 + 1] + Y2;
            int i14 = (iH >>> 2) | (iH << 30);
            int iH5 = i4 + ((iH4 << 5) | (iH4 >>> 27)) + h(iH3, i14, i13) + this.f7295X[i7 + 2] + Y2;
            i6 = (iH3 >>> 2) | (iH3 << 30);
            int i15 = i7 + 4;
            iH2 = i13 + ((iH5 << 5) | (iH5 >>> 27)) + h(iH4, i6, i14) + this.f7295X[i7 + 3] + Y2;
            i5 = (iH4 >>> 2) | (iH4 << 30);
            i7 += 5;
            iH = i14 + ((iH2 << 5) | (iH2 >>> 27)) + h(iH5, i5, i6) + this.f7295X[i15] + Y2;
            i4 = (iH5 >>> 2) | (iH5 << 30);
        }
        for (int i16 = 0; i16 < 4; i16++) {
            int iG = i6 + ((iH << 5) | (iH >>> 27)) + g(iH2, i4, i5) + this.f7295X[i7] + Y3;
            int i17 = (iH2 >>> 2) | (iH2 << 30);
            int iG2 = i5 + ((iG << 5) | (iG >>> 27)) + g(iH, i17, i4) + this.f7295X[i7 + 1] + Y3;
            int i18 = (iH >>> 2) | (iH << 30);
            int iG3 = i4 + ((iG2 << 5) | (iG2 >>> 27)) + g(iG, i18, i17) + this.f7295X[i7 + 2] + Y3;
            i6 = (iG >>> 2) | (iG << 30);
            int i19 = i7 + 4;
            iH2 = i17 + ((iG3 << 5) | (iG3 >>> 27)) + g(iG2, i6, i18) + this.f7295X[i7 + 3] + Y3;
            i5 = (iG2 >>> 2) | (iG2 << 30);
            i7 += 5;
            iH = i18 + ((iH2 << 5) | (iH2 >>> 27)) + g(iG3, i5, i6) + this.f7295X[i19] + Y3;
            i4 = (iG3 >>> 2) | (iG3 << 30);
        }
        for (int i20 = 0; i20 <= 3; i20++) {
            int iH6 = i6 + ((iH << 5) | (iH >>> 27)) + h(iH2, i4, i5) + this.f7295X[i7] + Y4;
            int i21 = (iH2 >>> 2) | (iH2 << 30);
            int iH7 = i5 + ((iH6 << 5) | (iH6 >>> 27)) + h(iH, i21, i4) + this.f7295X[i7 + 1] + Y4;
            int i22 = (iH >>> 2) | (iH << 30);
            int iH8 = i4 + ((iH7 << 5) | (iH7 >>> 27)) + h(iH6, i22, i21) + this.f7295X[i7 + 2] + Y4;
            i6 = (iH6 >>> 2) | (iH6 << 30);
            int i23 = i7 + 4;
            iH2 = i21 + ((iH8 << 5) | (iH8 >>> 27)) + h(iH7, i6, i22) + this.f7295X[i7 + 3] + Y4;
            i5 = (iH7 >>> 2) | (iH7 << 30);
            i7 += 5;
            iH = i22 + ((iH2 << 5) | (iH2 >>> 27)) + h(iH8, i5, i6) + this.f7295X[i23] + Y4;
            i4 = (iH8 >>> 2) | (iH8 << 30);
        }
        this.H1 += iH;
        this.H2 += iH2;
        this.H3 += i4;
        this.H4 += i5;
        this.H5 += i6;
        this.xOff = 0;
        for (int i24 = 0; i24 < 16; i24++) {
            this.f7295X[i24] = 0;
        }
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    public void processLength(long j2) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f7295X;
        iArr[14] = (int) (j2 >>> 32);
        iArr[15] = (int) j2;
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    public void processWord(byte[] bArr, int i2) {
        int i3 = (bArr[i2 + 3] & 255) | (bArr[i2] << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8);
        int[] iArr = this.f7295X;
        int i4 = this.xOff;
        iArr[i4] = i3;
        int i5 = i4 + 1;
        this.xOff = i5;
        if (i5 == 16) {
            processBlock();
        }
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest, org.spongycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.H1 = 1732584193;
        this.H2 = -271733879;
        this.H3 = -1732584194;
        this.H4 = 271733878;
        this.H5 = -1009589776;
        this.xOff = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.f7295X;
            if (i2 == iArr.length) {
                return;
            }
            iArr[i2] = 0;
            i2++;
        }
    }

    public SHA1Digest(SHA1Digest sHA1Digest) {
        super(sHA1Digest);
        this.f7295X = new int[80];
        copyIn(sHA1Digest);
    }

    public SHA1Digest(byte[] bArr) {
        super(bArr);
        this.f7295X = new int[80];
        this.H1 = Pack.bigEndianToInt(bArr, 16);
        this.H2 = Pack.bigEndianToInt(bArr, 20);
        this.H3 = Pack.bigEndianToInt(bArr, 24);
        this.H4 = Pack.bigEndianToInt(bArr, 28);
        this.H5 = Pack.bigEndianToInt(bArr, 32);
        this.xOff = Pack.bigEndianToInt(bArr, 36);
        for (int i2 = 0; i2 != this.xOff; i2++) {
            this.f7295X[i2] = Pack.bigEndianToInt(bArr, (i2 * 4) + 40);
        }
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        SHA1Digest sHA1Digest = (SHA1Digest) memoable;
        super.copyIn((GeneralDigest) sHA1Digest);
        copyIn(sHA1Digest);
    }
}
