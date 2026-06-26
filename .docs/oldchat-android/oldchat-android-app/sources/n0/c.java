package n0;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;

/* JADX INFO: loaded from: classes.dex */
public final class c implements View.OnTouchListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Camera f6698a = new Camera();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Matrix f6699b = new Matrix();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View.OnTouchListener f6700c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f6701d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f6702e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f6703f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f6704g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f6705h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f6706i;

    public static final class a extends Animation {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f6707a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Camera f6708b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Matrix f6709c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final float f6710d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final float f6711e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final float f6712f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final float f6713g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final float f6714h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final float f6715i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final float f6716j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public final float f6717k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final float f6718l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public final float f6719m;

        public a(View view, Camera camera, Matrix matrix, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11) {
            this.f6707a = view;
            this.f6708b = camera;
            this.f6709c = matrix;
            this.f6710d = f2;
            this.f6711e = f3;
            this.f6712f = f4;
            this.f6713g = f5;
            this.f6714h = f6;
            this.f6715i = f7;
            this.f6716j = f8;
            this.f6717k = f9;
            this.f6718l = f10;
            this.f6719m = f11;
        }

        public final float a(float f2) {
            if (f2 < 0.0f) {
                return 0.0f;
            }
            if (f2 > 1.0f) {
                return 1.0f;
            }
            return f2;
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f2, Transformation transformation) {
            if (this.f6707a == null || this.f6708b == null || transformation == null) {
                return;
            }
            float fA = a(f2);
            float fB = b(this.f6710d, this.f6715i, fA);
            float fB2 = b(this.f6711e, this.f6716j, fA);
            float fB3 = b(this.f6712f, this.f6717k, fA);
            float fB4 = b(this.f6713g, this.f6718l, fA);
            float fB5 = b(this.f6714h, this.f6719m, fA);
            float fMax = Math.max(1.0f, this.f6707a.getWidth());
            float fMax2 = Math.max(1.0f, this.f6707a.getHeight());
            if (Math.abs(fB2) < 0.01f) {
                fMax *= 0.5f;
            } else if (fB4 >= 0.0f) {
                fMax = 0.0f;
            }
            if (Math.abs(fB) < 0.01f) {
                fMax2 *= 0.5f;
            } else if (fB5 >= 0.0f) {
                fMax2 = 0.0f;
            }
            this.f6709c.reset();
            this.f6708b.save();
            this.f6708b.translate(0.0f, 0.0f, -fB3);
            this.f6708b.rotateX(fB);
            this.f6708b.rotateY(fB2);
            this.f6708b.getMatrix(this.f6709c);
            this.f6708b.restore();
            this.f6709c.preTranslate(-fMax, -fMax2);
            this.f6709c.postTranslate(fMax, fMax2);
            transformation.getMatrix().set(this.f6709c);
        }

        public final float b(float f2, float f3, float f4) {
            return f2 + ((f3 - f2) * f4);
        }
    }

    public c(View view, View.OnTouchListener onTouchListener) {
        this.f6700c = onTouchListener;
        c(view);
    }

    public final float a(float f2, float f3, float f4) {
        return f2 < f3 ? f3 : f2 > f4 ? f4 : f2;
    }

    public final void b(View view, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, long j2, boolean z2) {
        a aVar = new a(view, this.f6698a, this.f6699b, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11);
        aVar.setDuration(Math.max(0L, j2));
        aVar.setFillAfter(true);
        if (z2) {
            aVar.setInterpolator(new DecelerateInterpolator());
        } else {
            aVar.setInterpolator(new AccelerateDecelerateInterpolator());
        }
        view.clearAnimation();
        view.startAnimation(aVar);
    }

    public final void c(View view) {
        if (view == null) {
            return;
        }
        float f2 = view.getResources().getDisplayMetrics().density;
        try {
            Class cls = Float.TYPE;
            Camera.class.getMethod("setLocation", cls, cls, cls).invoke(this.f6698a, Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(f2 * (-40.0f)));
        } catch (Throwable unused) {
        }
    }

    public final void d(View view, float f2, float f3) {
        float fMax = Math.max(1.0f, view.getWidth());
        float fMax2 = Math.max(1.0f, view.getHeight());
        float f4 = fMax * 0.5f;
        float fA = a((f2 - f4) / f4, -1.0f, 1.0f);
        float f5 = fMax2 * 0.5f;
        float fA2 = a((f3 - f5) / f5, -1.0f, 1.0f);
        this.f6704g = fA;
        this.f6705h = fA2;
        this.f6702e = fA * 12.5f;
        this.f6701d = (-fA2) * 12.5f;
        this.f6703f = Math.max(Math.abs(fA), Math.abs(fA2)) * 5.5f;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r20, android.view.MotionEvent r21) {
        /*
            Method dump skipped, instruction units count: 228
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: n0.c.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
