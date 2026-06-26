package l0;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import o0.C;
import org.spongycastle.i18n.TextBundle;

/* JADX INFO: renamed from: l0.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0406v {
    public static CharSequence a(Context context, C.a aVar) {
        return AbstractC0405u.d(context, aVar);
    }

    public static C.a b(Context context, List list) {
        C.a aVar = new C.a();
        aVar.f6996a = context == null ? "聊天记录" : context.getString(f0.j.f5198V);
        if (list == null) {
            return aVar;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            C.b bVar = (C.b) list.get(i2);
            if (bVar != null) {
                C.b bVar2 = new C.b();
                bVar2.f6998a = m(bVar.f6998a);
                bVar2.f6999b = m(bVar.f6999b);
                String strM = m(bVar.f7000c);
                bVar2.f7000c = strM;
                if (strM.length() == 0) {
                    bVar2.f7000c = bVar2.f6999b;
                }
                bVar2.f7001d = m(bVar.f7001d);
                bVar2.f7002e = j(bVar.f7002e);
                bVar2.f7003f = m(bVar.f7003f);
                bVar2.f7004g = n(bVar.f7004g);
                aVar.f6997b.add(bVar2);
            }
        }
        return aVar;
    }

    public static C.b c(Context context, j0.k kVar, String str, String str2, String str3, String str4) {
        if (kVar == null) {
            return null;
        }
        o0.C cB = o0.C.b(kVar.f5553d);
        String strJ = j(kVar.f5554e);
        boolean zC = o0.F.c(context, kVar.f5552c, str);
        C.b bVar = new C.b();
        if (zC) {
            String strM = m(o0.F.b(context));
            if (strM.length() == 0) {
                strM = m(str);
            }
            if (strM.length() == 0) {
                strM = m(kVar.f5552c);
            }
            bVar.f6999b = strM;
            bVar.f7000c = l(context, strM);
            bVar.f7001d = "";
        } else {
            String strM2 = m(str2);
            if (strM2.length() == 0) {
                strM2 = m(kVar.f5552c);
            }
            bVar.f6999b = strM2;
            bVar.f7000c = k(context, strM2, str3);
            bVar.f7001d = m(str4);
        }
        bVar.f7002e = strJ;
        bVar.f6998a = m(kVar.f5550a);
        bVar.f7003f = cB != null ? m(cB.f6991b) : "";
        bVar.f7004g = n(g(context, strJ, cB));
        return bVar;
    }

    public static String d(Context context, List list) {
        int size = list == null ? 0 : list.size();
        if (size <= 0) {
            return "";
        }
        if (context != null) {
            return context.getString(f0.j.f5201Y, Integer.valueOf(size));
        }
        return "[聊天记录] 共" + size + "条";
    }

    public static String e(Context context, List list) {
        C.a aVarB = b(context, list);
        List list2 = aVarB.f6997b;
        if (list2 == null || list2.isEmpty()) {
            return "";
        }
        o0.C c2 = new o0.C();
        c2.f6995f = aVarB;
        c2.f6990a = d(context, aVarB.f6997b);
        return c2.f();
    }

    public static C.b f(Context context, j0.i iVar, String str, String str2) {
        if (iVar == null) {
            return null;
        }
        o0.C cB = o0.C.b(iVar.f5532d);
        String strJ = j(iVar.f5533e);
        C.b bVar = new C.b();
        bVar.f6999b = m(iVar.f5531c);
        String strM = m(str);
        bVar.f7000c = strM;
        if (strM.length() == 0) {
            bVar.f7000c = bVar.f6999b;
        }
        bVar.f7001d = m(str2);
        bVar.f7002e = strJ;
        bVar.f6998a = m(iVar.f5529a);
        bVar.f7003f = cB == null ? "" : m(cB.f6991b);
        bVar.f7004g = n(g(context, strJ, cB));
        return bVar;
    }

    public static String g(Context context, String str, o0.C c2) {
        if (TextBundle.TEXT_ENTRY.equals(str)) {
            if (i(c2)) {
                return context != null ? context.getString(f0.j.f5218h0) : "[聊天记录]";
            }
            String strM = c2 == null ? "" : m(c2.f6990a);
            if (strM.length() > 0) {
                return strM;
            }
        }
        if ("voice".equals(str) && c2 != null) {
            String strM2 = m(c2.f6992c);
            if (strM2.length() > 0) {
                return strM2;
            }
        }
        String strF = AbstractC0380g.f(str, c2 == null ? "" : m(c2.f6991b), c2 != null ? m(c2.f6990a) : "");
        return strF.length() > 0 ? strF : context != null ? context.getString(f0.j.f5216g0) : "...";
    }

    public static List h(List list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                C.b bVar = (C.b) list.get(i2);
                if (bVar != null) {
                    C.b bVar2 = new C.b();
                    bVar2.f6998a = m(bVar.f6998a);
                    bVar2.f6999b = m(bVar.f6999b);
                    bVar2.f7000c = m(bVar.f7000c);
                    bVar2.f7001d = m(bVar.f7001d);
                    bVar2.f7002e = j(bVar.f7002e);
                    bVar2.f7003f = m(bVar.f7003f);
                    bVar2.f7004g = n(bVar.f7004g);
                    arrayList.add(bVar2);
                }
            }
        }
        return arrayList;
    }

    public static boolean i(o0.C c2) {
        C.a aVar;
        List list;
        return (c2 == null || (aVar = c2.f6995f) == null || (list = aVar.f6997b) == null || list.isEmpty()) ? false : true;
    }

    public static String j(String str) {
        String lowerCase = m(str).toLowerCase();
        return lowerCase.length() == 0 ? TextBundle.TEXT_ENTRY : lowerCase;
    }

    public static String k(Context context, String str, String str2) {
        String strM = m(str2);
        if (strM.length() > 0) {
            return strM;
        }
        String strM2 = m(j0.x.b(context, str));
        return strM2.length() > 0 ? strM2 : str;
    }

    public static String l(Context context, String str) {
        String strM = m(j0.x.b(context, str));
        return strM.length() > 0 ? strM : context != null ? context.getString(f0.j.f5220i0) : "我";
    }

    public static String m(String str) {
        return str == null ? "" : str.trim();
    }

    public static String n(String str) {
        String strTrim = m(str).replace('\r', ' ').replace('\n', ' ').trim();
        if (strTrim.length() <= 48) {
            return strTrim;
        }
        return strTrim.substring(0, 48) + "...";
    }
}
