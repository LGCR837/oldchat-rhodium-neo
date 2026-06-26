package c0;

import c0.j;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2080a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte[] f2081b;

    public b(int i2, byte[] bArr) {
        this.f2080a = i2;
        this.f2081b = bArr;
    }

    public static b[] b(byte[] bArr, j jVar, f fVar) {
        if (bArr.length != jVar.h()) {
            throw new IllegalArgumentException();
        }
        j.b bVarF = jVar.f(fVar);
        j.a[] aVarArrA = bVarF.a();
        int iA = 0;
        for (j.a aVar : aVarArrA) {
            iA += aVar.a();
        }
        b[] bVarArr = new b[iA];
        int i2 = 0;
        for (j.a aVar2 : aVarArrA) {
            int i3 = 0;
            while (i3 < aVar2.a()) {
                int iB = aVar2.b();
                bVarArr[i2] = new b(iB, new byte[bVarF.b() + iB]);
                i3++;
                i2++;
            }
        }
        int length = bVarArr[0].f2081b.length;
        int i4 = iA - 1;
        while (i4 >= 0 && bVarArr[i4].f2081b.length != length) {
            i4--;
        }
        int i5 = i4 + 1;
        int iB2 = length - bVarF.b();
        int i6 = 0;
        for (int i7 = 0; i7 < iB2; i7++) {
            int i8 = 0;
            while (i8 < i2) {
                bVarArr[i8].f2081b[i7] = bArr[i6];
                i8++;
                i6++;
            }
        }
        int i9 = i5;
        while (i9 < i2) {
            bVarArr[i9].f2081b[iB2] = bArr[i6];
            i9++;
            i6++;
        }
        int length2 = bVarArr[0].f2081b.length;
        while (iB2 < length2) {
            int i10 = 0;
            while (i10 < i2) {
                bVarArr[i10].f2081b[i10 < i5 ? iB2 : iB2 + 1] = bArr[i6];
                i10++;
                i6++;
            }
            iB2++;
        }
        return bVarArr;
    }

    public byte[] a() {
        return this.f2081b;
    }

    public int c() {
        return this.f2080a;
    }
}
