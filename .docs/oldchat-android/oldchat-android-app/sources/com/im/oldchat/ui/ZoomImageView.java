package com.im.oldchat.ui;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class ZoomImageView extends AppCompatImageView {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix f4347c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float[] f4348d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ScaleGestureDetector f4349e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f4350f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f4351g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4352h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4353i;

    public class b extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        public b() {
        }

        /* JADX WARN: Removed duplicated region for block: B:4:0x0012 A[PHI: r3
  0x0012: PHI (r3v2 float) = (r3v0 float), (r3v1 float) binds: [B:3:0x0010, B:6:0x0019] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean onScale(android.view.ScaleGestureDetector r6) {
            /*
                r5 = this;
                float r0 = r6.getScaleFactor()
                com.im.oldchat.ui.ZoomImageView r1 = com.im.oldchat.ui.ZoomImageView.this
                float r1 = com.im.oldchat.ui.ZoomImageView.b(r1)
                float r2 = r1 * r0
                r3 = 1065353216(0x3f800000, float:1.0)
                int r4 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r4 >= 0) goto L15
            L12:
                float r0 = r3 / r1
                goto L1c
            L15:
                r3 = 1082130432(0x40800000, float:4.0)
                int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r2 <= 0) goto L1c
                goto L12
            L1c:
                com.im.oldchat.ui.ZoomImageView r1 = com.im.oldchat.ui.ZoomImageView.this
                android.graphics.Matrix r1 = com.im.oldchat.ui.ZoomImageView.d(r1)
                float r2 = r6.getFocusX()
                float r6 = r6.getFocusY()
                r1.postScale(r0, r0, r2, r6)
                com.im.oldchat.ui.ZoomImageView r6 = com.im.oldchat.ui.ZoomImageView.this
                android.graphics.Matrix r0 = com.im.oldchat.ui.ZoomImageView.d(r6)
                r6.setImageMatrix(r0)
                r6 = 1
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.ZoomImageView.b.onScale(android.view.ScaleGestureDetector):boolean");
        }
    }

    public ZoomImageView(Context context) {
        super(context);
        this.f4347c = new Matrix();
        this.f4348d = new float[9];
        this.f4353i = false;
        f(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getScale() {
        this.f4347c.getValues(this.f4348d);
        return this.f4348d[0];
    }

    public final void e() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        int width = getWidth();
        int height = getHeight();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (width == 0 || height == 0 || intrinsicWidth == 0 || intrinsicHeight == 0) {
            return;
        }
        this.f4347c.reset();
        float f2 = width;
        float f3 = intrinsicWidth;
        float f4 = height;
        float f5 = intrinsicHeight;
        float fMin = Math.min(f2 / f3, f4 / f5);
        if (fMin < 1.0f) {
            fMin = 1.0f;
        }
        this.f4347c.postScale(fMin, fMin);
        this.f4347c.postTranslate((f2 - (f3 * fMin)) / 2.0f, (f4 - (f5 * fMin)) / 2.0f);
        setImageMatrix(this.f4347c);
    }

    public final void f(Context context) {
        setScaleType(ImageView.ScaleType.MATRIX);
        this.f4349e = new ScaleGestureDetector(context, new b());
    }

    @Override // android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        if (this.f4353i || getDrawable() == null) {
            return;
        }
        e();
        this.f4353i = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x005c  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r10) {
        /*
            r9 = this;
            android.view.ScaleGestureDetector r0 = r9.f4349e
            if (r0 == 0) goto L7
            r0.onTouchEvent(r10)
        L7:
            int r0 = r10.getActionMasked()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L5f
            if (r0 == r2) goto L5c
            r3 = 2
            if (r0 == r3) goto L18
            r10 = 3
            if (r0 == r10) goto L5c
            goto L6d
        L18:
            android.view.ScaleGestureDetector r0 = r9.f4349e
            boolean r0 = r0.isInProgress()
            if (r0 != 0) goto L6d
            float r0 = r10.getX()
            float r3 = r9.f4350f
            float r0 = r0 - r3
            float r3 = r10.getY()
            float r4 = r9.f4351g
            float r3 = r3 - r4
            boolean r4 = r9.f4352h
            if (r4 != 0) goto L41
            double r4 = (double) r0
            double r6 = (double) r3
            double r4 = java.lang.Math.hypot(r4, r6)
            r6 = 4616189618054758400(0x4010000000000000, double:4.0)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 <= 0) goto L3f
            r1 = 1
        L3f:
            r9.f4352h = r1
        L41:
            boolean r1 = r9.f4352h
            if (r1 == 0) goto L6d
            android.graphics.Matrix r1 = r9.f4347c
            r1.postTranslate(r0, r3)
            android.graphics.Matrix r0 = r9.f4347c
            r9.setImageMatrix(r0)
            float r0 = r10.getX()
            r9.f4350f = r0
            float r10 = r10.getY()
            r9.f4351g = r10
            goto L6d
        L5c:
            r9.f4352h = r1
            goto L6d
        L5f:
            float r0 = r10.getX()
            r9.f4350f = r0
            float r10 = r10.getY()
            r9.f4351g = r10
            r9.f4352h = r1
        L6d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.im.oldchat.ui.ZoomImageView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        this.f4353i = false;
    }

    public ZoomImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4347c = new Matrix();
        this.f4348d = new float[9];
        this.f4353i = false;
        f(context);
    }
}
