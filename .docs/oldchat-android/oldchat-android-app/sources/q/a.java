package Q;

import H.j;
import H.p;
import L.g;
import L.i;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L.b f476a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final M.b f477b;

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final p f478a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final p f479b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f480c;

        public p a() {
            return this.f478a;
        }

        public p b() {
            return this.f479b;
        }

        public int c() {
            return this.f480c;
        }

        public String toString() {
            return this.f478a + "/" + this.f479b + '/' + this.f480c;
        }

        public b(p pVar, p pVar2, int i2) {
            this.f478a = pVar;
            this.f479b = pVar2;
            this.f480c = i2;
        }
    }

    public static final class c implements Serializable, Comparator {
        public c() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(b bVar, b bVar2) {
            return bVar.c() - bVar2.c();
        }
    }

    public a(L.b bVar) {
        this.f476a = bVar;
        this.f477b = new M.b(bVar);
    }

    public static int d(p pVar, p pVar2) {
        return M.a.c(p.b(pVar, pVar2));
    }

    public static void e(Map map, p pVar) {
        Integer num = (Integer) map.get(pVar);
        map.put(pVar, Integer.valueOf(num != null ? 1 + num.intValue() : 1));
    }

    public static L.b g(L.b bVar, p pVar, p pVar2, p pVar3, p pVar4, int i2, int i3) {
        float f2 = i2 - 0.5f;
        float f3 = i3 - 0.5f;
        return i.b().c(bVar, i2, i3, 0.5f, 0.5f, f2, 0.5f, f2, f3, 0.5f, f3, pVar.c(), pVar.d(), pVar4.c(), pVar4.d(), pVar3.c(), pVar3.d(), pVar2.c(), pVar2.d());
    }

    public final p a(p pVar, p pVar2, p pVar3, p pVar4, int i2) {
        float f2 = i2;
        float fD = d(pVar, pVar2) / f2;
        float fD2 = d(pVar3, pVar4);
        p pVar5 = new p(pVar4.c() + (((pVar4.c() - pVar3.c()) / fD2) * fD), pVar4.d() + (fD * ((pVar4.d() - pVar3.d()) / fD2)));
        float fD3 = d(pVar, pVar3) / f2;
        float fD4 = d(pVar2, pVar4);
        p pVar6 = new p(pVar4.c() + (((pVar4.c() - pVar2.c()) / fD4) * fD3), pVar4.d() + (fD3 * ((pVar4.d() - pVar2.d()) / fD4)));
        if (f(pVar5)) {
            return (f(pVar6) && Math.abs(h(pVar3, pVar5).c() - h(pVar2, pVar5).c()) > Math.abs(h(pVar3, pVar6).c() - h(pVar2, pVar6).c())) ? pVar6 : pVar5;
        }
        if (f(pVar6)) {
            return pVar6;
        }
        return null;
    }

    public final p b(p pVar, p pVar2, p pVar3, p pVar4, int i2, int i3) {
        float fD = d(pVar, pVar2) / i2;
        float fD2 = d(pVar3, pVar4);
        p pVar5 = new p(pVar4.c() + (((pVar4.c() - pVar3.c()) / fD2) * fD), pVar4.d() + (fD * ((pVar4.d() - pVar3.d()) / fD2)));
        float fD3 = d(pVar, pVar3) / i3;
        float fD4 = d(pVar2, pVar4);
        p pVar6 = new p(pVar4.c() + (((pVar4.c() - pVar2.c()) / fD4) * fD3), pVar4.d() + (fD3 * ((pVar4.d() - pVar2.d()) / fD4)));
        if (f(pVar5)) {
            return (f(pVar6) && Math.abs(i2 - h(pVar3, pVar5).c()) + Math.abs(i3 - h(pVar2, pVar5).c()) > Math.abs(i2 - h(pVar3, pVar6).c()) + Math.abs(i3 - h(pVar2, pVar6).c())) ? pVar6 : pVar5;
        }
        if (f(pVar6)) {
            return pVar6;
        }
        return null;
    }

    public g c() throws j {
        p pVar;
        L.b bVarG;
        p[] pVarArrC = this.f477b.c();
        p pVar2 = pVarArrC[0];
        p pVar3 = pVarArrC[1];
        p pVar4 = pVarArrC[2];
        p pVar5 = pVarArrC[3];
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(h(pVar2, pVar3));
        arrayList.add(h(pVar2, pVar4));
        arrayList.add(h(pVar3, pVar5));
        arrayList.add(h(pVar4, pVar5));
        p pVar6 = null;
        Collections.sort(arrayList, new c());
        b bVar = (b) arrayList.get(0);
        b bVar2 = (b) arrayList.get(1);
        HashMap map = new HashMap();
        e(map, bVar.a());
        e(map, bVar.b());
        e(map, bVar2.a());
        e(map, bVar2.b());
        p pVar7 = null;
        p pVar8 = null;
        for (Map.Entry entry : map.entrySet()) {
            p pVar9 = (p) entry.getKey();
            if (((Integer) entry.getValue()).intValue() == 2) {
                pVar7 = pVar9;
            } else if (pVar6 == null) {
                pVar6 = pVar9;
            } else {
                pVar8 = pVar9;
            }
        }
        if (pVar6 == null || pVar7 == null || pVar8 == null) {
            throw j.a();
        }
        p[] pVarArr = {pVar6, pVar7, pVar8};
        p.e(pVarArr);
        p pVar10 = pVarArr[0];
        p pVar11 = pVarArr[1];
        p pVar12 = pVarArr[2];
        p pVar13 = !map.containsKey(pVar2) ? pVar2 : !map.containsKey(pVar3) ? pVar3 : !map.containsKey(pVar4) ? pVar4 : pVar5;
        int iC = h(pVar12, pVar13).c();
        int iC2 = h(pVar10, pVar13).c();
        if ((iC & 1) == 1) {
            iC++;
        }
        int i2 = iC + 2;
        if ((iC2 & 1) == 1) {
            iC2++;
        }
        int i3 = iC2 + 2;
        if (i2 * 4 >= i3 * 7 || i3 * 4 >= i2 * 7) {
            pVar = pVar12;
            p pVarB = b(pVar11, pVar10, pVar12, pVar13, i2, i3);
            if (pVarB != null) {
                pVar13 = pVarB;
            }
            int iC3 = h(pVar, pVar13).c();
            int iC4 = h(pVar10, pVar13).c();
            if ((iC3 & 1) == 1) {
                iC3++;
            }
            int i4 = iC3;
            if ((iC4 & 1) == 1) {
                iC4++;
            }
            bVarG = g(this.f476a, pVar, pVar11, pVar10, pVar13, i4, iC4);
        } else {
            p pVarA = a(pVar11, pVar10, pVar12, pVar13, Math.min(i3, i2));
            if (pVarA != null) {
                pVar13 = pVarA;
            }
            int iMax = Math.max(h(pVar12, pVar13).c(), h(pVar10, pVar13).c());
            int i5 = iMax + 1;
            int i6 = (i5 & 1) == 1 ? iMax + 2 : i5;
            bVarG = g(this.f476a, pVar12, pVar11, pVar10, pVar13, i6, i6);
            pVar = pVar12;
        }
        return new g(bVarG, new p[]{pVar, pVar11, pVar10, pVar13});
    }

    public final boolean f(p pVar) {
        return pVar.c() >= 0.0f && pVar.c() < ((float) this.f476a.j()) && pVar.d() > 0.0f && pVar.d() < ((float) this.f476a.g());
    }

    public final b h(p pVar, p pVar2) {
        int iC = (int) pVar.c();
        int iD = (int) pVar.d();
        int iC2 = (int) pVar2.c();
        int iD2 = (int) pVar2.d();
        int i2 = 0;
        boolean z2 = Math.abs(iD2 - iD) > Math.abs(iC2 - iC);
        if (z2) {
            iD = iC;
            iC = iD;
            iD2 = iC2;
            iC2 = iD2;
        }
        int iAbs = Math.abs(iC2 - iC);
        int iAbs2 = Math.abs(iD2 - iD);
        int i3 = (-iAbs) / 2;
        int i4 = iD < iD2 ? 1 : -1;
        int i5 = iC >= iC2 ? -1 : 1;
        boolean zD = this.f476a.d(z2 ? iD : iC, z2 ? iC : iD);
        while (iC != iC2) {
            boolean zD2 = this.f476a.d(z2 ? iD : iC, z2 ? iC : iD);
            if (zD2 != zD) {
                i2++;
                zD = zD2;
            }
            i3 += iAbs2;
            if (i3 > 0) {
                if (iD == iD2) {
                    break;
                }
                iD += i4;
                i3 -= iAbs;
            }
            iC += i5;
        }
        return new b(pVar, pVar2, i2);
    }
}
