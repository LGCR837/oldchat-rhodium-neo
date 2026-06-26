package K;

import H.j;
import H.p;
import L.b;
import L.i;
import N.c;
import N.e;
import org.spongycastle.asn1.eac.CertificateBody;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[] f337g = {3808, 476, 2107, 1799};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f338a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f339b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f340c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f341d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f342e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f343f;

    /* JADX INFO: renamed from: K.a$a, reason: collision with other inner class name */
    public static final class C0007a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f344a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f345b;

        public C0007a(int i2, int i3) {
            this.f344a = i2;
            this.f345b = i3;
        }

        public int a() {
            return this.f344a;
        }

        public int b() {
            return this.f345b;
        }

        public p c() {
            return new p(a(), b());
        }

        public String toString() {
            return "<" + this.f344a + ' ' + this.f345b + '>';
        }
    }

    public a(b bVar) {
        this.f338a = bVar;
    }

    public static float b(p pVar, p pVar2) {
        return M.a.a(pVar.c(), pVar.d(), pVar2.c(), pVar2.d());
    }

    public static float c(C0007a c0007a, C0007a c0007a2) {
        return M.a.b(c0007a.a(), c0007a.b(), c0007a2.a(), c0007a2.b());
    }

    public static p[] d(p[] pVarArr, int i2, int i3) {
        float f2 = i3 / (i2 * 2.0f);
        float fC = pVarArr[0].c() - pVarArr[2].c();
        float fD = pVarArr[0].d() - pVarArr[2].d();
        float fC2 = (pVarArr[0].c() + pVarArr[2].c()) / 2.0f;
        float fD2 = (pVarArr[0].d() + pVarArr[2].d()) / 2.0f;
        float f3 = fC * f2;
        float f4 = fD * f2;
        p pVar = new p(fC2 + f3, fD2 + f4);
        p pVar2 = new p(fC2 - f3, fD2 - f4);
        float fC3 = pVarArr[1].c() - pVarArr[3].c();
        float fD3 = pVarArr[1].d() - pVarArr[3].d();
        float fC4 = (pVarArr[1].c() + pVarArr[3].c()) / 2.0f;
        float fD4 = (pVarArr[1].d() + pVarArr[3].d()) / 2.0f;
        float f5 = fC3 * f2;
        float f6 = f2 * fD3;
        return new p[]{pVar, new p(fC4 + f5, fD4 + f6), pVar2, new p(fC4 - f5, fD4 - f6)};
    }

    public static int h(long j2, boolean z2) throws j {
        int i2;
        int i3;
        if (z2) {
            i2 = 7;
            i3 = 2;
        } else {
            i2 = 10;
            i3 = 4;
        }
        int i4 = i2 - i3;
        int[] iArr = new int[i2];
        for (int i5 = i2 - 1; i5 >= 0; i5--) {
            iArr[i5] = ((int) j2) & 15;
            j2 >>= 4;
        }
        try {
            new c(N.a.f425k).a(iArr, i4);
            int i6 = 0;
            for (int i7 = 0; i7 < i3; i7++) {
                i6 = (i6 << 4) + iArr[i7];
            }
            return i6;
        } catch (e unused) {
            throw j.a();
        }
    }

    public static int m(int[] iArr, int i2) throws j {
        int i3 = 0;
        for (int i4 : iArr) {
            i3 = (i3 << 3) + ((i4 >> (i2 - 2)) << 1) + (i4 & 1);
        }
        int i5 = ((i3 & 1) << 11) + (i3 >> 1);
        for (int i6 = 0; i6 < 4; i6++) {
            if (Integer.bitCount(f337g[i6] ^ i5) <= 2) {
                return i6;
            }
        }
        throw j.a();
    }

    public I.a a(boolean z2) throws j {
        p[] pVarArrF = f(k());
        if (z2) {
            p pVar = pVarArrF[0];
            pVarArrF[0] = pVarArrF[2];
            pVarArrF[2] = pVar;
        }
        e(pVarArrF);
        b bVar = this.f338a;
        int i2 = this.f343f;
        return new I.a(q(bVar, pVarArrF[i2 % 4], pVarArrF[(i2 + 1) % 4], pVarArrF[(i2 + 2) % 4], pVarArrF[(i2 + 3) % 4]), l(pVarArrF), this.f339b, this.f341d, this.f340c);
    }

    public final void e(p[] pVarArr) throws j {
        long j2;
        long j3;
        if (!o(pVarArr[0]) || !o(pVarArr[1]) || !o(pVarArr[2]) || !o(pVarArr[3])) {
            throw j.a();
        }
        int i2 = this.f342e * 2;
        int[] iArr = {r(pVarArr[0], pVarArr[1], i2), r(pVarArr[1], pVarArr[2], i2), r(pVarArr[2], pVarArr[3], i2), r(pVarArr[3], pVarArr[0], i2)};
        this.f343f = m(iArr, i2);
        long j4 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            int i4 = iArr[(this.f343f + i3) % 4];
            if (this.f339b) {
                j2 = j4 << 7;
                j3 = (i4 >> 1) & CertificateBody.profileType;
            } else {
                j2 = j4 << 10;
                j3 = ((i4 >> 2) & 992) + ((i4 >> 1) & 31);
            }
            j4 = j2 + j3;
        }
        int iH = h(j4, this.f339b);
        if (this.f339b) {
            this.f340c = (iH >> 6) + 1;
            this.f341d = (iH & 63) + 1;
        } else {
            this.f340c = (iH >> 11) + 1;
            this.f341d = (iH & 2047) + 1;
        }
    }

    public final p[] f(C0007a c0007a) throws j {
        this.f342e = 1;
        C0007a c0007a2 = c0007a;
        C0007a c0007a3 = c0007a2;
        C0007a c0007a4 = c0007a3;
        C0007a c0007a5 = c0007a4;
        boolean z2 = true;
        while (this.f342e < 9) {
            C0007a c0007aJ = j(c0007a2, z2, 1, -1);
            C0007a c0007aJ2 = j(c0007a3, z2, 1, 1);
            C0007a c0007aJ3 = j(c0007a4, z2, -1, 1);
            C0007a c0007aJ4 = j(c0007a5, z2, -1, -1);
            if (this.f342e > 2) {
                double dC = (c(c0007aJ4, c0007aJ) * this.f342e) / (c(c0007a5, c0007a2) * (this.f342e + 2));
                if (dC < 0.75d || dC > 1.25d || !p(c0007aJ, c0007aJ2, c0007aJ3, c0007aJ4)) {
                    break;
                }
            }
            z2 = !z2;
            this.f342e++;
            c0007a5 = c0007aJ4;
            c0007a2 = c0007aJ;
            c0007a3 = c0007aJ2;
            c0007a4 = c0007aJ3;
        }
        int i2 = this.f342e;
        if (i2 != 5 && i2 != 7) {
            throw j.a();
        }
        this.f339b = i2 == 5;
        p[] pVarArr = {new p(c0007a2.a() + 0.5f, c0007a2.b() - 0.5f), new p(c0007a3.a() + 0.5f, c0007a3.b() + 0.5f), new p(c0007a4.a() - 0.5f, c0007a4.b() + 0.5f), new p(c0007a5.a() - 0.5f, c0007a5.b() - 0.5f)};
        int i3 = this.f342e;
        return d(pVarArr, (i3 * 2) - 3, i3 * 2);
    }

    public final int g(C0007a c0007a, C0007a c0007a2) {
        float fC = c(c0007a, c0007a2);
        float fA = (c0007a2.a() - c0007a.a()) / fC;
        float fB = (c0007a2.b() - c0007a.b()) / fC;
        float fA2 = c0007a.a();
        float fB2 = c0007a.b();
        boolean zD = this.f338a.d(c0007a.a(), c0007a.b());
        int iCeil = (int) Math.ceil(fC);
        int i2 = 0;
        for (int i3 = 0; i3 < iCeil; i3++) {
            fA2 += fA;
            fB2 += fB;
            if (this.f338a.d(M.a.c(fA2), M.a.c(fB2)) != zD) {
                i2++;
            }
        }
        float f2 = i2 / fC;
        if (f2 <= 0.1f || f2 >= 0.9f) {
            return (f2 <= 0.1f) == zD ? 1 : -1;
        }
        return 0;
    }

    public final int i() {
        if (this.f339b) {
            return (this.f340c * 4) + 11;
        }
        int i2 = this.f340c;
        return i2 <= 4 ? (i2 * 4) + 15 : (i2 * 4) + ((((i2 - 4) / 8) + 1) * 2) + 15;
    }

    public final C0007a j(C0007a c0007a, boolean z2, int i2, int i3) {
        int iA = c0007a.a() + i2;
        int iB = c0007a.b();
        while (true) {
            iB += i3;
            if (!n(iA, iB) || this.f338a.d(iA, iB) != z2) {
                break;
            }
            iA += i2;
        }
        int i4 = iA - i2;
        int i5 = iB - i3;
        while (n(i4, i5) && this.f338a.d(i4, i5) == z2) {
            i4 += i2;
        }
        int i6 = i4 - i2;
        while (n(i6, i5) && this.f338a.d(i6, i5) == z2) {
            i5 += i3;
        }
        return new C0007a(i6, i5 - i3);
    }

    public final C0007a k() {
        p pVarC;
        p pVar;
        p pVar2;
        p pVar3;
        p pVarC2;
        p pVarC3;
        p pVarC4;
        p pVarC5;
        try {
            p[] pVarArrC = new M.b(this.f338a).c();
            pVar2 = pVarArrC[0];
            pVar3 = pVarArrC[1];
            pVar = pVarArrC[2];
            pVarC = pVarArrC[3];
        } catch (j unused) {
            int iJ = this.f338a.j() / 2;
            int iG = this.f338a.g() / 2;
            int i2 = iJ + 7;
            int i3 = iG - 7;
            p pVarC6 = j(new C0007a(i2, i3), false, 1, -1).c();
            int i4 = iG + 7;
            p pVarC7 = j(new C0007a(i2, i4), false, 1, 1).c();
            int i5 = iJ - 7;
            p pVarC8 = j(new C0007a(i5, i4), false, -1, 1).c();
            pVarC = j(new C0007a(i5, i3), false, -1, -1).c();
            pVar = pVarC8;
            pVar2 = pVarC6;
            pVar3 = pVarC7;
        }
        int iC = M.a.c((((pVar2.c() + pVarC.c()) + pVar3.c()) + pVar.c()) / 4.0f);
        int iC2 = M.a.c((((pVar2.d() + pVarC.d()) + pVar3.d()) + pVar.d()) / 4.0f);
        try {
            p[] pVarArrC2 = new M.b(this.f338a, 15, iC, iC2).c();
            pVarC2 = pVarArrC2[0];
            pVarC3 = pVarArrC2[1];
            pVarC4 = pVarArrC2[2];
            pVarC5 = pVarArrC2[3];
        } catch (j unused2) {
            int i6 = iC + 7;
            int i7 = iC2 - 7;
            pVarC2 = j(new C0007a(i6, i7), false, 1, -1).c();
            int i8 = iC2 + 7;
            pVarC3 = j(new C0007a(i6, i8), false, 1, 1).c();
            int i9 = iC - 7;
            pVarC4 = j(new C0007a(i9, i8), false, -1, 1).c();
            pVarC5 = j(new C0007a(i9, i7), false, -1, -1).c();
        }
        return new C0007a(M.a.c((((pVarC2.c() + pVarC5.c()) + pVarC3.c()) + pVarC4.c()) / 4.0f), M.a.c((((pVarC2.d() + pVarC5.d()) + pVarC3.d()) + pVarC4.d()) / 4.0f));
    }

    public final p[] l(p[] pVarArr) {
        return d(pVarArr, this.f342e * 2, i());
    }

    public final boolean n(int i2, int i3) {
        return i2 >= 0 && i2 < this.f338a.j() && i3 > 0 && i3 < this.f338a.g();
    }

    public final boolean o(p pVar) {
        return n(M.a.c(pVar.c()), M.a.c(pVar.d()));
    }

    public final boolean p(C0007a c0007a, C0007a c0007a2, C0007a c0007a3, C0007a c0007a4) {
        C0007a c0007a5 = new C0007a(c0007a.a() - 3, c0007a.b() + 3);
        C0007a c0007a6 = new C0007a(c0007a2.a() - 3, c0007a2.b() - 3);
        C0007a c0007a7 = new C0007a(c0007a3.a() + 3, c0007a3.b() - 3);
        C0007a c0007a8 = new C0007a(c0007a4.a() + 3, c0007a4.b() + 3);
        int iG = g(c0007a8, c0007a5);
        return iG != 0 && g(c0007a5, c0007a6) == iG && g(c0007a6, c0007a7) == iG && g(c0007a7, c0007a8) == iG;
    }

    public final b q(b bVar, p pVar, p pVar2, p pVar3, p pVar4) {
        i iVarB = i.b();
        int i2 = i();
        float f2 = i2 / 2.0f;
        int i3 = this.f342e;
        float f3 = f2 - i3;
        float f4 = f2 + i3;
        return iVarB.c(bVar, i2, i2, f3, f3, f4, f3, f4, f4, f3, f4, pVar.c(), pVar.d(), pVar2.c(), pVar2.d(), pVar3.c(), pVar3.d(), pVar4.c(), pVar4.d());
    }

    public final int r(p pVar, p pVar2, int i2) {
        float fB = b(pVar, pVar2);
        float f2 = fB / i2;
        float fC = pVar.c();
        float fD = pVar.d();
        float fC2 = ((pVar2.c() - pVar.c()) * f2) / fB;
        float fD2 = (f2 * (pVar2.d() - pVar.d())) / fB;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            float f3 = i4;
            if (this.f338a.d(M.a.c((f3 * fC2) + fC), M.a.c((f3 * fD2) + fD))) {
                i3 |= 1 << ((i2 - i4) - 1);
            }
        }
        return i3;
    }
}
