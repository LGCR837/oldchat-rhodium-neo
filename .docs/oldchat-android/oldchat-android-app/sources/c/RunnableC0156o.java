package c;

import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import c.AbstractC0125b0;
import j.C0252a;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: renamed from: c.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0156o extends AbstractC0114F implements Runnable {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final boolean f1872v;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0109A f1873a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d f1874b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d f1875c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1876d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1877e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1878f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1879g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1880h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1881i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1882j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f1883k;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f1885m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f1886n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1888p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public CharSequence f1889q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1890r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public CharSequence f1891s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ArrayList f1892t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ArrayList f1893u;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f1884l = true;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1887o = -1;

    /* JADX INFO: renamed from: c.o$a */
    public class a implements AbstractC0125b0.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AbstractComponentCallbacksC0165t f1894a;

        public a(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
            this.f1894a = abstractComponentCallbacksC0165t;
        }

        @Override // c.AbstractC0125b0.f
        public View a() {
            return this.f1894a.s();
        }
    }

    /* JADX INFO: renamed from: c.o$b */
    public class b implements ViewTreeObserver.OnPreDrawListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f1896a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Object f1897b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ ArrayList f1898c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ e f1899d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Object f1900e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ Object f1901f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ boolean f1902g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ AbstractComponentCallbacksC0165t f1903h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ AbstractComponentCallbacksC0165t f1904i;

        public b(View view, Object obj, ArrayList arrayList, e eVar, Object obj2, Object obj3, boolean z2, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t2) {
            this.f1896a = view;
            this.f1897b = obj;
            this.f1898c = arrayList;
            this.f1899d = eVar;
            this.f1900e = obj2;
            this.f1901f = obj3;
            this.f1902g = z2;
            this.f1903h = abstractComponentCallbacksC0165t;
            this.f1904i = abstractComponentCallbacksC0165t2;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.f1896a.getViewTreeObserver().removeOnPreDrawListener(this);
            AbstractC0125b0.v(this.f1897b, this.f1898c);
            this.f1898c.remove(this.f1899d.f1923d);
            AbstractC0125b0.m(this.f1900e, this.f1901f, this.f1897b, this.f1898c, false);
            this.f1898c.clear();
            C0252a c0252aX = RunnableC0156o.this.x(this.f1899d, this.f1902g, this.f1903h);
            AbstractC0125b0.y(this.f1897b, this.f1899d.f1923d, c0252aX, this.f1898c);
            RunnableC0156o.this.D(c0252aX, this.f1899d);
            RunnableC0156o.this.j(this.f1899d, this.f1903h, this.f1904i, this.f1902g, c0252aX);
            AbstractC0125b0.m(this.f1900e, this.f1901f, this.f1897b, this.f1898c, true);
            return true;
        }
    }

    /* JADX INFO: renamed from: c.o$c */
    public class c implements ViewTreeObserver.OnPreDrawListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f1906a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ e f1907b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f1908c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Object f1909d;

        public c(View view, e eVar, int i2, Object obj) {
            this.f1906a = view;
            this.f1907b = eVar;
            this.f1908c = i2;
            this.f1909d = obj;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.f1906a.getViewTreeObserver().removeOnPreDrawListener(this);
            RunnableC0156o.this.q(this.f1907b, this.f1908c, this.f1909d);
            return true;
        }
    }

    /* JADX INFO: renamed from: c.o$d */
    public static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public d f1911a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public d f1912b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1913c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public AbstractComponentCallbacksC0165t f1914d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1915e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1916f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1917g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1918h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public ArrayList f1919i;
    }

    /* JADX INFO: renamed from: c.o$e */
    public class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public C0252a f1920a = new C0252a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList f1921b = new ArrayList();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public AbstractC0125b0.e f1922c = new AbstractC0125b0.e();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public View f1923d;

        public e() {
        }
    }

    static {
        f1872v = Build.VERSION.SDK_INT >= 21;
    }

    public RunnableC0156o(C0109A c0109a) {
        this.f1873a = c0109a;
    }

    public static C0252a A(ArrayList arrayList, ArrayList arrayList2, C0252a c0252a) {
        if (c0252a.isEmpty()) {
            return c0252a;
        }
        C0252a c0252a2 = new C0252a();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = (View) c0252a.get(arrayList.get(i2));
            if (view != null) {
                c0252a2.put(arrayList2.get(i2), view);
            }
        }
        return c0252a2;
    }

    public static void E(SparseArray sparseArray, SparseArray sparseArray2, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
        int i2;
        if (abstractComponentCallbacksC0165t == null || (i2 = abstractComponentCallbacksC0165t.f2036x) == 0 || abstractComponentCallbacksC0165t.y()) {
            return;
        }
        if (abstractComponentCallbacksC0165t.x() && abstractComponentCallbacksC0165t.s() != null && sparseArray.get(i2) == null) {
            sparseArray.put(i2, abstractComponentCallbacksC0165t);
        }
        if (sparseArray2.get(i2) == abstractComponentCallbacksC0165t) {
            sparseArray2.remove(i2);
        }
    }

    public static void G(C0252a c0252a, String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        for (int i2 = 0; i2 < c0252a.size(); i2++) {
            if (str.equals(c0252a.k(i2))) {
                c0252a.j(i2, str2);
                return;
            }
        }
        c0252a.put(str, str2);
    }

    public static void I(e eVar, ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList != null) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                G(eVar.f1920a, (String) arrayList.get(i2), (String) arrayList2.get(i2));
            }
        }
    }

    public static Object k(Object obj, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, ArrayList arrayList, C0252a c0252a, View view) {
        return obj != null ? AbstractC0125b0.h(obj, abstractComponentCallbacksC0165t.s(), arrayList, c0252a, view) : obj;
    }

    public static Object s(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, boolean z2) {
        if (abstractComponentCallbacksC0165t == null) {
            return null;
        }
        return AbstractC0125b0.k(z2 ? abstractComponentCallbacksC0165t.l() : abstractComponentCallbacksC0165t.i());
    }

    public static Object t(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, boolean z2) {
        if (abstractComponentCallbacksC0165t == null) {
            return null;
        }
        return AbstractC0125b0.k(z2 ? abstractComponentCallbacksC0165t.n() : abstractComponentCallbacksC0165t.j());
    }

    public static Object v(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t2, boolean z2) {
        if (abstractComponentCallbacksC0165t == null || abstractComponentCallbacksC0165t2 == null) {
            return null;
        }
        return AbstractC0125b0.z(z2 ? abstractComponentCallbacksC0165t2.p() : abstractComponentCallbacksC0165t.o());
    }

    public final C0252a B(e eVar, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, boolean z2) {
        C0252a c0252a = new C0252a();
        if (this.f1892t != null) {
            AbstractC0125b0.p(c0252a, abstractComponentCallbacksC0165t.s());
            if (z2) {
                c0252a.m(this.f1893u);
            } else {
                c0252a = A(this.f1892t, this.f1893u, c0252a);
            }
        }
        if (z2) {
            abstractComponentCallbacksC0165t.getClass();
            C(eVar, c0252a, false);
        } else {
            abstractComponentCallbacksC0165t.getClass();
            H(eVar, c0252a, false);
        }
        return c0252a;
    }

    public final void C(e eVar, C0252a c0252a, boolean z2) {
        ArrayList arrayList = this.f1893u;
        int size = arrayList == null ? 0 : arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            String str = (String) this.f1892t.get(i2);
            View view = (View) c0252a.get((String) this.f1893u.get(i2));
            if (view != null) {
                String strR = AbstractC0125b0.r(view);
                if (z2) {
                    G(eVar.f1920a, str, strR);
                } else {
                    G(eVar.f1920a, strR, str);
                }
            }
        }
    }

    public void D(C0252a c0252a, e eVar) {
        View view;
        if (this.f1893u == null || c0252a.isEmpty() || (view = (View) c0252a.get(this.f1893u.get(0))) == null) {
            return;
        }
        eVar.f1922c.f1856a = view;
    }

    public final void F(SparseArray sparseArray, SparseArray sparseArray2, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
        if (abstractComponentCallbacksC0165t != null) {
            int i2 = abstractComponentCallbacksC0165t.f2036x;
            if (i2 != 0) {
                if (!abstractComponentCallbacksC0165t.x()) {
                    sparseArray2.put(i2, abstractComponentCallbacksC0165t);
                }
                if (sparseArray.get(i2) == abstractComponentCallbacksC0165t) {
                    sparseArray.remove(i2);
                }
            }
            if (abstractComponentCallbacksC0165t.f2013a < 1) {
                C0109A c0109a = this.f1873a;
                if (c0109a.f1779l >= 1) {
                    c0109a.O(abstractComponentCallbacksC0165t);
                    this.f1873a.W(abstractComponentCallbacksC0165t, 1, 0, 0, false);
                }
            }
        }
    }

    public final void H(e eVar, C0252a c0252a, boolean z2) {
        int size = c0252a.size();
        for (int i2 = 0; i2 < size; i2++) {
            String str = (String) c0252a.h(i2);
            String strR = AbstractC0125b0.r((View) c0252a.k(i2));
            if (z2) {
                G(eVar.f1920a, str, strR);
            } else {
                G(eVar.f1920a, strR, str);
            }
        }
    }

    @Override // c.AbstractC0114F
    public AbstractC0114F a(int i2, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, String str) {
        n(i2, abstractComponentCallbacksC0165t, str, 1);
        return this;
    }

    @Override // c.AbstractC0114F
    public AbstractC0114F b(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
        d dVar = new d();
        dVar.f1913c = 7;
        dVar.f1914d = abstractComponentCallbacksC0165t;
        e(dVar);
        return this;
    }

    @Override // c.AbstractC0114F
    public void c() {
        m();
        this.f1873a.E(this, true);
    }

    @Override // c.AbstractC0114F
    public AbstractC0114F d(AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
        d dVar = new d();
        dVar.f1913c = 6;
        dVar.f1914d = abstractComponentCallbacksC0165t;
        e(dVar);
        return this;
    }

    public void e(d dVar) {
        if (this.f1874b == null) {
            this.f1875c = dVar;
            this.f1874b = dVar;
        } else {
            d dVar2 = this.f1875c;
            dVar.f1912b = dVar2;
            dVar2.f1911a = dVar;
            this.f1875c = dVar;
        }
        dVar.f1915e = this.f1877e;
        dVar.f1916f = this.f1878f;
        dVar.f1917g = this.f1879g;
        dVar.f1918h = this.f1880h;
        this.f1876d++;
    }

    public final e f(SparseArray sparseArray, SparseArray sparseArray2, boolean z2) {
        e eVar = new e();
        eVar.f1923d = new View(this.f1873a.f1780m.h());
        boolean z3 = false;
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            if (l(sparseArray.keyAt(i2), eVar, z2, sparseArray, sparseArray2)) {
                z3 = true;
            }
        }
        for (int i3 = 0; i3 < sparseArray2.size(); i3++) {
            int iKeyAt = sparseArray2.keyAt(i3);
            if (sparseArray.get(iKeyAt) == null && l(iKeyAt, eVar, z2, sparseArray, sparseArray2)) {
                z3 = true;
            }
        }
        if (z3) {
            return eVar;
        }
        return null;
    }

    public void g(int i2) {
        if (this.f1883k) {
            if (C0109A.f1765x) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i2);
            }
            for (d dVar = this.f1874b; dVar != null; dVar = dVar.f1911a) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = dVar.f1914d;
                if (abstractComponentCallbacksC0165t != null) {
                    abstractComponentCallbacksC0165t.f2029q += i2;
                    if (C0109A.f1765x) {
                        Log.v("FragmentManager", "Bump nesting of " + dVar.f1914d + " to " + dVar.f1914d.f2029q);
                    }
                }
                ArrayList arrayList = dVar.f1919i;
                if (arrayList != null) {
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t2 = (AbstractComponentCallbacksC0165t) dVar.f1919i.get(size);
                        abstractComponentCallbacksC0165t2.f2029q += i2;
                        if (C0109A.f1765x) {
                            Log.v("FragmentManager", "Bump nesting of " + abstractComponentCallbacksC0165t2 + " to " + abstractComponentCallbacksC0165t2.f2029q);
                        }
                    }
                }
            }
        }
    }

    public void h(SparseArray sparseArray, SparseArray sparseArray2) {
        if (this.f1873a.f1781n.b()) {
            for (d dVar = this.f1875c; dVar != null; dVar = dVar.f1912b) {
                switch (dVar.f1913c) {
                    case 1:
                        E(sparseArray, sparseArray2, dVar.f1914d);
                        break;
                    case 2:
                        ArrayList arrayList = dVar.f1919i;
                        if (arrayList != null) {
                            for (int size = arrayList.size() - 1; size >= 0; size--) {
                                F(sparseArray, sparseArray2, (AbstractComponentCallbacksC0165t) dVar.f1919i.get(size));
                            }
                        }
                        E(sparseArray, sparseArray2, dVar.f1914d);
                        break;
                    case 3:
                        F(sparseArray, sparseArray2, dVar.f1914d);
                        break;
                    case 4:
                        F(sparseArray, sparseArray2, dVar.f1914d);
                        break;
                    case 5:
                        E(sparseArray, sparseArray2, dVar.f1914d);
                        break;
                    case 6:
                        F(sparseArray, sparseArray2, dVar.f1914d);
                        break;
                    case 7:
                        E(sparseArray, sparseArray2, dVar.f1914d);
                        break;
                }
            }
        }
    }

    public final void i(SparseArray sparseArray, SparseArray sparseArray2) {
        if (this.f1873a.f1781n.b()) {
            for (d dVar = this.f1874b; dVar != null; dVar = dVar.f1911a) {
                switch (dVar.f1913c) {
                    case 1:
                        F(sparseArray, sparseArray2, dVar.f1914d);
                        break;
                    case 2:
                        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = dVar.f1914d;
                        if (this.f1873a.f1772e != null) {
                            for (int i2 = 0; i2 < this.f1873a.f1772e.size(); i2++) {
                                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t2 = (AbstractComponentCallbacksC0165t) this.f1873a.f1772e.get(i2);
                                if (abstractComponentCallbacksC0165t == null || abstractComponentCallbacksC0165t2.f2036x == abstractComponentCallbacksC0165t.f2036x) {
                                    if (abstractComponentCallbacksC0165t2 == abstractComponentCallbacksC0165t) {
                                        sparseArray2.remove(abstractComponentCallbacksC0165t2.f2036x);
                                        abstractComponentCallbacksC0165t = null;
                                    } else {
                                        E(sparseArray, sparseArray2, abstractComponentCallbacksC0165t2);
                                    }
                                }
                            }
                        }
                        F(sparseArray, sparseArray2, dVar.f1914d);
                        break;
                    case 3:
                        E(sparseArray, sparseArray2, dVar.f1914d);
                        break;
                    case 4:
                        E(sparseArray, sparseArray2, dVar.f1914d);
                        break;
                    case 5:
                        F(sparseArray, sparseArray2, dVar.f1914d);
                        break;
                    case 6:
                        E(sparseArray, sparseArray2, dVar.f1914d);
                        break;
                    case 7:
                        F(sparseArray, sparseArray2, dVar.f1914d);
                        break;
                }
            }
        }
    }

    public void j(e eVar, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t2, boolean z2, C0252a c0252a) {
        if (z2) {
            abstractComponentCallbacksC0165t2.getClass();
        } else {
            abstractComponentCallbacksC0165t.getClass();
        }
    }

    public final boolean l(int i2, e eVar, boolean z2, SparseArray sparseArray, SparseArray sparseArray2) {
        Object obj;
        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t;
        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t2;
        C0252a c0252a;
        Object obj2;
        Object obj3;
        Object obj4;
        View view;
        ViewGroup viewGroup = (ViewGroup) this.f1873a.f1781n.a(i2);
        if (viewGroup == null) {
            return false;
        }
        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t3 = (AbstractComponentCallbacksC0165t) sparseArray2.get(i2);
        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t4 = (AbstractComponentCallbacksC0165t) sparseArray.get(i2);
        Object objS = s(abstractComponentCallbacksC0165t3, z2);
        Object objV = v(abstractComponentCallbacksC0165t3, abstractComponentCallbacksC0165t4, z2);
        Object objT = t(abstractComponentCallbacksC0165t4, z2);
        ArrayList arrayList = new ArrayList();
        Object obj5 = null;
        if (objV != null) {
            C0252a c0252aB = B(eVar, abstractComponentCallbacksC0165t4, z2);
            if (c0252aB.isEmpty()) {
                c0252a = null;
                obj = objT;
                obj2 = objS;
                abstractComponentCallbacksC0165t = abstractComponentCallbacksC0165t4;
                abstractComponentCallbacksC0165t2 = abstractComponentCallbacksC0165t3;
            } else {
                if (z2) {
                    abstractComponentCallbacksC0165t4.getClass();
                } else {
                    abstractComponentCallbacksC0165t3.getClass();
                }
                obj = objT;
                abstractComponentCallbacksC0165t = abstractComponentCallbacksC0165t4;
                abstractComponentCallbacksC0165t2 = abstractComponentCallbacksC0165t3;
                z(eVar, viewGroup, objV, abstractComponentCallbacksC0165t3, abstractComponentCallbacksC0165t4, z2, arrayList, objS, obj);
                obj2 = objS;
                obj5 = objV;
                c0252a = c0252aB;
            }
        } else {
            obj = objT;
            abstractComponentCallbacksC0165t = abstractComponentCallbacksC0165t4;
            abstractComponentCallbacksC0165t2 = abstractComponentCallbacksC0165t3;
            c0252a = null;
            obj2 = objS;
            obj5 = objV;
        }
        if (obj2 == null && obj5 == null) {
            obj3 = obj;
            if (obj3 == null) {
                return false;
            }
        } else {
            obj3 = obj;
        }
        ArrayList arrayList2 = new ArrayList();
        Object objK = k(obj3, abstractComponentCallbacksC0165t, arrayList2, c0252a, eVar.f1923d);
        ArrayList arrayList3 = this.f1893u;
        if (arrayList3 != null && c0252a != null && (view = (View) c0252a.get(arrayList3.get(0))) != null) {
            if (objK != null) {
                AbstractC0125b0.w(objK, view);
            }
            if (obj5 != null) {
                AbstractC0125b0.w(obj5, view);
            }
        }
        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t5 = abstractComponentCallbacksC0165t2;
        a aVar = new a(abstractComponentCallbacksC0165t5);
        ArrayList arrayList4 = new ArrayList();
        C0252a c0252a2 = new C0252a();
        Object objU = AbstractC0125b0.u(obj2, objK, obj5, abstractComponentCallbacksC0165t5 != null ? z2 ? abstractComponentCallbacksC0165t5.g() : abstractComponentCallbacksC0165t5.f() : true);
        if (objU != null) {
            Object obj6 = obj2;
            AbstractC0125b0.e(obj6, obj5, objK, viewGroup, aVar, eVar.f1923d, eVar.f1922c, eVar.f1920a, arrayList4, arrayList2, c0252a, c0252a2, arrayList);
            obj4 = objU;
            r(viewGroup, eVar, i2, obj4);
            AbstractC0125b0.n(obj4, eVar.f1923d, true);
            q(eVar, i2, obj4);
            AbstractC0125b0.f(viewGroup, obj4);
            AbstractC0125b0.j(viewGroup, eVar.f1923d, obj6, arrayList4, objK, arrayList2, obj5, arrayList, obj4, eVar.f1921b, c0252a2);
        } else {
            obj4 = objU;
        }
        return obj4 != null;
    }

    public AbstractC0114F m() {
        if (this.f1883k) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.f1884l = false;
        return this;
    }

    public final void n(int i2, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, String str, int i3) {
        Class<?> cls = abstractComponentCallbacksC0165t.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        abstractComponentCallbacksC0165t.f2030r = this.f1873a;
        if (str != null) {
            String str2 = abstractComponentCallbacksC0165t.f2037y;
            if (str2 != null && !str.equals(str2)) {
                throw new IllegalStateException("Can't change tag of fragment " + abstractComponentCallbacksC0165t + ": was " + abstractComponentCallbacksC0165t.f2037y + " now " + str);
            }
            abstractComponentCallbacksC0165t.f2037y = str;
        }
        if (i2 != 0) {
            if (i2 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + abstractComponentCallbacksC0165t + " with tag " + str + " to container view with no id");
            }
            int i4 = abstractComponentCallbacksC0165t.f2035w;
            if (i4 != 0 && i4 != i2) {
                throw new IllegalStateException("Can't change container ID of fragment " + abstractComponentCallbacksC0165t + ": was " + abstractComponentCallbacksC0165t.f2035w + " now " + i2);
            }
            abstractComponentCallbacksC0165t.f2035w = i2;
            abstractComponentCallbacksC0165t.f2036x = i2;
        }
        d dVar = new d();
        dVar.f1913c = i3;
        dVar.f1914d = abstractComponentCallbacksC0165t;
        e(dVar);
    }

    public void o(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        p(str, printWriter, true);
    }

    public void p(String str, PrintWriter printWriter, boolean z2) {
        String str2;
        if (z2) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.f1885m);
            printWriter.print(" mIndex=");
            printWriter.print(this.f1887o);
            printWriter.print(" mCommitted=");
            printWriter.println(this.f1886n);
            if (this.f1881i != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f1881i));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.f1882j));
            }
            if (this.f1877e != 0 || this.f1878f != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f1877e));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f1878f));
            }
            if (this.f1879g != 0 || this.f1880h != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f1879g));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f1880h));
            }
            if (this.f1888p != 0 || this.f1889q != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.f1888p));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.f1889q);
            }
            if (this.f1890r != 0 || this.f1891s != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.f1890r));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.f1891s);
            }
        }
        if (this.f1874b != null) {
            printWriter.print(str);
            printWriter.println("Operations:");
            String str3 = str + "    ";
            d dVar = this.f1874b;
            int i2 = 0;
            while (dVar != null) {
                switch (dVar.f1913c) {
                    case 0:
                        str2 = "NULL";
                        break;
                    case 1:
                        str2 = "ADD";
                        break;
                    case 2:
                        str2 = "REPLACE";
                        break;
                    case 3:
                        str2 = "REMOVE";
                        break;
                    case 4:
                        str2 = "HIDE";
                        break;
                    case 5:
                        str2 = "SHOW";
                        break;
                    case 6:
                        str2 = "DETACH";
                        break;
                    case 7:
                        str2 = "ATTACH";
                        break;
                    default:
                        str2 = "cmd=" + dVar.f1913c;
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.print(str2);
                printWriter.print(" ");
                printWriter.println(dVar.f1914d);
                if (z2) {
                    if (dVar.f1915e != 0 || dVar.f1916f != 0) {
                        printWriter.print(str);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(dVar.f1915e));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(dVar.f1916f));
                    }
                    if (dVar.f1917g != 0 || dVar.f1918h != 0) {
                        printWriter.print(str);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(dVar.f1917g));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(dVar.f1918h));
                    }
                }
                ArrayList arrayList = dVar.f1919i;
                if (arrayList != null && arrayList.size() > 0) {
                    for (int i3 = 0; i3 < dVar.f1919i.size(); i3++) {
                        printWriter.print(str3);
                        if (dVar.f1919i.size() == 1) {
                            printWriter.print("Removed: ");
                        } else {
                            if (i3 == 0) {
                                printWriter.println("Removed:");
                            }
                            printWriter.print(str3);
                            printWriter.print("  #");
                            printWriter.print(i3);
                            printWriter.print(": ");
                        }
                        printWriter.println(dVar.f1919i.get(i3));
                    }
                }
                dVar = dVar.f1911a;
                i2++;
            }
        }
    }

    public void q(e eVar, int i2, Object obj) {
        if (this.f1873a.f1772e != null) {
            for (int i3 = 0; i3 < this.f1873a.f1772e.size(); i3++) {
                AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) this.f1873a.f1772e.get(i3);
                View view = abstractComponentCallbacksC0165t.f1998I;
                if (view != null && abstractComponentCallbacksC0165t.f1997H != null && abstractComponentCallbacksC0165t.f2036x == i2) {
                    if (!abstractComponentCallbacksC0165t.f2038z) {
                        AbstractC0125b0.n(obj, view, false);
                        eVar.f1921b.remove(abstractComponentCallbacksC0165t.f1998I);
                    } else if (!eVar.f1921b.contains(view)) {
                        AbstractC0125b0.n(obj, abstractComponentCallbacksC0165t.f1998I, true);
                        eVar.f1921b.add(abstractComponentCallbacksC0165t.f1998I);
                    }
                }
            }
        }
    }

    public final void r(View view, e eVar, int i2, Object obj) {
        view.getViewTreeObserver().addOnPreDrawListener(new c(view, eVar, i2, obj));
    }

    @Override // java.lang.Runnable
    public void run() {
        e eVarF;
        if (C0109A.f1765x) {
            Log.v("FragmentManager", "Run: " + this);
        }
        if (this.f1883k && this.f1887o < 0) {
            throw new IllegalStateException("addToBackStack() called after commit()");
        }
        g(1);
        if (!f1872v || this.f1873a.f1779l < 1) {
            eVarF = null;
        } else {
            SparseArray sparseArray = new SparseArray();
            SparseArray sparseArray2 = new SparseArray();
            i(sparseArray, sparseArray2);
            eVarF = f(sparseArray, sparseArray2, false);
        }
        int i2 = eVarF != null ? 0 : this.f1882j;
        int i3 = eVarF != null ? 0 : this.f1881i;
        for (d dVar = this.f1874b; dVar != null; dVar = dVar.f1911a) {
            int i4 = eVarF != null ? 0 : dVar.f1915e;
            int i5 = eVarF != null ? 0 : dVar.f1916f;
            switch (dVar.f1913c) {
                case 1:
                    AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = dVar.f1914d;
                    abstractComponentCallbacksC0165t.f1996G = i4;
                    this.f1873a.f(abstractComponentCallbacksC0165t, false);
                    continue;
                    break;
                case 2:
                    AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t2 = dVar.f1914d;
                    int i6 = abstractComponentCallbacksC0165t2.f2036x;
                    ArrayList arrayList = this.f1873a.f1772e;
                    if (arrayList != null) {
                        for (int size = arrayList.size() - 1; size >= 0; size--) {
                            AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t3 = (AbstractComponentCallbacksC0165t) this.f1873a.f1772e.get(size);
                            if (C0109A.f1765x) {
                                Log.v("FragmentManager", "OP_REPLACE: adding=" + abstractComponentCallbacksC0165t2 + " old=" + abstractComponentCallbacksC0165t3);
                            }
                            if (abstractComponentCallbacksC0165t3.f2036x == i6) {
                                if (abstractComponentCallbacksC0165t3 == abstractComponentCallbacksC0165t2) {
                                    dVar.f1914d = null;
                                    abstractComponentCallbacksC0165t2 = null;
                                } else {
                                    if (dVar.f1919i == null) {
                                        dVar.f1919i = new ArrayList();
                                    }
                                    dVar.f1919i.add(abstractComponentCallbacksC0165t3);
                                    abstractComponentCallbacksC0165t3.f1996G = i5;
                                    if (this.f1883k) {
                                        abstractComponentCallbacksC0165t3.f2029q++;
                                        if (C0109A.f1765x) {
                                            Log.v("FragmentManager", "Bump nesting of " + abstractComponentCallbacksC0165t3 + " to " + abstractComponentCallbacksC0165t3.f2029q);
                                        }
                                    }
                                    this.f1873a.b0(abstractComponentCallbacksC0165t3, i3, i2);
                                }
                            }
                        }
                    }
                    if (abstractComponentCallbacksC0165t2 != null) {
                        abstractComponentCallbacksC0165t2.f1996G = i4;
                        this.f1873a.f(abstractComponentCallbacksC0165t2, false);
                    }
                    break;
                case 3:
                    AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t4 = dVar.f1914d;
                    abstractComponentCallbacksC0165t4.f1996G = i5;
                    this.f1873a.b0(abstractComponentCallbacksC0165t4, i3, i2);
                    break;
                case 4:
                    AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t5 = dVar.f1914d;
                    abstractComponentCallbacksC0165t5.f1996G = i5;
                    this.f1873a.L(abstractComponentCallbacksC0165t5, i3, i2);
                    break;
                case 5:
                    AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t6 = dVar.f1914d;
                    abstractComponentCallbacksC0165t6.f1996G = i4;
                    this.f1873a.m0(abstractComponentCallbacksC0165t6, i3, i2);
                    break;
                case 6:
                    AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t7 = dVar.f1914d;
                    abstractComponentCallbacksC0165t7.f1996G = i5;
                    this.f1873a.j(abstractComponentCallbacksC0165t7, i3, i2);
                    break;
                case 7:
                    AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t8 = dVar.f1914d;
                    abstractComponentCallbacksC0165t8.f1996G = i4;
                    this.f1873a.h(abstractComponentCallbacksC0165t8, i3, i2);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + dVar.f1913c);
            }
        }
        C0109A c0109a = this.f1873a;
        c0109a.T(c0109a.f1779l, i3, i2, true);
        if (this.f1883k) {
            this.f1873a.e(this);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.f1887o >= 0) {
            sb.append(" #");
            sb.append(this.f1887o);
        }
        if (this.f1885m != null) {
            sb.append(" ");
            sb.append(this.f1885m);
        }
        sb.append("}");
        return sb.toString();
    }

    public String u() {
        return this.f1885m;
    }

    public final C0252a w(e eVar, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, boolean z2) {
        C0252a c0252a = new C0252a();
        View viewS = abstractComponentCallbacksC0165t.s();
        if (viewS == null || this.f1892t == null) {
            return c0252a;
        }
        AbstractC0125b0.p(c0252a, viewS);
        if (z2) {
            return A(this.f1892t, this.f1893u, c0252a);
        }
        c0252a.m(this.f1893u);
        return c0252a;
    }

    public C0252a x(e eVar, boolean z2, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t) {
        C0252a c0252aW = w(eVar, abstractComponentCallbacksC0165t, z2);
        if (z2) {
            abstractComponentCallbacksC0165t.getClass();
            C(eVar, c0252aW, true);
        } else {
            abstractComponentCallbacksC0165t.getClass();
            H(eVar, c0252aW, true);
        }
        return c0252aW;
    }

    public e y(boolean z2, e eVar, SparseArray sparseArray, SparseArray sparseArray2) {
        e eVar2 = null;
        if (C0109A.f1765x) {
            Log.v("FragmentManager", "popFromBackStack: " + this);
            o("  ", null, new PrintWriter(new j.d("FragmentManager")), null);
        }
        if (f1872v && this.f1873a.f1779l >= 1) {
            if (eVar == null) {
                if (sparseArray.size() != 0 || sparseArray2.size() != 0) {
                    eVar = f(sparseArray, sparseArray2, true);
                }
            } else if (!z2) {
                I(eVar, this.f1893u, this.f1892t);
            }
        }
        g(-1);
        int i2 = eVar != null ? 0 : this.f1882j;
        int i3 = eVar != null ? 0 : this.f1881i;
        for (d dVar = this.f1875c; dVar != null; dVar = dVar.f1912b) {
            int i4 = eVar != null ? 0 : dVar.f1917g;
            int i5 = eVar != null ? 0 : dVar.f1918h;
            switch (dVar.f1913c) {
                case 1:
                    AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = dVar.f1914d;
                    abstractComponentCallbacksC0165t.f1996G = i5;
                    this.f1873a.b0(abstractComponentCallbacksC0165t, C0109A.f0(i3), i2);
                    break;
                case 2:
                    AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t2 = dVar.f1914d;
                    if (abstractComponentCallbacksC0165t2 != null) {
                        abstractComponentCallbacksC0165t2.f1996G = i5;
                        this.f1873a.b0(abstractComponentCallbacksC0165t2, C0109A.f0(i3), i2);
                    }
                    if (dVar.f1919i != null) {
                        for (int i6 = 0; i6 < dVar.f1919i.size(); i6++) {
                            AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t3 = (AbstractComponentCallbacksC0165t) dVar.f1919i.get(i6);
                            abstractComponentCallbacksC0165t3.f1996G = i4;
                            this.f1873a.f(abstractComponentCallbacksC0165t3, false);
                        }
                    }
                    break;
                case 3:
                    AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t4 = dVar.f1914d;
                    abstractComponentCallbacksC0165t4.f1996G = i4;
                    this.f1873a.f(abstractComponentCallbacksC0165t4, false);
                    break;
                case 4:
                    AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t5 = dVar.f1914d;
                    abstractComponentCallbacksC0165t5.f1996G = i4;
                    this.f1873a.m0(abstractComponentCallbacksC0165t5, C0109A.f0(i3), i2);
                    break;
                case 5:
                    AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t6 = dVar.f1914d;
                    abstractComponentCallbacksC0165t6.f1996G = i5;
                    this.f1873a.L(abstractComponentCallbacksC0165t6, C0109A.f0(i3), i2);
                    break;
                case 6:
                    AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t7 = dVar.f1914d;
                    abstractComponentCallbacksC0165t7.f1996G = i4;
                    this.f1873a.h(abstractComponentCallbacksC0165t7, C0109A.f0(i3), i2);
                    break;
                case 7:
                    AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t8 = dVar.f1914d;
                    abstractComponentCallbacksC0165t8.f1996G = i4;
                    this.f1873a.j(abstractComponentCallbacksC0165t8, C0109A.f0(i3), i2);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + dVar.f1913c);
            }
        }
        if (z2) {
            C0109A c0109a = this.f1873a;
            c0109a.T(c0109a.f1779l, C0109A.f0(i3), i2, true);
        } else {
            eVar2 = eVar;
        }
        int i7 = this.f1887o;
        if (i7 >= 0) {
            this.f1873a.I(i7);
            this.f1887o = -1;
        }
        return eVar2;
    }

    public final void z(e eVar, View view, Object obj, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t, AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t2, boolean z2, ArrayList arrayList, Object obj2, Object obj3) {
        if (obj != null) {
            view.getViewTreeObserver().addOnPreDrawListener(new b(view, obj, arrayList, eVar, obj2, obj3, z2, abstractComponentCallbacksC0165t, abstractComponentCallbacksC0165t2));
        }
    }
}
