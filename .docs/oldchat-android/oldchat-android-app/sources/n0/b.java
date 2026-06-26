package n0;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import com.im.oldchat.ui.widget.TiltFrameLayout;
import f0.f;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import o0.V;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Method f6693a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Field f6694b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f6695c;

    public class a implements ViewTreeObserver.OnGlobalLayoutListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f6696a;

        public a(View view) {
            this.f6696a = view;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            b.l(this.f6696a);
        }
    }

    /* JADX INFO: renamed from: n0.b$b, reason: collision with other inner class name */
    public class RunnableC0065b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f6697a;

        public RunnableC0065b(View view) {
            this.f6697a = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f6697a.setTag(f.R8, Boolean.FALSE);
            if (this.f6697a.getWindowToken() == null) {
                return;
            }
            b.b(this.f6697a);
        }
    }

    public static void b(View view) {
        if (view != null && V.f(view.getContext())) {
            c(view, 0);
            view.setTag(f.Q8, Long.valueOf(SystemClock.uptimeMillis()));
        }
    }

    public static int c(View view, int i2) {
        if (i2 >= 320) {
            return i2;
        }
        if (view != null && view.getVisibility() == 0) {
            i2++;
            d(view);
            if (!(view instanceof ViewGroup) || i(view)) {
                return i2;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i3 = 0; i3 < viewGroup.getChildCount() && (i2 = c(viewGroup.getChildAt(i3), i2)) < 320; i3++) {
            }
        }
        return i2;
    }

    public static void d(View view) {
        if (view == null || !view.isClickable() || (view instanceof TiltFrameLayout) || view.getId() == f.X4 || view.getId() == f.i2 || view.getId() == f.l1 || g(view) || h(view)) {
            return;
        }
        Object tag = view.getTag(f.O8);
        Boolean bool = Boolean.TRUE;
        if (bool.equals(tag)) {
            return;
        }
        View.OnTouchListener onTouchListenerK = k(view);
        if (onTouchListenerK instanceof c) {
            view.setTag(f.O8, bool);
        } else {
            view.setTag(f.O8, bool);
            view.setOnTouchListener(new c(view, onTouchListenerK));
        }
    }

    public static void e() {
        if (f6695c) {
            return;
        }
        f6695c = true;
        try {
            Method declaredMethod = View.class.getDeclaredMethod("getListenerInfo", null);
            f6693a = declaredMethod;
            declaredMethod.setAccessible(true);
            Field declaredField = Class.forName("android.view.View$ListenerInfo").getDeclaredField("mOnTouchListener");
            f6694b = declaredField;
            declaredField.setAccessible(true);
        } catch (Throwable unused) {
            f6693a = null;
            f6694b = null;
        }
    }

    public static void f(View view) {
        if (view != null && V.f(view.getContext())) {
            b(view);
            Object tag = view.getTag(f.P8);
            Boolean bool = Boolean.TRUE;
            if (bool.equals(tag)) {
                return;
            }
            view.setTag(f.P8, bool);
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
                return;
            }
            viewTreeObserver.addOnGlobalLayoutListener(new a(view));
        }
    }

    public static boolean g(View view) {
        while (view != null) {
            if (view.getId() == f.Q6) {
                return true;
            }
            Object parent = view.getParent();
            if (!(parent instanceof View)) {
                return false;
            }
            view = (View) parent;
        }
        return false;
    }

    public static boolean h(View view) {
        while (view != null) {
            if (i(view)) {
                return true;
            }
            Object parent = view.getParent();
            if (!(parent instanceof View)) {
                return false;
            }
            view = (View) parent;
        }
        return false;
    }

    public static boolean i(View view) {
        if (view instanceof AbsListView) {
            return true;
        }
        String name = view.getClass().getName();
        return "android.support.v7.widget.RecyclerView".equals(name) || "androidx.recyclerview.widget.RecyclerView".equals(name);
    }

    public static long j(View view) {
        Object tag = view.getTag(f.Q8);
        if (tag instanceof Long) {
            return ((Long) tag).longValue();
        }
        return 0L;
    }

    public static View.OnTouchListener k(View view) {
        Object objInvoke;
        if (view == null) {
            return null;
        }
        try {
            e();
            Method method = f6693a;
            if (method == null || f6694b == null || (objInvoke = method.invoke(view, null)) == null) {
                return null;
            }
            Object obj = f6694b.get(objInvoke);
            if (obj instanceof View.OnTouchListener) {
                return (View.OnTouchListener) obj;
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void l(android.view.View r8) {
        /*
            if (r8 != 0) goto L3
            return
        L3:
            int r0 = f0.f.R8
            java.lang.Object r0 = r8.getTag(r0)
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L12
            return
        L12:
            long r2 = android.os.SystemClock.uptimeMillis()
            long r4 = j(r8)
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 <= 0) goto L29
            long r2 = r2 - r4
            r4 = 820(0x334, double:4.05E-321)
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 >= 0) goto L29
            long r4 = r4 - r2
            goto L2b
        L29:
            r4 = 180(0xb4, double:8.9E-322)
        L2b:
            int r0 = f0.f.R8
            r8.setTag(r0, r1)
            n0.b$b r0 = new n0.b$b
            r0.<init>(r8)
            long r1 = java.lang.Math.max(r6, r4)
            r8.postDelayed(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: n0.b.l(android.view.View):void");
    }
}
