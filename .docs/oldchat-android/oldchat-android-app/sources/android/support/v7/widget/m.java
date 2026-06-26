package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class m extends RecyclerView.k {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1713g = true;

    public abstract boolean A(RecyclerView.B b2);

    public final void B(RecyclerView.B b2) {
        J(b2);
        h(b2);
    }

    public final void C(RecyclerView.B b2) {
        K(b2);
    }

    public final void D(RecyclerView.B b2, boolean z2) {
        L(b2, z2);
        h(b2);
    }

    public final void E(RecyclerView.B b2, boolean z2) {
        M(b2, z2);
    }

    public final void F(RecyclerView.B b2) {
        N(b2);
        h(b2);
    }

    public final void G(RecyclerView.B b2) {
        O(b2);
    }

    public final void H(RecyclerView.B b2) {
        P(b2);
        h(b2);
    }

    public final void I(RecyclerView.B b2) {
        Q(b2);
    }

    public void J(RecyclerView.B b2) {
    }

    public void K(RecyclerView.B b2) {
    }

    public void L(RecyclerView.B b2, boolean z2) {
    }

    public void M(RecyclerView.B b2, boolean z2) {
    }

    public void N(RecyclerView.B b2) {
    }

    public void O(RecyclerView.B b2) {
    }

    public void P(RecyclerView.B b2) {
    }

    public void Q(RecyclerView.B b2) {
    }

    @Override // android.support.v7.widget.RecyclerView.k
    public boolean a(RecyclerView.B b2, RecyclerView.k.c cVar, RecyclerView.k.c cVar2) {
        int i2;
        int i3;
        return (cVar == null || ((i2 = cVar.f1383a) == (i3 = cVar2.f1383a) && cVar.f1384b == cVar2.f1384b)) ? x(b2) : z(b2, i2, cVar.f1384b, i3, cVar2.f1384b);
    }

    @Override // android.support.v7.widget.RecyclerView.k
    public boolean b(RecyclerView.B b2, RecyclerView.B b3, RecyclerView.k.c cVar, RecyclerView.k.c cVar2) {
        int i2;
        int i3;
        int i4 = cVar.f1383a;
        int i5 = cVar.f1384b;
        if (b3.Q()) {
            int i6 = cVar.f1383a;
            i3 = cVar.f1384b;
            i2 = i6;
        } else {
            i2 = cVar2.f1383a;
            i3 = cVar2.f1384b;
        }
        return y(b2, b3, i4, i5, i2, i3);
    }

    @Override // android.support.v7.widget.RecyclerView.k
    public boolean c(RecyclerView.B b2, RecyclerView.k.c cVar, RecyclerView.k.c cVar2) {
        int i2 = cVar.f1383a;
        int i3 = cVar.f1384b;
        View view = b2.f1354a;
        int left = cVar2 == null ? view.getLeft() : cVar2.f1383a;
        int top = cVar2 == null ? view.getTop() : cVar2.f1384b;
        if (b2.C() || (i2 == left && i3 == top)) {
            return A(b2);
        }
        view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
        return z(b2, i2, i3, left, top);
    }

    @Override // android.support.v7.widget.RecyclerView.k
    public boolean d(RecyclerView.B b2, RecyclerView.k.c cVar, RecyclerView.k.c cVar2) {
        int i2 = cVar.f1383a;
        int i3 = cVar2.f1383a;
        if (i2 != i3 || cVar.f1384b != cVar2.f1384b) {
            return z(b2, i2, cVar.f1384b, i3, cVar2.f1384b);
        }
        F(b2);
        return false;
    }

    @Override // android.support.v7.widget.RecyclerView.k
    public boolean f(RecyclerView.B b2) {
        return !this.f1713g || b2.A();
    }

    public abstract boolean x(RecyclerView.B b2);

    public abstract boolean y(RecyclerView.B b2, RecyclerView.B b3, int i2, int i3, int i4, int i5);

    public abstract boolean z(RecyclerView.B b2, int i2, int i3, int i4, int i5);
}
