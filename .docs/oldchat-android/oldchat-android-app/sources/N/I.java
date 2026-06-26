package n;

import android.content.Context;
import android.os.Build;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

/* JADX INFO: loaded from: classes.dex */
public final class I {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public OverScroller f6650a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f6651b;

    public I(boolean z2, Context context, Interpolator interpolator) {
        this.f6651b = z2;
        this.f6650a = interpolator != null ? new OverScroller(context, interpolator) : new OverScroller(context);
    }

    public static I c(Context context) {
        return d(context, null);
    }

    public static I d(Context context, Interpolator interpolator) {
        return new I(Build.VERSION.SDK_INT >= 14, context, interpolator);
    }

    public void a() {
        this.f6650a.abortAnimation();
    }

    public boolean b() {
        return this.f6650a.computeScrollOffset();
    }

    public void e(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.f6650a.fling(i2, i3, i4, i5, i6, i7, i8, i9);
    }

    public void f(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        this.f6650a.fling(i2, i3, i4, i5, i6, i7, i8, i9, i10, i11);
    }

    public float g() {
        if (this.f6651b) {
            return K.a(this.f6650a);
        }
        return 0.0f;
    }

    public int h() {
        return this.f6650a.getCurrX();
    }

    public int i() {
        return this.f6650a.getCurrY();
    }

    public int j() {
        return this.f6650a.getFinalX();
    }

    public int k() {
        return this.f6650a.getFinalY();
    }

    public boolean l() {
        return this.f6650a.isFinished();
    }

    public boolean m(int i2, int i3, int i4, int i5, int i6, int i7) {
        return this.f6650a.springBack(i2, i3, i4, i5, i6, i7);
    }

    public void n(int i2, int i3, int i4, int i5) {
        this.f6650a.startScroll(i2, i3, i4, i5);
    }

    public void o(int i2, int i3, int i4, int i5, int i6) {
        this.f6650a.startScroll(i2, i3, i4, i5, i6);
    }
}
