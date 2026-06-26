package k;

/* JADX INFO: loaded from: classes.dex */
public abstract class c2 {
    public static int a(Object obj) {
        return AbstractC0268e1.a(obj).getSystemWindowInsetBottom();
    }

    public static int b(Object obj) {
        return AbstractC0268e1.a(obj).getSystemWindowInsetLeft();
    }

    public static int c(Object obj) {
        return AbstractC0268e1.a(obj).getSystemWindowInsetRight();
    }

    public static int d(Object obj) {
        return AbstractC0268e1.a(obj).getSystemWindowInsetTop();
    }

    public static Object e(Object obj, int i2, int i3, int i4, int i5) {
        return AbstractC0268e1.a(obj).replaceSystemWindowInsets(i2, i3, i4, i5);
    }
}
