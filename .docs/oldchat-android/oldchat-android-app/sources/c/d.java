package C;

import z.o;
import z.p;

/* JADX INFO: loaded from: classes.dex */
public final class d implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final B.c f84a;

    public d(B.c cVar) {
        this.f84a = cVar;
    }

    @Override // z.p
    public o a(z.e eVar, F.a aVar) {
        A.b bVar = (A.b) aVar.d().getAnnotation(A.b.class);
        if (bVar == null) {
            return null;
        }
        return b(this.f84a, eVar, aVar, bVar);
    }

    public o b(B.c cVar, z.e eVar, F.a aVar, A.b bVar) {
        o oVarA;
        Object objA = cVar.a(F.a.a(bVar.value())).a();
        if (objA instanceof o) {
            oVarA = (o) objA;
        } else {
            if (!(objA instanceof p)) {
                throw new IllegalArgumentException("Invalid attempt to bind an instance of " + objA.getClass().getName() + " as a @JsonAdapter for " + aVar.toString() + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
            }
            oVarA = ((p) objA).a(eVar, aVar);
        }
        return (oVarA == null || !bVar.nullSafe()) ? oVarA : oVarA.a();
    }
}
