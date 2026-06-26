package g;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: renamed from: g.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0244a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f5263a;

    /* JADX INFO: renamed from: g.a$b */
    public interface b {
        void a(Drawable drawable, ColorStateList colorStateList);

        boolean b(Drawable drawable, int i2);

        void c(Drawable drawable, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);

        void d(Drawable drawable, int i2, int i3, int i4, int i5);

        ColorFilter e(Drawable drawable);

        void f(Drawable drawable, int i2);

        void g(Drawable drawable, Resources.Theme theme);

        void h(Drawable drawable, PorterDuff.Mode mode);

        void i(Drawable drawable, float f2, float f3);

        Drawable j(Drawable drawable);

        int k(Drawable drawable);

        boolean l(Drawable drawable);

        boolean m(Drawable drawable);

        void n(Drawable drawable, boolean z2);

        void o(Drawable drawable);

        int p(Drawable drawable);
    }

    /* JADX INFO: renamed from: g.a$c */
    public static class c extends C0039a {
        @Override // g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public Drawable j(Drawable drawable) {
            return g.g.b(drawable);
        }

        @Override // g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public void o(Drawable drawable) {
            g.g.a(drawable);
        }
    }

    /* JADX INFO: renamed from: g.a$d */
    public static class d extends c {
        @Override // g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public boolean b(Drawable drawable, int i2) {
            return h.b(drawable, i2);
        }

        @Override // g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public int k(Drawable drawable) {
            int iA = h.a(drawable);
            if (iA >= 0) {
                return iA;
            }
            return 0;
        }
    }

    /* JADX INFO: renamed from: g.a$e */
    public static class e extends d {
        @Override // g.AbstractC0244a.c, g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public Drawable j(Drawable drawable) {
            return l.d(drawable);
        }

        @Override // g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public boolean m(Drawable drawable) {
            return l.b(drawable);
        }

        @Override // g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public void n(Drawable drawable, boolean z2) {
            l.c(drawable, z2);
        }

        @Override // g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public int p(Drawable drawable) {
            return l.a(drawable);
        }
    }

    /* JADX INFO: renamed from: g.a$f */
    public static class f extends e {
        @Override // g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public void a(Drawable drawable, ColorStateList colorStateList) {
            v.h(drawable, colorStateList);
        }

        @Override // g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public void c(Drawable drawable, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
            v.d(drawable, resources, xmlPullParser, attributeSet, theme);
        }

        @Override // g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public void d(Drawable drawable, int i2, int i3, int i4, int i5) {
            v.f(drawable, i2, i3, i4, i5);
        }

        @Override // g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public ColorFilter e(Drawable drawable) {
            return v.c(drawable);
        }

        @Override // g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public void f(Drawable drawable, int i2) {
            v.g(drawable, i2);
        }

        @Override // g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public void g(Drawable drawable, Resources.Theme theme) {
            v.a(drawable, theme);
        }

        @Override // g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public void h(Drawable drawable, PorterDuff.Mode mode) {
            v.i(drawable, mode);
        }

        @Override // g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public void i(Drawable drawable, float f2, float f3) {
            v.e(drawable, f2, f3);
        }

        @Override // g.AbstractC0244a.e, g.AbstractC0244a.c, g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public Drawable j(Drawable drawable) {
            return v.j(drawable);
        }

        @Override // g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public boolean l(Drawable drawable) {
            return v.b(drawable);
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 23) {
            f5263a = new g();
            return;
        }
        if (i2 >= 21) {
            f5263a = new f();
            return;
        }
        if (i2 >= 19) {
            f5263a = new e();
            return;
        }
        if (i2 >= 17) {
            f5263a = new d();
        } else if (i2 >= 11) {
            f5263a = new c();
        } else {
            f5263a = new C0039a();
        }
    }

    public static void a(Drawable drawable, Resources.Theme theme) {
        f5263a.g(drawable, theme);
    }

    public static boolean b(Drawable drawable) {
        return f5263a.l(drawable);
    }

    public static int c(Drawable drawable) {
        return f5263a.p(drawable);
    }

    public static ColorFilter d(Drawable drawable) {
        return f5263a.e(drawable);
    }

    public static int e(Drawable drawable) {
        return f5263a.k(drawable);
    }

    public static void f(Drawable drawable, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        f5263a.c(drawable, resources, xmlPullParser, attributeSet, theme);
    }

    public static boolean g(Drawable drawable) {
        return f5263a.m(drawable);
    }

    public static void h(Drawable drawable) {
        f5263a.o(drawable);
    }

    public static void i(Drawable drawable, boolean z2) {
        f5263a.n(drawable, z2);
    }

    public static void j(Drawable drawable, float f2, float f3) {
        f5263a.i(drawable, f2, f3);
    }

    public static void k(Drawable drawable, int i2, int i3, int i4, int i5) {
        f5263a.d(drawable, i2, i3, i4, i5);
    }

    public static boolean l(Drawable drawable, int i2) {
        return f5263a.b(drawable, i2);
    }

    public static void m(Drawable drawable, int i2) {
        f5263a.f(drawable, i2);
    }

    public static void n(Drawable drawable, ColorStateList colorStateList) {
        f5263a.a(drawable, colorStateList);
    }

    public static void o(Drawable drawable, PorterDuff.Mode mode) {
        f5263a.h(drawable, mode);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Drawable p(Drawable drawable) {
        return drawable instanceof w ? ((w) drawable).a() : drawable;
    }

    public static Drawable q(Drawable drawable) {
        return f5263a.j(drawable);
    }

    /* JADX INFO: renamed from: g.a$a, reason: collision with other inner class name */
    public static class C0039a implements b {
        @Override // g.AbstractC0244a.b
        public void a(Drawable drawable, ColorStateList colorStateList) {
            g.e.c(drawable, colorStateList);
        }

        @Override // g.AbstractC0244a.b
        public boolean b(Drawable drawable, int i2) {
            return false;
        }

        @Override // g.AbstractC0244a.b
        public void c(Drawable drawable, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
            g.e.a(drawable, resources, xmlPullParser, attributeSet, theme);
        }

        @Override // g.AbstractC0244a.b
        public ColorFilter e(Drawable drawable) {
            return null;
        }

        @Override // g.AbstractC0244a.b
        public void f(Drawable drawable, int i2) {
            g.e.b(drawable, i2);
        }

        @Override // g.AbstractC0244a.b
        public void h(Drawable drawable, PorterDuff.Mode mode) {
            g.e.d(drawable, mode);
        }

        @Override // g.AbstractC0244a.b
        public Drawable j(Drawable drawable) {
            return g.e.e(drawable);
        }

        @Override // g.AbstractC0244a.b
        public int k(Drawable drawable) {
            return 0;
        }

        @Override // g.AbstractC0244a.b
        public boolean l(Drawable drawable) {
            return false;
        }

        @Override // g.AbstractC0244a.b
        public boolean m(Drawable drawable) {
            return false;
        }

        @Override // g.AbstractC0244a.b
        public int p(Drawable drawable) {
            return 0;
        }

        @Override // g.AbstractC0244a.b
        public void o(Drawable drawable) {
        }

        @Override // g.AbstractC0244a.b
        public void g(Drawable drawable, Resources.Theme theme) {
        }

        @Override // g.AbstractC0244a.b
        public void n(Drawable drawable, boolean z2) {
        }

        @Override // g.AbstractC0244a.b
        public void i(Drawable drawable, float f2, float f3) {
        }

        @Override // g.AbstractC0244a.b
        public void d(Drawable drawable, int i2, int i3, int i4, int i5) {
        }
    }

    /* JADX INFO: renamed from: g.a$g */
    public static class g extends f {
        @Override // g.AbstractC0244a.d, g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public boolean b(Drawable drawable, int i2) {
            return AbstractC0247d.b(drawable, i2);
        }

        @Override // g.AbstractC0244a.d, g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public int k(Drawable drawable) {
            return AbstractC0247d.a(drawable);
        }

        @Override // g.AbstractC0244a.f, g.AbstractC0244a.e, g.AbstractC0244a.c, g.AbstractC0244a.C0039a, g.AbstractC0244a.b
        public Drawable j(Drawable drawable) {
            return drawable;
        }
    }
}
