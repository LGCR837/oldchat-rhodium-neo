package k;

import android.view.MenuItem;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class S {
    public static View a(MenuItem menuItem) {
        return menuItem.getActionView();
    }

    public static MenuItem b(MenuItem menuItem, int i2) {
        return menuItem.setActionView(i2);
    }

    public static MenuItem c(MenuItem menuItem, View view) {
        return menuItem.setActionView(view);
    }

    public static void d(MenuItem menuItem, int i2) {
        menuItem.setShowAsAction(i2);
    }
}
