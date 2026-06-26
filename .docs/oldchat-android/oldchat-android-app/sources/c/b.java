package C;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import z.o;
import z.p;

/* JADX INFO: loaded from: classes.dex */
public final class b implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final B.c f79a;

    public static final class a extends o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final o f80a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final B.i f81b;

        public a(z.e eVar, Type type, o oVar, B.i iVar) {
            this.f80a = new k(eVar, oVar, type);
            this.f81b = iVar;
        }

        @Override // z.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Collection b(G.a aVar) throws IOException {
            if (aVar.x() == G.b.NULL) {
                aVar.t();
                return null;
            }
            Collection collection = (Collection) this.f81b.a();
            aVar.a();
            while (aVar.j()) {
                collection.add(this.f80a.b(aVar));
            }
            aVar.f();
            return collection;
        }

        @Override // z.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(G.c cVar, Collection collection) throws IOException {
            if (collection == null) {
                cVar.m();
                return;
            }
            cVar.c();
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                this.f80a.d(cVar, it.next());
            }
            cVar.f();
        }
    }

    public b(B.c cVar) {
        this.f79a = cVar;
    }

    @Override // z.p
    public o a(z.e eVar, F.a aVar) {
        Type typeE = aVar.e();
        Class clsD = aVar.d();
        if (!Collection.class.isAssignableFrom(clsD)) {
            return null;
        }
        Type typeH = B.b.h(typeE, clsD);
        return new a(eVar, typeH, eVar.k(F.a.b(typeH)), this.f79a.a(aVar));
    }
}
