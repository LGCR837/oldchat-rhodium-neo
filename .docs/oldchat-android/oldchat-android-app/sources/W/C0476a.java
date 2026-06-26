package w;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import d.AbstractC0235a;
import h.InterfaceMenuItemC0249b;
import k.AbstractC0299p;
import k.M;

/* JADX INFO: renamed from: w.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0476a implements InterfaceMenuItemC0249b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7985a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f7986b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f7987c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f7988d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f7989e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f7990f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Intent f7991g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public char f7992h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public char f7993i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Drawable f7994j;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Context f7996l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public MenuItem.OnMenuItemClickListener f7997m;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f7995k = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f7998n = 16;

    public C0476a(Context context, int i2, int i3, int i4, int i5, CharSequence charSequence) {
        this.f7996l = context;
        this.f7985a = i3;
        this.f7986b = i2;
        this.f7987c = i4;
        this.f7988d = i5;
        this.f7989e = charSequence;
    }

    @Override // h.InterfaceMenuItemC0249b
    public InterfaceMenuItemC0249b a(AbstractC0299p abstractC0299p) {
        throw new UnsupportedOperationException();
    }

    @Override // h.InterfaceMenuItemC0249b, android.view.MenuItem
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public InterfaceMenuItemC0249b setActionView(int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // h.InterfaceMenuItemC0249b, android.view.MenuItem
    public boolean collapseActionView() {
        return false;
    }

    @Override // h.InterfaceMenuItemC0249b, android.view.MenuItem
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public InterfaceMenuItemC0249b setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // h.InterfaceMenuItemC0249b
    public AbstractC0299p e() {
        return null;
    }

    @Override // h.InterfaceMenuItemC0249b, android.view.MenuItem
    public boolean expandActionView() {
        return false;
    }

    @Override // h.InterfaceMenuItemC0249b, android.view.MenuItem
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public InterfaceMenuItemC0249b setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // h.InterfaceMenuItemC0249b, android.view.MenuItem
    public View getActionView() {
        return null;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f7993i;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f7986b;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.f7994j;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f7991g;
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f7985a;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f7992h;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f7988d;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.f7989e;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f7990f;
        return charSequence != null ? charSequence : this.f7989e;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return false;
    }

    @Override // h.InterfaceMenuItemC0249b, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.f7998n & 1) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.f7998n & 2) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.f7998n & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return (this.f7998n & 8) == 0;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2) {
        this.f7993i = c2;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z2) {
        this.f7998n = (z2 ? 1 : 0) | (this.f7998n & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z2) {
        this.f7998n = (z2 ? 2 : 0) | (this.f7998n & (-3));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z2) {
        this.f7998n = (z2 ? 16 : 0) | (this.f7998n & (-17));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f7994j = drawable;
        this.f7995k = 0;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f7991g = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2) {
        this.f7992h = c2;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f7997m = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3) {
        this.f7992h = c2;
        this.f7993i = c3;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f7989e = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f7990f = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z2) {
        this.f7998n = (this.f7998n & 8) | (z2 ? 0 : 8);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i2) {
        this.f7989e = this.f7996l.getResources().getString(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i2) {
        this.f7995k = i2;
        this.f7994j = AbstractC0235a.d(this.f7996l, i2);
        return this;
    }

    @Override // h.InterfaceMenuItemC0249b
    public InterfaceMenuItemC0249b b(M.e eVar) {
        return this;
    }

    @Override // h.InterfaceMenuItemC0249b, android.view.MenuItem
    public void setShowAsAction(int i2) {
    }
}
