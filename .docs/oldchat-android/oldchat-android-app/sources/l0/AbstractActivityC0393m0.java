package l0;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;
import com.im.oldchat.bili.a;
import com.im.oldchat.bili.e;
import java.util.List;
import l0.AbstractC0389k0;
import o0.AbstractC0455v;

/* JADX INFO: renamed from: l0.m0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractActivityC0393m0 extends AbstractActivityC0391l0 {

    /* JADX INFO: renamed from: l0.m0$a */
    public class a implements a.h {
        public a() {
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            AbstractActivityC0393m0 abstractActivityC0393m0 = AbstractActivityC0393m0.this;
            abstractActivityC0393m0.f6188R = false;
            abstractActivityC0393m0.u0();
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(e.c cVar) {
            AbstractActivityC0393m0 abstractActivityC0393m0 = AbstractActivityC0393m0.this;
            abstractActivityC0393m0.f6188R = false;
            abstractActivityC0393m0.u0();
            AbstractActivityC0393m0.this.w0(cVar);
        }
    }

    /* JADX INFO: renamed from: l0.m0$b */
    public class b implements a.h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f6212a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f6213b;

        public b(boolean z2, int i2) {
            this.f6212a = z2;
            this.f6213b = i2;
        }

        @Override // com.im.oldchat.bili.a.h
        public void a(String str) {
            AbstractActivityC0393m0 abstractActivityC0393m0 = AbstractActivityC0393m0.this;
            abstractActivityC0393m0.f6189S = false;
            abstractActivityC0393m0.u0();
            if (this.f6212a) {
                AbstractActivityC0393m0.this.f6196Z.clear();
                AbstractActivityC0393m0 abstractActivityC0393m02 = AbstractActivityC0393m0.this;
                com.im.oldchat.ui.Z z2 = abstractActivityC0393m02.f6197a0;
                if (z2 != null) {
                    z2.g(abstractActivityC0393m02.f6196Z);
                }
            }
            if (str != null && str.length() > 0) {
                Toast.makeText(AbstractActivityC0393m0.this, str, 0).show();
            }
            AbstractActivityC0393m0.this.s0();
        }

        @Override // com.im.oldchat.bili.a.h
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(e.d dVar) {
            List list;
            AbstractActivityC0393m0 abstractActivityC0393m0 = AbstractActivityC0393m0.this;
            abstractActivityC0393m0.f6189S = false;
            abstractActivityC0393m0.u0();
            if (this.f6212a) {
                AbstractActivityC0393m0.this.f6196Z.clear();
            }
            if (dVar == null || (list = dVar.f2259a) == null) {
                AbstractActivityC0393m0.this.f6195Y = false;
            } else {
                AbstractActivityC0393m0.this.f6196Z.addAll(list);
                AbstractActivityC0393m0 abstractActivityC0393m02 = AbstractActivityC0393m0.this;
                int i2 = dVar.f2260b;
                if (i2 <= 0) {
                    i2 = this.f6213b;
                }
                abstractActivityC0393m02.f6194X = i2;
                abstractActivityC0393m02.f6195Y = dVar.f2263e;
            }
            AbstractActivityC0393m0 abstractActivityC0393m03 = AbstractActivityC0393m0.this;
            com.im.oldchat.ui.Z z2 = abstractActivityC0393m03.f6197a0;
            if (z2 != null) {
                z2.g(abstractActivityC0393m03.f6196Z);
            }
            AbstractActivityC0393m0.this.s0();
        }
    }

    /* JADX INFO: renamed from: l0.m0$c */
    public class c implements AbstractC0389k0.e {
        public c() {
        }

        @Override // l0.AbstractC0389k0.e
        public void a(boolean z2, String str, boolean z3) {
            AbstractActivityC0393m0.this.p0(false);
            if (z2) {
                AbstractActivityC0393m0.this.f6186P = z3;
            }
            AbstractActivityC0393m0.this.t0();
        }
    }

    /* JADX INFO: renamed from: l0.m0$d */
    public class d implements AbstractC0389k0.g {
        public d() {
        }

        @Override // l0.AbstractC0389k0.g
        public void a(boolean z2, String str, boolean z3) {
            AbstractActivityC0393m0.this.p0(false);
            if (!z2) {
                if (str == null || str.length() == 0) {
                    str = AbstractActivityC0393m0.this.getString(f0.j.l2);
                }
                Toast.makeText(AbstractActivityC0393m0.this, str, 0).show();
                AbstractActivityC0393m0.this.t0();
                return;
            }
            AbstractActivityC0393m0 abstractActivityC0393m0 = AbstractActivityC0393m0.this;
            abstractActivityC0393m0.f6186P = z3;
            abstractActivityC0393m0.t0();
            if (str == null || str.length() <= 0) {
                return;
            }
            Toast.makeText(AbstractActivityC0393m0.this, str, 0).show();
        }
    }

    /* JADX INFO: renamed from: l0.m0$e */
    public class e extends WebChromeClient {
        public e() {
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i2) {
            AbstractActivityC0393m0 abstractActivityC0393m0 = AbstractActivityC0393m0.this;
            abstractActivityC0393m0.f6190T = i2 < 100;
            abstractActivityC0393m0.u0();
        }
    }

    /* JADX INFO: renamed from: l0.m0$f */
    public class f extends WebViewClient {
        public f() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            AbstractActivityC0393m0 abstractActivityC0393m0 = AbstractActivityC0393m0.this;
            abstractActivityC0393m0.f6190T = false;
            abstractActivityC0393m0.u0();
            AbstractActivityC0393m0.this.s0();
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            AbstractActivityC0393m0 abstractActivityC0393m0 = AbstractActivityC0393m0.this;
            abstractActivityC0393m0.f6190T = true;
            abstractActivityC0393m0.f6192V = false;
            abstractActivityC0393m0.u0();
            AbstractActivityC0393m0.this.s0();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i2, String str, String str2) {
            AbstractActivityC0393m0 abstractActivityC0393m0 = AbstractActivityC0393m0.this;
            abstractActivityC0393m0.f6190T = false;
            abstractActivityC0393m0.f6192V = true;
            abstractActivityC0393m0.u0();
            AbstractActivityC0393m0.this.s0();
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str != null && str.length() != 0) {
                if (!str.startsWith("http://") && !str.startsWith("https://")) {
                    try {
                        AbstractActivityC0393m0.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    } catch (Exception unused) {
                    }
                    return true;
                }
                webView.loadUrl(str);
            }
            return true;
        }
    }

    public void A0() {
        if (this.f6182L <= 0) {
            return;
        }
        p0(true);
        AbstractC0389k0.n(this.f6182L, this.f6184N, this.f6185O, new c());
    }

    public final void B0(WebSettings webSettings, boolean z2) {
        if (webSettings == null) {
            return;
        }
        try {
            WebSettings.class.getMethod("setDisplayZoomControls", Boolean.TYPE).invoke(webSettings, Boolean.valueOf(z2));
        } catch (Throwable unused) {
        }
    }

    @Override // l0.AbstractActivityC0391l0
    public void i0(int i2, boolean z2) {
        if (this.f6189S || this.f6182L <= 0 || i2 <= 0) {
            return;
        }
        this.f6189S = true;
        u0();
        com.im.oldchat.bili.e.I(this.f6182L, i2, 20, this.f6185O, new b(z2, i2));
    }

    @Override // l0.AbstractActivityC0391l0
    public void j0() {
        if (this.f6187Q) {
            return;
        }
        boolean z2 = !this.f6186P;
        p0(true);
        AbstractC0389k0.s(this.f6182L, z2, this.f6184N, this.f6185O, new d());
    }

    @Override // l0.AbstractActivityC0391l0
    public void k0() {
        x0();
        z0();
    }

    public final void w0(e.c cVar) {
        String str;
        if (cVar == null) {
            return;
        }
        String str2 = cVar.f2252b;
        if (str2 != null && str2.length() > 0) {
            String str3 = cVar.f2252b;
            this.f6183M = str3;
            TextView textView = this.f6199x;
            if (textView != null) {
                textView.setText(str3);
            }
            TextView textView2 = this.f6201z;
            if (textView2 != null) {
                textView2.setText(cVar.f2252b);
            }
        }
        if (this.f6172B != null) {
            String str4 = cVar.f2254d;
            String strTrim = str4 != null ? str4.trim() : "";
            TextView textView3 = this.f6172B;
            if (strTrim.length() <= 0) {
                strTrim = "这个 UP 很神秘，暂时没有简介";
            }
            textView3.setText(strTrim);
        }
        TextView textView4 = this.f6173C;
        if (textView4 != null) {
            textView4.setText("粉丝 " + h0(cVar.f2257g));
        }
        TextView textView5 = this.f6174D;
        if (textView5 != null) {
            textView5.setText("关注 " + h0(cVar.f2256f));
        }
        TextView textView6 = this.f6175E;
        if (textView6 != null) {
            textView6.setText("投稿 " + h0(cVar.f2258h));
        }
        if (this.f6179I == null || (str = cVar.f2253c) == null || str.length() <= 0) {
            return;
        }
        AbstractC0455v.h(this.f6179I, cVar.f2253c);
        this.f6179I.setVisibility(0);
    }

    public final void x0() {
        WebView webView = this.f6181K;
        if (webView == null) {
            return;
        }
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        B0(settings, false);
        this.f6181K.setWebChromeClient(new e());
        this.f6181K.setWebViewClient(new f());
    }

    public void y0() {
        this.f6188R = true;
        u0();
        com.im.oldchat.bili.e.H(this.f6182L, this.f6185O, new a());
    }

    public final void z0() {
        WebView webView = this.f6181K;
        if (webView == null || this.f6182L <= 0) {
            return;
        }
        webView.loadUrl("https://space.bilibili.com/" + this.f6182L);
    }
}
