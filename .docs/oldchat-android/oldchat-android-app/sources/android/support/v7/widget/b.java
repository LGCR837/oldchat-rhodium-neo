package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.h;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class b implements h.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public j.h f1617a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f1618b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f1619c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f1620d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Runnable f1621e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f1622f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final h f1623g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1624h;

    public interface a {
        void a(C0015b c0015b);

        void b(int i2, int i3);

        RecyclerView.B c(int i2);

        void d(int i2, int i3);

        void e(int i2, int i3);

        void f(C0015b c0015b);

        void g(int i2, int i3);

        void h(int i2, int i3, Object obj);
    }

    /* JADX INFO: renamed from: android.support.v7.widget.b$b, reason: collision with other inner class name */
    public static class C0015b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1625a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1626b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Object f1627c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1628d;

        public C0015b(int i2, int i3, int i4, Object obj) {
            this.f1625a = i2;
            this.f1626b = i3;
            this.f1628d = i4;
            this.f1627c = obj;
        }

        public String a() {
            int i2 = this.f1625a;
            return i2 != 1 ? i2 != 2 ? i2 != 4 ? i2 != 8 ? "??" : "mv" : "up" : "rm" : "add";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C0015b c0015b = (C0015b) obj;
            int i2 = this.f1625a;
            if (i2 != c0015b.f1625a) {
                return false;
            }
            if (i2 == 8 && Math.abs(this.f1628d - this.f1626b) == 1 && this.f1628d == c0015b.f1626b && this.f1626b == c0015b.f1628d) {
                return true;
            }
            if (this.f1628d != c0015b.f1628d || this.f1626b != c0015b.f1626b) {
                return false;
            }
            Object obj2 = this.f1627c;
            if (obj2 != null) {
                if (!obj2.equals(c0015b.f1627c)) {
                    return false;
                }
            } else if (c0015b.f1627c != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.f1625a * 31) + this.f1626b) * 31) + this.f1628d;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + a() + ",s:" + this.f1626b + "c:" + this.f1628d + ",p:" + this.f1627c + "]";
        }
    }

    public b(a aVar) {
        this(aVar, false);
    }

    @Override // android.support.v7.widget.h.a
    public void a(C0015b c0015b) {
        if (this.f1622f) {
            return;
        }
        c0015b.f1627c = null;
        this.f1617a.a(c0015b);
    }

    @Override // android.support.v7.widget.h.a
    public C0015b b(int i2, int i3, int i4, Object obj) {
        C0015b c0015b = (C0015b) this.f1617a.b();
        if (c0015b == null) {
            return new C0015b(i2, i3, i4, obj);
        }
        c0015b.f1625a = i2;
        c0015b.f1626b = i3;
        c0015b.f1628d = i4;
        c0015b.f1627c = obj;
        return c0015b;
    }

    public final void c(C0015b c0015b) {
        u(c0015b);
    }

    public final void d(C0015b c0015b) {
        u(c0015b);
    }

    public int e(int i2) {
        int size = this.f1618b.size();
        for (int i3 = 0; i3 < size; i3++) {
            C0015b c0015b = (C0015b) this.f1618b.get(i3);
            int i4 = c0015b.f1625a;
            if (i4 != 1) {
                if (i4 == 2) {
                    int i5 = c0015b.f1626b;
                    if (i5 <= i2) {
                        int i6 = c0015b.f1628d;
                        if (i5 + i6 > i2) {
                            return -1;
                        }
                        i2 -= i6;
                    } else {
                        continue;
                    }
                } else if (i4 == 8) {
                    int i7 = c0015b.f1626b;
                    if (i7 == i2) {
                        i2 = c0015b.f1628d;
                    } else {
                        if (i7 < i2) {
                            i2--;
                        }
                        if (c0015b.f1628d <= i2) {
                            i2++;
                        }
                    }
                }
            } else if (c0015b.f1626b <= i2) {
                i2 += c0015b.f1628d;
            }
        }
        return i2;
    }

    public final void f(C0015b c0015b) {
        boolean z2;
        byte b2;
        int i2 = c0015b.f1626b;
        int i3 = c0015b.f1628d + i2;
        byte b3 = -1;
        int i4 = i2;
        int i5 = 0;
        while (i4 < i3) {
            if (this.f1620d.c(i4) != null || h(i4)) {
                if (b3 == 0) {
                    k(b(2, i2, i5, null));
                    z2 = true;
                } else {
                    z2 = false;
                }
                b2 = 1;
            } else {
                if (b3 == 1) {
                    u(b(2, i2, i5, null));
                    z2 = true;
                } else {
                    z2 = false;
                }
                b2 = 0;
            }
            if (z2) {
                i4 -= i5;
                i3 -= i5;
                i5 = 1;
            } else {
                i5++;
            }
            i4++;
            b3 = b2;
        }
        if (i5 != c0015b.f1628d) {
            a(c0015b);
            c0015b = b(2, i2, i5, null);
        }
        if (b3 == 0) {
            k(c0015b);
        } else {
            u(c0015b);
        }
    }

    public final void g(C0015b c0015b) {
        int i2 = c0015b.f1626b;
        int i3 = c0015b.f1628d + i2;
        int i4 = i2;
        byte b2 = -1;
        int i5 = 0;
        while (i2 < i3) {
            if (this.f1620d.c(i2) != null || h(i2)) {
                if (b2 == 0) {
                    k(b(4, i4, i5, c0015b.f1627c));
                    i4 = i2;
                    i5 = 0;
                }
                b2 = 1;
            } else {
                if (b2 == 1) {
                    u(b(4, i4, i5, c0015b.f1627c));
                    i4 = i2;
                    i5 = 0;
                }
                b2 = 0;
            }
            i5++;
            i2++;
        }
        if (i5 != c0015b.f1628d) {
            Object obj = c0015b.f1627c;
            a(c0015b);
            c0015b = b(4, i4, i5, obj);
        }
        if (b2 == 0) {
            k(c0015b);
        } else {
            u(c0015b);
        }
    }

    public final boolean h(int i2) {
        int size = this.f1619c.size();
        for (int i3 = 0; i3 < size; i3++) {
            C0015b c0015b = (C0015b) this.f1619c.get(i3);
            int i4 = c0015b.f1625a;
            if (i4 == 8) {
                if (n(c0015b.f1628d, i3 + 1) == i2) {
                    return true;
                }
            } else if (i4 == 1) {
                int i5 = c0015b.f1626b;
                int i6 = c0015b.f1628d + i5;
                while (i5 < i6) {
                    if (n(i5, i3 + 1) == i2) {
                        return true;
                    }
                    i5++;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    public void i() {
        int size = this.f1619c.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f1620d.f((C0015b) this.f1619c.get(i2));
        }
        w(this.f1619c);
        this.f1624h = 0;
    }

    public void j() {
        i();
        int size = this.f1618b.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0015b c0015b = (C0015b) this.f1618b.get(i2);
            int i3 = c0015b.f1625a;
            if (i3 == 1) {
                this.f1620d.f(c0015b);
                this.f1620d.g(c0015b.f1626b, c0015b.f1628d);
            } else if (i3 == 2) {
                this.f1620d.f(c0015b);
                this.f1620d.d(c0015b.f1626b, c0015b.f1628d);
            } else if (i3 == 4) {
                this.f1620d.f(c0015b);
                this.f1620d.h(c0015b.f1626b, c0015b.f1628d, c0015b.f1627c);
            } else if (i3 == 8) {
                this.f1620d.f(c0015b);
                this.f1620d.b(c0015b.f1626b, c0015b.f1628d);
            }
            Runnable runnable = this.f1621e;
            if (runnable != null) {
                runnable.run();
            }
        }
        w(this.f1618b);
        this.f1624h = 0;
    }

    public final void k(C0015b c0015b) {
        int i2;
        int i3 = c0015b.f1625a;
        if (i3 == 1 || i3 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iY = y(c0015b.f1626b, i3);
        int i4 = c0015b.f1626b;
        int i5 = c0015b.f1625a;
        if (i5 == 2) {
            i2 = 0;
        } else {
            if (i5 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + c0015b);
            }
            i2 = 1;
        }
        int i6 = 1;
        for (int i7 = 1; i7 < c0015b.f1628d; i7++) {
            int iY2 = y(c0015b.f1626b + (i2 * i7), c0015b.f1625a);
            int i8 = c0015b.f1625a;
            if (i8 == 2 ? iY2 != iY : !(i8 == 4 && iY2 == iY + 1)) {
                C0015b c0015bB = b(i8, iY, i6, c0015b.f1627c);
                l(c0015bB, i4);
                a(c0015bB);
                if (c0015b.f1625a == 4) {
                    i4 += i6;
                }
                iY = iY2;
                i6 = 1;
            } else {
                i6++;
            }
        }
        Object obj = c0015b.f1627c;
        a(c0015b);
        if (i6 > 0) {
            C0015b c0015bB2 = b(c0015b.f1625a, iY, i6, obj);
            l(c0015bB2, i4);
            a(c0015bB2);
        }
    }

    public void l(C0015b c0015b, int i2) {
        this.f1620d.a(c0015b);
        int i3 = c0015b.f1625a;
        if (i3 == 2) {
            this.f1620d.d(i2, c0015b.f1628d);
        } else {
            if (i3 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            this.f1620d.h(i2, c0015b.f1628d, c0015b.f1627c);
        }
    }

    public int m(int i2) {
        return n(i2, 0);
    }

    public int n(int i2, int i3) {
        int size = this.f1619c.size();
        while (i3 < size) {
            C0015b c0015b = (C0015b) this.f1619c.get(i3);
            int i4 = c0015b.f1625a;
            if (i4 == 8) {
                int i5 = c0015b.f1626b;
                if (i5 == i2) {
                    i2 = c0015b.f1628d;
                } else {
                    if (i5 < i2) {
                        i2--;
                    }
                    if (c0015b.f1628d <= i2) {
                        i2++;
                    }
                }
            } else {
                int i6 = c0015b.f1626b;
                if (i6 > i2) {
                    continue;
                } else if (i4 == 2) {
                    int i7 = c0015b.f1628d;
                    if (i2 < i6 + i7) {
                        return -1;
                    }
                    i2 -= i7;
                } else if (i4 == 1) {
                    i2 += c0015b.f1628d;
                }
            }
            i3++;
        }
        return i2;
    }

    public boolean o(int i2) {
        return (i2 & this.f1624h) != 0;
    }

    public boolean p() {
        return this.f1618b.size() > 0;
    }

    public boolean q() {
        return (this.f1619c.isEmpty() || this.f1618b.isEmpty()) ? false : true;
    }

    public boolean r(int i2, int i3, Object obj) {
        if (i3 < 1) {
            return false;
        }
        this.f1618b.add(b(4, i2, i3, obj));
        this.f1624h |= 4;
        return this.f1618b.size() == 1;
    }

    public boolean s(int i2, int i3) {
        if (i3 < 1) {
            return false;
        }
        this.f1618b.add(b(1, i2, i3, null));
        this.f1624h |= 1;
        return this.f1618b.size() == 1;
    }

    public boolean t(int i2, int i3, int i4) {
        if (i2 == i3) {
            return false;
        }
        if (i4 != 1) {
            throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
        }
        this.f1618b.add(b(8, i2, i3, null));
        this.f1624h |= 8;
        return this.f1618b.size() == 1;
    }

    public final void u(C0015b c0015b) {
        this.f1619c.add(c0015b);
        int i2 = c0015b.f1625a;
        if (i2 == 1) {
            this.f1620d.g(c0015b.f1626b, c0015b.f1628d);
            return;
        }
        if (i2 == 2) {
            this.f1620d.e(c0015b.f1626b, c0015b.f1628d);
            return;
        }
        if (i2 == 4) {
            this.f1620d.h(c0015b.f1626b, c0015b.f1628d, c0015b.f1627c);
        } else {
            if (i2 == 8) {
                this.f1620d.b(c0015b.f1626b, c0015b.f1628d);
                return;
            }
            throw new IllegalArgumentException("Unknown update op type for " + c0015b);
        }
    }

    public void v() {
        this.f1623g.b(this.f1618b);
        int size = this.f1618b.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0015b c0015b = (C0015b) this.f1618b.get(i2);
            int i3 = c0015b.f1625a;
            if (i3 == 1) {
                c(c0015b);
            } else if (i3 == 2) {
                f(c0015b);
            } else if (i3 == 4) {
                g(c0015b);
            } else if (i3 == 8) {
                d(c0015b);
            }
            Runnable runnable = this.f1621e;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.f1618b.clear();
    }

    public void w(List list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            a((C0015b) list.get(i2));
        }
        list.clear();
    }

    public void x() {
        w(this.f1618b);
        w(this.f1619c);
        this.f1624h = 0;
    }

    public final int y(int i2, int i3) {
        int i4;
        int i5;
        for (int size = this.f1619c.size() - 1; size >= 0; size--) {
            C0015b c0015b = (C0015b) this.f1619c.get(size);
            int i6 = c0015b.f1625a;
            if (i6 == 8) {
                int i7 = c0015b.f1626b;
                int i8 = c0015b.f1628d;
                if (i7 < i8) {
                    i5 = i7;
                    i4 = i8;
                } else {
                    i4 = i7;
                    i5 = i8;
                }
                if (i2 < i5 || i2 > i4) {
                    if (i2 < i7) {
                        if (i3 == 1) {
                            c0015b.f1626b = i7 + 1;
                            c0015b.f1628d = i8 + 1;
                        } else if (i3 == 2) {
                            c0015b.f1626b = i7 - 1;
                            c0015b.f1628d = i8 - 1;
                        }
                    }
                } else if (i5 == i7) {
                    if (i3 == 1) {
                        c0015b.f1628d = i8 + 1;
                    } else if (i3 == 2) {
                        c0015b.f1628d = i8 - 1;
                    }
                    i2++;
                } else {
                    if (i3 == 1) {
                        c0015b.f1626b = i7 + 1;
                    } else if (i3 == 2) {
                        c0015b.f1626b = i7 - 1;
                    }
                    i2--;
                }
            } else {
                int i9 = c0015b.f1626b;
                if (i9 <= i2) {
                    if (i6 == 1) {
                        i2 -= c0015b.f1628d;
                    } else if (i6 == 2) {
                        i2 += c0015b.f1628d;
                    }
                } else if (i3 == 1) {
                    c0015b.f1626b = i9 + 1;
                } else if (i3 == 2) {
                    c0015b.f1626b = i9 - 1;
                }
            }
        }
        for (int size2 = this.f1619c.size() - 1; size2 >= 0; size2--) {
            C0015b c0015b2 = (C0015b) this.f1619c.get(size2);
            if (c0015b2.f1625a == 8) {
                int i10 = c0015b2.f1628d;
                if (i10 == c0015b2.f1626b || i10 < 0) {
                    this.f1619c.remove(size2);
                    a(c0015b2);
                }
            } else if (c0015b2.f1628d <= 0) {
                this.f1619c.remove(size2);
                a(c0015b2);
            }
        }
        return i2;
    }

    public b(a aVar, boolean z2) {
        this.f1617a = new j.i(30);
        this.f1618b = new ArrayList();
        this.f1619c = new ArrayList();
        this.f1624h = 0;
        this.f1620d = aVar;
        this.f1622f = z2;
        this.f1623g = new h(this);
    }
}
