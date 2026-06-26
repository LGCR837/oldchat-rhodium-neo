package k;

import android.animation.ValueAnimator;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class E0 {
    public static float a(View view) {
        return view.getAlpha();
    }

    public static long b() {
        return ValueAnimator.getFrameDelay();
    }

    public static int c(View view) {
        return view.getLayerType();
    }

    public static Matrix d(View view) {
        return view.getMatrix();
    }

    public static int e(View view) {
        return view.getMeasuredState();
    }

    public static int f(View view) {
        return view.getMeasuredWidthAndState();
    }

    public static float g(View view) {
        return view.getScaleX();
    }

    public static float h(View view) {
        return view.getTranslationX();
    }

    public static float i(View view) {
        return view.getTranslationY();
    }

    public static void j(View view) {
        view.jumpDrawablesToCurrentState();
    }

    public static void k(View view, int i2) {
        view.offsetTopAndBottom(i2);
        if (view.getVisibility() == 0) {
            t(view);
            Object parent = view.getParent();
            if (parent instanceof View) {
                t((View) parent);
            }
        }
    }

    public static int l(int i2, int i3, int i4) {
        return View.resolveSizeAndState(i2, i3, i4);
    }

    public static void m(View view, float f2) {
        view.setAlpha(f2);
    }

    public static void n(View view, int i2, Paint paint) {
        view.setLayerType(i2, paint);
    }

    public static void o(View view, boolean z2) {
        view.setSaveFromParentEnabled(z2);
    }

    public static void p(View view, float f2) {
        view.setScaleX(f2);
    }

    public static void q(View view, float f2) {
        view.setScaleY(f2);
    }

    public static void r(View view, float f2) {
        view.setTranslationX(f2);
    }

    public static void s(View view, float f2) {
        view.setTranslationY(f2);
    }

    public static void t(View view) {
        float translationY = view.getTranslationY();
        view.setTranslationY(1.0f + translationY);
        view.setTranslationY(translationY);
    }
}
