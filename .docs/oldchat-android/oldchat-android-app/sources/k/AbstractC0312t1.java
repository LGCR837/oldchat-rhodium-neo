package k;

import android.os.Build;
import android.view.ViewConfiguration;

/* JADX INFO: renamed from: k.t1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0312t1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f5744a;

    /* JADX INFO: renamed from: k.t1$a */
    public static class a implements d {
        @Override // k.AbstractC0312t1.d
        public boolean a(ViewConfiguration viewConfiguration) {
            return true;
        }
    }

    /* JADX INFO: renamed from: k.t1$b */
    public static class b extends a {
        @Override // k.AbstractC0312t1.a, k.AbstractC0312t1.d
        public boolean a(ViewConfiguration viewConfiguration) {
            return false;
        }
    }

    /* JADX INFO: renamed from: k.t1$c */
    public static class c extends b {
        @Override // k.AbstractC0312t1.b, k.AbstractC0312t1.a, k.AbstractC0312t1.d
        public boolean a(ViewConfiguration viewConfiguration) {
            return AbstractC0318v1.a(viewConfiguration);
        }
    }

    /* JADX INFO: renamed from: k.t1$d */
    public interface d {
        boolean a(ViewConfiguration viewConfiguration);
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 14) {
            f5744a = new c();
        } else if (i2 >= 11) {
            f5744a = new b();
        } else {
            f5744a = new a();
        }
    }

    public static boolean a(ViewConfiguration viewConfiguration) {
        return f5744a.a(viewConfiguration);
    }
}
