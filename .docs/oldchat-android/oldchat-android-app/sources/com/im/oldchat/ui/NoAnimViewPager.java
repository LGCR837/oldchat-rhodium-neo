package com.im.oldchat.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes.dex */
public class NoAnimViewPager extends ViewPager {

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public boolean f3541k0;

    public NoAnimViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        R();
    }

    @Override // android.support.v4.view.ViewPager
    public void L(int i2, boolean z2) {
        super.L(i2, z2);
    }

    public final void R() {
        this.f3541k0 = false;
    }

    @Override // android.support.v4.view.ViewPager
    public void setCurrentItem(int i2) {
        super.L(i2, this.f3541k0);
    }

    public void setDefaultSmoothScroll(boolean z2) {
        this.f3541k0 = z2;
    }
}
