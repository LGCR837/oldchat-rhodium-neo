package com.im.oldchat.bili;

import android.os.AsyncTask;
import com.im.oldchat.bili.BiliModels;
import com.im.oldchat.bili.a;
import com.im.oldchat.bili.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends com.im.oldchat.bili.c {

    public class a extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2190a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long f2191b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f2192c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ a.h f2193d;

        public a(long j2, String str, a.h hVar) {
            this.f2191b = j2;
            this.f2192c = str;
            this.f2193d = hVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public List doInBackground(Void... voidArr) throws Throwable {
            if (this.f2191b <= 0) {
                this.f2190a = "invalid aid";
                return new ArrayList();
            }
            HashMap map = new HashMap();
            map.put("aid", String.valueOf(this.f2191b));
            c.a aVarG = com.im.oldchat.bili.c.g("https://api.bilibili.com/x/web-interface/archive/related", map, this.f2192c);
            if (aVarG.b()) {
                return b.o(aVarG.f2214b);
            }
            this.f2190a = aVarG.a();
            return null;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(List list) {
            if (list != null) {
                this.f2193d.b(list);
                return;
            }
            a.h hVar = this.f2193d;
            String str = this.f2190a;
            if (str == null) {
                str = "获取相关推荐失败";
            }
            hVar.a(str);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.bili.b$b, reason: collision with other inner class name */
    public class AsyncTaskC0023b extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2194a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long f2195b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ long f2196c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f2197d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ boolean f2198e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ String f2199f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ a.h f2200g;

        public AsyncTaskC0023b(long j2, long j3, String str, boolean z2, String str2, a.h hVar) {
            this.f2195b = j2;
            this.f2196c = j3;
            this.f2197d = str;
            this.f2198e = z2;
            this.f2199f = str2;
            this.f2200g = hVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BiliModels.SimpleResult doInBackground(Void... voidArr) throws Throwable {
            if (this.f2195b <= 0 || this.f2196c <= 0) {
                this.f2194a = "invalid input";
                return null;
            }
            String strF = com.im.oldchat.bili.c.f(this.f2197d);
            if (strF == null || strF.length() == 0) {
                this.f2194a = "csrf missing";
                return null;
            }
            HashMap map = new HashMap();
            map.put("oid", String.valueOf(this.f2195b));
            map.put("type", "1");
            map.put("rpid", String.valueOf(this.f2196c));
            map.put("action", this.f2198e ? "1" : "0");
            String str = this.f2199f;
            if (str != null && str.length() > 0) {
                map.put("access_key", this.f2199f);
            }
            map.put("csrf", strF);
            map.put("csrf_token", strF);
            c.a aVarJ = com.im.oldchat.bili.c.j("https://api.bilibili.com/x/v2/reply/action", map, this.f2197d);
            if (!aVarJ.b()) {
                this.f2194a = aVarJ.a();
                return null;
            }
            try {
                return (BiliModels.SimpleResult) com.im.oldchat.bili.c.f2209a.i(aVarJ.f2214b, BiliModels.SimpleResult.class);
            } catch (Exception e2) {
                this.f2194a = e2.getMessage();
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(BiliModels.SimpleResult simpleResult) {
            if (simpleResult != null) {
                this.f2200g.b(simpleResult);
                return;
            }
            a.h hVar = this.f2200g;
            String str = this.f2194a;
            if (str == null) {
                str = "评论点赞失败";
            }
            hVar.a(str);
        }
    }

    public class c extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2201a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long f2202b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ long f2203c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f2204d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f2205e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ String f2206f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ String f2207g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ a.h f2208h;

        public c(long j2, long j3, int i2, int i3, String str, String str2, a.h hVar) {
            this.f2202b = j2;
            this.f2203c = j3;
            this.f2204d = i2;
            this.f2205e = i3;
            this.f2206f = str;
            this.f2207g = str2;
            this.f2208h = hVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BiliModels.CommentResult doInBackground(Void... voidArr) throws Throwable {
            if (this.f2202b <= 0 || this.f2203c <= 0) {
                this.f2201a = "invalid input";
                return null;
            }
            HashMap map = new HashMap();
            map.put("oid", String.valueOf(this.f2202b));
            map.put("type", "1");
            map.put("root", String.valueOf(this.f2203c));
            map.put("pn", String.valueOf(Math.max(1, this.f2204d)));
            int i2 = this.f2205e;
            if (i2 <= 0) {
                i2 = 20;
            }
            map.put("ps", String.valueOf(i2));
            String str = this.f2206f;
            if (str != null && str.length() > 0) {
                map.put("access_key", this.f2206f);
            }
            c.a aVarG = com.im.oldchat.bili.c.g("https://api.bilibili.com/x/v2/reply/reply", map, this.f2207g);
            if (!aVarG.b()) {
                this.f2201a = aVarG.a();
                return null;
            }
            try {
                return (BiliModels.CommentResult) com.im.oldchat.bili.c.f2209a.i(aVarG.f2214b, BiliModels.CommentResult.class);
            } catch (Exception e2) {
                this.f2201a = e2.getMessage();
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(BiliModels.CommentResult commentResult) {
            if (commentResult != null) {
                this.f2208h.b(commentResult);
                return;
            }
            a.h hVar = this.f2208h;
            String str = this.f2201a;
            if (str == null) {
                str = "获取评论回复失败";
            }
            hVar.a(str);
        }
    }

    public static String n(int i2) {
        if (i2 <= 0) {
            return "";
        }
        int i3 = i2 / 3600;
        int i4 = (i2 % 3600) / 60;
        int i5 = i2 % 60;
        return i3 > 0 ? String.format(Locale.US, "%02d:%02d:%02d", Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)) : String.format(Locale.US, "%02d:%02d", Integer.valueOf(i4), Integer.valueOf(i5));
    }

    public static List o(String str) {
        JSONArray jSONArrayOptJSONArray;
        ArrayList arrayList = new ArrayList();
        try {
            if (str == null) {
                str = "{}";
            }
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("code", -1) != 0 || (jSONArrayOptJSONArray = jSONObject.optJSONArray("data")) == null) {
                return arrayList;
            }
            for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i2);
                if (jSONObjectOptJSONObject != null) {
                    BiliModels.RecommendItem recommendItem = new BiliModels.RecommendItem();
                    recommendItem.cardType = "related";
                    recommendItem.cardGoto = "av";
                    recommendItem.gotoType = "av";
                    recommendItem.title = jSONObjectOptJSONObject.optString(MessageBundle.TITLE_ENTRY, "");
                    recommendItem.cover = com.im.oldchat.bili.c.i(jSONObjectOptJSONObject.optString("pic", ""));
                    String strOptString = jSONObjectOptJSONObject.optString("bvid", "");
                    long jOptLong = jSONObjectOptJSONObject.optLong("aid", 0L);
                    if (strOptString != null && strOptString.length() > 0) {
                        recommendItem.param = strOptString;
                    } else if (jOptLong > 0) {
                        recommendItem.param = String.valueOf(jOptLong);
                    }
                    recommendItem.duration = n(jSONObjectOptJSONObject.optInt("duration", 0));
                    JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("owner");
                    BiliModels.RecommendArgs recommendArgs = new BiliModels.RecommendArgs();
                    if (jSONObjectOptJSONObject2 != null) {
                        recommendArgs.upName = jSONObjectOptJSONObject2.optString("name", "");
                    }
                    recommendArgs.aid = jOptLong;
                    recommendItem.args = recommendArgs;
                    JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject.optJSONObject("stat");
                    if (jSONObjectOptJSONObject3 != null) {
                        int iOptInt = jSONObjectOptJSONObject3.optInt("view", 0);
                        int iOptInt2 = jSONObjectOptJSONObject3.optInt("danmaku", 0);
                        recommendItem.playCount = iOptInt > 0 ? String.valueOf(iOptInt) : "";
                        recommendItem.danmakuCount = iOptInt2 > 0 ? String.valueOf(iOptInt2) : "";
                    }
                    arrayList.add(recommendItem);
                }
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }

    public static void p(long j2, long j3, int i2, int i3, String str, String str2, a.h hVar) {
        com.im.oldchat.bili.c.e(new c(j2, j3, i2, i3, str, str2, hVar), new Void[0]);
    }

    public static void q(long j2, long j3, boolean z2, String str, String str2, a.h hVar) {
        com.im.oldchat.bili.c.e(new AsyncTaskC0023b(j2, j3, str2, z2, str, hVar), new Void[0]);
    }

    public static void r(long j2, String str, a.h hVar) {
        com.im.oldchat.bili.c.e(new a(j2, str, hVar), new Void[0]);
    }
}
