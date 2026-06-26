package Y;

import H.p;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Z.a f655a = new Z.a();

    public static c a(j jVar) {
        int[] iArrJ;
        if (jVar == null || (iArrJ = jVar.j()) == null) {
            return null;
        }
        int iP = p(iArrJ);
        int i2 = 0;
        int i3 = 0;
        for (int i4 : iArrJ) {
            i3 += iP - i4;
            if (i4 > 0) {
                break;
            }
        }
        d[] dVarArrD = jVar.d();
        for (int i5 = 0; i3 > 0 && dVarArrD[i5] == null; i5++) {
            i3--;
        }
        for (int length = iArrJ.length - 1; length >= 0; length--) {
            int i6 = iArrJ[length];
            i2 += iP - i6;
            if (i6 > 0) {
                break;
            }
        }
        for (int length2 = dVarArrD.length - 1; i2 > 0 && dVarArrD[length2] == null; length2--) {
            i2--;
        }
        return jVar.a().a(i3, i2, jVar.k());
    }

    public static void b(h hVar, b[][] bVarArr) throws H.j {
        b bVar = bVarArr[0][1];
        int[] iArrA = bVar.a();
        int iJ = (hVar.j() * hVar.l()) - r(hVar.k());
        if (iArrA.length != 0) {
            if (iArrA[0] != iJ) {
                bVar.b(iJ);
            }
        } else {
            if (iJ <= 0 || iJ > 928) {
                throw H.j.a();
            }
            bVar.b(iJ);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0022, code lost:
    
        r0 = -r0;
        r8 = !r8;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0022, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0022, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0022, code lost:
    
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int c(L.b r5, int r6, int r7, boolean r8, int r9, int r10) {
        /*
            if (r8 == 0) goto L4
            r0 = -1
            goto L5
        L4:
            r0 = 1
        L5:
            r1 = 0
            r2 = r9
        L7:
            r3 = 2
            if (r1 >= r3) goto L28
        La:
            if (r8 == 0) goto Lf
            if (r2 < r6) goto L22
            goto L11
        Lf:
            if (r2 >= r7) goto L22
        L11:
            boolean r4 = r5.d(r2, r10)
            if (r8 != r4) goto L22
            int r4 = r9 - r2
            int r4 = java.lang.Math.abs(r4)
            if (r4 <= r3) goto L20
            return r9
        L20:
            int r2 = r2 + r0
            goto La
        L22:
            int r0 = -r0
            r8 = r8 ^ 1
            int r1 = r1 + 1
            goto L7
        L28:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: Y.l.c(L.b, int, int, boolean, int, int):int");
    }

    public static boolean d(int i2, int i3, int i4) {
        return i3 + (-2) <= i2 && i2 <= i4 + 2;
    }

    public static int e(int[] iArr, int[] iArr2, int i2) throws H.d {
        if ((iArr2 == null || iArr2.length <= (i2 / 2) + 3) && i2 >= 0 && i2 <= 512) {
            return f655a.a(iArr, i2, iArr2);
        }
        throw H.d.a();
    }

    public static b[][] f(h hVar) {
        int iC;
        b[][] bVarArr = (b[][]) Array.newInstance((Class<?>) b.class, hVar.l(), hVar.j() + 2);
        for (b[] bVarArr2 : bVarArr) {
            int i2 = 0;
            while (true) {
                if (i2 < bVarArr2.length) {
                    bVarArr2[i2] = new b();
                    i2++;
                }
            }
        }
        int i3 = 0;
        for (i iVar : hVar.o()) {
            if (iVar != null) {
                for (d dVar : iVar.d()) {
                    if (dVar != null && (iC = dVar.c()) >= 0 && iC < bVarArr.length) {
                        bVarArr[iC][i3].b(dVar.e());
                    }
                }
            }
            i3++;
        }
        return bVarArr;
    }

    public static L.e g(h hVar) throws H.j {
        b[][] bVarArrF = f(hVar);
        b(hVar, bVarArrF);
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[hVar.l() * hVar.j()];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i2 = 0; i2 < hVar.l(); i2++) {
            int i3 = 0;
            while (i3 < hVar.j()) {
                int i4 = i3 + 1;
                int[] iArrA = bVarArrF[i2][i4].a();
                int iJ = (hVar.j() * i2) + i3;
                if (iArrA.length == 0) {
                    arrayList.add(Integer.valueOf(iJ));
                } else if (iArrA.length == 1) {
                    iArr[iJ] = iArrA[0];
                } else {
                    arrayList3.add(Integer.valueOf(iJ));
                    arrayList2.add(iArrA);
                }
                i3 = i4;
            }
        }
        int size = arrayList2.size();
        int[][] iArr2 = new int[size][];
        for (int i5 = 0; i5 < size; i5++) {
            iArr2[i5] = (int[]) arrayList2.get(i5);
        }
        return h(hVar.k(), iArr, X.a.b(arrayList), X.a.b(arrayList3), iArr2);
    }

    public static L.e h(int i2, int[] iArr, int[] iArr2, int[] iArr3, int[][] iArr4) throws H.d {
        int length = iArr3.length;
        int[] iArr5 = new int[length];
        int i3 = 100;
        while (true) {
            int i4 = i3 - 1;
            if (i3 <= 0) {
                throw H.d.a();
            }
            for (int i5 = 0; i5 < length; i5++) {
                iArr[iArr3[i5]] = iArr4[i5][iArr5[i5]];
            }
            try {
                return j(iArr, i2, iArr2);
            } catch (H.d unused) {
                if (length == 0) {
                    throw H.d.a();
                }
                int i6 = 0;
                while (true) {
                    if (i6 >= length) {
                        break;
                    }
                    int i7 = iArr5[i6];
                    if (i7 < iArr4[i6].length - 1) {
                        iArr5[i6] = i7 + 1;
                        break;
                    }
                    iArr5[i6] = 0;
                    if (i6 == length - 1) {
                        throw H.d.a();
                    }
                    i6++;
                }
                i3 = i4;
            }
        }
    }

    public static L.e i(L.b bVar, p pVar, p pVar2, p pVar3, p pVar4, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        j jVarS = null;
        j jVarS2 = null;
        h hVarV = null;
        c cVar = new c(bVar, pVar, pVar2, pVar3, pVar4);
        for (int i7 = 0; i7 < 2; i7++) {
            if (pVar != null) {
                jVarS = s(bVar, cVar, pVar, true, i2, i3);
            }
            if (pVar3 != null) {
                jVarS2 = s(bVar, cVar, pVar3, false, i2, i3);
            }
            hVarV = v(jVarS, jVarS2);
            if (hVarV == null) {
                throw H.j.a();
            }
            if (i7 != 0 || hVarV.m() == null || (hVarV.m().g() >= cVar.g() && hVarV.m().e() <= cVar.e())) {
                hVarV.p(cVar);
                break;
            }
            cVar = hVarV.m();
        }
        int iJ = hVarV.j() + 1;
        hVarV.q(0, jVarS);
        hVarV.q(iJ, jVarS2);
        boolean z2 = jVarS != null;
        int iMin = i2;
        int iMax = i3;
        for (int i8 = 1; i8 <= iJ; i8++) {
            int i9 = z2 ? i8 : iJ - i8;
            if (hVarV.n(i9) == null) {
                i jVar = (i9 == 0 || i9 == iJ) ? new j(cVar, i9 == 0) : new i(cVar);
                hVarV.q(i9, jVar);
                int i10 = -1;
                int iG = cVar.g();
                int i11 = -1;
                while (iG <= cVar.e()) {
                    int iT = t(hVarV, i9, iG, z2);
                    if (iT >= 0 && iT <= cVar.d()) {
                        i6 = iT;
                    } else if (i11 != i10) {
                        i6 = i11;
                    } else {
                        i4 = i11;
                        i5 = iG;
                        i11 = i4;
                        iG = i5 + 1;
                        i10 = -1;
                    }
                    i4 = i11;
                    int i12 = iG;
                    d dVarK = k(bVar, cVar.f(), cVar.d(), z2, i6, i12, iMin, iMax);
                    i5 = i12;
                    if (dVarK != null) {
                        jVar.f(i5, dVarK);
                        iMin = Math.min(iMin, dVarK.f());
                        iMax = Math.max(iMax, dVarK.f());
                        i11 = i6;
                    } else {
                        i11 = i4;
                    }
                    iG = i5 + 1;
                    i10 = -1;
                }
            }
        }
        return g(hVarV);
    }

    public static L.e j(int[] iArr, int i2, int[] iArr2) throws H.g, H.d {
        if (iArr.length == 0) {
            throw H.g.a();
        }
        int i3 = 1 << (i2 + 1);
        int iE = e(iArr, iArr2, i3);
        w(iArr, i3);
        L.e eVarB = f.b(iArr, String.valueOf(i2));
        eVarB.k(Integer.valueOf(iE));
        eVarB.j(Integer.valueOf(iArr2.length));
        return eVarB;
    }

    public static d k(L.b bVar, int i2, int i3, boolean z2, int i4, int i5, int i6, int i7) {
        int i8;
        int iD;
        int iA;
        int iC = c(bVar, i2, i3, z2, i4, i5);
        int[] iArrQ = q(bVar, i2, i3, z2, iC, i5);
        if (iArrQ == null) {
            return null;
        }
        int iD2 = M.a.d(iArrQ);
        if (z2) {
            i8 = iC + iD2;
        } else {
            for (int i9 = 0; i9 < iArrQ.length / 2; i9++) {
                int i10 = iArrQ[i9];
                iArrQ[i9] = iArrQ[(iArrQ.length - 1) - i9];
                iArrQ[(iArrQ.length - 1) - i9] = i10;
            }
            iC -= iD2;
            i8 = iC;
        }
        if (d(iD2, i6, i7) && (iA = X.a.a((iD = k.d(iArrQ)))) != -1) {
            return new d(iC, i8, n(iD), iA);
        }
        return null;
    }

    public static a l(j jVar, j jVar2) {
        a aVarI;
        a aVarI2;
        if (jVar == null || (aVarI = jVar.i()) == null) {
            if (jVar2 == null) {
                return null;
            }
            return jVar2.i();
        }
        if (jVar2 == null || (aVarI2 = jVar2.i()) == null || aVarI.a() == aVarI2.a() || aVarI.b() == aVarI2.b() || aVarI.c() == aVarI2.c()) {
            return aVarI;
        }
        return null;
    }

    public static int[] m(int i2) {
        int[] iArr = new int[8];
        int i3 = 0;
        int i4 = 7;
        while (true) {
            int i5 = i2 & 1;
            if (i5 != i3) {
                i4--;
                if (i4 < 0) {
                    return iArr;
                }
                i3 = i5;
            }
            iArr[i4] = iArr[i4] + 1;
            i2 >>= 1;
        }
    }

    public static int n(int i2) {
        return o(m(i2));
    }

    public static int o(int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }

    public static int p(int[] iArr) {
        int iMax = -1;
        for (int i2 : iArr) {
            iMax = Math.max(iMax, i2);
        }
        return iMax;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int[] q(L.b r7, int r8, int r9, boolean r10, int r11, int r12) {
        /*
            r0 = 8
            int[] r1 = new int[r0]
            r2 = 1
            if (r10 == 0) goto L9
            r3 = 1
            goto La
        L9:
            r3 = -1
        La:
            r4 = 0
            r5 = r10
        Lc:
            if (r10 == 0) goto L11
            if (r11 >= r9) goto L27
            goto L13
        L11:
            if (r11 < r8) goto L27
        L13:
            if (r4 >= r0) goto L27
            boolean r6 = r7.d(r11, r12)
            if (r6 != r5) goto L22
            r6 = r1[r4]
            int r6 = r6 + r2
            r1[r4] = r6
            int r11 = r11 + r3
            goto Lc
        L22:
            int r4 = r4 + 1
            r5 = r5 ^ 1
            goto Lc
        L27:
            if (r4 == r0) goto L34
            if (r10 == 0) goto L2c
            r8 = r9
        L2c:
            if (r11 != r8) goto L32
            r7 = 7
            if (r4 != r7) goto L32
            goto L34
        L32:
            r7 = 0
            return r7
        L34:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: Y.l.q(L.b, int, int, boolean, int, int):int[]");
    }

    public static int r(int i2) {
        return 2 << i2;
    }

    public static j s(L.b bVar, c cVar, p pVar, boolean z2, int i2, int i3) {
        j jVar = new j(cVar, z2);
        int i4 = 0;
        while (i4 < 2) {
            int i5 = i4 == 0 ? 1 : -1;
            int iC = (int) pVar.c();
            for (int iD = (int) pVar.d(); iD <= cVar.e() && iD >= cVar.g(); iD += i5) {
                d dVarK = k(bVar, 0, bVar.j(), z2, iC, iD, i2, i3);
                if (dVarK != null) {
                    jVar.f(iD, dVarK);
                    iC = z2 ? dVarK.d() : dVarK.b();
                }
            }
            i4++;
        }
        return jVar;
    }

    public static int t(h hVar, int i2, int i3, boolean z2) {
        int i4 = z2 ? 1 : -1;
        int i5 = i2 - i4;
        d dVarB = u(hVar, i5) ? hVar.n(i5).b(i3) : null;
        if (dVarB != null) {
            return z2 ? dVarB.b() : dVarB.d();
        }
        d dVarC = hVar.n(i2).c(i3);
        if (dVarC != null) {
            return z2 ? dVarC.d() : dVarC.b();
        }
        if (u(hVar, i5)) {
            dVarC = hVar.n(i5).c(i3);
        }
        if (dVarC != null) {
            return z2 ? dVarC.b() : dVarC.d();
        }
        int i6 = 0;
        while (true) {
            i2 -= i4;
            if (!u(hVar, i2)) {
                c cVarM = hVar.m();
                return z2 ? cVarM.f() : cVarM.d();
            }
            for (d dVar : hVar.n(i2).d()) {
                if (dVar != null) {
                    return (z2 ? dVar.b() : dVar.d()) + (i4 * i6 * (dVar.b() - dVar.d()));
                }
            }
            i6++;
        }
    }

    public static boolean u(h hVar, int i2) {
        return i2 >= 0 && i2 <= hVar.j() + 1;
    }

    public static h v(j jVar, j jVar2) {
        a aVarL;
        if ((jVar == null && jVar2 == null) || (aVarL = l(jVar, jVar2)) == null) {
            return null;
        }
        return new h(aVarL, c.j(a(jVar), a(jVar2)));
    }

    public static void w(int[] iArr, int i2) throws H.g {
        if (iArr.length < 4) {
            throw H.g.a();
        }
        int i3 = iArr[0];
        if (i3 > iArr.length) {
            throw H.g.a();
        }
        if (i3 == 0) {
            if (i2 >= iArr.length) {
                throw H.g.a();
            }
            iArr[0] = iArr.length - i2;
        }
    }
}
