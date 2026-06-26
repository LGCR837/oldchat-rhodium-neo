package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;

/* JADX INFO: loaded from: classes.dex */
public class MD4Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 16;
    private static final int S11 = 3;
    private static final int S12 = 7;
    private static final int S13 = 11;
    private static final int S14 = 19;
    private static final int S21 = 3;
    private static final int S22 = 5;
    private static final int S23 = 9;
    private static final int S24 = 13;
    private static final int S31 = 3;
    private static final int S32 = 9;
    private static final int S33 = 11;
    private static final int S34 = 15;
    private int H1;
    private int H2;
    private int H3;
    private int H4;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    private int[] f7289X;
    private int xOff;

    public MD4Digest() {
        this.f7289X = new int[16];
        reset();
    }

    private int F(int i2, int i3, int i4) {
        return ((i2 ^ (-1)) & i4) | (i3 & i2);
    }

    private int G(int i2, int i3, int i4) {
        return (i2 & (i3 | i4)) | (i3 & i4);
    }

    private int H(int i2, int i3, int i4) {
        return (i2 ^ i3) ^ i4;
    }

    private void copyIn(MD4Digest mD4Digest) {
        super.copyIn((GeneralDigest) mD4Digest);
        this.H1 = mD4Digest.H1;
        this.H2 = mD4Digest.H2;
        this.H3 = mD4Digest.H3;
        this.H4 = mD4Digest.H4;
        int[] iArr = mD4Digest.f7289X;
        System.arraycopy(iArr, 0, this.f7289X, 0, iArr.length);
        this.xOff = mD4Digest.xOff;
    }

    private int rotateLeft(int i2, int i3) {
        return (i2 >>> (32 - i3)) | (i2 << i3);
    }

    private void unpackWord(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) i2;
        bArr[i3 + 1] = (byte) (i2 >>> 8);
        bArr[i3 + 2] = (byte) (i2 >>> 16);
        bArr[i3 + 3] = (byte) (i2 >>> 24);
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new MD4Digest(this);
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i2) {
        finish();
        unpackWord(this.H1, bArr, i2);
        unpackWord(this.H2, bArr, i2 + 4);
        unpackWord(this.H3, bArr, i2 + 8);
        unpackWord(this.H4, bArr, i2 + 12);
        reset();
        return 16;
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "MD4";
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return 16;
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    public void processBlock() {
        int i2 = this.H1;
        int i3 = this.H2;
        int i4 = this.H3;
        int i5 = this.H4;
        int iRotateLeft = rotateLeft(i2 + F(i3, i4, i5) + this.f7289X[0], 3);
        int iRotateLeft2 = rotateLeft(i5 + F(iRotateLeft, i3, i4) + this.f7289X[1], 7);
        int iRotateLeft3 = rotateLeft(i4 + F(iRotateLeft2, iRotateLeft, i3) + this.f7289X[2], 11);
        int iRotateLeft4 = rotateLeft(i3 + F(iRotateLeft3, iRotateLeft2, iRotateLeft) + this.f7289X[3], 19);
        int iRotateLeft5 = rotateLeft(iRotateLeft + F(iRotateLeft4, iRotateLeft3, iRotateLeft2) + this.f7289X[4], 3);
        int iRotateLeft6 = rotateLeft(iRotateLeft2 + F(iRotateLeft5, iRotateLeft4, iRotateLeft3) + this.f7289X[5], 7);
        int iRotateLeft7 = rotateLeft(iRotateLeft3 + F(iRotateLeft6, iRotateLeft5, iRotateLeft4) + this.f7289X[6], 11);
        int iRotateLeft8 = rotateLeft(iRotateLeft4 + F(iRotateLeft7, iRotateLeft6, iRotateLeft5) + this.f7289X[7], 19);
        int iRotateLeft9 = rotateLeft(iRotateLeft5 + F(iRotateLeft8, iRotateLeft7, iRotateLeft6) + this.f7289X[8], 3);
        int iRotateLeft10 = rotateLeft(iRotateLeft6 + F(iRotateLeft9, iRotateLeft8, iRotateLeft7) + this.f7289X[9], 7);
        int iRotateLeft11 = rotateLeft(iRotateLeft7 + F(iRotateLeft10, iRotateLeft9, iRotateLeft8) + this.f7289X[10], 11);
        int iRotateLeft12 = rotateLeft(iRotateLeft8 + F(iRotateLeft11, iRotateLeft10, iRotateLeft9) + this.f7289X[11], 19);
        int iRotateLeft13 = rotateLeft(iRotateLeft9 + F(iRotateLeft12, iRotateLeft11, iRotateLeft10) + this.f7289X[12], 3);
        int iRotateLeft14 = rotateLeft(iRotateLeft10 + F(iRotateLeft13, iRotateLeft12, iRotateLeft11) + this.f7289X[13], 7);
        int iRotateLeft15 = rotateLeft(iRotateLeft11 + F(iRotateLeft14, iRotateLeft13, iRotateLeft12) + this.f7289X[14], 11);
        int iRotateLeft16 = rotateLeft(iRotateLeft12 + F(iRotateLeft15, iRotateLeft14, iRotateLeft13) + this.f7289X[15], 19);
        int iRotateLeft17 = rotateLeft(iRotateLeft13 + G(iRotateLeft16, iRotateLeft15, iRotateLeft14) + this.f7289X[0] + 1518500249, 3);
        int iRotateLeft18 = rotateLeft(iRotateLeft14 + G(iRotateLeft17, iRotateLeft16, iRotateLeft15) + this.f7289X[4] + 1518500249, 5);
        int iRotateLeft19 = rotateLeft(iRotateLeft15 + G(iRotateLeft18, iRotateLeft17, iRotateLeft16) + this.f7289X[8] + 1518500249, 9);
        int iRotateLeft20 = rotateLeft(iRotateLeft16 + G(iRotateLeft19, iRotateLeft18, iRotateLeft17) + this.f7289X[12] + 1518500249, 13);
        int iRotateLeft21 = rotateLeft(iRotateLeft17 + G(iRotateLeft20, iRotateLeft19, iRotateLeft18) + this.f7289X[1] + 1518500249, 3);
        int iRotateLeft22 = rotateLeft(iRotateLeft18 + G(iRotateLeft21, iRotateLeft20, iRotateLeft19) + this.f7289X[5] + 1518500249, 5);
        int iRotateLeft23 = rotateLeft(iRotateLeft19 + G(iRotateLeft22, iRotateLeft21, iRotateLeft20) + this.f7289X[9] + 1518500249, 9);
        int iRotateLeft24 = rotateLeft(iRotateLeft20 + G(iRotateLeft23, iRotateLeft22, iRotateLeft21) + this.f7289X[13] + 1518500249, 13);
        int iRotateLeft25 = rotateLeft(iRotateLeft21 + G(iRotateLeft24, iRotateLeft23, iRotateLeft22) + this.f7289X[2] + 1518500249, 3);
        int iRotateLeft26 = rotateLeft(iRotateLeft22 + G(iRotateLeft25, iRotateLeft24, iRotateLeft23) + this.f7289X[6] + 1518500249, 5);
        int iRotateLeft27 = rotateLeft(iRotateLeft23 + G(iRotateLeft26, iRotateLeft25, iRotateLeft24) + this.f7289X[10] + 1518500249, 9);
        int iRotateLeft28 = rotateLeft(iRotateLeft24 + G(iRotateLeft27, iRotateLeft26, iRotateLeft25) + this.f7289X[14] + 1518500249, 13);
        int iRotateLeft29 = rotateLeft(iRotateLeft25 + G(iRotateLeft28, iRotateLeft27, iRotateLeft26) + this.f7289X[3] + 1518500249, 3);
        int iRotateLeft30 = rotateLeft(iRotateLeft26 + G(iRotateLeft29, iRotateLeft28, iRotateLeft27) + this.f7289X[7] + 1518500249, 5);
        int iRotateLeft31 = rotateLeft(iRotateLeft27 + G(iRotateLeft30, iRotateLeft29, iRotateLeft28) + this.f7289X[11] + 1518500249, 9);
        int iRotateLeft32 = rotateLeft(iRotateLeft28 + G(iRotateLeft31, iRotateLeft30, iRotateLeft29) + this.f7289X[15] + 1518500249, 13);
        int iRotateLeft33 = rotateLeft(iRotateLeft29 + H(iRotateLeft32, iRotateLeft31, iRotateLeft30) + this.f7289X[0] + 1859775393, 3);
        int iRotateLeft34 = rotateLeft(iRotateLeft30 + H(iRotateLeft33, iRotateLeft32, iRotateLeft31) + this.f7289X[8] + 1859775393, 9);
        int iRotateLeft35 = rotateLeft(iRotateLeft31 + H(iRotateLeft34, iRotateLeft33, iRotateLeft32) + this.f7289X[4] + 1859775393, 11);
        int iRotateLeft36 = rotateLeft(iRotateLeft32 + H(iRotateLeft35, iRotateLeft34, iRotateLeft33) + this.f7289X[12] + 1859775393, 15);
        int iRotateLeft37 = rotateLeft(iRotateLeft33 + H(iRotateLeft36, iRotateLeft35, iRotateLeft34) + this.f7289X[2] + 1859775393, 3);
        int iRotateLeft38 = rotateLeft(iRotateLeft34 + H(iRotateLeft37, iRotateLeft36, iRotateLeft35) + this.f7289X[10] + 1859775393, 9);
        int iRotateLeft39 = rotateLeft(iRotateLeft35 + H(iRotateLeft38, iRotateLeft37, iRotateLeft36) + this.f7289X[6] + 1859775393, 11);
        int iRotateLeft40 = rotateLeft(iRotateLeft36 + H(iRotateLeft39, iRotateLeft38, iRotateLeft37) + this.f7289X[14] + 1859775393, 15);
        int iRotateLeft41 = rotateLeft(iRotateLeft37 + H(iRotateLeft40, iRotateLeft39, iRotateLeft38) + this.f7289X[1] + 1859775393, 3);
        int iRotateLeft42 = rotateLeft(iRotateLeft38 + H(iRotateLeft41, iRotateLeft40, iRotateLeft39) + this.f7289X[9] + 1859775393, 9);
        int iRotateLeft43 = rotateLeft(iRotateLeft39 + H(iRotateLeft42, iRotateLeft41, iRotateLeft40) + this.f7289X[5] + 1859775393, 11);
        int iRotateLeft44 = rotateLeft(iRotateLeft40 + H(iRotateLeft43, iRotateLeft42, iRotateLeft41) + this.f7289X[13] + 1859775393, 15);
        int iRotateLeft45 = rotateLeft(iRotateLeft41 + H(iRotateLeft44, iRotateLeft43, iRotateLeft42) + this.f7289X[3] + 1859775393, 3);
        int iRotateLeft46 = rotateLeft(iRotateLeft42 + H(iRotateLeft45, iRotateLeft44, iRotateLeft43) + this.f7289X[11] + 1859775393, 9);
        int iRotateLeft47 = rotateLeft(iRotateLeft43 + H(iRotateLeft46, iRotateLeft45, iRotateLeft44) + this.f7289X[7] + 1859775393, 11);
        int iRotateLeft48 = rotateLeft(iRotateLeft44 + H(iRotateLeft47, iRotateLeft46, iRotateLeft45) + this.f7289X[15] + 1859775393, 15);
        this.H1 += iRotateLeft45;
        this.H2 += iRotateLeft48;
        this.H3 += iRotateLeft47;
        this.H4 += iRotateLeft46;
        this.xOff = 0;
        int i6 = 0;
        while (true) {
            int[] iArr = this.f7289X;
            if (i6 == iArr.length) {
                return;
            }
            iArr[i6] = 0;
            i6++;
        }
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    public void processLength(long j2) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f7289X;
        iArr[14] = (int) j2;
        iArr[15] = (int) (j2 >>> 32);
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    public void processWord(byte[] bArr, int i2) {
        int[] iArr = this.f7289X;
        int i3 = this.xOff;
        int i4 = i3 + 1;
        this.xOff = i4;
        iArr[i3] = ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
        if (i4 == 16) {
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
        this.xOff = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.f7289X;
            if (i2 == iArr.length) {
                return;
            }
            iArr[i2] = 0;
            i2++;
        }
    }

    public MD4Digest(MD4Digest mD4Digest) {
        super(mD4Digest);
        this.f7289X = new int[16];
        copyIn(mD4Digest);
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((MD4Digest) memoable);
    }
}
