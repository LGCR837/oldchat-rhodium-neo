package k;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class I0 {
    public static boolean a(View view, int i2) {
        return view.canScrollHorizontally(i2);
    }

    public static boolean b(View view, int i2) {
        return view.canScrollVertically(i2);
    }

    public static void c(View view, Object obj) {
        view.setAccessibilityDelegate(AbstractC0257b.a(obj));
    }
}
