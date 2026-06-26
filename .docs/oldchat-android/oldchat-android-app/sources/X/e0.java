package x;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class e0 extends Y {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final WeakReference f8144b;

    public e0(Context context, Resources resources) {
        super(resources);
        this.f8144b = new WeakReference(context);
    }

    @Override // x.Y, android.content.res.Resources
    public Drawable getDrawable(int i2) {
        Drawable drawable = super.getDrawable(i2);
        Context context = (Context) this.f8144b.get();
        if (drawable != null && context != null) {
            C0508k.m();
            C0508k.D(context, i2, drawable);
        }
        return drawable;
    }
}
