package x;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: renamed from: x.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0512o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ImageView f8187a;

    public C0512o(ImageView imageView) {
        this.f8187a = imageView;
    }

    public boolean a() {
        return Build.VERSION.SDK_INT < 21 || !AbstractC0511n.a(this.f8187a.getBackground());
    }

    public void b(AttributeSet attributeSet, int i2) {
        int iL;
        f0 f0VarS = null;
        try {
            Drawable drawable = this.f8187a.getDrawable();
            if (drawable == null && (iL = (f0VarS = f0.s(this.f8187a.getContext(), attributeSet, p.j.f7848M, i2, 0)).l(p.j.f7849N, -1)) != -1 && (drawable = q.b.d(this.f8187a.getContext(), iL)) != null) {
                this.f8187a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                H.b(drawable);
            }
            if (f0VarS != null) {
                f0VarS.t();
            }
        } catch (Throwable th) {
            if (f0VarS != null) {
                f0VarS.t();
            }
            throw th;
        }
    }

    public void c(int i2) {
        if (i2 == 0) {
            this.f8187a.setImageDrawable(null);
            return;
        }
        Drawable drawableD = q.b.d(this.f8187a.getContext(), i2);
        if (drawableD != null) {
            H.b(drawableD);
        }
        this.f8187a.setImageDrawable(drawableD);
    }
}
