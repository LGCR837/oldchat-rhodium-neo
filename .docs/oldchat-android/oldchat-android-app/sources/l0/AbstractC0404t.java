package l0;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.widget.TextView;
import d.AbstractC0235a;

/* JADX INFO: renamed from: l0.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0404t {
    public static void a(TextView textView, Context context) {
        if (textView == null || context == null) {
            return;
        }
        int iC = c(context, 8);
        int iC2 = c(context, 6);
        textView.setBackgroundDrawable(b(context));
        textView.setPadding(iC, iC2, iC, iC2);
        textView.setTextColor(AbstractC0235a.b(context, f0.d.f4973j));
    }

    public static GradientDrawable b(Context context) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(AbstractC0235a.b(context, f0.d.f4967d));
        float fE = e(context, f0.c.f4961a);
        if (fE > 0.0f) {
            gradientDrawable.setCornerRadius(fE);
        }
        gradientDrawable.setStroke(Math.max(1, c(context, 1)), AbstractC0235a.b(context, f0.d.f4969f));
        return gradientDrawable;
    }

    public static int c(Context context, int i2) {
        return (int) ((context.getResources().getDisplayMetrics().density * i2) + 0.5f);
    }

    public static void d(TextView textView) {
        if (textView == null) {
            return;
        }
        textView.setBackgroundResource(0);
        textView.setPadding(0, 0, 0, 0);
    }

    public static float e(Context context, int i2) {
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
