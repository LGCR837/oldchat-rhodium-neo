package l0;

import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: renamed from: l0.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0410z {
    public static boolean a(String str, String str2) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return false;
        }
        Locale locale = Locale.US;
        String lowerCase = str2.toLowerCase(locale);
        if (str.toLowerCase(locale).contains(lowerCase)) {
            return true;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("error", "");
            if (strOptString != null && strOptString.toLowerCase(locale).contains(lowerCase)) {
                return true;
            }
            String strOptString2 = jSONObject.optString("message", "");
            if (strOptString2 != null) {
                return strOptString2.toLowerCase(locale).contains(lowerCase);
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean b(String str) {
        return a(str, "request_pending") || a(str, "pending");
    }
}
