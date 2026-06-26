package org.spongycastle.crypto.modes.gcm;

/* JADX INFO: loaded from: classes.dex */
public class BasicGCMMultiplier implements GCMMultiplier {

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    private int[] f7375H;

    @Override // org.spongycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        this.f7375H = GCMUtil.asInts(bArr);
    }

    @Override // org.spongycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        int[] iArrAsInts = GCMUtil.asInts(bArr);
        GCMUtil.multiply(iArrAsInts, this.f7375H);
        GCMUtil.asBytes(iArrAsInts, bArr);
    }
}
