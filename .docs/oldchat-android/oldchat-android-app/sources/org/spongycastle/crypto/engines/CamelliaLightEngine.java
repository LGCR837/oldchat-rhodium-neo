package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.signers.PSSSigner;
import org.spongycastle.math.ec.Tnaf;

/* JADX INFO: loaded from: classes.dex */
public class CamelliaLightEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int MASK8 = 255;
    private boolean _keyis128;
    private boolean initialized;
    private static final int[] SIGMA = {-1600231809, 1003262091, -1233459112, 1286239154, -957401297, -380665154, 1426019237, -237801700, 283453434, -563598051, -1336506174, -1276722691};
    private static final byte[] SBOX1 = {112, -126, 44, -20, -77, 39, -64, -27, -28, -123, 87, 53, -22, 12, -82, 65, 35, -17, 107, -109, 69, 25, -91, 33, -19, 14, 79, 78, 29, 101, -110, -67, -122, -72, -81, -113, 124, -21, 31, -50, 62, 48, -36, 95, 94, -59, 11, 26, -90, -31, 57, -54, -43, 71, 93, 61, -39, 1, 90, -42, 81, 86, 108, 77, -117, 13, -102, 102, -5, -52, -80, 45, 116, 18, 43, 32, -16, -79, -124, -103, -33, 76, -53, -62, 52, 126, 118, 5, 109, -73, -87, 49, -47, 23, 4, -41, 20, 88, 58, 97, -34, 27, 17, 28, 50, 15, -100, 22, 83, 24, -14, 34, -2, 68, -49, -78, -61, -75, 122, -111, 36, 8, -24, -88, 96, -4, 105, 80, -86, -48, -96, 125, -95, -119, 98, -105, 84, 91, 30, -107, -32, -1, 100, -46, Tnaf.POW_2_WIDTH, -60, 0, 72, -93, -9, 117, -37, -118, 3, -26, -38, 9, 63, -35, -108, -121, 92, -125, 2, -51, 74, -112, 51, 115, 103, -10, -13, -99, 127, -65, -30, 82, -101, -40, 38, -56, 55, -58, 59, -127, -106, 111, 75, 19, -66, 99, 46, -23, 121, -89, -116, -97, 110, PSSSigner.TRAILER_IMPLICIT, -114, 41, -11, -7, -74, 47, -3, -76, 89, 120, -104, 6, 106, -25, 70, 113, -70, -44, 37, -85, 66, -120, -94, -115, -6, 114, 7, -71, 85, -8, -18, -84, 10, 54, 73, 42, 104, 60, 56, -15, -92, 64, 40, -45, 123, -69, -55, 67, -63, 21, -29, -83, -12, 119, -57, -128, -98};
    private int[] subkey = new int[96];
    private int[] kw = new int[8];
    private int[] ke = new int[12];
    private int[] state = new int[4];

    private int bytes2int(byte[] bArr, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < 4; i4++) {
            i3 = (i3 << 8) + (bArr[i4 + i2] & 255);
        }
        return i3;
    }

    private void camelliaF2(int[] iArr, int[] iArr2, int i2) {
        int i3 = iArr[0] ^ iArr2[i2];
        int iSbox4 = sbox4(i3 & 255) | (sbox3((i3 >>> 8) & 255) << 8) | (sbox2((i3 >>> 16) & 255) << 16);
        byte[] bArr = SBOX1;
        int i4 = ((bArr[(i3 >>> 24) & 255] & 255) << 24) | iSbox4;
        int i5 = iArr[1] ^ iArr2[i2 + 1];
        int iLeftRotate = leftRotate((sbox2((i5 >>> 24) & 255) << 24) | (bArr[i5 & 255] & 255) | (sbox4((i5 >>> 8) & 255) << 8) | (sbox3((i5 >>> 16) & 255) << 16), 8);
        int i6 = i4 ^ iLeftRotate;
        int iLeftRotate2 = leftRotate(iLeftRotate, 8) ^ i6;
        int iRightRotate = rightRotate(i6, 8) ^ iLeftRotate2;
        iArr[2] = (leftRotate(iLeftRotate2, 16) ^ iRightRotate) ^ iArr[2];
        iArr[3] = leftRotate(iRightRotate, 8) ^ iArr[3];
        int i7 = iArr[2] ^ iArr2[i2 + 2];
        int iSbox42 = ((bArr[(i7 >>> 24) & 255] & 255) << 24) | sbox4(i7 & 255) | (sbox3((i7 >>> 8) & 255) << 8) | (sbox2((i7 >>> 16) & 255) << 16);
        int i8 = iArr2[i2 + 3] ^ iArr[3];
        int iLeftRotate3 = leftRotate((sbox2((i8 >>> 24) & 255) << 24) | (bArr[i8 & 255] & 255) | (sbox4((i8 >>> 8) & 255) << 8) | (sbox3((i8 >>> 16) & 255) << 16), 8);
        int i9 = iSbox42 ^ iLeftRotate3;
        int iLeftRotate4 = leftRotate(iLeftRotate3, 8) ^ i9;
        int iRightRotate2 = rightRotate(i9, 8) ^ iLeftRotate4;
        iArr[0] = (leftRotate(iLeftRotate4, 16) ^ iRightRotate2) ^ iArr[0];
        iArr[1] = iArr[1] ^ leftRotate(iRightRotate2, 8);
    }

    private void camelliaFLs(int[] iArr, int[] iArr2, int i2) {
        int iLeftRotate = iArr[1] ^ leftRotate(iArr[0] & iArr2[i2], 1);
        iArr[1] = iLeftRotate;
        iArr[0] = (iLeftRotate | iArr2[i2 + 1]) ^ iArr[0];
        int i3 = iArr[2];
        int i4 = iArr2[i2 + 3];
        int i5 = iArr[3];
        int i6 = i3 ^ (i4 | i5);
        iArr[2] = i6;
        iArr[3] = leftRotate(iArr2[i2 + 2] & i6, 1) ^ i5;
    }

    private static void decroldq(int i2, int[] iArr, int i3, int[] iArr2, int i4) {
        int i5 = i4 + 2;
        int i6 = i3 + 1;
        int i7 = 32 - i2;
        iArr2[i5] = (iArr[i3] << i2) | (iArr[i6] >>> i7);
        int i8 = i4 + 3;
        int i9 = i3 + 2;
        iArr2[i8] = (iArr[i6] << i2) | (iArr[i9] >>> i7);
        int i10 = i3 + 3;
        iArr2[i4] = (iArr[i9] << i2) | (iArr[i10] >>> i7);
        int i11 = i4 + 1;
        iArr2[i11] = (iArr[i10] << i2) | (iArr[i3] >>> i7);
        iArr[i3] = iArr2[i5];
        iArr[i6] = iArr2[i8];
        iArr[i9] = iArr2[i4];
        iArr[i10] = iArr2[i11];
    }

    private static void decroldqo32(int i2, int[] iArr, int i3, int[] iArr2, int i4) {
        int i5 = i4 + 2;
        int i6 = i3 + 1;
        int i7 = i2 - 32;
        int i8 = i3 + 2;
        int i9 = 64 - i2;
        iArr2[i5] = (iArr[i6] << i7) | (iArr[i8] >>> i9);
        int i10 = i4 + 3;
        int i11 = i3 + 3;
        iArr2[i10] = (iArr[i8] << i7) | (iArr[i11] >>> i9);
        iArr2[i4] = (iArr[i11] << i7) | (iArr[i3] >>> i9);
        int i12 = i4 + 1;
        iArr2[i12] = (iArr[i6] >>> i9) | (iArr[i3] << i7);
        iArr[i3] = iArr2[i5];
        iArr[i6] = iArr2[i10];
        iArr[i8] = iArr2[i4];
        iArr[i11] = iArr2[i12];
    }

    private void int2bytes(int i2, byte[] bArr, int i3) {
        for (int i4 = 0; i4 < 4; i4++) {
            bArr[(3 - i4) + i3] = (byte) i2;
            i2 >>>= 8;
        }
    }

    private byte lRot8(byte b2, int i2) {
        return (byte) (((b2 & 255) >>> (8 - i2)) | (b2 << i2));
    }

    private static int leftRotate(int i2, int i3) {
        return (i2 << i3) + (i2 >>> (32 - i3));
    }

    private int processBlock128(byte[] bArr, int i2, byte[] bArr2, int i3) {
        for (int i4 = 0; i4 < 4; i4++) {
            this.state[i4] = bytes2int(bArr, (i4 * 4) + i2);
            int[] iArr = this.state;
            iArr[i4] = iArr[i4] ^ this.kw[i4];
        }
        camelliaF2(this.state, this.subkey, 0);
        camelliaF2(this.state, this.subkey, 4);
        camelliaF2(this.state, this.subkey, 8);
        camelliaFLs(this.state, this.ke, 0);
        camelliaF2(this.state, this.subkey, 12);
        camelliaF2(this.state, this.subkey, 16);
        camelliaF2(this.state, this.subkey, 20);
        camelliaFLs(this.state, this.ke, 4);
        camelliaF2(this.state, this.subkey, 24);
        camelliaF2(this.state, this.subkey, 28);
        camelliaF2(this.state, this.subkey, 32);
        int[] iArr2 = this.state;
        int i5 = iArr2[2];
        int[] iArr3 = this.kw;
        int i6 = iArr3[4] ^ i5;
        iArr2[2] = i6;
        iArr2[3] = iArr2[3] ^ iArr3[5];
        iArr2[0] = iArr2[0] ^ iArr3[6];
        iArr2[1] = iArr3[7] ^ iArr2[1];
        int2bytes(i6, bArr2, i3);
        int2bytes(this.state[3], bArr2, i3 + 4);
        int2bytes(this.state[0], bArr2, i3 + 8);
        int2bytes(this.state[1], bArr2, i3 + 12);
        return 16;
    }

    private int processBlock192or256(byte[] bArr, int i2, byte[] bArr2, int i3) {
        for (int i4 = 0; i4 < 4; i4++) {
            this.state[i4] = bytes2int(bArr, (i4 * 4) + i2);
            int[] iArr = this.state;
            iArr[i4] = iArr[i4] ^ this.kw[i4];
        }
        camelliaF2(this.state, this.subkey, 0);
        camelliaF2(this.state, this.subkey, 4);
        camelliaF2(this.state, this.subkey, 8);
        camelliaFLs(this.state, this.ke, 0);
        camelliaF2(this.state, this.subkey, 12);
        camelliaF2(this.state, this.subkey, 16);
        camelliaF2(this.state, this.subkey, 20);
        camelliaFLs(this.state, this.ke, 4);
        camelliaF2(this.state, this.subkey, 24);
        camelliaF2(this.state, this.subkey, 28);
        camelliaF2(this.state, this.subkey, 32);
        camelliaFLs(this.state, this.ke, 8);
        camelliaF2(this.state, this.subkey, 36);
        camelliaF2(this.state, this.subkey, 40);
        camelliaF2(this.state, this.subkey, 44);
        int[] iArr2 = this.state;
        int i5 = iArr2[2];
        int[] iArr3 = this.kw;
        int i6 = i5 ^ iArr3[4];
        iArr2[2] = i6;
        iArr2[3] = iArr2[3] ^ iArr3[5];
        iArr2[0] = iArr2[0] ^ iArr3[6];
        iArr2[1] = iArr3[7] ^ iArr2[1];
        int2bytes(i6, bArr2, i3);
        int2bytes(this.state[3], bArr2, i3 + 4);
        int2bytes(this.state[0], bArr2, i3 + 8);
        int2bytes(this.state[1], bArr2, i3 + 12);
        return 16;
    }

    private static int rightRotate(int i2, int i3) {
        return (i2 >>> i3) + (i2 << (32 - i3));
    }

    private static void roldq(int i2, int[] iArr, int i3, int[] iArr2, int i4) {
        int i5 = i3 + 1;
        int i6 = 32 - i2;
        iArr2[i4] = (iArr[i3] << i2) | (iArr[i5] >>> i6);
        int i7 = i4 + 1;
        int i8 = i3 + 2;
        iArr2[i7] = (iArr[i5] << i2) | (iArr[i8] >>> i6);
        int i9 = i4 + 2;
        int i10 = i3 + 3;
        iArr2[i9] = (iArr[i8] << i2) | (iArr[i10] >>> i6);
        int i11 = i4 + 3;
        iArr2[i11] = (iArr[i10] << i2) | (iArr[i3] >>> i6);
        iArr[i3] = iArr2[i4];
        iArr[i5] = iArr2[i7];
        iArr[i8] = iArr2[i9];
        iArr[i10] = iArr2[i11];
    }

    private static void roldqo32(int i2, int[] iArr, int i3, int[] iArr2, int i4) {
        int i5 = i3 + 1;
        int i6 = i2 - 32;
        int i7 = i3 + 2;
        int i8 = 64 - i2;
        iArr2[i4] = (iArr[i5] << i6) | (iArr[i7] >>> i8);
        int i9 = i4 + 1;
        int i10 = i3 + 3;
        iArr2[i9] = (iArr[i7] << i6) | (iArr[i10] >>> i8);
        int i11 = i4 + 2;
        iArr2[i11] = (iArr[i10] << i6) | (iArr[i3] >>> i8);
        int i12 = i4 + 3;
        iArr2[i12] = (iArr[i5] >>> i8) | (iArr[i3] << i6);
        iArr[i3] = iArr2[i4];
        iArr[i5] = iArr2[i9];
        iArr[i7] = iArr2[i11];
        iArr[i10] = iArr2[i12];
    }

    private int sbox2(int i2) {
        return lRot8(SBOX1[i2], 1) & 255;
    }

    private int sbox3(int i2) {
        return lRot8(SBOX1[i2], 7) & 255;
    }

    private int sbox4(int i2) {
        return SBOX1[lRot8((byte) i2, 1) & 255] & 255;
    }

    private void setKey(boolean z2, byte[] bArr) {
        int[] iArr = new int[8];
        int[] iArr2 = new int[4];
        int[] iArr3 = new int[4];
        int[] iArr4 = new int[4];
        int length = bArr.length;
        if (length == 16) {
            this._keyis128 = true;
            iArr[0] = bytes2int(bArr, 0);
            iArr[1] = bytes2int(bArr, 4);
            iArr[2] = bytes2int(bArr, 8);
            iArr[3] = bytes2int(bArr, 12);
            iArr[7] = 0;
            iArr[6] = 0;
            iArr[5] = 0;
            iArr[4] = 0;
        } else if (length == 24) {
            iArr[0] = bytes2int(bArr, 0);
            iArr[1] = bytes2int(bArr, 4);
            iArr[2] = bytes2int(bArr, 8);
            iArr[3] = bytes2int(bArr, 12);
            iArr[4] = bytes2int(bArr, 16);
            int iBytes2int = bytes2int(bArr, 20);
            iArr[5] = iBytes2int;
            iArr[6] = iArr[4] ^ (-1);
            iArr[7] = iBytes2int ^ (-1);
            this._keyis128 = false;
        } else {
            if (length != 32) {
                throw new IllegalArgumentException("key sizes are only 16/24/32 bytes.");
            }
            iArr[0] = bytes2int(bArr, 0);
            iArr[1] = bytes2int(bArr, 4);
            iArr[2] = bytes2int(bArr, 8);
            iArr[3] = bytes2int(bArr, 12);
            iArr[4] = bytes2int(bArr, 16);
            iArr[5] = bytes2int(bArr, 20);
            iArr[6] = bytes2int(bArr, 24);
            iArr[7] = bytes2int(bArr, 28);
            this._keyis128 = false;
        }
        for (int i2 = 0; i2 < 4; i2++) {
            iArr2[i2] = iArr[i2] ^ iArr[i2 + 4];
        }
        camelliaF2(iArr2, SIGMA, 0);
        for (int i3 = 0; i3 < 4; i3++) {
            iArr2[i3] = iArr2[i3] ^ iArr[i3];
        }
        camelliaF2(iArr2, SIGMA, 4);
        if (this._keyis128) {
            if (z2) {
                int[] iArr5 = this.kw;
                iArr5[0] = iArr[0];
                iArr5[1] = iArr[1];
                iArr5[2] = iArr[2];
                iArr5[3] = iArr[3];
                roldq(15, iArr, 0, this.subkey, 4);
                roldq(30, iArr, 0, this.subkey, 12);
                roldq(15, iArr, 0, iArr4, 0);
                int[] iArr6 = this.subkey;
                iArr6[18] = iArr4[2];
                iArr6[19] = iArr4[3];
                roldq(17, iArr, 0, this.ke, 4);
                roldq(17, iArr, 0, this.subkey, 24);
                roldq(17, iArr, 0, this.subkey, 32);
                int[] iArr7 = this.subkey;
                iArr7[0] = iArr2[0];
                iArr7[1] = iArr2[1];
                iArr7[2] = iArr2[2];
                iArr7[3] = iArr2[3];
                roldq(15, iArr2, 0, iArr7, 8);
                roldq(15, iArr2, 0, this.ke, 0);
                roldq(15, iArr2, 0, iArr4, 0);
                int[] iArr8 = this.subkey;
                iArr8[16] = iArr4[0];
                iArr8[17] = iArr4[1];
                roldq(15, iArr2, 0, iArr8, 20);
                roldqo32(34, iArr2, 0, this.subkey, 28);
                roldq(17, iArr2, 0, this.kw, 4);
                return;
            }
            int[] iArr9 = this.kw;
            iArr9[4] = iArr[0];
            iArr9[5] = iArr[1];
            iArr9[6] = iArr[2];
            iArr9[7] = iArr[3];
            decroldq(15, iArr, 0, this.subkey, 28);
            decroldq(30, iArr, 0, this.subkey, 20);
            decroldq(15, iArr, 0, iArr4, 0);
            int[] iArr10 = this.subkey;
            iArr10[16] = iArr4[0];
            iArr10[17] = iArr4[1];
            decroldq(17, iArr, 0, this.ke, 0);
            decroldq(17, iArr, 0, this.subkey, 8);
            decroldq(17, iArr, 0, this.subkey, 0);
            int[] iArr11 = this.subkey;
            iArr11[34] = iArr2[0];
            iArr11[35] = iArr2[1];
            iArr11[32] = iArr2[2];
            iArr11[33] = iArr2[3];
            decroldq(15, iArr2, 0, iArr11, 24);
            decroldq(15, iArr2, 0, this.ke, 4);
            decroldq(15, iArr2, 0, iArr4, 0);
            int[] iArr12 = this.subkey;
            iArr12[18] = iArr4[2];
            iArr12[19] = iArr4[3];
            decroldq(15, iArr2, 0, iArr12, 12);
            decroldqo32(34, iArr2, 0, this.subkey, 4);
            roldq(17, iArr2, 0, this.kw, 0);
            return;
        }
        for (int i4 = 0; i4 < 4; i4++) {
            iArr3[i4] = iArr2[i4] ^ iArr[i4 + 4];
        }
        camelliaF2(iArr3, SIGMA, 8);
        if (z2) {
            int[] iArr13 = this.kw;
            iArr13[0] = iArr[0];
            iArr13[1] = iArr[1];
            iArr13[2] = iArr[2];
            iArr13[3] = iArr[3];
            roldqo32(45, iArr, 0, this.subkey, 16);
            roldq(15, iArr, 0, this.ke, 4);
            roldq(17, iArr, 0, this.subkey, 32);
            roldqo32(34, iArr, 0, this.subkey, 44);
            roldq(15, iArr, 4, this.subkey, 4);
            roldq(15, iArr, 4, this.ke, 0);
            roldq(30, iArr, 4, this.subkey, 24);
            roldqo32(34, iArr, 4, this.subkey, 36);
            roldq(15, iArr2, 0, this.subkey, 8);
            roldq(30, iArr2, 0, this.subkey, 20);
            int[] iArr14 = this.ke;
            iArr14[8] = iArr2[1];
            iArr14[9] = iArr2[2];
            iArr14[10] = iArr2[3];
            iArr14[11] = iArr2[0];
            roldqo32(49, iArr2, 0, this.subkey, 40);
            int[] iArr15 = this.subkey;
            iArr15[0] = iArr3[0];
            iArr15[1] = iArr3[1];
            iArr15[2] = iArr3[2];
            iArr15[3] = iArr3[3];
            roldq(30, iArr3, 0, iArr15, 12);
            roldq(30, iArr3, 0, this.subkey, 28);
            roldqo32(51, iArr3, 0, this.kw, 4);
            return;
        }
        int[] iArr16 = this.kw;
        iArr16[4] = iArr[0];
        iArr16[5] = iArr[1];
        iArr16[6] = iArr[2];
        iArr16[7] = iArr[3];
        decroldqo32(45, iArr, 0, this.subkey, 28);
        decroldq(15, iArr, 0, this.ke, 4);
        decroldq(17, iArr, 0, this.subkey, 12);
        decroldqo32(34, iArr, 0, this.subkey, 0);
        decroldq(15, iArr, 4, this.subkey, 40);
        decroldq(15, iArr, 4, this.ke, 8);
        decroldq(30, iArr, 4, this.subkey, 20);
        decroldqo32(34, iArr, 4, this.subkey, 8);
        decroldq(15, iArr2, 0, this.subkey, 36);
        decroldq(30, iArr2, 0, this.subkey, 24);
        int[] iArr17 = this.ke;
        iArr17[2] = iArr2[1];
        iArr17[3] = iArr2[2];
        iArr17[0] = iArr2[3];
        iArr17[1] = iArr2[0];
        decroldqo32(49, iArr2, 0, this.subkey, 4);
        int[] iArr18 = this.subkey;
        iArr18[46] = iArr3[0];
        iArr18[47] = iArr3[1];
        iArr18[44] = iArr3[2];
        iArr18[45] = iArr3[3];
        decroldq(30, iArr3, 0, iArr18, 32);
        decroldq(30, iArr3, 0, this.subkey, 16);
        roldqo32(51, iArr3, 0, this.kw, 0);
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Camellia";
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void init(boolean z2, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("only simple KeyParameter expected.");
        }
        setKey(z2, ((KeyParameter) cipherParameters).getKey());
        this.initialized = true;
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (!this.initialized) {
            throw new IllegalStateException("Camellia is not initialized");
        }
        if (i2 + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + 16 <= bArr2.length) {
            return this._keyis128 ? processBlock128(bArr, i2, bArr2, i3) : processBlock192or256(bArr, i2, bArr2, i3);
        }
        throw new OutputLengthException("output buffer too short");
    }

    @Override // org.spongycastle.crypto.BlockCipher
    public void reset() {
    }
}
