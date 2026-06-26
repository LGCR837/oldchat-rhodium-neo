package g0;

import android.content.Context;
import android.content.SharedPreferences;
import j0.r;
import o0.AbstractC0435c;
import o0.AbstractC0443i;
import o0.B;
import o0.C;
import o0.K;
import org.json.JSONObject;
import org.spongycastle.i18n.MessageBundle;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: loaded from: classes.dex */
public class i {

    public interface a {
        void a(m mVar);

        void b(l lVar);

        void c(String str, String str2, long j2);

        void d(o oVar);

        void e(p pVar);

        void f(n nVar);
    }

    public final String a(Context context) {
        return context.getSharedPreferences("auth", 0).getString("my_uid", "");
    }

    public void b(Context context, String str, a aVar) {
        if (aVar == null || str == null) {
            return;
        }
        try {
            String strI = AbstractC0443i.i(str);
            if (strI == null) {
                strI = str;
            }
            JSONObject jSONObject = new JSONObject(strI);
            String strOptString = jSONObject.optString("type");
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            if (jSONObjectOptJSONObject == null) {
                return;
            }
            String str2 = null;
            String lowerCase = "";
            if ("direct_message".equals(strOptString)) {
                l lVarE = e(jSONObjectOptJSONObject);
                B.c(lVarE);
                if (context != null) {
                    r.l(context, lVarE.f5411d, null, null, k.a(lVarE), lVarE.f5417j, 1);
                    String str3 = lVarE.f5413f;
                    if (str3 != null) {
                        lowerCase = str3.toLowerCase();
                    }
                    if ("image".equals(lowerCase)) {
                        String str4 = lVarE.f5415h;
                        str2 = (str4 == null || str4.isEmpty()) ? lVarE.f5414g : lVarE.f5415h;
                    }
                    if (!AbstractC0435c.f()) {
                        K.k(context, lVarE.f5411d, k.a(lVarE), str2);
                    }
                }
                aVar.b(lVarE);
                return;
            }
            if ("direct_read".equals(strOptString)) {
                aVar.c(jSONObjectOptJSONObject.optString("thread_id"), jSONObjectOptJSONObject.optString("reader_uid"), jSONObjectOptJSONObject.optLong("read_at"));
                return;
            }
            if ("direct_recall".equals(strOptString)) {
                aVar.a(f(jSONObjectOptJSONObject));
                return;
            }
            if ("group_message".equals(strOptString)) {
                n nVarG = g(jSONObjectOptJSONObject);
                B.e(nVarG);
                if (context != null) {
                    j0.j.j(context, nVarG.f5422b, null, null, k.b(nVarG), nVarG.f5429i, 1);
                    if (d(context, nVarG)) {
                        j0.j.g(context, nVarG.f5422b, true);
                    }
                    String str5 = nVarG.f5425e;
                    if (str5 != null) {
                        lowerCase = str5.toLowerCase();
                    }
                    if ("image".equals(lowerCase)) {
                        String str6 = nVarG.f5427g;
                        str2 = (str6 == null || str6.isEmpty()) ? nVarG.f5426f : nVarG.f5427g;
                    }
                    if (!AbstractC0435c.f()) {
                        K.l(context, nVarG.f5422b, nVarG.f5423c, k.b(nVarG), str2);
                    }
                }
                aVar.f(nVarG);
                return;
            }
            if ("group_recall".equals(strOptString)) {
                aVar.d(h(jSONObjectOptJSONObject));
                return;
            }
            if (!"system_notification".equals(strOptString)) {
                if ("typing".equals(strOptString)) {
                    aVar.e(i(jSONObjectOptJSONObject));
                }
            } else if (context != null) {
                String strOptString2 = jSONObjectOptJSONObject.optString("id", "");
                String strOptString3 = jSONObjectOptJSONObject.optString(MessageBundle.TITLE_ENTRY, "");
                String strOptString4 = jSONObjectOptJSONObject.optString("body", "");
                if (strOptString3 == null || strOptString3.length() == 0) {
                    strOptString3 = "系统通知";
                }
                if (strOptString4 != null) {
                    lowerCase = strOptString4;
                }
                c(context, strOptString2);
                if (AbstractC0435c.f()) {
                    return;
                }
                K.m(context, strOptString2, strOptString3, lowerCase);
            }
        } catch (Exception unused) {
        }
    }

    public final void c(Context context, String str) {
        if (context == null) {
            return;
        }
        if (str == null) {
            str = "";
        }
        if (str.length() <= 0 || !i0.f.c(context, str)) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("notification", 0);
            if (str.length() <= 0 || !str.equals(sharedPreferences.getString("last_notification_id", ""))) {
                int i2 = sharedPreferences.getInt("unread_count", 0);
                int i3 = i2 >= 0 ? i2 : 0;
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putInt("unread_count", i3 + 1);
                if (str.length() > 0) {
                    editorEdit.putString("last_notification_id", str);
                }
                editorEdit.apply();
            }
        }
    }

    public final boolean d(Context context, n nVar) {
        String strA;
        if (context == null || nVar == null || (strA = a(context)) == null || strA.isEmpty()) {
            return false;
        }
        String str = nVar.f5423c;
        if (str == null || !str.equals(strA)) {
            return C.b(nVar.f5424d).d(strA);
        }
        return false;
    }

    public final l e(JSONObject jSONObject) {
        l lVar = new l();
        lVar.f5408a = jSONObject.optString("id");
        lVar.f5409b = jSONObject.optString("thread_id");
        lVar.f5410c = jSONObject.optString("from_uid");
        lVar.f5412e = jSONObject.optString("body");
        lVar.f5413f = jSONObject.optString("msg_type", TextBundle.TEXT_ENTRY);
        lVar.f5414g = jSONObject.optString("media_url");
        lVar.f5415h = jSONObject.optString("thumb_url");
        lVar.f5416i = jSONObject.optInt("duration_ms", 0);
        lVar.f5417j = jSONObject.optLong("created_at");
        lVar.f5411d = lVar.f5410c;
        return lVar;
    }

    public final m f(JSONObject jSONObject) {
        m mVar = new m();
        mVar.f5418a = jSONObject.optString("message_id");
        mVar.f5419b = jSONObject.optString("thread_id");
        mVar.f5420c = jSONObject.optString("from_uid");
        return mVar;
    }

    public final n g(JSONObject jSONObject) {
        n nVar = new n();
        nVar.f5421a = jSONObject.optString("id");
        nVar.f5422b = jSONObject.optString("group_id");
        nVar.f5423c = jSONObject.optString("from_uid");
        nVar.f5424d = jSONObject.optString("body");
        nVar.f5425e = jSONObject.optString("msg_type", TextBundle.TEXT_ENTRY);
        nVar.f5426f = jSONObject.optString("media_url");
        nVar.f5427g = jSONObject.optString("thumb_url");
        nVar.f5428h = jSONObject.optInt("duration_ms", 0);
        nVar.f5429i = jSONObject.optLong("created_at");
        return nVar;
    }

    public final o h(JSONObject jSONObject) {
        o oVar = new o();
        oVar.f5430a = jSONObject.optString("message_id");
        oVar.f5431b = jSONObject.optString("group_id");
        oVar.f5432c = jSONObject.optString("from_uid");
        return oVar;
    }

    public final p i(JSONObject jSONObject) {
        p pVar = new p();
        pVar.f5433a = jSONObject.optString("chat_id");
        pVar.f5434b = jSONObject.optString("uid");
        pVar.f5435c = jSONObject.optBoolean("is_group", false);
        pVar.f5436d = jSONObject.optBoolean("is_typing", false);
        return pVar;
    }
}
