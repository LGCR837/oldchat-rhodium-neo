package k;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import k.AbstractC0284k;
import k.AbstractC0296o;
import l.C0347e;

/* JADX INFO: renamed from: k.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0254a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f5681b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f5682c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f5683a = f5681b.a(this);

    /* JADX INFO: renamed from: k.a$a, reason: collision with other inner class name */
    public static class C0045a extends d {

        /* JADX INFO: renamed from: k.a$a$a, reason: collision with other inner class name */
        public class C0046a implements AbstractC0284k.b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ AbstractC0254a f5684a;

            public C0046a(AbstractC0254a abstractC0254a) {
                this.f5684a = abstractC0254a;
            }

            @Override // k.AbstractC0284k.b
            public boolean a(View view, AccessibilityEvent accessibilityEvent) {
                return this.f5684a.a(view, accessibilityEvent);
            }

            @Override // k.AbstractC0284k.b
            public void b(View view, Object obj) {
                this.f5684a.e(view, new C0347e(obj));
            }

            @Override // k.AbstractC0284k.b
            public void c(View view, AccessibilityEvent accessibilityEvent) {
                this.f5684a.f(view, accessibilityEvent);
            }

            @Override // k.AbstractC0284k.b
            public void d(View view, AccessibilityEvent accessibilityEvent) {
                this.f5684a.j(view, accessibilityEvent);
            }

            @Override // k.AbstractC0284k.b
            public void e(View view, AccessibilityEvent accessibilityEvent) {
                this.f5684a.d(view, accessibilityEvent);
            }

            @Override // k.AbstractC0284k.b
            public boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
                return this.f5684a.g(viewGroup, view, accessibilityEvent);
            }

            @Override // k.AbstractC0284k.b
            public void g(View view, int i2) {
                this.f5684a.i(view, i2);
            }
        }

        @Override // k.AbstractC0254a.d, k.AbstractC0254a.b
        public Object a(AbstractC0254a abstractC0254a) {
            return AbstractC0284k.b(new C0046a(abstractC0254a));
        }

        @Override // k.AbstractC0254a.d, k.AbstractC0254a.b
        public void b(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            AbstractC0284k.f(obj, view, accessibilityEvent);
        }

        @Override // k.AbstractC0254a.d, k.AbstractC0254a.b
        public boolean c(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            return AbstractC0284k.a(obj, view, accessibilityEvent);
        }

        @Override // k.AbstractC0254a.d, k.AbstractC0254a.b
        public Object d() {
            return AbstractC0284k.c();
        }

        @Override // k.AbstractC0254a.d, k.AbstractC0254a.b
        public void e(Object obj, View view, C0347e c0347e) {
            AbstractC0284k.e(obj, view, c0347e.h());
        }

        @Override // k.AbstractC0254a.d, k.AbstractC0254a.b
        public void g(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            AbstractC0284k.i(obj, view, accessibilityEvent);
        }

        @Override // k.AbstractC0254a.d, k.AbstractC0254a.b
        public void i(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            AbstractC0284k.d(obj, view, accessibilityEvent);
        }

        @Override // k.AbstractC0254a.d, k.AbstractC0254a.b
        public void j(Object obj, View view, int i2) {
            AbstractC0284k.h(obj, view, i2);
        }

        @Override // k.AbstractC0254a.d, k.AbstractC0254a.b
        public boolean k(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return AbstractC0284k.g(obj, viewGroup, view, accessibilityEvent);
        }
    }

    /* JADX INFO: renamed from: k.a$b */
    public interface b {
        Object a(AbstractC0254a abstractC0254a);

        void b(Object obj, View view, AccessibilityEvent accessibilityEvent);

        boolean c(Object obj, View view, AccessibilityEvent accessibilityEvent);

        Object d();

        void e(Object obj, View view, C0347e c0347e);

        boolean f(Object obj, View view, int i2, Bundle bundle);

        void g(Object obj, View view, AccessibilityEvent accessibilityEvent);

        l.M h(Object obj, View view);

        void i(Object obj, View view, AccessibilityEvent accessibilityEvent);

        void j(Object obj, View view, int i2);

        boolean k(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent);
    }

    /* JADX INFO: renamed from: k.a$c */
    public static class c extends C0045a {

        /* JADX INFO: renamed from: k.a$c$a, reason: collision with other inner class name */
        public class C0047a implements AbstractC0296o.b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ AbstractC0254a f5686a;

            public C0047a(AbstractC0254a abstractC0254a) {
                this.f5686a = abstractC0254a;
            }

            @Override // k.AbstractC0296o.b
            public boolean a(View view, AccessibilityEvent accessibilityEvent) {
                return this.f5686a.a(view, accessibilityEvent);
            }

            @Override // k.AbstractC0296o.b
            public void b(View view, Object obj) {
                this.f5686a.e(view, new C0347e(obj));
            }

            @Override // k.AbstractC0296o.b
            public void c(View view, AccessibilityEvent accessibilityEvent) {
                this.f5686a.f(view, accessibilityEvent);
            }

            @Override // k.AbstractC0296o.b
            public void d(View view, AccessibilityEvent accessibilityEvent) {
                this.f5686a.j(view, accessibilityEvent);
            }

            @Override // k.AbstractC0296o.b
            public void e(View view, AccessibilityEvent accessibilityEvent) {
                this.f5686a.d(view, accessibilityEvent);
            }

            @Override // k.AbstractC0296o.b
            public boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
                return this.f5686a.g(viewGroup, view, accessibilityEvent);
            }

            @Override // k.AbstractC0296o.b
            public void g(View view, int i2) {
                this.f5686a.i(view, i2);
            }

            @Override // k.AbstractC0296o.b
            public Object h(View view) {
                l.M mB = this.f5686a.b(view);
                if (mB != null) {
                    return mB.a();
                }
                return null;
            }

            @Override // k.AbstractC0296o.b
            public boolean i(View view, int i2, Bundle bundle) {
                return this.f5686a.h(view, i2, bundle);
            }
        }

        @Override // k.AbstractC0254a.C0045a, k.AbstractC0254a.d, k.AbstractC0254a.b
        public Object a(AbstractC0254a abstractC0254a) {
            return AbstractC0296o.b(new C0047a(abstractC0254a));
        }

        @Override // k.AbstractC0254a.d, k.AbstractC0254a.b
        public boolean f(Object obj, View view, int i2, Bundle bundle) {
            return AbstractC0296o.c(obj, view, i2, bundle);
        }

        @Override // k.AbstractC0254a.d, k.AbstractC0254a.b
        public l.M h(Object obj, View view) {
            Object objA = AbstractC0296o.a(obj, view);
            if (objA != null) {
                return new l.M(objA);
            }
            return null;
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 16) {
            f5681b = new c();
        } else if (i2 >= 14) {
            f5681b = new C0045a();
        } else {
            f5681b = new d();
        }
        f5682c = f5681b.d();
    }

    public boolean a(View view, AccessibilityEvent accessibilityEvent) {
        return f5681b.c(f5682c, view, accessibilityEvent);
    }

    public l.M b(View view) {
        return f5681b.h(f5682c, view);
    }

    public Object c() {
        return this.f5683a;
    }

    public void d(View view, AccessibilityEvent accessibilityEvent) {
        f5681b.i(f5682c, view, accessibilityEvent);
    }

    public void e(View view, C0347e c0347e) {
        f5681b.e(f5682c, view, c0347e);
    }

    public void f(View view, AccessibilityEvent accessibilityEvent) {
        f5681b.b(f5682c, view, accessibilityEvent);
    }

    public boolean g(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return f5681b.k(f5682c, viewGroup, view, accessibilityEvent);
    }

    public boolean h(View view, int i2, Bundle bundle) {
        return f5681b.f(f5682c, view, i2, bundle);
    }

    public void i(View view, int i2) {
        f5681b.j(f5682c, view, i2);
    }

    public void j(View view, AccessibilityEvent accessibilityEvent) {
        f5681b.g(f5682c, view, accessibilityEvent);
    }

    /* JADX INFO: renamed from: k.a$d */
    public static class d implements b {
        @Override // k.AbstractC0254a.b
        public Object a(AbstractC0254a abstractC0254a) {
            return null;
        }

        @Override // k.AbstractC0254a.b
        public boolean c(Object obj, View view, AccessibilityEvent accessibilityEvent) {
            return false;
        }

        @Override // k.AbstractC0254a.b
        public Object d() {
            return null;
        }

        @Override // k.AbstractC0254a.b
        public boolean f(Object obj, View view, int i2, Bundle bundle) {
            return false;
        }

        @Override // k.AbstractC0254a.b
        public l.M h(Object obj, View view) {
            return null;
        }

        @Override // k.AbstractC0254a.b
        public boolean k(Object obj, ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return true;
        }

        @Override // k.AbstractC0254a.b
        public void b(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        }

        @Override // k.AbstractC0254a.b
        public void e(Object obj, View view, C0347e c0347e) {
        }

        @Override // k.AbstractC0254a.b
        public void g(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        }

        @Override // k.AbstractC0254a.b
        public void i(Object obj, View view, AccessibilityEvent accessibilityEvent) {
        }

        @Override // k.AbstractC0254a.b
        public void j(Object obj, View view, int i2) {
        }
    }
}
