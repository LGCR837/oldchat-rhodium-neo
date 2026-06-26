package j;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f5467a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f5468b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public e f5469c;

    public final class a implements Iterator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f5470a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5471b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5472c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f5473d = false;

        public a(int i2) {
            this.f5470a = i2;
            this.f5471b = g.this.d();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f5472c < this.f5471b;
        }

        @Override // java.util.Iterator
        public Object next() {
            Object objB = g.this.b(this.f5472c, this.f5470a);
            this.f5472c++;
            this.f5473d = true;
            return objB;
        }

        @Override // java.util.Iterator
        public void remove() {
            if (!this.f5473d) {
                throw new IllegalStateException();
            }
            int i2 = this.f5472c - 1;
            this.f5472c = i2;
            this.f5471b--;
            this.f5473d = false;
            g.this.h(i2);
        }
    }

    public final class b implements Set {
        public b() {
        }

        @Override // java.util.Set, java.util.Collection
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public boolean add(Map.Entry entry) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean addAll(Collection collection) {
            int iD = g.this.d();
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                g.this.g(entry.getKey(), entry.getValue());
            }
            return iD != g.this.d();
        }

        @Override // java.util.Set, java.util.Collection
        public void clear() {
            g.this.a();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            int iE = g.this.e(entry.getKey());
            if (iE < 0) {
                return false;
            }
            return j.b.c(g.this.b(iE, 1), entry.getValue());
        }

        @Override // java.util.Set, java.util.Collection
        public boolean containsAll(Collection collection) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean equals(Object obj) {
            return g.k(this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public int hashCode() {
            int iHashCode = 0;
            for (int iD = g.this.d() - 1; iD >= 0; iD--) {
                Object objB = g.this.b(iD, 0);
                Object objB2 = g.this.b(iD, 1);
                iHashCode += (objB == null ? 0 : objB.hashCode()) ^ (objB2 == null ? 0 : objB2.hashCode());
            }
            return iHashCode;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return g.this.d() == 0;
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return g.this.new d();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean removeAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean retainAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public int size() {
            return g.this.d();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray(Object[] objArr) {
            throw new UnsupportedOperationException();
        }
    }

    public final class c implements Set {
        public c() {
        }

        @Override // java.util.Set, java.util.Collection
        public boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean addAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public void clear() {
            g.this.a();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean contains(Object obj) {
            return g.this.e(obj) >= 0;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean containsAll(Collection collection) {
            return g.j(g.this.c(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean equals(Object obj) {
            return g.k(this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public int hashCode() {
            int iHashCode = 0;
            for (int iD = g.this.d() - 1; iD >= 0; iD--) {
                Object objB = g.this.b(iD, 0);
                iHashCode += objB == null ? 0 : objB.hashCode();
            }
            return iHashCode;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return g.this.d() == 0;
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return g.this.new a(0);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean remove(Object obj) {
            int iE = g.this.e(obj);
            if (iE < 0) {
                return false;
            }
            g.this.h(iE);
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean removeAll(Collection collection) {
            return g.o(g.this.c(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean retainAll(Collection collection) {
            return g.p(g.this.c(), collection);
        }

        @Override // java.util.Set, java.util.Collection
        public int size() {
            return g.this.d();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray() {
            return g.this.q(0);
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray(Object[] objArr) {
            return g.this.r(objArr, 0);
        }
    }

    public final class d implements Iterator, Map.Entry {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5477a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f5479c = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5478b = -1;

        public d() {
            this.f5477a = g.this.d() - 1;
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Map.Entry next() {
            this.f5478b++;
            this.f5479c = true;
            return this;
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            if (!this.f5479c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return j.b.c(entry.getKey(), g.this.b(this.f5478b, 0)) && j.b.c(entry.getValue(), g.this.b(this.f5478b, 1));
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            if (this.f5479c) {
                return g.this.b(this.f5478b, 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            if (this.f5479c) {
                return g.this.b(this.f5478b, 1);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f5478b < this.f5477a;
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            if (!this.f5479c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            Object objB = g.this.b(this.f5478b, 0);
            Object objB2 = g.this.b(this.f5478b, 1);
            return (objB == null ? 0 : objB.hashCode()) ^ (objB2 != null ? objB2.hashCode() : 0);
        }

        @Override // java.util.Iterator
        public void remove() {
            if (!this.f5479c) {
                throw new IllegalStateException();
            }
            g.this.h(this.f5478b);
            this.f5478b--;
            this.f5477a--;
            this.f5479c = false;
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            if (this.f5479c) {
                return g.this.i(this.f5478b, obj);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }

    public final class e implements Collection {
        public e() {
        }

        @Override // java.util.Collection
        public boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean addAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public void clear() {
            g.this.a();
        }

        @Override // java.util.Collection
        public boolean contains(Object obj) {
            return g.this.f(obj) >= 0;
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection collection) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            return g.this.d() == 0;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return g.this.new a(1);
        }

        @Override // java.util.Collection
        public boolean remove(Object obj) {
            int iF = g.this.f(obj);
            if (iF < 0) {
                return false;
            }
            g.this.h(iF);
            return true;
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection collection) {
            int iD = g.this.d();
            int i2 = 0;
            boolean z2 = false;
            while (i2 < iD) {
                if (collection.contains(g.this.b(i2, 1))) {
                    g.this.h(i2);
                    i2--;
                    iD--;
                    z2 = true;
                }
                i2++;
            }
            return z2;
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection collection) {
            int iD = g.this.d();
            int i2 = 0;
            boolean z2 = false;
            while (i2 < iD) {
                if (!collection.contains(g.this.b(i2, 1))) {
                    g.this.h(i2);
                    i2--;
                    iD--;
                    z2 = true;
                }
                i2++;
            }
            return z2;
        }

        @Override // java.util.Collection
        public int size() {
            return g.this.d();
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            return g.this.q(1);
        }

        @Override // java.util.Collection
        public Object[] toArray(Object[] objArr) {
            return g.this.r(objArr, 1);
        }
    }

    public static boolean j(Map map, Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!map.containsKey(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static boolean k(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() == set2.size()) {
                    if (set.containsAll(set2)) {
                        return true;
                    }
                }
                return false;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public static boolean o(Map map, Collection collection) {
        int size = map.size();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            map.remove(it.next());
        }
        return size != map.size();
    }

    public static boolean p(Map map, Collection collection) {
        int size = map.size();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    public abstract void a();

    public abstract Object b(int i2, int i3);

    public abstract Map c();

    public abstract int d();

    public abstract int e(Object obj);

    public abstract int f(Object obj);

    public abstract void g(Object obj, Object obj2);

    public abstract void h(int i2);

    public abstract Object i(int i2, Object obj);

    public Set l() {
        if (this.f5467a == null) {
            this.f5467a = new b();
        }
        return this.f5467a;
    }

    public Set m() {
        if (this.f5468b == null) {
            this.f5468b = new c();
        }
        return this.f5468b;
    }

    public Collection n() {
        if (this.f5469c == null) {
            this.f5469c = new e();
        }
        return this.f5469c;
    }

    public Object[] q(int i2) {
        int iD = d();
        Object[] objArr = new Object[iD];
        for (int i3 = 0; i3 < iD; i3++) {
            objArr[i3] = b(i3, i2);
        }
        return objArr;
    }

    public Object[] r(Object[] objArr, int i2) {
        int iD = d();
        if (objArr.length < iD) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), iD);
        }
        for (int i3 = 0; i3 < iD; i3++) {
            objArr[i3] = b(i3, i2);
        }
        if (objArr.length > iD) {
            objArr[iD] = null;
        }
        return objArr;
    }
}
