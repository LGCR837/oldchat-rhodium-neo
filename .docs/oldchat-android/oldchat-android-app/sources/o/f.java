package o;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import i.AbstractC0250a;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f6832a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f6833b = false;

    public static void B(int i2) {
        if (i2 == -1 || i2 == 0 || i2 == 1 || i2 == 2) {
            f6832a = i2;
        } else {
            Log.d("AppCompatDelegate", "setDefaultNightMode() called with an unknown mode");
        }
    }

    public static f e(Activity activity, e eVar) {
        return g(activity, activity.getWindow(), eVar);
    }

    public static f f(Dialog dialog, e eVar) {
        return g(dialog.getContext(), dialog.getWindow(), eVar);
    }

    public static f g(Context context, Window window, e eVar) {
        int i2 = Build.VERSION.SDK_INT;
        return AbstractC0250a.a() ? new h(context, window, eVar) : i2 >= 23 ? new l(context, window, eVar) : i2 >= 14 ? new k(context, window, eVar) : i2 >= 11 ? new i(context, window, eVar) : new m(context, window, eVar);
    }

    public static int i() {
        return f6832a;
    }

    public static boolean n() {
        return f6833b;
    }

    public static void x(boolean z2) {
        f6833b = z2;
    }

    public abstract void A(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void C(int i2);

    public abstract void D(CharSequence charSequence);

    public abstract void c(View view, ViewGroup.LayoutParams layoutParams);

    public abstract boolean d();

    public abstract View h(int i2);

    public abstract MenuInflater j();

    public abstract AbstractC0430a k();

    public abstract void l();

    public abstract void m();

    public abstract void o(Configuration configuration);

    public abstract void p(Bundle bundle);

    public abstract void q();

    public abstract void r(Bundle bundle);

    public abstract void s();

    public abstract void t(Bundle bundle);

    public abstract void u();

    public abstract void v();

    public abstract boolean w(int i2);

    public abstract void y(int i2);

    public abstract void z(View view);
}
