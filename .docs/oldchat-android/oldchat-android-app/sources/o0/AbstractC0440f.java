package o0;

import android.content.Context;
import java.io.File;

/* JADX INFO: renamed from: o0.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0440f {
    public static void a(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return;
        }
        context.getSharedPreferences(str, 0).edit().clear().apply();
    }

    public static String b(long j2) {
        if (j2 < 1024) {
            return j2 + " B";
        }
        double d2 = j2;
        Double.isNaN(d2);
        double d3 = d2 / 1024.0d;
        if (d3 < 1024.0d) {
            return String.format("%.1f KB", Double.valueOf(d3));
        }
        double d4 = d3 / 1024.0d;
        return d4 < 1024.0d ? String.format("%.1f MB", Double.valueOf(d4)) : String.format("%.1f GB", Double.valueOf(d4 / 1024.0d));
    }

    public static long c(File file) {
        long jC = 0;
        if (file != null && file.exists()) {
            if (file.isFile()) {
                return file.length();
            }
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                return 0L;
            }
            for (File file2 : fileArrListFiles) {
                jC += c(file2);
            }
        }
        return jC;
    }

    public static long d(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return 0L;
        }
        File file = new File(new File(context.getFilesDir().getParentFile(), "shared_prefs"), str + ".xml");
        if (file.exists()) {
            return file.length();
        }
        return 0L;
    }
}
