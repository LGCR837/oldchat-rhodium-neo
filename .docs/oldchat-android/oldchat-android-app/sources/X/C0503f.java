package x;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import k.AbstractC0285k0;

/* JADX INFO: renamed from: x.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0503f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f8145a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a f8148d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f8149e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a f8150f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f8147c = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0508k f8146b = C0508k.m();

    /* JADX INFO: renamed from: x.f$a */
    public static class a extends d0 {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ColorStateList f8151e;

        @Override // x.d0
        public void a() {
            super.a();
            this.f8151e = null;
        }
    }

    public C0503f(View view) {
        this.f8145a = view;
    }

    public final boolean a(Drawable drawable) {
        if (this.f8150f == null) {
            this.f8150f = new a();
        }
        a aVar = this.f8150f;
        aVar.a();
        ColorStateList colorStateListF = AbstractC0285k0.f(this.f8145a);
        if (colorStateListF != null) {
            aVar.f8140d = true;
            aVar.f8137a = colorStateListF;
        }
        PorterDuff.Mode modeG = AbstractC0285k0.g(this.f8145a);
        if (modeG != null) {
            aVar.f8139c = true;
            aVar.f8138b = modeG;
        }
        if (!aVar.f8140d && !aVar.f8139c) {
            return false;
        }
        C0508k.C(drawable, aVar, this.f8145a.getDrawableState());
        return true;
    }

    public void b() {
        Drawable background = this.f8145a.getBackground();
        if (background != null) {
            if (Build.VERSION.SDK_INT == 21 && a(background)) {
                return;
            }
            a aVar = this.f8149e;
            if (aVar != null) {
                C0508k.C(background, aVar, this.f8145a.getDrawableState());
                return;
            }
            a aVar2 = this.f8148d;
            if (aVar2 != null) {
                C0508k.C(background, aVar2, this.f8145a.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        a aVar = this.f8149e;
        if (aVar != null) {
            return aVar.f8137a;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        a aVar = this.f8149e;
        if (aVar != null) {
            return aVar.f8138b;
        }
        return null;
    }

    public void e(AttributeSet attributeSet, int i2) {
        f0 f0VarS = f0.s(this.f8145a.getContext(), attributeSet, p.j.I2, i2, 0);
        try {
            if (f0VarS.p(p.j.J2)) {
                this.f8147c = f0VarS.l(p.j.J2, -1);
                ColorStateList colorStateListR = this.f8146b.r(this.f8145a.getContext(), this.f8147c);
                if (colorStateListR != null) {
                    h(colorStateListR);
                }
            }
            if (f0VarS.p(p.j.K2)) {
                AbstractC0285k0.L(this.f8145a, f0VarS.c(p.j.K2));
            }
            if (f0VarS.p(p.j.L2)) {
                AbstractC0285k0.M(this.f8145a, H.e(f0VarS.i(p.j.L2, -1), null));
            }
            f0VarS.t();
        } catch (Throwable th) {
            f0VarS.t();
            throw th;
        }
    }

    public void f(Drawable drawable) {
        this.f8147c = -1;
        h(null);
        if (k()) {
            b();
        }
    }

    public void g(int i2) {
        this.f8147c = i2;
        C0508k c0508k = this.f8146b;
        h(c0508k != null ? c0508k.r(this.f8145a.getContext(), i2) : null);
        if (k()) {
            b();
        }
    }

    public void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f8148d == null) {
                this.f8148d = new a();
            }
            a aVar = this.f8148d;
            aVar.f8137a = colorStateList;
            aVar.f8140d = true;
        } else {
            this.f8148d = null;
        }
        b();
    }

    public void i(ColorStateList colorStateList) {
        if (this.f8149e == null) {
            this.f8149e = new a();
        }
        a aVar = this.f8149e;
        aVar.f8151e = colorStateList;
        aVar.f8137a = null;
        aVar.f8140d = true;
        if (k()) {
            b();
        }
    }

    public void j(PorterDuff.Mode mode) {
        if (this.f8149e == null) {
            this.f8149e = new a();
        }
        a aVar = this.f8149e;
        aVar.f8138b = mode;
        aVar.f8139c = true;
        b();
    }

    public final boolean k() {
        ColorStateList colorStateListS;
        a aVar = this.f8149e;
        if (aVar == null || !aVar.f8140d) {
            return false;
        }
        if (this.f8147c >= 0 && (colorStateListS = this.f8146b.s(this.f8145a.getContext(), this.f8147c, this.f8149e.f8151e)) != null) {
            this.f8149e.f8137a = colorStateListS;
            return true;
        }
        a aVar2 = this.f8149e;
        ColorStateList colorStateList = aVar2.f8137a;
        ColorStateList colorStateList2 = aVar2.f8151e;
        if (colorStateList == colorStateList2) {
            return false;
        }
        aVar2.f8137a = colorStateList2;
        return true;
    }
}
