package com.im.oldchat.ui.widget;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import o0.V;

/* JADX INFO: loaded from: classes.dex */
public class TiltFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Camera f4813a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Matrix f4814b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Runnable f4815c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Runnable f4816d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f4817e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f4818f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f4819g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f4820h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f4821i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f4822j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f4823k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f4824l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f4825m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f4826n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final boolean f4827o;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (TiltFrameLayout.this.s()) {
                TiltFrameLayout.this.postDelayed(this, 16L);
            }
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!TiltFrameLayout.this.f4825m || TiltFrameLayout.this.f4823k || TiltFrameLayout.this.f4824l || TiltFrameLayout.this.getWidth() <= 0 || TiltFrameLayout.this.getHeight() <= 0) {
                return;
            }
            TiltFrameLayout.this.r();
            TiltFrameLayout.this.postDelayed(this, 16L);
        }
    }

    public TiltFrameLayout(Context context) {
        this(context, null);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!j()) {
            return super.dispatchTouchEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        try {
        } catch (Throwable unused) {
            i();
        }
        if (actionMasked == 0) {
            this.f4823k = true;
            u();
            t();
            v(motionEvent.getX(), motionEvent.getY());
            invalidate();
        } else {
            if (actionMasked != 2) {
                if (actionMasked == 1 || actionMasked == 3) {
                    this.f4823k = false;
                    q();
                }
                return super.dispatchTouchEvent(motionEvent);
            }
            v(motionEvent.getX(), motionEvent.getY());
            invalidate();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        if (!j()) {
            super.draw(canvas);
            return;
        }
        int iSave = canvas.save();
        try {
            g();
            canvas.concat(this.f4814b);
            super.draw(canvas);
            canvas.restoreToCount(iSave);
        } catch (Throwable unused) {
            canvas.restoreToCount(iSave);
            i();
            super.draw(canvas);
        }
    }

    public final void f(float f2, float f3, float f4, float f5) {
        this.f4820h = h(f2, -1.0f, 1.0f);
        float fH = h(f3, -1.0f, 1.0f);
        this.f4821i = fH;
        float f6 = this.f4820h;
        this.f4818f = f6 * f4;
        this.f4817e = (-fH) * f4;
        this.f4819g = Math.max(Math.abs(f6), Math.abs(this.f4821i)) * f5;
    }

    public final void g() {
        float fM = m();
        float fN = n();
        this.f4814b.reset();
        this.f4813a.save();
        this.f4813a.translate(0.0f, 0.0f, -this.f4819g);
        this.f4813a.rotateX(this.f4817e);
        this.f4813a.rotateY(this.f4818f);
        this.f4813a.getMatrix(this.f4814b);
        this.f4813a.restore();
        this.f4814b.preTranslate(-fM, -fN);
        this.f4814b.postTranslate(fM, fN);
    }

    public final float h(float f2, float f3, float f4) {
        return f2 < f3 ? f3 : f2 > f4 ? f4 : f2;
    }

    public final void i() {
        this.f4826n = true;
        this.f4823k = false;
        this.f4824l = false;
        this.f4825m = false;
        u();
        t();
        l();
        clearAnimation();
        invalidate();
    }

    public final boolean j() {
        return this.f4827o && !this.f4826n && V.f(getContext());
    }

    public final float k(float f2, float f3, float f4) {
        return f2 + ((f3 - f2) * f4);
    }

    public final void l() {
        this.f4817e = 0.0f;
        this.f4818f = 0.0f;
        this.f4819g = 0.0f;
        this.f4820h = 0.0f;
        this.f4821i = 0.0f;
    }

    public final float m() {
        if (Math.abs(this.f4818f) < 0.01f) {
            return getWidth() * 0.5f;
        }
        if (this.f4820h < 0.0f) {
            return getWidth();
        }
        return 0.0f;
    }

    public final float n() {
        if (Math.abs(this.f4817e) < 0.01f) {
            return getHeight() * 0.5f;
        }
        if (this.f4821i < 0.0f) {
            return getHeight();
        }
        return 0.0f;
    }

    public final void o() {
        float f2 = getResources().getDisplayMetrics().density;
        try {
            Class cls = Float.TYPE;
            Camera.class.getMethod("setLocation", cls, cls, cls).invoke(this.f4813a, Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(f2 * (-40.0f)));
        } catch (Throwable unused) {
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (j()) {
            p();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        u();
        t();
    }

    public final void p() {
        if (!this.f4825m || this.f4823k || this.f4824l) {
            return;
        }
        removeCallbacks(this.f4816d);
        post(this.f4816d);
    }

    public final void q() {
        if (this.f4824l) {
            return;
        }
        this.f4824l = true;
        removeCallbacks(this.f4815c);
        post(this.f4815c);
    }

    public final void r() {
        float fK;
        float f2 = this.f4822j + 0.035f;
        this.f4822j = f2;
        if (f2 >= 4.0f) {
            this.f4822j = f2 - 4.0f;
        }
        float f3 = this.f4822j;
        float fK2 = 0.72f;
        if (f3 < 1.0f) {
            fK2 = k(-0.72f, 0.72f, f3);
            fK = -0.72f;
        } else if (f3 < 2.0f) {
            fK = k(-0.72f, 0.72f, f3 - 1.0f);
        } else if (f3 < 3.0f) {
            fK2 = k(0.72f, -0.72f, f3 - 2.0f);
            fK = 0.72f;
        } else {
            fK = k(0.72f, -0.72f, f3 - 3.0f);
            fK2 = -0.72f;
        }
        f(fK2, fK, 4.4f, 2.3f);
        invalidate();
    }

    public final boolean s() {
        float f2 = this.f4817e;
        float f3 = f2 + ((0.0f - f2) * 0.24f);
        this.f4817e = f3;
        float f4 = this.f4818f;
        this.f4818f = f4 + ((0.0f - f4) * 0.24f);
        float f5 = this.f4819g;
        this.f4819g = f5 + ((0.0f - f5) * 0.24f);
        if (Math.abs(f3) >= 0.15f || Math.abs(this.f4818f) >= 0.15f || this.f4819g >= 0.2f) {
            invalidate();
            return true;
        }
        l();
        this.f4824l = false;
        invalidate();
        p();
        return false;
    }

    public void setAutoMorphEnabled(boolean z2) {
        if (!this.f4827o) {
            this.f4825m = false;
            return;
        }
        if (this.f4825m == z2) {
            return;
        }
        this.f4825m = z2;
        if (z2) {
            p();
            return;
        }
        t();
        if (this.f4823k || this.f4824l) {
            return;
        }
        l();
        invalidate();
    }

    public final void t() {
        removeCallbacks(this.f4816d);
    }

    public final void u() {
        this.f4824l = false;
        removeCallbacks(this.f4815c);
    }

    public final void v(float f2, float f3) {
        float fMax = Math.max(1.0f, getWidth()) * 0.5f;
        float fMax2 = Math.max(1.0f, getHeight()) * 0.5f;
        f(h((f2 - fMax) / fMax, -1.0f, 1.0f), h((f3 - fMax2) / fMax2, -1.0f, 1.0f), 12.5f, 5.5f);
    }

    public TiltFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TiltFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f4813a = new Camera();
        this.f4814b = new Matrix();
        this.f4815c = new a();
        this.f4816d = new b();
        this.f4827o = true;
        setClickable(true);
        setFocusable(true);
        setWillNotDraw(false);
        o();
    }
}
