package com.im.oldchat.bili;

import android.os.AsyncTask;
import com.im.oldchat.bili.BiliModels;
import com.im.oldchat.bili.c;
import com.im.oldchat.bili.f;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends com.im.oldchat.bili.d {

    /* JADX INFO: renamed from: com.im.oldchat.bili.a$a, reason: collision with other inner class name */
    public class AsyncTaskC0022a extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2157a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ h f2158b;

        public AsyncTaskC0022a(h hVar) {
            this.f2158b = hVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BiliModels.QRAuthCodeResult doInBackground(Void... voidArr) throws Throwable {
            HashMap map = new HashMap();
            map.put("local_id", "0");
            map.put("ts", String.valueOf(System.currentTimeMillis() / 1000));
            map.put("sign", h0.d.b(map));
            c.a aVarJ = com.im.oldchat.bili.c.j("https://passport.bilibili.com/x/passport-tv-login/qrcode/auth_code", map, "");
            if (aVarJ.b()) {
                try {
                    return (BiliModels.QRAuthCodeResult) com.im.oldchat.bili.c.f2209a.i(aVarJ.f2214b, BiliModels.QRAuthCodeResult.class);
                } catch (Exception e2) {
                    this.f2157a = e2.getMessage();
                    com.im.oldchat.bili.c.h("QR auth_code parse", e2);
                    return null;
                }
            }
            this.f2157a = aVarJ.a();
            com.im.oldchat.bili.c.h("QR auth_code http=" + aVarJ.f2213a + " body=" + com.im.oldchat.bili.c.d(aVarJ.f2214b), null);
            return null;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(BiliModels.QRAuthCodeResult qRAuthCodeResult) {
            String str;
            BiliModels.QRAuthCodeResponse qRAuthCodeResponse;
            if (qRAuthCodeResult != null && qRAuthCodeResult.code == 0 && (qRAuthCodeResponse = qRAuthCodeResult.data) != null) {
                this.f2158b.b(qRAuthCodeResponse);
                return;
            }
            String str2 = this.f2157a;
            if (str2 == null && qRAuthCodeResult != null && (str = qRAuthCodeResult.message) != null) {
                str2 = str;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("QR auth_code failed: ");
            sb.append(str2 != null ? str2 : "unknown");
            com.im.oldchat.bili.c.h(sb.toString(), null);
            h hVar = this.f2158b;
            if (str2 == null) {
                str2 = "获取二维码失败";
            }
            hVar.a(str2);
        }
    }

    public class b extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2159a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f2160b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ h f2161c;

        public b(String str, h hVar) {
            this.f2160b = str;
            this.f2161c = hVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BiliModels.QRPollResult doInBackground(Void... voidArr) throws Throwable {
            HashMap map = new HashMap();
            String str = this.f2160b;
            if (str == null) {
                str = "";
            }
            map.put("auth_code", str);
            map.put("local_id", "0");
            map.put("ts", String.valueOf(System.currentTimeMillis() / 1000));
            map.put("sign", h0.d.b(map));
            c.a aVarJ = com.im.oldchat.bili.c.j("https://passport.bilibili.com/x/passport-tv-login/qrcode/poll", map, "");
            if (aVarJ.b()) {
                try {
                    return (BiliModels.QRPollResult) com.im.oldchat.bili.c.f2209a.i(aVarJ.f2214b, BiliModels.QRPollResult.class);
                } catch (Exception e2) {
                    this.f2159a = e2.getMessage();
                    com.im.oldchat.bili.c.h("QR poll parse", e2);
                    return null;
                }
            }
            this.f2159a = aVarJ.a();
            com.im.oldchat.bili.c.h("QR poll http=" + aVarJ.f2213a + " body=" + com.im.oldchat.bili.c.d(aVarJ.f2214b), null);
            return null;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(BiliModels.QRPollResult qRPollResult) {
            if (qRPollResult != null) {
                this.f2161c.b(qRPollResult);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("QR poll failed: ");
            String str = this.f2159a;
            if (str == null) {
                str = "unknown";
            }
            sb.append(str);
            com.im.oldchat.bili.c.h(sb.toString(), null);
            h hVar = this.f2161c;
            String str2 = this.f2159a;
            if (str2 == null) {
                str2 = "扫码状态查询失败";
            }
            hVar.a(str2);
        }
    }

    public class c extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2162a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f2163b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f2164c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ h f2165d;

        public c(String str, String str2, h hVar) {
            this.f2163b = str;
            this.f2164c = str2;
            this.f2165d = hVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BiliModels.RecommendResult doInBackground(Void... voidArr) throws Throwable {
            HashMap map = new HashMap();
            String str = this.f2163b;
            if (str != null && str.length() > 0) {
                map.put("access_key", this.f2163b);
                map.put("accessKey", this.f2163b);
            }
            map.put("mobi_app", "android");
            map.put("platform", "android");
            map.put("ts", String.valueOf(System.currentTimeMillis() / 1000));
            map.put("pull", "true");
            map.put("idx", String.valueOf(System.currentTimeMillis() / 1000));
            map.put("sign", h0.d.b(map));
            c.a aVarG = com.im.oldchat.bili.c.g("https://app.bilibili.com/x/v2/feed/index", map, this.f2164c);
            if (aVarG.b()) {
                try {
                    return (BiliModels.RecommendResult) com.im.oldchat.bili.c.f2209a.i(aVarG.f2214b, BiliModels.RecommendResult.class);
                } catch (Exception e2) {
                    this.f2162a = e2.getMessage();
                    com.im.oldchat.bili.c.h("Recommend parse", e2);
                    return null;
                }
            }
            this.f2162a = aVarG.a();
            com.im.oldchat.bili.c.h("Recommend http=" + aVarG.f2213a + " body=" + com.im.oldchat.bili.c.d(aVarG.f2214b), null);
            return null;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(BiliModels.RecommendResult recommendResult) {
            String str;
            BiliModels.RecommendData recommendData;
            if (recommendResult != null && recommendResult.code == 0 && (recommendData = recommendResult.data) != null) {
                this.f2165d.b(recommendData.items);
                return;
            }
            String str2 = this.f2162a;
            if (str2 == null && recommendResult != null && (str = recommendResult.message) != null) {
                str2 = str;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Recommend failed: ");
            sb.append(str2 != null ? str2 : "unknown");
            com.im.oldchat.bili.c.h(sb.toString(), null);
            h hVar = this.f2165d;
            if (str2 == null) {
                str2 = "获取推荐失败";
            }
            hVar.a(str2);
        }
    }

    public class d extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2166a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f2167b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f2168c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f2169d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ h f2170e;

        public d(String str, int i2, String str2, h hVar) {
            this.f2167b = str;
            this.f2168c = i2;
            this.f2169d = str2;
            this.f2170e = hVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BiliModels.SearchResult doInBackground(Void... voidArr) throws Throwable {
            f.a aVarE;
            String str;
            String str2 = this.f2167b;
            if (str2 == null || str2.trim().length() == 0) {
                this.f2166a = "keyword empty";
                return null;
            }
            HashMap map = new HashMap();
            map.put("keyword", this.f2167b);
            map.put("search_type", "video");
            map.put("page", String.valueOf(Math.max(1, this.f2168c)));
            map.put("page_size", "20");
            if (com.im.oldchat.bili.d.o(this.f2169d) && (aVarE = com.im.oldchat.bili.f.e(map, com.im.oldchat.bili.c.f2210b, com.im.oldchat.bili.c.f2211c)) != null && (str = aVarE.f2265a) != null && str.length() > 0) {
                map.put("w_rid", aVarE.f2265a);
                map.put("wts", String.valueOf(aVarE.f2266b));
            }
            c.a aVarG = com.im.oldchat.bili.c.g("https://api.bilibili.com/x/web-interface/wbi/search/type", map, this.f2169d);
            if (aVarG.b()) {
                try {
                    return (BiliModels.SearchResult) com.im.oldchat.bili.c.f2209a.i(aVarG.f2214b, BiliModels.SearchResult.class);
                } catch (Exception e2) {
                    this.f2166a = e2.getMessage();
                    com.im.oldchat.bili.c.h("Search parse", e2);
                    return null;
                }
            }
            this.f2166a = aVarG.a();
            com.im.oldchat.bili.c.h("Search http=" + aVarG.f2213a + " body=" + com.im.oldchat.bili.c.d(aVarG.f2214b), null);
            return null;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(BiliModels.SearchResult searchResult) {
            if (searchResult != null) {
                this.f2170e.b(searchResult);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Search failed: ");
            String str = this.f2166a;
            if (str == null) {
                str = "unknown";
            }
            sb.append(str);
            com.im.oldchat.bili.c.h(sb.toString(), null);
            h hVar = this.f2170e;
            String str2 = this.f2166a;
            if (str2 == null) {
                str2 = "搜索失败";
            }
            hVar.a(str2);
        }
    }

    public class e extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2171a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f2172b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ long f2173c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f2174d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ h f2175e;

        public e(String str, long j2, String str2, h hVar) {
            this.f2172b = str;
            this.f2173c = j2;
            this.f2174d = str2;
            this.f2175e = hVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BiliModels.VideoDetailResult doInBackground(Void... voidArr) throws Throwable {
            HashMap map = new HashMap();
            String str = this.f2172b;
            if (str == null || str.length() <= 0) {
                long j2 = this.f2173c;
                if (j2 > 0) {
                    map.put("aid", String.valueOf(j2));
                }
            } else {
                map.put("bvid", this.f2172b);
            }
            c.a aVarG = com.im.oldchat.bili.c.g("https://api.bilibili.com/x/web-interface/view", map, this.f2174d);
            if (aVarG.b()) {
                try {
                    return (BiliModels.VideoDetailResult) com.im.oldchat.bili.c.f2209a.i(aVarG.f2214b, BiliModels.VideoDetailResult.class);
                } catch (Exception e2) {
                    this.f2171a = e2.getMessage();
                    com.im.oldchat.bili.c.h("Detail parse", e2);
                    return null;
                }
            }
            this.f2171a = aVarG.a();
            com.im.oldchat.bili.c.h("Detail http=" + aVarG.f2213a + " body=" + com.im.oldchat.bili.c.d(aVarG.f2214b), null);
            return null;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(BiliModels.VideoDetailResult videoDetailResult) {
            if (videoDetailResult != null) {
                this.f2175e.b(videoDetailResult);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Detail failed: ");
            String str = this.f2171a;
            if (str == null) {
                str = "unknown";
            }
            sb.append(str);
            com.im.oldchat.bili.c.h(sb.toString(), null);
            h hVar = this.f2175e;
            String str2 = this.f2171a;
            if (str2 == null) {
                str2 = "获取详情失败";
            }
            hVar.a(str2);
        }
    }

    public class f extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2176a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long f2177b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f2178c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f2179d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f2180e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ h f2181f;

        public f(long j2, int i2, String str, String str2, h hVar) {
            this.f2177b = j2;
            this.f2178c = i2;
            this.f2179d = str;
            this.f2180e = str2;
            this.f2181f = hVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BiliModels.CommentResult doInBackground(Void... voidArr) {
            String str;
            BiliModels.CommentResult commentResultQ;
            long j2 = this.f2177b;
            if (j2 <= 0) {
                this.f2176a = "invalid aid";
                return null;
            }
            BiliModels.CommentResult commentResultR = com.im.oldchat.bili.d.r(j2, this.f2178c, this.f2179d);
            if (commentResultR != null && commentResultR.code == 0) {
                return commentResultR;
            }
            BiliModels.CommentResult commentResultQ2 = com.im.oldchat.bili.d.q(this.f2177b, this.f2178c, this.f2180e, this.f2179d);
            return ((commentResultQ2 == null || commentResultQ2.code != 0) && (str = this.f2180e) != null && str.length() > 0 && (commentResultQ = com.im.oldchat.bili.d.q(this.f2177b, this.f2178c, "", this.f2179d)) != null) ? commentResultQ : commentResultQ2;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(BiliModels.CommentResult commentResult) {
            if (commentResult != null) {
                this.f2181f.b(commentResult);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Comments failed: ");
            String str = this.f2176a;
            if (str == null) {
                str = "unknown";
            }
            sb.append(str);
            com.im.oldchat.bili.c.h(sb.toString(), null);
            h hVar = this.f2181f;
            String str2 = this.f2176a;
            if (str2 == null) {
                str2 = "获取评论失败";
            }
            hVar.a(str2);
        }
    }

    public class g extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2182a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long f2183b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f2184c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f2185d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ long f2186e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ long f2187f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ String f2188g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ h f2189h;

        public g(long j2, String str, String str2, long j3, long j4, String str3, h hVar) {
            this.f2183b = j2;
            this.f2184c = str;
            this.f2185d = str2;
            this.f2186e = j3;
            this.f2187f = j4;
            this.f2188g = str3;
            this.f2189h = hVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BiliModels.SimpleResult doInBackground(Void... voidArr) throws Throwable {
            String str;
            if (this.f2183b <= 0 || (str = this.f2184c) == null || str.trim().length() == 0) {
                this.f2182a = "invalid input";
                return null;
            }
            String strF = com.im.oldchat.bili.c.f(this.f2185d);
            if (strF == null || strF.length() == 0) {
                this.f2182a = "csrf missing";
                return null;
            }
            HashMap map = new HashMap();
            map.put("oid", String.valueOf(this.f2183b));
            map.put("type", "1");
            map.put("message", this.f2184c);
            long j2 = this.f2186e;
            if (j2 > 0) {
                map.put("root", String.valueOf(j2));
            }
            long j3 = this.f2187f;
            if (j3 > 0) {
                map.put("parent", String.valueOf(j3));
            }
            String str2 = this.f2188g;
            if (str2 != null && str2.length() > 0) {
                map.put("access_key", this.f2188g);
            }
            map.put("csrf", strF);
            map.put("csrf_token", strF);
            c.a aVarJ = com.im.oldchat.bili.c.j("https://api.bilibili.com/x/v2/reply/add", map, this.f2185d);
            if (aVarJ.b()) {
                try {
                    return (BiliModels.SimpleResult) com.im.oldchat.bili.c.f2209a.i(aVarJ.f2214b, BiliModels.SimpleResult.class);
                } catch (Exception e2) {
                    this.f2182a = e2.getMessage();
                    com.im.oldchat.bili.c.h("AddComment parse", e2);
                    return null;
                }
            }
            this.f2182a = aVarJ.a();
            com.im.oldchat.bili.c.h("AddComment http=" + aVarJ.f2213a + " body=" + com.im.oldchat.bili.c.d(aVarJ.f2214b), null);
            return null;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(BiliModels.SimpleResult simpleResult) {
            if (simpleResult != null) {
                this.f2189h.b(simpleResult);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("AddComment failed: ");
            String str = this.f2182a;
            if (str == null) {
                str = "unknown";
            }
            sb.append(str);
            com.im.oldchat.bili.c.h(sb.toString(), null);
            h hVar = this.f2189h;
            String str2 = this.f2182a;
            if (str2 == null) {
                str2 = "发表评论失败";
            }
            hVar.a(str2);
        }
    }

    public interface h {
        void a(String str);

        void b(Object obj);
    }

    public static void A(long j2, String str, long j3, long j4, String str2, String str3, h hVar) {
        com.im.oldchat.bili.c.e(new g(j2, str, str3, j3, j4, str2, hVar), new Void[0]);
    }

    public static void B(long j2, int i2, String str, String str2, h hVar) {
        com.im.oldchat.bili.c.e(new f(j2, i2, str2, str, hVar), new Void[0]);
    }

    public static void C(h hVar) {
        com.im.oldchat.bili.c.e(new AsyncTaskC0022a(hVar), new Void[0]);
    }

    public static void D(String str, String str2, h hVar) {
        com.im.oldchat.bili.c.e(new c(str, str2, hVar), new Void[0]);
    }

    public static void E(String str, int i2, String str2, h hVar) {
        com.im.oldchat.bili.c.e(new d(str, i2, str2, hVar), new Void[0]);
    }

    public static void F(String str, long j2, String str2, h hVar) {
        com.im.oldchat.bili.c.e(new e(str, j2, str2, hVar), new Void[0]);
    }

    public static void z(String str, h hVar) {
        com.im.oldchat.bili.c.e(new b(str, hVar), new Void[0]);
    }
}
