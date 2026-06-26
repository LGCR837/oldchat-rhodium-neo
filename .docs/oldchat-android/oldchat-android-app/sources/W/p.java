package W;

/* JADX INFO: loaded from: classes.dex */
public final class p extends q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f591b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f592c;

    public p(int i2, int i3, int i4) throws H.g {
        super(i2);
        if (i3 < 0 || i3 > 10 || i4 < 0 || i4 > 10) {
            throw H.g.a();
        }
        this.f591b = i3;
        this.f592c = i4;
    }

    public int b() {
        return this.f591b;
    }

    public int c() {
        return this.f592c;
    }

    public boolean d() {
        return this.f591b == 10;
    }

    public boolean e() {
        return this.f592c == 10;
    }
}
