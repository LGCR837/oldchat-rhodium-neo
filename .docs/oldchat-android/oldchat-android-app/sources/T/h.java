package T;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class h extends k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f509b = {6, 8, 10, 12, 14};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f510c = {1, 1, 1, 1};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[][] f511d = {new int[]{1, 1, 2}, new int[]{1, 1, 3}};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[][] f512e = {new int[]{1, 1, 2, 2, 1}, new int[]{2, 1, 1, 1, 2}, new int[]{1, 2, 1, 1, 2}, new int[]{2, 2, 1, 1, 1}, new int[]{1, 1, 2, 1, 2}, new int[]{2, 1, 2, 1, 1}, new int[]{1, 2, 2, 1, 1}, new int[]{1, 1, 1, 2, 2}, new int[]{2, 1, 1, 2, 1}, new int[]{1, 2, 1, 2, 1}, new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f513a = -1;

    public static int g(int[] iArr) throws H.j {
        int length = f512e.length;
        float f2 = 0.38f;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            float fD = k.d(iArr, f512e[i3], 0.5f);
            if (fD < f2) {
                i2 = i3;
                f2 = fD;
            } else if (fD == f2) {
                i2 = -1;
            }
        }
        if (i2 >= 0) {
            return i2 % 10;
        }
        throw H.j.a();
    }

    public static void i(L.a aVar, int i2, int i3, StringBuilder sb) throws H.j {
        int[] iArr = new int[10];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[5];
        while (i2 < i3) {
            k.e(aVar, i2, iArr);
            for (int i4 = 0; i4 < 5; i4++) {
                int i5 = i4 * 2;
                iArr2[i4] = iArr[i5];
                iArr3[i4] = iArr[i5 + 1];
            }
            sb.append((char) (g(iArr2) + 48));
            sb.append((char) (g(iArr3) + 48));
            for (int i6 = 0; i6 < 10; i6++) {
                i2 += iArr[i6];
            }
        }
    }

    public static int[] k(L.a aVar, int i2, int[] iArr) throws H.j {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        int iK = aVar.k();
        int i3 = i2;
        boolean z2 = false;
        int i4 = 0;
        while (i2 < iK) {
            if (aVar.g(i2) != z2) {
                iArr2[i4] = iArr2[i4] + 1;
            } else {
                if (i4 != length - 1) {
                    i4++;
                } else {
                    if (k.d(iArr2, iArr, 0.5f) < 0.38f) {
                        return new int[]{i3, i2};
                    }
                    i3 += iArr2[0] + iArr2[1];
                    int i5 = i4 - 1;
                    System.arraycopy(iArr2, 2, iArr2, 0, i5);
                    iArr2[i5] = 0;
                    iArr2[i4] = 0;
                    i4--;
                }
                iArr2[i4] = 1;
                z2 = !z2;
            }
            i2++;
        }
        throw H.j.a();
    }

    public static int l(L.a aVar) throws H.j {
        int iK = aVar.k();
        int i2 = aVar.i(0);
        if (i2 != iK) {
            return i2;
        }
        throw H.j.a();
    }

    @Override // T.k
    public H.n b(int i2, L.a aVar, Map map) throws H.g, H.j {
        boolean z2;
        int[] iArrJ = j(aVar);
        int[] iArrH = h(aVar);
        StringBuilder sb = new StringBuilder(20);
        i(aVar, iArrJ[1], iArrH[0], sb);
        String string = sb.toString();
        int[] iArr = map != null ? (int[]) map.get(H.e.ALLOWED_LENGTHS) : null;
        if (iArr == null) {
            iArr = f509b;
        }
        int length = string.length();
        int length2 = iArr.length;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= length2) {
                z2 = false;
                break;
            }
            int i5 = iArr[i3];
            if (length == i5) {
                z2 = true;
                break;
            }
            if (i5 > i4) {
                i4 = i5;
            }
            i3++;
        }
        if (!z2 && length > i4) {
            z2 = true;
        }
        if (!z2) {
            throw H.g.a();
        }
        float f2 = i2;
        return new H.n(string, null, new H.p[]{new H.p(iArrJ[1], f2), new H.p(iArrH[0], f2)}, H.a.ITF);
    }

    public final int[] h(L.a aVar) {
        int[] iArrK;
        aVar.o();
        try {
            int iL = l(aVar);
            try {
                iArrK = k(aVar, iL, f511d[0]);
            } catch (H.j unused) {
                iArrK = k(aVar, iL, f511d[1]);
            }
            m(aVar, iArrK[0]);
            int i2 = iArrK[0];
            iArrK[0] = aVar.k() - iArrK[1];
            iArrK[1] = aVar.k() - i2;
            return iArrK;
        } finally {
            aVar.o();
        }
    }

    public final int[] j(L.a aVar) throws H.j {
        int[] iArrK = k(aVar, l(aVar), f510c);
        int i2 = iArrK[1];
        int i3 = iArrK[0];
        this.f513a = (i2 - i3) / 4;
        m(aVar, i3);
        return iArrK;
    }

    public final void m(L.a aVar, int i2) throws H.j {
        int i3 = this.f513a * 10;
        if (i3 >= i2) {
            i3 = i2;
        }
        for (int i4 = i2 - 1; i3 > 0 && i4 >= 0 && !aVar.g(i4); i4--) {
            i3--;
        }
        if (i3 != 0) {
            throw H.j.a();
        }
    }
}
