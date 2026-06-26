package g;

import android.graphics.drawable.Drawable;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Method f5264a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f5265b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Method f5266c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f5267d;

    public static int a(Drawable drawable) {
        if (!f5267d) {
            try {
                Method declaredMethod = Drawable.class.getDeclaredMethod("getLayoutDirection", null);
                f5266c = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException e2) {
                Log.i("DrawableCompatJellybeanMr1", "Failed to retrieve getLayoutDirection() method", e2);
            }
            f5267d = true;
        }
        Method method = f5266c;
        if (method == null) {
            return -1;
        }
        try {
            return ((Integer) method.invoke(drawable, null)).intValue();
        } catch (Exception e3) {
            Log.i("DrawableCompatJellybeanMr1", "Failed to invoke getLayoutDirection() via reflection", e3);
            f5266c = null;
            return -1;
        }
    }

    public static boolean b(Drawable drawable, int i2) {
        if (!f5265b) {
            try {
                Method declaredMethod = Drawable.class.getDeclaredMethod("setLayoutDirection", Integer.TYPE);
                f5264a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException e2) {
                Log.i("DrawableCompatJellybeanMr1", "Failed to retrieve setLayoutDirection(int) method", e2);
            }
            f5265b = true;
        }
        Method method = f5264a;
        if (method != null) {
            try {
                method.invoke(drawable, Integer.valueOf(i2));
                return true;
            } catch (Exception e3) {
                Log.i("DrawableCompatJellybeanMr1", "Failed to invoke setLayoutDirection(int) via reflection", e3);
                f5264a = null;
            }
        }
        return false;
    }
}
