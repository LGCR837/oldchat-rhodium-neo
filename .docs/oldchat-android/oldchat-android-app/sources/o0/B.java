package o0;

import org.spongycastle.i18n.TextBundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class B {
    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        return TextBundle.TEXT_ENTRY.equals(lowerCase) || "image".equals(lowerCase) || "voice".equals(lowerCase) || "video".equals(lowerCase) || "resource".equals(lowerCase) || "music".equals(lowerCase) || "red_packet".equals(lowerCase) || "recall".equals(lowerCase);
    }

    public static boolean b(String str) {
        String strTrim;
        int length;
        if (str == null || (length = (strTrim = str.trim()).length()) < 4 || length > 32) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = strTrim.charAt(i2);
            if (!Character.isLetterOrDigit(cCharAt) && cCharAt != '_' && cCharAt != '-') {
                return false;
            }
        }
        return true;
    }

    public static boolean c(g0.l lVar) {
        if (lVar == null || !g(lVar.f5410c, lVar.f5412e, lVar.f5413f)) {
            return false;
        }
        String str = lVar.f5410c;
        lVar.f5410c = lVar.f5413f;
        lVar.f5413f = lVar.f5412e;
        lVar.f5412e = str;
        return true;
    }

    public static boolean d(j0.k kVar) {
        if (kVar == null || !g(kVar.f5552c, kVar.f5553d, kVar.f5554e)) {
            return false;
        }
        String str = kVar.f5552c;
        kVar.f5552c = kVar.f5554e;
        kVar.f5554e = kVar.f5553d;
        kVar.f5553d = str;
        return true;
    }

    public static boolean e(g0.n nVar) {
        if (nVar == null || !g(nVar.f5423c, nVar.f5424d, nVar.f5425e)) {
            return false;
        }
        String str = nVar.f5423c;
        nVar.f5423c = nVar.f5425e;
        nVar.f5425e = nVar.f5424d;
        nVar.f5424d = str;
        return true;
    }

    public static boolean f(j0.i iVar) {
        if (iVar == null || !g(iVar.f5531c, iVar.f5532d, iVar.f5533e)) {
            return false;
        }
        String str = iVar.f5531c;
        iVar.f5531c = iVar.f5533e;
        iVar.f5533e = iVar.f5532d;
        iVar.f5532d = str;
        return true;
    }

    public static boolean g(String str, String str2, String str3) {
        if (str == null || str.isEmpty() || str2 == null || str2.isEmpty() || str3 == null || str3.isEmpty() || !a(str2) || a(str3) || !b(str3)) {
            return false;
        }
        return !b(str);
    }
}
