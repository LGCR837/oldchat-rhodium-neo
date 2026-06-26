package H;

/* JADX INFO: loaded from: classes.dex */
public abstract class m extends Exception {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f297a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final StackTraceElement[] f298b;

    static {
        f297a = System.getProperty("surefire.test.class.path") != null;
        f298b = new StackTraceElement[0];
    }

    public m() {
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable fillInStackTrace() {
        return null;
    }

    public m(Throwable th) {
        super(th);
    }
}
