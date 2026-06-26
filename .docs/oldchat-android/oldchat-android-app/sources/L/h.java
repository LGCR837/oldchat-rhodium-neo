package L;

/* JADX INFO: loaded from: classes.dex */
public abstract class h extends H.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final byte[] f399d = new byte[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f400b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int[] f401c;

    public h(H.h hVar) {
        super(hVar);
        this.f400b = f399d;
        this.f401c = new int[32];
    }

    public static int g(int[] iArr) throws H.j {
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            int i6 = iArr[i5];
            if (i6 > i2) {
                i4 = i5;
                i2 = i6;
            }
            if (i6 > i3) {
                i3 = i6;
            }
        }
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < length; i9++) {
            int i10 = i9 - i4;
            int i11 = iArr[i9] * i10 * i10;
            if (i11 > i8) {
                i7 = i9;
                i8 = i11;
            }
        }
        if (i4 <= i7) {
            int i12 = i4;
            i4 = i7;
            i7 = i12;
        }
        if (i4 - i7 <= length / 16) {
            throw H.j.a();
        }
        int i13 = i4 - 1;
        int i14 = i13;
        int i15 = -1;
        while (i13 > i7) {
            int i16 = i13 - i7;
            int i17 = i16 * i16 * (i4 - i13) * (i3 - iArr[i13]);
            if (i17 > i15) {
                i14 = i13;
                i15 = i17;
            }
            i13--;
        }
        return i14 << 3;
    }

    @Override // H.b
    public b b() throws H.j {
        H.h hVarE = e();
        int iD = hVarE.d();
        int iA = hVarE.a();
        b bVar = new b(iD, iA);
        h(iD);
        int[] iArr = this.f401c;
        for (int i2 = 1; i2 < 5; i2++) {
            byte[] bArrC = hVarE.c((iA * i2) / 5, this.f400b);
            int i3 = (iD << 2) / 5;
            for (int i4 = iD / 5; i4 < i3; i4++) {
                int i5 = (bArrC[i4] & 255) >> 3;
                iArr[i5] = iArr[i5] + 1;
            }
        }
        int iG = g(iArr);
        byte[] bArrB = hVarE.b();
        for (int i6 = 0; i6 < iA; i6++) {
            int i7 = i6 * iD;
            for (int i8 = 0; i8 < iD; i8++) {
                if ((bArrB[i7 + i8] & 255) < iG) {
                    bVar.l(i8, i6);
                }
            }
        }
        return bVar;
    }

    @Override // H.b
    public a c(int i2, a aVar) throws H.j {
        H.h hVarE = e();
        int iD = hVarE.d();
        if (aVar == null || aVar.k() < iD) {
            aVar = new a(iD);
        } else {
            aVar.d();
        }
        h(iD);
        byte[] bArrC = hVarE.c(i2, this.f400b);
        int[] iArr = this.f401c;
        for (int i3 = 0; i3 < iD; i3++) {
            int i4 = (bArrC[i3] & 255) >> 3;
            iArr[i4] = iArr[i4] + 1;
        }
        int iG = g(iArr);
        if (iD < 3) {
            for (int i5 = 0; i5 < iD; i5++) {
                if ((bArrC[i5] & 255) < iG) {
                    aVar.p(i5);
                }
            }
        } else {
            int i6 = 1;
            int i7 = bArrC[0] & 255;
            int i8 = bArrC[1] & 255;
            while (i6 < iD - 1) {
                int i9 = i6 + 1;
                int i10 = bArrC[i9] & 255;
                if ((((i8 << 2) - i7) - i10) / 2 < iG) {
                    aVar.p(i6);
                }
                i7 = i8;
                i6 = i9;
                i8 = i10;
            }
        }
        return aVar;
    }

    public final void h(int i2) {
        if (this.f400b.length < i2) {
            this.f400b = new byte[i2];
        }
        for (int i3 = 0; i3 < 32; i3++) {
            this.f401c[i3] = 0;
        }
    }
}
