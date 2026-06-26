package l0;

import android.R;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.bili.BiliModels;
import com.im.oldchat.bili.a;
import java.util.ArrayList;
import java.util.List;
import o0.AbstractC0455v;

/* JADX INFO: loaded from: classes.dex */
public abstract class s0 extends t0 {

    public class a implements a.h {
        public a() {
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            String str2;
            s0.this.x0(false);
            s0 s0Var = s0.this;
            StringBuilder sb = new StringBuilder();
            sb.append("详情请求异常: ");
            sb.append(str != null ? str : "unknown");
            s0Var.t0(sb.toString());
            s0.this.C0();
            s0 s0Var2 = s0.this;
            s0Var2.A0(true, s0Var2.getString(f0.j.M1));
            s0 s0Var3 = s0.this;
            if (s0Var3.f6311b0 <= 0 && (str2 = s0Var3.f6312c0) != null) {
                long jM0 = s0Var3.m0(str2);
                if (jM0 > 0) {
                    s0 s0Var4 = s0.this;
                    s0Var4.f6311b0 = jM0;
                    s0Var4.u0("loadDetail: error -> use aid from bvid=" + s0.this.f6311b0);
                }
            }
            s0 s0Var5 = s0.this;
            if (s0Var5.f6311b0 > 0 && !s0Var5.f6315f0) {
                s0Var5.u0("loadDetail: error -> loadComments");
                s0.this.F0(1, true);
            }
            s0 s0Var6 = s0.this;
            if (str == null) {
                str = "获取详情失败";
            }
            Toast.makeText(s0Var6, str, 0).show();
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(BiliModels.VideoDetailResult videoDetailResult) {
            String str;
            BiliModels.VideoDetailData videoDetailData;
            if (videoDetailResult != null && videoDetailResult.code == 0 && (videoDetailData = videoDetailResult.data) != null) {
                s0.this.B0(videoDetailData);
                s0 s0Var = s0.this;
                BiliModels.VideoDetailData videoDetailData2 = videoDetailResult.data;
                s0Var.f6311b0 = videoDetailData2.aid;
                s0Var.f6312c0 = videoDetailData2.bvid;
                s0Var.f6313d0 = s0Var.n0(videoDetailData2);
                s0.this.u0("loadDetail: success aid=" + s0.this.f6311b0 + " bvid=" + s0.this.f6312c0 + " cid=" + s0.this.f6313d0);
                s0 s0Var2 = s0.this;
                s0Var2.f6314e0 = 0;
                s0Var2.f6316g0 = true;
                s0Var2.F0(1, true);
                return;
            }
            if (videoDetailResult == null) {
                s0.this.x0(false);
                s0 s0Var3 = s0.this;
                s0Var3.A0(true, s0Var3.getString(f0.j.M1));
                return;
            }
            s0.this.x0(false);
            s0 s0Var4 = s0.this;
            StringBuilder sb = new StringBuilder();
            sb.append("详情请求失败: code=");
            sb.append(videoDetailResult.code);
            sb.append(" msg=");
            String str2 = videoDetailResult.message;
            if (str2 == null) {
                str2 = "unknown";
            }
            sb.append(str2);
            sb.append(" bvid=");
            sb.append(s0.this.f6312c0);
            sb.append(" aid=");
            sb.append(s0.this.f6311b0);
            s0Var4.t0(sb.toString());
            s0.this.C0();
            s0 s0Var5 = s0.this;
            s0Var5.A0(true, s0Var5.getString(f0.j.M1));
            s0 s0Var6 = s0.this;
            if (s0Var6.f6311b0 <= 0 && (str = s0Var6.f6312c0) != null) {
                long jM0 = s0Var6.m0(str);
                if (jM0 > 0) {
                    s0 s0Var7 = s0.this;
                    s0Var7.f6311b0 = jM0;
                    s0Var7.u0("loadDetail: use aid from bvid=" + s0.this.f6311b0);
                }
            }
            s0 s0Var8 = s0.this;
            if (s0Var8.f6311b0 > 0 && !s0Var8.f6315f0) {
                s0Var8.u0("loadDetail: fallback -> loadComments");
                s0.this.F0(1, true);
            }
            s0 s0Var9 = s0.this;
            String str3 = videoDetailResult.message;
            if (str3 == null) {
                str3 = "获取详情失败";
            }
            Toast.makeText(s0Var9, str3, 0).show();
        }
    }

    public class b implements a.h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f6280a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f6281b;

        public b(int i2, boolean z2) {
            this.f6280a = i2;
            this.f6281b = z2;
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            s0.this.x0(false);
            s0.this.f6315f0 = false;
            if (str == null || str.length() <= 0) {
                return;
            }
            s0.this.t0("评论请求异常: " + str);
            if (this.f6281b) {
                s0 s0Var = s0.this;
                s0Var.A0(true, s0Var.getString(f0.j.M1));
            }
            Toast.makeText(s0.this, str, 0).show();
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(BiliModels.CommentResult commentResult) {
            BiliModels.CommentData commentData;
            boolean z2 = false;
            s0.this.x0(false);
            s0 s0Var = s0.this;
            s0Var.f6315f0 = false;
            if (commentResult == null || commentResult.code != 0 || (commentData = commentResult.data) == null) {
                if (commentResult != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("评论请求失败: code=");
                    sb.append(commentResult.code);
                    sb.append(" msg=");
                    String str = commentResult.message;
                    if (str == null) {
                        str = "unknown";
                    }
                    sb.append(str);
                    sb.append(" aid=");
                    sb.append(s0.this.f6311b0);
                    s0Var.t0(sb.toString());
                    if (this.f6281b) {
                        s0 s0Var2 = s0.this;
                        s0Var2.A0(true, s0Var2.getString(f0.j.M1));
                    }
                    s0 s0Var3 = s0.this;
                    String str2 = commentResult.message;
                    if (str2 == null) {
                        str2 = "获取评论失败";
                    }
                    Toast.makeText(s0Var3, str2, 0).show();
                    return;
                }
                return;
            }
            List listH0 = s0Var.h0(commentData, this.f6280a);
            C0383h0 c0383h0 = s0.this.f6308Y;
            if (c0383h0 != null) {
                if (this.f6281b) {
                    c0383h0.h(listH0);
                } else if (!listH0.isEmpty()) {
                    ArrayList arrayList = new ArrayList();
                    int count = s0.this.f6308Y.getCount();
                    for (int i2 = 0; i2 < count; i2++) {
                        Object item = s0.this.f6308Y.getItem(i2);
                        if (item instanceof BiliModels.CommentReply) {
                            arrayList.add((BiliModels.CommentReply) item);
                        }
                    }
                    arrayList.addAll(listH0);
                    s0.this.f6308Y.h(arrayList);
                }
            }
            s0 s0Var4 = s0.this;
            s0Var4.f6314e0 = this.f6280a;
            s0Var4.f6316g0 = s0Var4.q0(commentResult.data.page, listH0);
            s0 s0Var5 = s0.this;
            if (this.f6281b && listH0.isEmpty()) {
                z2 = true;
            }
            s0Var5.A0(z2, s0.this.getString(f0.j.M1));
            s0.this.u0("loadComments: success size=" + listH0.size() + " hasMore=" + s0.this.f6316g0);
        }
    }

    public class c implements a.h {
        public c() {
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            s0.this.x0(false);
            s0 s0Var = s0.this;
            s0Var.v0 = false;
            if (str == null) {
                str = "评论失败";
            }
            Toast.makeText(s0Var, str, 0).show();
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(BiliModels.SimpleResult simpleResult) {
            s0.this.x0(false);
            s0 s0Var = s0.this;
            s0Var.v0 = false;
            if (simpleResult == null || simpleResult.code != 0) {
                if (simpleResult == null) {
                    Toast.makeText(s0Var, "评论失败", 0).show();
                    return;
                } else {
                    String str = simpleResult.message;
                    Toast.makeText(s0Var, str != null ? str : "评论失败", 0).show();
                    return;
                }
            }
            Toast.makeText(s0Var, "评论已发送", 0).show();
            EditText editText = s0.this.f6284A;
            if (editText != null) {
                editText.setText("");
            }
            s0 s0Var2 = s0.this;
            s0Var2.r0(s0Var2.f6284A);
            s0.this.F0(1, true);
        }
    }

    public void B0(BiliModels.VideoDetailData videoDetailData) {
        if (videoDetailData == null) {
            return;
        }
        TextView textView = this.f6300Q;
        if (textView != null) {
            String str = videoDetailData.title;
            if (str == null) {
                str = "";
            }
            textView.setText(str);
        }
        String str2 = videoDetailData.title;
        if (str2 != null && str2.length() > 0) {
            this.f6324o0 = videoDetailData.title;
        }
        long j2 = videoDetailData.duration;
        if (j2 > 0) {
            this.q0 = j2;
        }
        if (this.f6307X != null) {
            String str3 = videoDetailData.desc;
            String str4 = str3 != null ? str3 : "";
            if (str4.length() == 0) {
                str4 = "暂无简介";
            }
            this.f6307X.setText(str4);
        }
        TextView textView2 = this.f6301R;
        if (textView2 != null) {
            textView2.setText(i0(videoDetailData));
        }
        D0(videoDetailData);
        E0(videoDetailData);
        if (this.f6288E != null) {
            String strI = com.im.oldchat.bili.c.i(videoDetailData.pic);
            if (strI == null || strI.length() <= 0) {
                String str5 = this.f6323n0;
                if (str5 == null || str5.length() <= 0) {
                    this.f6288E.setImageResource(R.drawable.ic_media_play);
                } else {
                    AbstractC0455v.e(this.f6288E, com.im.oldchat.bili.c.i(this.f6323n0));
                }
            } else {
                this.r0 = strI;
                AbstractC0455v.e(this.f6288E, strI);
            }
        }
        View view = this.f6290G;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    public void C0() {
        View view = this.f6296M;
        if (view != null) {
            view.setVisibility(8);
        }
        View view2 = this.f6302S;
        if (view2 != null) {
            view2.setVisibility(8);
        }
        TextView textView = this.f6300Q;
        if (textView != null && textView.getText() != null && this.f6300Q.getText().length() == 0) {
            this.f6300Q.setText("视频详情");
        }
        String str = this.f6324o0;
        if (str == null || str.length() == 0) {
            TextView textView2 = this.f6300Q;
            CharSequence text = textView2 != null ? textView2.getText() : null;
            if (text != null && text.length() > 0) {
                this.f6324o0 = text.toString();
            }
        }
        TextView textView3 = this.f6307X;
        if (textView3 != null && textView3.getText() != null && this.f6307X.getText().length() == 0) {
            this.f6307X.setText("暂无简介");
        }
        TextView textView4 = this.f6301R;
        if (textView4 != null && textView4.getText() != null && this.f6301R.getText().length() == 0) {
            this.f6301R.setText("暂无更多信息");
        }
        if (this.f6288E != null) {
            String str2 = this.f6323n0;
            if (str2 == null || str2.length() <= 0) {
                this.f6288E.setImageResource(R.drawable.ic_media_play);
            } else {
                this.r0 = com.im.oldchat.bili.c.i(this.f6323n0);
                AbstractC0455v.e(this.f6288E, com.im.oldchat.bili.c.i(this.f6323n0));
            }
        }
        View view3 = this.f6290G;
        if (view3 != null) {
            view3.setVisibility(0);
        }
    }

    public void D0(BiliModels.VideoDetailData videoDetailData) {
        BiliModels.VideoOwner videoOwner;
        View view = this.f6296M;
        if (view == null) {
            return;
        }
        if (videoDetailData == null || (videoOwner = videoDetailData.owner) == null) {
            view.setVisibility(8);
            return;
        }
        TextView textView = this.f6298O;
        if (textView != null) {
            String str = videoOwner.name;
            if (str == null) {
                str = "";
            }
            textView.setText(str);
        }
        TextView textView2 = this.f6299P;
        if (textView2 != null) {
            textView2.setText(getString(f0.j.Z1));
        }
        if (this.f6297N != null) {
            String strI = com.im.oldchat.bili.c.i(videoDetailData.owner.face);
            if (strI == null || strI.length() <= 0) {
                this.f6297N.setImageResource(f0.e.f4999T);
            } else {
                AbstractC0455v.h(this.f6297N, strI);
            }
        }
        this.f6296M.setVisibility(0);
    }

    public void E0(BiliModels.VideoDetailData videoDetailData) {
        View view = this.f6302S;
        if (view == null) {
            return;
        }
        if (videoDetailData == null || videoDetailData.stat == null) {
            view.setVisibility(8);
            return;
        }
        TextView textView = this.f6303T;
        if (textView != null) {
            textView.setText("播放 " + o0(videoDetailData.stat.view));
        }
        TextView textView2 = this.f6304U;
        if (textView2 != null) {
            textView2.setText("弹幕 " + o0(videoDetailData.stat.danmaku));
        }
        TextView textView3 = this.f6305V;
        if (textView3 != null) {
            textView3.setText("评论 " + o0(videoDetailData.stat.reply));
        }
        TextView textView4 = this.f6306W;
        if (textView4 != null) {
            textView4.setText("点赞 " + o0(videoDetailData.stat.like));
        }
        this.f6302S.setVisibility(0);
    }

    public void F0(int i2, boolean z2) {
        String str;
        if (this.f6311b0 <= 0 && (str = this.f6312c0) != null) {
            long jM0 = m0(str);
            if (jM0 > 0) {
                this.f6311b0 = jM0;
                u0("loadComments: computed aid=" + this.f6311b0 + " from bvid");
            }
        }
        if (this.f6311b0 <= 0) {
            x0(false);
            A0(true, getString(f0.j.M1));
            return;
        }
        if (this.f6315f0) {
            return;
        }
        this.f6315f0 = true;
        u0("loadComments: page=" + i2 + " clear=" + z2);
        String strC = h0.a.c(this);
        String strB = h0.a.b(this);
        if (h0.a.e(this)) {
            strB = "";
        }
        com.im.oldchat.bili.a.B(this.f6311b0, i2, strB, strC, new b(i2, z2));
    }

    public void G0() {
        String str = this.f6312c0;
        if ((str == null || str.length() == 0) && this.f6311b0 <= 0) {
            x0(false);
            C0();
            A0(true, getString(f0.j.M1));
            u0("loadDetail: skip (no aid/bvid), use preload");
            return;
        }
        x0(true);
        u0("loadDetail: start");
        com.im.oldchat.bili.a.F(this.f6312c0, this.f6311b0, h0.a.c(this), new a());
    }

    public void H0(String str) {
        if (this.v0) {
            return;
        }
        if (this.f6311b0 <= 0) {
            Toast.makeText(this, "视频信息不完整", 0).show();
            return;
        }
        String strC = h0.a.c(this);
        if (strC == null || strC.length() == 0 || strC.indexOf("bili_jct=") < 0) {
            Toast.makeText(this, getString(f0.j.X1), 0).show();
            return;
        }
        String strB = h0.a.b(this);
        if (h0.a.e(this)) {
            strB = "";
        }
        this.v0 = true;
        x0(true);
        u0("sendComment: start");
        com.im.oldchat.bili.a.A(this.f6311b0, str, 0L, 0L, strB, strC, new c());
    }
}
