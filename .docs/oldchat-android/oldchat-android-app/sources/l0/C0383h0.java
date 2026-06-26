package l0;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.im.oldchat.bili.BiliModels;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import o0.AbstractC0455v;

/* JADX INFO: renamed from: l0.h0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0383h0 extends BaseAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LayoutInflater f6057a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f6058b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final SimpleDateFormat f6059c = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d f6060d;

    /* JADX INFO: renamed from: l0.h0$a */
    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ BiliModels.CommentReply f6061a;

        public a(BiliModels.CommentReply commentReply) {
            this.f6061a = commentReply;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C0383h0.this.f6060d != null) {
                C0383h0.this.f6060d.n(this.f6061a, !r0.likedByMe);
            }
        }
    }

    /* JADX INFO: renamed from: l0.h0$b */
    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ BiliModels.CommentReply f6063a;

        public b(BiliModels.CommentReply commentReply) {
            this.f6063a = commentReply;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C0383h0.this.f6060d != null) {
                C0383h0.this.f6060d.b(this.f6063a);
            }
        }
    }

    /* JADX INFO: renamed from: l0.h0$c */
    public class c implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ BiliModels.CommentReply f6065a;

        public c(BiliModels.CommentReply commentReply) {
            this.f6065a = commentReply;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (C0383h0.this.f6060d != null) {
                C0383h0.this.f6060d.d(this.f6065a);
            }
        }
    }

    /* JADX INFO: renamed from: l0.h0$d */
    public interface d {
        void b(BiliModels.CommentReply commentReply);

        void d(BiliModels.CommentReply commentReply);

        void n(BiliModels.CommentReply commentReply, boolean z2);
    }

    /* JADX INFO: renamed from: l0.h0$e */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f6067a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f6068b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f6069c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public TextView f6070d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public TextView f6071e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public TextView f6072f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public TextView f6073g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public TextView f6074h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public TextView f6075i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public TextView f6076j;

        public e() {
        }

        public /* synthetic */ e(a aVar) {
            this();
        }
    }

    public C0383h0(Context context) {
        this.f6057a = LayoutInflater.from(context);
    }

    public final void b(e eVar, BiliModels.CommentReply commentReply) {
        if (commentReply == null) {
            return;
        }
        BiliModels.CommentMember commentMember = commentReply.member;
        String str = commentMember != null ? commentMember.uname : "";
        BiliModels.CommentContent commentContent = commentReply.content;
        String str2 = commentContent != null ? commentContent.message : "";
        TextView textView = eVar.f6068b;
        if (str == null) {
            str = "";
        }
        textView.setText(str);
        TextView textView2 = eVar.f6070d;
        if (str2 == null) {
            str2 = "";
        }
        textView2.setText(str2);
        eVar.f6071e.setText(f(commentReply.ctime));
        String str3 = commentReply.topComment ? "置顶" : commentReply.hotComment ? "推荐" : "";
        if (str3.length() > 0) {
            eVar.f6069c.setVisibility(0);
            eVar.f6069c.setText(str3);
        } else {
            eVar.f6069c.setVisibility(8);
            eVar.f6069c.setText("");
        }
        int i2 = commentReply.like;
        if (i2 <= 0) {
            i2 = 0;
        }
        TextView textView3 = eVar.f6072f;
        StringBuilder sb = new StringBuilder();
        sb.append(commentReply.likedByMe ? "已赞 " : "点赞 ");
        sb.append(i2);
        textView3.setText(sb.toString());
        TextView textView4 = eVar.f6072f;
        textView4.setTextColor(commentReply.likedByMe ? o0.X.i(textView4.getContext()) : textView4.getResources().getColor(f0.d.f4973j));
        eVar.f6073g.setText("回复");
        if (commentReply.rcount > 0) {
            eVar.f6074h.setVisibility(0);
            eVar.f6074h.setText("回复 " + commentReply.rcount);
        } else {
            eVar.f6074h.setVisibility(8);
            eVar.f6074h.setText("");
        }
        eVar.f6072f.setOnClickListener(new a(commentReply));
        eVar.f6073g.setOnClickListener(new b(commentReply));
        c(eVar.f6075i, eVar.f6076j, commentReply);
        BiliModels.CommentMember commentMember2 = commentReply.member;
        String strI = com.im.oldchat.bili.c.i(commentMember2 != null ? commentMember2.avatar : null);
        if (strI == null || strI.length() <= 0) {
            eVar.f6067a.setImageResource(f0.e.f4999T);
        } else {
            AbstractC0455v.h(eVar.f6067a, strI);
        }
    }

    public final void c(TextView textView, TextView textView2, BiliModels.CommentReply commentReply) {
        if (textView == null || textView2 == null || commentReply == null) {
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
        if (size <= 0) {
            textView.setVisibility(8);
            textView.setText("");
            textView.setOnClickListener(null);
            textView2.setVisibility(8);
            textView2.setText("");
            textView2.setOnClickListener(null);
            return;
        }
        int size2 = commentReply.showAllReplies ? arrayList.size() : Math.min(2, arrayList.size());
        SpannableStringBuilder spannableStringBuilderD = d(textView, arrayList, size2);
        if (spannableStringBuilderD.length() == 0) {
            spannableStringBuilderD.append("有 ").append((CharSequence) String.valueOf(size)).append(" 条回复");
        }
        textView.setText(spannableStringBuilderD);
        textView.setVisibility(0);
        textView.setOnClickListener(null);
        String strE = e(commentReply, size, arrayList.size(), size2);
        if (TextUtils.isEmpty(strE)) {
            textView2.setVisibility(8);
            textView2.setText("");
            textView2.setOnClickListener(null);
        } else {
            textView2.setVisibility(0);
            textView2.setText(strE);
            textView2.setOnClickListener(new c(commentReply));
        }
    }

    public final SpannableStringBuilder d(TextView textView, List list, int i2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        int iMin = Math.min(i2, list.size());
        int i3 = o0.X.i(textView.getContext());
        for (int i4 = 0; i4 < iMin; i4++) {
            BiliModels.CommentReply commentReply = (BiliModels.CommentReply) list.get(i4);
            if (commentReply != null) {
                if (spannableStringBuilder.length() > 0) {
                    spannableStringBuilder.append('\n');
                }
                spannableStringBuilder.append((CharSequence) "- ");
                BiliModels.CommentMember commentMember = commentReply.member;
                String str = commentMember != null ? commentMember.uname : "";
                if (TextUtils.isEmpty(str)) {
                    str = "匿名用户";
                }
                int length = spannableStringBuilder.length();
                spannableStringBuilder.append((CharSequence) str);
                if (commentReply.like > 0) {
                    spannableStringBuilder.append((CharSequence) " (").append((CharSequence) String.valueOf(commentReply.like)).append((CharSequence) ")");
                }
                int length2 = spannableStringBuilder.length();
                spannableStringBuilder.setSpan(new ForegroundColorSpan(i3), length, length2, 33);
                spannableStringBuilder.setSpan(new StyleSpan(1), length, length2, 33);
                BiliModels.CommentContent commentContent = commentReply.content;
                String str2 = commentContent != null ? commentContent.message : "";
                if (!TextUtils.isEmpty(str2)) {
                    spannableStringBuilder.append((CharSequence) ": ").append((CharSequence) str2);
                }
            }
        }
        return spannableStringBuilder;
    }

    public final String e(BiliModels.CommentReply commentReply, int i2, int i3, int i4) {
        boolean z2 = commentReply.rcount > 0 && i2 > i3;
        boolean z3 = commentReply.showAllReplies;
        if (!z3 && (i2 > i4 || z2)) {
            return "展开全部 " + i2 + " 条回复";
        }
        if (z3 && i2 > 2) {
            return "收起回复";
        }
        if (i3 != 0 || i2 <= 0) {
            return null;
        }
        return "查看 " + i2 + " 条回复";
    }

    public final String f(long j2) {
        if (j2 <= 0) {
            return "";
        }
        try {
            return this.f6059c.format(new Date(j2 * 1000));
        } catch (Exception unused) {
            return "";
        }
    }

    public void g(d dVar) {
        this.f6060d = dVar;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f6058b.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        if (i2 < 0 || i2 >= this.f6058b.size()) {
            return null;
        }
        return this.f6058b.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        e eVar;
        if (view == null) {
            view = this.f6057a.inflate(f0.g.M0, viewGroup, false);
            eVar = new e(null);
            eVar.f6067a = (ImageView) view.findViewById(f0.f.P5);
            eVar.f6068b = (TextView) view.findViewById(f0.f.Db);
            eVar.f6069c = (TextView) view.findViewById(f0.f.Ib);
            eVar.f6070d = (TextView) view.findViewById(f0.f.Bb);
            eVar.f6071e = (TextView) view.findViewById(f0.f.Jb);
            eVar.f6072f = (TextView) view.findViewById(f0.f.Cb);
            eVar.f6073g = (TextView) view.findViewById(f0.f.Fb);
            eVar.f6074h = (TextView) view.findViewById(f0.f.Gb);
            eVar.f6075i = (TextView) view.findViewById(f0.f.Eb);
            eVar.f6076j = (TextView) view.findViewById(f0.f.Hb);
            view.setTag(eVar);
        } else {
            eVar = (e) view.getTag();
        }
        b(eVar, (BiliModels.CommentReply) this.f6058b.get(i2));
        return view;
    }

    public void h(List list) {
        this.f6058b.clear();
        if (list != null) {
            this.f6058b.addAll(list);
        }
        notifyDataSetChanged();
    }
}
