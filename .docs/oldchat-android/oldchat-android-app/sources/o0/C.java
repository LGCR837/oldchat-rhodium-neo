package o0;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.spongycastle.i18n.MessageBundle;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: loaded from: classes.dex */
public class C {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f6990a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f6991b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f6992c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d f6993d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List f6994e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a f6995f;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f6996a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public List f6997b = new ArrayList();
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f6998a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f6999b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f7000c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f7001d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f7002e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f7003f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public String f7004g;
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f7005a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f7006b;
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f7007a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f7008b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f7009c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f7010d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f7011e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f7012f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public String f7013g;
    }

    public static String a(JSONObject jSONObject, String str) {
        if (str == null) {
            str = "";
        }
        if (jSONObject == null) {
            return str;
        }
        String strE = e(jSONObject.optString(TextBundle.TEXT_ENTRY, ""));
        return strE.length() > 0 ? strE : str;
    }

    public static C b(String str) {
        C c2 = new C();
        c2.f6990a = str == null ? "" : str;
        if (str == null) {
            return c2;
        }
        String strTrim = str.trim();
        if (strTrim.startsWith("{") && strTrim.endsWith("}")) {
            try {
                JSONObject jSONObject = new JSONObject(strTrim);
                if (jSONObject.optInt("v", 0) != 2) {
                    c2.f6990a = a(jSONObject, c2.f6990a);
                    c2.f6991b = jSONObject.optString("media_kind", "");
                    c2.f6992c = e(jSONObject.optString("voice_text", ""));
                    return c2;
                }
                c2.f6990a = jSONObject.optString(TextBundle.TEXT_ENTRY, "");
                c2.f6991b = jSONObject.optString("media_kind", "");
                c2.f6992c = e(jSONObject.optString("voice_text", ""));
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("quote");
                if (jSONObjectOptJSONObject != null) {
                    d dVar = new d();
                    dVar.f7007a = jSONObjectOptJSONObject.optString("id", "");
                    dVar.f7008b = jSONObjectOptJSONObject.optString("from_uid", "");
                    dVar.f7009c = jSONObjectOptJSONObject.optString("from_name", "");
                    dVar.f7010d = jSONObjectOptJSONObject.optString("type", "");
                    dVar.f7011e = jSONObjectOptJSONObject.optString(TextBundle.TEXT_ENTRY, "");
                    dVar.f7012f = jSONObjectOptJSONObject.optString("media_kind", "");
                    dVar.f7013g = jSONObjectOptJSONObject.optString("thumb_url", "");
                    c2.f6993d = dVar;
                }
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("mentions");
                if (jSONArrayOptJSONArray != null) {
                    c2.f6994e.clear();
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                        JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(i2);
                        if (jSONObjectOptJSONObject2 != null) {
                            c cVar = new c();
                            cVar.f7005a = jSONObjectOptJSONObject2.optString("uid", "");
                            cVar.f7006b = jSONObjectOptJSONObject2.optString("name", "");
                            String str2 = cVar.f7005a;
                            if (str2 != null && !str2.isEmpty()) {
                                c2.f6994e.add(cVar);
                            }
                        }
                    }
                }
                JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("forward_v2");
                if (jSONObjectOptJSONObject3 != null) {
                    a aVar = new a();
                    aVar.f6996a = jSONObjectOptJSONObject3.optString(MessageBundle.TITLE_ENTRY, "");
                    JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject3.optJSONArray("items");
                    if (jSONArrayOptJSONArray2 != null) {
                        for (int i3 = 0; i3 < jSONArrayOptJSONArray2.length(); i3++) {
                            JSONObject jSONObjectOptJSONObject4 = jSONArrayOptJSONArray2.optJSONObject(i3);
                            if (jSONObjectOptJSONObject4 != null) {
                                b bVar = new b();
                                bVar.f6998a = jSONObjectOptJSONObject4.optString("sid", "");
                                bVar.f6999b = jSONObjectOptJSONObject4.optString("from_uid", "");
                                bVar.f7000c = jSONObjectOptJSONObject4.optString("from_name", "");
                                bVar.f7001d = jSONObjectOptJSONObject4.optString("from_avatar", "");
                                bVar.f7002e = jSONObjectOptJSONObject4.optString("type", "");
                                bVar.f7003f = jSONObjectOptJSONObject4.optString("media_kind", "");
                                bVar.f7004g = jSONObjectOptJSONObject4.optString(TextBundle.TEXT_ENTRY, "");
                                aVar.f6997b.add(bVar);
                            }
                        }
                    }
                    if (!aVar.f6997b.isEmpty()) {
                        c2.f6995f = aVar;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return c2;
    }

    public static String e(String str) {
        return str == null ? "" : str.trim();
    }

    public boolean c() {
        List list;
        String str;
        String str2;
        a aVar;
        List list2;
        return (this.f6993d == null && ((list = this.f6994e) == null || list.isEmpty()) && (((str = this.f6991b) == null || str.isEmpty()) && (((str2 = this.f6992c) == null || str2.isEmpty()) && ((aVar = this.f6995f) == null || (list2 = aVar.f6997b) == null || list2.isEmpty())))) ? false : true;
    }

    public boolean d(String str) {
        List<c> list;
        if (str != null && !str.isEmpty() && (list = this.f6994e) != null) {
            for (c cVar : list) {
                if (cVar != null && str.equals(cVar.f7005a)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String f() {
        List list;
        String str;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("v", 2);
            Object obj = this.f6990a;
            if (obj == null) {
                obj = "";
            }
            jSONObject.put(TextBundle.TEXT_ENTRY, obj);
            String str2 = this.f6991b;
            if (str2 != null && !str2.isEmpty()) {
                jSONObject.put("media_kind", this.f6991b);
            }
            String str3 = this.f6992c;
            if (str3 != null && !str3.isEmpty()) {
                jSONObject.put("voice_text", this.f6992c);
            }
            if (this.f6993d != null) {
                JSONObject jSONObject2 = new JSONObject();
                String str4 = this.f6993d.f7007a;
                if (str4 == null) {
                    str4 = "";
                }
                jSONObject2.put("id", str4);
                String str5 = this.f6993d.f7008b;
                if (str5 == null) {
                    str5 = "";
                }
                jSONObject2.put("from_uid", str5);
                String str6 = this.f6993d.f7009c;
                if (str6 == null) {
                    str6 = "";
                }
                jSONObject2.put("from_name", str6);
                String str7 = this.f6993d.f7010d;
                if (str7 == null) {
                    str7 = "";
                }
                jSONObject2.put("type", str7);
                String str8 = this.f6993d.f7011e;
                if (str8 == null) {
                    str8 = "";
                }
                jSONObject2.put(TextBundle.TEXT_ENTRY, str8);
                String str9 = this.f6993d.f7012f;
                if (str9 != null && !str9.isEmpty()) {
                    jSONObject2.put("media_kind", this.f6993d.f7012f);
                }
                String str10 = this.f6993d.f7013g;
                if (str10 != null && !str10.isEmpty()) {
                    jSONObject2.put("thumb_url", this.f6993d.f7013g);
                }
                jSONObject.put("quote", jSONObject2);
            }
            List list2 = this.f6994e;
            if (list2 != null && !list2.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (c cVar : this.f6994e) {
                    if (cVar != null && (str = cVar.f7005a) != null && !str.isEmpty()) {
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("uid", cVar.f7005a);
                        String str11 = cVar.f7006b;
                        if (str11 == null) {
                            str11 = "";
                        }
                        jSONObject3.put("name", str11);
                        jSONArray.put(jSONObject3);
                    }
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("mentions", jSONArray);
                }
            }
            a aVar = this.f6995f;
            if (aVar != null && (list = aVar.f6997b) != null && !list.isEmpty()) {
                JSONObject jSONObject4 = new JSONObject();
                Object obj2 = this.f6995f.f6996a;
                if (obj2 == null) {
                    obj2 = "";
                }
                jSONObject4.put(MessageBundle.TITLE_ENTRY, obj2);
                JSONArray jSONArray2 = new JSONArray();
                for (b bVar : this.f6995f.f6997b) {
                    if (bVar != null) {
                        JSONObject jSONObject5 = new JSONObject();
                        String str12 = bVar.f6998a;
                        if (str12 != null && !str12.isEmpty()) {
                            jSONObject5.put("sid", bVar.f6998a);
                        }
                        String str13 = bVar.f6999b;
                        if (str13 == null) {
                            str13 = "";
                        }
                        jSONObject5.put("from_uid", str13);
                        String str14 = bVar.f7000c;
                        if (str14 == null) {
                            str14 = "";
                        }
                        jSONObject5.put("from_name", str14);
                        String str15 = bVar.f7001d;
                        if (str15 != null && !str15.isEmpty()) {
                            jSONObject5.put("from_avatar", bVar.f7001d);
                        }
                        String str16 = bVar.f7002e;
                        if (str16 == null) {
                            str16 = "";
                        }
                        jSONObject5.put("type", str16);
                        String str17 = bVar.f7003f;
                        if (str17 != null && !str17.isEmpty()) {
                            jSONObject5.put("media_kind", bVar.f7003f);
                        }
                        String str18 = bVar.f7004g;
                        if (str18 == null) {
                            str18 = "";
                        }
                        jSONObject5.put(TextBundle.TEXT_ENTRY, str18);
                        jSONArray2.put(jSONObject5);
                    }
                }
                if (jSONArray2.length() > 0) {
                    jSONObject4.put("items", jSONArray2);
                    jSONObject.put("forward_v2", jSONObject4);
                }
            }
            return jSONObject.toString();
        } catch (Exception unused) {
            String str19 = this.f6990a;
            return str19 == null ? "" : str19;
        }
    }
}
