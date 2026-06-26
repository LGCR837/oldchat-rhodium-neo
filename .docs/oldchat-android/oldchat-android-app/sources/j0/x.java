package j0;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class x {
    public static void a(Context context) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("user_name_cache", 0).edit().clear().apply();
    }

    public static String b(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return "";
        }
        return f(context.getSharedPreferences("user_name_cache", 0).getString("name_" + str, ""));
    }

    public static void c(Context context, List list) {
        String str;
        if (context == null || list == null || list.isEmpty()) {
            return;
        }
        boolean z2 = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_name_cache", 0);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            w wVar = (w) it.next();
            if (wVar != null && (str = wVar.f5646b) != null && !str.isEmpty()) {
                String strC = d.c(wVar.f5649e, wVar.f5648d, wVar.f5647c, wVar.f5646b);
                if (!strC.isEmpty()) {
                    String str2 = "name_" + wVar.f5646b;
                    if (!strC.equals(sharedPreferences.getString(str2, ""))) {
                        editorEdit.putString(str2, strC);
                        z2 = true;
                    }
                }
            }
        }
        if (z2) {
            editorEdit.apply();
        }
    }

    public static void d(Context context, String str, String str2) {
        if (context == null || str == null || str.isEmpty()) {
            return;
        }
        String strF = f(str2);
        if (strF.isEmpty()) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_name_cache", 0);
        String str3 = "name_" + str;
        if (strF.equals(sharedPreferences.getString(str3, ""))) {
            return;
        }
        sharedPreferences.edit().putString(str3, strF).apply();
    }

    public static void e(Context context, Map map) {
        if (context == null || map == null || map.isEmpty()) {
            return;
        }
        boolean z2 = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_name_cache", 0);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            if (str != null && !str.isEmpty()) {
                String strF = f((String) entry.getValue());
                if (!strF.isEmpty()) {
                    String str2 = "name_" + str;
                    if (!strF.equals(sharedPreferences.getString(str2, ""))) {
                        editorEdit.putString(str2, strF);
                        z2 = true;
                    }
                }
            }
        }
        if (z2) {
            editorEdit.apply();
        }
    }

    public static String f(String str) {
        if (str == null) {
            return "";
        }
        String strTrim = str.replace('\r', ' ').replace('\n', ' ').trim();
        return strTrim.length() > 15 ? strTrim.substring(0, 15) : strTrim;
    }
}
