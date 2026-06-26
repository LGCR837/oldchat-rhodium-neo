package i0;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    public static void a(Context context) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("friend_requests", 0).edit().clear().apply();
    }

    public static int b(Context context) {
        if (context == null) {
            return 0;
        }
        return context.getSharedPreferences("friend_requests", 0).getInt("pending_count", 0);
    }

    public static void c(Context context, int i2) {
        if (context == null) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        context.getSharedPreferences("friend_requests", 0).edit().putInt("pending_count", i2).apply();
    }
}
