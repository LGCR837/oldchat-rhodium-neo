package i0;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {
    public static String a(Context context, String str, boolean z2) {
        String strE = e(context);
        String strF = f(str);
        return "mute_" + strE + "_" + (z2 ? "g" : "u") + "_" + strF;
    }

    public static boolean b(Context context, String str, boolean z2) {
        if (context == null || str == null || str.trim().length() == 0) {
            return false;
        }
        return d(context).getBoolean(a(context, str, z2), false);
    }

    public static boolean c(Context context) {
        return d(context).getBoolean("public_court_enabled", true);
    }

    public static SharedPreferences d(Context context) {
        return context.getSharedPreferences("oldchat_settings", 0);
    }

    public static String e(Context context) {
        String string;
        return (context == null || (string = context.getSharedPreferences("auth", 0).getString("my_uid", "")) == null || string.length() == 0) ? "" : f(string);
    }

    public static String f(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if ((cCharAt < '0' || cCharAt > '9') && ((cCharAt < 'A' || cCharAt > 'Z') && ((cCharAt < 'a' || cCharAt > 'z') && cCharAt != '_'))) {
                sb.append('_');
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    public static void g(Context context, String str, boolean z2, boolean z3) {
        if (context == null || str == null || str.trim().length() == 0) {
            return;
        }
        d(context).edit().putBoolean(a(context, str, z2), z3).apply();
    }

    public static void h(Context context, boolean z2) {
        d(context).edit().putBoolean("public_court_enabled", z2).apply();
    }
}
