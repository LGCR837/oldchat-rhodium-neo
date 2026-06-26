package g;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public abstract class l {
    public static int a(Drawable drawable) {
        return drawable.getAlpha();
    }

    public static boolean b(Drawable drawable) {
        return drawable.isAutoMirrored();
    }

    public static void c(Drawable drawable, boolean z2) {
        drawable.setAutoMirrored(z2);
    }

    public static Drawable d(Drawable drawable) {
        return !(drawable instanceof InterfaceC0243D) ? new z(drawable) : drawable;
    }
}
