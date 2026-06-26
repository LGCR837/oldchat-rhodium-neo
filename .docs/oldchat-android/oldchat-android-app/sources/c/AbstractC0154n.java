package c;

import android.app.AppOpsManager;
import android.content.Context;

/* JADX INFO: renamed from: c.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0154n {
    public static int a(Context context, String str, String str2) {
        return AbstractC0150l.a(context.getSystemService(AbstractC0146j.a())).noteProxyOp(str, str2);
    }

    public static String b(String str) {
        return AppOpsManager.permissionToOp(str);
    }
}
