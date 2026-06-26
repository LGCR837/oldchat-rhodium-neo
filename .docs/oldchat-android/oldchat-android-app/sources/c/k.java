package C;

import C.h;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import z.o;

/* JADX INFO: loaded from: classes.dex */
public final class k extends o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z.e f120a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o f121b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Type f122c;

    public k(z.e eVar, o oVar, Type type) {
        this.f120a = eVar;
        this.f121b = oVar;
        this.f122c = type;
    }

    @Override // z.o
    public Object b(G.a aVar) {
        return this.f121b.b(aVar);
    }

    @Override // z.o
    public void d(G.c cVar, Object obj) {
        o oVarK = this.f121b;
        Type typeE = e(this.f122c, obj);
        if (typeE != this.f122c) {
            oVarK = this.f120a.k(F.a.b(typeE));
            if (oVarK instanceof h.b) {
                o oVar = this.f121b;
                if (!(oVar instanceof h.b)) {
                    oVarK = oVar;
                }
            }
        }
        oVarK.d(cVar, obj);
    }

    public final Type e(Type type, Object obj) {
        return obj != null ? (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type : type;
    }
}
