package x;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import g.AbstractC0244a;
import k.AbstractC0285k0;

/* JADX INFO: renamed from: x.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0518v extends r {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SeekBar f8202d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f8203e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ColorStateList f8204f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public PorterDuff.Mode f8205g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f8206h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f8207i;

    public C0518v(SeekBar seekBar) {
        super(seekBar);
        this.f8204f = null;
        this.f8205g = null;
        this.f8206h = false;
        this.f8207i = false;
        this.f8202d = seekBar;
    }

    @Override // x.r
    public void c(AttributeSet attributeSet, int i2) {
        super.c(attributeSet, i2);
        f0 f0VarS = f0.s(this.f8202d.getContext(), attributeSet, p.j.f7850O, i2, 0);
        Drawable drawableG = f0VarS.g(p.j.f7851P);
        if (drawableG != null) {
            this.f8202d.setThumb(drawableG);
        }
        j(f0VarS.f(p.j.f7852Q));
        if (f0VarS.p(p.j.f7854S)) {
            this.f8205g = H.e(f0VarS.i(p.j.f7854S, -1), this.f8205g);
            this.f8207i = true;
        }
        if (f0VarS.p(p.j.f7853R)) {
            this.f8204f = f0VarS.c(p.j.f7853R);
            this.f8206h = true;
        }
        f0VarS.t();
        f();
    }

    public final void f() {
        Drawable drawable = this.f8203e;
        if (drawable != null) {
            if (this.f8206h || this.f8207i) {
                Drawable drawableQ = AbstractC0244a.q(drawable.mutate());
                this.f8203e = drawableQ;
                if (this.f8206h) {
                    AbstractC0244a.n(drawableQ, this.f8204f);
                }
                if (this.f8207i) {
                    AbstractC0244a.o(this.f8203e, this.f8205g);
                }
                if (this.f8203e.isStateful()) {
                    this.f8203e.setState(this.f8202d.getDrawableState());
                }
            }
        }
    }

    public void g(Canvas canvas) {
        if (this.f8203e != null) {
            int max = this.f8202d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f8203e.getIntrinsicWidth();
                int intrinsicHeight = this.f8203e.getIntrinsicHeight();
                int i2 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i3 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f8203e.setBounds(-i2, -i3, i2, i3);
                float width = ((this.f8202d.getWidth() - this.f8202d.getPaddingLeft()) - this.f8202d.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(this.f8202d.getPaddingLeft(), this.f8202d.getHeight() / 2);
                for (int i4 = 0; i4 <= max; i4++) {
                    this.f8203e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }

    public void h() {
        Drawable drawable = this.f8203e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f8202d.getDrawableState())) {
            this.f8202d.invalidateDrawable(drawable);
        }
    }

    public void i() {
        Drawable drawable = this.f8203e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    public void j(Drawable drawable) {
        Drawable drawable2 = this.f8203e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f8203e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f8202d);
            AbstractC0244a.l(drawable, AbstractC0285k0.k(this.f8202d));
            if (drawable.isStateful()) {
                drawable.setState(this.f8202d.getDrawableState());
            }
            f();
        }
        this.f8202d.invalidate();
    }
}
