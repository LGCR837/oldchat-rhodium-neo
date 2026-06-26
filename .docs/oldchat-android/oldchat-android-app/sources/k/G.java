package k;

import android.view.LayoutInflater;
import k.F;

/* JADX INFO: loaded from: classes.dex */
public abstract class G {
    public static void a(LayoutInflater layoutInflater, H h2) {
        layoutInflater.setFactory2(h2 != null ? new F.a(h2) : null);
    }
}
