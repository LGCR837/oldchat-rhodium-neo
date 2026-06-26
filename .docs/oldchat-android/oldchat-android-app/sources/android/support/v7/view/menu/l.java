package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.e;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class l extends e implements SubMenu {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public g f1141A;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public e f1142z;

    public l(Context context, e eVar, g gVar) {
        super(context);
        this.f1142z = eVar;
        this.f1141A = gVar;
    }

    @Override // android.support.v7.view.menu.e
    public e D() {
        return this.f1142z.D();
    }

    @Override // android.support.v7.view.menu.e
    public boolean F() {
        return this.f1142z.F();
    }

    @Override // android.support.v7.view.menu.e
    public boolean G() {
        return this.f1142z.G();
    }

    @Override // android.support.v7.view.menu.e
    public void Q(e.a aVar) {
        this.f1142z.Q(aVar);
    }

    public Menu d0() {
        return this.f1142z;
    }

    @Override // android.support.v7.view.menu.e
    public boolean f(g gVar) {
        return this.f1142z.f(gVar);
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return this.f1141A;
    }

    @Override // android.support.v7.view.menu.e
    public boolean h(e eVar, MenuItem menuItem) {
        return super.h(eVar, menuItem) || this.f1142z.h(eVar, menuItem);
    }

    @Override // android.support.v7.view.menu.e
    public boolean k(g gVar) {
        return this.f1142z.k(gVar);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        return (SubMenu) super.U(drawable);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        return (SubMenu) super.X(charSequence);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View view) {
        return (SubMenu) super.Y(view);
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        this.f1141A.setIcon(drawable);
        return this;
    }

    @Override // android.support.v7.view.menu.e, android.view.Menu
    public void setQwertyMode(boolean z2) {
        this.f1142z.setQwertyMode(z2);
    }

    @Override // android.support.v7.view.menu.e
    public String t() {
        g gVar = this.f1141A;
        int itemId = gVar != null ? gVar.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return super.t() + ":" + itemId;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i2) {
        return (SubMenu) super.T(i2);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i2) {
        return (SubMenu) super.W(i2);
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i2) {
        this.f1141A.setIcon(i2);
        return this;
    }
}
