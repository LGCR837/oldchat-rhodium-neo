package android.support.v4.view;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import k.AbstractC0267e0;
import k.AbstractC0270f0;
import k.AbstractC0285k0;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends ViewGroup {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final int[] f864o = {R.attr.textAppearance, R.attr.textSize, R.attr.textColor, R.attr.gravity};

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final int[] f865p = {R.attr.textAllCaps};

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final InterfaceC0010b f866q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewPager f867a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f868b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f869c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f870d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f871e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f872f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f873g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f874h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f875i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f876j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final a f877k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public WeakReference f878l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f879m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f880n;

    public class a extends DataSetObserver implements ViewPager.j, ViewPager.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f881a;

        public a() {
        }

        @Override // android.support.v4.view.ViewPager.j
        public void a(int i2, float f2, int i3) {
            if (f2 > 0.5f) {
                i2++;
            }
            b.this.d(i2, f2, false);
        }

        @Override // android.support.v4.view.ViewPager.j
        public void b(int i2) {
            this.f881a = i2;
        }

        @Override // android.support.v4.view.ViewPager.j
        public void c(int i2) {
            if (this.f881a == 0) {
                b bVar = b.this;
                bVar.c(bVar.f867a.getCurrentItem(), b.this.f867a.getAdapter());
                b bVar2 = b.this;
                float f2 = bVar2.f872f;
                if (f2 < 0.0f) {
                    f2 = 0.0f;
                }
                bVar2.d(bVar2.f867a.getCurrentItem(), f2, true);
            }
        }

        @Override // android.support.v4.view.ViewPager.i
        public void d(ViewPager viewPager, AbstractC0267e0 abstractC0267e0, AbstractC0267e0 abstractC0267e02) {
            b.this.b(abstractC0267e0, abstractC0267e02);
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            b bVar = b.this;
            bVar.c(bVar.f867a.getCurrentItem(), b.this.f867a.getAdapter());
            b bVar2 = b.this;
            float f2 = bVar2.f872f;
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            bVar2.d(bVar2.f867a.getCurrentItem(), f2, true);
        }
    }

    /* JADX INFO: renamed from: android.support.v4.view.b$b, reason: collision with other inner class name */
    public interface InterfaceC0010b {
        void a(TextView textView);
    }

    public static class c implements InterfaceC0010b {
        @Override // android.support.v4.view.b.InterfaceC0010b
        public void a(TextView textView) {
            textView.setSingleLine();
        }
    }

    public static class d implements InterfaceC0010b {
        @Override // android.support.v4.view.b.InterfaceC0010b
        public void a(TextView textView) {
            AbstractC0270f0.a(textView);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 14) {
            f866q = new d();
        } else {
            f866q = new c();
        }
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f871e = -1;
        this.f872f = -1.0f;
        this.f877k = new a();
        TextView textView = new TextView(context);
        this.f868b = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.f869c = textView2;
        addView(textView2);
        TextView textView3 = new TextView(context);
        this.f870d = textView3;
        addView(textView3);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f864o);
        boolean z2 = false;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            this.f868b.setTextAppearance(context, resourceId);
            this.f869c.setTextAppearance(context, resourceId);
            this.f870d.setTextAppearance(context, resourceId);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            a(0, dimensionPixelSize);
        }
        if (typedArrayObtainStyledAttributes.hasValue(2)) {
            int color = typedArrayObtainStyledAttributes.getColor(2, 0);
            this.f868b.setTextColor(color);
            this.f869c.setTextColor(color);
            this.f870d.setTextColor(color);
        }
        this.f874h = typedArrayObtainStyledAttributes.getInteger(3, 80);
        typedArrayObtainStyledAttributes.recycle();
        this.f880n = this.f869c.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        TextView textView4 = this.f868b;
        TextUtils.TruncateAt truncateAt = TextUtils.TruncateAt.END;
        textView4.setEllipsize(truncateAt);
        this.f869c.setEllipsize(truncateAt);
        this.f870d.setEllipsize(truncateAt);
        if (resourceId != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, f865p);
            z2 = typedArrayObtainStyledAttributes2.getBoolean(0, false);
            typedArrayObtainStyledAttributes2.recycle();
        }
        if (z2) {
            setSingleLineAllCaps(this.f868b);
            setSingleLineAllCaps(this.f869c);
            setSingleLineAllCaps(this.f870d);
        } else {
            this.f868b.setSingleLine();
            this.f869c.setSingleLine();
            this.f870d.setSingleLine();
        }
        this.f873g = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }

    private static void setSingleLineAllCaps(TextView textView) {
        f866q.a(textView);
    }

    public void a(int i2, float f2) {
        this.f868b.setTextSize(i2, f2);
        this.f869c.setTextSize(i2, f2);
        this.f870d.setTextSize(i2, f2);
    }

    public void b(AbstractC0267e0 abstractC0267e0, AbstractC0267e0 abstractC0267e02) {
        if (abstractC0267e0 != null) {
            abstractC0267e0.r(this.f877k);
            this.f878l = null;
        }
        if (abstractC0267e02 != null) {
            abstractC0267e02.j(this.f877k);
            this.f878l = new WeakReference(abstractC0267e02);
        }
        ViewPager viewPager = this.f867a;
        if (viewPager != null) {
            this.f871e = -1;
            this.f872f = -1.0f;
            c(viewPager.getCurrentItem(), abstractC0267e02);
            requestLayout();
        }
    }

    public void c(int i2, AbstractC0267e0 abstractC0267e0) {
        int iD = abstractC0267e0 != null ? abstractC0267e0.d() : 0;
        this.f875i = true;
        CharSequence charSequenceF = null;
        this.f868b.setText((i2 < 1 || abstractC0267e0 == null) ? null : abstractC0267e0.f(i2 - 1));
        this.f869c.setText((abstractC0267e0 == null || i2 >= iD) ? null : abstractC0267e0.f(i2));
        int i3 = i2 + 1;
        if (i3 < iD && abstractC0267e0 != null) {
            charSequenceF = abstractC0267e0.f(i3);
        }
        this.f870d.setText(charSequenceF);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((getWidth() - getPaddingLeft()) - getPaddingRight()) * 0.8f)), PKIFailureInfo.systemUnavail);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), PKIFailureInfo.systemUnavail);
        this.f868b.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f869c.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f870d.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f871e = i2;
        if (!this.f876j) {
            d(i2, this.f872f, false);
        }
        this.f875i = false;
    }

    public void d(int i2, float f2, boolean z2) {
        int i3;
        int i4;
        int i5;
        int i6;
        if (i2 != this.f871e) {
            c(i2, this.f867a.getAdapter());
        } else if (!z2 && f2 == this.f872f) {
            return;
        }
        this.f876j = true;
        int measuredWidth = this.f868b.getMeasuredWidth();
        int measuredWidth2 = this.f869c.getMeasuredWidth();
        int measuredWidth3 = this.f870d.getMeasuredWidth();
        int i7 = measuredWidth2 / 2;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i8 = paddingRight + i7;
        int i9 = (width - (paddingLeft + i7)) - i8;
        float f3 = 0.5f + f2;
        if (f3 > 1.0f) {
            f3 -= 1.0f;
        }
        int i10 = ((width - i8) - ((int) (i9 * f3))) - i7;
        int i11 = measuredWidth2 + i10;
        int baseline = this.f868b.getBaseline();
        int baseline2 = this.f869c.getBaseline();
        int baseline3 = this.f870d.getBaseline();
        int iMax = Math.max(Math.max(baseline, baseline2), baseline3);
        int i12 = iMax - baseline;
        int i13 = iMax - baseline2;
        int i14 = iMax - baseline3;
        int iMax2 = Math.max(Math.max(this.f868b.getMeasuredHeight() + i12, this.f869c.getMeasuredHeight() + i13), this.f870d.getMeasuredHeight() + i14);
        int i15 = this.f874h & 112;
        if (i15 == 16) {
            i3 = (((height - paddingTop) - paddingBottom) - iMax2) / 2;
        } else {
            if (i15 != 80) {
                i4 = i12 + paddingTop;
                i5 = i13 + paddingTop;
                i6 = paddingTop + i14;
                TextView textView = this.f869c;
                textView.layout(i10, i5, i11, textView.getMeasuredHeight() + i5);
                int iMin = Math.min(paddingLeft, (i10 - this.f873g) - measuredWidth);
                TextView textView2 = this.f868b;
                textView2.layout(iMin, i4, measuredWidth + iMin, textView2.getMeasuredHeight() + i4);
                int iMax3 = Math.max((width - paddingRight) - measuredWidth3, i11 + this.f873g);
                TextView textView3 = this.f870d;
                textView3.layout(iMax3, i6, iMax3 + measuredWidth3, textView3.getMeasuredHeight() + i6);
                this.f872f = f2;
                this.f876j = false;
            }
            i3 = (height - paddingBottom) - iMax2;
        }
        i4 = i12 + i3;
        i5 = i13 + i3;
        i6 = i3 + i14;
        TextView textView4 = this.f869c;
        textView4.layout(i10, i5, i11, textView4.getMeasuredHeight() + i5);
        int iMin2 = Math.min(paddingLeft, (i10 - this.f873g) - measuredWidth);
        TextView textView22 = this.f868b;
        textView22.layout(iMin2, i4, measuredWidth + iMin2, textView22.getMeasuredHeight() + i4);
        int iMax32 = Math.max((width - paddingRight) - measuredWidth3, i11 + this.f873g);
        TextView textView32 = this.f870d;
        textView32.layout(iMax32, i6, iMax32 + measuredWidth3, textView32.getMeasuredHeight() + i6);
        this.f872f = f2;
        this.f876j = false;
    }

    public int getMinHeight() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicHeight();
        }
        return 0;
    }

    public int getTextSpacing() {
        return this.f873g;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (!(parent instanceof ViewPager)) {
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        }
        ViewPager viewPager = (ViewPager) parent;
        AbstractC0267e0 adapter = viewPager.getAdapter();
        viewPager.O(this.f877k);
        viewPager.b(this.f877k);
        this.f867a = viewPager;
        WeakReference weakReference = this.f878l;
        b(weakReference != null ? (AbstractC0267e0) weakReference.get() : null, adapter);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager = this.f867a;
        if (viewPager != null) {
            b(viewPager.getAdapter(), null);
            this.f867a.O(null);
            this.f867a.H(this.f877k);
            this.f867a = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        if (this.f867a != null) {
            float f2 = this.f872f;
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            d(this.f871e, f2, true);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        int iMax;
        if (View.MeasureSpec.getMode(i2) != 1073741824) {
            throw new IllegalStateException("Must measure with an exact width");
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i3, paddingTop, -2);
        int size = View.MeasureSpec.getSize(i2);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, (int) (size * 0.2f), -2);
        this.f868b.measure(childMeasureSpec2, childMeasureSpec);
        this.f869c.measure(childMeasureSpec2, childMeasureSpec);
        this.f870d.measure(childMeasureSpec2, childMeasureSpec);
        if (View.MeasureSpec.getMode(i3) == 1073741824) {
            iMax = View.MeasureSpec.getSize(i3);
        } else {
            iMax = Math.max(getMinHeight(), this.f869c.getMeasuredHeight() + paddingTop);
        }
        setMeasuredDimension(size, AbstractC0285k0.I(iMax, i3, AbstractC0285k0.m(this.f869c) << 16));
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.f875i) {
            return;
        }
        super.requestLayout();
    }

    public void setGravity(int i2) {
        this.f874h = i2;
        requestLayout();
    }

    public void setNonPrimaryAlpha(float f2) {
        int i2 = ((int) (f2 * 255.0f)) & 255;
        this.f879m = i2;
        int i3 = (i2 << 24) | (this.f880n & 16777215);
        this.f868b.setTextColor(i3);
        this.f870d.setTextColor(i3);
    }

    public void setTextColor(int i2) {
        this.f880n = i2;
        this.f869c.setTextColor(i2);
        int i3 = (this.f879m << 24) | (this.f880n & 16777215);
        this.f868b.setTextColor(i3);
        this.f870d.setTextColor(i3);
    }

    public void setTextSpacing(int i2) {
        this.f873g = i2;
        requestLayout();
    }
}
