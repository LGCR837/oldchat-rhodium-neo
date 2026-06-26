package m0;

import android.content.Context;
import android.content.SharedPreferences;
import j0.j;
import j0.r;
import j0.w;
import j0.x;
import j0.y;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import l0.AbstractC0380g;

/* JADX INFO: loaded from: classes.dex */
public abstract class l {

    public class a implements Comparator {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(n nVar, n nVar2) {
            if (nVar == nVar2) {
                return 0;
            }
            if (nVar == null) {
                return 1;
            }
            if (nVar2 == null) {
                return -1;
            }
            boolean z2 = nVar.f6569b;
            if (z2 != nVar2.f6569b) {
                return z2 ? -1 : 1;
            }
            boolean z3 = nVar.f6571d;
            if (z3 != nVar2.f6571d) {
                return z3 ? -1 : 1;
            }
            long j2 = nVar.f6579l;
            long j3 = nVar2.f6579l;
            if (j2 < j3) {
                return 1;
            }
            if (j2 > j3) {
                return -1;
            }
            int i2 = (nVar.f6568a ? 1 : 0) - (nVar2.f6568a ? 1 : 0);
            if (i2 != 0) {
                return i2;
            }
            String str = nVar.f6573f;
            if (str == null) {
                str = "";
            }
            String str2 = nVar2.f6573f;
            if (str2 == null) {
                str2 = "";
            }
            int iCompareTo = str.compareTo(str2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            String str3 = nVar.f6574g;
            if (str3 == null) {
                str3 = "";
            }
            String str4 = nVar2.f6574g;
            return str3.compareTo(str4 != null ? str4 : "");
        }
    }

    public static List a(Context context, int i2) {
        String str;
        String str2;
        String strD;
        int i3;
        String str3;
        ArrayList arrayList;
        n nVar;
        n nVar2;
        String str4;
        ArrayList arrayList2 = new ArrayList();
        if (context == null) {
            return arrayList2;
        }
        n nVar3 = new n();
        int i4 = 1;
        nVar3.f6569b = true;
        nVar3.f6573f = "SYSTEM";
        nVar3.f6574g = "系统通知";
        nVar3.f6576i = "官方公告和通知";
        nVar3.f6579l = System.currentTimeMillis();
        nVar3.f6580m = i2;
        HashMap map = new HashMap();
        List<w> listB = j0.c.b(context);
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (w wVar : listB) {
            if (wVar != null) {
                String str5 = wVar.f5646b;
                if (str5 != null && !str5.isEmpty()) {
                    map2.put(wVar.f5646b, wVar);
                    hashSet.add(wVar.f5646b);
                }
                String str6 = wVar.f5645a;
                if (str6 != null && !str6.isEmpty()) {
                    hashSet2.add(wVar.f5645a);
                    String str7 = wVar.f5646b;
                    if (str7 != null && !str7.isEmpty()) {
                        map3.put(wVar.f5645a, wVar.f5646b);
                    }
                }
                String str8 = wVar.f5646b;
                if (str8 != null && !str8.isEmpty()) {
                    List listE = j0.l.e(context, wVar.f5646b);
                    if (!listE.isEmpty()) {
                        j0.k kVar = (j0.k) listE.get(listE.size() - i4);
                        n nVar4 = new n();
                        nVar4.f6568a = false;
                        nVar4.f6573f = wVar.f5646b;
                        nVar4.f6574g = j0.d.b(wVar);
                        nVar4.f6575h = wVar.f5650f;
                        nVar4.f6576i = AbstractC0380g.d(kVar);
                        nVar4.f6578k = wVar.f5651g;
                        nVar4.f6579l = AbstractC0380g.b(kVar.f5558i);
                        nVar4.f6580m = 0;
                        map = map;
                        map.put("u:" + wVar.f5646b, nVar4);
                        i4 = 1;
                    }
                }
            }
        }
        for (r.b bVar : r.d(context)) {
            String str9 = bVar.f5604b;
            String str10 = (str9 == null || str9.isEmpty()) ? bVar.f5603a : bVar.f5604b;
            if (str10 != null && !str10.isEmpty() && c(str10, hashSet, hashSet2)) {
                String str11 = "u:" + str10;
                n nVar5 = (n) map.get(str11);
                if (nVar5 == null) {
                    nVar2 = new n();
                    nVar2.f6568a = false;
                    nVar2.f6573f = str10;
                    arrayList = arrayList2;
                    nVar = nVar3;
                    nVar2.f6579l = 0L;
                    map.put(str11, nVar2);
                } else {
                    arrayList = arrayList2;
                    nVar = nVar3;
                    nVar2 = nVar5;
                }
                String str12 = nVar2.f6574g;
                if (str12 == null || str12.isEmpty()) {
                    String str13 = bVar.f5605c;
                    nVar2.f6574g = (str13 == null || str13.isEmpty()) ? str10 : bVar.f5605c;
                }
                String str14 = nVar2.f6575h;
                if (str14 == null || str14.isEmpty()) {
                    w wVar2 = (w) map2.get(str10);
                    if (wVar2 == null || (str4 = wVar2.f5650f) == null || str4.isEmpty()) {
                        nVar2.f6575h = y.a(context, str10);
                    } else {
                        nVar2.f6575h = wVar2.f5650f;
                    }
                }
                String str15 = nVar2.f6578k;
                if (str15 == null || str15.isEmpty()) {
                    nVar2.f6578k = bVar.f5606d;
                }
                long j2 = bVar.f5608f;
                ArrayList arrayList3 = arrayList;
                if (j2 > nVar2.f6579l) {
                    nVar2.f6579l = j2;
                    nVar2.f6576i = bVar.f5607e;
                } else {
                    String str16 = nVar2.f6576i;
                    if (str16 == null || str16.isEmpty()) {
                        nVar2.f6576i = bVar.f5607e;
                    }
                }
                int i5 = bVar.f5609g;
                if (i5 > nVar2.f6580m) {
                    nVar2.f6580m = i5;
                }
                arrayList2 = arrayList3;
                nVar3 = nVar;
            }
        }
        ArrayList arrayList4 = arrayList2;
        n nVar6 = nVar3;
        List<j0.e> listB2 = j0.f.b(context);
        HashMap map4 = new HashMap();
        for (j0.e eVar : listB2) {
            if (eVar != null && (str3 = eVar.f5504a) != null && !str3.isEmpty()) {
                map4.put(eVar.f5504a, eVar);
                List listG = j0.l.g(context, eVar.f5504a);
                if (!listG.isEmpty()) {
                    j0.i iVar = (j0.i) listG.get(listG.size() - 1);
                    n nVar7 = new n();
                    nVar7.f6568a = true;
                    nVar7.f6573f = eVar.f5504a;
                    String str17 = eVar.f5505b;
                    nVar7.f6574g = (str17 == null || str17.isEmpty()) ? eVar.f5504a : eVar.f5505b;
                    nVar7.f6576i = AbstractC0380g.e(iVar.f5533e, iVar.f5532d);
                    nVar7.f6578k = eVar.f5506c;
                    nVar7.f6579l = AbstractC0380g.b(iVar.f5537i);
                    nVar7.f6580m = 0;
                    nVar7.f6581n = eVar.f5509f;
                    nVar7.f6582o = false;
                    nVar7.f6583p = eVar.f5514k;
                    map.put("g:" + eVar.f5504a, nVar7);
                }
            }
        }
        Iterator it = j0.j.c(context).iterator();
        while (it.hasNext()) {
            j.b bVar2 = (j.b) it.next();
            String str18 = bVar2.f5541a;
            if (str18 != null && !str18.isEmpty() && map4.containsKey(bVar2.f5541a)) {
                String str19 = "g:" + bVar2.f5541a;
                n nVar8 = (n) map.get(str19);
                if (nVar8 == null) {
                    nVar8 = new n();
                    nVar8.f6568a = true;
                    nVar8.f6573f = bVar2.f5541a;
                    nVar8.f6579l = 0L;
                    map.put(str19, nVar8);
                }
                String str20 = nVar8.f6574g;
                if (str20 == null || str20.isEmpty()) {
                    String str21 = bVar2.f5542b;
                    nVar8.f6574g = (str21 == null || str21.isEmpty()) ? bVar2.f5541a : bVar2.f5542b;
                }
                String str22 = nVar8.f6578k;
                if (str22 == null || str22.isEmpty()) {
                    nVar8.f6578k = bVar2.f5543c;
                }
                if (nVar8.f6583p <= 0 && (i3 = bVar2.f5549i) > 0) {
                    nVar8.f6583p = i3;
                }
                long j3 = bVar2.f5545e;
                Iterator it2 = it;
                HashMap map5 = map4;
                if (j3 > nVar8.f6579l) {
                    nVar8.f6579l = j3;
                    nVar8.f6576i = bVar2.f5544d;
                } else {
                    String str23 = nVar8.f6576i;
                    if (str23 == null || str23.isEmpty()) {
                        nVar8.f6576i = bVar2.f5544d;
                    }
                }
                int i6 = bVar2.f5546f;
                if (i6 > nVar8.f6580m) {
                    nVar8.f6580m = i6;
                }
                nVar8.f6581n = bVar2.f5547g;
                nVar8.f6582o = bVar2.f5548h;
                map4 = map5;
                it = it2;
            }
        }
        Map map6 = map4;
        Iterator it3 = j0.l.d(context).iterator();
        while (it3.hasNext()) {
            String str24 = (String) it3.next();
            if (str24 != null && !str24.isEmpty() && c(str24, hashSet, hashSet2)) {
                String str25 = "u:" + str24;
                if (!map.containsKey(str25)) {
                    List listE2 = j0.l.e(context, str24);
                    if (!listE2.isEmpty()) {
                        j0.k kVar2 = (j0.k) listE2.get(listE2.size() - 1);
                        n nVar9 = new n();
                        nVar9.f6568a = false;
                        nVar9.f6573f = str24;
                        w wVar3 = (w) map2.get(str24);
                        Iterator it4 = it3;
                        if (wVar3 != null) {
                            nVar9.f6574g = j0.d.b(wVar3);
                            nVar9.f6578k = wVar3.f5651g;
                        }
                        String str26 = nVar9.f6574g;
                        if (str26 == null || str26.isEmpty()) {
                            String strB = x.b(context, str24);
                            if (strB != null && !strB.isEmpty()) {
                                str24 = strB;
                            }
                            nVar9.f6574g = str24;
                        }
                        nVar9.f6576i = AbstractC0380g.d(kVar2);
                        nVar9.f6579l = AbstractC0380g.b(kVar2.f5558i);
                        nVar9.f6580m = 0;
                        map.put(str25, nVar9);
                        it3 = it4;
                    }
                }
            }
        }
        for (String str27 : j0.l.f(context)) {
            if (str27 != null && !str27.isEmpty() && map6.containsKey(str27)) {
                String str28 = "g:" + str27;
                if (!map.containsKey(str28)) {
                    List listG2 = j0.l.g(context, str27);
                    if (!listG2.isEmpty()) {
                        j0.i iVar2 = (j0.i) listG2.get(listG2.size() - 1);
                        n nVar10 = new n();
                        nVar10.f6568a = true;
                        nVar10.f6573f = str27;
                        j0.e eVar2 = (j0.e) map6.get(str27);
                        if (eVar2 != null) {
                            nVar10.f6574g = eVar2.f5505b;
                            nVar10.f6578k = eVar2.f5506c;
                            nVar10.f6581n = eVar2.f5509f;
                            nVar10.f6583p = eVar2.f5514k;
                        }
                        String str29 = nVar10.f6574g;
                        if (str29 == null || str29.isEmpty()) {
                            nVar10.f6574g = str27;
                        }
                        nVar10.f6576i = AbstractC0380g.e(iVar2.f5533e, iVar2.f5532d);
                        nVar10.f6579l = AbstractC0380g.b(iVar2.f5537i);
                        nVar10.f6580m = 0;
                        map.put(str28, nVar10);
                    }
                }
            }
        }
        String strB2 = b(context);
        if (strB2 != null && !strB2.isEmpty()) {
            for (n nVar11 : map.values()) {
                if (nVar11 != null && (str2 = nVar11.f6573f) != null && !str2.isEmpty() && (strD = d(context, strB2, nVar11.f6568a, nVar11.f6573f)) != null && strD.trim().length() > 0) {
                    nVar11.f6577j = strD;
                }
            }
        }
        for (n nVar12 : map.values()) {
            if (nVar12 != null) {
                nVar12.f6571d = o.b(context, nVar12.f6568a, nVar12.f6573f);
                nVar12.f6572e = m.b(context, nVar12.f6568a, nVar12.f6573f);
                boolean zB = i0.g.b(context, nVar12.f6573f, nVar12.f6568a);
                nVar12.f6584q = zB;
                if (!nVar12.f6568a && !zB && (str = (String) map3.get(nVar12.f6573f)) != null && str.length() > 0) {
                    nVar12.f6584q = i0.g.b(context, str, false);
                }
            }
        }
        arrayList4.add(nVar6);
        arrayList4.addAll(map.values());
        Collections.sort(arrayList4, new a());
        return arrayList4;
    }

    public static String b(Context context) {
        return context == null ? "" : context.getSharedPreferences("auth", 0).getString("my_uid", "");
    }

    public static boolean c(String str, Set set, Set set2) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return set.contains(str) || set2.contains(str);
    }

    public static String d(Context context, String str, boolean z2, String str2) {
        if (context == null || str == null || str.isEmpty() || str2 == null || str2.isEmpty()) {
            return "";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("drafts", 0);
        StringBuilder sb = new StringBuilder();
        sb.append(z2 ? "group:" : "direct:");
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        return sharedPreferences.getString(sb.toString(), "");
    }
}
