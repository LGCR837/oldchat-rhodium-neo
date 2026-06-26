package W;

/* JADX INFO: loaded from: classes.dex */
public final class o extends q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f588b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f589c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f590d;

    public o(int i2, String str) {
        super(i2);
        this.f588b = str;
        this.f590d = false;
        this.f589c = 0;
    }

    public String b() {
        return this.f588b;
    }

    public int c() {
        return this.f589c;
    }

    public boolean d() {
        return this.f590d;
    }

    public o(int i2, String str, int i3) {
        super(i2);
        this.f590d = true;
        this.f589c = i3;
        this.f588b = str;
    }
}
