package android.support.v7.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import x.f0;

/* JADX INFO: loaded from: classes.dex */
public class ActivityChooserView$InnerLayout extends g {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final int[] f1217p = {R.attr.background};

    public ActivityChooserView$InnerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        f0 f0VarR = f0.r(context, attributeSet, f1217p);
        setBackgroundDrawable(f0VarR.f(0));
        f0VarR.t();
    }
}
