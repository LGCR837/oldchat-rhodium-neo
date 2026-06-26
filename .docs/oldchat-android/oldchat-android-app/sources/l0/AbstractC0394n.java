package l0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: l0.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0394n {

    /* JADX INFO: renamed from: l0.n$a */
    public class a implements Comparator {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(j0.k kVar, j0.k kVar2) {
            return AbstractC0394n.b(kVar, kVar2);
        }
    }

    public static int b(j0.k kVar, j0.k kVar2) {
        if (kVar == kVar2) {
            return 0;
        }
        if (kVar == null) {
            return -1;
        }
        if (kVar2 == null) {
            return 1;
        }
        long j2 = kVar.f5558i;
        long j3 = kVar2.f5558i;
        if (j2 < j3) {
            return -1;
        }
        if (j2 > j3) {
            return 1;
        }
        int iCompareTo = e(kVar.f5550a).compareTo(e(kVar2.f5550a));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int iCompareTo2 = e(kVar.f5552c).compareTo(e(kVar2.f5552c));
        return iCompareTo2 != 0 ? iCompareTo2 : e(kVar.f5551b).compareTo(e(kVar2.f5551b));
    }

    public static C0392m c(List list, HashSet hashSet, List list2, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            j0.k kVar = (j0.k) it.next();
            String str = kVar.f5550a;
            if (str == null || !hashSet.contains(str)) {
                arrayList.add(kVar);
            }
        }
        if (!arrayList.isEmpty()) {
            list.addAll(0, arrayList);
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                String str2 = ((j0.k) it2.next()).f5550a;
                if (str2 != null) {
                    hashSet.add(str2);
                }
            }
        }
        return new C0392m(i2 + list2.size(), list2.size() >= i3 && !arrayList.isEmpty(), false);
    }

    public static C0392m d(List list, HashSet hashSet, List list2, int i2, boolean z2) {
        if (!list.isEmpty()) {
            Iterator it = list2.iterator();
            while (true) {
                if (it.hasNext()) {
                    String str = ((j0.k) it.next()).f5550a;
                    if (str != null && hashSet.contains(str)) {
                        break;
                    }
                } else if (list2.size() >= i2) {
                    list.clear();
                    hashSet.clear();
                    list.addAll(list2);
                    Iterator it2 = list2.iterator();
                    while (it2.hasNext()) {
                        String str2 = ((j0.k) it2.next()).f5550a;
                        if (str2 != null) {
                            hashSet.add(str2);
                        }
                    }
                    f(list, hashSet, 320);
                    return new C0392m(list.size(), true, true);
                }
            }
        }
        Iterator it3 = list2.iterator();
        while (it3.hasNext()) {
            j0.k kVar = (j0.k) it3.next();
            String str3 = kVar.f5550a;
            if (str3 != null && hashSet.contains(str3)) {
                Iterator it4 = list.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        break;
                    }
                    j0.k kVar2 = (j0.k) it4.next();
                    String str4 = kVar2.f5550a;
                    if (str4 != null && str4.equals(kVar.f5550a)) {
                        kVar2.f5552c = kVar.f5552c;
                        kVar2.f5553d = kVar.f5553d;
                        kVar2.f5554e = kVar.f5554e;
                        kVar2.f5559j = kVar.f5559j;
                        kVar2.f5556g = kVar.f5556g;
                        kVar2.f5557h = kVar.f5557h;
                        kVar2.f5555f = kVar.f5555f;
                        kVar2.f5558i = kVar.f5558i;
                        break;
                    }
                }
            } else {
                list.add(kVar);
                String str5 = kVar.f5550a;
                if (str5 != null) {
                    hashSet.add(str5);
                }
            }
        }
        Collections.sort(list, new a());
        f(list, hashSet, 320);
        return new C0392m(list.size(), z2, false);
    }

    public static String e(String str) {
        return str == null ? "" : str;
    }

    public static int f(List list, HashSet hashSet, int i2) {
        int size;
        String str;
        if (list == null || hashSet == null || i2 <= 0 || (size = list.size()) <= i2) {
            return 0;
        }
        int i3 = size - i2;
        ArrayList<j0.k> arrayList = new ArrayList(list.subList(0, i3));
        list.subList(0, i3).clear();
        for (j0.k kVar : arrayList) {
            if (kVar != null && (str = kVar.f5550a) != null && str.length() > 0) {
                hashSet.remove(kVar.f5550a);
            }
        }
        return i3;
    }
}
