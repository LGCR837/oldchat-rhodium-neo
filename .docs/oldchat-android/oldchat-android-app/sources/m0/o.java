package m0;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class o {
    public static String a(boolean z2, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(z2 ? "g:" : "u:");
        sb.append(str);
        return sb.toString();
    }

    public static boolean b(Context context, boolean z2, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return false;
        }
        return context.getSharedPreferences("recent_pin", 0).getBoolean(a(z2, str), false);
    }

    public static void c(Context context, boolean z2, String str, boolean z3) {
        if (context == null || str == null || str.isEmpty()) {
            return;
        }
        context.getSharedPreferences("recent_pin", 0).edit().putBoolean(a(z2, str), z3).apply();
    }
}
