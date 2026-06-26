package x;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import t.C0470a;

/* JADX INFO: renamed from: x.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0496B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f8012a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d0 f8013b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d0 f8014c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d0 f8015d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public d0 f8016e;

    public C0496B(TextView textView) {
        this.f8012a = textView;
    }

    public static C0496B c(TextView textView) {
        return Build.VERSION.SDK_INT >= 17 ? new D(textView) : new C0496B(textView);
    }

    public static d0 d(Context context, C0508k c0508k, int i2) {
        ColorStateList colorStateListR = c0508k.r(context, i2);
        if (colorStateListR == null) {
            return null;
        }
        d0 d0Var = new d0();
        d0Var.f8140d = true;
        d0Var.f8137a = colorStateListR;
        return d0Var;
    }

    public final void a(Drawable drawable, d0 d0Var) {
        if (drawable == null || d0Var == null) {
            return;
        }
        C0508k.C(drawable, d0Var, this.f8012a.getDrawableState());
    }

    public void b() {
        if (this.f8013b == null && this.f8014c == null && this.f8015d == null && this.f8016e == null) {
            return;
        }
        Drawable[] compoundDrawables = this.f8012a.getCompoundDrawables();
        a(compoundDrawables[0], this.f8013b);
        a(compoundDrawables[1], this.f8014c);
        a(compoundDrawables[2], this.f8015d);
        a(compoundDrawables[3], this.f8016e);
    }

    public void e(AttributeSet attributeSet, int i2) {
        boolean zA;
        boolean z2;
        Context context = this.f8012a.getContext();
        C0508k c0508kM = C0508k.m();
        f0 f0VarS = f0.s(context, attributeSet, p.j.f7855T, i2, 0);
        int iL = f0VarS.l(p.j.f7856U, -1);
        if (f0VarS.p(p.j.f7859X)) {
            this.f8013b = d(context, c0508kM, f0VarS.l(p.j.f7859X, 0));
        }
        if (f0VarS.p(p.j.f7857V)) {
            this.f8014c = d(context, c0508kM, f0VarS.l(p.j.f7857V, 0));
        }
        if (f0VarS.p(p.j.f7860Y)) {
            this.f8015d = d(context, c0508kM, f0VarS.l(p.j.f7860Y, 0));
        }
        if (f0VarS.p(p.j.f7858W)) {
            this.f8016e = d(context, c0508kM, f0VarS.l(p.j.f7858W, 0));
        }
        f0VarS.t();
        boolean z3 = this.f8012a.getTransformationMethod() instanceof PasswordTransformationMethod;
        boolean z4 = true;
        ColorStateList colorStateListC = null;
        if (iL != -1) {
            f0 f0VarQ = f0.q(context, iL, p.j.W1);
            if (z3 || !f0VarQ.p(p.j.b2)) {
                zA = false;
                z2 = false;
            } else {
                zA = f0VarQ.a(p.j.b2, false);
                z2 = true;
            }
            if (Build.VERSION.SDK_INT < 23 && f0VarQ.p(p.j.a2)) {
                colorStateListC = f0VarQ.c(p.j.a2);
            }
            f0VarQ.t();
        } else {
            zA = false;
            z2 = false;
        }
        f0 f0VarS2 = f0.s(context, attributeSet, p.j.W1, i2, 0);
        if (z3 || !f0VarS2.p(p.j.b2)) {
            z4 = z2;
        } else {
            zA = f0VarS2.a(p.j.b2, false);
        }
        if (Build.VERSION.SDK_INT < 23 && f0VarS2.p(p.j.a2)) {
            colorStateListC = f0VarS2.c(p.j.a2);
        }
        f0VarS2.t();
        if (colorStateListC != null) {
            this.f8012a.setTextColor(colorStateListC);
        }
        if (z3 || !z4) {
            return;
        }
        g(zA);
    }

    public void f(Context context, int i2) {
        ColorStateList colorStateListC;
        f0 f0VarQ = f0.q(context, i2, p.j.W1);
        if (f0VarQ.p(p.j.b2)) {
            g(f0VarQ.a(p.j.b2, false));
        }
        if (Build.VERSION.SDK_INT < 23 && f0VarQ.p(p.j.a2) && (colorStateListC = f0VarQ.c(p.j.a2)) != null) {
            this.f8012a.setTextColor(colorStateListC);
        }
        f0VarQ.t();
    }

    public void g(boolean z2) {
        TextView textView = this.f8012a;
        textView.setTransformationMethod(z2 ? new C0470a(textView.getContext()) : null);
    }
}
