package k;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.view.View;
import java.lang.reflect.Field;

/* JADX INFO: renamed from: k.l0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0288l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Field f5706a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f5707b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Field f5708c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f5709d;

    /* JADX WARN: Multi-variable type inference failed */
    public static ColorStateList a(View view) {
        if (view instanceof InterfaceC0276h0) {
            return ((InterfaceC0276h0) view).getSupportBackgroundTintList();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static PorterDuff.Mode b(View view) {
        if (view instanceof InterfaceC0276h0) {
            return ((InterfaceC0276h0) view).getSupportBackgroundTintMode();
        }
        return null;
    }

    public static int c(View view) {
        if (!f5709d) {
            try {
                Field declaredField = View.class.getDeclaredField("mMinHeight");
                f5708c = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            f5709d = true;
        }
        Field field = f5708c;
        if (field == null) {
            return 0;
        }
        try {
            return ((Integer) field.get(view)).intValue();
        } catch (Exception unused2) {
            return 0;
        }
    }

    public static int d(View view) {
        if (!f5707b) {
            try {
                Field declaredField = View.class.getDeclaredField("mMinWidth");
                f5706a = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            f5707b = true;
        }
        Field field = f5706a;
        if (field == null) {
            return 0;
        }
        try {
            return ((Integer) field.get(view)).intValue();
        } catch (Exception unused2) {
            return 0;
        }
    }

    public static boolean e(View view) {
        return view.getWindowToken() != null;
    }

    public static boolean f(View view) {
        return view.getWidth() > 0 && view.getHeight() > 0;
    }

    public static void g(View view, int i2) {
        int top = view.getTop();
        view.offsetTopAndBottom(i2);
        if (i2 != 0) {
            Object parent = view.getParent();
            if (!(parent instanceof View)) {
                view.invalidate();
            } else {
                int iAbs = Math.abs(i2);
                ((View) parent).invalidate(view.getLeft(), top - iAbs, view.getRight(), top + view.getHeight() + iAbs);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void h(View view, ColorStateList colorStateList) {
        if (view instanceof InterfaceC0276h0) {
            ((InterfaceC0276h0) view).setSupportBackgroundTintList(colorStateList);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void i(View view, PorterDuff.Mode mode) {
        if (view instanceof InterfaceC0276h0) {
            ((InterfaceC0276h0) view).setSupportBackgroundTintMode(mode);
        }
    }
}
