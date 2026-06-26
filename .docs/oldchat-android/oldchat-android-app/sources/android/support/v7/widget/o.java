package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import j.C0252a;

/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0252a f1736a = new C0252a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j.e f1737b = new j.e();

    public static class a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static j.h f1738d = new j.i(20);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1739a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView.k.c f1740b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public RecyclerView.k.c f1741c;

        public static void a() {
            while (f1738d.b() != null) {
            }
        }

        public static a b() {
            a aVar = (a) f1738d.b();
            return aVar == null ? new a() : aVar;
        }

        public static void c(a aVar) {
            aVar.f1739a = 0;
            aVar.f1740b = null;
            aVar.f1741c = null;
            f1738d.a(aVar);
        }
    }

    public interface b {
        void a(RecyclerView.B b2);

        void b(RecyclerView.B b2, RecyclerView.k.c cVar, RecyclerView.k.c cVar2);

        void c(RecyclerView.B b2, RecyclerView.k.c cVar, RecyclerView.k.c cVar2);

        void d(RecyclerView.B b2, RecyclerView.k.c cVar, RecyclerView.k.c cVar2);
    }

    public void a(RecyclerView.B b2, RecyclerView.k.c cVar) {
        a aVarB = (a) this.f1736a.get(b2);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1736a.put(b2, aVarB);
        }
        aVarB.f1739a |= 2;
        aVarB.f1740b = cVar;
    }

    public void b(RecyclerView.B b2) {
        a aVarB = (a) this.f1736a.get(b2);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1736a.put(b2, aVarB);
        }
        aVarB.f1739a |= 1;
    }

    public void c(long j2, RecyclerView.B b2) {
        this.f1737b.h(j2, b2);
    }

    public void d(RecyclerView.B b2, RecyclerView.k.c cVar) {
        a aVarB = (a) this.f1736a.get(b2);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1736a.put(b2, aVarB);
        }
        aVarB.f1741c = cVar;
        aVarB.f1739a |= 8;
    }

    public void e(RecyclerView.B b2, RecyclerView.k.c cVar) {
        a aVarB = (a) this.f1736a.get(b2);
        if (aVarB == null) {
            aVarB = a.b();
            this.f1736a.put(b2, aVarB);
        }
        aVarB.f1740b = cVar;
        aVarB.f1739a |= 4;
    }

    public void f() {
        this.f1736a.clear();
        this.f1737b.a();
    }

    public RecyclerView.B g(long j2) {
        return (RecyclerView.B) this.f1737b.e(j2);
    }

    public boolean h(RecyclerView.B b2) {
        a aVar = (a) this.f1736a.get(b2);
        return (aVar == null || (aVar.f1739a & 1) == 0) ? false : true;
    }

    public boolean i(RecyclerView.B b2) {
        a aVar = (a) this.f1736a.get(b2);
        return (aVar == null || (aVar.f1739a & 4) == 0) ? false : true;
    }

    public void j() {
        a.a();
    }

    public void k(RecyclerView.B b2) {
        p(b2);
    }

    public final RecyclerView.k.c l(RecyclerView.B b2, int i2) {
        a aVar;
        RecyclerView.k.c cVar;
        int iE = this.f1736a.e(b2);
        if (iE >= 0 && (aVar = (a) this.f1736a.k(iE)) != null) {
            int i3 = aVar.f1739a;
            if ((i3 & i2) != 0) {
                int i4 = (i2 ^ (-1)) & i3;
                aVar.f1739a = i4;
                if (i2 == 4) {
                    cVar = aVar.f1740b;
                } else {
                    if (i2 != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    cVar = aVar.f1741c;
                }
                if ((i4 & 12) == 0) {
                    this.f1736a.i(iE);
                    a.c(aVar);
                }
                return cVar;
            }
        }
        return null;
    }

    public RecyclerView.k.c m(RecyclerView.B b2) {
        return l(b2, 8);
    }

    public RecyclerView.k.c n(RecyclerView.B b2) {
        return l(b2, 4);
    }

    public void o(b bVar) {
        for (int size = this.f1736a.size() - 1; size >= 0; size--) {
            RecyclerView.B b2 = (RecyclerView.B) this.f1736a.h(size);
            a aVar = (a) this.f1736a.i(size);
            int i2 = aVar.f1739a;
            if ((i2 & 3) == 3) {
                bVar.a(b2);
            } else if ((i2 & 1) != 0) {
                RecyclerView.k.c cVar = aVar.f1740b;
                if (cVar == null) {
                    bVar.a(b2);
                } else {
                    bVar.d(b2, cVar, aVar.f1741c);
                }
            } else if ((i2 & 14) == 14) {
                bVar.b(b2, aVar.f1740b, aVar.f1741c);
            } else if ((i2 & 12) == 12) {
                bVar.c(b2, aVar.f1740b, aVar.f1741c);
            } else if ((i2 & 4) != 0) {
                bVar.d(b2, aVar.f1740b, null);
            } else if ((i2 & 8) != 0) {
                bVar.b(b2, aVar.f1740b, aVar.f1741c);
            }
            a.c(aVar);
        }
    }

    public void p(RecyclerView.B b2) {
        a aVar = (a) this.f1736a.get(b2);
        if (aVar == null) {
            return;
        }
        aVar.f1739a &= -2;
    }

    public void q(RecyclerView.B b2) {
        int iJ = this.f1737b.j() - 1;
        while (true) {
            if (iJ < 0) {
                break;
            }
            if (b2 == this.f1737b.k(iJ)) {
                this.f1737b.i(iJ);
                break;
            }
            iJ--;
        }
        a aVar = (a) this.f1736a.remove(b2);
        if (aVar != null) {
            a.c(aVar);
        }
    }
}
