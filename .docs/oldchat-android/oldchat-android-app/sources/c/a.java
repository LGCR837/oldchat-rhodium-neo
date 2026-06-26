package C;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import z.o;
import z.p;

/* JADX INFO: loaded from: classes.dex */
public final class a extends o {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final p f76c = new C0003a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f77a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o f78b;

    /* JADX INFO: renamed from: C.a$a, reason: collision with other inner class name */
    public static class C0003a implements p {
        @Override // z.p
        public o a(z.e eVar, F.a aVar) {
            Type typeE = aVar.e();
            if (!(typeE instanceof GenericArrayType) && (!(typeE instanceof Class) || !((Class) typeE).isArray())) {
                return null;
            }
            Type typeG = B.b.g(typeE);
            return new a(eVar, eVar.k(F.a.b(typeG)), B.b.k(typeG));
        }
    }

    public a(z.e eVar, o oVar, Class cls) {
        this.f78b = new k(eVar, oVar, cls);
        this.f77a = cls;
    }

    @Override // z.o
    public Object b(G.a aVar) throws IOException {
        if (aVar.x() == G.b.NULL) {
            aVar.t();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        aVar.a();
        while (aVar.j()) {
            arrayList.add(this.f78b.b(aVar));
        }
        aVar.f();
        int size = arrayList.size();
        Object objNewInstance = Array.newInstance((Class<?>) this.f77a, size);
        for (int i2 = 0; i2 < size; i2++) {
            Array.set(objNewInstance, i2, arrayList.get(i2));
        }
        return objNewInstance;
    }

    @Override // z.o
    public void d(G.c cVar, Object obj) throws IOException {
        if (obj == null) {
            cVar.m();
            return;
        }
        cVar.c();
        int length = Array.getLength(obj);
        for (int i2 = 0; i2 < length; i2++) {
            this.f78b.d(cVar, Array.get(obj, i2));
        }
        cVar.f();
    }
}
