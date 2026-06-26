package b0;

import H.c;
import H.j;
import H.l;
import H.n;
import H.o;
import H.p;
import L.g;
import c0.e;
import c0.i;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class a implements l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final p[] f1759b = new p[0];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f1760a = new e();

    private static L.b b(L.b bVar) throws j {
        int[] iArrI = bVar.i();
        int[] iArrE = bVar.e();
        if (iArrI == null || iArrE == null) {
            throw j.a();
        }
        float fC = c(iArrI, bVar);
        int i2 = iArrI[1];
        int i3 = iArrE[1];
        int i4 = iArrI[0];
        int i5 = iArrE[0];
        if (i4 >= i5 || i2 >= i3) {
            throw j.a();
        }
        int i6 = i3 - i2;
        if (i6 != i5 - i4 && (i5 = i4 + i6) >= bVar.j()) {
            throw j.a();
        }
        int iRound = Math.round(((i5 - i4) + 1) / fC);
        int iRound2 = Math.round((i6 + 1) / fC);
        if (iRound <= 0 || iRound2 <= 0) {
            throw j.a();
        }
        if (iRound2 != iRound) {
            throw j.a();
        }
        int i7 = (int) (fC / 2.0f);
        int i8 = i2 + i7;
        int i9 = i4 + i7;
        int i10 = (((int) ((iRound - 1) * fC)) + i9) - i5;
        if (i10 > 0) {
            if (i10 > i7) {
                throw j.a();
            }
            i9 -= i10;
        }
        int i11 = (((int) ((iRound2 - 1) * fC)) + i8) - i3;
        if (i11 > 0) {
            if (i11 > i7) {
                throw j.a();
            }
            i8 -= i11;
        }
        L.b bVar2 = new L.b(iRound, iRound2);
        for (int i12 = 0; i12 < iRound2; i12++) {
            int i13 = ((int) (i12 * fC)) + i8;
            for (int i14 = 0; i14 < iRound; i14++) {
                if (bVar.d(((int) (i14 * fC)) + i9, i13)) {
                    bVar2.l(i14, i12);
                }
            }
        }
        return bVar2;
    }

    public static float c(int[] iArr, L.b bVar) throws j {
        int iG = bVar.g();
        int iJ = bVar.j();
        int i2 = iArr[0];
        boolean z2 = true;
        int i3 = iArr[1];
        int i4 = 0;
        while (i2 < iJ && i3 < iG) {
            if (z2 != bVar.d(i2, i3)) {
                i4++;
                if (i4 == 5) {
                    break;
                }
                z2 = !z2;
            }
            i2++;
            i3++;
        }
        if (i2 == iJ || i3 == iG) {
            throw j.a();
        }
        return (i2 - iArr[0]) / 7.0f;
    }

    @Override // H.l
    public final n a(c cVar, Map map) throws j {
        p[] pVarArrB;
        L.e eVarB;
        if (map == null || !map.containsKey(H.e.PURE_BARCODE)) {
            g gVarE = new d0.c(cVar.a()).e(map);
            L.e eVarB2 = this.f1760a.b(gVarE.a(), map);
            pVarArrB = gVarE.b();
            eVarB = eVarB2;
        } else {
            eVarB = this.f1760a.b(b(cVar.a()), map);
            pVarArrB = f1759b;
        }
        if (eVarB.d() instanceof i) {
            ((i) eVarB.d()).a(pVarArrB);
        }
        n nVar = new n(eVarB.h(), eVarB.e(), pVarArrB, H.a.QR_CODE);
        List listA = eVarB.a();
        if (listA != null) {
            nVar.h(o.BYTE_SEGMENTS, listA);
        }
        String strB = eVarB.b();
        if (strB != null) {
            nVar.h(o.ERROR_CORRECTION_LEVEL, strB);
        }
        if (eVarB.i()) {
            nVar.h(o.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(eVarB.g()));
            nVar.h(o.STRUCTURED_APPEND_PARITY, Integer.valueOf(eVarB.f()));
        }
        return nVar;
    }
}
