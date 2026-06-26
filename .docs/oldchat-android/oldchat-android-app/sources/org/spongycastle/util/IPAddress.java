package org.spongycastle.util;

/* JADX INFO: loaded from: classes.dex */
public class IPAddress {
    private static boolean isMaskValue(String str, int i2) {
        try {
            int i3 = Integer.parseInt(str);
            return i3 >= 0 && i3 <= i2;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public static boolean isValid(String str) {
        return isValidIPv4(str) || isValidIPv6(str);
    }

    public static boolean isValidIPv4(String str) {
        int iIndexOf;
        int i2;
        if (str.length() == 0) {
            return false;
        }
        String str2 = str + ".";
        int i3 = 0;
        int i4 = 0;
        while (i3 < str2.length() && (iIndexOf = str2.indexOf(46, i3)) > i3) {
            if (i4 == 4) {
                return false;
            }
            try {
                i2 = Integer.parseInt(str2.substring(i3, iIndexOf));
            } catch (NumberFormatException unused) {
            }
            if (i2 < 0 || i2 > 255) {
                return false;
            }
            i3 = iIndexOf + 1;
            i4++;
        }
        return i4 == 4;
    }

    public static boolean isValidIPv4WithNetmask(String str) {
        int iIndexOf = str.indexOf("/");
        String strSubstring = str.substring(iIndexOf + 1);
        if (iIndexOf <= 0 || !isValidIPv4(str.substring(0, iIndexOf))) {
            return false;
        }
        return isValidIPv4(strSubstring) || isMaskValue(strSubstring, 32);
    }

    public static boolean isValidIPv6(String str) {
        int iIndexOf;
        int i2;
        if (str.length() == 0) {
            return false;
        }
        String str2 = str + ":";
        int i3 = 0;
        int i4 = 0;
        boolean z2 = false;
        while (i3 < str2.length() && (iIndexOf = str2.indexOf(58, i3)) >= i3) {
            if (i4 == 8) {
                return false;
            }
            if (i3 != iIndexOf) {
                String strSubstring = str2.substring(i3, iIndexOf);
                if (iIndexOf != str2.length() - 1 || strSubstring.indexOf(46) <= 0) {
                    try {
                        i2 = Integer.parseInt(str2.substring(i3, iIndexOf), 16);
                    } catch (NumberFormatException unused) {
                    }
                    if (i2 < 0 || i2 > 65535) {
                        return false;
                    }
                } else {
                    if (!isValidIPv4(strSubstring)) {
                        return false;
                    }
                    i4++;
                }
            } else {
                if (iIndexOf != 1 && iIndexOf != str2.length() - 1 && z2) {
                    return false;
                }
                z2 = true;
            }
            i3 = iIndexOf + 1;
            i4++;
        }
        return i4 == 8 || z2;
    }

    public static boolean isValidIPv6WithNetmask(String str) {
        int iIndexOf = str.indexOf("/");
        String strSubstring = str.substring(iIndexOf + 1);
        if (iIndexOf <= 0 || !isValidIPv6(str.substring(0, iIndexOf))) {
            return false;
        }
        return isValidIPv6(strSubstring) || isMaskValue(strSubstring, 128);
    }

    public static boolean isValidWithNetMask(String str) {
        return isValidIPv4WithNetmask(str) || isValidIPv6WithNetmask(str);
    }
}
