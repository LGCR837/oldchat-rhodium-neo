package o;

import android.app.UiModeManager;
import android.content.Context;
import android.view.ActionMode;
import android.view.Window;
import o.k;

/* JADX INFO: loaded from: classes.dex */
public class l extends k {

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public final UiModeManager f6866X;

    public l(Context context, Window window, e eVar) {
        super(context, window, eVar);
        this.f6866X = (UiModeManager) context.getSystemService("uimode");
    }

    @Override // o.k
    public int C0(int i2) {
        if (i2 == 0 && this.f6866X.getNightMode() == 0) {
            return -1;
        }
        return super.C0(i2);
    }

    @Override // o.k, o.g
    public Window.Callback P(Window.Callback callback) {
        return new a(callback);
    }

    public class a extends k.a {
        public a(Window.Callback callback) {
            super(callback);
        }

        @Override // v.u, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i2) {
            return (l.this.B0() && i2 == 0) ? a(callback) : super.onWindowStartingActionMode(callback, i2);
        }

        @Override // o.k.a, v.u, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return null;
        }
    }
}
