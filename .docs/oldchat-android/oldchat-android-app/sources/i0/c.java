package i0;

import android.content.Context;
import android.content.SharedPreferences;
import j0.m;
import j0.p;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.spongycastle.asn1.x509.DisplayText;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5445a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5446b;

        public a(int i2, int i3) {
            this.f5445a = i2;
            this.f5446b = i3;
        }
    }

    public static void a(Context context) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("moment_notice", 0).edit().clear().apply();
    }

    public static void b(Context context) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("moment_notice", 0).edit().remove("notices").apply();
    }

    public static int c(Context context, List list, String str) {
        String str2;
        if (context == null || list == null || str == null || str.isEmpty()) {
            return 0;
        }
        Map mapE = e(context);
        List listD = d(context);
        ArrayList arrayList = new ArrayList();
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            m mVar = (m) it.next();
            if (mVar != null && (str2 = mVar.f5567a) != null && !str2.isEmpty() && str.equals(mVar.f5568b)) {
                a aVar = (a) mapE.get(mVar.f5567a);
                if (aVar != null) {
                    int i2 = mVar.f5575i - aVar.f5445a;
                    int i3 = mVar.f5576j - aVar.f5446b;
                    if (i3 > 0) {
                        p pVar = new p();
                        pVar.f5587a = mVar.f5567a + ":comment:" + jCurrentTimeMillis;
                        pVar.f5588b = mVar.f5567a;
                        pVar.f5589c = mVar.f5572f;
                        pVar.f5590d = mVar.f5573g;
                        pVar.f5591e = mVar.f5568b;
                        pVar.f5592f = "comment";
                        pVar.f5593g = i3;
                        pVar.f5594h = jCurrentTimeMillis;
                        arrayList.add(pVar);
                    }
                    if (i2 > 0) {
                        p pVar2 = new p();
                        pVar2.f5587a = mVar.f5567a + ":like:" + jCurrentTimeMillis;
                        pVar2.f5588b = mVar.f5567a;
                        pVar2.f5589c = mVar.f5572f;
                        pVar2.f5590d = mVar.f5573g;
                        pVar2.f5591e = mVar.f5568b;
                        pVar2.f5592f = "like";
                        pVar2.f5593g = i2;
                        pVar2.f5594h = jCurrentTimeMillis;
                        arrayList.add(pVar2);
                    }
                }
                mapE.put(mVar.f5567a, new a(mVar.f5575i, mVar.f5576j));
            }
        }
        if (!arrayList.isEmpty()) {
            List arrayList2 = new ArrayList();
            arrayList2.addAll(arrayList);
            if (listD != null && !listD.isEmpty()) {
                arrayList2.addAll(listD);
            }
            if (arrayList2.size() > 200) {
                arrayList2 = arrayList2.subList(0, DisplayText.DISPLAY_TEXT_MAXIMUM_SIZE);
            }
            g(context, arrayList2);
        }
        h(context, mapE);
        return arrayList.size();
    }

    public static List d(Context context) {
        if (context == null) {
            return new ArrayList();
        }
        String string = context.getSharedPreferences("moment_notice", 0).getString("notices", null);
        if (string == null) {
            return new ArrayList();
        }
        try {
            List list = (List) new z.e().j(string, F.a.c(List.class, p.class).e());
            return list == null ? new ArrayList() : list;
        } catch (Exception unused) {
            return new ArrayList();
        }
    }

    public static Map e(Context context) {
        if (context == null) {
            return new HashMap();
        }
        String string = context.getSharedPreferences("moment_notice", 0).getString("stats", null);
        if (string == null) {
            return new HashMap();
        }
        try {
            Map map = (Map) new z.e().j(string, F.a.c(Map.class, String.class, a.class).e());
            return map == null ? new HashMap() : map;
        } catch (Exception unused) {
            return new HashMap();
        }
    }

    public static boolean f(Context context) {
        List listD = d(context);
        return (listD == null || listD.isEmpty()) ? false : true;
    }

    public static void g(Context context, List list) {
        if (context == null || list == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("moment_notice", 0);
        sharedPreferences.edit().putString("notices", new z.e().q(list)).apply();
    }

    public static void h(Context context, Map map) {
        if (context == null || map == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("moment_notice", 0);
        sharedPreferences.edit().putString("stats", new z.e().q(map)).apply();
    }
}
