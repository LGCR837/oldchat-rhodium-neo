package k;

import android.os.Build;
import android.view.LayoutInflater;

/* JADX INFO: renamed from: k.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0253A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f5654a;

    /* JADX INFO: renamed from: k.A$a */
    public interface a {
        H a(LayoutInflater layoutInflater);

        void b(LayoutInflater layoutInflater, H h2);
    }

    /* JADX INFO: renamed from: k.A$b */
    public static class b implements a {
        @Override // k.AbstractC0253A.a
        public H a(LayoutInflater layoutInflater) {
            return B.a(layoutInflater);
        }

        @Override // k.AbstractC0253A.a
        public void b(LayoutInflater layoutInflater, H h2) {
            B.b(layoutInflater, h2);
        }
    }

    /* JADX INFO: renamed from: k.A$c */
    public static class c extends b {
        @Override // k.AbstractC0253A.b, k.AbstractC0253A.a
        public void b(LayoutInflater layoutInflater, H h2) {
            F.b(layoutInflater, h2);
        }
    }

    /* JADX INFO: renamed from: k.A$d */
    public static class d extends c {
        @Override // k.AbstractC0253A.c, k.AbstractC0253A.b, k.AbstractC0253A.a
        public void b(LayoutInflater layoutInflater, H h2) {
            G.a(layoutInflater, h2);
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            f5654a = new d();
        } else if (i2 >= 11) {
            f5654a = new c();
        } else {
            f5654a = new b();
        }
    }

    public static H a(LayoutInflater layoutInflater) {
        return f5654a.a(layoutInflater);
    }

    public static void b(LayoutInflater layoutInflater, H h2) {
        f5654a.b(layoutInflater, h2);
    }
}
