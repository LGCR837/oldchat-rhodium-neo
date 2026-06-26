package I;

import H.c;
import H.g;
import H.j;
import H.l;
import H.n;
import H.o;
import H.p;
import L.e;
import c.AbstractC0177z;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b implements l {
    @Override // H.l
    public n a(c cVar, Map map) throws g, j {
        p[] pVarArrB;
        p[] pVarArrB2;
        g gVar;
        a aVarA;
        K.a aVar = new K.a(cVar.a());
        e eVarC = null;
        try {
            aVarA = aVar.a(false);
            pVarArrB = aVarA.b();
        } catch (g e2) {
            e = e2;
            pVarArrB = null;
        } catch (j e3) {
            e = e3;
            pVarArrB = null;
        }
        try {
            pVarArrB2 = pVarArrB;
            gVar = null;
            eVarC = new J.a().c(aVarA);
            e = null;
        } catch (g e4) {
            e = e4;
            pVarArrB2 = pVarArrB;
            gVar = e;
            e = null;
        } catch (j e5) {
            e = e5;
            pVarArrB2 = pVarArrB;
            gVar = null;
        }
        if (eVarC == null) {
            try {
                a aVarA2 = aVar.a(true);
                pVarArrB2 = aVarA2.b();
                eVarC = new J.a().c(aVarA2);
            } catch (g | j e6) {
                if (e != null) {
                    throw e;
                }
                if (gVar != null) {
                    throw gVar;
                }
                throw e6;
            }
        }
        p[] pVarArr = pVarArrB2;
        if (map != null) {
            AbstractC0177z.a(map.get(H.e.NEED_RESULT_POINT_CALLBACK));
        }
        n nVar = new n(eVarC.h(), eVarC.e(), eVarC.c(), pVarArr, H.a.AZTEC, System.currentTimeMillis());
        List listA = eVarC.a();
        if (listA != null) {
            nVar.h(o.BYTE_SEGMENTS, listA);
        }
        String strB = eVarC.b();
        if (strB != null) {
            nVar.h(o.ERROR_CORRECTION_LEVEL, strB);
        }
        return nVar;
    }
}
