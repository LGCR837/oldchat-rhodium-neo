package l0;

import android.content.Intent;
import android.widget.Toast;
import com.im.oldchat.bili.BiliModels;
import com.im.oldchat.bili.a;
import com.im.oldchat.ui.OldViewVideoDetailActivity;
import java.util.ArrayList;
import java.util.List;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class u0 extends AbstractActivityC0402r0 {
    public boolean w0;
    public String x0 = "";

    public class a implements a.h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6329a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f6330b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f6331c;

        public a(String str, String str2, String str3) {
            this.f6329a = str;
            this.f6330b = str2;
            this.f6331c = str3;
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            u0.this.t1(this.f6330b, this.f6331c, this.f6329a);
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(List list) {
            List listK1 = u0.this.k1(list);
            if (listK1.isEmpty()) {
                u0.this.t1(this.f6330b, this.f6331c, this.f6329a);
                return;
            }
            u0.this.w0 = false;
            u0.this.x0 = this.f6329a;
            u0.this.f6309Z.g(listK1);
        }
    }

    public class b implements a.h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6333a;

        public b(String str) {
            this.f6333a = str;
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            u0.this.w0 = false;
            if (u0.this.f6309Z.getCount() == 0) {
                u0.this.f6309Z.g(new ArrayList());
            }
            if (str == null || str.length() <= 0) {
                return;
            }
            u0.this.t0("loadRelatedVideos failed: " + str);
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(List list) {
            u0.this.w0 = false;
            u0.this.x0 = this.f6333a;
            u0 u0Var = u0.this;
            u0Var.f6309Z.g(u0Var.k1(list));
        }
    }

    private long g1(BiliModels.RecommendItem recommendItem) {
        if (recommendItem == null) {
            return 0L;
        }
        BiliModels.RecommendArgs recommendArgs = recommendItem.args;
        if (recommendArgs != null) {
            long j2 = recommendArgs.aid;
            if (j2 > 0) {
                return j2;
            }
        }
        long jQ1 = q1(recommendItem.param);
        return jQ1 > 0 ? jQ1 : p1(recommendItem.uri);
    }

    private String h1(BiliModels.RecommendItem recommendItem) {
        char cCharAt;
        if (recommendItem == null) {
            return null;
        }
        if (s0(recommendItem.param)) {
            return recommendItem.param.trim();
        }
        String str = recommendItem.uri;
        if (str != null) {
            int iIndexOf = str.indexOf("BV");
            if (iIndexOf >= 0) {
                int i2 = iIndexOf;
                while (i2 < recommendItem.uri.length() && (cCharAt = recommendItem.uri.charAt(i2)) != '/' && cCharAt != '?' && cCharAt != '&') {
                    i2++;
                }
                String strSubstring = recommendItem.uri.substring(iIndexOf, i2);
                if (s0(strSubstring)) {
                    return strSubstring;
                }
            }
            String strL1 = l1(recommendItem.uri, "bvid");
            if (s0(strL1)) {
                return strL1;
            }
        }
        return null;
    }

    private long i1(String str) {
        return s1(l1(str, "cid"));
    }

    private String j1(String str) {
        String strI;
        String strL1 = l1(str, "url");
        return (strL1 == null || strL1.length() == 0 || (strI = com.im.oldchat.bili.c.i(strL1)) == null) ? "" : strI;
    }

    private String l1(String str, String str2) {
        int iIndexOf;
        if (str != null && str2 != null && str2.length() != 0 && (iIndexOf = str.indexOf(63)) >= 0 && iIndexOf < str.length() - 1) {
            for (String str3 : str.substring(iIndexOf + 1).split("&")) {
                int iIndexOf2 = str3.indexOf(61);
                if (iIndexOf2 > 0 && str2.equals(str3.substring(0, iIndexOf2))) {
                    return str3.substring(iIndexOf2 + 1);
                }
            }
        }
        return null;
    }

    private long p1(String str) {
        if (str == null || str.length() == 0) {
            return 0L;
        }
        long jS1 = s1(l1(str, "aid"));
        if (jS1 > 0) {
            return jS1;
        }
        int iIndexOf = str.indexOf("/av");
        if (iIndexOf >= 0) {
            return r1(str, iIndexOf + 3);
        }
        int iIndexOf2 = str.indexOf("video/");
        if (iIndexOf2 >= 0) {
            return r1(str, iIndexOf2 + 6);
        }
        return 0L;
    }

    private long q1(String str) {
        if (str == null || str.length() == 0) {
            return 0L;
        }
        String strTrim = str.trim();
        if (strTrim.startsWith("av") || strTrim.startsWith("AV")) {
            strTrim = strTrim.substring(2);
        }
        if (strTrim.startsWith("aid=")) {
            strTrim = strTrim.substring(4);
        }
        return s1(strTrim);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private long r1(java.lang.String r9, int r10) {
        /*
            r8 = this;
            r0 = 0
            if (r9 == 0) goto L34
            if (r10 < 0) goto L34
            int r2 = r9.length()
            if (r10 < r2) goto Ld
            goto L34
        Ld:
            r2 = 0
            r3 = r0
        Lf:
            int r5 = r9.length()
            if (r10 >= r5) goto L31
            char r5 = r9.charAt(r10)
            r6 = 48
            if (r5 < r6) goto L2d
            r6 = 57
            if (r5 > r6) goto L2d
            r6 = 10
            long r3 = r3 * r6
            int r5 = r5 + (-48)
            long r5 = (long) r5
            long r3 = r3 + r5
            int r10 = r10 + 1
            r2 = 1
            goto Lf
        L2d:
            if (r2 == 0) goto L30
            goto L31
        L30:
            return r0
        L31:
            if (r2 == 0) goto L34
            r0 = r3
        L34:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: l0.u0.r1(java.lang.String, int):long");
    }

    public final String f1() {
        String str = this.f6312c0;
        return (str != null ? str.trim() : "") + "#" + this.f6311b0;
    }

    public final List k1(List list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                BiliModels.RecommendItem recommendItem = (BiliModels.RecommendItem) list.get(i2);
                if (recommendItem != null) {
                    String strH1 = h1(recommendItem);
                    long jG1 = g1(recommendItem);
                    if (((strH1 != null && strH1.length() != 0) || jG1 > 0) && !m1(jG1, strH1)) {
                        arrayList.add(recommendItem);
                        if (arrayList.size() >= 24) {
                            break;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public final boolean m1(long j2, String str) {
        String str2;
        if (j2 > 0) {
            long j3 = this.f6311b0;
            if (j3 > 0 && j2 == j3) {
                return true;
            }
        }
        if (str == null || str.length() <= 0 || (str2 = this.f6312c0) == null || str2.length() <= 0) {
            return false;
        }
        return str.equalsIgnoreCase(this.f6312c0);
    }

    public void n1(boolean z2) {
        if (this.f6309Z == null) {
            return;
        }
        String strF1 = f1();
        if ((z2 || !strF1.equals(this.x0) || this.f6309Z.getCount() <= 0) && !this.w0) {
            this.w0 = true;
            String strC = h0.a.c(this);
            String strB = h0.a.b(this);
            if (h0.a.e(this)) {
                strB = "";
            }
            long j2 = this.f6311b0;
            if (j2 > 0) {
                com.im.oldchat.bili.b.r(j2, strC, new a(strF1, strB, strC));
            } else {
                t1(strB, strC, strF1);
            }
        }
    }

    public void o1(BiliModels.RecommendItem recommendItem) {
        if (recommendItem == null) {
            return;
        }
        String strH1 = h1(recommendItem);
        long jG1 = g1(recommendItem);
        long jI1 = i1(recommendItem.uri);
        String strJ1 = j1(recommendItem.uri);
        if ((strH1 == null || strH1.length() == 0) && jG1 <= 0 && (strJ1 == null || strJ1.length() == 0)) {
            Toast.makeText(this, "无法识别该视频", 0).show();
            return;
        }
        if (m1(jG1, strH1)) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) OldViewVideoDetailActivity.class);
        if (strH1 != null && strH1.length() > 0) {
            intent.putExtra("bvid", strH1);
        }
        if (jG1 > 0) {
            intent.putExtra("aid", jG1);
        }
        if (jI1 > 0) {
            intent.putExtra("cid", jI1);
        }
        if (strJ1 != null && strJ1.length() > 0) {
            intent.putExtra("preload_url", strJ1);
        }
        String str = recommendItem.cover;
        if (str != null && str.length() > 0) {
            intent.putExtra("cover_url", recommendItem.cover);
        }
        String str2 = recommendItem.title;
        if (str2 != null && str2.length() > 0) {
            intent.putExtra(MessageBundle.TITLE_ENTRY, recommendItem.title);
        }
        startActivity(intent);
    }

    public final long s1(String str) {
        if (str == null || str.length() == 0) {
            return 0L;
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt < '0' || cCharAt > '9') {
                return 0L;
            }
        }
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return 0L;
        }
    }

    public final void t1(String str, String str2, String str3) {
        com.im.oldchat.bili.a.D(str, str2, new b(str3));
    }
}
