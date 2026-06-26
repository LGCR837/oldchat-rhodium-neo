package j;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: j.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0252a extends j implements Map {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public g f5447h;

    /* JADX INFO: renamed from: j.a$a, reason: collision with other inner class name */
    public class C0043a extends g {
        public C0043a() {
        }

        @Override // j.g
        public void a() {
            C0252a.this.clear();
        }

        @Override // j.g
        public Object b(int i2, int i3) {
            return C0252a.this.f5489b[(i2 << 1) + i3];
        }

        @Override // j.g
        public Map c() {
            return C0252a.this;
        }

        @Override // j.g
        public int d() {
            return C0252a.this.f5490c;
        }

        @Override // j.g
        public int e(Object obj) {
            return C0252a.this.e(obj);
        }

        @Override // j.g
        public int f(Object obj) {
            return C0252a.this.g(obj);
        }

        @Override // j.g
        public void g(Object obj, Object obj2) {
            C0252a.this.put(obj, obj2);
        }

        @Override // j.g
        public void h(int i2) {
            C0252a.this.i(i2);
        }

        @Override // j.g
        public Object i(int i2, Object obj) {
            return C0252a.this.j(i2, obj);
        }
    }

    public C0252a() {
    }

    @Override // java.util.Map
    public Set entrySet() {
        return l().l();
    }

    @Override // java.util.Map
    public Set keySet() {
        return l().m();
    }

    public final g l() {
        if (this.f5447h == null) {
            this.f5447h = new C0043a();
        }
        return this.f5447h;
    }

    public boolean m(Collection collection) {
        return g.p(this, collection);
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        b(this.f5490c + map.size());
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public Collection values() {
        return l().n();
    }

    public C0252a(int i2) {
        super(i2);
    }
}
