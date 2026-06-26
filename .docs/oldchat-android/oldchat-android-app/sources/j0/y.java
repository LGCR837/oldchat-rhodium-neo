package j0;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class y {
    public static String a(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return "";
        }
        return context.getSharedPreferences("user_title_cache", 0).getString("title_" + str, "");
    }

    public static void b(Context context, List list) {
        String str;
        if (context == null || list == null || list.isEmpty()) {
            return;
        }
        boolean z2 = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_title_cache", 0);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            w wVar = (w) it.next();
            if (wVar != null && (str = wVar.f5646b) != null && !str.isEmpty()) {
                String strE = e(wVar.f5650f);
                String str2 = "title_" + wVar.f5646b;
                if (!strE.equals(sharedPreferences.getString(str2, ""))) {
                    editorEdit.putString(str2, strE);
                    z2 = true;
                }
            }
        }
        if (z2) {
            editorEdit.apply();
        }
    }

    public static void c(Context context, String str, String str2) {
        if (context == null || str == null || str.isEmpty()) {
            return;
        }
        String strE = e(str2);
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_title_cache", 0);
        String str3 = "title_" + str;
        if (strE.equals(sharedPreferences.getString(str3, ""))) {
            return;
        }
        sharedPreferences.edit().putString(str3, strE).apply();
    }

    public static void d(Context context, Map map) {
        if (context == null || map == null || map.isEmpty()) {
            return;
        }
        boolean z2 = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_title_cache", 0);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            if (str != null && !str.isEmpty()) {
                String strE = e((String) entry.getValue());
                String str2 = "title_" + str;
                if (!strE.equals(sharedPreferences.getString(str2, ""))) {
                    editorEdit.putString(str2, strE);
                    z2 = true;
                }
            }
        }
        if (z2) {
            editorEdit.apply();
        }
    }

    public static String e(String str) {
        return str == null ? "" : str.trim();
    }
}
