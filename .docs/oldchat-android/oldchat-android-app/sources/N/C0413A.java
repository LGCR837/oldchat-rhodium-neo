package n;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import java.util.ArrayList;
import m.C0411a;

/* JADX INFO: renamed from: n.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0413A extends Drawable implements Animatable {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final Interpolator f6603l = new LinearInterpolator();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Interpolator f6604m = new C0411a();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int[] f6605n = {-16777216};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f6606a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f6607b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f6608c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Resources f6609d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f6610e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Animation f6611f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f6612g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public double f6613h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public double f6614i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f6615j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Drawable.Callback f6616k;

    /* JADX INFO: renamed from: n.A$a */
    public class a extends Animation {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ d f6617a;

        public a(d dVar) {
            this.f6617a = dVar;
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f2, Transformation transformation) {
            C0413A c0413a = C0413A.this;
            if (c0413a.f6615j) {
                c0413a.a(f2, this.f6617a);
                return;
            }
            float fC = c0413a.c(this.f6617a);
            float fJ = this.f6617a.j();
            float fL = this.f6617a.l();
            float fK = this.f6617a.k();
            C0413A.this.m(f2, this.f6617a);
            if (f2 <= 0.5f) {
                this.f6617a.D(fL + ((0.8f - fC) * C0413A.f6604m.getInterpolation(f2 / 0.5f)));
            }
            if (f2 > 0.5f) {
                this.f6617a.z(fJ + ((0.8f - fC) * C0413A.f6604m.getInterpolation((f2 - 0.5f) / 0.5f)));
            }
            this.f6617a.B(fK + (0.25f * f2));
            C0413A c0413a2 = C0413A.this;
            c0413a2.h((f2 * 216.0f) + ((c0413a2.f6612g / 5.0f) * 1080.0f));
        }
    }

    /* JADX INFO: renamed from: n.A$c */
    public class c implements Drawable.Callback {
        public c() {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
            C0413A.this.invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
            C0413A.this.scheduleSelf(runnable, j2);
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            C0413A.this.unscheduleSelf(runnable);
        }
    }

    /* JADX INFO: renamed from: n.A$d */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final RectF f6622a = new RectF();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Paint f6623b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Paint f6624c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Drawable.Callback f6625d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f6626e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f6627f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public float f6628g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public float f6629h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public float f6630i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int[] f6631j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f6632k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public float f6633l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public float f6634m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public float f6635n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public boolean f6636o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public Path f6637p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public float f6638q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public double f6639r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f6640s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public int f6641t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public int f6642u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public final Paint f6643v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public int f6644w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public int f6645x;

        public d(Drawable.Callback callback) {
            Paint paint = new Paint();
            this.f6623b = paint;
            Paint paint2 = new Paint();
            this.f6624c = paint2;
            this.f6626e = 0.0f;
            this.f6627f = 0.0f;
            this.f6628g = 0.0f;
            this.f6629h = 5.0f;
            this.f6630i = 2.5f;
            this.f6643v = new Paint(1);
            this.f6625d = callback;
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
        }

        public void A(int i2, int i3) {
            double dCeil;
            float fMin = Math.min(i2, i3);
            double d2 = this.f6639r;
            if (d2 <= 0.0d || fMin < 0.0f) {
                dCeil = Math.ceil(this.f6629h / 2.0f);
            } else {
                double d3 = fMin / 2.0f;
                Double.isNaN(d3);
                dCeil = d3 - d2;
            }
            this.f6630i = (float) dCeil;
        }

        public void B(float f2) {
            this.f6628g = f2;
            o();
        }

        public void C(boolean z2) {
            if (this.f6636o != z2) {
                this.f6636o = z2;
                o();
            }
        }

        public void D(float f2) {
            this.f6626e = f2;
            o();
        }

        public void E(float f2) {
            this.f6629h = f2;
            this.f6623b.setStrokeWidth(f2);
            o();
        }

        public void F() {
            this.f6633l = this.f6626e;
            this.f6634m = this.f6627f;
            this.f6635n = this.f6628g;
        }

        public void a(Canvas canvas, Rect rect) {
            RectF rectF = this.f6622a;
            rectF.set(rect);
            float f2 = this.f6630i;
            rectF.inset(f2, f2);
            float f3 = this.f6626e;
            float f4 = this.f6628g;
            float f5 = (f3 + f4) * 360.0f;
            float f6 = ((this.f6627f + f4) * 360.0f) - f5;
            this.f6623b.setColor(this.f6645x);
            canvas.drawArc(rectF, f5, f6, false, this.f6623b);
            b(canvas, f5, f6, rect);
            if (this.f6642u < 255) {
                this.f6643v.setColor(this.f6644w);
                this.f6643v.setAlpha(255 - this.f6642u);
                canvas.drawCircle(rect.exactCenterX(), rect.exactCenterY(), rect.width() / 2, this.f6643v);
            }
        }

        public final void b(Canvas canvas, float f2, float f3, Rect rect) {
            if (this.f6636o) {
                Path path = this.f6637p;
                if (path == null) {
                    Path path2 = new Path();
                    this.f6637p = path2;
                    path2.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    path.reset();
                }
                float f4 = (((int) this.f6630i) / 2) * this.f6638q;
                double dCos = this.f6639r * Math.cos(0.0d);
                double dExactCenterX = rect.exactCenterX();
                Double.isNaN(dExactCenterX);
                float f5 = (float) (dCos + dExactCenterX);
                double dSin = this.f6639r * Math.sin(0.0d);
                double dExactCenterY = rect.exactCenterY();
                Double.isNaN(dExactCenterY);
                float f6 = (float) (dSin + dExactCenterY);
                this.f6637p.moveTo(0.0f, 0.0f);
                this.f6637p.lineTo(this.f6640s * this.f6638q, 0.0f);
                Path path3 = this.f6637p;
                float f7 = this.f6640s;
                float f8 = this.f6638q;
                path3.lineTo((f7 * f8) / 2.0f, this.f6641t * f8);
                this.f6637p.offset(f5 - f4, f6);
                this.f6637p.close();
                this.f6624c.setColor(this.f6645x);
                canvas.rotate((f2 + f3) - 5.0f, rect.exactCenterX(), rect.exactCenterY());
                canvas.drawPath(this.f6637p, this.f6624c);
            }
        }

        public int c() {
            return this.f6642u;
        }

        public double d() {
            return this.f6639r;
        }

        public float e() {
            return this.f6627f;
        }

        public int f() {
            return this.f6631j[g()];
        }

        public final int g() {
            return (this.f6632k + 1) % this.f6631j.length;
        }

        public float h() {
            return this.f6626e;
        }

        public int i() {
            return this.f6631j[this.f6632k];
        }

        public float j() {
            return this.f6634m;
        }

        public float k() {
            return this.f6635n;
        }

        public float l() {
            return this.f6633l;
        }

        public float m() {
            return this.f6629h;
        }

        public void n() {
            x(g());
        }

        public final void o() {
            this.f6625d.invalidateDrawable(null);
        }

        public void p() {
            this.f6633l = 0.0f;
            this.f6634m = 0.0f;
            this.f6635n = 0.0f;
            D(0.0f);
            z(0.0f);
            B(0.0f);
        }

        public void q(int i2) {
            this.f6642u = i2;
        }

        public void r(float f2, float f3) {
            this.f6640s = (int) f2;
            this.f6641t = (int) f3;
        }

        public void s(float f2) {
            if (f2 != this.f6638q) {
                this.f6638q = f2;
                o();
            }
        }

        public void t(int i2) {
            this.f6644w = i2;
        }

        public void u(double d2) {
            this.f6639r = d2;
        }

        public void v(int i2) {
            this.f6645x = i2;
        }

        public void w(ColorFilter colorFilter) {
            this.f6623b.setColorFilter(colorFilter);
            o();
        }

        public void x(int i2) {
            this.f6632k = i2;
            this.f6645x = this.f6631j[i2];
        }

        public void y(int[] iArr) {
            this.f6631j = iArr;
            x(0);
        }

        public void z(float f2) {
            this.f6627f = f2;
            o();
        }
    }

    public C0413A(Context context, View view) {
        c cVar = new c();
        this.f6616k = cVar;
        this.f6610e = view;
        this.f6609d = context.getResources();
        d dVar = new d(cVar);
        this.f6607b = dVar;
        dVar.y(f6605n);
        n(1);
        k();
    }

    public void a(float f2, d dVar) {
        m(f2, dVar);
        float fFloor = (float) (Math.floor(dVar.k() / 0.8f) + 1.0d);
        dVar.D(dVar.l() + (((dVar.j() - c(dVar)) - dVar.l()) * f2));
        dVar.z(dVar.j());
        dVar.B(dVar.k() + ((fFloor - dVar.k()) * f2));
    }

    public final int b(float f2, int i2, int i3) {
        return ((((i2 >> 24) & 255) + ((int) ((((i3 >> 24) & 255) - r0) * f2))) << 24) | ((((i2 >> 16) & 255) + ((int) ((((i3 >> 16) & 255) - r1) * f2))) << 16) | ((((i2 >> 8) & 255) + ((int) ((((i3 >> 8) & 255) - r2) * f2))) << 8) | ((i2 & 255) + ((int) (f2 * ((i3 & 255) - r8))));
    }

    public float c(d dVar) {
        double dM = dVar.m();
        double d2 = dVar.d() * 6.283185307179586d;
        Double.isNaN(dM);
        return (float) Math.toRadians(dM / d2);
    }

    public void d(float f2) {
        this.f6607b.s(f2);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int iSave = canvas.save();
        canvas.rotate(this.f6608c, bounds.exactCenterX(), bounds.exactCenterY());
        this.f6607b.a(canvas, bounds);
        canvas.restoreToCount(iSave);
    }

    public void e(int i2) {
        this.f6607b.t(i2);
    }

    public void f(int... iArr) {
        this.f6607b.y(iArr);
        this.f6607b.x(0);
    }

    public void g(float f2) {
        this.f6607b.B(f2);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f6607b.c();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.f6614i;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int) this.f6613h;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public void h(float f2) {
        this.f6608c = f2;
        invalidateSelf();
    }

    public final void i(double d2, double d3, double d4, double d5, float f2, float f3) {
        d dVar = this.f6607b;
        float f4 = this.f6609d.getDisplayMetrics().density;
        double d6 = f4;
        Double.isNaN(d6);
        this.f6613h = d2 * d6;
        Double.isNaN(d6);
        this.f6614i = d3 * d6;
        dVar.E(((float) d5) * f4);
        Double.isNaN(d6);
        dVar.u(d4 * d6);
        dVar.x(0);
        dVar.r(f2 * f4, f3 * f4);
        dVar.A((int) this.f6613h, (int) this.f6614i);
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        ArrayList arrayList = this.f6606a;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            Animation animation = (Animation) arrayList.get(i2);
            if (animation.hasStarted() && !animation.hasEnded()) {
                return true;
            }
        }
        return false;
    }

    public void j(float f2, float f3) {
        this.f6607b.D(f2);
        this.f6607b.z(f3);
    }

    public final void k() {
        d dVar = this.f6607b;
        a aVar = new a(dVar);
        aVar.setRepeatCount(-1);
        aVar.setRepeatMode(1);
        aVar.setInterpolator(f6603l);
        aVar.setAnimationListener(new b(dVar));
        this.f6611f = aVar;
    }

    public void l(boolean z2) {
        this.f6607b.C(z2);
    }

    public void m(float f2, d dVar) {
        if (f2 > 0.75f) {
            dVar.v(b((f2 - 0.75f) / 0.25f, dVar.i(), dVar.f()));
        }
    }

    public void n(int i2) {
        if (i2 == 0) {
            i(56.0d, 56.0d, 12.5d, 3.0d, 12.0f, 6.0f);
        } else {
            i(40.0d, 40.0d, 8.75d, 2.5d, 10.0f, 5.0f);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f6607b.q(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f6607b.w(colorFilter);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.f6611f.reset();
        this.f6607b.F();
        if (this.f6607b.e() != this.f6607b.h()) {
            this.f6615j = true;
            this.f6611f.setDuration(666L);
            this.f6610e.startAnimation(this.f6611f);
        } else {
            this.f6607b.x(0);
            this.f6607b.p();
            this.f6611f.setDuration(1332L);
            this.f6610e.startAnimation(this.f6611f);
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.f6610e.clearAnimation();
        h(0.0f);
        this.f6607b.C(false);
        this.f6607b.x(0);
        this.f6607b.p();
    }

    /* JADX INFO: renamed from: n.A$b */
    public class b implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ d f6619a;

        public b(d dVar) {
            this.f6619a = dVar;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            this.f6619a.F();
            this.f6619a.n();
            d dVar = this.f6619a;
            dVar.D(dVar.e());
            C0413A c0413a = C0413A.this;
            if (!c0413a.f6615j) {
                c0413a.f6612g = (c0413a.f6612g + 1.0f) % 5.0f;
                return;
            }
            c0413a.f6615j = false;
            animation.setDuration(1332L);
            this.f6619a.C(false);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            C0413A.this.f6612g = 0.0f;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
        }
    }
}
