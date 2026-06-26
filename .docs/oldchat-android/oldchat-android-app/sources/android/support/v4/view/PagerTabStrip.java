package android.support.v4.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/* JADX INFO: loaded from: classes.dex */
public class PagerTabStrip extends android.support.v4.view.b {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f766A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public boolean f767B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int f768C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f769D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public float f770E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public float f771F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public int f772G;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f773r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f774s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f775t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f776u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f777v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f778w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final Paint f779x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final Rect f780y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f781z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PagerTabStrip.this.f867a.setCurrentItem(r2.getCurrentItem() - 1);
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewPager viewPager = PagerTabStrip.this.f867a;
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    }

    public PagerTabStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.f779x = paint;
        this.f780y = new Rect();
        this.f781z = 255;
        this.f766A = false;
        this.f767B = false;
        int i2 = this.f880n;
        this.f773r = i2;
        paint.setColor(i2);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.f774s = (int) ((3.0f * f2) + 0.5f);
        this.f775t = (int) ((6.0f * f2) + 0.5f);
        this.f776u = (int) (64.0f * f2);
        this.f778w = (int) ((16.0f * f2) + 0.5f);
        this.f768C = (int) ((1.0f * f2) + 0.5f);
        this.f777v = (int) ((f2 * 32.0f) + 0.5f);
        this.f772G = ViewConfiguration.get(context).getScaledTouchSlop();
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setTextSpacing(getTextSpacing());
        setWillNotDraw(false);
        this.f868b.setFocusable(true);
        this.f868b.setOnClickListener(new a());
        this.f870d.setFocusable(true);
        this.f870d.setOnClickListener(new b());
        if (getBackground() == null) {
            this.f766A = true;
        }
    }

    @Override // android.support.v4.view.b
    public void d(int i2, float f2, boolean z2) {
        Rect rect = this.f780y;
        int height = getHeight();
        int left = this.f869c.getLeft() - this.f778w;
        int right = this.f869c.getRight() + this.f778w;
        int i3 = height - this.f774s;
        rect.set(left, i3, right, height);
        super.d(i2, f2, z2);
        this.f781z = (int) (Math.abs(f2 - 0.5f) * 2.0f * 255.0f);
        rect.union(this.f869c.getLeft() - this.f778w, i3, this.f869c.getRight() + this.f778w, height);
        invalidate(rect);
    }

    public boolean getDrawFullUnderline() {
        return this.f766A;
    }

    @Override // android.support.v4.view.b
    public int getMinHeight() {
        return Math.max(super.getMinHeight(), this.f777v);
    }

    public int getTabIndicatorColor() {
        return this.f773r;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int left = this.f869c.getLeft() - this.f778w;
        int right = this.f869c.getRight() + this.f778w;
        int i2 = height - this.f774s;
        this.f779x.setColor((this.f781z << 24) | (this.f773r & 16777215));
        float f2 = height;
        canvas.drawRect(left, i2, right, f2, this.f779x);
        if (this.f766A) {
            this.f779x.setColor((this.f773r & 16777215) | (-16777216));
            canvas.drawRect(getPaddingLeft(), height - this.f768C, getWidth() - getPaddingRight(), f2, this.f779x);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0 && this.f769D) {
            return false;
        }
        float x2 = motionEvent.getX();
        float y2 = motionEvent.getY();
        if (action == 0) {
            this.f770E = x2;
            this.f771F = y2;
            this.f769D = false;
        } else if (action != 1) {
            if (action == 2 && (Math.abs(x2 - this.f770E) > this.f772G || Math.abs(y2 - this.f771F) > this.f772G)) {
                this.f769D = true;
            }
        } else if (x2 < this.f869c.getLeft() - this.f778w) {
            ViewPager viewPager = this.f867a;
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        } else if (x2 > this.f869c.getRight() + this.f778w) {
            ViewPager viewPager2 = this.f867a;
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
        return true;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        super.setBackgroundColor(i2);
        if (this.f767B) {
            return;
        }
        this.f766A = (i2 & (-16777216)) == 0;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.f767B) {
            return;
        }
        this.f766A = drawable == null;
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        if (this.f767B) {
            return;
        }
        this.f766A = i2 == 0;
    }

    public void setDrawFullUnderline(boolean z2) {
        this.f766A = z2;
        this.f767B = true;
        invalidate();
    }

    @Override // android.view.View
    public void setPadding(int i2, int i3, int i4, int i5) {
        int i6 = this.f775t;
        if (i5 < i6) {
            i5 = i6;
        }
        super.setPadding(i2, i3, i4, i5);
    }

    public void setTabIndicatorColor(int i2) {
        this.f773r = i2;
        this.f779x.setColor(i2);
        invalidate();
    }

    public void setTabIndicatorColorResource(int i2) {
        setTabIndicatorColor(getContext().getResources().getColor(i2));
    }

    @Override // android.support.v4.view.b
    public void setTextSpacing(int i2) {
        int i3 = this.f776u;
        if (i2 < i3) {
            i2 = i3;
        }
        super.setTextSpacing(i2);
    }
}
