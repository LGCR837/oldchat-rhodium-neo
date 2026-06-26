package c;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

/* JADX INFO: renamed from: c.f0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0137f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f1864a;

    /* JADX INFO: renamed from: c.f0$a */
    public interface a {
        void a(Activity activity, Intent intent);

        Intent b(Activity activity);

        String c(Context context, ActivityInfo activityInfo);

        boolean d(Activity activity, Intent intent);
    }

    /* JADX INFO: renamed from: c.f0$b */
    public static class b implements a {
        @Override // c.AbstractC0137f0.a
        public void a(Activity activity, Intent intent) {
            intent.addFlags(67108864);
            activity.startActivity(intent);
            activity.finish();
        }

        @Override // c.AbstractC0137f0.a
        public Intent b(Activity activity) {
            String strC = AbstractC0137f0.c(activity);
            if (strC == null) {
                return null;
            }
            ComponentName componentName = new ComponentName(activity, strC);
            try {
                return AbstractC0137f0.d(activity, componentName) == null ? d.n.a(componentName) : new Intent().setComponent(componentName);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + strC + "' in manifest");
                return null;
            }
        }

        @Override // c.AbstractC0137f0.a
        public String c(Context context, ActivityInfo activityInfo) {
            String string;
            Bundle bundle = activityInfo.metaData;
            if (bundle == null || (string = bundle.getString("android.support.PARENT_ACTIVITY")) == null) {
                return null;
            }
            if (string.charAt(0) != '.') {
                return string;
            }
            return context.getPackageName() + string;
        }

        @Override // c.AbstractC0137f0.a
        public boolean d(Activity activity, Intent intent) {
            String action = activity.getIntent().getAction();
            return (action == null || action.equals("android.intent.action.MAIN")) ? false : true;
        }
    }

    /* JADX INFO: renamed from: c.f0$c */
    public static class c extends b {
        @Override // c.AbstractC0137f0.b, c.AbstractC0137f0.a
        public void a(Activity activity, Intent intent) {
            AbstractC0149k0.c(activity, intent);
        }

        @Override // c.AbstractC0137f0.b, c.AbstractC0137f0.a
        public Intent b(Activity activity) {
            Intent intentA = AbstractC0149k0.a(activity);
            return intentA == null ? e(activity) : intentA;
        }

        @Override // c.AbstractC0137f0.b, c.AbstractC0137f0.a
        public String c(Context context, ActivityInfo activityInfo) {
            String strB = AbstractC0149k0.b(activityInfo);
            return strB == null ? super.c(context, activityInfo) : strB;
        }

        @Override // c.AbstractC0137f0.b, c.AbstractC0137f0.a
        public boolean d(Activity activity, Intent intent) {
            return AbstractC0149k0.d(activity, intent);
        }

        public Intent e(Activity activity) {
            return super.b(activity);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 16) {
            f1864a = new c();
        } else {
            f1864a = new b();
        }
    }

    public static Intent a(Activity activity) {
        return f1864a.b(activity);
    }

    public static Intent b(Context context, ComponentName componentName) throws PackageManager.NameNotFoundException {
        String strD = d(context, componentName);
        if (strD == null) {
            return null;
        }
        ComponentName componentName2 = new ComponentName(componentName.getPackageName(), strD);
        return d(context, componentName2) == null ? d.n.a(componentName2) : new Intent().setComponent(componentName2);
    }

    public static String c(Activity activity) {
        try {
            return d(activity, activity.getComponentName());
        } catch (PackageManager.NameNotFoundException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static String d(Context context, ComponentName componentName) throws PackageManager.NameNotFoundException {
        return f1864a.c(context, context.getPackageManager().getActivityInfo(componentName, 128));
    }

    public static void e(Activity activity, Intent intent) {
        f1864a.a(activity, intent);
    }

    public static boolean f(Activity activity, Intent intent) {
        return f1864a.d(activity, intent);
    }
}
