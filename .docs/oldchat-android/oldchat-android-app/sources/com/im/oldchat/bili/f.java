package com.im.oldchat.bili;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f2264a = {46, 47, 18, 2, 53, 8, 23, 32, 15, 50, 10, 31, 58, 3, 45, 35, 27, 43, 5, 49, 33, 9, 42, 19, 29, 28, 14, 39, 12, 38, 41, 13, 37, 48, 7, 16, 24, 55, 40, 61, 26, 17, 0, 1, 60, 51, 30, 4, 22, 25, 54, 21, 56, 59, 6, 63, 57, 62, 11, 36, 20, 34, 44, 52};

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2265a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f2266b;
    }

    public static String a(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if ((cCharAt < 'a' || cCharAt > 'z') && ((cCharAt < 'A' || cCharAt > 'Z') && !((cCharAt >= '0' && cCharAt <= '9') || cCharAt == '-' || cCharAt == '_' || cCharAt == '.' || cCharAt == '~'))) {
                sb.append('%');
                String upperCase = Integer.toHexString(cCharAt).toUpperCase();
                if (upperCase.length() == 1) {
                    sb.append('0');
                }
                sb.append(upperCase);
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    public static String b(String str) {
        if (str == null || str.length() < 64) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < 32; i2++) {
            sb.append(str.charAt(f2264a[i2]));
        }
        return sb.toString();
    }

    public static String c(String str) {
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

    public static String d(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt != '!' && cCharAt != '\'' && cCharAt != '(' && cCharAt != ')' && cCharAt != '*') {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    public static a e(Map map, String str, String str2) {
        if (map == null || str == null || str2 == null) {
            return null;
        }
        String strB = b(str + str2);
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        map.put("wts", String.valueOf(jCurrentTimeMillis));
        ArrayList arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            String str3 = (String) arrayList.get(i2);
            String str4 = (String) map.get(str3);
            if (str4 == null) {
                str4 = "";
            }
            String strD = d(str4);
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(str3);
            sb.append("=");
            sb.append(a(strD));
        }
        String strC = c(sb.toString() + strB);
        a aVar = new a();
        aVar.f2265a = strC;
        aVar.f2266b = jCurrentTimeMillis;
        return aVar;
    }
}
