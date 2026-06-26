package o0;

import java.util.List;
import o0.C;

/* JADX INFO: loaded from: classes.dex */
public abstract class D {
    public static String a(String str, C.d dVar, List list, String str2) {
        C c2 = new C();
        if (str == null) {
            str = "";
        }
        c2.f6990a = str;
        if (str2 != null && !str2.isEmpty()) {
            c2.f6991b = str2;
        }
        if (dVar != null) {
            c2.f6993d = dVar;
        }
        if (list != null && !list.isEmpty()) {
            c2.f6994e.addAll(list);
        }
        return !c2.c() ? c2.f6990a : c2.f();
    }
}
