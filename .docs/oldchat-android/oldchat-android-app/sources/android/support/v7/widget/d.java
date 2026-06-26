package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import b.AbstractC0096a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import k.AbstractC0285k0;
import k.F1;
import k.T1;

/* JADX INFO: loaded from: classes.dex */
public class d extends m {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList f1634h = new ArrayList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList f1635i = new ArrayList();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList f1636j = new ArrayList();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ArrayList f1637k = new ArrayList();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ArrayList f1638l = new ArrayList();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ArrayList f1639m = new ArrayList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ArrayList f1640n = new ArrayList();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ArrayList f1641o = new ArrayList();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList f1642p = new ArrayList();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ArrayList f1643q = new ArrayList();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ArrayList f1644r = new ArrayList();

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ArrayList f1645a;

        public a(ArrayList arrayList) {
            this.f1645a = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (j jVar : this.f1645a) {
                d.this.T(jVar.f1675a, jVar.f1676b, jVar.f1677c, jVar.f1678d, jVar.f1679e);
            }
            this.f1645a.clear();
            d.this.f1639m.remove(this.f1645a);
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ArrayList f1647a;

        public b(ArrayList arrayList) {
            this.f1647a = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f1647a.iterator();
            while (it.hasNext()) {
                d.this.S((i) it.next());
            }
            this.f1647a.clear();
            d.this.f1640n.remove(this.f1647a);
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ArrayList f1649a;

        public c(ArrayList arrayList) {
            this.f1649a = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f1649a.iterator();
            while (it.hasNext()) {
                d.this.R((RecyclerView.B) it.next());
            }
            this.f1649a.clear();
            d.this.f1638l.remove(this.f1649a);
        }
    }

    /* JADX INFO: renamed from: android.support.v7.widget.d$d, reason: collision with other inner class name */
    public class C0016d extends k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.B f1651a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ F1 f1652b;

        public C0016d(RecyclerView.B b2, F1 f1) {
            this.f1651a = b2;
            this.f1652b = f1;
        }

        @Override // k.T1
        public void a(View view) {
            this.f1652b.f(null);
            AbstractC0285k0.K(view, 1.0f);
            d.this.H(this.f1651a);
            d.this.f1643q.remove(this.f1651a);
            d.this.W();
        }

        @Override // k.T1
        public void b(View view) {
            d.this.I(this.f1651a);
        }
    }

    public class e extends k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.B f1654a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ F1 f1655b;

        public e(RecyclerView.B b2, F1 f1) {
            this.f1654a = b2;
            this.f1655b = f1;
        }

        @Override // k.T1
        public void a(View view) {
            this.f1655b.f(null);
            d.this.B(this.f1654a);
            d.this.f1641o.remove(this.f1654a);
            d.this.W();
        }

        @Override // k.T1
        public void b(View view) {
            d.this.C(this.f1654a);
        }

        @Override // android.support.v7.widget.d.k, k.T1
        public void c(View view) {
            AbstractC0285k0.K(view, 1.0f);
        }
    }

    public class f extends k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.B f1657a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f1658b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f1659c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ F1 f1660d;

        public f(RecyclerView.B b2, int i2, int i3, F1 f1) {
            this.f1657a = b2;
            this.f1658b = i2;
            this.f1659c = i3;
            this.f1660d = f1;
        }

        @Override // k.T1
        public void a(View view) {
            this.f1660d.f(null);
            d.this.F(this.f1657a);
            d.this.f1642p.remove(this.f1657a);
            d.this.W();
        }

        @Override // k.T1
        public void b(View view) {
            d.this.G(this.f1657a);
        }

        @Override // android.support.v7.widget.d.k, k.T1
        public void c(View view) {
            if (this.f1658b != 0) {
                AbstractC0285k0.W(view, 0.0f);
            }
            if (this.f1659c != 0) {
                AbstractC0285k0.X(view, 0.0f);
            }
        }
    }

    public class g extends k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ i f1662a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ F1 f1663b;

        public g(i iVar, F1 f1) {
            this.f1662a = iVar;
            this.f1663b = f1;
        }

        @Override // k.T1
        public void a(View view) {
            this.f1663b.f(null);
            AbstractC0285k0.K(view, 1.0f);
            AbstractC0285k0.W(view, 0.0f);
            AbstractC0285k0.X(view, 0.0f);
            d.this.D(this.f1662a.f1669a, true);
            d.this.f1644r.remove(this.f1662a.f1669a);
            d.this.W();
        }

        @Override // k.T1
        public void b(View view) {
            d.this.E(this.f1662a.f1669a, true);
        }
    }

    public class h extends k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ i f1665a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ F1 f1666b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f1667c;

        public h(i iVar, F1 f1, View view) {
            this.f1665a = iVar;
            this.f1666b = f1;
            this.f1667c = view;
        }

        @Override // k.T1
        public void a(View view) {
            this.f1666b.f(null);
            AbstractC0285k0.K(this.f1667c, 1.0f);
            AbstractC0285k0.W(this.f1667c, 0.0f);
            AbstractC0285k0.X(this.f1667c, 0.0f);
            d.this.D(this.f1665a.f1670b, false);
            d.this.f1644r.remove(this.f1665a.f1670b);
            d.this.W();
        }

        @Override // k.T1
        public void b(View view) {
            d.this.E(this.f1665a.f1670b, false);
        }
    }

    public static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.B f1675a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1676b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1677c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1678d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1679e;

        public j(RecyclerView.B b2, int i2, int i3, int i4, int i5) {
            this.f1675a = b2;
            this.f1676b = i2;
            this.f1677c = i3;
            this.f1678d = i4;
            this.f1679e = i5;
        }
    }

    public static class k implements T1 {
        @Override // k.T1
        public void c(View view) {
        }
    }

    @Override // android.support.v7.widget.m
    public boolean A(RecyclerView.B b2) {
        a0(b2);
        this.f1634h.add(b2);
        return true;
    }

    public void R(RecyclerView.B b2) {
        F1 f1A = AbstractC0285k0.a(b2.f1354a);
        this.f1641o.add(b2);
        f1A.a(1.0f).d(l()).f(new e(b2, f1A)).i();
    }

    public void S(i iVar) {
        RecyclerView.B b2 = iVar.f1669a;
        View view = b2 == null ? null : b2.f1354a;
        RecyclerView.B b3 = iVar.f1670b;
        View view2 = b3 != null ? b3.f1354a : null;
        if (view != null) {
            F1 f1D = AbstractC0285k0.a(view).d(m());
            this.f1644r.add(iVar.f1669a);
            f1D.j(iVar.f1673e - iVar.f1671c);
            f1D.k(iVar.f1674f - iVar.f1672d);
            f1D.a(0.0f).f(new g(iVar, f1D)).i();
        }
        if (view2 != null) {
            F1 f1A = AbstractC0285k0.a(view2);
            this.f1644r.add(iVar.f1670b);
            f1A.j(0.0f).k(0.0f).d(m()).a(1.0f).f(new h(iVar, f1A, view2)).i();
        }
    }

    public void T(RecyclerView.B b2, int i2, int i3, int i4, int i5) {
        View view = b2.f1354a;
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        if (i6 != 0) {
            AbstractC0285k0.a(view).j(0.0f);
        }
        if (i7 != 0) {
            AbstractC0285k0.a(view).k(0.0f);
        }
        F1 f1A = AbstractC0285k0.a(view);
        this.f1642p.add(b2);
        f1A.d(n()).f(new f(b2, i6, i7, f1A)).i();
    }

    public final void U(RecyclerView.B b2) {
        F1 f1A = AbstractC0285k0.a(b2.f1354a);
        this.f1643q.add(b2);
        f1A.d(o()).a(0.0f).f(new C0016d(b2, f1A)).i();
    }

    public void V(List list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            AbstractC0285k0.a(((RecyclerView.B) list.get(size)).f1354a).b();
        }
    }

    public void W() {
        if (p()) {
            return;
        }
        i();
    }

    public final void X(List list, RecyclerView.B b2) {
        for (int size = list.size() - 1; size >= 0; size--) {
            i iVar = (i) list.get(size);
            if (Z(iVar, b2) && iVar.f1669a == null && iVar.f1670b == null) {
                list.remove(iVar);
            }
        }
    }

    public final void Y(i iVar) {
        RecyclerView.B b2 = iVar.f1669a;
        if (b2 != null) {
            Z(iVar, b2);
        }
        RecyclerView.B b3 = iVar.f1670b;
        if (b3 != null) {
            Z(iVar, b3);
        }
    }

    public final boolean Z(i iVar, RecyclerView.B b2) {
        boolean z2 = false;
        if (iVar.f1670b == b2) {
            iVar.f1670b = null;
        } else {
            if (iVar.f1669a != b2) {
                return false;
            }
            iVar.f1669a = null;
            z2 = true;
        }
        AbstractC0285k0.K(b2.f1354a, 1.0f);
        AbstractC0285k0.W(b2.f1354a, 0.0f);
        AbstractC0285k0.X(b2.f1354a, 0.0f);
        D(b2, z2);
        return true;
    }

    public final void a0(RecyclerView.B b2) {
        AbstractC0096a.a(b2.f1354a);
        j(b2);
    }

    @Override // android.support.v7.widget.RecyclerView.k
    public boolean g(RecyclerView.B b2, List list) {
        return !list.isEmpty() || super.g(b2, list);
    }

    @Override // android.support.v7.widget.RecyclerView.k
    public void j(RecyclerView.B b2) {
        View view = b2.f1354a;
        AbstractC0285k0.a(view).b();
        int size = this.f1636j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (((j) this.f1636j.get(size)).f1675a == b2) {
                AbstractC0285k0.X(view, 0.0f);
                AbstractC0285k0.W(view, 0.0f);
                F(b2);
                this.f1636j.remove(size);
            }
        }
        X(this.f1637k, b2);
        if (this.f1634h.remove(b2)) {
            AbstractC0285k0.K(view, 1.0f);
            H(b2);
        }
        if (this.f1635i.remove(b2)) {
            AbstractC0285k0.K(view, 1.0f);
            B(b2);
        }
        for (int size2 = this.f1640n.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList = (ArrayList) this.f1640n.get(size2);
            X(arrayList, b2);
            if (arrayList.isEmpty()) {
                this.f1640n.remove(size2);
            }
        }
        for (int size3 = this.f1639m.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList2 = (ArrayList) this.f1639m.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (((j) arrayList2.get(size4)).f1675a == b2) {
                    AbstractC0285k0.X(view, 0.0f);
                    AbstractC0285k0.W(view, 0.0f);
                    F(b2);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.f1639m.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.f1638l.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList3 = (ArrayList) this.f1638l.get(size5);
            if (arrayList3.remove(b2)) {
                AbstractC0285k0.K(view, 1.0f);
                B(b2);
                if (arrayList3.isEmpty()) {
                    this.f1638l.remove(size5);
                }
            }
        }
        this.f1643q.remove(b2);
        this.f1641o.remove(b2);
        this.f1644r.remove(b2);
        this.f1642p.remove(b2);
        W();
    }

    @Override // android.support.v7.widget.RecyclerView.k
    public void k() {
        int size = this.f1636j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            j jVar = (j) this.f1636j.get(size);
            View view = jVar.f1675a.f1354a;
            AbstractC0285k0.X(view, 0.0f);
            AbstractC0285k0.W(view, 0.0f);
            F(jVar.f1675a);
            this.f1636j.remove(size);
        }
        for (int size2 = this.f1634h.size() - 1; size2 >= 0; size2--) {
            H((RecyclerView.B) this.f1634h.get(size2));
            this.f1634h.remove(size2);
        }
        int size3 = this.f1635i.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.B b2 = (RecyclerView.B) this.f1635i.get(size3);
            AbstractC0285k0.K(b2.f1354a, 1.0f);
            B(b2);
            this.f1635i.remove(size3);
        }
        for (int size4 = this.f1637k.size() - 1; size4 >= 0; size4--) {
            Y((i) this.f1637k.get(size4));
        }
        this.f1637k.clear();
        if (p()) {
            for (int size5 = this.f1639m.size() - 1; size5 >= 0; size5--) {
                ArrayList arrayList = (ArrayList) this.f1639m.get(size5);
                for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                    j jVar2 = (j) arrayList.get(size6);
                    View view2 = jVar2.f1675a.f1354a;
                    AbstractC0285k0.X(view2, 0.0f);
                    AbstractC0285k0.W(view2, 0.0f);
                    F(jVar2.f1675a);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.f1639m.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.f1638l.size() - 1; size7 >= 0; size7--) {
                ArrayList arrayList2 = (ArrayList) this.f1638l.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    RecyclerView.B b3 = (RecyclerView.B) arrayList2.get(size8);
                    AbstractC0285k0.K(b3.f1354a, 1.0f);
                    B(b3);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.f1638l.remove(arrayList2);
                    }
                }
            }
            for (int size9 = this.f1640n.size() - 1; size9 >= 0; size9--) {
                ArrayList arrayList3 = (ArrayList) this.f1640n.get(size9);
                for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                    Y((i) arrayList3.get(size10));
                    if (arrayList3.isEmpty()) {
                        this.f1640n.remove(arrayList3);
                    }
                }
            }
            V(this.f1643q);
            V(this.f1642p);
            V(this.f1641o);
            V(this.f1644r);
            i();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.k
    public boolean p() {
        return (this.f1635i.isEmpty() && this.f1637k.isEmpty() && this.f1636j.isEmpty() && this.f1634h.isEmpty() && this.f1642p.isEmpty() && this.f1643q.isEmpty() && this.f1641o.isEmpty() && this.f1644r.isEmpty() && this.f1639m.isEmpty() && this.f1638l.isEmpty() && this.f1640n.isEmpty()) ? false : true;
    }

    @Override // android.support.v7.widget.RecyclerView.k
    public void v() {
        boolean zIsEmpty = this.f1634h.isEmpty();
        boolean zIsEmpty2 = this.f1636j.isEmpty();
        boolean zIsEmpty3 = this.f1637k.isEmpty();
        boolean zIsEmpty4 = this.f1635i.isEmpty();
        if (zIsEmpty && zIsEmpty2 && zIsEmpty4 && zIsEmpty3) {
            return;
        }
        Iterator it = this.f1634h.iterator();
        while (it.hasNext()) {
            U((RecyclerView.B) it.next());
        }
        this.f1634h.clear();
        if (!zIsEmpty2) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.f1636j);
            this.f1639m.add(arrayList);
            this.f1636j.clear();
            a aVar = new a(arrayList);
            if (zIsEmpty) {
                aVar.run();
            } else {
                AbstractC0285k0.G(((j) arrayList.get(0)).f1675a.f1354a, aVar, o());
            }
        }
        if (!zIsEmpty3) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(this.f1637k);
            this.f1640n.add(arrayList2);
            this.f1637k.clear();
            b bVar = new b(arrayList2);
            if (zIsEmpty) {
                bVar.run();
            } else {
                AbstractC0285k0.G(((i) arrayList2.get(0)).f1669a.f1354a, bVar, o());
            }
        }
        if (zIsEmpty4) {
            return;
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(this.f1635i);
        this.f1638l.add(arrayList3);
        this.f1635i.clear();
        c cVar = new c(arrayList3);
        if (zIsEmpty && zIsEmpty2 && zIsEmpty3) {
            cVar.run();
        } else {
            AbstractC0285k0.G(((RecyclerView.B) arrayList3.get(0)).f1354a, cVar, (!zIsEmpty ? o() : 0L) + Math.max(!zIsEmpty2 ? n() : 0L, zIsEmpty3 ? 0L : m()));
        }
    }

    @Override // android.support.v7.widget.m
    public boolean x(RecyclerView.B b2) {
        a0(b2);
        AbstractC0285k0.K(b2.f1354a, 0.0f);
        this.f1635i.add(b2);
        return true;
    }

    @Override // android.support.v7.widget.m
    public boolean y(RecyclerView.B b2, RecyclerView.B b3, int i2, int i3, int i4, int i5) {
        if (b2 == b3) {
            return z(b2, i2, i3, i4, i5);
        }
        float fR = AbstractC0285k0.r(b2.f1354a);
        float fS = AbstractC0285k0.s(b2.f1354a);
        float fE = AbstractC0285k0.e(b2.f1354a);
        a0(b2);
        int i6 = (int) ((i4 - i2) - fR);
        int i7 = (int) ((i5 - i3) - fS);
        AbstractC0285k0.W(b2.f1354a, fR);
        AbstractC0285k0.X(b2.f1354a, fS);
        AbstractC0285k0.K(b2.f1354a, fE);
        if (b3 != null) {
            a0(b3);
            AbstractC0285k0.W(b3.f1354a, -i6);
            AbstractC0285k0.X(b3.f1354a, -i7);
            AbstractC0285k0.K(b3.f1354a, 0.0f);
        }
        this.f1637k.add(new i(b2, b3, i2, i3, i4, i5));
        return true;
    }

    @Override // android.support.v7.widget.m
    public boolean z(RecyclerView.B b2, int i2, int i3, int i4, int i5) {
        View view = b2.f1354a;
        int iR = (int) (i2 + AbstractC0285k0.r(view));
        int iS = (int) (i3 + AbstractC0285k0.s(b2.f1354a));
        a0(b2);
        int i6 = i4 - iR;
        int i7 = i5 - iS;
        if (i6 == 0 && i7 == 0) {
            F(b2);
            return false;
        }
        if (i6 != 0) {
            AbstractC0285k0.W(view, -i6);
        }
        if (i7 != 0) {
            AbstractC0285k0.X(view, -i7);
        }
        this.f1636j.add(new j(b2, iR, iS, i4, i5));
        return true;
    }

    public static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.B f1669a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RecyclerView.B f1670b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1671c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1672d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1673e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1674f;

        public i(RecyclerView.B b2, RecyclerView.B b3) {
            this.f1669a = b2;
            this.f1670b = b3;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.f1669a + ", newHolder=" + this.f1670b + ", fromX=" + this.f1671c + ", fromY=" + this.f1672d + ", toX=" + this.f1673e + ", toY=" + this.f1674f + '}';
        }

        public i(RecyclerView.B b2, RecyclerView.B b3, int i2, int i3, int i4, int i5) {
            this(b2, b3);
            this.f1671c = i2;
            this.f1672d = i3;
            this.f1673e = i4;
            this.f1674f = i5;
        }
    }
}
