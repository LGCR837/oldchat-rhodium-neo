package E;

import B.e;
import java.lang.reflect.AccessibleObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f193a;

    static {
        f193a = e.c() < 9 ? new a() : new c();
    }

    public static b a() {
        return f193a;
    }

    public abstract void b(AccessibleObject accessibleObject);
}
