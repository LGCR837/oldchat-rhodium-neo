package org.spongycastle.crypto.modes.gcm;

import java.lang.reflect.Array;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

/* JADX INFO: loaded from: classes.dex */
public class Tables64kGCMMultiplier implements GCMMultiplier {

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    private byte[] f7376H;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    private int[][][] f7377M;

    @Override // org.spongycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        int i2 = 0;
        if (this.f7377M == null) {
            this.f7377M = (int[][][]) Array.newInstance((Class<?>) Integer.TYPE, 16, 256, 4);
        } else if (Arrays.areEqual(this.f7376H, bArr)) {
            return;
        }
        this.f7376H = Arrays.clone(bArr);
        GCMUtil.asInts(bArr, this.f7377M[0][128]);
        for (int i3 = 64; i3 >= 1; i3 >>= 1) {
            int[][] iArr = this.f7377M[0];
            GCMUtil.multiplyP(iArr[i3 + i3], iArr[i3]);
        }
        while (true) {
            for (int i4 = 2; i4 < 256; i4 += i4) {
                for (int i5 = 1; i5 < i4; i5++) {
                    int[][] iArr2 = this.f7377M[i2];
                    GCMUtil.xor(iArr2[i4], iArr2[i5], iArr2[i4 + i5]);
                }
            }
            int i6 = i2 + 1;
            if (i6 == 16) {
                return;
            }
            for (int i7 = 128; i7 > 0; i7 >>= 1) {
                int[][][] iArr3 = this.f7377M;
                GCMUtil.multiplyP8(iArr3[i2][i7], iArr3[i6][i7]);
            }
            i2 = i6;
        }
    }

    @Override // org.spongycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        int[] iArr = new int[4];
        for (int i2 = 15; i2 >= 0; i2--) {
            int[] iArr2 = this.f7377M[i2][bArr[i2] & 255];
            iArr[0] = iArr[0] ^ iArr2[0];
            iArr[1] = iArr[1] ^ iArr2[1];
            iArr[2] = iArr[2] ^ iArr2[2];
            iArr[3] = iArr2[3] ^ iArr[3];
        }
        Pack.intToBigEndian(iArr, bArr, 0);
    }
}
