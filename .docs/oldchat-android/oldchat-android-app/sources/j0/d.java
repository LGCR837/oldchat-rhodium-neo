package j0;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {
    public static String a(String str, int i2) {
        if (str == null) {
            return "";
        }
        String strTrim = str.replace('\r', ' ').replace('\n', ' ').trim();
        return (i2 <= 0 || strTrim.length() <= i2) ? strTrim : strTrim.substring(0, i2);
    }

    public static String b(w wVar) {
        return wVar == null ? "" : c(wVar.f5649e, wVar.f5648d, wVar.f5647c, wVar.f5646b);
    }

    public static String c(String str, String str2, String str3, String str4) {
        String strD = d(str);
        if (strD.length() > 0) {
            return strD;
        }
        String strD2 = d(str2);
        if (strD2.length() > 0) {
            return strD2;
        }
        String strD3 = d(str3);
        return strD3.length() > 0 ? strD3 : e(str4);
    }

    public static String d(String str) {
        return a(str, 15);
    }

    public static String e(String str) {
        return str == null ? "" : str.replace('\r', ' ').replace('\n', ' ').trim();
    }
}
