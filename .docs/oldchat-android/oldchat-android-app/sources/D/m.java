package d;

import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public abstract class m {
    public static File[] a(Context context) {
        return context.getExternalCacheDirs();
    }

    public static File[] b(Context context, String str) {
        return context.getExternalFilesDirs(str);
    }
}
