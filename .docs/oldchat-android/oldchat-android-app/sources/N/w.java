package n;

import android.os.Build;
import android.widget.ListView;

/* JADX INFO: loaded from: classes.dex */
public abstract class w {
    public static void a(ListView listView, int i2) {
        if (Build.VERSION.SDK_INT >= 19) {
            z.a(listView, i2);
        } else {
            x.a(listView, i2);
        }
    }
}
