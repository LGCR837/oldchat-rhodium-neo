package k;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: renamed from: k.q1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0304q1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ThreadLocal f5741a;

    /* JADX INFO: renamed from: k.q1$a */
    public static class a implements View.OnApplyWindowInsetsListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b f5742a;

        public a(b bVar) {
            this.f5742a = bVar;
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            return AbstractC0268e1.a(this.f5742a.a(view, windowInsets));
        }
    }

    /* JADX INFO: renamed from: k.q1$b */
    public interface b {
        Object a(View view, Object obj);
    }

    public static Object a(View view, Object obj) {
        WindowInsets windowInsetsA = AbstractC0268e1.a(obj);
        WindowInsets windowInsetsDispatchApplyWindowInsets = view.dispatchApplyWindowInsets(windowInsetsA);
        return windowInsetsDispatchApplyWindowInsets != windowInsetsA ? new WindowInsets(windowInsetsDispatchApplyWindowInsets) : obj;
    }

    public static ColorStateList b(View view) {
        return view.getBackgroundTintList();
    }

    public static PorterDuff.Mode c(View view) {
        return view.getBackgroundTintMode();
    }

    public static float d(View view) {
        return view.getElevation();
    }

    public static Rect e() {
        if (f5741a == null) {
            f5741a = new ThreadLocal();
        }
        Rect rect = (Rect) f5741a.get();
        if (rect == null) {
            rect = new Rect();
            f5741a.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    public static boolean f(View view) {
        return view.isNestedScrollingEnabled();
    }

    public static void g(View view, int i2) {
        boolean z2;
        Rect rectE = e();
        Object parent = view.getParent();
        if (parent instanceof View) {
            View view2 = (View) parent;
            rectE.set(view2.getLeft(), view2.getTop(), view2.getRight(), view2.getBottom());
            z2 = !rectE.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        } else {
            z2 = false;
        }
        E0.k(view, i2);
        if (z2 && rectE.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
            ((View) parent).invalidate(rectE);
        }
    }

    public static Object h(View view, Object obj) {
        WindowInsets windowInsetsA = AbstractC0268e1.a(obj);
        WindowInsets windowInsetsOnApplyWindowInsets = view.onApplyWindowInsets(windowInsetsA);
        return windowInsetsOnApplyWindowInsets != windowInsetsA ? new WindowInsets(windowInsetsOnApplyWindowInsets) : obj;
    }

    public static void i(View view) {
        view.requestApplyInsets();
    }

    public static void j(View view, ColorStateList colorStateList) {
        view.setBackgroundTintList(colorStateList);
        if (Build.VERSION.SDK_INT == 21) {
            Drawable background = view.getBackground();
            boolean z2 = (view.getBackgroundTintList() == null || view.getBackgroundTintMode() == null) ? false : true;
            if (background == null || !z2) {
                return;
            }
            if (background.isStateful()) {
                background.setState(view.getDrawableState());
            }
            view.setBackground(background);
        }
    }

    public static void k(View view, PorterDuff.Mode mode) {
        view.setBackgroundTintMode(mode);
        if (Build.VERSION.SDK_INT == 21) {
            Drawable background = view.getBackground();
            boolean z2 = (view.getBackgroundTintList() == null || view.getBackgroundTintMode() == null) ? false : true;
            if (background == null || !z2) {
                return;
            }
            if (background.isStateful()) {
                background.setState(view.getDrawableState());
            }
            view.setBackground(background);
        }
    }

    public static void l(View view, float f2) {
        view.setElevation(f2);
    }

    public static void m(View view, b bVar) {
        if (bVar == null) {
            view.setOnApplyWindowInsetsListener(null);
        } else {
            view.setOnApplyWindowInsetsListener(new a(bVar));
        }
    }

    public static void n(View view) {
        view.stopNestedScroll();
    }
}
