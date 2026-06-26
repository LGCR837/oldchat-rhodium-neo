package W;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f581a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f582b = a.NUMERIC;

    public enum a {
        NUMERIC,
        ALPHA,
        ISO_IEC_646
    }

    public int a() {
        return this.f581a;
    }

    public void b(int i2) {
        this.f581a += i2;
    }

    public boolean c() {
        return this.f582b == a.ALPHA;
    }

    public boolean d() {
        return this.f582b == a.ISO_IEC_646;
    }

    public void e() {
        this.f582b = a.ALPHA;
    }

    public void f() {
        this.f582b = a.ISO_IEC_646;
    }

    public void g() {
        this.f582b = a.NUMERIC;
    }

    public void h(int i2) {
        this.f581a = i2;
    }
}
