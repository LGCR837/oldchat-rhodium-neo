package android.support.v7.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.view.menu.j;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import h.InterfaceMenuItemC0249b;
import k.AbstractC0299p;
import k.M;

/* JADX INFO: loaded from: classes.dex */
public final class g implements InterfaceMenuItemC0249b {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static String f1081w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static String f1082x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static String f1083y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static String f1084z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1085a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1086b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1087c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1088d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f1089e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f1090f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Intent f1091g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public char f1092h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public char f1093i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Drawable f1094j;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public e f1096l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public l f1097m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Runnable f1098n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public MenuItem.OnMenuItemClickListener f1099o;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1101q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public View f1102r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public AbstractC0299p f1103s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public M.e f1104t;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ContextMenu.ContextMenuInfo f1106v;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1095k = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1100p = 16;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f1105u = false;

    public class a implements AbstractC0299p.b {
        public a() {
        }

        @Override // k.AbstractC0299p.b
        public void onActionProviderVisibilityChanged(boolean z2) {
            g gVar = g.this;
            gVar.f1096l.I(gVar);
        }
    }

    public g(e eVar, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6) {
        this.f1096l = eVar;
        this.f1085a = i3;
        this.f1086b = i2;
        this.f1087c = i4;
        this.f1088d = i5;
        this.f1089e = charSequence;
        this.f1101q = i6;
    }

    public boolean A() {
        return (this.f1101q & 4) == 4;
    }

    @Override // h.InterfaceMenuItemC0249b
    public InterfaceMenuItemC0249b a(AbstractC0299p abstractC0299p) {
        AbstractC0299p abstractC0299p2 = this.f1103s;
        if (abstractC0299p2 != null) {
            abstractC0299p2.h();
        }
        this.f1102r = null;
        this.f1103s = abstractC0299p;
        this.f1096l.J(true);
        AbstractC0299p abstractC0299p3 = this.f1103s;
        if (abstractC0299p3 != null) {
            abstractC0299p3.j(new a());
        }
        return this;
    }

    @Override // h.InterfaceMenuItemC0249b
    public InterfaceMenuItemC0249b b(M.e eVar) {
        this.f1104t = eVar;
        return this;
    }

    public void c() {
        this.f1096l.H(this);
    }

    @Override // h.InterfaceMenuItemC0249b, android.view.MenuItem
    public boolean collapseActionView() {
        if ((this.f1101q & 8) == 0) {
            return false;
        }
        if (this.f1102r == null) {
            return true;
        }
        M.e eVar = this.f1104t;
        if (eVar == null || eVar.c(this)) {
            return this.f1096l.f(this);
        }
        return false;
    }

    public int d() {
        return this.f1088d;
    }

    @Override // h.InterfaceMenuItemC0249b
    public AbstractC0299p e() {
        return this.f1103s;
    }

    @Override // h.InterfaceMenuItemC0249b, android.view.MenuItem
    public boolean expandActionView() {
        if (!i()) {
            return false;
        }
        M.e eVar = this.f1104t;
        if (eVar == null || eVar.d(this)) {
            return this.f1096l.k(this);
        }
        return false;
    }

    public char f() {
        return this.f1096l.F() ? this.f1093i : this.f1092h;
    }

    public String g() {
        char cF = f();
        if (cF == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(f1081w);
        if (cF == '\b') {
            sb.append(f1083y);
        } else if (cF == '\n') {
            sb.append(f1082x);
        } else if (cF != ' ') {
            sb.append(cF);
        } else {
            sb.append(f1084z);
        }
        return sb.toString();
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // h.InterfaceMenuItemC0249b, android.view.MenuItem
    public View getActionView() {
        View view = this.f1102r;
        if (view != null) {
            return view;
        }
        AbstractC0299p abstractC0299p = this.f1103s;
        if (abstractC0299p == null) {
            return null;
        }
        View viewD = abstractC0299p.d(this);
        this.f1102r = viewD;
        return viewD;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f1093i;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f1086b;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        Drawable drawable = this.f1094j;
        if (drawable != null) {
            return drawable;
        }
        if (this.f1095k == 0) {
            return null;
        }
        Drawable drawableD = q.b.d(this.f1096l.u(), this.f1095k);
        this.f1095k = 0;
        this.f1094j = drawableD;
        return drawableD;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f1091g;
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f1085a;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.f1106v;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f1092h;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f1087c;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return this.f1097m;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.f1089e;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f1090f;
        if (charSequence == null) {
            charSequence = this.f1089e;
        }
        return (Build.VERSION.SDK_INT >= 18 || charSequence == null || (charSequence instanceof String)) ? charSequence : charSequence.toString();
    }

    public CharSequence h(j.a aVar) {
        return (aVar == null || !aVar.d()) ? getTitle() : getTitleCondensed();
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return this.f1097m != null;
    }

    public boolean i() {
        AbstractC0299p abstractC0299p;
        if ((this.f1101q & 8) == 0) {
            return false;
        }
        if (this.f1102r == null && (abstractC0299p = this.f1103s) != null) {
            this.f1102r = abstractC0299p.d(this);
        }
        return this.f1102r != null;
    }

    @Override // h.InterfaceMenuItemC0249b, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.f1105u;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.f1100p & 1) == 1;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.f1100p & 2) == 2;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.f1100p & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        AbstractC0299p abstractC0299p = this.f1103s;
        return (abstractC0299p == null || !abstractC0299p.g()) ? (this.f1100p & 8) == 0 : (this.f1100p & 8) == 0 && this.f1103s.b();
    }

    public boolean j() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.f1099o;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(this)) {
            return true;
        }
        e eVar = this.f1096l;
        if (eVar.h(eVar.D(), this)) {
            return true;
        }
        Runnable runnable = this.f1098n;
        if (runnable != null) {
            runnable.run();
            return true;
        }
        if (this.f1091g != null) {
            try {
                this.f1096l.u().startActivity(this.f1091g);
                return true;
            } catch (ActivityNotFoundException e2) {
                Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e2);
            }
        }
        AbstractC0299p abstractC0299p = this.f1103s;
        return abstractC0299p != null && abstractC0299p.e();
    }

    public boolean k() {
        return (this.f1100p & 32) == 32;
    }

    public boolean l() {
        return (this.f1100p & 4) != 0;
    }

    public boolean m() {
        return (this.f1101q & 1) == 1;
    }

    public boolean n() {
        return (this.f1101q & 2) == 2;
    }

    @Override // h.InterfaceMenuItemC0249b, android.view.MenuItem
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public InterfaceMenuItemC0249b setActionView(int i2) {
        Context contextU = this.f1096l.u();
        setActionView(LayoutInflater.from(contextU).inflate(i2, (ViewGroup) new LinearLayout(contextU), false));
        return this;
    }

    @Override // h.InterfaceMenuItemC0249b, android.view.MenuItem
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public InterfaceMenuItemC0249b setActionView(View view) {
        int i2;
        this.f1102r = view;
        this.f1103s = null;
        if (view != null && view.getId() == -1 && (i2 = this.f1085a) > 0) {
            view.setId(i2);
        }
        this.f1096l.H(this);
        return this;
    }

    public void q(boolean z2) {
        this.f1105u = z2;
        this.f1096l.J(false);
    }

    public void r(boolean z2) {
        int i2 = this.f1100p;
        int i3 = (z2 ? 2 : 0) | (i2 & (-3));
        this.f1100p = i3;
        if (i2 != i3) {
            this.f1096l.J(false);
        }
    }

    public void s(boolean z2) {
        this.f1100p = (z2 ? 4 : 0) | (this.f1100p & (-5));
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2) {
        if (this.f1093i == c2) {
            return this;
        }
        this.f1093i = Character.toLowerCase(c2);
        this.f1096l.J(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z2) {
        int i2 = this.f1100p;
        int i3 = (z2 ? 1 : 0) | (i2 & (-2));
        this.f1100p = i3;
        if (i2 != i3) {
            this.f1096l.J(false);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z2) {
        if ((this.f1100p & 4) != 0) {
            this.f1096l.S(this);
        } else {
            r(z2);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z2) {
        if (z2) {
            this.f1100p |= 16;
        } else {
            this.f1100p &= -17;
        }
        this.f1096l.J(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f1095k = 0;
        this.f1094j = drawable;
        this.f1096l.J(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f1091g = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2) {
        if (this.f1092h == c2) {
            return this;
        }
        this.f1092h = c2;
        this.f1096l.J(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setOnActionExpandListener()");
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f1099o = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3) {
        this.f1092h = c2;
        this.f1093i = Character.toLowerCase(c3);
        this.f1096l.J(false);
        return this;
    }

    @Override // h.InterfaceMenuItemC0249b, android.view.MenuItem
    public void setShowAsAction(int i2) {
        int i3 = i2 & 3;
        if (i3 != 0 && i3 != 1 && i3 != 2) {
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
        this.f1101q = i2;
        this.f1096l.H(this);
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f1089e = charSequence;
        this.f1096l.J(false);
        l lVar = this.f1097m;
        if (lVar != null) {
            lVar.setHeaderTitle(charSequence);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f1090f = charSequence;
        this.f1096l.J(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z2) {
        if (x(z2)) {
            this.f1096l.I(this);
        }
        return this;
    }

    public void t(boolean z2) {
        if (z2) {
            this.f1100p |= 32;
        } else {
            this.f1100p &= -33;
        }
    }

    public String toString() {
        CharSequence charSequence = this.f1089e;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public void u(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.f1106v = contextMenuInfo;
    }

    @Override // h.InterfaceMenuItemC0249b, android.view.MenuItem
    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public InterfaceMenuItemC0249b setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    public void w(l lVar) {
        this.f1097m = lVar;
        lVar.setHeaderTitle(getTitle());
    }

    public boolean x(boolean z2) {
        int i2 = this.f1100p;
        int i3 = (z2 ? 0 : 8) | (i2 & (-9));
        this.f1100p = i3;
        return i2 != i3;
    }

    public boolean y() {
        return this.f1096l.A();
    }

    public boolean z() {
        return this.f1096l.G() && f() != 0;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i2) {
        this.f1094j = null;
        this.f1095k = i2;
        this.f1096l.J(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i2) {
        return setTitle(this.f1096l.u().getString(i2));
    }
}
