package org.spongycastle.crypto.digests;

import java.lang.reflect.Array;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.engines.GOST28147Engine;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithSBox;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class GOST3411Digest implements ExtendedDigest, Memoable {
    private static final byte[] C2 = {0, -1, 0, -1, 0, -1, 0, -1, -1, 0, -1, 0, -1, 0, -1, 0, 0, -1, -1, 0, -1, 0, 0, -1, -1, 0, 0, 0, -1, -1, 0, -1};
    private static final int DIGEST_LENGTH = 32;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    private byte[][] f7268C;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    private byte[] f7269H;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    private byte[] f7270K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    private byte[] f7271L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    private byte[] f7272M;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    byte[] f7273S;
    private byte[] Sum;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    byte[] f7274U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    byte[] f7275V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    byte[] f7276W;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    byte[] f7277a;
    private long byteCount;
    private BlockCipher cipher;
    private byte[] sBox;
    short[] wS;
    short[] w_S;
    private byte[] xBuf;
    private int xBufOff;

    public GOST3411Digest() {
        this.f7269H = new byte[32];
        this.f7271L = new byte[32];
        this.f7272M = new byte[32];
        this.Sum = new byte[32];
        this.f7268C = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, 4, 32);
        this.xBuf = new byte[32];
        this.cipher = new GOST28147Engine();
        this.f7270K = new byte[32];
        this.f7277a = new byte[8];
        this.wS = new short[16];
        this.w_S = new short[16];
        this.f7273S = new byte[32];
        this.f7274U = new byte[32];
        this.f7275V = new byte[32];
        this.f7276W = new byte[32];
        byte[] sBox = GOST28147Engine.getSBox("D-A");
        this.sBox = sBox;
        this.cipher.init(true, new ParametersWithSBox(null, sBox));
        reset();
    }

    private byte[] A(byte[] bArr) {
        for (int i2 = 0; i2 < 8; i2++) {
            this.f7277a[i2] = (byte) (bArr[i2] ^ bArr[i2 + 8]);
        }
        System.arraycopy(bArr, 8, bArr, 0, 24);
        System.arraycopy(this.f7277a, 0, bArr, 24, 8);
        return bArr;
    }

    private void E(byte[] bArr, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        this.cipher.init(true, new KeyParameter(bArr));
        this.cipher.processBlock(bArr3, i3, bArr2, i2);
    }

    private byte[] P(byte[] bArr) {
        for (int i2 = 0; i2 < 8; i2++) {
            byte[] bArr2 = this.f7270K;
            int i3 = i2 * 4;
            bArr2[i3] = bArr[i2];
            bArr2[i3 + 1] = bArr[i2 + 8];
            bArr2[i3 + 2] = bArr[i2 + 16];
            bArr2[i3 + 3] = bArr[i2 + 24];
        }
        return this.f7270K;
    }

    private void cpyBytesToShort(byte[] bArr, short[] sArr) {
        for (int i2 = 0; i2 < bArr.length / 2; i2++) {
            int i3 = i2 * 2;
            sArr[i2] = (short) ((bArr[i3] & 255) | ((bArr[i3 + 1] << 8) & CipherSuite.DRAFT_TLS_DHE_RSA_WITH_AES_128_OCB));
        }
    }

    private void cpyShortToBytes(short[] sArr, byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length / 2; i2++) {
            int i3 = i2 * 2;
            short s2 = sArr[i2];
            bArr[i3 + 1] = (byte) (s2 >> 8);
            bArr[i3] = (byte) s2;
        }
    }

    private void finish() {
        Pack.longToLittleEndian(this.byteCount * 8, this.f7271L, 0);
        while (this.xBufOff != 0) {
            update((byte) 0);
        }
        processBlock(this.f7271L, 0);
        processBlock(this.Sum, 0);
    }

    private void fw(byte[] bArr) {
        cpyBytesToShort(bArr, this.wS);
        short[] sArr = this.w_S;
        short[] sArr2 = this.wS;
        sArr[15] = (short) (((((sArr2[0] ^ sArr2[1]) ^ sArr2[2]) ^ sArr2[3]) ^ sArr2[12]) ^ sArr2[15]);
        System.arraycopy(sArr2, 1, sArr, 0, 15);
        cpyShortToBytes(this.w_S, bArr);
    }

    private void sumByteArray(byte[] bArr) {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr2 = this.Sum;
            if (i2 == bArr2.length) {
                return;
            }
            int i4 = (bArr2[i2] & 255) + (bArr[i2] & 255) + i3;
            bArr2[i2] = (byte) i4;
            i3 = i4 >>> 8;
            i2++;
        }
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new GOST3411Digest(this);
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i2) {
        finish();
        byte[] bArr2 = this.f7269H;
        System.arraycopy(bArr2, 0, bArr, i2, bArr2.length);
        reset();
        return 32;
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "GOST3411";
    }

    @Override // org.spongycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 32;
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    public void processBlock(byte[] bArr, int i2) {
        System.arraycopy(bArr, i2, this.f7272M, 0, 32);
        System.arraycopy(this.f7269H, 0, this.f7274U, 0, 32);
        System.arraycopy(this.f7272M, 0, this.f7275V, 0, 32);
        for (int i3 = 0; i3 < 32; i3++) {
            this.f7276W[i3] = (byte) (this.f7274U[i3] ^ this.f7275V[i3]);
        }
        E(P(this.f7276W), this.f7273S, 0, this.f7269H, 0);
        for (int i4 = 1; i4 < 4; i4++) {
            byte[] bArrA = A(this.f7274U);
            for (int i5 = 0; i5 < 32; i5++) {
                this.f7274U[i5] = (byte) (bArrA[i5] ^ this.f7268C[i4][i5]);
            }
            this.f7275V = A(A(this.f7275V));
            for (int i6 = 0; i6 < 32; i6++) {
                this.f7276W[i6] = (byte) (this.f7274U[i6] ^ this.f7275V[i6]);
            }
            int i7 = i4 * 8;
            E(P(this.f7276W), this.f7273S, i7, this.f7269H, i7);
        }
        for (int i8 = 0; i8 < 12; i8++) {
            fw(this.f7273S);
        }
        for (int i9 = 0; i9 < 32; i9++) {
            byte[] bArr2 = this.f7273S;
            bArr2[i9] = (byte) (bArr2[i9] ^ this.f7272M[i9]);
        }
        fw(this.f7273S);
        for (int i10 = 0; i10 < 32; i10++) {
            byte[] bArr3 = this.f7273S;
            bArr3[i10] = (byte) (this.f7269H[i10] ^ bArr3[i10]);
        }
        for (int i11 = 0; i11 < 61; i11++) {
            fw(this.f7273S);
        }
        byte[] bArr4 = this.f7273S;
        byte[] bArr5 = this.f7269H;
        System.arraycopy(bArr4, 0, bArr5, 0, bArr5.length);
    }

    @Override // org.spongycastle.crypto.Digest
    public void reset() {
        this.byteCount = 0L;
        this.xBufOff = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f7269H;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = 0;
            i2++;
        }
        int i3 = 0;
        while (true) {
            byte[] bArr2 = this.f7271L;
            if (i3 >= bArr2.length) {
                break;
            }
            bArr2[i3] = 0;
            i3++;
        }
        int i4 = 0;
        while (true) {
            byte[] bArr3 = this.f7272M;
            if (i4 >= bArr3.length) {
                break;
            }
            bArr3[i4] = 0;
            i4++;
        }
        int i5 = 0;
        while (true) {
            byte[] bArr4 = this.f7268C[1];
            if (i5 >= bArr4.length) {
                break;
            }
            bArr4[i5] = 0;
            i5++;
        }
        int i6 = 0;
        while (true) {
            byte[] bArr5 = this.f7268C[3];
            if (i6 >= bArr5.length) {
                break;
            }
            bArr5[i6] = 0;
            i6++;
        }
        int i7 = 0;
        while (true) {
            byte[] bArr6 = this.Sum;
            if (i7 >= bArr6.length) {
                break;
            }
            bArr6[i7] = 0;
            i7++;
        }
        int i8 = 0;
        while (true) {
            byte[] bArr7 = this.xBuf;
            if (i8 >= bArr7.length) {
                byte[] bArr8 = C2;
                System.arraycopy(bArr8, 0, this.f7268C[2], 0, bArr8.length);
                return;
            } else {
                bArr7[i8] = 0;
                i8++;
            }
        }
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte b2) {
        byte[] bArr = this.xBuf;
        int i2 = this.xBufOff;
        int i3 = i2 + 1;
        this.xBufOff = i3;
        bArr[i2] = b2;
        if (i3 == bArr.length) {
            sumByteArray(bArr);
            processBlock(this.xBuf, 0);
            this.xBufOff = 0;
        }
        this.byteCount++;
    }

    @Override // org.spongycastle.crypto.Digest
    public void update(byte[] bArr, int i2, int i3) {
        while (this.xBufOff != 0 && i3 > 0) {
            update(bArr[i2]);
            i2++;
            i3--;
        }
        while (true) {
            byte[] bArr2 = this.xBuf;
            if (i3 <= bArr2.length) {
                break;
            }
            System.arraycopy(bArr, i2, bArr2, 0, bArr2.length);
            sumByteArray(this.xBuf);
            processBlock(this.xBuf, 0);
            byte[] bArr3 = this.xBuf;
            i2 += bArr3.length;
            i3 -= bArr3.length;
            this.byteCount += (long) bArr3.length;
        }
        while (i3 > 0) {
            update(bArr[i2]);
            i2++;
            i3--;
        }
    }

    public GOST3411Digest(byte[] bArr) {
        this.f7269H = new byte[32];
        this.f7271L = new byte[32];
        this.f7272M = new byte[32];
        this.Sum = new byte[32];
        this.f7268C = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, 4, 32);
        this.xBuf = new byte[32];
        this.cipher = new GOST28147Engine();
        this.f7270K = new byte[32];
        this.f7277a = new byte[8];
        this.wS = new short[16];
        this.w_S = new short[16];
        this.f7273S = new byte[32];
        this.f7274U = new byte[32];
        this.f7275V = new byte[32];
        this.f7276W = new byte[32];
        byte[] bArrClone = Arrays.clone(bArr);
        this.sBox = bArrClone;
        this.cipher.init(true, new ParametersWithSBox(null, bArrClone));
        reset();
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        GOST3411Digest gOST3411Digest = (GOST3411Digest) memoable;
        byte[] bArr = gOST3411Digest.sBox;
        this.sBox = bArr;
        this.cipher.init(true, new ParametersWithSBox(null, bArr));
        reset();
        byte[] bArr2 = gOST3411Digest.f7269H;
        System.arraycopy(bArr2, 0, this.f7269H, 0, bArr2.length);
        byte[] bArr3 = gOST3411Digest.f7271L;
        System.arraycopy(bArr3, 0, this.f7271L, 0, bArr3.length);
        byte[] bArr4 = gOST3411Digest.f7272M;
        System.arraycopy(bArr4, 0, this.f7272M, 0, bArr4.length);
        byte[] bArr5 = gOST3411Digest.Sum;
        System.arraycopy(bArr5, 0, this.Sum, 0, bArr5.length);
        byte[] bArr6 = gOST3411Digest.f7268C[1];
        System.arraycopy(bArr6, 0, this.f7268C[1], 0, bArr6.length);
        byte[] bArr7 = gOST3411Digest.f7268C[2];
        System.arraycopy(bArr7, 0, this.f7268C[2], 0, bArr7.length);
        byte[] bArr8 = gOST3411Digest.f7268C[3];
        System.arraycopy(bArr8, 0, this.f7268C[3], 0, bArr8.length);
        byte[] bArr9 = gOST3411Digest.xBuf;
        System.arraycopy(bArr9, 0, this.xBuf, 0, bArr9.length);
        this.xBufOff = gOST3411Digest.xBufOff;
        this.byteCount = gOST3411Digest.byteCount;
    }

    public GOST3411Digest(GOST3411Digest gOST3411Digest) {
        this.f7269H = new byte[32];
        this.f7271L = new byte[32];
        this.f7272M = new byte[32];
        this.Sum = new byte[32];
        this.f7268C = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, 4, 32);
        this.xBuf = new byte[32];
        this.cipher = new GOST28147Engine();
        this.f7270K = new byte[32];
        this.f7277a = new byte[8];
        this.wS = new short[16];
        this.w_S = new short[16];
        this.f7273S = new byte[32];
        this.f7274U = new byte[32];
        this.f7275V = new byte[32];
        this.f7276W = new byte[32];
        reset(gOST3411Digest);
    }
}
