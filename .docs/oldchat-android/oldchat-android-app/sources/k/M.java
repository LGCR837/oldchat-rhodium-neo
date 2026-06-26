package k;

import android.os.Build;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import h.InterfaceMenuItemC0249b;

/* JADX INFO: loaded from: classes.dex */
public abstract class M {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f5671a;

    public static class b implements d {
        @Override // k.M.d
        public View a(MenuItem menuItem) {
            return S.a(menuItem);
        }

        @Override // k.M.d
        public void b(MenuItem menuItem, int i2) {
            S.d(menuItem, i2);
        }

        @Override // k.M.d
        public MenuItem c(MenuItem menuItem, View view) {
            return S.c(menuItem, view);
        }

        @Override // k.M.d
        public MenuItem d(MenuItem menuItem, int i2) {
            return S.b(menuItem, i2);
        }

        @Override // k.M.d
        public boolean e(MenuItem menuItem) {
            return false;
        }

        @Override // k.M.d
        public boolean f(MenuItem menuItem) {
            return false;
        }
    }

    public static class c extends b {
        @Override // k.M.b, k.M.d
        public boolean e(MenuItem menuItem) {
            return V.b(menuItem);
        }

        @Override // k.M.b, k.M.d
        public boolean f(MenuItem menuItem) {
            return V.a(menuItem);
        }
    }

    public interface d {
        View a(MenuItem menuItem);

        void b(MenuItem menuItem, int i2);

        MenuItem c(MenuItem menuItem, View view);

        MenuItem d(MenuItem menuItem, int i2);

        boolean e(MenuItem menuItem);

        boolean f(MenuItem menuItem);
    }

    public interface e {
        boolean c(MenuItem menuItem);

        boolean d(MenuItem menuItem);
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 14) {
            f5671a = new c();
        } else if (i2 >= 11) {
            f5671a = new b();
        } else {
            f5671a = new a();
        }
    }

    public static boolean a(MenuItem menuItem) {
        return menuItem instanceof InterfaceMenuItemC0249b ? ((InterfaceMenuItemC0249b) menuItem).expandActionView() : f5671a.f(menuItem);
    }

    public static View b(MenuItem menuItem) {
        return menuItem instanceof InterfaceMenuItemC0249b ? ((InterfaceMenuItemC0249b) menuItem).getActionView() : f5671a.a(menuItem);
    }

    public static boolean c(MenuItem menuItem) {
        return menuItem instanceof InterfaceMenuItemC0249b ? ((InterfaceMenuItemC0249b) menuItem).isActionViewExpanded() : f5671a.e(menuItem);
    }

    public static MenuItem d(MenuItem menuItem, AbstractC0299p abstractC0299p) {
        if (menuItem instanceof InterfaceMenuItemC0249b) {
            return ((InterfaceMenuItemC0249b) menuItem).a(abstractC0299p);
        }
        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem;
    }

    public static MenuItem e(MenuItem menuItem, int i2) {
        return menuItem instanceof InterfaceMenuItemC0249b ? ((InterfaceMenuItemC0249b) menuItem).setActionView(i2) : f5671a.d(menuItem, i2);
    }

    public static MenuItem f(MenuItem menuItem, View view) {
        return menuItem instanceof InterfaceMenuItemC0249b ? ((InterfaceMenuItemC0249b) menuItem).setActionView(view) : f5671a.c(menuItem, view);
    }

    public static void g(MenuItem menuItem, int i2) {
        if (menuItem instanceof InterfaceMenuItemC0249b) {
            ((InterfaceMenuItemC0249b) menuItem).setShowAsAction(i2);
        } else {
            f5671a.b(menuItem, i2);
        }
    }

    public static class a implements d {
        @Override // k.M.d
        public View a(MenuItem menuItem) {
            return null;
        }

        @Override // k.M.d
        public boolean e(MenuItem menuItem) {
            return false;
        }

        @Override // k.M.d
        public boolean f(MenuItem menuItem) {
            return false;
        }

        @Override // k.M.d
        public void b(MenuItem menuItem, int i2) {
        }

        @Override // k.M.d
        public MenuItem c(MenuItem menuItem, View view) {
            return menuItem;
        }

        @Override // k.M.d
        public MenuItem d(MenuItem menuItem, int i2) {
            return menuItem;
        }
    }
}
