package o0;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class F {
    public static void a(Context context) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("auth", 0).edit().remove("my_uid_aliases").apply();
    }

    public static String b(Context context) {
        return context == null ? "" : context.getSharedPreferences("auth", 0).getString("my_uid", "");
    }

    public static boolean c(Context context, String str, String str2) {
        if (str != null && str.length() != 0) {
            String strB = b(context);
            if (str2 == null || str2.length() == 0) {
                str2 = strB;
            }
            if (str.equals(str2)) {
                return true;
            }
            if (strB != null && strB.length() > 0 && str.equals(strB)) {
                return true;
            }
            if (context == null) {
                return false;
            }
            List listD = d(context);
            for (int i2 = 0; i2 < listD.size(); i2++) {
                if (str.equals(listD.get(i2))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List d(Context context) {
        ArrayList arrayList = new ArrayList();
        if (context == null) {
            return arrayList;
        }
        String string = context.getSharedPreferences("auth", 0).getString("my_uid_aliases", "");
        if (string != null && string.length() != 0) {
            int length = string.length();
            int i2 = 0;
            for (int i3 = 0; i3 <= length; i3++) {
                if (i3 == length || string.charAt(i3) == ',') {
                    if (i3 > i2) {
                        String strTrim = string.substring(i2, i3).trim();
                        if (strTrim.length() > 0) {
                            arrayList.add(strTrim);
                        }
                    }
                    i2 = i3 + 1;
                }
            }
        }
        return arrayList;
    }

    public static void e(Context context, String str, String str2) {
        if (context == null || str == null || str.length() == 0) {
            return;
        }
        if (str2 == null || !str.equals(str2)) {
            List listD = d(context);
            for (int size = listD.size() - 1; size >= 0; size--) {
                if (str.equals(listD.get(size))) {
                    listD.remove(size);
                }
            }
            listD.add(0, str);
            while (listD.size() > 5) {
                listD.remove(listD.size() - 1);
            }
            f(context, listD);
        }
    }

    public static void f(Context context, List list) {
        if (context == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (i2 > 0) {
                sb.append(',');
            }
            sb.append((String) list.get(i2));
        }
        context.getSharedPreferences("auth", 0).edit().putString("my_uid_aliases", sb.toString()).apply();
    }
}
