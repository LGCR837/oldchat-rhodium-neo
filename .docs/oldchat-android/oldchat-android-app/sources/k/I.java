package k;

import android.os.Build;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public abstract class I {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f5670a;

    public interface a {
        int a(ViewGroup.MarginLayoutParams marginLayoutParams);

        int b(ViewGroup.MarginLayoutParams marginLayoutParams);
    }

    public static class b implements a {
        @Override // k.I.a
        public int a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            return marginLayoutParams.rightMargin;
        }

        @Override // k.I.a
        public int b(ViewGroup.MarginLayoutParams marginLayoutParams) {
            return marginLayoutParams.leftMargin;
        }
    }

    public static class c implements a {
        @Override // k.I.a
        public int a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            return L.a(marginLayoutParams);
        }

        @Override // k.I.a
        public int b(ViewGroup.MarginLayoutParams marginLayoutParams) {
            return L.b(marginLayoutParams);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 17) {
            f5670a = new c();
        } else {
            f5670a = new b();
        }
    }

    public static int a(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return f5670a.a(marginLayoutParams);
    }

    public static int b(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return f5670a.b(marginLayoutParams);
    }
}
