package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import org.spongycastle.asn1.cmp.PKIFailureInfo;
import x.C0499b;
import x.C0500c;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1143a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f1144b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f1145c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f1146d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f1147e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f1148f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable f1149g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1150h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1151i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1152j;

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackgroundDrawable(Build.VERSION.SDK_INT >= 21 ? new C0500c(this) : new C0499b(this));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.j.f7862a);
        this.f1147e = typedArrayObtainStyledAttributes.getDrawable(p.j.f7864b);
        this.f1148f = typedArrayObtainStyledAttributes.getDrawable(p.j.f7868d);
        this.f1152j = typedArrayObtainStyledAttributes.getDimensionPixelSize(p.j.f7880j, -1);
        boolean z2 = true;
        if (getId() == p.f.f7812v) {
            this.f1150h = true;
            this.f1149g = typedArrayObtainStyledAttributes.getDrawable(p.j.f7866c);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!this.f1150h ? this.f1147e != null || this.f1148f != null : this.f1149g != null) {
            z2 = false;
        }
        setWillNotDraw(z2);
    }

    public final int a(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    public final boolean b(View view) {
        return view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f1147e;
        if (drawable != null && drawable.isStateful()) {
            this.f1147e.setState(getDrawableState());
        }
        Drawable drawable2 = this.f1148f;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f1148f.setState(getDrawableState());
        }
        Drawable drawable3 = this.f1149g;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.f1149g.setState(getDrawableState());
    }

    public View getTabContainer() {
        return this.f1144b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        if (Build.VERSION.SDK_INT >= 11) {
            super.jumpDrawablesToCurrentState();
            Drawable drawable = this.f1147e;
            if (drawable != null) {
                drawable.jumpToCurrentState();
            }
            Drawable drawable2 = this.f1148f;
            if (drawable2 != null) {
                drawable2.jumpToCurrentState();
            }
            Drawable drawable3 = this.f1149g;
            if (drawable3 != null) {
                drawable3.jumpToCurrentState();
            }
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f1145c = findViewById(p.f.f7791a);
        this.f1146d = findViewById(p.f.f7796f);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f1143a || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        Drawable drawable;
        super.onLayout(z2, i2, i3, i4, i5);
        View view = this.f1144b;
        boolean z3 = true;
        boolean z4 = false;
        boolean z5 = (view == null || view.getVisibility() == 8) ? false : true;
        if (view != null && view.getVisibility() != 8) {
            int measuredHeight = getMeasuredHeight();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            int measuredHeight2 = measuredHeight - view.getMeasuredHeight();
            int i6 = layoutParams.bottomMargin;
            view.layout(i2, measuredHeight2 - i6, i4, measuredHeight - i6);
        }
        if (this.f1150h) {
            Drawable drawable2 = this.f1149g;
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                z3 = false;
            }
        } else {
            if (this.f1147e != null) {
                if (this.f1145c.getVisibility() == 0) {
                    this.f1147e.setBounds(this.f1145c.getLeft(), this.f1145c.getTop(), this.f1145c.getRight(), this.f1145c.getBottom());
                } else {
                    View view2 = this.f1146d;
                    if (view2 == null || view2.getVisibility() != 0) {
                        this.f1147e.setBounds(0, 0, 0, 0);
                    } else {
                        this.f1147e.setBounds(this.f1146d.getLeft(), this.f1146d.getTop(), this.f1146d.getRight(), this.f1146d.getBottom());
                    }
                }
                z4 = true;
            }
            this.f1151i = z5;
            if (!z5 || (drawable = this.f1148f) == null) {
                z3 = z4;
            } else {
                drawable.setBounds(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
        }
        if (z3) {
            invalidate();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        int i4;
        if (this.f1145c == null && View.MeasureSpec.getMode(i3) == Integer.MIN_VALUE && (i4 = this.f1152j) >= 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(Math.min(i4, View.MeasureSpec.getSize(i3)), PKIFailureInfo.systemUnavail);
        }
        super.onMeasure(i2, i3);
        if (this.f1145c == null) {
            return;
        }
        int mode = View.MeasureSpec.getMode(i3);
        View view = this.f1144b;
        if (view == null || view.getVisibility() == 8 || mode == 1073741824) {
            return;
        }
        setMeasuredDimension(getMeasuredWidth(), Math.min((!b(this.f1145c) ? a(this.f1145c) : !b(this.f1146d) ? a(this.f1146d) : 0) + a(this.f1144b), mode == Integer.MIN_VALUE ? View.MeasureSpec.getSize(i3) : Integer.MAX_VALUE));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.f1147e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f1147e);
        }
        this.f1147e = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.f1145c;
            if (view != null) {
                this.f1147e.setBounds(view.getLeft(), this.f1145c.getTop(), this.f1145c.getRight(), this.f1145c.getBottom());
            }
        }
        boolean z2 = false;
        if (!this.f1150h ? !(this.f1147e != null || this.f1148f != null) : this.f1149g == null) {
            z2 = true;
        }
        setWillNotDraw(z2);
        invalidate();
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f1149g;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f1149g);
        }
        this.f1149g = drawable;
        boolean z2 = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f1150h && (drawable2 = this.f1149g) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!this.f1150h ? !(this.f1147e != null || this.f1148f != null) : this.f1149g == null) {
            z2 = true;
        }
        setWillNotDraw(z2);
        invalidate();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f1148f;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f1148f);
        }
        this.f1148f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f1151i && (drawable2 = this.f1148f) != null) {
                drawable2.setBounds(this.f1144b.getLeft(), this.f1144b.getTop(), this.f1144b.getRight(), this.f1144b.getBottom());
            }
        }
        boolean z2 = false;
        if (!this.f1150h ? !(this.f1147e != null || this.f1148f != null) : this.f1149g == null) {
            z2 = true;
        }
        setWillNotDraw(z2);
        invalidate();
    }

    public void setTabContainer(l lVar) {
        View view = this.f1144b;
        if (view != null) {
            removeView(view);
        }
        this.f1144b = lVar;
    }

    public void setTransitioning(boolean z2) {
        this.f1143a = z2;
        setDescendantFocusability(z2 ? 393216 : PKIFailureInfo.transactionIdInUse);
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        boolean z2 = i2 == 0;
        Drawable drawable = this.f1147e;
        if (drawable != null) {
            drawable.setVisible(z2, false);
        }
        Drawable drawable2 = this.f1148f;
        if (drawable2 != null) {
            drawable2.setVisible(z2, false);
        }
        Drawable drawable3 = this.f1149g;
        if (drawable3 != null) {
            drawable3.setVisible(z2, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.f1147e && !this.f1150h) || (drawable == this.f1148f && this.f1151i) || ((drawable == this.f1149g && this.f1150h) || super.verifyDrawable(drawable));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i2) {
        if (i2 != 0) {
            return super.startActionModeForChild(view, callback, i2);
        }
        return null;
    }
}
