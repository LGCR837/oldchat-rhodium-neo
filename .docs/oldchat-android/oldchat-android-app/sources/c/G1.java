package c;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import d.AbstractC0235a;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class G1 implements Iterable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b f1824c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f1825a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f1826b;

    public interface a {
        Intent i();
    }

    public interface b {
    }

    public static class c implements b {
    }

    public static class d implements b {
    }

    static {
        if (Build.VERSION.SDK_INT >= 11) {
            f1824c = new d();
        } else {
            f1824c = new c();
        }
    }

    public G1(Context context) {
        this.f1826b = context;
    }

    public static G1 d(Context context) {
        return new G1(context);
    }

    public G1 a(Intent intent) {
        this.f1825a.add(intent);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public G1 b(Activity activity) {
        Intent intentI = activity instanceof a ? ((a) activity).i() : null;
        if (intentI == null) {
            intentI = AbstractC0137f0.a(activity);
        }
        if (intentI != null) {
            ComponentName component = intentI.getComponent();
            if (component == null) {
                component = intentI.resolveActivity(this.f1826b.getPackageManager());
            }
            c(component);
            a(intentI);
        }
        return this;
    }

    public G1 c(ComponentName componentName) {
        int size = this.f1825a.size();
        try {
            Intent intentB = AbstractC0137f0.b(this.f1826b, componentName);
            while (intentB != null) {
                this.f1825a.add(size, intentB);
                intentB = AbstractC0137f0.b(this.f1826b, intentB.getComponent());
            }
            return this;
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e2);
        }
    }

    public void e() {
        f(null);
    }

    public void f(Bundle bundle) {
        if (this.f1825a.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        ArrayList arrayList = this.f1825a;
        Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        if (AbstractC0235a.g(this.f1826b, intentArr, bundle)) {
            return;
        }
        Intent intent = new Intent(intentArr[intentArr.length - 1]);
        intent.addFlags(268435456);
        this.f1826b.startActivity(intent);
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return this.f1825a.iterator();
    }
}
