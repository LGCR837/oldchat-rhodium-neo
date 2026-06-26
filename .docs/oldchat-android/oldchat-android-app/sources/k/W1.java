package k;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class W1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final d f5679b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f5680a;

    public static class a extends c {
        @Override // k.W1.c, k.W1.d
        public int a(Object obj) {
            return c2.c(obj);
        }

        @Override // k.W1.c, k.W1.d
        public int b(Object obj) {
            return c2.b(obj);
        }

        @Override // k.W1.c, k.W1.d
        public int c(Object obj) {
            return c2.a(obj);
        }

        @Override // k.W1.c, k.W1.d
        public int d(Object obj) {
            return c2.d(obj);
        }

        @Override // k.W1.c, k.W1.d
        public W1 e(Object obj, int i2, int i3, int i4, int i5) {
            return new W1(c2.e(obj, i2, i3, i4, i5));
        }
    }

    public static class b extends a {
        @Override // k.W1.c, k.W1.d
        public boolean f(Object obj) {
            return e2.a(obj);
        }
    }

    public static class c implements d {
        @Override // k.W1.d
        public int a(Object obj) {
            return 0;
        }

        @Override // k.W1.d
        public int b(Object obj) {
            return 0;
        }

        @Override // k.W1.d
        public int c(Object obj) {
            return 0;
        }

        @Override // k.W1.d
        public int d(Object obj) {
            return 0;
        }

        @Override // k.W1.d
        public W1 e(Object obj, int i2, int i3, int i4, int i5) {
            return null;
        }

        @Override // k.W1.d
        public boolean f(Object obj) {
            return false;
        }
    }

    public interface d {
        int a(Object obj);

        int b(Object obj);

        int c(Object obj);

        int d(Object obj);

        W1 e(Object obj, int i2, int i3, int i4, int i5);

        boolean f(Object obj);
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            f5679b = new b();
        } else if (i2 >= 20) {
            f5679b = new a();
        } else {
            f5679b = new c();
        }
    }

    public W1(Object obj) {
        this.f5680a = obj;
    }

    public static Object g(W1 w1) {
        if (w1 == null) {
            return null;
        }
        return w1.f5680a;
    }

    public static W1 h(Object obj) {
        if (obj == null) {
            return null;
        }
        return new W1(obj);
    }

    public int a() {
        return f5679b.c(this.f5680a);
    }

    public int b() {
        return f5679b.b(this.f5680a);
    }

    public int c() {
        return f5679b.a(this.f5680a);
    }

    public int d() {
        return f5679b.d(this.f5680a);
    }

    public boolean e() {
        return f5679b.f(this.f5680a);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Object obj2 = this.f5680a;
        Object obj3 = ((W1) obj).f5680a;
        return obj2 == null ? obj3 == null : obj2.equals(obj3);
    }

    public W1 f(int i2, int i3, int i4, int i5) {
        return f5679b.e(this.f5680a, i2, i3, i4, i5);
    }

    public int hashCode() {
        Object obj = this.f5680a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }
}
