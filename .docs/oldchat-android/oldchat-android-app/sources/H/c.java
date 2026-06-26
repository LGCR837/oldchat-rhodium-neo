package H;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f257a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public L.b f258b;

    public c(b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Binarizer must be non-null.");
        }
        this.f257a = bVar;
    }

    public L.b a() {
        if (this.f258b == null) {
            this.f258b = this.f257a.b();
        }
        return this.f258b;
    }

    public L.a b(int i2, L.a aVar) {
        return this.f257a.c(i2, aVar);
    }

    public int c() {
        return this.f257a.d();
    }

    public int d() {
        return this.f257a.f();
    }

    public boolean e() {
        return this.f257a.e().e();
    }

    public c f() {
        return new c(this.f257a.a(this.f257a.e().f()));
    }

    public String toString() {
        try {
            return a().toString();
        } catch (j unused) {
            return "";
        }
    }
}
