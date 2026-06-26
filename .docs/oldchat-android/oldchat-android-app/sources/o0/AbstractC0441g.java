package o0;

import android.content.Context;
import android.os.Build;
import android.text.ClipboardManager;
import android.widget.Toast;

/* JADX INFO: renamed from: o0.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0441g {
    public static void a(Context context, String str) {
        if (context == null || str == null || str.isEmpty()) {
            return;
        }
        if (Build.VERSION.SDK_INT < 11) {
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
            if (clipboardManager != null) {
                clipboardManager.setText(str);
                Toast.makeText(context, "已复制到剪贴板", 0).show();
                return;
            }
            return;
        }
        try {
            Class<?> cls = Class.forName("android.content.ClipData");
            Class<?> cls2 = Class.forName("android.content.ClipboardManager");
            Object objInvoke = cls.getMethod("newPlainText", CharSequence.class, CharSequence.class).invoke(null, "message", str);
            Object systemService = context.getSystemService("clipboard");
            if (cls2.isInstance(systemService)) {
                cls2.getMethod("setPrimaryClip", cls).invoke(systemService, objInvoke);
                Toast.makeText(context, "已复制到剪贴板", 0).show();
            }
        } catch (Throwable unused) {
        }
    }
}
