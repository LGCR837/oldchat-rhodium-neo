package k;

import android.os.Build;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: renamed from: k.w1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0321w1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f5746a;

    /* JADX INFO: renamed from: k.w1$a */
    public static class a extends e {
    }

    /* JADX INFO: renamed from: k.w1$b */
    public interface b {
        void a(ViewParent viewParent, View view, int i2, int i3, int i4, int i5);

        void b(ViewParent viewParent, View view, View view2, int i2);

        void c(ViewParent viewParent, View view, int i2, int i3, int[] iArr);

        boolean d(ViewParent viewParent, View view, float f2, float f3, boolean z2);

        boolean e(ViewParent viewParent, View view, View view2, int i2);

        boolean f(ViewParent viewParent, View view, float f2, float f3);

        void g(ViewParent viewParent, View view);
    }

    /* JADX INFO: renamed from: k.w1$c */
    public static class c extends a {
    }

    /* JADX INFO: renamed from: k.w1$d */
    public static class d extends c {
        @Override // k.AbstractC0321w1.e, k.AbstractC0321w1.b
        public void a(ViewParent viewParent, View view, int i2, int i3, int i4, int i5) {
            E1.d(viewParent, view, i2, i3, i4, i5);
        }

        @Override // k.AbstractC0321w1.e, k.AbstractC0321w1.b
        public void b(ViewParent viewParent, View view, View view2, int i2) {
            E1.e(viewParent, view, view2, i2);
        }

        @Override // k.AbstractC0321w1.e, k.AbstractC0321w1.b
        public void c(ViewParent viewParent, View view, int i2, int i3, int[] iArr) {
            E1.c(viewParent, view, i2, i3, iArr);
        }

        @Override // k.AbstractC0321w1.e, k.AbstractC0321w1.b
        public boolean d(ViewParent viewParent, View view, float f2, float f3, boolean z2) {
            return E1.a(viewParent, view, f2, f3, z2);
        }

        @Override // k.AbstractC0321w1.e, k.AbstractC0321w1.b
        public boolean e(ViewParent viewParent, View view, View view2, int i2) {
            return E1.f(viewParent, view, view2, i2);
        }

        @Override // k.AbstractC0321w1.e, k.AbstractC0321w1.b
        public boolean f(ViewParent viewParent, View view, float f2, float f3) {
            return E1.b(viewParent, view, f2, f3);
        }

        @Override // k.AbstractC0321w1.e, k.AbstractC0321w1.b
        public void g(ViewParent viewParent, View view) {
            E1.g(viewParent, view);
        }
    }

    /* JADX INFO: renamed from: k.w1$e */
    public static class e implements b {
        @Override // k.AbstractC0321w1.b
        public void a(ViewParent viewParent, View view, int i2, int i3, int i4, int i5) {
            if (viewParent instanceof InterfaceC0258b0) {
                ((InterfaceC0258b0) viewParent).onNestedScroll(view, i2, i3, i4, i5);
            }
        }

        @Override // k.AbstractC0321w1.b
        public void b(ViewParent viewParent, View view, View view2, int i2) {
            if (viewParent instanceof InterfaceC0258b0) {
                ((InterfaceC0258b0) viewParent).onNestedScrollAccepted(view, view2, i2);
            }
        }

        @Override // k.AbstractC0321w1.b
        public void c(ViewParent viewParent, View view, int i2, int i3, int[] iArr) {
            if (viewParent instanceof InterfaceC0258b0) {
                ((InterfaceC0258b0) viewParent).onNestedPreScroll(view, i2, i3, iArr);
            }
        }

        @Override // k.AbstractC0321w1.b
        public boolean d(ViewParent viewParent, View view, float f2, float f3, boolean z2) {
            if (viewParent instanceof InterfaceC0258b0) {
                return ((InterfaceC0258b0) viewParent).onNestedFling(view, f2, f3, z2);
            }
            return false;
        }

        @Override // k.AbstractC0321w1.b
        public boolean e(ViewParent viewParent, View view, View view2, int i2) {
            if (viewParent instanceof InterfaceC0258b0) {
                return ((InterfaceC0258b0) viewParent).onStartNestedScroll(view, view2, i2);
            }
            return false;
        }

        @Override // k.AbstractC0321w1.b
        public boolean f(ViewParent viewParent, View view, float f2, float f3) {
            if (viewParent instanceof InterfaceC0258b0) {
                return ((InterfaceC0258b0) viewParent).onNestedPreFling(view, f2, f3);
            }
            return false;
        }

        @Override // k.AbstractC0321w1.b
        public void g(ViewParent viewParent, View view) {
            if (viewParent instanceof InterfaceC0258b0) {
                ((InterfaceC0258b0) viewParent).onStopNestedScroll(view);
            }
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            f5746a = new d();
            return;
        }
        if (i2 >= 19) {
            f5746a = new c();
        } else if (i2 >= 14) {
            f5746a = new a();
        } else {
            f5746a = new e();
        }
    }

    public static boolean a(ViewParent viewParent, View view, float f2, float f3, boolean z2) {
        return f5746a.d(viewParent, view, f2, f3, z2);
    }

    public static boolean b(ViewParent viewParent, View view, float f2, float f3) {
        return f5746a.f(viewParent, view, f2, f3);
    }

    public static void c(ViewParent viewParent, View view, int i2, int i3, int[] iArr) {
        f5746a.c(viewParent, view, i2, i3, iArr);
    }

    public static void d(ViewParent viewParent, View view, int i2, int i3, int i4, int i5) {
        f5746a.a(viewParent, view, i2, i3, i4, i5);
    }

    public static void e(ViewParent viewParent, View view, View view2, int i2) {
        f5746a.b(viewParent, view, view2, i2);
    }

    public static boolean f(ViewParent viewParent, View view, View view2, int i2) {
        return f5746a.e(viewParent, view, view2, i2);
    }

    public static void g(ViewParent viewParent, View view) {
        f5746a.g(viewParent, view);
    }
}
