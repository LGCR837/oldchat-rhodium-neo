package e0;

import H.r;
import c0.h;
import c0.j;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f4942a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f4943a;

        static {
            int[] iArr = new int[h.values().length];
            f4943a = iArr;
            try {
                iArr[h.NUMERIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4943a[h.ALPHANUMERIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4943a[h.BYTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4943a[h.KANJI.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static void a(String str, L.a aVar, String str2) throws r {
        try {
            for (byte b2 : str.getBytes(str2)) {
                aVar.c(b2, 8);
            }
        } catch (UnsupportedEncodingException e2) {
            throw new r(e2);
        }
    }

    public static void b(CharSequence charSequence, L.a aVar) throws r {
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            int iP = p(charSequence.charAt(i2));
            if (iP == -1) {
                throw new r();
            }
            int i3 = i2 + 1;
            if (i3 < length) {
                int iP2 = p(charSequence.charAt(i3));
                if (iP2 == -1) {
                    throw new r();
                }
                aVar.c((iP * 45) + iP2, 11);
                i2 += 2;
            } else {
                aVar.c(iP, 6);
                i2 = i3;
            }
        }
    }

    public static void c(String str, h hVar, L.a aVar, String str2) throws r {
        int i2 = a.f4943a[hVar.ordinal()];
        if (i2 == 1) {
            h(str, aVar);
            return;
        }
        if (i2 == 2) {
            b(str, aVar);
        } else if (i2 == 3) {
            a(str, aVar, str2);
        } else {
            if (i2 != 4) {
                throw new r("Invalid mode: ".concat(String.valueOf(hVar)));
            }
            e(str, aVar);
        }
    }

    public static void d(L.d dVar, L.a aVar) {
        aVar.c(h.ECI.b(), 4);
        aVar.c(dVar.c(), 8);
    }

    public static void e(String str, L.a aVar) throws r {
        int i2;
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            for (int i3 = 0; i3 < length; i3 += 2) {
                int i4 = ((bytes[i3] & 255) << 8) | (bytes[i3 + 1] & 255);
                int i5 = 33088;
                if (i4 >= 33088 && i4 <= 40956) {
                    i2 = i4 - i5;
                } else if (i4 < 57408 || i4 > 60351) {
                    i2 = -1;
                } else {
                    i5 = 49472;
                    i2 = i4 - i5;
                }
                if (i2 == -1) {
                    throw new r("Invalid byte sequence");
                }
                aVar.c(((i2 >> 8) * 192) + (i2 & 255), 13);
            }
        } catch (UnsupportedEncodingException e2) {
            throw new r(e2);
        }
    }

    public static void f(int i2, j jVar, h hVar, L.a aVar) throws r {
        int iC = hVar.c(jVar);
        int i3 = 1 << iC;
        if (i2 < i3) {
            aVar.c(i2, iC);
            return;
        }
        throw new r(i2 + " is bigger than " + (i3 - 1));
    }

    public static void g(h hVar, L.a aVar) {
        aVar.c(hVar.b(), 4);
    }

    public static void h(CharSequence charSequence, L.a aVar) {
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            int iCharAt = charSequence.charAt(i2) - '0';
            int i3 = i2 + 2;
            if (i3 < length) {
                aVar.c((iCharAt * 100) + ((charSequence.charAt(i2 + 1) - '0') * 10) + (charSequence.charAt(i3) - '0'), 10);
                i2 += 3;
            } else {
                i2++;
                if (i2 < length) {
                    aVar.c((iCharAt * 10) + (charSequence.charAt(i2) - '0'), 7);
                    i2 = i3;
                } else {
                    aVar.c(iCharAt, 4);
                }
            }
        }
    }

    public static int i(h hVar, L.a aVar, L.a aVar2, j jVar) {
        return aVar.k() + hVar.c(jVar) + aVar2.k();
    }

    public static int j(b bVar) {
        return d.a(bVar) + d.c(bVar) + d.d(bVar) + d.e(bVar);
    }

    public static int k(L.a aVar, c0.f fVar, j jVar, b bVar) {
        int i2 = Integer.MAX_VALUE;
        int i3 = -1;
        for (int i4 = 0; i4 < 8; i4++) {
            e.a(aVar, fVar, jVar, i4, bVar);
            int iJ = j(bVar);
            if (iJ < i2) {
                i3 = i4;
                i2 = iJ;
            }
        }
        return i3;
    }

    public static h l(String str, String str2) {
        if ("Shift_JIS".equals(str2) && s(str)) {
            return h.KANJI;
        }
        boolean z2 = false;
        boolean z3 = false;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt >= '0' && cCharAt <= '9') {
                z3 = true;
            } else {
                if (p(cCharAt) == -1) {
                    return h.BYTE;
                }
                z2 = true;
            }
        }
        return z2 ? h.ALPHANUMERIC : z3 ? h.NUMERIC : h.BYTE;
    }

    public static j m(int i2, c0.f fVar) throws r {
        for (int i3 = 1; i3 <= 40; i3++) {
            j jVarI = j.i(i3);
            if (v(i2, jVarI, fVar)) {
                return jVarI;
            }
        }
        throw new r("Data too big");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static e0.f n(java.lang.String r6, c0.f r7, java.util.Map r8) throws H.r {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: e0.c.n(java.lang.String, c0.f, java.util.Map):e0.f");
    }

    public static byte[] o(byte[] bArr, int i2) {
        int length = bArr.length;
        int[] iArr = new int[length + i2];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = bArr[i3] & 255;
        }
        new N.d(N.a.f426l).b(iArr, i2);
        byte[] bArr2 = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            bArr2[i4] = (byte) iArr[length + i4];
        }
        return bArr2;
    }

    public static int p(int i2) {
        int[] iArr = f4942a;
        if (i2 < iArr.length) {
            return iArr[i2];
        }
        return -1;
    }

    public static void q(int i2, int i3, int i4, int i5, int[] iArr, int[] iArr2) throws r {
        if (i5 >= i4) {
            throw new r("Block ID too large");
        }
        int i6 = i2 % i4;
        int i7 = i4 - i6;
        int i8 = i2 / i4;
        int i9 = i8 + 1;
        int i10 = i3 / i4;
        int i11 = i10 + 1;
        int i12 = i8 - i10;
        int i13 = i9 - i11;
        if (i12 != i13) {
            throw new r("EC bytes mismatch");
        }
        if (i4 != i7 + i6) {
            throw new r("RS blocks mismatch");
        }
        if (i2 != ((i10 + i12) * i7) + ((i11 + i13) * i6)) {
            throw new r("Total bytes mismatch");
        }
        if (i5 < i7) {
            iArr[0] = i10;
            iArr2[0] = i12;
        } else {
            iArr[0] = i11;
            iArr2[0] = i13;
        }
    }

    public static L.a r(L.a aVar, int i2, int i3, int i4) throws r {
        if (aVar.l() != i3) {
            throw new r("Number of bits and data bytes does not match");
        }
        ArrayList arrayList = new ArrayList(i4);
        int i5 = 0;
        int iMax = 0;
        int iMax2 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            q(i2, i3, i4, i6, iArr, iArr2);
            int i7 = iArr[0];
            byte[] bArr = new byte[i7];
            aVar.r(i5 << 3, bArr, 0, i7);
            byte[] bArrO = o(bArr, iArr2[0]);
            arrayList.add(new e0.a(bArr, bArrO));
            iMax = Math.max(iMax, i7);
            iMax2 = Math.max(iMax2, bArrO.length);
            i5 += iArr[0];
        }
        if (i3 != i5) {
            throw new r("Data bytes does not match offset");
        }
        L.a aVar2 = new L.a();
        for (int i8 = 0; i8 < iMax; i8++) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                byte[] bArrA = ((e0.a) it.next()).a();
                if (i8 < bArrA.length) {
                    aVar2.c(bArrA[i8], 8);
                }
            }
        }
        for (int i9 = 0; i9 < iMax2; i9++) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                byte[] bArrB = ((e0.a) it2.next()).b();
                if (i9 < bArrB.length) {
                    aVar2.c(bArrB[i9], 8);
                }
            }
        }
        if (i2 == aVar2.l()) {
            return aVar2;
        }
        throw new r("Interleaving error: " + i2 + " and " + aVar2.l() + " differ.");
    }

    public static boolean s(String str) {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i2 = 0; i2 < length; i2 += 2) {
                int i3 = bytes[i2] & 255;
                if ((i3 < 129 || i3 > 159) && (i3 < 224 || i3 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }

    public static j t(c0.f fVar, h hVar, L.a aVar, L.a aVar2) {
        return m(i(hVar, aVar, aVar2, m(i(hVar, aVar, aVar2, j.i(1)), fVar)), fVar);
    }

    public static void u(int i2, L.a aVar) throws r {
        int i3 = i2 << 3;
        if (aVar.k() > i3) {
            throw new r("data bits cannot fit in the QR Code" + aVar.k() + " > " + i3);
        }
        for (int i4 = 0; i4 < 4 && aVar.k() < i3; i4++) {
            aVar.a(false);
        }
        int iK = aVar.k() & 7;
        if (iK > 0) {
            while (iK < 8) {
                aVar.a(false);
                iK++;
            }
        }
        int iL = i2 - aVar.l();
        for (int i5 = 0; i5 < iL; i5++) {
            aVar.c((i5 & 1) == 0 ? 236 : 17, 8);
        }
        if (aVar.k() != i3) {
            throw new r("Bits size does not equal capacity");
        }
    }

    public static boolean v(int i2, j jVar, c0.f fVar) {
        return jVar.h() - jVar.f(fVar).d() >= (i2 + 7) / 8;
    }
}
