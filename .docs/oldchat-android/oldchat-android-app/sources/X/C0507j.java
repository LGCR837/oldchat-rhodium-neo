package x;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import g.AbstractC0244a;
import n.AbstractC0420c;

/* JADX INFO: renamed from: x.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0507j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CompoundButton f8161a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ColorStateList f8162b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PorterDuff.Mode f8163c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f8164d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8165e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f8166f;

    public C0507j(CompoundButton compoundButton) {
        this.f8161a = compoundButton;
    }

    public void a() {
        Drawable drawableA = AbstractC0420c.a(this.f8161a);
        if (drawableA != null) {
            if (this.f8164d || this.f8165e) {
                Drawable drawableMutate = AbstractC0244a.q(drawableA).mutate();
                if (this.f8164d) {
                    AbstractC0244a.n(drawableMutate, this.f8162b);
                }
                if (this.f8165e) {
                    AbstractC0244a.o(drawableMutate, this.f8163c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.f8161a.getDrawableState());
                }
                this.f8161a.setButtonDrawable(drawableMutate);
            }
        }
    }

    public int b(int i2) {
        Drawable drawableA;
        return (Build.VERSION.SDK_INT >= 17 || (drawableA = AbstractC0420c.a(this.f8161a)) == null) ? i2 : i2 + drawableA.getIntrinsicWidth();
    }

    public ColorStateList c() {
        return this.f8162b;
    }

    public PorterDuff.Mode d() {
        return this.f8163c;
    }

    public void e(AttributeSet attributeSet, int i2) {
        int resourceId;
        TypedArray typedArrayObtainStyledAttributes = this.f8161a.getContext().obtainStyledAttributes(attributeSet, p.j.w0, i2, 0);
        try {
            if (typedArrayObtainStyledAttributes.hasValue(p.j.x0) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(p.j.x0, 0)) != 0) {
                CompoundButton compoundButton = this.f8161a;
                compoundButton.setButtonDrawable(q.b.d(compoundButton.getContext(), resourceId));
            }
            if (typedArrayObtainStyledAttributes.hasValue(p.j.y0)) {
                AbstractC0420c.b(this.f8161a, typedArrayObtainStyledAttributes.getColorStateList(p.j.y0));
            }
            if (typedArrayObtainStyledAttributes.hasValue(p.j.z0)) {
                AbstractC0420c.c(this.f8161a, H.e(typedArrayObtainStyledAttributes.getInt(p.j.z0, -1), null));
            }
            typedArrayObtainStyledAttributes.recycle();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void f() {
        if (this.f8166f) {
            this.f8166f = false;
        } else {
            this.f8166f = true;
            a();
        }
    }

    public void g(ColorStateList colorStateList) {
        this.f8162b = colorStateList;
        this.f8164d = true;
        a();
    }

    public void h(PorterDuff.Mode mode) {
        this.f8163c = mode;
        this.f8165e = true;
        a();
    }
}
