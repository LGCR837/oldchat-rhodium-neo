package w;

import android.content.Context;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;
import h.InterfaceMenuC0248a;
import h.InterfaceMenuItemC0249b;

/* JADX INFO: loaded from: classes.dex */
public abstract class x {
    public static Menu a(Context context, InterfaceMenuC0248a interfaceMenuC0248a) {
        if (Build.VERSION.SDK_INT >= 14) {
            return new y(context, interfaceMenuC0248a);
        }
        throw new UnsupportedOperationException();
    }

    public static MenuItem b(Context context, InterfaceMenuItemC0249b interfaceMenuItemC0249b) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 16) {
            return new t(context, interfaceMenuItemC0249b);
        }
        if (i2 >= 14) {
            return new MenuItemC0484i(context, interfaceMenuItemC0249b);
        }
        throw new UnsupportedOperationException();
    }
}
