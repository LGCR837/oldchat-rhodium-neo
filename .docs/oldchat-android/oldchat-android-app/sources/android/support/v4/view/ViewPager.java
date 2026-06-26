package android.support.v4.view;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import i.AbstractC0251b;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import k.AbstractC0254a;
import k.AbstractC0267e0;
import k.AbstractC0285k0;
import k.AbstractC0313u;
import k.InterfaceC0264d0;
import k.W;
import k.W1;
import l.AbstractC0343a;
import l.C0347e;
import l.N;
import n.C0427j;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public class ViewPager extends ViewGroup {

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public static final int[] f784g0 = {R.attr.layout_gravity};

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public static final Comparator f785h0 = new a();

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public static final Interpolator f786i0 = new b();

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public static final n f787j0 = new n();

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f788A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public int f789B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int f790C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public float f791D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public float f792E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public float f793F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public float f794G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public int f795H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public VelocityTracker f796I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f797J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public int f798K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public int f799L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public int f800M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public boolean f801N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public C0427j f802O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public C0427j f803P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public boolean f804Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public boolean f805R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public boolean f806S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public int f807T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public List f808U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public j f809V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public j f810W;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f811a;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public List f812a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f813b;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public Method f814b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final f f815c;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public int f816c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f817d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public ArrayList f818d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public AbstractC0267e0 f819e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final Runnable f820e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f821f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public int f822f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f823g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Parcelable f824h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ClassLoader f825i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Scroller f826j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f827k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public k f828l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f829m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Drawable f830n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f831o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f832p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f833q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f834r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f835s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f836t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f837u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f838v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f839w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f840x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f841y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f842z;

    public static class a implements Comparator {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(f fVar, f fVar2) {
            return fVar.f847b - fVar2.f847b;
        }
    }

    public static class b implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewPager.this.setScrollState(0);
            ViewPager.this.D();
        }
    }

    public class d implements InterfaceC0264d0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Rect f844a = new Rect();

        public d() {
        }

        @Override // k.InterfaceC0264d0
        public W1 a(View view, W1 w1) {
            W1 w1D = AbstractC0285k0.D(view, w1);
            if (w1D.e()) {
                return w1D;
            }
            Rect rect = this.f844a;
            rect.left = w1D.b();
            rect.top = w1D.d();
            rect.right = w1D.c();
            rect.bottom = w1D.a();
            int childCount = ViewPager.this.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                W1 w1D2 = AbstractC0285k0.d(ViewPager.this.getChildAt(i2), w1D);
                rect.left = Math.min(w1D2.b(), rect.left);
                rect.top = Math.min(w1D2.d(), rect.top);
                rect.right = Math.min(w1D2.c(), rect.right);
                rect.bottom = Math.min(w1D2.a(), rect.bottom);
            }
            return w1D.f(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface e {
    }

    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f846a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f847b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f848c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f849d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f850e;
    }

    public class h extends AbstractC0254a {
        public h() {
        }

        @Override // k.AbstractC0254a
        public void d(View view, AccessibilityEvent accessibilityEvent) {
            AbstractC0267e0 abstractC0267e0;
            super.d(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            N nA = AbstractC0343a.a(accessibilityEvent);
            nA.g(k());
            if (accessibilityEvent.getEventType() != 4096 || (abstractC0267e0 = ViewPager.this.f819e) == null) {
                return;
            }
            nA.b(abstractC0267e0.d());
            nA.a(ViewPager.this.f821f);
            nA.h(ViewPager.this.f821f);
        }

        @Override // k.AbstractC0254a
        public void e(View view, C0347e c0347e) {
            super.e(view, c0347e);
            c0347e.v(ViewPager.class.getName());
            c0347e.y(k());
            if (ViewPager.this.canScrollHorizontally(1)) {
                c0347e.a(PKIFailureInfo.certConfirmed);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                c0347e.a(PKIFailureInfo.certRevoked);
            }
        }

        @Override // k.AbstractC0254a
        public boolean h(View view, int i2, Bundle bundle) {
            if (super.h(view, i2, bundle)) {
                return true;
            }
            if (i2 == 4096) {
                if (!ViewPager.this.canScrollHorizontally(1)) {
                    return false;
                }
                ViewPager viewPager = ViewPager.this;
                viewPager.setCurrentItem(viewPager.f821f + 1);
                return true;
            }
            if (i2 != 8192 || !ViewPager.this.canScrollHorizontally(-1)) {
                return false;
            }
            ViewPager viewPager2 = ViewPager.this;
            viewPager2.setCurrentItem(viewPager2.f821f - 1);
            return true;
        }

        public final boolean k() {
            AbstractC0267e0 abstractC0267e0 = ViewPager.this.f819e;
            return abstractC0267e0 != null && abstractC0267e0.d() > 1;
        }
    }

    public interface i {
        void d(ViewPager viewPager, AbstractC0267e0 abstractC0267e0, AbstractC0267e0 abstractC0267e02);
    }

    public interface j {
        void a(int i2, float f2, int i3);

        void b(int i2);

        void c(int i2);
    }

    public class k extends DataSetObserver {
        public k() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            ViewPager.this.h();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ViewPager.this.h();
        }
    }

    public static class l extends android.support.v4.view.a {
        public static final Parcelable.Creator<l> CREATOR = AbstractC0251b.a(new a());

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f859c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Parcelable f860d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ClassLoader f861e;

        public static class a implements i.c {
            @Override // i.c
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public l createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new l(parcel, classLoader);
            }

            @Override // i.c
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public l[] newArray(int i2) {
                return new l[i2];
            }
        }

        public l(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.f859c + "}";
        }

        @Override // android.support.v4.view.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f859c);
            parcel.writeParcelable(this.f860d, i2);
        }

        public l(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.f859c = parcel.readInt();
            this.f860d = parcel.readParcelable(classLoader);
            this.f861e = classLoader;
        }
    }

    public static class m implements j {
        @Override // android.support.v4.view.ViewPager.j
        public void a(int i2, float f2, int i3) {
        }

        @Override // android.support.v4.view.ViewPager.j
        public void b(int i2) {
        }
    }

    public static class n implements Comparator {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            g gVar = (g) view.getLayoutParams();
            g gVar2 = (g) view2.getLayoutParams();
            boolean z2 = gVar.f851a;
            return z2 != gVar2.f851a ? z2 ? 1 : -1 : gVar.f855e - gVar2.f855e;
        }
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f813b = new ArrayList();
        this.f815c = new f();
        this.f817d = new Rect();
        this.f823g = -1;
        this.f824h = null;
        this.f825i = null;
        this.f833q = -3.4028235E38f;
        this.f834r = Float.MAX_VALUE;
        this.f840x = 1;
        this.f795H = -1;
        this.f804Q = true;
        this.f805R = false;
        this.f820e0 = new c();
        this.f822f0 = 0;
        u();
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private void setScrollingCacheEnabled(boolean z2) {
        if (this.f838v != z2) {
            this.f838v = z2;
        }
    }

    public static boolean v(View view) {
        return view.getClass().getAnnotation(e.class) != null;
    }

    public boolean A() {
        AbstractC0267e0 abstractC0267e0 = this.f819e;
        if (abstractC0267e0 == null || this.f821f >= abstractC0267e0.d() - 1) {
            return false;
        }
        L(this.f821f + 1, true);
        return true;
    }

    public final boolean B(int i2) {
        if (this.f813b.size() == 0) {
            if (this.f804Q) {
                return false;
            }
            this.f806S = false;
            x(0, 0.0f, 0);
            if (this.f806S) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        f fVarS = s();
        int clientWidth = getClientWidth();
        int i3 = this.f829m;
        int i4 = clientWidth + i3;
        float f2 = clientWidth;
        int i5 = fVarS.f847b;
        float f3 = ((i2 / f2) - fVarS.f850e) / (fVarS.f849d + (i3 / f2));
        this.f806S = false;
        x(i5, f3, (int) (i4 * f3));
        if (this.f806S) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    public final boolean C(float f2) {
        boolean z2;
        float f3 = this.f791D - f2;
        this.f791D = f2;
        float scrollX = getScrollX() + f3;
        float clientWidth = getClientWidth();
        float f4 = this.f833q * clientWidth;
        float f5 = this.f834r * clientWidth;
        f fVar = (f) this.f813b.get(0);
        ArrayList arrayList = this.f813b;
        boolean z3 = true;
        f fVar2 = (f) arrayList.get(arrayList.size() - 1);
        if (fVar.f847b != 0) {
            f4 = fVar.f850e * clientWidth;
            z2 = false;
        } else {
            z2 = true;
        }
        if (fVar2.f847b != this.f819e.d() - 1) {
            f5 = fVar2.f850e * clientWidth;
            z3 = false;
        }
        if (scrollX < f4) {
            zE = z2 ? this.f802O.e(Math.abs(f4 - scrollX) / clientWidth) : false;
            scrollX = f4;
        } else if (scrollX > f5) {
            zE = z3 ? this.f803P.e(Math.abs(scrollX - f5) / clientWidth) : false;
            scrollX = f5;
        }
        int i2 = (int) scrollX;
        this.f791D += scrollX - i2;
        scrollTo(i2, getScrollY());
        B(i2);
        return zE;
    }

    public void D() {
        E(this.f821f);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0066, code lost:
    
        r8 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00cb A[PHI: r7 r10 r15
  0x00cb: PHI (r7v6 int) = (r7v5 int), (r7v4 int), (r7v9 int) binds: [B:64:0x00ef, B:61:0x00d9, B:53:0x00c0] A[DONT_GENERATE, DONT_INLINE]
  0x00cb: PHI (r10v9 int) = (r10v1 int), (r10v8 int), (r10v12 int) binds: [B:64:0x00ef, B:61:0x00d9, B:53:0x00c0] A[DONT_GENERATE, DONT_INLINE]
  0x00cb: PHI (r15v7 float) = (r15v5 float), (r15v6 float), (r15v4 float) binds: [B:64:0x00ef, B:61:0x00d9, B:53:0x00c0] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void E(int r18) {
        /*
            Method dump skipped, instruction units count: 621
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.E(int):void");
    }

    public final void F(int i2, int i3, int i4, int i5) {
        if (i3 > 0 && !this.f813b.isEmpty()) {
            if (!this.f826j.isFinished()) {
                this.f826j.setFinalX(getCurrentItem() * getClientWidth());
                return;
            } else {
                scrollTo((int) ((getScrollX() / (((i3 - getPaddingLeft()) - getPaddingRight()) + i5)) * (((i2 - getPaddingLeft()) - getPaddingRight()) + i4)), getScrollY());
                return;
            }
        }
        f fVarT = t(this.f821f);
        int iMin = (int) ((fVarT != null ? Math.min(fVarT.f850e, this.f834r) : 0.0f) * ((i2 - getPaddingLeft()) - getPaddingRight()));
        if (iMin != getScrollX()) {
            g(false);
            scrollTo(iMin, getScrollY());
        }
    }

    public final void G() {
        int i2 = 0;
        while (i2 < getChildCount()) {
            if (!((g) getChildAt(i2).getLayoutParams()).f851a) {
                removeViewAt(i2);
                i2--;
            }
            i2++;
        }
    }

    public void H(i iVar) {
        List list = this.f812a0;
        if (list != null) {
            list.remove(iVar);
        }
    }

    public final void I(boolean z2) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z2);
        }
    }

    public final boolean J() {
        this.f795H = -1;
        n();
        return this.f802O.g() | this.f803P.g();
    }

    public final void K(int i2, boolean z2, int i3, boolean z3) {
        f fVarT = t(i2);
        int clientWidth = fVarT != null ? (int) (getClientWidth() * Math.max(this.f833q, Math.min(fVarT.f850e, this.f834r))) : 0;
        if (z2) {
            P(clientWidth, 0, i3);
            if (z3) {
                k(i2);
                return;
            }
            return;
        }
        if (z3) {
            k(i2);
        }
        g(false);
        scrollTo(clientWidth, 0);
        B(clientWidth);
    }

    public void L(int i2, boolean z2) {
        this.f839w = false;
        M(i2, z2, false);
    }

    public void M(int i2, boolean z2, boolean z3) {
        N(i2, z2, z3, 0);
    }

    public void N(int i2, boolean z2, boolean z3, int i3) {
        AbstractC0267e0 abstractC0267e0 = this.f819e;
        if (abstractC0267e0 == null || abstractC0267e0.d() <= 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (!z3 && this.f821f == i2 && this.f813b.size() != 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 >= this.f819e.d()) {
            i2 = this.f819e.d() - 1;
        }
        int i4 = this.f840x;
        int i5 = this.f821f;
        if (i2 > i5 + i4 || i2 < i5 - i4) {
            for (int i6 = 0; i6 < this.f813b.size(); i6++) {
                ((f) this.f813b.get(i6)).f848c = true;
            }
        }
        boolean z4 = this.f821f != i2;
        if (!this.f804Q) {
            E(i2);
            K(i2, z2, i3, z4);
        } else {
            this.f821f = i2;
            if (z4) {
                k(i2);
            }
            requestLayout();
        }
    }

    public j O(j jVar) {
        j jVar2 = this.f810W;
        this.f810W = jVar;
        return jVar2;
    }

    public void P(int i2, int i3, int i4) {
        int scrollX;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        Scroller scroller = this.f826j;
        if (scroller == null || scroller.isFinished()) {
            scrollX = getScrollX();
        } else {
            scrollX = this.f827k ? this.f826j.getCurrX() : this.f826j.getStartX();
            this.f826j.abortAnimation();
            setScrollingCacheEnabled(false);
        }
        int i5 = scrollX;
        int scrollY = getScrollY();
        int i6 = i2 - i5;
        int i7 = i3 - scrollY;
        if (i6 == 0 && i7 == 0) {
            g(false);
            D();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i8 = clientWidth / 2;
        float f2 = clientWidth;
        float f3 = i8;
        float fM = f3 + (m(Math.min(1.0f, (Math.abs(i6) * 1.0f) / f2)) * f3);
        int iAbs = Math.abs(i4);
        int iMin = Math.min(iAbs > 0 ? Math.round(Math.abs(fM / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i6) / ((f2 * this.f819e.g(this.f821f)) + this.f829m)) + 1.0f) * 100.0f), 600);
        this.f827k = false;
        this.f826j.startScroll(i5, scrollY, i6, i7, iMin);
        AbstractC0285k0.E(this);
    }

    public final void Q() {
        if (this.f816c0 != 0) {
            ArrayList arrayList = this.f818d0;
            if (arrayList == null) {
                this.f818d0 = new ArrayList();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.f818d0.add(getChildAt(i2));
            }
            Collections.sort(this.f818d0, f787j0);
        }
    }

    public f a(int i2, int i3) {
        f fVar = new f();
        fVar.f847b = i2;
        fVar.f846a = this.f819e.h(this, i2);
        fVar.f849d = this.f819e.g(i2);
        if (i3 < 0 || i3 >= this.f813b.size()) {
            this.f813b.add(fVar);
        } else {
            this.f813b.add(i3, fVar);
        }
        return fVar;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList arrayList, int i2, int i3) {
        f fVarR;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (fVarR = r(childAt)) != null && fVarR.f847b == this.f821f) {
                    childAt.addFocusables(arrayList, i2, i3);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if ((i3 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) {
                return;
            }
            arrayList.add(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList arrayList) {
        f fVarR;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (fVarR = r(childAt)) != null && fVarR.f847b == this.f821f) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        g gVar = (g) layoutParams;
        boolean zV = gVar.f851a | v(view);
        gVar.f851a = zV;
        if (!this.f837u) {
            super.addView(view, i2, layoutParams);
        } else {
            if (zV) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            gVar.f854d = true;
            addViewInLayout(view, i2, layoutParams);
        }
    }

    public void b(i iVar) {
        if (this.f812a0 == null) {
            this.f812a0 = new ArrayList();
        }
        this.f812a0.add(iVar);
    }

    public void c(j jVar) {
        if (this.f808U == null) {
            this.f808U = new ArrayList();
        }
        this.f808U.add(jVar);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        if (this.f819e == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        return i2 < 0 ? scrollX > ((int) (((float) clientWidth) * this.f833q)) : i2 > 0 && scrollX < ((int) (((float) clientWidth) * this.f834r));
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof g) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        this.f827k = true;
        if (this.f826j.isFinished() || !this.f826j.computeScrollOffset()) {
            g(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.f826j.getCurrX();
        int currY = this.f826j.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!B(currX)) {
                this.f826j.abortAnimation();
                scrollTo(0, currY);
            }
        }
        AbstractC0285k0.E(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00bf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean d(int r5) {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.d(int):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || o(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        f fVarR;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (fVarR = r(childAt)) != null && fVarR.f847b == this.f821f && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        AbstractC0267e0 abstractC0267e0;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean zA = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && (abstractC0267e0 = this.f819e) != null && abstractC0267e0.d() > 1)) {
            if (!this.f802O.c()) {
                int iSave = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((-height) + getPaddingTop(), this.f833q * width);
                this.f802O.h(height, width);
                zA = this.f802O.a(canvas);
                canvas.restoreToCount(iSave);
            }
            if (!this.f803P.c()) {
                int iSave2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.f834r + 1.0f)) * width2);
                this.f803P.h(height2, width2);
                zA |= this.f803P.a(canvas);
                canvas.restoreToCount(iSave2);
            }
        } else {
            this.f802O.b();
            this.f803P.b();
        }
        if (zA) {
            AbstractC0285k0.E(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f830n;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    public final void e(f fVar, int i2, f fVar2) {
        int i3;
        int i4;
        f fVar3;
        f fVar4;
        int iD = this.f819e.d();
        int clientWidth = getClientWidth();
        float f2 = clientWidth > 0 ? this.f829m / clientWidth : 0.0f;
        if (fVar2 != null) {
            int i5 = fVar2.f847b;
            int i6 = fVar.f847b;
            if (i5 < i6) {
                float fG = fVar2.f850e + fVar2.f849d + f2;
                int i7 = i5 + 1;
                int i8 = 0;
                while (i7 <= fVar.f847b && i8 < this.f813b.size()) {
                    Object obj = this.f813b.get(i8);
                    while (true) {
                        fVar4 = (f) obj;
                        if (i7 <= fVar4.f847b || i8 >= this.f813b.size() - 1) {
                            break;
                        }
                        i8++;
                        obj = this.f813b.get(i8);
                    }
                    while (i7 < fVar4.f847b) {
                        fG += this.f819e.g(i7) + f2;
                        i7++;
                    }
                    fVar4.f850e = fG;
                    fG += fVar4.f849d + f2;
                    i7++;
                }
            } else if (i5 > i6) {
                int size = this.f813b.size() - 1;
                float fG2 = fVar2.f850e;
                while (true) {
                    i5--;
                    if (i5 < fVar.f847b || size < 0) {
                        break;
                    }
                    Object obj2 = this.f813b.get(size);
                    while (true) {
                        fVar3 = (f) obj2;
                        if (i5 >= fVar3.f847b || size <= 0) {
                            break;
                        }
                        size--;
                        obj2 = this.f813b.get(size);
                    }
                    while (i5 > fVar3.f847b) {
                        fG2 -= this.f819e.g(i5) + f2;
                        i5--;
                    }
                    fG2 -= fVar3.f849d + f2;
                    fVar3.f850e = fG2;
                }
            }
        }
        int size2 = this.f813b.size();
        float fG3 = fVar.f850e;
        int i9 = fVar.f847b;
        int i10 = i9 - 1;
        this.f833q = i9 == 0 ? fG3 : -3.4028235E38f;
        int i11 = iD - 1;
        this.f834r = i9 == i11 ? (fVar.f849d + fG3) - 1.0f : Float.MAX_VALUE;
        int i12 = i2 - 1;
        while (i12 >= 0) {
            f fVar5 = (f) this.f813b.get(i12);
            while (true) {
                i4 = fVar5.f847b;
                if (i10 <= i4) {
                    break;
                }
                fG3 -= this.f819e.g(i10) + f2;
                i10--;
            }
            fG3 -= fVar5.f849d + f2;
            fVar5.f850e = fG3;
            if (i4 == 0) {
                this.f833q = fG3;
            }
            i12--;
            i10--;
        }
        float fG4 = fVar.f850e + fVar.f849d + f2;
        int i13 = fVar.f847b + 1;
        int i14 = i2 + 1;
        while (i14 < size2) {
            f fVar6 = (f) this.f813b.get(i14);
            while (true) {
                i3 = fVar6.f847b;
                if (i13 >= i3) {
                    break;
                }
                fG4 += this.f819e.g(i13) + f2;
                i13++;
            }
            if (i3 == i11) {
                this.f834r = (fVar6.f849d + fG4) - 1.0f;
            }
            fVar6.f850e = fG4;
            fG4 += fVar6.f849d + f2;
            i14++;
            i13++;
        }
        this.f805R = false;
    }

    public boolean f(View view, boolean z2, int i2, int i3, int i4) {
        int i5;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i6 = i3 + scrollX;
                if (i6 >= childAt.getLeft() && i6 < childAt.getRight() && (i5 = i4 + scrollY) >= childAt.getTop() && i5 < childAt.getBottom() && f(childAt, true, i2, i6 - childAt.getLeft(), i5 - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z2 && AbstractC0285k0.b(view, -i2);
    }

    public final void g(boolean z2) {
        boolean z3 = this.f822f0 == 2;
        if (z3) {
            setScrollingCacheEnabled(false);
            if (!this.f826j.isFinished()) {
                this.f826j.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.f826j.getCurrX();
                int currY = this.f826j.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        B(currX);
                    }
                }
            }
        }
        this.f839w = false;
        for (int i2 = 0; i2 < this.f813b.size(); i2++) {
            f fVar = (f) this.f813b.get(i2);
            if (fVar.f848c) {
                fVar.f848c = false;
                z3 = true;
            }
        }
        if (z3) {
            if (z2) {
                AbstractC0285k0.F(this, this.f820e0);
            } else {
                this.f820e0.run();
            }
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new g();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public AbstractC0267e0 getAdapter() {
        return this.f819e;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i2, int i3) {
        if (this.f816c0 == 2) {
            i3 = (i2 - 1) - i3;
        }
        return ((g) ((View) this.f818d0.get(i3)).getLayoutParams()).f856f;
    }

    public int getCurrentItem() {
        return this.f821f;
    }

    public int getOffscreenPageLimit() {
        return this.f840x;
    }

    public int getPageMargin() {
        return this.f829m;
    }

    public void h() {
        int iD = this.f819e.d();
        this.f811a = iD;
        boolean z2 = this.f813b.size() < (this.f840x * 2) + 1 && this.f813b.size() < iD;
        int iMax = this.f821f;
        int i2 = 0;
        boolean z3 = false;
        while (i2 < this.f813b.size()) {
            f fVar = (f) this.f813b.get(i2);
            int iE = this.f819e.e(fVar.f846a);
            if (iE != -1) {
                if (iE == -2) {
                    this.f813b.remove(i2);
                    i2--;
                    if (!z3) {
                        this.f819e.q(this);
                        z3 = true;
                    }
                    this.f819e.a(this, fVar.f847b, fVar.f846a);
                    int i3 = this.f821f;
                    if (i3 == fVar.f847b) {
                        iMax = Math.max(0, Math.min(i3, iD - 1));
                    }
                } else {
                    int i4 = fVar.f847b;
                    if (i4 != iE) {
                        if (i4 == this.f821f) {
                            iMax = iE;
                        }
                        fVar.f847b = iE;
                    }
                }
                z2 = true;
            }
            i2++;
        }
        if (z3) {
            this.f819e.c(this);
        }
        Collections.sort(this.f813b, f785h0);
        if (z2) {
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                g gVar = (g) getChildAt(i5).getLayoutParams();
                if (!gVar.f851a) {
                    gVar.f853c = 0.0f;
                }
            }
            M(iMax, false, true);
            requestLayout();
        }
    }

    public final int i(int i2, float f2, int i3, int i4) {
        if (Math.abs(i4) <= this.f799L || Math.abs(i3) <= this.f797J) {
            i2 += (int) (f2 + (i2 >= this.f821f ? 0.4f : 0.6f));
        } else if (i3 <= 0) {
            i2++;
        }
        if (this.f813b.size() <= 0) {
            return i2;
        }
        return Math.max(((f) this.f813b.get(0)).f847b, Math.min(i2, ((f) this.f813b.get(r4.size() - 1)).f847b));
    }

    public final void j(int i2, float f2, int i3) {
        j jVar = this.f809V;
        if (jVar != null) {
            jVar.a(i2, f2, i3);
        }
        List list = this.f808U;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                j jVar2 = (j) this.f808U.get(i4);
                if (jVar2 != null) {
                    jVar2.a(i2, f2, i3);
                }
            }
        }
        j jVar3 = this.f810W;
        if (jVar3 != null) {
            jVar3.a(i2, f2, i3);
        }
    }

    public final void k(int i2) {
        j jVar = this.f809V;
        if (jVar != null) {
            jVar.c(i2);
        }
        List list = this.f808U;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                j jVar2 = (j) this.f808U.get(i3);
                if (jVar2 != null) {
                    jVar2.c(i2);
                }
            }
        }
        j jVar3 = this.f810W;
        if (jVar3 != null) {
            jVar3.c(i2);
        }
    }

    public final void l(int i2) {
        j jVar = this.f809V;
        if (jVar != null) {
            jVar.b(i2);
        }
        List list = this.f808U;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                j jVar2 = (j) this.f808U.get(i3);
                if (jVar2 != null) {
                    jVar2.b(i2);
                }
            }
        }
        j jVar3 = this.f810W;
        if (jVar3 != null) {
            jVar3.b(i2);
        }
    }

    public float m(float f2) {
        Double.isNaN(f2 - 0.5f);
        return (float) Math.sin((float) (r0 * 0.4712389167638204d));
    }

    public final void n() {
        this.f841y = false;
        this.f842z = false;
        VelocityTracker velocityTracker = this.f796I;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f796I = null;
        }
    }

    public boolean o(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 21) {
                return d(17);
            }
            if (keyCode == 22) {
                return d(66);
            }
            if (keyCode == 61 && Build.VERSION.SDK_INT >= 11) {
                if (AbstractC0313u.b(keyEvent)) {
                    return d(2);
                }
                if (AbstractC0313u.a(keyEvent, 1)) {
                    return d(1);
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f804Q = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.f820e0);
        Scroller scroller = this.f826j;
        if (scroller != null && !scroller.isFinished()) {
            this.f826j.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int i2;
        float f2;
        float f3;
        super.onDraw(canvas);
        if (this.f829m <= 0 || this.f830n == null || this.f813b.size() <= 0 || this.f819e == null) {
            return;
        }
        int scrollX = getScrollX();
        float width = getWidth();
        float f4 = this.f829m / width;
        int i3 = 0;
        f fVar = (f) this.f813b.get(0);
        float f5 = fVar.f850e;
        int size = this.f813b.size();
        int i4 = fVar.f847b;
        int i5 = ((f) this.f813b.get(size - 1)).f847b;
        while (i4 < i5) {
            while (true) {
                i2 = fVar.f847b;
                if (i4 <= i2 || i3 >= size) {
                    break;
                }
                i3++;
                fVar = (f) this.f813b.get(i3);
            }
            if (i4 == i2) {
                float f6 = fVar.f850e;
                float f7 = fVar.f849d;
                f2 = (f6 + f7) * width;
                f5 = f6 + f7 + f4;
            } else {
                float fG = this.f819e.g(i4);
                f2 = (f5 + fG) * width;
                f5 += fG + f4;
            }
            if (this.f829m + f2 > scrollX) {
                f3 = f4;
                this.f830n.setBounds(Math.round(f2), this.f831o, Math.round(this.f829m + f2), this.f832p);
                this.f830n.draw(canvas);
            } else {
                f3 = f4;
            }
            if (f2 > scrollX + r2) {
                return;
            }
            i4++;
            f4 = f3;
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            J();
            return false;
        }
        if (action != 0) {
            if (this.f841y) {
                return true;
            }
            if (this.f842z) {
                return false;
            }
        }
        if (action == 0) {
            float x2 = motionEvent.getX();
            this.f793F = x2;
            this.f791D = x2;
            float y2 = motionEvent.getY();
            this.f794G = y2;
            this.f792E = y2;
            this.f795H = motionEvent.getPointerId(0);
            this.f842z = false;
            this.f827k = true;
            this.f826j.computeScrollOffset();
            if (this.f822f0 != 2 || Math.abs(this.f826j.getFinalX() - this.f826j.getCurrX()) <= this.f800M) {
                g(false);
                this.f841y = false;
            } else {
                this.f826j.abortAnimation();
                this.f839w = false;
                D();
                this.f841y = true;
                I(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i2 = this.f795H;
            if (i2 != -1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(i2);
                float x3 = motionEvent.getX(iFindPointerIndex);
                float f2 = x3 - this.f791D;
                float fAbs = Math.abs(f2);
                float y3 = motionEvent.getY(iFindPointerIndex);
                float fAbs2 = Math.abs(y3 - this.f794G);
                if (f2 != 0.0f && !w(this.f791D, f2) && f(this, false, (int) f2, (int) x3, (int) y3)) {
                    this.f791D = x3;
                    this.f792E = y3;
                    this.f842z = true;
                    return false;
                }
                int i3 = this.f790C;
                if (fAbs > i3 && fAbs * 0.5f > fAbs2) {
                    this.f841y = true;
                    I(true);
                    setScrollState(1);
                    this.f791D = f2 > 0.0f ? this.f793F + this.f790C : this.f793F - this.f790C;
                    this.f792E = y3;
                    setScrollingCacheEnabled(true);
                } else if (fAbs2 > i3) {
                    this.f842z = true;
                }
                if (this.f841y && C(x3)) {
                    AbstractC0285k0.E(this);
                }
            }
        } else if (action == 6) {
            y(motionEvent);
        }
        if (this.f796I == null) {
            this.f796I = VelocityTracker.obtain();
        }
        this.f796I.addMovement(motionEvent);
        return this.f841y;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0094  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instruction units count: 288
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a7  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r14, int r15) {
        /*
            Method dump skipped, instruction units count: 243
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i2, Rect rect) {
        int i3;
        int i4;
        int i5;
        f fVarR;
        int childCount = getChildCount();
        if ((i2 & 2) != 0) {
            i4 = childCount;
            i3 = 0;
            i5 = 1;
        } else {
            i3 = childCount - 1;
            i4 = -1;
            i5 = -1;
        }
        while (i3 != i4) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() == 0 && (fVarR = r(childAt)) != null && fVarR.f847b == this.f821f && childAt.requestFocus(i2, rect)) {
                return true;
            }
            i3 += i5;
        }
        return false;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof l)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        l lVar = (l) parcelable;
        super.onRestoreInstanceState(lVar.a());
        AbstractC0267e0 abstractC0267e0 = this.f819e;
        if (abstractC0267e0 != null) {
            abstractC0267e0.k(lVar.f860d, lVar.f861e);
            M(lVar.f859c, false, true);
        } else {
            this.f823g = lVar.f859c;
            this.f824h = lVar.f860d;
            this.f825i = lVar.f861e;
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        l lVar = new l(super.onSaveInstanceState());
        lVar.f859c = this.f821f;
        AbstractC0267e0 abstractC0267e0 = this.f819e;
        if (abstractC0267e0 != null) {
            lVar.f860d = abstractC0267e0.l();
        }
        return lVar;
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 != i4) {
            int i6 = this.f829m;
            F(i2, i4, i6, i6);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00dc  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            Method dump skipped, instruction units count: 351
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final Rect p(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left += viewGroup.getLeft();
            rect.right += viewGroup.getRight();
            rect.top += viewGroup.getTop();
            rect.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect;
    }

    public f q(View view) {
        while (true) {
            Object parent = view.getParent();
            if (parent == this) {
                return r(view);
            }
            if (parent == null || !(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
    }

    public f r(View view) {
        for (int i2 = 0; i2 < this.f813b.size(); i2++) {
            f fVar = (f) this.f813b.get(i2);
            if (this.f819e.i(view, fVar.f846a)) {
                return fVar;
            }
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.f837u) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public final f s() {
        int i2;
        int clientWidth = getClientWidth();
        float f2 = 0.0f;
        float scrollX = clientWidth > 0 ? getScrollX() / clientWidth : 0.0f;
        float f3 = clientWidth > 0 ? this.f829m / clientWidth : 0.0f;
        f fVar = null;
        float f4 = 0.0f;
        int i3 = -1;
        int i4 = 0;
        boolean z2 = true;
        while (i4 < this.f813b.size()) {
            f fVar2 = (f) this.f813b.get(i4);
            if (!z2 && fVar2.f847b != (i2 = i3 + 1)) {
                fVar2 = this.f815c;
                fVar2.f850e = f2 + f4 + f3;
                fVar2.f847b = i2;
                fVar2.f849d = this.f819e.g(i2);
                i4--;
            }
            f fVar3 = fVar2;
            f2 = fVar3.f850e;
            float f5 = fVar3.f849d + f2 + f3;
            if (!z2 && scrollX < f2) {
                return fVar;
            }
            if (scrollX < f5 || i4 == this.f813b.size() - 1) {
                return fVar3;
            }
            int i5 = fVar3.f847b;
            float f6 = fVar3.f849d;
            i4++;
            z2 = false;
            i3 = i5;
            f4 = f6;
            fVar = fVar3;
        }
        return fVar;
    }

    public void setAdapter(AbstractC0267e0 abstractC0267e0) {
        AbstractC0267e0 abstractC0267e02 = this.f819e;
        if (abstractC0267e02 != null) {
            abstractC0267e02.o(null);
            this.f819e.q(this);
            for (int i2 = 0; i2 < this.f813b.size(); i2++) {
                f fVar = (f) this.f813b.get(i2);
                this.f819e.a(this, fVar.f847b, fVar.f846a);
            }
            this.f819e.c(this);
            this.f813b.clear();
            G();
            this.f821f = 0;
            scrollTo(0, 0);
        }
        AbstractC0267e0 abstractC0267e03 = this.f819e;
        this.f819e = abstractC0267e0;
        this.f811a = 0;
        if (abstractC0267e0 != null) {
            if (this.f828l == null) {
                this.f828l = new k();
            }
            this.f819e.o(this.f828l);
            this.f839w = false;
            boolean z2 = this.f804Q;
            this.f804Q = true;
            this.f811a = this.f819e.d();
            if (this.f823g >= 0) {
                this.f819e.k(this.f824h, this.f825i);
                M(this.f823g, false, true);
                this.f823g = -1;
                this.f824h = null;
                this.f825i = null;
            } else if (z2) {
                requestLayout();
            } else {
                D();
            }
        }
        List list = this.f812a0;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.f812a0.size();
        for (int i3 = 0; i3 < size; i3++) {
            ((i) this.f812a0.get(i3)).d(this, abstractC0267e03, abstractC0267e0);
        }
    }

    public void setChildrenDrawingOrderEnabledCompat(boolean z2) {
        if (this.f814b0 == null) {
            try {
                this.f814b0 = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException e2) {
                Log.e("ViewPager", "Can't find setChildrenDrawingOrderEnabled", e2);
            }
        }
        try {
            this.f814b0.invoke(this, Boolean.valueOf(z2));
        } catch (Exception e3) {
            Log.e("ViewPager", "Error changing children drawing order", e3);
        }
    }

    public void setCurrentItem(int i2) {
        this.f839w = false;
        M(i2, !this.f804Q, false);
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i2 + " too small; defaulting to 1");
            i2 = 1;
        }
        if (i2 != this.f840x) {
            this.f840x = i2;
            D();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(j jVar) {
        this.f809V = jVar;
    }

    public void setPageMargin(int i2) {
        int i3 = this.f829m;
        this.f829m = i2;
        int width = getWidth();
        F(width, width, i2, i3);
        requestLayout();
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.f830n = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setScrollState(int i2) {
        if (this.f822f0 == i2) {
            return;
        }
        this.f822f0 = i2;
        l(i2);
    }

    public f t(int i2) {
        for (int i3 = 0; i3 < this.f813b.size(); i3++) {
            f fVar = (f) this.f813b.get(i3);
            if (fVar.f847b == i2) {
                return fVar;
            }
        }
        return null;
    }

    public void u() {
        setWillNotDraw(false);
        setDescendantFocusability(PKIFailureInfo.transactionIdInUse);
        setFocusable(true);
        Context context = getContext();
        this.f826j = new Scroller(context, f786i0);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.f790C = viewConfiguration.getScaledPagingTouchSlop();
        this.f797J = (int) (400.0f * f2);
        this.f798K = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f802O = new C0427j(context);
        this.f803P = new C0427j(context);
        this.f799L = (int) (25.0f * f2);
        this.f800M = (int) (2.0f * f2);
        this.f788A = (int) (f2 * 16.0f);
        AbstractC0285k0.J(this, new h());
        if (AbstractC0285k0.i(this) == 0) {
            AbstractC0285k0.P(this, 1);
        }
        AbstractC0285k0.R(this, new d());
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f830n;
    }

    public final boolean w(float f2, float f3) {
        return (f2 < ((float) this.f789B) && f3 > 0.0f) || (f2 > ((float) (getWidth() - this.f789B)) && f3 < 0.0f);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void x(int r12, float r13, int r14) {
        /*
            r11 = this;
            int r0 = r11.f807T
            r1 = 1
            if (r0 <= 0) goto L6b
            int r0 = r11.getScrollX()
            int r2 = r11.getPaddingLeft()
            int r3 = r11.getPaddingRight()
            int r4 = r11.getWidth()
            int r5 = r11.getChildCount()
            r6 = 0
        L1a:
            if (r6 >= r5) goto L6b
            android.view.View r7 = r11.getChildAt(r6)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            android.support.v4.view.ViewPager$g r8 = (android.support.v4.view.ViewPager.g) r8
            boolean r9 = r8.f851a
            if (r9 != 0) goto L2b
            goto L68
        L2b:
            int r8 = r8.f852b
            r8 = r8 & 7
            if (r8 == r1) goto L4f
            r9 = 3
            if (r8 == r9) goto L49
            r9 = 5
            if (r8 == r9) goto L39
            r8 = r2
            goto L5c
        L39:
            int r8 = r4 - r3
            int r9 = r7.getMeasuredWidth()
            int r8 = r8 - r9
            int r9 = r7.getMeasuredWidth()
            int r3 = r3 + r9
        L45:
            r10 = r8
            r8 = r2
            r2 = r10
            goto L5c
        L49:
            int r8 = r7.getWidth()
            int r8 = r8 + r2
            goto L5c
        L4f:
            int r8 = r7.getMeasuredWidth()
            int r8 = r4 - r8
            int r8 = r8 / 2
            int r8 = java.lang.Math.max(r8, r2)
            goto L45
        L5c:
            int r2 = r2 + r0
            int r9 = r7.getLeft()
            int r2 = r2 - r9
            if (r2 == 0) goto L67
            r7.offsetLeftAndRight(r2)
        L67:
            r2 = r8
        L68:
            int r6 = r6 + 1
            goto L1a
        L6b:
            r11.j(r12, r13, r14)
            r11.f806S = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.x(int, float, int):void");
    }

    public final void y(MotionEvent motionEvent) {
        int iA = W.a(motionEvent);
        if (motionEvent.getPointerId(iA) == this.f795H) {
            int i2 = iA == 0 ? 1 : 0;
            this.f791D = motionEvent.getX(i2);
            this.f795H = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.f796I;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    public boolean z() {
        int i2 = this.f821f;
        if (i2 <= 0) {
            return false;
        }
        L(i2 - 1, true);
        return true;
    }

    public static class g extends ViewGroup.LayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f851a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f852b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f853c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f854d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f855e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f856f;

        public g() {
            super(-1, -1);
            this.f853c = 0.0f;
        }

        public g(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f853c = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.f784g0);
            this.f852b = typedArrayObtainStyledAttributes.getInteger(0, 48);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new g(getContext(), attributeSet);
    }

    public void setPageMarginDrawable(int i2) {
        setPageMarginDrawable(getContext().getResources().getDrawable(i2));
    }
}
