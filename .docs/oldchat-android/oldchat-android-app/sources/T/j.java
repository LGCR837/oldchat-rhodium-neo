package T;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class j extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p[] f515a;

    public j(Map map) {
        Collection collection = map == null ? null : (Collection) map.get(H.e.POSSIBLE_FORMATS);
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(H.a.EAN_13)) {
                arrayList.add(new e());
            } else if (collection.contains(H.a.UPC_A)) {
                arrayList.add(new l());
            }
            if (collection.contains(H.a.EAN_8)) {
                arrayList.add(new f());
            }
            if (collection.contains(H.a.UPC_E)) {
                arrayList.add(new q());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new e());
            arrayList.add(new f());
            arrayList.add(new q());
        }
        this.f515a = (p[]) arrayList.toArray(new p[arrayList.size()]);
    }

    @Override // T.k
    public H.n b(int i2, L.a aVar, Map map) throws H.j {
        int[] iArrO = p.o(aVar);
        for (p pVar : this.f515a) {
            try {
                H.n nVarL = pVar.l(i2, aVar, iArrO, map);
                boolean z2 = nVarL.b() == H.a.EAN_13 && nVarL.f().charAt(0) == '0';
                Collection collection = map == null ? null : (Collection) map.get(H.e.POSSIBLE_FORMATS);
                boolean z3 = collection == null || collection.contains(H.a.UPC_A);
                if (!z2 || !z3) {
                    return nVarL;
                }
                H.n nVar = new H.n(nVarL.f().substring(1), nVarL.c(), nVarL.e(), H.a.UPC_A);
                nVar.g(nVarL.d());
                return nVar;
            } catch (H.m unused) {
            }
        }
        throw H.j.a();
    }
}
