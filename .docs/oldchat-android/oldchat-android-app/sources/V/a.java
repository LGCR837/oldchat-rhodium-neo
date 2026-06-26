package V;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static L.a a(List list) {
        int size = list.size() << 1;
        int i2 = size - 1;
        if (((b) list.get(list.size() - 1)).d() == null) {
            i2 = size - 2;
        }
        L.a aVar = new L.a(i2 * 12);
        int i3 = 0;
        int iB = ((b) list.get(0)).d().b();
        for (int i4 = 11; i4 >= 0; i4--) {
            if (((1 << i4) & iB) != 0) {
                aVar.p(i3);
            }
            i3++;
        }
        for (int i5 = 1; i5 < list.size(); i5++) {
            b bVar = (b) list.get(i5);
            int iB2 = bVar.c().b();
            for (int i6 = 11; i6 >= 0; i6--) {
                if (((1 << i6) & iB2) != 0) {
                    aVar.p(i3);
                }
                i3++;
            }
            if (bVar.d() != null) {
                int iB3 = bVar.d().b();
                for (int i7 = 11; i7 >= 0; i7--) {
                    if (((1 << i7) & iB3) != 0) {
                        aVar.p(i3);
                    }
                    i3++;
                }
            }
        }
        return aVar;
    }
}
