package android.support.v7.widget;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import k.AbstractC0254a;
import l.C0347e;

/* JADX INFO: loaded from: classes.dex */
public class j extends AbstractC0254a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RecyclerView f1710d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AbstractC0254a f1711e = new a();

    public class a extends AbstractC0254a {
        public a() {
        }

        @Override // k.AbstractC0254a
        public void e(View view, C0347e c0347e) {
            super.e(view, c0347e);
            if (j.this.l() || j.this.f1710d.getLayoutManager() == null) {
                return;
            }
            j.this.f1710d.getLayoutManager().I0(view, c0347e);
        }

        @Override // k.AbstractC0254a
        public boolean h(View view, int i2, Bundle bundle) {
            if (super.h(view, i2, bundle)) {
                return true;
            }
            if (j.this.l() || j.this.f1710d.getLayoutManager() == null) {
                return false;
            }
            return j.this.f1710d.getLayoutManager().b1(view, i2, bundle);
        }
    }

    public j(RecyclerView recyclerView) {
        this.f1710d = recyclerView;
    }

    @Override // k.AbstractC0254a
    public void d(View view, AccessibilityEvent accessibilityEvent) {
        super.d(view, accessibilityEvent);
        accessibilityEvent.setClassName(RecyclerView.class.getName());
        if (!(view instanceof RecyclerView) || l()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().E0(accessibilityEvent);
        }
    }

    @Override // k.AbstractC0254a
    public void e(View view, C0347e c0347e) {
        super.e(view, c0347e);
        c0347e.v(RecyclerView.class.getName());
        if (l() || this.f1710d.getLayoutManager() == null) {
            return;
        }
        this.f1710d.getLayoutManager().G0(c0347e);
    }

    @Override // k.AbstractC0254a
    public boolean h(View view, int i2, Bundle bundle) {
        if (super.h(view, i2, bundle)) {
            return true;
        }
        if (l() || this.f1710d.getLayoutManager() == null) {
            return false;
        }
        return this.f1710d.getLayoutManager().Y0(i2, bundle);
    }

    public AbstractC0254a k() {
        return this.f1711e;
    }

    public boolean l() {
        return this.f1710d.e0();
    }
}
