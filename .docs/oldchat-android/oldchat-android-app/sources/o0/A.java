package o0;

import java.net.URL;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class A {
    public static String a(String str, String str2, int i2) {
        if (str == null) {
            str = "http";
        }
        if (str2 == null) {
            str2 = "";
        }
        if (i2 <= 0) {
            return f(str + "://" + str2);
        }
        return f(str + "://" + str2 + ":" + i2);
    }

    public static String[] b(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (str != null) {
                String strTrim = str.trim();
                if (strTrim.length() != 0) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= arrayList.size()) {
                            arrayList.add(strTrim);
                            break;
                        }
                        if (strTrim.equals(arrayList.get(i2))) {
                            break;
                        }
                        i2++;
                    }
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static boolean c(String str) {
        if (str == null) {
            return false;
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0) {
            return false;
        }
        if (!strTrim.startsWith("http://") && !strTrim.startsWith("https://")) {
            return false;
        }
        try {
            URL url = new URL(strTrim);
            return l(a(url.getProtocol(), url.getHost(), url.getPort()), "http://154.8.227.219:9090");
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean d(String str) {
        String strK;
        if (str == null) {
            return false;
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0) {
            return false;
        }
        if ((strTrim.startsWith("http://") || strTrim.startsWith("https://")) && (strK = k()) != null && strK.length() != 0) {
            try {
                URL url = new URL(strTrim);
                return l(a(url.getProtocol(), url.getHost(), url.getPort()), strK);
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static boolean e(String str) {
        if (str == null) {
            return false;
        }
        String strTrim = str.trim();
        return strTrim.startsWith("/uploads/") || strTrim.startsWith("/v1/uploads/") || strTrim.startsWith("uploads/") || strTrim.startsWith("v1/uploads/");
    }

    public static String f(String str) {
        if (str == null) {
            return "";
        }
        String strTrim = str.trim();
        while (strTrim.endsWith("/")) {
            strTrim = strTrim.substring(0, strTrim.length() - 1);
        }
        return strTrim;
    }

    public static String g(String str) {
        String[] strArrI = i(str);
        return strArrI.length > 0 ? strArrI[0] : "";
    }

    public static String[] h(String str, String str2) {
        try {
            URL url = new URL(str);
            String file = url.getFile();
            if (file == null || file.length() == 0) {
                file = "/";
            }
            if (!e(file)) {
                return new String[]{str};
            }
            String strA = a(url.getProtocol(), url.getHost(), url.getPort());
            String strF = f("http://154.8.227.219:9090");
            String strF2 = f(str2);
            return l(strA, strF) ? b(new String[]{strF2 + file, str}) : l(strA, strF2) ? b(new String[]{str, strF + file}) : new String[]{str};
        } catch (Exception unused) {
            return new String[]{str};
        }
    }

    public static String[] i(String str) {
        if (str == null) {
            return new String[0];
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0) {
            return new String[0];
        }
        String strK = k();
        if (strTrim.startsWith("http://") || strTrim.startsWith("https://")) {
            return h(strTrim, strK);
        }
        if (strTrim.startsWith("/")) {
            if (!e(strTrim)) {
                return b(new String[]{strK + strTrim});
            }
            return b(new String[]{strK + strTrim, "http://154.8.227.219:9090" + strTrim});
        }
        if (!e(strTrim)) {
            return new String[]{strTrim};
        }
        if (!strTrim.startsWith("/")) {
            strTrim = "/" + strTrim;
        }
        return b(new String[]{strK + strTrim, "http://154.8.227.219:9090" + strTrim});
    }

    public static String j(String str) {
        String[] strArrI = i(str);
        return strArrI.length > 1 ? strArrI[1] : "";
    }

    public static String k() {
        String str = g0.e.f5335a;
        if (str == null) {
            return "";
        }
        String strTrim = str.trim();
        int iIndexOf = strTrim.indexOf("/v1");
        return iIndexOf > 0 ? f(strTrim.substring(0, iIndexOf)) : f(strTrim);
    }

    public static boolean l(String str, String str2) {
        return f(str).equals(f(str2));
    }
}
