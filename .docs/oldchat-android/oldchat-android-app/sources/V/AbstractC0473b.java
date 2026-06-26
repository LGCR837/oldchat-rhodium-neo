package v;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/* JADX INFO: renamed from: v.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0473b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f7922a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f7923b;

    /* JADX INFO: renamed from: v.b$a */
    public interface a {
        void a(AbstractC0473b abstractC0473b);

        boolean b(AbstractC0473b abstractC0473b, Menu menu);

        boolean c(AbstractC0473b abstractC0473b, Menu menu);

        boolean d(AbstractC0473b abstractC0473b, MenuItem menuItem);
    }

    public abstract void c();

    public abstract View d();

    public abstract Menu e();

    public abstract MenuInflater f();

    public abstract CharSequence g();

    public Object h() {
        return this.f7922a;
    }

    public abstract CharSequence i();

    public boolean j() {
        return this.f7923b;
    }

    public abstract void k();

    public abstract boolean l();

    public abstract void m(View view);

    public abstract void n(int i2);

    public abstract void o(CharSequence charSequence);

    public void p(Object obj) {
        this.f7922a = obj;
    }

    public abstract void q(int i2);

    public abstract void r(CharSequence charSequence);

    public void s(boolean z2) {
        this.f7923b = z2;
    }
}
