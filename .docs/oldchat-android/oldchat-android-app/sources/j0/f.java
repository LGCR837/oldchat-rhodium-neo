package j0;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {
    public static void a(Context context) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("group_cache", 0).edit().clear().apply();
    }

    public static List b(Context context) {
        if (context == null) {
            return new ArrayList();
        }
        String string = context.getSharedPreferences("group_cache", 0).getString("groups", null);
        if (string == null) {
            return new ArrayList();
        }
        try {
            List list = (List) new z.e().j(string, F.a.c(List.class, e.class).e());
            return list == null ? new ArrayList() : list;
        } catch (Exception unused) {
            return new ArrayList();
        }
    }

    public static void c(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return;
        }
        List listB = b(context);
        boolean z2 = false;
        for (int size = listB.size() - 1; size >= 0; size--) {
            e eVar = (e) listB.get(size);
            if (eVar != null && str.equals(eVar.f5504a)) {
                listB.remove(size);
                z2 = true;
            }
        }
        if (z2) {
            d(context, listB);
        }
    }

    public static void d(Context context, List list) {
        if (context == null || list == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("group_cache", 0);
        sharedPreferences.edit().putString("groups", new z.e().q(list)).apply();
    }

    public static void e(Context context, String str, int i2) {
        if (context == null || str == null || str.isEmpty() || i2 <= 0) {
            return;
        }
        List<e> listB = b(context);
        for (e eVar : listB) {
            if (eVar != null && str.equals(eVar.f5504a)) {
                if (eVar.f5514k != i2) {
                    eVar.f5514k = i2;
                    d(context, listB);
                    return;
                }
                return;
            }
        }
    }

    public static void f(Context context, String str, String str2) {
        if (context == null || str == null || str.isEmpty()) {
            return;
        }
        String strTrim = str2 == null ? "" : str2.trim();
        if (strTrim.length() == 0) {
            return;
        }
        List<e> listB = b(context);
        for (e eVar : listB) {
            if (eVar != null && str.equals(eVar.f5504a)) {
                String str3 = eVar.f5505b;
                if (str3 == null || !strTrim.equals(str3)) {
                    eVar.f5505b = strTrim;
                    d(context, listB);
                    return;
                }
                return;
            }
        }
    }
}
