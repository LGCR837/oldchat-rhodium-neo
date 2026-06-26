package m0;

import java.util.ArrayList;
import java.util.List;
import o0.E;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {
    public static List a(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            j0.m mVar = new j0.m();
            mVar.f5567a = jSONObject.optString("id");
            mVar.f5568b = jSONObject.optString("from_uid");
            mVar.f5569c = jSONObject.optString("from_name");
            mVar.f5570d = jSONObject.optString("from_title");
            mVar.f5571e = jSONObject.optString("from_avatar");
            mVar.f5572f = jSONObject.optString("body");
            String strOptString = jSONObject.optString("image_url");
            mVar.f5573g = strOptString;
            mVar.f5578l = E.b(strOptString);
            mVar.f5574h = jSONObject.optLong("created_at");
            mVar.f5575i = jSONObject.optInt("likes", 0);
            mVar.f5576j = jSONObject.optInt("comments", 0);
            mVar.f5577k = jSONObject.optBoolean("liked", false);
            arrayList.add(mVar);
        }
        return arrayList;
    }
}
