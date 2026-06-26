package x;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import n.AbstractC0414B;

/* JADX INFO: renamed from: x.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0514q extends PopupWindow {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final boolean f8191b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f8192a;

    /* JADX INFO: renamed from: x.q$a */
    public static class a implements ViewTreeObserver.OnScrollChangedListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Field f8193a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ PopupWindow f8194b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ ViewTreeObserver.OnScrollChangedListener f8195c;

        public a(Field field, PopupWindow popupWindow, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
            this.f8193a = field;
            this.f8194b = popupWindow;
            this.f8195c = onScrollChangedListener;
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            try {
                WeakReference weakReference = (WeakReference) this.f8193a.get(this.f8194b);
                if (weakReference != null && weakReference.get() != null) {
                    this.f8195c.onScrollChanged();
                }
            } catch (IllegalAccessException unused) {
            }
        }
    }

    static {
        f8191b = Build.VERSION.SDK_INT < 21;
    }

    public C0514q(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context, attributeSet, i2, 0);
    }

    public static void c(PopupWindow popupWindow) {
        try {
            Field declaredField = PopupWindow.class.getDeclaredField("mAnchor");
            declaredField.setAccessible(true);
            Field declaredField2 = PopupWindow.class.getDeclaredField("mOnScrollChangedListener");
            declaredField2.setAccessible(true);
            declaredField2.set(popupWindow, new a(declaredField, popupWindow, (ViewTreeObserver.OnScrollChangedListener) declaredField2.get(popupWindow)));
        } catch (Exception e2) {
            Log.d("AppCompatPopupWindow", "Exception while installing workaround OnScrollChangedListener", e2);
        }
    }

    public final void a(Context context, AttributeSet attributeSet, int i2, int i3) {
        f0 f0VarS = f0.s(context, attributeSet, p.j.v1, i2, i3);
        if (f0VarS.p(p.j.y1)) {
            b(f0VarS.a(p.j.y1, false));
        }
        setBackgroundDrawable(f0VarS.f(p.j.w1));
        int i4 = Build.VERSION.SDK_INT;
        if (i3 != 0 && i4 < 11 && f0VarS.p(p.j.x1)) {
            setAnimationStyle(f0VarS.l(p.j.x1, -1));
        }
        f0VarS.t();
        if (i4 < 14) {
            c(this);
        }
    }

    public void b(boolean z2) {
        if (f8191b) {
            this.f8192a = z2;
        } else {
            AbstractC0414B.a(this, z2);
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i2, int i3) {
        if (f8191b && this.f8192a) {
            i3 -= view.getHeight();
        }
        super.showAsDropDown(view, i2, i3);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i2, int i3, int i4, int i5) {
        if (f8191b && this.f8192a) {
            i3 -= view.getHeight();
        }
        super.update(view, i2, i3, i4, i5);
    }

    public C0514q(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        a(context, attributeSet, i2, i3);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i2, int i3, int i4) {
        if (f8191b && this.f8192a) {
            i3 -= view.getHeight();
        }
        super.showAsDropDown(view, i2, i3, i4);
    }
}
