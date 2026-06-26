package o0;

import android.app.Application;
import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileReader;

/* JADX INFO: loaded from: classes.dex */
public abstract class N {
    public static String a() throws Throwable {
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                Object objInvoke = Application.class.getMethod("getProcessName", null).invoke(null, null);
                if (objInvoke instanceof String) {
                    return (String) objInvoke;
                }
            } catch (Throwable unused) {
            }
        }
        String strB = b();
        if (TextUtils.isEmpty(strB)) {
            return null;
        }
        return strB;
    }

    public static String b() throws Throwable {
        BufferedReader bufferedReader;
        Throwable th;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/self/cmdline"));
        } catch (Exception unused) {
            bufferedReader = null;
        } catch (Throwable th2) {
            bufferedReader = null;
            th = th2;
        }
        try {
            String line = bufferedReader.readLine();
            if (line == null) {
                try {
                    bufferedReader.close();
                } catch (Exception unused2) {
                }
                return null;
            }
            String strTrim = line.trim();
            try {
                bufferedReader.close();
            } catch (Exception unused3) {
            }
            return strTrim;
        } catch (Exception unused4) {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception unused5) {
                }
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception unused6) {
                }
            }
            throw th;
        }
    }
}
