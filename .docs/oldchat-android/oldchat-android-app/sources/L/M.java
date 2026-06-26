package l;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class M {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f5747b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f5748a;

    public interface a {
    }

    public static class b extends d {
    }

    public static class c extends d {
    }

    public static class d implements a {
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 19) {
            f5747b = new c();
        } else if (i2 >= 16) {
            f5747b = new b();
        } else {
            f5747b = new d();
        }
    }

    public M(Object obj) {
        this.f5748a = obj;
    }

    public Object a() {
        return this.f5748a;
    }
}
