package c;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import k.AbstractC0267e0;

/* JADX INFO: renamed from: c.D, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0112D extends AbstractC0267e0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AbstractC0175y f1809c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public AbstractC0114F f1810d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public AbstractComponentCallbacksC0165t f1811e = null;

    public AbstractC0112D(AbstractC0175y abstractC0175y) {
        this.f1809c = abstractC0175y;
    }

    public static String u(int i2, long j2) {
        return "android:switcher:" + i2 + ":" + j2;
    }

    @Override // k.AbstractC0267e0
    public void a(ViewGroup viewGroup, int i2, Object obj) {
        if (this.f1810d == null) {
            this.f1810d = this.f1809c.a();
        }
        this.f1810d.d((AbstractComponentCallbacksC0165t) obj);
    }

    @Override // k.AbstractC0267e0
    public void c(ViewGroup viewGroup) {
        AbstractC0114F abstractC0114F = this.f1810d;
        if (abstractC0114F != null) {
            abstractC0114F.c();
            this.f1810d = null;
        }
    }

    @Override // k.AbstractC0267e0
    public Object h(ViewGroup viewGroup, int i2) {
        if (this.f1810d == null) {
            this.f1810d = this.f1809c.a();
        }
        long jT = t(i2);
        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165tC = this.f1809c.c(u(viewGroup.getId(), jT));
        if (abstractComponentCallbacksC0165tC != null) {
            this.f1810d.b(abstractComponentCallbacksC0165tC);
        } else {
            abstractComponentCallbacksC0165tC = s(i2);
            this.f1810d.a(viewGroup.getId(), abstractComponentCallbacksC0165tC, u(viewGroup.getId(), jT));
        }
        if (abstractComponentCallbacksC0165tC != this.f1811e) {
            abstractComponentCallbacksC0165tC.D0(false);
            abstractComponentCallbacksC0165tC.E0(false);
        }
        return abstractComponentCallbacksC0165tC;
    }

    @Override // k.AbstractC0267e0
    public boolean i(View view, Object obj) {
        return ((AbstractComponentCallbacksC0165t) obj).s() == view;
    }

    @Override // k.AbstractC0267e0
    public Parcelable l() {
        return null;
    }

    @Override // k.AbstractC0267e0
    public void n(ViewGroup viewGroup, int i2, Object obj) {
        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t = (AbstractComponentCallbacksC0165t) obj;
        AbstractComponentCallbacksC0165t abstractComponentCallbacksC0165t2 = this.f1811e;
        if (abstractComponentCallbacksC0165t != abstractComponentCallbacksC0165t2) {
            if (abstractComponentCallbacksC0165t2 != null) {
                abstractComponentCallbacksC0165t2.D0(false);
                this.f1811e.E0(false);
            }
            if (abstractComponentCallbacksC0165t != null) {
                abstractComponentCallbacksC0165t.D0(true);
                abstractComponentCallbacksC0165t.E0(true);
            }
            this.f1811e = abstractComponentCallbacksC0165t;
        }
    }

    @Override // k.AbstractC0267e0
    public void q(ViewGroup viewGroup) {
        if (viewGroup.getId() != -1) {
            return;
        }
        throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
    }

    public abstract AbstractComponentCallbacksC0165t s(int i2);

    public long t(int i2) {
        return i2;
    }

    @Override // k.AbstractC0267e0
    public void k(Parcelable parcelable, ClassLoader classLoader) {
    }
}
