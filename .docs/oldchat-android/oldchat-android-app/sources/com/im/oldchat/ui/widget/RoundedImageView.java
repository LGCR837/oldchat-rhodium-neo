package com.im.oldchat.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import f0.l;

/* JADX INFO: loaded from: classes.dex */
public class RoundedImageView extends AppCompatImageView {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final RectF f4810c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Path f4811d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f4812e;

    public RoundedImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4810c = new RectF();
        this.f4811d = new Path();
        b(context, attributeSet);
    }

    public final void b(Context context, AttributeSet attributeSet) {
        this.f4812e = context.getResources().getDisplayMetrics().density * 6.0f;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.f5261a);
            try {
                this.f4812e = typedArrayObtainStyledAttributes.getDimension(l.f5262b, this.f4812e);
            } finally {
                typedArrayObtainStyledAttributes.recycle();
            }
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 11 || i2 >= 18) {
            return;
        }
        setLayerType(1, null);
    }

    public final void d() {
        this.f4811d.reset();
        float f2 = this.f4812e;
        if (f2 > 0.0f) {
            this.f4811d.addRoundRect(this.f4810c, f2, f2, Path.Direction.CW);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        if (this.f4812e <= 0.0f) {
            super.onDraw(canvas);
            return;
        }
        int iSave = canvas.save();
        canvas.clipPath(this.f4811d);
        super.onDraw(canvas);
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.f4810c.set(0.0f, 0.0f, i2, i3);
        d();
    }

    public void setCornerRadiusPx(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.f4812e = f2;
        d();
        invalidate();
    }
}
