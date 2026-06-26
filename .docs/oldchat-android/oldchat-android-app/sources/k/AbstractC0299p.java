package k;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: renamed from: k.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0299p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f5711a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a f5712b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b f5713c;

    /* JADX INFO: renamed from: k.p$a */
    public interface a {
    }

    /* JADX INFO: renamed from: k.p$b */
    public interface b {
        void onActionProviderVisibilityChanged(boolean z2);
    }

    public AbstractC0299p(Context context) {
        this.f5711a = context;
    }

    public abstract boolean a();

    public boolean b() {
        return true;
    }

    public abstract View c();

    public View d(MenuItem menuItem) {
        return c();
    }

    public abstract boolean e();

    public abstract void f(SubMenu subMenu);

    public boolean g() {
        return false;
    }

    public void h() {
        this.f5713c = null;
        this.f5712b = null;
    }

    public void i(a aVar) {
        this.f5712b = aVar;
    }

    public void j(b bVar) {
        if (this.f5713c != null && bVar != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f5713c = bVar;
    }
}
