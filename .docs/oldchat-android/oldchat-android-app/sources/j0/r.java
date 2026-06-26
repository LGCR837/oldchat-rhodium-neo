package j0;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class r {

    public class a implements Comparator {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(b bVar, b bVar2) {
            String str;
            String str2;
            if (bVar == bVar2) {
                return 0;
            }
            if (bVar == null) {
                return 1;
            }
            if (bVar2 == null) {
                return -1;
            }
            long j2 = bVar.f5608f;
            long j3 = bVar2.f5608f;
            if (j2 < j3) {
                return 1;
            }
            if (j2 > j3) {
                return -1;
            }
            String str3 = bVar.f5604b;
            if (str3 == null || str3.length() <= 0) {
                str = bVar.f5603a;
                if (str == null) {
                    str = "";
                }
            } else {
                str = bVar.f5604b;
            }
            String str4 = bVar2.f5604b;
            if (str4 == null || str4.length() <= 0) {
                str2 = bVar2.f5603a;
                if (str2 == null) {
                    str2 = "";
                }
            } else {
                str2 = bVar2.f5604b;
            }
            int iCompareTo = str.compareTo(str2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            String str5 = bVar.f5605c;
            if (str5 == null) {
                str5 = "";
            }
            String str6 = bVar2.f5605c;
            return str5.compareTo(str6 != null ? str6 : "");
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f5603a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f5604b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f5605c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f5606d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f5607e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f5608f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f5609g;

        public b(String str, String str2, String str3, String str4, String str5, long j2, int i2) {
            this.f5603a = str;
            this.f5604b = str2;
            this.f5605c = str3;
            this.f5606d = str4;
            this.f5607e = str5;
            this.f5608f = j2;
            this.f5609g = i2;
        }
    }

    public static void a(Context context, List list) {
        if (context == null || list == null) {
            return;
        }
        boolean z2 = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("recent_chats", 0);
        List listD = d(context);
        for (int size = listD.size() - 1; size >= 0; size--) {
            b bVar = (b) listD.get(size);
            String str = bVar.f5604b;
            if (str != null && !str.isEmpty()) {
                Iterator it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        listD.remove(size);
                        z2 = true;
                        break;
                    } else {
                        String str2 = ((w) it.next()).f5646b;
                        if (str2 == null || !str2.equals(bVar.f5604b)) {
                        }
                    }
                }
            }
        }
        if (z2) {
            sharedPreferences.edit().putString("list", new z.e().q(listD)).apply();
        }
    }

    public static void b(Context context) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("recent_chats", 0).edit().clear().apply();
    }

    public static void c(Context context, String str) {
        m(context, str, null, null, null, 0L, 0, true);
    }

    public static List d(Context context) {
        if (context == null) {
            return new ArrayList();
        }
        String string = context.getSharedPreferences("recent_chats", 0).getString("list", null);
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

    public static void e(Context context, List list) {
        if (context == null || list == null || list.isEmpty()) {
            return;
        }
        boolean z2 = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("recent_chats", 0);
        List<b> listD = d(context);
        for (b bVar : listD) {
            String str = bVar.f5604b;
            if (str != null && !str.isEmpty()) {
                Iterator it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    w wVar = (w) it.next();
                    String str2 = wVar.f5646b;
                    if (str2 != null && str2.equals(bVar.f5604b)) {
                        String strB = d.b(wVar);
                        if (strB != null && !strB.isEmpty() && !strB.equals(bVar.f5605c)) {
                            bVar.f5605c = strB;
                            z2 = true;
                        }
                        String str3 = wVar.f5651g;
                        if (str3 != null && !str3.isEmpty() && !wVar.f5651g.equals(bVar.f5606d)) {
                            bVar.f5606d = wVar.f5651g;
                            z2 = true;
                        }
                    }
                }
            }
        }
        if (z2) {
            sharedPreferences.edit().putString("list", new z.e().q(listD)).apply();
        }
    }

    public static long f(long j2) {
        return j2 <= 0 ? System.currentTimeMillis() : j2 < 100000000000L ? j2 * 1000 : j2;
    }

    public static void g(Context context, String str) {
        h(context, str);
    }

    public static void h(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return;
        }
        boolean z2 = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("recent_chats", 0);
        List listD = d(context);
        for (int size = listD.size() - 1; size >= 0; size--) {
            b bVar = (b) listD.get(size);
            String str2 = bVar.f5604b;
            if (str2 == null) {
                str2 = bVar.f5603a;
            }
            if (str2 != null && str2.equals(str)) {
                listD.remove(size);
                z2 = true;
            }
        }
        if (z2) {
            sharedPreferences.edit().putString("list", new z.e().q(listD)).apply();
        }
    }

    public static void i(Context context, String str, String str2, String str3, String str4, long j2, int i2) {
        o(context, str, str2, str3, str4, f(j2), i2);
    }

    public static void j(Context context, String str, String str2, String str3) {
        m(context, str, str2, str3, null, System.currentTimeMillis(), 0, false);
    }

    public static void k(Context context, String str, String str2, String str3, String str4) {
        m(context, str, str2, str3, str4, System.currentTimeMillis(), 0, false);
    }

    public static void l(Context context, String str, String str2, String str3, String str4, long j2, int i2) {
        m(context, str, str2, str3, str4, f(j2), i2, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0090  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m(android.content.Context r13, java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, long r18, int r20, boolean r21) {
        /*
            r0 = r13
            r2 = r14
            if (r0 == 0) goto Lba
            if (r2 == 0) goto Lba
            boolean r1 = r14.isEmpty()
            if (r1 == 0) goto Le
            goto Lba
        Le:
            java.lang.String r1 = "recent_chats"
            r9 = 0
            android.content.SharedPreferences r10 = r13.getSharedPreferences(r1, r9)
            java.util.List r11 = d(r13)
            java.util.Iterator r0 = r11.iterator()
        L1d:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L39
            java.lang.Object r1 = r0.next()
            j0.r$b r1 = (j0.r.b) r1
            java.lang.String r3 = r1.f5604b
            if (r3 == 0) goto L2e
            goto L30
        L2e:
            java.lang.String r3 = r1.f5603a
        L30:
            if (r3 == 0) goto L1d
            boolean r3 = r3.equals(r14)
            if (r3 == 0) goto L1d
            goto L3a
        L39:
            r1 = 0
        L3a:
            if (r1 == 0) goto L3f
            r11.remove(r1)
        L3f:
            if (r1 == 0) goto L44
            int r0 = r1.f5609g
            goto L45
        L44:
            r0 = 0
        L45:
            if (r1 == 0) goto L63
            if (r15 == 0) goto L52
            boolean r3 = r15.isEmpty()
            if (r3 == 0) goto L50
            goto L52
        L50:
            r3 = r15
            goto L54
        L52:
            java.lang.String r3 = r1.f5605c
        L54:
            if (r16 == 0) goto L60
            boolean r4 = r16.isEmpty()
            if (r4 == 0) goto L5d
            goto L60
        L5d:
            r4 = r16
            goto L65
        L60:
            java.lang.String r4 = r1.f5606d
            goto L65
        L63:
            r3 = r15
            goto L5d
        L65:
            if (r3 == 0) goto L6d
            boolean r5 = r3.isEmpty()
            if (r5 == 0) goto L6e
        L6d:
            r3 = r2
        L6e:
            if (r21 == 0) goto L72
            r8 = 0
            goto L77
        L72:
            if (r20 <= 0) goto L76
            int r0 = r0 + r20
        L76:
            r8 = r0
        L77:
            r5 = 0
            int r0 = (r18 > r5 ? 1 : (r18 == r5 ? 0 : -1))
            if (r0 <= 0) goto L80
            r6 = r18
            goto L8b
        L80:
            if (r1 == 0) goto L86
            long r5 = r1.f5608f
        L84:
            r6 = r5
            goto L8b
        L86:
            long r5 = java.lang.System.currentTimeMillis()
            goto L84
        L8b:
            if (r17 == 0) goto L90
            r5 = r17
            goto L99
        L90:
            if (r1 == 0) goto L96
            java.lang.String r0 = r1.f5607e
        L94:
            r5 = r0
            goto L99
        L96:
            java.lang.String r0 = ""
            goto L94
        L99:
            j0.r$b r12 = new j0.r$b
            r0 = r12
            r1 = r14
            r2 = r14
            r0.<init>(r1, r2, r3, r4, r5, r6, r8)
            r11.add(r9, r12)
            z.e r0 = new z.e
            r0.<init>()
            java.lang.String r0 = r0.q(r11)
            android.content.SharedPreferences$Editor r1 = r10.edit()
            java.lang.String r2 = "list"
            android.content.SharedPreferences$Editor r0 = r1.putString(r2, r0)
            r0.apply()
        Lba:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: j0.r.m(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, long, int, boolean):void");
    }

    public static void n(Context context, String str, String str2, String str3, String str4, long j2) {
        m(context, str, str2, str3, str4, f(j2), 0, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void o(android.content.Context r13, java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, long r18, int r20) {
        /*
            r0 = r13
            r2 = r14
            if (r0 == 0) goto Lb5
            if (r2 == 0) goto Lb5
            boolean r1 = r14.isEmpty()
            if (r1 == 0) goto Le
            goto Lb5
        Le:
            java.lang.String r1 = "recent_chats"
            r9 = 0
            android.content.SharedPreferences r10 = r13.getSharedPreferences(r1, r9)
            java.util.List r11 = d(r13)
            java.util.Iterator r0 = r11.iterator()
        L1d:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L39
            java.lang.Object r1 = r0.next()
            j0.r$b r1 = (j0.r.b) r1
            java.lang.String r3 = r1.f5604b
            if (r3 == 0) goto L2e
            goto L30
        L2e:
            java.lang.String r3 = r1.f5603a
        L30:
            if (r3 == 0) goto L1d
            boolean r3 = r3.equals(r14)
            if (r3 == 0) goto L1d
            goto L3a
        L39:
            r1 = 0
        L3a:
            if (r1 == 0) goto L3f
            r11.remove(r1)
        L3f:
            if (r1 == 0) goto L5e
            if (r15 == 0) goto L4c
            boolean r0 = r15.isEmpty()
            if (r0 == 0) goto L4a
            goto L4c
        L4a:
            r0 = r15
            goto L4e
        L4c:
            java.lang.String r0 = r1.f5605c
        L4e:
            if (r16 == 0) goto L5a
            boolean r3 = r16.isEmpty()
            if (r3 == 0) goto L57
            goto L5a
        L57:
            r4 = r16
            goto L60
        L5a:
            java.lang.String r3 = r1.f5606d
            r4 = r3
            goto L60
        L5e:
            r0 = r15
            goto L57
        L60:
            if (r0 == 0) goto L6b
            boolean r3 = r0.isEmpty()
            if (r3 == 0) goto L69
            goto L6b
        L69:
            r3 = r0
            goto L6c
        L6b:
            r3 = r2
        L6c:
            r5 = 0
            int r0 = (r18 > r5 ? 1 : (r18 == r5 ? 0 : -1))
            if (r0 <= 0) goto L75
            r6 = r18
            goto L80
        L75:
            if (r1 == 0) goto L7b
            long r5 = r1.f5608f
        L79:
            r6 = r5
            goto L80
        L7b:
            long r5 = java.lang.System.currentTimeMillis()
            goto L79
        L80:
            if (r17 == 0) goto L85
            r5 = r17
            goto L8e
        L85:
            if (r1 == 0) goto L8b
            java.lang.String r0 = r1.f5607e
        L89:
            r5 = r0
            goto L8e
        L8b:
            java.lang.String r0 = ""
            goto L89
        L8e:
            if (r20 >= 0) goto L92
            r8 = 0
            goto L94
        L92:
            r8 = r20
        L94:
            j0.r$b r12 = new j0.r$b
            r0 = r12
            r1 = r14
            r2 = r14
            r0.<init>(r1, r2, r3, r4, r5, r6, r8)
            r11.add(r9, r12)
            z.e r0 = new z.e
            r0.<init>()
            java.lang.String r0 = r0.q(r11)
            android.content.SharedPreferences$Editor r1 = r10.edit()
            java.lang.String r2 = "list"
            android.content.SharedPreferences$Editor r0 = r1.putString(r2, r0)
            r0.apply()
        Lb5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: j0.r.o(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, long, int):void");
    }
}
