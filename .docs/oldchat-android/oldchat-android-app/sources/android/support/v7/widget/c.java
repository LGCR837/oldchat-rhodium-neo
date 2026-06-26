package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f1629a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f1630b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f1631c = new ArrayList();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f1632a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public a f1633b;

        public void a(int i2) {
            if (i2 < 64) {
                this.f1632a &= (1 << i2) ^ (-1);
                return;
            }
            a aVar = this.f1633b;
            if (aVar != null) {
                aVar.a(i2 - 64);
            }
        }

        public int b(int i2) {
            a aVar = this.f1633b;
            return aVar == null ? i2 >= 64 ? Long.bitCount(this.f1632a) : Long.bitCount(this.f1632a & ((1 << i2) - 1)) : i2 < 64 ? Long.bitCount(this.f1632a & ((1 << i2) - 1)) : aVar.b(i2 - 64) + Long.bitCount(this.f1632a);
        }

        public final void c() {
            if (this.f1633b == null) {
                this.f1633b = new a();
            }
        }

        public boolean d(int i2) {
            if (i2 < 64) {
                return (this.f1632a & (1 << i2)) != 0;
            }
            c();
            return this.f1633b.d(i2 - 64);
        }

        public void e(int i2, boolean z2) {
            if (i2 >= 64) {
                c();
                this.f1633b.e(i2 - 64, z2);
                return;
            }
            long j2 = this.f1632a;
            boolean z3 = (Long.MIN_VALUE & j2) != 0;
            long j3 = (1 << i2) - 1;
            this.f1632a = ((j2 & (j3 ^ (-1))) << 1) | (j2 & j3);
            if (z2) {
                h(i2);
            } else {
                a(i2);
            }
            if (z3 || this.f1633b != null) {
                c();
                this.f1633b.e(0, z3);
            }
        }

        public boolean f(int i2) {
            if (i2 >= 64) {
                c();
                return this.f1633b.f(i2 - 64);
            }
            long j2 = 1 << i2;
            long j3 = this.f1632a;
            boolean z2 = (j3 & j2) != 0;
            long j4 = j3 & (j2 ^ (-1));
            this.f1632a = j4;
            long j5 = j2 - 1;
            this.f1632a = (j4 & j5) | Long.rotateRight((j5 ^ (-1)) & j4, 1);
            a aVar = this.f1633b;
            if (aVar != null) {
                if (aVar.d(0)) {
                    h(63);
                }
                this.f1633b.f(0);
            }
            return z2;
        }

        public void g() {
            this.f1632a = 0L;
            a aVar = this.f1633b;
            if (aVar != null) {
                aVar.g();
            }
        }

        public void h(int i2) {
            if (i2 < 64) {
                this.f1632a |= 1 << i2;
            } else {
                c();
                this.f1633b.h(i2 - 64);
            }
        }

        public String toString() {
            if (this.f1633b == null) {
                return Long.toBinaryString(this.f1632a);
            }
            return this.f1633b.toString() + "xx" + Long.toBinaryString(this.f1632a);
        }
    }

    public interface b {
        void a(View view);

        View b(int i2);

        void c(int i2);

        void d();

        RecyclerView.B e(View view);

        void f(int i2);

        void g(View view);

        void h(View view, int i2, ViewGroup.LayoutParams layoutParams);

        void i(View view, int i2);

        int j(View view);

        int k();
    }

    public c(b bVar) {
        this.f1629a = bVar;
    }

    public void a(View view, int i2, boolean z2) {
        int iK = i2 < 0 ? this.f1629a.k() : h(i2);
        this.f1630b.e(iK, z2);
        if (z2) {
            l(view);
        }
        this.f1629a.i(view, iK);
    }

    public void b(View view, boolean z2) {
        a(view, -1, z2);
    }

    public void c(View view, int i2, ViewGroup.LayoutParams layoutParams, boolean z2) {
        int iK = i2 < 0 ? this.f1629a.k() : h(i2);
        this.f1630b.e(iK, z2);
        if (z2) {
            l(view);
        }
        this.f1629a.h(view, iK, layoutParams);
    }

    public void d(int i2) {
        int iH = h(i2);
        this.f1630b.f(iH);
        this.f1629a.f(iH);
    }

    public View e(int i2, int i3) {
        int size = this.f1631c.size();
        for (int i4 = 0; i4 < size; i4++) {
            View view = (View) this.f1631c.get(i4);
            RecyclerView.B bE = this.f1629a.e(view);
            if (bE.u() == i2 && !bE.A() && !bE.C() && (i3 == -1 || bE.t() == i3)) {
                return view;
            }
        }
        return null;
    }

    public View f(int i2) {
        return this.f1629a.b(h(i2));
    }

    public int g() {
        return this.f1629a.k() - this.f1631c.size();
    }

    public final int h(int i2) {
        if (i2 < 0) {
            return -1;
        }
        int iK = this.f1629a.k();
        int i3 = i2;
        while (i3 < iK) {
            int iB = i2 - (i3 - this.f1630b.b(i3));
            if (iB == 0) {
                while (this.f1630b.d(i3)) {
                    i3++;
                }
                return i3;
            }
            i3 += iB;
        }
        return -1;
    }

    public View i(int i2) {
        return this.f1629a.b(i2);
    }

    public int j() {
        return this.f1629a.k();
    }

    public void k(View view) {
        int iJ = this.f1629a.j(view);
        if (iJ >= 0) {
            this.f1630b.h(iJ);
            l(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    public final void l(View view) {
        this.f1631c.add(view);
        this.f1629a.a(view);
    }

    public int m(View view) {
        int iJ = this.f1629a.j(view);
        if (iJ == -1 || this.f1630b.d(iJ)) {
            return -1;
        }
        return iJ - this.f1630b.b(iJ);
    }

    public boolean n(View view) {
        return this.f1631c.contains(view);
    }

    public void o() {
        this.f1630b.g();
        for (int size = this.f1631c.size() - 1; size >= 0; size--) {
            this.f1629a.g((View) this.f1631c.get(size));
            this.f1631c.remove(size);
        }
        this.f1629a.d();
    }

    public void p(View view) {
        int iJ = this.f1629a.j(view);
        if (iJ < 0) {
            return;
        }
        if (this.f1630b.f(iJ)) {
            t(view);
        }
        this.f1629a.c(iJ);
    }

    public void q(int i2) {
        int iH = h(i2);
        View viewB = this.f1629a.b(iH);
        if (viewB == null) {
            return;
        }
        if (this.f1630b.f(iH)) {
            t(viewB);
        }
        this.f1629a.c(iH);
    }

    public boolean r(View view) {
        int iJ = this.f1629a.j(view);
        if (iJ == -1) {
            t(view);
            return true;
        }
        if (!this.f1630b.d(iJ)) {
            return false;
        }
        this.f1630b.f(iJ);
        t(view);
        this.f1629a.c(iJ);
        return true;
    }

    public void s(View view) {
        int iJ = this.f1629a.j(view);
        if (iJ < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        if (this.f1630b.d(iJ)) {
            this.f1630b.a(iJ);
            t(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public final boolean t(View view) {
        if (!this.f1631c.remove(view)) {
            return false;
        }
        this.f1629a.g(view);
        return true;
    }

    public String toString() {
        return this.f1630b.toString() + ", hidden list:" + this.f1631c.size();
    }
}
