package k;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes.dex */
public abstract class P1 {

    public static class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ T1 f5672a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f5673b;

        public a(T1 t1, View view) {
            this.f5672a = t1;
            this.f5673b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f5672a.c(this.f5673b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f5672a.a(this.f5673b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f5672a.b(this.f5673b);
        }
    }

    public static void a(View view, float f2) {
        view.animate().alpha(f2);
    }

    public static void b(View view) {
        view.animate().cancel();
    }

    public static long c(View view) {
        return view.animate().getDuration();
    }

    public static void d(View view, long j2) {
        view.animate().setDuration(j2);
    }

    public static void e(View view, Interpolator interpolator) {
        view.animate().setInterpolator(interpolator);
    }

    public static void f(View view, T1 t1) {
        if (t1 != null) {
            view.animate().setListener(new a(t1, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public static void g(View view, long j2) {
        view.animate().setStartDelay(j2);
    }

    public static void h(View view) {
        view.animate().start();
    }

    public static void i(View view, float f2) {
        view.animate().translationX(f2);
    }

    public static void j(View view, float f2) {
        view.animate().translationY(f2);
    }
}
