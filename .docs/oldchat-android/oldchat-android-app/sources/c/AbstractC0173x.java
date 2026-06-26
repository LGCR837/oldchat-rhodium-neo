package c;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: renamed from: c.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0173x extends AbstractC0169v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Activity f2057a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f2058b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Handler f2059c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2060d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0109A f2061e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public j.j f2062f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f2063g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public C0134e0 f2064h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2065i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f2066j;

    public AbstractC0173x(AbstractActivityC0167u abstractActivityC0167u) {
        this(abstractActivityC0167u, abstractActivityC0167u, abstractActivityC0167u.f2040c, 0);
    }

    public void c() {
        C0134e0 c0134e0 = this.f2064h;
        if (c0134e0 == null) {
            return;
        }
        c0134e0.a();
    }

    public void d() {
        if (this.f2066j) {
            return;
        }
        this.f2066j = true;
        C0134e0 c0134e0 = this.f2064h;
        if (c0134e0 != null) {
            c0134e0.e();
        } else if (!this.f2065i) {
            C0134e0 c0134e0K = k("(root)", true, false);
            this.f2064h = c0134e0K;
            if (c0134e0K != null && !c0134e0K.f1861d) {
                c0134e0K.e();
            }
        }
        this.f2065i = true;
    }

    public void e(boolean z2) {
        this.f2063g = z2;
        C0134e0 c0134e0 = this.f2064h;
        if (c0134e0 != null && this.f2066j) {
            this.f2066j = false;
            if (z2) {
                c0134e0.d();
            } else {
                c0134e0.f();
            }
        }
    }

    public void f(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mLoadersStarted=");
        printWriter.println(this.f2066j);
        if (this.f2064h != null) {
            printWriter.print(str);
            printWriter.print("Loader Manager ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this.f2064h)));
            printWriter.println(":");
            this.f2064h.g(str + "  ", fileDescriptor, printWriter, strArr);
        }
    }

    public Activity g() {
        return this.f2057a;
    }

    public Context h() {
        return this.f2058b;
    }

    public C0109A i() {
        return this.f2061e;
    }

    public Handler j() {
        return this.f2059c;
    }

    public C0134e0 k(String str, boolean z2, boolean z3) {
        if (this.f2062f == null) {
            this.f2062f = new j.j();
        }
        C0134e0 c0134e0 = (C0134e0) this.f2062f.get(str);
        if (c0134e0 != null) {
            c0134e0.j(this);
            return c0134e0;
        }
        if (!z3) {
            return c0134e0;
        }
        C0134e0 c0134e02 = new C0134e0(str, this, z2);
        this.f2062f.put(str, c0134e02);
        return c0134e02;
    }

    public boolean l() {
        return this.f2063g;
    }

    public void m(String str) {
        C0134e0 c0134e0;
        j.j jVar = this.f2062f;
        if (jVar == null || (c0134e0 = (C0134e0) jVar.get(str)) == null || c0134e0.f1862e) {
            return;
        }
        c0134e0.a();
        this.f2062f.remove(str);
    }

    public abstract void n(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t);

    public abstract void o(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract LayoutInflater p();

    public abstract int q();

    public abstract boolean r();

    public abstract boolean s(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t);

    public abstract void t(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, Intent intent, int i2, Bundle bundle);

    public abstract void u();

    public void v() {
        j.j jVar = this.f2062f;
        if (jVar != null) {
            int size = jVar.size();
            C0134e0[] c0134e0Arr = new C0134e0[size];
            for (int i2 = size - 1; i2 >= 0; i2--) {
                c0134e0Arr[i2] = (C0134e0) this.f2062f.k(i2);
            }
            for (int i3 = 0; i3 < size; i3++) {
                C0134e0 c0134e0 = c0134e0Arr[i3];
                c0134e0.h();
                c0134e0.c();
            }
        }
    }

    public void w(j.j jVar) {
        this.f2062f = jVar;
    }

    public j.j x() {
        j.j jVar = this.f2062f;
        int i2 = 0;
        if (jVar != null) {
            int size = jVar.size();
            C0134e0[] c0134e0Arr = new C0134e0[size];
            for (int i3 = size - 1; i3 >= 0; i3--) {
                c0134e0Arr[i3] = (C0134e0) this.f2062f.k(i3);
            }
            boolean zL = l();
            int i4 = 0;
            while (i2 < size) {
                C0134e0 c0134e0 = c0134e0Arr[i2];
                if (!c0134e0.f1862e && zL) {
                    if (!c0134e0.f1861d) {
                        c0134e0.e();
                    }
                    c0134e0.d();
                }
                if (c0134e0.f1862e) {
                    i4 = 1;
                } else {
                    c0134e0.a();
                    this.f2062f.remove(c0134e0.f1860c);
                }
                i2++;
            }
            i2 = i4;
        }
        if (i2 != 0) {
            return this.f2062f;
        }
        return null;
    }

    public AbstractC0173x(Activity activity, Context context, Handler handler, int i2) {
        this.f2061e = new C0109A();
        this.f2057a = activity;
        this.f2058b = context;
        this.f2059c = handler;
        this.f2060d = i2;
    }
}
