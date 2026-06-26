package o0;

import android.R;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.TypedValue;

/* JADX INFO: renamed from: o0.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0439e {
    public static float[] a(Context context, boolean z2) {
        float fD = d(context, f0.c.f4963c);
        float fD2 = d(context, f0.c.f4962b);
        return z2 ? new float[]{fD, fD, fD2, fD2, fD, fD, fD, fD} : new float[]{fD2, fD2, fD, fD, fD, fD, fD, fD};
    }

    public static StateListDrawable b(Context context, int i2, int i3, boolean z2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, c(context, i3, z2));
        stateListDrawable.addState(new int[0], c(context, i2, z2));
        return stateListDrawable;
    }

    public static GradientDrawable c(Context context, int i2, boolean z2) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadii(a(context, z2));
        gradientDrawable.setColor(i2);
        return gradientDrawable;
    }

    public static float d(Context context, int i2) {
        if (context != null && context.getTheme() != null) {
            TypedValue typedValue = new TypedValue();
            if (!context.getTheme().resolveAttribute(i2, typedValue, true)) {
                return 0.0f;
            }
            if (typedValue.resourceId != 0) {
                try {
                    return context.getResources().getDimension(typedValue.resourceId);
                } catch (Throwable unused) {
                    return 0.0f;
                }
            }
            if (typedValue.type == 5) {
                return TypedValue.complexToDimension(typedValue.data, context.getResources().getDisplayMetrics());
            }
        }
        return 0.0f;
    }
}
