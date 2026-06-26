package d;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.util.TypedValue;
import java.io.File;

/* JADX INFO: renamed from: d.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0235a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f4899a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static TypedValue f4900b;

    public static int a(Context context, String str) {
        if (str != null) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }

    public static final int b(Context context, int i2) {
        return Build.VERSION.SDK_INT >= 23 ? f.a(context, i2) : context.getResources().getColor(i2);
    }

    public static final ColorStateList c(Context context, int i2) {
        return Build.VERSION.SDK_INT >= 23 ? f.b(context, i2) : context.getResources().getColorStateList(i2);
    }

    public static final Drawable d(Context context, int i2) {
        int i3;
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 21) {
            return c.a(context, i2);
        }
        if (i4 >= 16) {
            return context.getResources().getDrawable(i2);
        }
        synchronized (f4899a) {
            try {
                if (f4900b == null) {
                    f4900b = new TypedValue();
                }
                context.getResources().getValue(i2, f4900b, true);
                i3 = f4900b.resourceId;
            } catch (Throwable th) {
                throw th;
            }
        }
        return context.getResources().getDrawable(i3);
    }

    public static File[] e(Context context) {
        return Build.VERSION.SDK_INT >= 19 ? m.a(context) : new File[]{context.getExternalCacheDir()};
    }

    public static File[] f(Context context, String str) {
        return Build.VERSION.SDK_INT >= 19 ? m.b(context, str) : new File[]{context.getExternalFilesDir(str)};
    }

    public static boolean g(Context context, Intent[] intentArr, Bundle bundle) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 16) {
            j.a(context, intentArr, bundle);
            return true;
        }
        if (i2 < 11) {
            return false;
        }
        h.a(context, intentArr);
        return true;
    }
}
