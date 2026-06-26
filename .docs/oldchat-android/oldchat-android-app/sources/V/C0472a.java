package v;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.ViewConfiguration;
import e.AbstractC0236a;
import k.AbstractC0312t1;
import p.AbstractC0461b;

/* JADX INFO: renamed from: v.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0472a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f7921a;

    public C0472a(Context context) {
        this.f7921a = context;
    }

    public static C0472a b(Context context) {
        return new C0472a(context);
    }

    public boolean a() {
        return this.f7921a.getApplicationInfo().targetSdkVersion < 14;
    }

    public int c() {
        return this.f7921a.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public int d() {
        Resources resources = this.f7921a.getResources();
        int iB = AbstractC0236a.b(resources);
        int iA = AbstractC0236a.a(resources);
        if (AbstractC0236a.c(resources) > 600 || iB > 600) {
            return 5;
        }
        if (iB > 960 && iA > 720) {
            return 5;
        }
        if (iB > 720 && iA > 960) {
            return 5;
        }
        if (iB >= 500) {
            return 4;
        }
        if (iB > 640 && iA > 480) {
            return 4;
        }
        if (iB <= 480 || iA <= 640) {
            return iB >= 360 ? 3 : 2;
        }
        return 4;
    }

    public boolean e() {
        return this.f7921a.getResources().getBoolean(AbstractC0461b.f7733a);
    }

    public boolean f() {
        if (Build.VERSION.SDK_INT >= 19) {
            return true;
        }
        return !AbstractC0312t1.a(ViewConfiguration.get(this.f7921a));
    }
}
