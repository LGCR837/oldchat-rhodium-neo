package o;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v7.view.menu.e;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import c.AbstractC0177z;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import k.AbstractC0285k0;
import k.F1;
import k.T1;
import k.U1;
import k.V1;
import p.AbstractC0460a;
import v.AbstractC0473b;
import v.C0472a;
import x.G;

/* JADX INFO: loaded from: classes.dex */
public class r extends AbstractC0430a implements ActionBarOverlayLayout.d {

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final Interpolator f6949E = new AccelerateInterpolator();

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final Interpolator f6950F = new DecelerateInterpolator();

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final boolean f6951G;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f6952A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f6956a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f6957b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Activity f6958c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Dialog f6959d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ActionBarOverlayLayout f6960e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ActionBarContainer f6961f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public G f6962g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ActionBarContextView f6963h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public View f6964i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f6967l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public d f6968m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public AbstractC0473b f6969n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public AbstractC0473b.a f6970o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f6971p;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f6973r;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f6976u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f6977v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f6978w;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public v.l f6980y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f6981z;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList f6965j = new ArrayList();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f6966k = -1;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ArrayList f6972q = new ArrayList();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f6974s = 0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f6975t = true;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f6979x = true;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public final T1 f6953B = new a();

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final T1 f6954C = new b();

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final V1 f6955D = new c();

    public class a extends U1 {
        public a() {
        }

        @Override // k.T1
        public void a(View view) {
            View view2;
            r rVar = r.this;
            if (rVar.f6975t && (view2 = rVar.f6964i) != null) {
                AbstractC0285k0.X(view2, 0.0f);
                AbstractC0285k0.X(r.this.f6961f, 0.0f);
            }
            r.this.f6961f.setVisibility(8);
            r.this.f6961f.setTransitioning(false);
            r rVar2 = r.this;
            rVar2.f6980y = null;
            rVar2.w();
            ActionBarOverlayLayout actionBarOverlayLayout = r.this.f6960e;
            if (actionBarOverlayLayout != null) {
                AbstractC0285k0.H(actionBarOverlayLayout);
            }
        }
    }

    public class b extends U1 {
        public b() {
        }

        @Override // k.T1
        public void a(View view) {
            r rVar = r.this;
            rVar.f6980y = null;
            rVar.f6961f.requestLayout();
        }
    }

    public class c implements V1 {
        public c() {
        }

        @Override // k.V1
        public void a(View view) {
            ((View) r.this.f6961f.getParent()).invalidate();
        }
    }

    public class d extends AbstractC0473b implements e.a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Context f6985c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final android.support.v7.view.menu.e f6986d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public AbstractC0473b.a f6987e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public WeakReference f6988f;

        public d(Context context, AbstractC0473b.a aVar) {
            this.f6985c = context;
            this.f6987e = aVar;
            android.support.v7.view.menu.e eVarR = new android.support.v7.view.menu.e(context).R(1);
            this.f6986d = eVarR;
            eVarR.Q(this);
        }

        @Override // android.support.v7.view.menu.e.a
        public void a(android.support.v7.view.menu.e eVar) {
            if (this.f6987e == null) {
                return;
            }
            k();
            r.this.f6963h.l();
        }

        @Override // android.support.v7.view.menu.e.a
        public boolean b(android.support.v7.view.menu.e eVar, MenuItem menuItem) {
            AbstractC0473b.a aVar = this.f6987e;
            if (aVar != null) {
                return aVar.d(this, menuItem);
            }
            return false;
        }

        @Override // v.AbstractC0473b
        public void c() {
            r rVar = r.this;
            if (rVar.f6968m != this) {
                return;
            }
            if (r.v(rVar.f6976u, rVar.f6977v, false)) {
                this.f6987e.a(this);
            } else {
                r rVar2 = r.this;
                rVar2.f6969n = this;
                rVar2.f6970o = this.f6987e;
            }
            this.f6987e = null;
            r.this.u(false);
            r.this.f6963h.g();
            r.this.f6962g.l().sendAccessibilityEvent(32);
            r rVar3 = r.this;
            rVar3.f6960e.setHideOnContentScrollEnabled(rVar3.f6952A);
            r.this.f6968m = null;
        }

        @Override // v.AbstractC0473b
        public View d() {
            WeakReference weakReference = this.f6988f;
            if (weakReference != null) {
                return (View) weakReference.get();
            }
            return null;
        }

        @Override // v.AbstractC0473b
        public Menu e() {
            return this.f6986d;
        }

        @Override // v.AbstractC0473b
        public MenuInflater f() {
            return new v.k(this.f6985c);
        }

        @Override // v.AbstractC0473b
        public CharSequence g() {
            return r.this.f6963h.getSubtitle();
        }

        @Override // v.AbstractC0473b
        public CharSequence i() {
            return r.this.f6963h.getTitle();
        }

        @Override // v.AbstractC0473b
        public void k() {
            if (r.this.f6968m != this) {
                return;
            }
            this.f6986d.c0();
            try {
                this.f6987e.b(this, this.f6986d);
            } finally {
                this.f6986d.b0();
            }
        }

        @Override // v.AbstractC0473b
        public boolean l() {
            return r.this.f6963h.j();
        }

        @Override // v.AbstractC0473b
        public void m(View view) {
            r.this.f6963h.setCustomView(view);
            this.f6988f = new WeakReference(view);
        }

        @Override // v.AbstractC0473b
        public void n(int i2) {
            o(r.this.f6956a.getResources().getString(i2));
        }

        @Override // v.AbstractC0473b
        public void o(CharSequence charSequence) {
            r.this.f6963h.setSubtitle(charSequence);
        }

        @Override // v.AbstractC0473b
        public void q(int i2) {
            r(r.this.f6956a.getResources().getString(i2));
        }

        @Override // v.AbstractC0473b
        public void r(CharSequence charSequence) {
            r.this.f6963h.setTitle(charSequence);
        }

        @Override // v.AbstractC0473b
        public void s(boolean z2) {
            super.s(z2);
            r.this.f6963h.setTitleOptional(z2);
        }

        public boolean t() {
            this.f6986d.c0();
            try {
                return this.f6987e.c(this, this.f6986d);
            } finally {
                this.f6986d.b0();
            }
        }
    }

    static {
        f6951G = Build.VERSION.SDK_INT >= 14;
    }

    public r(Activity activity, boolean z2) {
        this.f6958c = activity;
        View decorView = activity.getWindow().getDecorView();
        E(decorView);
        if (z2) {
            return;
        }
        this.f6964i = decorView.findViewById(R.id.content);
    }

    public static boolean v(boolean z2, boolean z3, boolean z4) {
        if (z4) {
            return true;
        }
        return (z2 || z3) ? false : true;
    }

    public int A() {
        return this.f6961f.getHeight();
    }

    public int B() {
        return this.f6960e.getActionBarHideOffset();
    }

    public int C() {
        return this.f6962g.o();
    }

    public final void D() {
        if (this.f6978w) {
            this.f6978w = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f6960e;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            N(false);
        }
    }

    public final void E(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(p.f.f7805o);
        this.f6960e = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.f6962g = z(view.findViewById(p.f.f7791a));
        this.f6963h = (ActionBarContextView) view.findViewById(p.f.f7796f);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(p.f.f7793c);
        this.f6961f = actionBarContainer;
        G g2 = this.f6962g;
        if (g2 == null || this.f6963h == null || actionBarContainer == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f6956a = g2.n();
        boolean z2 = (this.f6962g.i() & 4) != 0;
        if (z2) {
            this.f6967l = true;
        }
        C0472a c0472aB = C0472a.b(this.f6956a);
        K(c0472aB.a() || z2);
        I(c0472aB.e());
        TypedArray typedArrayObtainStyledAttributes = this.f6956a.obtainStyledAttributes(null, p.j.f7862a, AbstractC0460a.f7709c, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(p.j.f7882k, false)) {
            J(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(p.j.f7878i, 0);
        if (dimensionPixelSize != 0) {
            H(dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public void F(boolean z2) {
        G(z2 ? 4 : 0, 4);
    }

    public void G(int i2, int i3) {
        int i4 = this.f6962g.i();
        if ((i3 & 4) != 0) {
            this.f6967l = true;
        }
        this.f6962g.v((i2 & i3) | ((i3 ^ (-1)) & i4));
    }

    public void H(float f2) {
        AbstractC0285k0.O(this.f6961f, f2);
    }

    public final void I(boolean z2) {
        this.f6973r = z2;
        if (z2) {
            this.f6961f.setTabContainer(null);
            this.f6962g.p(null);
        } else {
            this.f6962g.p(null);
            this.f6961f.setTabContainer(null);
        }
        boolean z3 = false;
        boolean z4 = C() == 2;
        this.f6962g.u(!this.f6973r && z4);
        ActionBarOverlayLayout actionBarOverlayLayout = this.f6960e;
        if (!this.f6973r && z4) {
            z3 = true;
        }
        actionBarOverlayLayout.setHasNonEmbeddedTabs(z3);
    }

    public void J(boolean z2) {
        if (z2 && !this.f6960e.q()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.f6952A = z2;
        this.f6960e.setHideOnContentScrollEnabled(z2);
    }

    public void K(boolean z2) {
        this.f6962g.m(z2);
    }

    public final boolean L() {
        return AbstractC0285k0.z(this.f6961f);
    }

    public final void M() {
        if (this.f6978w) {
            return;
        }
        this.f6978w = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.f6960e;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setShowingForActionMode(true);
        }
        N(false);
    }

    public final void N(boolean z2) {
        if (v(this.f6976u, this.f6977v, this.f6978w)) {
            if (this.f6979x) {
                return;
            }
            this.f6979x = true;
            y(z2);
            return;
        }
        if (this.f6979x) {
            this.f6979x = false;
            x(z2);
        }
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.d
    public void a(boolean z2) {
        this.f6975t = z2;
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.d
    public void b() {
        if (this.f6977v) {
            this.f6977v = false;
            N(true);
        }
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.d
    public void c() {
        v.l lVar = this.f6980y;
        if (lVar != null) {
            lVar.a();
            this.f6980y = null;
        }
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.d
    public void d(int i2) {
        this.f6974s = i2;
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.d
    public void e() {
        if (this.f6977v) {
            return;
        }
        this.f6977v = true;
        N(true);
    }

    @Override // o.AbstractC0430a
    public boolean g() {
        G g2 = this.f6962g;
        if (g2 == null || !g2.s()) {
            return false;
        }
        this.f6962g.collapseActionView();
        return true;
    }

    @Override // o.AbstractC0430a
    public void h(boolean z2) {
        if (z2 == this.f6971p) {
            return;
        }
        this.f6971p = z2;
        if (this.f6972q.size() <= 0) {
            return;
        }
        AbstractC0177z.a(this.f6972q.get(0));
        throw null;
    }

    @Override // o.AbstractC0430a
    public int i() {
        return this.f6962g.i();
    }

    @Override // o.AbstractC0430a
    public Context j() {
        if (this.f6957b == null) {
            TypedValue typedValue = new TypedValue();
            this.f6956a.getTheme().resolveAttribute(AbstractC0460a.f7711e, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                this.f6957b = new ContextThemeWrapper(this.f6956a, i2);
            } else {
                this.f6957b = this.f6956a;
            }
        }
        return this.f6957b;
    }

    @Override // o.AbstractC0430a
    public boolean l() {
        int iA = A();
        return this.f6979x && (iA == 0 || B() < iA);
    }

    @Override // o.AbstractC0430a
    public void m(Configuration configuration) {
        I(C0472a.b(this.f6956a).e());
    }

    @Override // o.AbstractC0430a
    public boolean p() {
        ViewGroup viewGroupL = this.f6962g.l();
        if (viewGroupL == null || viewGroupL.hasFocus()) {
            return false;
        }
        viewGroupL.requestFocus();
        return true;
    }

    @Override // o.AbstractC0430a
    public void q(boolean z2) {
        if (this.f6967l) {
            return;
        }
        F(z2);
    }

    @Override // o.AbstractC0430a
    public void r(boolean z2) {
        v.l lVar;
        this.f6981z = z2;
        if (z2 || (lVar = this.f6980y) == null) {
            return;
        }
        lVar.a();
    }

    @Override // o.AbstractC0430a
    public void s(CharSequence charSequence) {
        this.f6962g.setWindowTitle(charSequence);
    }

    @Override // o.AbstractC0430a
    public AbstractC0473b t(AbstractC0473b.a aVar) {
        d dVar = this.f6968m;
        if (dVar != null) {
            dVar.c();
        }
        this.f6960e.setHideOnContentScrollEnabled(false);
        this.f6963h.k();
        d dVar2 = new d(this.f6963h.getContext(), aVar);
        if (!dVar2.t()) {
            return null;
        }
        this.f6968m = dVar2;
        dVar2.k();
        this.f6963h.h(dVar2);
        u(true);
        this.f6963h.sendAccessibilityEvent(32);
        return dVar2;
    }

    public void u(boolean z2) {
        F1 f1Q;
        F1 f1F;
        if (z2) {
            M();
        } else {
            D();
        }
        if (!L()) {
            if (z2) {
                this.f6962g.j(4);
                this.f6963h.setVisibility(0);
                return;
            } else {
                this.f6962g.j(0);
                this.f6963h.setVisibility(8);
                return;
            }
        }
        if (z2) {
            f1F = this.f6962g.q(4, 100L);
            f1Q = this.f6963h.f(0, 200L);
        } else {
            f1Q = this.f6962g.q(0, 200L);
            f1F = this.f6963h.f(8, 100L);
        }
        v.l lVar = new v.l();
        lVar.d(f1F, f1Q);
        lVar.h();
    }

    public void w() {
        AbstractC0473b.a aVar = this.f6970o;
        if (aVar != null) {
            aVar.a(this.f6969n);
            this.f6969n = null;
            this.f6970o = null;
        }
    }

    public void x(boolean z2) {
        View view;
        v.l lVar = this.f6980y;
        if (lVar != null) {
            lVar.a();
        }
        if (this.f6974s != 0 || !f6951G || (!this.f6981z && !z2)) {
            this.f6953B.a(null);
            return;
        }
        AbstractC0285k0.K(this.f6961f, 1.0f);
        this.f6961f.setTransitioning(true);
        v.l lVar2 = new v.l();
        float f2 = -this.f6961f.getHeight();
        if (z2) {
            this.f6961f.getLocationInWindow(new int[]{0, 0});
            f2 -= r5[1];
        }
        F1 f1K = AbstractC0285k0.a(this.f6961f).k(f2);
        f1K.h(this.f6955D);
        lVar2.c(f1K);
        if (this.f6975t && (view = this.f6964i) != null) {
            lVar2.c(AbstractC0285k0.a(view).k(f2));
        }
        lVar2.f(f6949E);
        lVar2.e(250L);
        lVar2.g(this.f6953B);
        this.f6980y = lVar2;
        lVar2.h();
    }

    public void y(boolean z2) {
        View view;
        View view2;
        v.l lVar = this.f6980y;
        if (lVar != null) {
            lVar.a();
        }
        this.f6961f.setVisibility(0);
        if (this.f6974s == 0 && f6951G && (this.f6981z || z2)) {
            AbstractC0285k0.X(this.f6961f, 0.0f);
            float f2 = -this.f6961f.getHeight();
            if (z2) {
                this.f6961f.getLocationInWindow(new int[]{0, 0});
                f2 -= r5[1];
            }
            AbstractC0285k0.X(this.f6961f, f2);
            v.l lVar2 = new v.l();
            F1 f1K = AbstractC0285k0.a(this.f6961f).k(0.0f);
            f1K.h(this.f6955D);
            lVar2.c(f1K);
            if (this.f6975t && (view2 = this.f6964i) != null) {
                AbstractC0285k0.X(view2, f2);
                lVar2.c(AbstractC0285k0.a(this.f6964i).k(0.0f));
            }
            lVar2.f(f6950F);
            lVar2.e(250L);
            lVar2.g(this.f6954C);
            this.f6980y = lVar2;
            lVar2.h();
        } else {
            AbstractC0285k0.K(this.f6961f, 1.0f);
            AbstractC0285k0.X(this.f6961f, 0.0f);
            if (this.f6975t && (view = this.f6964i) != null) {
                AbstractC0285k0.X(view, 0.0f);
            }
            this.f6954C.a(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f6960e;
        if (actionBarOverlayLayout != null) {
            AbstractC0285k0.H(actionBarOverlayLayout);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final G z(View view) {
        if (view instanceof G) {
            return (G) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't make a decor toolbar out of ");
        sb.append(view);
        throw new IllegalStateException(sb.toString() != null ? view.getClass().getSimpleName() : "null");
    }

    public r(Dialog dialog) {
        this.f6959d = dialog;
        E(dialog.getWindow().getDecorView());
    }

    @Override // android.support.v7.widget.ActionBarOverlayLayout.d
    public void f() {
    }
}
