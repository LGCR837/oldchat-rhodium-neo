package C;

import java.io.IOException;
import java.util.ArrayList;
import z.o;
import z.p;

/* JADX INFO: loaded from: classes.dex */
public final class g extends o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final p f96b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z.e f97a;

    public static class a implements p {
        @Override // z.p
        public o a(z.e eVar, F.a aVar) {
            if (aVar.d() == Object.class) {
                return new g(eVar);
            }
            return null;
        }
    }

    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f98a;

        static {
            int[] iArr = new int[G.b.values().length];
            f98a = iArr;
            try {
                iArr[G.b.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f98a[G.b.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f98a[G.b.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f98a[G.b.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f98a[G.b.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f98a[G.b.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public g(z.e eVar) {
        this.f97a = eVar;
    }

    @Override // z.o
    public Object b(G.a aVar) throws IOException {
        switch (b.f98a[aVar.x().ordinal()]) {
            case 1:
                ArrayList arrayList = new ArrayList();
                aVar.a();
                while (aVar.j()) {
                    arrayList.add(b(aVar));
                }
                aVar.f();
                return arrayList;
            case 2:
                B.h hVar = new B.h();
                aVar.b();
                while (aVar.j()) {
                    hVar.put(aVar.r(), b(aVar));
                }
                aVar.g();
                return hVar;
            case 3:
                return aVar.v();
            case 4:
                return Double.valueOf(aVar.o());
            case 5:
                return Boolean.valueOf(aVar.n());
            case 6:
                aVar.t();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    @Override // z.o
    public void d(G.c cVar, Object obj) throws IOException {
        if (obj == null) {
            cVar.m();
            return;
        }
        o oVarL = this.f97a.l(obj.getClass());
        if (!(oVarL instanceof g)) {
            oVarL.d(cVar, obj);
        } else {
            cVar.d();
            cVar.g();
        }
    }
}
