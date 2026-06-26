package x;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.TypedValue;
import f.AbstractC0239a;

/* JADX INFO: loaded from: classes.dex */
public abstract class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal f8124a = new ThreadLocal();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f8125b = {-16842910};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f8126c = {R.attr.state_focused};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f8127d = {R.attr.state_activated};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f8128e = {R.attr.state_pressed};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f8129f = {R.attr.state_checked};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[] f8130g = {R.attr.state_selected};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int[] f8131h = {-16842919, -16842908};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int[] f8132i = new int[0];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int[] f8133j = new int[1];

    public static int a(Context context, int i2) {
        ColorStateList colorStateListD = d(context, i2);
        if (colorStateListD != null && colorStateListD.isStateful()) {
            return colorStateListD.getColorForState(f8125b, colorStateListD.getDefaultColor());
        }
        TypedValue typedValueE = e();
        context.getTheme().resolveAttribute(R.attr.disabledAlpha, typedValueE, true);
        return c(context, i2, typedValueE.getFloat());
    }

    public static int b(Context context, int i2) {
        int[] iArr = f8133j;
        iArr[0] = i2;
        f0 f0VarR = f0.r(context, null, iArr);
        try {
            return f0VarR.b(0, 0);
        } finally {
            f0VarR.t();
        }
    }

    public static int c(Context context, int i2, float f2) {
        return AbstractC0239a.d(b(context, i2), Math.round(Color.alpha(r0) * f2));
    }

    public static ColorStateList d(Context context, int i2) {
        int[] iArr = f8133j;
        iArr[0] = i2;
        f0 f0VarR = f0.r(context, null, iArr);
        try {
            return f0VarR.c(0);
        } finally {
            f0VarR.t();
        }
    }

    public static TypedValue e() {
        ThreadLocal threadLocal = f8124a;
        TypedValue typedValue = (TypedValue) threadLocal.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        threadLocal.set(typedValue2);
        return typedValue2;
    }
}
