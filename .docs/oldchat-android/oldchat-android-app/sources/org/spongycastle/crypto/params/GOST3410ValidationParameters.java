package org.spongycastle.crypto.params;

/* JADX INFO: loaded from: classes.dex */
public class GOST3410ValidationParameters {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f7416c;
    private long cL;
    private int x0;
    private long x0L;

    public GOST3410ValidationParameters(int i2, int i3) {
        this.x0 = i2;
        this.f7416c = i3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410ValidationParameters)) {
            return false;
        }
        GOST3410ValidationParameters gOST3410ValidationParameters = (GOST3410ValidationParameters) obj;
        return gOST3410ValidationParameters.f7416c == this.f7416c && gOST3410ValidationParameters.x0 == this.x0 && gOST3410ValidationParameters.cL == this.cL && gOST3410ValidationParameters.x0L == this.x0L;
    }

    public int getC() {
        return this.f7416c;
    }

    public long getCL() {
        return this.cL;
    }

    public int getX0() {
        return this.x0;
    }

    public long getX0L() {
        return this.x0L;
    }

    public int hashCode() {
        int i2 = this.x0 ^ this.f7416c;
        long j2 = this.x0L;
        int i3 = (i2 ^ ((int) j2)) ^ ((int) (j2 >> 32));
        long j3 = this.cL;
        return (i3 ^ ((int) j3)) ^ ((int) (j3 >> 32));
    }

    public GOST3410ValidationParameters(long j2, long j3) {
        this.x0L = j2;
        this.cL = j3;
    }
}
