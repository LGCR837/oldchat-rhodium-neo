package c;

import android.app.Activity;

/* JADX INFO: renamed from: c.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0130d {

    /* JADX INFO: renamed from: c.d$a */
    public interface a {
        void a(int i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Activity activity, String[] strArr, int i2) {
        if (activity instanceof a) {
            ((a) activity).a(i2);
        }
        activity.requestPermissions(strArr, i2);
    }

    public static boolean b(Activity activity, String str) {
        return activity.shouldShowRequestPermissionRationale(str);
    }
}
