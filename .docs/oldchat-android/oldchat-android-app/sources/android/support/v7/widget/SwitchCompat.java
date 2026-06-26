package android.support.v7.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.CompoundButton;
import g.AbstractC0244a;
import k.AbstractC0285k0;
import p.AbstractC0460a;
import t.C0470a;
import x.H;
import x.f0;
import x.h0;

/* JADX INFO: loaded from: classes.dex */
public class SwitchCompat extends CompoundButton {

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public static final int[] f1488M = {R.attr.state_checked};

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f1489A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public int f1490B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int f1491C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public int f1492D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public int f1493E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public TextPaint f1494F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public ColorStateList f1495G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public Layout f1496H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public Layout f1497I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public TransformationMethod f1498J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public b f1499K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final Rect f1500L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Drawable f1501a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ColorStateList f1502b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PorterDuff.Mode f1503c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1504d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1505e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f1506f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ColorStateList f1507g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public PorterDuff.Mode f1508h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1509i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1510j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1511k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1512l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1513m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f1514n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public CharSequence f1515o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public CharSequence f1516p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1517q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1518r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1519s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f1520t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f1521u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public VelocityTracker f1522v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1523w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public float f1524x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f1525y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f1526z;

    public class a implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f1527a;

        public a(boolean z2) {
            this.f1527a = z2;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            SwitchCompat switchCompat = SwitchCompat.this;
            if (switchCompat.f1499K == animation) {
                switchCompat.setThumbPosition(this.f1527a ? 1.0f : 0.0f);
                SwitchCompat.this.f1499K = null;
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    public class b extends Animation {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final float f1529a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final float f1530b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final float f1531c;

        public b(float f2, float f3) {
            this.f1529a = f2;
            this.f1530b = f3;
            this.f1531c = f3 - f2;
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f2, Transformation transformation) {
            SwitchCompat.this.setThumbPosition(this.f1529a + (this.f1531c * f2));
        }
    }

    public SwitchCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0460a.f7704H);
    }

    public static float f(float f2, float f3, float f4) {
        return f2 < f3 ? f3 : f2 > f4 ? f4 : f2;
    }

    private boolean getTargetCheckedState() {
        return this.f1524x > 0.5f;
    }

    private int getThumbOffset() {
        return (int) (((h0.c(this) ? 1.0f - this.f1524x : this.f1524x) * getThumbScrollRange()) + 0.5f);
    }

    private int getThumbScrollRange() {
        Drawable drawable = this.f1506f;
        if (drawable == null) {
            return 0;
        }
        Rect rect = this.f1500L;
        drawable.getPadding(rect);
        Drawable drawable2 = this.f1501a;
        Rect rectD = drawable2 != null ? H.d(drawable2) : H.f8021a;
        return ((((this.f1525y - this.f1489A) - rect.left) - rect.right) - rectD.left) - rectD.right;
    }

    public final void a(boolean z2) {
        if (this.f1499K != null) {
            d();
        }
        b bVar = new b(this.f1524x, z2 ? 1.0f : 0.0f);
        this.f1499K = bVar;
        bVar.setDuration(250L);
        this.f1499K.setAnimationListener(new a(z2));
        startAnimation(this.f1499K);
    }

    public final void b() {
        Drawable drawable = this.f1501a;
        if (drawable != null) {
            if (this.f1504d || this.f1505e) {
                Drawable drawableMutate = drawable.mutate();
                this.f1501a = drawableMutate;
                if (this.f1504d) {
                    AbstractC0244a.n(drawableMutate, this.f1502b);
                }
                if (this.f1505e) {
                    AbstractC0244a.o(this.f1501a, this.f1503c);
                }
                if (this.f1501a.isStateful()) {
                    this.f1501a.setState(getDrawableState());
                }
            }
        }
    }

    public final void c() {
        Drawable drawable = this.f1506f;
        if (drawable != null) {
            if (this.f1509i || this.f1510j) {
                Drawable drawableMutate = drawable.mutate();
                this.f1506f = drawableMutate;
                if (this.f1509i) {
                    AbstractC0244a.n(drawableMutate, this.f1507g);
                }
                if (this.f1510j) {
                    AbstractC0244a.o(this.f1506f, this.f1508h);
                }
                if (this.f1506f.isStateful()) {
                    this.f1506f.setState(getDrawableState());
                }
            }
        }
    }

    public final void d() {
        if (this.f1499K != null) {
            clearAnimation();
            this.f1499K = null;
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int i2;
        int i3;
        Rect rect = this.f1500L;
        int i4 = this.f1490B;
        int i5 = this.f1491C;
        int i6 = this.f1492D;
        int i7 = this.f1493E;
        int thumbOffset = getThumbOffset() + i4;
        Drawable drawable = this.f1501a;
        Rect rectD = drawable != null ? H.d(drawable) : H.f8021a;
        Drawable drawable2 = this.f1506f;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            int i8 = rect.left;
            thumbOffset += i8;
            if (rectD != null) {
                int i9 = rectD.left;
                if (i9 > i8) {
                    i4 += i9 - i8;
                }
                int i10 = rectD.top;
                int i11 = rect.top;
                i2 = i10 > i11 ? (i10 - i11) + i5 : i5;
                int i12 = rectD.right;
                int i13 = rect.right;
                if (i12 > i13) {
                    i6 -= i12 - i13;
                }
                int i14 = rectD.bottom;
                int i15 = rect.bottom;
                if (i14 > i15) {
                    i3 = i7 - (i14 - i15);
                }
                this.f1506f.setBounds(i4, i2, i6, i3);
            } else {
                i2 = i5;
            }
            i3 = i7;
            this.f1506f.setBounds(i4, i2, i6, i3);
        }
        Drawable drawable3 = this.f1501a;
        if (drawable3 != null) {
            drawable3.getPadding(rect);
            int i16 = thumbOffset - rect.left;
            int i17 = thumbOffset + this.f1489A + rect.right;
            this.f1501a.setBounds(i16, i5, i17, i7);
            Drawable background = getBackground();
            if (background != null) {
                AbstractC0244a.k(background, i16, i5, i17, i7);
            }
        }
        super.draw(canvas);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableHotspotChanged(float f2, float f3) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.drawableHotspotChanged(f2, f3);
        }
        Drawable drawable = this.f1501a;
        if (drawable != null) {
            AbstractC0244a.j(drawable, f2, f3);
        }
        Drawable drawable2 = this.f1506f;
        if (drawable2 != null) {
            AbstractC0244a.j(drawable2, f2, f3);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f1501a;
        boolean state = (drawable == null || !drawable.isStateful()) ? false : drawable.setState(drawableState);
        Drawable drawable2 = this.f1506f;
        if (drawable2 != null && drawable2.isStateful()) {
            state |= drawable2.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    public final void e(MotionEvent motionEvent) {
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.setAction(3);
        super.onTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
    }

    public final boolean g(float f2, float f3) {
        if (this.f1501a == null) {
            return false;
        }
        int thumbOffset = getThumbOffset();
        this.f1501a.getPadding(this.f1500L);
        int i2 = this.f1491C;
        int i3 = this.f1519s;
        int i4 = i2 - i3;
        int i5 = (this.f1490B + thumbOffset) - i3;
        int i6 = this.f1489A + i5;
        Rect rect = this.f1500L;
        return f2 > ((float) i5) && f2 < ((float) (((i6 + rect.left) + rect.right) + i3)) && f3 > ((float) i4) && f3 < ((float) (this.f1493E + i3));
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        if (!h0.c(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.f1525y;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingLeft + this.f1513m : compoundPaddingLeft;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingRight() {
        if (h0.c(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.f1525y;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingRight + this.f1513m : compoundPaddingRight;
    }

    public boolean getShowText() {
        return this.f1517q;
    }

    public boolean getSplitTrack() {
        return this.f1514n;
    }

    public int getSwitchMinWidth() {
        return this.f1512l;
    }

    public int getSwitchPadding() {
        return this.f1513m;
    }

    public CharSequence getTextOff() {
        return this.f1516p;
    }

    public CharSequence getTextOn() {
        return this.f1515o;
    }

    public Drawable getThumbDrawable() {
        return this.f1501a;
    }

    public int getThumbTextPadding() {
        return this.f1511k;
    }

    public ColorStateList getThumbTintList() {
        return this.f1502b;
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.f1503c;
    }

    public Drawable getTrackDrawable() {
        return this.f1506f;
    }

    public ColorStateList getTrackTintList() {
        return this.f1507g;
    }

    public PorterDuff.Mode getTrackTintMode() {
        return this.f1508h;
    }

    public final Layout h(CharSequence charSequence) {
        TransformationMethod transformationMethod = this.f1498J;
        if (transformationMethod != null) {
            charSequence = transformationMethod.getTransformation(charSequence, this);
        }
        CharSequence charSequence2 = charSequence;
        return new StaticLayout(charSequence2, this.f1494F, charSequence2 != null ? (int) Math.ceil(Layout.getDesiredWidth(charSequence2, r2)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    public void i(Context context, int i2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i2, p.j.W1);
        ColorStateList colorStateList = typedArrayObtainStyledAttributes.getColorStateList(p.j.a2);
        if (colorStateList != null) {
            this.f1495G = colorStateList;
        } else {
            this.f1495G = getTextColors();
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(p.j.X1, 0);
        if (dimensionPixelSize != 0) {
            float f2 = dimensionPixelSize;
            if (f2 != this.f1494F.getTextSize()) {
                this.f1494F.setTextSize(f2);
                requestLayout();
            }
        }
        k(typedArrayObtainStyledAttributes.getInt(p.j.Y1, -1), typedArrayObtainStyledAttributes.getInt(p.j.Z1, -1));
        if (typedArrayObtainStyledAttributes.getBoolean(p.j.b2, false)) {
            this.f1498J = new C0470a(getContext());
        } else {
            this.f1498J = null;
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public void j(Typeface typeface, int i2) {
        if (i2 <= 0) {
            this.f1494F.setFakeBoldText(false);
            this.f1494F.setTextSkewX(0.0f);
            setSwitchTypeface(typeface);
        } else {
            Typeface typefaceDefaultFromStyle = typeface == null ? Typeface.defaultFromStyle(i2) : Typeface.create(typeface, i2);
            setSwitchTypeface(typefaceDefaultFromStyle);
            int style = ((typefaceDefaultFromStyle != null ? typefaceDefaultFromStyle.getStyle() : 0) ^ (-1)) & i2;
            this.f1494F.setFakeBoldText((style & 1) != 0);
            this.f1494F.setTextSkewX((style & 2) != 0 ? -0.25f : 0.0f);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void jumpDrawablesToCurrentState() {
        if (Build.VERSION.SDK_INT >= 11) {
            super.jumpDrawablesToCurrentState();
            Drawable drawable = this.f1501a;
            if (drawable != null) {
                drawable.jumpToCurrentState();
            }
            Drawable drawable2 = this.f1506f;
            if (drawable2 != null) {
                drawable2.jumpToCurrentState();
            }
            d();
            setThumbPosition(isChecked() ? 1.0f : 0.0f);
        }
    }

    public final void k(int i2, int i3) {
        j(i2 != 1 ? i2 != 2 ? i2 != 3 ? null : Typeface.MONOSPACE : Typeface.SERIF : Typeface.SANS_SERIF, i3);
    }

    public final void l(MotionEvent motionEvent) {
        this.f1518r = 0;
        boolean targetCheckedState = true;
        boolean z2 = motionEvent.getAction() == 1 && isEnabled();
        boolean zIsChecked = isChecked();
        if (z2) {
            this.f1522v.computeCurrentVelocity(1000);
            float xVelocity = this.f1522v.getXVelocity();
            if (Math.abs(xVelocity) <= this.f1523w) {
                targetCheckedState = getTargetCheckedState();
            } else if (!h0.c(this) ? xVelocity <= 0.0f : xVelocity >= 0.0f) {
                targetCheckedState = false;
            }
        } else {
            targetCheckedState = zIsChecked;
        }
        if (targetCheckedState != zIsChecked) {
            playSoundEffect(0);
        }
        setChecked(targetCheckedState);
        e(motionEvent);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i2) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        if (isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, f1488M);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        int width;
        super.onDraw(canvas);
        Rect rect = this.f1500L;
        Drawable drawable = this.f1506f;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i2 = this.f1491C;
        int i3 = this.f1493E;
        int i4 = i2 + rect.top;
        int i5 = i3 - rect.bottom;
        Drawable drawable2 = this.f1501a;
        if (drawable != null) {
            if (!this.f1514n || drawable2 == null) {
                drawable.draw(canvas);
            } else {
                Rect rectD = H.d(drawable2);
                drawable2.copyBounds(rect);
                rect.left += rectD.left;
                rect.right -= rectD.right;
                int iSave = canvas.save();
                canvas.clipRect(rect, Region.Op.DIFFERENCE);
                drawable.draw(canvas);
                canvas.restoreToCount(iSave);
            }
        }
        int iSave2 = canvas.save();
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        Layout layout = getTargetCheckedState() ? this.f1496H : this.f1497I;
        if (layout != null) {
            int[] drawableState = getDrawableState();
            ColorStateList colorStateList = this.f1495G;
            if (colorStateList != null) {
                this.f1494F.setColor(colorStateList.getColorForState(drawableState, 0));
            }
            this.f1494F.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                width = bounds.left + bounds.right;
            } else {
                width = getWidth();
            }
            canvas.translate((width / 2) - (layout.getWidth() / 2), ((i4 + i5) / 2) - (layout.getHeight() / 2));
            layout.draw(canvas);
        }
        canvas.restoreToCount(iSave2);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.widget.Switch");
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName("android.widget.Switch");
            CharSequence charSequence = isChecked() ? this.f1515o : this.f1516p;
            if (TextUtils.isEmpty(charSequence)) {
                return;
            }
            CharSequence text = accessibilityNodeInfo.getText();
            if (TextUtils.isEmpty(text)) {
                accessibilityNodeInfo.setText(charSequence);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(text);
            sb.append(' ');
            sb.append(charSequence);
            accessibilityNodeInfo.setText(sb);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int iMax;
        int width;
        int paddingLeft;
        int i6;
        int paddingTop;
        int height;
        super.onLayout(z2, i2, i3, i4, i5);
        int iMax2 = 0;
        if (this.f1501a != null) {
            Rect rect = this.f1500L;
            Drawable drawable = this.f1506f;
            if (drawable != null) {
                drawable.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect rectD = H.d(this.f1501a);
            iMax = Math.max(0, rectD.left - rect.left);
            iMax2 = Math.max(0, rectD.right - rect.right);
        } else {
            iMax = 0;
        }
        if (h0.c(this)) {
            paddingLeft = getPaddingLeft() + iMax;
            width = ((this.f1525y + paddingLeft) - iMax) - iMax2;
        } else {
            width = (getWidth() - getPaddingRight()) - iMax2;
            paddingLeft = (width - this.f1525y) + iMax + iMax2;
        }
        int gravity = getGravity() & 112;
        if (gravity == 16) {
            int paddingTop2 = ((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2;
            i6 = this.f1526z;
            paddingTop = paddingTop2 - (i6 / 2);
        } else {
            if (gravity == 80) {
                height = getHeight() - getPaddingBottom();
                paddingTop = height - this.f1526z;
                this.f1490B = paddingLeft;
                this.f1491C = paddingTop;
                this.f1493E = height;
                this.f1492D = width;
            }
            paddingTop = getPaddingTop();
            i6 = this.f1526z;
        }
        height = i6 + paddingTop;
        this.f1490B = paddingLeft;
        this.f1491C = paddingTop;
        this.f1493E = height;
        this.f1492D = width;
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        int intrinsicWidth;
        int intrinsicHeight;
        if (this.f1517q) {
            if (this.f1496H == null) {
                this.f1496H = h(this.f1515o);
            }
            if (this.f1497I == null) {
                this.f1497I = h(this.f1516p);
            }
        }
        Rect rect = this.f1500L;
        Drawable drawable = this.f1501a;
        int intrinsicHeight2 = 0;
        if (drawable != null) {
            drawable.getPadding(rect);
            intrinsicWidth = (this.f1501a.getIntrinsicWidth() - rect.left) - rect.right;
            intrinsicHeight = this.f1501a.getIntrinsicHeight();
        } else {
            intrinsicWidth = 0;
            intrinsicHeight = 0;
        }
        this.f1489A = Math.max(this.f1517q ? Math.max(this.f1496H.getWidth(), this.f1497I.getWidth()) + (this.f1511k * 2) : 0, intrinsicWidth);
        Drawable drawable2 = this.f1506f;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            intrinsicHeight2 = this.f1506f.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        int iMax = rect.left;
        int iMax2 = rect.right;
        Drawable drawable3 = this.f1501a;
        if (drawable3 != null) {
            Rect rectD = H.d(drawable3);
            iMax = Math.max(iMax, rectD.left);
            iMax2 = Math.max(iMax2, rectD.right);
        }
        int iMax3 = Math.max(this.f1512l, (this.f1489A * 2) + iMax + iMax2);
        int iMax4 = Math.max(intrinsicHeight2, intrinsicHeight);
        this.f1525y = iMax3;
        this.f1526z = iMax4;
        super.onMeasure(i2, i3);
        if (getMeasuredHeight() < iMax4) {
            setMeasuredDimension(AbstractC0285k0.n(this), iMax4);
        }
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence charSequence = isChecked() ? this.f1515o : this.f1516p;
        if (charSequence != null) {
            accessibilityEvent.getText().add(charSequence);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x008b  */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            android.view.VelocityTracker r0 = r6.f1522v
            r0.addMovement(r7)
            int r0 = k.W.b(r7)
            r1 = 1
            if (r0 == 0) goto L9f
            r2 = 2
            if (r0 == r1) goto L8b
            if (r0 == r2) goto L16
            r3 = 3
            if (r0 == r3) goto L8b
            goto Lb9
        L16:
            int r0 = r6.f1518r
            if (r0 == r1) goto L57
            if (r0 == r2) goto L1e
            goto Lb9
        L1e:
            float r7 = r7.getX()
            int r0 = r6.getThumbScrollRange()
            float r2 = r6.f1520t
            float r2 = r7 - r2
            r3 = 1065353216(0x3f800000, float:1.0)
            r4 = 0
            if (r0 == 0) goto L32
            float r0 = (float) r0
            float r2 = r2 / r0
            goto L3d
        L32:
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L39
            r2 = 1065353216(0x3f800000, float:1.0)
            goto L3d
        L39:
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
        L3d:
            boolean r0 = x.h0.c(r6)
            if (r0 == 0) goto L44
            float r2 = -r2
        L44:
            float r0 = r6.f1524x
            float r0 = r0 + r2
            float r0 = f(r0, r4, r3)
            float r2 = r6.f1524x
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 == 0) goto L56
            r6.f1520t = r7
            r6.setThumbPosition(r0)
        L56:
            return r1
        L57:
            float r0 = r7.getX()
            float r3 = r7.getY()
            float r4 = r6.f1520t
            float r4 = r0 - r4
            float r4 = java.lang.Math.abs(r4)
            int r5 = r6.f1519s
            float r5 = (float) r5
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 > 0) goto L7d
            float r4 = r6.f1521u
            float r4 = r3 - r4
            float r4 = java.lang.Math.abs(r4)
            int r5 = r6.f1519s
            float r5 = (float) r5
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto Lb9
        L7d:
            r6.f1518r = r2
            android.view.ViewParent r7 = r6.getParent()
            r7.requestDisallowInterceptTouchEvent(r1)
            r6.f1520t = r0
            r6.f1521u = r3
            return r1
        L8b:
            int r0 = r6.f1518r
            if (r0 != r2) goto L96
            r6.l(r7)
            super.onTouchEvent(r7)
            return r1
        L96:
            r0 = 0
            r6.f1518r = r0
            android.view.VelocityTracker r0 = r6.f1522v
            r0.clear()
            goto Lb9
        L9f:
            float r0 = r7.getX()
            float r2 = r7.getY()
            boolean r3 = r6.isEnabled()
            if (r3 == 0) goto Lb9
            boolean r3 = r6.g(r0, r2)
            if (r3 == 0) goto Lb9
            r6.f1518r = r1
            r6.f1520t = r0
            r6.f1521u = r2
        Lb9:
            boolean r7 = super.onTouchEvent(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.SwitchCompat.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z2) {
        super.setChecked(z2);
        boolean zIsChecked = isChecked();
        if (getWindowToken() != null && AbstractC0285k0.z(this) && isShown()) {
            a(zIsChecked);
        } else {
            d();
            setThumbPosition(zIsChecked ? 1.0f : 0.0f);
        }
    }

    public void setShowText(boolean z2) {
        if (this.f1517q != z2) {
            this.f1517q = z2;
            requestLayout();
        }
    }

    public void setSplitTrack(boolean z2) {
        this.f1514n = z2;
        invalidate();
    }

    public void setSwitchMinWidth(int i2) {
        this.f1512l = i2;
        requestLayout();
    }

    public void setSwitchPadding(int i2) {
        this.f1513m = i2;
        requestLayout();
    }

    public void setSwitchTypeface(Typeface typeface) {
        if (this.f1494F.getTypeface() != typeface) {
            this.f1494F.setTypeface(typeface);
            requestLayout();
            invalidate();
        }
    }

    public void setTextOff(CharSequence charSequence) {
        this.f1516p = charSequence;
        requestLayout();
    }

    public void setTextOn(CharSequence charSequence) {
        this.f1515o = charSequence;
        requestLayout();
    }

    public void setThumbDrawable(Drawable drawable) {
        Drawable drawable2 = this.f1501a;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f1501a = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbPosition(float f2) {
        this.f1524x = f2;
        invalidate();
    }

    public void setThumbResource(int i2) {
        setThumbDrawable(q.b.d(getContext(), i2));
    }

    public void setThumbTextPadding(int i2) {
        this.f1511k = i2;
        requestLayout();
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.f1502b = colorStateList;
        this.f1504d = true;
        b();
    }

    public void setThumbTintMode(PorterDuff.Mode mode) {
        this.f1503c = mode;
        this.f1505e = true;
        b();
    }

    public void setTrackDrawable(Drawable drawable) {
        Drawable drawable2 = this.f1506f;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f1506f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i2) {
        setTrackDrawable(q.b.d(getContext(), i2));
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        this.f1507g = colorStateList;
        this.f1509i = true;
        c();
    }

    public void setTrackTintMode(PorterDuff.Mode mode) {
        this.f1508h = mode;
        this.f1510j = true;
        c();
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        setChecked(!isChecked());
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f1501a || drawable == this.f1506f;
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f1502b = null;
        this.f1503c = null;
        this.f1504d = false;
        this.f1505e = false;
        this.f1507g = null;
        this.f1508h = null;
        this.f1509i = false;
        this.f1510j = false;
        this.f1522v = VelocityTracker.obtain();
        this.f1500L = new Rect();
        this.f1494F = new TextPaint(1);
        Resources resources = getResources();
        this.f1494F.density = resources.getDisplayMetrics().density;
        f0 f0VarS = f0.s(context, attributeSet, p.j.H1, i2, 0);
        Drawable drawableF = f0VarS.f(p.j.K1);
        this.f1501a = drawableF;
        if (drawableF != null) {
            drawableF.setCallback(this);
        }
        Drawable drawableF2 = f0VarS.f(p.j.T1);
        this.f1506f = drawableF2;
        if (drawableF2 != null) {
            drawableF2.setCallback(this);
        }
        this.f1515o = f0VarS.n(p.j.I1);
        this.f1516p = f0VarS.n(p.j.J1);
        this.f1517q = f0VarS.a(p.j.L1, true);
        this.f1511k = f0VarS.e(p.j.Q1, 0);
        this.f1512l = f0VarS.e(p.j.N1, 0);
        this.f1513m = f0VarS.e(p.j.O1, 0);
        this.f1514n = f0VarS.a(p.j.M1, false);
        ColorStateList colorStateListC = f0VarS.c(p.j.R1);
        if (colorStateListC != null) {
            this.f1502b = colorStateListC;
            this.f1504d = true;
        }
        PorterDuff.Mode modeE = H.e(f0VarS.i(p.j.S1, -1), null);
        if (this.f1503c != modeE) {
            this.f1503c = modeE;
            this.f1505e = true;
        }
        if (this.f1504d || this.f1505e) {
            b();
        }
        ColorStateList colorStateListC2 = f0VarS.c(p.j.U1);
        if (colorStateListC2 != null) {
            this.f1507g = colorStateListC2;
            this.f1509i = true;
        }
        PorterDuff.Mode modeE2 = H.e(f0VarS.i(p.j.V1, -1), null);
        if (this.f1508h != modeE2) {
            this.f1508h = modeE2;
            this.f1510j = true;
        }
        if (this.f1509i || this.f1510j) {
            c();
        }
        int iL = f0VarS.l(p.j.P1, 0);
        if (iL != 0) {
            i(context, iL);
        }
        f0VarS.t();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f1519s = viewConfiguration.getScaledTouchSlop();
        this.f1523w = viewConfiguration.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }
}
