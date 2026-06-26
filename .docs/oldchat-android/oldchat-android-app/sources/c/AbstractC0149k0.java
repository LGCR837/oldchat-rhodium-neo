package c;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;

/* JADX INFO: renamed from: c.k0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0149k0 {
    public static Intent a(Activity activity) {
        return activity.getParentActivityIntent();
    }

    public static String b(ActivityInfo activityInfo) {
        return activityInfo.parentActivityName;
    }

    public static void c(Activity activity, Intent intent) {
        activity.navigateUpTo(intent);
    }

    public static boolean d(Activity activity, Intent intent) {
        return activity.shouldUpRecreateTask(intent);
    }
}
