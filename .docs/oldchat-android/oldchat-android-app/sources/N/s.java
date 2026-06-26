package n;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.EdgeEffect;

/* JADX INFO: loaded from: classes.dex */
public abstract class s {
    public static boolean a(Object obj, Canvas canvas) {
        return AbstractC0428k.a(obj).draw(canvas);
    }

    public static void b(Object obj) {
        AbstractC0428k.a(obj).finish();
    }

    public static boolean c(Object obj) {
        return AbstractC0428k.a(obj).isFinished();
    }

    public static Object d(Context context) {
        return new EdgeEffect(context);
    }

    public static boolean e(Object obj, int i2) {
        AbstractC0428k.a(obj).onAbsorb(i2);
        return true;
    }

    public static boolean f(Object obj, float f2) {
        AbstractC0428k.a(obj).onPull(f2);
        return true;
    }

    public static boolean g(Object obj) {
        EdgeEffect edgeEffectA = AbstractC0428k.a(obj);
        edgeEffectA.onRelease();
        return edgeEffectA.isFinished();
    }

    public static void h(Object obj, int i2, int i3) {
        AbstractC0428k.a(obj).setSize(i2, i3);
    }
}
