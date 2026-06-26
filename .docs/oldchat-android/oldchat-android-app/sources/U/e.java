package U;

import H.j;
import H.n;
import H.p;
import T.k;
import c.AbstractC0177z;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public final class e extends a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int[] f549i = {1, 10, 34, 70, 126};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int[] f550j = {4, 20, 48, 81};

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int[] f551k = {0, CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384, 961, 2015, 2715};

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final int[] f552l = {0, 336, 1036, 1516};

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final int[] f553m = {8, 6, 4, 3, 1};

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int[] f554n = {2, 4, 6, 8};

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final int[][] f555o = {new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List f556g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List f557h = new ArrayList();

    public static void q(Collection collection, d dVar) {
        if (dVar == null) {
            return;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            d dVar2 = (d) it.next();
            if (dVar2.b() == dVar.b()) {
                dVar2.e();
                return;
            }
        }
        collection.add(dVar);
    }

    public static boolean s(d dVar, d dVar2) {
        int iA = (dVar.a() + (dVar2.a() * 16)) % 79;
        int iC = (dVar.d().c() * 9) + dVar2.d().c();
        if (iC > 72) {
            iC--;
        }
        if (iC > 8) {
            iC--;
        }
        return iA == iC;
    }

    public static n t(d dVar, d dVar2) {
        String strValueOf = String.valueOf((((long) dVar.b()) * 4537077) + ((long) dVar2.b()));
        StringBuilder sb = new StringBuilder(14);
        for (int length = 13 - strValueOf.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(strValueOf);
        int i2 = 0;
        for (int i3 = 0; i3 < 13; i3++) {
            int iCharAt = sb.charAt(i3) - '0';
            if ((i3 & 1) == 0) {
                iCharAt *= 3;
            }
            i2 += iCharAt;
        }
        int i4 = 10 - (i2 % 10);
        if (i4 == 10) {
            i4 = 0;
        }
        sb.append(i4);
        p[] pVarArrA = dVar.d().a();
        p[] pVarArrA2 = dVar2.d().a();
        return new n(sb.toString(), null, new p[]{pVarArrA[0], pVarArrA[1], pVarArrA2[0], pVarArrA2[1]}, H.a.RSS_14);
    }

    @Override // T.k
    public n b(int i2, L.a aVar, Map map) throws j {
        q(this.f556g, v(aVar, false, i2, map));
        aVar.o();
        q(this.f557h, v(aVar, true, i2, map));
        aVar.o();
        for (d dVar : this.f556g) {
            if (dVar.c() > 1) {
                for (d dVar2 : this.f557h) {
                    if (dVar2.c() > 1 && s(dVar, dVar2)) {
                        return t(dVar, dVar2);
                    }
                }
            }
        }
        throw j.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002a A[PHI: r6 r7
  0x002a: PHI (r6v7 boolean) = (r6v3 boolean), (r6v13 boolean) binds: [B:28:0x0043, B:13:0x0028] A[DONT_GENERATE, DONT_INLINE]
  0x002a: PHI (r7v5 boolean) = (r7v1 boolean), (r7v11 boolean) binds: [B:28:0x0043, B:13:0x0028] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002d A[PHI: r6 r7
  0x002d: PHI (r6v5 boolean) = (r6v3 boolean), (r6v13 boolean) binds: [B:28:0x0043, B:13:0x0028] A[DONT_GENERATE, DONT_INLINE]
  0x002d: PHI (r7v3 boolean) = (r7v1 boolean), (r7v11 boolean) binds: [B:28:0x0043, B:13:0x0028] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void r(boolean r10, int r11) throws H.j {
        /*
            Method dump skipped, instruction units count: 233
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: U.e.r(boolean, int):void");
    }

    public final b u(L.a aVar, c cVar, boolean z2) throws j {
        int[] iArrH = h();
        for (int i2 = 0; i2 < iArrH.length; i2++) {
            iArrH[i2] = 0;
        }
        if (z2) {
            k.f(aVar, cVar.b()[0], iArrH);
        } else {
            k.e(aVar, cVar.b()[1] + 1, iArrH);
            int i3 = 0;
            for (int length = iArrH.length - 1; i3 < length; length--) {
                int i4 = iArrH[i3];
                iArrH[i3] = iArrH[length];
                iArrH[length] = i4;
                i3++;
            }
        }
        int i5 = z2 ? 16 : 15;
        float fD = M.a.d(iArrH) / i5;
        int[] iArrL = l();
        int[] iArrJ = j();
        float[] fArrM = m();
        float[] fArrK = k();
        for (int i6 = 0; i6 < iArrH.length; i6++) {
            float f2 = iArrH[i6] / fD;
            int i7 = (int) (0.5f + f2);
            if (i7 <= 0) {
                i7 = 1;
            } else if (i7 > 8) {
                i7 = 8;
            }
            int i8 = i6 / 2;
            if ((i6 & 1) == 0) {
                iArrL[i8] = i7;
                fArrM[i8] = f2 - i7;
            } else {
                iArrJ[i8] = i7;
                fArrK[i8] = f2 - i7;
            }
        }
        r(z2, i5);
        int i9 = 0;
        int i10 = 0;
        for (int length2 = iArrL.length - 1; length2 >= 0; length2--) {
            int i11 = iArrL[length2];
            i9 = (i9 * 9) + i11;
            i10 += i11;
        }
        int i12 = 0;
        int i13 = 0;
        for (int length3 = iArrJ.length - 1; length3 >= 0; length3--) {
            int i14 = iArrJ[length3];
            i12 = (i12 * 9) + i14;
            i13 += i14;
        }
        int i15 = i9 + (i12 * 3);
        if (!z2) {
            if ((i13 & 1) != 0 || i13 > 10 || i13 < 4) {
                throw j.a();
            }
            int i16 = (10 - i13) / 2;
            int i17 = f554n[i16];
            return new b((f.b(iArrJ, 9 - i17, false) * f550j[i16]) + f.b(iArrL, i17, true) + f552l[i16], i15);
        }
        if ((i10 & 1) != 0 || i10 > 12 || i10 < 4) {
            throw j.a();
        }
        int i18 = (12 - i10) / 2;
        int i19 = f553m[i18];
        return new b((f.b(iArrL, i19, false) * f549i[i18]) + f.b(iArrJ, 9 - i19, true) + f551k[i18], i15);
    }

    public final d v(L.a aVar, boolean z2, int i2, Map map) {
        try {
            c cVarX = x(aVar, i2, z2, w(aVar, z2));
            if (map != null) {
                AbstractC0177z.a(map.get(H.e.NEED_RESULT_POINT_CALLBACK));
            }
            b bVarU = u(aVar, cVarX, true);
            b bVarU2 = u(aVar, cVarX, false);
            return new d((bVarU.b() * 1597) + bVarU2.b(), bVarU.a() + (bVarU2.a() * 4), cVarX);
        } catch (j unused) {
            return null;
        }
    }

    public final int[] w(L.a aVar, boolean z2) throws j {
        int[] iArrI = i();
        iArrI[0] = 0;
        iArrI[1] = 0;
        iArrI[2] = 0;
        iArrI[3] = 0;
        int iK = aVar.k();
        int i2 = 0;
        boolean z3 = false;
        while (i2 < iK) {
            z3 = !aVar.g(i2);
            if (z2 == z3) {
                break;
            }
            i2++;
        }
        int i3 = i2;
        int i4 = 0;
        while (i2 < iK) {
            if (aVar.g(i2) != z3) {
                iArrI[i4] = iArrI[i4] + 1;
            } else {
                if (i4 != 3) {
                    i4++;
                } else {
                    if (a.o(iArrI)) {
                        return new int[]{i3, i2};
                    }
                    i3 += iArrI[0] + iArrI[1];
                    iArrI[0] = iArrI[2];
                    iArrI[1] = iArrI[3];
                    iArrI[2] = 0;
                    iArrI[3] = 0;
                    i4--;
                }
                iArrI[i4] = 1;
                z3 = !z3;
            }
            i2++;
        }
        throw j.a();
    }

    public final c x(L.a aVar, int i2, boolean z2, int[] iArr) throws j {
        int iK;
        int i3;
        boolean zG = aVar.g(iArr[0]);
        int i4 = iArr[0] - 1;
        while (i4 >= 0 && zG != aVar.g(i4)) {
            i4--;
        }
        int i5 = i4 + 1;
        int i6 = iArr[0] - i5;
        int[] iArrI = i();
        System.arraycopy(iArrI, 0, iArrI, 1, iArrI.length - 1);
        iArrI[0] = i6;
        int iP = a.p(iArrI, f555o);
        int i7 = iArr[1];
        if (z2) {
            int iK2 = (aVar.k() - 1) - i5;
            iK = (aVar.k() - 1) - i7;
            i3 = iK2;
        } else {
            iK = i7;
            i3 = i5;
        }
        return new c(iP, new int[]{i5, iArr[1]}, i3, iK, i2);
    }
}
