package n;

import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Method;
import k.AbstractC0285k0;

/* JADX INFO: renamed from: n.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0414B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f6646a;

    /* JADX INFO: renamed from: n.B$a */
    public static class a extends d {
        @Override // n.AbstractC0414B.c, n.AbstractC0414B.e
        public void a(PopupWindow popupWindow, boolean z2) {
            AbstractC0415C.a(popupWindow, z2);
        }
    }

    /* JADX INFO: renamed from: n.B$b */
    public static class b extends a {
        @Override // n.AbstractC0414B.a, n.AbstractC0414B.c, n.AbstractC0414B.e
        public void a(PopupWindow popupWindow, boolean z2) {
            F.a(popupWindow, z2);
        }

        @Override // n.AbstractC0414B.c, n.AbstractC0414B.e
        public void b(PopupWindow popupWindow, int i2) {
            F.b(popupWindow, i2);
        }
    }

    /* JADX INFO: renamed from: n.B$d */
    public static class d extends c {
        @Override // n.AbstractC0414B.c, n.AbstractC0414B.e
        public void c(PopupWindow popupWindow, View view, int i2, int i3, int i4) {
            H.a(popupWindow, view, i2, i3, i4);
        }
    }

    /* JADX INFO: renamed from: n.B$e */
    public interface e {
        void a(PopupWindow popupWindow, boolean z2);

        void b(PopupWindow popupWindow, int i2);

        void c(PopupWindow popupWindow, View view, int i2, int i3, int i4);
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 23) {
            f6646a = new b();
            return;
        }
        if (i2 >= 21) {
            f6646a = new a();
        } else if (i2 >= 19) {
            f6646a = new d();
        } else {
            f6646a = new c();
        }
    }

    public static void a(PopupWindow popupWindow, boolean z2) {
        f6646a.a(popupWindow, z2);
    }

    public static void b(PopupWindow popupWindow, int i2) {
        f6646a.b(popupWindow, i2);
    }

    public static void c(PopupWindow popupWindow, View view, int i2, int i3, int i4) {
        f6646a.c(popupWindow, view, i2, i3, i4);
    }

    /* JADX INFO: renamed from: n.B$c */
    public static class c implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static Method f6647a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static boolean f6648b;

        @Override // n.AbstractC0414B.e
        public void b(PopupWindow popupWindow, int i2) {
            if (!f6648b) {
                try {
                    Method declaredMethod = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE);
                    f6647a = declaredMethod;
                    declaredMethod.setAccessible(true);
                } catch (Exception unused) {
                }
                f6648b = true;
            }
            Method method = f6647a;
            if (method != null) {
                try {
                    method.invoke(popupWindow, Integer.valueOf(i2));
                } catch (Exception unused2) {
                }
            }
        }

        @Override // n.AbstractC0414B.e
        public void c(PopupWindow popupWindow, View view, int i2, int i3, int i4) {
            if ((k.r.a(i4, AbstractC0285k0.k(view)) & 7) == 5) {
                i2 -= popupWindow.getWidth() - view.getWidth();
            }
            popupWindow.showAsDropDown(view, i2, i3);
        }

        @Override // n.AbstractC0414B.e
        public void a(PopupWindow popupWindow, boolean z2) {
        }
    }
}
