package o;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.view.menu.e;
import android.support.v7.view.menu.i;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.ViewStubCompat;
import android.support.v7.widget.e;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import c.AbstractC0137f0;
import k.AbstractC0253A;
import k.AbstractC0285k0;
import k.AbstractC0312t1;
import k.F1;
import k.H;
import k.InterfaceC0264d0;
import k.U1;
import k.W1;
import n.AbstractC0414B;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.crypto.tls.CipherSuite;
import p.AbstractC0460a;
import p.AbstractC0462c;
import v.AbstractC0473b;
import v.C0475d;
import x.C0508k;
import x.F;
import x.g0;
import x.h0;

/* JADX INFO: loaded from: classes.dex */
public class m extends o.g implements e.a, H {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public Runnable f6868A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public F1 f6869B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f6870C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public ViewGroup f6871D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public TextView f6872E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public View f6873F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f6874G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public boolean f6875H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f6876I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public j[] f6877J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public j f6878K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public boolean f6879L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public boolean f6880M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public int f6881N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public final Runnable f6882O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public boolean f6883P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public Rect f6884Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public Rect f6885R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public o f6886S;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public F f6887u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public g f6888v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public k f6889w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public AbstractC0473b f6890x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public ActionBarContextView f6891y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public PopupWindow f6892z;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            m mVar = m.this;
            if ((mVar.f6881N & 1) != 0) {
                mVar.Z(0);
            }
            m mVar2 = m.this;
            if ((mVar2.f6881N & PKIFailureInfo.certConfirmed) != 0) {
                mVar2.Z(CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256);
            }
            m mVar3 = m.this;
            mVar3.f6880M = false;
            mVar3.f6881N = 0;
        }
    }

    public class b implements InterfaceC0264d0 {
        public b() {
        }

        @Override // k.InterfaceC0264d0
        public W1 a(View view, W1 w1) {
            int iD = w1.d();
            int iY0 = m.this.y0(iD);
            if (iD != iY0) {
                w1 = w1.f(w1.b(), iY0, w1.c(), w1.a());
            }
            return AbstractC0285k0.D(view, w1);
        }
    }

    public class c implements e.a {
        public c() {
        }

        @Override // android.support.v7.widget.e.a
        public void a(Rect rect) {
            rect.top = m.this.y0(rect.top);
        }
    }

    public class e implements Runnable {

        public class a extends U1 {
            public a() {
            }

            @Override // k.T1
            public void a(View view) {
                AbstractC0285k0.K(m.this.f6891y, 1.0f);
                m.this.f6869B.f(null);
                m.this.f6869B = null;
            }

            @Override // k.U1, k.T1
            public void b(View view) {
                m.this.f6891y.setVisibility(0);
            }
        }

        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            m mVar = m.this;
            mVar.f6892z.showAtLocation(mVar.f6891y, 55, 0, 0);
            m.this.a0();
            if (!m.this.t0()) {
                AbstractC0285k0.K(m.this.f6891y, 1.0f);
                m.this.f6891y.setVisibility(0);
            } else {
                AbstractC0285k0.K(m.this.f6891y, 0.0f);
                m mVar2 = m.this;
                mVar2.f6869B = AbstractC0285k0.a(mVar2.f6891y).a(1.0f);
                m.this.f6869B.f(new a());
            }
        }
    }

    public class f extends U1 {
        public f() {
        }

        @Override // k.T1
        public void a(View view) {
            AbstractC0285k0.K(m.this.f6891y, 1.0f);
            m.this.f6869B.f(null);
            m.this.f6869B = null;
        }

        @Override // k.U1, k.T1
        public void b(View view) {
            m.this.f6891y.setVisibility(0);
            m.this.f6891y.sendAccessibilityEvent(32);
            if (m.this.f6891y.getParent() != null) {
                AbstractC0285k0.H((View) m.this.f6891y.getParent());
            }
        }
    }

    public final class g implements i.a {
        public g() {
        }

        @Override // android.support.v7.view.menu.i.a
        public void a(android.support.v7.view.menu.e eVar, boolean z2) {
            m.this.T(eVar);
        }

        @Override // android.support.v7.view.menu.i.a
        public boolean b(android.support.v7.view.menu.e eVar) {
            Window.Callback callbackH = m.this.H();
            if (callbackH == null) {
                return true;
            }
            callbackH.onMenuOpened(CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256, eVar);
            return true;
        }
    }

    public class h implements AbstractC0473b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public AbstractC0473b.a f6901a;

        public class a extends U1 {
            public a() {
            }

            @Override // k.T1
            public void a(View view) {
                m.this.f6891y.setVisibility(8);
                m mVar = m.this;
                PopupWindow popupWindow = mVar.f6892z;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (mVar.f6891y.getParent() instanceof View) {
                    AbstractC0285k0.H((View) m.this.f6891y.getParent());
                }
                m.this.f6891y.removeAllViews();
                m.this.f6869B.f(null);
                m.this.f6869B = null;
            }
        }

        public h(AbstractC0473b.a aVar) {
            this.f6901a = aVar;
        }

        @Override // v.AbstractC0473b.a
        public void a(AbstractC0473b abstractC0473b) {
            this.f6901a.a(abstractC0473b);
            m mVar = m.this;
            if (mVar.f6892z != null) {
                mVar.f6838d.getDecorView().removeCallbacks(m.this.f6868A);
            }
            m mVar2 = m.this;
            if (mVar2.f6891y != null) {
                mVar2.a0();
                m mVar3 = m.this;
                mVar3.f6869B = AbstractC0285k0.a(mVar3.f6891y).a(0.0f);
                m.this.f6869B.f(new a());
            }
            m mVar4 = m.this;
            o.e eVar = mVar4.f6841g;
            if (eVar != null) {
                eVar.p(mVar4.f6890x);
            }
            m.this.f6890x = null;
        }

        @Override // v.AbstractC0473b.a
        public boolean b(AbstractC0473b abstractC0473b, Menu menu) {
            return this.f6901a.b(abstractC0473b, menu);
        }

        @Override // v.AbstractC0473b.a
        public boolean c(AbstractC0473b abstractC0473b, Menu menu) {
            return this.f6901a.c(abstractC0473b, menu);
        }

        @Override // v.AbstractC0473b.a
        public boolean d(AbstractC0473b abstractC0473b, MenuItem menuItem) {
            return this.f6901a.d(abstractC0473b, menuItem);
        }
    }

    public class i extends ContentFrameLayout {
        public i(Context context) {
            super(context);
        }

        public final boolean c(int i2, int i3) {
            return i2 < -5 || i3 < -5 || i2 > getWidth() + 5 || i3 > getHeight() + 5;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return m.this.E(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !c((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            m.this.U(0);
            return true;
        }

        @Override // android.view.View
        public void setBackgroundResource(int i2) {
            setBackgroundDrawable(q.b.d(getContext(), i2));
        }
    }

    public static final class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f6905a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f6906b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f6907c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f6908d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f6909e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f6910f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ViewGroup f6911g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public View f6912h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public View f6913i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public android.support.v7.view.menu.e f6914j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public android.support.v7.view.menu.c f6915k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public Context f6916l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public boolean f6917m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public boolean f6918n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public boolean f6919o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public boolean f6920p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public boolean f6921q = false;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public boolean f6922r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public Bundle f6923s;

        public j(int i2) {
            this.f6905a = i2;
        }

        public android.support.v7.view.menu.j a(i.a aVar) {
            if (this.f6914j == null) {
                return null;
            }
            if (this.f6915k == null) {
                android.support.v7.view.menu.c cVar = new android.support.v7.view.menu.c(this.f6916l, p.g.f7825i);
                this.f6915k = cVar;
                cVar.f(aVar);
                this.f6914j.b(this.f6915k);
            }
            return this.f6915k.h(this.f6911g);
        }

        public boolean b() {
            if (this.f6912h == null) {
                return false;
            }
            return this.f6913i != null || this.f6915k.c().getCount() > 0;
        }

        public void c(android.support.v7.view.menu.e eVar) {
            android.support.v7.view.menu.c cVar;
            android.support.v7.view.menu.e eVar2 = this.f6914j;
            if (eVar == eVar2) {
                return;
            }
            if (eVar2 != null) {
                eVar2.N(this.f6915k);
            }
            this.f6914j = eVar;
            if (eVar == null || (cVar = this.f6915k) == null) {
                return;
            }
            eVar.b(cVar);
        }

        public void d(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme themeNewTheme = context.getResources().newTheme();
            themeNewTheme.setTo(context.getTheme());
            themeNewTheme.resolveAttribute(AbstractC0460a.f7707a, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                themeNewTheme.applyStyle(i2, true);
            }
            themeNewTheme.resolveAttribute(AbstractC0460a.f7698B, typedValue, true);
            int i3 = typedValue.resourceId;
            if (i3 != 0) {
                themeNewTheme.applyStyle(i3, true);
            } else {
                themeNewTheme.applyStyle(p.i.f7834a, true);
            }
            C0475d c0475d = new C0475d(context, 0);
            c0475d.getTheme().setTo(themeNewTheme);
            this.f6916l = c0475d;
            TypedArray typedArrayObtainStyledAttributes = c0475d.obtainStyledAttributes(p.j.f7867c0);
            this.f6906b = typedArrayObtainStyledAttributes.getResourceId(p.j.f7873f0, 0);
            this.f6910f = typedArrayObtainStyledAttributes.getResourceId(p.j.f7871e0, 0);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public final class k implements i.a {
        public k() {
        }

        @Override // android.support.v7.view.menu.i.a
        public void a(android.support.v7.view.menu.e eVar, boolean z2) {
            android.support.v7.view.menu.e eVarD = eVar.D();
            boolean z3 = eVarD != eVar;
            m mVar = m.this;
            if (z3) {
                eVar = eVarD;
            }
            j jVarC0 = mVar.c0(eVar);
            if (jVarC0 != null) {
                if (!z3) {
                    m.this.V(jVarC0, z2);
                } else {
                    m.this.S(jVarC0.f6905a, jVarC0, eVarD);
                    m.this.V(jVarC0, true);
                }
            }
        }

        @Override // android.support.v7.view.menu.i.a
        public boolean b(android.support.v7.view.menu.e eVar) {
            Window.Callback callbackH;
            if (eVar != null) {
                return true;
            }
            m mVar = m.this;
            if (!mVar.f6844j || (callbackH = mVar.H()) == null || m.this.J()) {
                return true;
            }
            callbackH.onMenuOpened(CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256, eVar);
            return true;
        }
    }

    public m(Context context, Window window, o.e eVar) {
        super(context, window, eVar);
        this.f6869B = null;
        this.f6882O = new a();
    }

    @Override // o.f
    public void A(View view, ViewGroup.LayoutParams layoutParams) {
        b0();
        ViewGroup viewGroup = (ViewGroup) this.f6871D.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.f6839e.onContentChanged();
    }

    @Override // o.g
    public boolean E(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 82 && this.f6839e.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? j0(keyCode, keyEvent) : l0(keyCode, keyEvent);
    }

    @Override // o.g
    public void I() {
        b0();
        if (this.f6844j && this.f6842h == null) {
            Window.Callback callback = this.f6839e;
            if (callback instanceof Activity) {
                this.f6842h = new r((Activity) this.f6839e, this.f6845k);
            } else if (callback instanceof Dialog) {
                this.f6842h = new r((Dialog) this.f6839e);
            }
            AbstractC0430a abstractC0430a = this.f6842h;
            if (abstractC0430a != null) {
                abstractC0430a.q(this.f6883P);
            }
        }
    }

    @Override // o.g
    public boolean K(int i2, KeyEvent keyEvent) {
        AbstractC0430a abstractC0430aK = k();
        if (abstractC0430aK != null && abstractC0430aK.o(i2, keyEvent)) {
            return true;
        }
        j jVar = this.f6878K;
        if (jVar != null && p0(jVar, keyEvent.getKeyCode(), keyEvent, 1)) {
            j jVar2 = this.f6878K;
            if (jVar2 != null) {
                jVar2.f6918n = true;
            }
            return true;
        }
        if (this.f6878K == null) {
            j jVarD0 = d0(0, true);
            q0(jVarD0, keyEvent);
            boolean zP0 = p0(jVarD0, keyEvent.getKeyCode(), keyEvent, 1);
            jVarD0.f6917m = false;
            if (zP0) {
                return true;
            }
        }
        return false;
    }

    @Override // o.g
    public boolean L(int i2, Menu menu) {
        if (i2 != 108) {
            return false;
        }
        AbstractC0430a abstractC0430aK = k();
        if (abstractC0430aK != null) {
            abstractC0430aK.h(true);
        }
        return true;
    }

    @Override // o.g
    public void M(int i2, Menu menu) {
        if (i2 == 108) {
            AbstractC0430a abstractC0430aK = k();
            if (abstractC0430aK != null) {
                abstractC0430aK.h(false);
                return;
            }
            return;
        }
        if (i2 == 0) {
            j jVarD0 = d0(i2, true);
            if (jVarD0.f6919o) {
                V(jVarD0, false);
            }
        }
    }

    @Override // o.g
    public void N(CharSequence charSequence) {
        F f2 = this.f6887u;
        if (f2 != null) {
            f2.setWindowTitle(charSequence);
            return;
        }
        if (O() != null) {
            O().s(charSequence);
            return;
        }
        TextView textView = this.f6872E;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public final void Q() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.f6871D.findViewById(R.id.content);
        View decorView = this.f6838d.getDecorView();
        contentFrameLayout.b(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray typedArrayObtainStyledAttributes = this.f6837c.obtainStyledAttributes(p.j.f7867c0);
        typedArrayObtainStyledAttributes.getValue(p.j.f7889n0, contentFrameLayout.getMinWidthMajor());
        typedArrayObtainStyledAttributes.getValue(p.j.f7891o0, contentFrameLayout.getMinWidthMinor());
        if (typedArrayObtainStyledAttributes.hasValue(p.j.f7885l0)) {
            typedArrayObtainStyledAttributes.getValue(p.j.f7885l0, contentFrameLayout.getFixedWidthMajor());
        }
        if (typedArrayObtainStyledAttributes.hasValue(p.j.f7887m0)) {
            typedArrayObtainStyledAttributes.getValue(p.j.f7887m0, contentFrameLayout.getFixedWidthMinor());
        }
        if (typedArrayObtainStyledAttributes.hasValue(p.j.f7881j0)) {
            typedArrayObtainStyledAttributes.getValue(p.j.f7881j0, contentFrameLayout.getFixedHeightMajor());
        }
        if (typedArrayObtainStyledAttributes.hasValue(p.j.f7883k0)) {
            typedArrayObtainStyledAttributes.getValue(p.j.f7883k0, contentFrameLayout.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    public View R(View view, String str, Context context, AttributeSet attributeSet) {
        View viewOnCreateView;
        Window.Callback callback = this.f6839e;
        if (!(callback instanceof LayoutInflater.Factory) || (viewOnCreateView = ((LayoutInflater.Factory) callback).onCreateView(str, context, attributeSet)) == null) {
            return null;
        }
        return viewOnCreateView;
    }

    public void S(int i2, j jVar, Menu menu) {
        if (menu == null) {
            if (jVar == null && i2 >= 0) {
                j[] jVarArr = this.f6877J;
                if (i2 < jVarArr.length) {
                    jVar = jVarArr[i2];
                }
            }
            if (jVar != null) {
                menu = jVar.f6914j;
            }
        }
        if ((jVar == null || jVar.f6919o) && !J()) {
            this.f6839e.onPanelClosed(i2, menu);
        }
    }

    public void T(android.support.v7.view.menu.e eVar) {
        if (this.f6876I) {
            return;
        }
        this.f6876I = true;
        this.f6887u.i();
        Window.Callback callbackH = H();
        if (callbackH != null && !J()) {
            callbackH.onPanelClosed(CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256, eVar);
        }
        this.f6876I = false;
    }

    public void U(int i2) {
        V(d0(i2, true), true);
    }

    public void V(j jVar, boolean z2) {
        ViewGroup viewGroup;
        F f2;
        if (z2 && jVar.f6905a == 0 && (f2 = this.f6887u) != null && f2.b()) {
            T(jVar.f6914j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f6837c.getSystemService("window");
        if (windowManager != null && jVar.f6919o && (viewGroup = jVar.f6911g) != null) {
            windowManager.removeView(viewGroup);
            if (z2) {
                S(jVar.f6905a, jVar, null);
            }
        }
        jVar.f6917m = false;
        jVar.f6918n = false;
        jVar.f6919o = false;
        jVar.f6912h = null;
        jVar.f6921q = true;
        if (this.f6878K == jVar) {
            this.f6878K = null;
        }
    }

    public final ViewGroup W() {
        ViewGroup viewGroup;
        TypedArray typedArrayObtainStyledAttributes = this.f6837c.obtainStyledAttributes(p.j.f7867c0);
        if (!typedArrayObtainStyledAttributes.hasValue(p.j.f7875g0)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(p.j.p0, false)) {
            w(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(p.j.f7875g0, false)) {
            w(CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(p.j.f7877h0, false)) {
            w(CipherSuite.TLS_DH_anon_WITH_AES_256_CBC_SHA256);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(p.j.f7879i0, false)) {
            w(10);
        }
        this.f6847m = typedArrayObtainStyledAttributes.getBoolean(p.j.f7869d0, false);
        typedArrayObtainStyledAttributes.recycle();
        this.f6838d.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f6837c);
        if (this.f6848n) {
            viewGroup = this.f6846l ? (ViewGroup) layoutInflaterFrom.inflate(p.g.f7830n, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(p.g.f7829m, (ViewGroup) null);
            if (Build.VERSION.SDK_INT >= 21) {
                AbstractC0285k0.R(viewGroup, new b());
            } else {
                ((android.support.v7.widget.e) viewGroup).setOnFitSystemWindowsListener(new c());
            }
        } else if (this.f6847m) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(p.g.f7821e, (ViewGroup) null);
            this.f6845k = false;
            this.f6844j = false;
        } else if (this.f6844j) {
            TypedValue typedValue = new TypedValue();
            this.f6837c.getTheme().resolveAttribute(AbstractC0460a.f7710d, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new C0475d(this.f6837c, typedValue.resourceId) : this.f6837c).inflate(p.g.f7831o, (ViewGroup) null);
            F f2 = (F) viewGroup.findViewById(p.f.f7805o);
            this.f6887u = f2;
            f2.setWindowCallback(H());
            if (this.f6845k) {
                this.f6887u.h(CipherSuite.TLS_DH_anon_WITH_AES_256_CBC_SHA256);
            }
            if (this.f6874G) {
                this.f6887u.h(2);
            }
            if (this.f6875H) {
                this.f6887u.h(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.f6844j + ", windowActionBarOverlay: " + this.f6845k + ", android:windowIsFloating: " + this.f6847m + ", windowActionModeOverlay: " + this.f6846l + ", windowNoTitle: " + this.f6848n + " }");
        }
        if (this.f6887u == null) {
            this.f6872E = (TextView) viewGroup.findViewById(p.f.f7815y);
        }
        h0.d(viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(p.f.f7792b);
        ViewGroup viewGroup2 = (ViewGroup) this.f6838d.findViewById(R.id.content);
        if (viewGroup2 != null) {
            while (viewGroup2.getChildCount() > 0) {
                View childAt = viewGroup2.getChildAt(0);
                viewGroup2.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            viewGroup2.setId(-1);
            contentFrameLayout.setId(R.id.content);
            if (viewGroup2 instanceof FrameLayout) {
                ((FrameLayout) viewGroup2).setForeground(null);
            }
        }
        this.f6838d.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new d());
        return viewGroup;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View X(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z2 = Build.VERSION.SDK_INT < 21;
        if (this.f6886S == null) {
            this.f6886S = new o();
        }
        return this.f6886S.c(view, str, context, attributeSet, z2 && u0((ViewParent) view), z2, true, g0.a());
    }

    public void Y() {
        android.support.v7.view.menu.e eVar;
        F f2 = this.f6887u;
        if (f2 != null) {
            f2.i();
        }
        if (this.f6892z != null) {
            this.f6838d.getDecorView().removeCallbacks(this.f6868A);
            if (this.f6892z.isShowing()) {
                try {
                    this.f6892z.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.f6892z = null;
        }
        a0();
        j jVarD0 = d0(0, false);
        if (jVarD0 == null || (eVar = jVarD0.f6914j) == null) {
            return;
        }
        eVar.close();
    }

    public void Z(int i2) {
        j jVarD0;
        j jVarD02 = d0(i2, true);
        if (jVarD02.f6914j != null) {
            Bundle bundle = new Bundle();
            jVarD02.f6914j.P(bundle);
            if (bundle.size() > 0) {
                jVarD02.f6923s = bundle;
            }
            jVarD02.f6914j.c0();
            jVarD02.f6914j.clear();
        }
        jVarD02.f6922r = true;
        jVarD02.f6921q = true;
        if ((i2 != 108 && i2 != 0) || this.f6887u == null || (jVarD0 = d0(0, false)) == null) {
            return;
        }
        jVarD0.f6917m = false;
        q0(jVarD0, null);
    }

    @Override // android.support.v7.view.menu.e.a
    public void a(android.support.v7.view.menu.e eVar) {
        r0(eVar, true);
    }

    public void a0() {
        F1 f1 = this.f6869B;
        if (f1 != null) {
            f1.b();
        }
    }

    @Override // android.support.v7.view.menu.e.a
    public boolean b(android.support.v7.view.menu.e eVar, MenuItem menuItem) {
        j jVarC0;
        Window.Callback callbackH = H();
        if (callbackH == null || J() || (jVarC0 = c0(eVar.D())) == null) {
            return false;
        }
        return callbackH.onMenuItemSelected(jVarC0.f6905a, menuItem);
    }

    public final void b0() {
        if (this.f6870C) {
            return;
        }
        this.f6871D = W();
        CharSequence charSequenceG = G();
        if (!TextUtils.isEmpty(charSequenceG)) {
            N(charSequenceG);
        }
        Q();
        n0(this.f6871D);
        this.f6870C = true;
        j jVarD0 = d0(0, false);
        if (J()) {
            return;
        }
        if (jVarD0 == null || jVarD0.f6914j == null) {
            h0(CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256);
        }
    }

    @Override // o.f
    public void c(View view, ViewGroup.LayoutParams layoutParams) {
        b0();
        ((ViewGroup) this.f6871D.findViewById(R.id.content)).addView(view, layoutParams);
        this.f6839e.onContentChanged();
    }

    public j c0(Menu menu) {
        j[] jVarArr = this.f6877J;
        int length = jVarArr != null ? jVarArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            j jVar = jVarArr[i2];
            if (jVar != null && jVar.f6914j == menu) {
                return jVar;
            }
        }
        return null;
    }

    public j d0(int i2, boolean z2) {
        j[] jVarArr = this.f6877J;
        if (jVarArr == null || jVarArr.length <= i2) {
            j[] jVarArr2 = new j[i2 + 1];
            if (jVarArr != null) {
                System.arraycopy(jVarArr, 0, jVarArr2, 0, jVarArr.length);
            }
            this.f6877J = jVarArr2;
            jVarArr = jVarArr2;
        }
        j jVar = jVarArr[i2];
        if (jVar != null) {
            return jVar;
        }
        j jVar2 = new j(i2);
        jVarArr[i2] = jVar2;
        return jVar2;
    }

    public final boolean e0(j jVar) {
        View view = jVar.f6913i;
        if (view != null) {
            jVar.f6912h = view;
            return true;
        }
        if (jVar.f6914j == null) {
            return false;
        }
        if (this.f6889w == null) {
            this.f6889w = new k();
        }
        View view2 = (View) jVar.a(this.f6889w);
        jVar.f6912h = view2;
        return view2 != null;
    }

    public final boolean f0(j jVar) {
        jVar.d(F());
        jVar.f6911g = new i(jVar.f6916l);
        jVar.f6907c = 81;
        return true;
    }

    public final boolean g0(j jVar) {
        Resources.Theme themeNewTheme;
        Context context = this.f6837c;
        int i2 = jVar.f6905a;
        if ((i2 == 0 || i2 == 108) && this.f6887u != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(AbstractC0460a.f7710d, typedValue, true);
            if (typedValue.resourceId != 0) {
                themeNewTheme = context.getResources().newTheme();
                themeNewTheme.setTo(theme);
                themeNewTheme.applyStyle(typedValue.resourceId, true);
                themeNewTheme.resolveAttribute(AbstractC0460a.f7711e, typedValue, true);
            } else {
                theme.resolveAttribute(AbstractC0460a.f7711e, typedValue, true);
                themeNewTheme = null;
            }
            if (typedValue.resourceId != 0) {
                if (themeNewTheme == null) {
                    themeNewTheme = context.getResources().newTheme();
                    themeNewTheme.setTo(theme);
                }
                themeNewTheme.applyStyle(typedValue.resourceId, true);
            }
            if (themeNewTheme != null) {
                C0475d c0475d = new C0475d(context, 0);
                c0475d.getTheme().setTo(themeNewTheme);
                context = c0475d;
            }
        }
        android.support.v7.view.menu.e eVar = new android.support.v7.view.menu.e(context);
        eVar.Q(this);
        jVar.c(eVar);
        return true;
    }

    @Override // o.f
    public View h(int i2) {
        b0();
        return this.f6838d.findViewById(i2);
    }

    public final void h0(int i2) {
        this.f6881N = (1 << i2) | this.f6881N;
        if (this.f6880M) {
            return;
        }
        AbstractC0285k0.F(this.f6838d.getDecorView(), this.f6882O);
        this.f6880M = true;
    }

    public boolean i0() {
        AbstractC0473b abstractC0473b = this.f6890x;
        if (abstractC0473b != null) {
            abstractC0473b.c();
            return true;
        }
        AbstractC0430a abstractC0430aK = k();
        return abstractC0430aK != null && abstractC0430aK.g();
    }

    public boolean j0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            this.f6879L = (keyEvent.getFlags() & 128) != 0;
        } else if (i2 == 82) {
            k0(0, keyEvent);
            return true;
        }
        if (Build.VERSION.SDK_INT < 11) {
            K(i2, keyEvent);
        }
        return false;
    }

    public final boolean k0(int i2, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        j jVarD0 = d0(i2, true);
        if (jVarD0.f6919o) {
            return false;
        }
        return q0(jVarD0, keyEvent);
    }

    @Override // o.f
    public void l() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f6837c);
        if (layoutInflaterFrom.getFactory() == null) {
            AbstractC0253A.b(layoutInflaterFrom, this);
        } else {
            if (AbstractC0253A.a(layoutInflaterFrom) instanceof m) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public boolean l0(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            boolean z2 = this.f6879L;
            this.f6879L = false;
            j jVarD0 = d0(0, false);
            if (jVarD0 != null && jVarD0.f6919o) {
                if (!z2) {
                    V(jVarD0, true);
                }
                return true;
            }
            if (i0()) {
                return true;
            }
        } else if (i2 == 82) {
            m0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // o.f
    public void m() {
        AbstractC0430a abstractC0430aK = k();
        if (abstractC0430aK == null || !abstractC0430aK.k()) {
            h0(0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean m0(int r5, android.view.KeyEvent r6) {
        /*
            r4 = this;
            v.b r0 = r4.f6890x
            r1 = 0
            if (r0 == 0) goto L6
            return r1
        L6:
            r0 = 1
            o.m$j r2 = r4.d0(r5, r0)
            if (r5 != 0) goto L45
            x.F r5 = r4.f6887u
            if (r5 == 0) goto L45
            boolean r5 = r5.g()
            if (r5 == 0) goto L45
            android.content.Context r5 = r4.f6837c
            android.view.ViewConfiguration r5 = android.view.ViewConfiguration.get(r5)
            boolean r5 = k.AbstractC0312t1.a(r5)
            if (r5 != 0) goto L45
            x.F r5 = r4.f6887u
            boolean r5 = r5.b()
            if (r5 != 0) goto L3e
            boolean r5 = r4.J()
            if (r5 != 0) goto L64
            boolean r5 = r4.q0(r2, r6)
            if (r5 == 0) goto L64
            x.F r5 = r4.f6887u
            boolean r0 = r5.e()
            goto L6a
        L3e:
            x.F r5 = r4.f6887u
            boolean r0 = r5.d()
            goto L6a
        L45:
            boolean r5 = r2.f6919o
            if (r5 != 0) goto L66
            boolean r3 = r2.f6918n
            if (r3 == 0) goto L4e
            goto L66
        L4e:
            boolean r5 = r2.f6917m
            if (r5 == 0) goto L64
            boolean r5 = r2.f6922r
            if (r5 == 0) goto L5d
            r2.f6917m = r1
            boolean r5 = r4.q0(r2, r6)
            goto L5e
        L5d:
            r5 = 1
        L5e:
            if (r5 == 0) goto L64
            r4.o0(r2, r6)
            goto L6a
        L64:
            r0 = 0
            goto L6a
        L66:
            r4.V(r2, r0)
            r0 = r5
        L6a:
            if (r0 == 0) goto L83
            android.content.Context r5 = r4.f6837c
            java.lang.String r6 = "audio"
            java.lang.Object r5 = r5.getSystemService(r6)
            android.media.AudioManager r5 = (android.media.AudioManager) r5
            if (r5 == 0) goto L7c
            r5.playSoundEffect(r1)
            goto L83
        L7c:
            java.lang.String r5 = "AppCompatDelegate"
            java.lang.String r6 = "Couldn't get audio manager"
            android.util.Log.w(r5, r6)
        L83:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: o.m.m0(int, android.view.KeyEvent):boolean");
    }

    @Override // o.f
    public void o(Configuration configuration) {
        AbstractC0430a abstractC0430aK;
        if (this.f6844j && this.f6870C && (abstractC0430aK = k()) != null) {
            abstractC0430aK.m(configuration);
        }
        C0508k.m().y(this.f6837c);
        d();
    }

    public final void o0(j jVar, KeyEvent keyEvent) {
        int i2;
        ViewGroup.LayoutParams layoutParams;
        if (jVar.f6919o || J()) {
            return;
        }
        if (jVar.f6905a == 0) {
            Context context = this.f6837c;
            boolean z2 = (context.getResources().getConfiguration().screenLayout & 15) == 4;
            boolean z3 = context.getApplicationInfo().targetSdkVersion >= 11;
            if (z2 && z3) {
                return;
            }
        }
        Window.Callback callbackH = H();
        if (callbackH != null && !callbackH.onMenuOpened(jVar.f6905a, jVar.f6914j)) {
            V(jVar, true);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f6837c.getSystemService("window");
        if (windowManager != null && q0(jVar, keyEvent)) {
            ViewGroup viewGroup = jVar.f6911g;
            if (viewGroup != null && !jVar.f6921q) {
                View view = jVar.f6913i;
                if (view != null && (layoutParams = view.getLayoutParams()) != null && layoutParams.width == -1) {
                    i2 = -1;
                }
                jVar.f6918n = false;
                WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i2, -2, jVar.f6908d, jVar.f6909e, 1002, 8519680, -3);
                layoutParams2.gravity = jVar.f6907c;
                layoutParams2.windowAnimations = jVar.f6910f;
                windowManager.addView(jVar.f6911g, layoutParams2);
                jVar.f6919o = true;
            }
            if (viewGroup == null) {
                if (!f0(jVar) || jVar.f6911g == null) {
                    return;
                }
            } else if (jVar.f6921q && viewGroup.getChildCount() > 0) {
                jVar.f6911g.removeAllViews();
            }
            if (!e0(jVar) || !jVar.b()) {
                return;
            }
            ViewGroup.LayoutParams layoutParams3 = jVar.f6912h.getLayoutParams();
            if (layoutParams3 == null) {
                layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
            }
            jVar.f6911g.setBackgroundResource(jVar.f6906b);
            ViewParent parent = jVar.f6912h.getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) parent).removeView(jVar.f6912h);
            }
            jVar.f6911g.addView(jVar.f6912h, layoutParams3);
            if (!jVar.f6912h.hasFocus()) {
                jVar.f6912h.requestFocus();
            }
            i2 = -2;
            jVar.f6918n = false;
            WindowManager.LayoutParams layoutParams22 = new WindowManager.LayoutParams(i2, -2, jVar.f6908d, jVar.f6909e, 1002, 8519680, -3);
            layoutParams22.gravity = jVar.f6907c;
            layoutParams22.windowAnimations = jVar.f6910f;
            windowManager.addView(jVar.f6911g, layoutParams22);
            jVar.f6919o = true;
        }
    }

    @Override // k.H
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewR = R(view, str, context, attributeSet);
        return viewR != null ? viewR : X(view, str, context, attributeSet);
    }

    @Override // o.f
    public void p(Bundle bundle) {
        Window.Callback callback = this.f6839e;
        if (!(callback instanceof Activity) || AbstractC0137f0.c((Activity) callback) == null) {
            return;
        }
        AbstractC0430a abstractC0430aO = O();
        if (abstractC0430aO == null) {
            this.f6883P = true;
        } else {
            abstractC0430aO.q(true);
        }
    }

    public final boolean p0(j jVar, int i2, KeyEvent keyEvent, int i3) {
        android.support.v7.view.menu.e eVar;
        boolean zPerformShortcut = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((jVar.f6917m || q0(jVar, keyEvent)) && (eVar = jVar.f6914j) != null) {
            zPerformShortcut = eVar.performShortcut(i2, keyEvent, i3);
        }
        if (zPerformShortcut && (i3 & 1) == 0 && this.f6887u == null) {
            V(jVar, true);
        }
        return zPerformShortcut;
    }

    @Override // o.g, o.f
    public void q() {
        super.q();
        AbstractC0430a abstractC0430a = this.f6842h;
        if (abstractC0430a != null) {
            abstractC0430a.n();
        }
    }

    public final boolean q0(j jVar, KeyEvent keyEvent) {
        F f2;
        F f3;
        F f4;
        if (J()) {
            return false;
        }
        if (jVar.f6917m) {
            return true;
        }
        j jVar2 = this.f6878K;
        if (jVar2 != null && jVar2 != jVar) {
            V(jVar2, false);
        }
        Window.Callback callbackH = H();
        if (callbackH != null) {
            jVar.f6913i = callbackH.onCreatePanelView(jVar.f6905a);
        }
        int i2 = jVar.f6905a;
        boolean z2 = i2 == 0 || i2 == 108;
        if (z2 && (f4 = this.f6887u) != null) {
            f4.f();
        }
        if (jVar.f6913i == null) {
            if (z2) {
                O();
            }
            android.support.v7.view.menu.e eVar = jVar.f6914j;
            if (eVar == null || jVar.f6922r) {
                if (eVar == null && (!g0(jVar) || jVar.f6914j == null)) {
                    return false;
                }
                if (z2 && this.f6887u != null) {
                    if (this.f6888v == null) {
                        this.f6888v = new g();
                    }
                    this.f6887u.c(jVar.f6914j, this.f6888v);
                }
                jVar.f6914j.c0();
                if (!callbackH.onCreatePanelMenu(jVar.f6905a, jVar.f6914j)) {
                    jVar.c(null);
                    if (z2 && (f2 = this.f6887u) != null) {
                        f2.c(null, this.f6888v);
                    }
                    return false;
                }
                jVar.f6922r = false;
            }
            jVar.f6914j.c0();
            Bundle bundle = jVar.f6923s;
            if (bundle != null) {
                jVar.f6914j.O(bundle);
                jVar.f6923s = null;
            }
            if (!callbackH.onPreparePanel(0, jVar.f6913i, jVar.f6914j)) {
                if (z2 && (f3 = this.f6887u) != null) {
                    f3.c(null, this.f6888v);
                }
                jVar.f6914j.b0();
                return false;
            }
            boolean z3 = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            jVar.f6920p = z3;
            jVar.f6914j.setQwertyMode(z3);
            jVar.f6914j.b0();
        }
        jVar.f6917m = true;
        jVar.f6918n = false;
        this.f6878K = jVar;
        return true;
    }

    @Override // o.f
    public void r(Bundle bundle) {
        b0();
    }

    public final void r0(android.support.v7.view.menu.e eVar, boolean z2) {
        F f2 = this.f6887u;
        if (f2 == null || !f2.g() || (AbstractC0312t1.a(ViewConfiguration.get(this.f6837c)) && !this.f6887u.a())) {
            j jVarD0 = d0(0, true);
            jVarD0.f6921q = true;
            V(jVarD0, false);
            o0(jVarD0, null);
            return;
        }
        Window.Callback callbackH = H();
        if (this.f6887u.b() && z2) {
            this.f6887u.d();
            if (J()) {
                return;
            }
            callbackH.onPanelClosed(CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256, d0(0, true).f6914j);
            return;
        }
        if (callbackH == null || J()) {
            return;
        }
        if (this.f6880M && (this.f6881N & 1) != 0) {
            this.f6838d.getDecorView().removeCallbacks(this.f6882O);
            this.f6882O.run();
        }
        j jVarD02 = d0(0, true);
        android.support.v7.view.menu.e eVar2 = jVarD02.f6914j;
        if (eVar2 == null || jVarD02.f6922r || !callbackH.onPreparePanel(0, jVarD02.f6913i, eVar2)) {
            return;
        }
        callbackH.onMenuOpened(CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256, jVarD02.f6914j);
        this.f6887u.e();
    }

    @Override // o.f
    public void s() {
        AbstractC0430a abstractC0430aK = k();
        if (abstractC0430aK != null) {
            abstractC0430aK.r(true);
        }
    }

    public final int s0(int i2) {
        if (i2 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256;
        }
        if (i2 != 9) {
            return i2;
        }
        Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
        return CipherSuite.TLS_DH_anon_WITH_AES_256_CBC_SHA256;
    }

    public final boolean t0() {
        ViewGroup viewGroup;
        return this.f6870C && (viewGroup = this.f6871D) != null && AbstractC0285k0.z(viewGroup);
    }

    public final boolean u0(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.f6838d.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || AbstractC0285k0.y((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    @Override // o.f
    public void v() {
        AbstractC0430a abstractC0430aK = k();
        if (abstractC0430aK != null) {
            abstractC0430aK.r(false);
        }
    }

    public AbstractC0473b v0(AbstractC0473b.a aVar) {
        o.e eVar;
        if (aVar == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        AbstractC0473b abstractC0473b = this.f6890x;
        if (abstractC0473b != null) {
            abstractC0473b.c();
        }
        h hVar = new h(aVar);
        AbstractC0430a abstractC0430aK = k();
        if (abstractC0430aK != null) {
            AbstractC0473b abstractC0473bT = abstractC0430aK.t(hVar);
            this.f6890x = abstractC0473bT;
            if (abstractC0473bT != null && (eVar = this.f6841g) != null) {
                eVar.e(abstractC0473bT);
            }
        }
        if (this.f6890x == null) {
            this.f6890x = w0(hVar);
        }
        return this.f6890x;
    }

    @Override // o.f
    public boolean w(int i2) {
        int iS0 = s0(i2);
        if (this.f6848n && iS0 == 108) {
            return false;
        }
        if (this.f6844j && iS0 == 1) {
            this.f6844j = false;
        }
        if (iS0 == 1) {
            x0();
            this.f6848n = true;
            return true;
        }
        if (iS0 == 2) {
            x0();
            this.f6874G = true;
            return true;
        }
        if (iS0 == 5) {
            x0();
            this.f6875H = true;
            return true;
        }
        if (iS0 == 10) {
            x0();
            this.f6846l = true;
            return true;
        }
        if (iS0 == 108) {
            x0();
            this.f6844j = true;
            return true;
        }
        if (iS0 != 109) {
            return this.f6838d.requestFeature(iS0);
        }
        x0();
        this.f6845k = true;
        return true;
    }

    public AbstractC0473b w0(AbstractC0473b.a aVar) {
        AbstractC0473b abstractC0473bH;
        Context c0475d;
        o.e eVar;
        a0();
        AbstractC0473b abstractC0473b = this.f6890x;
        if (abstractC0473b != null) {
            abstractC0473b.c();
        }
        if (!(aVar instanceof h)) {
            aVar = new h(aVar);
        }
        if (this.f6841g == null || J()) {
            abstractC0473bH = null;
        } else {
            try {
                abstractC0473bH = this.f6841g.h(aVar);
            } catch (AbstractMethodError unused) {
                abstractC0473bH = null;
            }
        }
        if (abstractC0473bH != null) {
            this.f6890x = abstractC0473bH;
        } else {
            if (this.f6891y == null) {
                if (this.f6847m) {
                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = this.f6837c.getTheme();
                    theme.resolveAttribute(AbstractC0460a.f7710d, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Resources.Theme themeNewTheme = this.f6837c.getResources().newTheme();
                        themeNewTheme.setTo(theme);
                        themeNewTheme.applyStyle(typedValue.resourceId, true);
                        c0475d = new C0475d(this.f6837c, 0);
                        c0475d.getTheme().setTo(themeNewTheme);
                    } else {
                        c0475d = this.f6837c;
                    }
                    this.f6891y = new ActionBarContextView(c0475d);
                    PopupWindow popupWindow = new PopupWindow(c0475d, (AttributeSet) null, AbstractC0460a.f7712f);
                    this.f6892z = popupWindow;
                    AbstractC0414B.b(popupWindow, 2);
                    this.f6892z.setContentView(this.f6891y);
                    this.f6892z.setWidth(-1);
                    c0475d.getTheme().resolveAttribute(AbstractC0460a.f7708b, typedValue, true);
                    this.f6891y.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, c0475d.getResources().getDisplayMetrics()));
                    this.f6892z.setHeight(-2);
                    this.f6868A = new e();
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.f6871D.findViewById(p.f.f7798h);
                    if (viewStubCompat != null) {
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(F()));
                        this.f6891y = (ActionBarContextView) viewStubCompat.a();
                    }
                }
            }
            if (this.f6891y != null) {
                a0();
                this.f6891y.k();
                v.e eVar2 = new v.e(this.f6891y.getContext(), this.f6891y, aVar, this.f6892z == null);
                if (aVar.c(eVar2, eVar2.e())) {
                    eVar2.k();
                    this.f6891y.h(eVar2);
                    this.f6890x = eVar2;
                    if (t0()) {
                        AbstractC0285k0.K(this.f6891y, 0.0f);
                        F1 f1A = AbstractC0285k0.a(this.f6891y).a(1.0f);
                        this.f6869B = f1A;
                        f1A.f(new f());
                    } else {
                        AbstractC0285k0.K(this.f6891y, 1.0f);
                        this.f6891y.setVisibility(0);
                        this.f6891y.sendAccessibilityEvent(32);
                        if (this.f6891y.getParent() != null) {
                            AbstractC0285k0.H((View) this.f6891y.getParent());
                        }
                    }
                    if (this.f6892z != null) {
                        this.f6838d.getDecorView().post(this.f6868A);
                    }
                } else {
                    this.f6890x = null;
                }
            }
        }
        AbstractC0473b abstractC0473b2 = this.f6890x;
        if (abstractC0473b2 != null && (eVar = this.f6841g) != null) {
            eVar.e(abstractC0473b2);
        }
        return this.f6890x;
    }

    public final void x0() {
        if (this.f6870C) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    @Override // o.f
    public void y(int i2) {
        b0();
        ViewGroup viewGroup = (ViewGroup) this.f6871D.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f6837c).inflate(i2, viewGroup);
        this.f6839e.onContentChanged();
    }

    public int y0(int i2) {
        boolean z2;
        boolean z3;
        ActionBarContextView actionBarContextView = this.f6891y;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z2 = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f6891y.getLayoutParams();
            if (this.f6891y.isShown()) {
                if (this.f6884Q == null) {
                    this.f6884Q = new Rect();
                    this.f6885R = new Rect();
                }
                Rect rect = this.f6884Q;
                Rect rect2 = this.f6885R;
                rect.set(0, i2, 0, 0);
                h0.b(this.f6871D, rect, rect2);
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i2 : 0)) {
                    marginLayoutParams.topMargin = i2;
                    View view = this.f6873F;
                    if (view == null) {
                        View view2 = new View(this.f6837c);
                        this.f6873F = view2;
                        view2.setBackgroundColor(this.f6837c.getResources().getColor(AbstractC0462c.f7735a));
                        this.f6871D.addView(this.f6873F, -1, new ViewGroup.LayoutParams(-1, i2));
                    } else {
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        if (layoutParams.height != i2) {
                            layoutParams.height = i2;
                            this.f6873F.setLayoutParams(layoutParams);
                        }
                    }
                    z3 = true;
                } else {
                    z3 = false;
                }
                z = this.f6873F != null;
                if (!this.f6846l && z) {
                    i2 = 0;
                }
                boolean z4 = z;
                z = z3;
                z2 = z4;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z2 = false;
            } else {
                z2 = false;
                z = false;
            }
            if (z) {
                this.f6891y.setLayoutParams(marginLayoutParams);
            }
        }
        View view3 = this.f6873F;
        if (view3 != null) {
            view3.setVisibility(z2 ? 0 : 8);
        }
        return i2;
    }

    @Override // o.f
    public void z(View view) {
        b0();
        ViewGroup viewGroup = (ViewGroup) this.f6871D.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.f6839e.onContentChanged();
    }

    public class d implements ContentFrameLayout.a {
        public d() {
        }

        @Override // android.support.v7.widget.ContentFrameLayout.a
        public void onDetachedFromWindow() {
            m.this.Y();
        }

        @Override // android.support.v7.widget.ContentFrameLayout.a
        public void a() {
        }
    }

    public void n0(ViewGroup viewGroup) {
    }
}
