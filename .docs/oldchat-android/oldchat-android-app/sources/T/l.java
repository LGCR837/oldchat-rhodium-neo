package T;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class l extends p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final p f516i = new e();

    public static H.n r(H.n nVar) throws H.g {
        String strF = nVar.f();
        if (strF.charAt(0) != '0') {
            throw H.g.a();
        }
        H.n nVar2 = new H.n(strF.substring(1), null, nVar.e(), H.a.UPC_A);
        if (nVar.d() != null) {
            nVar2.g(nVar.d());
        }
        return nVar2;
    }

    @Override // T.k, H.l
    public H.n a(H.c cVar, Map map) {
        return r(this.f516i.a(cVar, map));
    }

    @Override // T.p, T.k
    public H.n b(int i2, L.a aVar, Map map) {
        return r(this.f516i.b(i2, aVar, map));
    }

    @Override // T.p
    public int k(L.a aVar, int[] iArr, StringBuilder sb) {
        return this.f516i.k(aVar, iArr, sb);
    }

    @Override // T.p
    public H.n l(int i2, L.a aVar, int[] iArr, Map map) {
        return r(this.f516i.l(i2, aVar, iArr, map));
    }

    @Override // T.p
    public H.a p() {
        return H.a.UPC_A;
    }
}
