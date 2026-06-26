package org.spongycastle.crypto.modes.gcm;

import org.spongycastle.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class BasicGCMExponentiator implements GCMExponentiator {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private int[] f7374x;

    @Override // org.spongycastle.crypto.modes.gcm.GCMExponentiator
    public void exponentiateX(long j2, byte[] bArr) {
        int[] iArrOneAsInts = GCMUtil.oneAsInts();
        if (j2 > 0) {
            int[] iArrClone = Arrays.clone(this.f7374x);
            do {
                if ((1 & j2) != 0) {
                    GCMUtil.multiply(iArrOneAsInts, iArrClone);
                }
                GCMUtil.multiply(iArrClone, iArrClone);
                j2 >>>= 1;
            } while (j2 > 0);
        }
        GCMUtil.asBytes(iArrOneAsInts, bArr);
    }

    @Override // org.spongycastle.crypto.modes.gcm.GCMExponentiator
    public void init(byte[] bArr) {
        this.f7374x = GCMUtil.asInts(bArr);
    }
}
