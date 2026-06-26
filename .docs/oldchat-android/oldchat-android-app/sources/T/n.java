package T;

import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f519c = {24, 20, 18, 17, 12, 6, 3, 10, 9, 5};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f520a = new int[4];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final StringBuilder f521b = new StringBuilder();

    public static int c(int i2) throws H.j {
        for (int i3 = 0; i3 < 10; i3++) {
            if (i2 == f519c[i3]) {
                return i3;
            }
        }
        throw H.j.a();
    }

    public static int d(CharSequence charSequence) {
        int length = charSequence.length();
        int iCharAt = 0;
        for (int i2 = length - 2; i2 >= 0; i2 -= 2) {
            iCharAt += charSequence.charAt(i2) - '0';
        }
        int iCharAt2 = iCharAt * 3;
        for (int i3 = length - 1; i3 >= 0; i3 -= 2) {
            iCharAt2 += charSequence.charAt(i3) - '0';
        }
        return (iCharAt2 * 3) % 10;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String e(java.lang.String r5) {
        /*
            r0 = 1
            r1 = 0
            char r2 = r5.charAt(r1)
            r3 = 48
            if (r2 == r3) goto L4d
            r3 = 53
            if (r2 == r3) goto L4a
            r3 = 57
            java.lang.String r4 = ""
            if (r2 == r3) goto L15
            goto L4f
        L15:
            r2 = -1
            int r3 = r5.hashCode()
            switch(r3) {
                case 54118329: goto L35;
                case 54395376: goto L2a;
                case 54395377: goto L1f;
                default: goto L1d;
            }
        L1d:
            r1 = -1
            goto L3e
        L1f:
            java.lang.String r1 = "99991"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L28
            goto L1d
        L28:
            r1 = 2
            goto L3e
        L2a:
            java.lang.String r1 = "99990"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L33
            goto L1d
        L33:
            r1 = 1
            goto L3e
        L35:
            java.lang.String r3 = "90000"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L3e
            goto L1d
        L3e:
            switch(r1) {
                case 0: goto L48;
                case 1: goto L45;
                case 2: goto L42;
                default: goto L41;
            }
        L41:
            goto L4f
        L42:
            java.lang.String r5 = "0.00"
            return r5
        L45:
            java.lang.String r5 = "Used"
            return r5
        L48:
            r5 = 0
            return r5
        L4a:
            java.lang.String r4 = "$"
            goto L4f
        L4d:
            java.lang.String r4 = "£"
        L4f:
            java.lang.String r5 = r5.substring(r0)
            int r5 = java.lang.Integer.parseInt(r5)
            int r0 = r5 / 100
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r5 = r5 % 100
            r1 = 10
            if (r5 >= r1) goto L6e
            java.lang.String r1 = "0"
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String r5 = r1.concat(r5)
            goto L72
        L6e:
            java.lang.String r5 = java.lang.String.valueOf(r5)
        L72:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r4)
            r1.append(r0)
            r0 = 46
            r1.append(r0)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: T.n.e(java.lang.String):java.lang.String");
    }

    public static Map f(String str) {
        String strE;
        if (str.length() != 5 || (strE = e(str)) == null) {
            return null;
        }
        EnumMap enumMap = new EnumMap(H.o.class);
        enumMap.put(H.o.SUGGESTED_PRICE, strE);
        return enumMap;
    }

    public final int a(L.a aVar, int[] iArr, StringBuilder sb) throws H.j {
        int[] iArr2 = this.f520a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int iK = aVar.k();
        int iJ = iArr[1];
        int i2 = 0;
        for (int i3 = 0; i3 < 5 && iJ < iK; i3++) {
            int i4 = p.i(aVar, iArr2, iJ, p.f529h);
            sb.append((char) ((i4 % 10) + 48));
            for (int i5 : iArr2) {
                iJ += i5;
            }
            if (i4 >= 10) {
                i2 |= 1 << (4 - i3);
            }
            if (i3 != 4) {
                iJ = aVar.j(aVar.i(iJ));
            }
        }
        if (sb.length() != 5) {
            throw H.j.a();
        }
        if (d(sb.toString()) == c(i2)) {
            return iJ;
        }
        throw H.j.a();
    }

    public H.n b(int i2, L.a aVar, int[] iArr) throws H.j {
        StringBuilder sb = this.f521b;
        sb.setLength(0);
        int iA = a(aVar, iArr, sb);
        String string = sb.toString();
        Map mapF = f(string);
        float f2 = i2;
        H.n nVar = new H.n(string, null, new H.p[]{new H.p((iArr[0] + iArr[1]) / 2.0f, f2), new H.p(iA, f2)}, H.a.UPC_EAN_EXTENSION);
        if (mapF != null) {
            nVar.g(mapF);
        }
        return nVar;
    }
}
