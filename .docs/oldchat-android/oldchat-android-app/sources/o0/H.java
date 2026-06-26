package o0;

import android.app.NotificationManager;
import android.os.Build;
import java.lang.reflect.Constructor;

/* JADX INFO: loaded from: classes.dex */
public abstract class H {
    public static void a(NotificationManager notificationManager, String str, String str2, int i2) {
        if (notificationManager == null || str == null || str.length() == 0 || Build.VERSION.SDK_INT < 26) {
            return;
        }
        try {
            Class<?> cls = Class.forName("android.app.NotificationChannel");
            if (notificationManager.getClass().getMethod("getNotificationChannel", String.class).invoke(notificationManager, str) != null) {
                return;
            }
            Constructor<?> constructor = cls.getConstructor(String.class, CharSequence.class, Integer.TYPE);
            if (str2 == null) {
                str2 = "";
            }
            notificationManager.getClass().getMethod("createNotificationChannel", cls).invoke(notificationManager, constructor.newInstance(str, str2, Integer.valueOf(i2)));
        } catch (Throwable unused) {
        }
    }
}
