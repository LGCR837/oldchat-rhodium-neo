package org.spongycastle.pqc.crypto.gmss.util;

import org.spongycastle.crypto.Digest;

/* JADX INFO: loaded from: classes.dex */
public class WinternitzOTSVerify {
    private Digest messDigestOTS;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private int f7612w;

    public WinternitzOTSVerify(Digest digest, int i2) {
        this.f7612w = i2;
        this.messDigestOTS = digest;
    }

    public byte[] Verify(byte[] bArr, byte[] bArr2) {
        int i2;
        byte[] bArr3 = bArr2;
        int digestSize = this.messDigestOTS.getDigestSize();
        byte[] bArr4 = new byte[digestSize];
        int i3 = 0;
        this.messDigestOTS.update(bArr, 0, bArr.length);
        int digestSize2 = this.messDigestOTS.getDigestSize();
        byte[] bArr5 = new byte[digestSize2];
        this.messDigestOTS.doFinal(bArr5, 0);
        int i4 = digestSize << 3;
        int i5 = this.f7612w;
        int i6 = ((i5 - 1) + i4) / i5;
        int log = getLog((i6 << i5) + 1);
        int i7 = this.f7612w;
        int i8 = ((((log + i7) - 1) / i7) + i6) * digestSize;
        if (i8 != bArr3.length) {
            return null;
        }
        byte[] bArr6 = new byte[i8];
        int i9 = 8;
        if (8 % i7 == 0) {
            int i10 = 8 / i7;
            int i11 = (1 << i7) - 1;
            byte[] bArr7 = new byte[digestSize];
            int i12 = 0;
            int i13 = 0;
            int i14 = 0;
            while (i12 < digestSize2) {
                while (i3 < i10) {
                    int i15 = bArr5[i12] & i11;
                    i13 += i15;
                    int i16 = digestSize2;
                    int i17 = i14 * digestSize;
                    int i18 = i10;
                    System.arraycopy(bArr3, i17, bArr7, 0, digestSize);
                    int i19 = i15;
                    while (i19 < i11) {
                        this.messDigestOTS.update(bArr7, 0, bArr7.length);
                        bArr7 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr7, 0);
                        i19++;
                        i13 = i13;
                        i8 = i8;
                    }
                    System.arraycopy(bArr7, 0, bArr6, i17, digestSize);
                    bArr5[i12] = (byte) (bArr5[i12] >>> this.f7612w);
                    i14++;
                    i3++;
                    digestSize2 = i16;
                    bArr3 = bArr2;
                    i10 = i18;
                }
                i12++;
                bArr3 = bArr2;
                i3 = 0;
            }
            i2 = i8;
            int i20 = (i6 << this.f7612w) - i13;
            int i21 = 0;
            while (i21 < log) {
                int i22 = i14 * digestSize;
                System.arraycopy(bArr2, i22, bArr7, 0, digestSize);
                for (int i23 = i20 & i11; i23 < i11; i23++) {
                    this.messDigestOTS.update(bArr7, 0, bArr7.length);
                    bArr7 = new byte[this.messDigestOTS.getDigestSize()];
                    this.messDigestOTS.doFinal(bArr7, 0);
                }
                System.arraycopy(bArr7, 0, bArr6, i22, digestSize);
                int i24 = this.f7612w;
                i20 >>>= i24;
                i14++;
                i21 += i24;
            }
        } else {
            i2 = i8;
            if (i7 < 8) {
                int i25 = digestSize / i7;
                int i26 = (1 << i7) - 1;
                byte[] bArr8 = new byte[digestSize];
                int i27 = 0;
                int i28 = 0;
                int i29 = 0;
                int i30 = 0;
                while (i27 < i25) {
                    int i31 = 0;
                    long j2 = 0;
                    while (i31 < this.f7612w) {
                        j2 ^= (long) ((bArr5[i28] & 255) << (i31 << 3));
                        i28++;
                        i31++;
                        bArr8 = bArr8;
                    }
                    int i32 = 0;
                    while (i32 < i9) {
                        int i33 = i27;
                        int i34 = (int) (j2 & ((long) i26));
                        i29 += i34;
                        int i35 = i30 * digestSize;
                        System.arraycopy(bArr3, i35, bArr8, 0, digestSize);
                        while (i34 < i26) {
                            this.messDigestOTS.update(bArr8, 0, bArr8.length);
                            bArr8 = new byte[this.messDigestOTS.getDigestSize()];
                            this.messDigestOTS.doFinal(bArr8, 0);
                            i34++;
                            i25 = i25;
                            i28 = i28;
                        }
                        System.arraycopy(bArr8, 0, bArr6, i35, digestSize);
                        j2 >>>= this.f7612w;
                        i30++;
                        i32++;
                        i27 = i33;
                        i9 = 8;
                    }
                    i27++;
                    i9 = 8;
                }
                byte[] bArr9 = bArr8;
                int i36 = digestSize % this.f7612w;
                long j3 = 0;
                for (int i37 = 0; i37 < i36; i37++) {
                    j3 ^= (long) ((bArr5[i28] & 255) << (i37 << 3));
                    i28++;
                }
                int i38 = i36 << 3;
                byte[] bArr10 = bArr9;
                int i39 = 0;
                while (i39 < i38) {
                    int i40 = (int) (j3 & ((long) i26));
                    i29 += i40;
                    int i41 = i30 * digestSize;
                    System.arraycopy(bArr3, i41, bArr10, 0, digestSize);
                    while (i40 < i26) {
                        this.messDigestOTS.update(bArr10, 0, bArr10.length);
                        bArr10 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr10, 0);
                        i40++;
                    }
                    System.arraycopy(bArr10, 0, bArr6, i41, digestSize);
                    int i42 = this.f7612w;
                    j3 >>>= i42;
                    i30++;
                    i39 += i42;
                }
                int i43 = (i6 << this.f7612w) - i29;
                int i44 = 0;
                while (i44 < log) {
                    int i45 = i30 * digestSize;
                    System.arraycopy(bArr3, i45, bArr10, 0, digestSize);
                    for (int i46 = i43 & i26; i46 < i26; i46++) {
                        this.messDigestOTS.update(bArr10, 0, bArr10.length);
                        bArr10 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr10, 0);
                    }
                    System.arraycopy(bArr10, 0, bArr6, i45, digestSize);
                    int i47 = this.f7612w;
                    i43 >>>= i47;
                    i30++;
                    i44 += i47;
                }
            } else if (i7 < 57) {
                int i48 = i4 - i7;
                int i49 = (1 << i7) - 1;
                byte[] bArr11 = new byte[digestSize];
                int i50 = 0;
                int i51 = 0;
                int i52 = 0;
                while (i51 <= i48) {
                    int i53 = i51 >>> 3;
                    int i54 = i51 % 8;
                    int i55 = i51 + this.f7612w;
                    int i56 = (i55 + 7) >>> 3;
                    int i57 = 0;
                    long j4 = 0;
                    while (i53 < i56) {
                        j4 ^= (long) ((bArr5[i53] & 255) << (i57 << 3));
                        i57++;
                        i53++;
                        i48 = i48;
                        log = log;
                        i6 = i6;
                    }
                    int i58 = i48;
                    int i59 = log;
                    int i60 = i6;
                    long j5 = i49;
                    long j6 = (j4 >>> i54) & j5;
                    int i61 = i55;
                    i52 = (int) (((long) i52) + j6);
                    int i62 = i50 * digestSize;
                    System.arraycopy(bArr3, i62, bArr11, 0, digestSize);
                    while (j6 < j5) {
                        this.messDigestOTS.update(bArr11, 0, bArr11.length);
                        bArr11 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr11, 0);
                        j6++;
                        i61 = i61;
                        i52 = i52;
                    }
                    System.arraycopy(bArr11, 0, bArr6, i62, digestSize);
                    i50++;
                    i51 = i61;
                    i48 = i58;
                    log = i59;
                    i6 = i60;
                }
                int i63 = log;
                int i64 = i6;
                int i65 = i51 >>> 3;
                if (i65 < digestSize) {
                    int i66 = i51 % 8;
                    int i67 = 0;
                    long j7 = 0;
                    while (i65 < digestSize) {
                        j7 ^= (long) ((bArr5[i65] & 255) << (i67 << 3));
                        i67++;
                        i65++;
                    }
                    long j8 = i49;
                    long j9 = (j7 >>> i66) & j8;
                    i52 = (int) (((long) i52) + j9);
                    int i68 = i50 * digestSize;
                    System.arraycopy(bArr3, i68, bArr11, 0, digestSize);
                    while (j9 < j8) {
                        this.messDigestOTS.update(bArr11, 0, bArr11.length);
                        bArr11 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr11, 0);
                        j9++;
                    }
                    System.arraycopy(bArr11, 0, bArr6, i68, digestSize);
                    i50++;
                }
                int i69 = (i64 << this.f7612w) - i52;
                int i70 = 0;
                while (i70 < i63) {
                    int i71 = i50 * digestSize;
                    System.arraycopy(bArr3, i71, bArr11, 0, digestSize);
                    for (long j10 = i69 & i49; j10 < i49; j10++) {
                        this.messDigestOTS.update(bArr11, 0, bArr11.length);
                        bArr11 = new byte[this.messDigestOTS.getDigestSize()];
                        this.messDigestOTS.doFinal(bArr11, 0);
                    }
                    System.arraycopy(bArr11, 0, bArr6, i71, digestSize);
                    int i72 = this.f7612w;
                    i69 >>>= i72;
                    i50++;
                    i70 += i72;
                }
            }
        }
        byte[] bArr12 = new byte[digestSize];
        this.messDigestOTS.update(bArr6, 0, i2);
        byte[] bArr13 = new byte[this.messDigestOTS.getDigestSize()];
        this.messDigestOTS.doFinal(bArr13, 0);
        return bArr13;
    }

    public int getLog(int i2) {
        int i3 = 1;
        int i4 = 2;
        while (i4 < i2) {
            i4 <<= 1;
            i3++;
        }
        return i3;
    }

    public int getSignatureLength() {
        int digestSize = this.messDigestOTS.getDigestSize();
        int i2 = this.f7612w;
        int i3 = ((digestSize << 3) + (i2 - 1)) / i2;
        int log = getLog((i3 << i2) + 1);
        return digestSize * (i3 + (((log + r3) - 1) / this.f7612w));
    }
}
