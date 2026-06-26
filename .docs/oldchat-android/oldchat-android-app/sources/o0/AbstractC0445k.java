package o0;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

/* JADX INFO: renamed from: o0.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0445k {
    public static String a(Context context) {
        if (context == null) {
            return "";
        }
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            return str == null ? "" : str;
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public static String b(Context context) {
        if (context == null) {
            return "";
        }
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            return string != null ? string : "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String c() {
        String str = Build.MANUFACTURER;
        String strTrim = str == null ? "" : str.trim();
        String str2 = Build.MODEL;
        String strTrim2 = str2 != null ? str2.trim() : "";
        if (strTrim.length() == 0) {
            return strTrim2;
        }
        if (strTrim2.length() == 0) {
            return strTrim;
        }
        if (strTrim2.toLowerCase().startsWith(strTrim.toLowerCase())) {
            return strTrim2;
        }
        return strTrim + " " + strTrim2;
    }

    public static String d(Context context) {
        int i2;
        if (context == null || (i2 = Build.VERSION.SDK_INT) >= 29) {
            return "";
        }
        if (i2 >= 23) {
            try {
                if (context.checkSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
                    return "";
                }
            } catch (Exception unused) {
                return "";
            }
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        String deviceId = telephonyManager.getDeviceId();
        return deviceId != null ? deviceId : "";
    }
}
