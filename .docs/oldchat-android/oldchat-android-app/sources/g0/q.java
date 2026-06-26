package g0;

import android.content.Context;
import j0.r;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import o0.B;
import o0.C;
import org.json.JSONArray;
import org.json.JSONObject;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: loaded from: classes.dex */
public class q {

    public interface a {
        void a(l lVar);
    }

    public interface b {
        void a(n nVar);
    }

    public final String a(Context context) {
        return context == null ? "" : context.getSharedPreferences("auth", 0).getString("my_uid", "");
    }

    public void b(Context context, String str, a aVar) {
        if (str == null || aVar == null) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("messages");
            ArrayList arrayList = new ArrayList();
            HashMap map = new HashMap();
            HashMap map2 = new HashMap();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                l lVar = new l();
                lVar.f5408a = jSONObject.optString("id");
                lVar.f5409b = jSONObject.optString("thread_id");
                lVar.f5410c = jSONObject.optString("from_uid");
                lVar.f5411d = jSONObject.optString("peer_uid");
                lVar.f5412e = jSONObject.optString("body");
                lVar.f5413f = jSONObject.optString("msg_type", TextBundle.TEXT_ENTRY);
                lVar.f5414g = jSONObject.optString("media_url");
                lVar.f5415h = jSONObject.optString("thumb_url");
                lVar.f5416i = jSONObject.optInt("duration_ms", 0);
                lVar.f5417j = jSONObject.optLong("created_at");
                B.c(lVar);
                arrayList.add(lVar);
                String str2 = lVar.f5411d;
                if (str2 != null && str2.length() > 0) {
                    l lVar2 = (l) map.get(str2);
                    if (lVar2 == null || lVar.f5417j > lVar2.f5417j) {
                        map.put(str2, lVar);
                    }
                    map2.put(str2, Integer.valueOf((map2.containsKey(str2) ? ((Integer) map2.get(str2)).intValue() : 0) + 1));
                }
            }
            for (String str3 : map.keySet()) {
                l lVar3 = (l) map.get(str3);
                r.i(context, str3, null, null, k.a(lVar3), lVar3.f5417j, map2.containsKey(str3) ? ((Integer) map2.get(str3)).intValue() : 1);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                aVar.a((l) it.next());
            }
        } catch (Exception unused) {
        }
    }

    public void c(Context context, String str, b bVar) {
        String str2;
        if (str == null || bVar == null) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("messages");
            ArrayList arrayList = new ArrayList();
            HashMap map = new HashMap();
            HashMap map2 = new HashMap();
            HashMap map3 = new HashMap();
            String strA = a(context);
            int i2 = 0;
            boolean z2 = (strA == null || strA.isEmpty()) ? false : true;
            int i3 = 0;
            while (i3 < jSONArray.length()) {
                JSONObject jSONObject = jSONArray.getJSONObject(i3);
                n nVar = new n();
                nVar.f5421a = jSONObject.optString("id");
                nVar.f5422b = jSONObject.optString("group_id");
                nVar.f5423c = jSONObject.optString("from_uid");
                nVar.f5424d = jSONObject.optString("body");
                nVar.f5425e = jSONObject.optString("msg_type", TextBundle.TEXT_ENTRY);
                nVar.f5426f = jSONObject.optString("media_url");
                nVar.f5427g = jSONObject.optString("thumb_url");
                nVar.f5428h = jSONObject.optInt("duration_ms", i2);
                nVar.f5429i = jSONObject.optLong("created_at");
                B.e(nVar);
                arrayList.add(nVar);
                String str3 = nVar.f5422b;
                if (str3 != null && str3.length() > 0) {
                    n nVar2 = (n) map.get(str3);
                    if (nVar2 == null || nVar.f5429i > nVar2.f5429i) {
                        map.put(str3, nVar);
                    }
                    map2.put(str3, Integer.valueOf((map2.containsKey(str3) ? ((Integer) map2.get(str3)).intValue() : 0) + 1));
                    if (z2) {
                        Boolean bool = Boolean.TRUE;
                        if (!bool.equals(map3.get(str3)) && ((str2 = nVar.f5423c) == null || !str2.equals(strA))) {
                            if (C.b(nVar.f5424d).d(strA)) {
                                map3.put(str3, bool);
                            } else if (!map3.containsKey(str3)) {
                                map3.put(str3, Boolean.FALSE);
                            }
                        }
                    }
                }
                i3++;
                i2 = 0;
            }
            for (String str4 : map.keySet()) {
                n nVar3 = (n) map.get(str4);
                j0.j.h(context, str4, null, null, k.b(nVar3), nVar3.f5429i, map2.containsKey(str4) ? ((Integer) map2.get(str4)).intValue() : 1);
                if (z2) {
                    j0.j.g(context, str4, Boolean.TRUE.equals(map3.get(str4)));
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                bVar.a((n) it.next());
            }
        } catch (Exception unused) {
        }
    }
}
