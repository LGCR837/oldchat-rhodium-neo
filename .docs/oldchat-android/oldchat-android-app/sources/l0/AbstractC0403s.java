package l0;

import java.util.Locale;

/* JADX INFO: renamed from: l0.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0403s {
    public static String a(String str, long j2, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("文件: ");
        sb.append(f(str));
        if (j2 > 0) {
            sb.append("\n大小: ");
            sb.append(e(j2));
        }
        if (str2 != null && str2.trim().length() > 0) {
            sb.append("\n");
            sb.append(str2.trim());
        }
        return sb.toString();
    }

    public static String b(int i2) {
        int i3 = (i2 * 10) / 100;
        int i4 = 0;
        if (i3 < 0) {
            i3 = 0;
        }
        if (i3 > 10) {
            i3 = 10;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (i4 < 10) {
            sb.append(i4 < i3 ? "■" : "□");
            i4++;
        }
        sb.append("]");
        return sb.toString();
    }

    public static String c(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 > 100) {
            i2 = 100;
        }
        return "上传中 " + b(i2);
    }

    public static String d(String str) {
        if (str != null && str.length() != 0) {
            String[] strArrSplit = str.split("\\r?\\n");
            for (int i2 = 0; i2 < strArrSplit.length; i2++) {
                String str2 = strArrSplit[i2];
                String strTrim = str2 == null ? "" : str2.trim();
                if (strTrim.startsWith("上传中") || strTrim.startsWith("发送中") || strTrim.startsWith("上传失败")) {
                    return strTrim;
                }
            }
        }
        return "";
    }

    public static String e(long j2) {
        if (j2 <= 0) {
            return "未知";
        }
        double d2 = j2;
        String[] strArr = {"B", "KB", "MB", "GB", "TB"};
        int i2 = 0;
        while (d2 >= 1024.0d && i2 < 4) {
            d2 /= 1024.0d;
            i2++;
        }
        return i2 == 0 ? String.format(Locale.US, "%d %s", Long.valueOf((long) d2), strArr[i2]) : String.format(Locale.US, "%.1f %s", Double.valueOf(d2), strArr[i2]);
    }

    public static String f(String str) {
        String strTrim = str == null ? "" : str.trim();
        return strTrim.length() == 0 ? "未命名文件" : strTrim;
    }
}
