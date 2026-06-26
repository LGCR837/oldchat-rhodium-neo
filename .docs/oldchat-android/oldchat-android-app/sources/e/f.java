package e;

import android.content.res.Resources;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {
    public static int a(Resources resources) {
        return resources.getConfiguration().screenHeightDp;
    }

    public static int b(Resources resources) {
        return resources.getConfiguration().screenWidthDp;
    }

    public static int c(Resources resources) {
        return resources.getConfiguration().smallestScreenWidthDp;
    }
}
