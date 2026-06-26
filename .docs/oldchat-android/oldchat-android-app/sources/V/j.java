package v;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import h.InterfaceMenuC0248a;
import h.InterfaceMenuItemC0249b;
import java.util.ArrayList;
import v.AbstractC0473b;
import w.x;

/* JADX INFO: loaded from: classes.dex */
public class j extends ActionMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f7934a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AbstractC0473b f7935b;

    public static class a implements AbstractC0473b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ActionMode.Callback f7936a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Context f7937b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ArrayList f7938c = new ArrayList();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final j.j f7939d = new j.j();

        public a(Context context, ActionMode.Callback callback) {
            this.f7937b = context;
            this.f7936a = callback;
        }

        @Override // v.AbstractC0473b.a
        public void a(AbstractC0473b abstractC0473b) {
            this.f7936a.onDestroyActionMode(e(abstractC0473b));
        }

        @Override // v.AbstractC0473b.a
        public boolean b(AbstractC0473b abstractC0473b, Menu menu) {
            return this.f7936a.onPrepareActionMode(e(abstractC0473b), f(menu));
        }

        @Override // v.AbstractC0473b.a
        public boolean c(AbstractC0473b abstractC0473b, Menu menu) {
            return this.f7936a.onCreateActionMode(e(abstractC0473b), f(menu));
        }

        @Override // v.AbstractC0473b.a
        public boolean d(AbstractC0473b abstractC0473b, MenuItem menuItem) {
            return this.f7936a.onActionItemClicked(e(abstractC0473b), x.b(this.f7937b, (InterfaceMenuItemC0249b) menuItem));
        }

        public ActionMode e(AbstractC0473b abstractC0473b) {
            int size = this.f7938c.size();
            for (int i2 = 0; i2 < size; i2++) {
                j jVar = (j) this.f7938c.get(i2);
                if (jVar != null && jVar.f7935b == abstractC0473b) {
                    return jVar;
                }
            }
            j jVar2 = new j(this.f7937b, abstractC0473b);
            this.f7938c.add(jVar2);
            return jVar2;
        }

        public final Menu f(Menu menu) {
            Menu menu2 = (Menu) this.f7939d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            Menu menuA = x.a(this.f7937b, (InterfaceMenuC0248a) menu);
            this.f7939d.put(menu, menuA);
            return menuA;
        }
    }

    public j(Context context, AbstractC0473b abstractC0473b) {
        this.f7934a = context;
        this.f7935b = abstractC0473b;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f7935b.c();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f7935b.d();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return x.a(this.f7934a, (InterfaceMenuC0248a) this.f7935b.e());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f7935b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f7935b.g();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f7935b.h();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f7935b.i();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f7935b.j();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f7935b.k();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f7935b.l();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f7935b.m(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f7935b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f7935b.p(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f7935b.r(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z2) {
        this.f7935b.s(z2);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i2) {
        this.f7935b.n(i2);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i2) {
        this.f7935b.q(i2);
    }
}
