package z;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final F.a f8300v = F.a.a(Object.class);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ThreadLocal f8301a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f8302b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final B.c f8303c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C.d f8304d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f8305e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final B.d f8306f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final InterfaceC0530c f8307g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Map f8308h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f8309i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f8310j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f8311k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f8312l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f8313m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f8314n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final boolean f8315o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f8316p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f8317q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f8318r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final n f8319s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final List f8320t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final List f8321u;

    public class a extends o {
        public a() {
        }

        @Override // z.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Double b(G.a aVar) throws IOException {
            if (aVar.x() != G.b.NULL) {
                return Double.valueOf(aVar.o());
            }
            aVar.t();
            return null;
        }

        @Override // z.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(G.c cVar, Number number) throws IOException {
            if (number == null) {
                cVar.m();
            } else {
                e.d(number.doubleValue());
                cVar.y(number);
            }
        }
    }

    public class b extends o {
        public b() {
        }

        @Override // z.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Float b(G.a aVar) throws IOException {
            if (aVar.x() != G.b.NULL) {
                return Float.valueOf((float) aVar.o());
            }
            aVar.t();
            return null;
        }

        @Override // z.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(G.c cVar, Number number) throws IOException {
            if (number == null) {
                cVar.m();
            } else {
                e.d(number.floatValue());
                cVar.y(number);
            }
        }
    }

    public static class c extends o {
        @Override // z.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(G.a aVar) throws IOException {
            if (aVar.x() != G.b.NULL) {
                return Long.valueOf(aVar.q());
            }
            aVar.t();
            return null;
        }

        @Override // z.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(G.c cVar, Number number) throws IOException {
            if (number == null) {
                cVar.m();
            } else {
                cVar.z(number.toString());
            }
        }
    }

    public static class d extends o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o f8324a;

        public d(o oVar) {
            this.f8324a = oVar;
        }

        @Override // z.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicLong b(G.a aVar) {
            return new AtomicLong(((Number) this.f8324a.b(aVar)).longValue());
        }

        @Override // z.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(G.c cVar, AtomicLong atomicLong) {
            this.f8324a.d(cVar, Long.valueOf(atomicLong.get()));
        }
    }

    /* JADX INFO: renamed from: z.e$e, reason: collision with other inner class name */
    public static class C0076e extends o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ o f8325a;

        public C0076e(o oVar) {
            this.f8325a = oVar;
        }

        @Override // z.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicLongArray b(G.a aVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            aVar.a();
            while (aVar.j()) {
                arrayList.add(Long.valueOf(((Number) this.f8325a.b(aVar)).longValue()));
            }
            aVar.f();
            int size = arrayList.size();
            AtomicLongArray atomicLongArray = new AtomicLongArray(size);
            for (int i2 = 0; i2 < size; i2++) {
                atomicLongArray.set(i2, ((Long) arrayList.get(i2)).longValue());
            }
            return atomicLongArray;
        }

        @Override // z.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(G.c cVar, AtomicLongArray atomicLongArray) throws IOException {
            cVar.c();
            int length = atomicLongArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                this.f8325a.d(cVar, Long.valueOf(atomicLongArray.get(i2)));
            }
            cVar.f();
        }
    }

    public static class f extends o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public o f8326a;

        @Override // z.o
        public Object b(G.a aVar) {
            o oVar = this.f8326a;
            if (oVar != null) {
                return oVar.b(aVar);
            }
            throw new IllegalStateException();
        }

        @Override // z.o
        public void d(G.c cVar, Object obj) {
            o oVar = this.f8326a;
            if (oVar == null) {
                throw new IllegalStateException();
            }
            oVar.d(cVar, obj);
        }

        public void e(o oVar) {
            if (this.f8326a != null) {
                throw new AssertionError();
            }
            this.f8326a = oVar;
        }
    }

    public e() {
        this(B.d.f26g, EnumC0529b.f8293a, Collections.emptyMap(), false, false, false, true, false, false, false, n.f8332a, null, 2, 2, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    public static void a(Object obj, G.a aVar) {
        if (obj != null) {
            try {
                if (aVar.x() == G.b.END_DOCUMENT) {
                } else {
                    throw new h("JSON document was not fully consumed.");
                }
            } catch (G.d e2) {
                throw new m(e2);
            } catch (IOException e3) {
                throw new h(e3);
            }
        }
    }

    public static o b(o oVar) {
        return new d(oVar).a();
    }

    public static o c(o oVar) {
        return new C0076e(oVar).a();
    }

    public static void d(double d2) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            throw new IllegalArgumentException(d2 + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    public static o n(n nVar) {
        return nVar == n.f8332a ? C.l.f168t : new c();
    }

    public final o e(boolean z2) {
        return z2 ? C.l.f170v : new a();
    }

    public final o f(boolean z2) {
        return z2 ? C.l.f169u : new b();
    }

    public Object g(G.a aVar, Type type) {
        boolean zK = aVar.k();
        boolean z2 = true;
        aVar.C(true);
        try {
            try {
                try {
                    aVar.x();
                    z2 = false;
                    return k(F.a.b(type)).b(aVar);
                } catch (IOException e2) {
                    throw new m(e2);
                } catch (AssertionError e3) {
                    throw z.d.a("AssertionError (GSON 2.8.5): " + e3.getMessage(), e3);
                }
            } catch (EOFException e4) {
                if (!z2) {
                    throw new m(e4);
                }
                aVar.C(zK);
                return null;
            } catch (IllegalStateException e5) {
                throw new m(e5);
            }
        } finally {
            aVar.C(zK);
        }
    }

    public Object h(Reader reader, Type type) {
        G.a aVarO = o(reader);
        Object objG = g(aVarO, type);
        a(objG, aVarO);
        return objG;
    }

    public Object i(String str, Class cls) {
        return B.k.c(cls).cast(j(str, cls));
    }

    public Object j(String str, Type type) {
        if (str == null) {
            return null;
        }
        return h(new StringReader(str), type);
    }

    public o k(F.a aVar) {
        boolean z2;
        o oVar = (o) this.f8302b.get(aVar == null ? f8300v : aVar);
        if (oVar != null) {
            return oVar;
        }
        Map map = (Map) this.f8301a.get();
        if (map == null) {
            map = new HashMap();
            this.f8301a.set(map);
            z2 = true;
        } else {
            z2 = false;
        }
        f fVar = (f) map.get(aVar);
        if (fVar != null) {
            return fVar;
        }
        try {
            f fVar2 = new f();
            map.put(aVar, fVar2);
            Iterator it = this.f8305e.iterator();
            while (it.hasNext()) {
                o oVarA = ((p) it.next()).a(this, aVar);
                if (oVarA != null) {
                    fVar2.e(oVarA);
                    this.f8302b.put(aVar, oVarA);
                    return oVarA;
                }
            }
            throw new IllegalArgumentException("GSON (2.8.5) cannot handle " + aVar);
        } finally {
            map.remove(aVar);
            if (z2) {
                this.f8301a.remove();
            }
        }
    }

    public o l(Class cls) {
        return k(F.a.a(cls));
    }

    public o m(p pVar, F.a aVar) {
        if (!this.f8305e.contains(pVar)) {
            pVar = this.f8304d;
        }
        boolean z2 = false;
        for (p pVar2 : this.f8305e) {
            if (z2) {
                o oVarA = pVar2.a(this, aVar);
                if (oVarA != null) {
                    return oVarA;
                }
            } else if (pVar2 == pVar) {
                z2 = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + aVar);
    }

    public G.a o(Reader reader) {
        G.a aVar = new G.a(reader);
        aVar.C(this.f8314n);
        return aVar;
    }

    public G.c p(Writer writer) throws IOException {
        if (this.f8311k) {
            writer.write(")]}'\n");
        }
        G.c cVar = new G.c(writer);
        if (this.f8313m) {
            cVar.s("  ");
        }
        cVar.u(this.f8309i);
        return cVar;
    }

    public String q(Object obj) {
        return obj == null ? s(i.f8328a) : r(obj, obj.getClass());
    }

    public String r(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        u(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public String s(g gVar) {
        StringWriter stringWriter = new StringWriter();
        w(gVar, stringWriter);
        return stringWriter.toString();
    }

    public void t(Object obj, Type type, G.c cVar) {
        o oVarK = k(F.a.b(type));
        boolean zJ = cVar.j();
        cVar.t(true);
        boolean zI = cVar.i();
        cVar.r(this.f8312l);
        boolean zH = cVar.h();
        cVar.u(this.f8309i);
        try {
            try {
                oVarK.d(cVar, obj);
            } catch (IOException e2) {
                throw new h(e2);
            } catch (AssertionError e3) {
                throw z.d.a("AssertionError (GSON 2.8.5): " + e3.getMessage(), e3);
            }
        } finally {
            cVar.t(zJ);
            cVar.r(zI);
            cVar.u(zH);
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.f8309i + ",factories:" + this.f8305e + ",instanceCreators:" + this.f8303c + "}";
    }

    public void u(Object obj, Type type, Appendable appendable) {
        try {
            t(obj, type, p(B.l.b(appendable)));
        } catch (IOException e2) {
            throw new h(e2);
        }
    }

    public void v(g gVar, G.c cVar) {
        boolean zJ = cVar.j();
        cVar.t(true);
        boolean zI = cVar.i();
        cVar.r(this.f8312l);
        boolean zH = cVar.h();
        cVar.u(this.f8309i);
        try {
            try {
                B.l.a(gVar, cVar);
            } catch (IOException e2) {
                throw new h(e2);
            } catch (AssertionError e3) {
                throw z.d.a("AssertionError (GSON 2.8.5): " + e3.getMessage(), e3);
            }
        } finally {
            cVar.t(zJ);
            cVar.r(zI);
            cVar.u(zH);
        }
    }

    public void w(g gVar, Appendable appendable) {
        try {
            v(gVar, p(B.l.b(appendable)));
        } catch (IOException e2) {
            throw new h(e2);
        }
    }

    public e(B.d dVar, InterfaceC0530c interfaceC0530c, Map map, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, n nVar, String str, int i2, int i3, List list, List list2, List list3) {
        this.f8301a = new ThreadLocal();
        this.f8302b = new ConcurrentHashMap();
        this.f8306f = dVar;
        this.f8307g = interfaceC0530c;
        this.f8308h = map;
        B.c cVar = new B.c(map);
        this.f8303c = cVar;
        this.f8309i = z2;
        this.f8310j = z3;
        this.f8311k = z4;
        this.f8312l = z5;
        this.f8313m = z6;
        this.f8314n = z7;
        this.f8315o = z8;
        this.f8319s = nVar;
        this.f8316p = str;
        this.f8317q = i2;
        this.f8318r = i3;
        this.f8320t = list;
        this.f8321u = list2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(C.l.f147Y);
        arrayList.add(C.g.f96b);
        arrayList.add(dVar);
        arrayList.addAll(list3);
        arrayList.add(C.l.f126D);
        arrayList.add(C.l.f161m);
        arrayList.add(C.l.f155g);
        arrayList.add(C.l.f157i);
        arrayList.add(C.l.f159k);
        o oVarN = n(nVar);
        arrayList.add(C.l.a(Long.TYPE, Long.class, oVarN));
        arrayList.add(C.l.a(Double.TYPE, Double.class, e(z8)));
        arrayList.add(C.l.a(Float.TYPE, Float.class, f(z8)));
        arrayList.add(C.l.f172x);
        arrayList.add(C.l.f163o);
        arrayList.add(C.l.f165q);
        arrayList.add(C.l.b(AtomicLong.class, b(oVarN)));
        arrayList.add(C.l.b(AtomicLongArray.class, c(oVarN)));
        arrayList.add(C.l.f167s);
        arrayList.add(C.l.f174z);
        arrayList.add(C.l.f128F);
        arrayList.add(C.l.f130H);
        arrayList.add(C.l.b(BigDecimal.class, C.l.f124B));
        arrayList.add(C.l.b(BigInteger.class, C.l.f125C));
        arrayList.add(C.l.f132J);
        arrayList.add(C.l.f134L);
        arrayList.add(C.l.f138P);
        arrayList.add(C.l.f140R);
        arrayList.add(C.l.f145W);
        arrayList.add(C.l.f136N);
        arrayList.add(C.l.f152d);
        arrayList.add(C.c.f82b);
        arrayList.add(C.l.f143U);
        arrayList.add(C.j.f118b);
        arrayList.add(C.i.f116b);
        arrayList.add(C.l.f141S);
        arrayList.add(C.a.f76c);
        arrayList.add(C.l.f150b);
        arrayList.add(new C.b(cVar));
        arrayList.add(new C.f(cVar, z3));
        C.d dVar2 = new C.d(cVar);
        this.f8304d = dVar2;
        arrayList.add(dVar2);
        arrayList.add(C.l.f148Z);
        arrayList.add(new C.h(cVar, interfaceC0530c, dVar, dVar2));
        this.f8305e = Collections.unmodifiableList(arrayList);
    }
}
