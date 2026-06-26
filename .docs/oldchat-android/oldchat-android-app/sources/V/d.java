package V;

import H.n;
import H.p;
import T.k;
import U.f;
import W.j;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.spongycastle.asn1.eac.CertificateBody;
import org.spongycastle.asn1.x509.DisplayText;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.math.Primes;

/* JADX INFO: loaded from: classes.dex */
public final class d extends U.a {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int[] f565k = {7, 5, 4, 3, 1};

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final int[] f566l = {4, 20, 52, 104, 204};

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final int[] f567m = {0, 348, 1388, 2948, 3988};

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int[][] f568n = {new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final int[][] f569o = {new int[]{1, 3, 9, 27, 81, 32, 96, 77}, new int[]{20, 60, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256, 118, CipherSuite.TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA, 7, 21, 63}, new int[]{CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA, 13, 39, 117, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA, 209, 205}, new int[]{CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, 49, CipherSuite.TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA, 19, 57, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384, 91}, new int[]{62, CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384, 85, 44, CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA}, new int[]{CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA384, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA, 188, CipherSuite.TLS_DHE_PSK_WITH_RC4_128_SHA, 4, 12, 36, CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256}, new int[]{113, 128, CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384, 97, 80, 29, 87, 50}, new int[]{CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA, 28, 84, 41, 123, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, 52, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256}, new int[]{46, CipherSuite.TLS_PSK_WITH_RC4_128_SHA, 203, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256, CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA, 206, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256}, new int[]{76, 17, 51, CipherSuite.TLS_DHE_DSS_WITH_SEED_CBC_SHA, 37, 111, 122, CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA}, new int[]{43, 129, CipherSuite.TLS_PSK_WITH_NULL_SHA256, CipherSuite.TLS_DHE_DSS_WITH_AES_256_CBC_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA256, 110, 119, CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA}, new int[]{16, 48, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, 10, 30, 90, 59, CipherSuite.TLS_PSK_WITH_NULL_SHA384}, new int[]{CipherSuite.TLS_DH_anon_WITH_AES_256_CBC_SHA256, 116, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA, DisplayText.DISPLAY_TEXT_MAXIMUM_SIZE, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256, 112, 125, CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256}, new int[]{70, 210, 208, 202, CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA256, 130, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384, 115}, new int[]{CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA, 31, 93, 68, 204, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256}, new int[]{CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA, 22, 66, 198, CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256, 94, 71, 2}, new int[]{6, 18, 54, CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, 64, 192, CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA, 40}, new int[]{120, CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA, 25, 75, 14, 42, 126, CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384}, new int[]{79, 26, 78, 23, 69, 207, 199, CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384}, new int[]{103, 98, 83, 38, 114, 131, CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA256, 124}, new int[]{CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384, 61, CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384, CertificateBody.profileType, CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256, 88, 53, CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384}, new int[]{55, CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384, 73, 8, 24, 72, 5, 15}, new int[]{45, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256, 58, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256, 100, 89}};

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final int[][] f570p = {new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List f571g = new ArrayList(11);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List f572h = new ArrayList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int[] f573i = new int[2];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f574j;

    public static boolean A(Iterable iterable, Iterable iterable2) {
        Iterator it = iterable2.iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            Iterator it2 = iterable.iterator();
            while (it2.hasNext()) {
                b bVar = (b) it2.next();
                Iterator it3 = cVar.a().iterator();
                while (it3.hasNext()) {
                    if (bVar.equals((b) it3.next())) {
                        break;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static boolean B(List list) {
        for (int[] iArr : f570p) {
            if (list.size() <= iArr.length) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (((b) list.get(i2)).b().c() != iArr[i2]) {
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static void D(List list, List list2) {
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            if (cVar.a().size() != list.size()) {
                Iterator it2 = cVar.a().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        it.remove();
                        break;
                    }
                    b bVar = (b) it2.next();
                    Iterator it3 = list.iterator();
                    while (it3.hasNext()) {
                        if (bVar.equals((b) it3.next())) {
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void F(int[] iArr) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length / 2; i2++) {
            int i3 = iArr[i2];
            int i4 = (length - i2) - 1;
            iArr[i2] = iArr[i4];
            iArr[i4] = i3;
        }
    }

    public static n u(List list) {
        String strD = j.a(a.a(list)).d();
        p[] pVarArrA = ((b) list.get(0)).b().a();
        p[] pVarArrA2 = ((b) list.get(list.size() - 1)).b().a();
        return new n(strD, null, new p[]{pVarArrA[0], pVarArrA[1], pVarArrA2[0], pVarArrA2[1]}, H.a.RSS_EXPANDED);
    }

    public static int y(L.a aVar, int i2) {
        return aVar.g(i2) ? aVar.i(aVar.j(i2)) : aVar.j(aVar.i(i2));
    }

    public static boolean z(U.c cVar, boolean z2, boolean z3) {
        return (cVar.c() == 0 && z2 && z3) ? false : true;
    }

    public final U.c C(L.a aVar, int i2, boolean z2) {
        int i3;
        int i4;
        int i5;
        if (z2) {
            int i6 = this.f573i[0] - 1;
            while (i6 >= 0 && !aVar.g(i6)) {
                i6--;
            }
            int i7 = i6 + 1;
            int[] iArr = this.f573i;
            i5 = iArr[0] - i7;
            i3 = iArr[1];
            i4 = i7;
        } else {
            int[] iArr2 = this.f573i;
            int i8 = iArr2[0];
            int iJ = aVar.j(iArr2[1] + 1);
            i3 = iJ;
            i4 = i8;
            i5 = iJ - this.f573i[1];
        }
        int[] iArrI = i();
        System.arraycopy(iArrI, 0, iArrI, 1, iArrI.length - 1);
        iArrI[0] = i5;
        try {
            return new U.c(U.a.p(iArrI, f568n), new int[]{i4, i3}, i4, i3, i2);
        } catch (H.j unused) {
            return null;
        }
    }

    public b E(L.a aVar, List list, int i2) throws H.j {
        U.c cVarC;
        U.b bVarV;
        boolean z2 = list.size() % 2 == 0;
        if (this.f574j) {
            z2 = !z2;
        }
        int iY = -1;
        boolean z3 = true;
        do {
            x(aVar, list, iY);
            cVarC = C(aVar, i2, z2);
            if (cVarC == null) {
                iY = y(aVar, this.f573i[0]);
            } else {
                z3 = false;
            }
        } while (z3);
        U.b bVarV2 = v(aVar, cVarC, z2, true);
        if (!list.isEmpty() && ((b) list.get(list.size() - 1)).f()) {
            throw H.j.a();
        }
        try {
            bVarV = v(aVar, cVarC, z2, false);
        } catch (H.j unused) {
            bVarV = null;
        }
        return new b(bVarV2, bVarV, cVarC, true);
    }

    public final void G(int i2, boolean z2) {
        boolean zC = false;
        int i3 = 0;
        boolean zC2 = false;
        while (true) {
            if (i3 >= this.f572h.size()) {
                break;
            }
            c cVar = (c) this.f572h.get(i3);
            if (cVar.b() > i2) {
                zC = cVar.c(this.f571g);
                break;
            } else {
                zC2 = cVar.c(this.f571g);
                i3++;
            }
        }
        if (zC || zC2 || A(this.f571g, this.f572h)) {
            return;
        }
        this.f572h.add(i3, new c(this.f571g, i2, z2));
        D(this.f571g, this.f572h);
    }

    @Override // T.k
    public n b(int i2, L.a aVar, Map map) {
        this.f571g.clear();
        this.f574j = false;
        try {
            return u(w(i2, aVar));
        } catch (H.j unused) {
            this.f571g.clear();
            this.f574j = true;
            return u(w(i2, aVar));
        }
    }

    public final void q(int i2) throws H.j {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        int iD = M.a.d(l());
        int iD2 = M.a.d(j());
        boolean z6 = true;
        if (iD > 13) {
            z2 = false;
            z3 = true;
        } else {
            z2 = iD < 4;
            z3 = false;
        }
        if (iD2 > 13) {
            z4 = false;
            z5 = true;
        } else {
            z4 = iD2 < 4;
            z5 = false;
        }
        int i3 = (iD + iD2) - i2;
        boolean z7 = (iD & 1) == 1;
        boolean z8 = (iD2 & 1) == 0;
        if (i3 == 1) {
            if (z7) {
                if (z8) {
                    throw H.j.a();
                }
                z6 = z2;
                z3 = true;
            } else {
                if (!z8) {
                    throw H.j.a();
                }
                z6 = z2;
                z5 = true;
            }
        } else if (i3 == -1) {
            if (z7) {
                if (z8) {
                    throw H.j.a();
                }
            } else {
                if (!z8) {
                    throw H.j.a();
                }
                z6 = z2;
                z4 = true;
            }
        } else {
            if (i3 != 0) {
                throw H.j.a();
            }
            if (z7) {
                if (!z8) {
                    throw H.j.a();
                }
                if (iD >= iD2) {
                    z6 = z2;
                    z4 = true;
                    z3 = true;
                }
                z5 = true;
            } else {
                if (z8) {
                    throw H.j.a();
                }
                z6 = z2;
            }
        }
        if (z6) {
            if (z3) {
                throw H.j.a();
            }
            U.a.n(l(), m());
        }
        if (z3) {
            U.a.g(l(), m());
        }
        if (z4) {
            if (z5) {
                throw H.j.a();
            }
            U.a.n(j(), m());
        }
        if (z5) {
            U.a.g(j(), k());
        }
    }

    public final boolean r() {
        b bVar = (b) this.f571g.get(0);
        U.b bVarC = bVar.c();
        U.b bVarD = bVar.d();
        if (bVarD == null) {
            return false;
        }
        int iA = bVarD.a();
        int i2 = 2;
        for (int i3 = 1; i3 < this.f571g.size(); i3++) {
            b bVar2 = (b) this.f571g.get(i3);
            iA += bVar2.c().a();
            int i4 = i2 + 1;
            U.b bVarD2 = bVar2.d();
            if (bVarD2 != null) {
                iA += bVarD2.a();
                i2 += 2;
            } else {
                i2 = i4;
            }
        }
        return ((i2 + (-4)) * Primes.SMALL_FACTOR_LIMIT) + (iA % Primes.SMALL_FACTOR_LIMIT) == bVarC.b();
    }

    public final List s(List list, int i2) throws H.j {
        while (i2 < this.f572h.size()) {
            c cVar = (c) this.f572h.get(i2);
            this.f571g.clear();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                this.f571g.addAll(((c) it.next()).a());
            }
            this.f571g.addAll(cVar.a());
            if (B(this.f571g)) {
                if (r()) {
                    return this.f571g;
                }
                ArrayList arrayList = new ArrayList(list);
                arrayList.add(cVar);
                try {
                    return s(arrayList, i2 + 1);
                } catch (H.j unused) {
                    continue;
                }
            }
            i2++;
        }
        throw H.j.a();
    }

    public final List t(boolean z2) {
        List listS = null;
        if (this.f572h.size() > 25) {
            this.f572h.clear();
            return null;
        }
        this.f571g.clear();
        if (z2) {
            Collections.reverse(this.f572h);
        }
        try {
            listS = s(new ArrayList(), 0);
        } catch (H.j unused) {
        }
        if (z2) {
            Collections.reverse(this.f572h);
        }
        return listS;
    }

    public U.b v(L.a aVar, U.c cVar, boolean z2, boolean z3) throws H.j {
        int[] iArrH = h();
        for (int i2 = 0; i2 < iArrH.length; i2++) {
            iArrH[i2] = 0;
        }
        if (z3) {
            k.f(aVar, cVar.b()[0], iArrH);
        } else {
            k.e(aVar, cVar.b()[1], iArrH);
            int i3 = 0;
            for (int length = iArrH.length - 1; i3 < length; length--) {
                int i4 = iArrH[i3];
                iArrH[i3] = iArrH[length];
                iArrH[length] = i4;
                i3++;
            }
        }
        float fD = M.a.d(iArrH) / 17.0f;
        float f2 = (cVar.b()[1] - cVar.b()[0]) / 15.0f;
        if (Math.abs(fD - f2) / f2 > 0.3f) {
            throw H.j.a();
        }
        int[] iArrL = l();
        int[] iArrJ = j();
        float[] fArrM = m();
        float[] fArrK = k();
        for (int i5 = 0; i5 < iArrH.length; i5++) {
            float f3 = (iArrH[i5] * 1.0f) / fD;
            int i6 = (int) (0.5f + f3);
            if (i6 <= 0) {
                if (f3 < 0.3f) {
                    throw H.j.a();
                }
                i6 = 1;
            } else if (i6 > 8) {
                if (f3 > 8.7f) {
                    throw H.j.a();
                }
                i6 = 8;
            }
            int i7 = i5 / 2;
            if ((i5 & 1) == 0) {
                iArrL[i7] = i6;
                fArrM[i7] = f3 - i6;
            } else {
                iArrJ[i7] = i6;
                fArrK[i7] = f3 - i6;
            }
        }
        q(17);
        int iC = (((cVar.c() * 4) + (z2 ? 0 : 2)) + (!z3 ? 1 : 0)) - 1;
        int i8 = 0;
        int i9 = 0;
        for (int length2 = iArrL.length - 1; length2 >= 0; length2--) {
            if (z(cVar, z2, z3)) {
                i8 += iArrL[length2] * f569o[iC][length2 * 2];
            }
            i9 += iArrL[length2];
        }
        int i10 = 0;
        for (int length3 = iArrJ.length - 1; length3 >= 0; length3--) {
            if (z(cVar, z2, z3)) {
                i10 += iArrJ[length3] * f569o[iC][(length3 * 2) + 1];
            }
        }
        int i11 = i8 + i10;
        if ((i9 & 1) != 0 || i9 > 13 || i9 < 4) {
            throw H.j.a();
        }
        int i12 = (13 - i9) / 2;
        int i13 = f565k[i12];
        return new U.b((f.b(iArrL, i13, true) * f566l[i12]) + f.b(iArrJ, 9 - i13, false) + f567m[i12], i11);
    }

    public List w(int i2, L.a aVar) throws H.j {
        boolean z2 = false;
        while (!z2) {
            try {
                List list = this.f571g;
                list.add(E(aVar, list, i2));
            } catch (H.j e2) {
                if (this.f571g.isEmpty()) {
                    throw e2;
                }
                z2 = true;
            }
        }
        if (r()) {
            return this.f571g;
        }
        boolean zIsEmpty = this.f572h.isEmpty();
        G(i2, false);
        if (!zIsEmpty) {
            List listT = t(false);
            if (listT != null) {
                return listT;
            }
            List listT2 = t(true);
            if (listT2 != null) {
                return listT2;
            }
        }
        throw H.j.a();
    }

    public final void x(L.a aVar, List list, int i2) throws H.j {
        int[] iArrI = i();
        iArrI[0] = 0;
        iArrI[1] = 0;
        iArrI[2] = 0;
        iArrI[3] = 0;
        int iK = aVar.k();
        if (i2 < 0) {
            i2 = list.isEmpty() ? 0 : ((b) list.get(list.size() - 1)).b().b()[1];
        }
        boolean z2 = list.size() % 2 != 0;
        if (this.f574j) {
            z2 = !z2;
        }
        boolean z3 = false;
        while (true) {
            if (i2 >= iK) {
                break;
            }
            boolean zG = aVar.g(i2);
            boolean z4 = !zG;
            if (zG) {
                z3 = z4;
                break;
            } else {
                i2++;
                z3 = z4;
            }
        }
        boolean z5 = z3;
        int i3 = 0;
        int i4 = i2;
        while (i2 < iK) {
            if (aVar.g(i2) != z5) {
                iArrI[i3] = iArrI[i3] + 1;
            } else {
                if (i3 == 3) {
                    if (z2) {
                        F(iArrI);
                    }
                    if (U.a.o(iArrI)) {
                        int[] iArr = this.f573i;
                        iArr[0] = i4;
                        iArr[1] = i2;
                        return;
                    }
                    if (z2) {
                        F(iArrI);
                    }
                    i4 += iArrI[0] + iArrI[1];
                    iArrI[0] = iArrI[2];
                    iArrI[1] = iArrI[3];
                    iArrI[2] = 0;
                    iArrI[3] = 0;
                    i3--;
                } else {
                    i3++;
                }
                iArrI[i3] = 1;
                z5 = !z5;
            }
            i2++;
        }
        throw H.j.a();
    }
}
