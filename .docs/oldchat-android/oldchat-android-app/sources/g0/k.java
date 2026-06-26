package g0;

import o0.C;

/* JADX INFO: loaded from: classes.dex */
public abstract class k {
    public static String a(l lVar) {
        if (lVar == null) {
            return "";
        }
        String str = lVar.f5413f;
        String lowerCase = str == null ? "" : str.toLowerCase();
        C cB = C.b(lVar.f5412e);
        if ("image".equals(lowerCase)) {
            return "emoji".equals(cB.f6991b) ? "[表情]" : "[图片]";
        }
        if ("voice".equals(lowerCase)) {
            return "[语音]";
        }
        if ("video".equals(lowerCase)) {
            return "[视频]";
        }
        if ("resource".equals(lowerCase)) {
            return "music".equals(cB.f6991b) ? "[音乐]" : "[资源]";
        }
        if ("music".equals(lowerCase)) {
            return "[音乐]";
        }
        if ("red_packet".equals(lowerCase)) {
            return "[红包]";
        }
        String str2 = cB.f6990a;
        return str2 != null ? str2 : "";
    }

    public static String b(n nVar) {
        if (nVar == null) {
            return "";
        }
        String str = nVar.f5425e;
        String lowerCase = str == null ? "" : str.toLowerCase();
        C cB = C.b(nVar.f5424d);
        if ("image".equals(lowerCase)) {
            return "emoji".equals(cB.f6991b) ? "[表情]" : "[图片]";
        }
        if ("voice".equals(lowerCase)) {
            return "[语音]";
        }
        if ("video".equals(lowerCase)) {
            return "[视频]";
        }
        if ("resource".equals(lowerCase)) {
            return "music".equals(cB.f6991b) ? "[音乐]" : "[资源]";
        }
        if ("music".equals(lowerCase)) {
            return "[音乐]";
        }
        if ("red_packet".equals(lowerCase)) {
            return "[红包]";
        }
        String str2 = cB.f6990a;
        return str2 != null ? str2 : "";
    }
}
