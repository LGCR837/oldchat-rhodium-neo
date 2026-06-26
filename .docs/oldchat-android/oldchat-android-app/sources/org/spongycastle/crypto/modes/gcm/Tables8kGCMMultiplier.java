package org.spongycastle.crypto.modes.gcm;

import java.lang.reflect.Array;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class Tables8kGCMMultiplier implements GCMMultiplier {

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    private byte[] f7378H;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    private int[][][] f7379M;

    @Override // org.spongycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        int i2 = 0;
        if (this.f7379M == null) {
            this.f7379M = (int[][][]) Array.newInstance((Class<?>) Integer.TYPE, 32, 16, 4);
        } else if (Arrays.areEqual(this.f7378H, bArr)) {
            return;
        }
        this.f7378H = Arrays.clone(bArr);
        GCMUtil.asInts(bArr, this.f7379M[1][8]);
        for (int i3 = 4; i3 >= 1; i3 >>= 1) {
            int[][] iArr = this.f7379M[1];
            GCMUtil.multiplyP(iArr[i3 + i3], iArr[i3]);
        }
        int[][][] iArr2 = this.f7379M;
        GCMUtil.multiplyP(iArr2[1][1], iArr2[0][8]);
        for (int i4 = 4; i4 >= 1; i4 >>= 1) {
            int[][] iArr3 = this.f7379M[0];
            GCMUtil.multiplyP(iArr3[i4 + i4], iArr3[i4]);
        }
        while (true) {
            for (int i5 = 2; i5 < 16; i5 += i5) {
                for (int i6 = 1; i6 < i5; i6++) {
                    int[][] iArr4 = this.f7379M[i2];
                    GCMUtil.xor(iArr4[i5], iArr4[i6], iArr4[i5 + i6]);
                }
            }
            int i7 = i2 + 1;
            if (i7 == 32) {
                return;
            }
            if (i7 > 1) {
                for (int i8 = 8; i8 > 0; i8 >>= 1) {
                    int[][][] iArr5 = this.f7379M;
                    GCMUtil.multiplyP8(iArr5[i2 - 1][i8], iArr5[i7][i8]);
                }
            }
            i2 = i7;
        }
    }

    @Override // org.spongycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        int[] iArr = new int[4];
        for (int i2 = 15; i2 >= 0; i2--) {
            int[][][] iArr2 = this.f7379M;
            int i3 = i2 + i2;
            int[][] iArr3 = iArr2[i3];
            byte b2 = bArr[i2];
            int[] iArr4 = iArr3[b2 & 15];
            int i4 = iArr[0] ^ iArr4[0];
            iArr[0] = i4;
            int i5 = iArr[1] ^ iArr4[1];
            iArr[1] = i5;
            int i6 = iArr[2] ^ iArr4[2];
            iArr[2] = i6;
            int i7 = iArr[3] ^ iArr4[3];
            iArr[3] = i7;
            int[] iArr5 = iArr2[i3 + 1][(b2 & 240) >>> 4];
            iArr[0] = iArr5[0] ^ i4;
            iArr[1] = iArr5[1] ^ i5;
            iArr[2] = iArr5[2] ^ i6;
            iArr[3] = iArr5[3] ^ i7;
        }
        Pack.intToBigEndian(iArr, bArr, 0);
    }
}
