package Y;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f631a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f632b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f633c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f634d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f635e = -1;

    public d(int i2, int i3, int i4, int i5) {
        this.f631a = i2;
        this.f632b = i3;
        this.f633c = i4;
        this.f634d = i5;
    }

    public int a() {
        return this.f633c;
    }

    public int b() {
        return this.f632b;
    }

    public int c() {
        return this.f635e;
    }

    public int d() {
        return this.f631a;
    }

    public int e() {
        return this.f634d;
    }

    public int f() {
        return this.f632b - this.f631a;
    }

    public boolean g() {
        return h(this.f635e);
    }

    public boolean h(int i2) {
        return i2 != -1 && this.f633c == (i2 % 3) * 3;
    }

    public void i(int i2) {
        this.f635e = i2;
    }

    public void j() {
        this.f635e = ((this.f634d / 30) * 3) + (this.f633c / 3);
    }

    public String toString() {
        return this.f635e + "|" + this.f634d;
    }
}
