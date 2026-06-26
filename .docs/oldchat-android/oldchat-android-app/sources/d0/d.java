package d0;

import H.p;

/* JADX INFO: loaded from: classes.dex */
public final class d extends p {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f4925c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f4926d;

    public d(float f2, float f3, float f4) {
        this(f2, f3, f4, 1);
    }

    public boolean f(float f2, float f3, float f4) {
        if (Math.abs(f3 - d()) > f2 || Math.abs(f4 - c()) > f2) {
            return false;
        }
        float fAbs = Math.abs(f2 - this.f4925c);
        return fAbs <= 1.0f || fAbs <= this.f4925c;
    }

    public d g(float f2, float f3, float f4) {
        int i2 = this.f4926d;
        int i3 = i2 + 1;
        float fC = (i2 * c()) + f3;
        float f5 = i3;
        return new d(fC / f5, ((this.f4926d * d()) + f2) / f5, ((this.f4926d * this.f4925c) + f4) / f5, i3);
    }

    public int h() {
        return this.f4926d;
    }

    public float i() {
        return this.f4925c;
    }

    public d(float f2, float f3, float f4, int i2) {
        super(f2, f3);
        this.f4925c = f4;
        this.f4926d = i2;
    }
}
