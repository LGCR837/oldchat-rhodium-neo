package k;

import android.os.Build;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class F1 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final g f5658e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WeakReference f5659a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Runnable f5660b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Runnable f5661c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5662d = -1;

    public static class b extends a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public WeakHashMap f5667b = null;

        public static class a implements T1 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public F1 f5668a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public boolean f5669b;

            public a(F1 f1) {
                this.f5668a = f1;
            }

            @Override // k.T1
            public void a(View view) {
                int i2 = this.f5668a.f5662d;
                if (i2 >= 0) {
                    AbstractC0285k0.Q(view, i2, null);
                    this.f5668a.f5662d = -1;
                }
                if (Build.VERSION.SDK_INT >= 16 || !this.f5669b) {
                    F1 f1 = this.f5668a;
                    Runnable runnable = f1.f5661c;
                    if (runnable != null) {
                        f1.f5661c = null;
                        runnable.run();
                    }
                    Object tag = view.getTag(2113929216);
                    T1 t1 = tag instanceof T1 ? (T1) tag : null;
                    if (t1 != null) {
                        t1.a(view);
                    }
                    this.f5669b = true;
                }
            }

            @Override // k.T1
            public void b(View view) {
                this.f5669b = false;
                if (this.f5668a.f5662d >= 0) {
                    AbstractC0285k0.Q(view, 2, null);
                }
                F1 f1 = this.f5668a;
                Runnable runnable = f1.f5660b;
                if (runnable != null) {
                    f1.f5660b = null;
                    runnable.run();
                }
                Object tag = view.getTag(2113929216);
                T1 t1 = tag instanceof T1 ? (T1) tag : null;
                if (t1 != null) {
                    t1.b(view);
                }
            }

            @Override // k.T1
            public void c(View view) {
                Object tag = view.getTag(2113929216);
                T1 t1 = tag instanceof T1 ? (T1) tag : null;
                if (t1 != null) {
                    t1.c(view);
                }
            }
        }

        @Override // k.F1.a, k.F1.g
        public void a(F1 f1, View view) {
            P1.b(view);
        }

        @Override // k.F1.a, k.F1.g
        public void b(F1 f1, View view, Interpolator interpolator) {
            P1.e(view, interpolator);
        }

        @Override // k.F1.a, k.F1.g
        public void c(F1 f1, View view, float f2) {
            P1.i(view, f2);
        }

        @Override // k.F1.a, k.F1.g
        public void e(F1 f1, View view) {
            P1.h(view);
        }

        @Override // k.F1.a, k.F1.g
        public void f(F1 f1, View view, long j2) {
            P1.d(view, j2);
        }

        @Override // k.F1.a, k.F1.g
        public void g(F1 f1, View view, float f2) {
            P1.j(view, f2);
        }

        @Override // k.F1.a, k.F1.g
        public void h(F1 f1, View view, long j2) {
            P1.g(view, j2);
        }

        @Override // k.F1.a, k.F1.g
        public void i(F1 f1, View view, T1 t1) {
            view.setTag(2113929216, t1);
            P1.f(view, new a(f1));
        }

        @Override // k.F1.a, k.F1.g
        public long j(F1 f1, View view) {
            return P1.c(view);
        }

        @Override // k.F1.a, k.F1.g
        public void k(F1 f1, View view, float f2) {
            P1.a(view, f2);
        }
    }

    public static class c extends d {
    }

    public static class d extends b {
        @Override // k.F1.b, k.F1.a, k.F1.g
        public void i(F1 f1, View view, T1 t1) {
            Q1.a(view, t1);
        }
    }

    public static class e extends c {
        @Override // k.F1.a, k.F1.g
        public void d(F1 f1, View view, V1 v1) {
            S1.a(view, v1);
        }
    }

    public static class f extends e {
    }

    public interface g {
        void a(F1 f1, View view);

        void b(F1 f1, View view, Interpolator interpolator);

        void c(F1 f1, View view, float f2);

        void d(F1 f1, View view, V1 v1);

        void e(F1 f1, View view);

        void f(F1 f1, View view, long j2);

        void g(F1 f1, View view, float f2);

        void h(F1 f1, View view, long j2);

        void i(F1 f1, View view, T1 t1);

        long j(F1 f1, View view);

        void k(F1 f1, View view, float f2);
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            f5658e = new f();
            return;
        }
        if (i2 >= 19) {
            f5658e = new e();
            return;
        }
        if (i2 >= 18) {
            f5658e = new c();
            return;
        }
        if (i2 >= 16) {
            f5658e = new d();
        } else if (i2 >= 14) {
            f5658e = new b();
        } else {
            f5658e = new a();
        }
    }

    public F1(View view) {
        this.f5659a = new WeakReference(view);
    }

    public F1 a(float f2) {
        View view = (View) this.f5659a.get();
        if (view != null) {
            f5658e.k(this, view, f2);
        }
        return this;
    }

    public void b() {
        View view = (View) this.f5659a.get();
        if (view != null) {
            f5658e.a(this, view);
        }
    }

    public long c() {
        View view = (View) this.f5659a.get();
        if (view != null) {
            return f5658e.j(this, view);
        }
        return 0L;
    }

    public F1 d(long j2) {
        View view = (View) this.f5659a.get();
        if (view != null) {
            f5658e.f(this, view, j2);
        }
        return this;
    }

    public F1 e(Interpolator interpolator) {
        View view = (View) this.f5659a.get();
        if (view != null) {
            f5658e.b(this, view, interpolator);
        }
        return this;
    }

    public F1 f(T1 t1) {
        View view = (View) this.f5659a.get();
        if (view != null) {
            f5658e.i(this, view, t1);
        }
        return this;
    }

    public F1 g(long j2) {
        View view = (View) this.f5659a.get();
        if (view != null) {
            f5658e.h(this, view, j2);
        }
        return this;
    }

    public F1 h(V1 v1) {
        View view = (View) this.f5659a.get();
        if (view != null) {
            f5658e.d(this, view, v1);
        }
        return this;
    }

    public void i() {
        View view = (View) this.f5659a.get();
        if (view != null) {
            f5658e.e(this, view);
        }
    }

    public F1 j(float f2) {
        View view = (View) this.f5659a.get();
        if (view != null) {
            f5658e.c(this, view, f2);
        }
        return this;
    }

    public F1 k(float f2) {
        View view = (View) this.f5659a.get();
        if (view != null) {
            f5658e.g(this, view, f2);
        }
        return this;
    }

    public static class a implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakHashMap f5663a = null;

        /* JADX INFO: renamed from: k.F1$a$a, reason: collision with other inner class name */
        public class RunnableC0044a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public WeakReference f5664a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public F1 f5665b;

            public RunnableC0044a(F1 f1, View view) {
                this.f5664a = new WeakReference(view);
                this.f5665b = f1;
            }

            @Override // java.lang.Runnable
            public void run() {
                View view = (View) this.f5664a.get();
                if (view != null) {
                    a.this.n(this.f5665b, view);
                }
            }
        }

        @Override // k.F1.g
        public void a(F1 f1, View view) {
            l(f1, view);
        }

        @Override // k.F1.g
        public void c(F1 f1, View view, float f2) {
            l(f1, view);
        }

        @Override // k.F1.g
        public void e(F1 f1, View view) {
            m(view);
            n(f1, view);
        }

        @Override // k.F1.g
        public void g(F1 f1, View view, float f2) {
            l(f1, view);
        }

        @Override // k.F1.g
        public void i(F1 f1, View view, T1 t1) {
            view.setTag(2113929216, t1);
        }

        @Override // k.F1.g
        public long j(F1 f1, View view) {
            return 0L;
        }

        @Override // k.F1.g
        public void k(F1 f1, View view, float f2) {
            l(f1, view);
        }

        public final void l(F1 f1, View view) {
            WeakHashMap weakHashMap = this.f5663a;
            Runnable runnableC0044a = weakHashMap != null ? (Runnable) weakHashMap.get(view) : null;
            if (runnableC0044a == null) {
                runnableC0044a = new RunnableC0044a(f1, view);
                if (this.f5663a == null) {
                    this.f5663a = new WeakHashMap();
                }
                this.f5663a.put(view, runnableC0044a);
            }
            view.removeCallbacks(runnableC0044a);
            view.post(runnableC0044a);
        }

        public final void m(View view) {
            Runnable runnable;
            WeakHashMap weakHashMap = this.f5663a;
            if (weakHashMap == null || (runnable = (Runnable) weakHashMap.get(view)) == null) {
                return;
            }
            view.removeCallbacks(runnable);
        }

        public void n(F1 f1, View view) {
            Object tag = view.getTag(2113929216);
            T1 t1 = tag instanceof T1 ? (T1) tag : null;
            Runnable runnable = f1.f5660b;
            Runnable runnable2 = f1.f5661c;
            f1.f5660b = null;
            f1.f5661c = null;
            if (runnable != null) {
                runnable.run();
            }
            if (t1 != null) {
                t1.b(view);
                t1.a(view);
            }
            if (runnable2 != null) {
                runnable2.run();
            }
            WeakHashMap weakHashMap = this.f5663a;
            if (weakHashMap != null) {
                weakHashMap.remove(view);
            }
        }

        @Override // k.F1.g
        public void b(F1 f1, View view, Interpolator interpolator) {
        }

        @Override // k.F1.g
        public void d(F1 f1, View view, V1 v1) {
        }

        @Override // k.F1.g
        public void f(F1 f1, View view, long j2) {
        }

        @Override // k.F1.g
        public void h(F1 f1, View view, long j2) {
        }
    }
}
