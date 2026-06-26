package h0;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {
    public static String a(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b2 : bArrDigest) {
                String hexString = Integer.toHexString(b2 & 255);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(Map map) {
        return c(map, "4409e2ce8ffd12b8", "59b43e04ad6965f34319062b478f83dd");
    }

    public static String c(Map map, String str, String str2) {
        if (map == null) {
            return "";
        }
        map.put("appkey", str);
        ArrayList arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            String str3 = (String) arrayList.get(i2);
            String str4 = (String) map.get(str3);
            if (str4 == null) {
                str4 = "";
            }
            if (i2 > 0) {
                sb.append('&');
            }
            sb.append(d(str3));
            sb.append('=');
            sb.append(d(str4));
        }
        return a(sb.toString() + str2);
    }

    public static String d(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception unused) {
            return str;
        }
    }
}
