package O;

import H.c;
import H.j;
import H.l;
import H.n;
import H.o;
import H.p;
import L.b;
import L.e;
import L.g;
import P.d;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class a implements l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final p[] f442b = new p[0];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f443a = new d();

    public static b b(b bVar) throws j {
        int[] iArrI = bVar.i();
        int[] iArrE = bVar.e();
        if (iArrI == null || iArrE == null) {
            throw j.a();
        }
        int iC = c(iArrI, bVar);
        int i2 = iArrI[1];
        int i3 = iArrE[1];
        int i4 = iArrI[0];
        int i5 = ((iArrE[0] - i4) + 1) / iC;
        int i6 = ((i3 - i2) + 1) / iC;
        if (i5 <= 0 || i6 <= 0) {
            throw j.a();
        }
        int i7 = iC / 2;
        int i8 = i2 + i7;
        int i9 = i4 + i7;
        b bVar2 = new b(i5, i6);
        for (int i10 = 0; i10 < i6; i10++) {
            int i11 = (i10 * iC) + i8;
            for (int i12 = 0; i12 < i5; i12++) {
                if (bVar.d((i12 * iC) + i9, i11)) {
                    bVar2.l(i12, i10);
                }
            }
        }
        return bVar2;
    }

    public static int c(int[] iArr, b bVar) throws j {
        int iJ = bVar.j();
        int i2 = iArr[0];
        int i3 = iArr[1];
        while (i2 < iJ && bVar.d(i2, i3)) {
            i2++;
        }
        if (i2 == iJ) {
            throw j.a();
        }
        int i4 = i2 - iArr[0];
        if (i4 != 0) {
            return i4;
        }
        throw j.a();
    }

    @Override // H.l
    public n a(c cVar, Map map) throws j, H.d {
        p[] pVarArrB;
        e eVarB;
        if (map == null || !map.containsKey(H.e.PURE_BARCODE)) {
            g gVarC = new Q.a(cVar.a()).c();
            e eVarB2 = this.f443a.b(gVarC.a());
            pVarArrB = gVarC.b();
            eVarB = eVarB2;
        } else {
            eVarB = this.f443a.b(b(cVar.a()));
            pVarArrB = f442b;
        }
        n nVar = new n(eVarB.h(), eVarB.e(), pVarArrB, H.a.DATA_MATRIX);
        List listA = eVarB.a();
        if (listA != null) {
            nVar.h(o.BYTE_SEGMENTS, listA);
        }
        String strB = eVarB.b();
        if (strB != null) {
            nVar.h(o.ERROR_CORRECTION_LEVEL, strB);
        }
        return nVar;
    }
}
