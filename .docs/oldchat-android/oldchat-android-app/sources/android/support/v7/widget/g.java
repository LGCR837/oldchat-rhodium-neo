package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import x.f0;
import x.h0;

/* JADX INFO: loaded from: classes.dex */
public abstract class g extends ViewGroup {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1689a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1690b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1691c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1692d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1693e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1694f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f1695g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1696h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f1697i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int[] f1698j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Drawable f1699k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1700l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1701m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1702n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1703o;

    public g(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    public void e(Canvas canvas) {
        int right;
        int left;
        int i2;
        int virtualChildCount = getVirtualChildCount();
        boolean zC = h0.c(this);
        for (int i3 = 0; i3 < virtualChildCount; i3++) {
            View viewQ = q(i3);
            if (viewQ != null && viewQ.getVisibility() != 8 && r(i3)) {
                a aVar = (a) viewQ.getLayoutParams();
                h(canvas, zC ? viewQ.getRight() + ((ViewGroup.MarginLayoutParams) aVar).rightMargin : (viewQ.getLeft() - ((ViewGroup.MarginLayoutParams) aVar).leftMargin) - this.f1700l);
            }
        }
        if (r(virtualChildCount)) {
            View viewQ2 = q(virtualChildCount - 1);
            if (viewQ2 != null) {
                a aVar2 = (a) viewQ2.getLayoutParams();
                if (zC) {
                    left = viewQ2.getLeft() - ((ViewGroup.MarginLayoutParams) aVar2).leftMargin;
                    i2 = this.f1700l;
                    right = left - i2;
                } else {
                    right = viewQ2.getRight() + ((ViewGroup.MarginLayoutParams) aVar2).rightMargin;
                }
            } else if (zC) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i2 = this.f1700l;
                right = left - i2;
            }
            h(canvas, right);
        }
    }

    public void f(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View viewQ = q(i2);
            if (viewQ != null && viewQ.getVisibility() != 8 && r(i2)) {
                g(canvas, (viewQ.getTop() - ((ViewGroup.MarginLayoutParams) ((a) viewQ.getLayoutParams())).topMargin) - this.f1701m);
            }
        }
        if (r(virtualChildCount)) {
            View viewQ2 = q(virtualChildCount - 1);
            g(canvas, viewQ2 == null ? (getHeight() - getPaddingBottom()) - this.f1701m : viewQ2.getBottom() + ((ViewGroup.MarginLayoutParams) ((a) viewQ2.getLayoutParams())).bottomMargin);
        }
    }

    public void g(Canvas canvas, int i2) {
        this.f1699k.setBounds(getPaddingLeft() + this.f1703o, i2, (getWidth() - getPaddingRight()) - this.f1703o, this.f1701m + i2);
        this.f1699k.draw(canvas);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i2;
        if (this.f1690b < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i3 = this.f1690b;
        if (childCount <= i3) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i3);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.f1690b == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int bottom = this.f1691c;
        if (this.f1692d == 1 && (i2 = this.f1693e & 112) != 48) {
            if (i2 == 16) {
                bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.f1694f) / 2;
            } else if (i2 == 80) {
                bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.f1694f;
            }
        }
        return bottom + ((ViewGroup.MarginLayoutParams) ((a) childAt.getLayoutParams())).topMargin + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.f1690b;
    }

    public Drawable getDividerDrawable() {
        return this.f1699k;
    }

    public int getDividerPadding() {
        return this.f1703o;
    }

    public int getDividerWidth() {
        return this.f1700l;
    }

    public int getOrientation() {
        return this.f1692d;
    }

    public int getShowDividers() {
        return this.f1702n;
    }

    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.f1695g;
    }

    public void h(Canvas canvas, int i2) {
        this.f1699k.setBounds(i2, getPaddingTop() + this.f1703o, this.f1700l + i2, (getHeight() - getPaddingBottom()) - this.f1703o);
        this.f1699k.draw(canvas);
    }

    public final void i(int i2, int i3) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i4 = 0; i4 < i2; i4++) {
            View viewQ = q(i4);
            if (viewQ.getVisibility() != 8) {
                a aVar = (a) viewQ.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) aVar).height == -1) {
                    int i5 = ((ViewGroup.MarginLayoutParams) aVar).width;
                    ((ViewGroup.MarginLayoutParams) aVar).width = viewQ.getMeasuredWidth();
                    measureChildWithMargins(viewQ, i3, 0, iMakeMeasureSpec, 0);
                    ((ViewGroup.MarginLayoutParams) aVar).width = i5;
                }
            }
        }
    }

    public final void j(int i2, int i3) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i4 = 0; i4 < i2; i4++) {
            View viewQ = q(i4);
            if (viewQ.getVisibility() != 8) {
                a aVar = (a) viewQ.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) aVar).width == -1) {
                    int i5 = ((ViewGroup.MarginLayoutParams) aVar).height;
                    ((ViewGroup.MarginLayoutParams) aVar).height = viewQ.getMeasuredHeight();
                    measureChildWithMargins(viewQ, iMakeMeasureSpec, 0, i3, 0);
                    ((ViewGroup.MarginLayoutParams) aVar).height = i5;
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public a generateDefaultLayoutParams() {
        int i2 = this.f1692d;
        if (i2 == 0) {
            return new a(-2, -2);
        }
        if (i2 == 1) {
            return new a(-1, -2);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public a generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public a generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new a(layoutParams);
    }

    public int n(View view, int i2) {
        return 0;
    }

    public int o(View view) {
        return 0;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.f1699k == null) {
            return;
        }
        if (this.f1692d == 1) {
            f(canvas);
        } else {
            e(canvas);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(g.class.getName());
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(g.class.getName());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        if (this.f1692d == 1) {
            t(i2, i3, i4, i5);
        } else {
            s(i2, i3, i4, i5);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        if (this.f1692d == 1) {
            x(i2, i3);
        } else {
            v(i2, i3);
        }
    }

    public int p(View view) {
        return 0;
    }

    public View q(int i2) {
        return getChildAt(i2);
    }

    public boolean r(int i2) {
        if (i2 == 0) {
            return (this.f1702n & 1) != 0;
        }
        if (i2 == getChildCount()) {
            return (this.f1702n & 4) != 0;
        }
        if ((this.f1702n & 2) == 0) {
            return false;
        }
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            if (getChildAt(i3).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0100  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void s(int r25, int r26, int r27, int r28) {
        /*
            Method dump skipped, instruction units count: 322
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.g.s(int, int, int, int):void");
    }

    public void setBaselineAligned(boolean z2) {
        this.f1689a = z2;
    }

    public void setBaselineAlignedChildIndex(int i2) {
        if (i2 >= 0 && i2 < getChildCount()) {
            this.f1690b = i2;
            return;
        }
        throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.f1699k) {
            return;
        }
        this.f1699k = drawable;
        if (drawable != null) {
            this.f1700l = drawable.getIntrinsicWidth();
            this.f1701m = drawable.getIntrinsicHeight();
        } else {
            this.f1700l = 0;
            this.f1701m = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i2) {
        this.f1703o = i2;
    }

    public void setGravity(int i2) {
        if (this.f1693e != i2) {
            if ((8388615 & i2) == 0) {
                i2 |= 8388611;
            }
            if ((i2 & 112) == 0) {
                i2 |= 48;
            }
            this.f1693e = i2;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i2) {
        int i3 = i2 & 8388615;
        int i4 = this.f1693e;
        if ((8388615 & i4) != i3) {
            this.f1693e = i3 | ((-8388616) & i4);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z2) {
        this.f1696h = z2;
    }

    public void setOrientation(int i2) {
        if (this.f1692d != i2) {
            this.f1692d = i2;
            requestLayout();
        }
    }

    public void setShowDividers(int i2) {
        if (i2 != this.f1702n) {
            requestLayout();
        }
        this.f1702n = i2;
    }

    public void setVerticalGravity(int i2) {
        int i3 = i2 & 112;
        int i4 = this.f1693e;
        if ((i4 & 112) != i3) {
            this.f1693e = i3 | (i4 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f2) {
        this.f1695g = Math.max(0.0f, f2);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void t(int r18, int r19, int r20, int r21) {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.g.t(int, int, int, int):void");
    }

    public void u(View view, int i2, int i3, int i4, int i5, int i6) {
        measureChildWithMargins(view, i3, i4, i5, i6);
    }

    /* JADX WARN: Removed duplicated region for block: B:200:0x043c  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void v(int r40, int r41) {
        /*
            Method dump skipped, instruction units count: 1250
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.g.v(int, int):void");
    }

    public int w(int i2) {
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:152:0x0331  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void x(int r33, int r34) {
        /*
            Method dump skipped, instruction units count: 912
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.g.x(int, int):void");
    }

    public final void y(View view, int i2, int i3, int i4, int i5) {
        view.layout(i2, i3, i4 + i2, i5 + i3);
    }

    public g(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f1689a = true;
        this.f1690b = -1;
        this.f1691c = 0;
        this.f1693e = 8388659;
        f0 f0VarS = f0.s(context, attributeSet, p.j.B0, i2, 0);
        int i3 = f0VarS.i(p.j.D0, -1);
        if (i3 >= 0) {
            setOrientation(i3);
        }
        int i4 = f0VarS.i(p.j.C0, -1);
        if (i4 >= 0) {
            setGravity(i4);
        }
        boolean zA = f0VarS.a(p.j.E0, true);
        if (!zA) {
            setBaselineAligned(zA);
        }
        this.f1695g = f0VarS.h(p.j.G0, -1.0f);
        this.f1690b = f0VarS.i(p.j.F0, -1);
        this.f1696h = f0VarS.a(p.j.J0, false);
        setDividerDrawable(f0VarS.f(p.j.H0));
        this.f1702n = f0VarS.i(p.j.K0, 0);
        this.f1703o = f0VarS.e(p.j.I0, 0);
        f0VarS.t();
    }

    public static class a extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f1704a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1705b;

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1705b = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.j.L0);
            this.f1704a = typedArrayObtainStyledAttributes.getFloat(p.j.N0, 0.0f);
            this.f1705b = typedArrayObtainStyledAttributes.getInt(p.j.M0, -1);
            typedArrayObtainStyledAttributes.recycle();
        }

        public a(int i2, int i3) {
            super(i2, i3);
            this.f1705b = -1;
            this.f1704a = 0.0f;
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1705b = -1;
        }
    }
}
