package g;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {
    public static void a(Drawable drawable) {
        drawable.jumpToCurrentState();
    }

    public static Drawable b(Drawable drawable) {
        return !(drawable instanceof InterfaceC0243D) ? new y(drawable) : drawable;
    }
}
