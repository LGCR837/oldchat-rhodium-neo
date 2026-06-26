package com.im.oldchat.bili;

import android.os.AsyncTask;
import com.im.oldchat.bili.BiliModels;
import com.im.oldchat.bili.a;
import com.im.oldchat.bili.c;
import com.im.oldchat.bili.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class e extends com.im.oldchat.bili.d {

    public class a extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2241a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long f2242b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f2243c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ a.h f2244d;

        public a(long j2, String str, a.h hVar) {
            this.f2242b = j2;
            this.f2243c = str;
            this.f2244d = hVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c doInBackground(Void... voidArr) throws Throwable {
            long j2 = this.f2242b;
            if (j2 <= 0) {
                this.f2241a = "invalid mid";
                return null;
            }
            try {
                c cVarC = e.C(j2, this.f2243c);
                if (cVarC == null) {
                    this.f2241a = "fetch profile failed";
                    return null;
                }
                e.E(cVarC, this.f2243c);
                return cVarC;
            } catch (Exception e2) {
                this.f2241a = e2.getMessage();
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(c cVar) {
            if (cVar != null) {
                this.f2244d.b(cVar);
                return;
            }
            a.h hVar = this.f2244d;
            String str = this.f2241a;
            if (str == null) {
                str = "load profile failed";
            }
            hVar.a(str);
        }
    }

    public class b extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2245a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long f2246b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f2247c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f2248d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f2249e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ a.h f2250f;

        public b(long j2, int i2, int i3, String str, a.h hVar) {
            this.f2246b = j2;
            this.f2247c = i2;
            this.f2248d = i3;
            this.f2249e = str;
            this.f2250f = hVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public d doInBackground(Void... voidArr) {
            long j2 = this.f2246b;
            if (j2 <= 0) {
                this.f2245a = "invalid mid";
                return null;
            }
            try {
                return e.D(j2, this.f2247c, this.f2248d, this.f2249e);
            } catch (Exception e2) {
                this.f2245a = e2.getMessage();
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(d dVar) {
            if (dVar != null) {
                this.f2250f.b(dVar);
                return;
            }
            a.h hVar = this.f2250f;
            String str = this.f2245a;
            if (str == null) {
                str = "load videos failed";
            }
            hVar.a(str);
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f2251a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f2252b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f2253c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f2254d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f2255e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f2256f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f2257g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f2258h;
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List f2259a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2260b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2261c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2262d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f2263e;
    }

    public static c C(long j2, String str) throws Throwable {
        JSONObject jSONObjectOptJSONObject;
        HashMap map = new HashMap();
        map.put("mid", String.valueOf(j2));
        c.a aVarF = F("x/space/wbi/acc/info", "x/space/acc/info", map, str);
        if (aVarF != null && aVarF.b()) {
            try {
                String str2 = aVarF.f2214b;
                if (str2 == null) {
                    str2 = "{}";
                }
                JSONObject jSONObject = new JSONObject(str2);
                if (jSONObject.optInt("code", -1) != 0 || (jSONObjectOptJSONObject = jSONObject.optJSONObject("data")) == null) {
                    return null;
                }
                c cVar = new c();
                cVar.f2251a = jSONObjectOptJSONObject.optLong("mid", j2);
                cVar.f2252b = jSONObjectOptJSONObject.optString("name", "");
                cVar.f2253c = com.im.oldchat.bili.c.i(jSONObjectOptJSONObject.optString("face", ""));
                cVar.f2254d = jSONObjectOptJSONObject.optString("sign", "");
                cVar.f2258h = jSONObjectOptJSONObject.optInt("archive_count", 0);
                JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("level_info");
                cVar.f2255e = jSONObjectOptJSONObject2 != null ? jSONObjectOptJSONObject2.optInt("current_level", 0) : jSONObjectOptJSONObject.optInt("level", 0);
                return cVar;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static d D(long j2, int i2, int i3, String str) throws Throwable {
        JSONObject jSONObjectOptJSONObject;
        BiliModels.RecommendItem recommendItemJ;
        boolean z2 = true;
        int iMax = Math.max(1, i2);
        if (i3 <= 0) {
            i3 = 20;
        }
        HashMap map = new HashMap();
        map.put("mid", String.valueOf(j2));
        map.put("pn", String.valueOf(iMax));
        map.put("ps", String.valueOf(i3));
        map.put("order", "pubdate");
        map.put("tid", "0");
        map.put("keyword", "");
        map.put("platform", "web");
        c.a aVarF = F("x/space/wbi/arc/search", "x/space/arc/search", map, str);
        if (aVarF != null && aVarF.b()) {
            try {
                String str2 = aVarF.f2214b;
                if (str2 == null) {
                    str2 = "{}";
                }
                JSONObject jSONObject = new JSONObject(str2);
                if (jSONObject.optInt("code", -1) != 0 || (jSONObjectOptJSONObject = jSONObject.optJSONObject("data")) == null) {
                    return null;
                }
                d dVar = new d();
                dVar.f2260b = iMax;
                dVar.f2261c = i3;
                JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("page");
                if (jSONObjectOptJSONObject2 != null) {
                    dVar.f2262d = jSONObjectOptJSONObject2.optInt("count", 0);
                }
                JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject.optJSONObject("list");
                JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject3 != null ? jSONObjectOptJSONObject3.optJSONArray("vlist") : null;
                if (jSONArrayOptJSONArray != null) {
                    for (int i4 = 0; i4 < jSONArrayOptJSONArray.length(); i4++) {
                        JSONObject jSONObjectOptJSONObject4 = jSONArrayOptJSONArray.optJSONObject(i4);
                        if (jSONObjectOptJSONObject4 != null && (recommendItemJ = J(j2, jSONObjectOptJSONObject4)) != null) {
                            dVar.f2259a.add(recommendItemJ);
                        }
                    }
                }
                if (dVar.f2262d <= 0) {
                    dVar.f2262d = dVar.f2259a.size();
                }
                if (dVar.f2260b * dVar.f2261c >= dVar.f2262d) {
                    z2 = false;
                }
                dVar.f2263e = z2;
                return dVar;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static void E(c cVar, String str) throws Throwable {
        JSONObject jSONObjectOptJSONObject;
        if (cVar == null || cVar.f2251a <= 0) {
            return;
        }
        HashMap map = new HashMap();
        map.put("vmid", String.valueOf(cVar.f2251a));
        c.a aVarG = com.im.oldchat.bili.c.g("https://api.bilibili.com/x/relation/stat", map, str);
        if (aVarG.b()) {
            try {
                String str2 = aVarG.f2214b;
                if (str2 == null) {
                    str2 = "{}";
                }
                JSONObject jSONObject = new JSONObject(str2);
                if (jSONObject.optInt("code", -1) == 0 && (jSONObjectOptJSONObject = jSONObject.optJSONObject("data")) != null) {
                    cVar.f2256f = jSONObjectOptJSONObject.optInt("following", 0);
                    cVar.f2257g = jSONObjectOptJSONObject.optInt("follower", 0);
                }
            } catch (Exception unused) {
            }
        }
    }

    public static c.a F(String str, String str2, Map map, String str3) throws Throwable {
        HashMap map2;
        f.a aVarE;
        String str4;
        if (com.im.oldchat.bili.d.o(str3) && (aVarE = f.e((map2 = new HashMap(map)), com.im.oldchat.bili.c.f2210b, com.im.oldchat.bili.c.f2211c)) != null && (str4 = aVarE.f2265a) != null && str4.length() > 0) {
            map2.put("w_rid", aVarE.f2265a);
            map2.put("wts", String.valueOf(aVarE.f2266b));
            c.a aVarG = com.im.oldchat.bili.c.g("https://api.bilibili.com/" + str, map2, str3);
            if (aVarG != null && aVarG.b()) {
                return aVarG;
            }
        }
        return com.im.oldchat.bili.c.g("https://api.bilibili.com/" + str2, map, str3);
    }

    public static String G(String str) {
        if (str == null) {
            return "";
        }
        String strTrim = str.trim();
        return strTrim.length() == 0 ? "" : strTrim;
    }

    public static void H(long j2, String str, a.h hVar) {
        com.im.oldchat.bili.c.e(new a(j2, str, hVar), new Void[0]);
    }

    public static void I(long j2, int i2, int i3, String str, a.h hVar) {
        com.im.oldchat.bili.c.e(new b(j2, i2, i3, str, hVar), new Void[0]);
    }

    public static BiliModels.RecommendItem J(long j2, JSONObject jSONObject) {
        String strOptString = jSONObject.optString("bvid", "");
        long jOptLong = jSONObject.optLong("aid", 0L);
        if ((strOptString == null || strOptString.length() == 0) && jOptLong <= 0) {
            return null;
        }
        BiliModels.RecommendItem recommendItem = new BiliModels.RecommendItem();
        recommendItem.cardType = "up_archive";
        recommendItem.cardGoto = "av";
        recommendItem.gotoType = "av";
        recommendItem.title = jSONObject.optString(MessageBundle.TITLE_ENTRY, "");
        recommendItem.cover = com.im.oldchat.bili.c.i(jSONObject.optString("pic", ""));
        recommendItem.duration = G(jSONObject.optString("length", ""));
        if (strOptString == null || strOptString.length() <= 0) {
            strOptString = String.valueOf(jOptLong);
        }
        recommendItem.param = strOptString;
        recommendItem.playCount = String.valueOf(jSONObject.optString("play", ""));
        recommendItem.danmakuCount = String.valueOf(jSONObject.optString("video_review", ""));
        BiliModels.RecommendArgs recommendArgs = new BiliModels.RecommendArgs();
        recommendArgs.aid = jOptLong;
        recommendArgs.mid = j2;
        recommendArgs.upId = j2;
        recommendArgs.upName = jSONObject.optString("author", "");
        recommendItem.args = recommendArgs;
        return recommendItem;
    }
}
