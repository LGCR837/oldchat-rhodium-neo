package k;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: renamed from: k.e0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0267e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final DataSetObservable f5694a = new DataSetObservable();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public DataSetObserver f5695b;

    public abstract void a(ViewGroup viewGroup, int i2, Object obj);

    public void c(ViewGroup viewGroup) {
        b(viewGroup);
    }

    public abstract int d();

    public int e(Object obj) {
        return -1;
    }

    public CharSequence f(int i2) {
        return null;
    }

    public float g(int i2) {
        return 1.0f;
    }

    public abstract Object h(ViewGroup viewGroup, int i2);

    public abstract boolean i(View view, Object obj);

    public void j(DataSetObserver dataSetObserver) {
        this.f5694a.registerObserver(dataSetObserver);
    }

    public Parcelable l() {
        return null;
    }

    public void n(ViewGroup viewGroup, int i2, Object obj) {
        m(viewGroup, i2, obj);
    }

    public void o(DataSetObserver dataSetObserver) {
        synchronized (this) {
            this.f5695b = dataSetObserver;
        }
    }

    public void q(ViewGroup viewGroup) {
        p(viewGroup);
    }

    public void r(DataSetObserver dataSetObserver) {
        this.f5694a.unregisterObserver(dataSetObserver);
    }

    public void b(View view) {
    }

    public void p(View view) {
    }

    public void k(Parcelable parcelable, ClassLoader classLoader) {
    }

    public void m(View view, int i2, Object obj) {
    }
}
