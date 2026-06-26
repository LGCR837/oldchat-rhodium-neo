package h0;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static void a(Context context) {
        if (context == null) {
            return;
        }
        f(context).edit().clear().apply();
    }

    public static String b(Context context) {
        return context == null ? "" : f(context).getString("access_token", "");
    }

    public static String c(Context context) {
        return context == null ? "" : f(context).getString("cookies", "");
    }

    public static long d(Context context) {
        if (context == null) {
            return 0L;
        }
        return f(context).getLong("mid", 0L);
    }

    public static boolean e(Context context) {
        if (context == null) {
            return true;
        }
        long j2 = f(context).getLong("expires_at", 0L);
        return j2 > 0 && System.currentTimeMillis() >= j2;
    }

    public static SharedPreferences f(Context context) {
        return context.getSharedPreferences("bili_auth", 0);
    }

    public static void g(Context context, String str, String str2, long j2, long j3) {
        if (context == null) {
            return;
        }
        long jCurrentTimeMillis = j2 > 0 ? System.currentTimeMillis() + (j2 * 1000) : 0L;
        SharedPreferences.Editor editorEdit = f(context).edit();
        if (str == null) {
            str = "";
        }
        SharedPreferences.Editor editorPutString = editorEdit.putString("access_token", str);
        if (str2 == null) {
            str2 = "";
        }
        editorPutString.putString("cookies", str2).putLong("expires_at", jCurrentTimeMillis).putLong("mid", j3).apply();
    }

    public static void h(Context context, long j2) {
        if (context == null) {
            return;
        }
        f(context).edit().putLong("mid", j2).apply();
    }
}
