package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import d.AbstractC0235a;
import h.InterfaceMenuC0248a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import k.AbstractC0299p;
import k.M;

/* JADX INFO: loaded from: classes.dex */
public class e implements InterfaceMenuC0248a {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final int[] f1052y = {1, 4, 5, 3, 2, 0};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1053a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Resources f1054b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1055c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1056d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f1057e;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ContextMenu.ContextMenuInfo f1065m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public CharSequence f1066n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Drawable f1067o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public View f1068p;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public g f1075w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f1076x;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1064l = 0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1069q = false;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1070r = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1071s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1072t = false;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ArrayList f1073u = new ArrayList();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public CopyOnWriteArrayList f1074v = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList f1058f = new ArrayList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList f1059g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1060h = true;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList f1061i = new ArrayList();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList f1062j = new ArrayList();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f1063k = true;

    public interface a {
        void a(e eVar);

        boolean b(e eVar, MenuItem menuItem);
    }

    public interface b {
        boolean a(g gVar);
    }

    public e(Context context) {
        this.f1053a = context;
        this.f1054b = context.getResources();
        a0(true);
    }

    public static int B(int i2) {
        int i3 = ((-65536) & i2) >> 16;
        if (i3 >= 0) {
            int[] iArr = f1052y;
            if (i3 < iArr.length) {
                return (i2 & 65535) | (iArr[i3] << 16);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    public static int n(ArrayList arrayList, int i2) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (((g) arrayList.get(size)).d() <= i2) {
                return size + 1;
            }
        }
        return 0;
    }

    public boolean A() {
        return this.f1071s;
    }

    public Resources C() {
        return this.f1054b;
    }

    public e D() {
        return this;
    }

    public ArrayList E() {
        if (!this.f1060h) {
            return this.f1059g;
        }
        this.f1059g.clear();
        int size = this.f1058f.size();
        for (int i2 = 0; i2 < size; i2++) {
            g gVar = (g) this.f1058f.get(i2);
            if (gVar.isVisible()) {
                this.f1059g.add(gVar);
            }
        }
        this.f1060h = false;
        this.f1063k = true;
        return this.f1059g;
    }

    public boolean F() {
        return this.f1055c;
    }

    public boolean G() {
        return this.f1056d;
    }

    public void H(g gVar) {
        this.f1063k = true;
        J(true);
    }

    public void I(g gVar) {
        this.f1060h = true;
        J(true);
    }

    public void J(boolean z2) {
        if (this.f1069q) {
            this.f1070r = true;
            return;
        }
        if (z2) {
            this.f1060h = true;
            this.f1063k = true;
        }
        i(z2);
    }

    public boolean K(MenuItem menuItem, int i2) {
        return L(menuItem, null, i2);
    }

    public boolean L(MenuItem menuItem, i iVar, int i2) {
        g gVar = (g) menuItem;
        if (gVar == null || !gVar.isEnabled()) {
            return false;
        }
        boolean zJ = gVar.j();
        AbstractC0299p abstractC0299pE = gVar.e();
        boolean z2 = abstractC0299pE != null && abstractC0299pE.a();
        if (gVar.i()) {
            zJ |= gVar.expandActionView();
            if (zJ) {
                e(true);
            }
        } else if (gVar.hasSubMenu() || z2) {
            if ((i2 & 4) == 0) {
                e(false);
            }
            if (!gVar.hasSubMenu()) {
                gVar.w(new l(u(), this, gVar));
            }
            l lVar = (l) gVar.getSubMenu();
            if (z2) {
                abstractC0299pE.f(lVar);
            }
            zJ |= j(lVar, iVar);
            if (!zJ) {
                e(true);
            }
        } else if ((i2 & 1) == 0) {
            e(true);
        }
        return zJ;
    }

    public final void M(int i2, boolean z2) {
        if (i2 < 0 || i2 >= this.f1058f.size()) {
            return;
        }
        this.f1058f.remove(i2);
        if (z2) {
            J(true);
        }
    }

    public void N(i iVar) {
        for (WeakReference weakReference : this.f1074v) {
            i iVar2 = (i) weakReference.get();
            if (iVar2 == null || iVar2 == iVar) {
                this.f1074v.remove(weakReference);
            }
        }
    }

    public void O(Bundle bundle) {
        MenuItem menuItemFindItem;
        if (bundle == null) {
            return;
        }
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(t());
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = getItem(i2);
            View viewB = M.b(item);
            if (viewB != null && viewB.getId() != -1) {
                viewB.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((l) item.getSubMenu()).O(bundle);
            }
        }
        int i3 = bundle.getInt("android:menu:expandedactionview");
        if (i3 <= 0 || (menuItemFindItem = findItem(i3)) == null) {
            return;
        }
        M.a(menuItemFindItem);
    }

    public void P(Bundle bundle) {
        int size = size();
        SparseArray<Parcelable> sparseArray = null;
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = getItem(i2);
            View viewB = M.b(item);
            if (viewB != null && viewB.getId() != -1) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                viewB.saveHierarchyState(sparseArray);
                if (M.c(item)) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((l) item.getSubMenu()).P(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(t(), sparseArray);
        }
    }

    public void Q(a aVar) {
        this.f1057e = aVar;
    }

    public e R(int i2) {
        this.f1064l = i2;
        return this;
    }

    public void S(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.f1058f.size();
        for (int i2 = 0; i2 < size; i2++) {
            g gVar = (g) this.f1058f.get(i2);
            if (gVar.getGroupId() == groupId && gVar.l() && gVar.isCheckable()) {
                gVar.r(gVar == menuItem);
            }
        }
    }

    public e T(int i2) {
        V(0, null, i2, null, null);
        return this;
    }

    public e U(Drawable drawable) {
        V(0, null, 0, drawable, null);
        return this;
    }

    public final void V(int i2, CharSequence charSequence, int i3, Drawable drawable, View view) {
        Resources resourcesC = C();
        if (view != null) {
            this.f1068p = view;
            this.f1066n = null;
            this.f1067o = null;
        } else {
            if (i2 > 0) {
                this.f1066n = resourcesC.getText(i2);
            } else if (charSequence != null) {
                this.f1066n = charSequence;
            }
            if (i3 > 0) {
                this.f1067o = AbstractC0235a.d(u(), i3);
            } else if (drawable != null) {
                this.f1067o = drawable;
            }
            this.f1068p = null;
        }
        J(false);
    }

    public e W(int i2) {
        V(i2, null, 0, null, null);
        return this;
    }

    public e X(CharSequence charSequence) {
        V(0, charSequence, 0, null, null);
        return this;
    }

    public e Y(View view) {
        V(0, null, 0, null, view);
        return this;
    }

    public void Z(boolean z2) {
        this.f1076x = z2;
    }

    public MenuItem a(int i2, int i3, int i4, CharSequence charSequence) {
        int iB = B(i4);
        g gVarG = g(i2, i3, i4, iB, charSequence, this.f1064l);
        ContextMenu.ContextMenuInfo contextMenuInfo = this.f1065m;
        if (contextMenuInfo != null) {
            gVarG.u(contextMenuInfo);
        }
        ArrayList arrayList = this.f1058f;
        arrayList.add(n(arrayList, iB), gVarG);
        J(true);
        return gVarG;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a0(boolean r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L18
            android.content.res.Resources r3 = r2.f1054b
            android.content.res.Configuration r3 = r3.getConfiguration()
            int r3 = r3.keyboard
            r0 = 1
            if (r3 == r0) goto L18
            android.content.res.Resources r3 = r2.f1054b
            int r1 = p.AbstractC0461b.f7734b
            boolean r3 = r3.getBoolean(r1)
            if (r3 == 0) goto L18
            goto L19
        L18:
            r0 = 0
        L19:
            r2.f1056d = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.menu.e.a0(boolean):void");
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i2, int i3, int i4, ComponentName componentName, Intent[] intentArr, Intent intent, int i5, MenuItem[] menuItemArr) {
        int i6;
        PackageManager packageManager = this.f1053a.getPackageManager();
        List<ResolveInfo> listQueryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = listQueryIntentActivityOptions != null ? listQueryIntentActivityOptions.size() : 0;
        if ((i5 & 1) == 0) {
            removeGroup(i2);
        }
        for (int i7 = 0; i7 < size; i7++) {
            ResolveInfo resolveInfo = listQueryIntentActivityOptions.get(i7);
            int i8 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i8 < 0 ? intent : intentArr[i8]);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            MenuItem intent3 = add(i2, i3, i4, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && (i6 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i6] = intent3;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public void b(i iVar) {
        c(iVar, this.f1053a);
    }

    public void b0() {
        this.f1069q = false;
        if (this.f1070r) {
            this.f1070r = false;
            J(true);
        }
    }

    public void c(i iVar, Context context) {
        this.f1074v.add(new WeakReference(iVar));
        iVar.g(context, this);
        this.f1063k = true;
    }

    public void c0() {
        if (this.f1069q) {
            return;
        }
        this.f1069q = true;
        this.f1070r = false;
    }

    @Override // android.view.Menu
    public void clear() {
        g gVar = this.f1075w;
        if (gVar != null) {
            f(gVar);
        }
        this.f1058f.clear();
        J(true);
    }

    public void clearHeader() {
        this.f1067o = null;
        this.f1066n = null;
        this.f1068p = null;
        J(false);
    }

    @Override // android.view.Menu
    public void close() {
        e(true);
    }

    public void d() {
        a aVar = this.f1057e;
        if (aVar != null) {
            aVar.a(this);
        }
    }

    public final void e(boolean z2) {
        if (this.f1072t) {
            return;
        }
        this.f1072t = true;
        for (WeakReference weakReference : this.f1074v) {
            i iVar = (i) weakReference.get();
            if (iVar == null) {
                this.f1074v.remove(weakReference);
            } else {
                iVar.a(this, z2);
            }
        }
        this.f1072t = false;
    }

    public boolean f(g gVar) {
        boolean zE = false;
        if (!this.f1074v.isEmpty() && this.f1075w == gVar) {
            c0();
            for (WeakReference weakReference : this.f1074v) {
                i iVar = (i) weakReference.get();
                if (iVar != null) {
                    zE = iVar.e(this, gVar);
                    if (zE) {
                        break;
                    }
                } else {
                    this.f1074v.remove(weakReference);
                }
            }
            b0();
            if (zE) {
                this.f1075w = null;
            }
        }
        return zE;
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i2) {
        MenuItem menuItemFindItem;
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            g gVar = (g) this.f1058f.get(i3);
            if (gVar.getItemId() == i2) {
                return gVar;
            }
            if (gVar.hasSubMenu() && (menuItemFindItem = gVar.getSubMenu().findItem(i2)) != null) {
                return menuItemFindItem;
            }
        }
        return null;
    }

    public final g g(int i2, int i3, int i4, int i5, CharSequence charSequence, int i6) {
        return new g(this, i2, i3, i4, i5, charSequence, i6);
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i2) {
        return (MenuItem) this.f1058f.get(i2);
    }

    public boolean h(e eVar, MenuItem menuItem) {
        a aVar = this.f1057e;
        return aVar != null && aVar.b(eVar, menuItem);
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        if (this.f1076x) {
            return true;
        }
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((g) this.f1058f.get(i2)).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public final void i(boolean z2) {
        if (this.f1074v.isEmpty()) {
            return;
        }
        c0();
        for (WeakReference weakReference : this.f1074v) {
            i iVar = (i) weakReference.get();
            if (iVar == null) {
                this.f1074v.remove(weakReference);
            } else {
                iVar.k(z2);
            }
        }
        b0();
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i2, KeyEvent keyEvent) {
        return p(i2, keyEvent) != null;
    }

    public final boolean j(l lVar, i iVar) {
        if (this.f1074v.isEmpty()) {
            return false;
        }
        boolean zB = iVar != null ? iVar.b(lVar) : false;
        for (WeakReference weakReference : this.f1074v) {
            i iVar2 = (i) weakReference.get();
            if (iVar2 == null) {
                this.f1074v.remove(weakReference);
            } else if (!zB) {
                zB = iVar2.b(lVar);
            }
        }
        return zB;
    }

    public boolean k(g gVar) {
        boolean zJ = false;
        if (this.f1074v.isEmpty()) {
            return false;
        }
        c0();
        for (WeakReference weakReference : this.f1074v) {
            i iVar = (i) weakReference.get();
            if (iVar != null) {
                zJ = iVar.j(this, gVar);
                if (zJ) {
                    break;
                }
            } else {
                this.f1074v.remove(weakReference);
            }
        }
        b0();
        if (zJ) {
            this.f1075w = gVar;
        }
        return zJ;
    }

    public int l(int i2) {
        return m(i2, 0);
    }

    public int m(int i2, int i3) {
        int size = size();
        if (i3 < 0) {
            i3 = 0;
        }
        while (i3 < size) {
            if (((g) this.f1058f.get(i3)).getGroupId() == i2) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    public int o(int i2) {
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            if (((g) this.f1058f.get(i3)).getItemId() == i2) {
                return i3;
            }
        }
        return -1;
    }

    public g p(int i2, KeyEvent keyEvent) {
        ArrayList arrayList = this.f1073u;
        arrayList.clear();
        q(arrayList, i2, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return (g) arrayList.get(0);
        }
        boolean zF = F();
        for (int i3 = 0; i3 < size; i3++) {
            g gVar = (g) arrayList.get(i3);
            char alphabeticShortcut = zF ? gVar.getAlphabeticShortcut() : gVar.getNumericShortcut();
            char[] cArr = keyData.meta;
            if ((alphabeticShortcut == cArr[0] && (metaState & 2) == 0) || ((alphabeticShortcut == cArr[2] && (metaState & 2) != 0) || (zF && alphabeticShortcut == '\b' && i2 == 67))) {
                return gVar;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i2, int i3) {
        return K(findItem(i2), i3);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i2, KeyEvent keyEvent, int i3) {
        g gVarP = p(i2, keyEvent);
        boolean zK = gVarP != null ? K(gVarP, i3) : false;
        if ((i3 & 2) != 0) {
            e(true);
        }
        return zK;
    }

    public void q(List list, int i2, KeyEvent keyEvent) {
        boolean zF = F();
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i2 == 67) {
            int size = this.f1058f.size();
            for (int i3 = 0; i3 < size; i3++) {
                g gVar = (g) this.f1058f.get(i3);
                if (gVar.hasSubMenu()) {
                    ((e) gVar.getSubMenu()).q(list, i2, keyEvent);
                }
                char alphabeticShortcut = zF ? gVar.getAlphabeticShortcut() : gVar.getNumericShortcut();
                if ((metaState & 5) == 0 && alphabeticShortcut != 0) {
                    char[] cArr = keyData.meta;
                    if ((alphabeticShortcut == cArr[0] || alphabeticShortcut == cArr[2] || (zF && alphabeticShortcut == '\b' && i2 == 67)) && gVar.isEnabled()) {
                        list.add(gVar);
                    }
                }
            }
        }
    }

    public void r() {
        ArrayList arrayListE = E();
        if (this.f1063k) {
            boolean zD = false;
            for (WeakReference weakReference : this.f1074v) {
                i iVar = (i) weakReference.get();
                if (iVar == null) {
                    this.f1074v.remove(weakReference);
                } else {
                    zD |= iVar.d();
                }
            }
            if (zD) {
                this.f1061i.clear();
                this.f1062j.clear();
                int size = arrayListE.size();
                for (int i2 = 0; i2 < size; i2++) {
                    g gVar = (g) arrayListE.get(i2);
                    if (gVar.k()) {
                        this.f1061i.add(gVar);
                    } else {
                        this.f1062j.add(gVar);
                    }
                }
            } else {
                this.f1061i.clear();
                this.f1062j.clear();
                this.f1062j.addAll(E());
            }
            this.f1063k = false;
        }
    }

    @Override // android.view.Menu
    public void removeGroup(int i2) {
        int iL = l(i2);
        if (iL >= 0) {
            int size = this.f1058f.size() - iL;
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                if (i3 >= size || ((g) this.f1058f.get(iL)).getGroupId() != i2) {
                    break;
                }
                M(iL, false);
                i3 = i4;
            }
            J(true);
        }
    }

    @Override // android.view.Menu
    public void removeItem(int i2) {
        M(o(i2), true);
    }

    public ArrayList s() {
        r();
        return this.f1061i;
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i2, boolean z2, boolean z3) {
        int size = this.f1058f.size();
        for (int i3 = 0; i3 < size; i3++) {
            g gVar = (g) this.f1058f.get(i3);
            if (gVar.getGroupId() == i2) {
                gVar.s(z3);
                gVar.setCheckable(z2);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i2, boolean z2) {
        int size = this.f1058f.size();
        for (int i3 = 0; i3 < size; i3++) {
            g gVar = (g) this.f1058f.get(i3);
            if (gVar.getGroupId() == i2) {
                gVar.setEnabled(z2);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i2, boolean z2) {
        int size = this.f1058f.size();
        boolean z3 = false;
        for (int i3 = 0; i3 < size; i3++) {
            g gVar = (g) this.f1058f.get(i3);
            if (gVar.getGroupId() == i2 && gVar.x(z2)) {
                z3 = true;
            }
        }
        if (z3) {
            J(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z2) {
        this.f1055c = z2;
        J(false);
    }

    @Override // android.view.Menu
    public int size() {
        return this.f1058f.size();
    }

    public String t() {
        return "android:menu:actionviewstates";
    }

    public Context u() {
        return this.f1053a;
    }

    public g v() {
        return this.f1075w;
    }

    public Drawable w() {
        return this.f1067o;
    }

    public CharSequence x() {
        return this.f1066n;
    }

    public View y() {
        return this.f1068p;
    }

    public ArrayList z() {
        r();
        return this.f1062j;
    }

    @Override // android.view.Menu
    public MenuItem add(int i2) {
        return a(0, 0, 0, this.f1054b.getString(i2));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2) {
        return addSubMenu(0, 0, 0, this.f1054b.getString(i2));
    }

    @Override // android.view.Menu
    public MenuItem add(int i2, int i3, int i4, CharSequence charSequence) {
        return a(i2, i3, i4, charSequence);
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        g gVar = (g) a(i2, i3, i4, charSequence);
        l lVar = new l(this.f1053a, this, gVar);
        gVar.w(lVar);
        return lVar;
    }

    @Override // android.view.Menu
    public MenuItem add(int i2, int i3, int i4, int i5) {
        return a(i2, i3, i4, this.f1054b.getString(i5));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2, int i3, int i4, int i5) {
        return addSubMenu(i2, i3, i4, this.f1054b.getString(i5));
    }
}
