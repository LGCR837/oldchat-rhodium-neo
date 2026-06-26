package R;

import H.d;
import H.e;
import H.g;
import H.j;
import H.l;
import H.n;
import H.o;
import H.p;
import L.b;
import S.c;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class a implements l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final p[] f481b = new p[0];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f482a = new c();

    private static b b(b bVar) throws j {
        int[] iArrF = bVar.f();
        if (iArrF == null) {
            throw j.a();
        }
        int i2 = iArrF[0];
        int i3 = iArrF[1];
        int i4 = iArrF[2];
        int i5 = iArrF[3];
        b bVar2 = new b(30, 33);
        for (int i6 = 0; i6 < 33; i6++) {
            int i7 = (((i6 * i5) + (i5 / 2)) / 33) + i3;
            for (int i8 = 0; i8 < 30; i8++) {
                if (bVar.d(((((i8 * i4) + (i4 / 2)) + (((i6 & 1) * i4) / 2)) / 30) + i2, i7)) {
                    bVar2.l(i8, i6);
                }
            }
        }
        return bVar2;
    }

    @Override // H.l
    public n a(H.c cVar, Map map) throws g, j, d {
        if (map == null || !map.containsKey(e.PURE_BARCODE)) {
            throw j.a();
        }
        L.e eVarB = this.f482a.b(b(cVar.a()), map);
        n nVar = new n(eVarB.h(), eVarB.e(), f481b, H.a.MAXICODE);
        String strB = eVarB.b();
        if (strB != null) {
            nVar.h(o.ERROR_CORRECTION_LEVEL, strB);
        }
        return nVar;
    }
}
