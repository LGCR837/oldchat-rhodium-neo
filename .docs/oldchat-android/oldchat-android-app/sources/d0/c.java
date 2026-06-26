package d0;

import H.j;
import H.p;
import L.i;
import L.k;
import c.AbstractC0177z;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L.b f4924a;

    public c(L.b bVar) {
        this.f4924a = bVar;
    }

    public static int c(p pVar, p pVar2, p pVar3, float f2) throws j {
        int iC = (M.a.c(p.b(pVar, pVar2) / f2) + M.a.c(p.b(pVar, pVar3) / f2)) / 2;
        int i2 = iC + 7;
        int i3 = i2 & 3;
        if (i3 == 0) {
            return iC + 8;
        }
        if (i3 == 2) {
            return iC + 6;
        }
        if (i3 != 3) {
            return i2;
        }
        throw j.a();
    }

    public static k d(p pVar, p pVar2, p pVar3, p pVar4, int i2) {
        float fC;
        float fD;
        float f2;
        float f3 = i2 - 3.5f;
        if (pVar4 != null) {
            fC = pVar4.c();
            fD = pVar4.d();
            f2 = f3 - 3.0f;
        } else {
            fC = (pVar2.c() - pVar.c()) + pVar3.c();
            fD = (pVar2.d() - pVar.d()) + pVar3.d();
            f2 = f3;
        }
        return k.b(3.5f, 3.5f, f3, 3.5f, f2, f2, 3.5f, f3, pVar.c(), pVar.d(), pVar2.c(), pVar2.d(), fC, fD, pVar3.c(), pVar3.d());
    }

    public static L.b h(L.b bVar, k kVar, int i2) {
        return i.b().d(bVar, i2, i2, kVar);
    }

    public final float a(p pVar, p pVar2, p pVar3) {
        return (b(pVar, pVar2) + b(pVar, pVar3)) / 2.0f;
    }

    public final float b(p pVar, p pVar2) {
        float fJ = j((int) pVar.c(), (int) pVar.d(), (int) pVar2.c(), (int) pVar2.d());
        float fJ2 = j((int) pVar2.c(), (int) pVar2.d(), (int) pVar.c(), (int) pVar.d());
        return Float.isNaN(fJ) ? fJ2 / 7.0f : Float.isNaN(fJ2) ? fJ / 7.0f : (fJ + fJ2) / 14.0f;
    }

    public final L.g e(Map map) {
        if (map != null) {
            AbstractC0177z.a(map.get(H.e.NEED_RESULT_POINT_CALLBACK));
        }
        return g(new e(this.f4924a, null).f(map));
    }

    public final a f(float f2, int i2, int i3, float f3) throws j {
        int i4 = (int) (f3 * f2);
        int iMax = Math.max(0, i2 - i4);
        int iMin = Math.min(this.f4924a.j() - 1, i2 + i4) - iMax;
        float f4 = 3.0f * f2;
        if (iMin < f4) {
            throw j.a();
        }
        int iMax2 = Math.max(0, i3 - i4);
        int iMin2 = Math.min(this.f4924a.g() - 1, i3 + i4) - iMax2;
        if (iMin2 >= f4) {
            return new b(this.f4924a, iMax, iMax2, iMin, iMin2, f2, null).c();
        }
        throw j.a();
    }

    public final L.g g(g gVar) throws H.g, j {
        a aVarF;
        d dVarB = gVar.b();
        d dVarC = gVar.c();
        d dVarA = gVar.a();
        float fA = a(dVarB, dVarC, dVarA);
        if (fA < 1.0f) {
            throw j.a();
        }
        int iC = c(dVarB, dVarC, dVarA, fA);
        c0.j jVarG = c0.j.g(iC);
        int iE = jVarG.e() - 7;
        if (jVarG.d().length > 0) {
            float fC = (dVarC.c() - dVarB.c()) + dVarA.c();
            float fD = (dVarC.d() - dVarB.d()) + dVarA.d();
            float f2 = 1.0f - (3.0f / iE);
            int iC2 = (int) (dVarB.c() + ((fC - dVarB.c()) * f2));
            int iD = (int) (dVarB.d() + (f2 * (fD - dVarB.d())));
            for (int i2 = 4; i2 <= 16; i2 <<= 1) {
                try {
                    aVarF = f(fA, iC2, iD, i2);
                    break;
                } catch (j unused) {
                }
            }
            aVarF = null;
        } else {
            aVarF = null;
        }
        return new L.g(h(this.f4924a, d(dVarB, dVarC, dVarA, aVarF, iC), iC), aVarF == null ? new p[]{dVarA, dVarB, dVarC} : new p[]{dVarA, dVarB, dVarC, aVarF});
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0087, code lost:
    
        if (r15 != 2) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x008f, code lost:
    
        return M.a.b(r19, r6, r1, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0090, code lost:
    
        return Float.NaN;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float i(int r18, int r19, int r20, int r21) {
        /*
            r17 = this;
            int r0 = r21 - r19
            int r0 = java.lang.Math.abs(r0)
            int r1 = r20 - r18
            int r1 = java.lang.Math.abs(r1)
            r3 = 1
            if (r0 <= r1) goto L11
            r0 = 1
            goto L12
        L11:
            r0 = 0
        L12:
            if (r0 == 0) goto L1d
            r4 = r18
            r1 = r19
            r6 = r20
            r5 = r21
            goto L25
        L1d:
            r1 = r18
            r4 = r19
            r5 = r20
            r6 = r21
        L25:
            int r7 = r5 - r1
            int r7 = java.lang.Math.abs(r7)
            int r8 = r6 - r4
            int r8 = java.lang.Math.abs(r8)
            int r9 = -r7
            r10 = 2
            int r9 = r9 / r10
            r11 = -1
            if (r1 >= r5) goto L39
            r12 = 1
            goto L3a
        L39:
            r12 = -1
        L3a:
            if (r4 >= r6) goto L3d
            r11 = 1
        L3d:
            int r5 = r5 + r12
            r13 = r1
            r14 = r4
            r15 = 0
        L41:
            if (r13 == r5) goto L82
            if (r0 == 0) goto L47
            r2 = r14
            goto L48
        L47:
            r2 = r13
        L48:
            if (r0 == 0) goto L4c
            r10 = r13
            goto L4d
        L4c:
            r10 = r14
        L4d:
            if (r15 != r3) goto L57
            r3 = r17
            r16 = r0
            r19 = r5
            r0 = 1
            goto L5e
        L57:
            r3 = r17
            r16 = r0
            r19 = r5
            r0 = 0
        L5e:
            L.b r5 = r3.f4924a
            boolean r2 = r5.d(r2, r10)
            if (r0 != r2) goto L70
            r0 = 2
            if (r15 != r0) goto L6e
            float r0 = M.a.b(r13, r14, r1, r4)
            return r0
        L6e:
            int r15 = r15 + 1
        L70:
            int r9 = r9 + r8
            if (r9 <= 0) goto L7a
            if (r14 == r6) goto L78
            int r14 = r14 + r11
            int r9 = r9 - r7
            goto L7a
        L78:
            r0 = 2
            goto L87
        L7a:
            int r13 = r13 + r12
            r5 = r19
            r0 = r16
            r3 = 1
            r10 = 2
            goto L41
        L82:
            r3 = r17
            r19 = r5
            goto L78
        L87:
            if (r15 != r0) goto L90
            r5 = r19
            float r0 = M.a.b(r5, r6, r1, r4)
            return r0
        L90:
            r0 = 2143289344(0x7fc00000, float:NaN)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: d0.c.i(int, int, int, int):float");
    }

    public final float j(int i2, int i3, int i4, int i5) {
        float fJ;
        float fG;
        float fI = i(i2, i3, i4, i5);
        int iJ = i2 - (i4 - i2);
        int iG = 0;
        if (iJ < 0) {
            fJ = i2 / (i2 - iJ);
            iJ = 0;
        } else if (iJ >= this.f4924a.j()) {
            fJ = ((this.f4924a.j() - 1) - i2) / (iJ - i2);
            iJ = this.f4924a.j() - 1;
        } else {
            fJ = 1.0f;
        }
        float f2 = i3;
        int i6 = (int) (f2 - ((i5 - i3) * fJ));
        if (i6 < 0) {
            fG = f2 / (i3 - i6);
        } else if (i6 >= this.f4924a.g()) {
            fG = ((this.f4924a.g() - 1) - i3) / (i6 - i3);
            iG = this.f4924a.g() - 1;
        } else {
            iG = i6;
            fG = 1.0f;
        }
        return (fI + i(i2, i3, (int) (i2 + ((iJ - i2) * fG)), iG)) - 1.0f;
    }
}
