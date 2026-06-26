package x;

import android.content.Context;
import android.support.v7.view.menu.e;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import p.AbstractC0460a;

/* JADX INFO: loaded from: classes.dex */
public class U {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f8096a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final android.support.v7.view.menu.e f8097b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f8098c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final android.support.v7.view.menu.h f8099d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f8100e;

    public class b implements PopupWindow.OnDismissListener {
        public b() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            U.this.getClass();
        }
    }

    public interface c {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public U(Context context, View view) {
        this(context, view, 0);
    }

    public Menu a() {
        return this.f8097b;
    }

    public MenuInflater b() {
        return new v.k(this.f8096a);
    }

    public void c(c cVar) {
        this.f8100e = cVar;
    }

    public void d() {
        this.f8099d.k();
    }

    public U(Context context, View view, int i2) {
        this(context, view, i2, AbstractC0460a.f7699C, 0);
    }

    public U(Context context, View view, int i2, int i3, int i4) {
        this.f8096a = context;
        this.f8098c = view;
        android.support.v7.view.menu.e eVar = new android.support.v7.view.menu.e(context);
        this.f8097b = eVar;
        eVar.Q(new a());
        android.support.v7.view.menu.h hVar = new android.support.v7.view.menu.h(context, eVar, view, false, i3, i4);
        this.f8099d = hVar;
        hVar.h(i2);
        hVar.i(new b());
    }

    public class a implements e.a {
        public a() {
        }

        @Override // android.support.v7.view.menu.e.a
        public boolean b(android.support.v7.view.menu.e eVar, MenuItem menuItem) {
            c cVar = U.this.f8100e;
            if (cVar != null) {
                return cVar.onMenuItemClick(menuItem);
            }
            return false;
        }

        @Override // android.support.v7.view.menu.e.a
        public void a(android.support.v7.view.menu.e eVar) {
        }
    }
}
