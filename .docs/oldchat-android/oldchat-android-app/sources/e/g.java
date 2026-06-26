package e;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {
    public static Drawable a(Resources resources, int i2, Resources.Theme theme) {
        return Build.VERSION.SDK_INT >= 21 ? i.a(resources, i2, theme) : resources.getDrawable(i2);
    }
}
