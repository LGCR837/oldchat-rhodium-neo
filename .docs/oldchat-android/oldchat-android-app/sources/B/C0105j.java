package b;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;

/* JADX INFO: renamed from: b.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0105j implements InterfaceC0098c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TimeInterpolator f1753a;

    /* JADX INFO: renamed from: b.j$a */
    public static class a implements Animator.AnimatorListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final InterfaceC0097b f1754a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final r f1755b;

        public a(InterfaceC0097b interfaceC0097b, r rVar) {
            this.f1754a = interfaceC0097b;
            this.f1755b = rVar;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1754a.d(this.f1755b);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1754a.a(this.f1755b);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            this.f1754a.b(this.f1755b);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f1754a.c(this.f1755b);
        }
    }

    /* JADX INFO: renamed from: b.j$b */
    public static class b implements r {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Animator f1756a;

        /* JADX INFO: renamed from: b.j$b$a */
        public class a implements ValueAnimator.AnimatorUpdateListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ InterfaceC0099d f1757a;

            public a(InterfaceC0099d interfaceC0099d) {
                this.f1757a = interfaceC0099d;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f1757a.a(b.this);
            }
        }

        public b(Animator animator) {
            this.f1756a = animator;
        }

        @Override // b.r
        public void a(long j2) {
            this.f1756a.setDuration(j2);
        }

        @Override // b.r
        public void b(InterfaceC0099d interfaceC0099d) {
            if (p.a(this.f1756a)) {
                AbstractC0108m.a(this.f1756a).addUpdateListener(new a(interfaceC0099d));
            }
        }

        @Override // b.r
        public float c() {
            return AbstractC0108m.a(this.f1756a).getAnimatedFraction();
        }

        @Override // b.r
        public void cancel() {
            this.f1756a.cancel();
        }

        @Override // b.r
        public void d(InterfaceC0097b interfaceC0097b) {
            this.f1756a.addListener(new a(interfaceC0097b, this));
        }

        @Override // b.r
        public void e(View view) {
            this.f1756a.setTarget(view);
        }

        @Override // b.r
        public void start() {
            this.f1756a.start();
        }
    }

    @Override // b.InterfaceC0098c
    public r a() {
        return new b(ValueAnimator.ofFloat(0.0f, 1.0f));
    }

    @Override // b.InterfaceC0098c
    public void b(View view) {
        if (this.f1753a == null) {
            this.f1753a = new ValueAnimator().getInterpolator();
        }
        view.animate().setInterpolator(this.f1753a);
    }
}
