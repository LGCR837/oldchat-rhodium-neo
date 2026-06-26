package o0;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: o0.b0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0434b0 {
    public static JSONObject a(SharedPreferences sharedPreferences) {
        String str = "{}";
        try {
            String string = sharedPreferences.getString("uid_alias_map", "{}");
            if (string != null) {
                str = string;
            }
            return new JSONObject(str);
        } catch (Exception unused) {
            return new JSONObject();
        }
    }

    public static String b(String str) {
        if (str == null) {
            return "";
        }
        String strTrim = str.trim();
        while (strTrim.startsWith("@")) {
            strTrim = strTrim.substring(1).trim();
        }
        return strTrim.length() == 0 ? "" : strTrim.toUpperCase(Locale.US);
    }

    public static void c(Context context, String str, String str2) {
        if (context == null) {
            return;
        }
        String strB = b(str);
        String strB2 = b(str2);
        if (strB.length() == 0 || strB2.length() == 0 || strB.equals(strB2)) {
            return;
        }
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("uid_alias_store", 0);
        JSONObject jSONObjectA = a(sharedPreferences);
        String strE = e(jSONObjectA, strB2);
        if (strE.equals(e(jSONObjectA, strB))) {
            return;
        }
        try {
            jSONObjectA.put(strB, strE);
            JSONArray jSONArrayNames = jSONObjectA.names();
            if (jSONArrayNames != null) {
                for (int i2 = 0; i2 < jSONArrayNames.length(); i2++) {
                    String strB3 = b(jSONArrayNames.optString(i2));
                    if (strB3.length() != 0) {
                        String strE2 = e(jSONObjectA, strB3);
                        if (!strE2.equals(strB3)) {
                            try {
                                jSONObjectA.put(strB3, strE2);
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
            }
            sharedPreferences.edit().putString("uid_alias_map", jSONObjectA.toString()).apply();
        } catch (Exception unused2) {
        }
    }

    public static String d(Context context, String str) {
        String strB = b(str);
        return (strB.length() == 0 || context == null) ? strB : e(a(context.getApplicationContext().getSharedPreferences("uid_alias_store", 0)), strB);
    }

    public static String e(JSONObject jSONObject, String str) {
        String strB = b(str);
        if (strB.length() != 0 && jSONObject != null) {
            int i2 = 0;
            while (i2 < 8) {
                String strB2 = b(jSONObject.optString(strB, ""));
                if (strB2.length() == 0 || strB2.equals(strB)) {
                    break;
                }
                i2++;
                strB = strB2;
            }
        }
        return strB;
    }
}
