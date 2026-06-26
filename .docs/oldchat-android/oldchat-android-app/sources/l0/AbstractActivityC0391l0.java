package l0;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.im.oldchat.bili.BiliModels;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import k.AbstractC0285k0;

/* JADX INFO: renamed from: l0.l0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractActivityC0391l0 extends f0.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public TextView f6171A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public TextView f6172B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public TextView f6173C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public TextView f6174D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public TextView f6175E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public TextView f6176F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public TextView f6177G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public TextView f6178H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public ImageView f6179I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public ListView f6180J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public WebView f6181K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public long f6182L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public String f6183M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public String f6184N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public String f6185O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public boolean f6186P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public boolean f6187Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public boolean f6188R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public boolean f6189S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public boolean f6190T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public boolean f6191U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public boolean f6192V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public boolean f6193W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public int f6194X = 0;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public boolean f6195Y = true;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public final List f6196Z = new ArrayList();

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public com.im.oldchat.ui.Z f6197a0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ProgressBar f6198w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f6199x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f6200y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public TextView f6201z;

    /* JADX INFO: renamed from: l0.l0$a */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0391l0.this.finish();
        }
    }

    /* JADX INFO: renamed from: l0.l0$b */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0391l0.this.j0();
        }
    }

    /* JADX INFO: renamed from: l0.l0$c */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0391l0.this.r0(false);
        }
    }

    /* JADX INFO: renamed from: l0.l0$d */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AbstractActivityC0391l0.this.r0(true);
        }
    }

    /* JADX INFO: renamed from: l0.l0$e */
    public class e implements AdapterView.OnItemClickListener {
        public e() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            com.im.oldchat.ui.Z z2 = AbstractActivityC0391l0.this.f6197a0;
            Object item = z2 != null ? z2.getItem(i2) : null;
            if (item instanceof BiliModels.RecommendItem) {
                AbstractC0395n0.c(AbstractActivityC0391l0.this, (BiliModels.RecommendItem) item);
            }
        }
    }

    public void f0() {
        View viewA0 = a0(f0.f.C2);
        if (viewA0 != null) {
            viewA0.setOnClickListener(new a());
        }
        TextView textView = this.f6176F;
        if (textView != null) {
            textView.setOnClickListener(new b());
        }
        TextView textView2 = this.f6177G;
        if (textView2 != null) {
            textView2.setOnClickListener(new c());
        }
        TextView textView3 = this.f6178H;
        if (textView3 != null) {
            textView3.setOnClickListener(new d());
        }
    }

    public void g0() {
        this.f6198w = (ProgressBar) a0(f0.f.y7);
        this.f6199x = (TextView) a0(f0.f.mc);
        this.f6200y = (TextView) a0(f0.f.hc);
        this.f6201z = (TextView) a0(f0.f.kc);
        this.f6171A = (TextView) a0(f0.f.nc);
        this.f6172B = (TextView) a0(f0.f.lc);
        this.f6173C = (TextView) a0(f0.f.ic);
        this.f6174D = (TextView) a0(f0.f.jc);
        this.f6175E = (TextView) a0(f0.f.gc);
        this.f6176F = (TextView) a0(f0.f.D2);
        this.f6177G = (TextView) a0(f0.f.M8);
        this.f6178H = (TextView) a0(f0.f.L8);
        this.f6179I = (ImageView) a0(f0.f.U5);
        this.f6180J = (ListView) a0(f0.f.Z6);
        this.f6181K = (WebView) a0(f0.f.De);
    }

    public String h0(int i2) {
        return i2 >= 10000 ? String.format(Locale.US, "%.1f万", Float.valueOf(i2 / 10000.0f)) : String.valueOf(Math.max(0, i2));
    }

    public abstract void i0(int i2, boolean z2);

    public abstract void j0();

    public abstract void k0();

    public void l0() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        this.f6182L = intent.getLongExtra("up_mid", 0L);
        this.f6183M = intent.getStringExtra("up_name");
    }

    public void m0() {
        TextView textView = this.f6199x;
        if (textView != null) {
            textView.setText(o0());
        }
        TextView textView2 = this.f6201z;
        if (textView2 != null) {
            textView2.setText(o0());
        }
        TextView textView3 = this.f6171A;
        if (textView3 != null) {
            textView3.setText(getString(f0.j.r2, String.valueOf(this.f6182L)));
        }
    }

    public final void n0() {
        TextView textView = this.f6177G;
        if (textView != null) {
            textView.setBackgroundResource(this.f6193W ? f0.e.f5035v : f0.e.f5034u);
            this.f6177G.setTextColor(getResources().getColor(this.f6193W ? f0.d.f4974k : f0.d.f4970g));
        }
        TextView textView2 = this.f6178H;
        if (textView2 != null) {
            textView2.setBackgroundResource(this.f6193W ? f0.e.f5034u : f0.e.f5035v);
            this.f6178H.setTextColor(getResources().getColor(this.f6193W ? f0.d.f4970g : f0.d.f4974k));
        }
    }

    public String o0() {
        String str = this.f6183M;
        return (str == null || str.length() <= 0) ? getString(f0.j.p2) : this.f6183M;
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onBackPressed() {
        WebView webView;
        if (this.f6193W && (webView = this.f6181K) != null && webView.canGoBack()) {
            this.f6181K.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override // o.d, c.AbstractActivityC0167u, android.app.Activity
    public void onDestroy() {
        WebView webView = this.f6181K;
        if (webView != null) {
            try {
                webView.stopLoading();
                this.f6181K.setWebChromeClient(null);
                this.f6181K.setWebViewClient(null);
                this.f6181K.removeAllViews();
                this.f6181K.destroy();
            } catch (Throwable unused) {
            }
            this.f6181K = null;
        }
        super.onDestroy();
    }

    public void p0(boolean z2) {
        this.f6187Q = z2;
        t0();
    }

    public void q0() {
        if (this.f6180J == null) {
            return;
        }
        com.im.oldchat.ui.Z z2 = new com.im.oldchat.ui.Z(this);
        this.f6197a0 = z2;
        this.f6180J.setAdapter((ListAdapter) z2);
        this.f6180J.setOnItemClickListener(new e());
        this.f6180J.setOnScrollListener(new f());
    }

    public void r0(boolean z2) {
        this.f6193W = z2;
        n0();
        ListView listView = this.f6180J;
        if (listView != null) {
            listView.setVisibility(z2 ? 8 : 0);
        }
        WebView webView = this.f6181K;
        if (webView != null) {
            webView.setVisibility(z2 ? 0 : 8);
        }
        if (z2 && !this.f6191U) {
            k0();
            this.f6191U = true;
        }
        s0();
    }

    public void s0() {
        TextView textView = this.f6200y;
        if (textView == null) {
            return;
        }
        if (this.f6193W) {
            if (!this.f6192V) {
                textView.setVisibility(8);
                return;
            } else {
                textView.setText(getString(f0.j.o2));
                this.f6200y.setVisibility(0);
                return;
            }
        }
        if (this.f6189S || !this.f6196Z.isEmpty()) {
            this.f6200y.setVisibility(8);
        } else {
            this.f6200y.setText("这个 UP 暂时还没有投稿");
            this.f6200y.setVisibility(0);
        }
    }

    public void t0() {
        TextView textView = this.f6176F;
        if (textView == null) {
            return;
        }
        if (this.f6187Q) {
            textView.setText(getString(f0.j.n2));
            this.f6176F.setEnabled(false);
            AbstractC0285k0.K(this.f6176F, 0.7f);
        } else {
            textView.setText(getString(this.f6186P ? f0.j.m2 : f0.j.k2));
            this.f6176F.setEnabled(true);
            AbstractC0285k0.K(this.f6176F, 1.0f);
        }
    }

    public void u0() {
        ProgressBar progressBar = this.f6198w;
        if (progressBar != null) {
            progressBar.setVisibility(this.f6188R || this.f6189S || this.f6190T ? 0 : 8);
        }
    }

    /* JADX INFO: renamed from: l0.l0$f */
    public class f implements AbsListView.OnScrollListener {
        public f() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            AbstractActivityC0391l0 abstractActivityC0391l0 = AbstractActivityC0391l0.this;
            if (abstractActivityC0391l0.f6193W || abstractActivityC0391l0.f6189S || !abstractActivityC0391l0.f6195Y || i4 <= 0 || i2 + i3 < i4 - 2) {
                return;
            }
            abstractActivityC0391l0.i0(abstractActivityC0391l0.f6194X + 1, false);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
        }
    }
}
