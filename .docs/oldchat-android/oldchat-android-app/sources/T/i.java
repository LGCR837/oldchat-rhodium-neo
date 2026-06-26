package T;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class i extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k[] f514a;

    public i(Map map) {
        Collection collection = map == null ? null : (Collection) map.get(H.e.POSSIBLE_FORMATS);
        boolean z2 = (map == null || map.get(H.e.ASSUME_CODE_39_CHECK_DIGIT) == null) ? false : true;
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(H.a.EAN_13) || collection.contains(H.a.UPC_A) || collection.contains(H.a.EAN_8) || collection.contains(H.a.UPC_E)) {
                arrayList.add(new j(map));
            }
            if (collection.contains(H.a.CODE_39)) {
                arrayList.add(new c(z2));
            }
            if (collection.contains(H.a.CODE_93)) {
                arrayList.add(new d());
            }
            if (collection.contains(H.a.CODE_128)) {
                arrayList.add(new b());
            }
            if (collection.contains(H.a.ITF)) {
                arrayList.add(new h());
            }
            if (collection.contains(H.a.CODABAR)) {
                arrayList.add(new a());
            }
            if (collection.contains(H.a.RSS_14)) {
                arrayList.add(new U.e());
            }
            if (collection.contains(H.a.RSS_EXPANDED)) {
                arrayList.add(new V.d());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new j(map));
            arrayList.add(new c());
            arrayList.add(new a());
            arrayList.add(new d());
            arrayList.add(new b());
            arrayList.add(new h());
            arrayList.add(new U.e());
            arrayList.add(new V.d());
        }
        this.f514a = (k[]) arrayList.toArray(new k[arrayList.size()]);
    }

    @Override // T.k
    public H.n b(int i2, L.a aVar, Map map) throws H.j {
        for (k kVar : this.f514a) {
            try {
                return kVar.b(i2, aVar, map);
            } catch (H.m unused) {
            }
        }
        throw H.j.a();
    }
}
