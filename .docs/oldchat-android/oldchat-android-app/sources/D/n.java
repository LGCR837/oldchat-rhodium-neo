package d;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public abstract class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f4901a;

    public interface a {
        Intent a(ComponentName componentName);
    }

    public static class b implements a {
        @Override // d.n.a
        public Intent a(ComponentName componentName) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setComponent(componentName);
            intent.addCategory("android.intent.category.LAUNCHER");
            return intent;
        }
    }

    public static class c extends b {
        @Override // d.n.b, d.n.a
        public Intent a(ComponentName componentName) {
            return p.a(componentName);
        }
    }

    public static class d extends c {
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 15) {
            f4901a = new d();
        } else if (i2 >= 11) {
            f4901a = new c();
        } else {
            f4901a = new b();
        }
    }

    public static Intent a(ComponentName componentName) {
        return f4901a.a(componentName);
    }
}
