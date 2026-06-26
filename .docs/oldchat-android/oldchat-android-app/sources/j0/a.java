package j0;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static void a(Context context) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences("chat_cache", 0).edit().clear().apply();
    }
}
