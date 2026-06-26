package n;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import k.AbstractC0285k0;

/* JADX INFO: renamed from: n.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractViewOnTouchListenerC0418a implements View.OnTouchListener {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final int f6652r = ViewConfiguration.getTapTimeout();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f6655c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Runnable f6656d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f6659g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f6660h;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f6664l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f6665m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f6666n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f6667o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f6668p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f6669q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0063a f6653a = new C0063a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Interpolator f6654b = new AccelerateInterpolator();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float[] f6657e = {0.0f, 0.0f};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float[] f6658f = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float[] f6661i = {0.0f, 0.0f};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float[] f6662j = {0.0f, 0.0f};

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float[] f6663k = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* JADX INFO: renamed from: n.a$a, reason: collision with other inner class name */
    public static class C0063a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f6670a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f6671b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f6672c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f6673d;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f6679j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f6680k;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f6674e = Long.MIN_VALUE;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public long f6678i = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f6675f = 0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f6676g = 0;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f6677h = 0;

        public void a() {
            if (this.f6675f == 0) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float fG = g(e(jCurrentAnimationTimeMillis));
            long j2 = jCurrentAnimationTimeMillis - this.f6675f;
            this.f6675f = jCurrentAnimationTimeMillis;
            float f2 = j2 * fG;
            this.f6676g = (int) (this.f6672c * f2);
            this.f6677h = (int) (f2 * this.f6673d);
        }

        public int b() {
            return this.f6676g;
        }

        public int c() {
            return this.f6677h;
        }

        public int d() {
            float f2 = this.f6672c;
            return (int) (f2 / Math.abs(f2));
        }

        public final float e(long j2) {
            if (j2 < this.f6674e) {
                return 0.0f;
            }
            long j3 = this.f6678i;
            if (j3 < 0 || j2 < j3) {
                return AbstractViewOnTouchListenerC0418a.e((j2 - r0) / this.f6670a, 0.0f, 1.0f) * 0.5f;
            }
            float f2 = this.f6679j;
            return (1.0f - f2) + (f2 * AbstractViewOnTouchListenerC0418a.e((j2 - j3) / this.f6680k, 0.0f, 1.0f));
        }

        public int f() {
            float f2 = this.f6673d;
            return (int) (f2 / Math.abs(f2));
        }

        public final float g(float f2) {
            return ((-4.0f) * f2 * f2) + (f2 * 4.0f);
        }

        public boolean h() {
            return this.f6678i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.f6678i + ((long) this.f6680k);
        }

        public void i() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f6680k = AbstractViewOnTouchListenerC0418a.f((int) (jCurrentAnimationTimeMillis - this.f6674e), 0, this.f6671b);
            this.f6679j = e(jCurrentAnimationTimeMillis);
            this.f6678i = jCurrentAnimationTimeMillis;
        }

        public void j(int i2) {
            this.f6671b = i2;
        }

        public void k(int i2) {
            this.f6670a = i2;
        }

        public void l(float f2, float f3) {
            this.f6672c = f2;
            this.f6673d = f3;
        }

        public void m() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f6674e = jCurrentAnimationTimeMillis;
            this.f6678i = -1L;
            this.f6675f = jCurrentAnimationTimeMillis;
            this.f6679j = 0.5f;
            this.f6676g = 0;
            this.f6677h = 0;
        }
    }

    /* JADX INFO: renamed from: n.a$b */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractViewOnTouchListenerC0418a abstractViewOnTouchListenerC0418a = AbstractViewOnTouchListenerC0418a.this;
            if (abstractViewOnTouchListenerC0418a.f6667o) {
                if (abstractViewOnTouchListenerC0418a.f6665m) {
                    abstractViewOnTouchListenerC0418a.f6665m = false;
                    abstractViewOnTouchListenerC0418a.f6653a.m();
                }
                C0063a c0063a = AbstractViewOnTouchListenerC0418a.this.f6653a;
                if (c0063a.h() || !AbstractViewOnTouchListenerC0418a.this.u()) {
                    AbstractViewOnTouchListenerC0418a.this.f6667o = false;
                    return;
                }
                AbstractViewOnTouchListenerC0418a abstractViewOnTouchListenerC0418a2 = AbstractViewOnTouchListenerC0418a.this;
                if (abstractViewOnTouchListenerC0418a2.f6666n) {
                    abstractViewOnTouchListenerC0418a2.f6666n = false;
                    abstractViewOnTouchListenerC0418a2.c();
                }
                c0063a.a();
                AbstractViewOnTouchListenerC0418a.this.j(c0063a.b(), c0063a.c());
                AbstractC0285k0.F(AbstractViewOnTouchListenerC0418a.this.f6655c, this);
            }
        }
    }

    public AbstractViewOnTouchListenerC0418a(View view) {
        this.f6655c = view;
        float f2 = Resources.getSystem().getDisplayMetrics().density;
        float f3 = (int) ((1575.0f * f2) + 0.5f);
        o(f3, f3);
        float f4 = (int) ((f2 * 315.0f) + 0.5f);
        p(f4, f4);
        l(1);
        n(Float.MAX_VALUE, Float.MAX_VALUE);
        s(0.2f, 0.2f);
        t(1.0f, 1.0f);
        k(f6652r);
        r(500);
        q(500);
    }

    public static float e(float f2, float f3, float f4) {
        return f2 > f4 ? f4 : f2 < f3 ? f3 : f2;
    }

    public static int f(int i2, int i3, int i4) {
        return i2 > i4 ? i4 : i2 < i3 ? i3 : i2;
    }

    public abstract boolean a(int i2);

    public abstract boolean b(int i2);

    public void c() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        this.f6655c.onTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
    }

    public final float d(int i2, float f2, float f3, float f4) {
        float fH = h(this.f6657e[i2], f3, this.f6658f[i2], f2);
        if (fH == 0.0f) {
            return 0.0f;
        }
        float f5 = this.f6661i[i2];
        float f6 = this.f6662j[i2];
        float f7 = this.f6663k[i2];
        float f8 = f5 * f4;
        return fH > 0.0f ? e(fH * f8, f6, f7) : -e((-fH) * f8, f6, f7);
    }

    public final float g(float f2, float f3) {
        if (f3 == 0.0f) {
            return 0.0f;
        }
        int i2 = this.f6659g;
        if (i2 == 0 || i2 == 1) {
            if (f2 < f3) {
                if (f2 >= 0.0f) {
                    return 1.0f - (f2 / f3);
                }
                if (this.f6667o && i2 == 1) {
                    return 1.0f;
                }
            }
        } else if (i2 == 2 && f2 < 0.0f) {
            return f2 / (-f3);
        }
        return 0.0f;
    }

    public final float h(float f2, float f3, float f4, float f5) {
        float interpolation;
        float fE = e(f2 * f3, 0.0f, f4);
        float fG = g(f3 - f5, fE) - g(f5, fE);
        if (fG < 0.0f) {
            interpolation = -this.f6654b.getInterpolation(-fG);
        } else {
            if (fG <= 0.0f) {
                return 0.0f;
            }
            interpolation = this.f6654b.getInterpolation(fG);
        }
        return e(interpolation, -1.0f, 1.0f);
    }

    public final void i() {
        if (this.f6665m) {
            this.f6667o = false;
        } else {
            this.f6653a.i();
        }
    }

    public abstract void j(int i2, int i3);

    public AbstractViewOnTouchListenerC0418a k(int i2) {
        this.f6660h = i2;
        return this;
    }

    public AbstractViewOnTouchListenerC0418a l(int i2) {
        this.f6659g = i2;
        return this;
    }

    public AbstractViewOnTouchListenerC0418a m(boolean z2) {
        if (this.f6668p && !z2) {
            i();
        }
        this.f6668p = z2;
        return this;
    }

    public AbstractViewOnTouchListenerC0418a n(float f2, float f3) {
        float[] fArr = this.f6658f;
        fArr[0] = f2;
        fArr[1] = f3;
        return this;
    }

    public AbstractViewOnTouchListenerC0418a o(float f2, float f3) {
        float[] fArr = this.f6663k;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0016  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            boolean r0 = r5.f6668p
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            int r0 = k.W.b(r7)
            r2 = 1
            if (r0 == 0) goto L1a
            if (r0 == r2) goto L16
            r3 = 2
            if (r0 == r3) goto L1e
            r6 = 3
            if (r0 == r6) goto L16
            goto L58
        L16:
            r5.i()
            goto L58
        L1a:
            r5.f6666n = r2
            r5.f6664l = r1
        L1e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.f6655c
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.d(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.f6655c
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.d(r2, r7, r6, r3)
            n.a$a r7 = r5.f6653a
            r7.l(r0, r6)
            boolean r6 = r5.f6667o
            if (r6 != 0) goto L58
            boolean r6 = r5.u()
            if (r6 == 0) goto L58
            r5.v()
        L58:
            boolean r6 = r5.f6669q
            if (r6 == 0) goto L61
            boolean r6 = r5.f6667o
            if (r6 == 0) goto L61
            r1 = 1
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: n.AbstractViewOnTouchListenerC0418a.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public AbstractViewOnTouchListenerC0418a p(float f2, float f3) {
        float[] fArr = this.f6662j;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    public AbstractViewOnTouchListenerC0418a q(int i2) {
        this.f6653a.j(i2);
        return this;
    }

    public AbstractViewOnTouchListenerC0418a r(int i2) {
        this.f6653a.k(i2);
        return this;
    }

    public AbstractViewOnTouchListenerC0418a s(float f2, float f3) {
        float[] fArr = this.f6657e;
        fArr[0] = f2;
        fArr[1] = f3;
        return this;
    }

    public AbstractViewOnTouchListenerC0418a t(float f2, float f3) {
        float[] fArr = this.f6661i;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    public boolean u() {
        C0063a c0063a = this.f6653a;
        int iF = c0063a.f();
        int iD = c0063a.d();
        return (iF != 0 && b(iF)) || (iD != 0 && a(iD));
    }

    public final void v() {
        int i2;
        if (this.f6656d == null) {
            this.f6656d = new b();
        }
        this.f6667o = true;
        this.f6665m = true;
        if (this.f6664l || (i2 = this.f6660h) <= 0) {
            this.f6656d.run();
        } else {
            AbstractC0285k0.G(this.f6655c, this.f6656d, i2);
        }
        this.f6664l = true;
    }
}
