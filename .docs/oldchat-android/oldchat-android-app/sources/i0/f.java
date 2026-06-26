package i0;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {
    public static void a(Context context) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("notification_read", 0).edit().clear().apply();
    }

    public static Set b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("notification_read", 0);
        if (Build.VERSION.SDK_INT >= 11) {
            Collection stringSet = sharedPreferences.getStringSet("read_ids", new HashSet());
            if (stringSet == null) {
                stringSet = new HashSet();
            }
            return new HashSet(stringSet);
        }
        String string = sharedPreferences.getString("read_ids", "");
        HashSet hashSet = new HashSet();
        if (string != null && string.length() > 0) {
            for (String str : string.split("\\|")) {
                if (str != null && str.length() > 0) {
                    hashSet.add(str);
                }
            }
        }
        return hashSet;
    }

    public static boolean c(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return false;
        }
        return b(context).contains(str);
    }

    public static void d(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return;
        }
        Set setB = b(context);
        setB.add(str);
        e(context, setB);
    }

    public static void e(Context context, Set set) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("notification_read", 0);
        if (Build.VERSION.SDK_INT >= 11) {
            sharedPreferences.edit().putStringSet("read_ids", set).apply();
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (set != null) {
            Iterator it = set.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (str != null && str.length() != 0) {
                    if (sb.length() > 0) {
                        sb.append('|');
                    }
                    sb.append(str);
                }
            }
        }
        sharedPreferences.edit().putString("read_ids", sb.toString()).apply();
    }
}
