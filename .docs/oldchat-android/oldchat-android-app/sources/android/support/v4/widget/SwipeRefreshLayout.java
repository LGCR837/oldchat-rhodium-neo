package android.support.v4.widget;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import k.AbstractC0285k0;
import k.C0255a0;
import k.C0261c0;
import k.InterfaceC0258b0;
import k.W;
import k.Z;
import n.C0413A;
import n.C0419b;

/* JADX INFO: loaded from: classes.dex */
public class SwipeRefreshLayout extends ViewGroup implements InterfaceC0258b0, Z {

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public static final String f911M = "SwipeRefreshLayout";

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public static final int[] f912N = {R.attr.enabled};

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public Animation f913A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public Animation f914B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public Animation f915C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public Animation f916D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public Animation f917E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public float f918F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f919G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public int f920H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f921I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public Animation.AnimationListener f922J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final Animation f923K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final Animation f924L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f925a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public j f926b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f927c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f928d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f929e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f930f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C0261c0 f931g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C0255a0 f932h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int[] f933i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int[] f934j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f935k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f936l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f937m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f938n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f939o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f940p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f941q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f942r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f943s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final DecelerateInterpolator f944t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public C0419b f945u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f946v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f947w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public float f948x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f949y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public C0413A f950z;

    public class a implements Animation.AnimationListener {
        public a() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            j jVar;
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            if (!swipeRefreshLayout.f927c) {
                swipeRefreshLayout.m();
                return;
            }
            swipeRefreshLayout.f950z.setAlpha(255);
            SwipeRefreshLayout.this.f950z.start();
            SwipeRefreshLayout swipeRefreshLayout2 = SwipeRefreshLayout.this;
            if (swipeRefreshLayout2.f919G && (jVar = swipeRefreshLayout2.f926b) != null) {
                jVar.a();
            }
            SwipeRefreshLayout swipeRefreshLayout3 = SwipeRefreshLayout.this;
            swipeRefreshLayout3.f937m = swipeRefreshLayout3.f945u.getTop();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    public class b extends Animation {
        public b() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f2, Transformation transformation) {
            SwipeRefreshLayout.this.setAnimationProgress(f2);
        }
    }

    public class c extends Animation {
        public c() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f2, Transformation transformation) {
            SwipeRefreshLayout.this.setAnimationProgress(1.0f - f2);
        }
    }

    public class d extends Animation {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f954a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f955b;

        public d(int i2, int i3) {
            this.f954a = i2;
            this.f955b = i3;
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f2, Transformation transformation) {
            SwipeRefreshLayout.this.f950z.setAlpha((int) (this.f954a + ((this.f955b - r0) * f2)));
        }
    }

    public class e implements Animation.AnimationListener {
        public e() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            if (swipeRefreshLayout.f942r) {
                return;
            }
            swipeRefreshLayout.t(null);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    public class f extends Animation {
        public f() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f2, Transformation transformation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            int iAbs = !swipeRefreshLayout.f921I ? (int) (swipeRefreshLayout.f918F - Math.abs(swipeRefreshLayout.f949y)) : (int) swipeRefreshLayout.f918F;
            SwipeRefreshLayout swipeRefreshLayout2 = SwipeRefreshLayout.this;
            SwipeRefreshLayout.this.o((swipeRefreshLayout2.f947w + ((int) ((iAbs - r1) * f2))) - swipeRefreshLayout2.f945u.getTop(), false);
            SwipeRefreshLayout.this.f950z.d(1.0f - f2);
        }
    }

    public class g extends Animation {
        public g() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f2, Transformation transformation) {
            SwipeRefreshLayout.this.k(f2);
        }
    }

    public class h extends Animation {
        public h() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f2, Transformation transformation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            float f3 = swipeRefreshLayout.f948x;
            swipeRefreshLayout.setAnimationProgress(f3 + ((-f3) * f2));
            SwipeRefreshLayout.this.k(f2);
        }
    }

    public interface i {
    }

    public interface j {
        void a();
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f927c = false;
        this.f929e = -1.0f;
        this.f933i = new int[2];
        this.f934j = new int[2];
        this.f941q = -1;
        this.f946v = -1;
        this.f922J = new a();
        this.f923K = new f();
        this.f924L = new g();
        this.f928d = ViewConfiguration.get(context).getScaledTouchSlop();
        this.f936l = getResources().getInteger(R.integer.config_mediumAnimTime);
        setWillNotDraw(false);
        this.f944t = new DecelerateInterpolator(2.0f);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.f920H = (int) (displayMetrics.density * 40.0f);
        d();
        AbstractC0285k0.N(this, true);
        float f2 = displayMetrics.density * 64.0f;
        this.f918F = f2;
        this.f929e = f2;
        this.f931g = new C0261c0(this);
        this.f932h = new C0255a0(this);
        setNestedScrollingEnabled(true);
        int i2 = -this.f920H;
        this.f937m = i2;
        this.f949y = i2;
        k(1.0f);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f912N);
        setEnabled(typedArrayObtainStyledAttributes.getBoolean(0, true));
        typedArrayObtainStyledAttributes.recycle();
    }

    private void l(MotionEvent motionEvent) {
        int iA = W.a(motionEvent);
        if (motionEvent.getPointerId(iA) == this.f941q) {
            this.f941q = motionEvent.getPointerId(iA == 0 ? 1 : 0);
        }
    }

    private void setColorViewAlpha(int i2) {
        this.f945u.getBackground().setAlpha(i2);
        this.f950z.setAlpha(i2);
    }

    public final void a(int i2, Animation.AnimationListener animationListener) {
        this.f947w = i2;
        this.f923K.reset();
        this.f923K.setDuration(200L);
        this.f923K.setInterpolator(this.f944t);
        if (animationListener != null) {
            this.f945u.b(animationListener);
        }
        this.f945u.clearAnimation();
        this.f945u.startAnimation(this.f923K);
    }

    public final void b(int i2, Animation.AnimationListener animationListener) {
        if (this.f942r) {
            u(i2, animationListener);
            return;
        }
        this.f947w = i2;
        this.f924L.reset();
        this.f924L.setDuration(200L);
        this.f924L.setInterpolator(this.f944t);
        if (animationListener != null) {
            this.f945u.b(animationListener);
        }
        this.f945u.clearAnimation();
        this.f945u.startAnimation(this.f924L);
    }

    public boolean c() {
        if (Build.VERSION.SDK_INT >= 14) {
            return AbstractC0285k0.c(this.f925a, -1);
        }
        View view = this.f925a;
        if (!(view instanceof AbsListView)) {
            return AbstractC0285k0.c(view, -1) || this.f925a.getScrollY() > 0;
        }
        AbsListView absListView = (AbsListView) view;
        return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
    }

    public final void d() {
        this.f945u = new C0419b(getContext(), -328966);
        C0413A c0413a = new C0413A(getContext(), this);
        this.f950z = c0413a;
        c0413a.e(-328966);
        this.f945u.setImageDrawable(this.f950z);
        this.f945u.setVisibility(8);
        addView(this.f945u);
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f2, float f3, boolean z2) {
        return this.f932h.a(f2, f3, z2);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f2, float f3) {
        return this.f932h.b(f2, f3);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return this.f932h.c(i2, i3, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return this.f932h.d(i2, i3, i4, i5, iArr);
    }

    public final void e() {
        if (this.f925a == null) {
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                View childAt = getChildAt(i2);
                if (!childAt.equals(this.f945u)) {
                    this.f925a = childAt;
                    return;
                }
            }
        }
    }

    public final void f(float f2) {
        if (f2 > this.f929e) {
            n(true, true);
            return;
        }
        this.f927c = false;
        this.f950z.j(0.0f, 0.0f);
        b(this.f937m, !this.f942r ? new e() : null);
        this.f950z.l(false);
    }

    public final boolean g() {
        return Build.VERSION.SDK_INT < 11;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i2, int i3) {
        int i4 = this.f946v;
        return i4 < 0 ? i3 : i3 == i2 + (-1) ? i4 : i3 >= i4 ? i3 + 1 : i3;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.f931g.a();
    }

    public int getProgressCircleDiameter() {
        return this.f920H;
    }

    public final boolean h(Animation animation) {
        return (animation == null || !animation.hasStarted() || animation.hasEnded()) ? false : true;
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return this.f932h.e();
    }

    public boolean i() {
        return this.f927c;
    }

    @Override // android.view.View, k.Z
    public boolean isNestedScrollingEnabled() {
        return this.f932h.f();
    }

    public final void j(float f2) {
        this.f950z.l(true);
        float fMin = Math.min(1.0f, Math.abs(f2 / this.f929e));
        double d2 = fMin;
        Double.isNaN(d2);
        float fMax = (((float) Math.max(d2 - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float fAbs = Math.abs(f2) - this.f929e;
        float f3 = this.f921I ? this.f918F - this.f949y : this.f918F;
        double dMax = Math.max(0.0f, Math.min(fAbs, f3 * 2.0f) / f3) / 4.0f;
        double dPow = Math.pow(dMax, 2.0d);
        Double.isNaN(dMax);
        float f4 = ((float) (dMax - dPow)) * 2.0f;
        int i2 = this.f949y + ((int) ((f3 * fMin) + (f3 * f4 * 2.0f)));
        if (this.f945u.getVisibility() != 0) {
            this.f945u.setVisibility(0);
        }
        if (!this.f942r) {
            AbstractC0285k0.T(this.f945u, 1.0f);
            AbstractC0285k0.U(this.f945u, 1.0f);
        }
        if (this.f942r) {
            setAnimationProgress(Math.min(1.0f, f2 / this.f929e));
        }
        if (f2 < this.f929e) {
            if (this.f950z.getAlpha() > 76 && !h(this.f915C)) {
                s();
            }
        } else if (this.f950z.getAlpha() < 255 && !h(this.f916D)) {
            r();
        }
        this.f950z.j(0.0f, Math.min(0.8f, fMax * 0.8f));
        this.f950z.d(Math.min(1.0f, fMax));
        this.f950z.g((((fMax * 0.4f) - 0.25f) + (f4 * 2.0f)) * 0.5f);
        o(i2 - this.f937m, true);
    }

    public void k(float f2) {
        o((this.f947w + ((int) ((this.f949y - r0) * f2))) - this.f945u.getTop(), false);
    }

    public void m() {
        this.f945u.clearAnimation();
        this.f950z.stop();
        this.f945u.setVisibility(8);
        setColorViewAlpha(255);
        if (this.f942r) {
            setAnimationProgress(0.0f);
        } else {
            o(this.f949y - this.f937m, true);
        }
        this.f937m = this.f945u.getTop();
    }

    public final void n(boolean z2, boolean z3) {
        if (this.f927c != z2) {
            this.f919G = z3;
            e();
            this.f927c = z2;
            if (z2) {
                a(this.f937m, this.f922J);
            } else {
                t(this.f922J);
            }
        }
    }

    public void o(int i2, boolean z2) {
        this.f945u.bringToFront();
        AbstractC0285k0.C(this.f945u, i2);
        this.f937m = this.f945u.getTop();
        if (!z2 || Build.VERSION.SDK_INT >= 11) {
            return;
        }
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m();
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0058  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            r4.e()
            int r0 = k.W.b(r5)
            boolean r1 = r4.f943s
            r2 = 0
            if (r1 == 0) goto L10
            if (r0 != 0) goto L10
            r4.f943s = r2
        L10:
            boolean r1 = r4.isEnabled()
            if (r1 == 0) goto L81
            boolean r1 = r4.f943s
            if (r1 != 0) goto L81
            boolean r1 = r4.c()
            if (r1 != 0) goto L81
            boolean r1 = r4.f927c
            if (r1 != 0) goto L81
            boolean r1 = r4.f935k
            if (r1 == 0) goto L29
            goto L81
        L29:
            r1 = 1
            if (r0 == 0) goto L5d
            r3 = -1
            if (r0 == r1) goto L58
            r1 = 2
            if (r0 == r1) goto L3d
            r1 = 3
            if (r0 == r1) goto L58
            r1 = 6
            if (r0 == r1) goto L39
            goto L7e
        L39:
            r4.l(r5)
            goto L7e
        L3d:
            int r0 = r4.f941q
            if (r0 != r3) goto L49
            java.lang.String r5 = android.support.v4.widget.SwipeRefreshLayout.f911M
            java.lang.String r0 = "Got ACTION_MOVE event but don't have an active pointer id."
            android.util.Log.e(r5, r0)
            return r2
        L49:
            int r0 = r5.findPointerIndex(r0)
            if (r0 >= 0) goto L50
            return r2
        L50:
            float r5 = r5.getY(r0)
            r4.q(r5)
            goto L7e
        L58:
            r4.f940p = r2
            r4.f941q = r3
            goto L7e
        L5d:
            int r0 = r4.f949y
            n.b r3 = r4.f945u
            int r3 = r3.getTop()
            int r0 = r0 - r3
            r4.o(r0, r1)
            int r0 = r5.getPointerId(r2)
            r4.f941q = r0
            r4.f940p = r2
            int r0 = r5.findPointerIndex(r0)
            if (r0 >= 0) goto L78
            return r2
        L78:
            float r5 = r5.getY(r0)
            r4.f939o = r5
        L7e:
            boolean r5 = r4.f940p
            return r5
        L81:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.SwipeRefreshLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() == 0) {
            return;
        }
        if (this.f925a == null) {
            e();
        }
        View view = this.f925a;
        if (view == null) {
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        view.layout(paddingLeft, paddingTop, ((measuredWidth - getPaddingLeft()) - getPaddingRight()) + paddingLeft, ((measuredHeight - getPaddingTop()) - getPaddingBottom()) + paddingTop);
        int measuredWidth2 = this.f945u.getMeasuredWidth();
        int measuredHeight2 = this.f945u.getMeasuredHeight();
        int i6 = measuredWidth / 2;
        int i7 = measuredWidth2 / 2;
        int i8 = this.f937m;
        this.f945u.layout(i6 - i7, i8, i6 + i7, measuredHeight2 + i8);
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f925a == null) {
            e();
        }
        View view = this.f925a;
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
        this.f945u.measure(View.MeasureSpec.makeMeasureSpec(this.f920H, 1073741824), View.MeasureSpec.makeMeasureSpec(this.f920H, 1073741824));
        this.f946v = -1;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            if (getChildAt(i4) == this.f945u) {
                this.f946v = i4;
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public boolean onNestedFling(View view, float f2, float f3, boolean z2) {
        return dispatchNestedFling(f2, f3, z2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return dispatchNestedPreFling(f2, f3);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        if (i3 > 0) {
            float f2 = this.f930f;
            if (f2 > 0.0f) {
                float f3 = i3;
                if (f3 > f2) {
                    iArr[1] = i3 - ((int) f2);
                    this.f930f = 0.0f;
                } else {
                    this.f930f = f2 - f3;
                    iArr[1] = i3;
                }
                j(this.f930f);
            }
        }
        if (this.f921I && i3 > 0 && this.f930f == 0.0f && Math.abs(i3 - iArr[1]) > 0) {
            this.f945u.setVisibility(8);
        }
        int[] iArr2 = this.f933i;
        if (dispatchNestedPreScroll(i2 - iArr[0], i3 - iArr[1], iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        dispatchNestedScroll(i2, i3, i4, i5, this.f934j);
        if (i5 + this.f934j[1] >= 0 || c()) {
            return;
        }
        float fAbs = this.f930f + Math.abs(r11);
        this.f930f = fAbs;
        j(fAbs);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public void onNestedScrollAccepted(View view, View view2, int i2) {
        this.f931g.b(view, view2, i2);
        startNestedScroll(i2 & 2);
        this.f930f = 0.0f;
        this.f935k = true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        return (!isEnabled() || this.f943s || this.f927c || (i2 & 2) == 0) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public void onStopNestedScroll(View view) {
        this.f931g.c(view);
        this.f935k = false;
        float f2 = this.f930f;
        if (f2 > 0.0f) {
            f(f2);
            this.f930f = 0.0f;
        }
        stopNestedScroll();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int iB = W.b(motionEvent);
        if (this.f943s && iB == 0) {
            this.f943s = false;
        }
        if (!isEnabled() || this.f943s || c() || this.f927c || this.f935k) {
            return false;
        }
        if (iB == 0) {
            this.f941q = motionEvent.getPointerId(0);
            this.f940p = false;
        } else {
            if (iB == 1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.f941q);
                if (iFindPointerIndex < 0) {
                    Log.e(f911M, "Got ACTION_UP event but don't have an active pointer id.");
                    return false;
                }
                if (this.f940p) {
                    float y2 = (motionEvent.getY(iFindPointerIndex) - this.f938n) * 0.5f;
                    this.f940p = false;
                    f(y2);
                }
                this.f941q = -1;
                return false;
            }
            if (iB == 2) {
                int iFindPointerIndex2 = motionEvent.findPointerIndex(this.f941q);
                if (iFindPointerIndex2 < 0) {
                    Log.e(f911M, "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                float y3 = motionEvent.getY(iFindPointerIndex2);
                q(y3);
                if (this.f940p) {
                    float f2 = (y3 - this.f938n) * 0.5f;
                    if (f2 <= 0.0f) {
                        return false;
                    }
                    j(f2);
                }
            } else {
                if (iB == 3) {
                    return false;
                }
                if (iB == 5) {
                    int iA = W.a(motionEvent);
                    if (iA < 0) {
                        Log.e(f911M, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                        return false;
                    }
                    this.f941q = motionEvent.getPointerId(iA);
                } else if (iB == 6) {
                    l(motionEvent);
                }
            }
        }
        return true;
    }

    public final Animation p(int i2, int i3) {
        if (this.f942r && g()) {
            return null;
        }
        d dVar = new d(i2, i3);
        dVar.setDuration(300L);
        this.f945u.b(null);
        this.f945u.clearAnimation();
        this.f945u.startAnimation(dVar);
        return dVar;
    }

    public final void q(float f2) {
        float f3 = this.f939o;
        float f4 = f2 - f3;
        int i2 = this.f928d;
        if (f4 <= i2 || this.f940p) {
            return;
        }
        this.f938n = f3 + i2;
        this.f940p = true;
        this.f950z.setAlpha(76);
    }

    public final void r() {
        this.f916D = p(this.f950z.getAlpha(), 255);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z2) {
        if (Build.VERSION.SDK_INT >= 21 || !(this.f925a instanceof AbsListView)) {
            View view = this.f925a;
            if (view == null || AbstractC0285k0.A(view)) {
                super.requestDisallowInterceptTouchEvent(z2);
            }
        }
    }

    public final void s() {
        this.f915C = p(this.f950z.getAlpha(), 76);
    }

    public void setAnimationProgress(float f2) {
        if (g()) {
            setColorViewAlpha((int) (f2 * 255.0f));
        } else {
            AbstractC0285k0.T(this.f945u, f2);
            AbstractC0285k0.U(this.f945u, f2);
        }
    }

    @Deprecated
    public void setColorScheme(int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeColors(int... iArr) {
        e();
        this.f950z.f(iArr);
    }

    public void setColorSchemeResources(int... iArr) {
        Resources resources = getResources();
        int[] iArr2 = new int[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr2[i2] = resources.getColor(iArr[i2]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setDistanceToTriggerSync(int i2) {
        this.f929e = i2;
    }

    @Override // android.view.View
    public void setEnabled(boolean z2) {
        super.setEnabled(z2);
        if (z2) {
            return;
        }
        m();
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z2) {
        this.f932h.g(z2);
    }

    public void setOnChildScrollUpCallback(i iVar) {
    }

    public void setOnRefreshListener(j jVar) {
        this.f926b = jVar;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i2) {
        setProgressBackgroundColorSchemeResource(i2);
    }

    public void setProgressBackgroundColorSchemeColor(int i2) {
        this.f945u.setBackgroundColor(i2);
        this.f950z.e(i2);
    }

    public void setProgressBackgroundColorSchemeResource(int i2) {
        setProgressBackgroundColorSchemeColor(getResources().getColor(i2));
    }

    public void setRefreshing(boolean z2) {
        if (!z2 || this.f927c == z2) {
            n(z2, false);
            return;
        }
        this.f927c = z2;
        o(((int) (!this.f921I ? this.f918F + this.f949y : this.f918F)) - this.f937m, true);
        this.f919G = false;
        v(this.f922J);
    }

    public void setSize(int i2) {
        if (i2 == 0 || i2 == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (i2 == 0) {
                this.f920H = (int) (displayMetrics.density * 56.0f);
            } else {
                this.f920H = (int) (displayMetrics.density * 40.0f);
            }
            this.f945u.setImageDrawable(null);
            this.f950z.n(i2);
            this.f945u.setImageDrawable(this.f950z);
        }
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i2) {
        return this.f932h.h(i2);
    }

    @Override // android.view.View, k.Z
    public void stopNestedScroll() {
        this.f932h.i();
    }

    public void t(Animation.AnimationListener animationListener) {
        c cVar = new c();
        this.f914B = cVar;
        cVar.setDuration(150L);
        this.f945u.b(animationListener);
        this.f945u.clearAnimation();
        this.f945u.startAnimation(this.f914B);
    }

    public final void u(int i2, Animation.AnimationListener animationListener) {
        this.f947w = i2;
        if (g()) {
            this.f948x = this.f950z.getAlpha();
        } else {
            this.f948x = AbstractC0285k0.q(this.f945u);
        }
        h hVar = new h();
        this.f917E = hVar;
        hVar.setDuration(150L);
        if (animationListener != null) {
            this.f945u.b(animationListener);
        }
        this.f945u.clearAnimation();
        this.f945u.startAnimation(this.f917E);
    }

    public final void v(Animation.AnimationListener animationListener) {
        this.f945u.setVisibility(0);
        if (Build.VERSION.SDK_INT >= 11) {
            this.f950z.setAlpha(255);
        }
        b bVar = new b();
        this.f913A = bVar;
        bVar.setDuration(this.f936l);
        if (animationListener != null) {
            this.f945u.b(animationListener);
        }
        this.f945u.clearAnimation();
        this.f945u.startAnimation(this.f913A);
    }
}
