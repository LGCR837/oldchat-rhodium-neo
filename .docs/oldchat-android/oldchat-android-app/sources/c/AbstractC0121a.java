package c;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import d.AbstractC0235a;

/* JADX INFO: renamed from: c.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0121a extends AbstractC0235a {

    /* JADX INFO: renamed from: c.a$a, reason: collision with other inner class name */
    public static class RunnableC0018a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String[] f1831a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Activity f1832b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f1833c;

        public RunnableC0018a(String[] strArr, Activity activity, int i2) {
            this.f1831a = strArr;
            this.f1832b = activity;
            this.f1833c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            int[] iArr = new int[this.f1831a.length];
            PackageManager packageManager = this.f1832b.getPackageManager();
            String packageName = this.f1832b.getPackageName();
            int length = this.f1831a.length;
            for (int i2 = 0; i2 < length; i2++) {
                iArr[i2] = packageManager.checkPermission(this.f1831a[i2], packageName);
            }
            ((b) this.f1832b).onRequestPermissionsResult(this.f1833c, this.f1831a, iArr);
        }
    }

    /* JADX INFO: renamed from: c.a$b */
    public interface b {
        void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr);
    }

    public static void h(Activity activity) {
        if (Build.VERSION.SDK_INT >= 16) {
            AbstractC0139g.a(activity);
        } else {
            activity.finish();
        }
    }

    public static void i(Activity activity, String[] strArr, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            AbstractC0130d.a(activity, strArr, i2);
        } else if (activity instanceof b) {
            new Handler(Looper.getMainLooper()).post(new RunnableC0018a(strArr, activity, i2));
        }
    }

    public static boolean j(Activity activity, String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return AbstractC0130d.b(activity, str);
        }
        return false;
    }

    public static void k(Activity activity, Intent intent, int i2, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            AbstractC0139g.b(activity, intent, i2, bundle);
        } else {
            activity.startActivityForResult(intent, i2);
        }
    }
}
