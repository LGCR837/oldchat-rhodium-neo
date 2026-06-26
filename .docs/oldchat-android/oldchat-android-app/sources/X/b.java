package X;

import H.j;
import H.l;
import H.n;
import H.o;
import H.p;
import L.e;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b implements l {
    public static n[] b(H.c cVar, Map map, boolean z2) {
        ArrayList arrayList = new ArrayList();
        a0.b bVarB = a0.a.b(cVar, map, z2);
        for (p[] pVarArr : bVarB.b()) {
            e eVarI = Y.l.i(bVarB.a(), pVarArr[4], pVarArr[5], pVarArr[6], pVarArr[7], e(pVarArr), c(pVarArr));
            n nVar = new n(eVarI.h(), eVarI.e(), pVarArr, H.a.PDF_417);
            nVar.h(o.ERROR_CORRECTION_LEVEL, eVarI.b());
            c cVar2 = (c) eVarI.d();
            if (cVar2 != null) {
                nVar.h(o.PDF417_EXTRA_METADATA, cVar2);
            }
            arrayList.add(nVar);
        }
        return (n[]) arrayList.toArray(new n[arrayList.size()]);
    }

    public static int c(p[] pVarArr) {
        return Math.max(Math.max(d(pVarArr[0], pVarArr[4]), (d(pVarArr[6], pVarArr[2]) * 17) / 18), Math.max(d(pVarArr[1], pVarArr[5]), (d(pVarArr[7], pVarArr[3]) * 17) / 18));
    }

    public static int d(p pVar, p pVar2) {
        if (pVar == null || pVar2 == null) {
            return 0;
        }
        return (int) Math.abs(pVar.c() - pVar2.c());
    }

    public static int e(p[] pVarArr) {
        return Math.min(Math.min(f(pVarArr[0], pVarArr[4]), (f(pVarArr[6], pVarArr[2]) * 17) / 18), Math.min(f(pVarArr[1], pVarArr[5]), (f(pVarArr[7], pVarArr[3]) * 17) / 18));
    }

    public static int f(p pVar, p pVar2) {
        if (pVar == null || pVar2 == null) {
            return Integer.MAX_VALUE;
        }
        return (int) Math.abs(pVar.c() - pVar2.c());
    }

    @Override // H.l
    public n a(H.c cVar, Map map) throws j {
        n nVar;
        n[] nVarArrB = b(cVar, map, false);
        if (nVarArrB == null || nVarArrB.length == 0 || (nVar = nVarArrB[0]) == null) {
            throw j.a();
        }
        return nVar;
    }
}
