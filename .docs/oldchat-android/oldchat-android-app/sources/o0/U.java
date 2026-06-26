package o0;

import android.content.Context;
import android.content.SharedPreferences;
import java.net.URI;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class U {
    public static void a(Context context) {
        if (context == null) {
            return;
        }
        SharedPreferences sharedPreferencesD = d(context);
        String string = sharedPreferencesD.getString("server_base_url", null);
        String strC = c(string);
        if (strC != null) {
            g0.e.f5335a = strC;
            return;
        }
        g0.e.f5335a = "http://60.205.94.101:8080/v1";
        if (string == null || string.length() <= 0) {
            return;
        }
        sharedPreferencesD.edit().remove("server_base_url").apply();
    }

    public static String b() {
        return g0.e.f5335a;
    }

    public static String c(String str) {
        if (str == null) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0) {
            return null;
        }
        if (strTrim.indexOf("://") < 0) {
            strTrim = "http://" + strTrim;
        }
        try {
            URI uri = new URI(strTrim);
            String scheme = uri.getScheme();
            String rawAuthority = uri.getRawAuthority();
            if (scheme != null && rawAuthority != null && rawAuthority.length() != 0) {
                String lowerCase = scheme.toLowerCase(Locale.US);
                if (("http".equals(lowerCase) || "https".equals(lowerCase)) && uri.getRawQuery() == null && uri.getRawFragment() == null) {
                    String rawPath = uri.getRawPath();
                    if (rawPath == null || rawPath.length() == 0 || "/".equals(rawPath)) {
                        rawPath = "/v1";
                    } else {
                        while (rawPath.endsWith("/") && rawPath.length() > 1) {
                            rawPath = rawPath.substring(0, rawPath.length() - 1);
                        }
                    }
                    return lowerCase + "://" + rawAuthority + rawPath;
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static SharedPreferences d(Context context) {
        return context.getSharedPreferences("settings", 0);
    }

    public static void e(Context context) {
        g0.e.f5335a = "http://60.205.94.101:8080/v1";
        if (context != null) {
            d(context).edit().remove("server_base_url").apply();
        }
    }

    public static String f(Context context, String str) {
        String strC = c(str);
        if (strC == null) {
            return null;
        }
        g0.e.f5335a = strC;
        if (context != null) {
            d(context).edit().putString("server_base_url", strC).apply();
        }
        return strC;
    }
}
