package o0;

import org.json.JSONObject;
import org.spongycastle.i18n.MessageBundle;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: loaded from: classes.dex */
public class S {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f7029a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f7030b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f7031c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f7032d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f7033e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f7034f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f7035g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f7036h;

    public static S a(String str) {
        S s2 = new S();
        if (str == null) {
            s2.f7030b = "";
            return s2;
        }
        String strTrim = str.trim();
        if (strTrim.startsWith("{") && strTrim.endsWith("}")) {
            try {
                JSONObject jSONObject = new JSONObject(strTrim);
                s2.f7029a = jSONObject.optString("packet_id", "");
                String strOptString = jSONObject.optString(TextBundle.TEXT_ENTRY, "");
                if (strOptString == null || strOptString.isEmpty()) {
                    strOptString = jSONObject.optString(MessageBundle.TITLE_ENTRY, "");
                }
                if (strOptString == null) {
                    strOptString = "";
                }
                s2.f7030b = strOptString;
                s2.f7031c = jSONObject.optInt("total_amount", 0);
                s2.f7032d = jSONObject.optInt("total_count", 0);
                s2.f7033e = jSONObject.optString("cover_url", "");
                s2.f7034f = jSONObject.optString("status", "");
                s2.f7036h = jSONObject.has("remaining_count");
                s2.f7035g = jSONObject.optInt("remaining_count", 0);
                return s2;
            } catch (Exception unused) {
            }
        }
        s2.f7030b = str;
        s2.f7033e = "";
        return s2;
    }

    public boolean b() {
        if ("done".equalsIgnoreCase(this.f7034f)) {
            return true;
        }
        return this.f7036h && this.f7035g <= 0;
    }
}
