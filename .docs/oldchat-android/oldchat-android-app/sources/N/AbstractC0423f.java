package n;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

/* JADX INFO: renamed from: n.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0423f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Field f6688a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f6689b;

    public static Drawable a(CompoundButton compoundButton) {
        if (!f6689b) {
            try {
                Field declaredField = CompoundButton.class.getDeclaredField("mButtonDrawable");
                f6688a = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e2) {
                Log.i("CompoundButtonCompatGingerbread", "Failed to retrieve mButtonDrawable field", e2);
            }
            f6689b = true;
        }
        Field field = f6688a;
        if (field != null) {
            try {
                return (Drawable) field.get(compoundButton);
            } catch (IllegalAccessException e3) {
                Log.i("CompoundButtonCompatGingerbread", "Failed to get button drawable via reflection", e3);
                f6688a = null;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void b(CompoundButton compoundButton, ColorStateList colorStateList) {
        if (compoundButton instanceof L) {
            ((L) compoundButton).setSupportButtonTintList(colorStateList);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void c(CompoundButton compoundButton, PorterDuff.Mode mode) {
        if (compoundButton instanceof L) {
            ((L) compoundButton).setSupportButtonTintMode(mode);
        }
    }
}
