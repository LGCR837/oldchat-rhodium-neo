package n;

import android.util.Log;
import android.widget.PopupWindow;
import java.lang.reflect.Field;

/* JADX INFO: renamed from: n.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0415C {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Field f6649a;

    static {
        try {
            Field declaredField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
            f6649a = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e2) {
            Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", e2);
        }
    }

    public static void a(PopupWindow popupWindow, boolean z2) {
        Field field = f6649a;
        if (field != null) {
            try {
                field.set(popupWindow, Boolean.valueOf(z2));
            } catch (IllegalAccessException e2) {
                Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", e2);
            }
        }
    }
}
