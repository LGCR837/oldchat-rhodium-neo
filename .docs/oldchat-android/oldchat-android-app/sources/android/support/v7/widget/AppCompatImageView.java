package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import k.InterfaceC0276h0;
import x.C0503f;
import x.C0512o;
import x.c0;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatImageView extends ImageView implements InterfaceC0276h0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C0503f f1218a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0512o f1219b;

    public AppCompatImageView(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0503f c0503f = this.f1218a;
        if (c0503f != null) {
            c0503f.b();
        }
    }

    @Override // k.InterfaceC0276h0
    public ColorStateList getSupportBackgroundTintList() {
        C0503f c0503f = this.f1218a;
        if (c0503f != null) {
            return c0503f.c();
        }
        return null;
    }

    @Override // k.InterfaceC0276h0
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0503f c0503f = this.f1218a;
        if (c0503f != null) {
            return c0503f.d();
        }
        return null;
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return this.f1219b.a() && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0503f c0503f = this.f1218a;
        if (c0503f != null) {
            c0503f.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        C0503f c0503f = this.f1218a;
        if (c0503f != null) {
            c0503f.g(i2);
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i2) {
        this.f1219b.c(i2);
    }

    @Override // k.InterfaceC0276h0
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0503f c0503f = this.f1218a;
        if (c0503f != null) {
            c0503f.i(colorStateList);
        }
    }

    @Override // k.InterfaceC0276h0
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0503f c0503f = this.f1218a;
        if (c0503f != null) {
            c0503f.j(mode);
        }
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet, int i2) {
        super(c0.b(context), attributeSet, i2);
        C0503f c0503f = new C0503f(this);
        this.f1218a = c0503f;
        c0503f.e(attributeSet, i2);
        C0512o c0512o = new C0512o(this);
        this.f1219b = c0512o;
        c0512o.b(attributeSet, i2);
    }
}
