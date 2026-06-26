package k;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public abstract class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f5743a;

    public interface a {
        int a(int i2, int i3);
    }

    public static class b implements a {
        @Override // k.r.a
        public int a(int i2, int i3) {
            return i2 & (-8388609);
        }
    }

    public static class c implements a {
        @Override // k.r.a
        public int a(int i2, int i3) {
            return AbstractC0310t.a(i2, i3);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 17) {
            f5743a = new c();
        } else {
            f5743a = new b();
        }
    }

    public static int a(int i2, int i3) {
        return f5743a.a(i2, i3);
    }
}
