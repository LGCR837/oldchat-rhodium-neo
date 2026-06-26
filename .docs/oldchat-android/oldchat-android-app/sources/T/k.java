package T;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class k implements H.l {
    public static float d(int[] iArr, int[] iArr2, float f2) {
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            i2 += iArr[i4];
            i3 += iArr2[i4];
        }
        if (i2 < i3) {
            return Float.POSITIVE_INFINITY;
        }
        float f3 = i2;
        float f4 = f3 / i3;
        float f5 = f2 * f4;
        float f6 = 0.0f;
        for (int i5 = 0; i5 < length; i5++) {
            float f7 = iArr2[i5] * f4;
            float f8 = iArr[i5];
            float f9 = f8 > f7 ? f8 - f7 : f7 - f8;
            if (f9 > f5) {
                return Float.POSITIVE_INFINITY;
            }
            f6 += f9;
        }
        return f6 / f3;
    }

    public static void e(L.a aVar, int i2, int[] iArr) throws H.j {
        int length = iArr.length;
        int i3 = 0;
        Arrays.fill(iArr, 0, length, 0);
        int iK = aVar.k();
        if (i2 >= iK) {
            throw H.j.a();
        }
        boolean z2 = !aVar.g(i2);
        while (i2 < iK) {
            if (aVar.g(i2) == z2) {
                i3++;
                if (i3 == length) {
                    break;
                }
                iArr[i3] = 1;
                z2 = !z2;
            } else {
                iArr[i3] = iArr[i3] + 1;
            }
            i2++;
        }
        if (i3 != length) {
            if (i3 != length - 1 || i2 != iK) {
                throw H.j.a();
            }
        }
    }

    public static void f(L.a aVar, int i2, int[] iArr) throws H.j {
        int length = iArr.length;
        boolean zG = aVar.g(i2);
        while (i2 > 0 && length >= 0) {
            i2--;
            if (aVar.g(i2) != zG) {
                length--;
                zG = !zG;
            }
        }
        if (length >= 0) {
            throw H.j.a();
        }
        e(aVar, i2 + 1, iArr);
    }

    @Override // H.l
    public H.n a(H.c cVar, Map map) throws H.j {
        try {
            return c(cVar, map);
        } catch (H.j e2) {
            if (map == null || !map.containsKey(H.e.TRY_HARDER) || !cVar.e()) {
                throw e2;
            }
            H.c cVarF = cVar.f();
            H.n nVarC = c(cVarF, map);
            Map mapD = nVarC.d();
            int iIntValue = 270;
            if (mapD != null) {
                H.o oVar = H.o.ORIENTATION;
                if (mapD.containsKey(oVar)) {
                    iIntValue = (((Integer) mapD.get(oVar)).intValue() + 270) % 360;
                }
            }
            nVarC.h(H.o.ORIENTATION, Integer.valueOf(iIntValue));
            H.p[] pVarArrE = nVarC.e();
            if (pVarArrE != null) {
                int iC = cVarF.c();
                for (int i2 = 0; i2 < pVarArrE.length; i2++) {
                    pVarArrE[i2] = new H.p((iC - pVarArrE[i2].d()) - 1.0f, pVarArrE[i2].c());
                }
            }
            return nVarC;
        }
    }

    public abstract H.n b(int i2, L.a aVar, Map map);

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0048, code lost:
    
        r3 = r22.b(r11, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004c, code lost:
    
        r12 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00e0, code lost:
    
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final H.n c(H.c r22, java.util.Map r23) throws H.j {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T.k.c(H.c, java.util.Map):H.n");
    }
}
