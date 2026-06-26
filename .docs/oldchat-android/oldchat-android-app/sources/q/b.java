package q;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import d.AbstractC0235a;
import java.util.WeakHashMap;
import x.C0508k;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal f7903a = new ThreadLocal();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final WeakHashMap f7904b = new WeakHashMap(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f7905c = new Object();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ColorStateList f7906a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Configuration f7907b;

        public a(ColorStateList colorStateList, Configuration configuration) {
            this.f7906a = colorStateList;
            this.f7907b = configuration;
        }
    }

    public static void a(Context context, int i2, ColorStateList colorStateList) {
        synchronized (f7905c) {
            try {
                WeakHashMap weakHashMap = f7904b;
                SparseArray sparseArray = (SparseArray) weakHashMap.get(context);
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                    weakHashMap.put(context, sparseArray);
                }
                sparseArray.append(i2, new a(colorStateList, context.getResources().getConfiguration()));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static ColorStateList b(Context context, int i2) {
        a aVar;
        synchronized (f7905c) {
            try {
                SparseArray sparseArray = (SparseArray) f7904b.get(context);
                if (sparseArray != null && sparseArray.size() > 0 && (aVar = (a) sparseArray.get(i2)) != null) {
                    if (aVar.f7907b.equals(context.getResources().getConfiguration())) {
                        return aVar.f7906a;
                    }
                    sparseArray.remove(i2);
                }
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static ColorStateList c(Context context, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i2);
        }
        ColorStateList colorStateListB = b(context, i2);
        if (colorStateListB != null) {
            return colorStateListB;
        }
        ColorStateList colorStateListF = f(context, i2);
        if (colorStateListF == null) {
            return AbstractC0235a.c(context, i2);
        }
        a(context, i2, colorStateListF);
        return colorStateListF;
    }

    public static Drawable d(Context context, int i2) {
        return C0508k.m().o(context, i2);
    }

    public static TypedValue e() {
        ThreadLocal threadLocal = f7903a;
        TypedValue typedValue = (TypedValue) threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }

    public static ColorStateList f(Context context, int i2) {
        if (g(context, i2)) {
            return null;
        }
        Resources resources = context.getResources();
        try {
            return AbstractC0465a.a(resources, resources.getXml(i2), context.getTheme());
        } catch (Exception e2) {
            Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", e2);
            return null;
        }
    }

    public static boolean g(Context context, int i2) {
        Resources resources = context.getResources();
        TypedValue typedValueE = e();
        resources.getValue(i2, typedValueE, true);
        int i3 = typedValueE.type;
        return i3 >= 28 && i3 <= 31;
    }
}
