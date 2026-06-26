package v;

import android.content.Context;
import android.support.v7.view.menu.e;
import android.support.v7.widget.ActionBarContextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.lang.ref.WeakReference;
import v.AbstractC0473b;

/* JADX INFO: loaded from: classes.dex */
public class e extends AbstractC0473b implements e.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f7927c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ActionBarContextView f7928d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public AbstractC0473b.a f7929e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public WeakReference f7930f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f7931g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f7932h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public android.support.v7.view.menu.e f7933i;

    public e(Context context, ActionBarContextView actionBarContextView, AbstractC0473b.a aVar, boolean z2) {
        this.f7927c = context;
        this.f7928d = actionBarContextView;
        this.f7929e = aVar;
        android.support.v7.view.menu.e eVarR = new android.support.v7.view.menu.e(actionBarContextView.getContext()).R(1);
        this.f7933i = eVarR;
        eVarR.Q(this);
        this.f7932h = z2;
    }

    @Override // android.support.v7.view.menu.e.a
    public void a(android.support.v7.view.menu.e eVar) {
        k();
        this.f7928d.l();
    }

    @Override // android.support.v7.view.menu.e.a
    public boolean b(android.support.v7.view.menu.e eVar, MenuItem menuItem) {
        return this.f7929e.d(this, menuItem);
    }

    @Override // v.AbstractC0473b
    public void c() {
        if (this.f7931g) {
            return;
        }
        this.f7931g = true;
        this.f7928d.sendAccessibilityEvent(32);
        this.f7929e.a(this);
    }

    @Override // v.AbstractC0473b
    public View d() {
        WeakReference weakReference = this.f7930f;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    @Override // v.AbstractC0473b
    public Menu e() {
        return this.f7933i;
    }

    @Override // v.AbstractC0473b
    public MenuInflater f() {
        return new k(this.f7928d.getContext());
    }

    @Override // v.AbstractC0473b
    public CharSequence g() {
        return this.f7928d.getSubtitle();
    }

    @Override // v.AbstractC0473b
    public CharSequence i() {
        return this.f7928d.getTitle();
    }

    @Override // v.AbstractC0473b
    public void k() {
        this.f7929e.b(this, this.f7933i);
    }

    @Override // v.AbstractC0473b
    public boolean l() {
        return this.f7928d.j();
    }

    @Override // v.AbstractC0473b
    public void m(View view) {
        this.f7928d.setCustomView(view);
        this.f7930f = view != null ? new WeakReference(view) : null;
    }

    @Override // v.AbstractC0473b
    public void n(int i2) {
        o(this.f7927c.getString(i2));
    }

    @Override // v.AbstractC0473b
    public void o(CharSequence charSequence) {
        this.f7928d.setSubtitle(charSequence);
    }

    @Override // v.AbstractC0473b
    public void q(int i2) {
        r(this.f7927c.getString(i2));
    }

    @Override // v.AbstractC0473b
    public void r(CharSequence charSequence) {
        this.f7928d.setTitle(charSequence);
    }

    @Override // v.AbstractC0473b
    public void s(boolean z2) {
        super.s(z2);
        this.f7928d.setTitleOptional(z2);
    }
}
