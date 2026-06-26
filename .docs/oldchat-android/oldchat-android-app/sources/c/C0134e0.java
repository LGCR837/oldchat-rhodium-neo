package c;

import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: renamed from: c.e0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0134e0 extends AbstractC0131d0 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static boolean f1857g = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j.k f1858a = new j.k();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j.k f1859b = new j.k();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f1860c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1861d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1862e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public AbstractC0173x f1863f;

    public C0134e0(String str, AbstractC0173x abstractC0173x, boolean z2) {
        this.f1860c = str;
        this.f1863f = abstractC0173x;
        this.f1861d = z2;
    }

    public void a() {
        if (!this.f1862e) {
            if (f1857g) {
                Log.v("LoaderManager", "Destroying Active in " + this);
            }
            int iK = this.f1858a.k() - 1;
            if (iK >= 0) {
                AbstractC0177z.a(this.f1858a.l(iK));
                throw null;
            }
            this.f1858a.a();
        }
        if (f1857g) {
            Log.v("LoaderManager", "Destroying Inactive in " + this);
        }
        int iK2 = this.f1859b.k() - 1;
        if (iK2 < 0) {
            this.f1859b.a();
        } else {
            AbstractC0177z.a(this.f1859b.l(iK2));
            throw null;
        }
    }

    public void b() {
        int iK = this.f1858a.k() - 1;
        if (iK < 0) {
            return;
        }
        AbstractC0177z.a(this.f1858a.l(iK));
        throw null;
    }

    public void c() {
        int iK = this.f1858a.k() - 1;
        if (iK < 0) {
            return;
        }
        AbstractC0177z.a(this.f1858a.l(iK));
        throw null;
    }

    public void d() {
        if (f1857g) {
            Log.v("LoaderManager", "Retaining in " + this);
        }
        if (this.f1861d) {
            this.f1862e = true;
            this.f1861d = false;
            int iK = this.f1858a.k() - 1;
            if (iK < 0) {
                return;
            }
            AbstractC0177z.a(this.f1858a.l(iK));
            throw null;
        }
        RuntimeException runtimeException = new RuntimeException("here");
        runtimeException.fillInStackTrace();
        Log.w("LoaderManager", "Called doRetain when not started: " + this, runtimeException);
    }

    public void e() {
        if (f1857g) {
            Log.v("LoaderManager", "Starting in " + this);
        }
        if (!this.f1861d) {
            this.f1861d = true;
            int iK = this.f1858a.k() - 1;
            if (iK < 0) {
                return;
            }
            AbstractC0177z.a(this.f1858a.l(iK));
            throw null;
        }
        RuntimeException runtimeException = new RuntimeException("here");
        runtimeException.fillInStackTrace();
        Log.w("LoaderManager", "Called doStart when already started: " + this, runtimeException);
    }

    public void f() {
        if (f1857g) {
            Log.v("LoaderManager", "Stopping in " + this);
        }
        if (this.f1861d) {
            int iK = this.f1858a.k() - 1;
            if (iK < 0) {
                this.f1861d = false;
                return;
            } else {
                AbstractC0177z.a(this.f1858a.l(iK));
                throw null;
            }
        }
        RuntimeException runtimeException = new RuntimeException("here");
        runtimeException.fillInStackTrace();
        Log.w("LoaderManager", "Called doStop when not started: " + this, runtimeException);
    }

    public void g(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (this.f1858a.k() > 0) {
            printWriter.print(str);
            printWriter.println("Active Loaders:");
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("    ");
            if (this.f1858a.k() > 0) {
                AbstractC0177z.a(this.f1858a.l(0));
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.f1858a.h(0));
                printWriter.print(": ");
                throw null;
            }
        }
        if (this.f1859b.k() > 0) {
            printWriter.print(str);
            printWriter.println("Inactive Loaders:");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append("    ");
            if (this.f1859b.k() <= 0) {
                return;
            }
            AbstractC0177z.a(this.f1859b.l(0));
            printWriter.print(str);
            printWriter.print("  #");
            printWriter.print(this.f1859b.h(0));
            printWriter.print(": ");
            throw null;
        }
    }

    public void h() {
        if (this.f1862e) {
            if (f1857g) {
                Log.v("LoaderManager", "Finished Retaining in " + this);
            }
            this.f1862e = false;
            int iK = this.f1858a.k() - 1;
            if (iK < 0) {
                return;
            }
            AbstractC0177z.a(this.f1858a.l(iK));
            throw null;
        }
    }

    public boolean i() {
        if (this.f1858a.k() <= 0) {
            return false;
        }
        AbstractC0177z.a(this.f1858a.l(0));
        throw null;
    }

    public void j(AbstractC0173x abstractC0173x) {
        this.f1863f = abstractC0173x;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        j.c.a(this.f1863f, sb);
        sb.append("}}");
        return sb.toString();
    }
}
