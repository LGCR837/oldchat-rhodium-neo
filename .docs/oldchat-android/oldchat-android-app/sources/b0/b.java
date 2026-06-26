package b0;

import e0.c;
import e0.f;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static L.b c(f fVar, int i2, int i3, int i4) {
        e0.b bVarA = fVar.a();
        if (bVarA == null) {
            throw new IllegalStateException();
        }
        int iE = bVarA.e();
        int iD = bVarA.d();
        int i5 = i4 << 1;
        int i6 = iE + i5;
        int i7 = i5 + iD;
        int iMax = Math.max(i2, i6);
        int iMax2 = Math.max(i3, i7);
        int iMin = Math.min(iMax / i6, iMax2 / i7);
        int i8 = (iMax - (iE * iMin)) / 2;
        int i9 = (iMax2 - (iD * iMin)) / 2;
        L.b bVar = new L.b(iMax, iMax2);
        int i10 = 0;
        while (i10 < iD) {
            int i11 = i8;
            int i12 = 0;
            while (i12 < iE) {
                if (bVarA.b(i12, i10) == 1) {
                    bVar.m(i11, i9, iMin, iMin);
                }
                i12++;
                i11 += iMin;
            }
            i10++;
            i9 += iMin;
        }
        return bVar;
    }

    public L.b a(String str, H.a aVar, int i2, int i3) {
        return b(str, aVar, i2, i3, null);
    }

    public L.b b(String str, H.a aVar, int i2, int i3, Map map) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (aVar != H.a.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got ".concat(String.valueOf(aVar)));
        }
        if (i2 < 0 || i3 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i2 + 'x' + i3);
        }
        c0.f fVarValueOf = c0.f.L;
        int i4 = 4;
        if (map != null) {
            H.f fVar = H.f.ERROR_CORRECTION;
            if (map.containsKey(fVar)) {
                fVarValueOf = c0.f.valueOf(map.get(fVar).toString());
            }
            H.f fVar2 = H.f.MARGIN;
            if (map.containsKey(fVar2)) {
                i4 = Integer.parseInt(map.get(fVar2).toString());
            }
        }
        return c(c.n(str, fVarValueOf, map), i2, i3, i4);
    }
}
