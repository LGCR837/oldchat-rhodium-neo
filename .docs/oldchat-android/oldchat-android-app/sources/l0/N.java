package l0;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class N {

    public class a implements Comparator {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(j0.i iVar, j0.i iVar2) {
            return N.b(iVar, iVar2);
        }
    }

    public static int b(j0.i iVar, j0.i iVar2) {
        if (iVar == iVar2) {
            return 0;
        }
        if (iVar == null) {
            return -1;
        }
        if (iVar2 == null) {
            return 1;
        }
        long j2 = iVar.f5537i;
        long j3 = iVar2.f5537i;
        if (j2 < j3) {
            return -1;
        }
        if (j2 > j3) {
            return 1;
        }
        int iCompareTo = h(iVar.f5529a).compareTo(h(iVar2.f5529a));
        return iCompareTo != 0 ? iCompareTo : h(iVar.f5531c).compareTo(h(iVar2.f5531c));
    }

    public static int c(Context context, String str, List list, HashSet hashSet) {
        String str2;
        int i2 = 0;
        if (list != null && !list.isEmpty()) {
            HashSet hashSet2 = new HashSet(hashSet);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                j0.i iVar = (j0.i) it.next();
                String str3 = iVar.f5529a;
                if (str3 != null && !hashSet2.contains(str3) && ((str2 = iVar.f5531c) == null || !o0.F.c(context, str2, str))) {
                    i2++;
                }
            }
        }
        return i2;
    }

    public static M d(List list, HashSet hashSet, List list2, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            j0.i iVar = (j0.i) it.next();
            String str = iVar.f5529a;
            if (str == null || !hashSet.contains(str)) {
                arrayList.add(iVar);
            }
        }
        if (!arrayList.isEmpty()) {
            list.addAll(0, arrayList);
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                String str2 = ((j0.i) it2.next()).f5529a;
                if (str2 != null) {
                    hashSet.add(str2);
                }
            }
        }
        return new M(i2 + list2.size(), list2.size() >= i3 && !arrayList.isEmpty(), false);
    }

    public static M e(List list, HashSet hashSet, List list2, int i2, boolean z2) {
        if (!list.isEmpty()) {
            Iterator it = list2.iterator();
            while (true) {
                if (it.hasNext()) {
                    String str = ((j0.i) it.next()).f5529a;
                    if (str != null && hashSet.contains(str)) {
                        break;
                    }
                } else if (list2.size() >= i2) {
                    list.clear();
                    hashSet.clear();
                    list.addAll(list2);
                    Iterator it2 = list2.iterator();
                    while (it2.hasNext()) {
                        String str2 = ((j0.i) it2.next()).f5529a;
                        if (str2 != null) {
                            hashSet.add(str2);
                        }
                    }
                    return new M(list.size(), true, true);
                }
            }
        }
        Iterator it3 = list2.iterator();
        while (it3.hasNext()) {
            j0.i iVar = (j0.i) it3.next();
            String str3 = iVar.f5529a;
            if (str3 != null && hashSet.contains(str3)) {
                Iterator it4 = list.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        break;
                    }
                    j0.i iVar2 = (j0.i) it4.next();
                    String str4 = iVar2.f5529a;
                    if (str4 != null && str4.equals(iVar.f5529a)) {
                        iVar2.f5531c = iVar.f5531c;
                        iVar2.f5532d = iVar.f5532d;
                        iVar2.f5533e = iVar.f5533e;
                        iVar2.f5535g = iVar.f5535g;
                        iVar2.f5536h = iVar.f5536h;
                        iVar2.f5537i = iVar.f5537i;
                        iVar2.f5538j = iVar.f5538j;
                        iVar2.f5534f = iVar.f5534f;
                        break;
                    }
                }
            } else {
                list.add(iVar);
                String str5 = iVar.f5529a;
                if (str5 != null) {
                    hashSet.add(str5);
                }
            }
        }
        Collections.sort(list, new a());
        return new M(list.size(), z2, false);
    }

    public static int f(String str, int i2) {
        try {
            int iOptInt = new JSONObject(str).optInt("effective_offset", i2);
            if (iOptInt < 0) {
                return 0;
            }
            return iOptInt;
        } catch (Exception unused) {
            if (i2 < 0) {
                return 0;
            }
            return i2;
        }
    }

    public static List g(String str) throws JSONException {
        JSONArray jSONArray = new JSONObject(str).getJSONArray("messages");
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            j0.i iVar = new j0.i();
            iVar.f5529a = jSONObject.optString("id");
            iVar.f5530b = jSONObject.optString("group_id");
            iVar.f5531c = jSONObject.optString("from_uid");
            iVar.f5532d = jSONObject.optString("body");
            iVar.f5533e = jSONObject.optString("msg_type", TextBundle.TEXT_ENTRY);
            iVar.f5534f = jSONObject.optString("media_url");
            iVar.f5535g = jSONObject.optString("thumb_url");
            iVar.f5536h = jSONObject.optInt("duration_ms", 0);
            iVar.f5537i = AbstractC0380g.g(jSONObject.optLong("created_at", 0L));
            iVar.f5538j = jSONObject.optInt("read_count", 0);
            o0.B.f(iVar);
            arrayList.add(iVar);
        }
        Collections.reverse(arrayList);
        return arrayList;
    }

    public static String h(String str) {
        return str == null ? "" : str;
    }

    public static int i(List list, HashSet hashSet, int i2) {
        int size;
        String str;
        if (list == null || hashSet == null || i2 <= 0 || (size = list.size()) <= i2) {
            return 0;
        }
        int i3 = size - i2;
        ArrayList<j0.i> arrayList = new ArrayList(list.subList(0, i3));
        list.subList(0, i3).clear();
        for (j0.i iVar : arrayList) {
            if (iVar != null && (str = iVar.f5529a) != null && str.length() > 0) {
                hashSet.remove(iVar.f5529a);
            }
        }
        return i3;
    }
}
