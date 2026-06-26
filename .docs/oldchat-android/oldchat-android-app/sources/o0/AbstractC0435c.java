package o0;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.im.oldchat.service.MessageService;
import java.util.List;

/* JADX INFO: renamed from: o0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0435c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f7082a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f7083b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Context f7084c;

    public static /* synthetic */ int c() {
        int i2 = f7082a;
        f7082a = i2 + 1;
        return i2;
    }

    public static /* synthetic */ int d() {
        int i2 = f7082a;
        f7082a = i2 - 1;
        return i2;
    }

    public static void e(Application application) {
        if (f7083b) {
            return;
        }
        f7083b = true;
        f7084c = application.getApplicationContext();
        if (Build.VERSION.SDK_INT >= 14) {
            application.registerActivityLifecycleCallbacks(new b());
        }
    }

    public static boolean f() {
        ActivityManager activityManager;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (f7082a > 0) {
            return true;
        }
        Context context = f7084c;
        if (context == null || (activityManager = (ActivityManager) context.getSystemService("activity")) == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
            return false;
        }
        String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (packageName.equals(runningAppProcessInfo.processName)) {
                int i2 = runningAppProcessInfo.importance;
                return i2 == 100 || i2 == 200;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: o0.c$b */
    public static class b implements Application.ActivityLifecycleCallbacks {
        public b() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            AbstractC0435c.c();
            if (AbstractC0435c.f7082a == 1) {
                MessageService.g(activity.getApplicationContext());
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            AbstractC0435c.d();
            if (AbstractC0435c.f7082a < 0) {
                int unused = AbstractC0435c.f7082a = 0;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }
    }
}
