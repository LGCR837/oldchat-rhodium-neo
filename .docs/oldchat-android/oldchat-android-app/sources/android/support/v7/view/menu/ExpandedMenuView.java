package android.support.v7.view.menu;

import android.R;
import android.content.Context;
import android.support.v7.view.menu.e;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import x.f0;

/* JADX INFO: loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements e.b, j, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f973c = {R.attr.background, R.attr.divider};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public e f974a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f975b;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listViewStyle);
    }

    @Override // android.support.v7.view.menu.e.b
    public boolean a(g gVar) {
        return this.f974a.K(gVar, 0);
    }

    @Override // android.support.v7.view.menu.j
    public void c(e eVar) {
        this.f974a = eVar;
    }

    public int getWindowAnimations() {
        return this.f975b;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
        a((g) getAdapter().getItem(i2));
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        f0 f0VarS = f0.s(context, attributeSet, f973c, i2, 0);
        if (f0VarS.p(0)) {
            setBackgroundDrawable(f0VarS.f(0));
        }
        if (f0VarS.p(1)) {
            setDivider(f0VarS.f(1));
        }
        f0VarS.t();
    }
}
