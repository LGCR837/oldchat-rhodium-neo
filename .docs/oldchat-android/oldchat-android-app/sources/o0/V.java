package o0;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public abstract class V {
    public static void A(Context context, int i2) {
        o(context).edit().putInt("theme_color_index", i2).commit();
    }

    public static void B(Context context, boolean z2) {
        o(context).edit().putBoolean("typing_indicator_enabled", z2).apply();
    }

    public static int a(Context context) {
        return o(context).getInt("bubble_in_color_index", -1);
    }

    public static int b(Context context) {
        return o(context).getInt("bubble_out_color_index", -1);
    }

    public static float c(Context context) {
        int iD = d(context);
        if (iD == 0) {
            return 0.9f;
        }
        if (iD != 2) {
            return iD != 3 ? 1.0f : 1.3f;
        }
        return 1.15f;
    }

    public static int d(Context context) {
        return o(context).getInt("font_size_index", 1);
    }

    public static int e(Context context) {
        return o(context).getInt("theme_color_index", -1);
    }

    public static boolean f(Context context) {
        return o(context).getBoolean("button_3d_effect_enabled", true);
    }

    public static boolean g(Context context) {
        return o(context).getBoolean("dark_mode_enabled", false);
    }

    public static boolean h(Context context) {
        return o(context).getBoolean("enter_send_enabled", false);
    }

    public static boolean i(Context context) {
        return o(context).getBoolean("home_news_enabled", true);
    }

    public static boolean j(Context context) {
        return o(context).getBoolean("notify_enabled", true);
    }

    public static boolean k(Context context) {
        return o(context).getBoolean("old_view_entry_enabled", true);
    }

    public static boolean l(Context context) {
        return o(context).getBoolean("privacy_agreed", false);
    }

    public static boolean m(Context context) {
        return o(context).getBoolean("rounded_buttons_enabled", false);
    }

    public static boolean n(Context context) {
        return o(context).getBoolean("typing_indicator_enabled", true);
    }

    public static SharedPreferences o(Context context) {
        return context.getSharedPreferences("settings", 0);
    }

    public static void p(Context context, int i2) {
        o(context).edit().putInt("bubble_in_color_index", i2).commit();
    }

    public static void q(Context context, int i2) {
        o(context).edit().putInt("bubble_out_color_index", i2).commit();
    }

    public static void r(Context context, boolean z2) {
        o(context).edit().putBoolean("button_3d_effect_enabled", z2).commit();
    }

    public static void s(Context context, boolean z2) {
        o(context).edit().putBoolean("dark_mode_enabled", z2).commit();
    }

    public static void t(Context context, boolean z2) {
        o(context).edit().putBoolean("enter_send_enabled", z2).apply();
    }

    public static void u(Context context, int i2) {
        o(context).edit().putInt("font_size_index", i2).apply();
    }

    public static void v(Context context, boolean z2) {
        o(context).edit().putBoolean("home_news_enabled", z2).apply();
    }

    public static void w(Context context, boolean z2) {
        o(context).edit().putBoolean("notify_enabled", z2).apply();
    }

    public static void x(Context context, boolean z2) {
        o(context).edit().putBoolean("old_view_entry_enabled", z2).apply();
    }

    public static void y(Context context, boolean z2) {
        o(context).edit().putBoolean("privacy_agreed", z2).apply();
    }

    public static void z(Context context, boolean z2) {
        o(context).edit().putBoolean("rounded_buttons_enabled", z2).commit();
    }
}
