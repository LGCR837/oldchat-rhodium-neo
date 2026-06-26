package J;

import H.g;
import N.c;
import N.e;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f323b = {"CTRL_PS", " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String[] f324c = {"CTRL_PS", " ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String[] f325d = {"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "\u007f", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String[] f326e = {"", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/", ":", ";", "<", "=", ">", "?", "[", "]", "{", "}", "CTRL_UL"};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String[] f327f = {"CTRL_PS", " ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ",", ".", "CTRL_UL", "CTRL_US"};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public I.a f328a;

    /* JADX INFO: renamed from: J.a$a, reason: collision with other inner class name */
    public static /* synthetic */ class C0006a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f329a;

        static {
            int[] iArr = new int[b.values().length];
            f329a = iArr;
            try {
                iArr[b.UPPER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f329a[b.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f329a[b.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f329a[b.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f329a[b.DIGIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public enum b {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    public static byte[] a(boolean[] zArr) {
        int length = (zArr.length + 7) / 8;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            bArr[i2] = h(zArr, i2 << 3);
        }
        return bArr;
    }

    public static String e(b bVar, int i2) {
        int i3 = C0006a.f329a[bVar.ordinal()];
        if (i3 == 1) {
            return f323b[i2];
        }
        if (i3 == 2) {
            return f324c[i2];
        }
        if (i3 == 3) {
            return f325d[i2];
        }
        if (i3 == 4) {
            return f326e[i2];
        }
        if (i3 == 5) {
            return f327f[i2];
        }
        throw new IllegalStateException("Bad table");
    }

    public static String f(boolean[] zArr) {
        int length = zArr.length;
        b bVar = b.UPPER;
        StringBuilder sb = new StringBuilder(20);
        b bVarG = bVar;
        int i2 = 0;
        while (i2 < length) {
            if (bVar != b.BINARY) {
                int i3 = bVar == b.DIGIT ? 4 : 5;
                if (length - i2 < i3) {
                    break;
                }
                int i4 = i(zArr, i2, i3);
                i2 += i3;
                String strE = e(bVar, i4);
                if (strE.startsWith("CTRL_")) {
                    bVarG = g(strE.charAt(5));
                    if (strE.charAt(6) != 'L') {
                        bVarG = bVar;
                        bVar = bVarG;
                    }
                } else {
                    sb.append(strE);
                }
                bVar = bVarG;
            } else {
                if (length - i2 < 5) {
                    break;
                }
                int i5 = i(zArr, i2, 5);
                int i6 = i2 + 5;
                if (i5 == 0) {
                    if (length - i6 < 11) {
                        break;
                    }
                    i5 = i(zArr, i6, 11) + 31;
                    i6 = i2 + 16;
                }
                int i7 = 0;
                while (true) {
                    if (i7 >= i5) {
                        i2 = i6;
                        break;
                    }
                    if (length - i6 < 8) {
                        i2 = length;
                        break;
                    }
                    sb.append((char) i(zArr, i6, 8));
                    i6 += 8;
                    i7++;
                }
                bVar = bVarG;
            }
        }
        return sb.toString();
    }

    public static b g(char c2) {
        return c2 != 'B' ? c2 != 'D' ? c2 != 'P' ? c2 != 'L' ? c2 != 'M' ? b.UPPER : b.MIXED : b.LOWER : b.PUNCT : b.DIGIT : b.BINARY;
    }

    public static byte h(boolean[] zArr, int i2) {
        int length = zArr.length - i2;
        return (byte) (length >= 8 ? i(zArr, i2, 8) : i(zArr, i2, length) << (8 - length));
    }

    public static int i(boolean[] zArr, int i2, int i3) {
        int i4 = 0;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 <<= 1;
            if (zArr[i5]) {
                i4 |= 1;
            }
        }
        return i4;
    }

    public static int j(int i2, boolean z2) {
        return ((z2 ? 88 : 112) + (i2 << 4)) * i2;
    }

    public final boolean[] b(boolean[] zArr) throws g {
        int i2;
        N.a aVar;
        if (this.f328a.d() <= 2) {
            aVar = N.a.f424j;
            i2 = 6;
        } else {
            i2 = 8;
            if (this.f328a.d() <= 8) {
                aVar = N.a.f428n;
            } else if (this.f328a.d() <= 22) {
                aVar = N.a.f423i;
                i2 = 10;
            } else {
                aVar = N.a.f422h;
                i2 = 12;
            }
        }
        int iC = this.f328a.c();
        int length = zArr.length / i2;
        if (length < iC) {
            throw g.a();
        }
        int length2 = zArr.length % i2;
        int[] iArr = new int[length];
        int i3 = 0;
        while (i3 < length) {
            iArr[i3] = i(zArr, length2, i2);
            i3++;
            length2 += i2;
        }
        try {
            new c(aVar).a(iArr, length - iC);
            int i4 = 1 << i2;
            int i5 = i4 - 1;
            int i6 = 0;
            for (int i7 = 0; i7 < iC; i7++) {
                int i8 = iArr[i7];
                if (i8 == 0 || i8 == i5) {
                    throw g.a();
                }
                if (i8 == 1 || i8 == i4 - 2) {
                    i6++;
                }
            }
            boolean[] zArr2 = new boolean[(iC * i2) - i6];
            int i9 = 0;
            for (int i10 = 0; i10 < iC; i10++) {
                int i11 = iArr[i10];
                if (i11 == 1 || i11 == i4 - 2) {
                    Arrays.fill(zArr2, i9, (i9 + i2) - 1, i11 > 1);
                    i9 += i2 - 1;
                } else {
                    int i12 = i2 - 1;
                    while (i12 >= 0) {
                        int i13 = i9 + 1;
                        zArr2[i9] = ((1 << i12) & i11) != 0;
                        i12--;
                        i9 = i13;
                    }
                }
            }
            return zArr2;
        } catch (e e2) {
            throw g.b(e2);
        }
    }

    public L.e c(I.a aVar) throws g {
        this.f328a = aVar;
        boolean[] zArrB = b(d(aVar.a()));
        L.e eVar = new L.e(a(zArrB), f(zArrB), null, null);
        eVar.l(zArrB.length);
        return eVar;
    }

    public final boolean[] d(L.b bVar) {
        boolean zE = this.f328a.e();
        int iD = this.f328a.d();
        int i2 = (zE ? 11 : 14) + (iD << 2);
        int[] iArr = new int[i2];
        boolean[] zArr = new boolean[j(iD, zE)];
        int i3 = 2;
        if (zE) {
            for (int i4 = 0; i4 < i2; i4++) {
                iArr[i4] = i4;
            }
        } else {
            int i5 = i2 / 2;
            int i6 = ((i2 + 1) + (((i5 - 1) / 15) * 2)) / 2;
            for (int i7 = 0; i7 < i5; i7++) {
                iArr[(i5 - i7) - 1] = (i6 - r12) - 1;
                iArr[i5 + i7] = (i7 / 15) + i7 + i6 + 1;
            }
        }
        int i8 = 0;
        int i9 = 0;
        while (i8 < iD) {
            int i10 = ((iD - i8) << i3) + (zE ? 9 : 12);
            int i11 = i8 << 1;
            int i12 = (i2 - 1) - i11;
            int i13 = 0;
            while (i13 < i10) {
                int i14 = i13 << 1;
                int i15 = 0;
                while (i15 < i3) {
                    int i16 = i11 + i15;
                    int i17 = i11 + i13;
                    zArr[i9 + i14 + i15] = bVar.d(iArr[i16], iArr[i17]);
                    int i18 = iArr[i17];
                    int i19 = i12 - i15;
                    zArr[(i10 * 2) + i9 + i14 + i15] = bVar.d(i18, iArr[i19]);
                    int i20 = i12 - i13;
                    zArr[(i10 * 4) + i9 + i14 + i15] = bVar.d(iArr[i19], iArr[i20]);
                    zArr[(i10 * 6) + i9 + i14 + i15] = bVar.d(iArr[i20], iArr[i16]);
                    i15++;
                    iD = iD;
                    zE = zE;
                    i3 = 2;
                }
                i13++;
                i3 = 2;
            }
            i9 += i10 << 3;
            i8++;
            i3 = 2;
        }
        return zArr;
    }
}
