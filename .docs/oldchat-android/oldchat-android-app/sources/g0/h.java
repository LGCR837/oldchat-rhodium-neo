package g0;

import android.os.Handler;
import g0.j;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Set f5358a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ l f5359b;

        public a(Set set, l lVar) {
            this.f5358a = set;
            this.f5359b = lVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f5358a.iterator();
            while (it.hasNext()) {
                ((j.InterfaceC0042j) it.next()).b(this.f5359b);
            }
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Set f5360a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f5361b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f5362c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ long f5363d;

        public b(Set set, String str, String str2, long j2) {
            this.f5360a = set;
            this.f5361b = str;
            this.f5362c = str2;
            this.f5363d = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f5360a.iterator();
            while (it.hasNext()) {
                ((j.InterfaceC0042j) it.next()).c(this.f5361b, this.f5362c, this.f5363d);
            }
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Set f5364a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ m f5365b;

        public c(Set set, m mVar) {
            this.f5364a = set;
            this.f5365b = mVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f5364a.iterator();
            while (it.hasNext()) {
                ((j.InterfaceC0042j) it.next()).a(this.f5365b);
            }
        }
    }

    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Set f5366a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ n f5367b;

        public d(Set set, n nVar) {
            this.f5366a = set;
            this.f5367b = nVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f5366a.iterator();
            while (it.hasNext()) {
                ((j.InterfaceC0042j) it.next()).f(this.f5367b);
            }
        }
    }

    public class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Set f5368a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ o f5369b;

        public e(Set set, o oVar) {
            this.f5368a = set;
            this.f5369b = oVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f5368a.iterator();
            while (it.hasNext()) {
                ((j.InterfaceC0042j) it.next()).d(this.f5369b);
            }
        }
    }

    public class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Set f5370a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ p f5371b;

        public f(Set set, p pVar) {
            this.f5370a = set;
            this.f5371b = pVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f5370a.iterator();
            while (it.hasNext()) {
                ((j.InterfaceC0042j) it.next()).e(this.f5371b);
            }
        }
    }

    public class g implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Set f5372a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f5373b;

        public g(Set set, boolean z2) {
            this.f5372a = set;
            this.f5373b = z2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f5372a.iterator();
            while (it.hasNext()) {
                ((j.InterfaceC0042j) it.next()).g(this.f5373b);
            }
        }
    }

    public static void a(Handler handler, Set set, l lVar) {
        handler.post(new a(set, lVar));
    }

    public static void b(Handler handler, Set set, String str, String str2, long j2) {
        handler.post(new b(set, str, str2, j2));
    }

    public static void c(Handler handler, Set set, m mVar) {
        handler.post(new c(set, mVar));
    }

    public static void d(Handler handler, Set set, n nVar) {
        handler.post(new d(set, nVar));
    }

    public static void e(Handler handler, Set set, o oVar) {
        handler.post(new e(set, oVar));
    }

    public static void f(Handler handler, Set set, p pVar) {
        handler.post(new f(set, pVar));
    }

    public static void g(Handler handler, Set set, boolean z2) {
        handler.post(new g(set, z2));
    }
}
