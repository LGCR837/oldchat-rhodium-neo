package j0;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    public static void a(Context context) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("friend_cache", 0).edit().clear().apply();
    }

    public static List b(Context context) {
        if (context == null) {
            return new ArrayList();
        }
        String string = context.getSharedPreferences("friend_cache", 0).getString("friends", null);
        if (string == null) {
            return new ArrayList();
        }
        try {
            List list = (List) new z.e().j(string, F.a.c(List.class, w.class).e());
            return list == null ? new ArrayList() : list;
        } catch (Exception unused) {
            return new ArrayList();
        }
    }

    public static void c(Context context, List list) {
        if (context == null || list == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("friend_cache", 0);
        sharedPreferences.edit().putString("friends", new z.e().q(list)).apply();
    }
}
