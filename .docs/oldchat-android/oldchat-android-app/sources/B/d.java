package B;

import c.AbstractC0177z;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.spongycastle.crypto.tls.CipherSuite;
import z.C0528a;
import z.o;
import z.p;

/* JADX INFO: loaded from: classes.dex */
public final class d implements p, Cloneable {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final d f26g = new d();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f30d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double f27a = -1.0d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f28b = CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f29c = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List f31e = Collections.emptyList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List f32f = Collections.emptyList();

    public class a extends o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public o f33a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f34b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ boolean f35c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ z.e f36d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ F.a f37e;

        public a(boolean z2, boolean z3, z.e eVar, F.a aVar) {
            this.f34b = z2;
            this.f35c = z3;
            this.f36d = eVar;
            this.f37e = aVar;
        }

        @Override // z.o
        public Object b(G.a aVar) throws IOException {
            if (!this.f34b) {
                return e().b(aVar);
            }
            aVar.H();
            return null;
        }

        @Override // z.o
        public void d(G.c cVar, Object obj) throws IOException {
            if (this.f35c) {
                cVar.m();
            } else {
                e().d(cVar, obj);
            }
        }

        public final o e() {
            o oVar = this.f33a;
            if (oVar != null) {
                return oVar;
            }
            o oVarM = this.f36d.m(d.this, this.f37e);
            this.f33a = oVarM;
            return oVarM;
        }
    }

    @Override // z.p
    public o a(z.e eVar, F.a aVar) {
        Class clsD = aVar.d();
        boolean zD = d(clsD);
        boolean z2 = zD || e(clsD, true);
        boolean z3 = zD || e(clsD, false);
        if (z2 || z3) {
            return new a(z3, z2, eVar, aVar);
        }
        return null;
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public d clone() {
        try {
            return (d) super.clone();
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public boolean c(Class cls, boolean z2) {
        return d(cls) || e(cls, z2);
    }

    public final boolean d(Class cls) {
        if (this.f27a == -1.0d || l((A.d) cls.getAnnotation(A.d.class), (A.e) cls.getAnnotation(A.e.class))) {
            return (!this.f29c && h(cls)) || g(cls);
        }
        return true;
    }

    public final boolean e(Class cls, boolean z2) {
        Iterator it = (z2 ? this.f31e : this.f32f).iterator();
        if (!it.hasNext()) {
            return false;
        }
        AbstractC0177z.a(it.next());
        throw null;
    }

    public boolean f(Field field, boolean z2) {
        A.a aVar;
        if ((this.f28b & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.f27a != -1.0d && !l((A.d) field.getAnnotation(A.d.class), (A.e) field.getAnnotation(A.e.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.f30d && ((aVar = (A.a) field.getAnnotation(A.a.class)) == null || (!z2 ? aVar.deserialize() : aVar.serialize()))) {
            return true;
        }
        if ((!this.f29c && h(field.getType())) || g(field.getType())) {
            return true;
        }
        List list = z2 ? this.f31e : this.f32f;
        if (list.isEmpty()) {
            return false;
        }
        new C0528a(field);
        Iterator it = list.iterator();
        if (!it.hasNext()) {
            return false;
        }
        AbstractC0177z.a(it.next());
        throw null;
    }

    public final boolean g(Class cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    public final boolean h(Class cls) {
        return cls.isMemberClass() && !i(cls);
    }

    public final boolean i(Class cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    public final boolean j(A.d dVar) {
        return dVar == null || dVar.value() <= this.f27a;
    }

    public final boolean k(A.e eVar) {
        return eVar == null || eVar.value() > this.f27a;
    }

    public final boolean l(A.d dVar, A.e eVar) {
        return j(dVar) && k(eVar);
    }
}
