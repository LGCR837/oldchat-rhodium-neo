package U;

import H.p;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f544a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f545b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final p[] f546c;

    public c(int i2, int[] iArr, int i3, int i4, int i5) {
        this.f544a = i2;
        this.f545b = iArr;
        float f2 = i3;
        float f3 = i5;
        this.f546c = new p[]{new p(f2, f3), new p(i4, f3)};
    }

    public p[] a() {
        return this.f546c;
    }

    public int[] b() {
        return this.f545b;
    }

    public int c() {
        return this.f544a;
    }

    public boolean equals(Object obj) {
        return (obj instanceof c) && this.f544a == ((c) obj).f544a;
    }

    public int hashCode() {
        return this.f544a;
    }
}
