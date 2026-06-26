package c;

import android.content.Context;
import android.os.Build;

/* JADX INFO: renamed from: c.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0142h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f1870a;

    /* JADX INFO: renamed from: c.h$a */
    public static class a extends b {
        @Override // c.AbstractC0142h.b
        public int a(Context context, String str, String str2) {
            return AbstractC0154n.a(context, str, str2);
        }

        @Override // c.AbstractC0142h.b
        public String b(String str) {
            return AbstractC0154n.b(str);
        }
    }

    /* JADX INFO: renamed from: c.h$b */
    public static class b {
        public int a(Context context, String str, String str2) {
            return 1;
        }

        public String b(String str) {
            return null;
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 23) {
            f1870a = new a();
        } else {
            f1870a = new b();
        }
    }

    public static int a(Context context, String str, String str2) {
        return f1870a.a(context, str, str2);
    }

    public static String b(String str) {
        return f1870a.b(str);
    }
}
