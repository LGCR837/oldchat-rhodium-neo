package d0;

import H.p;

/* JADX INFO: loaded from: classes.dex */
public final class a extends p {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f4915c;

    public a(float f2, float f3, float f4) {
        super(f2, f3);
        this.f4915c = f4;
    }

    public boolean f(float f2, float f3, float f4) {
        if (Math.abs(f3 - d()) > f2 || Math.abs(f4 - c()) > f2) {
            return false;
        }
        float fAbs = Math.abs(f2 - this.f4915c);
        return fAbs <= 1.0f || fAbs <= this.f4915c;
    }

    public a g(float f2, float f3, float f4) {
        return new a((c() + f3) / 2.0f, (d() + f2) / 2.0f, (this.f4915c + f4) / 2.0f);
    }
}
