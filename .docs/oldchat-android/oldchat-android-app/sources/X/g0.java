package x;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class g0 extends Resources {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WeakReference f8156a;

    public g0(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.f8156a = new WeakReference(context);
    }

    public static boolean a() {
        return o.f.n() && Build.VERSION.SDK_INT <= 20;
    }

    public final Drawable b(int i2) {
        return super.getDrawable(i2);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i2) {
        Context context = (Context) this.f8156a.get();
        return context != null ? C0508k.m().z(context, this, i2) : super.getDrawable(i2);
    }
}
