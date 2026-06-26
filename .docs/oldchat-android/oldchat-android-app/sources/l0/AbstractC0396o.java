package l0;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: renamed from: l0.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0396o {
    public static List a(String str) throws JSONException {
        JSONArray jSONArray = new JSONObject(str).getJSONArray("messages");
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            j0.k kVar = new j0.k();
            kVar.f5550a = jSONObject.getString("id");
            kVar.f5552c = jSONObject.getString("from_uid");
            kVar.f5553d = jSONObject.getString("body");
            kVar.f5554e = jSONObject.optString("msg_type", TextBundle.TEXT_ENTRY);
            kVar.f5555f = jSONObject.optString("media_url");
            kVar.f5556g = jSONObject.optString("thumb_url");
            kVar.f5557h = jSONObject.optInt("duration_ms", 0);
            kVar.f5558i = AbstractC0380g.g(jSONObject.optLong("created_at", 0L));
            kVar.f5559j = AbstractC0380g.c(jSONObject);
            o0.B.d(kVar);
            arrayList.add(kVar);
        }
        return arrayList;
    }

    public static int b(String str, int i2) {
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
}
