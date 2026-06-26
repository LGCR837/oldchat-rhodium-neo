package e0;

import H.r;
import c0.j;
import org.spongycastle.crypto.tls.CipherSuite;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[][] f4944a = {new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[][] f4945b = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[][] f4946c = {new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, CipherSuite.TLS_DHE_DSS_WITH_AES_256_CBC_SHA256, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, 130, -1}, new int[]{6, 30, 56, 82, CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA, -1}, new int[]{6, 34, 60, 86, 112, CipherSuite.TLS_PSK_WITH_RC4_128_SHA, -1}, new int[]{6, 30, 58, 86, 114, CipherSuite.TLS_DHE_PSK_WITH_RC4_128_SHA, -1}, new int[]{6, 34, 62, 90, 118, CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA, -1}, new int[]{6, 30, 54, 78, 102, 126, CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA}, new int[]{6, 24, 50, 76, 102, 128, CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA}, new int[]{6, 28, 54, 80, CipherSuite.TLS_DHE_DSS_WITH_AES_256_CBC_SHA256, CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256}, new int[]{6, 32, 58, 84, 110, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256}, new int[]{6, 26, 54, 82, 110, CipherSuite.TLS_PSK_WITH_RC4_128_SHA, CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256}, new int[]{6, 30, 58, 86, 114, CipherSuite.TLS_DHE_PSK_WITH_RC4_128_SHA, CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256}};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[][] f4947d = {new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};

    public static void a(L.a aVar, c0.f fVar, j jVar, int i2, b bVar) {
        c(bVar);
        d(jVar, bVar);
        l(fVar, i2, bVar);
        s(jVar, bVar);
        f(aVar, i2, bVar);
    }

    public static int b(int i2, int i3) {
        if (i3 == 0) {
            throw new IllegalArgumentException("0 polynomial");
        }
        int iN = n(i3);
        int iN2 = i2 << (iN - 1);
        while (n(iN2) >= iN) {
            iN2 ^= i3 << (n(iN2) - iN);
        }
        return iN2;
    }

    public static void c(b bVar) {
        bVar.a((byte) -1);
    }

    public static void d(j jVar, b bVar) throws r {
        j(bVar);
        e(bVar);
        r(jVar, bVar);
        k(bVar);
    }

    public static void e(b bVar) throws r {
        if (bVar.b(8, bVar.d() - 8) == 0) {
            throw new r();
        }
        bVar.f(8, bVar.d() - 8, 1);
    }

    public static void f(L.a aVar, int i2, b bVar) throws r {
        boolean zG;
        int iE = bVar.e() - 1;
        int iD = bVar.d() - 1;
        int i3 = 0;
        int i4 = -1;
        while (iE > 0) {
            if (iE == 6) {
                iE--;
            }
            while (iD >= 0 && iD < bVar.d()) {
                for (int i5 = 0; i5 < 2; i5++) {
                    int i6 = iE - i5;
                    if (o(bVar.b(i6, iD))) {
                        if (i3 < aVar.k()) {
                            zG = aVar.g(i3);
                            i3++;
                        } else {
                            zG = false;
                        }
                        if (i2 != -1 && d.f(i2, i6, iD)) {
                            zG = !zG;
                        }
                        bVar.g(i6, iD, zG);
                    }
                }
                iD += i4;
            }
            i4 = -i4;
            iD += i4;
            iE -= 2;
        }
        if (i3 == aVar.k()) {
            return;
        }
        throw new r("Not all bits consumed: " + i3 + '/' + aVar.k());
    }

    public static void g(int i2, int i3, b bVar) throws r {
        for (int i4 = 0; i4 < 8; i4++) {
            int i5 = i2 + i4;
            if (!o(bVar.b(i5, i3))) {
                throw new r();
            }
            bVar.f(i5, i3, 0);
        }
    }

    public static void h(int i2, int i3, b bVar) {
        for (int i4 = 0; i4 < 5; i4++) {
            int[] iArr = f4945b[i4];
            for (int i5 = 0; i5 < 5; i5++) {
                bVar.f(i2 + i5, i3 + i4, iArr[i5]);
            }
        }
    }

    public static void i(int i2, int i3, b bVar) {
        for (int i4 = 0; i4 < 7; i4++) {
            int[] iArr = f4944a[i4];
            for (int i5 = 0; i5 < 7; i5++) {
                bVar.f(i2 + i5, i3 + i4, iArr[i5]);
            }
        }
    }

    public static void j(b bVar) throws r {
        int length = f4944a[0].length;
        i(0, 0, bVar);
        i(bVar.e() - length, 0, bVar);
        i(0, bVar.e() - length, bVar);
        g(0, 7, bVar);
        g(bVar.e() - 8, 7, bVar);
        g(0, bVar.e() - 8, bVar);
        m(7, 0, bVar);
        m(bVar.d() - 8, 0, bVar);
        m(7, bVar.d() - 7, bVar);
    }

    public static void k(b bVar) {
        int i2 = 8;
        while (i2 < bVar.e() - 8) {
            int i3 = i2 + 1;
            int i4 = i3 % 2;
            if (o(bVar.b(i2, 6))) {
                bVar.f(i2, 6, i4);
            }
            if (o(bVar.b(6, i2))) {
                bVar.f(6, i2, i4);
            }
            i2 = i3;
        }
    }

    public static void l(c0.f fVar, int i2, b bVar) throws r {
        L.a aVar = new L.a();
        p(fVar, i2, aVar);
        for (int i3 = 0; i3 < aVar.k(); i3++) {
            boolean zG = aVar.g((aVar.k() - 1) - i3);
            int[] iArr = f4947d[i3];
            bVar.g(iArr[0], iArr[1], zG);
            if (i3 < 8) {
                bVar.g((bVar.e() - i3) - 1, 8, zG);
            } else {
                bVar.g(8, (bVar.d() - 7) + (i3 - 8), zG);
            }
        }
    }

    public static void m(int i2, int i3, b bVar) throws r {
        for (int i4 = 0; i4 < 7; i4++) {
            int i5 = i3 + i4;
            if (!o(bVar.b(i2, i5))) {
                throw new r();
            }
            bVar.f(i2, i5, 0);
        }
    }

    public static int n(int i2) {
        return 32 - Integer.numberOfLeadingZeros(i2);
    }

    public static boolean o(int i2) {
        return i2 == -1;
    }

    public static void p(c0.f fVar, int i2, L.a aVar) throws r {
        if (!f.b(i2)) {
            throw new r("Invalid mask pattern");
        }
        int iB = (fVar.b() << 3) | i2;
        aVar.c(iB, 5);
        aVar.c(b(iB, 1335), 10);
        L.a aVar2 = new L.a();
        aVar2.c(21522, 15);
        aVar.s(aVar2);
        if (aVar.k() == 15) {
            return;
        }
        throw new r("should not happen but we got: " + aVar.k());
    }

    public static void q(j jVar, L.a aVar) throws r {
        aVar.c(jVar.j(), 6);
        aVar.c(b(jVar.j(), 7973), 12);
        if (aVar.k() == 18) {
            return;
        }
        throw new r("should not happen but we got: " + aVar.k());
    }

    public static void r(j jVar, b bVar) {
        if (jVar.j() < 2) {
            return;
        }
        int[] iArr = f4946c[jVar.j() - 1];
        for (int i2 : iArr) {
            if (i2 >= 0) {
                for (int i3 : iArr) {
                    if (i3 >= 0 && o(bVar.b(i3, i2))) {
                        h(i3 - 2, i2 - 2, bVar);
                    }
                }
            }
        }
    }

    public static void s(j jVar, b bVar) throws r {
        if (jVar.j() < 7) {
            return;
        }
        L.a aVar = new L.a();
        q(jVar, aVar);
        int i2 = 17;
        for (int i3 = 0; i3 < 6; i3++) {
            for (int i4 = 0; i4 < 3; i4++) {
                boolean zG = aVar.g(i2);
                i2--;
                bVar.g(i3, (bVar.d() - 11) + i4, zG);
                bVar.g((bVar.d() - 11) + i4, i3, zG);
            }
        }
    }
}
