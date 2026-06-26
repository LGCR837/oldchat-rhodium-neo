package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class k {
    public static int a(RecyclerView.y yVar, i iVar, View view, View view2, RecyclerView.n nVar, boolean z2) {
        if (nVar.H() == 0 || yVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z2) {
            return Math.abs(nVar.e0(view) - nVar.e0(view2)) + 1;
        }
        return Math.min(iVar.n(), iVar.d(view2) - iVar.g(view));
    }

    public static int b(RecyclerView.y yVar, i iVar, View view, View view2, RecyclerView.n nVar, boolean z2, boolean z3) {
        if (nVar.H() == 0 || yVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int iMax = z3 ? Math.max(0, (yVar.b() - Math.max(nVar.e0(view), nVar.e0(view2))) - 1) : Math.max(0, Math.min(nVar.e0(view), nVar.e0(view2)));
        if (z2) {
            return Math.round((iMax * (Math.abs(iVar.d(view2) - iVar.g(view)) / (Math.abs(nVar.e0(view) - nVar.e0(view2)) + 1))) + (iVar.m() - iVar.g(view)));
        }
        return iMax;
    }

    public static int c(RecyclerView.y yVar, i iVar, View view, View view2, RecyclerView.n nVar, boolean z2) {
        if (nVar.H() == 0 || yVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z2) {
            return yVar.b();
        }
        return (int) (((iVar.d(view2) - iVar.g(view)) / (Math.abs(nVar.e0(view) - nVar.e0(view2)) + 1)) * yVar.b());
    }
}
