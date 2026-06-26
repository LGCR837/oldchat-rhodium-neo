package T;

import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f517a = new int[4];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final StringBuilder f518b = new StringBuilder();

    public static Map c(String str) {
        if (str.length() != 2) {
            return null;
        }
        EnumMap enumMap = new EnumMap(H.o.class);
        enumMap.put(H.o.ISSUE_NUMBER, Integer.valueOf(str));
        return enumMap;
    }

    public final int a(L.a aVar, int[] iArr, StringBuilder sb) throws H.j {
        int[] iArr2 = this.f517a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int iK = aVar.k();
        int iJ = iArr[1];
        int i2 = 0;
        for (int i3 = 0; i3 < 2 && iJ < iK; i3++) {
            int i4 = p.i(aVar, iArr2, iJ, p.f529h);
            sb.append((char) ((i4 % 10) + 48));
            for (int i5 : iArr2) {
                iJ += i5;
            }
            if (i4 >= 10) {
                i2 |= 1 << (1 - i3);
            }
            if (i3 != 1) {
                iJ = aVar.j(aVar.i(iJ));
            }
        }
        if (sb.length() != 2) {
            throw H.j.a();
        }
        if (Integer.parseInt(sb.toString()) % 4 == i2) {
            return iJ;
        }
        throw H.j.a();
    }

    public H.n b(int i2, L.a aVar, int[] iArr) throws H.j {
        StringBuilder sb = this.f518b;
        sb.setLength(0);
        int iA = a(aVar, iArr, sb);
        String string = sb.toString();
        Map mapC = c(string);
        float f2 = i2;
        H.n nVar = new H.n(string, null, new H.p[]{new H.p((iArr[0] + iArr[1]) / 2.0f, f2), new H.p(iA, f2)}, H.a.UPC_EAN_EXTENSION);
        if (mapC != null) {
            nVar.g(mapC);
        }
        return nVar;
    }
}
