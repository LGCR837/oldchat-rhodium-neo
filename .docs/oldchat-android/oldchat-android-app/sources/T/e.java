package T;

/* JADX INFO: loaded from: classes.dex */
public final class e extends p {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int[] f504j = {0, 11, 13, 14, 19, 25, 28, 21, 22, 26};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int[] f505i = new int[4];

    public static void r(StringBuilder sb, int i2) throws H.j {
        for (int i3 = 0; i3 < 10; i3++) {
            if (i2 == f504j[i3]) {
                sb.insert(0, (char) (i3 + 48));
                return;
            }
        }
        throw H.j.a();
    }

    @Override // T.p
    public int k(L.a aVar, int[] iArr, StringBuilder sb) throws H.j {
        int[] iArr2 = this.f505i;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int iK = aVar.k();
        int i2 = iArr[1];
        int i3 = 0;
        for (int i4 = 0; i4 < 6 && i2 < iK; i4++) {
            int i5 = p.i(aVar, iArr2, i2, p.f529h);
            sb.append((char) ((i5 % 10) + 48));
            for (int i6 : iArr2) {
                i2 += i6;
            }
            if (i5 >= 10) {
                i3 |= 1 << (5 - i4);
            }
        }
        r(sb, i3);
        int i7 = p.m(aVar, i2, true, p.f526e)[1];
        for (int i8 = 0; i8 < 6 && i7 < iK; i8++) {
            sb.append((char) (p.i(aVar, iArr2, i7, p.f528g) + 48));
            for (int i9 : iArr2) {
                i7 += i9;
            }
        }
        return i7;
    }

    @Override // T.p
    public H.a p() {
        return H.a.EAN_13;
    }
}
