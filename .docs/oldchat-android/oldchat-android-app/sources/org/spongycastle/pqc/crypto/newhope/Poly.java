package org.spongycastle.pqc.crypto.newhope;

import android.R;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.crypto.digests.SHAKEDigest;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
class Poly {
    public static void add(short[] sArr, short[] sArr2, short[] sArr3) {
        for (int i2 = 0; i2 < 1024; i2++) {
            sArr3[i2] = Reduce.barrett((short) (sArr[i2] + sArr2[i2]));
        }
    }

    public static void fromBytes(short[] sArr, byte[] bArr) {
        for (int i2 = 0; i2 < 256; i2++) {
            int i3 = i2 * 7;
            int i4 = bArr[i3] & 255;
            byte b2 = bArr[i3 + 1];
            int i5 = bArr[i3 + 2] & 255;
            byte b3 = bArr[i3 + 3];
            int i6 = bArr[i3 + 4] & 255;
            byte b4 = bArr[i3 + 5];
            int i7 = bArr[i3 + 6] & 255;
            int i8 = i2 * 4;
            sArr[i8] = (short) (i4 | ((b2 & 63) << 8));
            sArr[i8 + 1] = (short) (((b2 & 255) >>> 6) | (i5 << 2) | ((b3 & 15) << 10));
            sArr[i8 + 2] = (short) (((b3 & 255) >>> 4) | (i6 << 4) | ((b4 & 3) << 12));
            sArr[i8 + 3] = (short) ((i7 << 6) | ((b4 & 255) >>> 2));
        }
    }

    public static void fromNTT(short[] sArr) {
        NTT.bitReverse(sArr);
        NTT.core(sArr, Precomp.OMEGAS_INV_MONTGOMERY);
        NTT.mulCoefficients(sArr, Precomp.PSIS_INV_MONTGOMERY);
    }

    public static void getNoise(short[] sArr, byte[] bArr, byte b2) {
        byte[] bArr2 = new byte[8];
        bArr2[0] = b2;
        byte[] bArr3 = new byte[PKIFailureInfo.certConfirmed];
        ChaCha20.process(bArr, bArr2, bArr3, 0, PKIFailureInfo.certConfirmed);
        for (int i2 = 0; i2 < 1024; i2++) {
            int iBigEndianToInt = Pack.bigEndianToInt(bArr3, i2 * 4);
            int i3 = 0;
            for (int i4 = 0; i4 < 8; i4++) {
                i3 += (iBigEndianToInt >> i4) & R.attr.cacheColorHint;
            }
            sArr[i2] = (short) (((((i3 >>> 24) + i3) & 255) + 12289) - (((i3 >>> 16) + (i3 >>> 8)) & 255));
        }
    }

    private static short normalize(short s2) {
        short sBarrett = Reduce.barrett(s2);
        int i2 = sBarrett - 12289;
        return (short) (((sBarrett ^ i2) & (i2 >> 31)) ^ i2);
    }

    public static void pointWise(short[] sArr, short[] sArr2, short[] sArr3) {
        for (int i2 = 0; i2 < 1024; i2++) {
            sArr3[i2] = Reduce.montgomery((sArr[i2] & 65535) * (65535 & Reduce.montgomery((sArr2[i2] & 65535) * 3186)));
        }
    }

    public static void toBytes(byte[] bArr, short[] sArr) {
        for (int i2 = 0; i2 < 256; i2++) {
            int i3 = i2 * 4;
            short sNormalize = normalize(sArr[i3]);
            short sNormalize2 = normalize(sArr[i3 + 1]);
            short sNormalize3 = normalize(sArr[i3 + 2]);
            short sNormalize4 = normalize(sArr[i3 + 3]);
            int i4 = i2 * 7;
            bArr[i4] = (byte) sNormalize;
            bArr[i4 + 1] = (byte) ((sNormalize >> 8) | (sNormalize2 << 6));
            bArr[i4 + 2] = (byte) (sNormalize2 >> 2);
            bArr[i4 + 3] = (byte) ((sNormalize2 >> 10) | (sNormalize3 << 4));
            bArr[i4 + 4] = (byte) (sNormalize3 >> 4);
            bArr[i4 + 5] = (byte) ((sNormalize3 >> 12) | (sNormalize4 << 2));
            bArr[i4 + 6] = (byte) (sNormalize4 >> 6);
        }
    }

    public static void toNTT(short[] sArr) {
        NTT.mulCoefficients(sArr, Precomp.PSIS_BITREV_MONTGOMERY);
        NTT.core(sArr, Precomp.OMEGAS_MONTGOMERY);
    }

    public static void uniform(short[] sArr, byte[] bArr) {
        SHAKEDigest sHAKEDigest = new SHAKEDigest(128);
        sHAKEDigest.update(bArr, 0, bArr.length);
        int i2 = 0;
        while (true) {
            byte[] bArr2 = new byte[256];
            sHAKEDigest.doOutput(bArr2, 0, 256);
            for (int i3 = 0; i3 < 256; i3 += 2) {
                int i4 = ((bArr2[i3] & 255) | ((bArr2[i3 + 1] & 255) << 8)) & 16383;
                if (i4 < 12289) {
                    int i5 = i2 + 1;
                    sArr[i2] = (short) i4;
                    if (i5 == 1024) {
                        return;
                    } else {
                        i2 = i5;
                    }
                }
            }
        }
    }
}
