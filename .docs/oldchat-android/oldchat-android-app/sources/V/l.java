package v;

import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;
import k.F1;
import k.T1;
import k.U1;

/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Interpolator f7977c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public T1 f7978d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f7979e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f7976b = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final U1 f7980f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f7975a = new ArrayList();

    public class a extends U1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7981a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f7982b = 0;

        public a() {
        }

        @Override // k.T1
        public void a(View view) {
            int i2 = this.f7982b + 1;
            this.f7982b = i2;
            if (i2 == l.this.f7975a.size()) {
                T1 t1 = l.this.f7978d;
                if (t1 != null) {
                    t1.a(null);
                }
                d();
            }
        }

        @Override // k.U1, k.T1
        public void b(View view) {
            if (this.f7981a) {
                return;
            }
            this.f7981a = true;
            T1 t1 = l.this.f7978d;
            if (t1 != null) {
                t1.b(null);
            }
        }

        public void d() {
            this.f7982b = 0;
            this.f7981a = false;
            l.this.b();
        }
    }

    public void a() {
        if (this.f7979e) {
            Iterator it = this.f7975a.iterator();
            while (it.hasNext()) {
                ((F1) it.next()).b();
            }
            this.f7979e = false;
        }
    }

    public void b() {
        this.f7979e = false;
    }

    public l c(F1 f1) {
        if (!this.f7979e) {
            this.f7975a.add(f1);
        }
        return this;
    }

    public l d(F1 f1, F1 f12) {
        this.f7975a.add(f1);
        f12.g(f1.c());
        this.f7975a.add(f12);
        return this;
    }

    public l e(long j2) {
        if (!this.f7979e) {
            this.f7976b = j2;
        }
        return this;
    }

    public l f(Interpolator interpolator) {
        if (!this.f7979e) {
            this.f7977c = interpolator;
        }
        return this;
    }

    public l g(T1 t1) {
        if (!this.f7979e) {
            this.f7978d = t1;
        }
        return this;
    }

    public void h() {
        if (this.f7979e) {
            return;
        }
        for (F1 f1 : this.f7975a) {
            long j2 = this.f7976b;
            if (j2 >= 0) {
                f1.d(j2);
            }
            Interpolator interpolator = this.f7977c;
            if (interpolator != null) {
                f1.e(interpolator);
            }
            if (this.f7978d != null) {
                f1.f(this.f7980f);
            }
            f1.i();
        }
        this.f7979e = true;
    }
}
