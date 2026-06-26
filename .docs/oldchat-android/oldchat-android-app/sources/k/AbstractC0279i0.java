package k;

import android.os.Build;
import android.view.VelocityTracker;

/* JADX INFO: renamed from: k.i0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0279i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f5697a;

    /* JADX INFO: renamed from: k.i0$a */
    public static class a implements c {
        @Override // k.AbstractC0279i0.c
        public float a(VelocityTracker velocityTracker, int i2) {
            return velocityTracker.getYVelocity();
        }

        @Override // k.AbstractC0279i0.c
        public float b(VelocityTracker velocityTracker, int i2) {
            return velocityTracker.getXVelocity();
        }
    }

    /* JADX INFO: renamed from: k.i0$b */
    public static class b implements c {
        @Override // k.AbstractC0279i0.c
        public float a(VelocityTracker velocityTracker, int i2) {
            return AbstractC0282j0.b(velocityTracker, i2);
        }

        @Override // k.AbstractC0279i0.c
        public float b(VelocityTracker velocityTracker, int i2) {
            return AbstractC0282j0.a(velocityTracker, i2);
        }
    }

    /* JADX INFO: renamed from: k.i0$c */
    public interface c {
        float a(VelocityTracker velocityTracker, int i2);

        float b(VelocityTracker velocityTracker, int i2);
    }

    static {
        if (Build.VERSION.SDK_INT >= 11) {
            f5697a = new b();
        } else {
            f5697a = new a();
        }
    }

    public static float a(VelocityTracker velocityTracker, int i2) {
        return f5697a.b(velocityTracker, i2);
    }

    public static float b(VelocityTracker velocityTracker, int i2) {
        return f5697a.a(velocityTracker, i2);
    }
}
