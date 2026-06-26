package e;

import android.content.res.Resources;
import android.os.Build;

/* JADX INFO: renamed from: e.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0236a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final InterfaceC0038a f4936a;

    /* JADX INFO: renamed from: e.a$a, reason: collision with other inner class name */
    public interface InterfaceC0038a {
        int a(Resources resources);

        int b(Resources resources);

        int c(Resources resources);
    }

    /* JADX INFO: renamed from: e.a$b */
    public static class b implements InterfaceC0038a {
        @Override // e.AbstractC0236a.InterfaceC0038a
        public int a(Resources resources) {
            return AbstractC0237b.b(resources);
        }

        @Override // e.AbstractC0236a.InterfaceC0038a
        public int b(Resources resources) {
            return AbstractC0237b.a(resources);
        }

        @Override // e.AbstractC0236a.InterfaceC0038a
        public int c(Resources resources) {
            return AbstractC0237b.c(resources);
        }
    }

    /* JADX INFO: renamed from: e.a$c */
    public static class c extends b {
        @Override // e.AbstractC0236a.b, e.AbstractC0236a.InterfaceC0038a
        public int a(Resources resources) {
            return f.b(resources);
        }

        @Override // e.AbstractC0236a.b, e.AbstractC0236a.InterfaceC0038a
        public int b(Resources resources) {
            return f.a(resources);
        }

        @Override // e.AbstractC0236a.b, e.AbstractC0236a.InterfaceC0038a
        public int c(Resources resources) {
            return f.c(resources);
        }
    }

    /* JADX INFO: renamed from: e.a$d */
    public static class d extends c {
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 17) {
            f4936a = new d();
        } else if (i2 >= 13) {
            f4936a = new c();
        } else {
            f4936a = new b();
        }
    }

    public static int a(Resources resources) {
        return f4936a.b(resources);
    }

    public static int b(Resources resources) {
        return f4936a.a(resources);
    }

    public static int c(Resources resources) {
        return f4936a.c(resources);
    }
}
