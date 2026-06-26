package o;

import android.content.Context;
import android.view.Menu;
import android.view.Window;
import java.util.List;
import o.l;
import o.m;

/* JADX INFO: loaded from: classes.dex */
public class h extends l {

    public class a extends l.a {
        public a(Window.Callback callback) {
            super(callback);
        }

        @Override // v.u, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List list, Menu menu, int i2) {
            android.support.v7.view.menu.e eVar;
            m.j jVarD0 = h.this.d0(0, true);
            if (jVarD0 == null || (eVar = jVarD0.f6914j) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i2);
            } else {
                super.onProvideKeyboardShortcuts(list, eVar, i2);
            }
        }
    }

    public h(Context context, Window window, e eVar) {
        super(context, window, eVar);
    }

    @Override // o.l, o.k, o.g
    public Window.Callback P(Window.Callback callback) {
        return new a(callback);
    }
}
