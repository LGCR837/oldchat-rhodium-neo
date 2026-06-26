package z;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class d {
    public static /* synthetic */ AssertionError a(String str, Throwable th) {
        try {
            return (AssertionError) AssertionError.class.getDeclaredConstructor(String.class, Throwable.class).newInstance(str, th);
        } catch (Exception unused) {
            return new AssertionError(str);
        }
    }
}
