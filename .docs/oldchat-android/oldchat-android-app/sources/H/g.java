package H;

/* JADX INFO: loaded from: classes.dex */
public final class g extends m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g f286c;

    static {
        g gVar = new g();
        f286c = gVar;
        gVar.setStackTrace(m.f298b);
    }

    public g() {
    }

    public static g a() {
        return m.f297a ? new g() : f286c;
    }

    public static g b(Throwable th) {
        return m.f297a ? new g(th) : f286c;
    }

    public g(Throwable th) {
        super(th);
    }
}
