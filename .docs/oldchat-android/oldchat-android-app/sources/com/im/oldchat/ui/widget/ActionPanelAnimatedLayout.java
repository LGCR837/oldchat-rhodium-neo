package com.im.oldchat.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public class ActionPanelAnimatedLayout extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f4805a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f4806b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4807c;

    public class a implements Animation.AnimationListener {
        public a() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            int i2 = ActionPanelAnimatedLayout.this.f4807c;
            ActionPanelAnimatedLayout.this.f4807c = -1;
            ActionPanelAnimatedLayout.this.clearAnimation();
            if (i2 < 0) {
                i2 = 8;
            }
            ActionPanelAnimatedLayout.this.d(i2);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    public ActionPanelAnimatedLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4807c = -1;
    }

    public final void d(int i2) {
        this.f4806b = true;
        try {
            super.setVisibility(i2);
        } finally {
            this.f4806b = false;
        }
    }

    public final AnimationSet e() {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(120L);
        animationSet.setInterpolator(new DecelerateInterpolator());
        animationSet.addAnimation(new AlphaAnimation(0.35f, 1.0f));
        animationSet.addAnimation(new TranslateAnimation(0.0f, 0.0f, g(8.0f), 0.0f));
        return animationSet;
    }

    public final AnimationSet f() {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(95L);
        animationSet.setInterpolator(new DecelerateInterpolator());
        animationSet.addAnimation(new AlphaAnimation(1.0f, 0.0f));
        animationSet.addAnimation(new TranslateAnimation(0.0f, 0.0f, 0.0f, g(5.2f)));
        return animationSet;
    }

    public final float g(float f2) {
        float f3 = getResources().getDisplayMetrics().density;
        if (f3 <= 0.0f) {
            f3 = 1.0f;
        }
        return f2 * f3;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f4805a = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        clearAnimation();
        this.f4805a = false;
        this.f4807c = -1;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        if (this.f4806b) {
            super.setVisibility(i2);
            return;
        }
        int visibility = getVisibility();
        if (!this.f4805a) {
            clearAnimation();
            super.setVisibility(i2);
            return;
        }
        if (i2 == visibility) {
            if (i2 == 0) {
                this.f4807c = -1;
            }
            clearAnimation();
            super.setVisibility(i2);
            return;
        }
        if (i2 == 0) {
            this.f4807c = -1;
            clearAnimation();
            d(0);
            startAnimation(e());
            return;
        }
        if (visibility != 0) {
            clearAnimation();
            super.setVisibility(i2);
            return;
        }
        this.f4807c = i2;
        clearAnimation();
        AnimationSet animationSetF = f();
        animationSetF.setAnimationListener(new a());
        startAnimation(animationSetF);
    }
}
