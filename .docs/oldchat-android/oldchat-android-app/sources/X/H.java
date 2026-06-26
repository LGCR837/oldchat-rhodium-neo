package x;

import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import r.AbstractC0466a;

/* JADX INFO: loaded from: classes.dex */
public abstract class H {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Rect f8021a = new Rect();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Class f8022b;

    static {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                f8022b = Class.forName("android.graphics.Insets");
            } catch (ClassNotFoundException unused) {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean a(Drawable drawable) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 15 && (drawable instanceof InsetDrawable)) {
            return false;
        }
        if (i2 < 15 && (drawable instanceof GradientDrawable)) {
            return false;
        }
        if (i2 < 17 && (drawable instanceof LayerDrawable)) {
            return false;
        }
        if (!(drawable instanceof DrawableContainer)) {
            if (drawable instanceof g.w) {
                return a(((g.w) drawable).a());
            }
            if (drawable instanceof AbstractC0466a) {
                return a(((AbstractC0466a) drawable).a());
            }
            if (drawable instanceof ScaleDrawable) {
                return a(((ScaleDrawable) drawable).getDrawable());
            }
            return true;
        }
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (!(constantState instanceof DrawableContainer.DrawableContainerState)) {
            return true;
        }
        for (Drawable drawable2 : ((DrawableContainer.DrawableContainerState) constantState).getChildren()) {
            if (!a(drawable2)) {
                return false;
            }
        }
        return true;
    }

    public static void b(Drawable drawable) {
        if (Build.VERSION.SDK_INT == 21 && "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName())) {
            c(drawable);
        }
    }

    public static void c(Drawable drawable) {
        int[] state = drawable.getState();
        if (state == null || state.length == 0) {
            drawable.setState(b0.f8129f);
        } else {
            drawable.setState(b0.f8132i);
        }
        drawable.setState(state);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Rect d(android.graphics.drawable.Drawable r11) {
        /*
            java.lang.Class r0 = x.H.f8022b
            if (r0 == 0) goto L92
            android.graphics.drawable.Drawable r11 = g.AbstractC0244a.p(r11)     // Catch: java.lang.Exception -> L8b
            java.lang.Class r0 = r11.getClass()     // Catch: java.lang.Exception -> L8b
            java.lang.String r1 = "getOpticalInsets"
            r2 = 0
            java.lang.reflect.Method r0 = r0.getMethod(r1, r2)     // Catch: java.lang.Exception -> L8b
            java.lang.Object r11 = r0.invoke(r11, r2)     // Catch: java.lang.Exception -> L8b
            if (r11 == 0) goto L92
            android.graphics.Rect r0 = new android.graphics.Rect     // Catch: java.lang.Exception -> L8b
            r0.<init>()     // Catch: java.lang.Exception -> L8b
            java.lang.Class r1 = x.H.f8022b     // Catch: java.lang.Exception -> L8b
            java.lang.reflect.Field[] r1 = r1.getFields()     // Catch: java.lang.Exception -> L8b
            int r2 = r1.length     // Catch: java.lang.Exception -> L8b
            r3 = 0
            r4 = 0
        L27:
            if (r4 >= r2) goto L8a
            r5 = r1[r4]     // Catch: java.lang.Exception -> L8b
            java.lang.String r6 = r5.getName()     // Catch: java.lang.Exception -> L8b
            int r7 = r6.hashCode()     // Catch: java.lang.Exception -> L8b
            r8 = 3
            r9 = 2
            r10 = 1
            switch(r7) {
                case -1383228885: goto L58;
                case 115029: goto L4e;
                case 3317767: goto L44;
                case 108511772: goto L3a;
                default: goto L39;
            }     // Catch: java.lang.Exception -> L8b
        L39:
            goto L62
        L3a:
            java.lang.String r7 = "right"
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Exception -> L8b
            if (r6 == 0) goto L62
            r6 = 2
            goto L63
        L44:
            java.lang.String r7 = "left"
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Exception -> L8b
            if (r6 == 0) goto L62
            r6 = 0
            goto L63
        L4e:
            java.lang.String r7 = "top"
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Exception -> L8b
            if (r6 == 0) goto L62
            r6 = 1
            goto L63
        L58:
            java.lang.String r7 = "bottom"
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Exception -> L8b
            if (r6 == 0) goto L62
            r6 = 3
            goto L63
        L62:
            r6 = -1
        L63:
            if (r6 == 0) goto L81
            if (r6 == r10) goto L7a
            if (r6 == r9) goto L73
            if (r6 == r8) goto L6c
            goto L87
        L6c:
            int r5 = r5.getInt(r11)     // Catch: java.lang.Exception -> L8b
            r0.bottom = r5     // Catch: java.lang.Exception -> L8b
            goto L87
        L73:
            int r5 = r5.getInt(r11)     // Catch: java.lang.Exception -> L8b
            r0.right = r5     // Catch: java.lang.Exception -> L8b
            goto L87
        L7a:
            int r5 = r5.getInt(r11)     // Catch: java.lang.Exception -> L8b
            r0.top = r5     // Catch: java.lang.Exception -> L8b
            goto L87
        L81:
            int r5 = r5.getInt(r11)     // Catch: java.lang.Exception -> L8b
            r0.left = r5     // Catch: java.lang.Exception -> L8b
        L87:
            int r4 = r4 + 1
            goto L27
        L8a:
            return r0
        L8b:
            java.lang.String r11 = "DrawableUtils"
            java.lang.String r0 = "Couldn't obtain the optical insets. Ignoring."
            android.util.Log.e(r11, r0)
        L92:
            android.graphics.Rect r11 = x.H.f8021a
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: x.H.d(android.graphics.drawable.Drawable):android.graphics.Rect");
    }

    public static PorterDuff.Mode e(int i2, PorterDuff.Mode mode) {
        if (i2 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i2 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i2 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i2) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return Build.VERSION.SDK_INT >= 11 ? PorterDuff.Mode.valueOf("ADD") : mode;
            default:
                return mode;
        }
    }
}
