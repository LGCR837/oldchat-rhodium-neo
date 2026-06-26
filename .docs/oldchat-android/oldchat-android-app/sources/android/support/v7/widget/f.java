package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1681b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1682c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1683d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1684e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1687h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1688i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1680a = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1685f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1686g = 0;

    public boolean a(RecyclerView.y yVar) {
        int i2 = this.f1682c;
        return i2 >= 0 && i2 < yVar.b();
    }

    public View b(RecyclerView.u uVar) {
        View viewO = uVar.o(this.f1682c);
        this.f1682c += this.f1683d;
        return viewO;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.f1681b + ", mCurrentPosition=" + this.f1682c + ", mItemDirection=" + this.f1683d + ", mLayoutDirection=" + this.f1684e + ", mStartLine=" + this.f1685f + ", mEndLine=" + this.f1686g + '}';
    }
}
