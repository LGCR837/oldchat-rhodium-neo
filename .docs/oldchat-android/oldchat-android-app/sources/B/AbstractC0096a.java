package b;

import android.os.Build;
import android.view.View;

/* JADX INFO: renamed from: b.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0096a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final InterfaceC0098c f1742a;

    static {
        if (Build.VERSION.SDK_INT >= 12) {
            f1742a = new C0105j();
        } else {
            f1742a = new C0100e();
        }
    }

    public static void a(View view) {
        f1742a.b(view);
    }

    public static r b() {
        return f1742a.a();
    }
}
