package l0;

import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;
import com.im.oldchat.bili.BiliModels;
import com.im.oldchat.bili.a;
import java.util.ArrayList;
import java.util.List;
import l0.C0383h0;
import o.c;

/* JADX INFO: loaded from: classes.dex */
public abstract class x0 extends w0 implements C0383h0.d {
    public boolean G0 = false;

    public class a implements a.h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ BiliModels.CommentReply f6382a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f6383b;

        public a(BiliModels.CommentReply commentReply, boolean z2) {
            this.f6382a = commentReply;
            this.f6383b = z2;
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            x0.this.G0 = false;
            x0 x0Var = x0.this;
            if (str == null) {
                str = "评论点赞失败";
            }
            Toast.makeText(x0Var, str, 0).show();
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(BiliModels.SimpleResult simpleResult) {
            String str;
            x0.this.G0 = false;
            if (simpleResult == null || simpleResult.code != 0) {
                if (simpleResult == null || (str = simpleResult.message) == null) {
                    str = "评论点赞失败";
                }
                Toast.makeText(x0.this, str, 0).show();
                return;
            }
            BiliModels.CommentReply commentReply = this.f6382a;
            boolean z2 = this.f6383b;
            commentReply.likedByMe = z2;
            if (z2) {
                commentReply.like++;
            } else {
                int i2 = commentReply.like;
                if (i2 > 0) {
                    commentReply.like = i2 - 1;
                }
            }
            C0383h0 c0383h0 = x0.this.f6308Y;
            if (c0383h0 != null) {
                c0383h0.notifyDataSetChanged();
            }
        }
    }

    public class b implements DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f6385a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ BiliModels.CommentReply f6386b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f6387c;

        public b(EditText editText, BiliModels.CommentReply commentReply, String str) {
            this.f6385a = editText;
            this.f6386b = commentReply;
            this.f6387c = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String strTrim = this.f6385a.getText() != null ? this.f6385a.getText().toString().trim() : "";
            if (strTrim.length() == 0) {
                Toast.makeText(x0.this, "请输入回复内容", 0).show();
            } else {
                x0.this.J1(this.f6386b, strTrim, this.f6387c);
            }
        }
    }

    public class c implements a.h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ BiliModels.CommentReply f6389a;

        public c(BiliModels.CommentReply commentReply) {
            this.f6389a = commentReply;
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            x0 x0Var = x0.this;
            if (str == null) {
                str = "获取回复失败";
            }
            Toast.makeText(x0Var, str, 0).show();
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(BiliModels.CommentResult commentResult) {
            String str;
            BiliModels.CommentData commentData;
            List<BiliModels.CommentReply> list;
            if (commentResult == null || commentResult.code != 0 || (commentData = commentResult.data) == null || (list = commentData.replies) == null) {
                if (commentResult == null || (str = commentResult.message) == null) {
                    str = "获取回复失败";
                }
                Toast.makeText(x0.this, str, 0).show();
                return;
            }
            BiliModels.CommentReply commentReply = this.f6389a;
            commentReply.replies = list;
            if (commentReply.rcount <= 0) {
                commentReply.rcount = list.size();
            }
            this.f6389a.showAllReplies = true;
            C0383h0 c0383h0 = x0.this.f6308Y;
            if (c0383h0 != null) {
                c0383h0.notifyDataSetChanged();
            }
        }
    }

    public class d implements a.h {
        public d() {
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            x0.this.x0(false);
            x0 x0Var = x0.this;
            x0Var.v0 = false;
            if (str == null) {
                str = "回复失败";
            }
            Toast.makeText(x0Var, str, 0).show();
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(BiliModels.SimpleResult simpleResult) {
            String str;
            x0.this.x0(false);
            x0 x0Var = x0.this;
            x0Var.v0 = false;
            if (simpleResult != null && simpleResult.code == 0) {
                Toast.makeText(x0Var, "回复已发送", 0).show();
                x0.this.F0(1, true);
            } else {
                if (simpleResult == null || (str = simpleResult.message) == null) {
                    str = "回复失败";
                }
                Toast.makeText(x0Var, str, 0).show();
            }
        }
    }

    public void I1() {
        C0383h0 c0383h0 = this.f6308Y;
        if (c0383h0 != null) {
            c0383h0.g(this);
        }
    }

    public final void J1(BiliModels.CommentReply commentReply, String str, String str2) {
        if (this.v0) {
            return;
        }
        if (this.f6311b0 <= 0) {
            Toast.makeText(this, "视频信息不完整", 0).show();
            return;
        }
        String strB = h0.a.b(this);
        if (h0.a.e(this)) {
            strB = "";
        }
        String str3 = strB;
        long j2 = commentReply.root;
        if (j2 <= 0) {
            j2 = commentReply.rpid;
        }
        long j3 = j2;
        long j4 = commentReply.rpid;
        this.v0 = true;
        x0(true);
        com.im.oldchat.bili.a.A(this.f6311b0, str, j3, j4, str3, str2, new d());
    }

    public void b(BiliModels.CommentReply commentReply) {
        String str;
        if (commentReply == null) {
            return;
        }
        String strC = h0.a.c(this);
        if (strC == null || strC.length() == 0 || strC.indexOf("bili_jct=") < 0) {
            Toast.makeText(this, getString(f0.j.X1), 0).show();
            return;
        }
        EditText editText = new EditText(this);
        editText.setSingleLine(false);
        editText.setMinLines(2);
        BiliModels.CommentMember commentMember = commentReply.member;
        String str2 = commentMember != null ? commentMember.uname : "";
        if (str2 == null || str2.length() <= 0) {
            str = "输入回复内容";
        } else {
            str = "回复 @" + str2;
        }
        editText.setHint(str);
        new c.a(this).t("发表回复").u(editText).k("取消", null).q("发送", new b(editText, commentReply, strC)).v();
    }

    public void d(BiliModels.CommentReply commentReply) {
        if (commentReply == null || commentReply.rpid <= 0 || this.f6311b0 <= 0) {
            return;
        }
        if (commentReply.showAllReplies) {
            commentReply.showAllReplies = false;
            C0383h0 c0383h0 = this.f6308Y;
            if (c0383h0 != null) {
                c0383h0.notifyDataSetChanged();
                return;
            }
            return;
        }
        List arrayList = commentReply.replies;
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        int size = commentReply.rcount;
        if (size <= 0) {
            size = arrayList.size();
        }
        if (!arrayList.isEmpty() && arrayList.size() >= Math.min(size, 20)) {
            commentReply.showAllReplies = true;
            C0383h0 c0383h02 = this.f6308Y;
            if (c0383h02 != null) {
                c0383h02.notifyDataSetChanged();
                return;
            }
            return;
        }
        String strC = h0.a.c(this);
        String strB = h0.a.b(this);
        if (h0.a.e(this)) {
            strB = "";
        }
        com.im.oldchat.bili.b.p(this.f6311b0, commentReply.rpid, 1, 20, strB, strC, new c(commentReply));
    }

    public void n(BiliModels.CommentReply commentReply, boolean z2) {
        if (commentReply == null || commentReply.rpid <= 0 || this.f6311b0 <= 0 || this.G0) {
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
        this.G0 = true;
        com.im.oldchat.bili.b.q(this.f6311b0, commentReply.rpid, z2, strB, strC, new a(commentReply, z2));
    }
}
