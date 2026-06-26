package x;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import p.AbstractC0460a;

/* JADX INFO: renamed from: x.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0515s extends RadioButton implements n.L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C0507j f8199a;

    public C0515s(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0460a.f7700D);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        C0507j c0507j = this.f8199a;
        return c0507j != null ? c0507j.b(compoundPaddingLeft) : compoundPaddingLeft;
    }

    public ColorStateList getSupportButtonTintList() {
        C0507j c0507j = this.f8199a;
        if (c0507j != null) {
            return c0507j.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        C0507j c0507j = this.f8199a;
        if (c0507j != null) {
            return c0507j.d();
        }
        return null;
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        C0507j c0507j = this.f8199a;
        if (c0507j != null) {
            c0507j.f();
        }
    }

    @Override // n.L
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        C0507j c0507j = this.f8199a;
        if (c0507j != null) {
            c0507j.g(colorStateList);
        }
    }

    @Override // n.L
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        C0507j c0507j = this.f8199a;
        if (c0507j != null) {
            c0507j.h(mode);
        }
    }

    public C0515s(Context context, AttributeSet attributeSet, int i2) {
        super(c0.b(context), attributeSet, i2);
        C0507j c0507j = new C0507j(this);
        this.f8199a = c0507j;
        c0507j.e(attributeSet, i2);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i2) {
        setButtonDrawable(q.b.d(getContext(), i2));
    }
}
