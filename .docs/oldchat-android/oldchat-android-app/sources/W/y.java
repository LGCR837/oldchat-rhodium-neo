package w;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import h.InterfaceMenuC0248a;

/* JADX INFO: loaded from: classes.dex */
public class y extends AbstractC0477b implements Menu {
    public y(Context context, InterfaceMenuC0248a interfaceMenuC0248a) {
        super(context, interfaceMenuC0248a);
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return f(((InterfaceMenuC0248a) this.f8002a).add(charSequence));
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i2, int i3, int i4, ComponentName componentName, Intent[] intentArr, Intent intent, int i5, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2 = menuItemArr != null ? new MenuItem[menuItemArr.length] : null;
        int iAddIntentOptions = ((InterfaceMenuC0248a) this.f8002a).addIntentOptions(i2, i3, i4, componentName, intentArr, intent, i5, menuItemArr2);
        if (menuItemArr2 != null) {
            int length = menuItemArr2.length;
            for (int i6 = 0; i6 < length; i6++) {
                menuItemArr[i6] = f(menuItemArr2[i6]);
            }
        }
        return iAddIntentOptions;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return g(((InterfaceMenuC0248a) this.f8002a).addSubMenu(charSequence));
    }

    @Override // android.view.Menu
    public void clear() {
        h();
        ((InterfaceMenuC0248a) this.f8002a).clear();
    }

    @Override // android.view.Menu
    public void close() {
        ((InterfaceMenuC0248a) this.f8002a).close();
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i2) {
        return f(((InterfaceMenuC0248a) this.f8002a).findItem(i2));
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i2) {
        return f(((InterfaceMenuC0248a) this.f8002a).getItem(i2));
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        return ((InterfaceMenuC0248a) this.f8002a).hasVisibleItems();
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i2, KeyEvent keyEvent) {
        return ((InterfaceMenuC0248a) this.f8002a).isShortcutKey(i2, keyEvent);
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i2, int i3) {
        return ((InterfaceMenuC0248a) this.f8002a).performIdentifierAction(i2, i3);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i2, KeyEvent keyEvent, int i3) {
        return ((InterfaceMenuC0248a) this.f8002a).performShortcut(i2, keyEvent, i3);
    }

    @Override // android.view.Menu
    public void removeGroup(int i2) {
        i(i2);
        ((InterfaceMenuC0248a) this.f8002a).removeGroup(i2);
    }

    @Override // android.view.Menu
    public void removeItem(int i2) {
        j(i2);
        ((InterfaceMenuC0248a) this.f8002a).removeItem(i2);
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i2, boolean z2, boolean z3) {
        ((InterfaceMenuC0248a) this.f8002a).setGroupCheckable(i2, z2, z3);
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i2, boolean z2) {
        ((InterfaceMenuC0248a) this.f8002a).setGroupEnabled(i2, z2);
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i2, boolean z2) {
        ((InterfaceMenuC0248a) this.f8002a).setGroupVisible(i2, z2);
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z2) {
        ((InterfaceMenuC0248a) this.f8002a).setQwertyMode(z2);
    }

    @Override // android.view.Menu
    public int size() {
        return ((InterfaceMenuC0248a) this.f8002a).size();
    }

    @Override // android.view.Menu
    public MenuItem add(int i2) {
        return f(((InterfaceMenuC0248a) this.f8002a).add(i2));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2) {
        return g(((InterfaceMenuC0248a) this.f8002a).addSubMenu(i2));
    }

    @Override // android.view.Menu
    public MenuItem add(int i2, int i3, int i4, CharSequence charSequence) {
        return f(((InterfaceMenuC0248a) this.f8002a).add(i2, i3, i4, charSequence));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        return g(((InterfaceMenuC0248a) this.f8002a).addSubMenu(i2, i3, i4, charSequence));
    }

    @Override // android.view.Menu
    public MenuItem add(int i2, int i3, int i4, int i5) {
        return f(((InterfaceMenuC0248a) this.f8002a).add(i2, i3, i4, i5));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2, int i3, int i4, int i5) {
        return g(((InterfaceMenuC0248a) this.f8002a).addSubMenu(i2, i3, i4, i5));
    }
}
