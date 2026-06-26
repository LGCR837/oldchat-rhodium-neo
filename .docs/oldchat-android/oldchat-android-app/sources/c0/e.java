package c0;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final N.c f2093a = new N.c(N.a.f426l);

    public final void a(byte[] bArr, int i2) throws H.d {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = bArr[i3] & 255;
        }
        try {
            this.f2093a.a(iArr, bArr.length - i2);
            for (int i4 = 0; i4 < i2; i4++) {
                bArr[i4] = (byte) iArr[i4];
            }
        } catch (N.e unused) {
            throw H.d.a();
        }
    }

    public L.e b(L.b bVar, Map map) {
        H.d e2;
        a aVar = new a(bVar);
        H.g gVar = null;
        try {
            return c(aVar, map);
        } catch (H.d e3) {
            e2 = e3;
            try {
                aVar.f();
                aVar.g(true);
                aVar.e();
                aVar.d();
                aVar.b();
                L.e eVarC = c(aVar, map);
                eVarC.m(new i(true));
                return eVarC;
            } catch (H.d | H.g unused) {
                if (gVar != null) {
                    throw gVar;
                }
                throw e2;
            }
        } catch (H.g e4) {
            e2 = null;
            gVar = e4;
            aVar.f();
            aVar.g(true);
            aVar.e();
            aVar.d();
            aVar.b();
            L.e eVarC2 = c(aVar, map);
            eVarC2.m(new i(true));
            return eVarC2;
        }
    }

    public final L.e c(a aVar, Map map) throws H.g, H.d {
        j jVarE = aVar.e();
        f fVarD = aVar.d().d();
        b[] bVarArrB = b.b(aVar.c(), jVarE, fVarD);
        int iC = 0;
        for (b bVar : bVarArrB) {
            iC += bVar.c();
        }
        byte[] bArr = new byte[iC];
        int i2 = 0;
        for (b bVar2 : bVarArrB) {
            byte[] bArrA = bVar2.a();
            int iC2 = bVar2.c();
            a(bArrA, iC2);
            int i3 = 0;
            while (i3 < iC2) {
                bArr[i2] = bArrA[i3];
                i3++;
                i2++;
            }
        }
        return d.a(bArr, jVarE, fVarD, map);
    }
}
