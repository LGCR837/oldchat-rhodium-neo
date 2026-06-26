package k;

import android.animation.ValueAnimator;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class S1 {

    public static class a implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ V1 f5676a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f5677b;

        public a(V1 v1, View view) {
            this.f5676a = v1;
            this.f5677b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f5676a.a(this.f5677b);
        }
    }

    public static void a(View view, V1 v1) {
        view.animate().setUpdateListener(v1 != null ? new a(v1, view) : null);
    }
}
