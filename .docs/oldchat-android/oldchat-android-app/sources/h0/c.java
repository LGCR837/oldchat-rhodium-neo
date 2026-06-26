package h0;

import java.net.URLDecoder;
import java.net.URLEncoder;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f5437a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f5438b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f5439c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f5440d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f5441e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f5442f;
    }

    public static boolean a(StringBuilder sb, String str, String str2, boolean z2) {
        if (z2) {
            sb.append('&');
        } else {
            sb.append('?');
            z2 = true;
        }
        sb.append(str);
        sb.append('=');
        sb.append(g(str2));
        return z2;
    }

    public static String b(String str, long j2, long j3, String str2, String str3, long j4) {
        StringBuilder sb = new StringBuilder();
        sb.append("oldchat://bili");
        boolean zA = false;
        if (str != null && str.length() > 0) {
            zA = a(sb, "bvid", str, false);
        }
        if (j2 > 0) {
            zA = a(sb, "aid", String.valueOf(j2), zA);
        }
        if (j3 > 0) {
            zA = a(sb, "cid", String.valueOf(j3), zA);
        }
        if (str2 != null && str2.length() > 0) {
            zA = a(sb, MessageBundle.TITLE_ENTRY, str2, zA);
        }
        if (str3 != null && str3.length() > 0) {
            zA = a(sb, "cover", str3, zA);
        }
        if (j4 > 0) {
            a(sb, "duration", String.valueOf(j4), zA);
        }
        return sb.toString();
    }

    public static boolean c(String str) {
        return str != null && str.startsWith("oldchat://bili");
    }

    public static long d(String str) {
        if (str == null || str.length() == 0) {
            return 0L;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static a e(String str) {
        int iIndexOf;
        a aVar = new a();
        if (str != null && (iIndexOf = str.indexOf(63)) >= 0 && iIndexOf < str.length() - 1) {
            String strSubstring = str.substring(iIndexOf + 1);
            int length = strSubstring.length();
            int i2 = 0;
            while (i2 < length) {
                int iIndexOf2 = strSubstring.indexOf(38, i2);
                if (iIndexOf2 < 0) {
                    iIndexOf2 = length;
                }
                int iIndexOf3 = strSubstring.indexOf(61, i2);
                if (iIndexOf3 >= 0 && iIndexOf3 <= iIndexOf2) {
                    String strSubstring2 = strSubstring.substring(i2, iIndexOf3);
                    String strF = f(strSubstring.substring(iIndexOf3 + 1, iIndexOf2));
                    if ("bvid".equals(strSubstring2)) {
                        aVar.f5437a = strF;
                    } else if ("aid".equals(strSubstring2)) {
                        aVar.f5438b = d(strF);
                    } else if ("cid".equals(strSubstring2)) {
                        aVar.f5439c = d(strF);
                    } else if (MessageBundle.TITLE_ENTRY.equals(strSubstring2)) {
                        aVar.f5440d = strF;
                    } else if ("cover".equals(strSubstring2)) {
                        aVar.f5441e = strF;
                    } else if ("duration".equals(strSubstring2)) {
                        aVar.f5442f = d(strF);
                    }
                }
                i2 = iIndexOf2 + 1;
            }
        }
        return aVar;
    }

    public static String f(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (Exception unused) {
            return str;
        }
    }

    public static String g(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception unused) {
            return str;
        }
    }
}
