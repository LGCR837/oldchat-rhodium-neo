package com.im.oldchat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;
import com.im.oldchat.bili.BiliModels;
import l0.C0383h0;
import l0.C0397o0;
import l0.x0;
import org.spongycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes.dex */
public class OldViewVideoDetailActivity extends x0 {
    public C0397o0 H0;
    public long I0;
    public String J0;

    public class a implements AdapterView.OnItemClickListener {
        public a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            if (OldViewVideoDetailActivity.this.A0) {
                return;
            }
            Object itemAtPosition = adapterView.getItemAtPosition(i2);
            if (itemAtPosition instanceof BiliModels.RecommendItem) {
                OldViewVideoDetailActivity.this.o1((BiliModels.RecommendItem) itemAtPosition);
            }
        }
    }

    public class b implements AbsListView.OnScrollListener {
        public b() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            int i5;
            OldViewVideoDetailActivity.this.a1();
            OldViewVideoDetailActivity oldViewVideoDetailActivity = OldViewVideoDetailActivity.this;
            if (oldViewVideoDetailActivity.A0 && oldViewVideoDetailActivity.f6316g0 && !oldViewVideoDetailActivity.f6315f0 && (i5 = oldViewVideoDetailActivity.f6314e0) > 0 && i4 > 0 && i2 + i3 >= i4 - 2) {
                oldViewVideoDetailActivity.F0(i5 + 1, false);
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent(OldViewVideoDetailActivity.this, (Class<?>) OldViewUpProfileActivity.class);
            intent.putExtra("up_mid", OldViewVideoDetailActivity.this.I0);
            intent.putExtra("up_name", OldViewVideoDetailActivity.this.J0);
            OldViewVideoDetailActivity.this.startActivity(intent);
        }
    }

    @Override // l0.s0
    public void B0(BiliModels.VideoDetailData videoDetailData) {
        super.B0(videoDetailData);
        M1(videoDetailData);
        B1();
        n1(false);
    }

    @Override // l0.s0
    public void E0(BiliModels.VideoDetailData videoDetailData) {
        super.E0(videoDetailData);
        if (videoDetailData != null) {
            long j2 = videoDetailData.aid;
            if (j2 > 0) {
                this.f6311b0 = j2;
            }
            String str = videoDetailData.bvid;
            if (str != null && str.trim().length() > 0) {
                this.f6312c0 = videoDetailData.bvid.trim();
            }
            BiliModels.VideoStat videoStat = videoDetailData.stat;
            int i2 = videoStat != null ? videoStat.like : -1;
            C0397o0 c0397o0 = this.H0;
            if (c0397o0 != null) {
                c0397o0.s(this.f6311b0, this.f6312c0, i2);
            }
        }
    }

    public final void M1(BiliModels.VideoDetailData videoDetailData) {
        BiliModels.VideoOwner videoOwner;
        this.I0 = 0L;
        this.J0 = "";
        if (videoDetailData != null && (videoOwner = videoDetailData.owner) != null) {
            this.I0 = videoOwner.mid;
            String str = videoOwner.name;
            this.J0 = str != null ? str : "";
        }
        View view = this.f6296M;
        if (view == null) {
            return;
        }
        if (this.I0 <= 0) {
            view.setOnClickListener(null);
            this.f6296M.setClickable(false);
            ImageView imageView = this.f6297N;
            if (imageView != null) {
                imageView.setOnClickListener(null);
            }
            TextView textView = this.f6298O;
            if (textView != null) {
                textView.setOnClickListener(null);
            }
            TextView textView2 = this.f6299P;
            if (textView2 != null) {
                textView2.setOnClickListener(null);
                return;
            }
            return;
        }
        c cVar = new c();
        this.f6296M.setClickable(true);
        this.f6296M.setOnClickListener(cVar);
        ImageView imageView2 = this.f6297N;
        if (imageView2 != null) {
            imageView2.setOnClickListener(cVar);
        }
        TextView textView3 = this.f6298O;
        if (textView3 != null) {
            textView3.setOnClickListener(cVar);
        }
        TextView textView4 = this.f6299P;
        if (textView4 != null) {
            textView4.setOnClickListener(cVar);
        }
    }

    public void N1() {
        this.f6325w = (ProgressBar) a0(f0.f.u7);
        this.f6326x = (ListView) a0(f0.f.X6);
        this.f6327y = (TextView) a0(f0.f.Lb);
        this.f6328z = a0(f0.f.p2);
        this.f6284A = (EditText) a0(f0.f.L4);
        this.f6285B = a0(f0.f.n2);
        this.f6310a0 = a0(f0.f.B6);
        View view = this.f6328z;
        if (view instanceof ImageView) {
            ((ImageView) view).setColorFilter(getResources().getColor(f0.d.f4973j));
        }
        this.s0 = getSharedPreferences("auth", 0).getString("access_token", "");
    }

    public void O1() {
        String str;
        TextView textView;
        Intent intent = getIntent();
        if (intent != null) {
            this.f6312c0 = intent.getStringExtra("bvid");
            this.f6311b0 = intent.getLongExtra("aid", 0L);
            this.f6313d0 = intent.getLongExtra("cid", 0L);
            this.f6322m0 = intent.getStringExtra("preload_url");
            this.f6323n0 = intent.getStringExtra("cover_url");
            String stringExtra = intent.getStringExtra(MessageBundle.TITLE_ENTRY);
            if (stringExtra != null && stringExtra.length() > 0 && (textView = this.f6300Q) != null) {
                textView.setText(stringExtra);
                this.f6324o0 = stringExtra;
            }
        }
        if (!s0(this.f6312c0)) {
            this.f6312c0 = null;
        }
        if (this.f6311b0 <= 0 && (str = this.f6312c0) != null) {
            long jM0 = m0(str);
            if (jM0 > 0) {
                this.f6311b0 = jM0;
                u0("intent: computed aid=" + this.f6311b0 + " from bvid");
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("intent: bvid=");
        String str2 = this.f6312c0;
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        sb.append(" aid=");
        sb.append(this.f6311b0);
        sb.append(" cid=");
        sb.append(this.f6313d0);
        sb.append(" preload=");
        String str3 = this.f6322m0;
        sb.append(str3 != null && str3.length() > 0);
        u0(sb.toString());
        C0397o0 c0397o0 = this.H0;
        if (c0397o0 != null) {
            c0397o0.s(this.f6311b0, this.f6312c0, -1);
        }
    }

    public void P1() {
        if (this.f6326x != null) {
            View viewInflate = getLayoutInflater().inflate(f0.g.d1, (ViewGroup) this.f6326x, false);
            this.f6286C = viewInflate;
            this.f6326x.addHeaderView(viewInflate, null, false);
        }
        View view = this.f6286C;
        if (view == null) {
            return;
        }
        this.f6287D = view.findViewById(f0.f.E6);
        this.f6288E = (ImageView) this.f6286C.findViewById(f0.f.R5);
        this.f6289F = (VideoView) this.f6286C.findViewById(f0.f.Ce);
        this.f6290G = this.f6286C.findViewById(f0.f.u2);
        this.f6291H = this.f6286C.findViewById(f0.f.r2);
        this.f6292I = this.f6286C.findViewById(f0.f.z2);
        this.f6293J = this.f6286C.findViewById(f0.f.F6);
        this.f6294K = (ProgressBar) this.f6286C.findViewById(f0.f.x7);
        this.f6295L = (TextView) this.f6286C.findViewById(f0.f.cc);
        this.f6296M = this.f6286C.findViewById(f0.f.D6);
        this.f6297N = (ImageView) this.f6286C.findViewById(f0.f.S5);
        this.f6298O = (TextView) this.f6286C.findViewById(f0.f.Nb);
        this.f6299P = (TextView) this.f6286C.findViewById(f0.f.Ob);
        this.f6300Q = (TextView) this.f6286C.findViewById(f0.f.Tb);
        this.f6301R = (TextView) this.f6286C.findViewById(f0.f.Mb);
        this.f6302S = this.f6286C.findViewById(f0.f.G6);
        this.f6303T = (TextView) this.f6286C.findViewById(f0.f.Rb);
        this.f6304U = (TextView) this.f6286C.findViewById(f0.f.Pb);
        this.f6305V = (TextView) this.f6286C.findViewById(f0.f.Sb);
        this.f6306W = (TextView) this.f6286C.findViewById(f0.f.Qb);
        this.f6307X = (TextView) this.f6286C.findViewById(f0.f.Kb);
        C0397o0 c0397o0 = this.H0;
        if (c0397o0 != null) {
            c0397o0.p(this.f6286C);
        }
        x1(this.f6286C);
        this.f6308Y = new C0383h0(this);
        I1();
        Z z2 = new Z(this);
        this.f6309Z = z2;
        ListView listView = this.f6326x;
        if (listView != null) {
            listView.setAdapter((ListAdapter) z2);
            this.f6326x.setOnItemClickListener(new a());
            this.f6326x.setOnScrollListener(new b());
        }
    }

    @Override // l0.x0, l0.C0383h0.d
    public /* bridge */ /* synthetic */ void b(BiliModels.CommentReply commentReply) {
        super.b(commentReply);
    }

    @Override // l0.x0, l0.C0383h0.d
    public /* bridge */ /* synthetic */ void d(BiliModels.CommentReply commentReply) {
        super.d(commentReply);
    }

    @Override // l0.x0, l0.C0383h0.d
    public /* bridge */ /* synthetic */ void n(BiliModels.CommentReply commentReply, boolean z2) {
        super.n(commentReply, z2);
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 9101) {
            this.f6321l0 = false;
            int intExtra = intent != null ? intent.getIntExtra("result_position", 0) : 0;
            if (intent != null && intent.getBooleanExtra("completed", false)) {
                Z0(true);
                return;
            }
            if (intExtra > 0) {
                this.f6320k0 = intExtra;
            }
            String str = this.p0;
            if (str == null || str.length() <= 0) {
                return;
            }
            this.f6319j0 = true;
            this.f6317h0 = false;
            this.f6318i0 = true;
            U0(this.p0);
        }
    }

    @Override // f0.a, o.d, c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(f0.g.f5118L);
        u0("onCreate");
        N1();
        this.H0 = new C0397o0(this);
        P1();
        F1();
        O1();
        G0();
        n1(true);
    }

    @Override // o.d, c.AbstractActivityC0167u, android.app.Activity
    public void onDestroy() {
        Z0(false);
        super.onDestroy();
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.f6321l0) {
            S0();
        } else {
            Z0(true);
        }
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.f6321l0) {
            this.f6321l0 = false;
        }
        C0397o0 c0397o0 = this.H0;
        if (c0397o0 != null) {
            c0397o0.t(true);
        }
    }
}
