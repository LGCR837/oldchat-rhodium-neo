package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class MD5Digest extends GeneralDigest implements EncodableDigest {
    private static final int DIGEST_LENGTH = 16;
    private static final int S11 = 7;
    private static final int S12 = 12;
    private static final int S13 = 17;
    private static final int S14 = 22;
    private static final int S21 = 5;
    private static final int S22 = 9;
    private static final int S23 = 14;
    private static final int S24 = 20;
    private static final int S31 = 4;
    private static final int S32 = 11;
    private static final int S33 = 16;
    private static final int S34 = 23;
    private static final int S41 = 6;
    private static final int S42 = 10;
    private static final int S43 = 15;
    private static final int S44 = 21;
    private int H1;
    private int H2;
    private int H3;
    private int H4;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    private int[] f7290X;
    private int xOff;

    public MD5Digest() {
        this.f7290X = new int[16];
        reset();
    }

    private int F(int i2, int i3, int i4) {
        return ((i2 ^ (-1)) & i4) | (i3 & i2);
    }

    private int G(int i2, int i3, int i4) {
        return (i2 & i4) | (i3 & (i4 ^ (-1)));
    }

    private int H(int i2, int i3, int i4) {
        return (i2 ^ i3) ^ i4;
    }

    private int K(int i2, int i3, int i4) {
        return (i2 | (i4 ^ (-1))) ^ i3;
    }

    private void copyIn(MD5Digest mD5Digest) {
        super.copyIn((GeneralDigest) mD5Digest);
        this.H1 = mD5Digest.H1;
        this.H2 = mD5Digest.H2;
        this.H3 = mD5Digest.H3;
        this.H4 = mD5Digest.H4;
        int[] iArr = mD5Digest.f7290X;
        System.arraycopy(iArr, 0, this.f7290X, 0, iArr.length);
        this.xOff = mD5Digest.xOff;
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
        return new MD5Digest(this);
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
        return "MD5";
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return 16;
    }

    @Override // org.spongycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] bArr = new byte[(this.xOff * 4) + 36];
        super.populateState(bArr);
        Pack.intToBigEndian(this.H1, bArr, 16);
        Pack.intToBigEndian(this.H2, bArr, 20);
        Pack.intToBigEndian(this.H3, bArr, 24);
        Pack.intToBigEndian(this.H4, bArr, 28);
        Pack.intToBigEndian(this.xOff, bArr, 32);
        for (int i2 = 0; i2 != this.xOff; i2++) {
            Pack.intToBigEndian(this.f7290X[i2], bArr, (i2 * 4) + 36);
        }
        return bArr;
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    public void processBlock() {
        int i2 = this.H1;
        int i3 = this.H2;
        int i4 = this.H3;
        int i5 = this.H4;
        int iRotateLeft = rotateLeft(((i2 + F(i3, i4, i5)) + this.f7290X[0]) - 680876936, 7) + i3;
        int iRotateLeft2 = rotateLeft(((i5 + F(iRotateLeft, i3, i4)) + this.f7290X[1]) - 389564586, 12) + iRotateLeft;
        int iRotateLeft3 = rotateLeft(i4 + F(iRotateLeft2, iRotateLeft, i3) + this.f7290X[2] + 606105819, 17) + iRotateLeft2;
        int iRotateLeft4 = rotateLeft(((i3 + F(iRotateLeft3, iRotateLeft2, iRotateLeft)) + this.f7290X[3]) - 1044525330, 22) + iRotateLeft3;
        int iRotateLeft5 = rotateLeft(((iRotateLeft + F(iRotateLeft4, iRotateLeft3, iRotateLeft2)) + this.f7290X[4]) - 176418897, 7) + iRotateLeft4;
        int iRotateLeft6 = rotateLeft(iRotateLeft2 + F(iRotateLeft5, iRotateLeft4, iRotateLeft3) + this.f7290X[5] + 1200080426, 12) + iRotateLeft5;
        int iRotateLeft7 = rotateLeft(((iRotateLeft3 + F(iRotateLeft6, iRotateLeft5, iRotateLeft4)) + this.f7290X[6]) - 1473231341, 17) + iRotateLeft6;
        int iRotateLeft8 = rotateLeft(((iRotateLeft4 + F(iRotateLeft7, iRotateLeft6, iRotateLeft5)) + this.f7290X[7]) - 45705983, 22) + iRotateLeft7;
        int iRotateLeft9 = rotateLeft(iRotateLeft5 + F(iRotateLeft8, iRotateLeft7, iRotateLeft6) + this.f7290X[8] + 1770035416, 7) + iRotateLeft8;
        int iRotateLeft10 = rotateLeft(((iRotateLeft6 + F(iRotateLeft9, iRotateLeft8, iRotateLeft7)) + this.f7290X[9]) - 1958414417, 12) + iRotateLeft9;
        int iRotateLeft11 = rotateLeft(((iRotateLeft7 + F(iRotateLeft10, iRotateLeft9, iRotateLeft8)) + this.f7290X[10]) - 42063, 17) + iRotateLeft10;
        int iRotateLeft12 = rotateLeft(((iRotateLeft8 + F(iRotateLeft11, iRotateLeft10, iRotateLeft9)) + this.f7290X[11]) - 1990404162, 22) + iRotateLeft11;
        int iRotateLeft13 = rotateLeft(iRotateLeft9 + F(iRotateLeft12, iRotateLeft11, iRotateLeft10) + this.f7290X[12] + 1804603682, 7) + iRotateLeft12;
        int iRotateLeft14 = rotateLeft(((iRotateLeft10 + F(iRotateLeft13, iRotateLeft12, iRotateLeft11)) + this.f7290X[13]) - 40341101, 12) + iRotateLeft13;
        int iRotateLeft15 = rotateLeft(((iRotateLeft11 + F(iRotateLeft14, iRotateLeft13, iRotateLeft12)) + this.f7290X[14]) - 1502002290, 17) + iRotateLeft14;
        int iRotateLeft16 = rotateLeft(iRotateLeft12 + F(iRotateLeft15, iRotateLeft14, iRotateLeft13) + this.f7290X[15] + 1236535329, 22) + iRotateLeft15;
        int iRotateLeft17 = rotateLeft(((iRotateLeft13 + G(iRotateLeft16, iRotateLeft15, iRotateLeft14)) + this.f7290X[1]) - 165796510, 5) + iRotateLeft16;
        int iRotateLeft18 = rotateLeft(((iRotateLeft14 + G(iRotateLeft17, iRotateLeft16, iRotateLeft15)) + this.f7290X[6]) - 1069501632, 9) + iRotateLeft17;
        int iRotateLeft19 = rotateLeft(iRotateLeft15 + G(iRotateLeft18, iRotateLeft17, iRotateLeft16) + this.f7290X[11] + 643717713, 14) + iRotateLeft18;
        int iRotateLeft20 = rotateLeft(((iRotateLeft16 + G(iRotateLeft19, iRotateLeft18, iRotateLeft17)) + this.f7290X[0]) - 373897302, 20) + iRotateLeft19;
        int iRotateLeft21 = rotateLeft(((iRotateLeft17 + G(iRotateLeft20, iRotateLeft19, iRotateLeft18)) + this.f7290X[5]) - 701558691, 5) + iRotateLeft20;
        int iRotateLeft22 = rotateLeft(iRotateLeft18 + G(iRotateLeft21, iRotateLeft20, iRotateLeft19) + this.f7290X[10] + 38016083, 9) + iRotateLeft21;
        int iRotateLeft23 = rotateLeft(((iRotateLeft19 + G(iRotateLeft22, iRotateLeft21, iRotateLeft20)) + this.f7290X[15]) - 660478335, 14) + iRotateLeft22;
        int iRotateLeft24 = rotateLeft(((iRotateLeft20 + G(iRotateLeft23, iRotateLeft22, iRotateLeft21)) + this.f7290X[4]) - 405537848, 20) + iRotateLeft23;
        int iRotateLeft25 = rotateLeft(iRotateLeft21 + G(iRotateLeft24, iRotateLeft23, iRotateLeft22) + this.f7290X[9] + 568446438, 5) + iRotateLeft24;
        int iRotateLeft26 = rotateLeft(((iRotateLeft22 + G(iRotateLeft25, iRotateLeft24, iRotateLeft23)) + this.f7290X[14]) - 1019803690, 9) + iRotateLeft25;
        int iRotateLeft27 = rotateLeft(((iRotateLeft23 + G(iRotateLeft26, iRotateLeft25, iRotateLeft24)) + this.f7290X[3]) - 187363961, 14) + iRotateLeft26;
        int iRotateLeft28 = rotateLeft(iRotateLeft24 + G(iRotateLeft27, iRotateLeft26, iRotateLeft25) + this.f7290X[8] + 1163531501, 20) + iRotateLeft27;
        int iRotateLeft29 = rotateLeft(((iRotateLeft25 + G(iRotateLeft28, iRotateLeft27, iRotateLeft26)) + this.f7290X[13]) - 1444681467, 5) + iRotateLeft28;
        int iRotateLeft30 = rotateLeft(((iRotateLeft26 + G(iRotateLeft29, iRotateLeft28, iRotateLeft27)) + this.f7290X[2]) - 51403784, 9) + iRotateLeft29;
        int iRotateLeft31 = rotateLeft(iRotateLeft27 + G(iRotateLeft30, iRotateLeft29, iRotateLeft28) + this.f7290X[7] + 1735328473, 14) + iRotateLeft30;
        int iRotateLeft32 = rotateLeft(((iRotateLeft28 + G(iRotateLeft31, iRotateLeft30, iRotateLeft29)) + this.f7290X[12]) - 1926607734, 20) + iRotateLeft31;
        int iRotateLeft33 = rotateLeft(((iRotateLeft29 + H(iRotateLeft32, iRotateLeft31, iRotateLeft30)) + this.f7290X[5]) - 378558, 4) + iRotateLeft32;
        int iRotateLeft34 = rotateLeft(((iRotateLeft30 + H(iRotateLeft33, iRotateLeft32, iRotateLeft31)) + this.f7290X[8]) - 2022574463, 11) + iRotateLeft33;
        int iRotateLeft35 = rotateLeft(iRotateLeft31 + H(iRotateLeft34, iRotateLeft33, iRotateLeft32) + this.f7290X[11] + 1839030562, 16) + iRotateLeft34;
        int iRotateLeft36 = rotateLeft(((iRotateLeft32 + H(iRotateLeft35, iRotateLeft34, iRotateLeft33)) + this.f7290X[14]) - 35309556, 23) + iRotateLeft35;
        int iRotateLeft37 = rotateLeft(((iRotateLeft33 + H(iRotateLeft36, iRotateLeft35, iRotateLeft34)) + this.f7290X[1]) - 1530992060, 4) + iRotateLeft36;
        int iRotateLeft38 = rotateLeft(iRotateLeft34 + H(iRotateLeft37, iRotateLeft36, iRotateLeft35) + this.f7290X[4] + 1272893353, 11) + iRotateLeft37;
        int iRotateLeft39 = rotateLeft(((iRotateLeft35 + H(iRotateLeft38, iRotateLeft37, iRotateLeft36)) + this.f7290X[7]) - 155497632, 16) + iRotateLeft38;
        int iRotateLeft40 = rotateLeft(((iRotateLeft36 + H(iRotateLeft39, iRotateLeft38, iRotateLeft37)) + this.f7290X[10]) - 1094730640, 23) + iRotateLeft39;
        int iRotateLeft41 = rotateLeft(iRotateLeft37 + H(iRotateLeft40, iRotateLeft39, iRotateLeft38) + this.f7290X[13] + 681279174, 4) + iRotateLeft40;
        int iRotateLeft42 = rotateLeft(((iRotateLeft38 + H(iRotateLeft41, iRotateLeft40, iRotateLeft39)) + this.f7290X[0]) - 358537222, 11) + iRotateLeft41;
        int iRotateLeft43 = rotateLeft(((iRotateLeft39 + H(iRotateLeft42, iRotateLeft41, iRotateLeft40)) + this.f7290X[3]) - 722521979, 16) + iRotateLeft42;
        int iRotateLeft44 = rotateLeft(iRotateLeft40 + H(iRotateLeft43, iRotateLeft42, iRotateLeft41) + this.f7290X[6] + 76029189, 23) + iRotateLeft43;
        int iRotateLeft45 = rotateLeft(((iRotateLeft41 + H(iRotateLeft44, iRotateLeft43, iRotateLeft42)) + this.f7290X[9]) - 640364487, 4) + iRotateLeft44;
        int iRotateLeft46 = rotateLeft(((iRotateLeft42 + H(iRotateLeft45, iRotateLeft44, iRotateLeft43)) + this.f7290X[12]) - 421815835, 11) + iRotateLeft45;
        int iRotateLeft47 = rotateLeft(iRotateLeft43 + H(iRotateLeft46, iRotateLeft45, iRotateLeft44) + this.f7290X[15] + 530742520, 16) + iRotateLeft46;
        int iRotateLeft48 = rotateLeft(((iRotateLeft44 + H(iRotateLeft47, iRotateLeft46, iRotateLeft45)) + this.f7290X[2]) - 995338651, 23) + iRotateLeft47;
        int iRotateLeft49 = rotateLeft(((iRotateLeft45 + K(iRotateLeft48, iRotateLeft47, iRotateLeft46)) + this.f7290X[0]) - 198630844, 6) + iRotateLeft48;
        int iRotateLeft50 = rotateLeft(iRotateLeft46 + K(iRotateLeft49, iRotateLeft48, iRotateLeft47) + this.f7290X[7] + 1126891415, 10) + iRotateLeft49;
        int iRotateLeft51 = rotateLeft(((iRotateLeft47 + K(iRotateLeft50, iRotateLeft49, iRotateLeft48)) + this.f7290X[14]) - 1416354905, 15) + iRotateLeft50;
        int iRotateLeft52 = rotateLeft(((iRotateLeft48 + K(iRotateLeft51, iRotateLeft50, iRotateLeft49)) + this.f7290X[5]) - 57434055, 21) + iRotateLeft51;
        int iRotateLeft53 = rotateLeft(iRotateLeft49 + K(iRotateLeft52, iRotateLeft51, iRotateLeft50) + this.f7290X[12] + 1700485571, 6) + iRotateLeft52;
        int iRotateLeft54 = rotateLeft(((iRotateLeft50 + K(iRotateLeft53, iRotateLeft52, iRotateLeft51)) + this.f7290X[3]) - 1894986606, 10) + iRotateLeft53;
        int iRotateLeft55 = rotateLeft(((iRotateLeft51 + K(iRotateLeft54, iRotateLeft53, iRotateLeft52)) + this.f7290X[10]) - 1051523, 15) + iRotateLeft54;
        int iRotateLeft56 = rotateLeft(((iRotateLeft52 + K(iRotateLeft55, iRotateLeft54, iRotateLeft53)) + this.f7290X[1]) - 2054922799, 21) + iRotateLeft55;
        int iRotateLeft57 = rotateLeft(iRotateLeft53 + K(iRotateLeft56, iRotateLeft55, iRotateLeft54) + this.f7290X[8] + 1873313359, 6) + iRotateLeft56;
        int iRotateLeft58 = rotateLeft(((iRotateLeft54 + K(iRotateLeft57, iRotateLeft56, iRotateLeft55)) + this.f7290X[15]) - 30611744, 10) + iRotateLeft57;
        int iRotateLeft59 = rotateLeft(((iRotateLeft55 + K(iRotateLeft58, iRotateLeft57, iRotateLeft56)) + this.f7290X[6]) - 1560198380, 15) + iRotateLeft58;
        int iRotateLeft60 = rotateLeft(iRotateLeft56 + K(iRotateLeft59, iRotateLeft58, iRotateLeft57) + this.f7290X[13] + 1309151649, 21) + iRotateLeft59;
        int iRotateLeft61 = rotateLeft(((iRotateLeft57 + K(iRotateLeft60, iRotateLeft59, iRotateLeft58)) + this.f7290X[4]) - 145523070, 6) + iRotateLeft60;
        int iRotateLeft62 = rotateLeft(((iRotateLeft58 + K(iRotateLeft61, iRotateLeft60, iRotateLeft59)) + this.f7290X[11]) - 1120210379, 10) + iRotateLeft61;
        int iRotateLeft63 = rotateLeft(iRotateLeft59 + K(iRotateLeft62, iRotateLeft61, iRotateLeft60) + this.f7290X[2] + 718787259, 15) + iRotateLeft62;
        int iRotateLeft64 = rotateLeft(((iRotateLeft60 + K(iRotateLeft63, iRotateLeft62, iRotateLeft61)) + this.f7290X[9]) - 343485551, 21) + iRotateLeft63;
        this.H1 += iRotateLeft61;
        this.H2 += iRotateLeft64;
        this.H3 += iRotateLeft63;
        this.H4 += iRotateLeft62;
        this.xOff = 0;
        int i6 = 0;
        while (true) {
            int[] iArr = this.f7290X;
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
        int[] iArr = this.f7290X;
        iArr[14] = (int) j2;
        iArr[15] = (int) (j2 >>> 32);
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    public void processWord(byte[] bArr, int i2) {
        int[] iArr = this.f7290X;
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
            int[] iArr = this.f7290X;
            if (i2 == iArr.length) {
                return;
            }
            iArr[i2] = 0;
            i2++;
        }
    }

    public MD5Digest(byte[] bArr) {
        super(bArr);
        this.f7290X = new int[16];
        this.H1 = Pack.bigEndianToInt(bArr, 16);
        this.H2 = Pack.bigEndianToInt(bArr, 20);
        this.H3 = Pack.bigEndianToInt(bArr, 24);
        this.H4 = Pack.bigEndianToInt(bArr, 28);
        this.xOff = Pack.bigEndianToInt(bArr, 32);
        for (int i2 = 0; i2 != this.xOff; i2++) {
            this.f7290X[i2] = Pack.bigEndianToInt(bArr, (i2 * 4) + 36);
        }
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((MD5Digest) memoable);
    }

    public MD5Digest(MD5Digest mD5Digest) {
        super(mD5Digest);
        this.f7290X = new int[16];
        copyIn(mD5Digest);
    }
}
