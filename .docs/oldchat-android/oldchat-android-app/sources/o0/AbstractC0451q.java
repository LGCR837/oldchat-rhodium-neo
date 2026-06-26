package o0;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: o0.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0451q {
    public static void a(Context context, Map map) {
        JSONObject jSONObjectC;
        JSONArray jSONArrayNames;
        String strOptString;
        if (context == null || map == null || (jSONArrayNames = (jSONObjectC = c(context.getApplicationContext())).names()) == null) {
            return;
        }
        for (int i2 = 0; i2 < jSONArrayNames.length(); i2++) {
            String strOptString2 = jSONArrayNames.optString(i2);
            if (strOptString2 != null && !strOptString2.isEmpty()) {
                String strE = e(strOptString2);
                if (strE.length() != 0 && (strOptString = jSONObjectC.optString(strOptString2, "")) != null && !strOptString.isEmpty()) {
                    String str = (String) map.get(strE);
                    if (str == null || str.isEmpty()) {
                        map.put(strE, strOptString);
                    }
                    String str2 = (String) map.get(strOptString2);
                    if (str2 == null || str2.isEmpty()) {
                        map.put(strOptString2, strOptString);
                    }
                }
            }
        }
    }

    public static String b(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return "";
        }
        String strE = e(str);
        if (strE.length() == 0) {
            return "";
        }
        JSONObject jSONObjectC = c(context.getApplicationContext());
        String strOptString = jSONObjectC.optString(strE, "");
        if (strOptString == null || strOptString.isEmpty()) {
            strOptString = jSONObjectC.optString(str.trim(), "");
        }
        if (strOptString == null || strOptString.isEmpty()) {
            strOptString = jSONObjectC.optString(str, "");
        }
        return strOptString == null ? "" : strOptString;
    }

    public static JSONObject c(Context context) {
        return d(context.getSharedPreferences("group_avatar_cache", 0));
    }

    public static JSONObject d(SharedPreferences sharedPreferences) {
        try {
            return new JSONObject(sharedPreferences.getString("avatar_map", "{}"));
        } catch (Exception unused) {
            return new JSONObject();
        }
    }

    public static String e(String str) {
        if (str == null) {
            return "";
        }
        String strTrim = str.trim();
        while (strTrim.startsWith("@")) {
            strTrim = strTrim.substring(1).trim();
        }
        return strTrim.length() == 0 ? "" : strTrim.toUpperCase(Locale.US);
    }

    public static void f(Context context, Map map) {
        g(context, map, true);
    }

    public static void g(Context context, Map map, boolean z2) {
        if (context == null || map == null || map.isEmpty()) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        boolean z3 = false;
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("group_avatar_cache", 0);
        JSONObject jSONObjectD = d(sharedPreferences);
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (str != null && !str.isEmpty() && str2 != null && !str2.isEmpty()) {
                String strE = e(str);
                if (strE.length() != 0) {
                    String strOptString = jSONObjectD.optString(strE, "");
                    if (strOptString == null || strOptString.isEmpty()) {
                        strOptString = jSONObjectD.optString(str, "");
                    }
                    if (!str2.equals(strOptString)) {
                        try {
                            jSONObjectD.put(strE, str2);
                            z3 = true;
                        } catch (Exception unused) {
                        }
                    }
                    if (z2 && !AbstractC0455v.d(applicationContext, str2)) {
                        AbstractC0455v.l(applicationContext, str2);
                    }
                }
            }
        }
        if (z3) {
            sharedPreferences.edit().putString("avatar_map", jSONObjectD.toString()).apply();
        }
    }

    public static void h(Context context, List list) {
        String str;
        String str2;
        if (context == null || list == null || list.isEmpty()) {
            return;
        }
        HashMap map = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            j0.h hVar = (j0.h) it.next();
            if (hVar != null && (str = hVar.f5522a) != null && !str.isEmpty() && (str2 = hVar.f5526e) != null && !str2.isEmpty()) {
                map.put(hVar.f5522a, hVar.f5526e);
            }
        }
        f(context, map);
    }
}
