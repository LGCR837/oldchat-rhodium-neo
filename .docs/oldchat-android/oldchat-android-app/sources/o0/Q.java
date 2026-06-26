package o0;

import android.net.Uri;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class Q {

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f7027a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f7028b;

        public a(String str, String str2) {
            this.f7027a = str == null ? "" : str;
            this.f7028b = str2 == null ? "" : str2;
        }
    }

    public static String a(String str) {
        String strD = d(str);
        return strD.length() == 0 ? "" : new Uri.Builder().scheme("oldchat").authority("group").appendQueryParameter("group_id", strD).build().toString();
    }

    public static String b(String str) {
        String strE = e(str);
        return strE.length() == 0 ? "" : new Uri.Builder().scheme("oldchat").authority("user").appendQueryParameter("uid", strE).build().toString();
    }

    public static String c(String str, String str2) {
        return (str == null || str.trim().length() <= 0) ? (str2 == null || str2.trim().length() <= 0) ? "" : str2.trim() : str.trim();
    }

    public static String d(String str) {
        return str == null ? "" : str.trim().toUpperCase(Locale.US);
    }

    public static String e(String str) {
        if (str == null) {
            return "";
        }
        String strTrim = str.trim();
        while (strTrim.startsWith("@")) {
            strTrim = strTrim.substring(1).trim();
        }
        return strTrim.toUpperCase(Locale.US);
    }

    public static a f(String str) {
        Uri uri;
        if (str == null) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0) {
            return null;
        }
        a aVarG = g(strTrim);
        if (aVarG != null) {
            return aVarG;
        }
        try {
            uri = Uri.parse(strTrim);
        } catch (Exception unused) {
        }
        if (!"oldchat".equalsIgnoreCase(uri.getScheme())) {
            return null;
        }
        String host = uri.getHost();
        if ("group".equalsIgnoreCase(host)) {
            String strD = d(c(uri.getQueryParameter("group_id"), uri.getQueryParameter("id")));
            if (strD.length() == 0) {
                return null;
            }
            return new a("group", strD);
        }
        if ("user".equalsIgnoreCase(host)) {
            String strE = e(c(uri.getQueryParameter("uid"), uri.getQueryParameter("id")));
            if (strE.length() == 0) {
                return null;
            }
            return new a("user", strE);
        }
        return null;
    }

    public static a g(String str) {
        String upperCase = str.toUpperCase(Locale.US);
        if (upperCase.startsWith("OLDCHAT_GROUP:")) {
            String strD = d(str.substring(14));
            if (strD.length() > 0) {
                return new a("group", strD);
            }
            return null;
        }
        if (upperCase.startsWith("OLDCHAT_USER:")) {
            String strE = e(str.substring(13));
            if (strE.length() > 0) {
                return new a("user", strE);
            }
        }
        return null;
    }
}
