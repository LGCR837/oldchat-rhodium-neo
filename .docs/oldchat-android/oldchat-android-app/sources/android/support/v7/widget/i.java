package android.support.v7.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RecyclerView.n f1707a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1708b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f1709c;

    public static class a extends i {
        public a(RecyclerView.n nVar) {
            super(nVar, null);
        }

        @Override // android.support.v7.widget.i
        public int d(View view) {
            return this.f1707a.Q(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.o) view.getLayoutParams())).rightMargin;
        }

        @Override // android.support.v7.widget.i
        public int e(View view) {
            RecyclerView.o oVar = (RecyclerView.o) view.getLayoutParams();
            return this.f1707a.P(view) + ((ViewGroup.MarginLayoutParams) oVar).leftMargin + ((ViewGroup.MarginLayoutParams) oVar).rightMargin;
        }

        @Override // android.support.v7.widget.i
        public int f(View view) {
            RecyclerView.o oVar = (RecyclerView.o) view.getLayoutParams();
            return this.f1707a.O(view) + ((ViewGroup.MarginLayoutParams) oVar).topMargin + ((ViewGroup.MarginLayoutParams) oVar).bottomMargin;
        }

        @Override // android.support.v7.widget.i
        public int g(View view) {
            return this.f1707a.N(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.o) view.getLayoutParams())).leftMargin;
        }

        @Override // android.support.v7.widget.i
        public int h() {
            return this.f1707a.l0();
        }

        @Override // android.support.v7.widget.i
        public int i() {
            return this.f1707a.l0() - this.f1707a.c0();
        }

        @Override // android.support.v7.widget.i
        public int j() {
            return this.f1707a.c0();
        }

        @Override // android.support.v7.widget.i
        public int k() {
            return this.f1707a.m0();
        }

        @Override // android.support.v7.widget.i
        public int l() {
            return this.f1707a.U();
        }

        @Override // android.support.v7.widget.i
        public int m() {
            return this.f1707a.b0();
        }

        @Override // android.support.v7.widget.i
        public int n() {
            return (this.f1707a.l0() - this.f1707a.b0()) - this.f1707a.c0();
        }

        @Override // android.support.v7.widget.i
        public int p(View view) {
            this.f1707a.k0(view, true, this.f1709c);
            return this.f1709c.right;
        }

        @Override // android.support.v7.widget.i
        public int q(View view) {
            this.f1707a.k0(view, true, this.f1709c);
            return this.f1709c.left;
        }

        @Override // android.support.v7.widget.i
        public void r(int i2) {
            this.f1707a.v0(i2);
        }
    }

    public static class b extends i {
        public b(RecyclerView.n nVar) {
            super(nVar, null);
        }

        @Override // android.support.v7.widget.i
        public int d(View view) {
            return this.f1707a.L(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.o) view.getLayoutParams())).bottomMargin;
        }

        @Override // android.support.v7.widget.i
        public int e(View view) {
            RecyclerView.o oVar = (RecyclerView.o) view.getLayoutParams();
            return this.f1707a.O(view) + ((ViewGroup.MarginLayoutParams) oVar).topMargin + ((ViewGroup.MarginLayoutParams) oVar).bottomMargin;
        }

        @Override // android.support.v7.widget.i
        public int f(View view) {
            RecyclerView.o oVar = (RecyclerView.o) view.getLayoutParams();
            return this.f1707a.P(view) + ((ViewGroup.MarginLayoutParams) oVar).leftMargin + ((ViewGroup.MarginLayoutParams) oVar).rightMargin;
        }

        @Override // android.support.v7.widget.i
        public int g(View view) {
            return this.f1707a.R(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.o) view.getLayoutParams())).topMargin;
        }

        @Override // android.support.v7.widget.i
        public int h() {
            return this.f1707a.T();
        }

        @Override // android.support.v7.widget.i
        public int i() {
            return this.f1707a.T() - this.f1707a.a0();
        }

        @Override // android.support.v7.widget.i
        public int j() {
            return this.f1707a.a0();
        }

        @Override // android.support.v7.widget.i
        public int k() {
            return this.f1707a.U();
        }

        @Override // android.support.v7.widget.i
        public int l() {
            return this.f1707a.m0();
        }

        @Override // android.support.v7.widget.i
        public int m() {
            return this.f1707a.d0();
        }

        @Override // android.support.v7.widget.i
        public int n() {
            return (this.f1707a.T() - this.f1707a.d0()) - this.f1707a.a0();
        }

        @Override // android.support.v7.widget.i
        public int p(View view) {
            this.f1707a.k0(view, true, this.f1709c);
            return this.f1709c.bottom;
        }

        @Override // android.support.v7.widget.i
        public int q(View view) {
            this.f1707a.k0(view, true, this.f1709c);
            return this.f1709c.top;
        }

        @Override // android.support.v7.widget.i
        public void r(int i2) {
            this.f1707a.w0(i2);
        }
    }

    public /* synthetic */ i(RecyclerView.n nVar, a aVar) {
        this(nVar);
    }

    public static i a(RecyclerView.n nVar) {
        return new a(nVar);
    }

    public static i b(RecyclerView.n nVar, int i2) {
        if (i2 == 0) {
            return a(nVar);
        }
        if (i2 == 1) {
            return c(nVar);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public static i c(RecyclerView.n nVar) {
        return new b(nVar);
    }

    public abstract int d(View view);

    public abstract int e(View view);

    public abstract int f(View view);

    public abstract int g(View view);

    public abstract int h();

    public abstract int i();

    public abstract int j();

    public abstract int k();

    public abstract int l();

    public abstract int m();

    public abstract int n();

    public int o() {
        if (Integer.MIN_VALUE == this.f1708b) {
            return 0;
        }
        return n() - this.f1708b;
    }

    public abstract int p(View view);

    public abstract int q(View view);

    public abstract void r(int i2);

    public void s() {
        this.f1708b = n();
    }

    public i(RecyclerView.n nVar) {
        this.f1708b = PKIFailureInfo.systemUnavail;
        this.f1709c = new Rect();
        this.f1707a = nVar;
    }
}
