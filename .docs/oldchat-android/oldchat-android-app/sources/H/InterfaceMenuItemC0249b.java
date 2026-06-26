package h;

import android.view.MenuItem;
import android.view.View;
import k.AbstractC0299p;
import k.M;

/* JADX INFO: renamed from: h.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceMenuItemC0249b extends MenuItem {
    InterfaceMenuItemC0249b a(AbstractC0299p abstractC0299p);

    InterfaceMenuItemC0249b b(M.e eVar);

    @Override // android.view.MenuItem
    boolean collapseActionView();

    AbstractC0299p e();

    @Override // android.view.MenuItem
    boolean expandActionView();

    @Override // android.view.MenuItem
    View getActionView();

    @Override // android.view.MenuItem
    boolean isActionViewExpanded();

    @Override // android.view.MenuItem
    MenuItem setActionView(int i2);

    @Override // android.view.MenuItem
    MenuItem setActionView(View view);

    @Override // android.view.MenuItem
    void setShowAsAction(int i2);

    @Override // android.view.MenuItem
    MenuItem setShowAsActionFlags(int i2);
}
