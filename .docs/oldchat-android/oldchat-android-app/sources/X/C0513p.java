package x;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.MultiAutoCompleteTextView;
import k.InterfaceC0276h0;
import p.AbstractC0460a;

/* JADX INFO: renamed from: x.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0513p extends MultiAutoCompleteTextView implements InterfaceC0276h0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f8188c = {R.attr.popupBackground};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C0503f f8189a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0496B f8190b;

    public C0513p(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0460a.f7719m);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0503f c0503f = this.f8189a;
        if (c0503f != null) {
            c0503f.b();
        }
        C0496B c0496b = this.f8190b;
        if (c0496b != null) {
            c0496b.b();
        }
    }

    @Override // k.InterfaceC0276h0
    public ColorStateList getSupportBackgroundTintList() {
        C0503f c0503f = this.f8189a;
        if (c0503f != null) {
            return c0503f.c();
        }
        return null;
    }

    @Override // k.InterfaceC0276h0
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0503f c0503f = this.f8189a;
        if (c0503f != null) {
            return c0503f.d();
        }
        return null;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0503f c0503f = this.f8189a;
        if (c0503f != null) {
            c0503f.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        C0503f c0503f = this.f8189a;
        if (c0503f != null) {
            c0503f.g(i2);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i2) {
        setDropDownBackgroundDrawable(q.b.d(getContext(), i2));
    }

    @Override // k.InterfaceC0276h0
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0503f c0503f = this.f8189a;
        if (c0503f != null) {
            c0503f.i(colorStateList);
        }
    }

    @Override // k.InterfaceC0276h0
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0503f c0503f = this.f8189a;
        if (c0503f != null) {
            c0503f.j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        C0496B c0496b = this.f8190b;
        if (c0496b != null) {
            c0496b.f(context, i2);
        }
    }

    public C0513p(Context context, AttributeSet attributeSet, int i2) {
        super(c0.b(context), attributeSet, i2);
        f0 f0VarS = f0.s(getContext(), attributeSet, f8188c, i2, 0);
        if (f0VarS.p(0)) {
            setDropDownBackgroundDrawable(f0VarS.f(0));
        }
        f0VarS.t();
        C0503f c0503f = new C0503f(this);
        this.f8189a = c0503f;
        c0503f.e(attributeSet, i2);
        C0496B c0496bC = C0496B.c(this);
        this.f8190b = c0496bC;
        c0496bC.e(attributeSet, i2);
        this.f8190b.b();
    }
}
