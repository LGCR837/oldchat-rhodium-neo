package o0;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import k.AbstractC0285k0;

/* JADX INFO: loaded from: classes.dex */
public class Z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f7050a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f7051b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f7052c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f7053d;

    public final Animation a(long j2) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.35f);
        alphaAnimation.setDuration(520L);
        alphaAnimation.setStartOffset(j2);
        alphaAnimation.setRepeatCount(-1);
        alphaAnimation.setRepeatMode(2);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setFillAfter(false);
        return alphaAnimation;
    }

    public void b(View view, View view2, View view3) {
        if (view == null || view2 == null || view3 == null) {
            return;
        }
        if (this.f7050a) {
            c();
        }
        this.f7050a = true;
        this.f7051b = view;
        this.f7052c = view2;
        this.f7053d = view3;
        AbstractC0285k0.K(view, 1.0f);
        AbstractC0285k0.K(this.f7052c, 1.0f);
        AbstractC0285k0.K(this.f7053d, 1.0f);
        Animation animationA = a(0L);
        Animation animationA2 = a(170L);
        Animation animationA3 = a(340L);
        view.startAnimation(animationA);
        view2.startAnimation(animationA2);
        view3.startAnimation(animationA3);
    }

    public void c() {
        this.f7050a = false;
        View view = this.f7051b;
        if (view != null) {
            view.clearAnimation();
            AbstractC0285k0.K(this.f7051b, 1.0f);
        }
        View view2 = this.f7052c;
        if (view2 != null) {
            view2.clearAnimation();
            AbstractC0285k0.K(this.f7052c, 1.0f);
        }
        View view3 = this.f7053d;
        if (view3 != null) {
            view3.clearAnimation();
            AbstractC0285k0.K(this.f7053d, 1.0f);
        }
        this.f7051b = null;
        this.f7052c = null;
        this.f7053d = null;
    }
}
