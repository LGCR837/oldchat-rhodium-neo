package o0;

import android.content.Context;
import java.io.File;
import java.security.MessageDigest;
import java.util.Locale;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public abstract class r {
    public static String a(String str) {
        return d(e(str));
    }

    public static void b(File file) {
        File[] fileArrListFiles;
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                b(file2);
            }
        }
        file.delete();
    }

    public static File c(Context context, String str, String str2) {
        if (context == null || str2 == null) {
            return null;
        }
        File file = new File(context.getCacheDir(), str);
        if (!file.exists() && !file.mkdirs()) {
            return null;
        }
        return new File(file, a(str2) + ".img");
    }

    public static String d(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA1).digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
            for (byte b2 : bArrDigest) {
                String hexString = Integer.toHexString(b2 & 255);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (Exception unused) {
            return String.valueOf(str.hashCode());
        }
    }

    public static String e(String str) {
        if (str == null) {
            return "";
        }
        String strTrim = str.trim();
        int iIndexOf = strTrim.indexOf(35);
        if (iIndexOf >= 0) {
            strTrim = strTrim.substring(0, iIndexOf);
        }
        int iIndexOf2 = strTrim.indexOf(63);
        if (iIndexOf2 <= 0) {
            return strTrim;
        }
        String lowerCase = strTrim.substring(0, iIndexOf2).toLowerCase(Locale.US);
        return (lowerCase.contains("/uploads/") || lowerCase.contains("/v1/uploads/")) ? strTrim.substring(0, iIndexOf2) : strTrim;
    }
}
