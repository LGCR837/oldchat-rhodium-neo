package x;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageButton;
import k.InterfaceC0276h0;
import p.AbstractC0460a;

/* JADX INFO: renamed from: x.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0510m extends ImageButton implements InterfaceC0276h0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C0503f f8185a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0512o f8186b;

    public C0510m(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0460a.f7732z);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0503f c0503f = this.f8185a;
        if (c0503f != null) {
            c0503f.b();
        }
    }

    @Override // k.InterfaceC0276h0
    public ColorStateList getSupportBackgroundTintList() {
        C0503f c0503f = this.f8185a;
        if (c0503f != null) {
            return c0503f.c();
        }
        return null;
    }

    @Override // k.InterfaceC0276h0
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0503f c0503f = this.f8185a;
        if (c0503f != null) {
            return c0503f.d();
        }
        return null;
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return this.f8186b.a() && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0503f c0503f = this.f8185a;
        if (c0503f != null) {
            c0503f.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        C0503f c0503f = this.f8185a;
        if (c0503f != null) {
            c0503f.g(i2);
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i2) {
        this.f8186b.c(i2);
    }

    @Override // k.InterfaceC0276h0
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0503f c0503f = this.f8185a;
        if (c0503f != null) {
            c0503f.i(colorStateList);
        }
    }

    @Override // k.InterfaceC0276h0
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0503f c0503f = this.f8185a;
        if (c0503f != null) {
            c0503f.j(mode);
        }
    }

    public C0510m(Context context, AttributeSet attributeSet, int i2) {
        super(c0.b(context), attributeSet, i2);
        C0503f c0503f = new C0503f(this);
        this.f8185a = c0503f;
        c0503f.e(attributeSet, i2);
        C0512o c0512o = new C0512o(this);
        this.f8186b = c0512o;
        c0512o.b(attributeSet, i2);
    }
}
