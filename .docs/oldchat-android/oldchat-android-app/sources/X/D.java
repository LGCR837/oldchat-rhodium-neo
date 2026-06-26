package x;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class D extends C0496B {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public d0 f8017f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public d0 f8018g;

    public D(TextView textView) {
        super(textView);
    }

    @Override // x.C0496B
    public void b() {
        super.b();
        if (this.f8017f == null && this.f8018g == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative = this.f8012a.getCompoundDrawablesRelative();
        a(compoundDrawablesRelative[0], this.f8017f);
        a(compoundDrawablesRelative[2], this.f8018g);
    }

    @Override // x.C0496B
    public void e(AttributeSet attributeSet, int i2) {
        super.e(attributeSet, i2);
        Context context = this.f8012a.getContext();
        C0508k c0508kM = C0508k.m();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.j.f7855T, i2, 0);
        if (typedArrayObtainStyledAttributes.hasValue(p.j.f7861Z)) {
            this.f8017f = C0496B.d(context, c0508kM, typedArrayObtainStyledAttributes.getResourceId(p.j.f7861Z, 0));
        }
        if (typedArrayObtainStyledAttributes.hasValue(p.j.f7863a0)) {
            this.f8018g = C0496B.d(context, c0508kM, typedArrayObtainStyledAttributes.getResourceId(p.j.f7863a0, 0));
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}
