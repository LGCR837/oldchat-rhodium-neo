package j0;

import android.content.Context;
import android.content.SharedPreferences;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public abstract class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final z.e f5562a = new z.e();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Executor f5563b = Executors.newSingleThreadExecutor();

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f5564a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f5565b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ List f5566c;

        public a(Context context, String str, List list) {
            this.f5564a = context;
            this.f5565b = str;
            this.f5566c = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            l.j(this.f5564a, this.f5565b, this.f5566c);
        }
    }

    public static void b(Context context) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("message_history_cache", 0).edit().clear().apply();
    }

    public static List c(Context context, String str) {
        if (context == null) {
            return new ArrayList();
        }
        Map<String, ?> all = context.getSharedPreferences("message_history_cache", 0).getAll();
        ArrayList arrayList = new ArrayList();
        for (String str2 : all.keySet()) {
            if (str2 != null && str2.startsWith(str)) {
                String strSubstring = str2.substring(str.length());
                if (!strSubstring.isEmpty()) {
                    arrayList.add(strSubstring);
                }
            }
        }
        return arrayList;
    }

    public static List d(Context context) {
        return c(context, "direct_");
    }

    public static List e(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return new ArrayList();
        }
        return h(context, "direct_" + str, F.a.c(List.class, k.class).e());
    }

    public static List f(Context context) {
        return c(context, "group_");
    }

    public static List g(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return new ArrayList();
        }
        return h(context, "group_" + str, F.a.c(List.class, i.class).e());
    }

    public static List h(Context context, String str, Type type) {
        String string = context.getSharedPreferences("message_history_cache", 0).getString(str, null);
        if (string == null) {
            return new ArrayList();
        }
        try {
            List list = (List) f5562a.j(string, type);
            return list == null ? new ArrayList() : list;
        } catch (Exception unused) {
            return new ArrayList();
        }
    }

    public static void i(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return;
        }
        context.getSharedPreferences("message_history_cache", 0).edit().remove("group_" + str).apply();
    }

    public static void j(Context context, String str, List list) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("message_history_cache", 0);
        sharedPreferences.edit().putString(str, f5562a.q(list)).apply();
    }

    public static void k(Context context, String str, List list) {
        if (context == null || str == null || str.isEmpty() || list == null) {
            return;
        }
        f5563b.execute(new a(context, str, list));
    }

    public static void l(Context context, String str, List list) {
        if (context == null || str == null || str.isEmpty() || list == null) {
            return;
        }
        k(context.getApplicationContext(), "direct_" + str, n(list));
    }

    public static void m(Context context, String str, List list) {
        if (context == null || str == null || str.isEmpty() || list == null) {
            return;
        }
        k(context.getApplicationContext(), "group_" + str, n(list));
    }

    public static List n(List list) {
        if (list == null) {
            return new ArrayList();
        }
        int size = list.size();
        return size <= 200 ? new ArrayList(list) : new ArrayList(list.subList(size - 200, size));
    }
}
