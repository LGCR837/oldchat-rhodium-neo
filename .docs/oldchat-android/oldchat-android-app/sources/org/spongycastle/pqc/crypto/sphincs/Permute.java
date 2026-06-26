package org.spongycastle.pqc.crypto.sphincs;

import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
class Permute {
    private static final int CHACHA_ROUNDS = 12;

    public static void permute(int i2, int[] iArr) {
        int i3 = 16;
        if (iArr.length != 16) {
            throw new IllegalArgumentException();
        }
        if (i2 % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        }
        char c2 = 0;
        int i4 = iArr[0];
        int i5 = iArr[1];
        int i6 = iArr[2];
        int i7 = iArr[3];
        int i8 = iArr[4];
        int i9 = iArr[5];
        int i10 = iArr[6];
        int i11 = 7;
        int i12 = iArr[7];
        int i13 = 8;
        int i14 = iArr[8];
        int i15 = iArr[9];
        int i16 = iArr[10];
        int i17 = iArr[11];
        int i18 = iArr[12];
        int i19 = iArr[13];
        int i20 = iArr[14];
        int iRotl = iArr[15];
        int iRotl2 = i20;
        int iRotl3 = i19;
        int iRotl4 = i18;
        int i21 = i17;
        int i22 = i16;
        int i23 = i15;
        int i24 = i14;
        int iRotl5 = i12;
        int iRotl6 = i10;
        int iRotl7 = i9;
        int iRotl8 = i8;
        int i25 = i7;
        int i26 = i6;
        int i27 = i5;
        int i28 = i4;
        int i29 = i2;
        while (i29 > 0) {
            int i30 = i28 + iRotl8;
            int iRotl9 = rotl(iRotl4 ^ i30, i3);
            int i31 = i24 + iRotl9;
            int iRotl10 = rotl(iRotl8 ^ i31, 12);
            int i32 = i30 + iRotl10;
            int iRotl11 = rotl(iRotl9 ^ i32, i13);
            int i33 = i31 + iRotl11;
            int iRotl12 = rotl(iRotl10 ^ i33, i11);
            int i34 = i27 + iRotl7;
            int iRotl13 = rotl(iRotl3 ^ i34, i3);
            int i35 = i23 + iRotl13;
            int iRotl14 = rotl(iRotl7 ^ i35, 12);
            int i36 = i34 + iRotl14;
            int iRotl15 = rotl(iRotl13 ^ i36, i13);
            int i37 = i35 + iRotl15;
            int iRotl16 = rotl(iRotl14 ^ i37, i11);
            int i38 = i26 + iRotl6;
            int iRotl17 = rotl(iRotl2 ^ i38, i3);
            int i39 = i22 + iRotl17;
            int iRotl18 = rotl(iRotl6 ^ i39, 12);
            int i40 = i38 + iRotl18;
            int iRotl19 = rotl(iRotl17 ^ i40, i13);
            int i41 = i39 + iRotl19;
            int iRotl20 = rotl(iRotl18 ^ i41, i11);
            int i42 = i25 + iRotl5;
            int iRotl21 = rotl(iRotl ^ i42, i3);
            int i43 = i21 + iRotl21;
            int iRotl22 = rotl(iRotl5 ^ i43, 12);
            int i44 = i42 + iRotl22;
            int iRotl23 = rotl(iRotl21 ^ i44, i13);
            int i45 = i43 + iRotl23;
            int iRotl24 = rotl(iRotl22 ^ i45, 7);
            int i46 = i32 + iRotl16;
            int iRotl25 = rotl(iRotl23 ^ i46, 16);
            int i47 = i41 + iRotl25;
            int iRotl26 = rotl(iRotl16 ^ i47, 12);
            i28 = i46 + iRotl26;
            iRotl = rotl(iRotl25 ^ i28, 8);
            i22 = i47 + iRotl;
            iRotl7 = rotl(iRotl26 ^ i22, 7);
            int i48 = i36 + iRotl20;
            int iRotl27 = rotl(iRotl11 ^ i48, 16);
            int i49 = i45 + iRotl27;
            int iRotl28 = rotl(iRotl20 ^ i49, 12);
            i27 = i48 + iRotl28;
            iRotl4 = rotl(iRotl27 ^ i27, 8);
            i21 = i49 + iRotl4;
            iRotl6 = rotl(iRotl28 ^ i21, 7);
            int i50 = i40 + iRotl24;
            int iRotl29 = rotl(iRotl15 ^ i50, 16);
            int i51 = i33 + iRotl29;
            int iRotl30 = rotl(iRotl24 ^ i51, 12);
            i26 = i50 + iRotl30;
            iRotl3 = rotl(iRotl29 ^ i26, 8);
            i24 = i51 + iRotl3;
            iRotl5 = rotl(iRotl30 ^ i24, 7);
            int i52 = i44 + iRotl12;
            i3 = 16;
            int iRotl31 = rotl(iRotl19 ^ i52, 16);
            int i53 = i37 + iRotl31;
            int iRotl32 = rotl(iRotl12 ^ i53, 12);
            i25 = i52 + iRotl32;
            iRotl2 = rotl(iRotl31 ^ i25, 8);
            i23 = i53 + iRotl2;
            iRotl8 = rotl(iRotl32 ^ i23, 7);
            i29 -= 2;
            c2 = 0;
            i13 = 8;
            i11 = 7;
        }
        iArr[c2] = i28;
        iArr[1] = i27;
        iArr[2] = i26;
        iArr[3] = i25;
        iArr[4] = iRotl8;
        iArr[5] = iRotl7;
        iArr[6] = iRotl6;
        iArr[7] = iRotl5;
        iArr[8] = i24;
        iArr[9] = i23;
        iArr[10] = i22;
        iArr[11] = i21;
        iArr[12] = iRotl4;
        iArr[13] = iRotl3;
        iArr[14] = iRotl2;
        iArr[15] = iRotl;
    }

    public static int rotl(int i2, int i3) {
        return (i2 >>> (-i3)) | (i2 << i3);
    }

    public void chacha_permute(byte[] bArr, byte[] bArr2) {
        int[] iArr = new int[16];
        for (int i2 = 0; i2 < 16; i2++) {
            iArr[i2] = Pack.littleEndianToInt(bArr2, i2 * 4);
        }
        permute(12, iArr);
        for (int i3 = 0; i3 < 16; i3++) {
            Pack.intToLittleEndian(iArr[i3], bArr, i3 * 4);
        }
    }
}
