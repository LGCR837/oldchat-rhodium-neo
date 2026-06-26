package android.support.v7.widget;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.view.menu.i;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import k.AbstractC0285k0;
import k.C0261c0;
import k.F1;
import k.InterfaceC0258b0;
import k.T1;
import k.U1;
import n.I;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import p.AbstractC0460a;
import x.F;
import x.G;
import x.h0;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarOverlayLayout extends ViewGroup implements F, InterfaceC0258b0 {

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public static final int[] f1166B = {AbstractC0460a.f7708b, R.attr.windowContentOverlay};

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final C0261c0 f1167A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1168a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1169b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ContentFrameLayout f1170c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ActionBarContainer f1171d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public G f1172e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f1173f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1174g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1175h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1176i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1177j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f1178k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1179l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1180m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Rect f1181n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Rect f1182o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Rect f1183p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Rect f1184q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Rect f1185r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Rect f1186s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public d f1187t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f1188u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public I f1189v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public F1 f1190w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final T1 f1191x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final Runnable f1192y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final Runnable f1193z;

    public class a extends U1 {
        public a() {
        }

        @Override // k.T1
        public void a(View view) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.f1190w = null;
            actionBarOverlayLayout.f1178k = false;
        }

        @Override // k.U1, k.T1
        public void c(View view) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.f1190w = null;
            actionBarOverlayLayout.f1178k = false;
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.o();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.f1190w = AbstractC0285k0.a(actionBarOverlayLayout.f1171d).k(0.0f).f(ActionBarOverlayLayout.this.f1191x);
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.o();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.f1190w = AbstractC0285k0.a(actionBarOverlayLayout.f1171d).k(-ActionBarOverlayLayout.this.f1171d.getHeight()).f(ActionBarOverlayLayout.this.f1191x);
        }
    }

    public interface d {
        void a(boolean z2);

        void b();

        void c();

        void d(int i2);

        void e();

        void f();
    }

    public static class e extends ViewGroup.MarginLayoutParams {
        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public e(int i2, int i3) {
            super(i2, i3);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1169b = 0;
        this.f1181n = new Rect();
        this.f1182o = new Rect();
        this.f1183p = new Rect();
        this.f1184q = new Rect();
        this.f1185r = new Rect();
        this.f1186s = new Rect();
        this.f1188u = 600;
        this.f1191x = new a();
        this.f1192y = new b();
        this.f1193z = new c();
        p(context);
        this.f1167A = new C0261c0(this);
    }

    @Override // x.F
    public boolean a() {
        t();
        return this.f1172e.a();
    }

    @Override // x.F
    public boolean b() {
        t();
        return this.f1172e.b();
    }

    @Override // x.F
    public void c(Menu menu, i.a aVar) {
        t();
        this.f1172e.c(menu, aVar);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e;
    }

    @Override // x.F
    public boolean d() {
        t();
        return this.f1172e.d();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f1173f == null || this.f1174g) {
            return;
        }
        int bottom = this.f1171d.getVisibility() == 0 ? (int) (this.f1171d.getBottom() + AbstractC0285k0.s(this.f1171d) + 0.5f) : 0;
        this.f1173f.setBounds(0, bottom, getWidth(), this.f1173f.getIntrinsicHeight() + bottom);
        this.f1173f.draw(canvas);
    }

    @Override // x.F
    public boolean e() {
        t();
        return this.f1172e.e();
    }

    @Override // x.F
    public void f() {
        t();
        this.f1172e.f();
    }

    @Override // android.view.View
    public boolean fitSystemWindows(Rect rect) {
        t();
        AbstractC0285k0.t(this);
        boolean zK = k(this.f1171d, rect, true, true, false, true);
        this.f1184q.set(rect);
        h0.b(this, this.f1184q, this.f1181n);
        if (!this.f1182o.equals(this.f1181n)) {
            this.f1182o.set(this.f1181n);
            zK = true;
        }
        if (zK) {
            requestLayout();
        }
        return true;
    }

    @Override // x.F
    public boolean g() {
        t();
        return this.f1172e.g();
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f1171d;
        if (actionBarContainer != null) {
            return -((int) AbstractC0285k0.s(actionBarContainer));
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.f1167A.a();
    }

    public CharSequence getTitle() {
        t();
        return this.f1172e.getTitle();
    }

    @Override // x.F
    public void h(int i2) {
        t();
        if (i2 == 2) {
            this.f1172e.r();
        } else if (i2 == 5) {
            this.f1172e.t();
        } else {
            if (i2 != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // x.F
    public void i() {
        t();
        this.f1172e.h();
    }

    public final void j() {
        o();
        this.f1193z.run();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean k(android.view.View r3, android.graphics.Rect r4, boolean r5, boolean r6, boolean r7, boolean r8) {
        /*
            r2 = this;
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            android.support.v7.widget.ActionBarOverlayLayout$e r3 = (android.support.v7.widget.ActionBarOverlayLayout.e) r3
            r0 = 1
            if (r5 == 0) goto L13
            int r5 = r3.leftMargin
            int r1 = r4.left
            if (r5 == r1) goto L13
            r3.leftMargin = r1
            r5 = 1
            goto L14
        L13:
            r5 = 0
        L14:
            if (r6 == 0) goto L1f
            int r6 = r3.topMargin
            int r1 = r4.top
            if (r6 == r1) goto L1f
            r3.topMargin = r1
            r5 = 1
        L1f:
            if (r8 == 0) goto L2a
            int r6 = r3.rightMargin
            int r8 = r4.right
            if (r6 == r8) goto L2a
            r3.rightMargin = r8
            r5 = 1
        L2a:
            if (r7 == 0) goto L35
            int r6 = r3.bottomMargin
            int r4 = r4.bottom
            if (r6 == r4) goto L35
            r3.bottomMargin = r4
            goto L36
        L35:
            r0 = r5
        L36:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionBarOverlayLayout.k(android.view.View, android.graphics.Rect, boolean, boolean, boolean, boolean):boolean");
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public e generateDefaultLayoutParams() {
        return new e(-1, -1);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public e generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final G n(View view) {
        if (view instanceof G) {
            return (G) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    public void o() {
        removeCallbacks(this.f1192y);
        removeCallbacks(this.f1193z);
        F1 f1 = this.f1190w;
        if (f1 != null) {
            f1.b();
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        p(getContext());
        AbstractC0285k0.H(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        o();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        getPaddingRight();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i7 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin + paddingLeft;
                int i8 = ((ViewGroup.MarginLayoutParams) eVar).topMargin + paddingTop;
                childAt.layout(i7, i8, measuredWidth + i7, measuredHeight + i8);
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        t();
        measureChildWithMargins(this.f1171d, i2, 0, i3, 0);
        e eVar = (e) this.f1171d.getLayoutParams();
        int measuredHeight = 0;
        int iMax = Math.max(0, this.f1171d.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin);
        int iMax2 = Math.max(0, this.f1171d.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
        int iA = h0.a(0, AbstractC0285k0.m(this.f1171d));
        boolean z2 = (AbstractC0285k0.t(this) & 256) != 0;
        if (z2) {
            measuredHeight = this.f1168a;
            if (this.f1176i && this.f1171d.getTabContainer() != null) {
                measuredHeight += this.f1168a;
            }
        } else if (this.f1171d.getVisibility() != 8) {
            measuredHeight = this.f1171d.getMeasuredHeight();
        }
        this.f1183p.set(this.f1181n);
        this.f1185r.set(this.f1184q);
        if (this.f1175h || z2) {
            Rect rect = this.f1185r;
            rect.top += measuredHeight;
            rect.bottom = rect.bottom;
        } else {
            Rect rect2 = this.f1183p;
            rect2.top += measuredHeight;
            rect2.bottom = rect2.bottom;
        }
        k(this.f1170c, this.f1183p, true, true, true, true);
        if (!this.f1186s.equals(this.f1185r)) {
            this.f1186s.set(this.f1185r);
            this.f1170c.a(this.f1185r);
        }
        measureChildWithMargins(this.f1170c, i2, 0, i3, 0);
        e eVar2 = (e) this.f1170c.getLayoutParams();
        int iMax3 = Math.max(iMax, this.f1170c.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar2).leftMargin + ((ViewGroup.MarginLayoutParams) eVar2).rightMargin);
        int iMax4 = Math.max(iMax2, this.f1170c.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar2).topMargin + ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin);
        int iA2 = h0.a(iA, AbstractC0285k0.m(this.f1170c));
        setMeasuredDimension(AbstractC0285k0.I(Math.max(iMax3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i2, iA2), AbstractC0285k0.I(Math.max(iMax4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i3, iA2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public boolean onNestedFling(View view, float f2, float f3, boolean z2) {
        if (!this.f1177j || !z2) {
            return false;
        }
        if (v(f2, f3)) {
            j();
        } else {
            u();
        }
        this.f1178k = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        int i6 = this.f1179l + i3;
        this.f1179l = i6;
        setActionBarHideOffset(i6);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public void onNestedScrollAccepted(View view, View view2, int i2) {
        this.f1167A.b(view, view2, i2);
        this.f1179l = getActionBarHideOffset();
        o();
        d dVar = this.f1187t;
        if (dVar != null) {
            dVar.c();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        if ((i2 & 2) == 0 || this.f1171d.getVisibility() != 0) {
            return false;
        }
        return this.f1177j;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public void onStopNestedScroll(View view) {
        if (this.f1177j && !this.f1178k) {
            if (this.f1179l <= this.f1171d.getHeight()) {
                s();
            } else {
                r();
            }
        }
        d dVar = this.f1187t;
        if (dVar != null) {
            dVar.f();
        }
    }

    @Override // android.view.View
    public void onWindowSystemUiVisibilityChanged(int i2) {
        if (Build.VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(i2);
        }
        t();
        int i3 = this.f1180m ^ i2;
        this.f1180m = i2;
        boolean z2 = (i2 & 4) == 0;
        boolean z3 = (i2 & 256) != 0;
        d dVar = this.f1187t;
        if (dVar != null) {
            dVar.a(!z3);
            if (z2 || !z3) {
                this.f1187t.b();
            } else {
                this.f1187t.e();
            }
        }
        if ((i3 & 256) == 0 || this.f1187t == null) {
            return;
        }
        AbstractC0285k0.H(this);
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        this.f1169b = i2;
        d dVar = this.f1187t;
        if (dVar != null) {
            dVar.d(i2);
        }
    }

    public final void p(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(f1166B);
        this.f1168a = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.f1173f = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.f1174g = context.getApplicationInfo().targetSdkVersion < 19;
        this.f1189v = I.c(context);
    }

    public boolean q() {
        return this.f1175h;
    }

    public final void r() {
        o();
        postDelayed(this.f1193z, 600L);
    }

    public final void s() {
        o();
        postDelayed(this.f1192y, 600L);
    }

    public void setActionBarHideOffset(int i2) {
        o();
        AbstractC0285k0.X(this.f1171d, -Math.max(0, Math.min(i2, this.f1171d.getHeight())));
    }

    public void setActionBarVisibilityCallback(d dVar) {
        this.f1187t = dVar;
        if (getWindowToken() != null) {
            this.f1187t.d(this.f1169b);
            int i2 = this.f1180m;
            if (i2 != 0) {
                onWindowSystemUiVisibilityChanged(i2);
                AbstractC0285k0.H(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z2) {
        this.f1176i = z2;
    }

    public void setHideOnContentScrollEnabled(boolean z2) {
        if (z2 != this.f1177j) {
            this.f1177j = z2;
            if (z2) {
                return;
            }
            o();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i2) {
        t();
        this.f1172e.setIcon(i2);
    }

    public void setLogo(int i2) {
        t();
        this.f1172e.k(i2);
    }

    public void setOverlayMode(boolean z2) {
        this.f1175h = z2;
        this.f1174g = z2 && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z2) {
    }

    public void setUiOptions(int i2) {
    }

    @Override // x.F
    public void setWindowCallback(Window.Callback callback) {
        t();
        this.f1172e.setWindowCallback(callback);
    }

    @Override // x.F
    public void setWindowTitle(CharSequence charSequence) {
        t();
        this.f1172e.setWindowTitle(charSequence);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public void t() {
        if (this.f1170c == null) {
            this.f1170c = (ContentFrameLayout) findViewById(p.f.f7792b);
            this.f1171d = (ActionBarContainer) findViewById(p.f.f7793c);
            this.f1172e = n(findViewById(p.f.f7791a));
        }
    }

    public final void u() {
        o();
        this.f1192y.run();
    }

    public final boolean v(float f2, float f3) {
        this.f1189v.e(0, 0, 0, (int) f3, 0, 0, PKIFailureInfo.systemUnavail, Integer.MAX_VALUE);
        return this.f1189v.k() > this.f1171d.getHeight();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new e(layoutParams);
    }

    public void setIcon(Drawable drawable) {
        t();
        this.f1172e.setIcon(drawable);
    }
}
