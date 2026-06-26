package i0;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import o0.AbstractC0453t;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static String a(Context context, boolean z2) {
        String strN = n(context);
        if (TextUtils.isEmpty(strN)) {
            strN = "guest";
        }
        return "bg_global_" + strN + "_" + (z2 ? "g" : "u");
    }

    public static File b(Context context, boolean z2) {
        if (context == null) {
            return null;
        }
        File file = new File(context.getFilesDir(), "chat_backgrounds");
        String strN = n(context);
        if (TextUtils.isEmpty(strN)) {
            strN = "guest";
        }
        File file2 = new File(file, strN);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        return new File(file2, z2 ? "global_group.jpg" : "global_chat.jpg");
    }

    public static String c(Context context, String str, boolean z2) {
        String strN = n(context);
        if (TextUtils.isEmpty(strN)) {
            strN = "guest";
        }
        String strO = o(str);
        return "bg_" + strN + "_" + (z2 ? "g" : "u") + "_" + strO;
    }

    public static File d(Context context, String str, boolean z2) {
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(context.getFilesDir(), "chat_backgrounds");
        String strN = n(context);
        if (TextUtils.isEmpty(strN)) {
            strN = "guest";
        }
        File file2 = new File(file, strN);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        String strO = o(str);
        StringBuilder sb = new StringBuilder();
        sb.append(z2 ? "g_" : "u_");
        sb.append(strO);
        sb.append(".jpg");
        return new File(file2, sb.toString());
    }

    public static void e(Context context, String str, boolean z2) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        String strC = c(context, str, z2);
        String string = m(context).getString(strC, "");
        m(context).edit().remove(strC).apply();
        g(string);
    }

    public static void f(Context context, boolean z2) {
        if (context == null) {
            return;
        }
        String strA = a(context, z2);
        String string = m(context).getString(strA, "");
        m(context).edit().remove(strA).apply();
        g(string);
    }

    public static void g(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static String h(Context context, String str, boolean z2) {
        return (context == null || TextUtils.isEmpty(str)) ? "" : m(context).getString(c(context, str, z2), "");
    }

    public static String i(Context context, String str, boolean z2) {
        String strH = h(context, str, z2);
        if (!TextUtils.isEmpty(strH) && new File(strH).exists()) {
            return strH;
        }
        String strJ = j(context, z2);
        return (TextUtils.isEmpty(strJ) || !new File(strJ).exists()) ? "" : strJ;
    }

    public static String j(Context context, boolean z2) {
        return context == null ? "" : m(context).getString(a(context, z2), "");
    }

    public static boolean k(Context context, String str, boolean z2) {
        String strH = h(context, str, z2);
        if (TextUtils.isEmpty(strH)) {
            return false;
        }
        return new File(strH).exists();
    }

    public static boolean l(Context context, boolean z2) {
        String strJ = j(context, z2);
        if (TextUtils.isEmpty(strJ)) {
            return false;
        }
        return new File(strJ).exists();
    }

    public static SharedPreferences m(Context context) {
        return context.getSharedPreferences("oldchat_chat_backgrounds", 0);
    }

    public static String n(Context context) {
        if (context == null) {
            return "";
        }
        String string = context.getSharedPreferences("auth", 0).getString("my_uid", "");
        return TextUtils.isEmpty(string) ? "" : o(string);
    }

    public static String o(String str) {
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

    public static boolean p(Context context, String str, boolean z2, Uri uri) {
        File fileD;
        if (context == null || TextUtils.isEmpty(str) || uri == null || (fileD = d(context, str, z2)) == null) {
            return false;
        }
        String strC = c(context, str, z2);
        String string = m(context).getString(strC, "");
        if (!AbstractC0453t.c(context, uri, fileD, 1280, 80, false, 819200L)) {
            return false;
        }
        m(context).edit().putString(strC, fileD.getAbsolutePath()).apply();
        if (TextUtils.isEmpty(string) || string.equals(fileD.getAbsolutePath())) {
            return true;
        }
        g(string);
        return true;
    }

    public static boolean q(Context context, boolean z2, Uri uri) {
        File fileB;
        if (context == null || uri == null || (fileB = b(context, z2)) == null) {
            return false;
        }
        String strA = a(context, z2);
        String string = m(context).getString(strA, "");
        if (!AbstractC0453t.c(context, uri, fileB, 1280, 80, false, 819200L)) {
            return false;
        }
        m(context).edit().putString(strA, fileB.getAbsolutePath()).apply();
        if (TextUtils.isEmpty(string) || string.equals(fileB.getAbsolutePath())) {
            return true;
        }
        g(string);
        return true;
    }
}
