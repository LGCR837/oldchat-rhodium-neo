package l;

import android.graphics.Rect;
import k.AbstractC0260c;

/* JADX INFO: renamed from: l.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0333C {
    public static void a(Object obj, int i2) {
        AbstractC0260c.a(obj).addAction(i2);
    }

    public static int b(Object obj) {
        return AbstractC0260c.a(obj).getActions();
    }

    public static void c(Object obj, Rect rect) {
        AbstractC0260c.a(obj).getBoundsInParent(rect);
    }

    public static void d(Object obj, Rect rect) {
        AbstractC0260c.a(obj).getBoundsInScreen(rect);
    }

    public static CharSequence e(Object obj) {
        return AbstractC0260c.a(obj).getClassName();
    }

    public static CharSequence f(Object obj) {
        return AbstractC0260c.a(obj).getContentDescription();
    }

    public static CharSequence g(Object obj) {
        return AbstractC0260c.a(obj).getPackageName();
    }

    public static CharSequence h(Object obj) {
        return AbstractC0260c.a(obj).getText();
    }

    public static boolean i(Object obj) {
        return AbstractC0260c.a(obj).isCheckable();
    }

    public static boolean j(Object obj) {
        return AbstractC0260c.a(obj).isChecked();
    }

    public static boolean k(Object obj) {
        return AbstractC0260c.a(obj).isClickable();
    }

    public static boolean l(Object obj) {
        return AbstractC0260c.a(obj).isEnabled();
    }

    public static boolean m(Object obj) {
        return AbstractC0260c.a(obj).isFocusable();
    }

    public static boolean n(Object obj) {
        return AbstractC0260c.a(obj).isFocused();
    }

    public static boolean o(Object obj) {
        return AbstractC0260c.a(obj).isLongClickable();
    }

    public static boolean p(Object obj) {
        return AbstractC0260c.a(obj).isPassword();
    }

    public static boolean q(Object obj) {
        return AbstractC0260c.a(obj).isScrollable();
    }

    public static boolean r(Object obj) {
        return AbstractC0260c.a(obj).isSelected();
    }

    public static void s(Object obj, CharSequence charSequence) {
        AbstractC0260c.a(obj).setClassName(charSequence);
    }

    public static void t(Object obj, boolean z2) {
        AbstractC0260c.a(obj).setScrollable(z2);
    }
}
