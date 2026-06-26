package j0;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    public class a implements Comparator {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(b bVar, b bVar2) {
            if (bVar == bVar2) {
                return 0;
            }
            if (bVar == null) {
                return 1;
            }
            if (bVar2 == null) {
                return -1;
            }
            long j2 = bVar.f5545e;
            long j3 = bVar2.f5545e;
            if (j2 < j3) {
                return 1;
            }
            if (j2 > j3) {
                return -1;
            }
            String str = bVar.f5541a;
            if (str == null) {
                str = "";
            }
            String str2 = bVar2.f5541a;
            if (str2 == null) {
                str2 = "";
            }
            int iCompareTo = str.compareTo(str2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            String str3 = bVar.f5542b;
            if (str3 == null) {
                str3 = "";
            }
            String str4 = bVar2.f5542b;
            return str3.compareTo(str4 != null ? str4 : "");
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f5541a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f5542b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f5543c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f5544d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f5545e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f5546f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f5547g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f5548h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f5549i;

        public b(String str, String str2, String str3, String str4, long j2, int i2, int i3, boolean z2, int i4) {
            this.f5541a = str;
            this.f5542b = str2;
            this.f5543c = str3;
            this.f5544d = str4;
            this.f5545e = j2;
            this.f5546f = i2;
            this.f5547g = i3;
            this.f5548h = z2;
            this.f5549i = i4;
        }
    }

    public static void a(Context context) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("recent_groups", 0).edit().clear().apply();
    }

    public static void b(Context context, String str) {
        l(context, str, null, null, null, 0L, 0, true, -1);
        g(context, str, false);
    }

    public static List c(Context context) {
        if (context == null) {
            return new ArrayList();
        }
        String string = context.getSharedPreferences("recent_groups", 0).getString("list", null);
        if (string == null) {
            return new ArrayList();
        }
        try {
            List list = (List) new z.e().j(string, F.a.c(List.class, b.class).e());
            if (list == null) {
                return new ArrayList();
            }
            Collections.sort(list, new a());
            return list;
        } catch (Exception unused) {
            return new ArrayList();
        }
    }

    public static void d(Context context, List list) {
        b bVar;
        if (context == null || list == null || list.isEmpty()) {
            return;
        }
        boolean z2 = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("recent_groups", 0);
        List listC = c(context);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            String str = eVar.f5504a;
            if (str != null && !str.isEmpty()) {
                Iterator it2 = listC.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        bVar = null;
                        break;
                    } else {
                        bVar = (b) it2.next();
                        if (eVar.f5504a.equals(bVar.f5541a)) {
                            break;
                        }
                    }
                }
                if (bVar == null) {
                    int i2 = eVar.f5514k;
                    listC.add(new b(eVar.f5504a, eVar.f5505b, eVar.f5506c, "", 0L, 0, eVar.f5509f, false, i2 > 0 ? i2 : -1));
                } else {
                    String str2 = eVar.f5505b;
                    if (str2 != null && !str2.isEmpty() && !eVar.f5505b.equals(bVar.f5542b)) {
                        bVar.f5542b = eVar.f5505b;
                        z2 = true;
                    }
                    String str3 = eVar.f5506c;
                    if (str3 != null && !str3.isEmpty() && !eVar.f5506c.equals(bVar.f5543c)) {
                        bVar.f5543c = eVar.f5506c;
                        z2 = true;
                    }
                    int i3 = bVar.f5547g;
                    int i4 = eVar.f5509f;
                    if (i3 != i4) {
                        bVar.f5547g = i4;
                        z2 = true;
                    }
                    int i5 = eVar.f5514k;
                    if (i5 > 0 && bVar.f5549i != i5) {
                        bVar.f5549i = i5;
                    }
                }
                z2 = true;
            }
        }
        if (z2) {
            sharedPreferences.edit().putString("list", new z.e().q(listC)).apply();
        }
    }

    public static long e(long j2) {
        return j2 <= 0 ? System.currentTimeMillis() : j2 < 100000000000L ? j2 * 1000 : j2;
    }

    public static void f(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return;
        }
        boolean z2 = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("recent_groups", 0);
        List listC = c(context);
        for (int size = listC.size() - 1; size >= 0; size--) {
            if (str.equals(((b) listC.get(size)).f5541a)) {
                listC.remove(size);
                z2 = true;
            }
        }
        if (z2) {
            sharedPreferences.edit().putString("list", new z.e().q(listC)).apply();
        }
    }

    public static void g(Context context, String str, boolean z2) {
        if (context == null || str == null || str.isEmpty()) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("recent_groups", 0);
        List<b> listC = c(context);
        for (b bVar : listC) {
            if (str.equals(bVar.f5541a)) {
                if (bVar.f5548h != z2) {
                    bVar.f5548h = z2;
                    sharedPreferences.edit().putString("list", new z.e().q(listC)).apply();
                    return;
                }
                return;
            }
        }
    }

    public static void h(Context context, String str, String str2, String str3, String str4, long j2, int i2) {
        m(context, str, str2, str3, str4, e(j2), i2, -1);
    }

    public static void i(Context context, String str, String str2, String str3, int i2) {
        l(context, str, str2, str3, null, System.currentTimeMillis(), 0, false, i2);
    }

    public static void j(Context context, String str, String str2, String str3, String str4, long j2, int i2) {
        l(context, str, str2, str3, str4, e(j2), i2, false, -1);
    }

    public static void k(Context context, String str, String str2, String str3, String str4, long j2) {
        l(context, str, str2, str3, str4, e(j2), 0, false, -1);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void l(android.content.Context r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, long r22, int r24, boolean r25, int r26) {
        /*
            Method dump skipped, instruction units count: 221
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: j0.j.l(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, long, int, boolean, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m(android.content.Context r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, long r21, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 207
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: j0.j.m(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, long, int, int):void");
    }

    public static void n(Context context, String str, int i2) {
        if (context == null || str == null || str.isEmpty() || i2 <= 0) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("recent_groups", 0);
        List<b> listC = c(context);
        for (b bVar : listC) {
            if (str.equals(bVar.f5541a)) {
                if (bVar.f5549i != i2) {
                    bVar.f5549i = i2;
                    sharedPreferences.edit().putString("list", new z.e().q(listC)).apply();
                    return;
                }
                return;
            }
        }
    }

    public static void o(Context context, String str, String str2) {
        if (context == null || str == null || str.isEmpty()) {
            return;
        }
        String strTrim = str2 == null ? "" : str2.trim();
        if (strTrim.length() == 0) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("recent_groups", 0);
        List<b> listC = c(context);
        for (b bVar : listC) {
            if (str.equals(bVar.f5541a)) {
                String str3 = bVar.f5542b;
                if (str3 == null || !strTrim.equals(str3)) {
                    bVar.f5542b = strTrim;
                    sharedPreferences.edit().putString("list", new z.e().q(listC)).apply();
                    return;
                }
                return;
            }
        }
    }
}
