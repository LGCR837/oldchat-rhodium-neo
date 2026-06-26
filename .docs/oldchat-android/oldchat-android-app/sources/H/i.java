package H;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class i implements l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Map f289a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public l[] f290b;

    @Override // H.l
    public n a(c cVar, Map map) {
        d(map);
        return c(cVar);
    }

    public n b(c cVar) {
        d(null);
        return c(cVar);
    }

    public final n c(c cVar) throws j {
        l[] lVarArr = this.f290b;
        if (lVarArr != null) {
            for (l lVar : lVarArr) {
                try {
                    return lVar.a(cVar, this.f289a);
                } catch (m unused) {
                }
            }
        }
        throw j.a();
    }

    public void d(Map map) {
        this.f289a = map;
        boolean z2 = map != null && map.containsKey(e.TRY_HARDER);
        Collection collection = map == null ? null : (Collection) map.get(e.POSSIBLE_FORMATS);
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            boolean z3 = collection.contains(a.UPC_A) || collection.contains(a.UPC_E) || collection.contains(a.EAN_13) || collection.contains(a.EAN_8) || collection.contains(a.CODABAR) || collection.contains(a.CODE_39) || collection.contains(a.CODE_93) || collection.contains(a.CODE_128) || collection.contains(a.ITF) || collection.contains(a.RSS_14) || collection.contains(a.RSS_EXPANDED);
            if (z3 && !z2) {
                arrayList.add(new T.i(map));
            }
            if (collection.contains(a.QR_CODE)) {
                arrayList.add(new b0.a());
            }
            if (collection.contains(a.DATA_MATRIX)) {
                arrayList.add(new O.a());
            }
            if (collection.contains(a.AZTEC)) {
                arrayList.add(new I.b());
            }
            if (collection.contains(a.PDF_417)) {
                arrayList.add(new X.b());
            }
            if (collection.contains(a.MAXICODE)) {
                arrayList.add(new R.a());
            }
            if (z3 && z2) {
                arrayList.add(new T.i(map));
            }
        }
        if (arrayList.isEmpty()) {
            if (!z2) {
                arrayList.add(new T.i(map));
            }
            arrayList.add(new b0.a());
            arrayList.add(new O.a());
            arrayList.add(new I.b());
            arrayList.add(new X.b());
            arrayList.add(new R.a());
            if (z2) {
                arrayList.add(new T.i(map));
            }
        }
        this.f290b = (l[]) arrayList.toArray(new l[arrayList.size()]);
    }
}
