package l;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class N {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c f5749b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f5750a;

    public static class a extends e {
        @Override // l.N.e, l.N.c
        public void a(Object obj, boolean z2) {
            W.e(obj, z2);
        }

        @Override // l.N.e, l.N.c
        public void b(Object obj, int i2) {
            W.a(obj, i2);
        }

        @Override // l.N.e, l.N.c
        public void c(Object obj, int i2) {
            W.b(obj, i2);
        }

        @Override // l.N.e, l.N.c
        public void d(Object obj, int i2) {
            W.d(obj, i2);
        }

        @Override // l.N.e, l.N.c
        public void f(Object obj, int i2) {
            W.f(obj, i2);
        }

        @Override // l.N.e, l.N.c
        public void g(Object obj, int i2) {
            W.c(obj, i2);
        }
    }

    public static class b extends a {
        @Override // l.N.e, l.N.c
        public void e(Object obj, int i2) {
            Z.b(obj, i2);
        }

        @Override // l.N.e, l.N.c
        public void h(Object obj, int i2) {
            Z.a(obj, i2);
        }
    }

    public interface c {
        void a(Object obj, boolean z2);

        void b(Object obj, int i2);

        void c(Object obj, int i2);

        void d(Object obj, int i2);

        void e(Object obj, int i2);

        void f(Object obj, int i2);

        void g(Object obj, int i2);

        void h(Object obj, int i2);
    }

    public static class d extends b {
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 16) {
            f5749b = new d();
            return;
        }
        if (i2 >= 15) {
            f5749b = new b();
        } else if (i2 >= 14) {
            f5749b = new a();
        } else {
            f5749b = new e();
        }
    }

    public N(Object obj) {
        this.f5750a = obj;
    }

    public void a(int i2) {
        f5749b.b(this.f5750a, i2);
    }

    public void b(int i2) {
        f5749b.c(this.f5750a, i2);
    }

    public void c(int i2) {
        f5749b.h(this.f5750a, i2);
    }

    public void d(int i2) {
        f5749b.e(this.f5750a, i2);
    }

    public void e(int i2) {
        f5749b.g(this.f5750a, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        N n2 = (N) obj;
        Object obj2 = this.f5750a;
        if (obj2 == null) {
            if (n2.f5750a != null) {
                return false;
            }
        } else if (!obj2.equals(n2.f5750a)) {
            return false;
        }
        return true;
    }

    public void f(int i2) {
        f5749b.d(this.f5750a, i2);
    }

    public void g(boolean z2) {
        f5749b.a(this.f5750a, z2);
    }

    public void h(int i2) {
        f5749b.f(this.f5750a, i2);
    }

    public int hashCode() {
        Object obj = this.f5750a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public static class e implements c {
        @Override // l.N.c
        public void a(Object obj, boolean z2) {
        }

        @Override // l.N.c
        public void b(Object obj, int i2) {
        }

        @Override // l.N.c
        public void c(Object obj, int i2) {
        }

        @Override // l.N.c
        public void d(Object obj, int i2) {
        }

        @Override // l.N.c
        public void e(Object obj, int i2) {
        }

        @Override // l.N.c
        public void f(Object obj, int i2) {
        }

        @Override // l.N.c
        public void g(Object obj, int i2) {
        }

        @Override // l.N.c
        public void h(Object obj, int i2) {
        }
    }
}
