package o;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Window;
import o.g;
import v.AbstractC0473b;
import v.j;

/* JADX INFO: loaded from: classes.dex */
public class k extends i {

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public int f6855T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public boolean f6856U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public boolean f6857V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public b f6858W;

    public class a extends g.b {
        public a(Window.Callback callback) {
            super(callback);
        }

        public final ActionMode a(ActionMode.Callback callback) {
            j.a aVar = new j.a(k.this.f6837c, callback);
            AbstractC0473b abstractC0473bV0 = k.this.v0(aVar);
            if (abstractC0473bV0 != null) {
                return aVar.e(abstractC0473bV0);
            }
            return null;
        }

        @Override // v.u, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return k.this.B0() ? a(callback) : super.onWindowStartingActionMode(callback);
        }
    }

    public final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public q f6860a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f6861b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public BroadcastReceiver f6862c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public IntentFilter f6863d;

        public class a extends BroadcastReceiver {
            public a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                b.this.b();
            }
        }

        public b(q qVar) {
            this.f6860a = qVar;
            this.f6861b = qVar.d();
        }

        public final void a() {
            BroadcastReceiver broadcastReceiver = this.f6862c;
            if (broadcastReceiver != null) {
                k.this.f6837c.unregisterReceiver(broadcastReceiver);
                this.f6862c = null;
            }
        }

        public final void b() {
            boolean zD = this.f6860a.d();
            if (zD != this.f6861b) {
                this.f6861b = zD;
                k.this.d();
            }
        }

        public final int c() {
            return this.f6861b ? 2 : 1;
        }

        public final void d() {
            a();
            if (this.f6862c == null) {
                this.f6862c = new a();
            }
            if (this.f6863d == null) {
                IntentFilter intentFilter = new IntentFilter();
                this.f6863d = intentFilter;
                intentFilter.addAction("android.intent.action.TIME_SET");
                this.f6863d.addAction("android.intent.action.TIMEZONE_CHANGED");
                this.f6863d.addAction("android.intent.action.TIME_TICK");
            }
            k.this.f6837c.registerReceiver(this.f6862c, this.f6863d);
        }
    }

    public k(Context context, Window window, e eVar) {
        super(context, window, eVar);
        this.f6855T = -100;
        this.f6857V = true;
    }

    public final int A0() {
        int i2 = this.f6855T;
        return i2 != -100 ? i2 : f.i();
    }

    public boolean B0() {
        return this.f6857V;
    }

    @Override // o.g, o.f
    public void C(int i2) {
        if (i2 != -1 && i2 != 0 && i2 != 1 && i2 != 2) {
            Log.i("AppCompatDelegate", "setLocalNightMode() called with an unknown mode");
        } else if (this.f6855T != i2) {
            this.f6855T = i2;
            if (this.f6856U) {
                d();
            }
        }
    }

    public int C0(int i2) {
        if (i2 == -100) {
            return -1;
        }
        if (i2 != 0) {
            return i2;
        }
        z0();
        return this.f6858W.c();
    }

    public final boolean D0() {
        if (this.f6856U) {
            Context context = this.f6837c;
            if (context instanceof Activity) {
                PackageManager packageManager = context.getPackageManager();
                try {
                    Context context2 = this.f6837c;
                    return (packageManager.getActivityInfo(new ComponentName(context2, context2.getClass()), 0).configChanges & 512) == 0;
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e2);
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean E0(int i2) {
        Resources resources = this.f6837c.getResources();
        Configuration configuration = resources.getConfiguration();
        int i3 = configuration.uiMode & 48;
        int i4 = i2 == 2 ? 32 : 16;
        if (i3 == i4) {
            return false;
        }
        if (D0()) {
            ((Activity) this.f6837c).recreate();
            return true;
        }
        Configuration configuration2 = new Configuration(configuration);
        configuration2.uiMode = i4 | (configuration2.uiMode & (-49));
        resources.updateConfiguration(configuration2, null);
        return true;
    }

    @Override // o.g
    public Window.Callback P(Window.Callback callback) {
        return new a(callback);
    }

    @Override // o.g, o.f
    public boolean d() {
        int iA0 = A0();
        int iC0 = C0(iA0);
        boolean zE0 = iC0 != -1 ? E0(iC0) : false;
        if (iA0 == 0) {
            z0();
            this.f6858W.d();
        }
        this.f6856U = true;
        return zE0;
    }

    @Override // o.m, o.f
    public void p(Bundle bundle) {
        super.p(bundle);
        if (bundle == null || this.f6855T != -100) {
            return;
        }
        this.f6855T = bundle.getInt("appcompat:local_night_mode", -100);
    }

    @Override // o.m, o.g, o.f
    public void q() {
        super.q();
        b bVar = this.f6858W;
        if (bVar != null) {
            bVar.a();
        }
    }

    @Override // o.g, o.f
    public void t(Bundle bundle) {
        super.t(bundle);
        int i2 = this.f6855T;
        if (i2 != -100) {
            bundle.putInt("appcompat:local_night_mode", i2);
        }
    }

    @Override // o.g, o.f
    public void u() {
        super.u();
        d();
    }

    @Override // o.m, o.f
    public void v() {
        super.v();
        b bVar = this.f6858W;
        if (bVar != null) {
            bVar.a();
        }
    }

    public final void z0() {
        if (this.f6858W == null) {
            this.f6858W = new b(q.a(this.f6837c));
        }
    }
}
