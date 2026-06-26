package k;

import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import i.AbstractC0250a;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import k.AbstractC0304q1;

/* JADX INFO: renamed from: k.k0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0285k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final l f5699a;

    /* JADX INFO: renamed from: k.k0$a */
    public static class a extends k {
    }

    /* JADX INFO: renamed from: k.k0$c */
    public static class c extends b {
        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void B(View view, boolean z2) {
            E0.o(view, z2);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void C(View view, int i2, Paint paint) {
            E0.n(view, i2, paint);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public float D(View view) {
            return E0.g(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public int H(int i2, int i3, int i4) {
            return E0.l(i2, i3, i4);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void M(View view, float f2) {
            E0.m(view, f2);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public float O(View view) {
            return E0.i(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void S(View view, float f2) {
            E0.r(view, f2);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void V(View view) {
            E0.j(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public int X(View view) {
            return E0.f(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void Y(View view, int i2) {
            E0.k(view, i2);
        }

        @Override // k.AbstractC0285k0.b
        public long b0() {
            return E0.b();
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public float h(View view) {
            return E0.h(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public int k(View view) {
            return E0.e(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void l(View view, float f2) {
            E0.q(view, f2);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public float n(View view) {
            return E0.a(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void p(View view, float f2) {
            E0.p(view, f2);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public Matrix r(View view) {
            return E0.d(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void u(View view, float f2) {
            E0.s(view, f2);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public int z(View view) {
            return E0.c(view);
        }
    }

    /* JADX INFO: renamed from: k.k0$d */
    public static class d extends e {
        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public boolean W(View view) {
            return K0.a(view);
        }
    }

    /* JADX INFO: renamed from: k.k0$e */
    public static class e extends c {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static Field f5702c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static boolean f5703d = false;

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public F1 G(View view) {
            if (this.f5701a == null) {
                this.f5701a = new WeakHashMap();
            }
            F1 f1 = (F1) this.f5701a.get(view);
            if (f1 != null) {
                return f1;
            }
            F1 f12 = new F1(view);
            this.f5701a.put(view, f12);
            return f12;
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public boolean J(View view) {
            if (f5703d) {
                return false;
            }
            if (f5702c == null) {
                try {
                    Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                    f5702c = declaredField;
                    declaredField.setAccessible(true);
                } catch (Throwable unused) {
                    f5703d = true;
                    return false;
                }
            }
            try {
                return f5702c.get(view) != null;
            } catch (Throwable unused2) {
                f5703d = true;
                return false;
            }
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public boolean R(View view, int i2) {
            return I0.b(view, i2);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public boolean T(View view, int i2) {
            return I0.a(view, i2);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void y(View view, AbstractC0254a abstractC0254a) {
            I0.c(view, abstractC0254a == null ? null : abstractC0254a.c());
        }
    }

    /* JADX INFO: renamed from: k.k0$f */
    public static class f extends d {
        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void A(View view, int i2) {
            if (i2 == 4) {
                i2 = 2;
            }
            V0.j(view, i2);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public int N(View view) {
            return V0.c(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public boolean Q(View view) {
            return V0.d(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public int c(View view) {
            return V0.b(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void d(View view) {
            V0.f(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public boolean f(View view) {
            return V0.e(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public int j(View view) {
            return V0.a(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void m(View view) {
            V0.i(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void q(View view, Runnable runnable, long j2) {
            V0.h(view, runnable, j2);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void t(View view, Runnable runnable) {
            V0.g(view, runnable);
        }
    }

    /* JADX INFO: renamed from: k.k0$g */
    public static class g extends f {
        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public int E(View view) {
            return Y0.a(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public int s(View view) {
            return Y0.b(view);
        }
    }

    /* JADX INFO: renamed from: k.k0$h */
    public static class h extends g {
    }

    /* JADX INFO: renamed from: k.k0$i */
    public static class i extends h {
        @Override // k.AbstractC0285k0.f, k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void A(View view, int i2) {
            V0.j(view, i2);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public boolean F(View view) {
            return AbstractC0259b1.a(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public boolean b(View view) {
            return AbstractC0259b1.b(view);
        }
    }

    /* JADX INFO: renamed from: k.k0$j */
    public static class j extends i {

        /* JADX INFO: renamed from: k.k0$j$a */
        public class a implements AbstractC0304q1.b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ InterfaceC0264d0 f5704a;

            public a(InterfaceC0264d0 interfaceC0264d0) {
                this.f5704a = interfaceC0264d0;
            }

            @Override // k.AbstractC0304q1.b
            public Object a(View view, Object obj) {
                return W1.g(this.f5704a.a(view, W1.h(obj)));
            }
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public PorterDuff.Mode I(View view) {
            return AbstractC0304q1.c(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public W1 K(View view, W1 w1) {
            return W1.h(AbstractC0304q1.a(view, W1.g(w1)));
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public float L(View view) {
            return AbstractC0304q1.d(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void U(View view) {
            AbstractC0304q1.n(view);
        }

        @Override // k.AbstractC0285k0.c, k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void Y(View view, int i2) {
            AbstractC0304q1.g(view, i2);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public W1 a(View view, W1 w1) {
            return W1.h(AbstractC0304q1.h(view, W1.g(w1)));
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public boolean e(View view) {
            return AbstractC0304q1.f(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void i(View view, ColorStateList colorStateList) {
            AbstractC0304q1.j(view, colorStateList);
        }

        @Override // k.AbstractC0285k0.f, k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void m(View view) {
            AbstractC0304q1.i(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void o(View view, float f2) {
            AbstractC0304q1.l(view, f2);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public ColorStateList v(View view) {
            return AbstractC0304q1.b(view);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void w(View view, PorterDuff.Mode mode) {
            AbstractC0304q1.k(view, mode);
        }

        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void x(View view, InterfaceC0264d0 interfaceC0264d0) {
            if (interfaceC0264d0 == null) {
                AbstractC0304q1.m(view, null);
            } else {
                AbstractC0304q1.m(view, new a(interfaceC0264d0));
            }
        }
    }

    /* JADX INFO: renamed from: k.k0$k */
    public static class k extends j {
        @Override // k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void P(View view, int i2, int i3) {
            AbstractC0309s1.b(view, i2, i3);
        }

        @Override // k.AbstractC0285k0.j, k.AbstractC0285k0.c, k.AbstractC0285k0.b, k.AbstractC0285k0.l
        public void Y(View view, int i2) {
            AbstractC0309s1.a(view, i2);
        }
    }

    /* JADX INFO: renamed from: k.k0$l */
    public interface l {
        void A(View view, int i2);

        void B(View view, boolean z2);

        void C(View view, int i2, Paint paint);

        float D(View view);

        int E(View view);

        boolean F(View view);

        F1 G(View view);

        int H(int i2, int i3, int i4);

        PorterDuff.Mode I(View view);

        boolean J(View view);

        W1 K(View view, W1 w1);

        float L(View view);

        void M(View view, float f2);

        int N(View view);

        float O(View view);

        void P(View view, int i2, int i3);

        boolean Q(View view);

        boolean R(View view, int i2);

        void S(View view, float f2);

        boolean T(View view, int i2);

        void U(View view);

        void V(View view);

        boolean W(View view);

        int X(View view);

        void Y(View view, int i2);

        W1 a(View view, W1 w1);

        boolean b(View view);

        int c(View view);

        void d(View view);

        boolean e(View view);

        boolean f(View view);

        void g(ViewGroup viewGroup, boolean z2);

        float h(View view);

        void i(View view, ColorStateList colorStateList);

        int j(View view);

        int k(View view);

        void l(View view, float f2);

        void m(View view);

        float n(View view);

        void o(View view, float f2);

        void p(View view, float f2);

        void q(View view, Runnable runnable, long j2);

        Matrix r(View view);

        int s(View view);

        void t(View view, Runnable runnable);

        void u(View view, float f2);

        ColorStateList v(View view);

        void w(View view, PorterDuff.Mode mode);

        void x(View view, InterfaceC0264d0 interfaceC0264d0);

        void y(View view, AbstractC0254a abstractC0254a);

        int z(View view);
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (AbstractC0250a.a()) {
            f5699a = new a();
            return;
        }
        if (i2 >= 23) {
            f5699a = new k();
            return;
        }
        if (i2 >= 21) {
            f5699a = new j();
            return;
        }
        if (i2 >= 19) {
            f5699a = new i();
            return;
        }
        if (i2 >= 18) {
            f5699a = new h();
            return;
        }
        if (i2 >= 17) {
            f5699a = new g();
            return;
        }
        if (i2 >= 16) {
            f5699a = new f();
            return;
        }
        if (i2 >= 15) {
            f5699a = new d();
            return;
        }
        if (i2 >= 14) {
            f5699a = new e();
        } else if (i2 >= 11) {
            f5699a = new c();
        } else {
            f5699a = new b();
        }
    }

    public static boolean A(View view) {
        return f5699a.e(view);
    }

    public static void B(View view) {
        f5699a.V(view);
    }

    public static void C(View view, int i2) {
        f5699a.Y(view, i2);
    }

    public static W1 D(View view, W1 w1) {
        return f5699a.a(view, w1);
    }

    public static void E(View view) {
        f5699a.d(view);
    }

    public static void F(View view, Runnable runnable) {
        f5699a.t(view, runnable);
    }

    public static void G(View view, Runnable runnable, long j2) {
        f5699a.q(view, runnable, j2);
    }

    public static void H(View view) {
        f5699a.m(view);
    }

    public static int I(int i2, int i3, int i4) {
        return f5699a.H(i2, i3, i4);
    }

    public static void J(View view, AbstractC0254a abstractC0254a) {
        f5699a.y(view, abstractC0254a);
    }

    public static void K(View view, float f2) {
        f5699a.M(view, f2);
    }

    public static void L(View view, ColorStateList colorStateList) {
        f5699a.i(view, colorStateList);
    }

    public static void M(View view, PorterDuff.Mode mode) {
        f5699a.w(view, mode);
    }

    public static void N(ViewGroup viewGroup, boolean z2) {
        f5699a.g(viewGroup, z2);
    }

    public static void O(View view, float f2) {
        f5699a.o(view, f2);
    }

    public static void P(View view, int i2) {
        f5699a.A(view, i2);
    }

    public static void Q(View view, int i2, Paint paint) {
        f5699a.C(view, i2, paint);
    }

    public static void R(View view, InterfaceC0264d0 interfaceC0264d0) {
        f5699a.x(view, interfaceC0264d0);
    }

    public static void S(View view, boolean z2) {
        f5699a.B(view, z2);
    }

    public static void T(View view, float f2) {
        f5699a.p(view, f2);
    }

    public static void U(View view, float f2) {
        f5699a.l(view, f2);
    }

    public static void V(View view, int i2, int i3) {
        f5699a.P(view, i2, i3);
    }

    public static void W(View view, float f2) {
        f5699a.S(view, f2);
    }

    public static void X(View view, float f2) {
        f5699a.u(view, f2);
    }

    public static void Y(View view) {
        f5699a.U(view);
    }

    public static F1 a(View view) {
        return f5699a.G(view);
    }

    public static boolean b(View view, int i2) {
        return f5699a.T(view, i2);
    }

    public static boolean c(View view, int i2) {
        return f5699a.R(view, i2);
    }

    public static W1 d(View view, W1 w1) {
        return f5699a.K(view, w1);
    }

    public static float e(View view) {
        return f5699a.n(view);
    }

    public static ColorStateList f(View view) {
        return f5699a.v(view);
    }

    public static PorterDuff.Mode g(View view) {
        return f5699a.I(view);
    }

    public static float h(View view) {
        return f5699a.L(view);
    }

    public static int i(View view) {
        return f5699a.j(view);
    }

    public static int j(View view) {
        return f5699a.z(view);
    }

    public static int k(View view) {
        return f5699a.E(view);
    }

    public static Matrix l(View view) {
        return f5699a.r(view);
    }

    public static int m(View view) {
        return f5699a.k(view);
    }

    public static int n(View view) {
        return f5699a.X(view);
    }

    public static int o(View view) {
        return f5699a.c(view);
    }

    public static int p(View view) {
        return f5699a.N(view);
    }

    public static float q(View view) {
        return f5699a.D(view);
    }

    public static float r(View view) {
        return f5699a.h(view);
    }

    public static float s(View view) {
        return f5699a.O(view);
    }

    public static int t(View view) {
        return f5699a.s(view);
    }

    public static boolean u(View view) {
        return f5699a.J(view);
    }

    public static boolean v(View view) {
        return f5699a.W(view);
    }

    public static boolean w(View view) {
        return f5699a.Q(view);
    }

    public static boolean x(View view) {
        return f5699a.f(view);
    }

    public static boolean y(View view) {
        return f5699a.F(view);
    }

    public static boolean z(View view) {
        return f5699a.b(view);
    }

    /* JADX INFO: renamed from: k.k0$b */
    public static class b implements l {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static Method f5700b;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakHashMap f5701a = null;

        @Override // k.AbstractC0285k0.l
        public float D(View view) {
            return 0.0f;
        }

        @Override // k.AbstractC0285k0.l
        public int E(View view) {
            return 0;
        }

        @Override // k.AbstractC0285k0.l
        public boolean F(View view) {
            return AbstractC0288l0.e(view);
        }

        @Override // k.AbstractC0285k0.l
        public F1 G(View view) {
            return new F1(view);
        }

        @Override // k.AbstractC0285k0.l
        public int H(int i2, int i3, int i4) {
            return View.resolveSize(i2, i3);
        }

        @Override // k.AbstractC0285k0.l
        public PorterDuff.Mode I(View view) {
            return AbstractC0288l0.b(view);
        }

        @Override // k.AbstractC0285k0.l
        public boolean J(View view) {
            return false;
        }

        @Override // k.AbstractC0285k0.l
        public float L(View view) {
            return 0.0f;
        }

        @Override // k.AbstractC0285k0.l
        public int N(View view) {
            return AbstractC0288l0.d(view);
        }

        @Override // k.AbstractC0285k0.l
        public float O(View view) {
            return 0.0f;
        }

        @Override // k.AbstractC0285k0.l
        public boolean Q(View view) {
            return true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // k.AbstractC0285k0.l
        public boolean R(View view, int i2) {
            return (view instanceof InterfaceC0273g0) && a0((InterfaceC0273g0) view, i2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // k.AbstractC0285k0.l
        public boolean T(View view, int i2) {
            return (view instanceof InterfaceC0273g0) && Z((InterfaceC0273g0) view, i2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // k.AbstractC0285k0.l
        public void U(View view) {
            if (view instanceof Z) {
                ((Z) view).stopNestedScroll();
            }
        }

        @Override // k.AbstractC0285k0.l
        public boolean W(View view) {
            return false;
        }

        @Override // k.AbstractC0285k0.l
        public int X(View view) {
            return view.getMeasuredWidth();
        }

        @Override // k.AbstractC0285k0.l
        public void Y(View view, int i2) {
            AbstractC0288l0.g(view, i2);
        }

        public final boolean Z(InterfaceC0273g0 interfaceC0273g0, int i2) {
            int iComputeHorizontalScrollOffset = interfaceC0273g0.computeHorizontalScrollOffset();
            int iComputeHorizontalScrollRange = interfaceC0273g0.computeHorizontalScrollRange() - interfaceC0273g0.computeHorizontalScrollExtent();
            if (iComputeHorizontalScrollRange == 0) {
                return false;
            }
            return i2 < 0 ? iComputeHorizontalScrollOffset > 0 : iComputeHorizontalScrollOffset < iComputeHorizontalScrollRange - 1;
        }

        public final boolean a0(InterfaceC0273g0 interfaceC0273g0, int i2) {
            int iComputeVerticalScrollOffset = interfaceC0273g0.computeVerticalScrollOffset();
            int iComputeVerticalScrollRange = interfaceC0273g0.computeVerticalScrollRange() - interfaceC0273g0.computeVerticalScrollExtent();
            if (iComputeVerticalScrollRange == 0) {
                return false;
            }
            return i2 < 0 ? iComputeVerticalScrollOffset > 0 : iComputeVerticalScrollOffset < iComputeVerticalScrollRange - 1;
        }

        @Override // k.AbstractC0285k0.l
        public boolean b(View view) {
            return AbstractC0288l0.f(view);
        }

        public long b0() {
            return 10L;
        }

        @Override // k.AbstractC0285k0.l
        public int c(View view) {
            return AbstractC0288l0.c(view);
        }

        @Override // k.AbstractC0285k0.l
        public void d(View view) {
            view.invalidate();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // k.AbstractC0285k0.l
        public boolean e(View view) {
            if (view instanceof Z) {
                return ((Z) view).isNestedScrollingEnabled();
            }
            return false;
        }

        @Override // k.AbstractC0285k0.l
        public boolean f(View view) {
            return false;
        }

        @Override // k.AbstractC0285k0.l
        public void g(ViewGroup viewGroup, boolean z2) {
            if (f5700b == null) {
                try {
                    f5700b = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", Boolean.TYPE);
                } catch (NoSuchMethodException e2) {
                    Log.e("ViewCompat", "Unable to find childrenDrawingOrderEnabled", e2);
                }
                f5700b.setAccessible(true);
            }
            try {
                f5700b.invoke(viewGroup, Boolean.valueOf(z2));
            } catch (IllegalAccessException e3) {
                Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", e3);
            } catch (IllegalArgumentException e4) {
                Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", e4);
            } catch (InvocationTargetException e5) {
                Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", e5);
            }
        }

        @Override // k.AbstractC0285k0.l
        public float h(View view) {
            return 0.0f;
        }

        @Override // k.AbstractC0285k0.l
        public void i(View view, ColorStateList colorStateList) {
            AbstractC0288l0.h(view, colorStateList);
        }

        @Override // k.AbstractC0285k0.l
        public int j(View view) {
            return 0;
        }

        @Override // k.AbstractC0285k0.l
        public int k(View view) {
            return 0;
        }

        @Override // k.AbstractC0285k0.l
        public float n(View view) {
            return 1.0f;
        }

        @Override // k.AbstractC0285k0.l
        public void q(View view, Runnable runnable, long j2) {
            view.postDelayed(runnable, b0() + j2);
        }

        @Override // k.AbstractC0285k0.l
        public Matrix r(View view) {
            return null;
        }

        @Override // k.AbstractC0285k0.l
        public int s(View view) {
            return 0;
        }

        @Override // k.AbstractC0285k0.l
        public void t(View view, Runnable runnable) {
            view.postDelayed(runnable, b0());
        }

        @Override // k.AbstractC0285k0.l
        public ColorStateList v(View view) {
            return AbstractC0288l0.a(view);
        }

        @Override // k.AbstractC0285k0.l
        public void w(View view, PorterDuff.Mode mode) {
            AbstractC0288l0.i(view, mode);
        }

        @Override // k.AbstractC0285k0.l
        public int z(View view) {
            return 0;
        }

        @Override // k.AbstractC0285k0.l
        public void V(View view) {
        }

        @Override // k.AbstractC0285k0.l
        public void m(View view) {
        }

        @Override // k.AbstractC0285k0.l
        public void A(View view, int i2) {
        }

        @Override // k.AbstractC0285k0.l
        public void B(View view, boolean z2) {
        }

        @Override // k.AbstractC0285k0.l
        public W1 K(View view, W1 w1) {
            return w1;
        }

        @Override // k.AbstractC0285k0.l
        public void M(View view, float f2) {
        }

        @Override // k.AbstractC0285k0.l
        public void S(View view, float f2) {
        }

        @Override // k.AbstractC0285k0.l
        public W1 a(View view, W1 w1) {
            return w1;
        }

        @Override // k.AbstractC0285k0.l
        public void l(View view, float f2) {
        }

        @Override // k.AbstractC0285k0.l
        public void o(View view, float f2) {
        }

        @Override // k.AbstractC0285k0.l
        public void p(View view, float f2) {
        }

        @Override // k.AbstractC0285k0.l
        public void u(View view, float f2) {
        }

        @Override // k.AbstractC0285k0.l
        public void x(View view, InterfaceC0264d0 interfaceC0264d0) {
        }

        @Override // k.AbstractC0285k0.l
        public void y(View view, AbstractC0254a abstractC0254a) {
        }

        @Override // k.AbstractC0285k0.l
        public void C(View view, int i2, Paint paint) {
        }

        @Override // k.AbstractC0285k0.l
        public void P(View view, int i2, int i3) {
        }
    }
}
