package B;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class h extends AbstractMap implements Serializable {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Comparator f42h = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Comparator f43a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public e f44b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f45c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f46d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e f47e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b f48f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c f49g;

    public static class a implements Comparator {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    public class b extends AbstractSet {

        public class a extends d {
            public a() {
                super();
            }

            @Override // java.util.Iterator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public Map.Entry next() {
                return a();
            }
        }

        public b() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            h.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && h.this.c((Map.Entry) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator iterator() {
            return new a();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            e eVarC;
            if (!(obj instanceof Map.Entry) || (eVarC = h.this.c((Map.Entry) obj)) == null) {
                return false;
            }
            h.this.f(eVarC, true);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return h.this.f45c;
        }
    }

    public final class c extends AbstractSet {

        public class a extends d {
            public a() {
                super();
            }

            @Override // java.util.Iterator
            public Object next() {
                return a().f63f;
            }
        }

        public c() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            h.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return h.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator iterator() {
            return new a();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return h.this.g(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return h.this.f45c;
        }
    }

    public abstract class d implements Iterator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public e f54a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public e f55b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f56c;

        public d() {
            this.f54a = h.this.f47e.f61d;
            this.f56c = h.this.f46d;
        }

        public final e a() {
            e eVar = this.f54a;
            h hVar = h.this;
            if (eVar == hVar.f47e) {
                throw new NoSuchElementException();
            }
            if (hVar.f46d != this.f56c) {
                throw new ConcurrentModificationException();
            }
            this.f54a = eVar.f61d;
            this.f55b = eVar;
            return eVar;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f54a != h.this.f47e;
        }

        @Override // java.util.Iterator
        public final void remove() {
            e eVar = this.f55b;
            if (eVar == null) {
                throw new IllegalStateException();
            }
            h.this.f(eVar, true);
            this.f55b = null;
            this.f56c = h.this.f46d;
        }
    }

    public h() {
        this(f42h);
    }

    public final boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public e b(Object obj, boolean z2) {
        int iCompareTo;
        e eVar;
        Comparator comparator = this.f43a;
        e eVar2 = this.f44b;
        if (eVar2 != null) {
            Comparable comparable = comparator == f42h ? (Comparable) obj : null;
            while (true) {
                iCompareTo = comparable != null ? comparable.compareTo(eVar2.f63f) : comparator.compare(obj, eVar2.f63f);
                if (iCompareTo == 0) {
                    return eVar2;
                }
                e eVar3 = iCompareTo < 0 ? eVar2.f59b : eVar2.f60c;
                if (eVar3 == null) {
                    break;
                }
                eVar2 = eVar3;
            }
        } else {
            iCompareTo = 0;
        }
        if (!z2) {
            return null;
        }
        e eVar4 = this.f47e;
        if (eVar2 != null) {
            eVar = new e(eVar2, obj, eVar4, eVar4.f62e);
            if (iCompareTo < 0) {
                eVar2.f59b = eVar;
            } else {
                eVar2.f60c = eVar;
            }
            e(eVar2, true);
        } else {
            if (comparator == f42h && !(obj instanceof Comparable)) {
                throw new ClassCastException(obj.getClass().getName() + " is not Comparable");
            }
            eVar = new e(eVar2, obj, eVar4, eVar4.f62e);
            this.f44b = eVar;
        }
        this.f45c++;
        this.f46d++;
        return eVar;
    }

    public e c(Map.Entry entry) {
        e eVarD = d(entry.getKey());
        if (eVarD == null || !a(eVarD.f64g, entry.getValue())) {
            return null;
        }
        return eVarD;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.f44b = null;
        this.f45c = 0;
        this.f46d++;
        e eVar = this.f47e;
        eVar.f62e = eVar;
        eVar.f61d = eVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return d(obj) != null;
    }

    public e d(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return b(obj, false);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public final void e(e eVar, boolean z2) {
        while (eVar != null) {
            e eVar2 = eVar.f59b;
            e eVar3 = eVar.f60c;
            int i2 = eVar2 != null ? eVar2.f65h : 0;
            int i3 = eVar3 != null ? eVar3.f65h : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                e eVar4 = eVar3.f59b;
                e eVar5 = eVar3.f60c;
                int i5 = (eVar4 != null ? eVar4.f65h : 0) - (eVar5 != null ? eVar5.f65h : 0);
                if (i5 == -1 || (i5 == 0 && !z2)) {
                    i(eVar);
                } else {
                    j(eVar3);
                    i(eVar);
                }
                if (z2) {
                    return;
                }
            } else if (i4 == 2) {
                e eVar6 = eVar2.f59b;
                e eVar7 = eVar2.f60c;
                int i6 = (eVar6 != null ? eVar6.f65h : 0) - (eVar7 != null ? eVar7.f65h : 0);
                if (i6 == 1 || (i6 == 0 && !z2)) {
                    j(eVar);
                } else {
                    i(eVar2);
                    j(eVar);
                }
                if (z2) {
                    return;
                }
            } else if (i4 == 0) {
                eVar.f65h = i2 + 1;
                if (z2) {
                    return;
                }
            } else {
                eVar.f65h = Math.max(i2, i3) + 1;
                if (!z2) {
                    return;
                }
            }
            eVar = eVar.f58a;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set entrySet() {
        b bVar = this.f48f;
        if (bVar != null) {
            return bVar;
        }
        b bVar2 = new b();
        this.f48f = bVar2;
        return bVar2;
    }

    public void f(e eVar, boolean z2) {
        int i2;
        if (z2) {
            e eVar2 = eVar.f62e;
            eVar2.f61d = eVar.f61d;
            eVar.f61d.f62e = eVar2;
        }
        e eVar3 = eVar.f59b;
        e eVar4 = eVar.f60c;
        e eVar5 = eVar.f58a;
        int i3 = 0;
        if (eVar3 == null || eVar4 == null) {
            if (eVar3 != null) {
                h(eVar, eVar3);
                eVar.f59b = null;
            } else if (eVar4 != null) {
                h(eVar, eVar4);
                eVar.f60c = null;
            } else {
                h(eVar, null);
            }
            e(eVar5, false);
            this.f45c--;
            this.f46d++;
            return;
        }
        e eVarB = eVar3.f65h > eVar4.f65h ? eVar3.b() : eVar4.a();
        f(eVarB, false);
        e eVar6 = eVar.f59b;
        if (eVar6 != null) {
            i2 = eVar6.f65h;
            eVarB.f59b = eVar6;
            eVar6.f58a = eVarB;
            eVar.f59b = null;
        } else {
            i2 = 0;
        }
        e eVar7 = eVar.f60c;
        if (eVar7 != null) {
            i3 = eVar7.f65h;
            eVarB.f60c = eVar7;
            eVar7.f58a = eVarB;
            eVar.f60c = null;
        }
        eVarB.f65h = Math.max(i2, i3) + 1;
        h(eVar, eVarB);
    }

    public e g(Object obj) {
        e eVarD = d(obj);
        if (eVarD != null) {
            f(eVarD, true);
        }
        return eVarD;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        e eVarD = d(obj);
        if (eVarD != null) {
            return eVarD.f64g;
        }
        return null;
    }

    public final void h(e eVar, e eVar2) {
        e eVar3 = eVar.f58a;
        eVar.f58a = null;
        if (eVar2 != null) {
            eVar2.f58a = eVar3;
        }
        if (eVar3 == null) {
            this.f44b = eVar2;
        } else if (eVar3.f59b == eVar) {
            eVar3.f59b = eVar2;
        } else {
            eVar3.f60c = eVar2;
        }
    }

    public final void i(e eVar) {
        e eVar2 = eVar.f59b;
        e eVar3 = eVar.f60c;
        e eVar4 = eVar3.f59b;
        e eVar5 = eVar3.f60c;
        eVar.f60c = eVar4;
        if (eVar4 != null) {
            eVar4.f58a = eVar;
        }
        h(eVar, eVar3);
        eVar3.f59b = eVar;
        eVar.f58a = eVar3;
        int iMax = Math.max(eVar2 != null ? eVar2.f65h : 0, eVar4 != null ? eVar4.f65h : 0) + 1;
        eVar.f65h = iMax;
        eVar3.f65h = Math.max(iMax, eVar5 != null ? eVar5.f65h : 0) + 1;
    }

    public final void j(e eVar) {
        e eVar2 = eVar.f59b;
        e eVar3 = eVar.f60c;
        e eVar4 = eVar2.f59b;
        e eVar5 = eVar2.f60c;
        eVar.f59b = eVar5;
        if (eVar5 != null) {
            eVar5.f58a = eVar;
        }
        h(eVar, eVar2);
        eVar2.f60c = eVar;
        eVar.f58a = eVar2;
        int iMax = Math.max(eVar3 != null ? eVar3.f65h : 0, eVar5 != null ? eVar5.f65h : 0) + 1;
        eVar.f65h = iMax;
        eVar2.f65h = Math.max(iMax, eVar4 != null ? eVar4.f65h : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set keySet() {
        c cVar = this.f49g;
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = new c();
        this.f49g = cVar2;
        return cVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object put(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("key == null");
        }
        e eVarB = b(obj, true);
        Object obj3 = eVarB.f64g;
        eVarB.f64g = obj2;
        return obj3;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        e eVarG = g(obj);
        if (eVarG != null) {
            return eVarG.f64g;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.f45c;
    }

    public h(Comparator comparator) {
        this.f45c = 0;
        this.f46d = 0;
        this.f47e = new e();
        this.f43a = comparator == null ? f42h : comparator;
    }

    public static final class e implements Map.Entry {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public e f58a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public e f59b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public e f60c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public e f61d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public e f62e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final Object f63f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Object f64g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f65h;

        public e() {
            this.f63f = null;
            this.f62e = this;
            this.f61d = this;
        }

        public e a() {
            e eVar = this;
            for (e eVar2 = this.f59b; eVar2 != null; eVar2 = eVar2.f59b) {
                eVar = eVar2;
            }
            return eVar;
        }

        public e b() {
            e eVar = this;
            for (e eVar2 = this.f60c; eVar2 != null; eVar2 = eVar2.f60c) {
                eVar = eVar2;
            }
            return eVar;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = this.f63f;
            if (obj2 == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!obj2.equals(entry.getKey())) {
                return false;
            }
            Object obj3 = this.f64g;
            if (obj3 == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!obj3.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            return this.f63f;
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            return this.f64g;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            Object obj = this.f63f;
            int iHashCode = obj == null ? 0 : obj.hashCode();
            Object obj2 = this.f64g;
            return iHashCode ^ (obj2 != null ? obj2.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            Object obj2 = this.f64g;
            this.f64g = obj;
            return obj2;
        }

        public String toString() {
            return this.f63f + "=" + this.f64g;
        }

        public e(e eVar, Object obj, e eVar2, e eVar3) {
            this.f58a = eVar;
            this.f63f = obj;
            this.f65h = 1;
            this.f61d = eVar2;
            this.f62e = eVar3;
            eVar3.f61d = this;
            eVar2.f62e = this;
        }
    }
}
