package org.spongycastle.pqc.crypto.sphincs;

/* JADX INFO: loaded from: classes.dex */
class Wots {
    static final int WOTS_L = 67;
    static final int WOTS_L1 = 64;
    static final int WOTS_LOGW = 4;
    static final int WOTS_LOG_L = 7;
    static final int WOTS_SIGBYTES = 2144;
    static final int WOTS_W = 16;

    private static void clear(byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 != i3; i4++) {
            bArr[i4 + i2] = 0;
        }
    }

    public static void expand_seed(byte[] bArr, int i2, byte[] bArr2, int i3) {
        clear(bArr, i2, WOTS_SIGBYTES);
        Seed.prg(bArr, i2, 2144L, bArr2, i3);
    }

    public static void gen_chain(HashFunctions hashFunctions, byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4, int i5) {
        for (int i6 = 0; i6 < 32; i6++) {
            bArr[i6 + i2] = bArr2[i6 + i3];
        }
        for (int i7 = 0; i7 < i5 && i7 < 16; i7++) {
            hashFunctions.hash_n_n_mask(bArr, i2, bArr, i2, bArr3, i4 + (i7 * 32));
        }
    }

    public void wots_pkgen(HashFunctions hashFunctions, byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4) {
        expand_seed(bArr, i2, bArr2, i3);
        for (int i5 = 0; i5 < 67; i5++) {
            int i6 = i2 + (i5 * 32);
            gen_chain(hashFunctions, bArr, i6, bArr, i6, bArr3, i4, 15);
        }
    }

    public void wots_sign(HashFunctions hashFunctions, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        int[] iArr = new int[67];
        int i3 = 0;
        int i4 = 0;
        while (i3 < 64) {
            byte b2 = bArr2[i3 / 2];
            iArr[i3] = b2 & 15;
            int i5 = (b2 & 255) >>> 4;
            iArr[i3 + 1] = i5;
            i4 = i4 + (15 - iArr[i3]) + (15 - i5);
            i3 += 2;
        }
        while (i3 < 67) {
            iArr[i3] = i4 & 15;
            i4 >>>= 4;
            i3++;
        }
        expand_seed(bArr, i2, bArr3, 0);
        for (int i6 = 0; i6 < 67; i6++) {
            int i7 = i2 + (i6 * 32);
            gen_chain(hashFunctions, bArr, i7, bArr, i7, bArr4, 0, iArr[i6]);
        }
    }

    public void wots_verify(HashFunctions hashFunctions, byte[] bArr, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4) {
        int[] iArr = new int[67];
        int i3 = 0;
        int i4 = 0;
        while (i3 < 64) {
            byte b2 = bArr3[i3 / 2];
            iArr[i3] = b2 & 15;
            int i5 = (b2 & 255) >>> 4;
            iArr[i3 + 1] = i5;
            i4 = i4 + (15 - iArr[i3]) + (15 - i5);
            i3 += 2;
        }
        while (i3 < 67) {
            iArr[i3] = i4 & 15;
            i4 >>>= 4;
            i3++;
        }
        for (int i6 = 0; i6 < 67; i6++) {
            int i7 = i6 * 32;
            int i8 = iArr[i6];
            gen_chain(hashFunctions, bArr, i7, bArr2, i2 + i7, bArr4, i8 * 32, 15 - i8);
        }
    }
}
