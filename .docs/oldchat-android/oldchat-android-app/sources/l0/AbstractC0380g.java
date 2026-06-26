package l0;

import android.content.Context;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: renamed from: l0.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0380g {
    public static void a(Context context, List list, String str) {
        Iterator it = list.iterator();
        long j2 = -1;
        long j3 = -1;
        while (it.hasNext()) {
            j0.k kVar = (j0.k) it.next();
            String str2 = kVar.f5552c;
            if (str2 != null) {
                if (o0.F.c(context, str2, str)) {
                    long j4 = kVar.f5558i;
                    if (j4 > j3) {
                        j3 = j4;
                    }
                } else {
                    long j5 = kVar.f5558i;
                    if (j5 > j2) {
                        j2 = j5;
                    }
                }
            }
        }
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            j0.k kVar2 = (j0.k) it2.next();
            String str3 = kVar2.f5552c;
            if (str3 == null || !o0.F.c(context, str3, str)) {
                kVar2.f5559j = 0;
            } else {
                int i2 = kVar2.f5559j;
                if (i2 != 3 && i2 != 2) {
                    long j6 = kVar2.f5558i;
                    if (j2 > j6) {
                        kVar2.f5559j = 3;
                    } else if (j3 > j6) {
                        kVar2.f5559j = 2;
                    } else {
                        kVar2.f5559j = 1;
                    }
                }
            }
        }
    }

    public static long b(long j2) {
        if (j2 <= 0) {
            return 0L;
        }
        return j2 < 100000000000L ? j2 * 1000 : j2;
    }

    public static int c(JSONObject jSONObject) {
        if (jSONObject.optLong("read_at", 0L) > 0) {
            return 3;
        }
        return jSONObject.optLong("delivered_at", 0L) > 0 ? 2 : 1;
    }

    public static String d(j0.k kVar) {
        if (kVar == null) {
            return "";
        }
        String str = kVar.f5554e;
        String lowerCase = str == null ? "" : str.toLowerCase();
        o0.C cB = o0.C.b(kVar.f5553d);
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

    public static String e(String str, String str2) {
        String lowerCase = str == null ? "" : str.toLowerCase();
        o0.C cB = o0.C.b(str2);
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
        String str3 = cB.f6990a;
        return str3 != null ? str3 : "";
    }

    public static String f(String str, String str2, String str3) {
        if (str3 != null && !str3.isEmpty()) {
            return str3;
        }
        String lowerCase = str == null ? "" : str.toLowerCase();
        return "image".equals(lowerCase) ? "emoji".equals(str2) ? "[表情]" : "[图片]" : "voice".equals(lowerCase) ? "[语音]" : "video".equals(lowerCase) ? "[视频]" : "resource".equals(lowerCase) ? "music".equals(str2) ? "[音乐]" : "[资源]" : "music".equals(lowerCase) ? "[音乐]" : "red_packet".equals(lowerCase) ? "[红包]" : "";
    }

    public static long g(long j2) {
        return (j2 <= 0 || j2 < 1000000000) ? System.currentTimeMillis() / 1000 : j2;
    }
}
