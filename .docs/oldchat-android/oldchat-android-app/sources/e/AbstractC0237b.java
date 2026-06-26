package e;

import android.content.res.Resources;

/* JADX INFO: renamed from: e.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0237b {
    public static int a(Resources resources) {
        return (int) (r1.heightPixels / resources.getDisplayMetrics().density);
    }

    public static int b(Resources resources) {
        return (int) (r1.widthPixels / resources.getDisplayMetrics().density);
    }

    public static int c(Resources resources) {
        return Math.min(b(resources), a(resources));
    }
}
