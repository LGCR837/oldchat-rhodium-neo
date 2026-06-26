package n;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;

/* JADX INFO: renamed from: n.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0427j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c f6690b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f6691a;

    /* JADX INFO: renamed from: n.j$b */
    public static class b implements c {
        @Override // n.C0427j.c
        public boolean a(Object obj, int i2) {
            return s.e(obj, i2);
        }

        @Override // n.C0427j.c
        public boolean b(Object obj) {
            return s.g(obj);
        }

        @Override // n.C0427j.c
        public boolean c(Object obj, float f2) {
            return s.f(obj, f2);
        }

        @Override // n.C0427j.c
        public Object d(Context context) {
            return s.d(context);
        }

        @Override // n.C0427j.c
        public boolean e(Object obj, Canvas canvas) {
            return s.a(obj, canvas);
        }

        @Override // n.C0427j.c
        public boolean f(Object obj, float f2, float f3) {
            return s.f(obj, f2);
        }

        @Override // n.C0427j.c
        public boolean g(Object obj) {
            return s.c(obj);
        }

        @Override // n.C0427j.c
        public void h(Object obj) {
            s.b(obj);
        }

        @Override // n.C0427j.c
        public void i(Object obj, int i2, int i3) {
            s.h(obj, i2, i3);
        }
    }

    /* JADX INFO: renamed from: n.j$c */
    public interface c {
        boolean a(Object obj, int i2);

        boolean b(Object obj);

        boolean c(Object obj, float f2);

        Object d(Context context);

        boolean e(Object obj, Canvas canvas);

        boolean f(Object obj, float f2, float f3);

        boolean g(Object obj);

        void h(Object obj);

        void i(Object obj, int i2, int i3);
    }

    /* JADX INFO: renamed from: n.j$d */
    public static class d extends b {
        @Override // n.C0427j.b, n.C0427j.c
        public boolean f(Object obj, float f2, float f3) {
            return u.a(obj, f2, f3);
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            f6690b = new d();
        } else if (i2 >= 14) {
            f6690b = new b();
        } else {
            f6690b = new a();
        }
    }

    public C0427j(Context context) {
        this.f6691a = f6690b.d(context);
    }

    public boolean a(Canvas canvas) {
        return f6690b.e(this.f6691a, canvas);
    }

    public void b() {
        f6690b.h(this.f6691a);
    }

    public boolean c() {
        return f6690b.g(this.f6691a);
    }

    public boolean d(int i2) {
        return f6690b.a(this.f6691a, i2);
    }

    public boolean e(float f2) {
        return f6690b.c(this.f6691a, f2);
    }

    public boolean f(float f2, float f3) {
        return f6690b.f(this.f6691a, f2, f3);
    }

    public boolean g() {
        return f6690b.b(this.f6691a);
    }

    public void h(int i2, int i3) {
        f6690b.i(this.f6691a, i2, i3);
    }

    /* JADX INFO: renamed from: n.j$a */
    public static class a implements c {
        @Override // n.C0427j.c
        public boolean a(Object obj, int i2) {
            return false;
        }

        @Override // n.C0427j.c
        public boolean b(Object obj) {
            return false;
        }

        @Override // n.C0427j.c
        public boolean c(Object obj, float f2) {
            return false;
        }

        @Override // n.C0427j.c
        public Object d(Context context) {
            return null;
        }

        @Override // n.C0427j.c
        public boolean e(Object obj, Canvas canvas) {
            return false;
        }

        @Override // n.C0427j.c
        public boolean f(Object obj, float f2, float f3) {
            return false;
        }

        @Override // n.C0427j.c
        public boolean g(Object obj) {
            return true;
        }

        @Override // n.C0427j.c
        public void h(Object obj) {
        }

        @Override // n.C0427j.c
        public void i(Object obj, int i2, int i3) {
        }
    }
}
