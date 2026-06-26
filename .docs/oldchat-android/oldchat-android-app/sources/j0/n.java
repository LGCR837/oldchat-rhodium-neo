package j0;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class n {
    public static void a(Context context) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("moment_cache", 0).edit().clear().apply();
    }

    public static List b(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return new ArrayList();
        }
        String string = context.getSharedPreferences("moment_cache", 0).getString("comments_" + str, null);
        if (string == null) {
            return new ArrayList();
        }
        try {
            List list = (List) new z.e().j(string, F.a.c(List.class, o.class).e());
            return list == null ? new ArrayList() : list;
        } catch (Exception unused) {
            return new ArrayList();
        }
    }

    public static List c(Context context) {
        if (context == null) {
            return new ArrayList();
        }
        String string = context.getSharedPreferences("moment_cache", 0).getString("feed", null);
        if (string == null) {
            return new ArrayList();
        }
        try {
            List list = (List) new z.e().j(string, F.a.c(List.class, m.class).e());
            return list == null ? new ArrayList() : list;
        } catch (Exception unused) {
            return new ArrayList();
        }
    }

    public static void d(Context context, String str, List list) {
        if (context == null || str == null || str.isEmpty() || list == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("moment_cache", 0);
        String strQ = new z.e().q(list);
        sharedPreferences.edit().putString("comments_" + str, strQ).apply();
    }

    public static void e(Context context, List list) {
        if (context == null || list == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("moment_cache", 0);
        sharedPreferences.edit().putString("feed", new z.e().q(list)).apply();
    }
}
