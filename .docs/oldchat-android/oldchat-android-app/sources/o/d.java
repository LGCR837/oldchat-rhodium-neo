package o;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import c.AbstractActivityC0167u;
import c.AbstractC0121a;
import c.AbstractC0137f0;
import c.G1;
import k.AbstractC0313u;
import v.AbstractC0473b;
import x.g0;

/* JADX INFO: loaded from: classes.dex */
public abstract class d extends AbstractActivityC0167u implements e, G1.a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public f f6828n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f6829o = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f6830p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Resources f6831q;

    @Override // c.AbstractActivityC0167u
    public void F() {
        H().m();
    }

    public f H() {
        if (this.f6828n == null) {
            this.f6828n = f.e(this, this);
        }
        return this.f6828n;
    }

    public AbstractC0430a I() {
        return H().k();
    }

    public void J(G1 g1) {
        g1.b(this);
    }

    public boolean M() {
        Intent intentI = i();
        if (intentI == null) {
            return false;
        }
        if (!O(intentI)) {
            N(intentI);
            return true;
        }
        G1 g1D = G1.d(this);
        J(g1D);
        K(g1D);
        g1D.e();
        try {
            AbstractC0121a.h(this);
            return true;
        } catch (IllegalStateException unused) {
            finish();
            return true;
        }
    }

    public void N(Intent intent) {
        AbstractC0137f0.e(this, intent);
    }

    public boolean O(Intent intent) {
        return AbstractC0137f0.f(this, intent);
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        H().c(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (AbstractC0313u.c(keyEvent) && keyEvent.getUnicodeChar(keyEvent.getMetaState() & (-28673)) == 60) {
            int action = keyEvent.getAction();
            if (action == 0) {
                AbstractC0430a abstractC0430aI = I();
                if (abstractC0430aI != null && abstractC0430aI.l() && abstractC0430aI.p()) {
                    this.f6830p = true;
                    return true;
                }
            } else if (action == 1 && this.f6830p) {
                this.f6830p = false;
                return true;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    public View findViewById(int i2) {
        return H().h(i2);
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        return H().j();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.f6831q == null && g0.a()) {
            this.f6831q = new g0(this, super.getResources());
        }
        Resources resources = this.f6831q;
        return resources == null ? super.getResources() : resources;
    }

    @Override // o.e
    public AbstractC0473b h(AbstractC0473b.a aVar) {
        return null;
    }

    @Override // c.G1.a
    public Intent i() {
        return AbstractC0137f0.a(this);
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        H().m();
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        H().o(configuration);
        if (this.f6831q != null) {
            this.f6831q.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        L();
    }

    @Override // c.AbstractActivityC0167u, c.AbstractActivityC0160q, android.app.Activity
    public void onCreate(Bundle bundle) {
        int i2;
        f fVarH = H();
        fVarH.l();
        fVarH.p(bundle);
        if (fVarH.d() && (i2 = this.f6829o) != 0) {
            if (Build.VERSION.SDK_INT >= 23) {
                onApplyThemeResource(getTheme(), this.f6829o, false);
            } else {
                setTheme(i2);
            }
        }
        super.onCreate(bundle);
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        H().q();
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        if (super.onMenuItemSelected(i2, menuItem)) {
            return true;
        }
        AbstractC0430a abstractC0430aI = I();
        if (menuItem.getItemId() != 16908332 || abstractC0430aI == null || (abstractC0430aI.i() & 4) == 0) {
            return false;
        }
        return M();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i2, Menu menu) {
        return super.onMenuOpened(i2, menu);
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i2, Menu menu) {
        super.onPanelClosed(i2, menu);
    }

    @Override // android.app.Activity
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        H().r(bundle);
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        H().s();
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        H().t(bundle);
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onStart() {
        super.onStart();
        H().u();
    }

    @Override // c.AbstractActivityC0167u, android.app.Activity
    public void onStop() {
        super.onStop();
        H().v();
    }

    @Override // android.app.Activity
    public void onTitleChanged(CharSequence charSequence, int i2) {
        super.onTitleChanged(charSequence, i2);
        H().D(charSequence);
    }

    @Override // android.app.Activity
    public void setContentView(int i2) {
        H().y(i2);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i2) {
        super.setTheme(i2);
        this.f6829o = i2;
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        H().z(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        H().A(view, layoutParams);
    }

    public void L() {
    }

    public void K(G1 g1) {
    }

    @Override // o.e
    public void e(AbstractC0473b abstractC0473b) {
    }

    @Override // o.e
    public void p(AbstractC0473b abstractC0473b) {
    }
}
