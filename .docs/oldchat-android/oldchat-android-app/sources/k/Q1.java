package k;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class Q1 {

    public static class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ T1 f5674a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f5675b;

        public a(T1 t1, View view) {
            this.f5674a = t1;
            this.f5675b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f5674a.c(this.f5675b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f5674a.a(this.f5675b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f5674a.b(this.f5675b);
        }
    }

    public static void a(View view, T1 t1) {
        if (t1 != null) {
            view.animate().setListener(new a(t1, view));
        } else {
            view.animate().setListener(null);
        }
    }
}
