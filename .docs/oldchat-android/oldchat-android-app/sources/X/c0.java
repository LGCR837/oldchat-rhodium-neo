package x;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.os.Build;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class c0 extends ContextWrapper {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ArrayList f8134c = new ArrayList();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Resources f8135a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Resources.Theme f8136b;

    public c0(Context context) {
        super(context);
        if (!g0.a()) {
            this.f8135a = new e0(this, context.getResources());
            this.f8136b = null;
            return;
        }
        g0 g0Var = new g0(this, context.getResources());
        this.f8135a = g0Var;
        Resources.Theme themeNewTheme = g0Var.newTheme();
        this.f8136b = themeNewTheme;
        themeNewTheme.setTo(context.getTheme());
    }

    public static boolean a(Context context) {
        if ((context instanceof c0) || (context.getResources() instanceof e0) || (context.getResources() instanceof g0)) {
            return false;
        }
        return !o.f.n() || Build.VERSION.SDK_INT <= 20;
    }

    public static Context b(Context context) {
        if (!a(context)) {
            return context;
        }
        int size = f8134c.size();
        for (int i2 = 0; i2 < size; i2++) {
            WeakReference weakReference = (WeakReference) f8134c.get(i2);
            c0 c0Var = weakReference != null ? (c0) weakReference.get() : null;
            if (c0Var != null && c0Var.getBaseContext() == context) {
                return c0Var;
            }
        }
        c0 c0Var2 = new c0(context);
        f8134c.add(new WeakReference(c0Var2));
        return c0Var2;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.f8135a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f8136b;
        return theme == null ? super.getTheme() : theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i2) {
        Resources.Theme theme = this.f8136b;
        if (theme == null) {
            super.setTheme(i2);
        } else {
            theme.applyStyle(i2, true);
        }
    }
}
