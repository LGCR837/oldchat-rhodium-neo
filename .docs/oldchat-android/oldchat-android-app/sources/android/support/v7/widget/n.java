package android.support.v7.widget;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.i;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import k.AbstractC0285k0;
import k.F1;
import k.U1;
import p.AbstractC0460a;
import p.AbstractC0464e;
import w.C0476a;
import x.G;
import x.f0;

/* JADX INFO: loaded from: classes.dex */
public class n implements G {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Toolbar f1714a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1715b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f1716c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f1717d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f1718e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f1719f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable f1720g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1721h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public CharSequence f1722i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CharSequence f1723j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public CharSequence f1724k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Window.Callback f1725l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f1726m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public android.support.v7.widget.a f1727n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1728o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1729p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Drawable f1730q;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final C0476a f1731a;

        public a() {
            this.f1731a = new C0476a(n.this.f1714a.getContext(), 0, R.id.home, 0, 0, n.this.f1722i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            n nVar = n.this;
            Window.Callback callback = nVar.f1725l;
            if (callback == null || !nVar.f1726m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f1731a);
        }
    }

    public class b extends U1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1733a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f1734b;

        public b(int i2) {
            this.f1734b = i2;
        }

        @Override // k.T1
        public void a(View view) {
            if (this.f1733a) {
                return;
            }
            n.this.f1714a.setVisibility(this.f1734b);
        }

        @Override // k.U1, k.T1
        public void b(View view) {
            n.this.f1714a.setVisibility(0);
        }

        @Override // k.U1, k.T1
        public void c(View view) {
            this.f1733a = true;
        }
    }

    public n(Toolbar toolbar, boolean z2) {
        this(toolbar, z2, p.h.f7833a, AbstractC0464e.f7775l);
    }

    public void A(int i2) {
        B(i2 == 0 ? null : n().getString(i2));
    }

    public void B(CharSequence charSequence) {
        this.f1724k = charSequence;
        G();
    }

    public void C(Drawable drawable) {
        this.f1720g = drawable;
        H();
    }

    public void D(CharSequence charSequence) {
        this.f1723j = charSequence;
        if ((this.f1715b & 8) != 0) {
            this.f1714a.setSubtitle(charSequence);
        }
    }

    public void E(CharSequence charSequence) {
        this.f1721h = true;
        F(charSequence);
    }

    public final void F(CharSequence charSequence) {
        this.f1722i = charSequence;
        if ((this.f1715b & 8) != 0) {
            this.f1714a.setTitle(charSequence);
        }
    }

    public final void G() {
        if ((this.f1715b & 4) != 0) {
            if (TextUtils.isEmpty(this.f1724k)) {
                this.f1714a.setNavigationContentDescription(this.f1729p);
            } else {
                this.f1714a.setNavigationContentDescription(this.f1724k);
            }
        }
    }

    public final void H() {
        if ((this.f1715b & 4) == 0) {
            this.f1714a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.f1714a;
        Drawable drawable = this.f1720g;
        if (drawable == null) {
            drawable = this.f1730q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    public final void I() {
        Drawable drawable;
        int i2 = this.f1715b;
        if ((i2 & 2) == 0) {
            drawable = null;
        } else if ((i2 & 1) == 0 || (drawable = this.f1719f) == null) {
            drawable = this.f1718e;
        }
        this.f1714a.setLogo(drawable);
    }

    @Override // x.G
    public boolean a() {
        return this.f1714a.y();
    }

    @Override // x.G
    public boolean b() {
        return this.f1714a.z();
    }

    @Override // x.G
    public void c(Menu menu, i.a aVar) {
        if (this.f1727n == null) {
            android.support.v7.widget.a aVar2 = new android.support.v7.widget.a(this.f1714a.getContext());
            this.f1727n = aVar2;
            aVar2.p(p.f.f7797g);
        }
        this.f1727n.f(aVar);
        this.f1714a.H((android.support.v7.view.menu.e) menu, this.f1727n);
    }

    @Override // x.G
    public void collapseActionView() {
        this.f1714a.e();
    }

    @Override // x.G
    public boolean d() {
        return this.f1714a.w();
    }

    @Override // x.G
    public boolean e() {
        return this.f1714a.M();
    }

    @Override // x.G
    public void f() {
        this.f1726m = true;
    }

    @Override // x.G
    public boolean g() {
        return this.f1714a.d();
    }

    @Override // x.G
    public CharSequence getTitle() {
        return this.f1714a.getTitle();
    }

    @Override // x.G
    public void h() {
        this.f1714a.f();
    }

    @Override // x.G
    public int i() {
        return this.f1715b;
    }

    @Override // x.G
    public void j(int i2) {
        this.f1714a.setVisibility(i2);
    }

    @Override // x.G
    public void k(int i2) {
        z(i2 != 0 ? q.b.d(n(), i2) : null);
    }

    @Override // x.G
    public ViewGroup l() {
        return this.f1714a;
    }

    @Override // x.G
    public void m(boolean z2) {
    }

    @Override // x.G
    public Context n() {
        return this.f1714a.getContext();
    }

    @Override // x.G
    public int o() {
        return this.f1728o;
    }

    @Override // x.G
    public void p(l lVar) {
        View view = this.f1716c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f1714a;
            if (parent == toolbar) {
                toolbar.removeView(this.f1716c);
            }
        }
        this.f1716c = lVar;
    }

    @Override // x.G
    public F1 q(int i2, long j2) {
        return AbstractC0285k0.a(this.f1714a).a(i2 == 0 ? 1.0f : 0.0f).d(j2).f(new b(i2));
    }

    @Override // x.G
    public void r() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // x.G
    public boolean s() {
        return this.f1714a.v();
    }

    @Override // x.G
    public void setIcon(int i2) {
        setIcon(i2 != 0 ? q.b.d(n(), i2) : null);
    }

    @Override // x.G
    public void setWindowCallback(Window.Callback callback) {
        this.f1725l = callback;
    }

    @Override // x.G
    public void setWindowTitle(CharSequence charSequence) {
        if (this.f1721h) {
            return;
        }
        F(charSequence);
    }

    @Override // x.G
    public void t() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // x.G
    public void u(boolean z2) {
        this.f1714a.setCollapsible(z2);
    }

    @Override // x.G
    public void v(int i2) {
        View view;
        int i3 = this.f1715b ^ i2;
        this.f1715b = i2;
        if (i3 != 0) {
            if ((i3 & 4) != 0) {
                if ((i2 & 4) != 0) {
                    G();
                }
                H();
            }
            if ((i3 & 3) != 0) {
                I();
            }
            if ((i3 & 8) != 0) {
                if ((i2 & 8) != 0) {
                    this.f1714a.setTitle(this.f1722i);
                    this.f1714a.setSubtitle(this.f1723j);
                } else {
                    this.f1714a.setTitle((CharSequence) null);
                    this.f1714a.setSubtitle((CharSequence) null);
                }
            }
            if ((i3 & 16) == 0 || (view = this.f1717d) == null) {
                return;
            }
            if ((i2 & 16) != 0) {
                this.f1714a.addView(view);
            } else {
                this.f1714a.removeView(view);
            }
        }
    }

    public final int w() {
        if (this.f1714a.getNavigationIcon() == null) {
            return 11;
        }
        this.f1730q = this.f1714a.getNavigationIcon();
        return 15;
    }

    public void x(View view) {
        View view2 = this.f1717d;
        if (view2 != null && (this.f1715b & 16) != 0) {
            this.f1714a.removeView(view2);
        }
        this.f1717d = view;
        if (view == null || (this.f1715b & 16) == 0) {
            return;
        }
        this.f1714a.addView(view);
    }

    public void y(int i2) {
        if (i2 == this.f1729p) {
            return;
        }
        this.f1729p = i2;
        if (TextUtils.isEmpty(this.f1714a.getNavigationContentDescription())) {
            A(this.f1729p);
        }
    }

    public void z(Drawable drawable) {
        this.f1719f = drawable;
        I();
    }

    public n(Toolbar toolbar, boolean z2, int i2, int i3) {
        Drawable drawable;
        this.f1728o = 0;
        this.f1729p = 0;
        this.f1714a = toolbar;
        this.f1722i = toolbar.getTitle();
        this.f1723j = toolbar.getSubtitle();
        this.f1721h = this.f1722i != null;
        this.f1720g = toolbar.getNavigationIcon();
        f0 f0VarS = f0.s(toolbar.getContext(), null, p.j.f7862a, AbstractC0460a.f7709c, 0);
        this.f1730q = f0VarS.f(p.j.f7884l);
        if (z2) {
            CharSequence charSequenceN = f0VarS.n(p.j.f7894r);
            if (!TextUtils.isEmpty(charSequenceN)) {
                E(charSequenceN);
            }
            CharSequence charSequenceN2 = f0VarS.n(p.j.f7892p);
            if (!TextUtils.isEmpty(charSequenceN2)) {
                D(charSequenceN2);
            }
            Drawable drawableF = f0VarS.f(p.j.f7888n);
            if (drawableF != null) {
                z(drawableF);
            }
            Drawable drawableF2 = f0VarS.f(p.j.f7886m);
            if (drawableF2 != null) {
                setIcon(drawableF2);
            }
            if (this.f1720g == null && (drawable = this.f1730q) != null) {
                C(drawable);
            }
            v(f0VarS.i(p.j.f7876h, 0));
            int iL = f0VarS.l(p.j.f7874g, 0);
            if (iL != 0) {
                x(LayoutInflater.from(this.f1714a.getContext()).inflate(iL, (ViewGroup) this.f1714a, false));
                v(this.f1715b | 16);
            }
            int iK = f0VarS.k(p.j.f7880j, 0);
            if (iK > 0) {
                ViewGroup.LayoutParams layoutParams = this.f1714a.getLayoutParams();
                layoutParams.height = iK;
                this.f1714a.setLayoutParams(layoutParams);
            }
            int iD = f0VarS.d(p.j.f7872f, -1);
            int iD2 = f0VarS.d(p.j.f7870e, -1);
            if (iD >= 0 || iD2 >= 0) {
                this.f1714a.G(Math.max(iD, 0), Math.max(iD2, 0));
            }
            int iL2 = f0VarS.l(p.j.f7895s, 0);
            if (iL2 != 0) {
                Toolbar toolbar2 = this.f1714a;
                toolbar2.J(toolbar2.getContext(), iL2);
            }
            int iL3 = f0VarS.l(p.j.f7893q, 0);
            if (iL3 != 0) {
                Toolbar toolbar3 = this.f1714a;
                toolbar3.I(toolbar3.getContext(), iL3);
            }
            int iL4 = f0VarS.l(p.j.f7890o, 0);
            if (iL4 != 0) {
                this.f1714a.setPopupTheme(iL4);
            }
        } else {
            this.f1715b = w();
        }
        f0VarS.t();
        y(i2);
        this.f1724k = this.f1714a.getNavigationContentDescription();
        this.f1714a.setNavigationOnClickListener(new a());
    }

    @Override // x.G
    public void setIcon(Drawable drawable) {
        this.f1718e = drawable;
        I();
    }
}
