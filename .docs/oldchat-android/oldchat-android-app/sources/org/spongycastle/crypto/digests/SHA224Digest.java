package org.spongycastle.crypto.digests;

import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class SHA224Digest extends GeneralDigest implements EncodableDigest {
    private static final int DIGEST_LENGTH = 28;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    static final int[] f7296K = {1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};
    private int H1;
    private int H2;
    private int H3;
    private int H4;
    private int H5;
    private int H6;
    private int H7;
    private int H8;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    private int[] f7297X;
    private int xOff;

    public SHA224Digest() {
        this.f7297X = new int[64];
        reset();
    }

    private int Ch(int i2, int i3, int i4) {
        return ((i2 ^ (-1)) & i4) ^ (i3 & i2);
    }

    private int Maj(int i2, int i3, int i4) {
        return ((i2 & i4) ^ (i2 & i3)) ^ (i3 & i4);
    }

    private int Sum0(int i2) {
        return ((i2 << 10) | (i2 >>> 22)) ^ (((i2 >>> 2) | (i2 << 30)) ^ ((i2 >>> 13) | (i2 << 19)));
    }

    private int Sum1(int i2) {
        return ((i2 << 7) | (i2 >>> 25)) ^ (((i2 >>> 6) | (i2 << 26)) ^ ((i2 >>> 11) | (i2 << 21)));
    }

    private int Theta0(int i2) {
        return (i2 >>> 3) ^ (((i2 >>> 7) | (i2 << 25)) ^ ((i2 >>> 18) | (i2 << 14)));
    }

    private int Theta1(int i2) {
        return (i2 >>> 10) ^ (((i2 >>> 17) | (i2 << 15)) ^ ((i2 >>> 19) | (i2 << 13)));
    }

    private void doCopy(SHA224Digest sHA224Digest) {
        super.copyIn(sHA224Digest);
        this.H1 = sHA224Digest.H1;
        this.H2 = sHA224Digest.H2;
        this.H3 = sHA224Digest.H3;
        this.H4 = sHA224Digest.H4;
        this.H5 = sHA224Digest.H5;
        this.H6 = sHA224Digest.H6;
        this.H7 = sHA224Digest.H7;
        this.H8 = sHA224Digest.H8;
        int[] iArr = sHA224Digest.f7297X;
        System.arraycopy(iArr, 0, this.f7297X, 0, iArr.length);
        this.xOff = sHA224Digest.xOff;
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new SHA224Digest(this);
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i2) {
        finish();
        Pack.intToBigEndian(this.H1, bArr, i2);
        Pack.intToBigEndian(this.H2, bArr, i2 + 4);
        Pack.intToBigEndian(this.H3, bArr, i2 + 8);
        Pack.intToBigEndian(this.H4, bArr, i2 + 12);
        Pack.intToBigEndian(this.H5, bArr, i2 + 16);
        Pack.intToBigEndian(this.H6, bArr, i2 + 20);
        Pack.intToBigEndian(this.H7, bArr, i2 + 24);
        reset();
        return 28;
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return McElieceCCA2KeyGenParameterSpec.SHA224;
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return 28;
    }

    @Override // org.spongycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] bArr = new byte[(this.xOff * 4) + 52];
        super.populateState(bArr);
        Pack.intToBigEndian(this.H1, bArr, 16);
        Pack.intToBigEndian(this.H2, bArr, 20);
        Pack.intToBigEndian(this.H3, bArr, 24);
        Pack.intToBigEndian(this.H4, bArr, 28);
        Pack.intToBigEndian(this.H5, bArr, 32);
        Pack.intToBigEndian(this.H6, bArr, 36);
        Pack.intToBigEndian(this.H7, bArr, 40);
        Pack.intToBigEndian(this.H8, bArr, 44);
        Pack.intToBigEndian(this.xOff, bArr, 48);
        for (int i2 = 0; i2 != this.xOff; i2++) {
            Pack.intToBigEndian(this.f7297X[i2], bArr, (i2 * 4) + 52);
        }
        return bArr;
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    public void processBlock() {
        for (int i2 = 16; i2 <= 63; i2++) {
            int[] iArr = this.f7297X;
            int iTheta1 = Theta1(iArr[i2 - 2]);
            int[] iArr2 = this.f7297X;
            iArr[i2] = iTheta1 + iArr2[i2 - 7] + Theta0(iArr2[i2 - 15]) + this.f7297X[i2 - 16];
        }
        int iSum0 = this.H1;
        int iSum02 = this.H2;
        int iSum03 = this.H3;
        int iSum04 = this.H4;
        int i3 = this.H5;
        int i4 = this.H6;
        int i5 = this.H7;
        int i6 = this.H8;
        int i7 = 0;
        for (int i8 = 0; i8 < 8; i8++) {
            int iSum1 = Sum1(i3) + Ch(i3, i4, i5);
            int[] iArr3 = f7296K;
            int i9 = i6 + iSum1 + iArr3[i7] + this.f7297X[i7];
            int i10 = iSum04 + i9;
            int iSum05 = i9 + Sum0(iSum0) + Maj(iSum0, iSum02, iSum03);
            int i11 = i7 + 1;
            int iSum12 = i5 + Sum1(i10) + Ch(i10, i3, i4) + iArr3[i11] + this.f7297X[i11];
            int i12 = iSum03 + iSum12;
            int iSum06 = iSum12 + Sum0(iSum05) + Maj(iSum05, iSum0, iSum02);
            int i13 = i7 + 2;
            int iSum13 = i4 + Sum1(i12) + Ch(i12, i10, i3) + iArr3[i13] + this.f7297X[i13];
            int i14 = iSum02 + iSum13;
            int iSum07 = iSum13 + Sum0(iSum06) + Maj(iSum06, iSum05, iSum0);
            int i15 = i7 + 3;
            int iSum14 = i3 + Sum1(i14) + Ch(i14, i12, i10) + iArr3[i15] + this.f7297X[i15];
            int i16 = iSum0 + iSum14;
            int iSum08 = iSum14 + Sum0(iSum07) + Maj(iSum07, iSum06, iSum05);
            int i17 = i7 + 4;
            int iSum15 = i10 + Sum1(i16) + Ch(i16, i14, i12) + iArr3[i17] + this.f7297X[i17];
            i6 = iSum05 + iSum15;
            iSum04 = iSum15 + Sum0(iSum08) + Maj(iSum08, iSum07, iSum06);
            int i18 = i7 + 5;
            int iSum16 = i12 + Sum1(i6) + Ch(i6, i16, i14) + iArr3[i18] + this.f7297X[i18];
            i5 = iSum06 + iSum16;
            iSum03 = iSum16 + Sum0(iSum04) + Maj(iSum04, iSum08, iSum07);
            int i19 = i7 + 6;
            int iSum17 = i14 + Sum1(i5) + Ch(i5, i6, i16) + iArr3[i19] + this.f7297X[i19];
            i4 = iSum07 + iSum17;
            iSum02 = iSum17 + Sum0(iSum03) + Maj(iSum03, iSum04, iSum08);
            int i20 = i7 + 7;
            int iSum18 = i16 + Sum1(i4) + Ch(i4, i5, i6) + iArr3[i20] + this.f7297X[i20];
            i3 = iSum08 + iSum18;
            iSum0 = iSum18 + Sum0(iSum02) + Maj(iSum02, iSum03, iSum04);
            i7 += 8;
        }
        this.H1 += iSum0;
        this.H2 += iSum02;
        this.H3 += iSum03;
        this.H4 += iSum04;
        this.H5 += i3;
        this.H6 += i4;
        this.H7 += i5;
        this.H8 += i6;
        this.xOff = 0;
        for (int i21 = 0; i21 < 16; i21++) {
            this.f7297X[i21] = 0;
        }
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    public void processLength(long j2) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f7297X;
        iArr[14] = (int) (j2 >>> 32);
        iArr[15] = (int) j2;
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    public void processWord(byte[] bArr, int i2) {
        int i3 = (bArr[i2 + 3] & 255) | (bArr[i2] << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8);
        int[] iArr = this.f7297X;
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
        this.H1 = -1056596264;
        this.H2 = 914150663;
        this.H3 = 812702999;
        this.H4 = -150054599;
        this.H5 = -4191439;
        this.H6 = 1750603025;
        this.H7 = 1694076839;
        this.H8 = -1090891868;
        this.xOff = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.f7297X;
            if (i2 == iArr.length) {
                return;
            }
            iArr[i2] = 0;
            i2++;
        }
    }

    public SHA224Digest(SHA224Digest sHA224Digest) {
        super(sHA224Digest);
        this.f7297X = new int[64];
        doCopy(sHA224Digest);
    }

    public SHA224Digest(byte[] bArr) {
        super(bArr);
        this.f7297X = new int[64];
        this.H1 = Pack.bigEndianToInt(bArr, 16);
        this.H2 = Pack.bigEndianToInt(bArr, 20);
        this.H3 = Pack.bigEndianToInt(bArr, 24);
        this.H4 = Pack.bigEndianToInt(bArr, 28);
        this.H5 = Pack.bigEndianToInt(bArr, 32);
        this.H6 = Pack.bigEndianToInt(bArr, 36);
        this.H7 = Pack.bigEndianToInt(bArr, 40);
        this.H8 = Pack.bigEndianToInt(bArr, 44);
        this.xOff = Pack.bigEndianToInt(bArr, 48);
        for (int i2 = 0; i2 != this.xOff; i2++) {
            this.f7297X[i2] = Pack.bigEndianToInt(bArr, (i2 * 4) + 52);
        }
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        doCopy((SHA224Digest) memoable);
    }
}
