package i;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {
    public static void a(String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            i.a(str);
        }
    }

    public static void b() {
        if (Build.VERSION.SDK_INT >= 18) {
            i.b();
        }
    }
}
