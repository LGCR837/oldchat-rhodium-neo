package C;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import z.m;
import z.o;
import z.p;

/* JADX INFO: loaded from: classes.dex */
public final class f implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final B.c f90a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f91b;

    public final class a extends o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final o f92a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final o f93b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final B.i f94c;

        public a(z.e eVar, Type type, o oVar, Type type2, o oVar2, B.i iVar) {
            this.f92a = new k(eVar, oVar, type);
            this.f93b = new k(eVar, oVar2, type2);
            this.f94c = iVar;
        }

        public final String e(z.g gVar) {
            if (!gVar.g()) {
                if (gVar.e()) {
                    return "null";
                }
                throw new AssertionError();
            }
            z.l lVarC = gVar.c();
            if (lVarC.n()) {
                return String.valueOf(lVarC.j());
            }
            if (lVarC.l()) {
                return Boolean.toString(lVarC.h());
            }
            if (lVarC.p()) {
                return lVarC.k();
            }
            throw new AssertionError();
        }

        @Override // z.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public Map b(G.a aVar) throws IOException {
            G.b bVarX = aVar.x();
            if (bVarX == G.b.NULL) {
                aVar.t();
                return null;
            }
            Map map = (Map) this.f94c.a();
            if (bVarX == G.b.BEGIN_ARRAY) {
                aVar.a();
                while (aVar.j()) {
                    aVar.a();
                    Object objB = this.f92a.b(aVar);
                    if (map.put(objB, this.f93b.b(aVar)) != null) {
                        throw new m("duplicate key: " + objB);
                    }
                    aVar.f();
                }
                aVar.f();
            } else {
                aVar.b();
                while (aVar.j()) {
                    B.f.f40a.a(aVar);
                    Object objB2 = this.f92a.b(aVar);
                    if (map.put(objB2, this.f93b.b(aVar)) != null) {
                        throw new m("duplicate key: " + objB2);
                    }
                }
                aVar.g();
            }
            return map;
        }

        @Override // z.o
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public void d(G.c cVar, Map map) throws IOException {
            if (map == null) {
                cVar.m();
                return;
            }
            if (!f.this.f91b) {
                cVar.d();
                for (Map.Entry entry : map.entrySet()) {
                    cVar.k(String.valueOf(entry.getKey()));
                    this.f93b.d(cVar, entry.getValue());
                }
                cVar.g();
                return;
            }
            ArrayList arrayList = new ArrayList(map.size());
            ArrayList arrayList2 = new ArrayList(map.size());
            int i2 = 0;
            boolean z2 = false;
            for (Map.Entry entry2 : map.entrySet()) {
                z.g gVarC = this.f92a.c(entry2.getKey());
                arrayList.add(gVarC);
                arrayList2.add(entry2.getValue());
                z2 |= gVarC.d() || gVarC.f();
            }
            if (!z2) {
                cVar.d();
                int size = arrayList.size();
                while (i2 < size) {
                    cVar.k(e((z.g) arrayList.get(i2)));
                    this.f93b.d(cVar, arrayList2.get(i2));
                    i2++;
                }
                cVar.g();
                return;
            }
            cVar.c();
            int size2 = arrayList.size();
            while (i2 < size2) {
                cVar.c();
                B.l.a((z.g) arrayList.get(i2), cVar);
                this.f93b.d(cVar, arrayList2.get(i2));
                cVar.f();
                i2++;
            }
            cVar.f();
        }
    }

    public f(B.c cVar, boolean z2) {
        this.f90a = cVar;
        this.f91b = z2;
    }

    @Override // z.p
    public o a(z.e eVar, F.a aVar) {
        Type typeE = aVar.e();
        if (!Map.class.isAssignableFrom(aVar.d())) {
            return null;
        }
        Type[] typeArrJ = B.b.j(typeE, B.b.k(typeE));
        return new a(eVar, typeArrJ[0], b(eVar, typeArrJ[0]), typeArrJ[1], eVar.k(F.a.b(typeArrJ[1])), this.f90a.a(aVar));
    }

    public final o b(z.e eVar, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? l.f154f : eVar.k(F.a.b(type));
    }
}
