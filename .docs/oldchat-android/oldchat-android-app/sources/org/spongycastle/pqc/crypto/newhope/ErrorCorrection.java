package org.spongycastle.pqc.crypto.newhope;

import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
class ErrorCorrection {
    public static short LDDecode(int i2, int i3, int i4, int i5) {
        return (short) (((((g(i2) + g(i3)) + g(i4)) + g(i5)) - 98312) >>> 31);
    }

    public static int abs(int i2) {
        int i3 = i2 >> 31;
        return (i2 ^ i3) - i3;
    }

    public static int f(int[] iArr, int i2, int i3, int i4) {
        int i5 = (i4 * 2730) >> 25;
        int i6 = i5 - ((12288 - (i4 - (i5 * 12289))) >> 31);
        iArr[i2] = (i6 >> 1) + (i6 & 1);
        int i7 = i6 - 1;
        iArr[i3] = (i7 >> 1) + (i7 & 1);
        return abs(i4 - (iArr[i2] * 24578));
    }

    public static int g(int i2) {
        int i3 = (i2 * 2730) >> 27;
        int i4 = i3 - ((CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA - (i2 - (CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA * i3))) >> 31);
        return abs((((i4 >> 1) + (i4 & 1)) * 98312) - i2);
    }

    public static void helpRec(short[] sArr, short[] sArr2, byte[] bArr, byte b2) {
        byte[] bArr2 = new byte[8];
        bArr2[0] = b2;
        byte[] bArr3 = new byte[32];
        ChaCha20.process(bArr, bArr2, bArr3, 0, 32);
        int[] iArr = new int[8];
        for (int i2 = 0; i2 < 256; i2++) {
            int i3 = ((bArr3[i2 >>> 3] >>> (i2 & 7)) & 1) * 4;
            int i4 = i2 + 256;
            int i5 = i2 + 512;
            int i6 = i2 + 768;
            int iF = (24577 - (((f(iArr, 0, 4, (sArr2[i2] * 8) + i3) + f(iArr, 1, 5, (sArr2[i4] * 8) + i3)) + f(iArr, 2, 6, (sArr2[i5] * 8) + i3)) + f(iArr, 3, 7, (sArr2[i6] * 8) + i3))) >> 31;
            int i7 = iF ^ (-1);
            int[] iArr2 = {(i7 & iArr[0]) ^ (iArr[4] & iF), (i7 & iArr[1]) ^ (iArr[5] & iF), (i7 & iArr[2]) ^ (iArr[6] & iF), (iArr[7] & iF) ^ (i7 & iArr[3])};
            int i8 = iArr2[0];
            int i9 = iArr2[3];
            sArr[i2] = (short) ((i8 - i9) & 3);
            sArr[i4] = (short) ((iArr2[1] - i9) & 3);
            sArr[i5] = (short) ((iArr2[2] - i9) & 3);
            sArr[i6] = (short) (((-iF) + (i9 * 2)) & 3);
        }
    }

    public static void rec(byte[] bArr, short[] sArr, short[] sArr2) {
        Arrays.fill(bArr, (byte) 0);
        for (int i2 = 0; i2 < 256; i2++) {
            int i3 = (sArr[i2] * 8) + 196624;
            int i4 = sArr2[i2] * 2;
            int i5 = i2 + 768;
            short s2 = sArr2[i5];
            int i6 = i3 - ((i4 + s2) * 12289);
            int i7 = i2 + 256;
            int i8 = ((sArr[i7] * 8) + 196624) - (((sArr2[i7] * 2) + s2) * 12289);
            int i9 = i2 + 512;
            int[] iArr = {i6, i8, ((sArr[i9] * 8) + 196624) - (((sArr2[i9] * 2) + s2) * 12289), ((sArr[i5] * 8) + 196624) - (s2 * 12289)};
            int i10 = i2 >>> 3;
            bArr[i10] = (byte) ((LDDecode(iArr[0], iArr[1], iArr[2], iArr[3]) << (i2 & 7)) | bArr[i10]);
        }
    }
}
