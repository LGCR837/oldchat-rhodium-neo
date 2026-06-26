package o0;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class T {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Set f7037a = Collections.synchronizedSet(new HashSet());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f7038b;

    public static void a(Context context) {
        if (f7038b || context == null) {
            return;
        }
        String string = context.getApplicationContext().getSharedPreferences("red_packet_state", 0).getString("done_ids", "");
        if (string != null && string.length() > 0) {
            for (String str : string.split(",")) {
                String strD = d(str);
                if (strD.length() > 0) {
                    f7037a.add(strD);
                }
            }
        }
        f7038b = true;
    }

    public static boolean b(Context context, String str) {
        String strD = d(str);
        if (strD.length() == 0) {
            return false;
        }
        a(context);
        return f7037a.contains(strD);
    }

    public static void c(Context context, String str) {
        String strD = d(str);
        if (strD.length() == 0) {
            return;
        }
        a(context);
        Set set = f7037a;
        if (set.contains(strD)) {
            return;
        }
        set.add(strD);
        e(context);
    }

    public static String d(String str) {
        String upperCase;
        return (str == null || (upperCase = str.trim().toUpperCase()) == null) ? "" : upperCase;
    }

    public static void e(Context context) {
        if (context == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("red_packet_state", 0);
        StringBuilder sb = new StringBuilder();
        Set set = f7037a;
        synchronized (set) {
            try {
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    if (sb.length() > 0) {
                        sb.append(',');
                    }
                    sb.append((String) it.next());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        sharedPreferences.edit().putString("done_ids", sb.toString()).apply();
    }
}
