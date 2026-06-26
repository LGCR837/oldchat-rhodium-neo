package L;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f404a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f405b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f406c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f407d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f408e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f409f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f410g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f411h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f412i;

    public k(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        this.f404a = f2;
        this.f405b = f5;
        this.f406c = f8;
        this.f407d = f3;
        this.f408e = f6;
        this.f409f = f9;
        this.f410g = f4;
        this.f411h = f7;
        this.f412i = f10;
    }

    public static k b(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17) {
        return d(f10, f11, f12, f13, f14, f15, f16, f17).e(c(f2, f3, f4, f5, f6, f7, f8, f9));
    }

    public static k c(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        return d(f2, f3, f4, f5, f6, f7, f8, f9).a();
    }

    public static k d(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        float f10 = ((f2 - f4) + f6) - f8;
        float f11 = ((f3 - f5) + f7) - f9;
        if (f10 == 0.0f && f11 == 0.0f) {
            return new k(f4 - f2, f6 - f4, f2, f5 - f3, f7 - f5, f3, 0.0f, 0.0f, 1.0f);
        }
        float f12 = f4 - f6;
        float f13 = f8 - f6;
        float f14 = f5 - f7;
        float f15 = f9 - f7;
        float f16 = (f12 * f15) - (f13 * f14);
        float f17 = ((f15 * f10) - (f13 * f11)) / f16;
        float f18 = ((f12 * f11) - (f10 * f14)) / f16;
        return new k((f17 * f4) + (f4 - f2), (f18 * f8) + (f8 - f2), f2, (f5 - f3) + (f17 * f5), (f9 - f3) + (f18 * f9), f3, f17, f18, 1.0f);
    }

    public k a() {
        float f2 = this.f408e;
        float f3 = this.f412i;
        float f4 = this.f409f;
        float f5 = this.f411h;
        float f6 = (f2 * f3) - (f4 * f5);
        float f7 = this.f410g;
        float f8 = this.f407d;
        float f9 = (f4 * f7) - (f8 * f3);
        float f10 = (f8 * f5) - (f2 * f7);
        float f11 = this.f406c;
        float f12 = this.f405b;
        float f13 = (f11 * f5) - (f12 * f3);
        float f14 = this.f404a;
        return new k(f6, f9, f10, f13, (f3 * f14) - (f11 * f7), (f7 * f12) - (f5 * f14), (f12 * f4) - (f11 * f2), (f11 * f8) - (f4 * f14), (f14 * f2) - (f12 * f8));
    }

    public k e(k kVar) {
        float f2 = this.f404a;
        float f3 = kVar.f404a;
        float f4 = this.f407d;
        float f5 = kVar.f405b;
        float f6 = this.f410g;
        float f7 = kVar.f406c;
        float f8 = (f2 * f3) + (f4 * f5) + (f6 * f7);
        float f9 = kVar.f407d;
        float f10 = kVar.f408e;
        float f11 = kVar.f409f;
        float f12 = (f2 * f9) + (f4 * f10) + (f6 * f11);
        float f13 = kVar.f410g;
        float f14 = kVar.f411h;
        float f15 = kVar.f412i;
        float f16 = (f2 * f13) + (f4 * f14) + (f6 * f15);
        float f17 = this.f405b;
        float f18 = this.f408e;
        float f19 = this.f411h;
        float f20 = (f17 * f3) + (f18 * f5) + (f19 * f7);
        float f21 = (f17 * f9) + (f18 * f10) + (f19 * f11);
        float f22 = (f19 * f15) + (f17 * f13) + (f18 * f14);
        float f23 = this.f406c;
        float f24 = this.f409f;
        float f25 = (f3 * f23) + (f5 * f24);
        float f26 = this.f412i;
        return new k(f8, f12, f16, f20, f21, f22, (f7 * f26) + f25, (f9 * f23) + (f10 * f24) + (f11 * f26), (f23 * f13) + (f24 * f14) + (f26 * f15));
    }

    public void f(float[] fArr) {
        int length = fArr.length;
        float f2 = this.f404a;
        float f3 = this.f405b;
        float f4 = this.f406c;
        float f5 = this.f407d;
        float f6 = this.f408e;
        float f7 = this.f409f;
        float f8 = this.f410g;
        float f9 = this.f411h;
        float f10 = this.f412i;
        for (int i2 = 0; i2 < length; i2 += 2) {
            float f11 = fArr[i2];
            int i3 = i2 + 1;
            float f12 = fArr[i3];
            float f13 = (f4 * f11) + (f7 * f12) + f10;
            fArr[i2] = (((f2 * f11) + (f5 * f12)) + f8) / f13;
            fArr[i3] = (((f11 * f3) + (f12 * f6)) + f9) / f13;
        }
    }
}
