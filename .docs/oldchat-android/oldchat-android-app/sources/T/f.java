package T;

/* JADX INFO: loaded from: classes.dex */
public final class f extends p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int[] f506i = new int[4];

    @Override // T.p
    public int k(L.a aVar, int[] iArr, StringBuilder sb) {
        int[] iArr2 = this.f506i;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int iK = aVar.k();
        int i2 = iArr[1];
        for (int i3 = 0; i3 < 4 && i2 < iK; i3++) {
            sb.append((char) (p.i(aVar, iArr2, i2, p.f528g) + 48));
            for (int i4 : iArr2) {
                i2 += i4;
            }
        }
        int i5 = p.m(aVar, i2, true, p.f526e)[1];
        for (int i6 = 0; i6 < 4 && i5 < iK; i6++) {
            sb.append((char) (p.i(aVar, iArr2, i5, p.f528g) + 48));
            for (int i7 : iArr2) {
                i5 += i7;
            }
        }
        return i5;
    }

    @Override // T.p
    public H.a p() {
        return H.a.EAN_8;
    }
}
