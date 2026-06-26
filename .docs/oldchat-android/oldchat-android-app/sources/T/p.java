package T;

import c.AbstractC0177z;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class p extends k {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f525d = {1, 1, 1};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f526e = {1, 1, 1, 1, 1};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f527f = {1, 1, 1, 1, 1, 1};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[][] f528g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int[][] f529h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final StringBuilder f530a = new StringBuilder(20);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o f531b = new o();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g f532c = new g();

    static {
        int[][] iArr = {new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
        f528g = iArr;
        int[][] iArr2 = new int[20][];
        f529h = iArr2;
        System.arraycopy(iArr, 0, iArr2, 0, 10);
        for (int i2 = 10; i2 < 20; i2++) {
            int[] iArr3 = f528g[i2 - 10];
            int[] iArr4 = new int[iArr3.length];
            for (int i3 = 0; i3 < iArr3.length; i3++) {
                iArr4[i3] = iArr3[(iArr3.length - i3) - 1];
            }
            f529h[i2] = iArr4;
        }
    }

    public static boolean h(CharSequence charSequence) {
        int length = charSequence.length();
        if (length == 0) {
            return false;
        }
        int i2 = length - 1;
        return q(charSequence.subSequence(0, i2)) == Character.digit(charSequence.charAt(i2), 10);
    }

    public static int i(L.a aVar, int[] iArr, int i2, int[][] iArr2) throws H.j {
        k.e(aVar, i2, iArr);
        int length = iArr2.length;
        float f2 = 0.48f;
        int i3 = -1;
        for (int i4 = 0; i4 < length; i4++) {
            float fD = k.d(iArr, iArr2[i4], 0.7f);
            if (fD < f2) {
                i3 = i4;
                f2 = fD;
            }
        }
        if (i3 >= 0) {
            return i3;
        }
        throw H.j.a();
    }

    public static int[] m(L.a aVar, int i2, boolean z2, int[] iArr) {
        return n(aVar, i2, z2, iArr, new int[iArr.length]);
    }

    public static int[] n(L.a aVar, int i2, boolean z2, int[] iArr, int[] iArr2) throws H.j {
        int iK = aVar.k();
        int iJ = z2 ? aVar.j(i2) : aVar.i(i2);
        int length = iArr.length;
        boolean z3 = z2;
        int i3 = 0;
        int i4 = iJ;
        while (iJ < iK) {
            if (aVar.g(iJ) != z3) {
                iArr2[i3] = iArr2[i3] + 1;
            } else {
                if (i3 != length - 1) {
                    i3++;
                } else {
                    if (k.d(iArr2, iArr, 0.7f) < 0.48f) {
                        return new int[]{i4, iJ};
                    }
                    i4 += iArr2[0] + iArr2[1];
                    int i5 = i3 - 1;
                    System.arraycopy(iArr2, 2, iArr2, 0, i5);
                    iArr2[i5] = 0;
                    iArr2[i3] = 0;
                    i3--;
                }
                iArr2[i3] = 1;
                z3 = !z3;
            }
            iJ++;
        }
        throw H.j.a();
    }

    public static int[] o(L.a aVar) throws H.j {
        int[] iArr = new int[f525d.length];
        int[] iArrN = null;
        boolean zM = false;
        int i2 = 0;
        while (!zM) {
            int[] iArr2 = f525d;
            Arrays.fill(iArr, 0, iArr2.length, 0);
            iArrN = n(aVar, i2, false, iArr2, iArr);
            int i3 = iArrN[0];
            int i4 = iArrN[1];
            int i5 = i3 - (i4 - i3);
            if (i5 >= 0) {
                zM = aVar.m(i5, i3, false);
            }
            i2 = i4;
        }
        return iArrN;
    }

    public static int q(CharSequence charSequence) throws H.g {
        int length = charSequence.length();
        int i2 = 0;
        for (int i3 = length - 1; i3 >= 0; i3 -= 2) {
            int iCharAt = charSequence.charAt(i3) - '0';
            if (iCharAt < 0 || iCharAt > 9) {
                throw H.g.a();
            }
            i2 += iCharAt;
        }
        int i4 = i2 * 3;
        for (int i5 = length - 2; i5 >= 0; i5 -= 2) {
            int iCharAt2 = charSequence.charAt(i5) - '0';
            if (iCharAt2 < 0 || iCharAt2 > 9) {
                throw H.g.a();
            }
            i4 += iCharAt2;
        }
        return (1000 - i4) % 10;
    }

    @Override // T.k
    public H.n b(int i2, L.a aVar, Map map) {
        return l(i2, aVar, o(aVar), map);
    }

    public boolean g(String str) {
        return h(str);
    }

    public int[] j(L.a aVar, int i2) {
        return m(aVar, i2, false, f525d);
    }

    public abstract int k(L.a aVar, int[] iArr, StringBuilder sb);

    public H.n l(int i2, L.a aVar, int[] iArr, Map map) throws H.g, H.j, H.d {
        int length;
        String strC;
        if (map != null) {
            AbstractC0177z.a(map.get(H.e.NEED_RESULT_POINT_CALLBACK));
        }
        StringBuilder sb = this.f530a;
        sb.setLength(0);
        int[] iArrJ = j(aVar, k(aVar, iArr, sb));
        int i3 = iArrJ[1];
        int i4 = (i3 - iArrJ[0]) + i3;
        if (i4 >= aVar.k() || !aVar.m(i3, i4, false)) {
            throw H.j.a();
        }
        String string = sb.toString();
        if (string.length() < 8) {
            throw H.g.a();
        }
        if (!g(string)) {
            throw H.d.a();
        }
        H.a aVarP = p();
        float f2 = i2;
        H.n nVar = new H.n(string, null, new H.p[]{new H.p((iArr[1] + iArr[0]) / 2.0f, f2), new H.p((iArrJ[1] + iArrJ[0]) / 2.0f, f2)}, aVarP);
        try {
            H.n nVarA = this.f531b.a(i2, aVar, iArrJ[1]);
            nVar.h(H.o.UPC_EAN_EXTENSION, nVarA.f());
            nVar.g(nVarA.d());
            nVar.a(nVarA.e());
            length = nVarA.f().length();
        } catch (H.m unused) {
            length = 0;
        }
        int[] iArr2 = map != null ? (int[]) map.get(H.e.ALLOWED_EAN_EXTENSIONS) : null;
        if (iArr2 != null) {
            for (int i5 : iArr2) {
                if (length != i5) {
                }
            }
            throw H.j.a();
        }
        if ((aVarP == H.a.EAN_13 || aVarP == H.a.UPC_A) && (strC = this.f532c.c(string)) != null) {
            nVar.h(H.o.POSSIBLE_COUNTRY, strC);
        }
        return nVar;
    }

    public abstract H.a p();
}
