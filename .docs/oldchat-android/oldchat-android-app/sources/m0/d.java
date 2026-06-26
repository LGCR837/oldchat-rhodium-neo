package m0;

import j0.w;
import java.util.ArrayList;
import java.util.List;
import l0.C0408x;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {
    public static List a(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            w wVar = new w();
            wVar.f5645a = jSONObject.optString("id", jSONObject.optString("user_id", ""));
            wVar.f5646b = jSONObject.optString("uid", "");
            wVar.f5647c = jSONObject.optString("username", "");
            wVar.f5648d = jSONObject.optString("display_name");
            wVar.f5649e = jSONObject.optString("remark_name", "");
            wVar.f5650f = jSONObject.optString("user_title");
            wVar.f5651g = jSONObject.optString("avatar_url");
            wVar.f5653i = jSONObject.optLong("friend_added_at", 0L);
            String str = wVar.f5646b;
            if (str != null && !str.isEmpty()) {
                arrayList.add(wVar);
            }
        }
        return arrayList;
    }

    public static List b(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            j0.e eVar = new j0.e();
            eVar.f5504a = jSONObject.optString("group_id");
            eVar.f5505b = jSONObject.optString("name");
            eVar.f5506c = jSONObject.optString("avatar_url");
            eVar.f5507d = jSONObject.optBoolean("join_approval", false);
            eVar.f5508e = jSONObject.optBoolean("global_mute", false);
            eVar.f5509f = jSONObject.optInt("role", 0);
            eVar.f5510g = jSONObject.optString("announcement", "");
            eVar.f5511h = jSONObject.optInt("announcement_mode", 0);
            eVar.f5512i = jSONObject.optLong("announcement_updated_at", 0L);
            eVar.f5513j = jSONObject.optLong("announcement_read_at", 0L);
            eVar.f5514k = jSONObject.optInt("member_count", 0);
            arrayList.add(eVar);
        }
        return arrayList;
    }

    public static List c(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            if (jSONObject.getInt("status") == 0) {
                String strOptString = jSONObject.optString("id", "");
                if (!strOptString.isEmpty()) {
                    String strOptString2 = jSONObject.optString("from_uid", "");
                    String strOptString3 = jSONObject.optString("from_username", "");
                    String strOptString4 = jSONObject.optString("from_display_name", "");
                    String strOptString5 = jSONObject.optString("from_title", "");
                    String strOptString6 = jSONObject.optString("avatar_url", "");
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("from_user");
                    if (jSONObjectOptJSONObject != null) {
                        if (strOptString2.isEmpty()) {
                            strOptString2 = jSONObjectOptJSONObject.optString("uid", "");
                        }
                        if (strOptString3.isEmpty()) {
                            strOptString3 = jSONObjectOptJSONObject.optString("username", "");
                        }
                        if (strOptString4.isEmpty()) {
                            strOptString4 = jSONObjectOptJSONObject.optString("display_name", "");
                        }
                        if (strOptString5.isEmpty()) {
                            strOptString5 = jSONObjectOptJSONObject.optString("user_title", "");
                        }
                        if (strOptString6.isEmpty()) {
                            strOptString6 = jSONObjectOptJSONObject.optString("avatar_url", "");
                        }
                    }
                    arrayList.add(new C0408x.b(strOptString, strOptString2, !strOptString4.isEmpty() ? strOptString4 : !strOptString3.isEmpty() ? strOptString3 : strOptString2, strOptString5, strOptString6));
                }
            }
        }
        return arrayList;
    }
}
