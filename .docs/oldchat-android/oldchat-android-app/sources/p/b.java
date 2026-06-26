package P;

import P.e;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f447a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte[] f448b;

    public b(int i2, byte[] bArr) {
        this.f447a = i2;
        this.f448b = bArr;
    }

    public static b[] b(byte[] bArr, e eVar) {
        e.c cVarD = eVar.d();
        e.b[] bVarArrA = cVarD.a();
        int iA = 0;
        for (e.b bVar : bVarArrA) {
            iA += bVar.a();
        }
        b[] bVarArr = new b[iA];
        int i2 = 0;
        for (e.b bVar2 : bVarArrA) {
            int i3 = 0;
            while (i3 < bVar2.a()) {
                int iB = bVar2.b();
                bVarArr[i2] = new b(iB, new byte[cVarD.b() + iB]);
                i3++;
                i2++;
            }
        }
        int length = bVarArr[0].f448b.length - cVarD.b();
        int i4 = length - 1;
        int i5 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            int i7 = 0;
            while (i7 < i2) {
                bVarArr[i7].f448b[i6] = bArr[i5];
                i7++;
                i5++;
            }
        }
        boolean z2 = eVar.i() == 24;
        int i8 = z2 ? 8 : i2;
        int i9 = 0;
        while (i9 < i8) {
            bVarArr[i9].f448b[i4] = bArr[i5];
            i9++;
            i5++;
        }
        int length2 = bVarArr[0].f448b.length;
        while (length < length2) {
            int i10 = 0;
            while (i10 < i2) {
                int i11 = z2 ? (i10 + 8) % i2 : i10;
                bVarArr[i11].f448b[(!z2 || i11 <= 7) ? length : length - 1] = bArr[i5];
                i10++;
                i5++;
            }
            length++;
        }
        if (i5 == bArr.length) {
            return bVarArr;
        }
        throw new IllegalArgumentException();
    }

    public byte[] a() {
        return this.f448b;
    }

    public int c() {
        return this.f447a;
    }
}
