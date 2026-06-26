package o0;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class G {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static G f7014e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f7015a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f7016b = System.currentTimeMillis();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f7017c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f7018d = 0;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = G.this.f7015a.iterator();
            while (it.hasNext()) {
                ((b) it.next()).a(G.this.f7017c);
            }
        }
    }

    public interface b {
        void a(boolean z2);
    }

    public static synchronized G d() {
        try {
            if (f7014e == null) {
                f7014e = new G();
            }
        } catch (Throwable th) {
            throw th;
        }
        return f7014e;
    }

    public void c(b bVar) {
        if (this.f7015a.contains(bVar)) {
            return;
        }
        this.f7015a.add(bVar);
    }

    public long e(long j2) {
        long jCurrentTimeMillis = j2 - (System.currentTimeMillis() - this.f7016b);
        if (jCurrentTimeMillis > 0) {
            return jCurrentTimeMillis;
        }
        return 0L;
    }

    public boolean f() {
        return this.f7017c;
    }

    public final void g() {
        new Handler(Looper.getMainLooper()).post(new a());
    }

    public void h(int i2) {
        if (i2 == -1 || i2 == 0) {
            int i3 = this.f7018d + 1;
            this.f7018d = i3;
            if (i3 < 3 || !this.f7017c) {
                return;
            }
            k(false);
        }
    }

    public void i() {
        this.f7018d = 0;
        if (this.f7017c) {
            return;
        }
        k(true);
    }

    public void j(b bVar) {
        this.f7015a.remove(bVar);
    }

    public final void k(boolean z2) {
        if (this.f7017c != z2) {
            this.f7017c = z2;
            g();
        }
    }
}
