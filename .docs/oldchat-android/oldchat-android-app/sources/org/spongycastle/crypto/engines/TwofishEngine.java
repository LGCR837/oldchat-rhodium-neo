package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.signers.PSSSigner;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.math.ec.Tnaf;

/* JADX INFO: loaded from: classes.dex */
public final class TwofishEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int GF256_FDBK = 361;
    private static final int GF256_FDBK_2 = 180;
    private static final int GF256_FDBK_4 = 90;
    private static final int INPUT_WHITEN = 0;
    private static final int MAX_KEY_BITS = 256;
    private static final int MAX_ROUNDS = 16;
    private static final int OUTPUT_WHITEN = 4;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    private static final byte[][] f7337P = {new byte[]{-87, 103, -77, -24, 4, -3, -93, 118, -102, -110, -128, 120, -28, -35, -47, 56, 13, -58, 53, -104, 24, -9, -20, 108, 67, 117, 55, 38, -6, 19, -108, 72, -14, -48, -117, 48, -124, 84, -33, 35, 25, 91, 61, 89, -13, -82, -94, -126, 99, 1, -125, 46, -39, 81, -101, 124, -90, -21, -91, -66, 22, 12, -29, 97, -64, -116, 58, -11, 115, 44, 37, 11, -69, 78, -119, 107, 83, 106, -76, -15, -31, -26, -67, 69, -30, -12, -74, 102, -52, -107, 3, 86, -44, 28, 30, -41, -5, -61, -114, -75, -23, -49, -65, -70, -22, 119, 57, -81, 51, -55, 98, 113, -127, 121, 9, -83, 36, -51, -7, -40, -27, -59, -71, 77, 68, 8, -122, -25, -95, 29, -86, -19, 6, 112, -78, -46, 65, 123, -96, 17, 49, -62, 39, -112, 32, -10, 96, -1, -106, 92, -79, -85, -98, -100, 82, 27, 95, -109, 10, -17, -111, -123, 73, -18, 45, 79, -113, 59, 71, -121, 109, 70, -42, 62, 105, 100, 42, -50, -53, 47, -4, -105, 5, 122, -84, 127, -43, 26, 75, 14, -89, 90, 40, 20, 63, 41, -120, 60, 76, 2, -72, -38, -80, 23, 85, 31, -118, 125, 87, -57, -115, 116, -73, -60, -97, 114, 126, 21, 34, 18, 88, 7, -103, 52, 110, 80, -34, 104, 101, PSSSigner.TRAILER_IMPLICIT, -37, -8, -56, -88, 43, 64, -36, -2, 50, -92, -54, Tnaf.POW_2_WIDTH, 33, -16, -45, 93, 15, 0, 111, -99, 54, 66, 74, 94, -63, -32}, new byte[]{117, -13, -58, -12, -37, 123, -5, -56, 74, -45, -26, 107, 69, 125, -24, 75, -42, 50, -40, -3, 55, 113, -15, -31, 48, 15, -8, 27, -121, -6, 6, 63, 94, -70, -82, 91, -118, 0, PSSSigner.TRAILER_IMPLICIT, -99, 109, -63, -79, 14, -128, 93, -46, -43, -96, -124, 7, 20, -75, -112, 44, -93, -78, 115, 76, 84, -110, 116, 54, 81, 56, -80, -67, 90, -4, 96, 98, -106, 108, 66, -9, Tnaf.POW_2_WIDTH, 124, 40, 39, -116, 19, -107, -100, -57, 36, 70, 59, 112, -54, -29, -123, -53, 17, -48, -109, -72, -90, -125, 32, -1, -97, 119, -61, -52, 3, 111, 8, -65, 64, -25, 43, -30, 121, 12, -86, -126, 65, 58, -22, -71, -28, -102, -92, -105, 126, -38, 122, 23, 102, -108, -95, 29, 61, -16, -34, -77, 11, 114, -89, 28, -17, -47, 83, 62, -113, 51, 38, 95, -20, 118, 42, 73, -127, -120, -18, 33, -60, 26, -21, -39, -59, 57, -103, -51, -83, 49, -117, 1, 24, 35, -35, 31, 78, 45, -7, 72, 79, -14, 101, -114, 120, 92, 88, 25, -115, -27, -104, 87, 103, 127, 5, 100, -81, 99, -74, -2, -11, -73, 60, -91, -50, -23, 104, 68, -32, 77, 67, 105, 41, 46, -84, 21, 89, -88, 10, -98, 110, 71, -33, 52, 53, 106, -49, -36, 34, -55, -64, -101, -119, -44, -19, -85, 18, -94, 13, 82, -69, 2, 47, -87, -41, 97, 30, -76, 80, 4, -10, -62, 22, 37, -122, 86, 85, 9, -66, -111}};
    private static final int P_00 = 1;
    private static final int P_01 = 0;
    private static final int P_02 = 0;
    private static final int P_03 = 1;
    private static final int P_04 = 1;
    private static final int P_10 = 0;
    private static final int P_11 = 0;
    private static final int P_12 = 1;
    private static final int P_13 = 1;
    private static final int P_14 = 0;
    private static final int P_20 = 1;
    private static final int P_21 = 1;
    private static final int P_22 = 0;
    private static final int P_23 = 0;
    private static final int P_24 = 0;
    private static final int P_30 = 0;
    private static final int P_31 = 1;
    private static final int P_32 = 1;
    private static final int P_33 = 0;
    private static final int P_34 = 1;
    private static final int ROUNDS = 16;
    private static final int ROUND_SUBKEYS = 8;
    private static final int RS_GF_FDBK = 333;
    private static final int SK_BUMP = 16843009;
    private static final int SK_ROTL = 9;
    private static final int SK_STEP = 33686018;
    private static final int TOTAL_SUBKEYS = 40;
    private int[] gSBox;
    private int[] gSubKeys;
    private boolean encrypting = false;
    private int[] gMDS0 = new int[256];
    private int[] gMDS1 = new int[256];
    private int[] gMDS2 = new int[256];
    private int[] gMDS3 = new int[256];
    private int k64Cnt = 0;
    private byte[] workingKey = null;

    public TwofishEngine() {
        for (int i2 = 0; i2 < 256; i2++) {
            byte[][] bArr = f7337P;
            int i3 = bArr[0][i2] & 255;
            int iMx_X = Mx_X(i3) & 255;
            int iMx_Y = Mx_Y(i3) & 255;
            int i4 = bArr[1][i2] & 255;
            int[] iArr = {i3, i4};
            int[] iArr2 = {iMx_X, Mx_X(i4) & 255};
            int[] iArr3 = {iMx_Y, Mx_Y(i4) & 255};
            int[] iArr4 = this.gMDS0;
            int i5 = iArr[1] | (iArr2[1] << 8);
            int i6 = iArr3[1];
            iArr4[i2] = i5 | (i6 << 16) | (i6 << 24);
            int[] iArr5 = this.gMDS1;
            int i7 = iArr3[0];
            iArr5[i2] = i7 | (i7 << 8) | (iArr2[0] << 16) | (iArr[0] << 24);
            int[] iArr6 = this.gMDS2;
            int i8 = iArr2[1];
            int i9 = iArr3[1];
            iArr6[i2] = (iArr[1] << 16) | i8 | (i9 << 8) | (i9 << 24);
            int[] iArr7 = this.gMDS3;
            int i10 = iArr2[0];
            iArr7[i2] = (iArr3[0] << 16) | (iArr[0] << 8) | i10 | (i10 << 24);
        }
    }

    private void Bits32ToBytes(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) i2;
        bArr[i3 + 1] = (byte) (i2 >> 8);
        bArr[i3 + 2] = (byte) (i2 >> 16);
        bArr[i3 + 3] = (byte) (i2 >> 24);
    }

    private int BytesTo32Bits(byte[] bArr, int i2) {
        return ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
    }

    private int F32(int i2, int[] iArr) {
        int i3;
        int i4;
        int iB0 = b0(i2);
        int iB1 = b1(i2);
        int iB2 = b2(i2);
        int iB3 = b3(i2);
        int i5 = iArr[0];
        int i6 = iArr[1];
        int i7 = iArr[2];
        int i8 = iArr[3];
        int i9 = this.k64Cnt & 3;
        if (i9 != 0) {
            if (i9 == 1) {
                int[] iArr2 = this.gMDS0;
                byte[][] bArr = f7337P;
                i3 = (iArr2[(bArr[0][iB0] & 255) ^ b0(i5)] ^ this.gMDS1[(bArr[0][iB1] & 255) ^ b1(i5)]) ^ this.gMDS2[(bArr[1][iB2] & 255) ^ b2(i5)];
                i4 = this.gMDS3[(bArr[1][iB3] & 255) ^ b3(i5)];
                return i3 ^ i4;
            }
            if (i9 != 2) {
                if (i9 != 3) {
                    return 0;
                }
            }
            int[] iArr3 = this.gMDS0;
            byte[][] bArr2 = f7337P;
            byte[] bArr3 = bArr2[0];
            i3 = (iArr3[(bArr3[(bArr3[iB0] & 255) ^ b0(i6)] & 255) ^ b0(i5)] ^ this.gMDS1[(bArr2[0][(bArr2[1][iB1] & 255) ^ b1(i6)] & 255) ^ b1(i5)]) ^ this.gMDS2[(bArr2[1][(bArr2[0][iB2] & 255) ^ b2(i6)] & 255) ^ b2(i5)];
            int[] iArr4 = this.gMDS3;
            byte[] bArr4 = bArr2[1];
            i4 = iArr4[(bArr4[(bArr4[iB3] & 255) ^ b3(i6)] & 255) ^ b3(i5)];
            return i3 ^ i4;
        }
        byte[][] bArr5 = f7337P;
        iB0 = (bArr5[1][iB0] & 255) ^ b0(i8);
        iB1 = (bArr5[0][iB1] & 255) ^ b1(i8);
        iB2 = (bArr5[0][iB2] & 255) ^ b2(i8);
        iB3 = (bArr5[1][iB3] & 255) ^ b3(i8);
        byte[][] bArr6 = f7337P;
        iB0 = (bArr6[1][iB0] & 255) ^ b0(i7);
        iB1 = (bArr6[1][iB1] & 255) ^ b1(i7);
        iB2 = (bArr6[0][iB2] & 255) ^ b2(i7);
        iB3 = (bArr6[0][iB3] & 255) ^ b3(i7);
        int[] iArr32 = this.gMDS0;
        byte[][] bArr22 = f7337P;
        byte[] bArr32 = bArr22[0];
        i3 = (iArr32[(bArr32[(bArr32[iB0] & 255) ^ b0(i6)] & 255) ^ b0(i5)] ^ this.gMDS1[(bArr22[0][(bArr22[1][iB1] & 255) ^ b1(i6)] & 255) ^ b1(i5)]) ^ this.gMDS2[(bArr22[1][(bArr22[0][iB2] & 255) ^ b2(i6)] & 255) ^ b2(i5)];
        int[] iArr42 = this.gMDS3;
        byte[] bArr42 = bArr22[1];
        i4 = iArr42[(bArr42[(bArr42[iB3] & 255) ^ b3(i6)] & 255) ^ b3(i5)];
        return i3 ^ i4;
    }

    private int Fe32_0(int i2) {
        int[] iArr = this.gSBox;
        return iArr[(((i2 >>> 24) & 255) * 2) + 513] ^ ((iArr[(i2 & 255) * 2] ^ iArr[(((i2 >>> 8) & 255) * 2) + 1]) ^ iArr[(((i2 >>> 16) & 255) * 2) + 512]);
    }

    private int Fe32_3(int i2) {
        int[] iArr = this.gSBox;
        return iArr[(((i2 >>> 16) & 255) * 2) + 513] ^ ((iArr[((i2 >>> 24) & 255) * 2] ^ iArr[((i2 & 255) * 2) + 1]) ^ iArr[(((i2 >>> 8) & 255) * 2) + 512]);
    }

    private int LFSR1(int i2) {
        return ((i2 & 1) != 0 ? 180 : 0) ^ (i2 >> 1);
    }

    private int LFSR2(int i2) {
        return ((i2 >> 2) ^ ((i2 & 2) != 0 ? 180 : 0)) ^ ((i2 & 1) != 0 ? GF256_FDBK_4 : 0);
    }

    private int Mx_X(int i2) {
        return i2 ^ LFSR2(i2);
    }

    private int Mx_Y(int i2) {
        return LFSR2(i2) ^ (LFSR1(i2) ^ i2);
    }

    private int RS_MDS_Encode(int i2, int i3) {
        for (int i4 = 0; i4 < 4; i4++) {
            i3 = RS_rem(i3);
        }
        int iRS_rem = i2 ^ i3;
        for (int i5 = 0; i5 < 4; i5++) {
            iRS_rem = RS_rem(iRS_rem);
        }
        return iRS_rem;
    }

    private int RS_rem(int i2) {
        int i3 = i2 >>> 24;
        int i4 = i3 & 255;
        int i5 = ((i4 << 1) ^ ((i3 & 128) != 0 ? RS_GF_FDBK : 0)) & 255;
        int i6 = ((i4 >>> 1) ^ ((i3 & 1) != 0 ? CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256 : 0)) ^ i5;
        return ((((i2 << 8) ^ (i6 << 24)) ^ (i5 << 16)) ^ (i6 << 8)) ^ i4;
    }

    private int b0(int i2) {
        return i2 & 255;
    }

    private int b1(int i2) {
        return (i2 >>> 8) & 255;
    }

    private int b2(int i2) {
        return (i2 >>> 16) & 255;
    }

    private int b3(int i2) {
        return (i2 >>> 24) & 255;
    }

    private void decryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iBytesTo32Bits = BytesTo32Bits(bArr, i2) ^ this.gSubKeys[4];
        int iBytesTo32Bits2 = BytesTo32Bits(bArr, i2 + 4) ^ this.gSubKeys[5];
        int iBytesTo32Bits3 = BytesTo32Bits(bArr, i2 + 8) ^ this.gSubKeys[6];
        int iBytesTo32Bits4 = BytesTo32Bits(bArr, i2 + 12) ^ this.gSubKeys[7];
        int i4 = 39;
        for (int i5 = 0; i5 < 16; i5 += 2) {
            int iFe32_0 = Fe32_0(iBytesTo32Bits);
            int iFe32_3 = Fe32_3(iBytesTo32Bits2);
            int[] iArr = this.gSubKeys;
            int i6 = iBytesTo32Bits4 ^ (((iFe32_3 * 2) + iFe32_0) + iArr[i4]);
            iBytesTo32Bits3 = ((iBytesTo32Bits3 >>> 31) | (iBytesTo32Bits3 << 1)) ^ ((iFe32_0 + iFe32_3) + iArr[i4 - 1]);
            iBytesTo32Bits4 = (i6 << 31) | (i6 >>> 1);
            int iFe32_02 = Fe32_0(iBytesTo32Bits3);
            int iFe32_32 = Fe32_3(iBytesTo32Bits4);
            int[] iArr2 = this.gSubKeys;
            int i7 = i4 - 3;
            int i8 = iBytesTo32Bits2 ^ (((iFe32_32 * 2) + iFe32_02) + iArr2[i4 - 2]);
            i4 -= 4;
            iBytesTo32Bits = ((iBytesTo32Bits >>> 31) | (iBytesTo32Bits << 1)) ^ ((iFe32_02 + iFe32_32) + iArr2[i7]);
            iBytesTo32Bits2 = (i8 << 31) | (i8 >>> 1);
        }
        Bits32ToBytes(this.gSubKeys[0] ^ iBytesTo32Bits3, bArr2, i3);
        Bits32ToBytes(iBytesTo32Bits4 ^ this.gSubKeys[1], bArr2, i3 + 4);
        Bits32ToBytes(this.gSubKeys[2] ^ iBytesTo32Bits, bArr2, i3 + 8);
        Bits32ToBytes(this.gSubKeys[3] ^ iBytesTo32Bits2, bArr2, i3 + 12);
    }

    private void encryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iBytesTo32Bits = BytesTo32Bits(bArr, i2) ^ this.gSubKeys[0];
        int iBytesTo32Bits2 = BytesTo32Bits(bArr, i2 + 4) ^ this.gSubKeys[1];
        int iBytesTo32Bits3 = BytesTo32Bits(bArr, i2 + 8) ^ this.gSubKeys[2];
        int iBytesTo32Bits4 = BytesTo32Bits(bArr, i2 + 12) ^ this.gSubKeys[3];
        int i4 = 8;
        for (int i5 = 0; i5 < 16; i5 += 2) {
            int iFe32_0 = Fe32_0(iBytesTo32Bits);
            int iFe32_3 = Fe32_3(iBytesTo32Bits2);
            int[] iArr = this.gSubKeys;
            int i6 = iBytesTo32Bits3 ^ ((iFe32_0 + iFe32_3) + iArr[i4]);
            iBytesTo32Bits3 = (i6 << 31) | (i6 >>> 1);
            iBytesTo32Bits4 = ((iBytesTo32Bits4 >>> 31) | (iBytesTo32Bits4 << 1)) ^ ((iFe32_0 + (iFe32_3 * 2)) + iArr[i4 + 1]);
            int iFe32_02 = Fe32_0(iBytesTo32Bits3);
            int iFe32_32 = Fe32_3(iBytesTo32Bits4);
            int[] iArr2 = this.gSubKeys;
            int i7 = i4 + 3;
            int i8 = iBytesTo32Bits ^ ((iFe32_02 + iFe32_32) + iArr2[i4 + 2]);
            iBytesTo32Bits = (i8 << 31) | (i8 >>> 1);
            i4 += 4;
            iBytesTo32Bits2 = ((iBytesTo32Bits2 >>> 31) | (iBytesTo32Bits2 << 1)) ^ ((iFe32_02 + (iFe32_32 * 2)) + iArr2[i7]);
        }
        Bits32ToBytes(this.gSubKeys[4] ^ iBytesTo32Bits3, bArr2, i3);
        Bits32ToBytes(iBytesTo32Bits4 ^ this.gSubKeys[5], bArr2, i3 + 4);
        Bits32ToBytes(this.gSubKeys[6] ^ iBytesTo32Bits, bArr2, i3 + 8);
        Bits32ToBytes(this.gSubKeys[7] ^ iBytesTo32Bits2, bArr2, i3 + 12);
    }

    private void setKey(byte[] bArr) {
        int iB0;
        int iB1;
        int iB2;
        int iB3;
        int iB22;
        int iB12;
        int iB02;
        int iB32;
        int[] iArr = new int[4];
        int[] iArr2 = new int[4];
        int[] iArr3 = new int[4];
        this.gSubKeys = new int[40];
        int i2 = this.k64Cnt;
        if (i2 < 1) {
            throw new IllegalArgumentException("Key size less than 64 bits");
        }
        if (i2 > 4) {
            throw new IllegalArgumentException("Key size larger than 256 bits");
        }
        for (int i3 = 0; i3 < this.k64Cnt; i3++) {
            int i4 = i3 * 8;
            iArr[i3] = BytesTo32Bits(bArr, i4);
            int iBytesTo32Bits = BytesTo32Bits(bArr, i4 + 4);
            iArr2[i3] = iBytesTo32Bits;
            iArr3[(this.k64Cnt - 1) - i3] = RS_MDS_Encode(iArr[i3], iBytesTo32Bits);
        }
        for (int i5 = 0; i5 < 20; i5++) {
            int i6 = SK_STEP * i5;
            int iF32 = F32(i6, iArr);
            int iF322 = F32(i6 + 16843009, iArr2);
            int i7 = (iF322 >>> 24) | (iF322 << 8);
            int i8 = iF32 + i7;
            int[] iArr4 = this.gSubKeys;
            int i9 = i5 * 2;
            iArr4[i9] = i8;
            int i10 = i8 + i7;
            iArr4[i9 + 1] = (i10 << 9) | (i10 >>> 23);
        }
        int i11 = iArr3[0];
        int i12 = iArr3[1];
        int i13 = 2;
        int i14 = iArr3[2];
        int i15 = iArr3[3];
        this.gSBox = new int[1024];
        int i16 = 0;
        while (i16 < 256) {
            int i17 = this.k64Cnt & 3;
            if (i17 != 0) {
                if (i17 == 1) {
                    int[] iArr5 = this.gSBox;
                    int i18 = i16 * 2;
                    int[] iArr6 = this.gMDS0;
                    byte[][] bArr2 = f7337P;
                    iArr5[i18] = iArr6[(bArr2[0][i16] & 255) ^ b0(i11)];
                    this.gSBox[i18 + 1] = this.gMDS1[(bArr2[0][i16] & 255) ^ b1(i11)];
                    this.gSBox[i18 + 512] = this.gMDS2[(bArr2[1][i16] & 255) ^ b2(i11)];
                    this.gSBox[i18 + 513] = this.gMDS3[(bArr2[1][i16] & 255) ^ b3(i11)];
                } else if (i17 == i13) {
                    iB32 = i16;
                    iB02 = iB32;
                    iB12 = iB02;
                    iB22 = iB12;
                    int[] iArr7 = this.gSBox;
                    int i19 = i16 * 2;
                    int[] iArr8 = this.gMDS0;
                    byte[][] bArr3 = f7337P;
                    byte[] bArr4 = bArr3[0];
                    iArr7[i19] = iArr8[(bArr4[(bArr4[iB02] & 255) ^ b0(i12)] & 255) ^ b0(i11)];
                    this.gSBox[i19 + 1] = this.gMDS1[(bArr3[0][(bArr3[1][iB12] & 255) ^ b1(i12)] & 255) ^ b1(i11)];
                    this.gSBox[i19 + 512] = this.gMDS2[(bArr3[1][(bArr3[0][iB22] & 255) ^ b2(i12)] & 255) ^ b2(i11)];
                    int[] iArr9 = this.gMDS3;
                    byte[] bArr5 = bArr3[1];
                    this.gSBox[i19 + 513] = iArr9[(bArr5[(bArr5[iB32] & 255) ^ b3(i12)] & 255) ^ b3(i11)];
                } else if (i17 == 3) {
                    iB3 = i16;
                    iB0 = iB3;
                    iB1 = iB0;
                    iB2 = iB1;
                }
                i16++;
                i13 = 2;
            } else {
                byte[][] bArr6 = f7337P;
                iB0 = (bArr6[1][i16] & 255) ^ b0(i15);
                iB1 = (bArr6[0][i16] & 255) ^ b1(i15);
                iB2 = (bArr6[0][i16] & 255) ^ b2(i15);
                iB3 = (bArr6[1][i16] & 255) ^ b3(i15);
            }
            byte[][] bArr7 = f7337P;
            iB02 = (bArr7[1][iB0] & 255) ^ b0(i14);
            iB12 = (bArr7[1][iB1] & 255) ^ b1(i14);
            iB22 = (bArr7[0][iB2] & 255) ^ b2(i14);
            iB32 = (bArr7[0][iB3] & 255) ^ b3(i14);
            int[] iArr72 = this.gSBox;
            int i192 = i16 * 2;
            int[] iArr82 = this.gMDS0;
            byte[][] bArr32 = f7337P;
            byte[] bArr42 = bArr32[0];
            iArr72[i192] = iArr82[(bArr42[(bArr42[iB02] & 255) ^ b0(i12)] & 255) ^ b0(i11)];
            this.gSBox[i192 + 1] = this.gMDS1[(bArr32[0][(bArr32[1][iB12] & 255) ^ b1(i12)] & 255) ^ b1(i11)];
            this.gSBox[i192 + 512] = this.gMDS2[(bArr32[1][(bArr32[0][iB22] & 255) ^ b2(i12)] & 255) ^ b2(i11)];
            int[] iArr92 = this.gMDS3;
            byte[] bArr52 = bArr32[1];
            this.gSBox[i192 + 513] = iArr92[(bArr52[(bArr52[iB32] & 255) ^ b3(i12)] & 255) ^ b3(i11)];
            i16++;
            i13 = 2;
        }
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Twofish";
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to Twofish init - " + cipherParameters.getClass().getName());
        }
        this.encrypting = z2;
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        this.workingKey = key;
        this.k64Cnt = key.length / 8;
        setKey(key);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (this.workingKey == null) {
            throw new IllegalStateException("Twofish not initialised");
        }
        if (i2 + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.encrypting) {
            encryptBlock(bArr, i2, bArr2, i3);
            return 16;
        }
        decryptBlock(bArr, i2, bArr2, i3);
        return 16;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
        byte[] bArr = this.workingKey;
        if (bArr != null) {
            setKey(bArr);
        }
    }
}
