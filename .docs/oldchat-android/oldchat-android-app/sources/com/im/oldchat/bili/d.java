package com.im.oldchat.bili;

import android.os.AsyncTask;
import com.im.oldchat.bili.BiliModels;
import com.im.oldchat.bili.a;
import com.im.oldchat.bili.c;
import com.im.oldchat.bili.f;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class d extends com.im.oldchat.bili.c {

    public class a extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2215a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f2216b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ long f2217c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ long f2218d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f2219e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ String f2220f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ a.h f2221g;

        public a(String str, long j2, long j3, int i2, String str2, a.h hVar) {
            this.f2216b = str;
            this.f2217c = j2;
            this.f2218d = j3;
            this.f2219e = i2;
            this.f2220f = str2;
            this.f2221g = hVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BiliModels.PlayUrlResult doInBackground(Void... voidArr) throws Throwable {
            String str = this.f2216b;
            if ((str == null || str.length() == 0) && (this.f2217c <= 0 || this.f2218d <= 0)) {
                this.f2215a = "invalid aid/cid";
                return null;
            }
            BiliModels.PlayUrlResult playUrlResultS = d.s(this.f2216b, this.f2217c, this.f2218d, this.f2219e, this.f2220f, 1, "html5");
            if (d.t(playUrlResultS)) {
                return playUrlResultS;
            }
            BiliModels.PlayUrlResult playUrlResultS2 = d.s(this.f2216b, this.f2217c, this.f2218d, this.f2219e, this.f2220f, 0, "android");
            return (!d.t(playUrlResultS2) && playUrlResultS2 == null) ? playUrlResultS : playUrlResultS2;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(BiliModels.PlayUrlResult playUrlResult) {
            if (playUrlResult == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("PlayUrl failed: ");
                String str = this.f2215a;
                if (str == null) {
                    str = "unknown";
                }
                sb.append(str);
                com.im.oldchat.bili.c.h(sb.toString(), null);
                a.h hVar = this.f2221g;
                String str2 = this.f2215a;
                if (str2 == null) {
                    str2 = "获取播放地址失败";
                }
                hVar.a(str2);
                return;
            }
            if (playUrlResult.code != 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("PlayUrl code=");
                sb2.append(playUrlResult.code);
                sb2.append(" msg=");
                sb2.append(playUrlResult.message);
                sb2.append(" bvid=");
                String str3 = this.f2216b;
                if (str3 == null) {
                    str3 = "";
                }
                sb2.append(str3);
                sb2.append(" aid=");
                sb2.append(this.f2217c);
                sb2.append(" cid=");
                sb2.append(this.f2218d);
                com.im.oldchat.bili.c.h(sb2.toString(), null);
            }
            this.f2221g.b(playUrlResult);
        }
    }

    public class b extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2222a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f2223b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ a.h f2224c;

        public b(String str, a.h hVar) {
            this.f2223b = str;
            this.f2224c = hVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BiliModels.NavResult doInBackground(Void... voidArr) throws Throwable {
            c.a aVarG = com.im.oldchat.bili.c.g("https://api.bilibili.com/x/web-interface/nav", new HashMap(), this.f2223b);
            if (aVarG.b()) {
                try {
                    return (BiliModels.NavResult) com.im.oldchat.bili.c.f2209a.i(aVarG.f2214b, BiliModels.NavResult.class);
                } catch (Exception e2) {
                    this.f2222a = e2.getMessage();
                    com.im.oldchat.bili.c.h("Nav parse", e2);
                    return null;
                }
            }
            this.f2222a = aVarG.a();
            com.im.oldchat.bili.c.h("Nav http=" + aVarG.f2213a + " body=" + com.im.oldchat.bili.c.d(aVarG.f2214b), null);
            return null;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(BiliModels.NavResult navResult) {
            if (navResult != null) {
                this.f2224c.b(navResult);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Nav failed: ");
            String str = this.f2222a;
            if (str == null) {
                str = "unknown";
            }
            sb.append(str);
            com.im.oldchat.bili.c.h(sb.toString(), null);
            a.h hVar = this.f2224c;
            String str2 = this.f2222a;
            if (str2 == null) {
                str2 = "获取用户信息失败";
            }
            hVar.a(str2);
        }
    }

    public class c extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2225a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2226b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ long f2227c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ long f2228d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f2229e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ a.h f2230f;

        public c(int i2, long j2, long j3, String str, a.h hVar) {
            this.f2226b = i2;
            this.f2227c = j2;
            this.f2228d = j3;
            this.f2229e = str;
            this.f2230f = hVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BiliModels.HistoryResult doInBackground(Void... voidArr) throws Throwable {
            HashMap map = new HashMap();
            int i2 = this.f2226b;
            if (i2 <= 0) {
                i2 = 20;
            }
            map.put("ps", String.valueOf(i2));
            map.put("max", String.valueOf(Math.max(0L, this.f2227c)));
            map.put("view_at", String.valueOf(Math.max(0L, this.f2228d)));
            c.a aVarG = com.im.oldchat.bili.c.g("https://api.bilibili.com/x/web-interface/history/cursor", map, this.f2229e);
            if (aVarG.b()) {
                try {
                    return (BiliModels.HistoryResult) com.im.oldchat.bili.c.f2209a.i(aVarG.f2214b, BiliModels.HistoryResult.class);
                } catch (Exception e2) {
                    this.f2225a = e2.getMessage();
                    com.im.oldchat.bili.c.h("History parse", e2);
                    return null;
                }
            }
            this.f2225a = aVarG.a();
            com.im.oldchat.bili.c.h("History http=" + aVarG.f2213a + " body=" + com.im.oldchat.bili.c.d(aVarG.f2214b), null);
            return null;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(BiliModels.HistoryResult historyResult) {
            if (historyResult != null) {
                this.f2230f.b(historyResult);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("History failed: ");
            String str = this.f2225a;
            if (str == null) {
                str = "unknown";
            }
            sb.append(str);
            com.im.oldchat.bili.c.h(sb.toString(), null);
            a.h hVar = this.f2230f;
            String str2 = this.f2225a;
            if (str2 == null) {
                str2 = "获取历史失败";
            }
            hVar.a(str2);
        }
    }

    /* JADX INFO: renamed from: com.im.oldchat.bili.d$d, reason: collision with other inner class name */
    public class AsyncTaskC0024d extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2231a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long f2232b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f2233c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ a.h f2234d;

        public AsyncTaskC0024d(long j2, String str, a.h hVar) {
            this.f2232b = j2;
            this.f2233c = str;
            this.f2234d = hVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BiliModels.FavFolderResult doInBackground(Void... voidArr) throws Throwable {
            if (this.f2232b <= 0) {
                this.f2231a = "invalid mid";
                return null;
            }
            HashMap map = new HashMap();
            map.put("up_mid", String.valueOf(this.f2232b));
            map.put("type", "2");
            c.a aVarG = com.im.oldchat.bili.c.g("https://api.bilibili.com/x/v3/fav/folder/created/list-all", map, this.f2233c);
            if (aVarG.b()) {
                try {
                    return (BiliModels.FavFolderResult) com.im.oldchat.bili.c.f2209a.i(aVarG.f2214b, BiliModels.FavFolderResult.class);
                } catch (Exception e2) {
                    this.f2231a = e2.getMessage();
                    com.im.oldchat.bili.c.h("FavFolder parse", e2);
                    return null;
                }
            }
            this.f2231a = aVarG.a();
            com.im.oldchat.bili.c.h("FavFolder http=" + aVarG.f2213a + " body=" + com.im.oldchat.bili.c.d(aVarG.f2214b), null);
            return null;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(BiliModels.FavFolderResult favFolderResult) {
            if (favFolderResult != null) {
                this.f2234d.b(favFolderResult);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("FavFolder failed: ");
            String str = this.f2231a;
            if (str == null) {
                str = "unknown";
            }
            sb.append(str);
            com.im.oldchat.bili.c.h(sb.toString(), null);
            a.h hVar = this.f2234d;
            String str2 = this.f2231a;
            if (str2 == null) {
                str2 = "获取收藏夹失败";
            }
            hVar.a(str2);
        }
    }

    public class e extends AsyncTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f2235a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long f2236b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f2237c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f2238d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ String f2239e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ a.h f2240f;

        public e(long j2, int i2, int i3, String str, a.h hVar) {
            this.f2236b = j2;
            this.f2237c = i2;
            this.f2238d = i3;
            this.f2239e = str;
            this.f2240f = hVar;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BiliModels.FavResourceResult doInBackground(Void... voidArr) throws Throwable {
            if (this.f2236b <= 0) {
                this.f2235a = "invalid media id";
                return null;
            }
            HashMap map = new HashMap();
            map.put("media_id", String.valueOf(this.f2236b));
            map.put("pn", String.valueOf(Math.max(1, this.f2237c)));
            int i2 = this.f2238d;
            if (i2 <= 0) {
                i2 = 20;
            }
            map.put("ps", String.valueOf(i2));
            c.a aVarG = com.im.oldchat.bili.c.g("https://api.bilibili.com/x/v3/fav/resource/list", map, this.f2239e);
            if (aVarG.b()) {
                try {
                    return (BiliModels.FavResourceResult) com.im.oldchat.bili.c.f2209a.i(aVarG.f2214b, BiliModels.FavResourceResult.class);
                } catch (Exception e2) {
                    this.f2235a = e2.getMessage();
                    com.im.oldchat.bili.c.h("FavResource parse", e2);
                    return null;
                }
            }
            this.f2235a = aVarG.a();
            com.im.oldchat.bili.c.h("FavResource http=" + aVarG.f2213a + " body=" + com.im.oldchat.bili.c.d(aVarG.f2214b), null);
            return null;
        }

        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(BiliModels.FavResourceResult favResourceResult) {
            if (favResourceResult != null) {
                this.f2240f.b(favResourceResult);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("FavResource failed: ");
            String str = this.f2235a;
            if (str == null) {
                str = "unknown";
            }
            sb.append(str);
            com.im.oldchat.bili.c.h(sb.toString(), null);
            a.h hVar = this.f2240f;
            String str2 = this.f2235a;
            if (str2 == null) {
                str2 = "获取收藏内容失败";
            }
            hVar.a(str2);
        }
    }

    public static boolean o(String str) throws Throwable {
        BiliModels.NavData navData;
        BiliModels.WbiImg wbiImg;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (com.im.oldchat.bili.c.f2210b != null && com.im.oldchat.bili.c.f2211c != null && jCurrentTimeMillis - com.im.oldchat.bili.c.f2212d < 86400000) {
            return true;
        }
        c.a aVarG = com.im.oldchat.bili.c.g("https://api.bilibili.com/x/web-interface/nav", new HashMap(), str);
        if (!aVarG.b()) {
            com.im.oldchat.bili.c.h("Nav http=" + aVarG.f2213a + " body=" + com.im.oldchat.bili.c.d(aVarG.f2214b), null);
            return false;
        }
        try {
            BiliModels.NavResult navResult = (BiliModels.NavResult) com.im.oldchat.bili.c.f2209a.i(aVarG.f2214b, BiliModels.NavResult.class);
            if (navResult != null && navResult.code == 0 && (navData = navResult.data) != null && (wbiImg = navData.wbiImg) != null) {
                String str2 = wbiImg.imgUrl;
                String str3 = wbiImg.subUrl;
                String strP = p(str2);
                String strP2 = p(str3);
                if (strP != null && strP.length() > 0 && strP2 != null && strP2.length() > 0) {
                    com.im.oldchat.bili.c.f2210b = strP;
                    com.im.oldchat.bili.c.f2211c = strP2;
                    com.im.oldchat.bili.c.f2212d = jCurrentTimeMillis;
                    return true;
                }
            }
        } catch (Exception e2) {
            com.im.oldchat.bili.c.h("Nav parse", e2);
        }
        return false;
    }

    public static String p(String str) {
        if (str == null) {
            return "";
        }
        int iLastIndexOf = str.lastIndexOf(47);
        if (iLastIndexOf >= 0) {
            str = str.substring(iLastIndexOf + 1);
        }
        int iIndexOf = str.indexOf(46);
        return iIndexOf > 0 ? str.substring(0, iIndexOf) : str;
    }

    public static BiliModels.CommentResult q(long j2, int i2, String str, String str2) {
        HashMap map = new HashMap();
        map.put("oid", String.valueOf(j2));
        map.put("type", "1");
        map.put("pn", String.valueOf(Math.max(1, i2)));
        map.put("ps", "20");
        map.put("sort", "0");
        map.put("mode", "3");
        map.put("nohot", "0");
        if (str != null && str.length() > 0) {
            map.put("access_key", str);
        }
        c.a aVarG = com.im.oldchat.bili.c.g("https://api.bilibili.com/x/v2/reply", map, str2);
        if (aVarG.b()) {
            try {
                return (BiliModels.CommentResult) com.im.oldchat.bili.c.f2209a.i(aVarG.f2214b, BiliModels.CommentResult.class);
            } catch (Exception e2) {
                com.im.oldchat.bili.c.h("Comments parse", e2);
                return null;
            }
        }
        com.im.oldchat.bili.c.h("Comments http=" + aVarG.f2213a + " body=" + com.im.oldchat.bili.c.d(aVarG.f2214b), null);
        return null;
    }

    public static BiliModels.CommentResult r(long j2, int i2, String str) {
        String str2;
        if (!o(str)) {
            return null;
        }
        HashMap map = new HashMap();
        map.put("oid", String.valueOf(j2));
        map.put("type", "1");
        map.put("pn", String.valueOf(Math.max(1, i2)));
        map.put("ps", "20");
        map.put("mode", "3");
        f.a aVarE = f.e(map, com.im.oldchat.bili.c.f2210b, com.im.oldchat.bili.c.f2211c);
        if (aVarE != null && (str2 = aVarE.f2265a) != null && str2.length() != 0) {
            map.put("w_rid", aVarE.f2265a);
            map.put("wts", String.valueOf(aVarE.f2266b));
            c.a aVarG = com.im.oldchat.bili.c.g("https://api.bilibili.com/x/v2/reply/wbi/main", map, str);
            if (!aVarG.b()) {
                com.im.oldchat.bili.c.h("CommentsWbi http=" + aVarG.f2213a + " body=" + com.im.oldchat.bili.c.d(aVarG.f2214b), null);
                return null;
            }
            try {
                return (BiliModels.CommentResult) com.im.oldchat.bili.c.f2209a.i(aVarG.f2214b, BiliModels.CommentResult.class);
            } catch (Exception e2) {
                com.im.oldchat.bili.c.h("CommentsWbi parse", e2);
            }
        }
        return null;
    }

    public static BiliModels.PlayUrlResult s(String str, long j2, long j3, int i2, String str2, int i3, String str3) throws Throwable {
        HashMap map = new HashMap();
        if (str == null || str.length() <= 0) {
            map.put("aid", String.valueOf(j2));
        } else {
            map.put("bvid", str);
        }
        map.put("cid", String.valueOf(j3));
        map.put("qn", String.valueOf(i2));
        if (i3 > 0) {
            map.put("fnval", String.valueOf(i3));
        }
        map.put("fnver", "0");
        map.put("fourk", "0");
        if (str3 != null && str3.length() > 0) {
            map.put("platform", str3);
            map.put("mobi_app", str3);
        }
        map.put("otype", "json");
        c.a aVarG = com.im.oldchat.bili.c.g("https://api.bilibili.com/x/player/playurl", map, str2);
        if (aVarG.b()) {
            try {
                return (BiliModels.PlayUrlResult) com.im.oldchat.bili.c.f2209a.i(aVarG.f2214b, BiliModels.PlayUrlResult.class);
            } catch (Exception e2) {
                com.im.oldchat.bili.c.h("PlayUrl parse", e2);
                return null;
            }
        }
        com.im.oldchat.bili.c.h("PlayUrl http=" + aVarG.f2213a + " body=" + com.im.oldchat.bili.c.d(aVarG.f2214b), null);
        return null;
    }

    public static boolean t(BiliModels.PlayUrlResult playUrlResult) {
        BiliModels.PlayUrlData playUrlData;
        List<BiliModels.PlayUrlDurl> list;
        if (playUrlResult == null || playUrlResult.code != 0 || (playUrlData = playUrlResult.data) == null || (list = playUrlData.durl) == null || list.isEmpty()) {
            return false;
        }
        String str = playUrlResult.data.durl.get(0) != null ? playUrlResult.data.durl.get(0).url : null;
        return str != null && str.length() > 0;
    }

    public static void u(long j2, String str, a.h hVar) {
        com.im.oldchat.bili.c.e(new AsyncTaskC0024d(j2, str, hVar), new Void[0]);
    }

    public static void v(long j2, int i2, int i3, String str, a.h hVar) {
        com.im.oldchat.bili.c.e(new e(j2, i2, i3, str, hVar), new Void[0]);
    }

    public static void w(long j2, long j3, int i2, String str, a.h hVar) {
        com.im.oldchat.bili.c.e(new c(i2, j2, j3, str, hVar), new Void[0]);
    }

    public static void x(String str, a.h hVar) {
        com.im.oldchat.bili.c.e(new b(str, hVar), new Void[0]);
    }

    public static void y(String str, long j2, long j3, int i2, String str2, a.h hVar) {
        com.im.oldchat.bili.c.e(new a(str, j2, j3, i2, str2, hVar), new Void[0]);
    }
}
