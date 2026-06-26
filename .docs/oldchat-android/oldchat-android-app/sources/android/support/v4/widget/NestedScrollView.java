package android.support.v4.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import k.AbstractC0254a;
import k.AbstractC0279i0;
import k.AbstractC0285k0;
import k.C0255a0;
import k.C0261c0;
import k.InterfaceC0258b0;
import k.InterfaceC0273g0;
import k.W;
import k.Z;
import l.AbstractC0343a;
import l.C0347e;
import l.N;
import n.C0427j;
import n.I;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public class NestedScrollView extends FrameLayout implements InterfaceC0258b0, Z, InterfaceC0273g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f885a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Rect f886b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public I f887c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public C0427j f888d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public C0427j f889e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f890f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f891g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f892h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public View f893i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f894j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public VelocityTracker f895k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f896l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f897m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f898n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f899o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f900p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f901q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int[] f902r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int[] f903s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f904t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public c f905u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final C0261c0 f906v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final C0255a0 f907w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public float f908x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public b f909y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final a f884z = new a();

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final int[] f883A = {R.attr.fillViewport};

    public static class a extends AbstractC0254a {
        @Override // k.AbstractC0254a
        public void d(View view, AccessibilityEvent accessibilityEvent) {
            super.d(view, accessibilityEvent);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            accessibilityEvent.setClassName(ScrollView.class.getName());
            N nA = AbstractC0343a.a(accessibilityEvent);
            nA.g(nestedScrollView.getScrollRange() > 0);
            nA.e(nestedScrollView.getScrollX());
            nA.f(nestedScrollView.getScrollY());
            nA.c(nestedScrollView.getScrollX());
            nA.d(nestedScrollView.getScrollRange());
        }

        @Override // k.AbstractC0254a
        public void e(View view, C0347e c0347e) {
            int scrollRange;
            super.e(view, c0347e);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            c0347e.v(ScrollView.class.getName());
            if (!nestedScrollView.isEnabled() || (scrollRange = nestedScrollView.getScrollRange()) <= 0) {
                return;
            }
            c0347e.y(true);
            if (nestedScrollView.getScrollY() > 0) {
                c0347e.a(PKIFailureInfo.certRevoked);
            }
            if (nestedScrollView.getScrollY() < scrollRange) {
                c0347e.a(PKIFailureInfo.certConfirmed);
            }
        }

        @Override // k.AbstractC0254a
        public boolean h(View view, int i2, Bundle bundle) {
            if (super.h(view, i2, bundle)) {
                return true;
            }
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            if (!nestedScrollView.isEnabled()) {
                return false;
            }
            if (i2 == 4096) {
                int iMin = Math.min(nestedScrollView.getScrollY() + ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), nestedScrollView.getScrollRange());
                if (iMin == nestedScrollView.getScrollY()) {
                    return false;
                }
                nestedScrollView.B(0, iMin);
                return true;
            }
            if (i2 != 8192) {
                return false;
            }
            int iMax = Math.max(nestedScrollView.getScrollY() - ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), 0);
            if (iMax == nestedScrollView.getScrollY()) {
                return false;
            }
            nestedScrollView.B(0, iMax);
            return true;
        }
    }

    public interface b {
        void a(NestedScrollView nestedScrollView, int i2, int i3, int i4, int i5);
    }

    public static class c extends View.BaseSavedState {
        public static final Parcelable.Creator<c> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f910a;

        public static class a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public c createFromParcel(Parcel parcel) {
                return new c(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public c[] newArray(int i2) {
                return new c[i2];
            }
        }

        public c(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.f910a + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f910a);
        }

        public c(Parcel parcel) {
            super(parcel);
            this.f910a = parcel.readInt();
        }
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public static int c(int i2, int i3, int i4) {
        if (i3 >= i4 || i2 < 0) {
            return 0;
        }
        return i3 + i2 > i4 ? i4 - i3 : i2;
    }

    private float getVerticalScrollFactorCompat() {
        if (this.f908x == 0.0f) {
            TypedValue typedValue = new TypedValue();
            Context context = getContext();
            if (!context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
            this.f908x = typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return this.f908x;
    }

    public static boolean r(View view, View view2) {
        if (view == view2) {
            return true;
        }
        Object parent = view.getParent();
        return (parent instanceof ViewGroup) && r((View) parent, view2);
    }

    public final void A(int i2, int i3) {
        if (getChildCount() == 0) {
            return;
        }
        if (AnimationUtils.currentAnimationTimeMillis() - this.f885a > 250) {
            int iMax = Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
            int scrollY = getScrollY();
            this.f887c.n(getScrollX(), scrollY, 0, Math.max(0, Math.min(i3 + scrollY, iMax)) - scrollY);
            AbstractC0285k0.E(this);
        } else {
            if (!this.f887c.l()) {
                this.f887c.a();
            }
            scrollBy(i2, i3);
        }
        this.f885a = AnimationUtils.currentAnimationTimeMillis();
    }

    public final void B(int i2, int i3) {
        A(i2 - getScrollX(), i3 - getScrollY());
    }

    public boolean a(int i2) {
        int bottom;
        View viewFindFocus = findFocus();
        if (viewFindFocus == this) {
            viewFindFocus = null;
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i2);
        int maxScrollAmount = getMaxScrollAmount();
        if (viewFindNextFocus == null || !s(viewFindNextFocus, maxScrollAmount, getHeight())) {
            if (i2 == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i2 == 130 && getChildCount() > 0 && (bottom = getChildAt(0).getBottom() - ((getScrollY() + getHeight()) - getPaddingBottom())) < maxScrollAmount) {
                maxScrollAmount = bottom;
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i2 != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            e(maxScrollAmount);
        } else {
            viewFindNextFocus.getDrawingRect(this.f886b);
            offsetDescendantRectToMyCoords(viewFindNextFocus, this.f886b);
            e(d(this.f886b));
            viewFindNextFocus.requestFocus(i2);
        }
        if (viewFindFocus == null || !viewFindFocus.isFocused() || !q(viewFindFocus)) {
            return true;
        }
        int descendantFocusability = getDescendantFocusability();
        setDescendantFocusability(PKIFailureInfo.unsupportedVersion);
        requestFocus();
        setDescendantFocusability(descendantFocusability);
        return true;
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view);
    }

    public final boolean b() {
        View childAt = getChildAt(0);
        if (childAt != null) {
            return getHeight() < (childAt.getHeight() + getPaddingTop()) + getPaddingBottom();
        }
        return false;
    }

    @Override // android.view.View, k.InterfaceC0273g0
    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @Override // android.view.View, k.InterfaceC0273g0
    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @Override // android.view.View, k.InterfaceC0273g0
    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.f887c.b()) {
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int iH = this.f887c.h();
            int i2 = this.f887c.i();
            if (scrollX == iH && scrollY == i2) {
                return;
            }
            int scrollRange = getScrollRange();
            int overScrollMode = getOverScrollMode();
            boolean z2 = overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0);
            u(iH - scrollX, i2 - scrollY, scrollX, scrollY, 0, scrollRange, 0, 0, false);
            if (z2) {
                g();
                if (i2 <= 0 && scrollY > 0) {
                    this.f888d.d((int) this.f887c.g());
                } else {
                    if (i2 < scrollRange || scrollY >= scrollRange) {
                        return;
                    }
                    this.f889e.d((int) this.f887c.g());
                }
            }
        }
    }

    @Override // android.view.View, k.InterfaceC0273g0
    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override // android.view.View, k.InterfaceC0273g0
    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @Override // android.view.View, k.InterfaceC0273g0
    public int computeVerticalScrollRange() {
        int childCount = getChildCount();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (childCount == 0) {
            return height;
        }
        int bottom = getChildAt(0).getBottom();
        int scrollY = getScrollY();
        int iMax = Math.max(0, bottom - height);
        return scrollY < 0 ? bottom - scrollY : scrollY > iMax ? bottom + (scrollY - iMax) : bottom;
    }

    public int d(Rect rect) {
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i2 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        if (rect.bottom < getChildAt(0).getHeight()) {
            i2 -= verticalFadingEdgeLength;
        }
        int i3 = rect.bottom;
        if (i3 > i2 && rect.top > scrollY) {
            return Math.min(rect.height() > height ? rect.top - scrollY : rect.bottom - i2, getChildAt(0).getBottom() - i2);
        }
        if (rect.top >= scrollY || i3 >= i2) {
            return 0;
        }
        return Math.max(rect.height() > height ? 0 - (i2 - rect.bottom) : 0 - (scrollY - rect.top), -getScrollY());
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || h(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f2, float f3, boolean z2) {
        return this.f907w.a(f2, f3, z2);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f2, float f3) {
        return this.f907w.b(f2, f3);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return this.f907w.c(i2, i3, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return this.f907w.d(i2, i3, i4, i5, iArr);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f888d != null) {
            int scrollY = getScrollY();
            if (!this.f888d.c()) {
                int iSave = canvas.save();
                int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
                canvas.translate(getPaddingLeft(), Math.min(0, scrollY));
                this.f888d.h(width, getHeight());
                if (this.f888d.a(canvas)) {
                    AbstractC0285k0.E(this);
                }
                canvas.restoreToCount(iSave);
            }
            if (this.f889e.c()) {
                return;
            }
            int iSave2 = canvas.save();
            int width2 = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int height = getHeight();
            canvas.translate((-width2) + getPaddingLeft(), Math.max(getScrollRange(), scrollY) + height);
            canvas.rotate(180.0f, width2, 0.0f);
            this.f889e.h(width2, height);
            if (this.f889e.a(canvas)) {
                AbstractC0285k0.E(this);
            }
            canvas.restoreToCount(iSave2);
        }
    }

    public final void e(int i2) {
        if (i2 != 0) {
            if (this.f897m) {
                A(0, i2);
            } else {
                scrollBy(0, i2);
            }
        }
    }

    public final void f() {
        this.f894j = false;
        w();
        stopNestedScroll();
        C0427j c0427j = this.f888d;
        if (c0427j != null) {
            c0427j.g();
            this.f889e.g();
        }
    }

    public final void g() {
        if (getOverScrollMode() == 2) {
            this.f888d = null;
            this.f889e = null;
        } else if (this.f888d == null) {
            Context context = getContext();
            this.f888d = new C0427j(context);
            this.f889e = new C0427j(context);
        }
    }

    @Override // android.view.View
    public float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = (getChildAt(0).getBottom() - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return bottom / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (getHeight() * 0.5f);
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.f906v.a();
    }

    public int getScrollRange() {
        if (getChildCount() > 0) {
            return Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
        }
        return 0;
    }

    @Override // android.view.View
    public float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return scrollY / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public boolean h(KeyEvent keyEvent) {
        this.f886b.setEmpty();
        if (!b()) {
            if (!isFocused() || keyEvent.getKeyCode() == 4) {
                return false;
            }
            View viewFindFocus = findFocus();
            if (viewFindFocus == this) {
                viewFindFocus = null;
            }
            View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, 130);
            return (viewFindNextFocus == null || viewFindNextFocus == this || !viewFindNextFocus.requestFocus(130)) ? false : true;
        }
        if (keyEvent.getAction() != 0) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 19) {
            return !keyEvent.isAltPressed() ? a(33) : l(33);
        }
        if (keyCode == 20) {
            return !keyEvent.isAltPressed() ? a(130) : l(130);
        }
        if (keyCode != 62) {
            return false;
        }
        v(keyEvent.isShiftPressed() ? 33 : 130);
        return false;
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return this.f907w.e();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View i(boolean r13, int r14, int r15) {
        /*
            r12 = this;
            r0 = 2
            java.util.ArrayList r0 = r12.getFocusables(r0)
            int r1 = r0.size()
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
        Ld:
            if (r4 >= r1) goto L53
            java.lang.Object r6 = r0.get(r4)
            android.view.View r6 = (android.view.View) r6
            int r7 = r6.getTop()
            int r8 = r6.getBottom()
            if (r14 >= r8) goto L50
            if (r7 >= r15) goto L50
            r9 = 1
            if (r14 >= r7) goto L28
            if (r8 >= r15) goto L28
            r10 = 1
            goto L29
        L28:
            r10 = 0
        L29:
            if (r2 != 0) goto L2e
            r2 = r6
            r5 = r10
            goto L50
        L2e:
            if (r13 == 0) goto L36
            int r11 = r2.getTop()
            if (r7 < r11) goto L3e
        L36:
            if (r13 != 0) goto L40
            int r7 = r2.getBottom()
            if (r8 <= r7) goto L40
        L3e:
            r7 = 1
            goto L41
        L40:
            r7 = 0
        L41:
            if (r5 == 0) goto L48
            if (r10 == 0) goto L50
            if (r7 == 0) goto L50
            goto L4f
        L48:
            if (r10 == 0) goto L4d
            r2 = r6
            r5 = 1
            goto L50
        L4d:
            if (r7 == 0) goto L50
        L4f:
            r2 = r6
        L50:
            int r4 = r4 + 1
            goto Ld
        L53:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.NestedScrollView.i(boolean, int, int):android.view.View");
    }

    @Override // android.view.View, k.Z
    public boolean isNestedScrollingEnabled() {
        return this.f907w.f();
    }

    public void j(int i2) {
        if (getChildCount() > 0) {
            int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
            this.f887c.f(getScrollX(), getScrollY(), 0, i2, 0, 0, 0, Math.max(0, getChildAt(0).getHeight() - height), 0, height / 2);
            AbstractC0285k0.E(this);
        }
    }

    public final void k(int i2) {
        int scrollY = getScrollY();
        boolean z2 = (scrollY > 0 || i2 > 0) && (scrollY < getScrollRange() || i2 < 0);
        float f2 = i2;
        if (dispatchNestedPreFling(0.0f, f2)) {
            return;
        }
        dispatchNestedFling(0.0f, f2, z2);
        if (z2) {
            j(i2);
        }
    }

    public boolean l(int i2) {
        int childCount;
        boolean z2 = i2 == 130;
        int height = getHeight();
        Rect rect = this.f886b;
        rect.top = 0;
        rect.bottom = height;
        if (z2 && (childCount = getChildCount()) > 0) {
            this.f886b.bottom = getChildAt(childCount - 1).getBottom() + getPaddingBottom();
            Rect rect2 = this.f886b;
            rect2.top = rect2.bottom - height;
        }
        Rect rect3 = this.f886b;
        return x(i2, rect3.top, rect3.bottom);
    }

    public final boolean m(int i2, int i3) {
        if (getChildCount() <= 0) {
            return false;
        }
        int scrollY = getScrollY();
        View childAt = getChildAt(0);
        return i3 >= childAt.getTop() - scrollY && i3 < childAt.getBottom() - scrollY && i2 >= childAt.getLeft() && i2 < childAt.getRight();
    }

    @Override // android.view.ViewGroup
    public void measureChild(View view, int i2, int i3) {
        view.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight(), view.getLayoutParams().width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    @Override // android.view.ViewGroup
    public void measureChildWithMargins(View view, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    public final void n() {
        VelocityTracker velocityTracker = this.f895k;
        if (velocityTracker == null) {
            this.f895k = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    public final void o() {
        this.f887c = I.d(getContext(), null);
        setFocusable(true);
        setDescendantFocusability(PKIFailureInfo.transactionIdInUse);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.f898n = viewConfiguration.getScaledTouchSlop();
        this.f899o = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f900p = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f892h = false;
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8 && !this.f894j) {
            float fC = W.c(motionEvent, 9);
            if (fC != 0.0f) {
                int verticalScrollFactorCompat = (int) (fC * getVerticalScrollFactorCompat());
                int scrollRange = getScrollRange();
                int scrollY = getScrollY();
                int i2 = scrollY - verticalScrollFactorCompat;
                if (i2 < 0) {
                    scrollRange = 0;
                } else if (i2 <= scrollRange) {
                    scrollRange = i2;
                }
                if (scrollRange != scrollY) {
                    super.scrollTo(getScrollX(), scrollRange);
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x007d  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r10) {
        /*
            Method dump skipped, instruction units count: 220
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.NestedScrollView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        this.f891g = false;
        View view = this.f893i;
        if (view != null && r(view, this)) {
            y(this.f893i);
        }
        this.f893i = null;
        if (!this.f892h) {
            if (this.f905u != null) {
                scrollTo(getScrollX(), this.f905u.f910a);
                this.f905u = null;
            }
            int iMax = Math.max(0, (getChildCount() > 0 ? getChildAt(0).getMeasuredHeight() : 0) - (((i5 - i3) - getPaddingBottom()) - getPaddingTop()));
            if (getScrollY() > iMax) {
                scrollTo(getScrollX(), iMax);
            } else if (getScrollY() < 0) {
                scrollTo(getScrollX(), 0);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.f892h = true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f896l && View.MeasureSpec.getMode(i3) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            int measuredHeight = getMeasuredHeight();
            if (childAt.getMeasuredHeight() < measuredHeight) {
                childAt.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight(), ((FrameLayout.LayoutParams) childAt.getLayoutParams()).width), View.MeasureSpec.makeMeasureSpec((measuredHeight - getPaddingTop()) - getPaddingBottom(), 1073741824));
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public boolean onNestedFling(View view, float f2, float f3, boolean z2) {
        if (z2) {
            return false;
        }
        k((int) f3);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return dispatchNestedPreFling(f2, f3);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        dispatchNestedPreScroll(i2, i3, iArr, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        int scrollY = getScrollY();
        scrollBy(0, i5);
        int scrollY2 = getScrollY() - scrollY;
        dispatchNestedScroll(0, scrollY2, 0, i5 - scrollY2, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public void onNestedScrollAccepted(View view, View view2, int i2) {
        this.f906v.b(view, view2, i2);
        startNestedScroll(2);
    }

    @Override // android.view.View
    public void onOverScrolled(int i2, int i3, boolean z2, boolean z3) {
        super.scrollTo(i2, i3);
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (i2 == 2) {
            i2 = 130;
        } else if (i2 == 1) {
            i2 = 33;
        }
        View viewFindNextFocus = rect == null ? FocusFinder.getInstance().findNextFocus(this, null, i2) : FocusFinder.getInstance().findNextFocusFromRect(this, rect, i2);
        if (viewFindNextFocus == null || q(viewFindNextFocus)) {
            return false;
        }
        return viewFindNextFocus.requestFocus(i2, rect);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof c)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        c cVar = (c) parcelable;
        super.onRestoreInstanceState(cVar.getSuperState());
        this.f905u = cVar;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        c cVar = new c(super.onSaveInstanceState());
        cVar.f910a = getScrollY();
        return cVar;
    }

    @Override // android.view.View
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        b bVar = this.f909y;
        if (bVar != null) {
            bVar.a(this, i2, i3, i4, i5);
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        View viewFindFocus = findFocus();
        if (viewFindFocus == null || this == viewFindFocus || !s(viewFindFocus, 0, i5)) {
            return;
        }
        viewFindFocus.getDrawingRect(this.f886b);
        offsetDescendantRectToMyCoords(viewFindFocus, this.f886b);
        e(d(this.f886b));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        return (i2 & 2) != 0;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, k.InterfaceC0258b0
    public void onStopNestedScroll(View view) {
        this.f906v.c(view);
        stopNestedScroll();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        p();
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        int iB = W.b(motionEvent);
        if (iB == 0) {
            this.f904t = 0;
        }
        motionEventObtain.offsetLocation(0.0f, this.f904t);
        if (iB != 0) {
            if (iB == 1) {
                if (this.f894j) {
                    VelocityTracker velocityTracker = this.f895k;
                    velocityTracker.computeCurrentVelocity(1000, this.f900p);
                    int iB2 = (int) AbstractC0279i0.b(velocityTracker, this.f901q);
                    if (Math.abs(iB2) > this.f899o) {
                        k(-iB2);
                    } else if (this.f887c.m(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                        AbstractC0285k0.E(this);
                    }
                }
                this.f901q = -1;
                f();
            } else if (iB == 2) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.f901q);
                if (iFindPointerIndex == -1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + this.f901q + " in onTouchEvent");
                } else {
                    int y2 = (int) motionEvent.getY(iFindPointerIndex);
                    int i2 = this.f890f - y2;
                    if (dispatchNestedPreScroll(0, i2, this.f903s, this.f902r)) {
                        i2 -= this.f903s[1];
                        motionEventObtain.offsetLocation(0.0f, this.f902r[1]);
                        this.f904t += this.f902r[1];
                    }
                    if (!this.f894j && Math.abs(i2) > this.f898n) {
                        ViewParent parent2 = getParent();
                        if (parent2 != null) {
                            parent2.requestDisallowInterceptTouchEvent(true);
                        }
                        this.f894j = true;
                        i2 = i2 > 0 ? i2 - this.f898n : i2 + this.f898n;
                    }
                    int i3 = i2;
                    if (this.f894j) {
                        this.f890f = y2 - this.f902r[1];
                        int scrollY = getScrollY();
                        int scrollRange = getScrollRange();
                        int overScrollMode = getOverScrollMode();
                        boolean z2 = overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0);
                        if (u(0, i3, 0, getScrollY(), 0, scrollRange, 0, 0, true) && !hasNestedScrollingParent()) {
                            this.f895k.clear();
                        }
                        int scrollY2 = getScrollY() - scrollY;
                        if (dispatchNestedScroll(0, scrollY2, 0, i3 - scrollY2, this.f902r)) {
                            int i4 = this.f890f;
                            int i5 = this.f902r[1];
                            this.f890f = i4 - i5;
                            motionEventObtain.offsetLocation(0.0f, i5);
                            this.f904t += this.f902r[1];
                        } else if (z2) {
                            g();
                            int i6 = scrollY + i3;
                            if (i6 < 0) {
                                this.f888d.f(i3 / getHeight(), motionEvent.getX(iFindPointerIndex) / getWidth());
                                if (!this.f889e.c()) {
                                    this.f889e.g();
                                }
                            } else if (i6 > scrollRange) {
                                this.f889e.f(i3 / getHeight(), 1.0f - (motionEvent.getX(iFindPointerIndex) / getWidth()));
                                if (!this.f888d.c()) {
                                    this.f888d.g();
                                }
                            }
                            C0427j c0427j = this.f888d;
                            if (c0427j != null && (!c0427j.c() || !this.f889e.c())) {
                                AbstractC0285k0.E(this);
                            }
                        }
                    }
                }
            } else if (iB == 3) {
                if (this.f894j && getChildCount() > 0 && this.f887c.m(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    AbstractC0285k0.E(this);
                }
                this.f901q = -1;
                f();
            } else if (iB == 5) {
                int iA = W.a(motionEvent);
                this.f890f = (int) motionEvent.getY(iA);
                this.f901q = motionEvent.getPointerId(iA);
            } else if (iB == 6) {
                t(motionEvent);
                this.f890f = (int) motionEvent.getY(motionEvent.findPointerIndex(this.f901q));
            }
        } else {
            if (getChildCount() == 0) {
                return false;
            }
            boolean zL = this.f887c.l();
            this.f894j = !zL;
            if (!zL && (parent = getParent()) != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            if (!this.f887c.l()) {
                this.f887c.a();
            }
            this.f890f = (int) motionEvent.getY();
            this.f901q = motionEvent.getPointerId(0);
            startNestedScroll(2);
        }
        VelocityTracker velocityTracker2 = this.f895k;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEventObtain);
        }
        motionEventObtain.recycle();
        return true;
    }

    public final void p() {
        if (this.f895k == null) {
            this.f895k = VelocityTracker.obtain();
        }
    }

    public final boolean q(View view) {
        return !s(view, 0, getHeight());
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (this.f891g) {
            this.f893i = view2;
        } else {
            y(view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z2) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        return z(rect, z2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z2) {
        if (z2) {
            w();
        }
        super.requestDisallowInterceptTouchEvent(z2);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        this.f891g = true;
        super.requestLayout();
    }

    public final boolean s(View view, int i2, int i3) {
        view.getDrawingRect(this.f886b);
        offsetDescendantRectToMyCoords(view, this.f886b);
        return this.f886b.bottom + i2 >= getScrollY() && this.f886b.top - i2 <= getScrollY() + i3;
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            int iC = c(i2, (getWidth() - getPaddingRight()) - getPaddingLeft(), childAt.getWidth());
            int iC2 = c(i3, (getHeight() - getPaddingBottom()) - getPaddingTop(), childAt.getHeight());
            if (iC == getScrollX() && iC2 == getScrollY()) {
                return;
            }
            super.scrollTo(iC, iC2);
        }
    }

    public void setFillViewport(boolean z2) {
        if (z2 != this.f896l) {
            this.f896l = z2;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z2) {
        this.f907w.g(z2);
    }

    public void setOnScrollChangeListener(b bVar) {
        this.f909y = bVar;
    }

    public void setSmoothScrollingEnabled(boolean z2) {
        this.f897m = z2;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i2) {
        return this.f907w.h(i2);
    }

    @Override // android.view.View, k.Z
    public void stopNestedScroll() {
        this.f907w.i();
    }

    public final void t(MotionEvent motionEvent) {
        int action = (motionEvent.getAction() & CipherSuite.DRAFT_TLS_DHE_RSA_WITH_AES_128_OCB) >> 8;
        if (motionEvent.getPointerId(action) == this.f901q) {
            int i2 = action == 0 ? 1 : 0;
            this.f890f = (int) motionEvent.getY(i2);
            this.f901q = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.f895k;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean u(int r13, int r14, int r15, int r16, int r17, int r18, int r19, int r20, boolean r21) {
        /*
            r12 = this;
            r0 = r12
            int r1 = r12.getOverScrollMode()
            int r2 = r12.computeHorizontalScrollRange()
            int r3 = r12.computeHorizontalScrollExtent()
            r4 = 0
            r5 = 1
            if (r2 <= r3) goto L13
            r2 = 1
            goto L14
        L13:
            r2 = 0
        L14:
            int r3 = r12.computeVerticalScrollRange()
            int r6 = r12.computeVerticalScrollExtent()
            if (r3 <= r6) goto L20
            r3 = 1
            goto L21
        L20:
            r3 = 0
        L21:
            if (r1 == 0) goto L2a
            if (r1 != r5) goto L28
            if (r2 == 0) goto L28
            goto L2a
        L28:
            r2 = 0
            goto L2b
        L2a:
            r2 = 1
        L2b:
            if (r1 == 0) goto L34
            if (r1 != r5) goto L32
            if (r3 == 0) goto L32
            goto L34
        L32:
            r1 = 0
            goto L35
        L34:
            r1 = 1
        L35:
            int r3 = r15 + r13
            if (r2 != 0) goto L3b
            r2 = 0
            goto L3d
        L3b:
            r2 = r19
        L3d:
            int r6 = r16 + r14
            if (r1 != 0) goto L43
            r1 = 0
            goto L45
        L43:
            r1 = r20
        L45:
            int r7 = -r2
            int r2 = r2 + r17
            int r8 = -r1
            int r1 = r1 + r18
            if (r3 <= r2) goto L50
            r3 = r2
        L4e:
            r2 = 1
            goto L55
        L50:
            if (r3 >= r7) goto L54
            r3 = r7
            goto L4e
        L54:
            r2 = 0
        L55:
            if (r6 <= r1) goto L5a
            r6 = r1
        L58:
            r1 = 1
            goto L5f
        L5a:
            if (r6 >= r8) goto L5e
            r6 = r8
            goto L58
        L5e:
            r1 = 0
        L5f:
            if (r1 == 0) goto L78
            n.I r7 = r0.f887c
            r8 = 0
            int r9 = r12.getScrollRange()
            r10 = 0
            r11 = 0
            r13 = r7
            r14 = r3
            r15 = r6
            r16 = r10
            r17 = r11
            r18 = r8
            r19 = r9
            r13.m(r14, r15, r16, r17, r18, r19)
        L78:
            r12.onOverScrolled(r3, r6, r2, r1)
            if (r2 != 0) goto L7f
            if (r1 == 0) goto L80
        L7f:
            r4 = 1
        L80:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.NestedScrollView.u(int, int, int, int, int, int, int, int, boolean):boolean");
    }

    public boolean v(int i2) {
        boolean z2 = i2 == 130;
        int height = getHeight();
        if (z2) {
            this.f886b.top = getScrollY() + height;
            int childCount = getChildCount();
            if (childCount > 0) {
                View childAt = getChildAt(childCount - 1);
                if (this.f886b.top + height > childAt.getBottom()) {
                    this.f886b.top = childAt.getBottom() - height;
                }
            }
        } else {
            this.f886b.top = getScrollY() - height;
            Rect rect = this.f886b;
            if (rect.top < 0) {
                rect.top = 0;
            }
        }
        Rect rect2 = this.f886b;
        int i3 = rect2.top;
        int i4 = height + i3;
        rect2.bottom = i4;
        return x(i2, i3, i4);
    }

    public final void w() {
        VelocityTracker velocityTracker = this.f895k;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f895k = null;
        }
    }

    public final boolean x(int i2, int i3, int i4) {
        int height = getHeight();
        int scrollY = getScrollY();
        int i5 = height + scrollY;
        boolean z2 = false;
        boolean z3 = i2 == 33;
        View viewI = i(z3, i3, i4);
        if (viewI == null) {
            viewI = this;
        }
        if (i3 < scrollY || i4 > i5) {
            e(z3 ? i3 - scrollY : i4 - i5);
            z2 = true;
        }
        if (viewI != findFocus()) {
            viewI.requestFocus(i2);
        }
        return z2;
    }

    public final void y(View view) {
        view.getDrawingRect(this.f886b);
        offsetDescendantRectToMyCoords(view, this.f886b);
        int iD = d(this.f886b);
        if (iD != 0) {
            scrollBy(0, iD);
        }
    }

    public final boolean z(Rect rect, boolean z2) {
        int iD = d(rect);
        boolean z3 = iD != 0;
        if (z3) {
            if (z2) {
                scrollBy(0, iD);
            } else {
                A(0, iD);
            }
        }
        return z3;
    }

    public NestedScrollView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f886b = new Rect();
        this.f891g = true;
        this.f892h = false;
        this.f893i = null;
        this.f894j = false;
        this.f897m = true;
        this.f901q = -1;
        this.f902r = new int[2];
        this.f903s = new int[2];
        o();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f883A, i2, 0);
        setFillViewport(typedArrayObtainStyledAttributes.getBoolean(0, false));
        typedArrayObtainStyledAttributes.recycle();
        this.f906v = new C0261c0(this);
        this.f907w = new C0255a0(this);
        setNestedScrollingEnabled(true);
        AbstractC0285k0.J(this, f884z);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2) {
        if (getChildCount() <= 0) {
            super.addView(view, i2);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i2, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }
}
