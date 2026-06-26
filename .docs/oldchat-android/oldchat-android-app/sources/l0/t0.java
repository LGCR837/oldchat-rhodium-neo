package l0;

import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;
import com.im.oldchat.bili.BiliModels;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class t0 extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public EditText f6284A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public View f6285B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public View f6286C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public View f6287D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public ImageView f6288E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public VideoView f6289F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public View f6290G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public View f6291H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public View f6292I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public View f6293J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public ProgressBar f6294K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public TextView f6295L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public View f6296M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public ImageView f6297N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public TextView f6298O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public TextView f6299P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public TextView f6300Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public TextView f6301R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public View f6302S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public TextView f6303T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public TextView f6304U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public TextView f6305V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public TextView f6306W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public TextView f6307X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public C0383h0 f6308Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public com.im.oldchat.ui.Z f6309Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public View f6310a0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public String f6312c0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public String f6322m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public String f6323n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public String f6324o0;
    public String p0;
    public String r0;
    public String s0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ProgressBar f6325w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ListView f6326x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f6327y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public View f6328z;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public long f6311b0 = 0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public long f6313d0 = 0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public int f6314e0 = 0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public boolean f6315f0 = false;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public boolean f6316g0 = true;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public boolean f6317h0 = false;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public boolean f6318i0 = false;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public boolean f6319j0 = false;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public int f6320k0 = 0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public boolean f6321l0 = false;
    public long q0 = 0;
    public int t0 = 0;
    public int u0 = 0;
    public boolean v0 = false;

    public void A0(boolean z2, String str) {
        TextView textView = this.f6327y;
        if (textView == null) {
            return;
        }
        if (str != null) {
            textView.setText(str);
        }
        this.f6327y.setVisibility(z2 ? 0 : 8);
    }

    public void f0(StringBuilder sb, String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        if (sb.length() > 0) {
            sb.append(" · ");
        }
        sb.append(str);
    }

    public final void g0(List list, Set set, BiliModels.CommentReply commentReply) {
        if (list == null || set == null || commentReply == null) {
            return;
        }
        long j2 = commentReply.rpid;
        if (j2 <= 0 || !set.contains(Long.valueOf(j2))) {
            list.add(commentReply);
            long j3 = commentReply.rpid;
            if (j3 > 0) {
                set.add(Long.valueOf(j3));
            }
        }
    }

    public List h0(BiliModels.CommentData commentData, int i2) {
        BiliModels.CommentReply commentReply;
        BiliModels.CommentUpper commentUpper;
        ArrayList arrayList = new ArrayList();
        if (commentData == null) {
            return arrayList;
        }
        List arrayList2 = commentData.replies;
        if (arrayList2 == null) {
            arrayList2 = new ArrayList();
        }
        int i3 = 0;
        if (i2 == 1) {
            BiliModels.CommentTop commentTop = commentData.top;
            if (commentTop == null || ((commentReply = commentTop.reply) == null && (commentReply = commentTop.upper) == null)) {
                commentReply = null;
            }
            if (commentReply == null && (commentUpper = commentData.upper) != null) {
                commentReply = commentUpper.top;
            }
            HashSet hashSet = new HashSet();
            if (commentReply != null) {
                commentReply.topComment = true;
                g0(arrayList, hashSet, commentReply);
            }
            List arrayList3 = commentData.hots;
            if (arrayList3 == null) {
                arrayList3 = new ArrayList();
            }
            for (int i4 = 0; i4 < arrayList3.size(); i4++) {
                BiliModels.CommentReply commentReply2 = (BiliModels.CommentReply) arrayList3.get(i4);
                if (commentReply2 != null) {
                    commentReply2.hotComment = true;
                    g0(arrayList, hashSet, commentReply2);
                }
            }
            if (arrayList2.isEmpty() && !arrayList3.isEmpty()) {
                u0("loadComments: use hot replies size=" + arrayList3.size());
            }
            while (i3 < arrayList2.size()) {
                BiliModels.CommentReply commentReply3 = (BiliModels.CommentReply) arrayList2.get(i3);
                if (commentReply3 != null) {
                    g0(arrayList, hashSet, commentReply3);
                }
                i3++;
            }
        } else {
            while (i3 < arrayList2.size()) {
                BiliModels.CommentReply commentReply4 = (BiliModels.CommentReply) arrayList2.get(i3);
                if (commentReply4 != null) {
                    arrayList.add(commentReply4);
                }
                i3++;
            }
        }
        return arrayList;
    }

    public String i0(BiliModels.VideoDetailData videoDetailData) {
        StringBuilder sb = new StringBuilder();
        String strP0 = p0(videoDetailData.duration);
        if (strP0.length() > 0) {
            f0(sb, "时长 " + strP0);
        }
        return sb.toString();
    }

    public String j0() {
        String strK0 = k0();
        StringBuilder sb = new StringBuilder();
        sb.append("视频: ");
        sb.append(strK0);
        String strP0 = p0(this.q0);
        if (strP0.length() > 0) {
            sb.append("\n时长: ");
            sb.append(strP0);
        }
        sb.append("\n点击气泡观看");
        return sb.toString();
    }

    public String k0() {
        String str = this.f6324o0;
        if (str != null && str.length() > 0) {
            return this.f6324o0;
        }
        TextView textView = this.f6300Q;
        return (textView == null || textView.getText() == null || this.f6300Q.getText().length() <= 0) ? "B站视频" : this.f6300Q.getText().toString();
    }

    public String l0() {
        String str = this.r0;
        if (str == null || str.length() == 0) {
            str = this.f6323n0;
        }
        return h0.c.b(this.f6312c0, this.f6311b0, this.f6313d0, k0(), str, this.q0);
    }

    public long m0(String str) {
        if (str == null || str.length() < 10) {
            return 0L;
        }
        int[] iArr = {11, 10, 3, 8, 4, 6};
        long j2 = 0;
        for (int i2 = 0; i2 < 6; i2++) {
            try {
                int iIndexOf = "fZodR9XQDSUm21yCkr6zBqiveYah8bt4xsWpHnJE7jL5VG3guMTKNPAwcF".indexOf(str.charAt(iArr[i2]));
                if (iIndexOf < 0) {
                    return 0L;
                }
                long j3 = iIndexOf;
                long jV0 = v0(i2);
                Long.signum(j3);
                j2 += j3 * jV0;
            } catch (Exception unused) {
                return 0L;
            }
        }
        long j4 = 177451812 ^ (j2 - 8728348608L);
        if (j4 > 0) {
            return j4;
        }
        return 0L;
    }

    public long n0(BiliModels.VideoDetailData videoDetailData) {
        List<BiliModels.VideoPage> list;
        if (videoDetailData == null || (list = videoDetailData.pages) == null || list.isEmpty()) {
            if (videoDetailData != null) {
                return videoDetailData.cid;
            }
            return 0L;
        }
        BiliModels.VideoPage videoPage = videoDetailData.pages.get(0);
        if (videoPage != null) {
            long j2 = videoPage.cid;
            if (j2 > 0) {
                return j2;
            }
        }
        return videoDetailData.cid;
    }

    public String o0(int i2) {
        if (i2 < 10000) {
            return String.valueOf(i2);
        }
        Locale locale = Locale.US;
        double d2 = i2;
        Double.isNaN(d2);
        return String.format(locale, "%.1f万", Double.valueOf(d2 / 10000.0d));
    }

    public String p0(long j2) {
        return j2 <= 0 ? "" : String.format(Locale.US, "%02d:%02d", Long.valueOf(j2 / 60), Long.valueOf(j2 % 60));
    }

    public boolean q0(BiliModels.CommentPage commentPage, List list) {
        if (commentPage == null) {
            return list != null && list.size() >= 20;
        }
        int i2 = commentPage.count;
        int i3 = commentPage.size;
        int i4 = i3 > 0 ? i3 : 20;
        int i5 = commentPage.num;
        double d2 = i2;
        double d3 = i4;
        Double.isNaN(d2);
        Double.isNaN(d3);
        return i5 < ((int) Math.ceil(d2 / d3));
    }

    public void r0(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
            if (inputMethodManager == null || view == null) {
                return;
            }
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception unused) {
        }
    }

    public boolean s0(String str) {
        if (str == null) {
            return false;
        }
        String strTrim = str.trim();
        if (strTrim.length() < 3) {
            return false;
        }
        return strTrim.startsWith("BV") || strTrim.startsWith("bv");
    }

    public void t0(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("【bilibili 测试报错！！！！！】 ");
        if (str == null) {
            str = "";
        }
        sb.append(str);
        Log.e("OldViewDetail", sb.toString());
    }

    public void u0(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("【bilibili 测试报错！！！！！】 ");
        if (str == null) {
            str = "";
        }
        sb.append(str);
        Log.d("OldViewDetail", sb.toString());
    }

    public long v0(int i2) {
        long j2 = 1;
        for (int i3 = 0; i3 < i2; i3++) {
            j2 *= 58;
        }
        return j2;
    }

    public void w0(String str) {
        if (this.f6295L == null || str == null || str.length() == 0) {
            return;
        }
        this.f6295L.setText(str);
    }

    public void x0(boolean z2) {
        ProgressBar progressBar = this.f6325w;
        if (progressBar != null) {
            progressBar.setVisibility(z2 ? 0 : 8);
        }
        if (z2) {
            A0(false, null);
        }
    }

    public void y0(boolean z2, String str) {
        View view = this.f6293J;
        if (view != null) {
            view.setVisibility(z2 ? 0 : 8);
        }
        ProgressBar progressBar = this.f6294K;
        if (progressBar != null) {
            progressBar.setVisibility(z2 ? 0 : 8);
        }
        if (this.f6295L != null && str != null && str.length() > 0) {
            this.f6295L.setText(str);
        }
        if (z2) {
            z0(false);
        }
    }

    public boolean z0(boolean z2) {
        View view = this.f6292I;
        if (view == null) {
            return false;
        }
        view.setVisibility(z2 ? 0 : 8);
        return true;
    }
}
