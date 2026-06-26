package k;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class V0 {
    public static int a(View view) {
        return view.getImportantForAccessibility();
    }

    public static int b(View view) {
        return view.getMinimumHeight();
    }

    public static int c(View view) {
        return view.getMinimumWidth();
    }

    public static boolean d(View view) {
        return view.hasOverlappingRendering();
    }

    public static boolean e(View view) {
        return view.hasTransientState();
    }

    public static void f(View view) {
        view.postInvalidateOnAnimation();
    }

    public static void g(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static void h(View view, Runnable runnable, long j2) {
        view.postOnAnimationDelayed(runnable, j2);
    }

    public static void i(View view) {
        view.requestFitSystemWindows();
    }

    public static void j(View view, int i2) {
        view.setImportantForAccessibility(i2);
    }
}
