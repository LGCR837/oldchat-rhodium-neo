package x;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.i;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import k.F1;

/* JADX INFO: loaded from: classes.dex */
public interface G {
    boolean a();

    boolean b();

    void c(Menu menu, i.a aVar);

    void collapseActionView();

    boolean d();

    boolean e();

    void f();

    boolean g();

    CharSequence getTitle();

    void h();

    int i();

    void j(int i2);

    void k(int i2);

    ViewGroup l();

    void m(boolean z2);

    Context n();

    int o();

    void p(android.support.v7.widget.l lVar);

    F1 q(int i2, long j2);

    void r();

    boolean s();

    void setIcon(int i2);

    void setIcon(Drawable drawable);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    void t();

    void u(boolean z2);

    void v(int i2);
}
