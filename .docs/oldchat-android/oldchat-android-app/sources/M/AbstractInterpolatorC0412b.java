package m;

import android.view.animation.Interpolator;

/* JADX INFO: renamed from: m.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractInterpolatorC0412b implements Interpolator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f6422a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f6423b;

    public AbstractInterpolatorC0412b(float[] fArr) {
        this.f6422a = fArr;
        this.f6423b = 1.0f / (fArr.length - 1);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        if (f2 >= 1.0f) {
            return 1.0f;
        }
        if (f2 <= 0.0f) {
            return 0.0f;
        }
        float[] fArr = this.f6422a;
        int iMin = Math.min((int) ((fArr.length - 1) * f2), fArr.length - 2);
        float f3 = this.f6423b;
        float f4 = (f2 - (iMin * f3)) / f3;
        float[] fArr2 = this.f6422a;
        float f5 = fArr2[iMin];
        return f5 + (f4 * (fArr2[iMin + 1] - f5));
    }
}
