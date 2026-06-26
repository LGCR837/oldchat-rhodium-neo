package T;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class d extends k {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final char[] f499c = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".toCharArray();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f500d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f501e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final StringBuilder f502a = new StringBuilder(20);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f503b = new int[6];

    static {
        int[] iArr = {276, 328, 324, 322, 296, 292, 290, 336, 274, 266, 424, 420, 418, 404, 402, 394, 360, 356, 354, 308, 282, 344, 332, 326, 300, 278, 436, 434, 428, 422, 406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, 350};
        f500d = iArr;
        f501e = iArr[47];
    }

    public static void g(CharSequence charSequence) throws H.d {
        int length = charSequence.length();
        h(charSequence, length - 2, 20);
        h(charSequence, length - 1, 15);
    }

    public static void h(CharSequence charSequence, int i2, int i3) throws H.d {
        int iIndexOf = 0;
        int i4 = 1;
        for (int i5 = i2 - 1; i5 >= 0; i5--) {
            iIndexOf += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(charSequence.charAt(i5)) * i4;
            i4++;
            if (i4 > i3) {
                i4 = 1;
            }
        }
        if (charSequence.charAt(i2) != f499c[iIndexOf % 47]) {
            throw H.d.a();
        }
    }

    private static String i(CharSequence charSequence) throws H.g {
        int i2;
        char c2;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i3 = 0;
        while (i3 < length) {
            char cCharAt = charSequence.charAt(i3);
            if (cCharAt >= 'a' && cCharAt <= 'd') {
                if (i3 >= length - 1) {
                    throw H.g.a();
                }
                i3++;
                char cCharAt2 = charSequence.charAt(i3);
                switch (cCharAt) {
                    case 'a':
                        if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                            throw H.g.a();
                        }
                        i2 = cCharAt2 - '@';
                        c2 = (char) i2;
                        sb.append(c2);
                        break;
                    case 'b':
                        if (cCharAt2 >= 'A' && cCharAt2 <= 'E') {
                            i2 = cCharAt2 - '&';
                        } else if (cCharAt2 >= 'F' && cCharAt2 <= 'J') {
                            i2 = cCharAt2 - 11;
                        } else if (cCharAt2 >= 'K' && cCharAt2 <= 'O') {
                            i2 = cCharAt2 + 16;
                        } else if (cCharAt2 >= 'P' && cCharAt2 <= 'S') {
                            i2 = cCharAt2 + '+';
                        } else {
                            if (cCharAt2 < 'T' || cCharAt2 > 'Z') {
                                throw H.g.a();
                            }
                            c2 = 127;
                            sb.append(c2);
                        }
                        c2 = (char) i2;
                        sb.append(c2);
                        break;
                    case 'c':
                        if (cCharAt2 >= 'A' && cCharAt2 <= 'O') {
                            i2 = cCharAt2 - ' ';
                            c2 = (char) i2;
                            sb.append(c2);
                        } else {
                            if (cCharAt2 != 'Z') {
                                throw H.g.a();
                            }
                            c2 = ':';
                            sb.append(c2);
                        }
                        break;
                    case 'd':
                        if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                            throw H.g.a();
                        }
                        i2 = cCharAt2 + ' ';
                        c2 = (char) i2;
                        sb.append(c2);
                        break;
                    default:
                        c2 = 0;
                        sb.append(c2);
                        break;
                }
            } else {
                sb.append(cCharAt);
            }
            i3++;
        }
        return sb.toString();
    }

    private static char k(int i2) throws H.j {
        int i3 = 0;
        while (true) {
            int[] iArr = f500d;
            if (i3 >= iArr.length) {
                throw H.j.a();
            }
            if (iArr[i3] == i2) {
                return f499c[i3];
            }
            i3++;
        }
    }

    public static int l(int[] iArr) {
        int i2 = 0;
        for (int i3 : iArr) {
            i2 += i3;
        }
        int length = iArr.length;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            int iRound = Math.round((iArr[i5] * 9.0f) / i2);
            if (iRound <= 0 || iRound > 4) {
                return -1;
            }
            if ((i5 & 1) == 0) {
                for (int i6 = 0; i6 < iRound; i6++) {
                    i4 = (i4 << 1) | 1;
                }
            } else {
                i4 <<= iRound;
            }
        }
        return i4;
    }

    @Override // T.k
    public H.n b(int i2, L.a aVar, Map map) throws H.j, H.d {
        int i3 = aVar.i(j(aVar)[1]);
        int iK = aVar.k();
        int[] iArr = this.f503b;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.f502a;
        sb.setLength(0);
        while (true) {
            k.e(aVar, i3, iArr);
            int iL = l(iArr);
            if (iL < 0) {
                throw H.j.a();
            }
            char cK = k(iL);
            sb.append(cK);
            int i4 = i3;
            for (int i5 : iArr) {
                i4 += i5;
            }
            int i6 = aVar.i(i4);
            if (cK == '*') {
                sb.deleteCharAt(sb.length() - 1);
                int i7 = 0;
                for (int i8 : iArr) {
                    i7 += i8;
                }
                if (i6 == iK || !aVar.g(i6)) {
                    throw H.j.a();
                }
                if (sb.length() < 2) {
                    throw H.j.a();
                }
                g(sb);
                sb.setLength(sb.length() - 2);
                float f2 = i2;
                return new H.n(i(sb), null, new H.p[]{new H.p((r0[1] + r0[0]) / 2.0f, f2), new H.p(i3 + (i7 / 2.0f), f2)}, H.a.CODE_93);
            }
            i3 = i6;
        }
    }

    public final int[] j(L.a aVar) throws H.j {
        int iK = aVar.k();
        int i2 = aVar.i(0);
        Arrays.fill(this.f503b, 0);
        int[] iArr = this.f503b;
        int length = iArr.length;
        int i3 = i2;
        boolean z2 = false;
        int i4 = 0;
        while (i2 < iK) {
            if (aVar.g(i2) != z2) {
                iArr[i4] = iArr[i4] + 1;
            } else {
                if (i4 != length - 1) {
                    i4++;
                } else {
                    if (l(iArr) == f501e) {
                        return new int[]{i3, i2};
                    }
                    i3 += iArr[0] + iArr[1];
                    int i5 = i4 - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i5);
                    iArr[i5] = 0;
                    iArr[i4] = 0;
                    i4--;
                }
                iArr[i4] = 1;
                z2 = !z2;
            }
            i2++;
        }
        throw H.j.a();
    }
}
