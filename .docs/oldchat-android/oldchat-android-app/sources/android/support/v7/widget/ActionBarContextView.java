package android.support.v7.widget;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import k.F1;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import p.AbstractC0460a;
import v.AbstractC0473b;
import x.AbstractC0498a;
import x.f0;
import x.h0;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContextView extends AbstractC0498a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public CharSequence f1153i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CharSequence f1154j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public View f1155k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public View f1156l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public LinearLayout f1157m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public TextView f1158n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public TextView f1159o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1160p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1161q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1162r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1163s;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AbstractC0473b f1164a;

        public a(AbstractC0473b abstractC0473b) {
            this.f1164a = abstractC0473b;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f1164a.c();
        }
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    @Override // x.AbstractC0498a
    public /* bridge */ /* synthetic */ F1 f(int i2, long j2) {
        return super.f(i2, j2);
    }

    public void g() {
        if (this.f1155k == null) {
            k();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // x.AbstractC0498a
    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    @Override // x.AbstractC0498a
    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.f1154j;
    }

    public CharSequence getTitle() {
        return this.f1153i;
    }

    public void h(AbstractC0473b abstractC0473b) {
        View view = this.f1155k;
        if (view == null) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(this.f1163s, (ViewGroup) this, false);
            this.f1155k = viewInflate;
            addView(viewInflate);
        } else if (view.getParent() == null) {
            addView(this.f1155k);
        }
        this.f1155k.findViewById(p.f.f7799i).setOnClickListener(new a(abstractC0473b));
        android.support.v7.view.menu.e eVar = (android.support.v7.view.menu.e) abstractC0473b.e();
        android.support.v7.widget.a aVar = this.f8115d;
        if (aVar != null) {
            aVar.x();
        }
        android.support.v7.widget.a aVar2 = new android.support.v7.widget.a(getContext());
        this.f8115d = aVar2;
        aVar2.I(true);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        eVar.c(this.f8115d, this.f8113b);
        ActionMenuView actionMenuView = (ActionMenuView) this.f8115d.o(this);
        this.f8114c = actionMenuView;
        actionMenuView.setBackgroundDrawable(null);
        addView(this.f8114c, layoutParams);
    }

    public final void i() {
        if (this.f1157m == null) {
            LayoutInflater.from(getContext()).inflate(p.g.f7817a, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.f1157m = linearLayout;
            this.f1158n = (TextView) linearLayout.findViewById(p.f.f7795e);
            this.f1159o = (TextView) this.f1157m.findViewById(p.f.f7794d);
            if (this.f1160p != 0) {
                this.f1158n.setTextAppearance(getContext(), this.f1160p);
            }
            if (this.f1161q != 0) {
                this.f1159o.setTextAppearance(getContext(), this.f1161q);
            }
        }
        this.f1158n.setText(this.f1153i);
        this.f1159o.setText(this.f1154j);
        boolean zIsEmpty = TextUtils.isEmpty(this.f1153i);
        boolean zIsEmpty2 = TextUtils.isEmpty(this.f1154j);
        this.f1159o.setVisibility(!zIsEmpty2 ? 0 : 8);
        this.f1157m.setVisibility((zIsEmpty && zIsEmpty2) ? 8 : 0);
        if (this.f1157m.getParent() == null) {
            addView(this.f1157m);
        }
    }

    public boolean j() {
        return this.f1162r;
    }

    public void k() {
        removeAllViews();
        this.f1156l = null;
        this.f8114c = null;
    }

    public boolean l() {
        android.support.v7.widget.a aVar = this.f8115d;
        if (aVar != null) {
            return aVar.J();
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        android.support.v7.widget.a aVar = this.f8115d;
        if (aVar != null) {
            aVar.A();
            this.f8115d.B();
        }
    }

    @Override // x.AbstractC0498a, android.view.View
    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (Build.VERSION.SDK_INT >= 14) {
            if (accessibilityEvent.getEventType() != 32) {
                super.onInitializeAccessibilityEvent(accessibilityEvent);
                return;
            }
            accessibilityEvent.setSource(this);
            accessibilityEvent.setClassName(getClass().getName());
            accessibilityEvent.setPackageName(getContext().getPackageName());
            accessibilityEvent.setContentDescription(this.f1153i);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        boolean zC = h0.c(this);
        int paddingRight = zC ? (i4 - i2) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i5 - i3) - getPaddingTop()) - getPaddingBottom();
        View view = this.f1155k;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f1155k.getLayoutParams();
            int i6 = zC ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i7 = zC ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int iD = AbstractC0498a.d(paddingRight, i6, zC);
            paddingRight = AbstractC0498a.d(iD + e(this.f1155k, iD, paddingTop, paddingTop2, zC), i7, zC);
        }
        int iE = paddingRight;
        LinearLayout linearLayout = this.f1157m;
        if (linearLayout != null && this.f1156l == null && linearLayout.getVisibility() != 8) {
            iE += e(this.f1157m, iE, paddingTop, paddingTop2, zC);
        }
        int i8 = iE;
        View view2 = this.f1156l;
        if (view2 != null) {
            e(view2, i8, paddingTop, paddingTop2, zC);
        }
        int paddingLeft = zC ? getPaddingLeft() : (i4 - i2) - getPaddingRight();
        ActionMenuView actionMenuView = this.f8114c;
        if (actionMenuView != null) {
            e(actionMenuView, paddingLeft, paddingTop, paddingTop2, !zC);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        if (View.MeasureSpec.getMode(i2) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
        }
        if (View.MeasureSpec.getMode(i3) == 0) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_height=\"wrap_content\"");
        }
        int size = View.MeasureSpec.getSize(i2);
        int size2 = this.f8116e;
        if (size2 <= 0) {
            size2 = View.MeasureSpec.getSize(i3);
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int iMin = size2 - paddingTop;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, PKIFailureInfo.systemUnavail);
        View view = this.f1155k;
        if (view != null) {
            int iC = c(view, paddingLeft, iMakeMeasureSpec, 0);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f1155k.getLayoutParams();
            paddingLeft = iC - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
        }
        ActionMenuView actionMenuView = this.f8114c;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            paddingLeft = c(this.f8114c, paddingLeft, iMakeMeasureSpec, 0);
        }
        LinearLayout linearLayout = this.f1157m;
        if (linearLayout != null && this.f1156l == null) {
            if (this.f1162r) {
                this.f1157m.measure(View.MeasureSpec.makeMeasureSpec(0, 0), iMakeMeasureSpec);
                int measuredWidth = this.f1157m.getMeasuredWidth();
                boolean z2 = measuredWidth <= paddingLeft;
                if (z2) {
                    paddingLeft -= measuredWidth;
                }
                this.f1157m.setVisibility(z2 ? 0 : 8);
            } else {
                paddingLeft = c(linearLayout, paddingLeft, iMakeMeasureSpec, 0);
            }
        }
        View view2 = this.f1156l;
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            int i4 = layoutParams.width;
            int i5 = i4 != -2 ? 1073741824 : PKIFailureInfo.systemUnavail;
            if (i4 >= 0) {
                paddingLeft = Math.min(i4, paddingLeft);
            }
            int i6 = layoutParams.height;
            int i7 = i6 == -2 ? PKIFailureInfo.systemUnavail : 1073741824;
            if (i6 >= 0) {
                iMin = Math.min(i6, iMin);
            }
            this.f1156l.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i5), View.MeasureSpec.makeMeasureSpec(iMin, i7));
        }
        if (this.f8116e > 0) {
            setMeasuredDimension(size, size2);
            return;
        }
        int childCount = getChildCount();
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            int measuredHeight = getChildAt(i9).getMeasuredHeight() + paddingTop;
            if (measuredHeight > i8) {
                i8 = measuredHeight;
            }
        }
        setMeasuredDimension(size, i8);
    }

    @Override // x.AbstractC0498a, android.view.View
    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @Override // x.AbstractC0498a
    public void setContentHeight(int i2) {
        this.f8116e = i2;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.f1156l;
        if (view2 != null) {
            removeView(view2);
        }
        this.f1156l = view;
        if (view != null && (linearLayout = this.f1157m) != null) {
            removeView(linearLayout);
            this.f1157m = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f1154j = charSequence;
        i();
    }

    public void setTitle(CharSequence charSequence) {
        this.f1153i = charSequence;
        i();
    }

    public void setTitleOptional(boolean z2) {
        if (z2 != this.f1162r) {
            requestLayout();
        }
        this.f1162r = z2;
    }

    @Override // x.AbstractC0498a, android.view.View
    public /* bridge */ /* synthetic */ void setVisibility(int i2) {
        super.setVisibility(i2);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0460a.f7713g);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        f0 f0VarS = f0.s(context, attributeSet, p.j.f7901y, i2, 0);
        setBackgroundDrawable(f0VarS.f(p.j.f7902z));
        this.f1160p = f0VarS.l(p.j.f7839D, 0);
        this.f1161q = f0VarS.l(p.j.f7838C, 0);
        this.f8116e = f0VarS.k(p.j.f7837B, 0);
        this.f1163s = f0VarS.l(p.j.f7836A, p.g.f7820d);
        f0VarS.t();
    }
}
