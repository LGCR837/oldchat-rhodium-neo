package o;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import java.lang.Thread;
import v.u;
import x.f0;

/* JADX INFO: loaded from: classes.dex */
public abstract class g extends f {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static boolean f6834r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final boolean f6835s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final int[] f6836t;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f6837c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Window f6838d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Window.Callback f6839e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Window.Callback f6840f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final e f6841g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public AbstractC0430a f6842h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public MenuInflater f6843i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f6844j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f6845k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f6846l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f6847m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f6848n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public CharSequence f6849o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f6850p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f6851q;

    public static class a implements Thread.UncaughtExceptionHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Thread.UncaughtExceptionHandler f6852a;

        public a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.f6852a = uncaughtExceptionHandler;
        }

        public final boolean a(Throwable th) {
            String message;
            if (!(th instanceof Resources.NotFoundException) || (message = th.getMessage()) == null) {
                return false;
            }
            return message.contains("drawable") || message.contains("Drawable");
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            if (!a(th)) {
                this.f6852a.uncaughtException(thread, th);
                return;
            }
            Resources.NotFoundException notFoundException = new Resources.NotFoundException(th.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
            notFoundException.initCause(th.getCause());
            notFoundException.setStackTrace(th.getStackTrace());
            this.f6852a.uncaughtException(thread, notFoundException);
        }
    }

    static {
        boolean z2 = Build.VERSION.SDK_INT < 21;
        f6835s = z2;
        if (z2 && !f6834r) {
            Thread.setDefaultUncaughtExceptionHandler(new a(Thread.getDefaultUncaughtExceptionHandler()));
            f6834r = true;
        }
        f6836t = new int[]{R.attr.windowBackground};
    }

    public g(Context context, Window window, e eVar) {
        this.f6837c = context;
        this.f6838d = window;
        this.f6841g = eVar;
        Window.Callback callback = window.getCallback();
        this.f6839e = callback;
        if (callback instanceof b) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback callbackP = P(callback);
        this.f6840f = callbackP;
        window.setCallback(callbackP);
        f0 f0VarR = f0.r(context, null, f6836t);
        Drawable drawableG = f0VarR.g(0);
        if (drawableG != null) {
            window.setBackgroundDrawable(drawableG);
        }
        f0VarR.t();
    }

    @Override // o.f
    public final void D(CharSequence charSequence) {
        this.f6849o = charSequence;
        N(charSequence);
    }

    public abstract boolean E(KeyEvent keyEvent);

    public final Context F() {
        AbstractC0430a abstractC0430aK = k();
        Context contextJ = abstractC0430aK != null ? abstractC0430aK.j() : null;
        return contextJ == null ? this.f6837c : contextJ;
    }

    public final CharSequence G() {
        Window.Callback callback = this.f6839e;
        return callback instanceof Activity ? ((Activity) callback).getTitle() : this.f6849o;
    }

    public final Window.Callback H() {
        return this.f6838d.getCallback();
    }

    public abstract void I();

    public final boolean J() {
        return this.f6851q;
    }

    public abstract boolean K(int i2, KeyEvent keyEvent);

    public abstract boolean L(int i2, Menu menu);

    public abstract void M(int i2, Menu menu);

    public abstract void N(CharSequence charSequence);

    public final AbstractC0430a O() {
        return this.f6842h;
    }

    public Window.Callback P(Window.Callback callback) {
        return new b(callback);
    }

    @Override // o.f
    public boolean d() {
        return false;
    }

    @Override // o.f
    public MenuInflater j() {
        if (this.f6843i == null) {
            I();
            AbstractC0430a abstractC0430a = this.f6842h;
            this.f6843i = new v.k(abstractC0430a != null ? abstractC0430a.j() : this.f6837c);
        }
        return this.f6843i;
    }

    @Override // o.f
    public AbstractC0430a k() {
        I();
        return this.f6842h;
    }

    @Override // o.f
    public void q() {
        this.f6851q = true;
    }

    @Override // o.f
    public void u() {
        this.f6850p = true;
    }

    public class b extends u {
        public b(Window.Callback callback) {
            super(callback);
        }

        @Override // v.u, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return g.this.E(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // v.u, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || g.this.K(keyEvent.getKeyCode(), keyEvent);
        }

        @Override // v.u, android.view.Window.Callback
        public boolean onCreatePanelMenu(int i2, Menu menu) {
            if (i2 != 0 || (menu instanceof android.support.v7.view.menu.e)) {
                return super.onCreatePanelMenu(i2, menu);
            }
            return false;
        }

        @Override // v.u, android.view.Window.Callback
        public boolean onMenuOpened(int i2, Menu menu) {
            super.onMenuOpened(i2, menu);
            g.this.L(i2, menu);
            return true;
        }

        @Override // v.u, android.view.Window.Callback
        public void onPanelClosed(int i2, Menu menu) {
            super.onPanelClosed(i2, menu);
            g.this.M(i2, menu);
        }

        @Override // v.u, android.view.Window.Callback
        public boolean onPreparePanel(int i2, View view, Menu menu) {
            android.support.v7.view.menu.e eVar = menu instanceof android.support.v7.view.menu.e ? (android.support.v7.view.menu.e) menu : null;
            if (i2 == 0 && eVar == null) {
                return false;
            }
            if (eVar != null) {
                eVar.Z(true);
            }
            boolean zOnPreparePanel = super.onPreparePanel(i2, view, menu);
            if (eVar != null) {
                eVar.Z(false);
            }
            return zOnPreparePanel;
        }

        @Override // android.view.Window.Callback
        public void onContentChanged() {
        }
    }

    @Override // o.f
    public void C(int i2) {
    }

    @Override // o.f
    public void t(Bundle bundle) {
    }
}
