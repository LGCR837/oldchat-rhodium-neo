package C;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import z.InterfaceC0530c;
import z.m;
import z.o;
import z.p;

/* JADX INFO: loaded from: classes.dex */
public final class h implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final B.c f99a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC0530c f100b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final B.d f101c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f102d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final E.b f103e = E.b.a();

    public class a extends c {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Field f104d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ boolean f105e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ o f106f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ z.e f107g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ F.a f108h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ boolean f109i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, boolean z2, boolean z3, Field field, boolean z4, o oVar, z.e eVar, F.a aVar, boolean z5) {
            super(str, z2, z3);
            this.f104d = field;
            this.f105e = z4;
            this.f106f = oVar;
            this.f107g = eVar;
            this.f108h = aVar;
            this.f109i = z5;
        }

        @Override // C.h.c
        public void a(G.a aVar, Object obj) throws IllegalAccessException {
            Object objB = this.f106f.b(aVar);
            if (objB == null && this.f109i) {
                return;
            }
            this.f104d.set(obj, objB);
        }

        @Override // C.h.c
        public void b(G.c cVar, Object obj) throws IllegalAccessException {
            (this.f105e ? this.f106f : new k(this.f107g, this.f106f, this.f108h.e())).d(cVar, this.f104d.get(obj));
        }

        @Override // C.h.c
        public boolean c(Object obj) {
            return this.f114b && this.f104d.get(obj) != obj;
        }
    }

    public static final class b extends o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final B.i f111a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map f112b;

        public b(B.i iVar, Map map) {
            this.f111a = iVar;
            this.f112b = map;
        }

        @Override // z.o
        public Object b(G.a aVar) throws IOException {
            if (aVar.x() == G.b.NULL) {
                aVar.t();
                return null;
            }
            Object objA = this.f111a.a();
            try {
                aVar.b();
                while (aVar.j()) {
                    c cVar = (c) this.f112b.get(aVar.r());
                    if (cVar == null || !cVar.f115c) {
                        aVar.H();
                    } else {
                        cVar.a(aVar, objA);
                    }
                }
                aVar.g();
                return objA;
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (IllegalStateException e3) {
                throw new m(e3);
            }
        }

        @Override // z.o
        public void d(G.c cVar, Object obj) throws IOException {
            if (obj == null) {
                cVar.m();
                return;
            }
            cVar.d();
            try {
                for (c cVar2 : this.f112b.values()) {
                    if (cVar2.c(obj)) {
                        cVar.k(cVar2.f113a);
                        cVar2.b(cVar, obj);
                    }
                }
                cVar.g();
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f113a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f114b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f115c;

        public c(String str, boolean z2, boolean z3) {
            this.f113a = str;
            this.f114b = z2;
            this.f115c = z3;
        }

        public abstract void a(G.a aVar, Object obj);

        public abstract void b(G.c cVar, Object obj);

        public abstract boolean c(Object obj);
    }

    public h(B.c cVar, InterfaceC0530c interfaceC0530c, B.d dVar, d dVar2) {
        this.f99a = cVar;
        this.f100b = interfaceC0530c;
        this.f101c = dVar;
        this.f102d = dVar2;
    }

    public static boolean d(Field field, boolean z2, B.d dVar) {
        return (dVar.c(field.getType(), z2) || dVar.f(field, z2)) ? false : true;
    }

    @Override // z.p
    public o a(z.e eVar, F.a aVar) {
        Class clsD = aVar.d();
        if (Object.class.isAssignableFrom(clsD)) {
            return new b(this.f99a.a(aVar), e(eVar, aVar, clsD));
        }
        return null;
    }

    public final c b(z.e eVar, Field field, String str, F.a aVar, boolean z2, boolean z3) {
        boolean zB = B.k.b(aVar.d());
        A.b bVar = (A.b) field.getAnnotation(A.b.class);
        o oVarB = bVar != null ? this.f102d.b(this.f99a, eVar, aVar, bVar) : null;
        boolean z4 = oVarB != null;
        if (oVarB == null) {
            oVarB = eVar.k(aVar);
        }
        return new a(str, z2, z3, field, z4, oVarB, eVar, aVar, zB);
    }

    public boolean c(Field field, boolean z2) {
        return d(field, z2, this.f101c);
    }

    public final Map e(z.e eVar, F.a aVar, Class cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type typeE = aVar.e();
        F.a aVarB = aVar;
        Class clsD = cls;
        while (clsD != Object.class) {
            Field[] declaredFields = clsD.getDeclaredFields();
            int length = declaredFields.length;
            boolean z2 = false;
            int i2 = 0;
            while (i2 < length) {
                Field field = declaredFields[i2];
                boolean zC = c(field, true);
                boolean zC2 = c(field, z2);
                if (zC || zC2) {
                    this.f103e.b(field);
                    Type typeP = B.b.p(aVarB.e(), clsD, field.getGenericType());
                    List listF = f(field);
                    int size = listF.size();
                    c cVar = null;
                    int i3 = 0;
                    while (i3 < size) {
                        String str = (String) listF.get(i3);
                        boolean z3 = i3 != 0 ? false : zC;
                        int i4 = i3;
                        c cVar2 = cVar;
                        int i5 = size;
                        List list = listF;
                        Field field2 = field;
                        cVar = cVar2 == null ? (c) linkedHashMap.put(str, b(eVar, field, str, F.a.b(typeP), z3, zC2)) : cVar2;
                        i3 = i4 + 1;
                        zC = z3;
                        listF = list;
                        size = i5;
                        field = field2;
                    }
                    c cVar3 = cVar;
                    if (cVar3 != null) {
                        throw new IllegalArgumentException(typeE + " declares multiple JSON fields named " + cVar3.f113a);
                    }
                }
                i2++;
                z2 = false;
            }
            aVarB = F.a.b(B.b.p(aVarB.e(), clsD, clsD.getGenericSuperclass()));
            clsD = aVarB.d();
        }
        return linkedHashMap;
    }

    public final List f(Field field) {
        A.c cVar = (A.c) field.getAnnotation(A.c.class);
        if (cVar == null) {
            return Collections.singletonList(this.f100b.a(field));
        }
        String strValue = cVar.value();
        String[] strArrAlternate = cVar.alternate();
        if (strArrAlternate.length == 0) {
            return Collections.singletonList(strValue);
        }
        ArrayList arrayList = new ArrayList(strArrAlternate.length + 1);
        arrayList.add(strValue);
        for (String str : strArrAlternate) {
            arrayList.add(str);
        }
        return arrayList;
    }
}
