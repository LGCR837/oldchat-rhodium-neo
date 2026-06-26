package o0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
public abstract class E {
    public static String a(List list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        if (list.size() == 1) {
            return (String) list.get(0);
        }
        JSONArray jSONArray = new JSONArray();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && !str.trim().isEmpty()) {
                jSONArray.put(str.trim());
            }
        }
        return jSONArray.toString();
    }

    public static List b(String str) {
        if (str == null) {
            return Collections.emptyList();
        }
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return Collections.emptyList();
        }
        if (strTrim.startsWith("[") && strTrim.endsWith("]")) {
            try {
                JSONArray jSONArray = new JSONArray(strTrim);
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    String strTrim2 = jSONArray.optString(i2, "").trim();
                    if (!strTrim2.isEmpty()) {
                        arrayList.add(strTrim2);
                    }
                }
                return arrayList;
            } catch (Exception unused) {
            }
        }
        if (strTrim.contains(",")) {
            String[] strArrSplit = strTrim.split(",");
            ArrayList arrayList2 = new ArrayList();
            for (String str2 : strArrSplit) {
                if (str2 != null) {
                    String strTrim3 = str2.trim();
                    if (!strTrim3.isEmpty()) {
                        arrayList2.add(strTrim3);
                    }
                }
            }
            if (!arrayList2.isEmpty()) {
                return arrayList2;
            }
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(strTrim);
        return arrayList3;
    }
}
