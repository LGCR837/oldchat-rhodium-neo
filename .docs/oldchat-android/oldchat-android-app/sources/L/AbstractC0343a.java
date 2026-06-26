package l;

import android.os.Build;
import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: renamed from: l.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0343a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f5751a;

    /* JADX INFO: renamed from: l.a$a, reason: collision with other inner class name */
    public static class C0048a extends d {
    }

    /* JADX INFO: renamed from: l.a$b */
    public static class b extends C0048a {
    }

    /* JADX INFO: renamed from: l.a$c */
    public static class c extends b {
        @Override // l.AbstractC0343a.d, l.AbstractC0343a.e
        public int a(AccessibilityEvent accessibilityEvent) {
            return AbstractC0346d.a(accessibilityEvent);
        }

        @Override // l.AbstractC0343a.d, l.AbstractC0343a.e
        public void b(AccessibilityEvent accessibilityEvent, int i2) {
            AbstractC0346d.b(accessibilityEvent, i2);
        }
    }

    /* JADX INFO: renamed from: l.a$e */
    public interface e {
        int a(AccessibilityEvent accessibilityEvent);

        void b(AccessibilityEvent accessibilityEvent, int i2);
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 19) {
            f5751a = new c();
            return;
        }
        if (i2 >= 16) {
            f5751a = new b();
        } else if (i2 >= 14) {
            f5751a = new C0048a();
        } else {
            f5751a = new d();
        }
    }

    public static N a(AccessibilityEvent accessibilityEvent) {
        return new N(accessibilityEvent);
    }

    public static int b(AccessibilityEvent accessibilityEvent) {
        return f5751a.a(accessibilityEvent);
    }

    public static void c(AccessibilityEvent accessibilityEvent, int i2) {
        f5751a.b(accessibilityEvent, i2);
    }

    /* JADX INFO: renamed from: l.a$d */
    public static class d implements e {
        @Override // l.AbstractC0343a.e
        public int a(AccessibilityEvent accessibilityEvent) {
            return 0;
        }

        @Override // l.AbstractC0343a.e
        public void b(AccessibilityEvent accessibilityEvent, int i2) {
        }
    }
}
