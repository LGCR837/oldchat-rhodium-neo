package w;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import h.InterfaceMenuItemC0249b;
import j.C0252a;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: w.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0477b extends AbstractC0478c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f7999b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Map f8000c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Map f8001d;

    public AbstractC0477b(Context context, Object obj) {
        super(obj);
        this.f7999b = context;
    }

    public final MenuItem f(MenuItem menuItem) {
        if (!(menuItem instanceof InterfaceMenuItemC0249b)) {
            return menuItem;
        }
        InterfaceMenuItemC0249b interfaceMenuItemC0249b = (InterfaceMenuItemC0249b) menuItem;
        if (this.f8000c == null) {
            this.f8000c = new C0252a();
        }
        MenuItem menuItem2 = (MenuItem) this.f8000c.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        MenuItem menuItemB = x.b(this.f7999b, interfaceMenuItemC0249b);
        this.f8000c.put(interfaceMenuItemC0249b, menuItemB);
        return menuItemB;
    }

    public final void h() {
        Map map = this.f8000c;
        if (map != null) {
            map.clear();
        }
        Map map2 = this.f8001d;
        if (map2 != null) {
            map2.clear();
        }
    }

    public final void i(int i2) {
        Map map = this.f8000c;
        if (map == null) {
            return;
        }
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            if (i2 == ((MenuItem) it.next()).getGroupId()) {
                it.remove();
            }
        }
    }

    public final void j(int i2) {
        Map map = this.f8000c;
        if (map == null) {
            return;
        }
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            if (i2 == ((MenuItem) it.next()).getItemId()) {
                it.remove();
                return;
            }
        }
    }

    public final SubMenu g(SubMenu subMenu) {
        return subMenu;
    }
}
