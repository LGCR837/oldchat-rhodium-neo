package w;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ActionProvider;
import android.view.CollapsibleActionView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import h.InterfaceMenuItemC0249b;
import java.lang.reflect.Method;
import k.AbstractC0299p;
import k.M;
import v.InterfaceC0474c;

/* JADX INFO: renamed from: w.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class MenuItemC0484i extends AbstractC0477b implements MenuItem {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Method f8003e;

    /* JADX INFO: renamed from: w.i$a */
    public class a extends AbstractC0299p {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final ActionProvider f8004d;

        public a(Context context, ActionProvider actionProvider) {
            super(context);
            this.f8004d = actionProvider;
        }

        @Override // k.AbstractC0299p
        public boolean a() {
            return this.f8004d.hasSubMenu();
        }

        @Override // k.AbstractC0299p
        public View c() {
            return this.f8004d.onCreateActionView();
        }

        @Override // k.AbstractC0299p
        public boolean e() {
            return this.f8004d.onPerformDefaultAction();
        }

        @Override // k.AbstractC0299p
        public void f(SubMenu subMenu) {
            this.f8004d.onPrepareSubMenu(MenuItemC0484i.this.g(subMenu));
        }
    }

    /* JADX INFO: renamed from: w.i$b */
    public static class b extends FrameLayout implements InterfaceC0474c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final CollapsibleActionView f8006a;

        public b(View view) {
            super(view.getContext());
            this.f8006a = AbstractC0486k.a(view);
            addView(view);
        }

        public View a() {
            return (View) this.f8006a;
        }

        @Override // v.InterfaceC0474c
        public void b() {
            this.f8006a.onActionViewExpanded();
        }

        @Override // v.InterfaceC0474c
        public void d() {
            this.f8006a.onActionViewCollapsed();
        }
    }

    /* JADX INFO: renamed from: w.i$c */
    public class c extends AbstractC0478c implements M.e {
        public c(MenuItem.OnActionExpandListener onActionExpandListener) {
            super(onActionExpandListener);
        }

        @Override // k.M.e
        public boolean c(MenuItem menuItem) {
            return AbstractC0488m.a(this.f8002a).onMenuItemActionCollapse(MenuItemC0484i.this.f(menuItem));
        }

        @Override // k.M.e
        public boolean d(MenuItem menuItem) {
            return AbstractC0488m.a(this.f8002a).onMenuItemActionExpand(MenuItemC0484i.this.f(menuItem));
        }
    }

    /* JADX INFO: renamed from: w.i$d */
    public class d extends AbstractC0478c implements MenuItem.OnMenuItemClickListener {
        public d(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
            super(onMenuItemClickListener);
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            return ((MenuItem.OnMenuItemClickListener) this.f8002a).onMenuItemClick(MenuItemC0484i.this.f(menuItem));
        }
    }

    public MenuItemC0484i(Context context, InterfaceMenuItemC0249b interfaceMenuItemC0249b) {
        super(context, interfaceMenuItemC0249b);
    }

    @Override // android.view.MenuItem
    public boolean collapseActionView() {
        return ((InterfaceMenuItemC0249b) this.f8002a).collapseActionView();
    }

    @Override // android.view.MenuItem
    public boolean expandActionView() {
        return ((InterfaceMenuItemC0249b) this.f8002a).expandActionView();
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        AbstractC0299p abstractC0299pE = ((InterfaceMenuItemC0249b) this.f8002a).e();
        if (abstractC0299pE instanceof a) {
            return ((a) abstractC0299pE).f8004d;
        }
        return null;
    }

    @Override // android.view.MenuItem
    public View getActionView() {
        View actionView = ((InterfaceMenuItemC0249b) this.f8002a).getActionView();
        return actionView instanceof b ? ((b) actionView).a() : actionView;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return ((InterfaceMenuItemC0249b) this.f8002a).getAlphabeticShortcut();
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return ((InterfaceMenuItemC0249b) this.f8002a).getGroupId();
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return ((InterfaceMenuItemC0249b) this.f8002a).getIcon();
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return ((InterfaceMenuItemC0249b) this.f8002a).getIntent();
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return ((InterfaceMenuItemC0249b) this.f8002a).getItemId();
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return ((InterfaceMenuItemC0249b) this.f8002a).getMenuInfo();
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return ((InterfaceMenuItemC0249b) this.f8002a).getNumericShortcut();
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return ((InterfaceMenuItemC0249b) this.f8002a).getOrder();
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return g(((InterfaceMenuItemC0249b) this.f8002a).getSubMenu());
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return ((InterfaceMenuItemC0249b) this.f8002a).getTitle();
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        return ((InterfaceMenuItemC0249b) this.f8002a).getTitleCondensed();
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return ((InterfaceMenuItemC0249b) this.f8002a).hasSubMenu();
    }

    @Override // android.view.MenuItem
    public boolean isActionViewExpanded() {
        return ((InterfaceMenuItemC0249b) this.f8002a).isActionViewExpanded();
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return ((InterfaceMenuItemC0249b) this.f8002a).isCheckable();
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return ((InterfaceMenuItemC0249b) this.f8002a).isChecked();
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return ((InterfaceMenuItemC0249b) this.f8002a).isEnabled();
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return ((InterfaceMenuItemC0249b) this.f8002a).isVisible();
    }

    public a k(ActionProvider actionProvider) {
        return new a(this.f7999b, actionProvider);
    }

    public void l(boolean z2) {
        try {
            if (this.f8003e == null) {
                this.f8003e = ((InterfaceMenuItemC0249b) this.f8002a).getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
            }
            this.f8003e.invoke(this.f8002a, Boolean.valueOf(z2));
        } catch (Exception e2) {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e2);
        }
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        ((InterfaceMenuItemC0249b) this.f8002a).a(actionProvider != null ? k(actionProvider) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(View view) {
        if (AbstractC0479d.a(view)) {
            view = new b(view);
        }
        ((InterfaceMenuItemC0249b) this.f8002a).setActionView(view);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2) {
        ((InterfaceMenuItemC0249b) this.f8002a).setAlphabeticShortcut(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z2) {
        ((InterfaceMenuItemC0249b) this.f8002a).setCheckable(z2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z2) {
        ((InterfaceMenuItemC0249b) this.f8002a).setChecked(z2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z2) {
        ((InterfaceMenuItemC0249b) this.f8002a).setEnabled(z2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        ((InterfaceMenuItemC0249b) this.f8002a).setIcon(drawable);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        ((InterfaceMenuItemC0249b) this.f8002a).setIntent(intent);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2) {
        ((InterfaceMenuItemC0249b) this.f8002a).setNumericShortcut(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        ((InterfaceMenuItemC0249b) this.f8002a).b(onActionExpandListener != null ? new c(onActionExpandListener) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        ((InterfaceMenuItemC0249b) this.f8002a).setOnMenuItemClickListener(onMenuItemClickListener != null ? new d(onMenuItemClickListener) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3) {
        ((InterfaceMenuItemC0249b) this.f8002a).setShortcut(c2, c3);
        return this;
    }

    @Override // android.view.MenuItem
    public void setShowAsAction(int i2) {
        ((InterfaceMenuItemC0249b) this.f8002a).setShowAsAction(i2);
    }

    @Override // android.view.MenuItem
    public MenuItem setShowAsActionFlags(int i2) {
        ((InterfaceMenuItemC0249b) this.f8002a).setShowAsActionFlags(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        ((InterfaceMenuItemC0249b) this.f8002a).setTitle(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        ((InterfaceMenuItemC0249b) this.f8002a).setTitleCondensed(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z2) {
        return ((InterfaceMenuItemC0249b) this.f8002a).setVisible(z2);
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i2) {
        ((InterfaceMenuItemC0249b) this.f8002a).setIcon(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i2) {
        ((InterfaceMenuItemC0249b) this.f8002a).setTitle(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(int i2) {
        ((InterfaceMenuItemC0249b) this.f8002a).setActionView(i2);
        View actionView = ((InterfaceMenuItemC0249b) this.f8002a).getActionView();
        if (AbstractC0479d.a(actionView)) {
            ((InterfaceMenuItemC0249b) this.f8002a).setActionView(new b(actionView));
        }
        return this;
    }
}
